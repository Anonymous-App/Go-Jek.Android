package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;

public class OperatorSkipLast<T>
  implements Observable.Operator<T, T>
{
  private final int count;
  
  public OperatorSkipLast(int paramInt)
  {
    if (paramInt < 0) {
      throw new IndexOutOfBoundsException("count could not be negative");
    }
    this.count = paramInt;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new OperatorSkipLast.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorSkipLast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */