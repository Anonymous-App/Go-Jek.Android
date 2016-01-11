package com.gojek.app.mart;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.Services;
import com.gojek.app.adapter.mart.MartHomeAdapter;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.Adult;
import com.gojek.app.model.BookingSaved;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Martlocation;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.RouteItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class MartHome
  extends GojekActivityBase
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  public static final int REVIEW_ORDER_REQ = 111;
  public static final String TAG = MartHome.class.getSimpleName();
  public String LOCATION_LAT = "LOCATION_LAT";
  private String LOCATION_LNG = "LOCATION_LNG";
  private final int REQUEST_ITEMS = 12;
  private String[] dataLatLon = { "", "" };
  private String dataResponse;
  private int[] data_id = { 1, 2 };
  private List<HashMap<String, String>> fillMaps = new ArrayList();
  private boolean isDifferent;
  private AdapterView.OnItemClickListener listItemClickHandler = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong)
    {
      if (((MartMerchant)MartHome.this.listMartMerchantWithOther.get(paramAnonymousInt)).ageRestriction)
      {
        paramAnonymousAdapterView = new AlertDialog.Builder(MartHome.this);
        paramAnonymousAdapterView.setTitle("Confirm");
        paramAnonymousAdapterView.setMessage(2131165676);
        paramAnonymousAdapterView.setPositiveButton("YES", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
            MartHome.this.listenerAction(paramAnonymousInt);
          }
        });
        paramAnonymousAdapterView.setNegativeButton("NO", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
          }
        });
        paramAnonymousAdapterView.create().show();
        return;
      }
      MartHome.this.listenerAction(paramAnonymousInt);
    }
  };
  private List<Martlocation> listMartLocation = new ArrayList();
  private List<MartMerchant> listMartMerchantWithOther;
  private String location;
  private Adult mAdult;
  private BookingSaved mBookingSaved;
  private BookingStatus mBookingStatus;
  private CustomerSaved mCustomer;
  private TextView mETSearch;
  protected GoogleApiClient mGoogleApiClient;
  private LinearLayout mLLSearchBar;
  private ListView mLVMartMerchant;
  private Location mLastLocation;
  private TextView mTVCommingSoon;
  private MartHomeAdapter martHomeAdapter;
  private ArrayList<RouteItem> otherRouteItems;
  private ProgressBar progressBar;
  private int[] to;
  
  private void init()
  {
    this.mBookingStatus = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = new ArrayList();
    this.mCustomer = new CustomerSaved(this);
    this.mBookingSaved = new BookingSaved(this);
    this.mAdult = new Adult(this);
    martIdActived = -1;
    Object localObject = this.mBookingSaved.getBookingStatus();
    ArrayList localArrayList = this.mBookingSaved.getOtherRouteItems();
    if (localObject != null) {
      this.mBookingStatus = ((BookingStatus)localObject);
    }
    if (localArrayList != null) {
      this.otherRouteItems = localArrayList;
    }
    this.mBookingStatus.customer = this.mCustomer;
    setTitle(getString(2131165648));
    this.to = new int[] { 2131624779, 2131624780 };
    this.listMartMerchantWithOther = new ArrayList();
    localObject = new Addresses();
    if (this.mBookingStatus != null) {
      this.mBookingStatus.addresses.add(localObject);
    }
    this.mLLSearchBar.setVisibility(8);
    this.isDifferent = false;
  }
  
  private void initView()
  {
    this.mLLSearchBar = ((LinearLayout)findViewById(2131624458));
    this.mETSearch = ((TextView)findViewById(2131624215));
    this.mLVMartMerchant = ((ListView)findViewById(2131624802));
    this.progressBar = ((ProgressBar)findViewById(2131624784));
    this.mTVCommingSoon = ((TextView)findViewById(2131624467));
    this.mTVCommingSoon.setVisibility(8);
    this.mLLSearchBar.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MartHome.this.doSearchMerchantByName("", MartHome.this.location, MartHome.this.mBookingStatus, MartHome.this.dataResponse);
      }
    });
  }
  
  private void listenerAction(final int paramInt)
  {
    final int i = ((MartMerchant)this.listMartMerchantWithOther.get(paramInt)).martId;
    martIdActived = i;
    Object localObject;
    if (i == 0)
    {
      this.mBookingStatus.serviceType = 3;
      localObject = new Intent(this, Services.class);
      ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingStatus);
      ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
      startActivity((Intent)localObject);
      return;
    }
    this.mBookingStatus.serviceType = 6;
    if ((i == ((Addresses)this.mBookingStatus.addresses.get(0)).martMerchant.martId) || (((Addresses)this.mBookingStatus.addresses.get(0)).martMerchant.martId == 0))
    {
      localObject = new Martlocation();
      ((Martlocation)localObject).lon = getIntent().getDoubleExtra(this.LOCATION_LNG, 0.0D);
      ((Martlocation)localObject).lat = getIntent().getDoubleExtra(this.LOCATION_LAT, 0.0D);
      ((MartMerchant)this.listMartMerchantWithOther.get(paramInt)).location = ((Martlocation)localObject);
      ((Addresses)this.mBookingStatus.addresses.get(0)).martMerchant = ((MartMerchant)this.listMartMerchantWithOther.get(paramInt));
      ((Addresses)this.mBookingStatus.addresses.get(0)).martMerchantId = ((MartMerchant)this.listMartMerchantWithOther.get(paramInt)).martMerchantId;
      ((Addresses)this.mBookingStatus.addresses.get(0)).originNote = "";
      ((Addresses)this.mBookingStatus.addresses.get(0)).martMerchant.latlong = (((Martlocation)this.listMartLocation.get(paramInt)).lat + "," + ((Martlocation)this.listMartLocation.get(paramInt)).lon);
      localObject = new Intent(this, MerchantCategoryMenu.class);
      ((Intent)localObject).putExtra("MART_NAME", (String)((HashMap)this.fillMaps.get(paramInt)).get("merchant"));
      ((Intent)localObject).putExtra("MART_ID", "" + i);
      ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingStatus);
      ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
      ((Intent)localObject).putExtra("MART_MERCHANT", (Parcelable)this.listMartMerchantWithOther.get(paramInt));
      ((Intent)localObject).putExtra("LOCATION", this.dataLatLon[paramInt]);
      startActivityForResult((Intent)localObject, 12);
      return;
    }
    if (((Addresses)this.mBookingStatus.addresses.get(0)).foodQuantityTotal == 0)
    {
      this.mBookingStatus = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
      this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
      this.mBookingSaved.clearData();
      localObject = new Addresses();
      if (this.mBookingStatus != null) {
        this.mBookingStatus.addresses.add(localObject);
      }
      localObject = new Martlocation();
      ((Martlocation)localObject).lon = getIntent().getDoubleExtra(this.LOCATION_LNG, 0.0D);
      ((Martlocation)localObject).lat = getIntent().getDoubleExtra(this.LOCATION_LAT, 0.0D);
      ((MartMerchant)this.listMartMerchantWithOther.get(paramInt)).location = ((Martlocation)localObject);
      ((Addresses)this.mBookingStatus.addresses.get(0)).martMerchant = ((MartMerchant)this.listMartMerchantWithOther.get(paramInt));
      ((Addresses)this.mBookingStatus.addresses.get(0)).martMerchantId = ((MartMerchant)this.listMartMerchantWithOther.get(paramInt)).martMerchantId;
      ((Addresses)this.mBookingStatus.addresses.get(0)).originNote = "";
      ((Addresses)this.mBookingStatus.addresses.get(0)).martMerchant.latlong = (((Martlocation)this.listMartLocation.get(paramInt)).lat + "," + ((Martlocation)this.listMartLocation.get(paramInt)).lon);
      localObject = new Intent(this, MerchantCategoryMenu.class);
      ((Intent)localObject).putExtra("MART_NAME", (String)((HashMap)this.fillMaps.get(paramInt)).get("merchant"));
      ((Intent)localObject).putExtra("MART_ID", "" + i);
      ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingStatus);
      ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
      ((Intent)localObject).putExtra("MART_MERCHANT", (Parcelable)this.listMartMerchantWithOther.get(paramInt));
      ((Intent)localObject).putExtra("LOCATION", this.dataLatLon[paramInt]);
      startActivityForResult((Intent)localObject, 12);
      return;
    }
    new AlertDialog.Builder(this).setTitle("You Can't choose different mart in one order").setMessage("Are you sure move to this mart? ").setNegativeButton(17039360, null).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MartHome.access$202(MartHome.this, (BookingStatus)MartHome.this.getIntent().getParcelableExtra("BOOKING_DATA"));
        MartHome.this.mBookingSaved.clearData();
        MartHome.access$402(MartHome.this, new ArrayList());
        paramAnonymousDialogInterface = new Addresses();
        if (MartHome.this.mBookingStatus != null) {
          MartHome.this.mBookingStatus.addresses.add(paramAnonymousDialogInterface);
        }
        paramAnonymousDialogInterface = new Martlocation();
        paramAnonymousDialogInterface.lon = MartHome.this.getIntent().getDoubleExtra(MartHome.this.LOCATION_LNG, 0.0D);
        paramAnonymousDialogInterface.lat = MartHome.this.getIntent().getDoubleExtra(MartHome.this.LOCATION_LAT, 0.0D);
        ((MartMerchant)MartHome.this.listMartMerchantWithOther.get(paramInt)).location = paramAnonymousDialogInterface;
        ((Addresses)MartHome.this.mBookingStatus.addresses.get(0)).martMerchant = ((MartMerchant)MartHome.this.listMartMerchantWithOther.get(paramInt));
        ((Addresses)MartHome.this.mBookingStatus.addresses.get(0)).martMerchantId = ((MartMerchant)MartHome.this.listMartMerchantWithOther.get(paramInt)).martMerchantId;
        ((Addresses)MartHome.this.mBookingStatus.addresses.get(0)).originNote = "";
        ((Addresses)MartHome.this.mBookingStatus.addresses.get(0)).martMerchant.latlong = (((Martlocation)MartHome.this.listMartLocation.get(paramInt)).lat + "," + ((Martlocation)MartHome.this.listMartLocation.get(paramInt)).lon);
        paramAnonymousDialogInterface = new Intent(MartHome.this, MerchantCategoryMenu.class);
        paramAnonymousDialogInterface.putExtra("MART_NAME", (String)((HashMap)MartHome.this.fillMaps.get(paramInt)).get("merchant"));
        paramAnonymousDialogInterface.putExtra("MART_ID", "" + i);
        paramAnonymousDialogInterface.putExtra("BOOKING_DATA", MartHome.this.mBookingStatus);
        paramAnonymousDialogInterface.putParcelableArrayListExtra("OTHER_ITEM", MartHome.this.otherRouteItems);
        paramAnonymousDialogInterface.putExtra("MART_MERCHANT", (Parcelable)MartHome.this.listMartMerchantWithOther.get(paramInt));
        paramAnonymousDialogInterface.putExtra("LOCATION", MartHome.this.dataLatLon[paramInt]);
        MartHome.this.startActivityForResult(paramAnonymousDialogInterface, 12);
      }
    }).create().show();
  }
  
  private void showGPSDisabledAlertToUser()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?").setCancelable(false).setPositiveButton("Go to Settings Page To Enable GPS", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        MartHome.this.startActivity(paramAnonymousDialogInterface);
        MartHome.this.finish();
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        MartHome.this.finish();
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
  
  public void doLoadCompany(String paramString)
  {
    this.mLVMartMerchant.setVisibility(8);
    this.progressBar.setVisibility(0);
    paramString = String.format("https://api.gojek.co.id/gojek/mart-merchant/find?location=%s", new Object[] { paramString });
    VolleyClient.getInstance(this).getList(TAG, paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MartHome.TAG, "loadCategory get error " + paramAnonymousVolleyError);
        MartHome.this.progressBar.setVisibility(8);
        MartHome.this.mTVCommingSoon.setVisibility(0);
        MartHome.this.mTVCommingSoon.setText("We will be serving your area soon");
        MartHome.this.mLVMartMerchant.setVisibility(0);
        MartHome.this.listMartMerchantWithOther.clear();
        paramAnonymousVolleyError = new MartMerchant();
        Martlocation localMartlocation = new Martlocation();
        localMartlocation.lat = -6.2728787D;
        localMartlocation.lon = 106.8174865D;
        paramAnonymousVolleyError.id = 0;
        paramAnonymousVolleyError.martId = 0;
        paramAnonymousVolleyError.martMerchantName = "Other Store";
        paramAnonymousVolleyError.martName = "Please input your order manually";
        paramAnonymousVolleyError.location = localMartlocation;
        paramAnonymousVolleyError.martCategory = "";
        MartHome.this.listMartMerchantWithOther.add(paramAnonymousVolleyError);
        MartHome.access$1502(MartHome.this, new MartHome.6.3(this, MartHome.this.getApplicationContext(), MartHome.this.listMartMerchantWithOther, MartHome.this.mBookingStatus));
        MartHome.this.mLVMartMerchant.setAdapter(MartHome.this.martHomeAdapter);
        MartHome.this.mLVMartMerchant.setOnItemClickListener(MartHome.this.listItemClickHandler);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Object localObject2 = MartHome.this;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          localObject1 = paramAnonymousJSONArray.toString();
          MartHome.access$1002((MartHome)localObject2, (String)localObject1);
          localObject1 = new Gson();
          if ((paramAnonymousJSONArray instanceof JSONArray)) {
            break label217;
          }
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          label48:
          localObject2 = new MartHome.6.1(this).getType();
          if ((localObject1 instanceof Gson)) {
            break label228;
          }
        }
        label217:
        label228:
        for (paramAnonymousJSONArray = ((Gson)localObject1).fromJson(paramAnonymousJSONArray, (Type)localObject2);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localObject1, paramAnonymousJSONArray, (Type)localObject2))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          MartHome.this.progressBar.setVisibility(8);
          MartHome.this.mLVMartMerchant.setVisibility(0);
          MartHome.this.listMartMerchantWithOther.clear();
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.size() <= 0)) {
            break label243;
          }
          MartHome.this.listMartMerchantWithOther.clear();
          MartHome.this.listMartMerchantWithOther.addAll(paramAnonymousJSONArray);
          paramAnonymousJSONArray = paramAnonymousJSONArray.iterator();
          while (paramAnonymousJSONArray.hasNext())
          {
            localObject1 = (MartMerchant)paramAnonymousJSONArray.next();
            MartHome.this.listMartLocation.add(((MartMerchant)localObject1).location);
          }
          localObject1 = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break label48;
        }
        label243:
        MartHome.this.mTVCommingSoon.setVisibility(0);
        MartHome.this.mTVCommingSoon.setText("More stores are coming soon");
        paramAnonymousJSONArray = new MartMerchant();
        Object localObject1 = new Martlocation();
        ((Martlocation)localObject1).lat = -6.2728787D;
        ((Martlocation)localObject1).lon = 106.8174865D;
        paramAnonymousJSONArray.id = 0;
        paramAnonymousJSONArray.martId = 0;
        paramAnonymousJSONArray.martMerchantName = "Other Store";
        paramAnonymousJSONArray.martName = "Other Store";
        paramAnonymousJSONArray.location = ((Martlocation)localObject1);
        paramAnonymousJSONArray.martCategory = "";
        MartHome.this.listMartMerchantWithOther.add(paramAnonymousJSONArray);
        MartHome.access$1402(MartHome.this, new int[MartHome.this.listMartMerchantWithOther.size()]);
        MartHome.access$802(MartHome.this, new String[MartHome.this.listMartMerchantWithOther.size()]);
        int j = 0;
        while (j < MartHome.this.listMartMerchantWithOther.size())
        {
          paramAnonymousJSONArray = new HashMap();
          paramAnonymousJSONArray.put("merchant", ((MartMerchant)MartHome.this.listMartMerchantWithOther.get(j)).martName);
          int k = 0;
          int m = 1;
          localObject1 = new StringBuilder(((MartMerchant)MartHome.this.listMartMerchantWithOther.get(j)).martCategory.toLowerCase());
          if (k < ((StringBuilder)localObject1).length())
          {
            int i;
            if (((StringBuilder)localObject1).charAt(k) == ' ') {
              i = 1;
            }
            for (;;)
            {
              k += 1;
              m = i;
              break;
              i = m;
              if (m != 0)
              {
                i = m;
                if (!Character.isWhitespace(((StringBuilder)localObject1).charAt(k)))
                {
                  ((StringBuilder)localObject1).setCharAt(k, Character.toUpperCase(((StringBuilder)localObject1).charAt(k)));
                  i = 0;
                }
              }
            }
          }
          paramAnonymousJSONArray.put("category", ((StringBuilder)localObject1).toString());
          MartHome.this.data_id[j] = ((MartMerchant)MartHome.this.listMartMerchantWithOther.get(j)).martId;
          MartHome.this.dataLatLon[j] = ((MartMerchant)MartHome.this.listMartMerchantWithOther.get(j)).getLatLong();
          MartHome.this.fillMaps.add(paramAnonymousJSONArray);
          j += 1;
        }
        MartHome.access$1502(MartHome.this, new MartHome.6.2(this, MartHome.this.getApplicationContext(), MartHome.this.listMartMerchantWithOther, MartHome.this.mBookingStatus));
        MartHome.this.mLLSearchBar.setVisibility(0);
        MartHome.this.mLVMartMerchant.setAdapter(MartHome.this.martHomeAdapter);
        MartHome.this.mLVMartMerchant.setOnItemClickListener(MartHome.this.listItemClickHandler);
      }
    });
  }
  
  public void doSearchMerchantByName(String paramString1, String paramString2, BookingStatus paramBookingStatus, String paramString3)
  {
    Intent localIntent = new Intent(this, MartSearchMerchant.class);
    localIntent.putExtra("MART_NAME", paramString1);
    localIntent.putExtra("LAST_LOCATION", paramString2);
    localIntent.putExtra("BOOKING_DATA", paramBookingStatus);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    localIntent.putExtra("JSON", paramString3);
    startActivityForResult(localIntent, 13);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    martIdActived = -1;
    if (paramIntent != null)
    {
      this.mBookingStatus = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      this.otherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
      doLoadCompany(this.location);
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onConnected(Bundle paramBundle)
  {
    try
    {
      this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
      if (this.mLastLocation != null)
      {
        this.location = (this.mLastLocation.getLatitude() + "," + this.mLastLocation.getLongitude());
        doLoadCompany(this.location);
        return;
      }
      this.progressBar.setVisibility(8);
      this.mTVCommingSoon.setVisibility(0);
      this.mTVCommingSoon.setText(getString(2131165694));
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
      Log.e(TAG, " " + paramBundle.getMessage());
    }
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
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968755);
    buildGoogleApiClient();
    if (((LocationManager)getSystemService("location")).isProviderEnabled("gps"))
    {
      initView();
      init();
      return;
    }
    showGPSDisabledAlertToUser();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    paramMenu.add(0, 7, 0, getString(2131165601)).setIcon(2130837852);
    int i = 0;
    while (i < paramMenu.size())
    {
      MenuItemCompat.setShowAsAction(paramMenu.getItem(i), 1);
      i += 1;
    }
    return true;
  }
  
  protected void onStart()
  {
    super.onStart();
    this.mGoogleApiClient.connect();
  }
  
  protected void onStop()
  {
    Log.i(TAG, "onStop");
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    super.onStop();
    if (this.mGoogleApiClient.isConnected()) {
      this.mGoogleApiClient.disconnect();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MartHome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */