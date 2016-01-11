package com.gojek.app;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.flurry.android.FlurryAgent;
import com.gojek.app.custom.CustomScrollView;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.NearDriverLocation;
import com.gojek.app.model.Poi;
import com.gojek.app.model.Route;
import com.gojek.app.model.RouteResponse;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Services
  extends GojekActivityBase
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{
  private static final String TAG = Services.class.getSimpleName();
  public static String locDetailsFromConfirmation;
  private final int REQUEST_CONTACT_FROM = 21;
  private final int REQUEST_CONTACT_RES_FROM = 31;
  private final int REQUEST_CONTACT_RES_TO = 32;
  private final int REQUEST_CONTACT_TO = 22;
  private final int REQUEST_DETAIL = 41;
  private final int REQUEST_FROM = 11;
  private final int REQUEST_TO = 12;
  private NearDriverLocation[] driverList;
  private boolean firstLoadMap = false;
  JSONObject fromLocationDetailsProperties;
  JSONObject fromLocationProperties;
  private Gson gson = new Gson();
  private boolean isLocationHistoryLoaded = false;
  private LatLng lastMapPoint = new LatLng(-6.184694D, 106.828145D);
  private BookingStatus mBookingData;
  private Bundle mBundle;
  private CustomerSaved mCustomer;
  private long mDateSelected;
  private EditText mETContactNameFrom;
  private EditText mETContactNameTo;
  private EditText mETContactPhoneFrom;
  private EditText mETContactPhoneTo;
  private EditText mETEstimatedCost;
  private EditText mETItemToDeliver;
  private EditText mETItemsToBuy;
  private EditText mETLocationDetailFrom;
  private EditText mETLocationDetailTo;
  private int mFlag;
  protected GoogleApiClient mGoogleApiClient;
  private GoogleMap mGoogleMap;
  private LinearLayout mLLContactInfoFrom;
  private LinearLayout mLLContactInfoTo;
  private LinearLayout mLLPickTime;
  private LinearLayout mLLPrice;
  private Location mLastLocation;
  private Poi mPoiFrom;
  private Poi mPoiTo;
  private RadioButton mRBPickupLater;
  private RadioButton mRBPickupNow;
  private RadioGroup mRGPickup;
  private CustomScrollView mSVServices;
  private TextView mTVDetails;
  private TextView mTVPickLocationFrom;
  private TextView mTVPickLocationTo;
  private TextView mTVPickTime;
  private TextView mTVPrice;
  private TextView mTVShoopingFee;
  private MapView nearMeMap;
  private String oldLocationDetails;
  private RouteResponse routeResponse;
  JSONObject toLocationProperties;
  private VolleyClient volleyClient;
  
  private void doCalculate()
  {
    findViewById(2131624286).setVisibility(0);
    this.mTVPrice.setVisibility(8);
    JSONObject localJSONObject1 = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject1.putOpt("serviceType", Integer.valueOf(this.mBookingData.serviceType));
        if (this.mCustomer.customerId != null) {
          continue;
        }
        localJSONObject1.putOpt("customerId", "0");
        localJSONObject1.putOpt("corporateId", "");
        localJSONObject1.putOpt("marketplaceType", "");
        localJSONObject1.putOpt("region", "");
        localJSONObject1.putOpt("paymentType", "0");
        JSONArray localJSONArray = new JSONArray();
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.putOpt("originLatLong", this.mPoiFrom.latitude + "," + this.mPoiFrom.longitude);
        localJSONObject2.putOpt("destinationLatLong", this.mPoiTo.latitude + "," + this.mPoiTo.longitude);
        localJSONObject2.putOpt("serviceType", Integer.valueOf(this.mBookingData.serviceType));
        localJSONArray.put(localJSONObject2);
        localJSONObject1.putOpt("routeRequests", localJSONArray);
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        continue;
      }
      this.volleyClient.post("https://api.gojek.co.id/gojek/v2/calculate/", localJSONObject1, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(Services.TAG, "error calculate " + paramAnonymousVolleyError);
          Services.this.findViewById(2131624286).setVisibility(8);
          Services.this.mTVPrice.setVisibility(0);
          Services.this.mTVPrice.setText(Services.this.getString(2131165537));
        }
        
        public void onResponse(RouteResponse paramAnonymousRouteResponse)
        {
          Services.this.findViewById(2131624286).setVisibility(8);
          Services.this.mTVPrice.setVisibility(0);
          if (paramAnonymousRouteResponse != null)
          {
            Services.access$502(Services.this, paramAnonymousRouteResponse);
            if ((Services.this.routeResponse.routes != null) && (Services.this.routeResponse.routes.size() > 0))
            {
              paramAnonymousRouteResponse = (Route)Services.this.routeResponse.routes.get(0);
              try
              {
                Object localObject = "(" + paramAnonymousRouteResponse.distance + " KM) " + Util.getRupiahFormat(String.valueOf(paramAnonymousRouteResponse.price));
                Services.this.mTVPrice.setText((CharSequence)localObject);
                localObject = new HashMap();
                ((Map)localObject).put("price", String.valueOf(paramAnonymousRouteResponse.price));
                FlurryAgent.logEvent("Price_Check", (Map)localObject);
                if (Services.this.mBookingData.serviceType == 3)
                {
                  Services.this.mTVShoopingFee.setVisibility(0);
                  long l1 = paramAnonymousRouteResponse.price;
                  long l2 = Services.this.routeResponse.shoppingFee;
                  paramAnonymousRouteResponse = "(" + paramAnonymousRouteResponse.distance + " km) " + Util.getRupiahFormat(String.valueOf(l1 + l2));
                  Services.this.mTVPrice.setText(paramAnonymousRouteResponse);
                  Services.this.mTVShoopingFee.setText("Price includes " + Util.getRupiahFormat(String.valueOf(l2)) + " shopping fee");
                  return;
                }
                Services.this.mTVShoopingFee.setVisibility(8);
                return;
              }
              catch (Exception paramAnonymousRouteResponse) {}
            }
          }
        }
      }, RouteResponse.class);
      return;
      localJSONObject1.putOpt("customerId", this.mCustomer.customerId);
    }
  }
  
  private void init()
  {
    this.mBundle = getIntent().getExtras();
    this.mCustomer = new CustomerSaved(this);
    this.mBookingData = ((BookingStatus)this.mBundle.getParcelable("BOOKING_DATA"));
    this.mFlag = this.mBundle.getInt("FLAG");
    this.volleyClient = VolleyClient.getInstance(this);
    this.mSVServices.addInterceptScrollView(this.nearMeMap);
    Double localDouble1 = Double.valueOf(this.mBundle.getDouble("lat", 0.0D));
    Double localDouble2 = Double.valueOf(this.mBundle.getDouble("lon", 0.0D));
    if ((localDouble1.doubleValue() != 0.0D) && (localDouble2.doubleValue() != 0.0D))
    {
      String str = this.mBundle.getString("destination");
      this.mBookingData = new BookingStatus();
      this.mBookingData.setCustomer(this.mCustomer);
      this.mBookingData.serviceType = 1;
      this.mPoiTo = new Poi(0, str, localDouble1.doubleValue(), localDouble2.doubleValue(), "");
      this.mTVPickLocationTo.setText(this.mPoiTo.name);
    }
    switch (this.mBookingData.serviceType)
    {
    }
    for (;;)
    {
      if ((this.mFlag == 23) || (this.mFlag == 24)) {
        setUpdateData();
      }
      this.mETEstimatedCost.addTextChangedListener(new NumberTextWatcher(this.mETEstimatedCost));
      this.mLLPickTime.setVisibility(8);
      this.mRBPickupNow.setChecked(true);
      this.mRGPickup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
      {
        public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
        {
          paramAnonymousRadioGroup = (RadioButton)Services.this.findViewById(paramAnonymousInt);
          switch (paramAnonymousInt)
          {
          }
          do
          {
            do
            {
              return;
            } while (!paramAnonymousRadioGroup.isChecked());
            Services.this.mLLPickTime.setVisibility(0);
            Services.this.openTimePicker();
            return;
          } while (!paramAnonymousRadioGroup.isChecked());
          Services.this.mTVPickTime.setText(2131165729);
          Services.access$302(Services.this, 0L);
          Services.this.mLLPickTime.setVisibility(8);
        }
      });
      initializeMap();
      return;
      setTitle(getString(2131165881));
      FlurryAgent.logEvent("Transport_Selected");
      findViewById(2131624258).setVisibility(8);
      findViewById(2131624995).setVisibility(8);
      findViewById(2131624259).setVisibility(8);
      findViewById(2131624895).setVisibility(8);
      findViewById(2131624992).setVisibility(8);
      findViewById(2131624261).setVisibility(8);
      findViewById(2131625001).setVisibility(8);
      continue;
      setTitle(getString(2131165431));
      FlurryAgent.logEvent("Courier_Selected");
      findViewById(2131624992).setVisibility(8);
      findViewById(2131624261).setVisibility(8);
      this.mTVDetails.setText(getString(2131165458));
      continue;
      setTitle(getString(2131165798));
      FlurryAgent.logEvent("Shopping_Selected");
      findViewById(2131624258).setVisibility(8);
      findViewById(2131624259).setVisibility(8);
      findViewById(2131624895).setVisibility(8);
      findViewById(2131625001).setVisibility(8);
      continue;
      setTitle(getString(2131165648));
      findViewById(2131624258).setVisibility(8);
      findViewById(2131624259).setVisibility(8);
      findViewById(2131624895).setVisibility(8);
      findViewById(2131625001).setVisibility(8);
    }
  }
  
  private void initializeMap()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i == 0)
    {
      this.mGoogleMap = this.nearMeMap.getMap();
      this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
      this.mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
      this.mGoogleMap.setMyLocationEnabled(true);
      MapsInitializer.initialize(this);
      LatLng localLatLng = new LatLng(-6.184694D, 106.828145D);
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng, 12.0F));
      return;
    }
    GooglePlayServicesUtil.getErrorDialog(i, this, 10).show();
  }
  
  private void loadOriginMap(double paramDouble1, double paramDouble2)
  {
    Log.i(TAG, "load map to " + paramDouble1 + "," + paramDouble2);
    this.lastMapPoint = new LatLng(paramDouble1, paramDouble2);
    Object localObject = new LatLng(paramDouble1, paramDouble2);
    this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((LatLng)localObject, 15.0F));
    this.mGoogleMap.clear();
    localObject = this.mGoogleMap.getCameraPosition();
    localObject = String.format("https://api.gojek.co.id/gojek/drivers/area?location=%s&timeout=false&limit=9999", new Object[] { ((CameraPosition)localObject).target.latitude + "," + ((CameraPosition)localObject).target.longitude });
    this.volleyClient.getList((String)localObject, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(Services.TAG, "error get driver nearMe " + paramAnonymousVolleyError);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        if (paramAnonymousJSONArray != null)
        {
          Services localServices = Services.this;
          Gson localGson = Services.this.gson;
          if (!(paramAnonymousJSONArray instanceof JSONArray))
          {
            paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
            if ((localGson instanceof Gson)) {
              break label138;
            }
          }
          label138:
          for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, NearDriverLocation[].class);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, NearDriverLocation[].class))
          {
            Services.access$902(localServices, (NearDriverLocation[])paramAnonymousJSONArray);
            paramAnonymousJSONArray = Services.this.driverList;
            int j = paramAnonymousJSONArray.length;
            int i = 0;
            while (i < j)
            {
              localServices = paramAnonymousJSONArray[i];
              localServices.convertToLatLng();
              Services.this.mGoogleMap.addMarker(new MarkerOptions().position(localServices.latlng).icon(BitmapDescriptorFactory.fromResource(2130837724)));
              i += 1;
            }
            paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
            break;
          }
        }
      }
    });
  }
  
  private void openTimePicker()
  {
    final View localView = View.inflate(this, 2130968665, null);
    final AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localView.findViewById(2131624413).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = (DatePicker)localView.findViewById(2131624411);
        Object localObject1 = (TimePicker)localView.findViewById(2131624412);
        paramAnonymousView = new GregorianCalendar(paramAnonymousView.getYear(), paramAnonymousView.getMonth(), paramAnonymousView.getDayOfMonth(), ((TimePicker)localObject1).getCurrentHour().intValue(), ((TimePicker)localObject1).getCurrentMinute().intValue());
        localObject1 = new Date();
        Object localObject2 = Calendar.getInstance();
        ((Calendar)localObject2).setTime((Date)localObject1);
        ((Calendar)localObject2).clear(13);
        ((Calendar)localObject2).clear(14);
        localObject1 = ((Calendar)localObject2).getTime();
        localObject2 = paramAnonymousView.getTime();
        Services.access$302(Services.this, paramAnonymousView.getTimeInMillis());
        if ((((Date)localObject2).compareTo((Date)localObject1) == 0) || (((Date)localObject2).after((Date)localObject1)))
        {
          Services.this.mTVPickTime.setText(new SimpleDateFormat("hh:mm a, dd MMM", Locale.US).format((Date)localObject2));
          localAlertDialog.dismiss();
          return;
        }
        Toast.makeText(Services.this, Services.this.getString(2131165897), 0).show();
      }
    });
    localView.findViewById(2131624414).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localAlertDialog.dismiss();
      }
    });
    localAlertDialog.setView(localView);
    localAlertDialog.show();
  }
  
  private void renderView()
  {
    setContentView(2130968826);
    this.mSVServices = ((CustomScrollView)findViewById(2131624982));
    this.mTVPickLocationFrom = ((TextView)findViewById(2131624984));
    this.mLLContactInfoFrom = ((LinearLayout)findViewById(2131624988));
    this.mETContactNameFrom = ((EditText)findViewById(2131624989));
    this.mETContactPhoneFrom = ((EditText)findViewById(2131624990));
    this.mETLocationDetailFrom = ((EditText)findViewById(2131624985));
    this.mTVPickLocationTo = ((TextView)findViewById(2131624549));
    this.mLLContactInfoTo = ((LinearLayout)findViewById(2131624997));
    this.mETContactNameTo = ((EditText)findViewById(2131624998));
    this.mETContactPhoneTo = ((EditText)findViewById(2131624999));
    this.mETLocationDetailTo = ((EditText)findViewById(2131624550));
    this.mLLPickTime = ((LinearLayout)findViewById(2131624556));
    this.mTVPickTime = ((TextView)findViewById(2131624557));
    this.mTVDetails = ((TextView)findViewById(2131624551));
    this.mLLPrice = ((LinearLayout)findViewById(2131625003));
    this.mTVPrice = ((TextView)findViewById(2131624267));
    this.mETItemToDeliver = ((EditText)findViewById(2131625002));
    this.mETItemsToBuy = ((EditText)findViewById(2131624993));
    this.mETEstimatedCost = ((EditText)findViewById(2131624994));
    this.mTVShoopingFee = ((TextView)findViewById(2131625004));
    this.mRGPickup = ((RadioGroup)findViewById(2131624552));
    this.mRBPickupNow = ((RadioButton)findViewById(2131624553));
    this.mRBPickupLater = ((RadioButton)findViewById(2131624554));
    this.nearMeMap = ((MapView)findViewById(2131624827));
    findViewById(2131624554).setVisibility(8);
    findViewById(2131624555).setVisibility(8);
    findViewById(2131624556).setVisibility(8);
    buildGoogleApiClient();
  }
  
  private void setUpdateData()
  {
    Addresses localAddresses = (Addresses)this.mBookingData.addresses.get(0);
    this.mPoiFrom = new Poi();
    this.mPoiTo = new Poi();
    this.mPoiFrom.name = localAddresses.originName;
    this.mPoiFrom.address = localAddresses.originAddress;
    String[] arrayOfString = localAddresses.latLongOrigin.split(",");
    this.mPoiFrom.latitude = Double.parseDouble(arrayOfString[0]);
    this.mPoiFrom.longitude = Double.parseDouble(arrayOfString[1]);
    this.mPoiTo.name = localAddresses.destinationName;
    this.mPoiTo.address = localAddresses.destinationAddress;
    arrayOfString = localAddresses.latLongDestination.split(",");
    this.mPoiTo.latitude = Double.parseDouble(arrayOfString[0]);
    this.mPoiTo.longitude = Double.parseDouble(arrayOfString[1]);
    this.mTVPickLocationFrom.setText(localAddresses.originName);
    this.mETLocationDetailFrom.setText(localAddresses.originNote);
    this.mTVPickLocationTo.setText(localAddresses.destinationName);
    this.mETLocationDetailTo.setText(localAddresses.destinationNote);
    this.mTVPickTime.setText(Util.formatDateFromAPI(this.mBookingData.timeField, null));
    if (this.mBookingData.serviceType == 2)
    {
      this.mETContactNameFrom.setText(localAddresses.originContactName);
      this.mETContactPhoneFrom.setText(localAddresses.originContactPhone);
      this.mETContactNameTo.setText(localAddresses.destinationContactName);
      this.mETContactPhoneTo.setText(localAddresses.destinationContactPhone);
      this.mETItemToDeliver.setText(localAddresses.item);
    }
    if (this.mBookingData.serviceType == 3)
    {
      this.mETEstimatedCost.setText(String.valueOf(localAddresses.estimatedPrice));
      this.mETItemsToBuy.setText(localAddresses.item);
    }
    this.mLLPrice.setVisibility(0);
    findViewById(2131624983).setVisibility(0);
    doCalculate();
  }
  
  private void trackFromLocationDetails(String paramString)
  {
    for (;;)
    {
      try
      {
        if (this.fromLocationProperties == null) {
          return;
        }
        this.fromLocationDetailsProperties = new JSONObject();
        if ((this.fromLocationProperties.has("CurrentLocationSelected")) && (this.fromLocationProperties.getBoolean("CurrentLocationSelected")))
        {
          this.fromLocationDetailsProperties.put("CustomLocationSelected", false);
          this.fromLocationDetailsProperties.put("PastUsedLocationSelected", false);
          this.fromLocationDetailsProperties.put("CurrentLocationSelected", true);
          if (locDetailsFromConfirmation == null) {
            break;
          }
          if (!locDetailsFromConfirmation.equalsIgnoreCase(paramString)) {
            this.mixPanel.track("TR: From Location Details Entered", this.fromLocationDetailsProperties);
          }
          locDetailsFromConfirmation = null;
          return;
        }
        if ((this.fromLocationProperties.has("CustomLocationSelected")) && (this.fromLocationProperties.getBoolean("CustomLocationSelected")))
        {
          this.fromLocationDetailsProperties.put("CustomLocationSelected", true);
          this.fromLocationDetailsProperties.put("PastUsedLocationSelected", false);
          this.fromLocationDetailsProperties.put("CurrentLocationSelected", false);
          continue;
        }
        if (!this.fromLocationProperties.has("PastUsedLocationSelected")) {
          continue;
        }
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
        return;
      }
      if (this.fromLocationProperties.getBoolean("PastUsedLocationSelected")) {
        if (this.oldLocationDetails.equalsIgnoreCase(paramString))
        {
          this.fromLocationDetailsProperties.put("CurrentLocationSelected", false);
          this.fromLocationDetailsProperties.put("CustomLocationSelected", false);
          this.fromLocationDetailsProperties.put("PastUsedLocationSelected", true);
        }
        else
        {
          this.fromLocationDetailsProperties.put("CustomLocationSelected", true);
          this.fromLocationDetailsProperties.put("PastUsedLocationSelected", false);
          this.fromLocationDetailsProperties.put("CurrentLocationSelected", false);
        }
      }
    }
    this.mixPanel.track("TR: From Location Details Entered", this.fromLocationDetailsProperties);
  }
  
  private boolean validation()
  {
    boolean bool = true;
    StringBuilder localStringBuilder = new StringBuilder();
    if (TextUtils.isEmpty(this.mTVPickLocationFrom.getText().toString())) {
      localStringBuilder.append(getString(2131165563)).append("\n");
    }
    if (TextUtils.isEmpty(this.mTVPickLocationTo.getText().toString())) {
      localStringBuilder.append(getString(2131165556)).append("\n");
    }
    if ((this.mBookingData.serviceType == 2) && (TextUtils.isEmpty(this.mETItemToDeliver.getText().toString()))) {
      localStringBuilder.append(getString(2131165558)).append("\n");
    }
    if (this.mBookingData.serviceType == 3)
    {
      if (TextUtils.isEmpty(this.mETItemsToBuy.getText().toString())) {
        localStringBuilder.append("- " + getString(2131165619)).append("\n");
      }
      if (TextUtils.isEmpty(this.mETEstimatedCost.getText().toString())) {
        localStringBuilder.append("- " + getString(2131165519)).append("\n");
      }
    }
    if ((this.mDateSelected == 0L) && (this.mRBPickupLater.isChecked())) {
      localStringBuilder.append("- " + getString(2131165729)).append("\n");
    }
    if (!localStringBuilder.toString().equals(""))
    {
      showSimpleDialog(null, getString(2131165732) + "\n" + localStringBuilder.toString(), null, true, null);
      bool = false;
    }
    return bool;
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
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Object localObject4;
    Object localObject5;
    if (paramInt2 == -1)
    {
      if (paramInt1 != 11) {
        break label293;
      }
      this.mPoiFrom = new Poi(paramIntent.getIntExtra(PickLocation.LOCATION_ID, 0), paramIntent.getStringExtra(PickLocation.LOCATION_NAME), paramIntent.getDoubleExtra(PickLocation.LOCATION_LAT, 0.0D), paramIntent.getDoubleExtra(PickLocation.LOCATION_LNG, 0.0D), paramIntent.getStringExtra(PickLocation.LOCATION_ADDRESS));
      double d1 = paramIntent.getDoubleExtra(PickLocation.LOCATION_LAT, 0.0D);
      double d2 = paramIntent.getDoubleExtra(PickLocation.LOCATION_LNG, 0.0D);
      if ((d1 != this.lastMapPoint.latitude) && (d2 != this.lastMapPoint.longitude)) {
        loadOriginMap(d1, d2);
      }
      this.mTVPickLocationFrom.setText(this.mPoiFrom.name);
      localObject4 = paramIntent.getStringExtra(PickLocation.LOCATION_DESC);
      localObject5 = this.mETLocationDetailFrom;
      Object localObject1 = localObject4;
      if (localObject4 == null) {
        localObject1 = "";
      }
      ((EditText)localObject5).setText((CharSequence)localObject1);
      this.oldLocationDetails = this.mETLocationDetailFrom.getText().toString();
      if (this.mPoiTo != null)
      {
        this.mLLPrice.setVisibility(0);
        findViewById(2131624983).setVisibility(0);
        doCalculate();
      }
      this.fromLocationProperties = new JSONObject();
    }
    for (;;)
    {
      try
      {
        this.fromLocationProperties.put("CurrentLocationSelected", paramIntent.getBooleanExtra(PickLocation.CURRENT_LOCATION, false));
        this.fromLocationProperties.put("PastUsedLocationSelected", paramIntent.getBooleanExtra(PickLocation.PAST_LOCATION, false));
        this.fromLocationProperties.put("CustomLocationSelected", paramIntent.getBooleanExtra(PickLocation.CUSTOM_LOCATION, false));
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        return;
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
        continue;
      }
      label293:
      if (paramInt1 == 12)
      {
        this.mPoiTo = new Poi(paramIntent.getIntExtra(PickLocation.LOCATION_ID, 0), paramIntent.getStringExtra(PickLocation.LOCATION_NAME), paramIntent.getDoubleExtra(PickLocation.LOCATION_LAT, 0.0D), paramIntent.getDoubleExtra(PickLocation.LOCATION_LNG, 0.0D), paramIntent.getStringExtra(PickLocation.LOCATION_ADDRESS));
        this.mTVPickLocationTo.setText(this.mPoiTo.name);
        localObject4 = paramIntent.getStringExtra(PickLocation.LOCATION_DESC);
        localObject5 = this.mETLocationDetailTo;
        Object localObject2 = localObject4;
        if (localObject4 == null) {
          localObject2 = "";
        }
        ((EditText)localObject5).setText((CharSequence)localObject2);
        if (this.mPoiFrom != null)
        {
          this.mLLPrice.setVisibility(0);
          findViewById(2131624983).setVisibility(0);
          doCalculate();
        }
        this.toLocationProperties = new JSONObject();
        try
        {
          this.toLocationProperties.put("PastUsedLocationSelected", paramIntent.getBooleanExtra(PickLocation.PAST_LOCATION, false));
          this.toLocationProperties.put("CustomLocationSelected", paramIntent.getBooleanExtra(PickLocation.CUSTOM_LOCATION, false));
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
        }
      }
      else
      {
        Object localObject3;
        if (paramInt1 == 21)
        {
          localObject3 = paramIntent.getData();
          localObject4 = getContentResolver().query((Uri)localObject3, null, null, null, null);
          if (((Cursor)localObject4).moveToFirst())
          {
            localObject3 = ((Cursor)localObject4).getString(((Cursor)localObject4).getColumnIndex("_id"));
            localObject4 = ((Cursor)localObject4).getString(((Cursor)localObject4).getColumnIndex("display_name"));
            localObject5 = new Intent(this, ContactList.class);
            ((Intent)localObject5).putExtra(ContactList.ID, (String)localObject3);
            ((Intent)localObject5).putExtra(ContactList.NAME, (String)localObject4);
            startActivityForResult((Intent)localObject5, 31);
          }
        }
        else if (paramInt1 == 22)
        {
          localObject3 = paramIntent.getData();
          localObject4 = getContentResolver().query((Uri)localObject3, null, null, null, null);
          if (((Cursor)localObject4).moveToFirst())
          {
            localObject3 = ((Cursor)localObject4).getString(((Cursor)localObject4).getColumnIndex("_id"));
            localObject4 = ((Cursor)localObject4).getString(((Cursor)localObject4).getColumnIndex("display_name"));
            localObject5 = new Intent(this, ContactList.class);
            ((Intent)localObject5).putExtra(ContactList.ID, (String)localObject3);
            ((Intent)localObject5).putExtra(ContactList.NAME, (String)localObject4);
            startActivityForResult((Intent)localObject5, 32);
          }
        }
        else if (paramInt1 == 31)
        {
          localObject3 = paramIntent.getExtras().getString(ContactList.NUMBER);
          localObject4 = paramIntent.getExtras().getString(ContactList.NAME);
          if (localObject3 != null)
          {
            this.mETContactPhoneFrom.setText((CharSequence)localObject3);
            this.mETContactNameFrom.setText((CharSequence)localObject4);
          }
        }
        else if (paramInt1 == 32)
        {
          localObject3 = paramIntent.getExtras().getString(ContactList.NUMBER);
          localObject4 = paramIntent.getExtras().getString(ContactList.NAME);
          if (localObject3 != null)
          {
            this.mETContactPhoneTo.setText((CharSequence)localObject3);
            this.mETContactNameTo.setText((CharSequence)localObject4);
          }
        }
        else if (paramInt1 == 41)
        {
          finish();
        }
      }
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      do
      {
        do
        {
          return;
          paramView = new Intent(this, PickLocation.class);
          if (this.isLocationHistoryLoaded) {
            paramView.putExtra("CACHE_LOCATION_HISTORY", true);
          }
          startActivityForResult(paramView, 11);
        } while (this.isLocationHistoryLoaded);
        this.isLocationHistoryLoaded = true;
        return;
        paramView = new Intent(this, PickLocation.class);
        if (this.isLocationHistoryLoaded) {
          paramView.putExtra("CACHE_LOCATION_HISTORY", true);
        }
        startActivityForResult(paramView, 12);
      } while (this.isLocationHistoryLoaded);
      this.isLocationHistoryLoaded = true;
      return;
      openTimePicker();
      return;
      if (this.mLLContactInfoFrom.getVisibility() == 0)
      {
        this.mLLContactInfoFrom.setVisibility(8);
        return;
      }
      this.mLLContactInfoFrom.setVisibility(0);
      return;
      if (this.mLLContactInfoTo.getVisibility() == 0)
      {
        this.mLLContactInfoTo.setVisibility(8);
        return;
      }
      this.mLLContactInfoTo.setVisibility(0);
      return;
      startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 21);
      return;
      startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 22);
      return;
      if (this.mCustomer.customerId == null)
      {
        startActivity(new Intent(this, SignUp.class));
        return;
      }
    } while (!validation());
    if (this.routeResponse == null)
    {
      doCalculate();
      return;
    }
    Object localObject2 = this.mTVPickLocationFrom.getText().toString();
    String str3 = this.mETLocationDetailFrom.getText().toString();
    String str4 = this.mETContactNameFrom.getText().toString();
    String str5 = this.mETContactPhoneFrom.getText().toString();
    if (!str3.trim().isEmpty()) {
      trackFromLocationDetails(str3);
    }
    String str6 = this.mTVPickLocationTo.getText().toString();
    paramView = this.mETLocationDetailTo.getText().toString();
    String str7 = this.mETContactNameTo.getText().toString();
    String str8 = this.mETContactPhoneTo.getText().toString();
    Object localObject1 = this.mETItemToDeliver.getText().toString();
    String str2 = this.mETItemsToBuy.getText().toString();
    String str1 = this.mETEstimatedCost.getText().toString().replace(",", "").replace(".", "");
    if (this.mBookingData.serviceType == 1) {
      paramView = "";
    }
    if (this.mFlag != 24)
    {
      Addresses localAddresses = new Addresses();
      this.mBookingData.addresses.add(localAddresses);
    }
    ((Addresses)this.mBookingData.addresses.get(0)).originNote = str3;
    ((Addresses)this.mBookingData.addresses.get(0)).originName = ((String)localObject2);
    ((Addresses)this.mBookingData.addresses.get(0)).originContactName = str4;
    ((Addresses)this.mBookingData.addresses.get(0)).originContactPhone = str5;
    ((Addresses)this.mBookingData.addresses.get(0)).destinationNote = paramView;
    ((Addresses)this.mBookingData.addresses.get(0)).destinationName = str6;
    ((Addresses)this.mBookingData.addresses.get(0)).destinationContactName = str7;
    ((Addresses)this.mBookingData.addresses.get(0)).destinationContactPhone = str8;
    ((Addresses)this.mBookingData.addresses.get(0)).latLongOrigin = (this.mPoiFrom.latitude + "," + this.mPoiFrom.longitude);
    ((Addresses)this.mBookingData.addresses.get(0)).latLongDestination = (this.mPoiTo.latitude + "," + this.mPoiTo.longitude);
    localObject2 = (Addresses)this.mBookingData.addresses.get(0);
    if (TextUtils.isEmpty((CharSequence)localObject1)) {}
    for (paramView = str2;; paramView = (View)localObject1)
    {
      ((Addresses)localObject2).item = paramView;
      localObject1 = (Addresses)this.mBookingData.addresses.get(0);
      paramView = str1;
      if (TextUtils.isEmpty(str1)) {
        paramView = "0";
      }
      ((Addresses)localObject1).estimatedPrice = Long.parseLong(paramView);
      FlurryAgent.logEvent("Next_Clicked");
      this.mixPanel.track("TR: Booking Confirmed");
      if (this.fromLocationProperties != null) {
        this.mixPanel.track("TR: From Location Selected", this.fromLocationProperties);
      }
      if (this.toLocationProperties != null) {
        this.mixPanel.track("TR: To Location Selected", this.toLocationProperties);
      }
      paramView = new Intent(this, BookingConfirmation.class);
      paramView.putExtra("BOOKING_DATA", this.mBookingData);
      paramView.putExtra("PRICE", ((Route)this.routeResponse.routes.get(0)).price);
      paramView.putExtra("VOUCHER", this.routeResponse.shoppingFee);
      paramView.putExtra("FREE_DELIVERY", this.routeResponse.isFree);
      paramView.putExtra("FLAG", this.mFlag);
      startActivity(paramView);
      return;
    }
  }
  
  public void onConnected(Bundle paramBundle)
  {
    try
    {
      this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
      if (!this.firstLoadMap)
      {
        loadOriginMap(this.mLastLocation.getLatitude(), this.mLastLocation.getLongitude());
        this.firstLoadMap = true;
      }
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
    renderView();
    this.nearMeMap.onCreate(paramBundle);
    init();
  }
  
  public void onDestroy()
  {
    this.nearMeMap.onDestroy();
    super.onDestroy();
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    this.mLastLocation = paramLocation;
  }
  
  public void onLowMemory()
  {
    this.nearMeMap.onLowMemory();
    super.onLowMemory();
  }
  
  protected void onPause()
  {
    this.nearMeMap.onPause();
    super.onPause();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  public void onResume()
  {
    this.mCustomer = new CustomerSaved(this);
    this.nearMeMap.onResume();
    super.onResume();
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
  
  private class NumberTextWatcher
    implements TextWatcher
  {
    private static final String TAG = "NumberTextWatcher";
    private DecimalFormat df = new DecimalFormat("#,###.##");
    private DecimalFormat dfnd;
    private EditText et;
    private boolean hasFractionalPart;
    
    public NumberTextWatcher(EditText paramEditText)
    {
      this.df.setDecimalSeparatorAlwaysShown(true);
      this.dfnd = new DecimalFormat("#,###");
      this.et = paramEditText;
      this.hasFractionalPart = false;
    }
    
    public void afterTextChanged(Editable paramEditable)
    {
      this.et.removeTextChangedListener(this);
      for (;;)
      {
        try
        {
          int i = this.et.getText().length();
          paramEditable = paramEditable.toString().replace(String.valueOf(this.df.getDecimalFormatSymbols().getGroupingSeparator()), "");
          paramEditable = this.df.parse(paramEditable);
          int j = this.et.getSelectionStart();
          if (!this.hasFractionalPart) {
            continue;
          }
          this.et.setText(this.df.format(paramEditable));
          i = j + (this.et.getText().length() - i);
          if ((i <= 0) || (i > this.et.getText().length())) {
            continue;
          }
          this.et.setSelection(i);
        }
        catch (ParseException paramEditable)
        {
          continue;
        }
        catch (NumberFormatException paramEditable)
        {
          continue;
        }
        this.et.addTextChangedListener(this);
        return;
        this.et.setText(this.dfnd.format(paramEditable));
        continue;
        this.et.setSelection(this.et.getText().length() - 1);
      }
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramCharSequence.toString().contains(String.valueOf(this.df.getDecimalFormatSymbols().getDecimalSeparator())))
      {
        this.hasFractionalPart = true;
        return;
      }
      this.hasFractionalPart = false;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/Services.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */