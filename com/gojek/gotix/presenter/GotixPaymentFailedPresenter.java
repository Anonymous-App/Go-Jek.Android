package com.gojek.gotix.presenter;

import android.os.Bundle;
import com.gojek.gotix.activities.GotixPaymentFailedActivity;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.GotixNetworkManager;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class GotixPaymentFailedPresenter
  extends GotixBasePresenter<GotixPaymentFailedActivity>
{
  private static final String PHONE_NUMBER = "phoneNumber";
  private static final String PHONE_TEXT = "phoneText";
  private GotixNetworkManager gotixNetworkManager;
  
  private void clearExistingActiveOrder(int paramInt)
  {
    if (GotixData.getActiveOrder(paramInt) != null) {
      GotixData.clearActiveOrder(paramInt);
    }
  }
  
  private void getDataCallCenter()
  {
    this.gotixNetworkManager.getCallCenter().observeOn(AndroidSchedulers.mainThread()).subscribe(GotixPaymentFailedPresenter..Lambda.1.lambdaFactory$(this), GotixPaymentFailedPresenter..Lambda.2.lambdaFactory$());
  }
  
  public void ackPurchaseFailed(int paramInt)
  {
    this.gotixNetworkManager.ackPurchaseFailed(paramInt).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixPaymentFailedPresenter..Lambda.3.lambdaFactory$(this, paramInt), GotixPaymentFailedPresenter..Lambda.4.lambdaFactory$());
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
    ((GotixPaymentFailedActivity)getView()).showDialogNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    ((GotixPaymentFailedActivity)getView()).showDialogWhenNoInternetConnection();
  }
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixPaymentFailedPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */