package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.TimeInterval;

public final class OperatorTimeInterval<T>
  implements Observable.Operator<TimeInterval<T>, T>
{
  private final Scheduler scheduler;
  
  public OperatorTimeInterval(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super TimeInterval<T>> paramSubscriber)
  {
    return new OperatorTimeInterval.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorTimeInterval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */