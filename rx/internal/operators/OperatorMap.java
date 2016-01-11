package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;

public final class OperatorMap<T, R>
  implements Observable.Operator<R, T>
{
  private final Func1<? super T, ? extends R> transformer;
  
  public OperatorMap(Func1<? super T, ? extends R> paramFunc1)
  {
    this.transformer = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
  {
    return new OperatorMap.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */