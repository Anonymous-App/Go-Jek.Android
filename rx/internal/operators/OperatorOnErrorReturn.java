package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;

public final class OperatorOnErrorReturn<T>
  implements Observable.Operator<T, T>
{
  final Func1<Throwable, ? extends T> resultFunction;
  
  public OperatorOnErrorReturn(Func1<Throwable, ? extends T> paramFunc1)
  {
    this.resultFunction = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    OperatorOnErrorReturn.1 local1 = new OperatorOnErrorReturn.1(this, paramSubscriber);
    paramSubscriber.add(local1);
    return local1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorOnErrorReturn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */