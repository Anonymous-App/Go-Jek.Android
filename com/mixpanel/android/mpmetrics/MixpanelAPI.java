package com.mixpanel.android.mpmetrics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import com.mixpanel.android.viewcrawler.TrackingDebug;
import com.mixpanel.android.viewcrawler.UpdatesFromMixpanel;
import com.mixpanel.android.viewcrawler.ViewCrawler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MixpanelAPI
{
  private static final String APP_LINKS_LOGTAG = "MixpanelAPI.AL";
  private static final String ENGAGE_DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss";
  private static final String LOGTAG = "MixpanelAPI.API";
  public static final String VERSION = "4.6.4";
  private static final Map<String, Map<Context, MixpanelAPI>> sInstanceMap = new HashMap();
  private static final SharedPreferencesLoader sPrefsLoader = new SharedPreferencesLoader();
  private static Future<SharedPreferences> sReferrerPrefs;
  private static final Tweaks sSharedTweaks = new Tweaks();
  private final MPConfig mConfig;
  private final Context mContext;
  private final DecideMessages mDecideMessages;
  private final Map<String, String> mDeviceInfo;
  private final Map<String, Long> mEventTimings;
  private final AnalyticsMessages mMessages;
  private final PeopleImpl mPeople;
  private final PersistentIdentity mPersistentIdentity;
  private final String mToken;
  private final TrackingDebug mTrackingDebug;
  private final UpdatesFromMixpanel mUpdatesFromMixpanel;
  private final UpdatesListener mUpdatesListener;
  
  MixpanelAPI(Context paramContext, Future<SharedPreferences> paramFuture, String paramString)
  {
    this.mContext = paramContext;
    this.mToken = paramString;
    this.mEventTimings = new HashMap();
    this.mPeople = new PeopleImpl(null);
    this.mConfig = getConfig();
    HashMap localHashMap = new HashMap();
    localHashMap.put("$android_lib_version", "4.6.4");
    localHashMap.put("$android_os", "Android");
    Object localObject;
    if (Build.VERSION.RELEASE == null) {
      localObject = "UNKNOWN";
    }
    for (;;)
    {
      localHashMap.put("$android_os_version", localObject);
      if (Build.MANUFACTURER == null)
      {
        localObject = "UNKNOWN";
        label111:
        localHashMap.put("$android_manufacturer", localObject);
        if (Build.BRAND != null) {
          break label377;
        }
        localObject = "UNKNOWN";
        label133:
        localHashMap.put("$android_brand", localObject);
        if (Build.MODEL != null) {
          break label385;
        }
        localObject = "UNKNOWN";
        localHashMap.put("$android_model", localObject);
      }
      try
      {
        localObject = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
        localHashMap.put("$android_app_version", ((PackageInfo)localObject).versionName);
        localHashMap.put("$android_app_version_code", Integer.toString(((PackageInfo)localObject).versionCode));
        this.mDeviceInfo = Collections.unmodifiableMap(localHashMap);
        this.mUpdatesFromMixpanel = constructUpdatesFromMixpanel(paramContext, paramString);
        this.mTrackingDebug = constructTrackingDebug();
        this.mPersistentIdentity = getPersistentIdentity(paramContext, paramFuture, paramString);
        this.mUpdatesListener = constructUpdatesListener();
        this.mDecideMessages = constructDecideUpdates(paramString, this.mUpdatesListener, this.mUpdatesFromMixpanel);
        paramFuture = this.mPersistentIdentity.getPeopleDistinctId();
        paramContext = paramFuture;
        if (paramFuture == null) {
          paramContext = this.mPersistentIdentity.getEventsDistinctId();
        }
        this.mDecideMessages.setDistinctId(paramContext);
        this.mMessages = getAnalyticsMessages();
        this.mMessages.installDecideCheck(this.mDecideMessages);
        registerMixpanelActivityLifecycleCallbacks();
        if (sendAppOpen()) {
          track("$app_open", null);
        }
        this.mUpdatesFromMixpanel.startUpdates();
        return;
        localObject = Build.VERSION.RELEASE;
        continue;
        localObject = Build.MANUFACTURER;
        break label111;
        label377:
        localObject = Build.BRAND;
        break label133;
        label385:
        localObject = Build.MODEL;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          Log.e("MixpanelAPI.API", "Exception getting app version name", localNameNotFoundException);
        }
      }
    }
  }
  
  static void allInstances(InstanceProcessor paramInstanceProcessor)
  {
    synchronized (sInstanceMap)
    {
      Iterator localIterator1 = sInstanceMap.values().iterator();
      while (localIterator1.hasNext())
      {
        Iterator localIterator2 = ((Map)localIterator1.next()).values().iterator();
        if (localIterator2.hasNext()) {
          paramInstanceProcessor.process((MixpanelAPI)localIterator2.next());
        }
      }
    }
  }
  
  public static Tweak<Boolean> booleanTweak(String paramString, boolean paramBoolean)
  {
    return sSharedTweaks.booleanTweak(paramString, paramBoolean);
  }
  
  public static Tweak<Byte> byteTweak(String paramString, byte paramByte)
  {
    return sSharedTweaks.byteTweak(paramString, paramByte);
  }
  
  private static void checkIntentForInboundAppLink(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      try
      {
        Class localClass = Class.forName("bolts.AppLinks");
        Intent localIntent = ((Activity)paramContext).getIntent();
        localClass.getMethod("getTargetUrlFromInboundIntent", new Class[] { Context.class, Intent.class }).invoke(null, new Object[] { paramContext, localIntent });
        return;
      }
      catch (InvocationTargetException paramContext)
      {
        Log.d("MixpanelAPI.AL", "Failed to invoke bolts.AppLinks.getTargetUrlFromInboundIntent() -- Unable to detect inbound App Links", paramContext);
        return;
      }
      catch (ClassNotFoundException paramContext)
      {
        Log.d("MixpanelAPI.AL", "Please install the Bolts library >= 1.1.2 to track App Links: " + paramContext.getMessage());
        return;
      }
      catch (NoSuchMethodException paramContext)
      {
        Log.d("MixpanelAPI.AL", "Please install the Bolts library >= 1.1.2 to track App Links: " + paramContext.getMessage());
        return;
      }
      catch (IllegalAccessException paramContext)
      {
        Log.d("MixpanelAPI.AL", "Unable to detect inbound App Links: " + paramContext.getMessage());
        return;
      }
    }
    Log.d("MixpanelAPI.AL", "Context is not an instance of Activity. To detect inbound App Links, pass an instance of an Activity to getInstance.");
  }
  
  public static Tweak<Double> doubleTweak(String paramString, double paramDouble)
  {
    return sSharedTweaks.doubleTweak(paramString, paramDouble);
  }
  
  @Deprecated
  public static void enableFallbackServer(Context paramContext, boolean paramBoolean)
  {
    Log.i("MixpanelAPI.API", "MixpanelAPI.enableFallbackServer is deprecated. This call is a no-op.\n    To enable fallback in your application, add\n    <meta-data android:name=\"com.mixpanel.android.MPConfig.DisableFallback\" android:value=\"false\" />\n    to the <application> section of your AndroidManifest.xml.");
  }
  
  public static Tweak<Float> floatTweak(String paramString, float paramFloat)
  {
    return sSharedTweaks.floatTweak(paramString, paramFloat);
  }
  
  public static MixpanelAPI getInstance(Context paramContext, String paramString)
  {
    if ((paramString == null) || (paramContext == null)) {
      return null;
    }
    synchronized (sInstanceMap)
    {
      Context localContext = paramContext.getApplicationContext();
      if (sReferrerPrefs == null) {
        sReferrerPrefs = sPrefsLoader.loadPreferences(paramContext, "com.mixpanel.android.mpmetrics.ReferralInfo", null);
      }
      Object localObject2 = (Map)sInstanceMap.get(paramString);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new HashMap();
        sInstanceMap.put(paramString, localObject1);
      }
      MixpanelAPI localMixpanelAPI = (MixpanelAPI)((Map)localObject1).get(localContext);
      localObject2 = localMixpanelAPI;
      if (localMixpanelAPI == null)
      {
        localObject2 = localMixpanelAPI;
        if (ConfigurationChecker.checkBasicConfiguration(localContext))
        {
          localObject2 = new MixpanelAPI(localContext, sReferrerPrefs, paramString);
          registerAppLinksListeners(paramContext, (MixpanelAPI)localObject2);
          ((Map)localObject1).put(localContext, localObject2);
        }
      }
      checkIntentForInboundAppLink(paramContext);
      return (MixpanelAPI)localObject2;
    }
  }
  
  public static Tweak<Integer> intTweak(String paramString, int paramInt)
  {
    return sSharedTweaks.intTweak(paramString, paramInt);
  }
  
  public static Tweak<Long> longTweak(String paramString, long paramLong)
  {
    return sSharedTweaks.longTweak(paramString, paramLong);
  }
  
  private void pushWaitingPeopleRecord()
  {
    JSONArray localJSONArray = this.mPersistentIdentity.waitingPeopleRecordsForSending();
    if (localJSONArray != null) {
      sendAllPeopleRecords(localJSONArray);
    }
  }
  
  private void recordPeopleMessage(JSONObject paramJSONObject)
  {
    if (paramJSONObject.has("$distinct_id"))
    {
      this.mMessages.peopleMessage(paramJSONObject);
      return;
    }
    this.mPersistentIdentity.storeWaitingPeopleRecord(paramJSONObject);
  }
  
  private static void registerAppLinksListeners(Context paramContext, MixpanelAPI paramMixpanelAPI)
  {
    try
    {
      Class localClass = Class.forName("android.support.v4.content.LocalBroadcastManager");
      Method localMethod = localClass.getMethod("getInstance", new Class[] { Context.class });
      localClass.getMethod("registerReceiver", new Class[] { BroadcastReceiver.class, IntentFilter.class }).invoke(localMethod.invoke(null, new Object[] { paramContext }), new Object[] { new BroadcastReceiver()new IntentFilter
      {
        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          paramAnonymousContext = new JSONObject();
          Bundle localBundle = paramAnonymousIntent.getBundleExtra("event_args");
          if (localBundle != null)
          {
            Iterator localIterator = localBundle.keySet().iterator();
            while (localIterator.hasNext())
            {
              String str = (String)localIterator.next();
              try
              {
                paramAnonymousContext.put(str, localBundle.get(str));
              }
              catch (JSONException localJSONException)
              {
                Log.e("MixpanelAPI.AL", "failed to add key \"" + str + "\" to properties for tracking bolts event", localJSONException);
              }
            }
          }
          this.val$mixpanel.track("$" + paramAnonymousIntent.getStringExtra("event_name"), paramAnonymousContext);
        }
      }, new IntentFilter("com.parse.bolts.measurement_event") });
      return;
    }
    catch (InvocationTargetException paramContext)
    {
      Log.d("MixpanelAPI.AL", "Failed to invoke LocalBroadcastManager.registerReceiver() -- App Links tracking will not be enabled due to this exception", paramContext);
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      Log.d("MixpanelAPI.AL", "To enable App Links tracking android.support.v4 must be installed: " + paramContext.getMessage());
      return;
    }
    catch (NoSuchMethodException paramContext)
    {
      Log.d("MixpanelAPI.AL", "To enable App Links tracking android.support.v4 must be installed: " + paramContext.getMessage());
      return;
    }
    catch (IllegalAccessException paramContext)
    {
      Log.d("MixpanelAPI.AL", "App Links tracking will not be enabled due to this exception: " + paramContext.getMessage());
    }
  }
  
  private void sendAllPeopleRecords(JSONArray paramJSONArray)
  {
    int i = 0;
    for (;;)
    {
      if (i < paramJSONArray.length()) {
        try
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
          this.mMessages.peopleMessage(localJSONObject);
          i += 1;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            Log.e("MixpanelAPI.API", "Malformed people record stored pending identity, will not send it.", localJSONException);
          }
        }
      }
    }
  }
  
  @Deprecated
  public static void setFlushInterval(Context paramContext, long paramLong)
  {
    Log.i("MixpanelAPI.API", "MixpanelAPI.setFlushInterval is deprecated. Calling is now a no-op.\n    To set a custom Mixpanel flush interval for your application, add\n    <meta-data android:name=\"com.mixpanel.android.MPConfig.FlushInterval\" android:value=\"YOUR_INTERVAL\" />\n    to the <application> section of your AndroidManifest.xml.");
  }
  
  public static Tweak<Short> shortTweak(String paramString, short paramShort)
  {
    return sSharedTweaks.shortTweak(paramString, paramShort);
  }
  
  public static Tweak<String> stringTweak(String paramString1, String paramString2)
  {
    return sSharedTweaks.stringTweak(paramString1, paramString2);
  }
  
  public void alias(String paramString1, String paramString2)
  {
    String str = paramString2;
    if (paramString2 == null) {
      str = getDistinctId();
    }
    if (paramString1.equals(str))
    {
      Log.w("MixpanelAPI.API", "Attempted to alias identical distinct_ids " + paramString1 + ". Alias message will not be sent.");
      return;
    }
    try
    {
      paramString2 = new JSONObject();
      paramString2.put("alias", paramString1);
      paramString2.put("original", str);
      track("$create_alias", paramString2);
      flush();
      return;
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        Log.e("MixpanelAPI.API", "Failed to alias", paramString1);
      }
    }
  }
  
  public void clearSuperProperties()
  {
    this.mPersistentIdentity.clearSuperProperties();
  }
  
  DecideMessages constructDecideUpdates(String paramString, DecideMessages.OnNewResultsListener paramOnNewResultsListener, UpdatesFromMixpanel paramUpdatesFromMixpanel)
  {
    return new DecideMessages(paramString, paramOnNewResultsListener, paramUpdatesFromMixpanel);
  }
  
  TrackingDebug constructTrackingDebug()
  {
    if ((this.mUpdatesFromMixpanel instanceof ViewCrawler)) {
      return (TrackingDebug)this.mUpdatesFromMixpanel;
    }
    return null;
  }
  
  UpdatesFromMixpanel constructUpdatesFromMixpanel(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      Log.i("MixpanelAPI.API", "Web Configuration, A/B Testing, and Dynamic Tweaks are not supported on this Android OS Version");
      return new UnsupportedUpdatesFromMixpanel(sSharedTweaks);
    }
    return new ViewCrawler(this.mContext, this.mToken, this, sSharedTweaks);
  }
  
  UpdatesListener constructUpdatesListener()
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      Log.i("MixpanelAPI.API", "Surveys and Notifications are not supported on this Android OS Version");
      return new UnsupportedUpdatesListener(null);
    }
    return new SupportedUpdatesListener(null);
  }
  
  public void flush()
  {
    this.mMessages.postToServer();
  }
  
  AnalyticsMessages getAnalyticsMessages()
  {
    return AnalyticsMessages.getInstance(this.mContext);
  }
  
  MPConfig getConfig()
  {
    return MPConfig.getInstance(this.mContext);
  }
  
  public Map<String, String> getDeviceInfo()
  {
    return this.mDeviceInfo;
  }
  
  public String getDistinctId()
  {
    return this.mPersistentIdentity.getEventsDistinctId();
  }
  
  public People getPeople()
  {
    return this.mPeople;
  }
  
  PersistentIdentity getPersistentIdentity(Context paramContext, Future<SharedPreferences> paramFuture, String paramString)
  {
    SharedPreferencesLoader.OnPrefsLoadedListener local1 = new SharedPreferencesLoader.OnPrefsLoadedListener()
    {
      public void onPrefsLoaded(SharedPreferences paramAnonymousSharedPreferences)
      {
        paramAnonymousSharedPreferences = PersistentIdentity.waitingPeopleRecordsForSending(paramAnonymousSharedPreferences);
        if (paramAnonymousSharedPreferences != null) {
          MixpanelAPI.this.sendAllPeopleRecords(paramAnonymousSharedPreferences);
        }
      }
    };
    paramString = "com.mixpanel.android.mpmetrics.MixpanelAPI_" + paramString;
    return new PersistentIdentity(paramFuture, sPrefsLoader.loadPreferences(paramContext, paramString, local1));
  }
  
  public JSONObject getSuperProperties()
  {
    JSONObject localJSONObject = new JSONObject();
    this.mPersistentIdentity.addSuperPropertiesToObject(localJSONObject);
    return localJSONObject;
  }
  
  public void identify(String paramString)
  {
    synchronized (this.mPersistentIdentity)
    {
      this.mPersistentIdentity.setEventsDistinctId(paramString);
      String str = this.mPersistentIdentity.getPeopleDistinctId();
      paramString = str;
      if (str == null) {
        paramString = this.mPersistentIdentity.getEventsDistinctId();
      }
      this.mDecideMessages.setDistinctId(paramString);
      return;
    }
  }
  
  @Deprecated
  public void logPosts()
  {
    Log.i("MixpanelAPI.API", "MixpanelAPI.logPosts() is deprecated.\n    To get verbose debug level logging, add\n    <meta-data android:name=\"com.mixpanel.android.MPConfig.EnableDebugLogging\" value=\"true\" />\n    to the <application> section of your AndroidManifest.xml.");
  }
  
  @TargetApi(16)
  void registerMixpanelActivityLifecycleCallbacks()
  {
    if ((Build.VERSION.SDK_INT >= 16) && (this.mConfig.getAutoShowMixpanelUpdates()))
    {
      if ((this.mContext.getApplicationContext() instanceof Application)) {
        ((Application)this.mContext.getApplicationContext()).registerActivityLifecycleCallbacks(new MixpanelActivityLifecycleCallbacks(this));
      }
    }
    else {
      return;
    }
    Log.i("MixpanelAPI.API", "Context is not an Application, Mixpanel will not automatically show surveys, in-app notifications, or A/B test experiments.");
  }
  
  public void registerSuperProperties(JSONObject paramJSONObject)
  {
    this.mPersistentIdentity.registerSuperProperties(paramJSONObject);
  }
  
  public void registerSuperPropertiesMap(Map<String, Object> paramMap)
  {
    if (paramMap == null)
    {
      Log.e("MixpanelAPI.API", "registerSuperPropertiesMap does not accept null properties");
      return;
    }
    try
    {
      registerSuperProperties(new JSONObject(paramMap));
      return;
    }
    catch (NullPointerException paramMap)
    {
      Log.w("MixpanelAPI.API", "Can't have null keys in the properties of registerSuperPropertiesMap!");
    }
  }
  
  public void registerSuperPropertiesOnce(JSONObject paramJSONObject)
  {
    this.mPersistentIdentity.registerSuperPropertiesOnce(paramJSONObject);
  }
  
  public void registerSuperPropertiesOnceMap(Map<String, Object> paramMap)
  {
    if (paramMap == null)
    {
      Log.e("MixpanelAPI.API", "registerSuperPropertiesOnceMap does not accept null properties");
      return;
    }
    try
    {
      registerSuperPropertiesOnce(new JSONObject(paramMap));
      return;
    }
    catch (NullPointerException paramMap)
    {
      Log.w("MixpanelAPI.API", "Can't have null keys in the properties of registerSuperPropertiesOnce!");
    }
  }
  
  public void reset()
  {
    this.mPersistentIdentity.clearPreferences();
  }
  
  boolean sendAppOpen()
  {
    return !this.mConfig.getDisableAppOpenEvent();
  }
  
  public void timeEvent(String paramString)
  {
    long l = System.currentTimeMillis();
    synchronized (this.mEventTimings)
    {
      this.mEventTimings.put(paramString, Long.valueOf(l));
      return;
    }
  }
  
  public void track(String paramString)
  {
    track(paramString, null);
  }
  
  public void track(String paramString, JSONObject paramJSONObject)
  {
    do
    {
      Object localObject2;
      Object localObject3;
      synchronized (this.mEventTimings)
      {
        localObject2 = (Long)this.mEventTimings.get(paramString);
        this.mEventTimings.remove(paramString);
        try
        {
          ??? = new JSONObject();
          localObject3 = this.mPersistentIdentity.getReferrerProperties().entrySet().iterator();
          while (((Iterator)localObject3).hasNext())
          {
            Map.Entry localEntry = (Map.Entry)((Iterator)localObject3).next();
            ((JSONObject)???).put((String)localEntry.getKey(), (String)localEntry.getValue());
            continue;
            paramString = finally;
          }
        }
        catch (JSONException paramJSONObject)
        {
          Log.e("MixpanelAPI.API", "Exception tracking event " + paramString, paramJSONObject);
          return;
        }
      }
      this.mPersistentIdentity.addSuperPropertiesToObject((JSONObject)???);
      double d = System.currentTimeMillis() / 1000.0D;
      ((JSONObject)???).put("time", d);
      ((JSONObject)???).put("distinct_id", getDistinctId());
      if (localObject2 != null) {
        ((JSONObject)???).put("$duration", d - ((Long)localObject2).longValue() / 1000.0D);
      }
      if (paramJSONObject != null)
      {
        localObject2 = paramJSONObject.keys();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          ((JSONObject)???).put((String)localObject3, paramJSONObject.get((String)localObject3));
        }
      }
      paramJSONObject = new AnalyticsMessages.EventDescription(paramString, (JSONObject)???, this.mToken);
      this.mMessages.eventsMessage(paramJSONObject);
    } while (this.mTrackingDebug == null);
    this.mTrackingDebug.reportTrack(paramString);
  }
  
  public void trackMap(String paramString, Map<String, Object> paramMap)
  {
    if (paramMap == null)
    {
      track(paramString, null);
      return;
    }
    try
    {
      track(paramString, new JSONObject(paramMap));
      return;
    }
    catch (NullPointerException paramString)
    {
      Log.w("MixpanelAPI.API", "Can't have null keys in the properties of trackMap!");
    }
  }
  
  public void unregisterSuperProperty(String paramString)
  {
    this.mPersistentIdentity.unregisterSuperProperty(paramString);
  }
  
  public void updateSuperProperties(SuperPropertyUpdate paramSuperPropertyUpdate)
  {
    this.mPersistentIdentity.updateSuperProperties(paramSuperPropertyUpdate);
  }
  
  static abstract interface InstanceProcessor
  {
    public abstract void process(MixpanelAPI paramMixpanelAPI);
  }
  
  public static abstract interface People
  {
    public abstract void addOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener);
    
    public abstract void append(String paramString, Object paramObject);
    
    @Deprecated
    public abstract void checkForSurvey(SurveyCallbacks paramSurveyCallbacks);
    
    @Deprecated
    public abstract void checkForSurvey(SurveyCallbacks paramSurveyCallbacks, Activity paramActivity);
    
    public abstract void clearCharges();
    
    public abstract void clearPushRegistrationId();
    
    public abstract void deleteUser();
    
    public abstract String getDistinctId();
    
    public abstract InAppNotification getNotificationIfAvailable();
    
    public abstract Survey getSurveyIfAvailable();
    
    public abstract void identify(String paramString);
    
    public abstract void increment(String paramString, double paramDouble);
    
    public abstract void increment(Map<String, ? extends Number> paramMap);
    
    public abstract void initPushHandling(String paramString);
    
    public abstract void joinExperimentIfAvailable();
    
    public abstract void merge(String paramString, JSONObject paramJSONObject);
    
    public abstract void removeOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener);
    
    public abstract void set(String paramString, Object paramObject);
    
    public abstract void set(JSONObject paramJSONObject);
    
    public abstract void setMap(Map<String, Object> paramMap);
    
    public abstract void setOnce(String paramString, Object paramObject);
    
    public abstract void setOnce(JSONObject paramJSONObject);
    
    public abstract void setOnceMap(Map<String, Object> paramMap);
    
    public abstract void setPushRegistrationId(String paramString);
    
    public abstract void showGivenNotification(InAppNotification paramInAppNotification, Activity paramActivity);
    
    public abstract void showNotificationById(int paramInt, Activity paramActivity);
    
    public abstract void showNotificationIfAvailable(Activity paramActivity);
    
    @Deprecated
    public abstract void showSurvey(Survey paramSurvey, Activity paramActivity);
    
    public abstract void showSurveyById(int paramInt, Activity paramActivity);
    
    public abstract void showSurveyIfAvailable(Activity paramActivity);
    
    public abstract void trackCharge(double paramDouble, JSONObject paramJSONObject);
    
    public abstract void trackNotification(String paramString, InAppNotification paramInAppNotification);
    
    public abstract void trackNotificationSeen(InAppNotification paramInAppNotification);
    
    public abstract void union(String paramString, JSONArray paramJSONArray);
    
    public abstract void unset(String paramString);
    
    public abstract People withIdentity(String paramString);
  }
  
  private class PeopleImpl
    implements MixpanelAPI.People
  {
    private PeopleImpl() {}
    
    @TargetApi(19)
    private void registerForPushIdAPI19AndOlder(String paramString)
    {
      try
      {
        if (MPConfig.DEBUG) {
          Log.v("MixpanelAPI.API", "Registering a new push id");
        }
        Intent localIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
        localIntent.putExtra("app", PendingIntent.getBroadcast(MixpanelAPI.this.mContext, 0, new Intent(), 0));
        localIntent.putExtra("sender", paramString);
        MixpanelAPI.this.mContext.startService(localIntent);
        return;
      }
      catch (SecurityException paramString)
      {
        Log.w("MixpanelAPI.API", paramString);
      }
    }
    
    @TargetApi(21)
    private void registerForPushIdAPI21AndUp(String paramString)
    {
      MixpanelAPI.this.mMessages.registerForGCM(paramString);
    }
    
    private void showGivenOrAvailableNotification(InAppNotification paramInAppNotification, Activity paramActivity)
    {
      if (Build.VERSION.SDK_INT < 16)
      {
        if (MPConfig.DEBUG) {
          Log.v("MixpanelAPI.API", "Will not show notifications, os version is too low.");
        }
        return;
      }
      paramActivity.runOnUiThread(new MixpanelAPI.PeopleImpl.4(this, paramInAppNotification, paramActivity));
    }
    
    private void showGivenOrAvailableSurvey(Survey paramSurvey, Activity paramActivity)
    {
      if (Build.VERSION.SDK_INT < 16) {
        if (MPConfig.DEBUG) {
          Log.v("MixpanelAPI.API", "Will not show survey, os version is too low.");
        }
      }
      do
      {
        return;
        if (ConfigurationChecker.checkSurveyActivityAvailable(paramActivity.getApplicationContext())) {
          break;
        }
      } while (!MPConfig.DEBUG);
      Log.v("MixpanelAPI.API", "Will not show survey, application isn't configured appropriately.");
      return;
      ReentrantLock localReentrantLock = UpdateDisplayState.getLockObject();
      localReentrantLock.lock();
      try
      {
        boolean bool = UpdateDisplayState.hasCurrentProposal();
        if (bool) {
          return;
        }
        Survey localSurvey = paramSurvey;
        if (paramSurvey == null) {
          localSurvey = getSurveyIfAvailable();
        }
        if (localSurvey == null) {
          return;
        }
        paramSurvey = new UpdateDisplayState.DisplayState.SurveyState(localSurvey);
        int i = UpdateDisplayState.proposeDisplay(paramSurvey, getDistinctId(), MixpanelAPI.this.mToken);
        if (i <= 0)
        {
          Log.e("MixpanelAPI.API", "DisplayState Lock is in an inconsistent state! Please report this issue to Mixpanel");
          return;
        }
        paramSurvey = new MixpanelAPI.PeopleImpl.3(this, paramSurvey, paramActivity, i);
        localReentrantLock.unlock();
        BackgroundCapture.captureBackground(paramActivity, paramSurvey);
        return;
      }
      finally
      {
        localReentrantLock.unlock();
      }
    }
    
    private JSONObject stdPeopleMessage(String paramString, Object paramObject)
      throws JSONException
    {
      JSONObject localJSONObject = new JSONObject();
      String str = getDistinctId();
      localJSONObject.put(paramString, paramObject);
      localJSONObject.put("$token", MixpanelAPI.this.mToken);
      localJSONObject.put("$time", System.currentTimeMillis());
      if (str != null) {
        localJSONObject.put("$distinct_id", str);
      }
      return localJSONObject;
    }
    
    public void addOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener)
    {
      MixpanelAPI.this.mUpdatesListener.addOnMixpanelUpdatesReceivedListener(paramOnMixpanelUpdatesReceivedListener);
    }
    
    public void append(String paramString, Object paramObject)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put(paramString, paramObject);
        paramString = stdPeopleMessage("$append", localJSONObject);
        MixpanelAPI.this.recordPeopleMessage(paramString);
        return;
      }
      catch (JSONException paramString)
      {
        Log.e("MixpanelAPI.API", "Exception appending a property", paramString);
      }
    }
    
    @Deprecated
    public void checkForSurvey(SurveyCallbacks paramSurveyCallbacks)
    {
      Log.i("MixpanelAPI.API", "MixpanelAPI.checkForSurvey is deprecated. Calling is now a no-op.\n    to query surveys, call MixpanelAPI.getPeople().getSurveyIfAvailable()");
    }
    
    @Deprecated
    public void checkForSurvey(SurveyCallbacks paramSurveyCallbacks, Activity paramActivity)
    {
      Log.i("MixpanelAPI.API", "MixpanelAPI.checkForSurvey is deprecated. Calling is now a no-op.\n    to query surveys, call MixpanelAPI.getPeople().getSurveyIfAvailable()");
    }
    
    public void clearCharges()
    {
      unset("$transactions");
    }
    
    public void clearPushRegistrationId()
    {
      MixpanelAPI.this.mPersistentIdentity.clearPushId();
      set("$android_devices", new JSONArray());
    }
    
    public void deleteUser()
    {
      try
      {
        JSONObject localJSONObject = stdPeopleMessage("$delete", JSONObject.NULL);
        MixpanelAPI.this.recordPeopleMessage(localJSONObject);
        return;
      }
      catch (JSONException localJSONException)
      {
        Log.e("MixpanelAPI.API", "Exception deleting a user");
      }
    }
    
    public String getDistinctId()
    {
      return MixpanelAPI.this.mPersistentIdentity.getPeopleDistinctId();
    }
    
    public InAppNotification getNotificationIfAvailable()
    {
      return MixpanelAPI.this.mDecideMessages.getNotification(MixpanelAPI.this.mConfig.getTestMode());
    }
    
    public Survey getSurveyIfAvailable()
    {
      return MixpanelAPI.this.mDecideMessages.getSurvey(MixpanelAPI.this.mConfig.getTestMode());
    }
    
    public void identify(String paramString)
    {
      synchronized (MixpanelAPI.this.mPersistentIdentity)
      {
        MixpanelAPI.this.mPersistentIdentity.setPeopleDistinctId(paramString);
        MixpanelAPI.this.mDecideMessages.setDistinctId(paramString);
        MixpanelAPI.this.pushWaitingPeopleRecord();
        return;
      }
    }
    
    public void increment(String paramString, double paramDouble)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put(paramString, Double.valueOf(paramDouble));
      increment(localHashMap);
    }
    
    public void increment(Map<String, ? extends Number> paramMap)
    {
      paramMap = new JSONObject(paramMap);
      try
      {
        paramMap = stdPeopleMessage("$add", paramMap);
        MixpanelAPI.this.recordPeopleMessage(paramMap);
        return;
      }
      catch (JSONException paramMap)
      {
        Log.e("MixpanelAPI.API", "Exception incrementing properties", paramMap);
      }
    }
    
    public void initPushHandling(String paramString)
    {
      if (!ConfigurationChecker.checkPushConfiguration(MixpanelAPI.this.mContext))
      {
        Log.i("MixpanelAPI.API", "Can't register for push notification services. Push notifications will not work.");
        Log.i("MixpanelAPI.API", "See log tagged " + ConfigurationChecker.LOGTAG + " above for details.");
        return;
      }
      String str = MixpanelAPI.this.mPersistentIdentity.getPushId();
      if (str == null)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          registerForPushIdAPI21AndUp(paramString);
          return;
        }
        registerForPushIdAPI19AndOlder(paramString);
        return;
      }
      MixpanelAPI.allInstances(new MixpanelAPI.PeopleImpl.1(this, str));
    }
    
    public void joinExperimentIfAvailable()
    {
      JSONArray localJSONArray = MixpanelAPI.this.mDecideMessages.getVariants();
      if (localJSONArray != null) {
        MixpanelAPI.this.mUpdatesFromMixpanel.setVariants(localJSONArray);
      }
    }
    
    public void merge(String paramString, JSONObject paramJSONObject)
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put(paramString, paramJSONObject);
        paramString = stdPeopleMessage("$merge", localJSONObject);
        MixpanelAPI.this.recordPeopleMessage(paramString);
        return;
      }
      catch (JSONException paramString)
      {
        Log.e("MixpanelAPI.API", "Exception merging a property", paramString);
      }
    }
    
    public void removeOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener)
    {
      MixpanelAPI.this.mUpdatesListener.removeOnMixpanelUpdatesReceivedListener(paramOnMixpanelUpdatesReceivedListener);
    }
    
    public void set(String paramString, Object paramObject)
    {
      try
      {
        set(new JSONObject().put(paramString, paramObject));
        return;
      }
      catch (JSONException paramString)
      {
        Log.e("MixpanelAPI.API", "set", paramString);
      }
    }
    
    public void set(JSONObject paramJSONObject)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject(MixpanelAPI.this.mDeviceInfo);
        Iterator localIterator = paramJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localJSONObject.put(str, paramJSONObject.get(str));
        }
        paramJSONObject = stdPeopleMessage("$set", localJSONObject);
      }
      catch (JSONException paramJSONObject)
      {
        Log.e("MixpanelAPI.API", "Exception setting people properties", paramJSONObject);
        return;
      }
      MixpanelAPI.this.recordPeopleMessage(paramJSONObject);
    }
    
    public void setMap(Map<String, Object> paramMap)
    {
      if (paramMap == null)
      {
        Log.e("MixpanelAPI.API", "setMap does not accept null properties");
        return;
      }
      try
      {
        set(new JSONObject(paramMap));
        return;
      }
      catch (NullPointerException paramMap)
      {
        Log.w("MixpanelAPI.API", "Can't have null keys in the properties of setMap!");
      }
    }
    
    public void setOnce(String paramString, Object paramObject)
    {
      try
      {
        setOnce(new JSONObject().put(paramString, paramObject));
        return;
      }
      catch (JSONException paramString)
      {
        Log.e("MixpanelAPI.API", "set", paramString);
      }
    }
    
    public void setOnce(JSONObject paramJSONObject)
    {
      try
      {
        paramJSONObject = stdPeopleMessage("$set_once", paramJSONObject);
        MixpanelAPI.this.recordPeopleMessage(paramJSONObject);
        return;
      }
      catch (JSONException paramJSONObject)
      {
        Log.e("MixpanelAPI.API", "Exception setting people properties");
      }
    }
    
    public void setOnceMap(Map<String, Object> paramMap)
    {
      if (paramMap == null)
      {
        Log.e("MixpanelAPI.API", "setOnceMap does not accept null properties");
        return;
      }
      try
      {
        setOnce(new JSONObject(paramMap));
        return;
      }
      catch (NullPointerException paramMap)
      {
        Log.w("MixpanelAPI.API", "Can't have null keys in the properties setOnceMap!");
      }
    }
    
    public void setPushRegistrationId(String paramString)
    {
      synchronized (MixpanelAPI.this.mPersistentIdentity)
      {
        if (MixpanelAPI.this.mPersistentIdentity.getPeopleDistinctId() == null) {
          return;
        }
        MixpanelAPI.this.mPersistentIdentity.storePushId(paramString);
        JSONArray localJSONArray = new JSONArray();
        localJSONArray.put(paramString);
        union("$android_devices", localJSONArray);
        return;
      }
    }
    
    public void showGivenNotification(InAppNotification paramInAppNotification, Activity paramActivity)
    {
      if (paramInAppNotification != null) {
        showGivenOrAvailableNotification(paramInAppNotification, paramActivity);
      }
    }
    
    public void showNotificationById(int paramInt, Activity paramActivity)
    {
      showGivenNotification(MixpanelAPI.this.mDecideMessages.getNotification(paramInt, MixpanelAPI.this.mConfig.getTestMode()), paramActivity);
    }
    
    public void showNotificationIfAvailable(Activity paramActivity)
    {
      if (Build.VERSION.SDK_INT < 16) {
        return;
      }
      showGivenOrAvailableNotification(null, paramActivity);
    }
    
    @Deprecated
    public void showSurvey(Survey paramSurvey, Activity paramActivity)
    {
      showGivenOrAvailableSurvey(paramSurvey, paramActivity);
    }
    
    public void showSurveyById(int paramInt, Activity paramActivity)
    {
      Survey localSurvey = MixpanelAPI.this.mDecideMessages.getSurvey(paramInt, MixpanelAPI.this.mConfig.getTestMode());
      if (localSurvey != null) {
        showGivenOrAvailableSurvey(localSurvey, paramActivity);
      }
    }
    
    public void showSurveyIfAvailable(Activity paramActivity)
    {
      if (Build.VERSION.SDK_INT < 16) {
        return;
      }
      showGivenOrAvailableSurvey(null, paramActivity);
    }
    
    public void trackCharge(double paramDouble, JSONObject paramJSONObject)
    {
      Object localObject1 = new Date();
      Object localObject2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
      ((DateFormat)localObject2).setTimeZone(TimeZone.getTimeZone("UTC"));
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("$amount", paramDouble);
        localJSONObject.put("$time", ((DateFormat)localObject2).format((Date)localObject1));
        if (paramJSONObject != null)
        {
          localObject1 = paramJSONObject.keys();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            localJSONObject.put((String)localObject2, paramJSONObject.get((String)localObject2));
          }
        }
        append("$transactions", localJSONObject);
      }
      catch (JSONException paramJSONObject)
      {
        Log.e("MixpanelAPI.API", "Exception creating new charge", paramJSONObject);
        return;
      }
    }
    
    public void trackNotification(String paramString, InAppNotification paramInAppNotification)
    {
      MixpanelAPI.this.track(paramString, paramInAppNotification.getCampaignProperties());
    }
    
    public void trackNotificationSeen(InAppNotification paramInAppNotification)
    {
      if (paramInAppNotification == null) {
        return;
      }
      trackNotification("$campaign_delivery", paramInAppNotification);
      MixpanelAPI.People localPeople = MixpanelAPI.this.getPeople().withIdentity(getDistinctId());
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
      JSONObject localJSONObject = paramInAppNotification.getCampaignProperties();
      try
      {
        localJSONObject.put("$time", localSimpleDateFormat.format(new Date()));
        localPeople.append("$campaigns", Integer.valueOf(paramInAppNotification.getId()));
        localPeople.append("$notifications", localJSONObject);
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          Log.e("MixpanelAPI.API", "Exception trying to track an in-app notification seen", localJSONException);
        }
      }
    }
    
    public void union(String paramString, JSONArray paramJSONArray)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put(paramString, paramJSONArray);
        paramString = stdPeopleMessage("$union", localJSONObject);
        MixpanelAPI.this.recordPeopleMessage(paramString);
        return;
      }
      catch (JSONException paramString)
      {
        Log.e("MixpanelAPI.API", "Exception unioning a property");
      }
    }
    
    public void unset(String paramString)
    {
      try
      {
        JSONArray localJSONArray = new JSONArray();
        localJSONArray.put(paramString);
        paramString = stdPeopleMessage("$unset", localJSONArray);
        MixpanelAPI.this.recordPeopleMessage(paramString);
        return;
      }
      catch (JSONException paramString)
      {
        Log.e("MixpanelAPI.API", "Exception unsetting a property", paramString);
      }
    }
    
    public MixpanelAPI.People withIdentity(String paramString)
    {
      if (paramString == null) {
        return null;
      }
      return new MixpanelAPI.PeopleImpl.2(this, paramString);
    }
  }
  
  private class SupportedUpdatesListener
    implements MixpanelAPI.UpdatesListener, Runnable
  {
    private final Executor mExecutor = Executors.newSingleThreadExecutor();
    private final Set<OnMixpanelUpdatesReceivedListener> mListeners = new HashSet();
    
    private SupportedUpdatesListener() {}
    
    public void addOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener)
    {
      try
      {
        if (MixpanelAPI.this.mDecideMessages.hasUpdatesAvailable()) {
          onNewResults();
        }
        this.mListeners.add(paramOnMixpanelUpdatesReceivedListener);
        return;
      }
      finally {}
    }
    
    public void onNewResults()
    {
      this.mExecutor.execute(this);
    }
    
    public void removeOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener)
    {
      try
      {
        this.mListeners.remove(paramOnMixpanelUpdatesReceivedListener);
        return;
      }
      finally
      {
        paramOnMixpanelUpdatesReceivedListener = finally;
        throw paramOnMixpanelUpdatesReceivedListener;
      }
    }
    
    public void run()
    {
      try
      {
        Iterator localIterator = this.mListeners.iterator();
        while (localIterator.hasNext()) {
          ((OnMixpanelUpdatesReceivedListener)localIterator.next()).onMixpanelUpdatesReceived();
        }
      }
      finally {}
    }
  }
  
  private class UnsupportedUpdatesFromMixpanel
    implements UpdatesFromMixpanel
  {
    private final Tweaks mTweaks;
    
    public UnsupportedUpdatesFromMixpanel(Tweaks paramTweaks)
    {
      this.mTweaks = paramTweaks;
    }
    
    public Tweaks getTweaks()
    {
      return this.mTweaks;
    }
    
    public void setEventBindings(JSONArray paramJSONArray) {}
    
    public void setVariants(JSONArray paramJSONArray) {}
    
    public void startUpdates() {}
  }
  
  private class UnsupportedUpdatesListener
    implements MixpanelAPI.UpdatesListener
  {
    private UnsupportedUpdatesListener() {}
    
    public void addOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener) {}
    
    public void onNewResults() {}
    
    public void removeOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener) {}
  }
  
  private static abstract interface UpdatesListener
    extends DecideMessages.OnNewResultsListener
  {
    public abstract void addOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener);
    
    public abstract void removeOnMixpanelUpdatesReceivedListener(OnMixpanelUpdatesReceivedListener paramOnMixpanelUpdatesReceivedListener);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/mpmetrics/MixpanelAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */