package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.Subscribers;

public class OperatorDoOnSubscribe<T>
  implements Observable.Operator<T, T>
{
  private final Action0 subscribe;
  
  public OperatorDoOnSubscribe(Action0 paramAction0)
  {
    this.subscribe = paramAction0;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    this.subscribe.call();
    return Subscribers.wrap(paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorDoOnSubscribe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */