package rx.plugins;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Subscription;

public abstract class RxJavaObservableExecutionHook
{
  public <T> Observable.OnSubscribe<T> onCreate(Observable.OnSubscribe<T> paramOnSubscribe)
  {
    return paramOnSubscribe;
  }
  
  public <T, R> Observable.Operator<? extends R, ? super T> onLift(Observable.Operator<? extends R, ? super T> paramOperator)
  {
    return paramOperator;
  }
  
  public <T> Throwable onSubscribeError(Throwable paramThrowable)
  {
    return paramThrowable;
  }
  
  public <T> Subscription onSubscribeReturn(Subscription paramSubscription)
  {
    return paramSubscription;
  }
  
  public <T> Observable.OnSubscribe<T> onSubscribeStart(Observable<? extends T> paramObservable, Observable.OnSubscribe<T> paramOnSubscribe)
  {
    return paramOnSubscribe;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/plugins/RxJavaObservableExecutionHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */