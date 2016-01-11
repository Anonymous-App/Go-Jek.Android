package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorSkip<T>
  implements Observable.Operator<T, T>
{
  final int toSkip;
  
  public OperatorSkip(int paramInt)
  {
    this.toSkip = paramInt;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new OperatorSkip.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorSkip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */