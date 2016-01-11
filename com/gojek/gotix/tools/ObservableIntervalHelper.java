package com.gojek.gotix.tools;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class ObservableIntervalHelper
{
  private static final int DEFAULT_DELAY_TIMER = 30;
  private CompositeSubscription compositeSubscription;
  private int delayInSecond;
  
  public ObservableIntervalHelper()
  {
    this(30);
  }
  
  public ObservableIntervalHelper(int paramInt)
  {
    if (this.compositeSubscription == null) {
      this.compositeSubscription = new CompositeSubscription();
    }
    this.delayInSecond = paramInt;
  }
  
  public abstract void doOnTimerStart();
  
  public void startTimer()
  {
    this.compositeSubscription.add(Observable.interval(this.delayInSecond, this.delayInSecond, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(ObservableIntervalHelper..Lambda.1.lambdaFactory$(this)));
  }
  
  public void startTimerWithoutDelay()
  {
    this.compositeSubscription.add(Observable.interval(0L, this.delayInSecond, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(ObservableIntervalHelper..Lambda.2.lambdaFactory$(this)));
  }
  
  public void unsubscribe()
  {
    if (this.compositeSubscription.hasSubscriptions()) {
      this.compositeSubscription.unsubscribe();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/tools/ObservableIntervalHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */