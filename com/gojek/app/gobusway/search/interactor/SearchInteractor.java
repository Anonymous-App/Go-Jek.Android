package com.gojek.app.gobusway.search.interactor;

import com.gojek.app.gobusway.model.HalteResponse;
import rx.Subscriber;

public abstract interface SearchInteractor
{
  public abstract void fetchAllHalte(Subscriber<HalteResponse> paramSubscriber);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/search/interactor/SearchInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */