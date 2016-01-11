package com.gojek.gobox.cargoType.presenter;

import com.gojek.gobox.cargoType.view.CargoTypeView;
import com.gojek.gobox.model.CargoTypeResponse;
import rx.Subscriber;

class CargoTypePresenterImpl$1
  extends Subscriber<CargoTypeResponse>
{
  CargoTypePresenterImpl$1(CargoTypePresenterImpl paramCargoTypePresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    CargoTypePresenterImpl.access$000(this.this$0).showErrorLoadingCargoType(paramThrowable);
  }
  
  public void onNext(CargoTypeResponse paramCargoTypeResponse)
  {
    CargoTypePresenterImpl.access$000(this.this$0).hideProgressBar();
    CargoTypePresenterImpl.access$000(this.this$0).populateCargoTypeList(paramCargoTypeResponse);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/presenter/CargoTypePresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */