package com.gojek.gotix.presenter;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import com.gojek.gotix.Event;
import com.gojek.gotix.R.string;
import com.gojek.gotix.fragments.GotixCheckoutFragment;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.GotixNetworkManager;
import com.gojek.gotix.network.model.PurchasedOrderData;
import com.gojek.gotix.network.model.ReleaseOrder;
import com.gojek.gotix.network.model.Transaction;
import com.gojek.gotix.network.model.TransactionTicket;
import com.gojek.gotix.tools.GotixUtils;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class GotixCheckoutPresenter
  extends GotixBasePresenter<GotixCheckoutFragment>
{
  private static final String CANCELED = "canceled";
  private static final int LENGTH_BR = 6;
  private static final long RESET_DURATION = 0L;
  private static final String TAG = GotixCheckoutPresenter.class.getSimpleName();
  private static final int TICK_DURATION = 1;
  private static final String TIMEOUT = "timeout";
  private CountDownTimer countDownTimer;
  private boolean deliverable;
  private String eventName;
  private GotixNetworkManager gotixNetworkManager;
  private int orderId;
  private BigDecimal totalPrice = BigDecimal.ZERO;
  private Observable<Transaction> transactionObservable;
  
  private String formatStringOfItemPurchase(String paramString, int paramInt, long paramLong)
  {
    return String.format(getContext().getString(R.string.format_item_purchase), new Object[] { paramString, Integer.valueOf(paramInt), GotixUtils.getRupiahFormat(String.valueOf(paramLong)) });
  }
  
  private boolean isDeliveryReady(int paramInt)
  {
    return paramInt == 500;
  }
  
  private boolean isPaymentFailed(int paramInt)
  {
    return (paramInt == 101) || (paramInt == 102) || (paramInt == 201) || (paramInt == 202);
  }
  
  private boolean isPaymentInProgress(int paramInt)
  {
    return (paramInt == 100) || (paramInt == 200) || (paramInt == 300);
  }
  
  private boolean isPaymentPurchased(int paramInt)
  {
    return paramInt == 400;
  }
  
  private void setTotalPriceTransaction(GotixCheckoutFragment paramGotixCheckoutFragment, Transaction paramTransaction, int paramInt)
  {
    this.totalPrice = new BigDecimal(paramTransaction.getTotal_price());
    if (!isFreeEvent())
    {
      paramGotixCheckoutFragment.setTotalPrice(paramTransaction.getTotal_price(), paramTransaction.getTotal_price_credit_card());
      paramGotixCheckoutFragment.initPaymentWidget(paramInt);
      return;
    }
    paramGotixCheckoutFragment.setTotalPrice(paramTransaction.getTotal_price(), paramTransaction.getTotal_price());
    paramGotixCheckoutFragment.hideComponentForFreeEvent();
  }
  
  private void startCountDownTimerOfCheckOut(int paramInt, long paramLong, GotixCheckoutFragment paramGotixCheckoutFragment)
  {
    if (this.countDownTimer == null)
    {
      this.countDownTimer = new CountDownTimer(TimeUnit.SECONDS.toMillis(paramLong), TimeUnit.SECONDS.toMillis(1L))
      {
        public void onFinish()
        {
          GotixCheckoutPresenter.this.doWhenTimerFinished(this.val$gotixCheckoutFragment);
          this.val$gotixCheckoutFragment.showNotificationBasedOnFragmentStatus();
        }
        
        public void onTick(long paramAnonymousLong)
        {
          Long localLong = Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(paramAnonymousLong));
          paramAnonymousLong = TimeUnit.MILLISECONDS.toSeconds(paramAnonymousLong);
          long l = TimeUnit.MINUTES.toSeconds(localLong.longValue());
          this.val$gotixCheckoutFragment.bindDataTimerToView(localLong.longValue(), Long.valueOf(paramAnonymousLong - l).longValue());
        }
      };
      this.countDownTimer.start();
    }
  }
  
  public void attemptToConfirmPurchase(PurchasedOrderData paramPurchasedOrderData)
  {
    this.gotixNetworkManager.createPurchaseOrder(this.orderId, paramPurchasedOrderData).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixCheckoutPresenter..Lambda.1.lambdaFactory$(this), GotixCheckoutPresenter..Lambda.2.lambdaFactory$());
  }
  
  public void doWhenTimerFinished(GotixCheckoutFragment paramGotixCheckoutFragment)
  {
    releasePaidOrderWhenTimeout();
    paramGotixCheckoutFragment.bindDataTimerToView(0L, 0L);
    releaseTranscationData();
  }
  
  public void fixDateRange(int paramInt1, int paramInt2, int paramInt3)
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = localCalendar.get(5);
    ((GotixCheckoutFragment)getView()).fixYear = paramInt1;
    ((GotixCheckoutFragment)getView()).fixMonthOfYear = paramInt2;
    ((GotixCheckoutFragment)getView()).fixDayOfMonth = paramInt3;
    if (paramInt1 > i) {
      ((GotixCheckoutFragment)getView()).fixYear = i;
    }
    for (;;)
    {
      ((GotixCheckoutFragment)getView()).setDateOfBirth();
      return;
      if ((paramInt2 > j) && (paramInt1 == i)) {
        ((GotixCheckoutFragment)getView()).fixMonthOfYear = j;
      } else if ((paramInt3 > k) && (paramInt1 == i) && (paramInt2 == j)) {
        ((GotixCheckoutFragment)getView()).fixDayOfMonth = k;
      }
    }
  }
  
  public boolean isFreeEvent()
  {
    return this.totalPrice.compareTo(BigDecimal.ZERO) == 0;
  }
  
  public void loadDataTransactionFromInstance(int paramInt)
  {
    if ("production".equals("apiary")) {
      paramInt = 1;
    }
    this.transactionObservable = Observable.just(GotixData.getTransactionData(paramInt));
    this.eventName = GotixData.getEventData(paramInt).getName();
    this.deliverable = GotixData.getEventData(paramInt).getType_is_delivered().booleanValue();
    Log.i(TAG, "loadDataTransaction: " + this.deliverable);
  }
  
  public void loadMainDataToView()
  {
    this.transactionObservable.compose(deliverReplay()).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixCheckoutPresenter..Lambda.3.lambdaFactory$(this), GotixCheckoutPresenter..Lambda.4.lambdaFactory$());
  }
  
  public void loadSummaryOfPurchaseToDynamicView()
  {
    final StringBuilder localStringBuilder = new StringBuilder();
    this.transactionObservable.flatMap(GotixCheckoutPresenter..Lambda.5.lambdaFactory$()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber()
    {
      public void onCompleted()
      {
        if (GotixCheckoutPresenter.this.isViewExists())
        {
          localStringBuilder.setLength(localStringBuilder.length() - 6);
          ((GotixCheckoutFragment)GotixCheckoutPresenter.this.getView()).addSummaryTicketByPrice(localStringBuilder);
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramAnonymousThrowable.printStackTrace();
      }
      
      public void onNext(TransactionTicket paramAnonymousTransactionTicket)
      {
        localStringBuilder.append(GotixCheckoutPresenter.this.formatStringOfItemPurchase(paramAnonymousTransactionTicket.getName(), paramAnonymousTransactionTicket.getQuantity(), paramAnonymousTransactionTicket.getPrice()));
      }
    });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.gotixNetworkManager = new GotixNetworkManager(getContext(), this);
  }
  
  public void onFailedToProcessRequest(Response paramResponse) {}
  
  public void onNetworkProblem()
  {
    ((GotixCheckoutFragment)getView()).showDialogNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    ((GotixCheckoutFragment)getView()).showDialogWhenNoInternetConnection();
  }
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
  
  public void releasePaidOrderWhenCanceled()
  {
    releaseUnpaidOrder("canceled");
  }
  
  public void releasePaidOrderWhenTimeout()
  {
    releaseUnpaidOrder("timeout");
  }
  
  public void releaseTranscationData()
  {
    this.transactionObservable.map(GotixCheckoutPresenter..Lambda.6.lambdaFactory$()).subscribe(GotixCheckoutPresenter..Lambda.7.lambdaFactory$());
  }
  
  public void releaseUnpaidOrder(String paramString)
  {
    stopCountDownTimerOfCheckout();
    this.gotixNetworkManager.putUnpaidOrder(this.orderId, new ReleaseOrder(paramString)).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixCheckoutPresenter..Lambda.8.lambdaFactory$(), GotixCheckoutPresenter..Lambda.9.lambdaFactory$(this));
  }
  
  public void stopCountDownTimerOfCheckout()
  {
    if (this.countDownTimer != null) {
      this.countDownTimer.cancel();
    }
  }
  
  public void updatePurchaseButtonState(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    if (!this.deliverable)
    {
      if ((paramBoolean1) && (paramBoolean3) && (paramBoolean4)) {}
      for (paramBoolean1 = bool1;; paramBoolean1 = false)
      {
        if (isViewExists()) {
          ((GotixCheckoutFragment)getView()).setButtonPurchaseEnabled(paramBoolean1);
        }
        return;
      }
    }
    if ((paramBoolean1) && (paramBoolean2) && (paramBoolean3) && (paramBoolean4)) {}
    for (paramBoolean1 = bool2;; paramBoolean1 = false) {
      break;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixCheckoutPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */