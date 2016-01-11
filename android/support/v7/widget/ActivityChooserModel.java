package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class ActivityChooserModel
  extends DataSetObservable
{
  private static final String ATTRIBUTE_ACTIVITY = "activity";
  private static final String ATTRIBUTE_TIME = "time";
  private static final String ATTRIBUTE_WEIGHT = "weight";
  private static final boolean DEBUG = false;
  private static final int DEFAULT_ACTIVITY_INFLATION = 5;
  private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0F;
  public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
  public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
  private static final String HISTORY_FILE_EXTENSION = ".xml";
  private static final int INVALID_INDEX = -1;
  private static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
  private static final String TAG_HISTORICAL_RECORD = "historical-record";
  private static final String TAG_HISTORICAL_RECORDS = "historical-records";
  private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
  private static final Object sRegistryLock = new Object();
  private final List<ActivityResolveInfo> mActivities = new ArrayList();
  private OnChooseActivityListener mActivityChoserModelPolicy;
  private ActivitySorter mActivitySorter = new DefaultSorter(null);
  private boolean mCanReadHistoricalData = true;
  private final Context mContext;
  private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
  private boolean mHistoricalRecordsChanged = true;
  private final String mHistoryFileName;
  private int mHistoryMaxSize = 50;
  private final Object mInstanceLock = new Object();
  private Intent mIntent;
  private boolean mReadShareHistoryCalled = false;
  private boolean mReloadActivities = false;
  
  private ActivityChooserModel(Context paramContext, String paramString)
  {
    this.mContext = paramContext.getApplicationContext();
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.endsWith(".xml")))
    {
      this.mHistoryFileName = (paramString + ".xml");
      return;
    }
    this.mHistoryFileName = paramString;
  }
  
  private boolean addHisoricalRecord(HistoricalRecord paramHistoricalRecord)
  {
    boolean bool = this.mHistoricalRecords.add(paramHistoricalRecord);
    if (bool)
    {
      this.mHistoricalRecordsChanged = true;
      pruneExcessiveHistoricalRecordsIfNeeded();
      persistHistoricalDataIfNeeded();
      sortActivitiesIfNeeded();
      notifyChanged();
    }
    return bool;
  }
  
  private void ensureConsistentState()
  {
    boolean bool1 = loadActivitiesIfNeeded();
    boolean bool2 = readHistoricalDataIfNeeded();
    pruneExcessiveHistoricalRecordsIfNeeded();
    if ((bool1 | bool2))
    {
      sortActivitiesIfNeeded();
      notifyChanged();
    }
  }
  
  public static ActivityChooserModel get(Context paramContext, String paramString)
  {
    synchronized (sRegistryLock)
    {
      ActivityChooserModel localActivityChooserModel2 = (ActivityChooserModel)sDataModelRegistry.get(paramString);
      ActivityChooserModel localActivityChooserModel1 = localActivityChooserModel2;
      if (localActivityChooserModel2 == null)
      {
        localActivityChooserModel1 = new ActivityChooserModel(paramContext, paramString);
        sDataModelRegistry.put(paramString, localActivityChooserModel1);
      }
      return localActivityChooserModel1;
    }
  }
  
  private boolean loadActivitiesIfNeeded()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mReloadActivities)
    {
      bool1 = bool2;
      if (this.mIntent != null)
      {
        this.mReloadActivities = false;
        this.mActivities.clear();
        List localList = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
        int j = localList.size();
        int i = 0;
        while (i < j)
        {
          ResolveInfo localResolveInfo = (ResolveInfo)localList.get(i);
          this.mActivities.add(new ActivityResolveInfo(localResolveInfo));
          i += 1;
        }
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void persistHistoricalDataIfNeeded()
  {
    if (!this.mReadShareHistoryCalled) {
      throw new IllegalStateException("No preceding call to #readHistoricalData");
    }
    if (!this.mHistoricalRecordsChanged) {}
    do
    {
      return;
      this.mHistoricalRecordsChanged = false;
    } while (TextUtils.isEmpty(this.mHistoryFileName));
    AsyncTaskCompat.executeParallel(new PersistHistoryAsyncTask(null), new Object[] { new ArrayList(this.mHistoricalRecords), this.mHistoryFileName });
  }
  
  private void pruneExcessiveHistoricalRecordsIfNeeded()
  {
    int j = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
    if (j <= 0) {}
    for (;;)
    {
      return;
      this.mHistoricalRecordsChanged = true;
      int i = 0;
      while (i < j)
      {
        HistoricalRecord localHistoricalRecord = (HistoricalRecord)this.mHistoricalRecords.remove(0);
        i += 1;
      }
    }
  }
  
  private boolean readHistoricalDataIfNeeded()
  {
    if ((this.mCanReadHistoricalData) && (this.mHistoricalRecordsChanged) && (!TextUtils.isEmpty(this.mHistoryFileName)))
    {
      this.mCanReadHistoricalData = false;
      this.mReadShareHistoryCalled = true;
      readHistoricalDataImpl();
      return true;
    }
    return false;
  }
  
  private void readHistoricalDataImpl()
  {
    try
    {
      FileInputStream localFileInputStream = this.mContext.openFileInput(this.mHistoryFileName);
      try
      {
        XmlPullParser localXmlPullParser = Xml.newPullParser();
        localXmlPullParser.setInput(localFileInputStream, "UTF-8");
        for (i = 0; (i != 1) && (i != 2); i = localXmlPullParser.next()) {}
        if (!"historical-records".equals(localXmlPullParser.getName())) {
          throw new XmlPullParserException("Share records file does not start with historical-records tag.");
        }
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        int i;
        Log.e(LOG_TAG, "Error reading historical recrod file: " + this.mHistoryFileName, localXmlPullParserException);
        if (localFileInputStream != null)
        {
          try
          {
            localFileInputStream.close();
            return;
          }
          catch (IOException localIOException1)
          {
            return;
          }
          localList = this.mHistoricalRecords;
          localList.clear();
          do
          {
            i = localXmlPullParserException.next();
            if (i == 1)
            {
              if (localIOException1 == null) {
                break;
              }
              try
              {
                localIOException1.close();
                return;
              }
              catch (IOException localIOException2)
              {
                return;
              }
            }
          } while ((i == 3) || (i == 4));
          if (!"historical-record".equals(localXmlPullParserException.getName())) {
            throw new XmlPullParserException("Share records file not well-formed.");
          }
        }
      }
      catch (IOException localIOException5)
      {
        for (;;)
        {
          List localList;
          Log.e(LOG_TAG, "Error reading historical recrod file: " + this.mHistoryFileName, localIOException5);
          if (localIOException2 == null) {
            break;
          }
          try
          {
            localIOException2.close();
            return;
          }
          catch (IOException localIOException3)
          {
            return;
          }
          localList.add(new HistoricalRecord(localIOException5.getAttributeValue(null, "activity"), Long.parseLong(localIOException5.getAttributeValue(null, "time")), Float.parseFloat(localIOException5.getAttributeValue(null, "weight"))));
        }
      }
      finally
      {
        if (localIOException3 != null) {}
        try
        {
          localIOException3.close();
          throw ((Throwable)localObject);
        }
        catch (IOException localIOException4)
        {
          for (;;) {}
        }
      }
      return;
    }
    catch (FileNotFoundException localFileNotFoundException) {}
  }
  
  private boolean sortActivitiesIfNeeded()
  {
    if ((this.mActivitySorter != null) && (this.mIntent != null) && (!this.mActivities.isEmpty()) && (!this.mHistoricalRecords.isEmpty()))
    {
      this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
      return true;
    }
    return false;
  }
  
  public Intent chooseActivity(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mIntent == null) {
        return null;
      }
      ensureConsistentState();
      Object localObject2 = (ActivityResolveInfo)this.mActivities.get(paramInt);
      localObject2 = new ComponentName(((ActivityResolveInfo)localObject2).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo)localObject2).resolveInfo.activityInfo.name);
      Intent localIntent1 = new Intent(this.mIntent);
      localIntent1.setComponent((ComponentName)localObject2);
      if (this.mActivityChoserModelPolicy != null)
      {
        Intent localIntent2 = new Intent(localIntent1);
        if (this.mActivityChoserModelPolicy.onChooseActivity(this, localIntent2)) {
          return null;
        }
      }
      addHisoricalRecord(new HistoricalRecord((ComponentName)localObject2, System.currentTimeMillis(), 1.0F));
      return localIntent1;
    }
  }
  
  public ResolveInfo getActivity(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivities.get(paramInt)).resolveInfo;
      return localResolveInfo;
    }
  }
  
  public int getActivityCount()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      int i = this.mActivities.size();
      return i;
    }
  }
  
  public int getActivityIndex(ResolveInfo paramResolveInfo)
  {
    for (;;)
    {
      int i;
      synchronized (this.mInstanceLock)
      {
        ensureConsistentState();
        List localList = this.mActivities;
        int j = localList.size();
        i = 0;
        if (i < j)
        {
          if (((ActivityResolveInfo)localList.get(i)).resolveInfo == paramResolveInfo) {
            return i;
          }
        }
        else {
          return -1;
        }
      }
      i += 1;
    }
  }
  
  public ResolveInfo getDefaultActivity()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      if (!this.mActivities.isEmpty())
      {
        ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivities.get(0)).resolveInfo;
        return localResolveInfo;
      }
      return null;
    }
  }
  
  public int getHistoryMaxSize()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mHistoryMaxSize;
      return i;
    }
  }
  
  public int getHistorySize()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      int i = this.mHistoricalRecords.size();
      return i;
    }
  }
  
  public Intent getIntent()
  {
    synchronized (this.mInstanceLock)
    {
      Intent localIntent = this.mIntent;
      return localIntent;
    }
  }
  
  public void setActivitySorter(ActivitySorter paramActivitySorter)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mActivitySorter == paramActivitySorter) {
        return;
      }
      this.mActivitySorter = paramActivitySorter;
      if (sortActivitiesIfNeeded()) {
        notifyChanged();
      }
      return;
    }
  }
  
  public void setDefaultActivity(int paramInt)
  {
    for (;;)
    {
      synchronized (this.mInstanceLock)
      {
        ensureConsistentState();
        ActivityResolveInfo localActivityResolveInfo1 = (ActivityResolveInfo)this.mActivities.get(paramInt);
        ActivityResolveInfo localActivityResolveInfo2 = (ActivityResolveInfo)this.mActivities.get(0);
        if (localActivityResolveInfo2 != null)
        {
          f = localActivityResolveInfo2.weight - localActivityResolveInfo1.weight + 5.0F;
          addHisoricalRecord(new HistoricalRecord(new ComponentName(localActivityResolveInfo1.resolveInfo.activityInfo.packageName, localActivityResolveInfo1.resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
          return;
        }
      }
      float f = 1.0F;
    }
  }
  
  public void setHistoryMaxSize(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mHistoryMaxSize == paramInt) {
        return;
      }
      this.mHistoryMaxSize = paramInt;
      pruneExcessiveHistoricalRecordsIfNeeded();
      if (sortActivitiesIfNeeded()) {
        notifyChanged();
      }
      return;
    }
  }
  
  public void setIntent(Intent paramIntent)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mIntent == paramIntent) {
        return;
      }
      this.mIntent = paramIntent;
      this.mReloadActivities = true;
      ensureConsistentState();
      return;
    }
  }
  
  public void setOnChooseActivityListener(OnChooseActivityListener paramOnChooseActivityListener)
  {
    synchronized (this.mInstanceLock)
    {
      this.mActivityChoserModelPolicy = paramOnChooseActivityListener;
      return;
    }
  }
  
  public static abstract interface ActivityChooserModelClient
  {
    public abstract void setActivityChooserModel(ActivityChooserModel paramActivityChooserModel);
  }
  
  public final class ActivityResolveInfo
    implements Comparable<ActivityResolveInfo>
  {
    public final ResolveInfo resolveInfo;
    public float weight;
    
    public ActivityResolveInfo(ResolveInfo paramResolveInfo)
    {
      this.resolveInfo = paramResolveInfo;
    }
    
    public int compareTo(ActivityResolveInfo paramActivityResolveInfo)
    {
      return Float.floatToIntBits(paramActivityResolveInfo.weight) - Float.floatToIntBits(this.weight);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (ActivityResolveInfo)paramObject;
      } while (Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo)paramObject).weight));
      return false;
    }
    
    public int hashCode()
    {
      return Float.floatToIntBits(this.weight) + 31;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("resolveInfo:").append(this.resolveInfo.toString());
      localStringBuilder.append("; weight:").append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface ActivitySorter
  {
    public abstract void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1);
  }
  
  private final class DefaultSorter
    implements ActivityChooserModel.ActivitySorter
  {
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
    private final Map<ComponentName, ActivityChooserModel.ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();
    
    private DefaultSorter() {}
    
    public void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1)
    {
      paramIntent = this.mPackageNameToActivityMap;
      paramIntent.clear();
      int j = paramList.size();
      int i = 0;
      Object localObject;
      while (i < j)
      {
        localObject = (ActivityChooserModel.ActivityResolveInfo)paramList.get(i);
        ((ActivityChooserModel.ActivityResolveInfo)localObject).weight = 0.0F;
        paramIntent.put(new ComponentName(((ActivityChooserModel.ActivityResolveInfo)localObject).resolveInfo.activityInfo.packageName, ((ActivityChooserModel.ActivityResolveInfo)localObject).resolveInfo.activityInfo.name), localObject);
        i += 1;
      }
      i = paramList1.size();
      float f1 = 1.0F;
      i -= 1;
      while (i >= 0)
      {
        localObject = (ActivityChooserModel.HistoricalRecord)paramList1.get(i);
        ActivityChooserModel.ActivityResolveInfo localActivityResolveInfo = (ActivityChooserModel.ActivityResolveInfo)paramIntent.get(((ActivityChooserModel.HistoricalRecord)localObject).activity);
        float f2 = f1;
        if (localActivityResolveInfo != null)
        {
          localActivityResolveInfo.weight += ((ActivityChooserModel.HistoricalRecord)localObject).weight * f1;
          f2 = f1 * 0.95F;
        }
        i -= 1;
        f1 = f2;
      }
      Collections.sort(paramList);
    }
  }
  
  public static final class HistoricalRecord
  {
    public final ComponentName activity;
    public final long time;
    public final float weight;
    
    public HistoricalRecord(ComponentName paramComponentName, long paramLong, float paramFloat)
    {
      this.activity = paramComponentName;
      this.time = paramLong;
      this.weight = paramFloat;
    }
    
    public HistoricalRecord(String paramString, long paramLong, float paramFloat)
    {
      this(ComponentName.unflattenFromString(paramString), paramLong, paramFloat);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (HistoricalRecord)paramObject;
        if (this.activity == null)
        {
          if (((HistoricalRecord)paramObject).activity != null) {
            return false;
          }
        }
        else if (!this.activity.equals(((HistoricalRecord)paramObject).activity)) {
          return false;
        }
        if (this.time != ((HistoricalRecord)paramObject).time) {
          return false;
        }
      } while (Float.floatToIntBits(this.weight) == Float.floatToIntBits(((HistoricalRecord)paramObject).weight));
      return false;
    }
    
    public int hashCode()
    {
      if (this.activity == null) {}
      for (int i = 0;; i = this.activity.hashCode()) {
        return ((i + 31) * 31 + (int)(this.time ^ this.time >>> 32)) * 31 + Float.floatToIntBits(this.weight);
      }
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("; activity:").append(this.activity);
      localStringBuilder.append("; time:").append(this.time);
      localStringBuilder.append("; weight:").append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface OnChooseActivityListener
  {
    public abstract boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent);
  }
  
  private final class PersistHistoryAsyncTask
    extends AsyncTask<Object, Void, Void>
  {
    private PersistHistoryAsyncTask() {}
    
    /* Error */
    public Void doInBackground(Object... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: checkcast 36	java/util/List
      //   6: astore 4
      //   8: aload_1
      //   9: iconst_1
      //   10: aaload
      //   11: checkcast 38	java/lang/String
      //   14: astore 5
      //   16: aload_0
      //   17: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   20: invokestatic 42	android/support/v7/widget/ActivityChooserModel:access$200	(Landroid/support/v7/widget/ActivityChooserModel;)Landroid/content/Context;
      //   23: aload 5
      //   25: iconst_0
      //   26: invokevirtual 48	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   29: astore_1
      //   30: invokestatic 54	android/util/Xml:newSerializer	()Lorg/xmlpull/v1/XmlSerializer;
      //   33: astore 5
      //   35: aload 5
      //   37: aload_1
      //   38: aconst_null
      //   39: invokeinterface 60 3 0
      //   44: aload 5
      //   46: ldc 62
      //   48: iconst_1
      //   49: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   52: invokeinterface 72 3 0
      //   57: aload 5
      //   59: aconst_null
      //   60: ldc 74
      //   62: invokeinterface 78 3 0
      //   67: pop
      //   68: aload 4
      //   70: invokeinterface 82 1 0
      //   75: istore_3
      //   76: iconst_0
      //   77: istore_2
      //   78: iload_2
      //   79: iload_3
      //   80: if_icmpge +133 -> 213
      //   83: aload 4
      //   85: iconst_0
      //   86: invokeinterface 86 2 0
      //   91: checkcast 88	android/support/v7/widget/ActivityChooserModel$HistoricalRecord
      //   94: astore 6
      //   96: aload 5
      //   98: aconst_null
      //   99: ldc 90
      //   101: invokeinterface 78 3 0
      //   106: pop
      //   107: aload 5
      //   109: aconst_null
      //   110: ldc 92
      //   112: aload 6
      //   114: getfield 95	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:activity	Landroid/content/ComponentName;
      //   117: invokevirtual 101	android/content/ComponentName:flattenToString	()Ljava/lang/String;
      //   120: invokeinterface 105 4 0
      //   125: pop
      //   126: aload 5
      //   128: aconst_null
      //   129: ldc 107
      //   131: aload 6
      //   133: getfield 110	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:time	J
      //   136: invokestatic 113	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   139: invokeinterface 105 4 0
      //   144: pop
      //   145: aload 5
      //   147: aconst_null
      //   148: ldc 115
      //   150: aload 6
      //   152: getfield 118	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:weight	F
      //   155: invokestatic 121	java/lang/String:valueOf	(F)Ljava/lang/String;
      //   158: invokeinterface 105 4 0
      //   163: pop
      //   164: aload 5
      //   166: aconst_null
      //   167: ldc 90
      //   169: invokeinterface 124 3 0
      //   174: pop
      //   175: iload_2
      //   176: iconst_1
      //   177: iadd
      //   178: istore_2
      //   179: goto -101 -> 78
      //   182: astore_1
      //   183: invokestatic 127	android/support/v7/widget/ActivityChooserModel:access$300	()Ljava/lang/String;
      //   186: new 129	java/lang/StringBuilder
      //   189: dup
      //   190: invokespecial 130	java/lang/StringBuilder:<init>	()V
      //   193: ldc -124
      //   195: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   198: aload 5
      //   200: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   203: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   206: aload_1
      //   207: invokestatic 145	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   210: pop
      //   211: aconst_null
      //   212: areturn
      //   213: aload 5
      //   215: aconst_null
      //   216: ldc 74
      //   218: invokeinterface 124 3 0
      //   223: pop
      //   224: aload 5
      //   226: invokeinterface 148 1 0
      //   231: aload_0
      //   232: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   235: iconst_1
      //   236: invokestatic 152	android/support/v7/widget/ActivityChooserModel:access$502	(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
      //   239: pop
      //   240: aload_1
      //   241: ifnull +7 -> 248
      //   244: aload_1
      //   245: invokevirtual 157	java/io/FileOutputStream:close	()V
      //   248: aconst_null
      //   249: areturn
      //   250: astore 4
      //   252: invokestatic 127	android/support/v7/widget/ActivityChooserModel:access$300	()Ljava/lang/String;
      //   255: new 129	java/lang/StringBuilder
      //   258: dup
      //   259: invokespecial 130	java/lang/StringBuilder:<init>	()V
      //   262: ldc -124
      //   264: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   267: aload_0
      //   268: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   271: invokestatic 161	android/support/v7/widget/ActivityChooserModel:access$400	(Landroid/support/v7/widget/ActivityChooserModel;)Ljava/lang/String;
      //   274: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   277: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   280: aload 4
      //   282: invokestatic 145	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   285: pop
      //   286: aload_0
      //   287: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   290: iconst_1
      //   291: invokestatic 152	android/support/v7/widget/ActivityChooserModel:access$502	(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
      //   294: pop
      //   295: aload_1
      //   296: ifnull -48 -> 248
      //   299: aload_1
      //   300: invokevirtual 157	java/io/FileOutputStream:close	()V
      //   303: goto -55 -> 248
      //   306: astore_1
      //   307: goto -59 -> 248
      //   310: astore 4
      //   312: invokestatic 127	android/support/v7/widget/ActivityChooserModel:access$300	()Ljava/lang/String;
      //   315: new 129	java/lang/StringBuilder
      //   318: dup
      //   319: invokespecial 130	java/lang/StringBuilder:<init>	()V
      //   322: ldc -124
      //   324: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   327: aload_0
      //   328: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   331: invokestatic 161	android/support/v7/widget/ActivityChooserModel:access$400	(Landroid/support/v7/widget/ActivityChooserModel;)Ljava/lang/String;
      //   334: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   337: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   340: aload 4
      //   342: invokestatic 145	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   345: pop
      //   346: aload_0
      //   347: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   350: iconst_1
      //   351: invokestatic 152	android/support/v7/widget/ActivityChooserModel:access$502	(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
      //   354: pop
      //   355: aload_1
      //   356: ifnull -108 -> 248
      //   359: aload_1
      //   360: invokevirtual 157	java/io/FileOutputStream:close	()V
      //   363: goto -115 -> 248
      //   366: astore_1
      //   367: goto -119 -> 248
      //   370: astore 4
      //   372: invokestatic 127	android/support/v7/widget/ActivityChooserModel:access$300	()Ljava/lang/String;
      //   375: new 129	java/lang/StringBuilder
      //   378: dup
      //   379: invokespecial 130	java/lang/StringBuilder:<init>	()V
      //   382: ldc -124
      //   384: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   387: aload_0
      //   388: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   391: invokestatic 161	android/support/v7/widget/ActivityChooserModel:access$400	(Landroid/support/v7/widget/ActivityChooserModel;)Ljava/lang/String;
      //   394: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   397: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   400: aload 4
      //   402: invokestatic 145	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   405: pop
      //   406: aload_0
      //   407: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   410: iconst_1
      //   411: invokestatic 152	android/support/v7/widget/ActivityChooserModel:access$502	(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
      //   414: pop
      //   415: aload_1
      //   416: ifnull -168 -> 248
      //   419: aload_1
      //   420: invokevirtual 157	java/io/FileOutputStream:close	()V
      //   423: goto -175 -> 248
      //   426: astore_1
      //   427: goto -179 -> 248
      //   430: astore 4
      //   432: aload_0
      //   433: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   436: iconst_1
      //   437: invokestatic 152	android/support/v7/widget/ActivityChooserModel:access$502	(Landroid/support/v7/widget/ActivityChooserModel;Z)Z
      //   440: pop
      //   441: aload_1
      //   442: ifnull +7 -> 449
      //   445: aload_1
      //   446: invokevirtual 157	java/io/FileOutputStream:close	()V
      //   449: aload 4
      //   451: athrow
      //   452: astore_1
      //   453: goto -205 -> 248
      //   456: astore_1
      //   457: goto -8 -> 449
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	460	0	this	PersistHistoryAsyncTask
      //   0	460	1	paramVarArgs	Object[]
      //   77	102	2	i	int
      //   75	6	3	j	int
      //   6	78	4	localList	List
      //   250	31	4	localIllegalArgumentException	IllegalArgumentException
      //   310	31	4	localIllegalStateException	IllegalStateException
      //   370	31	4	localIOException	IOException
      //   430	20	4	localObject1	Object
      //   14	211	5	localObject2	Object
      //   94	57	6	localHistoricalRecord	ActivityChooserModel.HistoricalRecord
      // Exception table:
      //   from	to	target	type
      //   16	30	182	java/io/FileNotFoundException
      //   35	76	250	java/lang/IllegalArgumentException
      //   83	175	250	java/lang/IllegalArgumentException
      //   213	231	250	java/lang/IllegalArgumentException
      //   299	303	306	java/io/IOException
      //   35	76	310	java/lang/IllegalStateException
      //   83	175	310	java/lang/IllegalStateException
      //   213	231	310	java/lang/IllegalStateException
      //   359	363	366	java/io/IOException
      //   35	76	370	java/io/IOException
      //   83	175	370	java/io/IOException
      //   213	231	370	java/io/IOException
      //   419	423	426	java/io/IOException
      //   35	76	430	finally
      //   83	175	430	finally
      //   213	231	430	finally
      //   252	286	430	finally
      //   312	346	430	finally
      //   372	406	430	finally
      //   244	248	452	java/io/IOException
      //   445	449	456	java/io/IOException
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/ActivityChooserModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */