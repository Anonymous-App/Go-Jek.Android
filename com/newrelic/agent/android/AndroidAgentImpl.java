package com.newrelic.agent.android;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.api.v1.ConnectionEvent;
import com.newrelic.agent.android.api.v1.ConnectionListener;
import com.newrelic.agent.android.api.v1.DeviceForm;
import com.newrelic.agent.android.api.v2.TraceMachineInterface;
import com.newrelic.agent.android.background.ApplicationStateEvent;
import com.newrelic.agent.android.background.ApplicationStateListener;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.crashes.CrashReporter;
import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.ConnectInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.sample.MachineMeasurementConsumer;
import com.newrelic.agent.android.sample.Sampler;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.Sample;
import com.newrelic.agent.android.tracing.SampleValue;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.AndroidEncoder;
import com.newrelic.agent.android.util.Connectivity;
import com.newrelic.agent.android.util.Encoder;
import com.newrelic.agent.android.util.JsonCrashStore;
import com.newrelic.agent.android.util.UiBackgroundListener;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import proguard.canary.NewRelicCanary;

public class AndroidAgentImpl
  implements AgentImpl, ConnectionListener, ApplicationStateListener, TraceMachineInterface
{
  private static final float LOCATION_ACCURACY_THRESHOLD = 500.0F;
  private static final Comparator<TransactionData> cmp = new AndroidAgentImpl.1();
  private static final AgentLog log = ;
  private final AgentConfiguration agentConfiguration;
  private ApplicationInformation applicationInformation;
  private final Context context;
  private DeviceInformation deviceInformation;
  private final Encoder encoder = new AndroidEncoder();
  private LocationListener locationListener;
  private final Lock lock = new ReentrantLock();
  private MachineMeasurementConsumer machineMeasurementConsumer;
  private SavedState savedState;
  
  public AndroidAgentImpl(Context paramContext, AgentConfiguration paramAgentConfiguration)
    throws AgentInitializationException
  {
    this.context = appContext(paramContext);
    this.agentConfiguration = paramAgentConfiguration;
    this.savedState = new SavedState(this.context);
    if (isDisabled()) {
      throw new AgentInitializationException("This version of the agent has been disabled");
    }
    initApplicationInformation();
    if ((paramAgentConfiguration.useLocationService()) && (this.context.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", getApplicationInformation().getPackageId()) == 0))
    {
      log.debug("Location stats enabled");
      addLocationListener();
    }
    TraceMachine.setTraceMachineInterface(this);
    paramAgentConfiguration.setCrashStore(new JsonCrashStore(paramContext));
    ApplicationStateMonitor.getInstance().addApplicationStateListener(this);
    if (Build.VERSION.SDK_INT >= 14) {
      paramContext.registerComponentCallbacks(new UiBackgroundListener());
    }
    Sampler.init(paramContext);
  }
  
  private void addLocationListener()
  {
    LocationManager localLocationManager = (LocationManager)this.context.getSystemService("location");
    if (localLocationManager == null)
    {
      log.error("Unable to retrieve reference to LocationManager. Disabling location listener.");
      return;
    }
    this.locationListener = new AndroidAgentImpl.2(this);
    localLocationManager.requestLocationUpdates("passive", 1000L, 0.0F, this.locationListener);
  }
  
  private static Context appContext(Context paramContext)
  {
    Context localContext = paramContext;
    if (!(paramContext instanceof Application)) {
      localContext = paramContext.getApplicationContext();
    }
    return localContext;
  }
  
  private static DeviceForm deviceForm(Context paramContext)
  {
    int i = paramContext.getResources().getConfiguration().screenLayout & 0xF;
    switch (i)
    {
    default: 
      if (i > 3) {
        return DeviceForm.XLARGE;
      }
      break;
    case 1: 
      return DeviceForm.SMALL;
    case 2: 
      return DeviceForm.NORMAL;
    case 3: 
      return DeviceForm.LARGE;
    }
    return DeviceForm.UNKNOWN;
  }
  
  private String getUUID()
  {
    String str2 = this.savedState.getConnectInformation().getDeviceInformation().getDeviceId();
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.equals("")) {}
    }
    else
    {
      str1 = UUID.randomUUID().toString();
      this.savedState.saveDeviceId(str1);
    }
    return str1;
  }
  
  private String getUnhandledExceptionHandlerName()
  {
    try
    {
      String str = Thread.getDefaultUncaughtExceptionHandler().getClass().getName();
      return str;
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public static void init(Context paramContext, AgentConfiguration paramAgentConfiguration)
  {
    try
    {
      Agent.setImpl(new AndroidAgentImpl(paramContext, paramAgentConfiguration));
      Agent.start();
      return;
    }
    catch (AgentInitializationException paramContext)
    {
      log.error("Failed to initialize the agent: " + paramContext.toString());
    }
  }
  
  private boolean isAccurate(Location paramLocation)
  {
    if (paramLocation == null) {}
    while (500.0F < paramLocation.getAccuracy()) {
      return false;
    }
    return true;
  }
  
  private void pokeCanary() {}
  
  private void removeLocationListener()
  {
    if (this.locationListener == null) {
      return;
    }
    LocationManager localLocationManager = (LocationManager)this.context.getSystemService("location");
    if (localLocationManager == null)
    {
      log.error("Unable to retrieve reference to LocationManager. Can't unregister location listener.");
      return;
    }
    try
    {
      localLocationManager.removeUpdates(this.locationListener);
      this.locationListener = null;
      return;
    }
    finally {}
  }
  
  private void stop(boolean paramBoolean)
  {
    Sampler.shutdown();
    TraceMachine.haltTracing();
    if (paramBoolean) {
      Harvest.harvestNow();
    }
    TraceMachine.clearActivityHistory();
    Harvest.shutdown();
    Measurements.shutdown();
  }
  
  @Deprecated
  public void addTransactionData(TransactionData paramTransactionData) {}
  
  @Deprecated
  public void applicationBackgrounded(ApplicationStateEvent paramApplicationStateEvent)
  {
    log.info("AndroidAgentImpl: application backgrounded ");
    stop();
  }
  
  @Deprecated
  public void applicationForegrounded(ApplicationStateEvent paramApplicationStateEvent)
  {
    log.info("AndroidAgentImpl: application foregrounded ");
    start();
  }
  
  @Deprecated
  public void connected(ConnectionEvent paramConnectionEvent)
  {
    log.error("AndroidAgentImpl: connected ");
  }
  
  /* Error */
  public void disable()
  {
    // Byte code:
    //   0: getstatic 48	com/newrelic/agent/android/AndroidAgentImpl:log	Lcom/newrelic/agent/android/logging/AgentLog;
    //   3: new 326	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 327	java/lang/StringBuilder:<init>	()V
    //   10: ldc_w 397
    //   13: invokevirtual 333	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: invokestatic 400	com/newrelic/agent/android/Agent:getVersion	()Ljava/lang/String;
    //   19: invokevirtual 333	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 335	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokeinterface 403 2 0
    //   30: aload_0
    //   31: getfield 85	com/newrelic/agent/android/AndroidAgentImpl:savedState	Lcom/newrelic/agent/android/SavedState;
    //   34: invokestatic 400	com/newrelic/agent/android/Agent:getVersion	()Ljava/lang/String;
    //   37: invokevirtual 406	com/newrelic/agent/android/SavedState:saveDisabledVersion	(Ljava/lang/String;)V
    //   40: aload_0
    //   41: iconst_0
    //   42: invokespecial 408	com/newrelic/agent/android/AndroidAgentImpl:stop	(Z)V
    //   45: getstatic 414	com/newrelic/agent/android/NullAgentImpl:instance	Lcom/newrelic/agent/android/NullAgentImpl;
    //   48: invokestatic 321	com/newrelic/agent/android/Agent:setImpl	(Lcom/newrelic/agent/android/AgentImpl;)V
    //   51: return
    //   52: astore_1
    //   53: getstatic 414	com/newrelic/agent/android/NullAgentImpl:instance	Lcom/newrelic/agent/android/NullAgentImpl;
    //   56: invokestatic 321	com/newrelic/agent/android/Agent:setImpl	(Lcom/newrelic/agent/android/AgentImpl;)V
    //   59: aload_1
    //   60: athrow
    //   61: astore_1
    //   62: aload_0
    //   63: iconst_0
    //   64: invokespecial 408	com/newrelic/agent/android/AndroidAgentImpl:stop	(Z)V
    //   67: getstatic 414	com/newrelic/agent/android/NullAgentImpl:instance	Lcom/newrelic/agent/android/NullAgentImpl;
    //   70: invokestatic 321	com/newrelic/agent/android/Agent:setImpl	(Lcom/newrelic/agent/android/AgentImpl;)V
    //   73: aload_1
    //   74: athrow
    //   75: astore_1
    //   76: getstatic 414	com/newrelic/agent/android/NullAgentImpl:instance	Lcom/newrelic/agent/android/NullAgentImpl;
    //   79: invokestatic 321	com/newrelic/agent/android/Agent:setImpl	(Lcom/newrelic/agent/android/AgentImpl;)V
    //   82: aload_1
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	AndroidAgentImpl
    //   52	8	1	localObject1	Object
    //   61	13	1	localObject2	Object
    //   75	8	1	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   40	45	52	finally
    //   30	40	61	finally
    //   62	67	75	finally
  }
  
  @Deprecated
  public void disconnected(ConnectionEvent paramConnectionEvent)
  {
    this.savedState.clear();
  }
  
  @Deprecated
  public List<TransactionData> getAndClearTransactionData()
  {
    return null;
  }
  
  public ApplicationInformation getApplicationInformation()
  {
    return this.applicationInformation;
  }
  
  public String getCrossProcessId()
  {
    this.lock.lock();
    try
    {
      String str = this.savedState.getCrossProcessId();
      return str;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public long getCurrentThreadId()
  {
    return Thread.currentThread().getId();
  }
  
  public String getCurrentThreadName()
  {
    return Thread.currentThread().getName();
  }
  
  public DeviceInformation getDeviceInformation()
  {
    if (this.deviceInformation != null) {
      return this.deviceInformation;
    }
    DeviceInformation localDeviceInformation = new DeviceInformation();
    localDeviceInformation.setOsName("Android");
    localDeviceInformation.setOsVersion(Build.VERSION.RELEASE);
    localDeviceInformation.setOsBuild(Build.VERSION.INCREMENTAL);
    localDeviceInformation.setModel(Build.MODEL);
    localDeviceInformation.setAgentName("AndroidAgent");
    localDeviceInformation.setAgentVersion(Agent.getVersion());
    localDeviceInformation.setManufacturer(Build.MANUFACTURER);
    localDeviceInformation.setDeviceId(getUUID());
    localDeviceInformation.setArchitecture(System.getProperty("os.arch"));
    localDeviceInformation.setRunTime(System.getProperty("java.vm.version"));
    localDeviceInformation.setSize(deviceForm(this.context).name().toLowerCase());
    this.deviceInformation = localDeviceInformation;
    return this.deviceInformation;
  }
  
  public Encoder getEncoder()
  {
    return this.encoder;
  }
  
  public EnvironmentInformation getEnvironmentInformation()
  {
    localEnvironmentInformation = new EnvironmentInformation();
    ActivityManager localActivityManager = (ActivityManager)this.context.getSystemService("activity");
    arrayOfLong = new long[2];
    for (;;)
    {
      try
      {
        localStatFs1 = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        localStatFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (Build.VERSION.SDK_INT < 18) {
          continue;
        }
        arrayOfLong[0] = (localStatFs1.getAvailableBlocksLong() * localStatFs1.getBlockSizeLong());
        arrayOfLong[1] = (localStatFs2.getAvailableBlocksLong() * localStatFs1.getBlockSizeLong());
      }
      catch (Exception localException)
      {
        StatFs localStatFs1;
        StatFs localStatFs2;
        AgentHealth.noticeException(localException);
        if (arrayOfLong[0] >= 0L) {
          continue;
        }
        arrayOfLong[0] = 0L;
        if (arrayOfLong[1] >= 0L) {
          continue;
        }
        arrayOfLong[1] = 0L;
        localEnvironmentInformation.setDiskAvailable(arrayOfLong);
        continue;
      }
      finally
      {
        if (arrayOfLong[0] >= 0L) {
          continue;
        }
        arrayOfLong[0] = 0L;
        if (arrayOfLong[1] >= 0L) {
          continue;
        }
        arrayOfLong[1] = 0L;
        localEnvironmentInformation.setDiskAvailable(arrayOfLong);
      }
      localEnvironmentInformation.setMemoryUsage(Sampler.sampleMemory(localActivityManager).getSampleValue().asLong().longValue());
      localEnvironmentInformation.setOrientation(this.context.getResources().getConfiguration().orientation);
      localEnvironmentInformation.setNetworkStatus(getNetworkCarrier());
      localEnvironmentInformation.setNetworkWanType(getNetworkWanType());
      return localEnvironmentInformation;
      arrayOfLong[0] = (localStatFs1.getAvailableBlocks() * localStatFs1.getBlockSize());
      arrayOfLong[1] = (localStatFs2.getAvailableBlocks() * localStatFs2.getBlockSize());
    }
  }
  
  public String getNetworkCarrier()
  {
    return Connectivity.carrierNameFromContext(this.context);
  }
  
  public String getNetworkWanType()
  {
    return Connectivity.wanType(this.context);
  }
  
  public int getResponseBodyLimit()
  {
    this.lock.lock();
    try
    {
      int i = this.savedState.getHarvestConfiguration().getResponse_body_limit();
      return i;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public int getStackTraceLimit()
  {
    this.lock.lock();
    try
    {
      int i = this.savedState.getStackTraceLimit();
      return i;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public void initApplicationInformation()
    throws AgentInitializationException
  {
    if (this.applicationInformation != null)
    {
      log.debug("attempted to reinitialize ApplicationInformation.");
      return;
    }
    str4 = this.context.getPackageName();
    PackageManager localPackageManager = this.context.getPackageManager();
    Object localObject1 = this.agentConfiguration.getCustomApplicationVersion();
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!((String)localObject1).equals("")) {
        break label94;
      }
    }
    for (;;)
    {
      try
      {
        localObject1 = localPackageManager.getPackageInfo(str4, 0);
        if ((localObject1 != null) && (((PackageInfo)localObject1).versionName != null) && (((PackageInfo)localObject1).versionName.length() > 0))
        {
          localObject2 = ((PackageInfo)localObject1).versionName;
          label94:
          log.debug("Using application version " + (String)localObject2);
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        throw new AgentInitializationException("Could not determine package version: " + localNameNotFoundException1.getMessage());
      }
      try
      {
        localObject1 = localPackageManager.getApplicationInfo(str4, 0);
        if (localObject1 == null) {
          break label204;
        }
        localObject1 = localPackageManager.getApplicationLabel((ApplicationInfo)localObject1).toString();
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException2)
      {
        String str1;
        log.warning(localNameNotFoundException2.toString());
        String str2 = str4;
        continue;
      }
      catch (SecurityException localSecurityException)
      {
        log.warning(localSecurityException.toString());
        String str3 = str4;
        continue;
      }
      this.applicationInformation = new ApplicationInformation((String)localObject1, (String)localObject2, str4);
      return;
      throw new AgentInitializationException("Your app doesn't appear to have a version defined. Ensure you have defined 'versionName' in your manifest.");
      label204:
      str1 = str4;
    }
  }
  
  protected void initialize()
  {
    Harvest.addHarvestListener(this.savedState);
    Harvest.initialize(this.agentConfiguration);
    Harvest.setHarvestConfiguration(this.savedState.getHarvestConfiguration());
    Harvest.setHarvestConnectInformation(this.savedState.getConnectInformation());
    Measurements.initialize();
    log.info(MessageFormat.format("New Relic Agent v{0}", new Object[] { Agent.getVersion() }));
    log.verbose(MessageFormat.format("Application token: {0}", new Object[] { this.agentConfiguration.getApplicationToken() }));
    this.machineMeasurementConsumer = new MachineMeasurementConsumer();
    Measurements.addMeasurementConsumer(this.machineMeasurementConsumer);
    StatsEngine.get().inc("Supportability/AgentHealth/UncaughtExceptionHandler/" + getUnhandledExceptionHandlerName());
    CrashReporter.initialize(this.agentConfiguration);
  }
  
  public boolean isDisabled()
  {
    return Agent.getVersion().equals(this.savedState.getDisabledVersion());
  }
  
  public boolean isUIThread()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }
  
  @Deprecated
  public void mergeTransactionData(List<TransactionData> paramList) {}
  
  public void setLocation(Location paramLocation)
  {
    if (paramLocation == null) {
      throw new IllegalArgumentException("Location must not be null.");
    }
    Geocoder localGeocoder = new Geocoder(this.context);
    Object localObject = null;
    try
    {
      paramLocation = localGeocoder.getFromLocation(paramLocation.getLatitude(), paramLocation.getLongitude(), 1);
      if ((paramLocation == null) || (paramLocation.size() == 0)) {
        return;
      }
    }
    catch (IOException paramLocation)
    {
      do
      {
        do
        {
          for (;;)
          {
            log.error("Unable to geocode location: " + paramLocation.toString());
            paramLocation = (Location)localObject;
          }
          localObject = (Address)paramLocation.get(0);
        } while (localObject == null);
        paramLocation = ((Address)localObject).getCountryCode();
        localObject = ((Address)localObject).getAdminArea();
      } while ((paramLocation == null) || (localObject == null));
      setLocation(paramLocation, (String)localObject);
      removeLocationListener();
    }
  }
  
  public void setLocation(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException("Country code and administrative region are required.");
    }
  }
  
  public void start()
  {
    if (!isDisabled())
    {
      initialize();
      Harvest.start();
      return;
    }
    stop(false);
  }
  
  public void stop()
  {
    stop(true);
  }
  
  public boolean updateSavedConnectInformation()
  {
    ConnectInformation localConnectInformation1 = this.savedState.getConnectInformation();
    ConnectInformation localConnectInformation2 = new ConnectInformation(getApplicationInformation(), getDeviceInformation());
    String str = this.savedState.getAppToken();
    if ((!localConnectInformation2.equals(localConnectInformation1)) || (!this.agentConfiguration.getApplicationToken().equals(str)))
    {
      this.savedState.clear();
      this.savedState.saveConnectInformation(localConnectInformation2);
      this.savedState.saveAppToken(this.agentConfiguration.getApplicationToken());
      return true;
    }
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/newrelic/agent/android/AndroidAgentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */