package com.gojek.app.food;

import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.adapter.food.CategoryListAdapter;
import com.gojek.app.custom.XTextView;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.Category;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.RouteItem;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class FoodCategoryList
  extends GojekActivityBase
{
  public static final String TAG = FoodCategoryList.class.getSimpleName();
  private String category;
  private ArrayList<Category> categoryList;
  private CategoryListAdapter categoryListAdapter;
  private boolean isSwipe = false;
  private String location;
  private BookingStatus mBookingData;
  private LinearLayout mLLSearchBar;
  private ListView mLVFoodCategory;
  private ProgressBar mProgressBar;
  private SwipeRefreshLayout mPullRefresh;
  private XTextView mTVNoInternetConnection;
  private ArrayList<RouteItem> otherItems;
  private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener()
  {
    public void onRefresh()
    {
      FoodCategoryList.access$602(FoodCategoryList.this, true);
      FoodCategoryList.this.doLoadCategory();
    }
  };
  
  private void doLoadCategory()
  {
    if (!this.isSwipe) {
      this.mProgressBar.setVisibility(0);
    }
    VolleyClient.getInstance(this).getList(TAG, "https://api.gojek.co.id/gojek/shopping-category/find?code=&serviceType=5", new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(FoodCategoryList.TAG, "loadCategory get error " + paramAnonymousVolleyError);
        if ((FoodCategoryList.this.categoryList == null) || (FoodCategoryList.this.categoryList.size() <= 0))
        {
          if ((paramAnonymousVolleyError.getMessage() == null) || (!paramAnonymousVolleyError.getMessage().contains("No address associated with hostname"))) {
            break label131;
          }
          FoodCategoryList.this.mTVNoInternetConnection.setText(FoodCategoryList.this.getString(2131165695));
          FoodCategoryList.this.mTVNoInternetConnection.setVisibility(0);
        }
        for (;;)
        {
          FoodCategoryList.this.mPullRefresh.setRefreshing(false);
          FoodCategoryList.access$602(FoodCategoryList.this, false);
          FoodCategoryList.this.mProgressBar.setVisibility(8);
          return;
          label131:
          FoodCategoryList.this.mTVNoInternetConnection.setText(FoodCategoryList.this.getString(2131165693));
          FoodCategoryList.this.mTVNoInternetConnection.setVisibility(0);
        }
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson = new Gson();
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new FoodCategoryList.5.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label152;
          }
          paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);
          label46:
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          FoodCategoryList.this.mProgressBar.setVisibility(8);
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.size() <= 0)) {
            break label165;
          }
          FoodCategoryList.this.mTVNoInternetConnection.setVisibility(8);
          FoodCategoryList.this.categoryList.clear();
          FoodCategoryList.this.categoryList.addAll(paramAnonymousJSONArray);
          FoodCategoryList.this.categoryListAdapter.notifyDataSetChanged();
        }
        for (;;)
        {
          FoodCategoryList.this.mPullRefresh.setRefreshing(false);
          FoodCategoryList.access$602(FoodCategoryList.this, false);
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
          label152:
          paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType);
          break label46;
          label165:
          FoodCategoryList.this.mTVNoInternetConnection.setText(FoodCategoryList.this.getString(2131165693));
          FoodCategoryList.this.mTVNoInternetConnection.setVisibility(0);
        }
      }
    });
  }
  
  private void init()
  {
    this.otherItems = new ArrayList();
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.category = getIntent().getStringExtra("CATEGORY");
    this.location = getIntent().getStringExtra("LAST_LOCATION");
    this.otherItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    Object localObject = getString(2131165764);
    if (this.category != null) {
      localObject = getIntent().getStringExtra("CATEGORY_NAME");
    }
    setTitle((CharSequence)localObject);
    localObject = new Addresses();
    if (this.mBookingData != null) {
      this.mBookingData.addresses.add(localObject);
    }
    this.mPullRefresh.setEnabled(true);
    this.mPullRefresh.setOnRefreshListener(this.refreshListener);
    this.mPullRefresh.setColorSchemeResources(new int[] { 2131558495, 2131558493, 2131558495, 2131558493 });
    this.categoryList = new ArrayList();
    this.categoryListAdapter = new CategoryListAdapter(this, this.categoryList);
    this.mLVFoodCategory.setAdapter(this.categoryListAdapter);
    this.mLVFoodCategory.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        boolean bool2 = false;
        boolean bool1 = bool2;
        if (FoodCategoryList.this.mLVFoodCategory != null)
        {
          bool1 = bool2;
          if (FoodCategoryList.this.mLVFoodCategory.getChildCount() > 0)
          {
            if (FoodCategoryList.this.mLVFoodCategory.getFirstVisiblePosition() != 0) {
              break label92;
            }
            paramAnonymousInt1 = 1;
            if (FoodCategoryList.this.mLVFoodCategory.getChildAt(0).getTop() != 0) {
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
          FoodCategoryList.this.mPullRefresh.setEnabled(bool1);
          return;
          paramAnonymousInt1 = 0;
          break;
          paramAnonymousInt2 = 0;
          break label68;
        }
      }
      
      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt) {}
    });
    doLoadCategory();
    this.mLVFoodCategory.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = FoodCategoryList.this.categoryListAdapter.getItem(paramAnonymousInt);
        paramAnonymousView = new Intent(FoodCategoryList.this, MerchantList.class);
        paramAnonymousView.putExtra("CATEGORY", paramAnonymousAdapterView.code);
        paramAnonymousView.putExtra("CATEGORY_NAME", paramAnonymousAdapterView.name);
        paramAnonymousView.putExtra("LAST_LOCATION", FoodCategoryList.this.location);
        paramAnonymousView.putExtra("BOOKING_DATA", FoodCategoryList.this.mBookingData);
        paramAnonymousView.putParcelableArrayListExtra("OTHER_ITEM", FoodCategoryList.this.otherItems);
        FoodCategoryList.this.startActivityForResult(paramAnonymousView, 101);
      }
    });
  }
  
  private void initView()
  {
    this.mTVNoInternetConnection = ((XTextView)findViewById(2131624451));
    this.mLVFoodCategory = ((ListView)findViewById(2131624459));
    this.mProgressBar = ((ProgressBar)findViewById(2131624286));
    this.mPullRefresh = ((SwipeRefreshLayout)findViewById(2131624450));
    this.mLLSearchBar = ((LinearLayout)findViewById(2131624458));
    this.mLLSearchBar.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FoodCategoryList.this.doSearchBar("", FoodCategoryList.this.location, FoodCategoryList.this.mBookingData);
      }
    });
  }
  
  public void doSearchBar(String paramString1, String paramString2, BookingStatus paramBookingStatus)
  {
    Intent localIntent = new Intent(this, MerchantSearch.class);
    localIntent.putExtra("MERCHANT_NAME", paramString1);
    localIntent.putExtra("LAST_LOCATION", paramString2);
    localIntent.putExtra("BOOKING_DATA", paramBookingStatus);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
    startActivityForResult(localIntent, 101);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      this.otherItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
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
    setContentView(2130968685);
    initView();
    init();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  public void onResume()
  {
    super.onResume();
  }
  
  public void onStop()
  {
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    super.onStop();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/FoodCategoryList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */