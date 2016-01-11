package com.gojek.gobox.orderForm.presenter;

import com.gojek.gobox.model.CargoType;
import com.gojek.gobox.model.EstimatedPrice;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.orderForm.component.OnCalculateEstimationListener;
import com.gojek.gobox.orderForm.interactor.OrderFormInteractor;
import com.gojek.gobox.orderForm.view.OrderFormView;

public class OrderFormPresenterImpl
  implements OnCalculateEstimationListener, OrderFormPresenter
{
  private OrderFormInteractor mOrderFormInteractor;
  private OrderFormView mOrderFormView;
  
  public OrderFormPresenterImpl(OrderFormView paramOrderFormView, OrderFormInteractor paramOrderFormInteractor)
  {
    this.mOrderFormView = paramOrderFormView;
    this.mOrderFormInteractor = paramOrderFormInteractor;
  }
  
  public void onAdditionalShipperChanged(int paramInt)
  {
    this.mOrderFormInteractor.addAdditionalShipper(paramInt);
  }
  
  public void onCalculating()
  {
    this.mOrderFormView.onCalculatingEstimation();
  }
  
  public void onCalculatingError(Throwable paramThrowable)
  {
    this.mOrderFormView.onCalculationError(paramThrowable);
  }
  
  public void onCargoTypeChanged(CargoType paramCargoType)
  {
    this.mOrderFormInteractor.setSelectedCargoType(paramCargoType);
  }
  
  public int onDestinationAdded()
  {
    return this.mOrderFormInteractor.addNewDestination();
  }
  
  public void onDestinationLocationPicked(int paramInt, double paramDouble1, double paramDouble2, String paramString)
  {
    this.mOrderFormInteractor.addDestinationLocationDetails(paramInt, paramDouble1, paramDouble2, paramString);
  }
  
  public void onEstimationContainerClicked()
  {
    this.mOrderFormInteractor.fetchEstimationPrice();
  }
  
  public void onEstimationReceived(EstimationResponse paramEstimationResponse)
  {
    this.mOrderFormView.onCalculationSuccess(paramEstimationResponse.getEstimatedDistance(), paramEstimationResponse.getEstimatedPrice().getTotal());
  }
  
  public void onLocationDeleted(int paramInt)
  {
    this.mOrderFormInteractor.deleteDestination(paramInt);
  }
  
  public void onNextButtonClicked()
  {
    this.mOrderFormView.showProgressBar();
    this.mOrderFormInteractor.fetchGojekCredit(new OrderFormPresenterImpl.2(this));
  }
  
  public void onNoDestinationPicked()
  {
    this.mOrderFormView.hideEstimationView();
  }
  
  public void onOrderViewCreate()
  {
    this.mOrderFormInteractor.setOnCalculateEstimationListener(this);
    this.mOrderFormView.initOrderForm();
    this.mOrderFormView.initVehicleOption();
    this.mOrderFormView.initMapView();
  }
  
  public void onOriginLocationPicked(double paramDouble1, double paramDouble2, String paramString)
  {
    this.mOrderFormInteractor.addOriginDetails(paramDouble1, paramDouble2, paramString);
  }
  
  public void onWaitingListVehicle(long paramLong, double paramDouble1, double paramDouble2)
  {
    this.mOrderFormInteractor.fetchAllVehicle(new OrderFormPresenterImpl.1(this), paramLong, paramDouble1, paramDouble2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/presenter/OrderFormPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */