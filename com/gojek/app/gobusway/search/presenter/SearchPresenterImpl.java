package com.gojek.app.gobusway.search.presenter;

import com.gojek.app.gobusway.search.interactor.SearchInteractor;
import com.gojek.app.gobusway.search.view.SearchView;

public class SearchPresenterImpl
  implements SearchPresenter
{
  private SearchInteractor mSearchInteractor;
  private SearchView mSearchView;
  
  public SearchPresenterImpl(SearchView paramSearchView, SearchInteractor paramSearchInteractor)
  {
    this.mSearchView = paramSearchView;
    this.mSearchInteractor = paramSearchInteractor;
  }
  
  public void onHalteListEmpty()
  {
    this.mSearchInteractor.fetchAllHalte(new SearchPresenterImpl.1(this));
  }
  
  public void onSearchCreateView()
  {
    this.mSearchView.initListView();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/search/presenter/SearchPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */