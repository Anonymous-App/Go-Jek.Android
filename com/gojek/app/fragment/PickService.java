package com.gojek.app.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.Services;
import com.gojek.app.clean.CleanActivity;
import com.gojek.app.food.FoodHome;
import com.gojek.app.glam.GlamActivity;
import com.gojek.app.gobusway.activity.BusWayMainActivity;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.mart.MartHome;
import com.gojek.app.massage.MassageActivity;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.util.Util;
import com.gojek.gobox.cargoType.view.CargoTypeActivity;
import com.gojek.gotix.activities.GotixMainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;

@Instrumented
public class PickService
  extends Fragment
  implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, TraceFieldInterface
{
  private static final String TAG = PickService.class.getSimpleName();
  private Gson gson = new Gson();
  private ImageLoader imageLoader;
  private boolean isFoodFreeLoaded = false;
  private BookingStatus mBookingData;
  private int mFlag;
  protected GoogleApiClient mGoogleApiClient;
  private ImageView mIVBox;
  private ImageView mIVBusWay;
  private ImageView mIVClean;
  private ImageView mIVCourier;
  private ImageView mIVFood;
  private ImageView mIVFreeSticker;
  private ImageView mIVGlam;
  private ImageView mIVMart;
  private ImageView mIVMassage;
  private ImageView mIVTix;
  private ImageView mIVTransport;
  private Location mLastLocation;
  private String ursLoc;
  
  private void getServiceAreaAvailability(Location paramLocation) {}
  
  public static PickService newInstance()
  {
    return new PickService();
  }
  
  private void showGPSDisabledAlertToUser()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
    localBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?").setCancelable(false).setPositiveButton("Goto Settings Page To Enable GPS", new PickService.1(this));
    localBuilder.setNegativeButton("Cancel", new PickService.2(this));
    localBuilder.create().show();
  }
  
  protected void buildGoogleApiClient()
  {
    try
    {
      this.mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void onClick(View paramView)
  {
    Intent localIntent2 = new Intent(getActivity(), Services.class);
    this.mBookingData = new BookingStatus();
    Intent localIntent1 = localIntent2;
    switch (paramView.getId())
    {
    default: 
      localIntent1 = localIntent2;
    }
    for (;;)
    {
      localIntent1.putExtra("BOOKING_DATA", this.mBookingData);
      startActivity(localIntent1);
      return;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: Instant Courier Selected");
      this.mBookingData.serviceType = 2;
      localIntent1 = localIntent2;
      continue;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoRide Selected");
      this.mBookingData.serviceType = 1;
      localIntent1 = localIntent2;
      continue;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoFood Selected");
      this.mBookingData.serviceType = 5;
      localIntent1 = new Intent(getActivity(), FoodHome.class);
      continue;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoBox Selected");
      localIntent1 = new Intent(getActivity(), CargoTypeActivity.class);
      continue;
      this.mBookingData.serviceType = 6;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoMart Selected");
      localIntent1 = new Intent(getActivity(), MartHome.class);
      continue;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoMassage Selected");
      localIntent1 = new Intent(getActivity(), MassageActivity.class);
      continue;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoClean Selected");
      localIntent1 = new Intent(getActivity(), CleanActivity.class);
      continue;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoGlam Selected");
      localIntent1 = new Intent(getActivity(), GlamActivity.class);
      continue;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoBusWay Selected");
      localIntent1 = new Intent(getActivity(), BusWayMainActivity.class);
      continue;
      ((GojekActivityBase)getActivity()).mixPanel.track("PAS: GoTix Selected");
      localIntent1 = new Intent(getActivity(), GotixMainActivity.class);
    }
  }
  
  public void onConnected(Bundle paramBundle)
  {
    try
    {
      this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
      if ((this.mLastLocation != null) && (!this.isFoodFreeLoaded)) {
        getServiceAreaAvailability(this.mLastLocation);
      }
      return;
    }
    catch (Exception paramBundle) {}
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + paramConnectionResult.getErrorCode());
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    Log.i(TAG, "Connection suspended");
    this.mGoogleApiClient.connect();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("PickService");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "PickService#onCreate", null);
      super.onCreate(paramBundle);
      buildGoogleApiClient();
      this.mBookingData = ((BookingStatus)getActivity().getIntent().getParcelableExtra("BOOKING_DATA"));
      this.mFlag = getActivity().getIntent().getIntExtra("FLAG", 0);
      if (((this.mFlag == 23) || (this.mFlag == 24) || (this.mFlag == 25)) && (this.mBookingData.serviceType != 5))
      {
        paramBundle = new Intent(getActivity(), Services.class);
        paramBundle.putExtra("BOOKING_DATA", this.mBookingData);
        paramBundle.putExtra("FLAG", this.mFlag);
        startActivity(paramBundle);
        getActivity().getIntent().putExtra("BOOKING_DATA", null);
        getActivity().getIntent().putExtra("FLAG", 0);
      }
      this.imageLoader = ImageLoader.getInstance();
      paramBundle = new ImageLoaderConfiguration.Builder(getActivity()).defaultDisplayImageOptions(Util.getDisplayImageConfig()).build();
      this.imageLoader.init(paramBundle);
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "PickService#onCreate", null);
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "PickService#onCreateView", null);
      paramLayoutInflater = paramLayoutInflater.inflate(2130968811, null);
      TraceMachine.exitMethod();
      return paramLayoutInflater;
    }
    catch (NoSuchFieldError paramViewGroup)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "PickService#onCreateView", null);
      }
    }
  }
  
  public void onDestroy()
  {
    Log.i(TAG, "onDestroy");
    VolleyClient.getInstance(getActivity()).cancelAllRequest(TAG);
    super.onDestroy();
  }
  
  public void onStart()
  {
    ApplicationStateMonitor.getInstance().activityStarted();
    super.onStart();
    this.mGoogleApiClient.connect();
  }
  
  public void onStop()
  {
    ApplicationStateMonitor.getInstance().activityStopped();
    Log.i(TAG, "onStop");
    VolleyClient.getInstance(getActivity()).cancelAllRequest(TAG);
    super.onStop();
    if (this.mGoogleApiClient.isConnected()) {
      this.mGoogleApiClient.disconnect();
    }
  }
  
  public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.mIVCourier = ((ImageView)paramView.findViewById(2131624924));
    this.mIVTransport = ((ImageView)paramView.findViewById(2131624925));
    this.mIVFood = ((ImageView)paramView.findViewById(2131624927));
    this.mIVMart = ((ImageView)paramView.findViewById(2131624930));
    this.mIVFreeSticker = ((ImageView)paramView.findViewById(2131624928));
    this.mIVBox = ((ImageView)paramView.findViewById(2131624936));
    this.mIVMassage = ((ImageView)paramView.findViewById(2131624942));
    this.mIVClean = ((ImageView)paramView.findViewById(2131624938));
    this.mIVGlam = ((ImageView)paramView.findViewById(2131624940));
    this.mIVBusWay = ((ImageView)paramView.findViewById(2131624932));
    this.mIVTix = ((ImageView)paramView.findViewById(2131624934));
    this.mIVCourier.setOnClickListener(this);
    this.mIVTransport.setOnClickListener(this);
    this.mIVFood.setOnClickListener(this);
    this.mIVMart.setOnClickListener(this);
    this.mIVBox.setOnClickListener(this);
    this.mIVMassage.setOnClickListener(this);
    this.mIVClean.setOnClickListener(this);
    this.mIVGlam.setOnClickListener(this);
    this.mIVBusWay.setOnClickListener(this);
    this.mIVTix.setOnClickListener(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/fragment/PickService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */