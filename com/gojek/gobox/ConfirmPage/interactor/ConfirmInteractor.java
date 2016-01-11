package com.gojek.gobox.ConfirmPage.interactor;

import com.gojek.gobox.model.BookingRequestBody;
import com.gojek.gobox.model.CorporatePINResponse;
import com.gojek.gobox.model.CustomerResponse;
import com.gojek.gobox.model.OrderResponse;
import rx.Subscriber;

public abstract interface ConfirmInteractor
{
  public abstract void fetchCorporatePinResponse(String paramString, Subscriber<CorporatePINResponse> paramSubscriber);
  
  public abstract void fetchCostumerResponse(String paramString, Subscriber<CustomerResponse> paramSubscriber);
  
  public abstract void fetchOrderId(Subscriber<OrderResponse> paramSubscriber, int paramInt, BookingRequestBody paramBookingRequestBody);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ConfirmPage/interactor/ConfirmInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */