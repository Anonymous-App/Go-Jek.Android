package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;

public final class OperatorFilter<T>
  implements Observable.Operator<T, T>
{
  private final Func1<? super T, Boolean> predicate;
  
  public OperatorFilter(Func1<? super T, Boolean> paramFunc1)
  {
    this.predicate = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new OperatorFilter.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */