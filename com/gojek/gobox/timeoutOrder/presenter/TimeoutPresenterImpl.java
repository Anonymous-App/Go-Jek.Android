package com.gojek.gobox.timeoutOrder.presenter;

import com.gojek.gobox.timeoutOrder.interactor.TimeoutOrderInteractor;
import com.gojek.gobox.timeoutOrder.view.TimeoutOrderView;

public class TimeoutPresenterImpl
  implements TimeoutPresenter
{
  private TimeoutOrderInteractor mTimeoutOrderInteractor;
  private TimeoutOrderView mTimeoutOrderView;
  
  public TimeoutPresenterImpl(TimeoutOrderView paramTimeoutOrderView, TimeoutOrderInteractor paramTimeoutOrderInteractor)
  {
    this.mTimeoutOrderView = paramTimeoutOrderView;
    this.mTimeoutOrderInteractor = paramTimeoutOrderInteractor;
  }
  
  public void onInitView()
  {
    this.mTimeoutOrderView.initView();
  }
  
  public void onReorderAction(String paramString)
  {
    this.mTimeoutOrderInteractor.fetchReorderResponse(new TimeoutPresenterImpl.1(this), paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/timeoutOrder/presenter/TimeoutPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */