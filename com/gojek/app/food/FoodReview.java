package com.gojek.app.food;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.flurry.android.FlurryAgent;
import com.gojek.app.BookingConfirmation;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.PickLocation;
import com.gojek.app.SignUp;
import com.gojek.app.custom.FoodMenuItemRender;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Poi;
import com.gojek.app.model.Route;
import com.gojek.app.model.RouteResponse;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FoodReview
  extends GojekActivityBase
{
  public static final String TAG = FoodReview.class.getSimpleName();
  private final int REQUEST_TO = 12;
  private FoodMenuItemRender foodMenuItemRender;
  private FoodMenuItemRender foodOtherMenuItemRender;
  private boolean isLocationHistoryLoaded = false;
  private boolean isLogon;
  private ArrayList<RouteItem> itemList;
  private BookingStatus mBookingData;
  private CustomerSaved mCustomer;
  private long mDateSelected;
  private EditText mETLocationDetailTo;
  private ImageView mIVNextBtn;
  private LinearLayout mLLPickLocationTo;
  private LinearLayout mLLPickTime;
  private ListView mLVReviewOrder;
  private Poi mPoiTo = new Poi();
  private ProgressBar mProgressDeliveryCost;
  private RadioButton mRBPickupLater;
  private RadioButton mRBPickupNow;
  private RadioGroup mRGPickup;
  private RelativeLayout mRLDeliveryCost;
  private RelativeLayout mRLFreeDeliveryTitle;
  private TextView mTVDeliveryCost;
  private TextView mTVFoodCost;
  private TextView mTVFoodTotalCost;
  private TextView mTVFreeDeliveryTitle;
  private TextView mTVPickLocationTo;
  private TextView mTVPickTime;
  private View mVPriceDeliveryFreeLine;
  private Map<Integer, RouteItem> mapOtherRouteItems;
  private Map<Integer, RouteItem> mapRouteItems;
  private Merchant merchant;
  private ArrayList<RouteItem> otherRouteItems;
  private RouteResponse routeResponse;
  private LinearLayout rowLayout;
  private LinearLayout rowOtherItemLayout;
  private List<RouteItem> saveFoodItems;
  private long tempDeliv;
  private int tempFoodCost;
  private int tempQuantity;
  JSONObject toLocationProperties;
  
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
    i = 0;
    localIterator = this.otherRouteItems.iterator();
    while (localIterator.hasNext())
    {
      localRouteItem = (RouteItem)localIterator.next();
      i += localRouteItem.getPrice() * localRouteItem.getQuantity();
    }
    this.tempFoodCost += i;
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
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("serviceType", Integer.valueOf(this.mBookingData.serviceType));
      localJSONObject.putOpt("customerId", this.mCustomer.customerId);
      localJSONObject.putOpt("corporateId", "");
      localJSONObject.putOpt("marketplaceType", "");
      localJSONObject.putOpt("region", "");
      localJSONObject.putOpt("paymentType", "0");
      Object localObject1 = new JSONArray();
      Object localObject2 = new JSONObject();
      ((JSONObject)localObject2).putOpt("originLatLong", this.merchant.latLong);
      ((JSONObject)localObject2).putOpt("destinationLatLong", this.mPoiTo.latitude + "," + this.mPoiTo.longitude);
      ((JSONObject)localObject2).putOpt("serviceType", Integer.valueOf(this.mBookingData.serviceType));
      ((JSONArray)localObject1).put(localObject2);
      localJSONObject.putOpt("routeRequests", localObject1);
      this.mTVDeliveryCost.setText("");
      this.mProgressDeliveryCost.setVisibility(0);
      localObject2 = TAG;
      StringBuilder localStringBuilder = new StringBuilder().append("request calculate ");
      if (!(localJSONObject instanceof JSONObject))
      {
        localObject1 = localJSONObject.toString();
        Log.i((String)localObject2, (String)localObject1);
        VolleyClient.getInstance(this).post("https://api.gojek.co.id/gojek/v2/calculate/", localJSONObject, new JsonCallback()
        {
          public void onError(VolleyError paramAnonymousVolleyError)
          {
            FoodReview.this.mTVDeliveryCost.setText(FoodReview.this.getString(2131165537));
            Log.e(FoodReview.TAG, "doCalculateError " + paramAnonymousVolleyError);
            FoodReview.this.mProgressDeliveryCost.setVisibility(8);
          }
          
          public void onResponse(RouteResponse paramAnonymousRouteResponse)
          {
            FoodReview.access$1002(FoodReview.this, paramAnonymousRouteResponse);
            if ((FoodReview.this.routeResponse.getRoutes() != null) && (FoodReview.this.routeResponse.getRoutes().size() > 0))
            {
              Log.i(FoodReview.TAG, "response routes " + FoodReview.this.routeResponse.toString());
              paramAnonymousRouteResponse = (Route)FoodReview.this.routeResponse.routes.get(0);
            }
            try
            {
              Object localObject = Util.getRupiahFormat(String.valueOf(paramAnonymousRouteResponse.price));
              FoodReview.this.mTVDeliveryCost.setText((CharSequence)localObject);
              localObject = new HashMap();
              ((Map)localObject).put("price", String.valueOf(paramAnonymousRouteResponse.price));
              FlurryAgent.logEvent("Price_Check", (Map)localObject);
              FoodReview.access$1902(FoodReview.this, paramAnonymousRouteResponse.price);
              long l = paramAnonymousRouteResponse.price + FoodReview.this.tempFoodCost;
              if (FoodReview.this.routeResponse.isFree)
              {
                FoodReview.this.enabledDeliveryFreeLine();
                l = FoodReview.this.tempFoodCost;
              }
              FoodReview.this.mTVFoodTotalCost.setText(Util.getRupiahFormat(String.valueOf(l)));
              if (paramVarArgs.length > 0) {
                paramVarArgs[0].sendEmptyMessage(0);
              }
            }
            catch (Exception paramAnonymousRouteResponse)
            {
              for (;;) {}
            }
            FoodReview.this.mProgressDeliveryCost.setVisibility(8);
          }
        }, RouteResponse.class);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
        continue;
        String str = JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
      }
    }
  }
  
  private void enabledDeliveryFreeLine()
  {
    this.mRLDeliveryCost.post(new Runnable()
    {
      public void run()
      {
        FoodReview.this.mVPriceDeliveryFreeLine.setVisibility(0);
        int i = FoodReview.this.mTVDeliveryCost.getMeasuredWidth();
        ViewGroup.LayoutParams localLayoutParams = FoodReview.this.mVPriceDeliveryFreeLine.getLayoutParams();
        localLayoutParams.width = i;
        FoodReview.this.mVPriceDeliveryFreeLine.setLayoutParams(localLayoutParams);
        FoodReview.this.mRLFreeDeliveryTitle.setVisibility(0);
        FoodReview.this.mTVFreeDeliveryTitle.setText(Html.fromHtml(FoodReview.this.getString(2131165574)));
      }
    });
  }
  
  private ArrayList<RouteItem> generateItemIdTmp(List<RouteItem> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)paramList.next();
      localRouteItem.setItemId(i);
      localArrayList.add(localRouteItem);
      i += 1;
    }
    return localArrayList;
  }
  
  private void init()
  {
    setTitle(getString(2131165771));
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.merchant = ((Addresses)this.mBookingData.addresses.get(0)).merchant;
    this.mCustomer = new CustomerSaved(this);
    if (this.mCustomer.customerId != null) {
      this.isLogon = true;
    }
    Object localObject = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
    this.itemList = new ArrayList((Collection)localObject);
    this.mapRouteItems = listToMap((List)localObject);
    this.otherRouteItems = new ArrayList();
    this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    if (this.otherRouteItems != null) {}
    for (this.mapOtherRouteItems = listToMap(this.otherRouteItems);; this.mapOtherRouteItems = new LinkedHashMap())
    {
      this.tempFoodCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
      this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
      this.tempDeliv = 0L;
      setFoodCost();
      this.foodMenuItemRender = new FoodMenuItemRender(this, this.itemList)
      {
        public void onChangeFoodItemNote(RouteItem paramAnonymousRouteItem)
        {
          FoodReview.this.updateItemNote(paramAnonymousRouteItem);
        }
        
        public void onMinusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          FoodReview.this.minusItemQuantity(paramAnonymousRouteItem);
        }
        
        public void onPlusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          FoodReview.this.plusItemQuantity(paramAnonymousRouteItem);
        }
      };
      this.foodMenuItemRender.renderView(this.rowLayout);
      this.foodOtherMenuItemRender = new FoodMenuItemRender(this, this.otherRouteItems)
      {
        public void onChangeFoodItemNote(RouteItem paramAnonymousRouteItem)
        {
          if ((FoodReview.this.mapRouteItems.containsKey(Integer.valueOf(paramAnonymousRouteItem.itemId))) && (Util.isTextNotNullEmpty(paramAnonymousRouteItem.notes))) {
            ((RouteItem)FoodReview.this.mapRouteItems.get(Integer.valueOf(paramAnonymousRouteItem.itemId))).notes = paramAnonymousRouteItem.notes;
          }
        }
        
        public void onMinusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          if (FoodReview.this.mapOtherRouteItems.containsKey(Integer.valueOf(paramAnonymousRouteItem.itemId)))
          {
            if (paramAnonymousRouteItem.quantity == 0) {
              break label74;
            }
            ((RouteItem)FoodReview.this.mapOtherRouteItems.get(Integer.valueOf(paramAnonymousRouteItem.itemId))).quantity = paramAnonymousRouteItem.quantity;
          }
          for (;;)
          {
            FoodReview.access$310(FoodReview.this);
            FoodReview.this.calculateBookingPrice();
            return;
            label74:
            FoodReview.this.mapOtherRouteItems.remove(Integer.valueOf(paramAnonymousRouteItem.itemId));
          }
        }
        
        public void onPlusFoodItemValue(RouteItem paramAnonymousRouteItem)
        {
          if (FoodReview.this.mapOtherRouteItems.containsKey(Integer.valueOf(paramAnonymousRouteItem.itemId))) {
            if (paramAnonymousRouteItem.quantity != 0) {
              ((RouteItem)FoodReview.this.mapOtherRouteItems.get(Integer.valueOf(paramAnonymousRouteItem.itemId))).quantity = paramAnonymousRouteItem.quantity;
            }
          }
          for (;;)
          {
            FoodReview.access$308(FoodReview.this);
            FoodReview.this.calculateBookingPrice();
            return;
            FoodReview.this.mapOtherRouteItems.put(Integer.valueOf(paramAnonymousRouteItem.itemId), paramAnonymousRouteItem);
          }
        }
      };
      this.foodOtherMenuItemRender.renderView(this.rowOtherItemLayout);
      calculateBookingPrice();
      this.mLLPickTime.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FoodReview.this.openTimePicker();
        }
      });
      this.mLLPickLocationTo.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(FoodReview.this.getApplicationContext(), PickLocation.class);
          if (FoodReview.this.isLocationHistoryLoaded) {
            paramAnonymousView.putExtra("CACHE_LOCATION_HISTORY", true);
          }
          FoodReview.this.startActivityForResult(paramAnonymousView, 12);
          if (!FoodReview.this.isLocationHistoryLoaded) {
            FoodReview.access$702(FoodReview.this, true);
          }
        }
      });
      this.mIVNextBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (FoodReview.this.mCustomer.customerId == null)
          {
            paramAnonymousView = new Intent(FoodReview.this, SignUp.class);
            FoodReview.this.startActivity(paramAnonymousView);
          }
          while (!FoodReview.this.validate()) {
            return;
          }
          if (FoodReview.this.routeResponse == null)
          {
            FoodReview.this.doCalculate(new Handler[0]);
            return;
          }
          if (!FoodReview.this.isLogon)
          {
            FoodReview.this.doCalculate(new Handler[] { new FoodReview.5.1(this) });
            return;
          }
          FoodReview.this.sendBookingData();
        }
      });
      VolleyClient.getInstance(this).get(String.format("https://api.gojek.co.id/gojek/merchant/%s", new Object[] { Integer.valueOf(this.merchant.id) }), new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError) {}
        
        public void onResponse(Merchant paramAnonymousMerchant)
        {
          FoodReview.access$1402(FoodReview.this, paramAnonymousMerchant);
        }
      }, Merchant.class);
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
        this.mTVPickLocationTo.setText(((Addresses)this.mBookingData.addresses.get(0)).destinationName);
        this.mETLocationDetailTo.setText(((Addresses)this.mBookingData.addresses.get(0)).destinationNote);
        doCalculate(new Handler[0]);
      }
      this.mRBPickupNow.setChecked(true);
      this.mLLPickTime.setVisibility(8);
      this.mRGPickup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
      {
        public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
        {
          paramAnonymousRadioGroup = (RadioButton)FoodReview.this.findViewById(paramAnonymousInt);
          switch (paramAnonymousInt)
          {
          }
          do
          {
            do
            {
              return;
            } while (!paramAnonymousRadioGroup.isChecked());
            FoodReview.this.mLLPickTime.setVisibility(0);
            FoodReview.this.openTimePicker();
            return;
          } while (!paramAnonymousRadioGroup.isChecked());
          FoodReview.this.mTVPickTime.setText(2131165729);
          FoodReview.access$1702(FoodReview.this, 0L);
          FoodReview.this.mLLPickTime.setVisibility(8);
        }
      });
      return;
    }
  }
  
  private void initView()
  {
    this.mTVFoodCost = ((TextView)findViewById(2131624538));
    this.mTVDeliveryCost = ((TextView)findViewById(2131624540));
    this.mTVFoodTotalCost = ((TextView)findViewById(2131624544));
    this.mLVReviewOrder = ((ListView)findViewById(2131624545));
    this.mLLPickLocationTo = ((LinearLayout)findViewById(2131624548));
    this.mTVPickLocationTo = ((TextView)findViewById(2131624549));
    this.mETLocationDetailTo = ((EditText)findViewById(2131624550));
    this.mLLPickTime = ((LinearLayout)findViewById(2131624556));
    this.mTVPickTime = ((TextView)findViewById(2131624557));
    this.mIVNextBtn = ((ImageView)findViewById(2131624558));
    this.mProgressDeliveryCost = ((ProgressBar)findViewById(2131624541));
    this.rowLayout = ((LinearLayout)findViewById(2131624546));
    this.rowOtherItemLayout = ((LinearLayout)findViewById(2131624547));
    this.mVPriceDeliveryFreeLine = findViewById(2131624268);
    this.mRLDeliveryCost = ((RelativeLayout)findViewById(2131624539));
    this.mRLFreeDeliveryTitle = ((RelativeLayout)findViewById(2131624542));
    this.mTVFreeDeliveryTitle = ((TextView)findViewById(2131624543));
    this.mRGPickup = ((RadioGroup)findViewById(2131624552));
    this.mRBPickupNow = ((RadioButton)findViewById(2131624553));
    this.mRBPickupLater = ((RadioButton)findViewById(2131624554));
    findViewById(2131624554).setVisibility(8);
    findViewById(2131624555).setVisibility(8);
    findViewById(2131624556).setVisibility(8);
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
        break label67;
      }
      ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramRouteItem.itemId))).quantity = paramRouteItem.quantity;
    }
    for (;;)
    {
      this.tempQuantity -= 1;
      calculateBookingPrice();
      return;
      label67:
      this.mapRouteItems.remove(Integer.valueOf(paramRouteItem.itemId));
    }
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
        FoodReview.access$1702(FoodReview.this, paramAnonymousView.getTimeInMillis());
        if ((((Date)localObject2).compareTo((Date)localObject1) == 0) || (((Date)localObject2).after((Date)localObject1)))
        {
          FoodReview.this.mTVPickTime.setText(new SimpleDateFormat("hh:mm a, dd MMM", Locale.US).format((Date)localObject2));
          localAlertDialog.dismiss();
          return;
        }
        Toast.makeText(FoodReview.this, FoodReview.this.getString(2131165897), 0).show();
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
  
  private void sendBookingData()
  {
    Object localObject = this.mTVPickLocationTo.getText().toString();
    String str = this.mETLocationDetailTo.getText().toString();
    ((Addresses)this.mBookingData.addresses.get(0)).originName = this.merchant.name;
    ((Addresses)this.mBookingData.addresses.get(0)).originAddress = this.merchant.address;
    ((Addresses)this.mBookingData.addresses.get(0)).latLongOrigin = this.merchant.latLong;
    ((Addresses)this.mBookingData.addresses.get(0)).detailAddress = this.merchant.detailAddress;
    ((Addresses)this.mBookingData.addresses.get(0)).item = "";
    ((Addresses)this.mBookingData.addresses.get(0)).originContactName = "";
    ((Addresses)this.mBookingData.addresses.get(0)).originContactPhone = "";
    ((Addresses)this.mBookingData.addresses.get(0)).destinationContactName = "";
    ((Addresses)this.mBookingData.addresses.get(0)).destinationContactPhone = "";
    ((Addresses)this.mBookingData.addresses.get(0)).destinationNote = str;
    ((Addresses)this.mBookingData.addresses.get(0)).destinationName = ((String)localObject);
    ((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice = this.tempFoodCost;
    if (!Util.isTextNotNullEmpty(this.mTVPickTime.getText().toString())) {
      this.mBookingData.timeField = null;
    }
    ((Addresses)this.mBookingData.addresses.get(0)).routeItems = concatRouteItem();
    FlurryAgent.logEvent("Next_Clicked");
    if (!this.mETLocationDetailTo.getText().toString().trim().equals("")) {
      this.mixPanel.track("TR: To Location Details Entered");
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
  
  private void updateItemNote(RouteItem paramRouteItem)
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
      localStringBuilder.append("- You have no food item").append("\n");
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
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 12) && (paramInt2 == -1))
    {
      this.mPoiTo = new Poi(paramIntent.getIntExtra(PickLocation.LOCATION_ID, 0), paramIntent.getStringExtra(PickLocation.LOCATION_NAME), paramIntent.getDoubleExtra(PickLocation.LOCATION_LAT, 0.0D), paramIntent.getDoubleExtra(PickLocation.LOCATION_LNG, 0.0D), paramIntent.getStringExtra(PickLocation.LOCATION_ADDRESS));
      this.mTVPickLocationTo.setText(this.mPoiTo.name);
      String str2 = paramIntent.getStringExtra(PickLocation.LOCATION_DESC);
      EditText localEditText = this.mETLocationDetailTo;
      String str1 = str2;
      if (str2 == null) {
        str1 = "";
      }
      localEditText.setText(str1);
      if (!this.mBookingData.addresses.isEmpty()) {
        doCalculate(new Handler[0]);
      }
      ((Addresses)this.mBookingData.addresses.get(0)).destinationName = this.mPoiTo.name;
      ((Addresses)this.mBookingData.addresses.get(0)).destinationAddress = this.mPoiTo.address;
      ((Addresses)this.mBookingData.addresses.get(0)).latLongDestination = (this.mPoiTo.latitude + "," + this.mPoiTo.longitude);
      this.mTVPickLocationTo.setText(this.mPoiTo.name);
      this.toLocationProperties = new JSONObject();
    }
    try
    {
      this.toLocationProperties.put("PastUsedLocationSelected", paramIntent.getBooleanExtra(PickLocation.PAST_LOCATION, false));
      this.toLocationProperties.put("CustomLocationSelected", paramIntent.getBooleanExtra(PickLocation.CUSTOM_LOCATION, false));
      return;
    }
    catch (JSONException paramIntent)
    {
      paramIntent.printStackTrace();
    }
  }
  
  public void onBackPressed()
  {
    validateFoodItemList();
    this.otherRouteItems = generateItemIdTmp(this.otherRouteItems);
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
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968704);
    initView();
    init();
  }
  
  protected void onResume()
  {
    this.mCustomer = new CustomerSaved(this);
    super.onResume();
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
      this.tempQuantity += 1;
      calculateBookingPrice();
      return;
      this.mapRouteItems.put(Integer.valueOf(paramRouteItem.itemId), paramRouteItem);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/FoodReview.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */