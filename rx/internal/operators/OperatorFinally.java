package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorFinally<T>
  implements Observable.Operator<T, T>
{
  final Action0 action;
  
  public OperatorFinally(Action0 paramAction0)
  {
    this.action = paramAction0;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new OperatorFinally.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorFinally.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */