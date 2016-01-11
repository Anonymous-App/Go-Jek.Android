package rx.internal.operators;

import rx.Observable;
import rx.Single.OnSubscribe;
import rx.SingleSubscriber;

public class OnSubscribeSingle<T>
  implements Single.OnSubscribe<T>
{
  private final Observable<T> observable;
  
  public OnSubscribeSingle(Observable<T> paramObservable)
  {
    this.observable = paramObservable;
  }
  
  public static <T> OnSubscribeSingle<T> create(Observable<T> paramObservable)
  {
    return new OnSubscribeSingle(paramObservable);
  }
  
  public void call(SingleSubscriber<? super T> paramSingleSubscriber)
  {
    OnSubscribeSingle.1 local1 = new OnSubscribeSingle.1(this, paramSingleSubscriber);
    paramSingleSubscriber.add(local1);
    this.observable.unsafeSubscribe(local1);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OnSubscribeSingle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */