package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;

public final class OperatorTimeout<T>
  extends OperatorTimeoutBase<T>
{
  public OperatorTimeout(long paramLong, TimeUnit paramTimeUnit, Observable<? extends T> paramObservable, Scheduler paramScheduler)
  {
    super(new OperatorTimeout.1(paramLong, paramTimeUnit), new OperatorTimeout.2(paramLong, paramTimeUnit), paramObservable, paramScheduler);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorTimeout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */