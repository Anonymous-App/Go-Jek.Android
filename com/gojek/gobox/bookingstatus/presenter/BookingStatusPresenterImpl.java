package com.gojek.gobox.bookingstatus.presenter;

import com.gojek.gobox.bookingstatus.interactor.BookingStatusInteractor;
import com.gojek.gobox.bookingstatus.view.BookingStatusView;

public class BookingStatusPresenterImpl
  implements BookingStatusPresenter
{
  private BookingStatusInteractor mBookingStatusInteractor;
  private BookingStatusView mBookingStatusView;
  private String mCancelBookingId;
  
  public BookingStatusPresenterImpl(BookingStatusView paramBookingStatusView, BookingStatusInteractor paramBookingStatusInteractor)
  {
    this.mBookingStatusView = paramBookingStatusView;
    this.mBookingStatusInteractor = paramBookingStatusInteractor;
  }
  
  public void onBookingStatusViewCreated()
  {
    this.mBookingStatusView.initView();
  }
  
  public void onCancelButtonClicked(String paramString)
  {
    this.mCancelBookingId = paramString;
    this.mBookingStatusView.showCancelConfirmationDialog();
  }
  
  public void onDoCanceling()
  {
    this.mBookingStatusView.showCancelingProgress();
    this.mBookingStatusInteractor.cancelBooking(this.mCancelBookingId, new BookingStatusPresenterImpl.1(this));
  }
  
  public void onRefreshButtonClicked(String paramString)
  {
    this.mBookingStatusView.showToolBarProgress();
    this.mBookingStatusInteractor.refreshBookingStatus(paramString, new BookingStatusPresenterImpl.2(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/presenter/BookingStatusPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */