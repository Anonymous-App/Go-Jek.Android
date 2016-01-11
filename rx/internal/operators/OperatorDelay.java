package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;

public final class OperatorDelay<T>
  implements Observable.Operator<T, T>
{
  final long delay;
  final Scheduler scheduler;
  final Observable<? extends T> source;
  final TimeUnit unit;
  
  public OperatorDelay(Observable<? extends T> paramObservable, long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.source = paramObservable;
    this.delay = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    return new OperatorDelay.1(this, paramSubscriber, localWorker, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorDelay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */