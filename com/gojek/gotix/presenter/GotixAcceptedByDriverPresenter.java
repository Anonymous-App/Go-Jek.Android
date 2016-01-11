package com.gojek.gotix.presenter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.gojek.gotix.R.string;
import com.gojek.gotix.activities.GotixAcceptedByDriverActivity;
import com.gojek.gotix.network.GotixNetworkManager;
import com.gojek.gotix.network.model.Booking;
import com.gojek.gotix.network.model.Driver;
import com.gojek.gotix.network.model.MapPoint;
import com.gojek.gotix.tools.ObservableIntervalHelper;
import lib.gojek.base.networks.GeneralNetworkHandler;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class GotixAcceptedByDriverPresenter
  extends GotixBasePresenter<GotixAcceptedByDriverActivity>
{
  private static final int SECOND_PER_MINUTES = 60;
  private static final String TAG = GotixAcceptedByDriverPresenter.class.getSimpleName();
  private GotixNetworkManager gotixNetworkManager;
  private ObservableIntervalHelper observableIntervalHelper;
  
  private void bindData(Booking paramBooking)
  {
    ((GotixAcceptedByDriverActivity)getView()).setArrivedTimeText(getEtaAsSecond(paramBooking.getDriver().getEta()));
    GotixAcceptedByDriverActivity localGotixAcceptedByDriverActivity = (GotixAcceptedByDriverActivity)getView();
    if (paramBooking.getBooking_status().intValue() == 2) {}
    for (String str = paramBooking.getOrigin().getName();; str = paramBooking.getDestination().getName())
    {
      localGotixAcceptedByDriverActivity.setDestinationText(str);
      ((GotixAcceptedByDriverActivity)getView()).setBookingLocationOrigin(paramBooking.getOrigin().getName());
      ((GotixAcceptedByDriverActivity)getView()).setBookingLocationOriginAddress(paramBooking.getOrigin().getAddress());
      ((GotixAcceptedByDriverActivity)getView()).setBookingLocationDestination(paramBooking.getDestination().getName());
      ((GotixAcceptedByDriverActivity)getView()).setBookingLocationDestinationAddress(paramBooking.getDestination().getAddress());
      ((GotixAcceptedByDriverActivity)getView()).setBookingDateTime(paramBooking.getBooking_created_timestamp().intValue());
      ((GotixAcceptedByDriverActivity)getView()).setDriverName(paramBooking.getDriver().getName());
      ((GotixAcceptedByDriverActivity)getView()).setDriverAvatar(paramBooking.getDriver().getPhoto());
      ((GotixAcceptedByDriverActivity)getView()).callDriver(paramBooking.getDriver().getPhone());
      ((GotixAcceptedByDriverActivity)getView()).sendMessageDriver(paramBooking.getDriver().getPhone());
      ((GotixAcceptedByDriverActivity)getView()).setItemTicketInformation(paramBooking.getItem());
      drawMap(paramBooking);
      return;
    }
  }
  
  private void drawMap(Booking paramBooking)
  {
    String str1 = String.format(getContext().getString(R.string.accepted_by_driver_format_latlong), new Object[] { paramBooking.getOrigin().getLatitude(), paramBooking.getOrigin().getLongitude() });
    String str2 = String.format(getContext().getString(R.string.accepted_by_driver_format_latlong), new Object[] { paramBooking.getDestination().getLatitude(), paramBooking.getDestination().getLongitude() });
    ((GotixAcceptedByDriverActivity)getView()).drawRoutOnMap(paramBooking.getBooking_status().intValue(), paramBooking.getRoutePolyline(), str1, str2, paramBooking.getDriver().getLatitude().doubleValue(), paramBooking.getDriver().getLongitude().doubleValue());
  }
  
  private String getEtaAsSecond(String paramString)
  {
    int i = 0;
    if (!TextUtils.isEmpty(paramString)) {
      i = Integer.parseInt(paramString);
    }
    return String.valueOf((int)Math.ceil(i / 60.0D));
  }
  
  public void checkStatusBooking(int paramInt)
  {
    this.gotixNetworkManager.findDriver(paramInt, new GeneralNetworkHandler()
    {
      public void onFailedToProcessRequest(Response paramAnonymousResponse) {}
      
      public void onNetworkProblem()
      {
        if (GotixAcceptedByDriverPresenter.this.isViewExists()) {
          ((GotixAcceptedByDriverActivity)GotixAcceptedByDriverPresenter.this.getView()).onNetworkProblem();
        }
      }
      
      public void onNoInternetConnection() {}
      
      public void onRequestEnd() {}
      
      public void onRequesting() {}
    }).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixAcceptedByDriverPresenter..Lambda.3.lambdaFactory$(this, paramInt), GotixAcceptedByDriverPresenter..Lambda.4.lambdaFactory$(this));
  }
  
  public void doCancelbooking(int paramInt)
  {
    this.gotixNetworkManager.cancelBooking(paramInt).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixAcceptedByDriverPresenter..Lambda.1.lambdaFactory$(this), GotixAcceptedByDriverPresenter..Lambda.2.lambdaFactory$());
  }
  
  public void findingDriver(final int paramInt)
  {
    this.observableIntervalHelper = new ObservableIntervalHelper()
    {
      public void doOnTimerStart()
      {
        GotixAcceptedByDriverPresenter.this.checkStatusBooking(paramInt);
      }
    };
    this.observableIntervalHelper.startTimerWithoutDelay();
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
      ((GotixAcceptedByDriverActivity)getView()).failedCancelBookingInformation();
    }
  }
  
  public void onNetworkProblem()
  {
    ((GotixAcceptedByDriverActivity)getView()).onNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    ((GotixAcceptedByDriverActivity)getView()).onNoInternetConnection();
  }
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixAcceptedByDriverPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */