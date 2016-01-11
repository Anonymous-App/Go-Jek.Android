package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;

public final class OnSubscribeTimerPeriodically
  implements Observable.OnSubscribe<Long>
{
  final long initialDelay;
  final long period;
  final Scheduler scheduler;
  final TimeUnit unit;
  
  public OnSubscribeTimerPeriodically(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.initialDelay = paramLong1;
    this.period = paramLong2;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }
  
  public void call(Subscriber<? super Long> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    localWorker.schedulePeriodically(new OnSubscribeTimerPeriodically.1(this, paramSubscriber, localWorker), this.initialDelay, this.period, this.unit);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OnSubscribeTimerPeriodically.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */