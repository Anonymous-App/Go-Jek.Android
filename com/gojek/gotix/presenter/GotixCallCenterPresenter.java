package com.gojek.gotix.presenter;

import com.gojek.gotix.activities.GotixCallCenterActivity;
import com.gojek.gotix.helper.DataHelper;
import com.gojek.gotix.network.GotixNetworkManager;
import com.gojek.gotix.network.model.CallCenter;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class GotixCallCenterPresenter
  extends GotixBasePresenter<GotixCallCenterActivity>
{
  private static final String PHONE_NUMBER = "phoneNumber";
  private static final String PHONE_TEXT = "phoneText";
  private static String TAG = GotixCallCenterPresenter.class.getSimpleName();
  private GotixNetworkManager mGoTixNetworkManager;
  
  private void getDataCallCenter()
  {
    this.mGoTixNetworkManager.getCallCenter().observeOn(AndroidSchedulers.mainThread()).subscribe(GotixCallCenterPresenter..Lambda.1.lambdaFactory$(this), GotixCallCenterPresenter..Lambda.2.lambdaFactory$());
  }
  
  private boolean isDataHelperEmpty()
  {
    return (DataHelper.getString("phoneText") == null) && (DataHelper.getString("phoneNumber") == null);
  }
  
  private boolean isDataNetworkAvailable(CallCenter paramCallCenter)
  {
    return (!DataHelper.getString("phoneText").equals(paramCallCenter.getCallCenterText())) && (!DataHelper.getString("phoneNumber").equals(paramCallCenter.getCallCenterNumber()));
  }
  
  public void onFailedToProcessRequest(Response paramResponse) {}
  
  public void onNetworkProblem() {}
  
  public void onNoInternetConnection() {}
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
  
  protected void onTakeView(GotixCallCenterActivity paramGotixCallCenterActivity)
  {
    super.onTakeView(paramGotixCallCenterActivity);
    this.mGoTixNetworkManager = new GotixNetworkManager(getContext(), this);
    getDataCallCenter();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixCallCenterPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */