package com.gojek.gotix.presenter;

import android.os.Bundle;
import com.gojek.gotix.Order;
import com.gojek.gotix.OrderSchedule;
import com.gojek.gotix.activities.GotixReceiptTicketActivity;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.GotixNetworkManager;
import com.gojek.gotix.repositories.OrderRepository;
import com.gojek.gotix.tools.GotixUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import lib.gojek.base.helper.BasePreferences;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GotixReceiptTicketPresenter
  extends GotixBasePresenter<GotixReceiptTicketActivity>
{
  private static final String TAG = GotixReceiptTicketPresenter.class.getSimpleName();
  private GotixNetworkManager mGotixNetworkManager;
  
  private void clearExistingActiveOrder(int paramInt)
  {
    if (GotixData.getActiveOrder(paramInt) != null) {
      GotixData.clearActiveOrder(paramInt);
    }
  }
  
  private void displayData(Order paramOrder)
  {
    ((GotixReceiptTicketActivity)getView()).hideProgressBar();
    ((GotixReceiptTicketActivity)getView()).setReceiptDetail(paramOrder);
    setLocationTakeMeThere(paramOrder);
    setTimeEventReceipt(paramOrder);
    setDateEventReceipt(paramOrder);
    setTicketEventReceipt(paramOrder);
    setLocationEventReceipt(paramOrder);
    setDateAndTimeForShare(paramOrder);
  }
  
  private Observable<Order> getLocalOrderObservable(int paramInt)
  {
    return Observable.just(OrderRepository.find(paramInt)).cache().map(GotixReceiptTicketPresenter..Lambda.2.lambdaFactory$(paramInt));
  }
  
  private Observable<Order> getNetworkOrderObservable(int paramInt1, int paramInt2)
  {
    return this.mGotixNetworkManager.getOrderPurchased(paramInt1, paramInt2).cache().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).onErrorResumeNext(GotixReceiptTicketPresenter..Lambda.3.lambdaFactory$()).map(GotixReceiptTicketPresenter..Lambda.4.lambdaFactory$(this));
  }
  
  private Observable<Integer> getTodayOrderScheduleLocationId(Order paramOrder)
  {
    return Observable.from(paramOrder.getSchedules()).first(GotixReceiptTicketPresenter..Lambda.28.lambdaFactory$()).onErrorResumeNext(GotixReceiptTicketPresenter..Lambda.29.lambdaFactory$()).map(GotixReceiptTicketPresenter..Lambda.30.lambdaFactory$());
  }
  
  private void saveOrder(Order paramOrder)
  {
    OrderRepository.save(paramOrder);
    clearExistingActiveOrder(paramOrder.getOrder_id().intValue());
  }
  
  private void setDateAndTimeForShare(Order paramOrder)
  {
    Observable.just(paramOrder.getSchedules()).subscribe(GotixReceiptTicketPresenter..Lambda.21.lambdaFactory$(this), GotixReceiptTicketPresenter..Lambda.22.lambdaFactory$());
  }
  
  private void setDateEventReceipt(Order paramOrder)
  {
    Observable.from(paramOrder.getSchedules()).reduce("", GotixReceiptTicketPresenter..Lambda.11.lambdaFactory$(this)).subscribe(GotixReceiptTicketPresenter..Lambda.12.lambdaFactory$(this), GotixReceiptTicketPresenter..Lambda.13.lambdaFactory$());
  }
  
  private void setDeliverNow(Order paramOrder, String paramString)
  {
    ((GotixReceiptTicketActivity)getView()).setDeliverNowButtonListener(paramOrder.getEvent_id().intValue(), paramOrder.getBooking_reference(), paramOrder.getName(), paramString);
  }
  
  private void setLocation(Order paramOrder, int paramInt)
  {
    Observable.from(paramOrder.getLocations()).first(GotixReceiptTicketPresenter..Lambda.25.lambdaFactory$(paramInt)).subscribe(GotixReceiptTicketPresenter..Lambda.26.lambdaFactory$(this), GotixReceiptTicketPresenter..Lambda.27.lambdaFactory$());
  }
  
  private void setLocationEventReceipt(Order paramOrder)
  {
    Observable.from(paramOrder.getLocations()).reduce("", GotixReceiptTicketPresenter..Lambda.18.lambdaFactory$(this)).subscribe(GotixReceiptTicketPresenter..Lambda.19.lambdaFactory$(this), GotixReceiptTicketPresenter..Lambda.20.lambdaFactory$());
  }
  
  private void setTicketEventReceipt(Order paramOrder)
  {
    Observable.from(paramOrder.getSchedules()).flatMap(GotixReceiptTicketPresenter..Lambda.14.lambdaFactory$()).reduce("", GotixReceiptTicketPresenter..Lambda.15.lambdaFactory$(this)).subscribe(GotixReceiptTicketPresenter..Lambda.16.lambdaFactory$(this, paramOrder), GotixReceiptTicketPresenter..Lambda.17.lambdaFactory$());
  }
  
  private void setTimeEventReceipt(Order paramOrder)
  {
    Observable.from(paramOrder.getSchedules()).reduce("", GotixReceiptTicketPresenter..Lambda.8.lambdaFactory$(this)).subscribe(GotixReceiptTicketPresenter..Lambda.9.lambdaFactory$(this), GotixReceiptTicketPresenter..Lambda.10.lambdaFactory$());
  }
  
  public void getPendingReviewByOrder(int paramInt)
  {
    this.mGotixNetworkManager.getPendingReviewByOrder(BasePreferences.getCustomerId(), paramInt).observeOn(AndroidSchedulers.mainThread()).filter(GotixReceiptTicketPresenter..Lambda.5.lambdaFactory$()).subscribe(GotixReceiptTicketPresenter..Lambda.6.lambdaFactory$(this), GotixReceiptTicketPresenter..Lambda.7.lambdaFactory$(this));
  }
  
  public boolean isTimeScheduleToday(List<OrderSchedule> paramList)
  {
    boolean bool = false;
    if (!paramList.isEmpty())
    {
      ArrayList localArrayList1 = new ArrayList(paramList.size());
      ArrayList localArrayList2 = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        OrderSchedule localOrderSchedule = (OrderSchedule)paramList.next();
        localArrayList1.add(localOrderSchedule.getStart_time_at());
        localArrayList2.add(localOrderSchedule.getFinish_time_at());
      }
      Collections.sort(localArrayList1);
      Collections.sort(localArrayList2);
      bool = GotixUtils.getEnabledBtnTakeMeThere(((Long)localArrayList1.get(0)).longValue(), ((Long)localArrayList2.get(localArrayList2.size() - 1)).longValue());
    }
    return bool;
  }
  
  public void loadData(int paramInt1, int paramInt2)
  {
    Observable localObservable = getLocalOrderObservable(paramInt2);
    getNetworkOrderObservable(paramInt1, paramInt2).startWith(localObservable).compose(deliverLatestCache()).subscribe(GotixReceiptTicketPresenter..Lambda.1.lambdaFactory$(this));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mGotixNetworkManager = new GotixNetworkManager(getContext(), this);
  }
  
  public void onFailedToProcessRequest(Response paramResponse) {}
  
  public void onNetworkProblem()
  {
    if (isViewExists()) {}
  }
  
  public void onNoInternetConnection()
  {
    if (isViewExists()) {
      ((GotixReceiptTicketActivity)getView()).onNoInternetConnection();
    }
  }
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
  
  public void setLocationTakeMeThere(Order paramOrder)
  {
    getTodayOrderScheduleLocationId(paramOrder).subscribe(GotixReceiptTicketPresenter..Lambda.23.lambdaFactory$(this, paramOrder), GotixReceiptTicketPresenter..Lambda.24.lambdaFactory$());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixReceiptTicketPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */