package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Func0;

public final class OnSubscribeDelaySubscriptionWithSelector<T, U>
  implements Observable.OnSubscribe<T>
{
  final Observable<? extends T> source;
  final Func0<? extends Observable<U>> subscriptionDelay;
  
  public OnSubscribeDelaySubscriptionWithSelector(Observable<? extends T> paramObservable, Func0<? extends Observable<U>> paramFunc0)
  {
    this.source = paramObservable;
    this.subscriptionDelay = paramFunc0;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    try
    {
      ((Observable)this.subscriptionDelay.call()).take(1).unsafeSubscribe(new OnSubscribeDelaySubscriptionWithSelector.1(this, paramSubscriber));
      return;
    }
    catch (Throwable localThrowable)
    {
      paramSubscriber.onError(localThrowable);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OnSubscribeDelaySubscriptionWithSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */