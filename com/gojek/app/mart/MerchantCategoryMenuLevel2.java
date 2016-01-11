package com.gojek.app.mart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.adapter.mart.ExpandableProductItemAdapter;
import com.gojek.app.adapter.mart.ExpandableProductItemAdapter.MenuClickListener;
import com.gojek.app.adapter.mart.MyRecyclerAdapter;
import com.gojek.app.adapter.mart.MyRecyclerAdapter.ItemClick;
import com.gojek.app.custom.XTextView;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.BookingSaved;
import com.gojek.app.model.CompanyProductCategory;
import com.gojek.app.model.MartItem;
import com.gojek.app.model.SubCategory;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class MerchantCategoryMenuLevel2
  extends GojekActivityBase
{
  public static final String JSON_CATEGORY = "jsonCategory";
  private static final String TAG = MerchantCategoryMenuLevel2.class.getSimpleName();
  private LinearLayout content_1;
  private ExpandableProductItemAdapter expAdapter;
  private Gson gson;
  private boolean isRotate = false;
  private String jsonMartCategoryList;
  private int lastExpandedPosition = -1;
  private ArrayList<MyRecyclerAdapter> listHitemAdapter;
  private ArrayList<List<MartItem>> listMartItem;
  private BookingStatus mBookingData;
  private BookingSaved mBookingSaved;
  private String mCodeCategory;
  private ExpandableListView mELVMerchantCatagory;
  private ImageView mIVArrow;
  private MartMerchant mMartMerchant;
  private LinearLayout mTVByCategory;
  private XTextView mTVManyAndCost;
  private XTextView mTVReviewBtn;
  private ArrayList<RouteItem> otherRouteItems;
  private int parentId;
  private ProgressBar progressBar;
  private RelativeLayout reviewLayout;
  private LinearLayout searchBar;
  private int tempCost = 0;
  private int tempQuantity = 0;
  
  private void buttonPlusMinusClicked(MartItem paramMartItem, boolean paramBoolean)
  {
    RouteItem localRouteItem = new RouteItem();
    localRouteItem.setItemId(paramMartItem.getId());
    localRouteItem.setImageUrl(paramMartItem.getImgLocation());
    localRouteItem.setItemName(paramMartItem.getBrand() + ", " + paramMartItem.getSubBrand() + " " + paramMartItem.getSize());
    localRouteItem.setQuantity(paramMartItem.quantity);
    localRouteItem.setPrice(Integer.parseInt(paramMartItem.getPrice()));
    int k = 0;
    int i;
    if (((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().size() != 0)
    {
      i = 0;
      int j = k;
      if (i < ((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().size())
      {
        if (((RouteItem)((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().get(i)).getItemId() != localRouteItem.getItemId()) {
          break label306;
        }
        if ((!paramBoolean) && (localRouteItem.getQuantity() == 0))
        {
          ((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().remove(i);
          label235:
          j = 1;
        }
      }
      else if (j == 0)
      {
        ((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().add(localRouteItem);
      }
    }
    for (;;)
    {
      setQuantityCost();
      return;
      ((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().set(i, localRouteItem);
      break label235;
      label306:
      i += 1;
      break;
      if (paramBoolean) {
        ((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().add(localRouteItem);
      }
    }
  }
  
  private void goCategoryDetailList(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(getApplicationContext(), MartCategoryDetailList.class);
    localIntent.putExtra("MART_NAME", this.mMartMerchant.martName + "");
    localIntent.putExtra("MART_ID", this.mMartMerchant.martId + "");
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingData, this.otherRouteItems);
    localIntent.putExtra("MART_CATEGORY_ID", paramInt2);
    localIntent.putExtra("MART_CATEGORY_CODE", paramString1);
    localIntent.putExtra("MART_CATEGORY_NAME", paramString2);
    localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    localIntent.putExtra("jsonCategory", this.jsonMartCategoryList);
    localIntent.putExtra("JSON_RECENT_PURCHASE", getIntent().getStringExtra("JSON_RECENT_PURCHASE"));
    startActivityForResult(localIntent, 1);
  }
  
  private void goToMerchantCategoryLevel2(CompanyProductCategory paramCompanyProductCategory)
  {
    Intent localIntent = new Intent(this, MerchantCategoryMenuLevel2.class);
    localIntent.putExtra("MART_CATEGORY_ID", paramCompanyProductCategory.getId() + "");
    localIntent.putExtra("MART_CATEGORY_NAME", paramCompanyProductCategory.getName());
    localIntent.putExtra("MART_ID", this.mMartMerchant.martId + "");
    localIntent.putExtra("MART_CATEGORY_CODE", paramCompanyProductCategory.getCode());
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingData, this.otherRouteItems);
    localIntent.putExtra("MART_MERCHANT", this.mMartMerchant);
    localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    localIntent.putExtra("jsonCategory", this.jsonMartCategoryList);
    startActivityForResult(localIntent, 12);
  }
  
  private void goToProductDetail(MartItem paramMartItem)
  {
    Intent localIntent = new Intent(this, MartProductDetail.class);
    localIntent.putExtra("MART_NAME", getIntent().getStringExtra("MART_NAME"));
    localIntent.putExtra("MART_ID", getIntent().getStringExtra("MART_ID"));
    localIntent.putExtra("MART_ITEM_ID", "" + paramMartItem.getId());
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingData, this.otherRouteItems);
    localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    if (paramMartItem.getItemCategories() != null) {
      localIntent.putExtra("MART_ITEM_CATEGORY", paramMartItem.getItemCategories().split(",")[0]);
    }
    startActivityForResult(localIntent, 2);
  }
  
  private void gotToMartReview()
  {
    if (validateRouteItem())
    {
      Intent localIntent = new Intent(this, MartReview.class);
      localIntent.putExtra("BOOKING_DATA", this.mBookingData);
      localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
      this.mBookingSaved.saveData(this.mBookingData, this.otherRouteItems);
      localIntent.putExtra("MERCHANT", this.mMartMerchant);
      localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
      startActivityForResult(localIntent, 111);
    }
  }
  
  private void refreshHorizontalAdapter()
  {
    if (this.listMartItem != null)
    {
      int i = 0;
      while (i < this.listMartItem.size())
      {
        int j = 0;
        if (j < ((List)this.listMartItem.get(i)).size())
        {
          if (((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().size() == 0) {
            ((MartItem)((List)this.listMartItem.get(i)).get(j)).quantity = 0;
          }
          label251:
          for (;;)
          {
            j += 1;
            break;
            int k = 0;
            for (;;)
            {
              if (k >= ((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().size()) {
                break label251;
              }
              RouteItem localRouteItem = (RouteItem)((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().get(k);
              if (localRouteItem.getItemId() == ((MartItem)((List)this.listMartItem.get(i)).get(j)).getId())
              {
                ((MartItem)((List)this.listMartItem.get(i)).get(j)).quantity = localRouteItem.getQuantity();
                break;
              }
              ((MartItem)((List)this.listMartItem.get(i)).get(j)).quantity = 0;
              k += 1;
            }
          }
        }
        ((MyRecyclerAdapter)this.listHitemAdapter.get(i)).notifyDataSetChanged();
        i += 1;
      }
    }
  }
  
  private void setQuantityCost()
  {
    this.tempCost = 0;
    this.tempQuantity = 0;
    Iterator localIterator = ((Addresses)this.mBookingData.addresses.get(0)).getRouteItems().iterator();
    while (localIterator.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)localIterator.next();
      this.tempCost += localRouteItem.getPrice() * localRouteItem.quantity;
      this.tempQuantity += 1;
      ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal = this.tempCost;
      ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal = this.tempQuantity;
    }
    if (this.tempQuantity != 0)
    {
      this.reviewLayout.setVisibility(0);
      this.mTVManyAndCost.setText(String.format(getString(2131165577), new Object[] { Integer.valueOf(this.tempQuantity), Util.getRupiahFormat(String.valueOf(this.tempCost)) }));
      return;
    }
    this.reviewLayout.setVisibility(8);
  }
  
  private boolean validateRouteItem()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((((Addresses)this.mBookingData.getAddresses().get(0)).getRouteItems().isEmpty()) && (this.otherRouteItems.isEmpty())) {
      localStringBuilder.append(getString(2131165698));
    }
    if (!localStringBuilder.toString().equals(""))
    {
      Toast.makeText(this, localStringBuilder.toString(), 0).show();
      return false;
    }
    return true;
  }
  
  public void doLoadCategory(String paramString1, String paramString2)
  {
    this.progressBar.setVisibility(0);
    paramString1 = String.format("https://api.gojek.co.id/gojek/mart-merchant-category/find-with-items?martId=%1s&code=%2s&limit=%d", new Object[] { paramString1, paramString2, Integer.valueOf(10) });
    VolleyClient.getInstance(getApplicationContext()).getList(TAG, paramString1, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MerchantCategoryMenuLevel2.TAG, "loadCategory get error " + paramAnonymousVolleyError);
        MerchantCategoryMenuLevel2.this.progressBar.setVisibility(8);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Object localObject1 = MerchantCategoryMenuLevel2.this.gson;
        Object localObject2;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localObject2 = new MerchantCategoryMenuLevel2.7.1(this).getType();
          if ((localObject1 instanceof Gson)) {
            break label364;
          }
        }
        label364:
        for (paramAnonymousJSONArray = ((Gson)localObject1).fromJson(paramAnonymousJSONArray, (Type)localObject2);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localObject1, paramAnonymousJSONArray, (Type)localObject2))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          MerchantCategoryMenuLevel2.access$902(MerchantCategoryMenuLevel2.this, new ArrayList());
          MerchantCategoryMenuLevel2.access$1002(MerchantCategoryMenuLevel2.this, new ArrayList());
          int i = 0;
          while (i < paramAnonymousJSONArray.size())
          {
            Object localObject3 = (CompanyProductCategory)paramAnonymousJSONArray.get(i);
            localObject1 = MerchantCategoryMenuLevel2.this.getLayoutInflater().inflate(2130968757, null);
            TextView localTextView = (TextView)((View)localObject1).findViewById(2131624803);
            localObject2 = (LinearLayout)((View)localObject1).findViewById(2131624804);
            RecyclerView localRecyclerView = (RecyclerView)((View)localObject1).findViewById(2131624807);
            ((View)localObject1).findViewById(2131624806).setVisibility(8);
            localRecyclerView.setVisibility(0);
            localRecyclerView.setLayoutManager(new LinearLayoutManager(MerchantCategoryMenuLevel2.this, 0, false));
            MyRecyclerAdapter localMyRecyclerAdapter = new MyRecyclerAdapter(MerchantCategoryMenuLevel2.this, ((CompanyProductCategory)paramAnonymousJSONArray.get(i)).getMartItems());
            localTextView.setText(((CompanyProductCategory)paramAnonymousJSONArray.get(i)).getName());
            MerchantCategoryMenuLevel2.this.listMartItem.add(((CompanyProductCategory)paramAnonymousJSONArray.get(i)).getMartItems());
            localObject3 = new MerchantCategoryMenuLevel2.7.2(this, (CompanyProductCategory)localObject3);
            MerchantCategoryMenuLevel2.this.listHitemAdapter.add(localMyRecyclerAdapter);
            localMyRecyclerAdapter.setmListener((MyRecyclerAdapter.ItemClick)localObject3);
            localRecyclerView.setAdapter(localMyRecyclerAdapter);
            ((LinearLayout)localObject2).setOnClickListener(new MerchantCategoryMenuLevel2.7.3(this, i, paramAnonymousJSONArray));
            MerchantCategoryMenuLevel2.this.content_1.addView((View)localObject1);
            if (i == paramAnonymousJSONArray.size() - 1) {
              MerchantCategoryMenuLevel2.this.progressBar.setVisibility(8);
            }
            i += 1;
          }
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
        if (paramAnonymousJSONArray.size() <= 0) {
          MerchantCategoryMenuLevel2.this.findViewById(2131624815).setVisibility(0);
        }
        for (;;)
        {
          MerchantCategoryMenuLevel2.this.setQuantityCost();
          MerchantCategoryMenuLevel2.this.refreshHorizontalAdapter();
          return;
          MerchantCategoryMenuLevel2.this.findViewById(2131624815).setVisibility(8);
        }
      }
    });
  }
  
  public void doLoadExpandable(String paramString)
  {
    Gson localGson = this.gson;
    paramString = paramString.toString();
    Type localType = new TypeToken() {}.getType();
    if (!(localGson instanceof Gson))
    {
      paramString = localGson.fromJson(paramString, localType);
      paramString = (List)paramString;
      if (paramString == null) {
        break label113;
      }
    }
    for (;;)
    {
      this.expAdapter = new ExpandableProductItemAdapter(this, paramString);
      this.expAdapter.setListener(new ExpandableProductItemAdapter.MenuClickListener()
      {
        public void onMenuClickAll(CompanyProductCategory paramAnonymousCompanyProductCategory)
        {
          MerchantCategoryMenuLevel2.this.goToMerchantCategoryLevel2(paramAnonymousCompanyProductCategory);
        }
        
        public void onMenuClickProduct(int paramAnonymousInt, List<SubCategory> paramAnonymousList)
        {
          MerchantCategoryMenuLevel2.this.goCategoryDetailList(paramAnonymousInt, ((SubCategory)paramAnonymousList.get(paramAnonymousInt)).id, ((SubCategory)paramAnonymousList.get(paramAnonymousInt)).code, ((SubCategory)paramAnonymousList.get(paramAnonymousInt)).name);
        }
      });
      this.mELVMerchantCatagory.setAdapter(this.expAdapter);
      this.mELVMerchantCatagory.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
      {
        public void onGroupExpand(int paramAnonymousInt)
        {
          if ((MerchantCategoryMenuLevel2.this.lastExpandedPosition != -1) && (paramAnonymousInt != MerchantCategoryMenuLevel2.this.lastExpandedPosition)) {
            MerchantCategoryMenuLevel2.this.mELVMerchantCatagory.collapseGroup(MerchantCategoryMenuLevel2.this.lastExpandedPosition);
          }
          MerchantCategoryMenuLevel2.this.mELVMerchantCatagory.setSelection(paramAnonymousInt);
          MerchantCategoryMenuLevel2.access$702(MerchantCategoryMenuLevel2.this, paramAnonymousInt);
        }
      });
      return;
      paramString = GsonInstrumentation.fromJson((Gson)localGson, paramString, localType);
      break;
      label113:
      paramString = new ArrayList();
    }
  }
  
  public void doSearchItem()
  {
    Intent localIntent = new Intent(this, MartSearchByItem.class);
    localIntent.putExtra("MART_ID", getIntent().getStringExtra("MART_ID"));
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    localIntent.putExtra("MART_MERCHANT", getIntent().getParcelableExtra("MART_MERCHANT"));
    localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    localIntent.putExtra("JSON_RECENT_PURCHASE", getIntent().getStringExtra("JSON_RECENT_PURCHASE"));
    startActivityForResult(localIntent, 12);
  }
  
  public BookingStatus getmBookingData()
  {
    return this.mBookingData;
  }
  
  public void init()
  {
    setTitle(getIntent().getStringExtra("MART_CATEGORY_NAME"));
    this.gson = new Gson();
    this.otherRouteItems = new ArrayList();
    this.parentId = Integer.parseInt(getIntent().getStringExtra("MART_CATEGORY_ID"));
    this.mCodeCategory = getIntent().getStringExtra("MART_CATEGORY_CODE");
    this.mMartMerchant = ((MartMerchant)getIntent().getParcelableExtra("MART_MERCHANT"));
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    this.mBookingSaved = new BookingSaved(this);
    this.jsonMartCategoryList = getIntent().getStringExtra("jsonCategory");
    this.searchBar.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MerchantCategoryMenuLevel2.this.doSearchItem();
      }
    });
    this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MerchantCategoryMenuLevel2.this.gotToMartReview();
      }
    });
    this.mTVByCategory.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = MerchantCategoryMenuLevel2.this.mIVArrow;
        MerchantCategoryMenuLevel2 localMerchantCategoryMenuLevel2 = MerchantCategoryMenuLevel2.this;
        Bitmap localBitmap = BitmapFactoryInstrumentation.decodeResource(MerchantCategoryMenuLevel2.this.getResources(), 2130837847);
        float f;
        if (MerchantCategoryMenuLevel2.this.isRotate)
        {
          f = 0.0F;
          paramAnonymousView.setImageBitmap(localMerchantCategoryMenuLevel2.rotateImage(localBitmap, f));
          if (!MerchantCategoryMenuLevel2.this.isRotate) {
            break label115;
          }
          MerchantCategoryMenuLevel2.this.content_1.setVisibility(0);
          MerchantCategoryMenuLevel2.this.mELVMerchantCatagory.setVisibility(8);
          label85:
          paramAnonymousView = MerchantCategoryMenuLevel2.this;
          if (MerchantCategoryMenuLevel2.this.isRotate) {
            break label141;
          }
        }
        label115:
        label141:
        for (boolean bool = true;; bool = false)
        {
          MerchantCategoryMenuLevel2.access$102(paramAnonymousView, bool);
          return;
          f = 90.0F;
          break;
          MerchantCategoryMenuLevel2.this.content_1.setVisibility(8);
          MerchantCategoryMenuLevel2.this.mELVMerchantCatagory.setVisibility(0);
          break label85;
        }
      }
    });
    doLoadCategory(this.mMartMerchant.martId + "", this.mCodeCategory);
    doLoadExpandable(this.jsonMartCategoryList);
    this.tempCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    setQuantityCost();
    refreshHorizontalAdapter();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    this.tempCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    setQuantityCost();
    refreshHorizontalAdapter();
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingData, this.otherRouteItems);
    setResult(-1, localIntent);
    super.onBackPressed();
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
  
  protected void onResume()
  {
    super.onResume();
  }
  
  public void renderView()
  {
    setContentView(2130968764);
    this.mTVReviewBtn = ((XTextView)findViewById(2131624490));
    this.mTVManyAndCost = ((XTextView)findViewById(2131624497));
    this.mELVMerchantCatagory = ((ExpandableListView)findViewById(2131624812));
    this.content_1 = ((LinearLayout)findViewById(2131624816));
    this.reviewLayout = ((RelativeLayout)findViewById(2131624488));
    this.reviewLayout.setVisibility(8);
    this.progressBar = ((ProgressBar)findViewById(2131624784));
    this.mTVByCategory = ((LinearLayout)findViewById(2131624808));
    this.mIVArrow = ((ImageView)findViewById(2131624790));
    this.searchBar = ((LinearLayout)findViewById(2131624458));
    findViewById(2131624815).setVisibility(8);
  }
  
  public Bitmap rotateImage(Bitmap paramBitmap, float paramFloat)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramFloat);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }
  
  public void setmBookingData(BookingStatus paramBookingStatus)
  {
    this.mBookingData = paramBookingStatus;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MerchantCategoryMenuLevel2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */