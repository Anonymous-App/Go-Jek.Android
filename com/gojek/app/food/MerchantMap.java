package com.gojek.app.food;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.parcelable.Merchant;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MerchantMap
  extends GojekActivityBase
{
  private static final String TAG = MerchantMap.class.getSimpleName();
  private GoogleMap mGoogleMap;
  private MapView mMVLocation;
  private int mScreenHeight;
  private int mScreenWidth;
  private Merchant merchant;
  
  private void doLoadLocationMarker(LatLng paramLatLng)
  {
    this.mGoogleMap.addMarker(new MarkerOptions().position(paramLatLng).icon(BitmapDescriptorFactory.fromResource(2130837729)).flat(true).title(this.merchant.name));
    this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paramLatLng, 13.0F));
  }
  
  private void init(Bundle paramBundle)
  {
    this.merchant = ((Merchant)getIntent().getParcelableExtra("MERCHANT"));
    if (this.merchant != null) {
      setTitle(this.merchant.name);
    }
    for (;;)
    {
      this.mMVLocation.onCreate(paramBundle);
      setScreenSize();
      initializeMap();
      return;
      setTitle(getString(2131165764));
    }
  }
  
  private void initializeMap()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i == 0)
    {
      this.mGoogleMap = this.mMVLocation.getMap();
      this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
      this.mGoogleMap.setMyLocationEnabled(true);
      MapsInitializer.initialize(this);
      if ((this.merchant != null) && (this.merchant.getLatLng() != null))
      {
        doLoadLocationMarker(this.merchant.getLatLng());
        return;
      }
      LatLng localLatLng = new LatLng(-6.184694D, 106.828145D);
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng, 13.0F));
      return;
    }
    GooglePlayServicesUtil.getErrorDialog(i, this, 10).show();
  }
  
  private void renderView()
  {
    setContentView(2130968692);
    this.mMVLocation = ((MapView)findViewById(2131624296));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.action.setIcon(null);
    renderView();
    init(paramBundle);
  }
  
  public void onDestroy()
  {
    this.mMVLocation.onDestroy();
    super.onDestroy();
  }
  
  public void onLowMemory()
  {
    this.mMVLocation.onLowMemory();
    super.onLowMemory();
  }
  
  protected void onPause()
  {
    this.mMVLocation.onPause();
    super.onPause();
  }
  
  public void onResume()
  {
    this.mMVLocation.onResume();
    super.onResume();
  }
  
  @SuppressLint({"NewApi"})
  public void setScreenSize()
  {
    if (Build.VERSION.SDK_INT >= 11) {
      localObject = new Point();
    }
    try
    {
      getWindowManager().getDefaultDisplay().getRealSize((Point)localObject);
      this.mScreenWidth = ((Point)localObject).x;
      this.mScreenHeight = ((Point)localObject).y;
      return;
    }
    catch (NoSuchMethodError localNoSuchMethodError) {}
    Object localObject = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    this.mScreenWidth = ((DisplayMetrics)localObject).widthPixels;
    this.mScreenHeight = ((DisplayMetrics)localObject).heightPixels;
    return;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/MerchantMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */