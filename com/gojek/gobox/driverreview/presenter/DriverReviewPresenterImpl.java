package com.gojek.gobox.driverreview.presenter;

import com.gojek.gobox.driverreview.interactor.DriverReviewInteractor;
import com.gojek.gobox.driverreview.view.DriverReviewView;

public class DriverReviewPresenterImpl
  implements DriverReviewPresenter
{
  private DriverReviewInteractor mDriverReviewInteractor;
  private DriverReviewView mDriverReviewView;
  
  public DriverReviewPresenterImpl(DriverReviewView paramDriverReviewView, DriverReviewInteractor paramDriverReviewInteractor)
  {
    this.mDriverReviewView = paramDriverReviewView;
    this.mDriverReviewInteractor = paramDriverReviewInteractor;
  }
  
  public void onDriverReviewPresenterCreateView()
  {
    this.mDriverReviewView.initOrderData();
    this.mDriverReviewView.initView();
    this.mDriverReviewView.hideProgressBar();
  }
  
  public void onSubmitButtonClicked(String paramString1, int paramInt, String paramString2)
  {
    this.mDriverReviewView.hideSubmitButton();
    this.mDriverReviewView.showProgressBar();
    this.mDriverReviewInteractor.submitReview(paramString1, paramInt, paramString2, new DriverReviewPresenterImpl.1(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverreview/presenter/DriverReviewPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */