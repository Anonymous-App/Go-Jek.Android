package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.observers.Subscribers;
import rx.subjects.PublishSubject;

public final class OperatorDelayWithSelector<T, V>
  implements Observable.Operator<T, T>
{
  final Func1<? super T, ? extends Observable<V>> itemDelay;
  final Observable<? extends T> source;
  
  public OperatorDelayWithSelector(Observable<? extends T> paramObservable, Func1<? super T, ? extends Observable<V>> paramFunc1)
  {
    this.source = paramObservable;
    this.itemDelay = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    PublishSubject localPublishSubject = PublishSubject.create();
    paramSubscriber.add(Observable.merge(localPublishSubject).unsafeSubscribe(Subscribers.from(localSerializedSubscriber)));
    return new OperatorDelayWithSelector.1(this, paramSubscriber, localPublishSubject, localSerializedSubscriber);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorDelayWithSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */