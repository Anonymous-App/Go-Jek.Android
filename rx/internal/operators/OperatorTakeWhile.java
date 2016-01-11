package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

public final class OperatorTakeWhile<T>
  implements Observable.Operator<T, T>
{
  private final Func2<? super T, ? super Integer, Boolean> predicate;
  
  public OperatorTakeWhile(Func1<? super T, Boolean> paramFunc1)
  {
    this(new OperatorTakeWhile.1(paramFunc1));
  }
  
  public OperatorTakeWhile(Func2<? super T, ? super Integer, Boolean> paramFunc2)
  {
    this.predicate = paramFunc2;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    OperatorTakeWhile.2 local2 = new OperatorTakeWhile.2(this, paramSubscriber, false, paramSubscriber);
    paramSubscriber.add(local2);
    return local2;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorTakeWhile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */