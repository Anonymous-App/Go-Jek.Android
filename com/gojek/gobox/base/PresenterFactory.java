package com.gojek.gobox.base;

import com.gojek.gobox.ConfirmPage.interactor.ConfirmInteractorImpl;
import com.gojek.gobox.ConfirmPage.presenter.ConfirmPresenter;
import com.gojek.gobox.ConfirmPage.presenter.ConfirmPresenterImpl;
import com.gojek.gobox.ConfirmPage.view.ConfirmView;
import com.gojek.gobox.bookingstatus.interactor.BookingStatusInteractorImpl;
import com.gojek.gobox.bookingstatus.presenter.BookingStatusPresenter;
import com.gojek.gobox.bookingstatus.presenter.BookingStatusPresenterImpl;
import com.gojek.gobox.bookingstatus.view.BookingStatusView;
import com.gojek.gobox.cargoType.interactor.CargoTypeInteractorImpl;
import com.gojek.gobox.cargoType.presenter.CargoTypePresenter;
import com.gojek.gobox.cargoType.presenter.CargoTypePresenterImpl;
import com.gojek.gobox.cargoType.view.CargoTypeView;
import com.gojek.gobox.driverreview.interactor.DriverReviewInteractorImpl;
import com.gojek.gobox.driverreview.presenter.DriverReviewPresenter;
import com.gojek.gobox.driverreview.presenter.DriverReviewPresenterImpl;
import com.gojek.gobox.driverreview.view.DriverReviewView;
import com.gojek.gobox.driverstatus.interactor.DriverStatusInteractorImpl;
import com.gojek.gobox.driverstatus.presenter.DriverStatusPresenter;
import com.gojek.gobox.driverstatus.presenter.DriverStatusPresenterImpl;
import com.gojek.gobox.driverstatus.view.DriverStatusView;
import com.gojek.gobox.networking.NetworkService;
import com.gojek.gobox.orderForm.interactor.OrderFormInteractorImpl;
import com.gojek.gobox.orderForm.presenter.OrderFormPresenter;
import com.gojek.gobox.orderForm.presenter.OrderFormPresenterImpl;
import com.gojek.gobox.orderForm.view.OrderFormView;
import com.gojek.gobox.ordercompleteconfirmation.interactor.OrderCompleteConfirmationInteractorImpl;
import com.gojek.gobox.ordercompleteconfirmation.presenter.OrderCompleteConfirmationPresenter;
import com.gojek.gobox.ordercompleteconfirmation.presenter.OrderCompleteConfirmationPresenterImpl;
import com.gojek.gobox.ordercompleteconfirmation.view.OrderCompleteConfirmationView;
import com.gojek.gobox.timeoutOrder.interactor.TimeoutOrderInteractorImpl;
import com.gojek.gobox.timeoutOrder.presenter.TimeoutPresenter;
import com.gojek.gobox.timeoutOrder.presenter.TimeoutPresenterImpl;
import com.gojek.gobox.timeoutOrder.view.TimeoutOrderView;
import rx.Observable;

public class PresenterFactory
{
  public static BookingStatusPresenter createBookingStatusPresenter(BookingStatusView paramBookingStatusView, Observable<NetworkService> paramObservable)
  {
    return new BookingStatusPresenterImpl(paramBookingStatusView, new BookingStatusInteractorImpl(paramObservable));
  }
  
  public static CargoTypePresenter createCargoTypePresenter(CargoTypeView paramCargoTypeView, Observable<NetworkService> paramObservable)
  {
    return new CargoTypePresenterImpl(paramCargoTypeView, new CargoTypeInteractorImpl(paramObservable));
  }
  
  public static ConfirmPresenter createConfirmPresenterFactory(ConfirmView paramConfirmView, Observable<NetworkService> paramObservable)
  {
    return new ConfirmPresenterImpl(paramConfirmView, new ConfirmInteractorImpl(paramObservable));
  }
  
  public static DriverReviewPresenter createDriverReviewPresenter(DriverReviewView paramDriverReviewView, Observable<NetworkService> paramObservable)
  {
    return new DriverReviewPresenterImpl(paramDriverReviewView, new DriverReviewInteractorImpl(paramObservable));
  }
  
  public static DriverStatusPresenter createDriverStatusPresenter(DriverStatusView paramDriverStatusView, Observable<NetworkService> paramObservable)
  {
    return new DriverStatusPresenterImpl(paramDriverStatusView, new DriverStatusInteractorImpl(paramObservable));
  }
  
  public static OrderCompleteConfirmationPresenter createOrderCompleteConfirmationPresenter(OrderCompleteConfirmationView paramOrderCompleteConfirmationView, Observable<NetworkService> paramObservable)
  {
    return new OrderCompleteConfirmationPresenterImpl(paramOrderCompleteConfirmationView, new OrderCompleteConfirmationInteractorImpl(paramObservable));
  }
  
  public static OrderFormPresenter createOrderFormPresenterFactory(OrderFormView paramOrderFormView, Observable<NetworkService> paramObservable)
  {
    return new OrderFormPresenterImpl(paramOrderFormView, new OrderFormInteractorImpl(paramObservable));
  }
  
  public static TimeoutPresenter createTimeoutPresenterFactory(TimeoutOrderView paramTimeoutOrderView, Observable<NetworkService> paramObservable)
  {
    return new TimeoutPresenterImpl(paramTimeoutOrderView, new TimeoutOrderInteractorImpl(paramObservable));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/base/PresenterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */