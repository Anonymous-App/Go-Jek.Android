package com.gojek.app.food;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.custom.XTextView;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.RouteItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mixpanel.android.mpmetrics.Tweak;
import java.util.ArrayList;
import java.util.List;

public class FoodHome
  extends GojekActivityBase
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{
  public static final int MERCHANT_ITEM_MENU_REQ = 110;
  public static final int MERCHANT_MENU_REQ = 101;
  public static final int REVIEW_ORDER_REQ = 111;
  public static final String TAG = FoodHome.class.getSimpleName();
  private Tweak<String> catTitleTweak = MixpanelAPI.stringTweak("catTitle", "Category");
  private String locNow;
  private BookingStatus mBookingStatus;
  private TextView mETSearch;
  protected GoogleApiClient mGoogleApiClient;
  private LinearLayout mLLSearchBar;
  private Location mLastLocation;
  private Tweak<String> nearMeTitleTweak = MixpanelAPI.stringTweak("nearMeTitle", "Near Me");
  private ArrayList<RouteItem> otherItems;
  private List<RouteItem> routeItemList;
  private ActionBar.Tab tabCategory;
  private ActionBar.Tab tabNearMe;
  private ActionBar.Tab tabReorder;
  
  private void init()
  {
    this.otherItems = new ArrayList();
    this.mBookingStatus = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    setTitle(getString(2131165585));
    this.action.setNavigationMode(2);
    XTextView localXTextView = (XTextView)getLayoutInflater().inflate(2130968836, null).findViewById(2131625027);
    this.tabCategory = this.action.newTab();
    this.tabCategory.setTabListener(new FoodTabListener(new FoodCategory()));
    localXTextView.setText((CharSequence)this.catTitleTweak.get());
    this.tabCategory.setCustomView(localXTextView);
    this.action.addTab(this.tabCategory);
    this.tabNearMe = this.action.newTab().setText(getString(2131165580));
    this.tabNearMe.setTabListener(new FoodTabListener(new FoodNearMe()));
    localXTextView = (XTextView)getLayoutInflater().inflate(2130968836, null).findViewById(2131625027);
    localXTextView.setText((CharSequence)this.nearMeTitleTweak.get());
    this.tabNearMe.setCustomView(localXTextView);
    this.action.addTab(this.tabNearMe);
  }
  
  private void initView()
  {
    this.mLLSearchBar = ((LinearLayout)findViewById(2131624458));
    this.mETSearch = ((TextView)findViewById(2131624215));
    this.mLLSearchBar.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = FoodHome.this.getLastLocation();
        if (FoodHome.this.locNow != null) {
          FoodHome.this.doSearchBar("", FoodHome.this.locNow, FoodHome.this.mBookingStatus);
        }
        while (FoodHome.this.getLastLocation() == null) {
          return;
        }
        FoodHome.this.doSearchBar("", paramAnonymousView.getLatitude() + "," + paramAnonymousView.getLongitude(), FoodHome.this.mBookingStatus);
      }
    });
  }
  
  private void showGPSDisabledAlertToUser()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?").setCancelable(false).setPositiveButton("Goto Settings Page To Enable GPS", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        FoodHome.this.startActivity(paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.create().show();
  }
  
  protected void buildGoogleApiClient()
  {
    try
    {
      this.mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void doSearchBar(String paramString1, String paramString2, BookingStatus paramBookingStatus)
  {
    Intent localIntent = new Intent(this, MerchantSearch.class);
    localIntent.putExtra("MERCHANT_NAME", paramString1);
    localIntent.putExtra("LAST_LOCATION", paramString2);
    localIntent.putExtra("BOOKING_DATA", paramBookingStatus);
    startActivity(localIntent);
  }
  
  public BookingStatus getBookingData()
  {
    return this.mBookingStatus;
  }
  
  public Location getLastLocation()
  {
    return this.mLastLocation;
  }
  
  public String getLocationString()
  {
    return getIntent().getStringExtra("LOC_LAT");
  }
  
  public ArrayList<RouteItem> getOtherItems()
  {
    return this.otherItems;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Log.i(TAG, "onActivityResult() " + paramInt1);
    if (paramIntent != null)
    {
      this.mBookingStatus = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      this.otherItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onConnected(Bundle paramBundle)
  {
    try
    {
      this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
      Log.e(TAG, "ERROR = " + paramBundle.getMessage());
    }
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + paramConnectionResult.getErrorCode());
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    this.mGoogleApiClient.connect();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968688);
    if (((LocationManager)getSystemService("location")).isProviderEnabled("gps")) {}
    for (;;)
    {
      this.locNow = getIntent().getStringExtra("LOC_LAT");
      buildGoogleApiClient();
      initView();
      init();
      return;
      showGPSDisabledAlertToUser();
    }
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    this.mLastLocation = paramLocation;
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  protected void onStart()
  {
    super.onStart();
    this.mGoogleApiClient.connect();
  }
  
  protected void onStop()
  {
    super.onStop();
    if (this.mGoogleApiClient.isConnected()) {
      this.mGoogleApiClient.disconnect();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/FoodHome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */