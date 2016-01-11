package com.gojek.app.food;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.gojek.app.custom.XTextView;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.Category;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FoodCategory
  extends FoodFragmentBase
  implements View.OnClickListener
{
  public static final String TAG = FoodCategory.class.getSimpleName();
  private int GET_CODE = 1;
  private int GET_NAME = 2;
  private List<Category> categoryList;
  private ImageLoader imageLoader;
  private ImageView imgBestSeller;
  private ImageView imgCategory;
  private ImageView imgMyRestaurant;
  private ImageView imgRecommendedDishes;
  private boolean isSwipe = false;
  private String locationString = null;
  private BookingStatus mBookingData;
  private ProgressBar mProgressBar;
  private SwipeRefreshLayout mPullRefresh;
  private XTextView mTVNoInternetConnection;
  private ArrayList<RouteItem> otherItems;
  private SwipeRefreshLayout.OnRefreshListener refreshListener = new FoodCategory.3(this);
  
  private void doLoadCategory()
  {
    if (!this.isSwipe) {
      this.mProgressBar.setVisibility(0);
    }
    VolleyClient.getInstance(getActivity()).getList(TAG, "https://api.gojek.co.id/gojek/shopping-category/find3", new FoodCategory.2(this));
  }
  
  private String getCodeNameValue(List<Category> paramList, String paramString, int paramInt)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Category localCategory = (Category)paramList.next();
      if (localCategory.code.equalsIgnoreCase(paramString))
      {
        if (paramInt == this.GET_CODE) {
          return localCategory.code;
        }
        return localCategory.name;
      }
    }
    return null;
  }
  
  private void initView(View paramView)
  {
    this.mTVNoInternetConnection = ((XTextView)paramView.findViewById(2131624451));
    this.imgBestSeller = ((ImageView)paramView.findViewById(2131624452));
    this.imgBestSeller.setEnabled(false);
    this.imgBestSeller.setTag("BEST_SELLER");
    this.imgBestSeller.setOnClickListener(this);
    this.imgRecommendedDishes = ((ImageView)paramView.findViewById(2131624453));
    this.imgRecommendedDishes.setEnabled(false);
    this.imgRecommendedDishes.setTag("RECOMMENDED_DISHES");
    this.imgRecommendedDishes.setOnClickListener(this);
    this.imgMyRestaurant = ((ImageView)paramView.findViewById(2131624454));
    this.imgMyRestaurant.setEnabled(false);
    this.imgMyRestaurant.setTag("MY_RESTAURANT");
    this.imgMyRestaurant.setOnClickListener(this);
    this.imgCategory = ((ImageView)paramView.findViewById(2131624455));
    this.imgCategory.setEnabled(false);
    this.imgCategory.setTag("CATEGORIES");
    this.imgCategory.setOnClickListener(this);
    this.mProgressBar = ((ProgressBar)paramView.findViewById(2131624286));
    this.mPullRefresh = ((SwipeRefreshLayout)paramView.findViewById(2131624450));
    this.mPullRefresh.setEnabled(true);
    this.mPullRefresh.setOnRefreshListener(this.refreshListener);
    this.mPullRefresh.setColorSchemeResources(new int[] { 2131558495, 2131558493, 2131558495, 2131558493 });
  }
  
  private void loadImage(String paramString, ImageView paramImageView)
  {
    paramImageView.setVisibility(0);
    paramImageView.setEnabled(true);
    if ((Util.isTextNotNullEmpty(paramString)) && (paramString.trim().length() > 0)) {
      Picasso.with(getActivity()).load(paramString).fit().transform(new RoundedTransformation(10, 0)).into(paramImageView);
    }
  }
  
  public int getLayoutId()
  {
    return 2130968683;
  }
  
  public void onClick(View paramView)
  {
    String str1 = null;
    String str2 = null;
    Intent localIntent = null;
    if (paramView.getTag().equals("BEST_SELLER"))
    {
      localIntent = new Intent(getActivity(), MerchantList.class);
      str1 = getCodeNameValue(this.categoryList, "BEST_SELLER", this.GET_CODE);
      str2 = getCodeNameValue(this.categoryList, "BEST_SELLER", this.GET_NAME);
    }
    for (;;)
    {
      if ((localIntent != null) && (str1 != null) && (str2 != null))
      {
        localIntent.putExtra("CATEGORY", str1);
        localIntent.putExtra("CATEGORY_NAME", str2);
        localIntent.putExtra("LAST_LOCATION", this.locationString);
        localIntent.putExtra("BOOKING_DATA", this.mBookingData);
        localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
        startActivityForResult(localIntent, 101);
      }
      return;
      if (paramView.getTag().equals("RECOMMENDED_DISHES"))
      {
        localIntent = new Intent(getActivity(), RecommendedDishesList.class);
        str1 = getCodeNameValue(this.categoryList, "RECOMMENDED_DISHES", this.GET_CODE);
        str2 = getCodeNameValue(this.categoryList, "RECOMMENDED_DISHES", this.GET_NAME);
      }
      else if (paramView.getTag().equals("MY_RESTAURANT"))
      {
        localIntent = new Intent(getActivity(), MerchantList.class);
        str1 = getCodeNameValue(this.categoryList, "MY_RESTAURANT", this.GET_CODE);
        str2 = getCodeNameValue(this.categoryList, "MY_RESTAURANT", this.GET_NAME);
      }
      else if (paramView.getTag().equals("CATEGORIES"))
      {
        localIntent = new Intent(getActivity(), FoodCategoryList.class);
        str1 = getCodeNameValue(this.categoryList, "CATEGORIES", this.GET_CODE);
        str2 = getCodeNameValue(this.categoryList, "CATEGORIES", this.GET_NAME);
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130968683, paramViewGroup, false);
  }
  
  public void onResume()
  {
    this.mBookingData = ((FoodHome)getActivity()).getBookingData();
    this.otherItems = ((FoodHome)getActivity()).getOtherItems();
    super.onResume();
  }
  
  public void onStop()
  {
    VolleyClient.getInstance(getActivity()).cancelAllRequest(TAG);
    super.onStop();
  }
  
  public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    paramBundle = ((FoodHome)getActivity()).getLastLocation();
    String str = ((FoodHome)getActivity()).getLocationString();
    if (str != null) {
      if (paramBundle == null) {
        this.locationString = str;
      }
    }
    for (;;)
    {
      if (this.locationString == null) {
        new Handler().postDelayed(new FoodCategory.1(this), 1000L);
      }
      this.otherItems = ((FoodHome)getActivity()).getOtherItems();
      this.mBookingData = ((FoodHome)getActivity()).getBookingData();
      initView(paramView);
      this.imageLoader = ImageLoader.getInstance();
      paramView = new ImageLoaderConfiguration.Builder(getActivity()).defaultDisplayImageOptions(Util.getDisplayImageConfig()).build();
      this.imageLoader.init(paramView);
      this.categoryList = new ArrayList();
      doLoadCategory();
      return;
      this.locationString = (paramBundle.getLatitude() + "," + paramBundle.getLongitude());
      continue;
      if (paramBundle != null) {
        this.locationString = (paramBundle.getLatitude() + "," + paramBundle.getLongitude());
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/FoodCategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */