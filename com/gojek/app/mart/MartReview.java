package com.gojek.app.mart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.flurry.android.FlurryAgent;
import com.gojek.app.BookingConfirmation;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.PickLocation;
import com.gojek.app.SignUp;
import com.gojek.app.custom.CustomScrollView;
import com.gojek.app.custom.FoodMenuItemRender;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Martlocation;
import com.gojek.app.model.Poi;
import com.gojek.app.model.Route;
import com.gojek.app.model.RouteResponse;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MartReview
  extends GojekActivityBase
  implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{
  public static final String TAG = MartReview.class.getSimpleName();
  private final int REQUEST_ITEMS = 13;
  private final int REQUEST_TO = 12;
  private Context context = this;
  private LatLng destLocation;
  private FoodMenuItemRender foodMenuItemRender;
  private FoodMenuItemRender foodOtherMenuItemRender;
  private Gson gson = new Gson();
  private boolean isChooseFromMap = false;
  private boolean isFromHistory = false;
  private boolean isLocationFromLoaded = false;
  private boolean isLocationHistoryLoaded = false;
  private boolean isLogon;
  private ArrayList<RouteItem> itemList;
  private LinearLayout linFrom;
  private List<MartMerchant> listMartAsal;
  private BookingStatus mBookingData;
  private CustomerSaved mCustomer;
  private long mDateSelected;
  private EditText mETLocationDetailTo;
  protected GoogleApiClient mGoogleApiClient;
  private GoogleMap mGoogleMap;
  private ImageView mIVNextBtn;
  private LinearLayout mLLAddOtherItem;
  private LinearLayout mLLBackToMart;
  private LinearLayout mLLMapView;
  private LinearLayout mLLNoItem;
  private LinearLayout mLLPickLocationFrom;
  private LinearLayout mLLPickLocationOutofService;
  private LinearLayout mLLPickLocationTo;
  private ListView mLVReviewOrder;
  private Location mLastLocation;
  private Poi mPoiTo = new Poi();
  private ProgressBar mProgressDeliveryCost;
  private RelativeLayout mRLDeliveryCost;
  private RelativeLayout mRLFreeDeliveryTitle;
  private CustomScrollView mSVReview;
  private TextView mTVDeliveryCost;
  private TextView mTVFoodCost;
  private TextView mTVFoodTotalCost;
  private TextView mTVFreeDeliveryTitle;
  private TextView mTVOriginAddress;
  private TextView mTVOutofService;
  private TextView mTVPickLocationTo;
  private View mVPriceDeliveryFreeLine;
  private Map<Integer, RouteItem> mapOtherRouteItems;
  private Map<Integer, RouteItem> mapRouteItems;
  private MartMerchant martMerchant;
  private Map<String, MartMerchant> merchantMap;
  private MapView nearMeMap;
  private ArrayList<RouteItem> otherRouteItems;
  private RouteResponse routeResponse;
  private LinearLayout rowLayout;
  private LinearLayout rowOtherItemLayout;
  private List<RouteItem> saveFoodItems;
  private MartMerchant[] storeList;
  private long tempDeliv;
  private int tempFoodCost;
  private int tempQuantity;
  JSONObject toLocationProperties;
  private VolleyClient volleyClient;
  
  private void calculateBookingPrice()
  {
    int i = 0;
    Iterator localIterator = this.itemList.iterator();
    RouteItem localRouteItem;
    while (localIterator.hasNext())
    {
      localRouteItem = (RouteItem)localIterator.next();
      i += localRouteItem.getPrice() * localRouteItem.getQuantity();
    }
    this.tempFoodCost = i;
    this.tempQuantity = this.itemList.size();
    i = 0;
    if (this.otherRouteItems != null)
    {
      localIterator = this.otherRouteItems.iterator();
      while (localIterator.hasNext())
      {
        localRouteItem = (RouteItem)localIterator.next();
        i += localRouteItem.getPrice() * localRouteItem.getQuantity();
      }
      this.tempFoodCost += i;
      this.tempQuantity += this.otherRouteItems.size();
    }
    long l = this.tempFoodCost + this.tempDeliv;
    this.mTVFoodCost.setText(Util.getRupiahFormat(String.valueOf(this.tempFoodCost)));
    this.mTVFoodTotalCost.setText(Util.getRupiahFormat(String.valueOf(l)));
    Log.i(TAG, "booking total price " + l);
  }
  
  private List<RouteItem> concatRouteItem()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.mapRouteItems.keySet().iterator();
    Integer localInteger;
    while (localIterator.hasNext())
    {
      localInteger = (Integer)localIterator.next();
      if (((RouteItem)this.mapRouteItems.get(localInteger)).getQuantity() > 0) {
        localLinkedList.add(this.mapRouteItems.get(localInteger));
      }
    }
    localIterator = this.mapOtherRouteItems.keySet().iterator();
    while (localIterator.hasNext())
    {
      localInteger = (Integer)localIterator.next();
      if (((RouteItem)this.mapOtherRouteItems.get(localInteger)).getQuantity() > 0)
      {
        ((RouteItem)this.mapOtherRouteItems.get(localInteger)).setItemId(0);
        localLinkedList.add(this.mapOtherRouteItems.get(localInteger));
      }
    }
    return localLinkedList;
  }
  
  private void doCalculate(final Handler... paramVarArgs)
  {
    localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.putOpt("serviceType", Integer.valueOf(6));
        localJSONObject.putOpt("customerId", this.mCustomer.customerId);
        localJSONObject.putOpt("corporateId", "");
        localJSONObject.putOpt("marketplaceType", "");
        localJSONObject.putOpt("region", "");
        localJSONObject.putOpt("paymentType", "0");
        localObject1 = new JSONArray();
        localObject2 = new JSONObject();
        ((JSONObject)localObject2).putOpt("originLatLong", this.martMerchant.latlong);
        ((JSONObject)localObject2).putOpt("destinationLatLong", this.mPoiTo.latitude + "," + this.mPoiTo.longitude);
        ((JSONObject)localObject2).putOpt("serviceType", Integer.valueOf(6));
        ((JSONArray)localObject1).put(localObject2);
        localJSONObject.putOpt("routeRequests", localObject1);
        localObject2 = TAG;
        localStringBuilder = new StringBuilder().append("request calculate ");
        if ((localJSONObject instanceof JSONObject)) {
          continue;
        }
        localObject1 = localJSONObject.toString();
        Log.i((String)localObject2, (String)localObject1);
      }
      catch (JSONException localJSONException)
      {
        Object localObject1;
        Object localObject2;
        StringBuilder localStringBuilder;
        localJSONException.printStackTrace();
        continue;
        String str = JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
        continue;
      }
      this.mTVDeliveryCost.setText("");
      this.mProgressDeliveryCost.setVisibility(0);
      localObject2 = TAG;
      localStringBuilder = new StringBuilder().append("request calculate Mart Review ");
      if ((localJSONObject instanceof JSONObject)) {
        continue;
      }
      localObject1 = localJSONObject.toString();
      Log.i((String)localObject2, (String)localObject1);
      VolleyClient.getInstance(this).post("https://api.gojek.co.id/gojek/v2/calculate/", localJSONObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          MartReview.this.mTVDeliveryCost.setText(MartReview.this.getString(2131165537));
          Log.e(MartReview.TAG, "doCalculateError " + paramAnonymousVolleyError);
          MartReview.this.mProgressDeliveryCost.setVisibility(8);
        }
        
        public void onResponse(RouteResponse paramAnonymousRouteResponse)
        {
          MartReview.access$1002(MartReview.this, paramAnonymousRouteResponse);
          if ((MartReview.this.routeResponse.getRoutes() != null) && (MartReview.this.routeResponse.getRoutes().size() > 0))
          {
            Log.i(MartReview.TAG, "response routes " + MartReview.this.routeResponse.toString());
            paramAnonymousRouteResponse = (Route)MartReview.this.routeResponse.routes.get(0);
          }
          try
          {
            Object localObject = Util.getRupiahFormat(String.valueOf(paramAnonymousRouteResponse.price));
            MartReview.this.mTVDeliveryCost.setText((CharSequence)localObject);
            localObject = new HashMap();
            ((Map)localObject).put("price", String.valueOf(paramAnonymousRouteResponse.price));
            FlurryAgent.logEvent("Price_Check", (Map)localObject);
            MartReview.access$1602(MartReview.this, paramAnonymousRouteResponse.price);
            long l = paramAnonymousRouteResponse.price + MartReview.this.tempFoodCost;
            if (MartReview.this.routeResponse.isFree)
            {
              MartReview.this.enabledDeliveryFreeLine();
              l = MartReview.this.tempFoodCost;
            }
            MartReview.this.mTVFoodTotalCost.setText(Util.getRupiahFormat(String.valueOf(l)));
            if (paramVarArgs.length > 0) {
              paramVarArgs[0].sendEmptyMessage(0);
            }
          }
          catch (Exception paramAnonymousRouteResponse)
          {
            for (;;) {}
          }
          MartReview.this.mProgressDeliveryCost.setVisibility(8);
        }
      }, RouteResponse.class);
      return;
      localObject1 = JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
    }
  }
  
  private void enabledDeliveryFreeLine()
  {
    this.mRLDeliveryCost.post(new Runnable()
    {
      public void run()
      {
        MartReview.this.mVPriceDeliveryFreeLine.setVisibility(0);
        int i = MartReview.this.mTVDeliveryCost.getMeasuredWidth();
        ViewGroup.LayoutParams localLayoutParams = MartReview.this.mVPriceDeliveryFreeLine.getLayoutParams();
        localLayoutParams.width = i;
        MartReview.this.mVPriceDeliveryFreeLine.setLayoutParams(localLayoutParams);
        MartReview.this.mRLFreeDeliveryTitle.setVisibility(0);
        MartReview.this.mTVFreeDeliveryTitle.setText(Html.fromHtml(MartReview.this.getString(2131165574)));
      }
    });
  }
  
  private void init()
  {
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.martMerchant = ((Addresses)this.mBookingData.addresses.get(0)).martMerchant;
    setTitle(this.martMerchant.martMerchantName);
    this.isFromHistory = getIntent().getBooleanExtra("isFromHistory", false);
    this.mCustomer = new CustomerSaved(this);
    this.mSVReview.addInterceptScrollView(this.nearMeMap);
    this.volleyClient = VolleyClient.getInstance(this);
    this.merchantMap = new TreeMap();
    if (this.mCustomer.customerId != null) {
      this.isLogon = true;
    }
    Object localObject = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
    this.itemList = new ArrayList((Collection)localObject);
    this.mapRouteItems = listToMap((List)localObject);
    this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    if (this.otherRouteItems == null) {
      this.otherRouteItems = new ArrayList();
    }
    if (this.otherRouteItems != null) {}
    for (this.mapOtherRouteItems = listToMap(this.otherRouteItems);; this.mapOtherRouteItems = new LinkedHashMap())
    {
      this.tempFoodCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
      this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
      this.tempDeliv = 0L;
      setFoodCost();
      setNoitem();
      this.foodMenuItemRender = new FoodMenuItemRender(this, this.itemList)
      {
        public void onChangeFoodItemNote(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.updateItemNote(paramAnonymousRouteItem);
        }
        
        public void onMinusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.minusItemQuantity(paramAnonymousRouteItem);
        }
        
        public void onPlusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.plusItemQuantity(paramAnonymousRouteItem);
        }
      };
      this.foodMenuItemRender.renderView(this.rowLayout);
      this.foodOtherMenuItemRender = new FoodMenuItemRender(this, this.otherRouteItems)
      {
        public void onChangeFoodItemNote(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.updateOtherItemNote(paramAnonymousRouteItem);
        }
        
        public void onMinusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.minusOtherItemQuantity(paramAnonymousRouteItem);
        }
        
        public void onPlusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.plusOtherItemQuantity(paramAnonymousRouteItem);
        }
      };
      this.foodOtherMenuItemRender.renderView(this.rowOtherItemLayout);
      calculateBookingPrice();
      this.mLLPickLocationTo.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(MartReview.this.getApplicationContext(), PickLocation.class);
          if (MartReview.this.isLocationHistoryLoaded) {
            paramAnonymousView.putExtra("CACHE_LOCATION_HISTORY", true);
          }
          MartReview.this.startActivityForResult(paramAnonymousView, 12);
          if (!MartReview.this.isLocationHistoryLoaded) {
            MartReview.access$402(MartReview.this, true);
          }
        }
      });
      this.mIVNextBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (MartReview.this.mCustomer.customerId == null)
          {
            paramAnonymousView = new Intent(MartReview.this, SignUp.class);
            MartReview.this.startActivity(paramAnonymousView);
          }
          do
          {
            return;
            MartReview.this.validateFoodItemList();
          } while ((!MartReview.this.validate()) || (!MartReview.this.isLocationFromLoaded));
          boolean bool = ((MartMerchant)MartReview.this.listMartAsal.get(0)).isOpen;
          Log.wtf("", "isOpen = " + bool);
          if (bool)
          {
            if (MartReview.this.routeResponse == null)
            {
              MartReview.this.doCalculate(new Handler[0]);
              return;
            }
            if (!MartReview.this.isLogon)
            {
              MartReview.this.doCalculate(new Handler[] { new MartReview.4.1(this) });
              return;
            }
            MartReview.this.sendBookingData();
            return;
          }
          paramAnonymousView = new AlertDialog.Builder(MartReview.this.context);
          paramAnonymousView.setMessage("Sorry, All store is closed").setCancelable(false).setPositiveButton("Yes", new MartReview.4.2(this));
          paramAnonymousView.create().show();
        }
      });
      if ((((Addresses)this.mBookingData.addresses.get(0)).latLongOrigin != null) && (((Addresses)this.mBookingData.addresses.get(0)).latLongDestination != null))
      {
        this.mTVPickLocationTo.setText("");
        this.mPoiTo.id = ((Addresses)this.mBookingData.addresses.get(0)).destinationPoiId;
        this.mPoiTo.name = ((Addresses)this.mBookingData.addresses.get(0)).destinationName;
        this.mPoiTo.address = ((Addresses)this.mBookingData.addresses.get(0)).destinationAddress;
        localObject = Util.getLatLong(((Addresses)this.mBookingData.addresses.get(0)).latLongDestination);
        if (localObject.length == 2)
        {
          this.mPoiTo.latitude = Double.parseDouble(localObject[0]);
          this.mPoiTo.longitude = Double.parseDouble(localObject[1]);
        }
        this.mTVOriginAddress.setVisibility(8);
        this.linFrom.setVisibility(8);
        this.mLLPickLocationFrom.setVisibility(8);
        this.mLLPickLocationOutofService.setVisibility(8);
      }
      this.mLLMapView.setVisibility(8);
      initializeMap();
      if (this.isFromHistory) {
        this.mLLBackToMart.setVisibility(0);
      }
      return;
    }
  }
  
  private void initView()
  {
    this.mTVFoodCost = ((TextView)findViewById(2131624538));
    this.mTVDeliveryCost = ((TextView)findViewById(2131624540));
    this.mTVFoodTotalCost = ((TextView)findViewById(2131624544));
    this.mTVOriginAddress = ((TextView)findViewById(2131624828));
    this.mTVOutofService = ((TextView)findViewById(2131624830));
    this.linFrom = ((LinearLayout)findViewById(2131624826));
    this.mLLPickLocationFrom = ((LinearLayout)findViewById(2131624264));
    this.mLVReviewOrder = ((ListView)findViewById(2131624545));
    this.mLLPickLocationTo = ((LinearLayout)findViewById(2131624548));
    this.mLLPickLocationOutofService = ((LinearLayout)findViewById(2131624829));
    this.mLLNoItem = ((LinearLayout)findViewById(2131624791));
    this.mLLAddOtherItem = ((LinearLayout)findViewById(2131624824));
    this.mLLBackToMart = ((LinearLayout)findViewById(2131624825));
    this.mLLMapView = ((LinearLayout)findViewById(2131624774));
    this.mTVPickLocationTo = ((TextView)findViewById(2131624549));
    this.mETLocationDetailTo = ((EditText)findViewById(2131624550));
    this.mIVNextBtn = ((ImageView)findViewById(2131624558));
    this.mProgressDeliveryCost = ((ProgressBar)findViewById(2131624541));
    this.rowLayout = ((LinearLayout)findViewById(2131624546));
    this.rowOtherItemLayout = ((LinearLayout)findViewById(2131624547));
    this.mVPriceDeliveryFreeLine = findViewById(2131624268);
    this.mRLDeliveryCost = ((RelativeLayout)findViewById(2131624539));
    this.mRLFreeDeliveryTitle = ((RelativeLayout)findViewById(2131624542));
    this.mTVFreeDeliveryTitle = ((TextView)findViewById(2131624543));
    this.mSVReview = ((CustomScrollView)findViewById(2131624821));
    this.nearMeMap = ((MapView)findViewById(2131624827));
    this.mLLBackToMart.setVisibility(8);
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
  
  private Map<Integer, RouteItem> listToMap(List<RouteItem> paramList)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)paramList.next();
      localLinkedHashMap.put(Integer.valueOf(localRouteItem.itemId), localRouteItem);
    }
    return localLinkedHashMap;
  }
  
  private List<RouteItem> mapToList(Map<Integer, RouteItem> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    paramMap = paramMap.values().iterator();
    while (paramMap.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)paramMap.next();
      if (localRouteItem.getQuantity() > 0) {
        localArrayList.add(localRouteItem);
      }
    }
    return localArrayList;
  }
  
  private void minusItemQuantity(RouteItem paramRouteItem)
  {
    if (this.mapRouteItems.containsKey(Integer.valueOf(paramRouteItem.itemId)))
    {
      if (paramRouteItem.quantity == 0) {
        break label57;
      }
      ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramRouteItem.itemId))).quantity = paramRouteItem.quantity;
    }
    for (;;)
    {
      calculateBookingPrice();
      return;
      label57:
      this.mapRouteItems.remove(Integer.valueOf(paramRouteItem.itemId));
      int j = -1;
      int i = 0;
      while (i < this.itemList.size())
      {
        if (((RouteItem)this.itemList.get(i)).getItemName() == paramRouteItem.getItemName()) {
          j = i;
        }
        i += 1;
      }
      this.itemList.remove(j);
      this.rowLayout.removeAllViews();
      this.foodMenuItemRender = new FoodMenuItemRender(this, this.itemList)
      {
        public void onChangeFoodItemNote(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.updateItemNote(paramAnonymousRouteItem);
        }
        
        public void onMinusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.minusItemQuantity(paramAnonymousRouteItem);
        }
        
        public void onPlusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.plusItemQuantity(paramAnonymousRouteItem);
        }
      };
      setNoitem();
      this.foodMenuItemRender.renderView(this.rowLayout);
    }
  }
  
  private void minusOtherItemQuantity(RouteItem paramRouteItem)
  {
    if (this.mapOtherRouteItems.containsKey(Integer.valueOf(paramRouteItem.itemId)))
    {
      if (paramRouteItem.quantity == 0) {
        break label57;
      }
      ((RouteItem)this.mapOtherRouteItems.get(Integer.valueOf(paramRouteItem.itemId))).quantity = paramRouteItem.quantity;
    }
    for (;;)
    {
      calculateBookingPrice();
      return;
      label57:
      this.mapOtherRouteItems.remove(Integer.valueOf(paramRouteItem.itemId));
      int j = -1;
      int i = 0;
      while (i < this.otherRouteItems.size())
      {
        if (((RouteItem)this.otherRouteItems.get(i)).getItemName() == paramRouteItem.getItemName()) {
          j = i;
        }
        i += 1;
      }
      this.otherRouteItems.remove(j);
      this.rowOtherItemLayout.removeAllViews();
      this.foodOtherMenuItemRender = new FoodMenuItemRender(this, this.otherRouteItems)
      {
        public void onChangeFoodItemNote(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.updateOtherItemNote(paramAnonymousRouteItem);
        }
        
        public void onMinusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.minusOtherItemQuantity(paramAnonymousRouteItem);
        }
        
        public void onPlusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          MartReview.this.plusOtherItemQuantity(paramAnonymousRouteItem);
        }
      };
      setNoitem();
      this.foodOtherMenuItemRender.renderView(this.rowOtherItemLayout);
    }
  }
  
  private void sendBookingData()
  {
    Object localObject = this.mTVPickLocationTo.getText().toString();
    String str = this.mETLocationDetailTo.getText().toString();
    ((Addresses)this.mBookingData.addresses.get(0)).originName = this.martMerchant.martMerchantName;
    ((Addresses)this.mBookingData.addresses.get(0)).originAddress = this.martMerchant.address;
    ((Addresses)this.mBookingData.addresses.get(0)).latLongOrigin = this.martMerchant.latlong;
    ((Addresses)this.mBookingData.addresses.get(0)).item = "";
    ((Addresses)this.mBookingData.addresses.get(0)).originContactName = "";
    ((Addresses)this.mBookingData.addresses.get(0)).originContactPhone = "";
    ((Addresses)this.mBookingData.addresses.get(0)).destinationContactName = "";
    ((Addresses)this.mBookingData.addresses.get(0)).destinationContactPhone = "";
    ((Addresses)this.mBookingData.addresses.get(0)).destinationNote = str;
    ((Addresses)this.mBookingData.addresses.get(0)).destinationName = ((String)localObject);
    ((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice = this.tempFoodCost;
    if (this.mDateSelected > 0L) {
      this.mBookingData.timeField = Util.formatTimeMiles(this.mDateSelected);
    }
    if (!this.isFromHistory) {
      ((Addresses)this.mBookingData.addresses.get(0)).routeItems = concatRouteItem();
    }
    FlurryAgent.logEvent("Next_Clicked");
    if (!this.mETLocationDetailTo.getText().toString().trim().isEmpty()) {
      this.mixPanel.track("TR: From Location Details Entered");
    }
    this.mixPanel.track("TR: Booking Confirmed");
    if (this.toLocationProperties != null) {
      this.mixPanel.track("TR: To Location Selected", this.toLocationProperties);
    }
    localObject = new Intent(getApplicationContext(), BookingConfirmation.class);
    ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingData);
    ((Intent)localObject).putExtra("PRICE", ((Route)this.routeResponse.routes.get(0)).price);
    ((Intent)localObject).putExtra("VOUCHER", this.routeResponse.shoppingFee);
    ((Intent)localObject).putExtra("FREE_DELIVERY", this.routeResponse.isFree);
    startActivity((Intent)localObject);
  }
  
  private void setFoodCost()
  {
    this.mTVFoodCost.setText(Util.getRupiahFormat(this.tempFoodCost + ""));
    this.mTVFoodTotalCost.setText(Util.getRupiahFormat(String.valueOf(this.tempFoodCost + this.tempDeliv)));
  }
  
  private void setNoitem()
  {
    if ((this.itemList.size() == 0) && (this.otherRouteItems.size() == 0))
    {
      this.mLLNoItem.setVisibility(0);
      this.mLLAddOtherItem.setVisibility(8);
      return;
    }
    this.mLLNoItem.setVisibility(8);
    this.mLLAddOtherItem.setVisibility(0);
  }
  
  private void updateItemNote(RouteItem paramRouteItem)
  {
    if ((this.mapRouteItems.containsKey(Integer.valueOf(paramRouteItem.itemId))) && (Util.isTextNotNullEmpty(paramRouteItem.notes))) {
      ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramRouteItem.itemId))).notes = paramRouteItem.notes;
    }
  }
  
  private void updateOtherItemNote(RouteItem paramRouteItem)
  {
    if ((this.mapRouteItems.containsKey(Integer.valueOf(paramRouteItem.itemId))) && (Util.isTextNotNullEmpty(paramRouteItem.notes))) {
      ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramRouteItem.itemId))).notes = paramRouteItem.notes;
    }
  }
  
  private boolean validate()
  {
    boolean bool = true;
    StringBuilder localStringBuilder = new StringBuilder();
    if (TextUtils.isEmpty(this.mTVPickLocationTo.getText().toString())) {
      localStringBuilder.append(getString(2131165556)).append("\n");
    }
    if ((this.mapRouteItems.isEmpty()) && (this.mapOtherRouteItems.isEmpty())) {
      localStringBuilder.append("- You have no item").append("\n");
    }
    if (!localStringBuilder.toString().equals(""))
    {
      showSimpleDialog(null, getString(2131165732) + "\n" + localStringBuilder.toString(), null, true, null);
      bool = false;
    }
    return bool;
  }
  
  private void validateFoodItemList()
  {
    if (this.saveFoodItems == null) {
      this.saveFoodItems = new ArrayList();
    }
    for (;;)
    {
      Iterator localIterator = this.itemList.iterator();
      while (localIterator.hasNext())
      {
        RouteItem localRouteItem = (RouteItem)localIterator.next();
        if (localRouteItem.quantity != 0) {
          this.saveFoodItems.add(localRouteItem);
        }
      }
      this.saveFoodItems.clear();
    }
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
  
  public void doRefreshOrigin(String paramString, int paramInt)
  {
    paramString = String.format("https://api.gojek.co.id/gojek/mart-merchant/find?location=%1s&martId=%d", new Object[] { paramString, Integer.valueOf(paramInt) });
    Log.wtf("", " url " + paramString);
    VolleyClient.getInstance(this).getList(TAG, paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MartReview.TAG, "refresh Origin get error " + paramAnonymousVolleyError);
        MartReview.this.mLLPickLocationOutofService.setVisibility(0);
        MartReview.this.mTVOutofService.setVisibility(0);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Log.wtf(MartReview.TAG, "respon mart asal = " + paramAnonymousJSONArray + "\n");
        Gson localGson = new Gson();
        MartReview localMartReview = MartReview.this;
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new MartReview.9.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label386;
          }
        }
        label386:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          MartReview.access$902(localMartReview, (List)paramAnonymousJSONArray);
          if ((MartReview.this.listMartAsal != null) && (MartReview.this.listMartAsal.size() > 0))
          {
            MartReview.this.mTVOriginAddress.setVisibility(0);
            MartReview.this.linFrom.setVisibility(0);
            MartReview.access$802(MartReview.this, true);
            MartReview.this.mLLMapView.setVisibility(0);
            paramAnonymousJSONArray = new LatLng(MartReview.this.mPoiTo.latitude, MartReview.this.mPoiTo.longitude);
            MartReview.access$2802(MartReview.this, new LatLng(MartReview.this.mPoiTo.latitude, MartReview.this.mPoiTo.longitude));
            MartReview.this.loadMap(paramAnonymousJSONArray);
            MartReview.this.martMerchant.latlong = ((MartMerchant)MartReview.this.listMartAsal.get(0)).getLatLong();
            paramAnonymousJSONArray = ((MartMerchant)MartReview.this.listMartAsal.get(0)).detailAddress.replaceAll("\n", " ").replaceAll(",", " ");
            MartReview.this.mTVOriginAddress.setText(((MartMerchant)MartReview.this.listMartAsal.get(0)).martName + "\n" + paramAnonymousJSONArray);
            if (!MartReview.this.mBookingData.addresses.isEmpty()) {
              MartReview.this.doCalculate(new Handler[0]);
            }
          }
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
      }
    });
  }
  
  public void loadMap(final LatLng paramLatLng)
  {
    try
    {
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paramLatLng, 15.0F));
      this.mGoogleMap.clear();
      this.merchantMap.clear();
      Object localObject = this.mGoogleMap.getCameraPosition();
      localObject = String.format("https://api.gojek.co.id/gojek/mart-merchant/findNearestMerchant?location=%s&martId=%d&limit=10", new Object[] { ((CameraPosition)localObject).target.latitude + "," + ((CameraPosition)localObject).target.longitude, Integer.valueOf(((Addresses)this.mBookingData.addresses.get(0)).martMerchant.martId) });
      this.volleyClient.getList((String)localObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(MartReview.TAG, "error get driver nearMe " + paramAnonymousVolleyError);
        }
        
        public void onResponse(JSONArray paramAnonymousJSONArray)
        {
          MartReview localMartReview;
          Gson localGson;
          if (paramAnonymousJSONArray != null)
          {
            localMartReview = MartReview.this;
            localGson = MartReview.this.gson;
            if ((paramAnonymousJSONArray instanceof JSONArray)) {
              break label65;
            }
            paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
            if ((localGson instanceof Gson)) {
              break label76;
            }
          }
          label65:
          label76:
          for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, MartMerchant[].class);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, MartMerchant[].class))
          {
            MartReview.access$3302(localMartReview, (MartMerchant[])paramAnonymousJSONArray);
            MartReview.this.loadMarker(paramLatLng);
            return;
            paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
            break;
          }
        }
      });
      return;
    }
    catch (Exception paramLatLng)
    {
      paramLatLng.printStackTrace();
    }
  }
  
  public void loadMarker(final LatLng paramLatLng)
  {
    this.mGoogleMap.clear();
    this.mGoogleMap.addMarker(new MarkerOptions().position(paramLatLng).icon(BitmapDescriptorFactory.fromResource(2130838130)).snippet("000"));
    int j = 0;
    Boolean localBoolean = Boolean.valueOf(false);
    MartMerchant[] arrayOfMartMerchant = this.storeList;
    int k = arrayOfMartMerchant.length;
    int i = 0;
    if (i < k)
    {
      MartMerchant localMartMerchant = arrayOfMartMerchant[i];
      Object localObject = new LatLng(localMartMerchant.location.lat, localMartMerchant.location.lon);
      if ((j == 0) && (!this.isChooseFromMap)) {
        if (localMartMerchant.isOpen)
        {
          localObject = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).title(localMartMerchant.martName).snippet("" + j).icon(BitmapDescriptorFactory.fromResource(2130838053)));
          localBoolean = Boolean.valueOf(true);
        }
      }
      for (;;)
      {
        this.merchantMap.put(((Marker)localObject).getId(), localMartMerchant);
        j += 1;
        i += 1;
        break;
        localObject = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).title(localMartMerchant.martName).snippet("" + j).icon(BitmapDescriptorFactory.fromResource(2130838052)));
        continue;
        if (this.isChooseFromMap)
        {
          if (localMartMerchant.isOpen)
          {
            if (localMartMerchant.isSelected) {
              localObject = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).title(localMartMerchant.martName).snippet("" + j).icon(BitmapDescriptorFactory.fromResource(2130838053)));
            } else {
              localObject = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).title(localMartMerchant.martName).snippet("" + j).icon(BitmapDescriptorFactory.fromResource(2130838054)));
            }
          }
          else {
            localObject = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).title(localMartMerchant.martName).snippet("" + j).icon(BitmapDescriptorFactory.fromResource(2130838052)));
          }
        }
        else if (localMartMerchant.isOpen)
        {
          if (!localBoolean.booleanValue())
          {
            localObject = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).title(localMartMerchant.martName).snippet("" + j).icon(BitmapDescriptorFactory.fromResource(2130838053)));
            localBoolean = Boolean.valueOf(true);
            localMartMerchant.isSelected = true;
          }
          else
          {
            localObject = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).title(localMartMerchant.martName).snippet("" + j).icon(BitmapDescriptorFactory.fromResource(2130838054)));
          }
        }
        else {
          localObject = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).title(localMartMerchant.martName).snippet("" + j).icon(BitmapDescriptorFactory.fromResource(2130838052)));
        }
      }
    }
    this.mGoogleMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
    this.mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
    {
      public boolean onMarkerClick(Marker paramAnonymousMarker)
      {
        if (paramAnonymousMarker.getSnippet().equals("000")) {
          return true;
        }
        float f = MartReview.this.getResources().getDimension(2131296541);
        Object localObject = MartReview.this.mGoogleMap.getProjection();
        Point localPoint = ((Projection)localObject).toScreenLocation(paramAnonymousMarker.getPosition());
        localObject = ((Projection)localObject).fromScreenLocation(new Point(localPoint.x, (int)(localPoint.y - f / 2.0F)));
        paramAnonymousMarker.showInfoWindow();
        localObject = CameraUpdateFactory.newLatLng((LatLng)localObject);
        MartReview.this.mGoogleMap.moveCamera((CameraUpdate)localObject);
        MartReview.this.mGoogleMap.animateCamera((CameraUpdate)localObject);
        paramAnonymousMarker.showInfoWindow();
        return true;
      }
    });
    this.mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
    {
      public void onInfoWindowClick(Marker paramAnonymousMarker)
      {
        if (paramAnonymousMarker.getSnippet().equals("000")) {
          return;
        }
        MartReview.access$2902(MartReview.this, (MartMerchant)MartReview.this.merchantMap.get(paramAnonymousMarker.getId()));
        MartReview.this.martMerchant.latlong = ((MartMerchant)MartReview.this.merchantMap.get(paramAnonymousMarker.getId())).getLatLong();
        int i = 0;
        while (i < MartReview.this.storeList.length)
        {
          MartReview.this.storeList[i].isSelected = false;
          i += 1;
        }
        MartReview.this.storeList[Integer.parseInt(paramAnonymousMarker.getSnippet())].isSelected = true;
        MartReview.access$3702(MartReview.this, true);
        MartReview.this.loadMarker(paramLatLng);
        MartReview.this.doCalculate(new Handler[0]);
      }
    });
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 12) && (paramInt2 == -1))
    {
      this.mPoiTo = new Poi(paramIntent.getIntExtra(PickLocation.LOCATION_ID, 0), paramIntent.getStringExtra(PickLocation.LOCATION_NAME), paramIntent.getDoubleExtra(PickLocation.LOCATION_LAT, 0.0D), paramIntent.getDoubleExtra(PickLocation.LOCATION_LNG, 0.0D), paramIntent.getStringExtra(PickLocation.LOCATION_ADDRESS));
      this.mTVPickLocationTo.setText(this.mPoiTo.name);
      str = paramIntent.getStringExtra(PickLocation.LOCATION_DESC);
      localEditText = this.mETLocationDetailTo;
      localObject = str;
      if (str == null) {
        localObject = "";
      }
      localEditText.setText((CharSequence)localObject);
      ((Addresses)this.mBookingData.addresses.get(0)).destinationName = this.mPoiTo.name;
      ((Addresses)this.mBookingData.addresses.get(0)).destinationAddress = this.mPoiTo.address;
      ((Addresses)this.mBookingData.addresses.get(0)).latLongDestination = (this.mPoiTo.latitude + "," + this.mPoiTo.longitude);
      doRefreshOrigin(((Addresses)this.mBookingData.addresses.get(0)).getLatLongDestination(), ((Addresses)this.mBookingData.addresses.get(0)).martMerchant.martId);
      this.mTVPickLocationTo.setText(this.mPoiTo.name);
      this.toLocationProperties = new JSONObject();
    }
    while ((paramInt1 != 13) || (paramInt2 != -1)) {
      try
      {
        String str;
        EditText localEditText;
        this.toLocationProperties.put("PastUsedLocationSelected", paramIntent.getBooleanExtra(PickLocation.PAST_LOCATION, false));
        this.toLocationProperties.put("CustomLocationSelected", paramIntent.getBooleanExtra(PickLocation.CUSTOM_LOCATION, false));
        return;
      }
      catch (JSONException paramIntent)
      {
        paramIntent.printStackTrace();
        return;
      }
    }
    this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
    Object localObject = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
    this.itemList = new ArrayList((Collection)localObject);
    this.mapRouteItems = listToMap((List)localObject);
    this.otherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    this.tempFoodCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    this.tempDeliv = 0L;
    setFoodCost();
    setNoitem();
    this.rowLayout.removeAllViews();
    this.foodMenuItemRender = new FoodMenuItemRender(this, this.itemList)
    {
      public void onChangeFoodItemNote(RouteItem paramAnonymousRouteItem)
      {
        MartReview.this.updateItemNote(paramAnonymousRouteItem);
      }
      
      public void onMinusFoodItemValue(RouteItem paramAnonymousRouteItem)
      {
        MartReview.this.minusItemQuantity(paramAnonymousRouteItem);
      }
      
      public void onPlusFoodItemValue(RouteItem paramAnonymousRouteItem)
      {
        MartReview.this.plusItemQuantity(paramAnonymousRouteItem);
      }
    };
    this.foodMenuItemRender.renderView(this.rowLayout);
    this.rowOtherItemLayout.removeAllViews();
    this.foodOtherMenuItemRender = new FoodMenuItemRender(this, this.otherRouteItems)
    {
      public void onChangeFoodItemNote(RouteItem paramAnonymousRouteItem)
      {
        MartReview.this.updateOtherItemNote(paramAnonymousRouteItem);
      }
      
      public void onMinusFoodItemValue(RouteItem paramAnonymousRouteItem)
      {
        MartReview.this.minusOtherItemQuantity(paramAnonymousRouteItem);
      }
      
      public void onPlusFoodItemValue(RouteItem paramAnonymousRouteItem)
      {
        MartReview.this.plusOtherItemQuantity(paramAnonymousRouteItem);
      }
    };
    this.foodOtherMenuItemRender.renderView(this.rowOtherItemLayout);
  }
  
  public void onBackPressed()
  {
    validateFoodItemList();
    mapToList(this.mapRouteItems);
    Object localObject = (Addresses)this.mBookingData.addresses.get(0);
    ((Addresses)localObject).routeItems = this.saveFoodItems;
    ((Addresses)localObject).foodQuantityTotal = this.tempQuantity;
    ((Addresses)localObject).foodCostTotal = this.tempFoodCost;
    localObject = new Intent();
    ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingData);
    ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    setResult(-1, (Intent)localObject);
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131624824: 
      paramView = new Intent(this, MartOtherItem.class);
      paramView.putExtra("MART_ID", "" + ((Addresses)this.mBookingData.addresses.get(0)).martMerchant.martId);
      paramView.putExtra("BOOKING_DATA", this.mBookingData);
      paramView.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
      paramView.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
      paramView.putExtra("MART_MERCHANT", ((Addresses)this.mBookingData.addresses.get(0)).martMerchant);
      startActivityForResult(paramView, 13);
      return;
    }
    paramView = new Intent(this, MerchantCategoryMenu.class);
    validateFoodItemList();
    ((Addresses)this.mBookingData.addresses.get(0)).routeItems = this.saveFoodItems;
    ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal = this.tempQuantity;
    ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal = this.tempFoodCost;
    paramView.putExtra("MART_ID", "" + ((Addresses)this.mBookingData.addresses.get(0)).martMerchant.martId);
    paramView.putExtra("BOOKING_DATA", this.mBookingData);
    paramView.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    paramView.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    paramView.putExtra("MART_MERCHANT", ((Addresses)this.mBookingData.addresses.get(0)).martMerchant);
    startActivityForResult(paramView, 13);
  }
  
  public void onConnected(Bundle paramBundle)
  {
    this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
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
    setContentView(2130968768);
    buildGoogleApiClient();
    initView();
    this.nearMeMap.onCreate(paramBundle);
    init();
  }
  
  protected void onDestroy()
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
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  protected void onResume()
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
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  
  protected void onStop()
  {
    super.onStop();
    if (this.mGoogleApiClient.isConnected()) {
      this.mGoogleApiClient.disconnect();
    }
  }
  
  public void plusItemQuantity(RouteItem paramRouteItem)
  {
    if (this.mapRouteItems.containsKey(Integer.valueOf(paramRouteItem.itemId))) {
      if (paramRouteItem.quantity != 0) {
        ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramRouteItem.itemId))).quantity = paramRouteItem.quantity;
      }
    }
    for (;;)
    {
      calculateBookingPrice();
      return;
      this.mapRouteItems.put(Integer.valueOf(paramRouteItem.itemId), paramRouteItem);
    }
  }
  
  public void plusOtherItemQuantity(RouteItem paramRouteItem)
  {
    if (this.mapOtherRouteItems.containsKey(Integer.valueOf(paramRouteItem.itemId))) {
      if (paramRouteItem.quantity != 0) {
        ((RouteItem)this.mapOtherRouteItems.get(Integer.valueOf(paramRouteItem.itemId))).quantity = paramRouteItem.quantity;
      }
    }
    for (;;)
    {
      calculateBookingPrice();
      return;
      this.mapOtherRouteItems.put(Integer.valueOf(paramRouteItem.itemId), paramRouteItem);
    }
  }
  
  public class MarkerInfoWindowAdapter
    implements GoogleMap.InfoWindowAdapter
  {
    public MarkerInfoWindowAdapter() {}
    
    public View getInfoContents(Marker paramMarker)
    {
      View localView = MartReview.this.getLayoutInflater().inflate(2130968745, null);
      TextView localTextView1 = (TextView)localView.findViewById(2131624771);
      TextView localTextView2 = (TextView)localView.findViewById(2131624772);
      TextView localTextView3 = (TextView)localView.findViewById(2131624773);
      paramMarker = (MartMerchant)MartReview.this.merchantMap.get(paramMarker.getId());
      if (paramMarker != null)
      {
        localTextView1.setText(paramMarker.martName);
        localTextView2.setText(Util.textDecimalFormat(paramMarker.distance) + " KM");
        localTextView3.setText(paramMarker.detailAddress);
      }
      return localView;
    }
    
    public View getInfoWindow(Marker paramMarker)
    {
      return null;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MartReview.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */