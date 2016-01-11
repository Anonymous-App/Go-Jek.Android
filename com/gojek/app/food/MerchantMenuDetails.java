package com.gojek.app.food;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.json.CustomTypeAdapterFactory;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.FoodItem;
import com.gojek.app.model.OfficialHourModel;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MerchantMenuDetails
  extends GojekActivityBase
{
  public static final String TAG = MerchantMenuDetails.class.getSimpleName();
  private BaseAdapter baseAdapter;
  private Gson gson;
  private boolean isDifferent;
  private String isOpen;
  private boolean isOpenNow;
  private List<FoodItem> listfood;
  private BookingStatus mBookingData;
  private ListView mLVMenuDetailList;
  private ProgressBar mProgressBar;
  private TextView mTVManyAndCost;
  private TextView mTVReviewBtn;
  private Map<Integer, RouteItem> mapRouteItems;
  private String menuCategory;
  private Merchant merchant;
  private int merchantId;
  private OfficialHourModel officialhourNow;
  private ArrayList<RouteItem> otherRouteItems;
  private String statuspage;
  private int tempCost;
  private int tempQuantity;
  private VolleyClient volleyClient;
  
  private void getOfficialHour(int paramInt)
  {
    Object localObject = String.format("https://api.gojek.co.id/gojek/merchant/%s", new Object[] { Integer.valueOf(paramInt) });
    Log.i(TAG, "request menu category to " + (String)localObject);
    localObject = new JsonObjectRequest(0, (String)localObject, new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONArray("listOperationalHour");
          int i = 0;
          for (;;)
          {
            if (i < paramAnonymousJSONObject.length())
            {
              Object localObject = paramAnonymousJSONObject.getJSONObject(i);
              if (((JSONObject)localObject).getString("dayOfWeek").equals(this.val$dayNow))
              {
                MerchantMenuDetails.access$1702(MerchantMenuDetails.this, new OfficialHourModel(((JSONObject)localObject).getInt("id"), ((JSONObject)localObject).getInt("merchantId"), ((JSONObject)localObject).getString("dayOfWeek"), ((JSONObject)localObject).getString("openTime"), ((JSONObject)localObject).getString("closeTime")));
                if ((Util.isTextNotNullEmpty(MerchantMenuDetails.this.officialhourNow.getOpenTime())) && (Util.isTextNotNullEmpty(MerchantMenuDetails.this.officialhourNow.getCloseTime())))
                {
                  paramAnonymousJSONObject = MerchantMenuDetails.this.officialhourNow.getOpenTime();
                  localObject = MerchantMenuDetails.this.officialhourNow.getCloseTime();
                  MerchantMenuDetails.access$1802(MerchantMenuDetails.this, Util.isBetweenHour(paramAnonymousJSONObject, (String)localObject));
                  MerchantMenuDetails.access$1902(MerchantMenuDetails.this, MerchantMenuDetails.this.isOpenNow + "");
                  Log.i(MerchantMenuDetails.TAG, "isOpen ow" + MerchantMenuDetails.this.isOpenNow);
                }
              }
            }
            else
            {
              return;
            }
            i += 1;
          }
          return;
        }
        catch (JSONException paramAnonymousJSONObject)
        {
          Toast.makeText(MerchantMenuDetails.this.getApplicationContext(), paramAnonymousJSONObject.toString(), 1).show();
        }
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError) {}
    });
    VolleyClient.getInstance(getApplicationContext()).addToRequestQueue((Request)localObject);
  }
  
  private void init()
  {
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    setTitle(getIntent().getStringExtra("ITEM_CATEGORY_NAME"));
    Object localObject = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
    if (localObject == null) {}
    for (this.mapRouteItems = listToMap(new ArrayList());; this.mapRouteItems = listToMap((List)localObject))
    {
      this.isDifferent = false;
      if ((((Addresses)this.mBookingData.addresses.get(0)).merchant.id != 0) && (this.merchantId != ((Addresses)this.mBookingData.addresses.get(0)).merchant.id)) {
        this.isDifferent = true;
      }
      localObject = new GsonBuilder();
      ((GsonBuilder)localObject).registerTypeAdapterFactory(new CustomTypeAdapterFactory(FoodItem.class));
      this.gson = ((GsonBuilder)localObject).create();
      this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
      this.menuCategory = getIntent().getStringExtra("ITEM_CATEGORY");
      this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
      this.tempCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
      setQuantityCost();
      this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (MerchantMenuDetails.this.validateRouteItem())
          {
            paramAnonymousView = MerchantMenuDetails.this.mapToList(MerchantMenuDetails.this.mapRouteItems);
            Addresses localAddresses = (Addresses)MerchantMenuDetails.this.mBookingData.addresses.get(0);
            localAddresses.routeItems = paramAnonymousView;
            localAddresses.foodQuantityTotal = MerchantMenuDetails.this.tempQuantity;
            localAddresses.foodCostTotal = MerchantMenuDetails.this.tempCost;
            paramAnonymousView = new Intent(MerchantMenuDetails.this.getApplicationContext(), FoodReview.class);
            paramAnonymousView.putExtra("BOOKING_DATA", MerchantMenuDetails.this.mBookingData);
            paramAnonymousView.putExtra("MERCHANT", MerchantMenuDetails.this.merchant);
            paramAnonymousView.putParcelableArrayListExtra("OTHER_ITEM", MerchantMenuDetails.this.otherRouteItems);
            MerchantMenuDetails.this.startActivityForResult(paramAnonymousView, 111);
          }
        }
      });
      this.mProgressBar.setVisibility(0);
      localObject = this.menuCategory.replace(" ", "%20");
      Log.d("menu category : ", (String)localObject);
      localObject = String.format("https://api.gojek.co.id/gojek/shopping-item/find?category=%1s&merchantId=%2s", new Object[] { localObject, Integer.valueOf(this.merchantId) });
      Log.i(TAG, "request merchant item to " + (String)localObject);
      this.volleyClient.getList((String)localObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          MerchantMenuDetails.this.mProgressBar.setVisibility(8);
          Log.e(MerchantMenuDetails.TAG, "error get menuDetails " + paramAnonymousVolleyError);
        }
        
        public void onResponse(JSONArray paramAnonymousJSONArray)
        {
          Object localObject2;
          Object localObject1;
          Object localObject3;
          label59:
          label90:
          Type localType;
          if ((paramAnonymousJSONArray != null) && (paramAnonymousJSONArray.length() > 0))
          {
            localObject2 = MerchantMenuDetails.this.gson;
            if ((paramAnonymousJSONArray instanceof JSONArray)) {
              break label218;
            }
            localObject1 = paramAnonymousJSONArray.toString();
            localObject3 = new MerchantMenuDetails.2.1(this).getType();
            if ((localObject2 instanceof Gson)) {
              break label229;
            }
            localObject1 = ((Gson)localObject2).fromJson((String)localObject1, (Type)localObject3);
            localObject1 = (List)localObject1;
            localObject2 = MerchantMenuDetails.this;
            localObject3 = MerchantMenuDetails.this.gson;
            if ((paramAnonymousJSONArray instanceof JSONArray)) {
              break label243;
            }
            paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
            localType = new MerchantMenuDetails.2.2(this).getType();
            if ((localObject3 instanceof Gson)) {
              break label254;
            }
          }
          label218:
          label229:
          label243:
          label254:
          for (paramAnonymousJSONArray = ((Gson)localObject3).fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localObject3, paramAnonymousJSONArray, localType))
          {
            MerchantMenuDetails.access$902((MerchantMenuDetails)localObject2, (List)paramAnonymousJSONArray);
            if (localObject1 != null)
            {
              Log.e("ini list", "" + MerchantMenuDetails.this.listfood);
              MerchantMenuDetails.access$1002(MerchantMenuDetails.this, new MerchantMenuDetails.CustomBaseAdapter(MerchantMenuDetails.this, MerchantMenuDetails.this, (List)localObject1));
              MerchantMenuDetails.this.mLVMenuDetailList.setAdapter(MerchantMenuDetails.this.baseAdapter);
            }
            MerchantMenuDetails.this.mProgressBar.setVisibility(8);
            return;
            localObject1 = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
            break;
            localObject1 = GsonInstrumentation.fromJson((Gson)localObject2, (String)localObject1, (Type)localObject3);
            break label59;
            paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
            break label90;
          }
        }
      });
      return;
    }
  }
  
  private void initView()
  {
    this.mLVMenuDetailList = ((ListView)findViewById(2131624496));
    this.mTVManyAndCost = ((TextView)findViewById(2131624497));
    this.mTVReviewBtn = ((TextView)findViewById(2131624490));
    this.mProgressBar = ((ProgressBar)findViewById(2131624286));
    this.merchant = ((Merchant)getIntent().getParcelableExtra("MERCHANT"));
    this.isOpen = getIntent().getStringExtra("Merchant_OfficialHour");
    this.merchantId = this.merchant.id;
    this.volleyClient = VolleyClient.getInstance(this);
    this.statuspage = getIntent().getStringExtra("statuspage");
    if ((this.isOpen.equals("")) && (this.statuspage.equals("RecommendedDishesList"))) {
      getOfficialHour(this.merchantId);
    }
  }
  
  private Map<Integer, RouteItem> listToMap(List<RouteItem> paramList)
  {
    TreeMap localTreeMap = new TreeMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)paramList.next();
      localTreeMap.put(Integer.valueOf(localRouteItem.itemId), localRouteItem);
    }
    return localTreeMap;
  }
  
  private List<RouteItem> mapToList(Map<Integer, RouteItem> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    paramMap = paramMap.values().iterator();
    while (paramMap.hasNext()) {
      localArrayList.add((RouteItem)paramMap.next());
    }
    return localArrayList;
  }
  
  private void plusButton(TextView paramTextView, FoodItem paramFoodItem)
  {
    if ((this.isOpen.equals("true")) || (this.isOpen.equals("")) || (this.isOpen.equals("null")))
    {
      ((Addresses)this.mBookingData.addresses.get(0)).merchant = this.merchant;
      int i = Integer.parseInt(paramTextView.getText().toString()) + 1;
      paramTextView.setText(String.valueOf(i));
      this.tempCost += paramFoodItem.price;
      if (this.mapRouteItems.containsKey(Integer.valueOf(paramFoodItem.id))) {
        ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramFoodItem.id))).quantity = i;
      }
      for (;;)
      {
        this.tempQuantity += 1;
        setQuantityCost();
        return;
        paramTextView = new RouteItem();
        paramTextView.id = 0;
        paramTextView.price = paramFoodItem.price;
        paramTextView.itemId = paramFoodItem.id;
        paramTextView.itemName = paramFoodItem.name;
        paramTextView.quantity = i;
        this.mapRouteItems.put(Integer.valueOf(paramFoodItem.id), paramTextView);
      }
    }
    Util.confirmDialog(this, new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage) {}
    }, "Your restaurant is currently closed :(", "SORRY");
  }
  
  private void setQuantityCost()
  {
    this.mTVManyAndCost.setText(String.format(getString(2131165577), new Object[] { Integer.valueOf(this.tempQuantity), Util.getRupiahFormat(String.valueOf(this.tempCost)) }));
  }
  
  private boolean validateRouteItem()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((this.mapRouteItems.isEmpty()) && (this.otherRouteItems.isEmpty())) {
      localStringBuilder.append(getString(2131165698));
    }
    if (!localStringBuilder.toString().equals(""))
    {
      Toast.makeText(this, localStringBuilder.toString(), 0).show();
      return false;
    }
    return true;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 111) && (paramInt2 == -1))
    {
      this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      List localList = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
      this.otherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
      if (localList != null)
      {
        this.mapRouteItems = listToMap(localList);
        if (this.baseAdapter != null) {
          this.baseAdapter.notifyDataSetChanged();
        }
        this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
        this.tempCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
        setQuantityCost();
      }
    }
  }
  
  public void onBackPressed()
  {
    Object localObject = mapToList(this.mapRouteItems);
    Addresses localAddresses = (Addresses)this.mBookingData.addresses.get(0);
    localAddresses.routeItems = ((List)localObject);
    localAddresses.foodQuantityTotal = this.tempQuantity;
    localAddresses.foodCostTotal = this.tempCost;
    localObject = new Intent();
    ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingData);
    ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    setResult(-1, (Intent)localObject);
    if (this.statuspage.equals("RecommendedDishesList"))
    {
      Log.i("statuspage ", this.statuspage);
      localObject = new Intent(getApplicationContext(), MerchantMenu.class);
      ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingData);
      ((Intent)localObject).putExtra("MERCHANT", this.merchant);
      ((Intent)localObject).putExtra("statuspage", this.statuspage);
      ((Intent)localObject).putExtra("POI_LAT_LNG", this.merchant.latLong);
      ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
      startActivityForResult((Intent)localObject, 101);
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968695);
    initView();
    init();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  class CustomBaseAdapter
    extends BaseAdapter
  {
    private int intShowPosition = -1;
    private List<FoodItem> mFoodItems;
    private LayoutInflater mlayoutInflater;
    
    public CustomBaseAdapter(List<FoodItem> paramList)
    {
      Object localObject;
      this$1 = (MerchantMenuDetails)localObject;
      if (localObject == null) {
        this$1 = new ArrayList();
      }
      this.mFoodItems = MerchantMenuDetails.this;
      this.mlayoutInflater = ((LayoutInflater)paramList.getSystemService("layout_inflater"));
    }
    
    public int getCount()
    {
      return this.mFoodItems.size();
    }
    
    public FoodItem getItem(int paramInt)
    {
      return (FoodItem)this.mFoodItems.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return ((FoodItem)this.mFoodItems.get(paramInt)).id;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      FoodItem localFoodItem;
      if (paramView == null)
      {
        paramView = this.mlayoutInflater.inflate(2130968696, null);
        paramViewGroup = new MerchantMenuDetails.ViewHolder(MerchantMenuDetails.this);
        paramViewGroup.mLLRowItem = ((RelativeLayout)paramView.findViewById(2131624498));
        paramViewGroup.mLLAddNote = ((LinearLayout)paramView.findViewById(2131624506));
        paramViewGroup.mLLItemNote = ((LinearLayout)paramView.findViewById(2131624507));
        paramViewGroup.mETItemNote = ((EditText)paramView.findViewById(2131624508));
        paramViewGroup.itemName = ((TextView)paramView.findViewById(2131624500));
        paramViewGroup.itemDescription = ((TextView)paramView.findViewById(2131624501));
        paramViewGroup.itemPrice = ((TextView)paramView.findViewById(2131624502));
        paramViewGroup.plusBtn = ((TextView)paramView.findViewById(2131624505));
        paramViewGroup.itemQuantity = ((TextView)paramView.findViewById(2131624504));
        paramViewGroup.minusBtn = ((TextView)paramView.findViewById(2131624503));
        paramViewGroup.mutableWatcher = new MerchantMenuDetails.MutableWatcher(MerchantMenuDetails.this);
        paramViewGroup.mETItemNote.addTextChangedListener(paramViewGroup.mutableWatcher);
        paramViewGroup.listDivider = paramView.findViewById(2131624509);
        paramView.setTag(paramViewGroup);
        localFoodItem = getItem(paramInt);
        Object localObject = new MerchantMenuDetails.CustomBaseAdapter.1(this, paramViewGroup);
        paramViewGroup.mLLAddNote.setOnClickListener((View.OnClickListener)localObject);
        localObject = new MerchantMenuDetails.CustomBaseAdapter.2(this, paramViewGroup, localFoodItem, paramInt);
        paramViewGroup.plusBtn.setOnClickListener((View.OnClickListener)localObject);
        paramViewGroup.minusBtn.setOnClickListener((View.OnClickListener)localObject);
        paramViewGroup.itemName.setText(localFoodItem.name);
        paramViewGroup.itemDescription.setVisibility(0);
        if ((!Util.isTextNotNullEmpty(localFoodItem.description)) || (localFoodItem.description.trim().length() <= 0)) {
          break label608;
        }
        paramViewGroup.itemDescription.setText(localFoodItem.description);
      }
      for (;;)
      {
        paramViewGroup.itemPrice.setText(Util.getRupiahFormat(localFoodItem.price + ""));
        paramViewGroup.itemQuantity.setText(String.valueOf(0));
        paramViewGroup.mLLItemNote.setVisibility(8);
        paramViewGroup.mLLRowItem.setBackgroundColor(MerchantMenuDetails.this.getResources().getColor(2131558630));
        paramViewGroup.mutableWatcher.setActive(false);
        if ((MerchantMenuDetails.this.mapRouteItems.containsKey(Integer.valueOf(localFoodItem.id))) && (!MerchantMenuDetails.this.isDifferent)) {
          paramViewGroup.itemQuantity.setText(String.valueOf(((RouteItem)MerchantMenuDetails.this.mapRouteItems.get(Integer.valueOf(localFoodItem.id))).quantity));
        }
        if (!paramViewGroup.itemQuantity.getText().toString().equals("0"))
        {
          paramViewGroup.mLLRowItem.setBackgroundColor(MerchantMenuDetails.this.getResources().getColor(2131558501));
          paramViewGroup.mLLItemNote.setVisibility(0);
          paramViewGroup.mETItemNote.setText(((RouteItem)MerchantMenuDetails.this.mapRouteItems.get(Integer.valueOf(localFoodItem.id))).notes);
          if (!Util.isTextNotNullEmpty(paramViewGroup.mETItemNote.getText().toString())) {
            paramViewGroup.mLLItemNote.setVisibility(8);
          }
          paramViewGroup.mutableWatcher.setPosition(paramInt);
          paramViewGroup.mutableWatcher.setActive(true);
        }
        paramViewGroup.listDivider.setVisibility(8);
        return paramView;
        paramViewGroup = (MerchantMenuDetails.ViewHolder)paramView.getTag();
        break;
        label608:
        paramViewGroup.itemDescription.setText(MerchantMenuDetails.this.getResources().getString(2131165578));
      }
    }
  }
  
  class MutableWatcher
    implements TextWatcher
  {
    private boolean mActive;
    private int mPosition;
    
    MutableWatcher() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      if ((this.mActive) && (MerchantMenuDetails.this.mapRouteItems.containsKey(Integer.valueOf(((FoodItem)MerchantMenuDetails.this.baseAdapter.getItem(this.mPosition)).id)))) {
        ((RouteItem)MerchantMenuDetails.this.mapRouteItems.get(Integer.valueOf(((FoodItem)MerchantMenuDetails.this.baseAdapter.getItem(this.mPosition)).id))).notes = paramEditable.toString();
      }
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    void setActive(boolean paramBoolean)
    {
      this.mActive = paramBoolean;
    }
    
    void setPosition(int paramInt)
    {
      this.mPosition = paramInt;
    }
  }
  
  class ViewHolder
  {
    TextView itemDescription;
    TextView itemName;
    TextView itemPrice;
    TextView itemQuantity;
    View listDivider;
    EditText mETItemNote;
    LinearLayout mLLAddNote;
    LinearLayout mLLItemNote;
    RelativeLayout mLLRowItem;
    TextView minusBtn;
    MerchantMenuDetails.MutableWatcher mutableWatcher;
    TextView plusBtn;
    
    ViewHolder() {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/MerchantMenuDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */