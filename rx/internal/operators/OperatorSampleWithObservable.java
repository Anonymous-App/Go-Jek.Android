package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

public final class OperatorSampleWithObservable<T, U>
  implements Observable.Operator<T, T>
{
  static final Object EMPTY_TOKEN = new Object();
  final Observable<U> sampler;
  
  public OperatorSampleWithObservable(Observable<U> paramObservable)
  {
    this.sampler = paramObservable;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    AtomicReference localAtomicReference = new AtomicReference(EMPTY_TOKEN);
    OperatorSampleWithObservable.1 local1 = new OperatorSampleWithObservable.1(this, paramSubscriber, localAtomicReference, localSerializedSubscriber);
    paramSubscriber = new OperatorSampleWithObservable.2(this, paramSubscriber, localAtomicReference, localSerializedSubscriber);
    this.sampler.unsafeSubscribe(local1);
    return paramSubscriber;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorSampleWithObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */