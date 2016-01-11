package com.gojek.app.gobusway.util;

import com.gojek.app.gobusway.map.interactor.BusWayMapInteractorImpl;
import com.gojek.app.gobusway.map.presenter.BusWayMapPresenter;
import com.gojek.app.gobusway.map.presenter.BusWayMapPresenterImpl;
import com.gojek.app.gobusway.map.view.BusWayMapView;
import com.gojek.app.gobusway.networking.NetworkService;
import com.gojek.app.gobusway.schedule.interactor.ScheduleInteractorImpl;
import com.gojek.app.gobusway.schedule.presenter.SchedulePresenter;
import com.gojek.app.gobusway.schedule.presenter.SchedulePresenterImpl;
import com.gojek.app.gobusway.schedule.view.ScheduleView;
import com.gojek.app.gobusway.search.interactor.SearchInteractorImpl;
import com.gojek.app.gobusway.search.presenter.SearchPresenter;
import com.gojek.app.gobusway.search.presenter.SearchPresenterImpl;
import com.gojek.app.gobusway.search.view.SearchView;
import rx.Observable;

public class PresenterFactory
{
  public static BusWayMapPresenter createBusWayMapPresenter(BusWayMapView paramBusWayMapView, Observable<NetworkService> paramObservable)
  {
    return new BusWayMapPresenterImpl(paramBusWayMapView, new BusWayMapInteractorImpl(paramObservable));
  }
  
  public static SchedulePresenter createSchedulePresenter(ScheduleView paramScheduleView, Observable<NetworkService> paramObservable)
  {
    return new SchedulePresenterImpl(paramScheduleView, new ScheduleInteractorImpl(paramObservable));
  }
  
  public static SearchPresenter createSearchPresenter(SearchView paramSearchView, Observable<NetworkService> paramObservable)
  {
    return new SearchPresenterImpl(paramSearchView, new SearchInteractorImpl(paramObservable));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/util/PresenterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */