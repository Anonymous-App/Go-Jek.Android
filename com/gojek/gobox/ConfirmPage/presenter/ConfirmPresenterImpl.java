package com.gojek.gobox.ConfirmPage.presenter;

import com.gojek.gobox.ConfirmPage.interactor.ConfirmInteractor;
import com.gojek.gobox.ConfirmPage.view.ConfirmView;
import com.gojek.gobox.model.BookingRequestBody;

public class ConfirmPresenterImpl
  implements ConfirmPresenter
{
  private ConfirmInteractor mConfirmInteractor;
  private ConfirmView mConfirmView;
  
  public ConfirmPresenterImpl(ConfirmView paramConfirmView, ConfirmInteractor paramConfirmInteractor)
  {
    this.mConfirmInteractor = paramConfirmInteractor;
    this.mConfirmView = paramConfirmView;
  }
  
  public void onConfirmScreenResumed(String paramString)
  {
    this.mConfirmView.onFetchingGoJekCredit();
    this.mConfirmInteractor.fetchCostumerResponse(paramString, new ConfirmPresenterImpl.2(this));
  }
  
  public void onCreateView()
  {
    this.mConfirmView.initView();
  }
  
  public void onOrderAction(int paramInt, BookingRequestBody paramBookingRequestBody)
  {
    this.mConfirmView.showConfirmProgressBar();
    this.mConfirmInteractor.fetchOrderId(new ConfirmPresenterImpl.1(this), paramInt, paramBookingRequestBody);
  }
  
  public void onPopulateData()
  {
    this.mConfirmView.populateView();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ConfirmPage/presenter/ConfirmPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */