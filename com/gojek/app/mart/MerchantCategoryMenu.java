package com.gojek.app.mart;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.adapter.mart.ExpandableProductItemAdapter;
import com.gojek.app.custom.HorizontalListView;
import com.gojek.app.custom.XTextView;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.BookingSaved;
import com.gojek.app.model.CompanyProductCategory;
import com.gojek.app.model.MartItem;
import com.gojek.app.model.SubCategory;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Customer;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public class MerchantCategoryMenu
  extends GojekActivityBase
{
  private static final String TAG = MerchantCategoryMenu.class.getSimpleName();
  private final int REQUEST_ITEMS = 12;
  private LinearLayout content_1;
  private ExpandableProductItemAdapter expAdapter;
  private Gson gson;
  HorizontalRecentAdapter hRecentAdapter;
  private HorizontalSubCategoriesAdapter horizontalAdapter;
  private boolean isRotate = false;
  private String jsonMerchantCategoryList;
  private String jsonrecentPurchase;
  private int lastExpandedPosition = -1;
  List<MartItem> listMartItem;
  private HorizontalSubCategoriesAdapter.HorizontalItemClick listener;
  private BookingSaved mBookingSaved;
  private BookingStatus mBookingStatus;
  private ExpandableListView mELVMerchantCatagory;
  private ImageView mIVArrow;
  private MartMerchant mMartMerchant;
  private LinearLayout mTVByCategory;
  private XTextView mTVManyAndCost;
  private XTextView mTVReviewBtn;
  private Map<Integer, RouteItem> mapRouteItems;
  private ArrayList<RouteItem> otherRouteItems;
  private ProgressBar progressBar;
  private RelativeLayout reviewLayout;
  private LinearLayout searchBar;
  private int tempCost;
  private int tempQuantity;
  
  private void buttonPlusMinusClicked(MartItem paramMartItem, boolean paramBoolean)
  {
    RouteItem localRouteItem = new RouteItem();
    localRouteItem.setItemId(paramMartItem.getId());
    localRouteItem.setItemName(paramMartItem.getBrand() + ", " + paramMartItem.getSubBrand() + " " + paramMartItem.getSize());
    localRouteItem.setImageUrl(paramMartItem.getImgLocation());
    localRouteItem.setQuantity(paramMartItem.quantity);
    localRouteItem.setPrice(Integer.parseInt(paramMartItem.getPrice()));
    int k = 0;
    int i;
    if (((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().size() != 0)
    {
      i = 0;
      int j = k;
      if (i < ((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().size())
      {
        if (((RouteItem)((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().get(i)).getItemId() != localRouteItem.getItemId()) {
          break label306;
        }
        if ((!paramBoolean) && (localRouteItem.getQuantity() == 0))
        {
          ((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().remove(i);
          label235:
          j = 1;
        }
      }
      else if (j == 0)
      {
        ((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().add(localRouteItem);
      }
    }
    for (;;)
    {
      setQuantityCost();
      return;
      ((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().set(i, localRouteItem);
      break label235;
      label306:
      i += 1;
      break;
      if (paramBoolean) {
        ((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().add(localRouteItem);
      }
    }
  }
  
  private void goCategoryDetailList(int paramInt, List<SubCategory> paramList)
  {
    Intent localIntent = new Intent(getApplicationContext(), MartCategoryDetailList.class);
    localIntent.putExtra("MART_NAME", this.mMartMerchant.martName);
    localIntent.putExtra("MART_ID", this.mMartMerchant.martId + "");
    localIntent.putExtra("BOOKING_DATA", this.mBookingStatus);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingStatus, this.otherRouteItems);
    localIntent.putExtra("MART_CATEGORY_ID", ((SubCategory)paramList.get(paramInt)).id);
    localIntent.putExtra("MART_CATEGORY_CODE", ((SubCategory)paramList.get(paramInt)).code);
    localIntent.putExtra("MART_CATEGORY_NAME", ((SubCategory)paramList.get(paramInt)).name);
    localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    localIntent.putExtra("JSON_RECENT_PURCHASE", this.jsonrecentPurchase);
    startActivityForResult(localIntent, 12);
  }
  
  private void goToMerchantCategoryLevel2(CompanyProductCategory paramCompanyProductCategory)
  {
    Intent localIntent = new Intent(this, MerchantCategoryMenuLevel2.class);
    localIntent.putExtra("MART_CATEGORY_ID", paramCompanyProductCategory.getId() + "");
    localIntent.putExtra("MART_CATEGORY_NAME", paramCompanyProductCategory.getName());
    localIntent.putExtra("MART_CATEGORY_CODE", paramCompanyProductCategory.getCode());
    localIntent.putExtra("MART_ID", this.mMartMerchant.martId + "");
    localIntent.putExtra("BOOKING_DATA", this.mBookingStatus);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingStatus, this.otherRouteItems);
    localIntent.putExtra("MART_MERCHANT", this.mMartMerchant);
    localIntent.putExtra("jsonCategory", this.jsonMerchantCategoryList);
    localIntent.putExtra("JSON_RECENT_PURCHASE", this.jsonrecentPurchase);
    localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    startActivityForResult(localIntent, 12);
  }
  
  private void goToProductDetail(MartItem paramMartItem)
  {
    Intent localIntent = new Intent(this, MartProductDetail.class);
    localIntent.putExtra("MART_NAME", getIntent().getStringExtra("MART_NAME"));
    localIntent.putExtra("MART_ID", getIntent().getStringExtra("MART_ID"));
    localIntent.putExtra("MART_ITEM_ID", "" + paramMartItem.getId());
    localIntent.putExtra("BOOKING_DATA", this.mBookingStatus);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingStatus, this.otherRouteItems);
    localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    if (paramMartItem.getItemCategories() != null) {
      localIntent.putExtra("MART_ITEM_CATEGORY", paramMartItem.getItemCategories().split(",")[0]);
    }
    startActivityForResult(localIntent, 12);
  }
  
  private void gotToMartReview()
  {
    if (validateRouteItem())
    {
      Intent localIntent = new Intent(this, MartReview.class);
      localIntent.putExtra("BOOKING_DATA", this.mBookingStatus);
      localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
      this.mBookingSaved.saveData(this.mBookingStatus, this.otherRouteItems);
      localIntent.putExtra("MERCHANT", this.mMartMerchant);
      localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
      startActivityForResult(localIntent, 111);
    }
  }
  
  private void refreshRecentPurchase()
  {
    if (this.listMartItem != null)
    {
      int i = 0;
      if (i < this.listMartItem.size())
      {
        if (((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().size() == 0) {
          ((MartItem)this.listMartItem.get(i)).quantity = 0;
        }
        label200:
        for (;;)
        {
          i += 1;
          break;
          int j = 0;
          for (;;)
          {
            if (j >= ((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().size()) {
              break label200;
            }
            RouteItem localRouteItem = (RouteItem)((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().get(j);
            if (localRouteItem.getItemId() == ((MartItem)this.listMartItem.get(i)).getId())
            {
              ((MartItem)this.listMartItem.get(i)).quantity = localRouteItem.getQuantity();
              break;
            }
            ((MartItem)this.listMartItem.get(i)).quantity = 0;
            j += 1;
          }
        }
      }
      this.hRecentAdapter.notifyDataSetChanged();
    }
  }
  
  private void setQuantityCost()
  {
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
    if ((((Addresses)this.mBookingStatus.getAddresses().get(0)).getRouteItems().isEmpty()) && (this.otherRouteItems.isEmpty())) {
      localStringBuilder.append(getString(2131165698));
    }
    if (!localStringBuilder.toString().equals(""))
    {
      Toast.makeText(this, localStringBuilder.toString(), 0).show();
      return false;
    }
    return true;
  }
  
  public void doLoadCategory(String paramString)
  {
    this.progressBar.setVisibility(0);
    paramString = String.format("https://api.gojek.co.id/gojek/mart-merchant-category/find-with-sub-category?martId=%1s", new Object[] { paramString });
    VolleyClient.getInstance(getApplicationContext()).getList(TAG, paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MerchantCategoryMenu.TAG, "loadCategory get error " + paramAnonymousVolleyError);
        MerchantCategoryMenu.this.progressBar.setVisibility(8);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Object localObject2 = MerchantCategoryMenu.this;
        Object localObject1;
        label45:
        label73:
        Object localObject3;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          localObject1 = paramAnonymousJSONArray.toString();
          MerchantCategoryMenu.access$502((MerchantCategoryMenu)localObject2, (String)localObject1);
          localObject1 = MerchantCategoryMenu.this.gson;
          if ((paramAnonymousJSONArray instanceof JSONArray)) {
            break label357;
          }
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localObject2 = new MerchantCategoryMenu.4.1(this).getType();
          if ((localObject1 instanceof Gson)) {
            break label368;
          }
          paramAnonymousJSONArray = ((Gson)localObject1).fromJson(paramAnonymousJSONArray, (Type)localObject2);
          localObject1 = (List)paramAnonymousJSONArray;
          localObject2 = MerchantCategoryMenu.this;
          localObject3 = MerchantCategoryMenu.this;
          if (localObject1 == null) {
            break label382;
          }
        }
        label357:
        label368:
        label382:
        for (paramAnonymousJSONArray = (JSONArray)localObject1;; paramAnonymousJSONArray = new ArrayList())
        {
          MerchantCategoryMenu.access$702((MerchantCategoryMenu)localObject2, new ExpandableProductItemAdapter((Activity)localObject3, paramAnonymousJSONArray));
          MerchantCategoryMenu.this.expAdapter.setListener(new MerchantCategoryMenu.4.2(this));
          MerchantCategoryMenu.this.mELVMerchantCatagory.setAdapter(MerchantCategoryMenu.this.expAdapter);
          MerchantCategoryMenu.this.mELVMerchantCatagory.setOnGroupExpandListener(new MerchantCategoryMenu.4.3(this));
          int i = 0;
          while (i < ((List)localObject1).size())
          {
            paramAnonymousJSONArray = (CompanyProductCategory)((List)localObject1).get(i);
            localObject2 = MerchantCategoryMenu.this.getLayoutInflater().inflate(2130968765, null);
            Object localObject4 = (TextView)((View)localObject2).findViewById(2131624803);
            localObject3 = (LinearLayout)((View)localObject2).findViewById(2131624804);
            ((TextView)localObject4).setText(((CompanyProductCategory)((List)localObject1).get(i)).getName());
            localObject4 = (HorizontalListView)((View)localObject2).findViewById(2131624806);
            MerchantCategoryMenu.access$1102(MerchantCategoryMenu.this, new HorizontalSubCategoriesAdapter(MerchantCategoryMenu.this.getApplicationContext(), paramAnonymousJSONArray.subCategory));
            MerchantCategoryMenu.4.4 local4 = new MerchantCategoryMenu.4.4(this, paramAnonymousJSONArray);
            ((HorizontalListView)localObject4).setAdapter(MerchantCategoryMenu.this.horizontalAdapter);
            ((HorizontalListView)localObject4).setOnItemClickListener(local4);
            ((LinearLayout)localObject3).setOnClickListener(new MerchantCategoryMenu.4.5(this, paramAnonymousJSONArray));
            MerchantCategoryMenu.this.content_1.addView((View)localObject2);
            i += 1;
          }
          localObject1 = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break label45;
          paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localObject1, paramAnonymousJSONArray, (Type)localObject2);
          break label73;
        }
        MerchantCategoryMenu.this.progressBar.setVisibility(8);
        if (((List)localObject1).size() <= 0)
        {
          MerchantCategoryMenu.this.findViewById(2131624815).setVisibility(0);
          return;
        }
        MerchantCategoryMenu.this.findViewById(2131624815).setVisibility(8);
      }
    });
  }
  
  public void doLoadRecentPurchased(String paramString, int paramInt)
  {
    this.progressBar.setVisibility(0);
    paramString = String.format("https://api.gojek.co.id/gojek/mart-merchant/recent/?customerId=%1s&martId=%d", new Object[] { paramString, Integer.valueOf(paramInt) });
    VolleyClient.getInstance(this).getList(TAG, paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MerchantCategoryMenu.TAG, "loadCategory get error " + paramAnonymousVolleyError);
        MerchantCategoryMenu.this.progressBar.setVisibility(8);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Object localObject2 = MerchantCategoryMenu.this;
        Object localObject1;
        label48:
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          localObject1 = paramAnonymousJSONArray.toString();
          MerchantCategoryMenu.access$1402((MerchantCategoryMenu)localObject2, (String)localObject1);
          localObject2 = new Gson();
          localObject1 = MerchantCategoryMenu.this;
          if ((paramAnonymousJSONArray instanceof JSONArray)) {
            break label242;
          }
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new MerchantCategoryMenu.5.1(this).getType();
          if ((localObject2 instanceof Gson)) {
            break label253;
          }
        }
        label242:
        label253:
        for (paramAnonymousJSONArray = ((Gson)localObject2).fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localObject2, paramAnonymousJSONArray, localType))
        {
          ((MerchantCategoryMenu)localObject1).listMartItem = ((List)paramAnonymousJSONArray);
          paramAnonymousJSONArray = MerchantCategoryMenu.this.getLayoutInflater().inflate(2130968757, null);
          localObject1 = (TextView)paramAnonymousJSONArray.findViewById(2131624803);
          ((LinearLayout)paramAnonymousJSONArray.findViewById(2131624804)).setVisibility(8);
          ((TextView)localObject1).setText(MerchantCategoryMenu.this.getResources().getText(2131165758));
          localObject1 = (HorizontalListView)paramAnonymousJSONArray.findViewById(2131624806);
          MerchantCategoryMenu.this.hRecentAdapter = new HorizontalRecentAdapter(MerchantCategoryMenu.this, MerchantCategoryMenu.this.getApplicationContext(), MerchantCategoryMenu.this.listMartItem);
          localObject2 = new MerchantCategoryMenu.5.2(this);
          MerchantCategoryMenu.this.hRecentAdapter.setListener((HorizontalRecentAdapter.HorizontalItemClick)localObject2);
          ((HorizontalListView)localObject1).setAdapter(MerchantCategoryMenu.this.hRecentAdapter);
          MerchantCategoryMenu.this.refreshRecentPurchase();
          MerchantCategoryMenu.this.content_1.addView(paramAnonymousJSONArray, 0);
          return;
          localObject1 = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break label48;
        }
      }
    });
  }
  
  public void doSearchBar(String paramString1, String paramString2)
  {
    paramString1 = new Intent(this, MartSearchByItem.class);
    paramString1.putExtra("MART_ID", getIntent().getStringExtra("MART_ID"));
    paramString1.putExtra("BOOKING_DATA", this.mBookingStatus);
    paramString1.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    paramString1.putExtra("MART_MERCHANT", getIntent().getParcelableExtra("MART_MERCHANT"));
    paramString1.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    paramString1.putExtra("JSON_RECENT_PURCHASE", this.jsonrecentPurchase);
    startActivityForResult(paramString1, 12);
  }
  
  public BookingStatus getmBookingStatus()
  {
    return this.mBookingStatus;
  }
  
  public void init()
  {
    this.mBookingStatus = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.mMartMerchant = ((MartMerchant)getIntent().getParcelableExtra("MART_MERCHANT"));
    this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    ((Addresses)this.mBookingStatus.addresses.get(0)).martMerchant = this.mMartMerchant;
    ((Addresses)this.mBookingStatus.addresses.get(0)).merchantId = this.mMartMerchant.martId;
    this.mBookingSaved = new BookingSaved(this);
    setTitle(getIntent().getStringExtra("MART_NAME"));
    this.mTVByCategory.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = MerchantCategoryMenu.this.mIVArrow;
        MerchantCategoryMenu localMerchantCategoryMenu = MerchantCategoryMenu.this;
        Bitmap localBitmap = BitmapFactoryInstrumentation.decodeResource(MerchantCategoryMenu.this.getResources(), 2130837847);
        float f;
        if (MerchantCategoryMenu.this.isRotate)
        {
          f = 0.0F;
          paramAnonymousView.setImageBitmap(localMerchantCategoryMenu.rotateImage(localBitmap, f));
          if (!MerchantCategoryMenu.this.isRotate) {
            break label115;
          }
          MerchantCategoryMenu.this.content_1.setVisibility(0);
          MerchantCategoryMenu.this.mELVMerchantCatagory.setVisibility(8);
          label85:
          paramAnonymousView = MerchantCategoryMenu.this;
          if (MerchantCategoryMenu.this.isRotate) {
            break label141;
          }
        }
        label115:
        label141:
        for (boolean bool = true;; bool = false)
        {
          MerchantCategoryMenu.access$002(paramAnonymousView, bool);
          return;
          f = 90.0F;
          break;
          MerchantCategoryMenu.this.content_1.setVisibility(8);
          MerchantCategoryMenu.this.mELVMerchantCatagory.setVisibility(0);
          break label85;
        }
      }
    });
    this.tempCost = ((Addresses)this.mBookingStatus.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingStatus.addresses.get(0)).foodQuantityTotal;
    this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MerchantCategoryMenu.this.gotToMartReview();
      }
    });
    doLoadCategory(this.mMartMerchant.martId + "");
    if (this.mBookingStatus.customer.getCustomerId() != null) {
      doLoadRecentPurchased(this.mBookingStatus.customer.getCustomerId(), this.mMartMerchant.martId);
    }
    setQuantityCost();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.mBookingStatus = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    this.tempCost = ((Addresses)this.mBookingStatus.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingStatus.addresses.get(0)).foodQuantityTotal;
    setQuantityCost();
    refreshRecentPurchase();
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("BOOKING_DATA", this.mBookingStatus);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingStatus, this.otherRouteItems);
    setResult(-1, localIntent);
    super.onBackPressed();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.gson = new Gson();
    setContentView(2130968764);
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
    this.mTVReviewBtn = ((XTextView)findViewById(2131624490));
    this.mTVManyAndCost = ((XTextView)findViewById(2131624497));
    this.mTVByCategory = ((LinearLayout)findViewById(2131624808));
    this.mIVArrow = ((ImageView)findViewById(2131624790));
    this.searchBar = ((LinearLayout)findViewById(2131624458));
    this.content_1 = ((LinearLayout)findViewById(2131624816));
    this.reviewLayout = ((RelativeLayout)findViewById(2131624488));
    this.reviewLayout.setVisibility(8);
    this.progressBar = ((ProgressBar)findViewById(2131624784));
    this.searchBar.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MerchantCategoryMenu.this.doSearchBar("", "");
      }
    });
    this.mELVMerchantCatagory = ((ExpandableListView)findViewById(2131624812));
    findViewById(2131624815).setVisibility(8);
  }
  
  public Bitmap rotateImage(Bitmap paramBitmap, float paramFloat)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramFloat);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }
  
  public void setmBookingStatus(BookingStatus paramBookingStatus)
  {
    this.mBookingStatus = paramBookingStatus;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MerchantCategoryMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */