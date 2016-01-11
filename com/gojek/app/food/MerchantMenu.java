package com.gojek.app.food;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.MenuCategory;
import com.gojek.app.model.OfficialHourModel;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.parcelable.myRestaurant;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MerchantMenu
  extends GojekActivityBase
{
  public static final String TAG = MerchantMenu.class.getSimpleName();
  private final int REQUEST_ITEMS = 12;
  private final int REQUEST_OTHER = 11;
  private List<MenuCategory> categoryList;
  private Gson gson = new Gson();
  private String hariini;
  private ImageLoader imageLoader;
  private boolean isOpen;
  private BookingStatus mBookingData;
  private ImageView mIMMerchnatImgGradient;
  private ImageView mIVCall;
  private ImageView mIVMerchantClose;
  private ImageView mIVMerchantImg;
  private ImageView mIVPinMap;
  private ListView mLVMenuCategory;
  private ProgressBar mProgressBar;
  private RelativeLayout mRLReviewCost;
  private TextView mTVMerchantDistance;
  private TextView mTVMerchantName;
  private TextView mTVMerchantPlace;
  private TextView mTVQuantityCost;
  private TextView mTVReviewBtn;
  private ArrayAdapter<MenuCategory> menuAdapter;
  private Merchant merchant;
  private String merchant_address;
  private int merchant_id;
  private String merchant_imgLocation;
  private String merchant_name;
  private myRestaurant mymerchant;
  private OfficialHourModel officialhourNow;
  private ArrayList<RouteItem> otherItems;
  private String statuspage;
  private VolleyClient volleyClient;
  private String waktubuka;
  private String waktututup;
  
  private void callMerchant(final String paramString)
  {
    Util.confirmDialog(this, new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        try
        {
          paramAnonymousMessage = new Intent("android.intent.action.DIAL");
          paramAnonymousMessage.setData(Uri.parse("tel:" + paramString));
          MerchantMenu.this.startActivity(paramAnonymousMessage);
          return;
        }
        catch (Exception paramAnonymousMessage) {}
      }
    }, null, paramString, null, "Call", "Cancel");
  }
  
  private MenuCategory createOtherCategory()
  {
    MenuCategory localMenuCategory = new MenuCategory();
    localMenuCategory.setId(-1);
    localMenuCategory.setName(getString(2131165668));
    localMenuCategory.setCode("Input_Order");
    localMenuCategory.setMerchantId(this.merchant.getId());
    return localMenuCategory;
  }
  
  private String getDay(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "SUNDAY";
    case 2: 
      return "MONDAY";
    case 3: 
      return "TUESDAY";
    case 4: 
      return "WEDNESDAY";
    case 5: 
      return "THURSDAY";
    case 6: 
      return "FRIDAY";
    }
    return "SATURDAY";
  }
  
  private void getOfficialHour()
  {
    Object localObject = String.format("https://api.gojek.co.id/gojek/merchant/%s", new Object[] { Integer.valueOf(this.merchant.id) });
    Log.i(TAG, "request menu category to " + (String)localObject);
    localObject = new JsonObjectRequest(0, (String)localObject, new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(JSONObject paramAnonymousJSONObject)
      {
        for (;;)
        {
          int i;
          try
          {
            paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONArray("listOperationalHour");
            i = 0;
            if (i >= paramAnonymousJSONObject.length()) {
              break;
            }
            Object localObject = paramAnonymousJSONObject.getJSONObject(i);
            String str = ((JSONObject)localObject).getString("dayOfWeek");
            if (!str.equals(this.val$dayNow)) {
              break label333;
            }
            MerchantMenu.access$1202(MerchantMenu.this, str);
            MerchantMenu.access$1502(MerchantMenu.this, new OfficialHourModel(((JSONObject)localObject).getInt("id"), ((JSONObject)localObject).getInt("merchantId"), ((JSONObject)localObject).getString("dayOfWeek"), ((JSONObject)localObject).getString("openTime"), ((JSONObject)localObject).getString("closeTime")));
            Log.d("official hour now", MerchantMenu.this.officialhourNow.openTime + " " + MerchantMenu.this.officialhourNow.closeTime);
            if ((Util.isTextNotNullEmpty(MerchantMenu.this.officialhourNow.getOpenTime())) && (Util.isTextNotNullEmpty(MerchantMenu.this.officialhourNow.getCloseTime())))
            {
              paramAnonymousJSONObject = MerchantMenu.this.officialhourNow.getOpenTime();
              localObject = MerchantMenu.this.officialhourNow.getCloseTime();
              MerchantMenu.access$1302(MerchantMenu.this, paramAnonymousJSONObject);
              MerchantMenu.access$1402(MerchantMenu.this, (String)localObject);
              Log.i(MerchantMenu.TAG, "openTime " + paramAnonymousJSONObject + " - closeTime " + (String)localObject);
              MerchantMenu.access$302(MerchantMenu.this, Util.isBetweenHour(paramAnonymousJSONObject, (String)localObject));
              if (MerchantMenu.this.isOpen)
              {
                MerchantMenu.this.mIVMerchantClose.setVisibility(8);
                return;
              }
              MerchantMenu.this.mIVMerchantClose.setVisibility(0);
              return;
            }
          }
          catch (JSONException paramAnonymousJSONObject)
          {
            Toast.makeText(MerchantMenu.this.getApplicationContext(), paramAnonymousJSONObject.toString(), 1).show();
            return;
          }
          MerchantMenu.this.mIVMerchantClose.setVisibility(8);
          return;
          label333:
          i += 1;
        }
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError) {}
    });
    VolleyClient.getInstance(getApplicationContext()).addToRequestQueue((Request)localObject);
  }
  
  private void goToOtherItem()
  {
    if (this.isOpen)
    {
      Intent localIntent = new Intent(this, OtherItem.class);
      localIntent.putExtra("MERCHANT", this.merchant);
      localIntent.putExtra("ITEM_CATEGORY", -1);
      localIntent.putExtra("ITEM_CATEGORY_NAME", "Other_Item");
      localIntent.putExtra("POI_LAT_LNG", getIntent().getStringExtra("POI_LAT_LNG"));
      localIntent.putExtra("BOOKING_DATA", this.mBookingData);
      localIntent.putExtra("statuspage", "MerchantMenu");
      localIntent.putExtra("Merchant_OfficialHour", this.isOpen + "");
      localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
      startActivityForResult(localIntent, 11);
      return;
    }
    Util.confirmDialog(this, new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage) {}
    }, "Your restaurant is currently closed :(", "SORRY");
  }
  
  private void init()
  {
    this.volleyClient = VolleyClient.getInstance(this);
    this.otherItems = new ArrayList();
    this.categoryList = new ArrayList();
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.merchant = ((Merchant)getIntent().getParcelableExtra("MERCHANT"));
    this.otherItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    setTitle(this.merchant.name);
    Log.i("merchant", this.merchant + "");
    this.statuspage = getIntent().getStringExtra("statuspage");
    setQuantityCost();
    this.imageLoader = ImageLoader.getInstance();
    Object localObject = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(Util.getDisplayImageConfig()).build();
    this.imageLoader.init((ImageLoaderConfiguration)localObject);
    getOfficialHour();
    Log.i(TAG, "request for logo for " + this.merchant.getName() + " image : " + this.merchant.getImgLocation());
    if (Util.isTextNotNullEmpty(this.merchant.getImgLocation())) {
      this.imageLoader.displayImage(this.merchant.imgLocation, this.mIVMerchantImg, Util.getDisplayImageConfig());
    }
    this.mTVMerchantName.setText(this.merchant.getName());
    this.mTVMerchantPlace.setText(this.merchant.getAddress());
    if (this.merchant.getDistance() > 0.0D) {
      this.mTVMerchantDistance.setText(Util.textDecimalFormat(this.merchant.getDistance()) + " KM");
    }
    for (;;)
    {
      this.menuAdapter = new ArrayAdapter(this, 2130968697, this.categoryList)
      {
        public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
        {
          paramAnonymousViewGroup = paramAnonymousView;
          if (paramAnonymousView == null) {
            paramAnonymousViewGroup = MerchantMenu.this.getLayoutInflater().inflate(2130968697, null);
          }
          paramAnonymousView = (MenuCategory)getItem(paramAnonymousInt);
          ((TextView)paramAnonymousViewGroup.findViewById(2131624510)).setText(paramAnonymousView.name);
          return paramAnonymousViewGroup;
        }
      };
      this.mLVMenuCategory.setAdapter(this.menuAdapter);
      this.mLVMenuCategory.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (paramAnonymousInt != MerchantMenu.this.menuAdapter.getCount() - 1)
          {
            paramAnonymousAdapterView = (MenuCategory)MerchantMenu.this.menuAdapter.getItem(paramAnonymousInt);
            paramAnonymousView = new Intent(MerchantMenu.this.getApplicationContext(), MerchantMenuDetails.class);
            paramAnonymousView.putExtra("MERCHANT", MerchantMenu.this.merchant);
            paramAnonymousView.putExtra("ITEM_CATEGORY", paramAnonymousAdapterView.code);
            paramAnonymousView.putExtra("ITEM_CATEGORY_NAME", paramAnonymousAdapterView.name);
            paramAnonymousView.putExtra("POI_LAT_LNG", MerchantMenu.this.getIntent().getStringExtra("POI_LAT_LNG"));
            paramAnonymousView.putExtra("BOOKING_DATA", MerchantMenu.this.mBookingData);
            paramAnonymousView.putExtra("statuspage", "MerchantMenu");
            paramAnonymousView.putExtra("Merchant_OfficialHour", MerchantMenu.this.isOpen + "");
            paramAnonymousView.putParcelableArrayListExtra("OTHER_ITEM", MerchantMenu.this.otherItems);
            MerchantMenu.this.startActivityForResult(paramAnonymousView, 12);
            return;
          }
          MerchantMenu.this.goToOtherItem();
        }
      });
      this.mProgressBar.setVisibility(0);
      localObject = String.format("https://api.gojek.co.id/gojek/item-category/find?merchantId=%1s", new Object[] { Integer.valueOf(this.merchant.id), "", "" });
      Log.i(TAG, "request menu category to " + (String)localObject);
      this.volleyClient.getList((String)localObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(MerchantMenu.TAG, "error get list menu " + paramAnonymousVolleyError);
          MerchantMenu.this.mProgressBar.setVisibility(8);
        }
        
        public void onResponse(JSONArray paramAnonymousJSONArray)
        {
          Gson localGson;
          Type localType;
          if ((paramAnonymousJSONArray != null) && (paramAnonymousJSONArray.length() > 0))
          {
            localGson = MerchantMenu.this.gson;
            if ((paramAnonymousJSONArray instanceof JSONArray)) {
              break label132;
            }
            paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
            localType = new MerchantMenu.3.1(this).getType();
            if ((localGson instanceof Gson)) {
              break label143;
            }
          }
          label132:
          label143:
          for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
          {
            paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
            if ((paramAnonymousJSONArray != null) && (paramAnonymousJSONArray.size() != 0)) {
              MerchantMenu.this.categoryList.addAll(paramAnonymousJSONArray);
            }
            MerchantMenu.this.categoryList.add(MerchantMenu.this.createOtherCategory());
            MerchantMenu.this.menuAdapter.notifyDataSetChanged();
            MerchantMenu.this.mProgressBar.setVisibility(8);
            return;
            paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
            break;
          }
        }
      });
      this.mIVCall.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Util.isTextNotNullEmpty(MerchantMenu.this.merchant.getPhone())) {
            MerchantMenu.this.callMerchant(MerchantMenu.this.merchant.getPhone());
          }
        }
      });
      this.mIVPinMap.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(MerchantMenu.this, MerchantMap.class);
          paramAnonymousView.putExtra("MERCHANT", MerchantMenu.this.merchant);
          MerchantMenu.this.startActivity(paramAnonymousView);
        }
      });
      this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (MerchantMenu.this.validateRouteItem())
          {
            paramAnonymousView = new Intent(MerchantMenu.this.getApplicationContext(), FoodReview.class);
            paramAnonymousView.putExtra("BOOKING_DATA", MerchantMenu.this.mBookingData);
            paramAnonymousView.putExtra("MERCHANT", MerchantMenu.this.merchant);
            paramAnonymousView.putParcelableArrayListExtra("OTHER_ITEM", MerchantMenu.this.otherItems);
            MerchantMenu.this.startActivityForResult(paramAnonymousView, 111);
          }
        }
      });
      this.mIMMerchnatImgGradient.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = MerchantMenu.this.getSupportFragmentManager();
          MerchantDescriptionDialogBox.newInstance(MerchantMenu.this.merchant, MerchantMenu.this.hariini, MerchantMenu.this.waktubuka, MerchantMenu.this.waktututup).show(paramAnonymousView, "description box");
        }
      });
      return;
      this.mTVMerchantDistance.setVisibility(8);
    }
  }
  
  private void initView()
  {
    this.mLVMenuCategory = ((ListView)findViewById(2131624487));
    this.mIVMerchantImg = ((ImageView)findViewById(2131624479));
    this.mTVMerchantName = ((TextView)findViewById(2131624483));
    this.mTVMerchantPlace = ((TextView)findViewById(2131624484));
    this.mIMMerchnatImgGradient = ((ImageView)findViewById(2131624480));
    this.mIVCall = ((ImageView)findViewById(2131624486));
    this.mIVPinMap = ((ImageView)findViewById(2131624482));
    this.mProgressBar = ((ProgressBar)findViewById(2131624286));
    this.mRLReviewCost = ((RelativeLayout)findViewById(2131624488));
    this.mTVQuantityCost = ((TextView)findViewById(2131624489));
    this.mTVReviewBtn = ((TextView)findViewById(2131624490));
    this.mTVMerchantDistance = ((TextView)findViewById(2131624485));
    this.mIVMerchantClose = ((ImageView)findViewById(2131624481));
    this.mIVMerchantClose.setVisibility(8);
  }
  
  private void setQuantityCost()
  {
    int i = (int)(50.0F * getResources().getDisplayMetrics().density + 0.5F);
    if ((!this.mBookingData.addresses.isEmpty()) && (((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal != 0))
    {
      this.mLVMenuCategory.setPadding(0, 0, 0, i);
      this.mRLReviewCost.setVisibility(0);
      this.mTVQuantityCost.setText(String.format(getString(2131165577), new Object[] { Integer.valueOf(((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal), Util.getRupiahFormat(String.valueOf(((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal)) }));
      return;
    }
    this.mLVMenuCategory.setPadding(0, 0, 0, 0);
    this.mRLReviewCost.setVisibility(8);
  }
  
  private boolean validateRouteItem()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((this.otherItems.isEmpty()) && (((Addresses)this.mBookingData.addresses.get(0)).routeItems.isEmpty())) {
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
    if ((paramInt2 == -1) && (paramInt1 == 12))
    {
      this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      this.otherItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
      setQuantityCost();
    }
    if ((paramInt2 == -1) && (paramInt1 == 11))
    {
      this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      this.otherItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
      setQuantityCost();
    }
    for (;;)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      if ((paramInt2 == -1) && (paramInt1 == 111))
      {
        this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
        this.otherItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
        setQuantityCost();
      }
    }
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
    setResult(-1, localIntent);
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968693);
    initView();
    init();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/MerchantMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */