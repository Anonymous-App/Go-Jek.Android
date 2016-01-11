package rx.plugins;

import java.util.concurrent.atomic.AtomicReference;

public class RxJavaPlugins
{
  static final RxJavaErrorHandler DEFAULT_ERROR_HANDLER = new RxJavaErrorHandler() {};
  private static final RxJavaPlugins INSTANCE = new RxJavaPlugins();
  private final AtomicReference<RxJavaErrorHandler> errorHandler = new AtomicReference();
  private final AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook = new AtomicReference();
  private final AtomicReference<RxJavaSchedulersHook> schedulersHook = new AtomicReference();
  
  public static RxJavaPlugins getInstance()
  {
    return INSTANCE;
  }
  
  private static Object getPluginImplementationViaProperty(Class<?> paramClass)
  {
    String str1 = paramClass.getSimpleName();
    String str2 = System.getProperty("rxjava.plugin." + str1 + ".implementation");
    if (str2 != null) {
      try
      {
        paramClass = Class.forName(str2).asSubclass(paramClass).newInstance();
        return paramClass;
      }
      catch (ClassCastException paramClass)
      {
        throw new RuntimeException(str1 + " implementation is not an instance of " + str1 + ": " + str2);
      }
      catch (ClassNotFoundException paramClass)
      {
        throw new RuntimeException(str1 + " implementation class not found: " + str2, paramClass);
      }
      catch (InstantiationException paramClass)
      {
        throw new RuntimeException(str1 + " implementation not able to be instantiated: " + str2, paramClass);
      }
      catch (IllegalAccessException paramClass)
      {
        throw new RuntimeException(str1 + " implementation not able to be accessed: " + str2, paramClass);
      }
    }
    return null;
  }
  
  public RxJavaErrorHandler getErrorHandler()
  {
    Object localObject;
    if (this.errorHandler.get() == null)
    {
      localObject = getPluginImplementationViaProperty(RxJavaErrorHandler.class);
      if (localObject != null) {
        break label43;
      }
      this.errorHandler.compareAndSet(null, DEFAULT_ERROR_HANDLER);
    }
    for (;;)
    {
      return (RxJavaErrorHandler)this.errorHandler.get();
      label43:
      this.errorHandler.compareAndSet(null, (RxJavaErrorHandler)localObject);
    }
  }
  
  public RxJavaObservableExecutionHook getObservableExecutionHook()
  {
    Object localObject;
    if (this.observableExecutionHook.get() == null)
    {
      localObject = getPluginImplementationViaProperty(RxJavaObservableExecutionHook.class);
      if (localObject != null) {
        break label43;
      }
      this.observableExecutionHook.compareAndSet(null, RxJavaObservableExecutionHookDefault.getInstance());
    }
    for (;;)
    {
      return (RxJavaObservableExecutionHook)this.observableExecutionHook.get();
      label43:
      this.observableExecutionHook.compareAndSet(null, (RxJavaObservableExecutionHook)localObject);
    }
  }
  
  public RxJavaSchedulersHook getSchedulersHook()
  {
    Object localObject;
    if (this.schedulersHook.get() == null)
    {
      localObject = getPluginImplementationViaProperty(RxJavaSchedulersHook.class);
      if (localObject != null) {
        break label43;
      }
      this.schedulersHook.compareAndSet(null, RxJavaSchedulersHook.getDefaultInstance());
    }
    for (;;)
    {
      return (RxJavaSchedulersHook)this.schedulersHook.get();
      label43:
      this.schedulersHook.compareAndSet(null, (RxJavaSchedulersHook)localObject);
    }
  }
  
  public void registerErrorHandler(RxJavaErrorHandler paramRxJavaErrorHandler)
  {
    if (!this.errorHandler.compareAndSet(null, paramRxJavaErrorHandler)) {
      throw new IllegalStateException("Another strategy was already registered: " + this.errorHandler.get());
    }
  }
  
  public void registerObservableExecutionHook(RxJavaObservableExecutionHook paramRxJavaObservableExecutionHook)
  {
    if (!this.observableExecutionHook.compareAndSet(null, paramRxJavaObservableExecutionHook)) {
      throw new IllegalStateException("Another strategy was already registered: " + this.observableExecutionHook.get());
    }
  }
  
  public void registerSchedulersHook(RxJavaSchedulersHook paramRxJavaSchedulersHook)
  {
    if (!this.schedulersHook.compareAndSet(null, paramRxJavaSchedulersHook)) {
      throw new IllegalStateException("Another strategy was already registered: " + this.schedulersHook.get());
    }
  }
  
  void reset()
  {
    INSTANCE.errorHandler.set(null);
    INSTANCE.observableExecutionHook.set(null);
    INSTANCE.schedulersHook.set(null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/plugins/RxJavaPlugins.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */