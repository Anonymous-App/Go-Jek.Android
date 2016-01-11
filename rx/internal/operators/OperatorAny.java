package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.producers.SingleDelayedProducer;

public final class OperatorAny<T>
  implements Observable.Operator<Boolean, T>
{
  private final Func1<? super T, Boolean> predicate;
  private final boolean returnOnEmpty;
  
  public OperatorAny(Func1<? super T, Boolean> paramFunc1, boolean paramBoolean)
  {
    this.predicate = paramFunc1;
    this.returnOnEmpty = paramBoolean;
  }
  
  public Subscriber<? super T> call(Subscriber<? super Boolean> paramSubscriber)
  {
    SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    OperatorAny.1 local1 = new OperatorAny.1(this, localSingleDelayedProducer, paramSubscriber);
    paramSubscriber.add(local1);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    return local1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorAny.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */