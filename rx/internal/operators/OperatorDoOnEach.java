package rx.internal.operators;

import rx.Observable.Operator;
import rx.Observer;
import rx.Subscriber;

public class OperatorDoOnEach<T>
  implements Observable.Operator<T, T>
{
  private final Observer<? super T> doOnEachObserver;
  
  public OperatorDoOnEach(Observer<? super T> paramObserver)
  {
    this.doOnEachObserver = paramObserver;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new OperatorDoOnEach.1(this, paramSubscriber, paramSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorDoOnEach.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */