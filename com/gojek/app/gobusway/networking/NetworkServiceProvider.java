package com.gojek.app.gobusway.networking;

import rx.Observable;

public abstract interface NetworkServiceProvider
{
  public abstract Observable<NetworkService> getNetworkManager();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/networking/NetworkServiceProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */