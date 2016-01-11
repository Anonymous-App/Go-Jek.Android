package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.facebook.internal.Validate;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class FacebookSdk
{
  public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
  public static final String APPLICATION_NAME_PROPERTY = "com.facebook.sdk.ApplicationName";
  private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
  static final String CALLBACK_OFFSET_CHANGED_AFTER_INIT = "The callback request code offset can't be updated once the SDK is initialized.";
  static final String CALLBACK_OFFSET_NEGATIVE = "The callback request code offset can't be negative.";
  public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
  private static final int DEFAULT_CORE_POOL_SIZE = 5;
  private static final int DEFAULT_KEEP_ALIVE = 1;
  private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
  private static final ThreadFactory DEFAULT_THREAD_FACTORY = new FacebookSdk.1();
  private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE;
  private static final String FACEBOOK_COM = "facebook.com";
  private static final Object LOCK;
  private static final int MAX_REQUEST_CODE_RANGE = 100;
  private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
  private static final String TAG = FacebookSdk.class.getCanonicalName();
  private static volatile String appClientToken;
  private static Context applicationContext;
  private static volatile String applicationId;
  private static volatile String applicationName;
  private static File cacheDir;
  private static int callbackRequestCodeOffset;
  private static volatile Executor executor;
  private static volatile String facebookDomain;
  private static volatile boolean isDebugEnabled;
  private static boolean isLegacyTokenUpgradeSupported;
  private static final HashSet<LoggingBehavior> loggingBehaviors = new HashSet(Arrays.asList(new LoggingBehavior[] { LoggingBehavior.DEVELOPER_ERRORS }));
  private static AtomicLong onProgressThreshold;
  private static Boolean sdkInitialized = Boolean.valueOf(false);
  
  static
  {
    facebookDomain = "facebook.com";
    onProgressThreshold = new AtomicLong(65536L);
    isDebugEnabled = false;
    isLegacyTokenUpgradeSupported = false;
    callbackRequestCodeOffset = 64206;
    LOCK = new Object();
    DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
  }
  
  public static void addLoggingBehavior(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.add(paramLoggingBehavior);
      updateGraphDebugBehavior();
      return;
    }
  }
  
  public static void clearLoggingBehaviors()
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.clear();
      return;
    }
  }
  
  public static Context getApplicationContext()
  {
    Validate.sdkInitialized();
    return applicationContext;
  }
  
  public static String getApplicationId()
  {
    Validate.sdkInitialized();
    return applicationId;
  }
  
  public static String getApplicationName()
  {
    Validate.sdkInitialized();
    return applicationName;
  }
  
  /* Error */
  public static String getApplicationSignature(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 159	com/facebook/internal/Validate:sdkInitialized	()V
    //   3: aload_0
    //   4: ifnonnull +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: invokevirtual 179	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore_1
    //   14: aload_1
    //   15: ifnull -8 -> 7
    //   18: aload_0
    //   19: invokevirtual 182	android/content/Context:getPackageName	()Ljava/lang/String;
    //   22: astore_0
    //   23: aload_1
    //   24: aload_0
    //   25: bipush 64
    //   27: invokevirtual 188	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   30: astore_0
    //   31: aload_0
    //   32: getfield 194	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   35: astore_1
    //   36: aload_1
    //   37: ifnull -30 -> 7
    //   40: aload_1
    //   41: arraylength
    //   42: ifeq -35 -> 7
    //   45: ldc -60
    //   47: invokestatic 202	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   50: astore_1
    //   51: aload_1
    //   52: aload_0
    //   53: getfield 194	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   56: iconst_0
    //   57: aaload
    //   58: invokevirtual 208	android/content/pm/Signature:toByteArray	()[B
    //   61: invokevirtual 212	java/security/MessageDigest:update	([B)V
    //   64: aload_1
    //   65: invokevirtual 215	java/security/MessageDigest:digest	()[B
    //   68: bipush 9
    //   70: invokestatic 221	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   73: areturn
    //   74: astore_0
    //   75: aconst_null
    //   76: areturn
    //   77: astore_0
    //   78: aconst_null
    //   79: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	paramContext	Context
    //   13	52	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   23	31	74	android/content/pm/PackageManager$NameNotFoundException
    //   45	51	77	java/security/NoSuchAlgorithmException
  }
  
  private static Executor getAsyncTaskExecutor()
  {
    try
    {
      Object localObject = AsyncTask.class.getField("THREAD_POOL_EXECUTOR");
      if ((localIllegalAccessException instanceof Executor)) {
        break label35;
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      try
      {
        localObject = ((Field)localObject).get(null);
        if (localObject != null) {
          break label26;
        }
        return null;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        return null;
      }
      localNoSuchFieldException = localNoSuchFieldException;
      return null;
    }
    label26:
    return null;
    label35:
    return (Executor)localIllegalAccessException;
  }
  
  public static File getCacheDir()
  {
    Validate.sdkInitialized();
    return cacheDir;
  }
  
  public static int getCallbackRequestCodeOffset()
  {
    Validate.sdkInitialized();
    return callbackRequestCodeOffset;
  }
  
  public static String getClientToken()
  {
    Validate.sdkInitialized();
    return appClientToken;
  }
  
  public static Executor getExecutor()
  {
    synchronized (LOCK)
    {
      if (executor == null)
      {
        Executor localExecutor = getAsyncTaskExecutor();
        Object localObject1 = localExecutor;
        if (localExecutor == null) {
          localObject1 = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, DEFAULT_WORK_QUEUE, DEFAULT_THREAD_FACTORY);
        }
        executor = (Executor)localObject1;
      }
      return executor;
    }
  }
  
  public static String getFacebookDomain()
  {
    return facebookDomain;
  }
  
  public static boolean getLimitEventAndDataUsage(Context paramContext)
  {
    Validate.sdkInitialized();
    return paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
  }
  
  public static Set<LoggingBehavior> getLoggingBehaviors()
  {
    synchronized (loggingBehaviors)
    {
      Set localSet = Collections.unmodifiableSet(new HashSet(loggingBehaviors));
      return localSet;
    }
  }
  
  public static long getOnProgressThreshold()
  {
    Validate.sdkInitialized();
    return onProgressThreshold.get();
  }
  
  public static String getSdkVersion()
  {
    Validate.sdkInitialized();
    return "4.1.0";
  }
  
  public static boolean isDebugEnabled()
  {
    return isDebugEnabled;
  }
  
  public static boolean isFacebookRequestCode(int paramInt)
  {
    return (paramInt >= callbackRequestCodeOffset) && (paramInt < callbackRequestCodeOffset + 100);
  }
  
  public static boolean isInitialized()
  {
    try
    {
      boolean bool = sdkInitialized.booleanValue();
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static boolean isLegacyTokenUpgradeSupported()
  {
    return isLegacyTokenUpgradeSupported;
  }
  
  public static boolean isLoggingBehaviorEnabled(LoggingBehavior paramLoggingBehavior)
  {
    for (;;)
    {
      synchronized (loggingBehaviors)
      {
        if ((isDebugEnabled()) && (loggingBehaviors.contains(paramLoggingBehavior)))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  static void loadDefaultsFromMetadata(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    for (;;)
    {
      Object localObject;
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        if ((paramContext == null) || (paramContext.metaData == null)) {
          break;
        }
        if (applicationId == null)
        {
          localObject = paramContext.metaData.get("com.facebook.sdk.ApplicationId");
          if ((localObject instanceof String)) {
            applicationId = (String)localObject;
          }
        }
        else
        {
          if (applicationName == null) {
            applicationName = paramContext.metaData.getString("com.facebook.sdk.ApplicationName");
          }
          if (appClientToken != null) {
            break;
          }
          appClientToken = paramContext.metaData.getString("com.facebook.sdk.ClientToken");
          return;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return;
      }
      if ((localObject instanceof Integer)) {
        applicationId = localObject.toString();
      }
    }
  }
  
  /* Error */
  static GraphResponse publishInstallAndWaitForResponse(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +41 -> 46
    //   8: new 352	java/lang/IllegalArgumentException
    //   11: dup
    //   12: ldc_w 354
    //   15: invokespecial 357	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   18: athrow
    //   19: astore_0
    //   20: ldc_w 359
    //   23: aload_0
    //   24: invokestatic 365	com/facebook/internal/Utility:logd	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   27: new 367	com/facebook/GraphResponse
    //   30: dup
    //   31: aconst_null
    //   32: aconst_null
    //   33: new 369	com/facebook/FacebookRequestError
    //   36: dup
    //   37: aconst_null
    //   38: aload_0
    //   39: invokespecial 372	com/facebook/FacebookRequestError:<init>	(Ljava/net/HttpURLConnection;Ljava/lang/Exception;)V
    //   42: invokespecial 375	com/facebook/GraphResponse:<init>	(Lcom/facebook/GraphRequest;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookRequestError;)V
    //   45: areturn
    //   46: aload_0
    //   47: invokestatic 381	com/facebook/internal/AttributionIdentifiers:getAttributionIdentifiers	(Landroid/content/Context;)Lcom/facebook/internal/AttributionIdentifiers;
    //   50: astore 8
    //   52: aload_0
    //   53: ldc 14
    //   55: iconst_0
    //   56: invokevirtual 277	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   59: astore 7
    //   61: new 383	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 384	java/lang/StringBuilder:<init>	()V
    //   68: aload_1
    //   69: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: ldc_w 390
    //   75: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: astore 5
    //   83: new 383	java/lang/StringBuilder
    //   86: dup
    //   87: invokespecial 384	java/lang/StringBuilder:<init>	()V
    //   90: aload_1
    //   91: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc_w 393
    //   97: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: astore 4
    //   105: aload 7
    //   107: aload 5
    //   109: lconst_0
    //   110: invokeinterface 397 4 0
    //   115: lstore_2
    //   116: aload 7
    //   118: aload 4
    //   120: aconst_null
    //   121: invokeinterface 400 3 0
    //   126: astore 6
    //   128: getstatic 406	com/facebook/internal/AppEventsLoggerUtility$GraphAPIActivityType:MOBILE_INSTALL_EVENT	Lcom/facebook/internal/AppEventsLoggerUtility$GraphAPIActivityType;
    //   131: aload 8
    //   133: aload_0
    //   134: invokestatic 411	com/facebook/appevents/AppEventsLogger:getAnonymousAppDeviceGUID	(Landroid/content/Context;)Ljava/lang/String;
    //   137: aload_0
    //   138: invokestatic 413	com/facebook/FacebookSdk:getLimitEventAndDataUsage	(Landroid/content/Context;)Z
    //   141: aload_0
    //   142: invokestatic 419	com/facebook/internal/AppEventsLoggerUtility:getJSONObjectForGraphAPICall	(Lcom/facebook/internal/AppEventsLoggerUtility$GraphAPIActivityType;Lcom/facebook/internal/AttributionIdentifiers;Ljava/lang/String;ZLandroid/content/Context;)Lorg/json/JSONObject;
    //   145: astore_0
    //   146: aconst_null
    //   147: ldc 45
    //   149: iconst_1
    //   150: anewarray 4	java/lang/Object
    //   153: dup
    //   154: iconst_0
    //   155: aload_1
    //   156: aastore
    //   157: invokestatic 423	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   160: aload_0
    //   161: aconst_null
    //   162: invokestatic 429	com/facebook/GraphRequest:newPostRequest	(Lcom/facebook/AccessToken;Ljava/lang/String;Lorg/json/JSONObject;Lcom/facebook/GraphRequest$Callback;)Lcom/facebook/GraphRequest;
    //   165: astore 8
    //   167: lload_2
    //   168: lconst_0
    //   169: lcmp
    //   170: ifeq +80 -> 250
    //   173: aconst_null
    //   174: astore_1
    //   175: aload_1
    //   176: astore_0
    //   177: aload 6
    //   179: ifnull +9 -> 188
    //   182: aload 6
    //   184: invokestatic 435	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:init	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   187: astore_0
    //   188: aload_0
    //   189: ifnonnull +49 -> 238
    //   192: ldc_w 437
    //   195: aconst_null
    //   196: new 439	com/facebook/GraphRequestBatch
    //   199: dup
    //   200: iconst_1
    //   201: anewarray 425	com/facebook/GraphRequest
    //   204: dup
    //   205: iconst_0
    //   206: aload 8
    //   208: aastore
    //   209: invokespecial 442	com/facebook/GraphRequestBatch:<init>	([Lcom/facebook/GraphRequest;)V
    //   212: invokestatic 446	com/facebook/GraphResponse:createResponsesFromString	(Ljava/lang/String;Ljava/net/HttpURLConnection;Lcom/facebook/GraphRequestBatch;)Ljava/util/List;
    //   215: iconst_0
    //   216: invokeinterface 451 2 0
    //   221: checkcast 367	com/facebook/GraphResponse
    //   224: areturn
    //   225: astore_0
    //   226: new 453	com/facebook/FacebookException
    //   229: dup
    //   230: ldc_w 455
    //   233: aload_0
    //   234: invokespecial 458	com/facebook/FacebookException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   237: athrow
    //   238: new 367	com/facebook/GraphResponse
    //   241: dup
    //   242: aconst_null
    //   243: aconst_null
    //   244: aconst_null
    //   245: aload_0
    //   246: invokespecial 461	com/facebook/GraphResponse:<init>	(Lcom/facebook/GraphRequest;Ljava/net/HttpURLConnection;Ljava/lang/String;Lorg/json/JSONObject;)V
    //   249: areturn
    //   250: aload 8
    //   252: invokevirtual 465	com/facebook/GraphRequest:executeAndWait	()Lcom/facebook/GraphResponse;
    //   255: astore_1
    //   256: aload 7
    //   258: invokeinterface 469 1 0
    //   263: astore 6
    //   265: aload 6
    //   267: aload 5
    //   269: invokestatic 474	java/lang/System:currentTimeMillis	()J
    //   272: invokeinterface 480 4 0
    //   277: pop
    //   278: aload_1
    //   279: invokevirtual 484	com/facebook/GraphResponse:getJSONObject	()Lorg/json/JSONObject;
    //   282: ifnull +31 -> 313
    //   285: aload_1
    //   286: invokevirtual 484	com/facebook/GraphResponse:getJSONObject	()Lorg/json/JSONObject;
    //   289: astore_0
    //   290: aload_0
    //   291: instanceof 486
    //   294: ifne +28 -> 322
    //   297: aload_0
    //   298: invokevirtual 487	org/json/JSONObject:toString	()Ljava/lang/String;
    //   301: astore_0
    //   302: aload 6
    //   304: aload 4
    //   306: aload_0
    //   307: invokeinterface 491 3 0
    //   312: pop
    //   313: aload 6
    //   315: invokeinterface 494 1 0
    //   320: aload_1
    //   321: areturn
    //   322: aload_0
    //   323: checkcast 486	org/json/JSONObject
    //   326: invokestatic 497	com/newrelic/agent/android/instrumentation/JSONObjectInstrumentation:toString	(Lorg/json/JSONObject;)Ljava/lang/String;
    //   329: astore_0
    //   330: goto -28 -> 302
    //   333: astore_0
    //   334: aload_1
    //   335: astore_0
    //   336: goto -148 -> 188
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	339	0	paramContext	Context
    //   0	339	1	paramString	String
    //   115	53	2	l	long
    //   103	202	4	str1	String
    //   81	187	5	str2	String
    //   126	188	6	localObject1	Object
    //   59	198	7	localSharedPreferences	SharedPreferences
    //   50	201	8	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   8	19	19	java/lang/Exception
    //   46	128	19	java/lang/Exception
    //   128	146	19	java/lang/Exception
    //   146	167	19	java/lang/Exception
    //   182	188	19	java/lang/Exception
    //   192	225	19	java/lang/Exception
    //   226	238	19	java/lang/Exception
    //   238	250	19	java/lang/Exception
    //   250	302	19	java/lang/Exception
    //   302	313	19	java/lang/Exception
    //   313	320	19	java/lang/Exception
    //   322	330	19	java/lang/Exception
    //   128	146	225	org/json/JSONException
    //   182	188	333	org/json/JSONException
  }
  
  public static void publishInstallAsync(Context paramContext, String paramString)
  {
    paramContext = paramContext.getApplicationContext();
    getExecutor().execute(new FacebookSdk.3(paramContext, paramString));
  }
  
  public static void removeLoggingBehavior(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.remove(paramLoggingBehavior);
      return;
    }
  }
  
  /* Error */
  public static void sdkInitialize(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 139	com/facebook/FacebookSdk:sdkInitialized	Ljava/lang/Boolean;
    //   6: invokevirtual 309	java/lang/Boolean:booleanValue	()Z
    //   9: istore_1
    //   10: iload_1
    //   11: iconst_1
    //   12: if_icmpne +7 -> 19
    //   15: ldc 2
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: ldc_w 517
    //   23: invokestatic 521	com/facebook/internal/Validate:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   26: aload_0
    //   27: iconst_0
    //   28: invokestatic 525	com/facebook/internal/Validate:hasFacebookActivity	(Landroid/content/Context;Z)V
    //   31: aload_0
    //   32: iconst_0
    //   33: invokestatic 528	com/facebook/internal/Validate:hasInternetPermissions	(Landroid/content/Context;Z)V
    //   36: aload_0
    //   37: invokevirtual 501	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   40: putstatic 161	com/facebook/FacebookSdk:applicationContext	Landroid/content/Context;
    //   43: getstatic 161	com/facebook/FacebookSdk:applicationContext	Landroid/content/Context;
    //   46: invokestatic 530	com/facebook/FacebookSdk:loadDefaultsFromMetadata	(Landroid/content/Context;)V
    //   49: getstatic 161	com/facebook/FacebookSdk:applicationContext	Landroid/content/Context;
    //   52: getstatic 164	com/facebook/FacebookSdk:applicationId	Ljava/lang/String;
    //   55: invokestatic 533	com/facebook/internal/Utility:loadAppSettingsAsync	(Landroid/content/Context;Ljava/lang/String;)V
    //   58: invokestatic 538	com/facebook/internal/NativeProtocol:updateAllAvailableProtocolVersionsAsync	()V
    //   61: getstatic 161	com/facebook/FacebookSdk:applicationContext	Landroid/content/Context;
    //   64: invokestatic 543	com/facebook/internal/BoltsMeasurementEventListener:getInstance	(Landroid/content/Context;)Lcom/facebook/internal/BoltsMeasurementEventListener;
    //   67: pop
    //   68: getstatic 161	com/facebook/FacebookSdk:applicationContext	Landroid/content/Context;
    //   71: invokevirtual 545	android/content/Context:getCacheDir	()Ljava/io/File;
    //   74: putstatic 247	com/facebook/FacebookSdk:cacheDir	Ljava/io/File;
    //   77: new 547	java/util/concurrent/FutureTask
    //   80: dup
    //   81: new 549	com/facebook/FacebookSdk$2
    //   84: dup
    //   85: invokespecial 550	com/facebook/FacebookSdk$2:<init>	()V
    //   88: invokespecial 553	java/util/concurrent/FutureTask:<init>	(Ljava/util/concurrent/Callable;)V
    //   91: astore_0
    //   92: invokestatic 503	com/facebook/FacebookSdk:getExecutor	()Ljava/util/concurrent/Executor;
    //   95: aload_0
    //   96: invokeinterface 511 2 0
    //   101: iconst_1
    //   102: invokestatic 137	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   105: putstatic 139	com/facebook/FacebookSdk:sdkInitialized	Ljava/lang/Boolean;
    //   108: goto -93 -> 15
    //   111: astore_0
    //   112: ldc 2
    //   114: monitorexit
    //   115: aload_0
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	paramContext	Context
    //   9	4	1	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	10	111	finally
    //   19	108	111	finally
  }
  
  public static void sdkInitialize(Context paramContext, int paramInt)
  {
    try
    {
      if ((sdkInitialized.booleanValue()) && (paramInt != callbackRequestCodeOffset)) {
        throw new FacebookException("The callback request code offset can't be updated once the SDK is initialized.");
      }
    }
    finally {}
    if (paramInt < 0) {
      throw new FacebookException("The callback request code offset can't be negative.");
    }
    callbackRequestCodeOffset = paramInt;
    sdkInitialize(paramContext);
  }
  
  public static void setApplicationId(String paramString)
  {
    applicationId = paramString;
  }
  
  public static void setApplicationName(String paramString)
  {
    applicationName = paramString;
  }
  
  public static void setCacheDir(File paramFile)
  {
    cacheDir = paramFile;
  }
  
  public static void setClientToken(String paramString)
  {
    appClientToken = paramString;
  }
  
  public static void setExecutor(Executor paramExecutor)
  {
    Validate.notNull(paramExecutor, "executor");
    synchronized (LOCK)
    {
      executor = paramExecutor;
      return;
    }
  }
  
  public static void setFacebookDomain(String paramString)
  {
    Log.w(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
    facebookDomain = paramString;
  }
  
  public static void setIsDebugEnabled(boolean paramBoolean)
  {
    isDebugEnabled = paramBoolean;
  }
  
  public static void setLegacyTokenUpgradeSupported(boolean paramBoolean)
  {
    isLegacyTokenUpgradeSupported = paramBoolean;
  }
  
  public static void setLimitEventAndDataUsage(Context paramContext, boolean paramBoolean)
  {
    paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("limitEventUsage", paramBoolean).apply();
  }
  
  public static void setOnProgressThreshold(long paramLong)
  {
    onProgressThreshold.set(paramLong);
  }
  
  private static void updateGraphDebugBehavior()
  {
    if ((loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_INFO)) && (!loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_WARNING))) {
      loggingBehaviors.add(LoggingBehavior.GRAPH_API_DEBUG_WARNING);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/facebook/FacebookSdk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */