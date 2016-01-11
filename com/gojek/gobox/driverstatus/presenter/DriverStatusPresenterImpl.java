package com.gojek.gobox.driverstatus.presenter;

import com.gojek.gobox.driverstatus.interactor.DriverStatusInteractor;
import com.gojek.gobox.driverstatus.view.DriverStatusView;
import com.gojek.gobox.model.BookingInfoResponse;

public class DriverStatusPresenterImpl
  implements DriverStatusPresenter
{
  private BookingInfoResponse mBookingInfoResponse;
  private String mCancelOrderId;
  private DriverStatusInteractor mDriverStatusInteractor;
  private DriverStatusView mDriverStatusView;
  
  public DriverStatusPresenterImpl(DriverStatusView paramDriverStatusView, DriverStatusInteractor paramDriverStatusInteractor)
  {
    this.mDriverStatusView = paramDriverStatusView;
    this.mDriverStatusInteractor = paramDriverStatusInteractor;
  }
  
  public void onCallingCallCenterButtonClicked()
  {
    this.mDriverStatusView.callingCallCenterButtonClicked();
  }
  
  public void onCancelButtonClicked(String paramString)
  {
    this.mCancelOrderId = paramString;
    this.mDriverStatusView.showCancelConfirmationDialog();
  }
  
  public void onDoCanceling()
  {
    this.mDriverStatusView.showCancelingProgress();
    this.mDriverStatusInteractor.cancelBooking(new DriverStatusPresenterImpl.3(this), this.mCancelOrderId);
  }
  
  public void onDriverStatusCreateView(String paramString)
  {
    this.mDriverStatusView.initViews();
    this.mDriverStatusView.showProgressBar(false);
    this.mDriverStatusInteractor.fetchBookingData(new DriverStatusPresenterImpl.1(this), paramString);
  }
  
  public void onOrderUnconfirmedState(String paramString)
  {
    this.mDriverStatusView.showProgressBar(false);
    this.mDriverStatusInteractor.confirmOrder(paramString, 6, new DriverStatusPresenterImpl.5(this));
  }
  
  public void onRefreshStatus(String paramString)
  {
    this.mDriverStatusView.showToolBarProgress();
    this.mDriverStatusInteractor.fetchBookingStatus(new DriverStatusPresenterImpl.2(this), paramString);
  }
  
  public void onSendInvoiceButtonClicked(String paramString1, String paramString2)
  {
    this.mDriverStatusView.showProgressBar(true);
    this.mDriverStatusInteractor.sendInvoice(paramString1, paramString2, new DriverStatusPresenterImpl.4(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/presenter/DriverStatusPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */