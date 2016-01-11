package rx.schedulers;

import java.util.concurrent.Executor;
import rx.Scheduler;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;

public final class Schedulers
{
  private static final Schedulers INSTANCE = new Schedulers();
  private final Scheduler computationScheduler;
  private final Scheduler ioScheduler;
  private final Scheduler newThreadScheduler;
  
  private Schedulers()
  {
    Scheduler localScheduler = RxJavaPlugins.getInstance().getSchedulersHook().getComputationScheduler();
    if (localScheduler != null)
    {
      this.computationScheduler = localScheduler;
      localScheduler = RxJavaPlugins.getInstance().getSchedulersHook().getIOScheduler();
      if (localScheduler == null) {
        break label76;
      }
    }
    label76:
    for (this.ioScheduler = localScheduler;; this.ioScheduler = new CachedThreadScheduler())
    {
      localScheduler = RxJavaPlugins.getInstance().getSchedulersHook().getNewThreadScheduler();
      if (localScheduler == null) {
        break label90;
      }
      this.newThreadScheduler = localScheduler;
      return;
      this.computationScheduler = new EventLoopsScheduler();
      break;
    }
    label90:
    this.newThreadScheduler = NewThreadScheduler.instance();
  }
  
  public static Scheduler computation()
  {
    return INSTANCE.computationScheduler;
  }
  
  public static Scheduler from(Executor paramExecutor)
  {
    return new ExecutorScheduler(paramExecutor);
  }
  
  public static Scheduler immediate()
  {
    return ImmediateScheduler.instance();
  }
  
  public static Scheduler io()
  {
    return INSTANCE.ioScheduler;
  }
  
  public static Scheduler newThread()
  {
    return INSTANCE.newThreadScheduler;
  }
  
  public static TestScheduler test()
  {
    return new TestScheduler();
  }
  
  public static Scheduler trampoline()
  {
    return TrampolineScheduler.instance();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/schedulers/Schedulers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */