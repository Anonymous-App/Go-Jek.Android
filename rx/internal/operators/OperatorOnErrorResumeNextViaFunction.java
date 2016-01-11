package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.subscriptions.SerialSubscription;

public final class OperatorOnErrorResumeNextViaFunction<T>
  implements Observable.Operator<T, T>
{
  private final Func1<Throwable, ? extends Observable<? extends T>> resumeFunction;
  
  public OperatorOnErrorResumeNextViaFunction(Func1<Throwable, ? extends Observable<? extends T>> paramFunc1)
  {
    this.resumeFunction = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    ProducerArbiter localProducerArbiter = new ProducerArbiter();
    SerialSubscription localSerialSubscription = new SerialSubscription();
    OperatorOnErrorResumeNextViaFunction.1 local1 = new OperatorOnErrorResumeNextViaFunction.1(this, paramSubscriber, localProducerArbiter, localSerialSubscription);
    paramSubscriber.add(localSerialSubscription);
    localSerialSubscription.set(local1);
    paramSubscriber.setProducer(localProducerArbiter);
    return local1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorOnErrorResumeNextViaFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */