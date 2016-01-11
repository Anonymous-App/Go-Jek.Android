package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorTake<T>
  implements Observable.Operator<T, T>
{
  final int limit;
  
  public OperatorTake(int paramInt)
  {
    this.limit = paramInt;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    OperatorTake.1 local1 = new OperatorTake.1(this, paramSubscriber);
    if (this.limit == 0)
    {
      paramSubscriber.onCompleted();
      local1.unsubscribe();
    }
    paramSubscriber.add(local1);
    return local1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorTake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */