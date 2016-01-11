package com.gojek.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.util.Util;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import java.util.Iterator;
import java.util.List;

public class BookingStatusMap
  extends GojekActivityBase
{
  private static final String TAG = BookingStatusMap.class.getSimpleName();
  private boolean isBookingLoad = false;
  private com.gojek.app.parcelable.BookingStatus mBookingData;
  private CustomerSaved mCustomerSaved;
  private GoogleMap mGoogleMap;
  private MapView mMVLocation;
  private Menu optionsMenu;
  
  private void doLoadBooking(int paramInt)
  {
    setRefreshActionButtonState(true);
    String str = String.format("https://api.gojek.co.id/gojek/v2/booking/%s", new Object[] { Integer.valueOf(paramInt) });
    VolleyClient.getInstance(this).get(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        BookingStatusMap.this.setRefreshActionButtonState(false);
        Log.e(BookingStatusMap.TAG, "error load booking " + paramAnonymousVolleyError);
        if ((paramAnonymousVolleyError instanceof AuthFailureError)) {
          BookingStatusMap.this.isUnauthorizedApiAccess();
        }
      }
      
      public void onResponse(com.gojek.app.parcelable.BookingStatus paramAnonymousBookingStatus)
      {
        BookingStatusMap.this.setRefreshActionButtonState(false);
        if (paramAnonymousBookingStatus != null)
        {
          BookingStatusMap.access$002(BookingStatusMap.this, false);
          BookingStatusMap.access$102(BookingStatusMap.this, paramAnonymousBookingStatus);
          BookingStatusMap.this.zoomRoute();
        }
      }
    }, com.gojek.app.parcelable.BookingStatus.class, this.mCustomerSaved.getAccessToken());
  }
  
  private void initializeMap()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i == 0)
    {
      this.mGoogleMap = this.mMVLocation.getMap();
      this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
      MapsInitializer.initialize(this);
      return;
    }
    GooglePlayServicesUtil.getErrorDialog(i, this, 10).show();
  }
  
  private void renderView()
  {
    setContentView(2130968638);
    this.mMVLocation = ((MapView)findViewById(2131624296));
  }
  
  private void zoomRoute()
  {
    this.mGoogleMap.clear();
    if (this.mBookingData.addresses.size() == 0) {
      return;
    }
    Object localObject1 = BookingStatus.getLatLngFromStr(((Addresses)this.mBookingData.addresses.get(0)).latLongOrigin);
    Object localObject2 = BookingStatus.getLatLngFromStr(((Addresses)this.mBookingData.addresses.get(0)).latLongDestination);
    for (;;)
    {
      try
      {
        LatLng localLatLng1 = new LatLng(this.mBookingData.driverLatitude, this.mBookingData.driverLongitude);
        try
        {
          this.mGoogleMap.addMarker(BookingStatus.createMarker(localLatLng1, "Driver", 2130837724));
          if (this.mBookingData.statusBooking == 2)
          {
            localLatLng2 = Util.getMapMidPoint(((LatLng)localObject1).latitude, ((LatLng)localObject1).longitude, localLatLng1.latitude, localLatLng1.longitude);
            i = BookingStatus.getZoomLevel(((LatLng)localObject1).latitude, ((LatLng)localObject1).longitude, localLatLng1.latitude, localLatLng1.longitude);
            this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng2, i));
            this.mGoogleMap.addMarker(BookingStatus.createMarker((LatLng)localObject1, "Origin", 2130837910));
            this.mGoogleMap.addMarker(BookingStatus.createMarker((LatLng)localObject2, "Destination", 2130837871));
            localObject1 = ((Addresses)this.mBookingData.addresses.get(0)).routePolyline;
            if (!Util.isTextNotNullEmpty((String)localObject1)) {
              break;
            }
            localObject2 = PolyUtil.decode((String)localObject1);
            localObject1 = new PolylineOptions().width(5.0F).color(-16776961).geodesic(true);
            localObject2 = ((List)localObject2).iterator();
            if (!((Iterator)localObject2).hasNext()) {
              continue;
            }
            ((PolylineOptions)localObject1).add((LatLng)((Iterator)localObject2).next());
            continue;
          }
          LatLng localLatLng2 = Util.getMapMidPoint(((LatLng)localObject2).latitude, ((LatLng)localObject2).longitude, localLatLng1.latitude, localLatLng1.longitude);
          int i = BookingStatus.getZoomLevel(((LatLng)localObject2).latitude, ((LatLng)localObject2).longitude, localLatLng1.latitude, localLatLng1.longitude);
          this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng2, i));
          this.mGoogleMap.addMarker(BookingStatus.createMarker((LatLng)localObject1, "Origin", 2130837952));
          this.mGoogleMap.addMarker(BookingStatus.createMarker((LatLng)localObject2, "Destination", 2130837910));
          continue;
          if (this.mBookingData.statusBooking != 2) {
            continue;
          }
        }
        catch (Exception localException1) {}
      }
      catch (Exception localException2)
      {
        continue;
      }
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((LatLng)localObject1, 12));
      this.mGoogleMap.addMarker(BookingStatus.createMarker((LatLng)localObject1, "Origin", 2130837910));
      this.mGoogleMap.addMarker(BookingStatus.createMarker((LatLng)localObject2, "Destination", 2130837871));
      continue;
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((LatLng)localObject2, 12));
      this.mGoogleMap.addMarker(BookingStatus.createMarker((LatLng)localObject1, "Origin", 2130837952));
      this.mGoogleMap.addMarker(BookingStatus.createMarker((LatLng)localObject2, "Destination", 2130837910));
    }
    this.mGoogleMap.addPolyline((PolylineOptions)localObject1);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165333));
    this.mCustomerSaved = new CustomerSaved(this);
    this.mBookingData = ((com.gojek.app.parcelable.BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    renderView();
    this.mMVLocation.onCreate(paramBundle);
    initializeMap();
    zoomRoute();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    this.optionsMenu = paramMenu;
    getMenuInflater().inflate(2131755018, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.mMVLocation.onDestroy();
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    this.mMVLocation.onLowMemory();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    doLoadBooking(this.mBookingData.id);
    return true;
  }
  
  public void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    if (!this.isBookingLoad) {
      doLoadBooking(this.mBookingData.id);
    }
    super.onResume();
    this.mMVLocation.onResume();
  }
  
  public void setRefreshActionButtonState(boolean paramBoolean)
  {
    MenuItem localMenuItem;
    if (this.optionsMenu != null)
    {
      localMenuItem = this.optionsMenu.findItem(2131625037);
      if (localMenuItem != null)
      {
        if (!paramBoolean) {
          break label39;
        }
        localMenuItem.setActionView(2130968814);
      }
    }
    return;
    label39:
    localMenuItem.setActionView(null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/BookingStatusMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */