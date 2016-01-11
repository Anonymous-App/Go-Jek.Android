package com.gojek.gobox.cargoType.view;

import com.gojek.gobox.model.CargoTypeResponse;

public abstract interface CargoTypeView
{
  public abstract void hideProgressBar();
  
  public abstract void initRecyclerView();
  
  public abstract void populateCargoTypeList(CargoTypeResponse paramCargoTypeResponse);
  
  public abstract void showErrorLoadingCargoType(Throwable paramThrowable);
  
  public abstract void showProgressBar();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/view/CargoTypeView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */