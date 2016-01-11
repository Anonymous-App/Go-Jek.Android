package com.gojek.gobox.orderForm.presenter;

import com.gojek.gobox.model.VehicleTypeResponse;
import com.gojek.gobox.orderForm.view.OrderFormView;
import rx.Subscriber;

class OrderFormPresenterImpl$1
  extends Subscriber<VehicleTypeResponse>
{
  OrderFormPresenterImpl$1(OrderFormPresenterImpl paramOrderFormPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    OrderFormPresenterImpl.access$000(this.this$0).showErrorConfirmOrder(paramThrowable);
  }
  
  public void onNext(VehicleTypeResponse paramVehicleTypeResponse)
  {
    OrderFormPresenterImpl.access$000(this.this$0).populateVehicleType(paramVehicleTypeResponse);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/presenter/OrderFormPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */