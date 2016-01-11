package com.gojek.gotix.presenter;

import android.os.Bundle;
import com.gojek.gotix.Order;
import com.gojek.gotix.fragments.GotixMyTicketsFragment;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.GotixNetworkManager;
import com.gojek.gotix.network.model.RegistrationGCM;
import com.gojek.gotix.repositories.OrderRepository;
import java.util.List;
import lib.gojek.base.util.GCMHelper;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GotixOrderPresenter
  extends GotixBasePresenter<GotixMyTicketsFragment>
{
  public static final int EVENT_LIMIT = 5;
  private static final String TAG = GotixOrderPresenter.class.getSimpleName();
  private GCMHelper gcmHelper;
  private GotixNetworkManager gotixNetworkManager;
  private int nextOrderPageIdx = 1;
  private RegistrationGCM registrationGCM;
  private String registrationId;
  
  public void attemptMyEventList(int paramInt)
  {
    getListAllPurchasedOrder(paramInt, 1, this.nextOrderPageIdx * 5);
  }
  
  public void getListAllPurchasedOrder(int paramInt1, int paramInt2, int paramInt3)
  {
    getListAllPurchasedOrderFromNetwork(paramInt1, paramInt2, paramInt3).observeOn(AndroidSchedulers.mainThread()).compose(deliverLatestCache()).subscribe(GotixOrderPresenter..Lambda.1.lambdaFactory$(), GotixOrderPresenter..Lambda.2.lambdaFactory$());
  }
  
  public Observable<List<Order>> getListAllPurchasedOrderFromNetwork(int paramInt1, int paramInt2, int paramInt3)
  {
    return this.gotixNetworkManager.getListAllPurchasedOrder(paramInt1, paramInt2, paramInt3).flatMap(GotixOrderPresenter..Lambda.3.lambdaFactory$()).doOnNext(GotixOrderPresenter..Lambda.4.lambdaFactory$()).toList();
  }
  
  public void loadMoreMyEventList(int paramInt)
  {
    this.nextOrderPageIdx += 1;
    getListAllPurchasedOrder(paramInt, this.nextOrderPageIdx, 5);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.gotixNetworkManager = new GotixNetworkManager(getContext(), this);
    this.gcmHelper = new GCMHelper(getContext(), "56154713749");
  }
  
  public void onFailedToProcessRequest(Response paramResponse) {}
  
  public void onNetworkProblem()
  {
    Observable.just(OrderRepository.loadData()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixOrderPresenter..Lambda.7.lambdaFactory$(this), GotixOrderPresenter..Lambda.8.lambdaFactory$(this));
  }
  
  public void onNoInternetConnection()
  {
    Observable.just(OrderRepository.loadData()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixOrderPresenter..Lambda.5.lambdaFactory$(this), GotixOrderPresenter..Lambda.6.lambdaFactory$(this));
  }
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
  
  public void registerToken(int paramInt)
  {
    if (!GotixData.isGCMRegistrationExist()) {
      this.gcmHelper.registerToken(new GotixOrderPresenter.1(this, paramInt));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixOrderPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */