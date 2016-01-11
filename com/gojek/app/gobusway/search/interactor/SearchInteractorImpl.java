package com.gojek.app.gobusway.search.interactor;

import com.gojek.app.gobusway.model.HalteResponse;
import com.gojek.app.gobusway.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class SearchInteractorImpl
  implements SearchInteractor
{
  private Observable<NetworkService> mNetworkServiceObservable;
  
  public SearchInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
  }
  
  public void fetchAllHalte(Subscriber<HalteResponse> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(SearchInteractorImpl..Lambda.1.lambdaFactory$(paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/search/interactor/SearchInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */