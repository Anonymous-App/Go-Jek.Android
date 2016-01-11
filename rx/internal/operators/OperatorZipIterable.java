package rx.internal.operators;

import java.util.Iterator;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func2;
import rx.observers.Subscribers;

public final class OperatorZipIterable<T1, T2, R>
  implements Observable.Operator<R, T1>
{
  final Iterable<? extends T2> iterable;
  final Func2<? super T1, ? super T2, ? extends R> zipFunction;
  
  public OperatorZipIterable(Iterable<? extends T2> paramIterable, Func2<? super T1, ? super T2, ? extends R> paramFunc2)
  {
    this.iterable = paramIterable;
    this.zipFunction = paramFunc2;
  }
  
  public Subscriber<? super T1> call(Subscriber<? super R> paramSubscriber)
  {
    Iterator localIterator = this.iterable.iterator();
    try
    {
      if (!localIterator.hasNext())
      {
        paramSubscriber.onCompleted();
        Subscriber localSubscriber = Subscribers.empty();
        return localSubscriber;
      }
    }
    catch (Throwable localThrowable)
    {
      paramSubscriber.onError(localThrowable);
    }
    return new OperatorZipIterable.1(this, paramSubscriber, paramSubscriber, localIterator);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorZipIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */