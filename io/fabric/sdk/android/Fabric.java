package io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class Fabric
{
  static final boolean DEFAULT_DEBUGGABLE = false;
  static final Logger DEFAULT_LOGGER = new DefaultLogger();
  static final String ROOT_DIR = ".Fabric";
  public static final String TAG = "Fabric";
  static volatile Fabric singleton;
  private WeakReference<Activity> activity;
  private ActivityLifecycleManager activityLifecycleManager;
  private final Context context;
  final boolean debuggable;
  private final ExecutorService executorService;
  private final IdManager idManager;
  private final InitializationCallback<Fabric> initializationCallback;
  private AtomicBoolean initialized;
  private final InitializationCallback<?> kitInitializationCallback;
  private final Map<Class<? extends Kit>, Kit> kits;
  final Logger logger;
  private final Handler mainHandler;
  
  Fabric(Context paramContext, Map<Class<? extends Kit>, Kit> paramMap, PriorityThreadPoolExecutor paramPriorityThreadPoolExecutor, Handler paramHandler, Logger paramLogger, boolean paramBoolean, InitializationCallback paramInitializationCallback, IdManager paramIdManager)
  {
    this.context = paramContext;
    this.kits = paramMap;
    this.executorService = paramPriorityThreadPoolExecutor;
    this.mainHandler = paramHandler;
    this.logger = paramLogger;
    this.debuggable = paramBoolean;
    this.initializationCallback = paramInitializationCallback;
    this.initialized = new AtomicBoolean(false);
    this.kitInitializationCallback = createKitInitializationCallback(paramMap.size());
    this.idManager = paramIdManager;
  }
  
  private static void addToKitMap(Map<Class<? extends Kit>, Kit> paramMap, Collection<? extends Kit> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Kit localKit = (Kit)paramCollection.next();
      paramMap.put(localKit.getClass(), localKit);
      if ((localKit instanceof KitGroup)) {
        addToKitMap(paramMap, ((KitGroup)localKit).getKits());
      }
    }
  }
  
  private Activity extractActivity(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      return (Activity)paramContext;
    }
    return null;
  }
  
  public static <T extends Kit> T getKit(Class<T> paramClass)
  {
    return (Kit)singleton().kits.get(paramClass);
  }
  
  private static Map<Class<? extends Kit>, Kit> getKitMap(Collection<? extends Kit> paramCollection)
  {
    HashMap localHashMap = new HashMap(paramCollection.size());
    addToKitMap(localHashMap, paramCollection);
    return localHashMap;
  }
  
  public static Logger getLogger()
  {
    if (singleton == null) {
      return DEFAULT_LOGGER;
    }
    return singleton.logger;
  }
  
  private void init()
  {
    setCurrentActivity(extractActivity(this.context));
    this.activityLifecycleManager = new ActivityLifecycleManager(this.context);
    this.activityLifecycleManager.registerCallbacks(new Fabric.1(this));
    initializeKits(this.context);
  }
  
  public static boolean isDebuggable()
  {
    if (singleton == null) {
      return false;
    }
    return singleton.debuggable;
  }
  
  public static boolean isInitialized()
  {
    return (singleton != null) && (singleton.initialized.get());
  }
  
  private static void setFabric(Fabric paramFabric)
  {
    singleton = paramFabric;
    paramFabric.init();
  }
  
  static Fabric singleton()
  {
    if (singleton == null) {
      throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }
    return singleton;
  }
  
  public static Fabric with(Context paramContext, Kit... paramVarArgs)
  {
    if (singleton == null) {}
    try
    {
      if (singleton == null) {
        setFabric(new Builder(paramContext).kits(paramVarArgs).build());
      }
      return singleton;
    }
    finally {}
  }
  
  public static Fabric with(Fabric paramFabric)
  {
    if (singleton == null) {}
    try
    {
      if (singleton == null) {
        setFabric(paramFabric);
      }
      return singleton;
    }
    finally {}
  }
  
  void addAnnotatedDependencies(Map<Class<? extends Kit>, Kit> paramMap, Kit paramKit)
  {
    Object localObject1 = (DependsOn)paramKit.getClass().getAnnotation(DependsOn.class);
    if (localObject1 != null)
    {
      localObject1 = ((DependsOn)localObject1).value();
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        if (((Class)localObject2).isInterface())
        {
          Iterator localIterator = paramMap.values().iterator();
          while (localIterator.hasNext())
          {
            Kit localKit = (Kit)localIterator.next();
            if (((Class)localObject2).isAssignableFrom(localKit.getClass())) {
              paramKit.initializationTask.addDependency(localKit.initializationTask);
            }
          }
        }
        if ((Kit)paramMap.get(localObject2) == null) {
          throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
        }
        paramKit.initializationTask.addDependency(((Kit)paramMap.get(localObject2)).initializationTask);
        i += 1;
      }
    }
  }
  
  InitializationCallback<?> createKitInitializationCallback(int paramInt)
  {
    return new Fabric.2(this, paramInt);
  }
  
  public ActivityLifecycleManager getActivityLifecycleManager()
  {
    return this.activityLifecycleManager;
  }
  
  public String getAppIdentifier()
  {
    return this.idManager.getAppIdentifier();
  }
  
  public String getAppInstallIdentifier()
  {
    return this.idManager.getAppInstallIdentifier();
  }
  
  public Activity getCurrentActivity()
  {
    if (this.activity != null) {
      return (Activity)this.activity.get();
    }
    return null;
  }
  
  public ExecutorService getExecutorService()
  {
    return this.executorService;
  }
  
  public String getIdentifier()
  {
    return "io.fabric.sdk.android:fabric";
  }
  
  public Collection<Kit> getKits()
  {
    return this.kits.values();
  }
  
  Future<Map<String, KitInfo>> getKitsFinderFuture(Context paramContext)
  {
    paramContext = new FabricKitsFinder(paramContext.getPackageCodePath());
    return getExecutorService().submit(paramContext);
  }
  
  public Handler getMainHandler()
  {
    return this.mainHandler;
  }
  
  public String getVersion()
  {
    return "1.3.6.79";
  }
  
  void initializeKits(Context paramContext)
  {
    Object localObject1 = getKitsFinderFuture(paramContext);
    Object localObject2 = getKits();
    localObject1 = new Onboarding((Future)localObject1, (Collection)localObject2);
    localObject2 = new ArrayList((Collection)localObject2);
    Collections.sort((List)localObject2);
    ((Onboarding)localObject1).injectParameters(paramContext, this, InitializationCallback.EMPTY, this.idManager);
    Object localObject3 = ((List)localObject2).iterator();
    while (((Iterator)localObject3).hasNext()) {
      ((Kit)((Iterator)localObject3).next()).injectParameters(paramContext, this, this.kitInitializationCallback, this.idManager);
    }
    ((Onboarding)localObject1).initialize();
    if (getLogger().isLoggable("Fabric", 3)) {}
    for (paramContext = new StringBuilder("Initializing ").append(getIdentifier()).append(" [Version: ").append(getVersion()).append("], with the following kits:\n");; paramContext = null)
    {
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Kit)((Iterator)localObject2).next();
        ((Kit)localObject3).initializationTask.addDependency(((Onboarding)localObject1).initializationTask);
        addAnnotatedDependencies(this.kits, (Kit)localObject3);
        ((Kit)localObject3).initialize();
        if (paramContext != null) {
          paramContext.append(((Kit)localObject3).getIdentifier()).append(" [Version: ").append(((Kit)localObject3).getVersion()).append("]\n");
        }
      }
    }
    if (paramContext != null) {
      getLogger().d("Fabric", paramContext.toString());
    }
  }
  
  public Fabric setCurrentActivity(Activity paramActivity)
  {
    this.activity = new WeakReference(paramActivity);
    return this;
  }
  
  public static class Builder
  {
    private String appIdentifier;
    private String appInstallIdentifier;
    private final Context context;
    private boolean debuggable;
    private Handler handler;
    private InitializationCallback<Fabric> initializationCallback;
    private Kit[] kits;
    private Logger logger;
    private PriorityThreadPoolExecutor threadPoolExecutor;
    
    public Builder(Context paramContext)
    {
      if (paramContext == null) {
        throw new IllegalArgumentException("Context must not be null.");
      }
      this.context = paramContext.getApplicationContext();
    }
    
    public Builder appIdentifier(String paramString)
    {
      if (paramString == null) {
        throw new IllegalArgumentException("appIdentifier must not be null.");
      }
      if (this.appIdentifier != null) {
        throw new IllegalStateException("appIdentifier already set.");
      }
      this.appIdentifier = paramString;
      return this;
    }
    
    public Builder appInstallIdentifier(String paramString)
    {
      if (paramString == null) {
        throw new IllegalArgumentException("appInstallIdentifier must not be null.");
      }
      if (this.appInstallIdentifier != null) {
        throw new IllegalStateException("appInstallIdentifier already set.");
      }
      this.appInstallIdentifier = paramString;
      return this;
    }
    
    public Fabric build()
    {
      if (this.threadPoolExecutor == null) {
        this.threadPoolExecutor = PriorityThreadPoolExecutor.create();
      }
      if (this.handler == null) {
        this.handler = new Handler(Looper.getMainLooper());
      }
      if (this.logger == null)
      {
        if (this.debuggable) {
          this.logger = new DefaultLogger(3);
        }
      }
      else
      {
        if (this.appIdentifier == null) {
          this.appIdentifier = this.context.getPackageName();
        }
        if (this.initializationCallback == null) {
          this.initializationCallback = InitializationCallback.EMPTY;
        }
        if (this.kits != null) {
          break label182;
        }
      }
      label182:
      for (Object localObject = new HashMap();; localObject = Fabric.getKitMap(Arrays.asList(this.kits)))
      {
        IdManager localIdManager = new IdManager(this.context, this.appIdentifier, this.appInstallIdentifier, ((Map)localObject).values());
        return new Fabric(this.context, (Map)localObject, this.threadPoolExecutor, this.handler, this.logger, this.debuggable, this.initializationCallback, localIdManager);
        this.logger = new DefaultLogger();
        break;
      }
    }
    
    public Builder debuggable(boolean paramBoolean)
    {
      this.debuggable = paramBoolean;
      return this;
    }
    
    @Deprecated
    public Builder executorService(ExecutorService paramExecutorService)
    {
      return this;
    }
    
    @Deprecated
    public Builder handler(Handler paramHandler)
    {
      return this;
    }
    
    public Builder initializationCallback(InitializationCallback<Fabric> paramInitializationCallback)
    {
      if (paramInitializationCallback == null) {
        throw new IllegalArgumentException("initializationCallback must not be null.");
      }
      if (this.initializationCallback != null) {
        throw new IllegalStateException("initializationCallback already set.");
      }
      this.initializationCallback = paramInitializationCallback;
      return this;
    }
    
    public Builder kits(Kit... paramVarArgs)
    {
      if (this.kits != null) {
        throw new IllegalStateException("Kits already set.");
      }
      this.kits = paramVarArgs;
      return this;
    }
    
    public Builder logger(Logger paramLogger)
    {
      if (paramLogger == null) {
        throw new IllegalArgumentException("Logger must not be null.");
      }
      if (this.logger != null) {
        throw new IllegalStateException("Logger already set.");
      }
      this.logger = paramLogger;
      return this;
    }
    
    public Builder threadPoolExecutor(PriorityThreadPoolExecutor paramPriorityThreadPoolExecutor)
    {
      if (paramPriorityThreadPoolExecutor == null) {
        throw new IllegalArgumentException("PriorityThreadPoolExecutor must not be null.");
      }
      if (this.threadPoolExecutor != null) {
        throw new IllegalStateException("PriorityThreadPoolExecutor already set.");
      }
      this.threadPoolExecutor = paramPriorityThreadPoolExecutor;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/fabric/sdk/android/Fabric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */