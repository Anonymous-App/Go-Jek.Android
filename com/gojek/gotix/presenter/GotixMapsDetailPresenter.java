package com.gojek.gotix.presenter;

import android.os.Bundle;
import com.gojek.gotix.activities.GotixMapsDetailActivity;
import com.gojek.gotix.network.GotixNetworkManager;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class GotixMapsDetailPresenter
  extends GotixBasePresenter<GotixMapsDetailActivity>
{
  private GotixNetworkManager gotixNetworkManager;
  
  public void findBooking(int paramInt)
  {
    this.gotixNetworkManager.findDriver(paramInt).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixMapsDetailPresenter..Lambda.1.lambdaFactory$(this, paramInt), GotixMapsDetailPresenter..Lambda.2.lambdaFactory$());
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.gotixNetworkManager = new GotixNetworkManager(getContext(), this);
  }
  
  public void onFailedToProcessRequest(Response paramResponse) {}
  
  public void onNetworkProblem() {}
  
  public void onNoInternetConnection() {}
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixMapsDetailPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */