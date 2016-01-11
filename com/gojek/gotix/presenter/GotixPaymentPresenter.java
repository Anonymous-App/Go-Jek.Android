package com.gojek.gotix.presenter;

import android.os.Bundle;
import com.gojek.gotix.activities.GotixWaitingPaymentActivity;
import com.gojek.gotix.network.GotixNetworkManager;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class GotixPaymentPresenter
  extends GotixBasePresenter<GotixWaitingPaymentActivity>
{
  private static final String PHONE_NUMBER = "phoneNumber";
  private static final String PHONE_TEXT = "phoneText";
  private GotixNetworkManager gotixNetworkManager;
  
  private void getDataCallCenter()
  {
    this.gotixNetworkManager.getCallCenter().observeOn(AndroidSchedulers.mainThread()).subscribe(GotixPaymentPresenter..Lambda.3.lambdaFactory$(this), GotixPaymentPresenter..Lambda.4.lambdaFactory$());
  }
  
  private boolean isDeliveryReady(int paramInt)
  {
    return paramInt == 500;
  }
  
  private boolean isPaymentFailed(int paramInt)
  {
    return (paramInt == 101) || (paramInt == 102) || (paramInt == 201) || (paramInt == 202);
  }
  
  private boolean isPaymentPurchased(int paramInt)
  {
    return paramInt == 400;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.gotixNetworkManager = new GotixNetworkManager(getContext(), this);
    getDataCallCenter();
  }
  
  public void onFailedToProcessRequest(Response paramResponse) {}
  
  public void onNetworkProblem()
  {
    ((GotixWaitingPaymentActivity)getView()).showDialogNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    ((GotixWaitingPaymentActivity)getView()).showDialogWhenNoInternetConnection();
  }
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
  
  public void refreshPurchaseCall(int paramInt)
  {
    this.gotixNetworkManager.getPurchaseStatus(paramInt).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixPaymentPresenter..Lambda.1.lambdaFactory$(this, paramInt), GotixPaymentPresenter..Lambda.2.lambdaFactory$());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixPaymentPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */