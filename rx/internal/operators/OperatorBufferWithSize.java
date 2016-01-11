package rx.internal.operators;

import java.util.List;
import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorBufferWithSize<T>
  implements Observable.Operator<List<T>, T>
{
  final int count;
  final int skip;
  
  public OperatorBufferWithSize(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0) {
      throw new IllegalArgumentException("count must be greater than 0");
    }
    if (paramInt2 <= 0) {
      throw new IllegalArgumentException("skip must be greater than 0");
    }
    this.count = paramInt1;
    this.skip = paramInt2;
  }
  
  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    if (this.count == this.skip) {
      return new OperatorBufferWithSize.1(this, paramSubscriber, paramSubscriber);
    }
    return new OperatorBufferWithSize.2(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorBufferWithSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */