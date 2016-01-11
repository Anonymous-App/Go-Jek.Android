package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;

public final class OperatorThrottleFirst<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;
  private final long timeInMilliseconds;
  
  public OperatorThrottleFirst(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.timeInMilliseconds = paramTimeUnit.toMillis(paramLong);
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new OperatorThrottleFirst.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorThrottleFirst.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */