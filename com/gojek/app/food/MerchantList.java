package com.gojek.app.food;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.SignUp;
import com.gojek.app.adapter.food.MerchantGridAdapter;
import com.gojek.app.custom.EndlessScrollListener;
import com.gojek.app.custom.FloatingActionButton;
import com.gojek.app.custom.FloatingActionButton.Builder;
import com.gojek.app.custom.GridViewWithHeaderAndFooter;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.persistence.dao.BookingHistoryDao;
import com.gojek.app.util.Util;
import com.google.android.gms.common.GooglePlayServicesUtil;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;

public class MerchantList
  extends GojekActivityBase
{
  public static final String TAG = MerchantList.class.getSimpleName();
  private String category;
  private FloatingActionButton fabButton;
  private Gson gson = new Gson();
  private ArrayList<Integer> listMerchantId;
  private String location;
  private BookingStatus mBookingData;
  private CustomerSaved mCustomerSaved;
  private TextView mETSearch;
  private GridViewWithHeaderAndFooter mGVMerchant;
  private GoogleMap mGoogleMap;
  private LinearLayout mLLSearchBar;
  private MapView mMVFoodMap;
  private RelativeLayout mRLSuggestMerchant;
  private TextView mTVCommingSoon;
  private Merchant merchant;
  private MerchantGridAdapter merchantAdapter;
  private ArrayList<Merchant> merchantList;
  private Map<String, Merchant> merchantMap;
  private ArrayList<RouteItem> otherItems;
  private ProgressBar progressBar;
  
  private ArrayList<Integer> addListMerchantId(List<BookingStatus> paramList)
  {
    this.listMerchantId = new ArrayList();
    Object localObject1 = new ArrayList();
    paramList = paramList.iterator();
    Object localObject2;
    while (paramList.hasNext())
    {
      localObject2 = (BookingStatus)paramList.next();
      if ((((BookingStatus)localObject2).serviceType == 5) && (isGoJekCompleteBooking((BookingStatus)localObject2))) {
        ((List)localObject1).add(localObject2);
      }
    }
    Collections.sort((List)localObject1);
    paramList = ((List)localObject1).iterator();
    while (paramList.hasNext())
    {
      localObject1 = ((BookingStatus)paramList.next()).addresses.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Addresses)((Iterator)localObject1).next();
        if ((!this.listMerchantId.contains(Integer.valueOf(((Addresses)localObject2).merchantId))) && (this.listMerchantId.size() <= 10)) {
          this.listMerchantId.add(Integer.valueOf(((Addresses)localObject2).merchantId));
        }
      }
    }
    return this.listMerchantId;
  }
  
  private List<BookingStatus> doLoadLocalHistory()
  {
    return new BookingHistoryDao(getApplicationContext()).getBookingData();
  }
  
  private void doLoadMarker(List<Merchant> paramList)
  {
    Object localObject1 = null;
    LatLngBounds.Builder localBuilder = new LatLngBounds.Builder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Merchant localMerchant = (Merchant)paramList.next();
      Object localObject2 = localMerchant.getLatLng();
      localObject2 = this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject2).icon(BitmapDescriptorFactory.fromResource(2130837731)).flat(true).title(localMerchant.name));
      this.mGoogleMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
      this.merchantMap.put(((Marker)localObject2).getId(), localMerchant);
      localBuilder.include(localMerchant.getLatLng());
    }
    paramList = (List<Merchant>)localObject1;
    if (this.location != null)
    {
      paramList = Util.getLatLong(this.location);
      paramList = new LatLng(Double.parseDouble(paramList[0]), Double.parseDouble(paramList[1]));
      localBuilder.include(paramList);
      this.mGoogleMap.addMarker(new MarkerOptions().position(paramList).icon(BitmapDescriptorFactory.fromResource(2130837871)).flat(true));
    }
    CameraUpdateFactory.newLatLngBounds(localBuilder.build(), 50);
    paramList = CameraUpdateFactory.newLatLngZoom(paramList, 13.0F);
    this.mGoogleMap.moveCamera(paramList);
  }
  
  private void doLoadMerchantByCategory()
  {
    this.fabButton.setVisibility(8);
    if (this.location == null)
    {
      this.mTVCommingSoon.setVisibility(0);
      this.mTVCommingSoon.setText(getString(2131165694));
      return;
    }
    if (this.category.equals("MY_RESTAURANT"))
    {
      if (this.mCustomerSaved.customerId != null)
      {
        this.listMerchantId = addListMerchantId(doLoadLocalHistory());
        String str = generateArrayOfMerchantId(this.listMerchantId);
        if ((str != null) && (str.trim().length() > 0))
        {
          loadMerchant(String.format("https://api.gojek.co.id/gojek/merchant/history?location=%1s&merchantIds=%2s", new Object[] { this.location, str }));
          return;
        }
        loadHistoryFromServer(this.mCustomerSaved.customerId, this.mCustomerSaved.getAccessToken());
        return;
      }
      startActivityForResult(new Intent(this, SignUp.class), 100);
      return;
    }
    loadMerchant(String.format("https://api.gojek.co.id/gojek/merchant/find?location=%1s&category=%2s&page=%d&limit=%d", new Object[] { this.location, this.category, Integer.valueOf(0), Integer.valueOf(32) }));
  }
  
  private void doLoadMerchantByCategoryPage(final int paramInt)
  {
    if (this.location == null) {}
    String str;
    do
    {
      return;
      str = "";
      if (!this.category.equals("MY_RESTAURANT")) {
        str = String.format("https://api.gojek.co.id/gojek/merchant/find?location=%1s&category=%2s&page=%d&limit=%d", new Object[] { this.location, this.category, Integer.valueOf(paramInt), Integer.valueOf(32) });
      }
    } while (str.trim().length() <= 0);
    Log.i(TAG, "request merchantByCategoryPage " + str);
    VolleyClient.getInstance(this).getList(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MerchantList.TAG, "doLoadMerchantByCategoryPage() got error " + paramAnonymousVolleyError);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson = MerchantList.this.gson;
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new MerchantList.7.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label121;
          }
        }
        label121:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.isEmpty())) {
            break label134;
          }
          if (MerchantList.this.category.equals("BEST_SELLER")) {
            MerchantList.this.doLoadMarker(paramAnonymousJSONArray);
          }
          MerchantList.this.merchantList.addAll(paramAnonymousJSONArray);
          MerchantList.this.merchantAdapter.notifyDataSetChanged();
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
        label134:
        Log.i(MerchantList.TAG, "no merchant found at page " + paramInt);
      }
    });
  }
  
  private void doSelectMerchant(final Merchant paramMerchant)
  {
    if ((((Addresses)this.mBookingData.addresses.get(0)).merchant.getId() != 0) && (paramMerchant.getId() != ((Addresses)this.mBookingData.addresses.get(0)).merchant.getId()) && ((((Addresses)this.mBookingData.addresses.get(0)).routeItems.size() > 0) || (this.otherItems.size() > 0)))
    {
      Util.confirmDialog(this, new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          MerchantList.this.resetBookingData();
          MerchantList.this.gotoMerchantMenu(paramMerchant);
        }
      }, null, getString(2131165898), "Warning");
      return;
    }
    gotoMerchantMenu(paramMerchant);
  }
  
  private String generateArrayOfMerchantId(ArrayList<Integer> paramArrayList)
  {
    String str = "";
    Object localObject = str;
    if (paramArrayList != null)
    {
      localObject = str;
      if (paramArrayList.size() > 0)
      {
        Iterator localIterator = this.listMerchantId.iterator();
        for (paramArrayList = str;; paramArrayList = paramArrayList + String.valueOf(localObject) + ",")
        {
          localObject = paramArrayList;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject = (Integer)localIterator.next();
        }
      }
    }
    return (String)localObject;
  }
  
  private void gotoMerchantMenu(Merchant paramMerchant)
  {
    Intent localIntent = new Intent(getApplicationContext(), MerchantMenu.class);
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putExtra("MERCHANT", paramMerchant);
    localIntent.putExtra("POI_LAT_LNG", this.merchant.latLong);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
    startActivityForResult(localIntent, 101);
  }
  
  private void init(Bundle paramBundle)
  {
    this.listMerchantId = new ArrayList();
    this.mCustomerSaved = new CustomerSaved(this);
    this.otherItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    Object localObject = new Addresses();
    if (this.mBookingData != null) {
      this.mBookingData.addresses.add(localObject);
    }
    this.category = getIntent().getStringExtra("CATEGORY");
    this.location = getIntent().getStringExtra("LAST_LOCATION");
    setTitle(getString(2131165764));
    if (this.category != null) {
      setTitle(getIntent().getStringExtra("CATEGORY_NAME"));
    }
    this.merchantList = new ArrayList();
    this.merchantMap = new TreeMap();
    localObject = getIntent().getStringExtra("MERCHANT_NAME");
    if (localObject != null) {
      this.mETSearch.setText((CharSequence)localObject);
    }
    this.mLLSearchBar.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(MerchantList.this, MerchantSearch.class);
        paramAnonymousView.putExtra("MERCHANT_NAME", "");
        paramAnonymousView.putExtra("LAST_LOCATION", MerchantList.this.location);
        paramAnonymousView.putExtra("BOOKING_DATA", MerchantList.this.mBookingData);
        paramAnonymousView.putParcelableArrayListExtra("OTHER_ITEM", MerchantList.this.otherItems);
        MerchantList.this.startActivityForResult(paramAnonymousView, 101);
      }
    });
    this.merchantAdapter = new MerchantGridAdapter(this, this.merchantList);
    localObject = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130968746, null);
    this.mMVFoodMap = ((MapView)((View)localObject).findViewById(2131624775));
    this.mMVFoodMap.onCreate(paramBundle);
    initializeMap();
    if (this.category.equals("BEST_SELLER"))
    {
      this.mGVMerchant.addHeaderView((View)localObject, null, false);
      this.mGVMerchant.setInterceptTouchHeader(true);
    }
    this.mGVMerchant.setAdapter(this.merchantAdapter);
    this.mGVMerchant.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        int i = MerchantList.this.mGVMerchant.getNumColumns();
        if (MerchantList.this.mGVMerchant.getHeaderViewCount() != 0) {
          MerchantList.access$402(MerchantList.this, MerchantList.this.merchantAdapter.getItem(paramAnonymousInt - i));
        }
        for (;;)
        {
          MerchantList.this.doSelectMerchant(MerchantList.this.merchant);
          return;
          MerchantList.access$402(MerchantList.this, MerchantList.this.merchantAdapter.getItem(paramAnonymousInt));
        }
      }
    });
    this.fabButton = new FloatingActionButton.Builder(this).withDrawable(getResources().getDrawable(2130837930)).withButtonColor(-7829368).withGravity(85).withButtonSize(40).withMargins(0, 0, 10, 50).create();
    this.fabButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MerchantList.this.mGVMerchant.smoothScrollToPosition(0);
      }
    });
    this.fabButton.setVisibility(8);
    if (this.category != null) {
      doLoadMerchantByCategory();
    }
    this.mGVMerchant.setOnScrollListener(new EndlessScrollListener()
    {
      protected void onLoadMore(int paramAnonymousInt)
      {
        if (MerchantList.this.category.equals("BEST_SELLER"))
        {
          MerchantList.this.doLoadMerchantByCategoryPage(paramAnonymousInt);
          return;
        }
        MerchantList.this.fabButton.setVisibility(8);
      }
    });
    this.mRLSuggestMerchant.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(MerchantList.this, MerchantSuggest.class);
        MerchantList.this.startActivity(paramAnonymousView);
      }
    });
  }
  
  private void initView()
  {
    this.mLLSearchBar = ((LinearLayout)findViewById(2131624458));
    this.mGVMerchant = ((GridViewWithHeaderAndFooter)findViewById(2131624466));
    this.progressBar = ((ProgressBar)findViewById(2131624286));
    this.mETSearch = ((TextView)findViewById(2131624215));
    this.mTVCommingSoon = ((TextView)findViewById(2131624467));
    this.mRLSuggestMerchant = ((RelativeLayout)findViewById(2131624468));
  }
  
  private void initializeMap()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i == 0)
    {
      this.mGoogleMap = this.mMVFoodMap.getMap();
      this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
      this.mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
      this.mGoogleMap.setMyLocationEnabled(true);
      MapsInitializer.initialize(this);
      Object localObject;
      if ((this.location != null) && (!this.location.isEmpty()))
      {
        localObject = Util.getLatLong(this.location);
        localObject = new LatLng(Double.parseDouble(localObject[0]), Double.parseDouble(localObject[1]));
        this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((LatLng)localObject, 13.0F));
        this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).icon(BitmapDescriptorFactory.fromResource(2130837871)).flat(true));
      }
      for (;;)
      {
        this.mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
          public boolean onMarkerClick(Marker paramAnonymousMarker)
          {
            MerchantList.access$402(MerchantList.this, (Merchant)MerchantList.this.merchantMap.get(paramAnonymousMarker.getId()));
            float f = MerchantList.this.getResources().getDimension(2131296541);
            Object localObject = MerchantList.this.mGoogleMap.getProjection();
            Point localPoint = ((Projection)localObject).toScreenLocation(paramAnonymousMarker.getPosition());
            localObject = ((Projection)localObject).fromScreenLocation(new Point(localPoint.x, (int)(localPoint.y - f / 2.0F)));
            paramAnonymousMarker.showInfoWindow();
            localObject = CameraUpdateFactory.newLatLng((LatLng)localObject);
            MerchantList.this.mGoogleMap.moveCamera((CameraUpdate)localObject);
            MerchantList.this.mGoogleMap.animateCamera((CameraUpdate)localObject);
            paramAnonymousMarker.showInfoWindow();
            return true;
          }
        });
        this.mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
          public void onInfoWindowClick(Marker paramAnonymousMarker)
          {
            paramAnonymousMarker = (Merchant)MerchantList.this.merchantMap.get(paramAnonymousMarker.getId());
            Intent localIntent = new Intent(MerchantList.this, MerchantMenu.class);
            localIntent.putExtra("BOOKING_DATA", MerchantList.this.mBookingData);
            localIntent.putExtra("MERCHANT", paramAnonymousMarker);
            localIntent.putExtra("POI_LAT_LNG", paramAnonymousMarker.latLong);
            localIntent.putParcelableArrayListExtra("OTHER_ITEM", MerchantList.this.otherItems);
            MerchantList.this.startActivityForResult(localIntent, 101);
          }
        });
        return;
        localObject = new LatLng(-6.184694D, 106.828145D);
        this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((LatLng)localObject, 13.0F));
      }
    }
    GooglePlayServicesUtil.getErrorDialog(i, this, 10).show();
  }
  
  private boolean isGoJekCompleteBooking(BookingStatus paramBookingStatus)
  {
    return paramBookingStatus.statusBooking == 4;
  }
  
  private void loadHistoryFromServer(String paramString1, String paramString2)
  {
    this.progressBar.setVisibility(0);
    paramString1 = String.format("https://api.gojek.co.id/gojek/v2/booking/history/%s", new Object[] { paramString1 });
    VolleyClient.getInstance(this).getList(paramString1, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        MerchantList.this.progressBar.setVisibility(8);
        if (MerchantList.this.category.equals("MY_RESTAURANT"))
        {
          MerchantList.this.fabButton.setVisibility(8);
          if ((paramAnonymousVolleyError.getMessage() != null) && (paramAnonymousVolleyError.getMessage().contains("No address associated with hostname")))
          {
            MerchantList.this.mTVCommingSoon.setText(MerchantList.this.getString(2131165695));
            MerchantList.this.mTVCommingSoon.setVisibility(0);
          }
        }
        else
        {
          return;
        }
        MerchantList.this.mTVCommingSoon.setText("No record found");
        MerchantList.this.mTVCommingSoon.setVisibility(0);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson;
        Type localType;
        if (paramAnonymousJSONArray != null)
        {
          localGson = MerchantList.this.gson;
          if ((paramAnonymousJSONArray instanceof JSONArray)) {
            break label142;
          }
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new MerchantList.11.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label153;
          }
        }
        label142:
        label153:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          if (paramAnonymousJSONArray.size() <= 0) {
            break label202;
          }
          MerchantList.access$1302(MerchantList.this, MerchantList.this.addListMerchantId(paramAnonymousJSONArray));
          paramAnonymousJSONArray = MerchantList.this.generateArrayOfMerchantId(MerchantList.this.listMerchantId);
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.trim().length() <= 0)) {
            break label166;
          }
          paramAnonymousJSONArray = String.format("https://api.gojek.co.id/gojek/merchant/history?location=%1s&merchantIds=%2s", new Object[] { MerchantList.this.location, paramAnonymousJSONArray });
          MerchantList.this.loadMerchant(paramAnonymousJSONArray);
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
        label166:
        MerchantList.this.progressBar.setVisibility(8);
        MerchantList.this.mTVCommingSoon.setText("No record found, please make an order first :)");
        MerchantList.this.mTVCommingSoon.setVisibility(0);
        return;
        label202:
        MerchantList.this.progressBar.setVisibility(8);
        MerchantList.this.mTVCommingSoon.setText("No record found, please make an order first :)");
        MerchantList.this.mTVCommingSoon.setVisibility(0);
      }
    }, paramString2);
  }
  
  private void loadMerchant(String paramString)
  {
    Log.i(TAG, "url request merchantByCategory " + paramString);
    if (this.progressBar.getVisibility() == 8) {
      this.progressBar.setVisibility(0);
    }
    VolleyClient.getInstance(this).getList(paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MerchantList.TAG, "doLoadMerchantByCategory() got error " + paramAnonymousVolleyError);
        if ((MerchantList.this.merchantList == null) || (MerchantList.this.merchantList.size() <= 0))
        {
          if ((paramAnonymousVolleyError.getMessage() == null) || (!paramAnonymousVolleyError.getMessage().contains("No address associated with hostname"))) {
            break label134;
          }
          MerchantList.this.mTVCommingSoon.setText(MerchantList.this.getString(2131165695));
          MerchantList.this.mTVCommingSoon.setVisibility(0);
        }
        for (;;)
        {
          MerchantList.this.progressBar.setVisibility(8);
          MerchantList.this.mGVMerchant.setVisibility(0);
          MerchantList.this.fabButton.setVisibility(8);
          return;
          label134:
          MerchantList.this.mTVCommingSoon.setText(MerchantList.this.getString(2131165696));
          MerchantList.this.mTVCommingSoon.setVisibility(0);
        }
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Object localObject1 = MerchantList.this.gson;
        Object localObject2;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localObject2 = new MerchantList.6.1(this).getType();
          if ((localObject1 instanceof Gson)) {
            break label213;
          }
        }
        label213:
        for (paramAnonymousJSONArray = ((Gson)localObject1).fromJson(paramAnonymousJSONArray, (Type)localObject2);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localObject1, paramAnonymousJSONArray, (Type)localObject2))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          MerchantList.this.progressBar.setVisibility(8);
          MerchantList.this.mGVMerchant.setVisibility(0);
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.isEmpty())) {
            break label283;
          }
          MerchantList.this.mTVCommingSoon.setVisibility(8);
          if (!MerchantList.this.category.equals("MY_RESTAURANT")) {
            break label226;
          }
          localObject1 = MerchantList.this.listMerchantId.iterator();
          for (;;)
          {
            if (!((Iterator)localObject1).hasNext()) {
              break label261;
            }
            localObject2 = (Integer)((Iterator)localObject1).next();
            Iterator localIterator = paramAnonymousJSONArray.iterator();
            if (localIterator.hasNext())
            {
              Merchant localMerchant = (Merchant)localIterator.next();
              if (((Integer)localObject2).intValue() != localMerchant.id) {
                break;
              }
              MerchantList.this.merchantList.add(localMerchant);
            }
          }
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
        label226:
        if (MerchantList.this.category.equals("BEST_SELLER")) {
          MerchantList.this.doLoadMarker(paramAnonymousJSONArray);
        }
        MerchantList.this.merchantList.addAll(paramAnonymousJSONArray);
        label261:
        MerchantList.this.merchantAdapter.notifyDataSetChanged();
        MerchantList.this.fabButton.setVisibility(0);
        return;
        label283:
        if (MerchantList.this.category.equals("MY_RESTAURANT"))
        {
          MerchantList.this.fabButton.setVisibility(8);
          MerchantList.this.mTVCommingSoon.setText("No record found, please make an order first :)");
          MerchantList.this.mTVCommingSoon.setVisibility(0);
          return;
        }
        MerchantList.this.fabButton.setVisibility(8);
        MerchantList.this.mTVCommingSoon.setText(MerchantList.this.getString(2131165696));
        MerchantList.this.mTVCommingSoon.setVisibility(0);
      }
    });
  }
  
  private void resetBookingData()
  {
    ((Addresses)this.mBookingData.addresses.get(0)).merchantId = 0;
    ((Addresses)this.mBookingData.addresses.get(0)).merchant = new Merchant();
    ((Addresses)this.mBookingData.addresses.get(0)).item = null;
    ((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice = 0L;
    ((Addresses)this.mBookingData.addresses.get(0)).routeItems = new ArrayList();
    ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal = 0;
    ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal = 0;
    this.otherItems = new ArrayList();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.progressBar.getVisibility() == 0) {
      this.progressBar.setVisibility(8);
    }
    if ((paramInt1 == 101) && (paramInt2 == -1))
    {
      this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      this.otherItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    }
    for (;;)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      if (((paramInt1 != 111) || (paramInt2 != -1)) && (paramInt1 == 100) && (this.category.equals("MY_RESTAURANT")))
      {
        this.mCustomerSaved = new CustomerSaved(this);
        if (this.mCustomerSaved.customerId != null)
        {
          doLoadMerchantByCategory();
        }
        else
        {
          this.progressBar.setVisibility(8);
          this.mGVMerchant.setVisibility(8);
          this.fabButton.setVisibility(8);
          this.mTVCommingSoon.setText("Please Login First");
          this.mTVCommingSoon.setVisibility(0);
        }
      }
    }
  }
  
  public void onBackPressed()
  {
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    Intent localIntent = new Intent();
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
    setResult(-1, localIntent);
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968690);
    initView();
    init(paramBundle);
  }
  
  public void onDestroy()
  {
    if (this.mMVFoodMap != null) {
      this.mMVFoodMap.onDestroy();
    }
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
    if (this.fabButton != null) {
      this.fabButton.setVisibility(8);
    }
    if (this.mMVFoodMap != null) {
      this.mMVFoodMap.onPause();
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  public void onResume()
  {
    super.onResume();
    if ((this.fabButton != null) && (this.merchantList.size() > 0)) {
      this.fabButton.setVisibility(0);
    }
    if (this.mMVFoodMap != null) {
      this.mMVFoodMap.onResume();
    }
  }
  
  public class MarkerInfoWindowAdapter
    implements GoogleMap.InfoWindowAdapter
  {
    public MarkerInfoWindowAdapter() {}
    
    public View getInfoContents(Marker paramMarker)
    {
      View localView = MerchantList.this.getLayoutInflater().inflate(2130968744, null);
      TextView localTextView1 = (TextView)localView.findViewById(2131624768);
      TextView localTextView2 = (TextView)localView.findViewById(2131624769);
      paramMarker = (Merchant)MerchantList.this.merchantMap.get(paramMarker.getId());
      if (paramMarker != null)
      {
        localTextView1.setText(paramMarker.getName());
        localTextView2.setText(Util.textDecimalFormat(paramMarker.getDistance()) + " KM");
      }
      return localView;
    }
    
    public View getInfoWindow(Marker paramMarker)
    {
      return null;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/MerchantList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */