package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;

public final class OnSubscribeDelaySubscription<T>
  implements Observable.OnSubscribe<T>
{
  final Scheduler scheduler;
  final Observable<? extends T> source;
  final long time;
  final TimeUnit unit;
  
  public OnSubscribeDelaySubscription(Observable<? extends T> paramObservable, long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.source = paramObservable;
    this.time = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    localWorker.schedule(new OnSubscribeDelaySubscription.1(this, paramSubscriber), this.time, this.unit);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OnSubscribeDelaySubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */