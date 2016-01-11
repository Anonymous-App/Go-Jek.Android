package com.gojek.app.mart;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.custom.EndlessScrollListener;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.Adult;
import com.gojek.app.model.BookingSaved;
import com.gojek.app.model.MartItem;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;

public class MartCategoryDetailList
  extends GojekActivityBase
{
  private static final String TAG = MartCategoryDetailList.class.getSimpleName();
  private final int REQUEST_ITEMS = 12;
  private final int REQUEST_OTHER = 11;
  private String category;
  CategoryDetailListAdapter categoryDetailAdapter;
  private Adult mAdult;
  private BookingStatus mBookingData;
  private BookingSaved mBookingSaved;
  private GridView mGVProductList;
  private TextView mTVByCategory;
  private TextView mTVManyAndCost;
  private TextView mTVReviewBtn;
  private Map<Integer, RouteItem> mapRouteItems;
  private MartHome martHome;
  private int martId;
  private List<MartItem> martItems = new ArrayList();
  private MartMerchant merchant;
  private ArrayList<RouteItem> otherRouteItems;
  private ProgressBar progressBar;
  private LinearLayout searchItem;
  private int tempCost;
  private int tempQuantity;
  
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
  
  private void setQuantityCost()
  {
    int i = (int)(50.0F * getResources().getDisplayMetrics().density + 0.5F);
    if (this.tempQuantity != 0)
    {
      this.mGVProductList.setPadding(0, 0, 0, i);
      findViewById(2131624488).setVisibility(0);
      this.mTVManyAndCost.setText(String.format(getString(2131165577), new Object[] { Integer.valueOf(this.tempQuantity), Util.getRupiahFormat(String.valueOf(this.tempCost)) }));
      return;
    }
    this.mGVProductList.setPadding(0, 0, 0, 0);
    findViewById(2131624488).setVisibility(8);
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
  
  public void init()
  {
    setTitle(getIntent().getStringExtra("MART_NAME"));
    this.martHome = new MartHome();
    this.mBookingSaved = new BookingSaved(this);
    this.mAdult = new Adult(this);
    this.martId = Integer.parseInt(getIntent().getStringExtra("MART_ID"));
    this.category = getIntent().getStringExtra("MART_CATEGORY_CODE");
    this.mTVByCategory.setText(getIntent().getStringExtra("MART_CATEGORY_NAME"));
    this.mTVManyAndCost = ((TextView)findViewById(2131624497));
    this.mTVReviewBtn = ((TextView)findViewById(2131624490));
    this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MartCategoryDetailList.this.validateRouteItem())
        {
          paramAnonymousView = MartCategoryDetailList.this.mapToList(MartCategoryDetailList.this.mapRouteItems);
          Addresses localAddresses = (Addresses)MartCategoryDetailList.this.mBookingData.addresses.get(0);
          localAddresses.routeItems = paramAnonymousView;
          localAddresses.foodQuantityTotal = MartCategoryDetailList.this.tempQuantity;
          localAddresses.foodCostTotal = MartCategoryDetailList.this.tempCost;
          paramAnonymousView = new Intent(MartCategoryDetailList.this, MartReview.class);
          paramAnonymousView.putExtra("BOOKING_DATA", MartCategoryDetailList.this.mBookingData);
          paramAnonymousView.putExtra("MERCHANT", MartCategoryDetailList.this.merchant);
          paramAnonymousView.putExtra("LOCATION", MartCategoryDetailList.this.getIntent().getStringExtra("LOCATION"));
          paramAnonymousView.putParcelableArrayListExtra("OTHER_ITEM", MartCategoryDetailList.this.otherRouteItems);
          MartCategoryDetailList.this.startActivityForResult(paramAnonymousView, 111);
        }
      }
    });
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    this.merchant = ((Addresses)this.mBookingData.addresses.get(0)).martMerchant;
    this.tempCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    List localList = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
    if (localList == null) {}
    for (this.mapRouteItems = listToMap(new ArrayList());; this.mapRouteItems = listToMap(localList))
    {
      this.categoryDetailAdapter = new CategoryDetailListAdapter();
      this.mGVProductList.setAdapter(this.categoryDetailAdapter);
      this.mGVProductList.setOnScrollListener(new EndlessScrollListener()
      {
        protected void onLoadMore(int paramAnonymousInt)
        {
          MartCategoryDetailList.this.loadItemPage(paramAnonymousInt);
        }
      });
      setQuantityCost();
      loadItem();
      return;
    }
  }
  
  public void loadItem()
  {
    this.progressBar.setVisibility(0);
    this.mGVProductList.setVisibility(8);
    String str = String.format("https://api.gojek.co.id/gojek/mart-item/find?itemCategories=%1s&martId=%d&page=%d&limit=%d", new Object[] { this.category, Integer.valueOf(this.martId), Integer.valueOf(0), Integer.valueOf(21) });
    VolleyClient.getInstance(this).getList(TAG, str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        MartCategoryDetailList.this.progressBar.setVisibility(8);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson = new Gson();
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new MartCategoryDetailList.4.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label123;
          }
        }
        label123:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          if ((paramAnonymousJSONArray != null) && (!paramAnonymousJSONArray.isEmpty()))
          {
            MartCategoryDetailList.this.martItems.addAll(paramAnonymousJSONArray);
            MartCategoryDetailList.this.categoryDetailAdapter.notifyDataSetChanged();
          }
          MartCategoryDetailList.this.mGVProductList.setVisibility(0);
          MartCategoryDetailList.this.progressBar.setVisibility(8);
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
      }
    });
  }
  
  public void loadItemPage(final int paramInt)
  {
    String str = String.format("https://api.gojek.co.id/gojek/mart-item/find?itemCategories=%1s&martId=%d&page=%d&limit=%d", new Object[] { this.category, Integer.valueOf(this.martId), Integer.valueOf(paramInt), Integer.valueOf(21) });
    VolleyClient.getInstance(this).getList(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MartCategoryDetailList.TAG, "loadItemPage() got error " + paramAnonymousVolleyError);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson = new Gson();
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new MartCategoryDetailList.5.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label100;
          }
        }
        label100:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.isEmpty())) {
            break label113;
          }
          MartCategoryDetailList.this.martItems.addAll(paramAnonymousJSONArray);
          MartCategoryDetailList.this.categoryDetailAdapter.notifyDataSetChanged();
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
        label113:
        Log.i(MartCategoryDetailList.TAG, "no item found at page " + paramInt);
      }
    });
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    this.merchant = ((Addresses)this.mBookingData.addresses.get(0)).martMerchant;
    this.tempCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    List localList = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
    if (localList == null) {}
    for (this.mapRouteItems = listToMap(new ArrayList());; this.mapRouteItems = listToMap(localList))
    {
      setQuantityCost();
      this.categoryDetailAdapter.notifyDataSetChanged();
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    }
  }
  
  public void onBackPressed()
  {
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    setmBookingData();
    Intent localIntent = new Intent();
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    setResult(-1, localIntent);
    super.onBackPressed();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    renderView();
    init();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    paramMenu.add(0, 7, 0, getString(2131165601)).setIcon(2130837852);
    int i = 0;
    while (i < paramMenu.size())
    {
      MenuItemCompat.setShowAsAction(paramMenu.getItem(i), 1);
      i += 1;
    }
    return true;
  }
  
  public void renderView()
  {
    setContentView(2130968749);
    this.mTVByCategory = ((TextView)findViewById(2131624783));
    this.mGVProductList = ((GridView)findViewById(2131624785));
    this.searchItem = ((LinearLayout)findViewById(2131624781));
    this.progressBar = ((ProgressBar)findViewById(2131624784));
    this.searchItem.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MartCategoryDetailList.this.searchItem();
      }
    });
  }
  
  public void searchItem()
  {
    setmBookingData();
    Intent localIntent = new Intent(this, MartSearchByItem.class);
    localIntent.putExtra("MART_ID", getIntent().getStringExtra("MART_ID"));
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    localIntent.putExtra("MART_MERCHANT", ((Addresses)this.mBookingData.addresses.get(0)).martMerchant);
    localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    localIntent.putExtra("JSON_RECENT_PURCHASE", getIntent().getStringExtra("JSON_RECENT_PURCHASE"));
    startActivityForResult(localIntent, 12);
  }
  
  public void setmBookingData()
  {
    List localList = mapToList(this.mapRouteItems);
    Addresses localAddresses = (Addresses)this.mBookingData.addresses.get(0);
    localAddresses.routeItems = localList;
    localAddresses.foodQuantityTotal = this.tempQuantity;
    localAddresses.foodCostTotal = this.tempCost;
    this.mBookingSaved.saveData(this.mBookingData, this.otherRouteItems);
  }
  
  public class CategoryDetailListAdapter
    extends BaseAdapter
  {
    public CategoryDetailListAdapter() {}
    
    public int getCount()
    {
      return MartCategoryDetailList.this.martItems.size();
    }
    
    public MartItem getItem(int paramInt)
    {
      return (MartItem)MartCategoryDetailList.this.martItems.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return ((MartItem)MartCategoryDetailList.this.martItems.get(paramInt)).id;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = paramView;
      if (paramView == null)
      {
        paramViewGroup = ((LayoutInflater)MartCategoryDetailList.this.getSystemService("layout_inflater")).inflate(2130968750, null);
        paramView = new MartCategoryDetailList.ViewHolder();
        paramView.image = ((ImageView)paramViewGroup.findViewById(2131624039));
        paramView.mIVHaram = ((ImageView)paramViewGroup.findViewById(2131624786));
        paramView.title = ((TextView)paramViewGroup.findViewById(2131624043));
        paramView.mTVProductPrice = ((TextView)paramViewGroup.findViewById(2131624698));
        paramView.mTVProductWeight = ((TextView)paramViewGroup.findViewById(2131624699));
        paramView.minusButon = ((TextView)paramViewGroup.findViewById(2131624503));
        paramView.quantityButton = ((TextView)paramViewGroup.findViewById(2131624504));
        paramView.plusButton = ((TextView)paramViewGroup.findViewById(2131624505));
        paramViewGroup.setTag(paramView);
      }
      MartCategoryDetailList.ViewHolder localViewHolder = (MartCategoryDetailList.ViewHolder)paramViewGroup.getTag();
      localViewHolder.title.setText(((MartItem)MartCategoryDetailList.this.martItems.get(paramInt)).brand);
      localViewHolder.mTVProductPrice.setText("Est. " + Util.getRupiahFormat(new StringBuilder().append("").append(((MartItem)MartCategoryDetailList.this.martItems.get(paramInt)).price).toString()));
      localViewHolder.mTVProductWeight.setText(((MartItem)MartCategoryDetailList.this.martItems.get(paramInt)).size);
      Object localObject = localViewHolder.quantityButton;
      if (MartCategoryDetailList.this.mapRouteItems.containsKey(Integer.valueOf(((MartItem)MartCategoryDetailList.this.martItems.get(paramInt)).id)))
      {
        paramView = String.valueOf(((RouteItem)MartCategoryDetailList.this.mapRouteItems.get(Integer.valueOf(((MartItem)MartCategoryDetailList.this.martItems.get(paramInt)).id))).quantity);
        ((TextView)localObject).setText(paramView);
        Glide.with(MartCategoryDetailList.this.getApplicationContext()).load(((MartItem)MartCategoryDetailList.this.martItems.get(paramInt)).imgLocation).placeholder(2131558630).diskCacheStrategy(DiskCacheStrategy.ALL).error(2130837935).into(localViewHolder.image);
        localObject = Glide.with(MartCategoryDetailList.this.getApplicationContext());
        if (!((MartItem)MartCategoryDetailList.this.martItems.get(paramInt)).isHaram) {
          break label543;
        }
      }
      label543:
      for (paramView = "https://dmkykyvzhmwd0.cloudfront.net/non_halal.png";; paramView = "")
      {
        ((RequestManager)localObject).load(paramView).diskCacheStrategy(DiskCacheStrategy.ALL).into(localViewHolder.mIVHaram);
        localViewHolder.image.setOnClickListener(new MartCategoryDetailList.CategoryDetailListAdapter.1(this, paramInt));
        localViewHolder.plusButton.setOnClickListener(new MartCategoryDetailList.CategoryDetailListAdapter.2(this, paramInt, localViewHolder));
        localViewHolder.minusButon.setOnClickListener(new MartCategoryDetailList.CategoryDetailListAdapter.3(this, localViewHolder, paramInt));
        return paramViewGroup;
        paramView = "0";
        break;
      }
    }
  }
  
  static class ViewHolder
  {
    ImageView image;
    ImageView mIVHaram;
    TextView mTVProductPrice;
    TextView mTVProductWeight;
    TextView minusButon;
    TextView plusButton;
    TextView quantityButton;
    TextView title;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MartCategoryDetailList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */