package com.gojek.gotix.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.gojek.gotix.OrderDao.Properties;
import com.gojek.gotix.R.drawable;
import com.gojek.gotix.R.id;
import com.gojek.gotix.network.model.Booking;
import com.gojek.gotix.network.model.Driver;
import com.gojek.gotix.network.model.MapPoint;
import com.gojek.gotix.presenter.GotixMapsDetailPresenter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import de.greenrobot.dao.Property;
import lib.gojek.base.tools.MapManipulator;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(GotixMapsDetailPresenter.class)
public class GotixMapsDetailActivity
  extends GotixBaseMapActivity<GotixMapsDetailPresenter>
{
  public static final String DESTINATION_MARKER = "Destination";
  public static final String DRIVER_MARKER = "Driver";
  public static final String ORIGIN_MARKER = "Origin";
  public static final String TAG = GotixMapsDetailActivity.class.getSimpleName();
  private int orderId;
  
  public void drawRouteOnMap(Booking paramBooking)
  {
    Marker localMarker;
    if (paramBooking != null)
    {
      localObject = updateDriverLocation(paramBooking.getDriver());
      if (paramBooking.getBooking_status().intValue() != 2) {
        break label84;
      }
      localMarker = updateOriginLocation(paramBooking.getOrigin(), false);
      updateDestinationLocation(paramBooking.getDestination(), false);
    }
    for (Object localObject = getMapManipulator().getZoomBoundFromMarkerCameraUpdate(new Marker[] { localObject, localMarker });; localObject = getMapManipulator().getZoomBoundFromMarkerCameraUpdate(new Marker[] { localObject, localMarker }))
    {
      getMapManipulator().moveCamera((CameraUpdate)localObject);
      getMapManipulator().drawPolyline(paramBooking.getRoutePolyline());
      return;
      label84:
      updateOriginLocation(paramBooking.getOrigin(), true);
      localMarker = updateDestinationLocation(paramBooking.getDestination(), true);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.orderId = getIntent().getIntExtra(OrderDao.Properties.Order_id.name, 0);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    setRefreshVisible(true);
    return true;
  }
  
  public void onFragmentReady()
  {
    super.onFragmentReady();
    getGoogleMap().setOnMapLoadedCallback(GotixMapsDetailActivity..Lambda.1.lambdaFactory$(this));
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.action_refresh)
    {
      ((GotixMapsDetailPresenter)getPresenter()).findBooking(this.orderId);
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public Marker updateDestinationLocation(MapPoint paramMapPoint, boolean paramBoolean)
  {
    Marker localMarker2 = getMapManipulator().getMarker("Destination");
    Marker localMarker1 = localMarker2;
    if (localMarker2 == null) {
      localMarker1 = getMapManipulator().addMarker(new LatLng(paramMapPoint.getLatitude().doubleValue(), paramMapPoint.getLongitude().doubleValue()), "Destination", R.drawable.ic_destination_pin);
    }
    localMarker1.setPosition(new LatLng(paramMapPoint.getLatitude().doubleValue(), paramMapPoint.getLongitude().doubleValue()));
    if (paramBoolean) {
      localMarker1.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_origin_pin));
    }
    return localMarker1;
  }
  
  public Marker updateDriverLocation(Driver paramDriver)
  {
    Marker localMarker2 = getMapManipulator().getMarker("Driver");
    Marker localMarker1 = localMarker2;
    if (localMarker2 == null) {
      localMarker1 = getMapManipulator().addMarker(new LatLng(paramDriver.getLatitude().doubleValue(), paramDriver.getLongitude().doubleValue()), "Driver", R.drawable.ic_driver);
    }
    localMarker1.setPosition(new LatLng(paramDriver.getLatitude().doubleValue(), paramDriver.getLongitude().doubleValue()));
    return localMarker1;
  }
  
  public Marker updateOriginLocation(MapPoint paramMapPoint, boolean paramBoolean)
  {
    Marker localMarker2 = getMapManipulator().getMarker("Origin");
    Marker localMarker1 = localMarker2;
    if (localMarker2 == null) {
      localMarker1 = getMapManipulator().addMarker(new LatLng(paramMapPoint.getLatitude().doubleValue(), paramMapPoint.getLongitude().doubleValue()), "Origin", R.drawable.ic_origin_pin);
    }
    localMarker1.setPosition(new LatLng(paramMapPoint.getLatitude().doubleValue(), paramMapPoint.getLongitude().doubleValue()));
    if (paramBoolean) {
      localMarker1.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.gotix_icon_location_origin));
    }
    return localMarker1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixMapsDetailActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */