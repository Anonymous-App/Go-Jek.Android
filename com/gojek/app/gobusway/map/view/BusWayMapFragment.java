package com.gojek.app.gobusway.map.view;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.gojek.app.gobusway.R.drawable;
import com.gojek.app.gobusway.R.id;
import com.gojek.app.gobusway.R.layout;
import com.gojek.app.gobusway.R.string;
import com.gojek.app.gobusway.activity.BusWayMainActivity;
import com.gojek.app.gobusway.activity.FragmentController;
import com.gojek.app.gobusway.adapter.InfoHalteAdapter;
import com.gojek.app.gobusway.map.colaborator.OnMyMarkerClickListener;
import com.gojek.app.gobusway.map.presenter.BusWayMapPresenter;
import com.gojek.app.gobusway.model.BusWay;
import com.gojek.app.gobusway.model.Halte;
import com.gojek.app.gobusway.networking.ConnectionManager;
import com.gojek.app.gobusway.networking.NetworkServiceProvider;
import com.gojek.app.gobusway.util.PresenterFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Instrumented
public class BusWayMapFragment
  extends Fragment
  implements View.OnClickListener, BusWayMapView, GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener, TraceFieldInterface
{
  private static final int CAMERA_ZOOM = 15;
  private static final double DEFAULT_JAKARTA_LATITUDE = -6.184694D;
  private static final double DEFAULT_JAKARTA_LONGITUDE = 106.828145D;
  private boolean hasInitCameraPosition = false;
  private BusWayMapPresenter mBusWayMapPresenter;
  private OnMyMarkerClickListener mBusWayMarkerClickListener;
  private ArrayList<Marker> mBusWayMarkers;
  private BusWayRequestTimer mBusWayRequestTimer;
  private float mCurrentZoom;
  private FragmentController mFragmentController;
  private GoogleMap mGoogleMap;
  private ArrayList<Halte> mHalteList;
  private HashMap<Marker, Halte> mHalteMap;
  private OnMyMarkerClickListener mHalteMarkerClickListener;
  private Location mLastLocation;
  private LocationClient mLocationClient;
  private LocationRequest mLocationRequest;
  private MapView mMapView;
  private HashMap<Halte, Marker> mMarkerMap;
  private RelativeLayout mSearchButton;
  private HashMap<Marker, OnMyMarkerClickListener> markerClickListenerHashMap;
  private View view;
  
  private void addNewBusWayMarker(ArrayList<Marker> paramArrayList, BusWay paramBusWay)
  {
    paramBusWay = createBusWayMarker(paramBusWay);
    paramArrayList.add(paramBusWay);
    this.markerClickListenerHashMap.put(paramBusWay, this.mBusWayMarkerClickListener);
  }
  
  private Marker createBusWayMarker(BusWay paramBusWay)
  {
    return this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(paramBusWay.getLatitude(), paramBusWay.getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location_busway)));
  }
  
  private void createLocationRequest()
  {
    this.mLocationRequest = LocationRequest.create();
    this.mLocationRequest.setInterval(5000L);
    this.mLocationRequest.setPriority(104);
    this.mLocationRequest.setFastestInterval(1000L);
    this.mLocationClient.requestLocationUpdates(this.mLocationRequest, this);
  }
  
  private void initMarkerClickListener()
  {
    this.markerClickListenerHashMap = new HashMap();
    this.mBusWayMarkerClickListener = BusWayMapFragment..Lambda.2.lambdaFactory$();
    this.mHalteMarkerClickListener = BusWayMapFragment..Lambda.3.lambdaFactory$();
  }
  
  private void removeMarker(int paramInt)
  {
    Marker localMarker = (Marker)this.mBusWayMarkers.get(paramInt);
    localMarker.remove();
    this.markerClickListenerHashMap.remove(localMarker);
  }
  
  public void initBusWayTimer()
  {
    if (this.mBusWayRequestTimer == null) {
      this.mBusWayRequestTimer = new BusWayRequestTimer('᭘', 'Ϩ', this.mBusWayMapPresenter, this.mGoogleMap);
    }
    this.mBusWayMapPresenter.onRefreshBusWayPosition(this.mGoogleMap.getCameraPosition().target.latitude, this.mGoogleMap.getCameraPosition().target.longitude);
  }
  
  public void initMapView()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
    this.mCurrentZoom = 15.0F;
    if (i == 0)
    {
      initMarkerClickListener();
      this.mGoogleMap = this.mMapView.getMap();
      MapsInitializer.initialize(getActivity());
      LatLng localLatLng = new LatLng(-6.184694D, 106.828145D);
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng, 15.0F));
      this.mGoogleMap.setInfoWindowAdapter(new InfoHalteAdapter(getActivity()));
      this.mGoogleMap.setOnMarkerClickListener(this);
      this.mGoogleMap.setOnInfoWindowClickListener(this);
      this.mGoogleMap.setMyLocationEnabled(true);
      this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
      this.mGoogleMap.setOnCameraChangeListener(BusWayMapFragment..Lambda.1.lambdaFactory$(this));
      initBusWayTimer();
      this.mLocationClient = new LocationClient(getActivity(), this, this);
      this.mLocationClient.connect();
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.mFragmentController = ((FragmentController)paramActivity);
  }
  
  public void onClick(View paramView)
  {
    this.mFragmentController.showSearchFragment(this.mHalteList);
  }
  
  public void onConnected(Bundle paramBundle)
  {
    if (this.mLocationClient.getLastLocation() != null) {
      this.hasInitCameraPosition = true;
    }
    double d1;
    if (this.mLocationClient.getLastLocation() != null)
    {
      d1 = this.mLocationClient.getLastLocation().getLatitude();
      if (this.mLocationClient.getLastLocation() == null) {
        break label95;
      }
    }
    label95:
    for (double d2 = this.mLocationClient.getLastLocation().getLongitude();; d2 = 106.828145D)
    {
      paramBundle = new LatLng(d1, d2);
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paramBundle, 15.0F));
      createLocationRequest();
      return;
      d1 = -6.184694D;
      break;
    }
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    Toast.makeText(getActivity(), "Location Connection Failed", 0).show();
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "BusWayMapFragment#onCreateView", null);
      if (this.view == null)
      {
        this.view = paramLayoutInflater.inflate(R.layout.fragment_map, null);
        this.mBusWayMapPresenter = PresenterFactory.createBusWayMapPresenter(this, ((BusWayMainActivity)getActivity()).getNetworkServiceProvider().getNetworkManager());
        this.mMapView = ((MapView)this.view.findViewById(R.id.map));
        this.mSearchButton = ((RelativeLayout)this.view.findViewById(R.id.search_button));
        this.mSearchButton.setOnClickListener(this);
        this.mMapView.onCreate(paramBundle);
        initMapView();
        if (ConnectionManager.isConnected(getActivity())) {
          this.mBusWayMapPresenter.onMapCreateView();
        }
      }
      else
      {
        paramLayoutInflater = this.view;
        TraceMachine.exitMethod();
        return paramLayoutInflater;
      }
    }
    catch (NoSuchFieldError paramViewGroup)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "BusWayMapFragment#onCreateView", null);
        continue;
        this.mFragmentController.noInternetConnectionHandler();
      }
    }
  }
  
  public void onDestroy()
  {
    if (this.mMapView != null) {
      this.mMapView.onDestroy();
    }
    super.onDestroy();
  }
  
  public void onDisconnected() {}
  
  public void onInfoWindowClick(Marker paramMarker)
  {
    new Handler().postDelayed(new BusWayMapFragment.1(this, paramMarker), 500L);
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    if (!this.hasInitCameraPosition)
    {
      LatLng localLatLng = new LatLng(paramLocation.getLatitude(), paramLocation.getLongitude());
      this.mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(localLatLng, 15.0F));
      this.hasInitCameraPosition = true;
    }
    this.mLastLocation = paramLocation;
  }
  
  public void onLowMemory()
  {
    this.mMapView.onLowMemory();
    super.onLowMemory();
  }
  
  public boolean onMarkerClick(Marker paramMarker)
  {
    ((OnMyMarkerClickListener)this.markerClickListenerHashMap.get(paramMarker)).OnMapMarkerListener(paramMarker);
    return true;
  }
  
  public void onPause()
  {
    this.mMapView.onPause();
    super.onPause();
  }
  
  public void onResume()
  {
    this.mMapView.onResume();
    this.mFragmentController.setFragmentTitle(getString(R.string.busway_map_title));
    this.mBusWayMapPresenter.onBusWayMapResume();
    super.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  public void onStop()
  {
    ApplicationStateMonitor.getInstance().activityStopped();
    if ((this.mLocationClient != null) && (this.mLocationClient.isConnected()))
    {
      this.mLocationClient.removeLocationUpdates(this);
      this.mLocationClient.disconnect();
    }
    if (this.mBusWayRequestTimer != null) {
      this.mBusWayRequestTimer.cancel();
    }
    super.onStop();
  }
  
  public void showBusMarker(ArrayList<BusWay> paramArrayList)
  {
    getActivity().runOnUiThread(BusWayMapFragment..Lambda.4.lambdaFactory$(this, paramArrayList));
  }
  
  public void showHalteInfoWindow(Halte paramHalte)
  {
    ((Marker)this.mMarkerMap.get(paramHalte)).showInfoWindow();
    paramHalte = new LatLng(paramHalte.getLatitude(), paramHalte.getLongitude());
    this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paramHalte, this.mCurrentZoom));
  }
  
  public void showHalteList(ArrayList<Halte> paramArrayList)
  {
    this.mMarkerMap = new HashMap();
    this.mHalteMap = new HashMap();
    this.mHalteList = paramArrayList;
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Halte localHalte = (Halte)paramArrayList.next();
      Marker localMarker = this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(localHalte.getLatitude(), localHalte.getLongitude())).title(localHalte.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_halte)));
      this.mMarkerMap.put(localHalte, localMarker);
      this.mHalteMap.put(localMarker, localHalte);
      this.markerClickListenerHashMap.put(localMarker, this.mHalteMarkerClickListener);
    }
  }
  
  private class BusWayRequestTimer
    extends CountDownTimer
  {
    private BusWayMapPresenter mBusWayMapPresenter;
    private GoogleMap mTimerGoogleMap;
    
    public BusWayRequestTimer(long paramLong1, long paramLong2, BusWayMapPresenter paramBusWayMapPresenter, GoogleMap paramGoogleMap)
    {
      super(paramLong2);
      this.mBusWayMapPresenter = paramBusWayMapPresenter;
      this.mTimerGoogleMap = paramGoogleMap;
    }
    
    public void onFinish()
    {
      this.mBusWayMapPresenter.onRefreshBusWayPosition(this.mTimerGoogleMap.getCameraPosition().target.latitude, this.mTimerGoogleMap.getCameraPosition().target.longitude);
    }
    
    public void onTick(long paramLong) {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/view/BusWayMapFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */