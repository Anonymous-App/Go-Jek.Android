package com.gojek.app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.flurry.android.FlurryAgent;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.mart.MartHome;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Poi;
import com.gojek.app.util.DelayTask;
import com.gojek.app.util.DelayTask.CallBack;
import com.gojek.app.util.GojekPreference;
import com.gojek.app.util.Util;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class ChooseFromMap
  extends GojekActivityBase
  implements View.OnClickListener, TextWatcher, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  public static final String CACHE_KEY = "CACHE_KEY";
  public static final String CACHE_LOCATION = "CACHE_LOCATION";
  public static String LOCATION_ADDRESS = "LOCATION_ADDRESS";
  public static String LOCATION_DESC = "LOCATION_DESC";
  public static String LOCATION_ID;
  public static String LOCATION_LAT;
  public static String LOCATION_LNG;
  public static String LOCATION_NAME;
  private static final String TAG = ChooseFromMap.class.getSimpleName();
  public static boolean isFromMart = false;
  private Marker currentMarker;
  private Gson gson = new Gson();
  private boolean isCurrent = false;
  private boolean isInitZoom = true;
  private CustomerSaved mCustomer;
  private DelayTask mDelayTask;
  private EditText mETSearch;
  protected GoogleApiClient mGoogleApiClient;
  private GoogleMap mGoogleMap;
  private ImageView mIVPin;
  private boolean mIsZoom;
  private LinearLayout mLLMarker;
  private ListView mLVLocation;
  private ProgressBar mLVLocationProgress;
  private Location mLastLocation;
  private List<Poi> mListPoi;
  private MapView mMVLocation;
  private Poi mPoi;
  private BasePoiAdapter mPoiAdapter;
  private ProgressBar mProgress;
  private int mScreenHeight;
  private int mScreenWidth;
  private TextView mTVLocation;
  private View mTVPin;
  private MarkerOptions markerOption;
  private VolleyClient volleyClient;
  
  static
  {
    LOCATION_NAME = "LOCATION_NAME";
    LOCATION_LAT = "LOCATION_LAT";
    LOCATION_LNG = "LOCATION_LNG";
    LOCATION_ID = "LOCATION_ID";
  }
  
  private void doLoadPOI(String paramString)
  {
    if (this.mListPoi.size() == 0) {
      this.mLVLocationProgress.setVisibility(0);
    }
    paramString = String.format("https://api.gojek.co.id/gojek/poi/v2/findPoi?name=%s", new Object[] { Util.urlEncode(paramString.trim()) });
    Log.i(TAG, "find poi " + paramString);
    this.volleyClient.getList(TAG, paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(ChooseFromMap.TAG, "error loadPoi " + paramAnonymousVolleyError);
        ChooseFromMap.this.mLVLocationProgress.setVisibility(8);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        if ((paramAnonymousJSONArray != null) && (paramAnonymousJSONArray.length() > 0))
        {
          Object localObject2 = GojekPreference.getInstance(ChooseFromMap.this.getApplicationContext());
          Object localObject1;
          if (!(paramAnonymousJSONArray instanceof JSONArray))
          {
            localObject1 = paramAnonymousJSONArray.toString();
            ((GojekPreference)localObject2).setString("CACHE_LOCATION", (String)localObject1);
            GojekPreference.getInstance(ChooseFromMap.this.getApplicationContext()).setString("CACHE_KEY", ChooseFromMap.this.mETSearch.getText().toString());
            localObject1 = ChooseFromMap.this.gson;
            if ((paramAnonymousJSONArray instanceof JSONArray)) {
              break label205;
            }
            paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
            label91:
            localObject2 = new ChooseFromMap.7.1(this).getType();
            if ((localObject1 instanceof Gson)) {
              break label216;
            }
          }
          label205:
          label216:
          for (paramAnonymousJSONArray = ((Gson)localObject1).fromJson(paramAnonymousJSONArray, (Type)localObject2);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localObject1, paramAnonymousJSONArray, (Type)localObject2))
          {
            paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
            ChooseFromMap.this.mListPoi.clear();
            localObject1 = paramAnonymousJSONArray.iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject2 = (Poi)((Iterator)localObject1).next();
              if ((!((Poi)localObject2).name.isEmpty()) && (((Poi)localObject2).name != null)) {
                ChooseFromMap.this.mListPoi.add(localObject2);
              }
            }
            localObject1 = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
            break;
            paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
            break label91;
          }
          ChooseFromMap.this.mPoiAdapter.notifyDataSetChanged();
          paramAnonymousJSONArray.clear();
        }
        for (;;)
        {
          ChooseFromMap.this.mLVLocationProgress.setVisibility(8);
          return;
          ChooseFromMap.this.mListPoi.clear();
          ChooseFromMap.this.mPoiAdapter.notifyDataSetChanged();
        }
      }
    });
  }
  
  private void initializeMap()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i == 0)
    {
      this.mGoogleMap = this.mMVLocation.getMap();
      this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
      this.mGoogleMap.setMyLocationEnabled(false);
      this.mGoogleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener()
      {
        public void onCameraChange(CameraPosition paramAnonymousCameraPosition)
        {
          if (ChooseFromMap.this.isInitZoom)
          {
            ChooseFromMap.access$302(ChooseFromMap.this, false);
            Log.i(ChooseFromMap.TAG, "init zoom for first map loaded");
            if (ChooseFromMap.this.mPoi != null) {
              ChooseFromMap.this.doReverseGeoCode(ChooseFromMap.this.mPoi.latitude, ChooseFromMap.this.mPoi.longitude);
            }
            return;
          }
          if (ChooseFromMap.this.mIsZoom)
          {
            ChooseFromMap.access$1102(ChooseFromMap.this, false);
            Log.i(ChooseFromMap.TAG, "mIsZoom call doReverseGeoCode");
            if (ChooseFromMap.this.mPoi != null) {
              ChooseFromMap.this.doReverseGeoCode(ChooseFromMap.this.mPoi.latitude, ChooseFromMap.this.mPoi.longitude);
            }
          }
          for (;;)
          {
            ChooseFromMap.this.mProgress.setVisibility(0);
            ChooseFromMap.this.mTVLocation.setVisibility(8);
            ChooseFromMap.this.mTVLocation.setText("");
            return;
            if (ChooseFromMap.this.mDelayTask != null)
            {
              ChooseFromMap.this.mDelayTask.cancel(true);
              ChooseFromMap.access$1202(ChooseFromMap.this, null);
            }
            ChooseFromMap.access$1202(ChooseFromMap.this, new DelayTask(1300, new ChooseFromMap.5.1(this)));
            paramAnonymousCameraPosition = ChooseFromMap.this.mDelayTask;
            Void[] arrayOfVoid = new Void[0];
            if (!(paramAnonymousCameraPosition instanceof AsyncTask)) {
              paramAnonymousCameraPosition.execute(arrayOfVoid);
            } else {
              AsyncTaskInstrumentation.execute((AsyncTask)paramAnonymousCameraPosition, arrayOfVoid);
            }
          }
        }
      });
      MapsInitializer.initialize(this);
      LatLng localLatLng = new LatLng(-6.184694D, 106.828145D);
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng, 12.0F));
      return;
    }
    GooglePlayServicesUtil.getErrorDialog(i, this, 10).show();
  }
  
  private void renderView()
  {
    setContentView(2130968645);
    this.mMVLocation = ((MapView)findViewById(2131624296));
    this.mLVLocation = ((ListView)findViewById(2131624343));
    this.mIVPin = ((ImageView)findViewById(2131624341));
    this.mTVLocation = ((TextView)findViewById(2131624340));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
    this.mLLMarker = ((LinearLayout)findViewById(2131624339));
    this.mTVPin = findViewById(2131624342);
    this.mETSearch = ((EditText)findViewById(2131624215));
    this.mLVLocationProgress = ((ProgressBar)findViewById(2131624344));
  }
  
  private void zoomMapCamera()
  {
    hideKeyboard();
    this.mIsZoom = true;
    LatLng localLatLng = new LatLng(this.mPoi.latitude, this.mPoi.longitude);
    this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng, 15.0F));
  }
  
  public void afterTextChanged(final Editable paramEditable)
  {
    paramEditable = paramEditable.toString();
    if (paramEditable.length() > 0)
    {
      findViewById(2131624214).setBackgroundColor(getResources().getColor(2131558493));
      this.mLVLocation.setVisibility(0);
      if (this.mDelayTask != null)
      {
        this.mDelayTask.cancel(true);
        this.mDelayTask = null;
      }
      this.mDelayTask = new DelayTask(600, new DelayTask.CallBack()
      {
        public void onFinish()
        {
          ChooseFromMap.this.doLoadPOI(paramEditable);
        }
      });
      paramEditable = this.mDelayTask;
      Void[] arrayOfVoid = new Void[0];
      if (!(paramEditable instanceof AsyncTask))
      {
        paramEditable.execute(arrayOfVoid);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)paramEditable, arrayOfVoid);
      return;
    }
    findViewById(2131624214).setBackgroundColor(0);
    this.mLVLocationProgress.setVisibility(8);
    this.mLVLocation.setVisibility(8);
    GojekPreference.getInstance(getApplicationContext()).setString("CACHE_LOCATION", null);
    GojekPreference.getInstance(getApplicationContext()).setString("CACHE_KEY", null);
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
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
  
  public void doReverseGeoCode(final double paramDouble1, double paramDouble2)
  {
    String str = String.format("https://api.gojek.co.id/gojek/poi/reverse-geocode?latLong=%s", new Object[] { paramDouble1 + "," + paramDouble2 });
    Log.i(TAG, "request reverse Geocode to " + str);
    this.volleyClient.get(TAG, str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(ChooseFromMap.TAG, "error reverse GeoCode " + paramAnonymousVolleyError);
        ChooseFromMap.this.mProgress.setVisibility(8);
        ChooseFromMap.this.mTVLocation.setVisibility(0);
        paramAnonymousVolleyError = ChooseFromMap.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
        ChooseFromMap.this.mTVLocation.setText(paramAnonymousVolleyError);
      }
      
      public void onResponse(Poi paramAnonymousPoi)
      {
        if (paramAnonymousPoi != null)
        {
          paramAnonymousPoi.latitude = paramDouble1;
          paramAnonymousPoi.longitude = this.val$lng;
          ChooseFromMap.this.mGoogleMap.clear();
          if (!Util.isTextNotNullEmpty(paramAnonymousPoi.getName())) {
            paramAnonymousPoi.setName(ChooseFromMap.this.mPoi.getName());
          }
          ChooseFromMap.access$102(ChooseFromMap.this, paramAnonymousPoi);
          ChooseFromMap.this.mTVLocation.setText(ChooseFromMap.this.mPoi.name);
        }
        ChooseFromMap.this.mProgress.setVisibility(8);
        ChooseFromMap.this.mTVLocation.setVisibility(0);
      }
    }, Poi.class);
  }
  
  public void fetchLatLng(Poi paramPoi)
  {
    if (!paramPoi.getID().isEmpty())
    {
      paramPoi = String.format("https://api.gojek.co.id/gojek/poi/v2/findLatLng%s", new Object[] { paramPoi.getID() });
      Log.i(TAG, "fetchLatLng to " + paramPoi);
      this.volleyClient.get(TAG, paramPoi, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(ChooseFromMap.TAG, "error fetchLatLng " + paramAnonymousVolleyError);
        }
        
        public void onResponse(Poi paramAnonymousPoi)
        {
          if (paramAnonymousPoi != null)
          {
            ChooseFromMap.access$102(ChooseFromMap.this, paramAnonymousPoi);
            ChooseFromMap.access$302(ChooseFromMap.this, true);
            ChooseFromMap.this.zoomMapCamera();
            ChooseFromMap.this.mLVLocation.setVisibility(8);
            ChooseFromMap.this.mMVLocation.setVisibility(0);
            ChooseFromMap.this.mLLMarker.setVisibility(0);
            ChooseFromMap.this.mProgress.setVisibility(8);
            ChooseFromMap.this.mTVLocation.setVisibility(0);
            ChooseFromMap.this.mTVLocation.setText(ChooseFromMap.this.mPoi.getName());
            ChooseFromMap.this.findViewById(2131624214).setBackgroundColor(0);
          }
        }
      }, Poi.class);
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    FlurryAgent.logEvent("Use_Pick_Location");
    if ((this.mPoi == null) || (TextUtils.isEmpty(this.mPoi.name)))
    {
      Toast.makeText(this, "Can not find your current location. Please check your location settings.", 1).show();
      return;
    }
    if (isFromMart) {}
    for (paramView = new Intent(this, MartHome.class);; paramView = new Intent())
    {
      paramView.putExtra(LOCATION_NAME, this.mPoi.name);
      paramView.putExtra(LOCATION_LAT, this.mPoi.latitude);
      paramView.putExtra(LOCATION_LNG, this.mPoi.longitude);
      paramView.putExtra(LOCATION_ID, this.mPoi.id);
      paramView.putExtra(LOCATION_ADDRESS, this.mPoi.address);
      paramView.putExtra("current", this.isCurrent);
      setResult(-1, paramView);
      if (isFromMart) {
        startActivity(paramView);
      }
      isFromMart = false;
      finish();
      return;
    }
  }
  
  public void onConnected(Bundle paramBundle)
  {
    try
    {
      this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
      doReverseGeoCode(this.mLastLocation.getLatitude(), this.mLastLocation.getLongitude());
      this.mPoi = new Poi();
      this.mPoi.latitude = this.mLastLocation.getLatitude();
      this.mPoi.longitude = this.mLastLocation.getLongitude();
      zoomMapCamera();
      return;
    }
    catch (Exception paramBundle) {}
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult) {}
  
  public void onConnectionSuspended(int paramInt)
  {
    this.mGoogleApiClient.connect();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165409));
    this.action.setIcon(null);
    renderView();
    buildGoogleApiClient();
    this.volleyClient = VolleyClient.getInstance(this);
    this.mCustomer = new CustomerSaved(this);
    this.mMVLocation.onCreate(paramBundle);
    setScreenSize();
    initializeMap();
    this.mListPoi = new ArrayList();
    this.mPoiAdapter = new BasePoiAdapter(this, this.mListPoi);
    this.mLVLocation.setAdapter(this.mPoiAdapter);
    this.mLVLocation.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (ChooseFromMap.this.mGoogleMap != null) {
          ChooseFromMap.this.mGoogleMap.clear();
        }
        ChooseFromMap.access$102(ChooseFromMap.this, (Poi)ChooseFromMap.this.mListPoi.get(paramAnonymousInt));
        ChooseFromMap.this.fetchLatLng(ChooseFromMap.this.mPoi);
      }
    });
    Object localObject2 = GojekPreference.getInstance(this).getString("CACHE_LOCATION", null);
    Object localObject1 = GojekPreference.getInstance(this).getString("CACHE_KEY", null);
    EditText localEditText = this.mETSearch;
    int i;
    if (localObject1 == null)
    {
      paramBundle = "";
      localEditText.setText(paramBundle);
      paramBundle = this.mETSearch;
      if (localObject1 != null) {
        break label269;
      }
      i = 0;
      label176:
      paramBundle.setSelection(i);
      this.isCurrent = getIntent().getBooleanExtra("current", false);
      if (!this.isCurrent) {
        break label277;
      }
      this.mGoogleApiClient.connect();
      findViewById(2131624214).setBackgroundColor(0);
      this.mLVLocation.setVisibility(8);
      getWindow().setSoftInputMode(2);
    }
    for (;;)
    {
      this.mETSearch.addTextChangedListener(this);
      this.mETSearch.setOnEditorActionListener(new TextView.OnEditorActionListener()
      {
        public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          ChooseFromMap.this.hideKeyboard();
          return false;
        }
      });
      return;
      paramBundle = (Bundle)localObject1;
      break;
      label269:
      i = ((String)localObject1).length();
      break label176;
      label277:
      getWindow().setSoftInputMode(4);
      findViewById(2131624214).setBackgroundColor(getResources().getColor(2131558493));
      this.mLVLocation.setVisibility(0);
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        paramBundle = new Gson();
        localObject1 = new TypeToken() {}.getType();
        if (!(paramBundle instanceof Gson)) {}
        for (paramBundle = paramBundle.fromJson((String)localObject2, (Type)localObject1);; paramBundle = GsonInstrumentation.fromJson((Gson)paramBundle, (String)localObject2, (Type)localObject1))
        {
          paramBundle = (List)paramBundle;
          localObject1 = paramBundle.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (Poi)((Iterator)localObject1).next();
            if ((((Poi)localObject2).name != null) && (!((Poi)localObject2).name.isEmpty())) {
              this.mListPoi.add(localObject2);
            }
          }
        }
        paramBundle.clear();
        this.mPoiAdapter.notifyDataSetChanged();
      }
    }
  }
  
  public void onDestroy()
  {
    this.mMVLocation.onDestroy();
    super.onDestroy();
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    try
    {
      this.mPoi = new Poi();
      this.mPoi.latitude = paramLocation.getLatitude();
      this.mPoi.longitude = paramLocation.getLongitude();
      zoomMapCamera();
      return;
    }
    catch (Exception paramLocation) {}
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
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  public void onResume()
  {
    this.mMVLocation.onResume();
    super.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
    this.mGoogleApiClient.connect();
  }
  
  protected void onStop()
  {
    this.volleyClient.cancelAllRequest(TAG);
    super.onStop();
    if (this.mGoogleApiClient.isConnected()) {
      this.mGoogleApiClient.disconnect();
    }
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
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
  
  class BasePoiAdapter
    extends BaseAdapter
  {
    private LayoutInflater layoutInflater;
    private List<Poi> mPois;
    
    public BasePoiAdapter(List<Poi> paramList)
    {
      List localList;
      this.mPois = localList;
      this.layoutInflater = ((LayoutInflater)paramList.getSystemService("layout_inflater"));
    }
    
    public int getCount()
    {
      return this.mPois.size();
    }
    
    public Poi getItem(int paramInt)
    {
      return (Poi)this.mPois.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return ((Poi)this.mPois.get(paramInt)).getId();
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Poi localPoi = getItem(paramInt);
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968646, null);
        paramViewGroup = new ChooseFromMap.ViewHolder(ChooseFromMap.this);
        paramViewGroup.mIV = ((ImageView)paramView.findViewById(2131624305));
        paramViewGroup.mTVName = ((TextView)paramView.findViewById(2131624306));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        paramViewGroup.mTVName.setText(localPoi.name);
        paramViewGroup.mIV.setImageResource(2130837897);
        return paramView;
        paramViewGroup = (ChooseFromMap.ViewHolder)paramView.getTag();
      }
    }
  }
  
  class ViewHolder
  {
    ImageView mIV;
    TextView mTVName;
    
    ViewHolder() {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/ChooseFromMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */