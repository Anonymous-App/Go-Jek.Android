package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

public final class OperatorDebounceWithSelector<T, U>
  implements Observable.Operator<T, T>
{
  final Func1<? super T, ? extends Observable<U>> selector;
  
  public OperatorDebounceWithSelector(Func1<? super T, ? extends Observable<U>> paramFunc1)
  {
    this.selector = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    SerialSubscription localSerialSubscription = new SerialSubscription();
    paramSubscriber.add(localSerialSubscription);
    return new OperatorDebounceWithSelector.1(this, paramSubscriber, localSerializedSubscriber, localSerialSubscription);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorDebounceWithSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */