package com.gojek.gotix.presenter;

import android.os.Bundle;
import com.gojek.gotix.activities.GotixSearchEventActivity;
import com.gojek.gotix.network.GotixNetworkManager;
import retrofit.client.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class GotixSearchEventPresenter
  extends GotixBasePresenter<GotixSearchEventActivity>
{
  public static final int SEARCH_EVENT_LIMIT = 20;
  private GotixNetworkManager gotixNetworkManager;
  private int nextSearchPageIdx;
  private Subscription searchEventSubscription;
  
  private void performSearchCall(String paramString)
  {
    this.searchEventSubscription = this.gotixNetworkManager.searchEvent(paramString, this.nextSearchPageIdx, 20).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixSearchEventPresenter..Lambda.1.lambdaFactory$(this), GotixSearchEventPresenter..Lambda.2.lambdaFactory$(this));
  }
  
  private void stopPreviousCall(Subscription paramSubscription)
  {
    if ((paramSubscription != null) && (!paramSubscription.isUnsubscribed())) {
      paramSubscription.unsubscribe();
    }
  }
  
  public void attemptSearchEvent(String paramString)
  {
    this.nextSearchPageIdx = 1;
    stopPreviousCall(this.searchEventSubscription);
    performSearchCall(paramString);
  }
  
  public void loadMoreSearchEvent(String paramString)
  {
    this.nextSearchPageIdx += 1;
    performSearchCall(paramString);
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixSearchEventPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */