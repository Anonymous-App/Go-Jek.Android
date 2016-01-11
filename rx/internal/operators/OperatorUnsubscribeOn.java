package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.Subscriptions;

public class OperatorUnsubscribeOn<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;
  
  public OperatorUnsubscribeOn(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    OperatorUnsubscribeOn.1 local1 = new OperatorUnsubscribeOn.1(this, paramSubscriber);
    paramSubscriber.add(Subscriptions.create(new OperatorUnsubscribeOn.2(this, local1)));
    return local1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorUnsubscribeOn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */