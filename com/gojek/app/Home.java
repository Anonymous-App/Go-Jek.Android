package com.gojek.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.gojek.app.fragment.History;
import com.gojek.app.fragment.PickService;
import com.gojek.app.fragment.Settings;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Push;
import com.gojek.app.model.PushModel;
import com.gojek.app.persistence.dao.BookingRateDao;
import com.gojek.app.persistence.domain.BookingRate;
import com.gojek.app.util.GojekPreference;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import java.util.List;

public class Home
  extends GojekActivityBase
{
  public static final int MENU_HISTORY = 2;
  public static final int MENU_PICK = 1;
  public static final int MENU_SETTINGS = 3;
  private static final String TAG = Home.class.getSimpleName();
  private final int PERMISSIONS_REQUEST_MAPS = 0;
  private BookingRateDao bookingRateDao;
  private com.gojek.app.parcelable.BookingStatus mBookingData;
  private CustomerSaved mCustomerSaved;
  private Push mPushData;
  private BroadcastReceiver mPushReceiver;
  private int mSelectedMenu;
  
  private void bindReceiver()
  {
    this.mPushReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        Home.access$002(Home.this, (Push)paramAnonymousIntent.getParcelableExtra("PUSH_DATA"));
        Home.this.selectFragment(2);
        Home.this.supportInvalidateOptionsMenu();
        paramAnonymousContext = new com.gojek.app.parcelable.BookingStatus();
        paramAnonymousContext.id = Home.this.mPushData.model.id;
        paramAnonymousContext.setOrderNo(Home.this.mPushData.model.no);
        Log.i(Home.TAG, "get booking status " + Home.this.mPushData.model.status);
        if (Home.this.mPushData.model.status == 5)
        {
          paramAnonymousIntent = new Intent(Home.this, BookingTryAgain.class);
          paramAnonymousIntent.putExtra("BOOKING_DATA", paramAnonymousContext);
          Home.this.startActivity(paramAnonymousIntent);
          Home.this.finish();
          return;
        }
        paramAnonymousIntent = new Intent(Home.this, BookingStatus.class);
        paramAnonymousIntent.putExtra("PUSH_DATA", Home.this.mPushData);
        paramAnonymousIntent.putExtra("BOOKING_DATA", paramAnonymousContext);
        Home.this.startActivity(paramAnonymousIntent);
        Home.this.finish();
      }
    };
    registerReceiver(this.mPushReceiver, new IntentFilter("com.gojek.app.PUSH_RECEIVER"));
  }
  
  private void doLoadUnratedBooking(int paramInt)
  {
    String str = String.format("https://api.gojek.co.id/gojek/v2/booking/%s", new Object[] { Integer.valueOf(paramInt) });
    VolleyClient.getInstance(this).get(TAG, str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(Home.TAG, "doLoadUnratedBooking got error " + paramAnonymousVolleyError);
      }
      
      public void onResponse(com.gojek.app.parcelable.BookingStatus paramAnonymousBookingStatus)
      {
        if ((paramAnonymousBookingStatus != null) && (paramAnonymousBookingStatus.statusBooking == 4))
        {
          Home.access$302(Home.this, paramAnonymousBookingStatus);
          paramAnonymousBookingStatus = new Intent(Home.this, ReviewExperience.class);
          paramAnonymousBookingStatus.putExtra("PUSH_DATA", Home.this.mPushData);
          paramAnonymousBookingStatus.putExtra("BOOKING_DATA", Home.this.mBookingData);
          paramAnonymousBookingStatus.putExtra("FORCE_RATING", true);
          Home.this.startActivity(paramAnonymousBookingStatus);
        }
      }
    }, com.gojek.app.parcelable.BookingStatus.class, this.mCustomerSaved.getAccessToken());
  }
  
  private void findUnratedBooking()
  {
    if (this.mCustomerSaved.customerId == null) {
      return;
    }
    int i = Integer.parseInt(this.mCustomerSaved.customerId);
    List localList = this.bookingRateDao.findUnratedBooking(i);
    if (localList.size() > 0)
    {
      i = ((BookingRate)localList.get(0)).getId();
      Log.i(TAG, "get unrated booking " + i);
      doLoadUnratedBooking(i);
      return;
    }
    Log.i(TAG, "you have no unrated booking ");
  }
  
  private void renderView()
  {
    setContentView(2130968721);
  }
  
  private void selectFragment(int paramInt)
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    switch (paramInt)
    {
    }
    for (;;)
    {
      localFragmentTransaction.commit();
      this.mSelectedMenu = paramInt;
      setTitle();
      return;
      localFragmentTransaction.replace(2131624697, PickService.newInstance());
      continue;
      localFragmentTransaction.replace(2131624697, History.newInstance());
      continue;
      localFragmentTransaction.replace(2131624697, Settings.newInstance());
    }
  }
  
  private void setTitle()
  {
    if (this.mSelectedMenu == 1) {
      setTitle(getString(2131165311));
    }
    do
    {
      return;
      if (this.mSelectedMenu == 2)
      {
        setTitle(getString(2131165333));
        return;
      }
    } while (this.mSelectedMenu != 3);
    setTitle(getString(2131165786));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    CustomerSaved localCustomerSaved = new CustomerSaved(this);
    if ((paramIntent != null) && (paramIntent.getBooleanExtra("SETTINGS", false)) && (localCustomerSaved.customerId != null))
    {
      finish();
      startActivity(getIntent());
    }
  }
  
  public void onBackPressed()
  {
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    if (this.mSelectedMenu != 1)
    {
      selectFragment(1);
      supportInvalidateOptionsMenu();
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    FacebookSdk.sdkInitialize(getApplicationContext());
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    getSupportActionBar().setHomeButtonEnabled(false);
    Intent localIntent;
    if ((ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) || (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")))
    {
      this.mCustomerSaved = new CustomerSaved(this);
      this.bookingRateDao = new BookingRateDao(this);
      this.mBookingData = new com.gojek.app.parcelable.BookingStatus();
      this.mSelectedMenu = getIntent().getIntExtra("FEED_BACK", 1);
      this.mPushData = ((Push)getIntent().getParcelableExtra("PUSH_DATA"));
      if (this.mPushData != null)
      {
        paramBundle = new com.gojek.app.parcelable.BookingStatus();
        paramBundle.id = this.mPushData.model.id;
        paramBundle.setOrderNo(this.mPushData.model.no);
        Log.i(TAG, "get booking status from push_data " + this.mPushData.model.status);
        if (this.mPushData.model.status == 5) {
          break label348;
        }
        localIntent = new Intent(this, BookingStatus.class);
        localIntent.putExtra("PUSH_DATA", this.mPushData);
        localIntent.putExtra("BOOKING_DATA", paramBundle);
        startActivity(localIntent);
      }
    }
    for (;;)
    {
      paramBundle = GojekPreference.getInstance(this).getString("GojekUpdate", "");
      if ((paramBundle.equals("")) || (paramBundle == null))
      {
        GojekPreference.getInstance(this).setString("GojekUpdate", "1");
        if (this.mCustomerSaved != null) {
          this.mixPanel.identify(this.mCustomerSaved.getCustomerId());
        }
      }
      renderView();
      selectFragment(this.mSelectedMenu);
      supportInvalidateOptionsMenu();
      findUnratedBooking();
      return;
      ActivityCompat.requestPermissions(this, new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 0);
      break;
      label348:
      paramBundle.setOrderNo(this.mPushData.model.no);
      localIntent = new Intent(this, BookingTryAgain.class);
      localIntent.putExtra("BOOKING_DATA", paramBundle);
      startActivity(localIntent);
      finish();
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if ((i == 1) || (i == 2) || (i == 3))
    {
      selectFragment(i);
      supportInvalidateOptionsMenu();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    super.onPause();
    AppEventsLogger.deactivateApp(getApplicationContext(), getString(2131165535));
    try
    {
      unregisterReceiver(this.mPushReceiver);
      return;
    }
    catch (Exception localException) {}
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    boolean bool2 = true;
    paramMenu.clear();
    if (this.mSelectedMenu == 2)
    {
      this.mixPanel.track("PAS: MyBookings Selected");
      paramMenu.add(0, 1, 0, getString(2131165725)).setIcon(2130837903);
      paramMenu.add(0, 3, 0, getString(2131165786)).setIcon(2130837904);
    }
    for (;;)
    {
      boolean bool1 = super.onPrepareOptionsMenu(paramMenu);
      return bool1;
      if (this.mSelectedMenu == 3)
      {
        this.mixPanel.track("PAS: Settings Selected");
        paramMenu.add(0, 1, 0, getString(2131165725)).setIcon(2130837903);
        paramMenu.add(0, 2, 0, getString(2131165333)).setIcon(2130837902);
        int i = 0;
        for (;;)
        {
          bool1 = bool2;
          if (i >= paramMenu.size()) {
            break;
          }
          MenuItemCompat.setShowAsAction(paramMenu.getItem(i), 1);
          i += 1;
        }
      }
      if (this.mSelectedMenu == 1)
      {
        paramMenu.add(0, 2, 0, getString(2131165333)).setIcon(2130837902);
        paramMenu.add(0, 3, 0, getString(2131165786)).setIcon(2130837904);
      }
    }
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      return;
    } while ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0));
    Toast.makeText(getApplicationContext(), "Please enable the Maps permission in your Settings", 1).show();
  }
  
  protected void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    super.onResume();
    AppEventsLogger.activateApp(getApplicationContext(), getString(2131165535));
    bindReceiver();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/Home.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */