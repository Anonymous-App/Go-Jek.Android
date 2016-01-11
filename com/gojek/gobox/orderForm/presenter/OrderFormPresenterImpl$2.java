package com.gojek.gobox.orderForm.presenter;

import com.gojek.gobox.model.CustomerResponse;
import com.gojek.gobox.model.EstimatedPrice;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.model.Location;
import com.gojek.gobox.orderForm.interactor.OrderFormInteractor;
import com.gojek.gobox.orderForm.view.OrderFormView;
import java.util.ArrayList;
import rx.Subscriber;

class OrderFormPresenterImpl$2
  extends Subscriber<CustomerResponse>
{
  OrderFormPresenterImpl$2(OrderFormPresenterImpl paramOrderFormPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    OrderFormPresenterImpl.access$000(this.this$0).hideProgressBar();
    if (OrderFormPresenterImpl.access$100(this.this$0).getEstimationData().getEstimatedPrice().getTotal() >= OrderFormPresenterImpl.access$000(this.this$0).getEscrowCeiling())
    {
      OrderFormPresenterImpl.access$000(this.this$0).showErrorConfirmOrder(paramThrowable);
      return;
    }
    paramThrowable = OrderFormPresenterImpl.access$100(this.this$0).getOriginLocationData();
    ArrayList localArrayList = OrderFormPresenterImpl.access$100(this.this$0).getDestinationLocationData();
    EstimationResponse localEstimationResponse = OrderFormPresenterImpl.access$100(this.this$0).getEstimationData();
    long l = OrderFormPresenterImpl.access$100(this.this$0).getCargoId();
    OrderFormPresenterImpl.access$000(this.this$0).showConfirmationScreen(paramThrowable, localArrayList, localEstimationResponse, l, -1L);
  }
  
  public void onNext(CustomerResponse paramCustomerResponse)
  {
    OrderFormPresenterImpl.access$000(this.this$0).hideProgressBar();
    Location localLocation = OrderFormPresenterImpl.access$100(this.this$0).getOriginLocationData();
    ArrayList localArrayList = OrderFormPresenterImpl.access$100(this.this$0).getDestinationLocationData();
    EstimationResponse localEstimationResponse = OrderFormPresenterImpl.access$100(this.this$0).getEstimationData();
    long l = OrderFormPresenterImpl.access$100(this.this$0).getCargoId();
    OrderFormPresenterImpl.access$000(this.this$0).showConfirmationScreen(localLocation, localArrayList, localEstimationResponse, l, paramCustomerResponse.getCreditBalance());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/presenter/OrderFormPresenterImpl$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */