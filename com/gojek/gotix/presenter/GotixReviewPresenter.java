package com.gojek.gotix.presenter;

import android.os.Bundle;
import com.gojek.gotix.activities.GotixReviewActivity;
import com.gojek.gotix.network.GotixNetworkManager;
import com.gojek.gotix.network.model.Review;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class GotixReviewPresenter
  extends GotixBasePresenter<GotixReviewActivity>
{
  private static final String TAG = GotixReviewPresenter.class.getSimpleName();
  private GotixNetworkManager gotixNetworkManager;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.gotixNetworkManager = new GotixNetworkManager(getContext(), this);
  }
  
  public void onFailedToProcessRequest(Response paramResponse) {}
  
  public void onNetworkProblem()
  {
    if (isViewExists()) {
      ((GotixReviewActivity)getView()).onNetworkProblem();
    }
  }
  
  public void onNoInternetConnection() {}
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
  
  public void submitReview(int paramInt, Review paramReview)
  {
    this.gotixNetworkManager.submitReview(paramInt, paramReview).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixReviewPresenter..Lambda.1.lambdaFactory$(this), GotixReviewPresenter..Lambda.2.lambdaFactory$(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixReviewPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */