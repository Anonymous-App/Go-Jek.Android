package com.gojek.app.food;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.adapter.food.RecommendedDishesListAdapter;
import com.gojek.app.custom.EndlessScrollListener;
import com.gojek.app.custom.FloatingActionButton;
import com.gojek.app.custom.FloatingActionButton.Builder;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.OfficialHourModel;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.parcelable.recommendedDishes;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendedDishesList
  extends GojekActivityBase
{
  public static final String TAG = RecommendedDishesList.class.getSimpleName();
  private String category;
  private FloatingActionButton fabButton;
  private Gson gson = new Gson();
  private ImageLoader imageLoader;
  private boolean isOpen = false;
  private boolean isSwipe;
  private String location;
  private BookingStatus mBookingData;
  private TextView mETSearch;
  private LinearLayout mLLSearchBar;
  private SwipeRefreshLayout mPullRefresh;
  private RelativeLayout mRLSuggestMerchant;
  private TextView mTVCommingSoon;
  private ArrayList<Merchant> merchantList;
  private OfficialHourModel officialhourNow;
  private ArrayList<RouteItem> otherItems;
  private ProgressBar progressBar;
  private RecommendedDishesListAdapter recDishesAdapter;
  private ArrayList<recommendedDishes> recDishesList;
  private ListView recDishesListView;
  private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener()
  {
    public void onRefresh()
    {
      RecommendedDishesList.access$802(RecommendedDishesList.this, true);
      RecommendedDishesList.this.doRecommendedDishesByLocation();
    }
  };
  private VolleyClient volleyClient;
  
  private void doLoadMoreRecommendedDishesByLocation(final int paramInt)
  {
    if (this.location == null) {
      return;
    }
    String str = String.format("https://api.gojek.co.id/gojek/recommended/dishes?location=%1s&page=%d&limit=%d", new Object[] { this.location, Integer.valueOf(paramInt), Integer.valueOf(32) });
    Log.i(TAG, "request recDishes " + str);
    VolleyClient.getInstance(this).getList(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(RecommendedDishesList.TAG, "doLoadMerchantByCategory() got error " + paramAnonymousVolleyError);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson = RecommendedDishesList.this.gson;
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new RecommendedDishesList.8.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label98;
          }
        }
        label98:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.isEmpty())) {
            break label111;
          }
          RecommendedDishesList.this.recDishesList.addAll(paramAnonymousJSONArray);
          RecommendedDishesList.this.recDishesAdapter.notifyDataSetChanged();
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
        label111:
        Log.i(RecommendedDishesList.TAG, "no recommended dishes found at page " + paramInt);
      }
    });
  }
  
  private void doRecommendedDishesByLocation()
  {
    if (!this.isSwipe) {
      this.progressBar.setVisibility(0);
    }
    this.fabButton.setVisibility(8);
    if (this.location == null)
    {
      this.progressBar.setVisibility(8);
      this.mTVCommingSoon.setVisibility(0);
      this.mTVCommingSoon.setText(getString(2131165694));
      return;
    }
    String str = String.format("https://api.gojek.co.id/gojek/recommended/dishes?location=%1s&page=%d&limit=%d", new Object[] { this.location, Integer.valueOf(0), Integer.valueOf(100) });
    Log.i(TAG, "request recDishes " + str);
    VolleyClient.getInstance(this).getList(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(RecommendedDishesList.TAG, "doLoadMerchantByCategory() got error " + paramAnonymousVolleyError);
        if ((RecommendedDishesList.this.recDishesList == null) || (RecommendedDishesList.this.recDishesList.size() <= 0))
        {
          if ((paramAnonymousVolleyError.getMessage() == null) || (!paramAnonymousVolleyError.getMessage().contains("No address associated with hostname"))) {
            break label154;
          }
          RecommendedDishesList.this.mTVCommingSoon.setText(RecommendedDishesList.this.getString(2131165695));
          RecommendedDishesList.this.mTVCommingSoon.setVisibility(0);
        }
        for (;;)
        {
          RecommendedDishesList.this.progressBar.setVisibility(8);
          RecommendedDishesList.this.recDishesListView.setVisibility(0);
          RecommendedDishesList.this.mPullRefresh.setRefreshing(false);
          RecommendedDishesList.access$802(RecommendedDishesList.this, false);
          RecommendedDishesList.this.fabButton.setVisibility(8);
          return;
          label154:
          RecommendedDishesList.this.mTVCommingSoon.setText(RecommendedDishesList.this.getString(2131165693));
          RecommendedDishesList.this.mTVCommingSoon.setVisibility(0);
        }
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson = RecommendedDishesList.this.gson;
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new RecommendedDishesList.7.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label153;
          }
        }
        label153:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          RecommendedDishesList.this.progressBar.setVisibility(8);
          RecommendedDishesList.this.mPullRefresh.setRefreshing(false);
          RecommendedDishesList.access$802(RecommendedDishesList.this, false);
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.isEmpty())) {
            break label166;
          }
          RecommendedDishesList.this.mTVCommingSoon.setVisibility(8);
          RecommendedDishesList.this.recDishesList.addAll(paramAnonymousJSONArray);
          RecommendedDishesList.this.recDishesAdapter.notifyDataSetChanged();
          RecommendedDishesList.this.fabButton.setVisibility(0);
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
        label166:
        RecommendedDishesList.this.fabButton.setVisibility(8);
        RecommendedDishesList.this.mTVCommingSoon.setVisibility(0);
        RecommendedDishesList.this.mTVCommingSoon.setText(RecommendedDishesList.this.getString(2131165693));
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
          RecommendedDishesList.this.resetBookingData();
          RecommendedDishesList.this.gotoMerchantMenu(paramMerchant);
        }
      }, null, getString(2131165898), "Warning");
      return;
    }
    gotoMerchantMenu(paramMerchant);
  }
  
  private void getMerchantById(int paramInt, final recommendedDishes paramrecommendedDishes)
  {
    String str = String.format("https://api.gojek.co.id/gojek/merchant/%s", new Object[] { Integer.valueOf(paramInt) });
    Log.i("getMerchantById", "request merchantById " + str);
    VolleyClient.getInstance(this).get(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(RecommendedDishesList.TAG, "getMerchantById() got error " + paramAnonymousVolleyError);
      }
      
      public void onResponse(Merchant paramAnonymousMerchant)
      {
        paramAnonymousMerchant.distance = paramrecommendedDishes.distance.doubleValue();
        Log.i(RecommendedDishesList.TAG, "recDishes merchantid " + paramAnonymousMerchant.name);
        RecommendedDishesList.this.gotoMenuMerchantDetail(paramAnonymousMerchant, paramrecommendedDishes);
      }
    }, Merchant.class);
  }
  
  private void gotoMenuMerchantDetail(final Merchant paramMerchant, final recommendedDishes paramrecommendedDishes)
  {
    if ((((Addresses)this.mBookingData.addresses.get(0)).merchant.getId() != 0) && (paramMerchant.getId() != ((Addresses)this.mBookingData.addresses.get(0)).merchant.getId()) && ((((Addresses)this.mBookingData.addresses.get(0)).routeItems.size() > 0) || (this.otherItems.size() > 0)))
    {
      Util.confirmDialog(this, new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          RecommendedDishesList.this.resetBookingData();
          RecommendedDishesList.this.getShoppingItemName(paramMerchant, paramrecommendedDishes);
        }
      }, null, getString(2131165898), "Warning");
      return;
    }
    getShoppingItemName(paramMerchant, paramrecommendedDishes);
  }
  
  private void gotoMerchantMenu(Merchant paramMerchant)
  {
    Intent localIntent = new Intent(getApplicationContext(), MerchantMenu.class);
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putExtra("MERCHANT", paramMerchant);
    localIntent.putExtra("POI_LAT_LNG", paramMerchant.latLong);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
    startActivityForResult(localIntent, 101);
  }
  
  private void init(Bundle paramBundle)
  {
    this.otherItems = new ArrayList();
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.otherItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    this.imageLoader = ImageLoader.getInstance();
    paramBundle = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(Util.getDisplayImageConfig()).build();
    this.imageLoader.init(paramBundle);
    paramBundle = new Addresses();
    if (this.mBookingData != null) {
      this.mBookingData.addresses.add(paramBundle);
    }
    this.mPullRefresh.setEnabled(true);
    this.mPullRefresh.setOnRefreshListener(this.refreshListener);
    this.mPullRefresh.setColorSchemeResources(new int[] { 2131558495, 2131558493, 2131558495, 2131558493 });
    this.category = getIntent().getStringExtra("CATEGORY");
    this.location = getIntent().getStringExtra("LAST_LOCATION");
    setTitle(getString(2131165764));
    if (this.category != null) {
      setTitle(getIntent().getStringExtra("CATEGORY_NAME"));
    }
    paramBundle = getIntent().getStringExtra("MERCHANT_NAME");
    if (paramBundle != null) {
      this.mETSearch.setText(paramBundle);
    }
    this.mLLSearchBar.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(RecommendedDishesList.this, MerchantSearch.class);
        paramAnonymousView.putExtra("MERCHANT_NAME", "");
        paramAnonymousView.putExtra("LAST_LOCATION", RecommendedDishesList.this.location);
        paramAnonymousView.putExtra("BOOKING_DATA", RecommendedDishesList.this.mBookingData);
        RecommendedDishesList.this.startActivity(paramAnonymousView);
      }
    });
    this.merchantList = new ArrayList();
    this.recDishesList = new ArrayList();
    this.recDishesAdapter = new RecommendedDishesListAdapter(this, this.recDishesList);
    this.recDishesListView.setAdapter(this.recDishesAdapter);
    this.recDishesListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        boolean bool2 = false;
        boolean bool1 = bool2;
        if (RecommendedDishesList.this.recDishesListView != null)
        {
          bool1 = bool2;
          if (RecommendedDishesList.this.recDishesListView.getChildCount() > 0)
          {
            if (RecommendedDishesList.this.recDishesListView.getFirstVisiblePosition() != 0) {
              break label92;
            }
            paramAnonymousInt1 = 1;
            if (RecommendedDishesList.this.recDishesListView.getChildAt(0).getTop() != 0) {
              break label97;
            }
            paramAnonymousInt2 = 1;
            label68:
            if ((paramAnonymousInt1 == 0) || (paramAnonymousInt2 == 0)) {
              break label102;
            }
          }
        }
        label92:
        label97:
        label102:
        for (bool1 = true;; bool1 = false)
        {
          RecommendedDishesList.this.mPullRefresh.setEnabled(bool1);
          return;
          paramAnonymousInt1 = 0;
          break;
          paramAnonymousInt2 = 0;
          break label68;
        }
      }
      
      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt) {}
    });
    this.fabButton = new FloatingActionButton.Builder(this).withDrawable(getResources().getDrawable(2130837930)).withButtonColor(-7829368).withGravity(85).withButtonSize(40).withMargins(0, 0, 10, 50).create();
    this.fabButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RecommendedDishesList.this.recDishesListView.smoothScrollToPosition(0);
      }
    });
    this.fabButton.setVisibility(8);
    doRecommendedDishesByLocation();
    this.recDishesListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = RecommendedDishesList.this.recDishesAdapter.getItem(paramAnonymousInt);
        RecommendedDishesList.this.getMerchantById(paramAnonymousAdapterView.merchantId, paramAnonymousAdapterView);
      }
    });
    this.mRLSuggestMerchant.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(RecommendedDishesList.this, MerchantSuggest.class);
        RecommendedDishesList.this.startActivity(paramAnonymousView);
      }
    });
    this.recDishesListView.setOnScrollListener(new EndlessScrollListener()
    {
      protected void onLoadMore(int paramAnonymousInt) {}
    });
  }
  
  private void initView()
  {
    this.volleyClient = VolleyClient.getInstance(this);
    this.recDishesListView = ((ListView)findViewById(2131624528));
    this.mLLSearchBar = ((LinearLayout)findViewById(2131624523));
    this.progressBar = ((ProgressBar)findViewById(2131624526));
    this.mETSearch = ((TextView)findViewById(2131624524));
    this.mTVCommingSoon = ((TextView)findViewById(2131624525));
    this.mRLSuggestMerchant = ((RelativeLayout)findViewById(2131624529));
    this.mPullRefresh = ((SwipeRefreshLayout)findViewById(2131624527));
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
  
  public void getShoppingItemName(final Merchant paramMerchant, final recommendedDishes paramrecommendedDishes)
  {
    String str = String.format("https://api.gojek.co.id/gojek/item-category/find?code=%1s&merchantId=%2s", new Object[] { paramrecommendedDishes.itemCategories.replace(" ", "%20"), Integer.valueOf(paramMerchant.id) });
    this.volleyClient.getList(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Toast.makeText(RecommendedDishesList.this.getApplicationContext(), "Sorry, something went wrong :(", 1).show();
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        if ((paramAnonymousJSONArray != null) && (paramAnonymousJSONArray.length() > 0)) {}
        try
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.getJSONObject(0).getString("name");
          Intent localIntent = new Intent(RecommendedDishesList.this.getApplicationContext(), MerchantMenuDetails.class);
          localIntent.putExtra("MERCHANT", paramMerchant);
          localIntent.putExtra("ITEM_CATEGORY", paramrecommendedDishes.getItemCategories());
          localIntent.putExtra("ITEM_CATEGORY_NAME", paramAnonymousJSONArray);
          localIntent.putExtra("POI_LAT_LNG", RecommendedDishesList.this.getIntent().getStringExtra("POI_LAT_LNG"));
          localIntent.putExtra("BOOKING_DATA", RecommendedDishesList.this.mBookingData);
          localIntent.putParcelableArrayListExtra("OTHER_ITEM", RecommendedDishesList.this.otherItems);
          localIntent.putExtra("statuspage", "RecommendedDishesList");
          localIntent.putExtra("Merchant_OfficialHour", "");
          RecommendedDishesList.this.startActivityForResult(localIntent, 101);
          return;
        }
        catch (JSONException paramAnonymousJSONArray)
        {
          Toast.makeText(RecommendedDishesList.this.getApplicationContext(), paramAnonymousJSONArray + "", 1).show();
        }
      }
    });
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 101) && (paramInt2 == -1))
    {
      this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      this.otherItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    }
    for (;;)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      if ((paramInt1 != 111) || (paramInt2 != -1)) {}
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968701);
    initView();
    init(paramBundle);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
    if (this.fabButton != null) {
      this.fabButton.setVisibility(8);
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
    if ((this.fabButton != null) && (this.recDishesList.size() > 0)) {
      this.fabButton.setVisibility(0);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/RecommendedDishesList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */