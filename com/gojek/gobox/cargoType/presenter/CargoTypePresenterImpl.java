package com.gojek.gobox.cargoType.presenter;

import com.gojek.gobox.cargoType.interactor.CargoTypeInteractor;
import com.gojek.gobox.cargoType.view.CargoTypeView;

public class CargoTypePresenterImpl
  implements CargoTypePresenter
{
  private CargoTypeInteractor mCargoTypeInteractor;
  private CargoTypeView mCargoTypeView;
  
  public CargoTypePresenterImpl(CargoTypeView paramCargoTypeView, CargoTypeInteractor paramCargoTypeInteractor)
  {
    this.mCargoTypeInteractor = paramCargoTypeInteractor;
    this.mCargoTypeView = paramCargoTypeView;
  }
  
  public void onViewCreate()
  {
    this.mCargoTypeView.initRecyclerView();
    this.mCargoTypeView.showProgressBar();
    this.mCargoTypeInteractor.fetchAllCargoType(new CargoTypePresenterImpl.1(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/presenter/CargoTypePresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */