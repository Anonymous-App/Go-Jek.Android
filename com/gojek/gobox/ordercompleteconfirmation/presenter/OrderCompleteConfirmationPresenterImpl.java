package com.gojek.gobox.ordercompleteconfirmation.presenter;

import com.gojek.gobox.ordercompleteconfirmation.interactor.OrderCompleteConfirmationInteractor;
import com.gojek.gobox.ordercompleteconfirmation.view.OrderCompleteConfirmationView;

public class OrderCompleteConfirmationPresenterImpl
  implements OrderCompleteConfirmationPresenter
{
  private OrderCompleteConfirmationInteractor mOrderCompleteConfirmationInteractor;
  private OrderCompleteConfirmationView mOrderCompleteConfirmationView;
  
  public OrderCompleteConfirmationPresenterImpl(OrderCompleteConfirmationView paramOrderCompleteConfirmationView, OrderCompleteConfirmationInteractor paramOrderCompleteConfirmationInteractor)
  {
    this.mOrderCompleteConfirmationView = paramOrderCompleteConfirmationView;
    this.mOrderCompleteConfirmationInteractor = paramOrderCompleteConfirmationInteractor;
  }
  
  public void onCreateView(String paramString)
  {
    this.mOrderCompleteConfirmationView.initView();
    this.mOrderCompleteConfirmationView.showProgressBar("");
    this.mOrderCompleteConfirmationInteractor.fetchConfirmationRemainingTime(paramString, new OrderCompleteConfirmationPresenterImpl.1(this));
  }
  
  public void onNoButtonClicked(String paramString)
  {
    this.mOrderCompleteConfirmationView.showProgressBar("Please wait ...");
    this.mOrderCompleteConfirmationInteractor.confirmOrder(paramString, 5, new OrderCompleteConfirmationPresenterImpl.3(this));
  }
  
  public void onYesButtonClicked(String paramString)
  {
    this.mOrderCompleteConfirmationView.showProgressBar("Please wait ...");
    this.mOrderCompleteConfirmationInteractor.confirmOrder(paramString, 6, new OrderCompleteConfirmationPresenterImpl.2(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ordercompleteconfirmation/presenter/OrderCompleteConfirmationPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */