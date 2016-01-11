package com.gojek.gobox.orderForm.view;

import android.view.View;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.model.Location;
import com.gojek.gobox.model.VehicleTypeResponse;
import java.util.ArrayList;

public abstract interface OrderFormView
{
  public abstract void addNextDestination();
  
  public abstract long getEscrowCeiling();
  
  public abstract void hideEstimationView();
  
  public abstract void hideProgressBar();
  
  public abstract void initMapView();
  
  public abstract void initOrderForm();
  
  public abstract void initVehicleOption();
  
  public abstract void onCalculatingEstimation();
  
  public abstract void onCalculationError(Throwable paramThrowable);
  
  public abstract void onCalculationSuccess(double paramDouble1, double paramDouble2);
  
  public abstract void openTimePicker();
  
  public abstract void populateVehicleType(VehicleTypeResponse paramVehicleTypeResponse);
  
  public abstract void removeNextDestination(View paramView);
  
  public abstract void showConfirmationScreen(Location paramLocation, ArrayList<Location> paramArrayList, EstimationResponse paramEstimationResponse, long paramLong1, long paramLong2);
  
  public abstract void showErrorConfirmOrder(Throwable paramThrowable);
  
  public abstract void showItemToSendInfo();
  
  public abstract void showProgressBar();
  
  public abstract void showTermAndCondition();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/view/OrderFormView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */