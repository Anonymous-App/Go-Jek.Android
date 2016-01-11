package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;

public class OperatorSubscribeOn<T>
  implements Observable.Operator<T, Observable<T>>
{
  private final Scheduler scheduler;
  
  public OperatorSubscribeOn(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super Observable<T>> call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    return new OperatorSubscribeOn.1(this, paramSubscriber, paramSubscriber, localWorker);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorSubscribeOn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */