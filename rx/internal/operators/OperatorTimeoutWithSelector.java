package rx.internal.operators;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class OperatorTimeoutWithSelector<T, U, V>
  extends OperatorTimeoutBase<T>
{
  public OperatorTimeoutWithSelector(Func0<? extends Observable<U>> paramFunc0, Func1<? super T, ? extends Observable<V>> paramFunc1, Observable<? extends T> paramObservable)
  {
    super(new OperatorTimeoutWithSelector.1(paramFunc0), new OperatorTimeoutWithSelector.2(paramFunc1), paramObservable, Schedulers.immediate());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorTimeoutWithSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */