package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;

public class OperatorSkipLastTimed<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;
  private final long timeInMillis;
  
  public OperatorSkipLastTimed(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.timeInMillis = paramTimeUnit.toMillis(paramLong);
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new OperatorSkipLastTimed.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorSkipLastTimed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */