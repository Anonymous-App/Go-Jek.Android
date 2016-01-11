package com.gojek.gotix.presenter;

import android.os.Bundle;
import com.gojek.gotix.activities.GotixWaitingDriverActivity;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.GotixNetworkManager;
import com.gojek.gotix.network.model.DeliveryBooking;
import com.gojek.gotix.tools.ObservableIntervalHelper;
import lib.gojek.base.networks.GeneralNetworkHandler;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class GotixWaitingDriverPresenter
  extends GotixBasePresenter<GotixWaitingDriverActivity>
{
  private static final String TAG = GotixWaitingDriverPresenter.class.getSimpleName();
  private GotixNetworkManager gotixNetworkManager;
  private ObservableIntervalHelper observableIntervalHelper;
  
  public void checkStatusBooking(int paramInt)
  {
    this.gotixNetworkManager.findDriver(paramInt, new GeneralNetworkHandler()
    {
      public void onFailedToProcessRequest(Response paramAnonymousResponse)
      {
        if (GotixWaitingDriverPresenter.this.isViewExists()) {
          ((GotixWaitingDriverActivity)GotixWaitingDriverPresenter.this.getView()).onNetworkProblem();
        }
      }
      
      public void onNetworkProblem()
      {
        if (GotixWaitingDriverPresenter.this.isViewExists()) {
          ((GotixWaitingDriverActivity)GotixWaitingDriverPresenter.this.getView()).onNetworkProblem();
        }
      }
      
      public void onNoInternetConnection() {}
      
      public void onRequestEnd() {}
      
      public void onRequesting() {}
    }).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixWaitingDriverPresenter..Lambda.3.lambdaFactory$(this, paramInt), GotixWaitingDriverPresenter..Lambda.4.lambdaFactory$(this));
  }
  
  public void doCancelbooking(int paramInt)
  {
    this.gotixNetworkManager.cancelBooking(paramInt).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixWaitingDriverPresenter..Lambda.1.lambdaFactory$(this), GotixWaitingDriverPresenter..Lambda.2.lambdaFactory$(this));
  }
  
  public void findingDriver(final int paramInt)
  {
    this.observableIntervalHelper = new ObservableIntervalHelper()
    {
      public void doOnTimerStart()
      {
        GotixWaitingDriverPresenter.this.checkStatusBooking(paramInt);
      }
    };
    this.observableIntervalHelper.startTimerWithoutDelay();
  }
  
  public DeliveryBooking getDeliveryData(int paramInt)
  {
    return GotixData.getDeliveryData(paramInt);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.gotixNetworkManager = new GotixNetworkManager(getContext(), this);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.observableIntervalHelper.unsubscribe();
  }
  
  public void onFailedToProcessRequest(Response paramResponse)
  {
    if (paramResponse.getStatus() != 200) {
      ((GotixWaitingDriverActivity)getView()).failedCancelBookingInformation();
    }
  }
  
  public void onNetworkProblem()
  {
    ((GotixWaitingDriverActivity)getView()).onNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    ((GotixWaitingDriverActivity)getView()).onNoInternetConnection();
  }
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
  
  public void retryPostDeliveryData(int paramInt)
  {
    this.gotixNetworkManager.createBooking(paramInt, getDeliveryData(paramInt)).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixWaitingDriverPresenter..Lambda.5.lambdaFactory$(this), GotixWaitingDriverPresenter..Lambda.6.lambdaFactory$(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixWaitingDriverPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */