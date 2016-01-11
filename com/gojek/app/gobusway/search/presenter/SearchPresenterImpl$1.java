package com.gojek.app.gobusway.search.presenter;

import com.gojek.app.gobusway.model.HalteResponse;
import com.gojek.app.gobusway.search.view.SearchView;
import rx.Subscriber;

class SearchPresenterImpl$1
  extends Subscriber<HalteResponse>
{
  SearchPresenterImpl$1(SearchPresenterImpl paramSearchPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable) {}
  
  public void onNext(HalteResponse paramHalteResponse)
  {
    SearchPresenterImpl.access$000(this.this$0).showAllHalte(paramHalteResponse.getHalte());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/search/presenter/SearchPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */