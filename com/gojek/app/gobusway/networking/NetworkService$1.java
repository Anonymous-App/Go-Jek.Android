package com.gojek.app.gobusway.networking;

import rx.Observable;
import rx.functions.Func1;

class NetworkService$1
  implements Func1<Boolean, Observable<T>>
{
  NetworkService$1(NetworkService paramNetworkService, Observable paramObservable, Throwable paramThrowable) {}
  
  public Observable<T> call(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue()) {
      return this.this$0.handleRefreshToken(this.val$observable);
    }
    return Observable.error(this.val$throwable);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/networking/NetworkService$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */