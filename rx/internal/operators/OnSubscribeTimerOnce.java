package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;

public final class OnSubscribeTimerOnce
  implements Observable.OnSubscribe<Long>
{
  final Scheduler scheduler;
  final long time;
  final TimeUnit unit;
  
  public OnSubscribeTimerOnce(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.time = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }
  
  public void call(Subscriber<? super Long> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    localWorker.schedule(new OnSubscribeTimerOnce.1(this, paramSubscriber), this.time, this.unit);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OnSubscribeTimerOnce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */