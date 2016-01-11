package com.gojek.gobox.orderForm.interactor;

import com.gojek.gobox.model.CargoType;
import com.gojek.gobox.model.CustomerResponse;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.model.Location;
import com.gojek.gobox.model.VehicleTypeResponse;
import com.gojek.gobox.orderForm.component.OnCalculateEstimationListener;
import java.util.ArrayList;
import rx.Subscriber;

public abstract interface OrderFormInteractor
{
  public abstract void addAdditionalShipper(int paramInt);
  
  public abstract void addDestinationLocationDetails(int paramInt, double paramDouble1, double paramDouble2, String paramString);
  
  public abstract int addNewDestination();
  
  public abstract void addOriginDetails(double paramDouble1, double paramDouble2, String paramString);
  
  public abstract void deleteDestination(int paramInt);
  
  public abstract void fetchAllVehicle(Subscriber<VehicleTypeResponse> paramSubscriber, long paramLong, double paramDouble1, double paramDouble2);
  
  public abstract void fetchEstimationPrice();
  
  public abstract void fetchGojekCredit(Subscriber<CustomerResponse> paramSubscriber);
  
  public abstract long getCargoId();
  
  public abstract ArrayList<Location> getDestinationLocationData();
  
  public abstract EstimationResponse getEstimationData();
  
  public abstract Location getOriginLocationData();
  
  public abstract void setOnCalculateEstimationListener(OnCalculateEstimationListener paramOnCalculateEstimationListener);
  
  public abstract void setSelectedCargoType(CargoType paramCargoType);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/interactor/OrderFormInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */