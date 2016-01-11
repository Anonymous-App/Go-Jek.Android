package com.mixpanel.android.mpmetrics;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build.VERSION;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"CommitPrefEdits"})
class PersistentIdentity
{
  private static final String LOGTAG = "MixpanelAPI.PIdentity";
  private static boolean sReferrerPrefsDirty = true;
  private static final Object sReferrerPrefsLock = new Object();
  private String mEventsDistinctId;
  private boolean mIdentitiesLoaded;
  private final Future<SharedPreferences> mLoadReferrerPreferences;
  private final Future<SharedPreferences> mLoadStoredPreferences;
  private String mPeopleDistinctId;
  private final SharedPreferences.OnSharedPreferenceChangeListener mReferrerChangeListener;
  private Map<String, String> mReferrerPropertiesCache;
  private JSONObject mSuperPropertiesCache;
  private JSONArray mWaitingPeopleRecords;
  
  public PersistentIdentity(Future<SharedPreferences> paramFuture1, Future<SharedPreferences> paramFuture2)
  {
    this.mLoadReferrerPreferences = paramFuture1;
    this.mLoadStoredPreferences = paramFuture2;
    this.mSuperPropertiesCache = null;
    this.mReferrerPropertiesCache = null;
    this.mIdentitiesLoaded = false;
    this.mReferrerChangeListener = new PersistentIdentity.1(this);
  }
  
  private JSONObject getSuperPropertiesCache()
  {
    if (this.mSuperPropertiesCache == null) {
      readSuperProperties();
    }
    return this.mSuperPropertiesCache;
  }
  
  private void readIdentities()
  {
    Object localObject = null;
    try
    {
      SharedPreferences localSharedPreferences = (SharedPreferences)this.mLoadStoredPreferences.get();
      localObject = localSharedPreferences;
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;)
      {
        Log.e("MixpanelAPI.PIdentity", "Cannot read distinct ids from sharedPreferences.", localExecutionException.getCause());
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        Log.e("MixpanelAPI.PIdentity", "Cannot read distinct ids from sharedPreferences.", localInterruptedException);
      }
      this.mEventsDistinctId = ((SharedPreferences)localObject).getString("events_distinct_id", null);
      this.mPeopleDistinctId = ((SharedPreferences)localObject).getString("people_distinct_id", null);
      this.mWaitingPeopleRecords = null;
      localObject = ((SharedPreferences)localObject).getString("waiting_array", null);
      if (localObject == null) {
        break label104;
      }
    }
    if (localObject == null) {
      return;
    }
    try
    {
      this.mWaitingPeopleRecords = JSONArrayInstrumentation.init((String)localObject);
      label104:
      if (this.mEventsDistinctId == null)
      {
        this.mEventsDistinctId = UUID.randomUUID().toString();
        writeIdentities();
      }
      this.mIdentitiesLoaded = true;
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Log.e("MixpanelAPI.PIdentity", "Could not interpret waiting people JSON record " + (String)localObject);
      }
    }
  }
  
  private void readReferrerProperties()
  {
    this.mReferrerPropertiesCache = new HashMap();
    try
    {
      Object localObject1 = (SharedPreferences)this.mLoadReferrerPreferences.get();
      ((SharedPreferences)localObject1).unregisterOnSharedPreferenceChangeListener(this.mReferrerChangeListener);
      ((SharedPreferences)localObject1).registerOnSharedPreferenceChangeListener(this.mReferrerChangeListener);
      localObject1 = ((SharedPreferences)localObject1).getAll().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (Map.Entry)((Iterator)localObject1).next();
        String str = (String)((Map.Entry)localObject2).getKey();
        localObject2 = ((Map.Entry)localObject2).getValue();
        this.mReferrerPropertiesCache.put(str, localObject2.toString());
      }
      return;
    }
    catch (ExecutionException localExecutionException)
    {
      Log.e("MixpanelAPI.PIdentity", "Cannot load referrer properties from shared preferences.", localExecutionException.getCause());
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      Log.e("MixpanelAPI.PIdentity", "Cannot load referrer properties from shared preferences.", localInterruptedException);
    }
  }
  
  private void readSuperProperties()
  {
    try
    {
      String str = ((SharedPreferences)this.mLoadStoredPreferences.get()).getString("super_properties", "{}");
      if (MPConfig.DEBUG) {
        Log.v("MixpanelAPI.PIdentity", "Loading Super Properties " + str);
      }
      this.mSuperPropertiesCache = JSONObjectInstrumentation.init(str);
      return;
    }
    catch (ExecutionException localExecutionException)
    {
      Log.e("MixpanelAPI.PIdentity", "Cannot load superProperties from SharedPreferences.", localExecutionException.getCause());
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      Log.e("MixpanelAPI.PIdentity", "Cannot load superProperties from SharedPreferences.", localInterruptedException);
      return;
    }
    catch (JSONException localJSONException)
    {
      Log.e("MixpanelAPI.PIdentity", "Cannot parse stored superProperties");
      storeSuperProperties();
      return;
    }
    finally
    {
      if (this.mSuperPropertiesCache == null) {
        this.mSuperPropertiesCache = new JSONObject();
      }
    }
  }
  
  private void storeSuperProperties()
  {
    if (this.mSuperPropertiesCache == null)
    {
      Log.e("MixpanelAPI.PIdentity", "storeSuperProperties should not be called with uninitialized superPropertiesCache.");
      return;
    }
    Object localObject = this.mSuperPropertiesCache;
    if (!(localObject instanceof JSONObject)) {
      localObject = ((JSONObject)localObject).toString();
    }
    for (;;)
    {
      if (MPConfig.DEBUG) {
        Log.v("MixpanelAPI.PIdentity", "Storing Super Properties " + (String)localObject);
      }
      try
      {
        SharedPreferences.Editor localEditor = ((SharedPreferences)this.mLoadStoredPreferences.get()).edit();
        localEditor.putString("super_properties", (String)localObject);
        writeEdits(localEditor);
        return;
      }
      catch (ExecutionException localExecutionException)
      {
        Log.e("MixpanelAPI.PIdentity", "Cannot store superProperties in shared preferences.", localExecutionException.getCause());
        return;
        String str = JSONObjectInstrumentation.toString((JSONObject)localExecutionException);
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.e("MixpanelAPI.PIdentity", "Cannot store superProperties in shared preferences.", localInterruptedException);
      }
    }
  }
  
  /* Error */
  public static JSONArray waitingPeopleRecordsForSending(SharedPreferences paramSharedPreferences)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: ldc 110
    //   5: aconst_null
    //   6: invokeinterface 106 3 0
    //   11: astore 4
    //   13: aload_0
    //   14: ldc 116
    //   16: aconst_null
    //   17: invokeinterface 106 3 0
    //   22: astore 5
    //   24: aload_3
    //   25: astore_2
    //   26: aload 5
    //   28: ifnull +114 -> 142
    //   31: aload_3
    //   32: astore_2
    //   33: aload 4
    //   35: ifnull +107 -> 142
    //   38: aload 5
    //   40: invokestatic 122	com/newrelic/agent/android/instrumentation/JSONArrayInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   43: astore_3
    //   44: new 257	org/json/JSONArray
    //   47: dup
    //   48: invokespecial 258	org/json/JSONArray:<init>	()V
    //   51: astore_2
    //   52: iconst_0
    //   53: istore_1
    //   54: iload_1
    //   55: aload_3
    //   56: invokevirtual 262	org/json/JSONArray:length	()I
    //   59: if_icmpge +63 -> 122
    //   62: aload_3
    //   63: iload_1
    //   64: invokevirtual 266	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   67: astore 5
    //   69: aload 5
    //   71: ldc_w 268
    //   74: aload 4
    //   76: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   79: pop
    //   80: aload_2
    //   81: aload 5
    //   83: invokevirtual 274	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   86: pop
    //   87: iload_1
    //   88: iconst_1
    //   89: iadd
    //   90: istore_1
    //   91: goto -37 -> 54
    //   94: astore_0
    //   95: ldc 11
    //   97: ldc_w 276
    //   100: invokestatic 148	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   103: pop
    //   104: aconst_null
    //   105: areturn
    //   106: astore 5
    //   108: ldc 11
    //   110: ldc_w 278
    //   113: aload 5
    //   115: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   118: pop
    //   119: goto -32 -> 87
    //   122: aload_0
    //   123: invokeinterface 238 1 0
    //   128: astore_0
    //   129: aload_0
    //   130: ldc 116
    //   132: invokeinterface 282 2 0
    //   137: pop
    //   138: aload_0
    //   139: invokestatic 248	com/mixpanel/android/mpmetrics/PersistentIdentity:writeEdits	(Landroid/content/SharedPreferences$Editor;)V
    //   142: aload_2
    //   143: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	144	0	paramSharedPreferences	SharedPreferences
    //   53	38	1	i	int
    //   25	118	2	localJSONArray1	JSONArray
    //   1	62	3	localJSONArray2	JSONArray
    //   11	64	4	str	String
    //   22	60	5	localObject	Object
    //   106	8	5	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   38	44	94	org/json/JSONException
    //   62	87	106	org/json/JSONException
  }
  
  @TargetApi(9)
  private static void writeEdits(SharedPreferences.Editor paramEditor)
  {
    if (Build.VERSION.SDK_INT >= 9)
    {
      paramEditor.apply();
      return;
    }
    paramEditor.commit();
  }
  
  private void writeIdentities()
  {
    for (;;)
    {
      try
      {
        localEditor = ((SharedPreferences)this.mLoadStoredPreferences.get()).edit();
        localEditor.putString("events_distinct_id", this.mEventsDistinctId);
        localEditor.putString("people_distinct_id", this.mPeopleDistinctId);
        if (this.mWaitingPeopleRecords == null)
        {
          localEditor.remove("waiting_array");
          writeEdits(localEditor);
          return;
        }
        localObject = this.mWaitingPeopleRecords;
        if ((localObject instanceof JSONArray)) {
          continue;
        }
        localObject = ((JSONArray)localObject).toString();
      }
      catch (ExecutionException localExecutionException)
      {
        SharedPreferences.Editor localEditor;
        Object localObject;
        Log.e("MixpanelAPI.PIdentity", "Can't write distinct ids to shared preferences.", localExecutionException.getCause());
        return;
        String str = JSONArrayInstrumentation.toString((JSONArray)localExecutionException);
        continue;
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.e("MixpanelAPI.PIdentity", "Can't write distinct ids to shared preferences.", localInterruptedException);
      }
      localEditor.putString("waiting_array", (String)localObject);
    }
  }
  
  public static void writeReferrerPrefs(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    synchronized (sReferrerPrefsLock)
    {
      paramContext = paramContext.getSharedPreferences(paramString, 0).edit();
      paramContext.clear();
      paramString = paramMap.entrySet().iterator();
      if (paramString.hasNext())
      {
        paramMap = (Map.Entry)paramString.next();
        paramContext.putString((String)paramMap.getKey(), (String)paramMap.getValue());
      }
    }
    writeEdits(paramContext);
    sReferrerPrefsDirty = true;
  }
  
  public void addSuperPropertiesToObject(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = getSuperPropertiesCache();
      Iterator localIterator = localJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          paramJSONObject.put(str, localJSONObject.get(str));
        }
        catch (JSONException localJSONException)
        {
          Log.wtf("MixpanelAPI.PIdentity", "Object read from one JSON Object cannot be written to another", localJSONException);
        }
      }
    }
    finally {}
  }
  
  /* Error */
  public void clearPreferences()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 46	com/mixpanel/android/mpmetrics/PersistentIdentity:mLoadStoredPreferences	Ljava/util/concurrent/Future;
    //   6: invokeinterface 86 1 0
    //   11: checkcast 88	android/content/SharedPreferences
    //   14: invokeinterface 238 1 0
    //   19: astore_1
    //   20: aload_1
    //   21: invokeinterface 314 1 0
    //   26: pop
    //   27: aload_1
    //   28: invokestatic 248	com/mixpanel/android/mpmetrics/PersistentIdentity:writeEdits	(Landroid/content/SharedPreferences$Editor;)V
    //   31: aload_0
    //   32: invokespecial 74	com/mixpanel/android/mpmetrics/PersistentIdentity:readSuperProperties	()V
    //   35: aload_0
    //   36: invokespecial 333	com/mixpanel/android/mpmetrics/PersistentIdentity:readIdentities	()V
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: astore_1
    //   43: new 335	java/lang/RuntimeException
    //   46: dup
    //   47: aload_1
    //   48: invokevirtual 94	java/util/concurrent/ExecutionException:getCause	()Ljava/lang/Throwable;
    //   51: invokespecial 338	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   54: athrow
    //   55: astore_1
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_1
    //   59: athrow
    //   60: astore_1
    //   61: new 335	java/lang/RuntimeException
    //   64: dup
    //   65: aload_1
    //   66: invokevirtual 339	java/lang/InterruptedException:getCause	()Ljava/lang/Throwable;
    //   69: invokespecial 338	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	PersistentIdentity
    //   19	9	1	localEditor	SharedPreferences.Editor
    //   42	6	1	localExecutionException	ExecutionException
    //   55	4	1	localObject	Object
    //   60	6	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	39	42	java/util/concurrent/ExecutionException
    //   2	39	55	finally
    //   43	55	55	finally
    //   61	73	55	finally
    //   2	39	60	java/lang/InterruptedException
  }
  
  /* Error */
  public void clearPushId()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 46	com/mixpanel/android/mpmetrics/PersistentIdentity:mLoadStoredPreferences	Ljava/util/concurrent/Future;
    //   6: invokeinterface 86 1 0
    //   11: checkcast 88	android/content/SharedPreferences
    //   14: invokeinterface 238 1 0
    //   19: astore_1
    //   20: aload_1
    //   21: ldc_w 342
    //   24: invokeinterface 282 2 0
    //   29: pop
    //   30: aload_1
    //   31: invokestatic 248	com/mixpanel/android/mpmetrics/PersistentIdentity:writeEdits	(Landroid/content/SharedPreferences$Editor;)V
    //   34: aload_0
    //   35: monitorexit
    //   36: return
    //   37: astore_1
    //   38: ldc 11
    //   40: ldc_w 344
    //   43: aload_1
    //   44: invokevirtual 94	java/util/concurrent/ExecutionException:getCause	()Ljava/lang/Throwable;
    //   47: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   50: pop
    //   51: goto -17 -> 34
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    //   59: astore_1
    //   60: ldc 11
    //   62: ldc_w 344
    //   65: aload_1
    //   66: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   69: pop
    //   70: goto -36 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	PersistentIdentity
    //   19	12	1	localEditor	SharedPreferences.Editor
    //   37	7	1	localExecutionException	ExecutionException
    //   54	4	1	localObject	Object
    //   59	7	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	34	37	java/util/concurrent/ExecutionException
    //   2	34	54	finally
    //   38	51	54	finally
    //   60	70	54	finally
    //   2	34	59	java/lang/InterruptedException
  }
  
  public void clearSuperProperties()
  {
    try
    {
      this.mSuperPropertiesCache = new JSONObject();
      storeSuperProperties();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getEventsDistinctId()
  {
    try
    {
      if (!this.mIdentitiesLoaded) {
        readIdentities();
      }
      String str = this.mEventsDistinctId;
      return str;
    }
    finally {}
  }
  
  public String getPeopleDistinctId()
  {
    try
    {
      if (!this.mIdentitiesLoaded) {
        readIdentities();
      }
      String str = this.mPeopleDistinctId;
      return str;
    }
    finally {}
  }
  
  /* Error */
  public String getPushId()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore_1
    //   4: aload_0
    //   5: getfield 46	com/mixpanel/android/mpmetrics/PersistentIdentity:mLoadStoredPreferences	Ljava/util/concurrent/Future;
    //   8: invokeinterface 86 1 0
    //   13: checkcast 88	android/content/SharedPreferences
    //   16: ldc_w 342
    //   19: aconst_null
    //   20: invokeinterface 106 3 0
    //   25: astore_2
    //   26: aload_2
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: areturn
    //   32: astore_2
    //   33: ldc 11
    //   35: ldc_w 344
    //   38: aload_2
    //   39: invokevirtual 94	java/util/concurrent/ExecutionException:getCause	()Ljava/lang/Throwable;
    //   42: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   45: pop
    //   46: goto -18 -> 28
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    //   54: astore_2
    //   55: ldc 11
    //   57: ldc_w 344
    //   60: aload_2
    //   61: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   64: pop
    //   65: goto -37 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	PersistentIdentity
    //   3	28	1	localObject1	Object
    //   49	4	1	localObject2	Object
    //   25	2	2	str	String
    //   32	7	2	localExecutionException	ExecutionException
    //   54	7	2	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   4	26	32	java/util/concurrent/ExecutionException
    //   4	26	49	finally
    //   33	46	49	finally
    //   55	65	49	finally
    //   4	26	54	java/lang/InterruptedException
  }
  
  public Map<String, String> getReferrerProperties()
  {
    synchronized (sReferrerPrefsLock)
    {
      if ((sReferrerPrefsDirty) || (this.mReferrerPropertiesCache == null))
      {
        readReferrerProperties();
        sReferrerPrefsDirty = false;
      }
      return this.mReferrerPropertiesCache;
    }
  }
  
  public void registerSuperProperties(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = getSuperPropertiesCache();
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          localJSONObject.put(str, paramJSONObject.get(str));
        }
        catch (JSONException localJSONException)
        {
          Log.e("MixpanelAPI.PIdentity", "Exception registering super property.", localJSONException);
        }
      }
      storeSuperProperties();
    }
    finally {}
  }
  
  public void registerSuperPropertiesOnce(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = getSuperPropertiesCache();
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        boolean bool = localJSONObject.has(str);
        if (!bool) {
          try
          {
            localJSONObject.put(str, paramJSONObject.get(str));
          }
          catch (JSONException localJSONException)
          {
            Log.e("MixpanelAPI.PIdentity", "Exception registering super property.", localJSONException);
          }
        }
      }
      storeSuperProperties();
    }
    finally {}
  }
  
  public void setEventsDistinctId(String paramString)
  {
    try
    {
      if (!this.mIdentitiesLoaded) {
        readIdentities();
      }
      this.mEventsDistinctId = paramString;
      writeIdentities();
      return;
    }
    finally {}
  }
  
  public void setPeopleDistinctId(String paramString)
  {
    try
    {
      if (!this.mIdentitiesLoaded) {
        readIdentities();
      }
      this.mPeopleDistinctId = paramString;
      writeIdentities();
      return;
    }
    finally {}
  }
  
  /* Error */
  public void storePushId(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 46	com/mixpanel/android/mpmetrics/PersistentIdentity:mLoadStoredPreferences	Ljava/util/concurrent/Future;
    //   6: invokeinterface 86 1 0
    //   11: checkcast 88	android/content/SharedPreferences
    //   14: invokeinterface 238 1 0
    //   19: astore_2
    //   20: aload_2
    //   21: ldc_w 342
    //   24: aload_1
    //   25: invokeinterface 244 3 0
    //   30: pop
    //   31: aload_2
    //   32: invokestatic 248	com/mixpanel/android/mpmetrics/PersistentIdentity:writeEdits	(Landroid/content/SharedPreferences$Editor;)V
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: astore_1
    //   39: ldc 11
    //   41: ldc_w 344
    //   44: aload_1
    //   45: invokevirtual 94	java/util/concurrent/ExecutionException:getCause	()Ljava/lang/Throwable;
    //   48: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   51: pop
    //   52: goto -17 -> 35
    //   55: astore_1
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_1
    //   59: athrow
    //   60: astore_1
    //   61: ldc 11
    //   63: ldc_w 344
    //   66: aload_1
    //   67: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   70: pop
    //   71: goto -36 -> 35
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	PersistentIdentity
    //   0	74	1	paramString	String
    //   19	13	2	localEditor	SharedPreferences.Editor
    // Exception table:
    //   from	to	target	type
    //   2	35	38	java/util/concurrent/ExecutionException
    //   2	35	55	finally
    //   39	52	55	finally
    //   61	71	55	finally
    //   2	35	60	java/lang/InterruptedException
  }
  
  public void storeWaitingPeopleRecord(JSONObject paramJSONObject)
  {
    try
    {
      if (!this.mIdentitiesLoaded) {
        readIdentities();
      }
      if (this.mWaitingPeopleRecords == null) {
        this.mWaitingPeopleRecords = new JSONArray();
      }
      this.mWaitingPeopleRecords.put(paramJSONObject);
      writeIdentities();
      return;
    }
    finally {}
  }
  
  public void unregisterSuperProperty(String paramString)
  {
    try
    {
      getSuperPropertiesCache().remove(paramString);
      storeSuperProperties();
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void updateSuperProperties(SuperPropertyUpdate paramSuperPropertyUpdate)
  {
    for (;;)
    {
      JSONObject localJSONObject2;
      try
      {
        JSONObject localJSONObject1 = getSuperPropertiesCache();
        localJSONObject2 = new JSONObject();
        try
        {
          Iterator localIterator = localJSONObject1.keys();
          if (!localIterator.hasNext()) {
            continue;
          }
          String str = (String)localIterator.next();
          localJSONObject2.put(str, localJSONObject1.get(str));
          continue;
        }
        catch (JSONException paramSuperPropertyUpdate)
        {
          Log.wtf("MixpanelAPI.PIdentity", "Can't copy from one JSONObject to another", paramSuperPropertyUpdate);
        }
        return;
      }
      finally {}
      paramSuperPropertyUpdate = paramSuperPropertyUpdate.update(localJSONObject2);
      if (paramSuperPropertyUpdate == null)
      {
        Log.w("MixpanelAPI.PIdentity", "An update to Mixpanel's super properties returned null, and will have no effect.");
      }
      else
      {
        this.mSuperPropertiesCache = paramSuperPropertyUpdate;
        storeSuperProperties();
      }
    }
  }
  
  /* Error */
  public JSONArray waitingPeopleRecordsForSending()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_1
    //   6: aload_0
    //   7: getfield 46	com/mixpanel/android/mpmetrics/PersistentIdentity:mLoadStoredPreferences	Ljava/util/concurrent/Future;
    //   10: invokeinterface 86 1 0
    //   15: checkcast 88	android/content/SharedPreferences
    //   18: invokestatic 384	com/mixpanel/android/mpmetrics/PersistentIdentity:waitingPeopleRecordsForSending	(Landroid/content/SharedPreferences;)Lorg/json/JSONArray;
    //   21: astore_3
    //   22: aload_3
    //   23: astore_1
    //   24: aload_3
    //   25: astore_2
    //   26: aload_0
    //   27: invokespecial 333	com/mixpanel/android/mpmetrics/PersistentIdentity:readIdentities	()V
    //   30: aload_3
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: areturn
    //   36: astore_2
    //   37: ldc 11
    //   39: ldc_w 386
    //   42: aload_2
    //   43: invokevirtual 94	java/util/concurrent/ExecutionException:getCause	()Ljava/lang/Throwable;
    //   46: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   49: pop
    //   50: goto -18 -> 32
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    //   58: astore_1
    //   59: ldc 11
    //   61: ldc_w 386
    //   64: aload_1
    //   65: invokestatic 100	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   68: pop
    //   69: aload_2
    //   70: astore_1
    //   71: goto -39 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	PersistentIdentity
    //   5	30	1	localObject1	Object
    //   53	4	1	localObject2	Object
    //   58	7	1	localInterruptedException	InterruptedException
    //   70	1	1	localExecutionException1	ExecutionException
    //   3	23	2	localObject3	Object
    //   36	34	2	localExecutionException2	ExecutionException
    //   21	10	3	localJSONArray	JSONArray
    // Exception table:
    //   from	to	target	type
    //   6	22	36	java/util/concurrent/ExecutionException
    //   26	30	36	java/util/concurrent/ExecutionException
    //   6	22	53	finally
    //   26	30	53	finally
    //   37	50	53	finally
    //   59	69	53	finally
    //   6	22	58	java/lang/InterruptedException
    //   26	30	58	java/lang/InterruptedException
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/mpmetrics/PersistentIdentity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */