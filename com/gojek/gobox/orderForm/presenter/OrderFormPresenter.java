package com.gojek.gobox.orderForm.presenter;

import com.gojek.gobox.model.CargoType;

public abstract interface OrderFormPresenter
{
  public abstract void onAdditionalShipperChanged(int paramInt);
  
  public abstract void onCargoTypeChanged(CargoType paramCargoType);
  
  public abstract int onDestinationAdded();
  
  public abstract void onDestinationLocationPicked(int paramInt, double paramDouble1, double paramDouble2, String paramString);
  
  public abstract void onEstimationContainerClicked();
  
  public abstract void onLocationDeleted(int paramInt);
  
  public abstract void onNextButtonClicked();
  
  public abstract void onOrderViewCreate();
  
  public abstract void onOriginLocationPicked(double paramDouble1, double paramDouble2, String paramString);
  
  public abstract void onWaitingListVehicle(long paramLong, double paramDouble1, double paramDouble2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/presenter/OrderFormPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */