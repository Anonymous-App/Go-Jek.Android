package com.gojek.app.mart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.adapter.mart.CategoryDetailAdapter;
import com.gojek.app.adapter.mart.CategoryDetailAdapter.CategoryAdapterHandleClikInterface;
import com.gojek.app.adapter.mart.RecentPurchaseAdapter;
import com.gojek.app.adapter.mart.RecentPurchaseSearchItem;
import com.gojek.app.adapter.mart.RecentPurchaseSearchItem.onItemClick;
import com.gojek.app.custom.XTextView;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.MartItem;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.DelayTask;
import com.gojek.app.util.DelayTask.CallBack;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class MartSearchByItem
  extends GojekActivityBase
  implements TextWatcher
{
  private static BookingStatus mBookingStatus;
  private static XTextView mTVManyAndCost;
  private static RelativeLayout reviewLayout;
  private static int tempCost;
  private static int tempQuantity;
  private final int NOT_FOUND_RESULT_ID = -99;
  private CategoryDetailAdapter categoryDetailAdapter;
  private DelayTask delayTask;
  private GridView gridProduk;
  private Intent inte = new Intent();
  private View labelRecentPurchase;
  private List<MartItem> listMartItem;
  private ImageView mIVSearch;
  private LinearLayout mLLAddOtherItem;
  private int mScreenHeight;
  private int mScreenWidth;
  private XTextView mTVReviewBtn;
  private List<MartItem> notFoundMerchants;
  private ArrayList<RouteItem> otherRouteItems;
  private ProgressBar progres;
  private RecentPurchaseAdapter recentPurchaseAdapter;
  private ListView recentPurchaseHorizontalListView;
  private String recentPurchasedBawaan;
  private RecentPurchaseSearchItem rpsi;
  private EditText search;
  
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
    if (((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().size() != 0)
    {
      i = 0;
      int j = k;
      if (i < ((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().size())
      {
        if (((RouteItem)((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().get(i)).getItemId() != localRouteItem.getItemId()) {
          break label300;
        }
        if ((!paramBoolean) && (localRouteItem.getQuantity() == 0))
        {
          ((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().remove(i);
          label231:
          j = 1;
        }
      }
      else if (j == 0)
      {
        ((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().add(localRouteItem);
      }
    }
    for (;;)
    {
      setQuantityCost();
      return;
      ((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().set(i, localRouteItem);
      break label231;
      label300:
      i += 1;
      break;
      if (paramBoolean) {
        ((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().add(localRouteItem);
      }
    }
  }
  
  private List<MartItem> getNotFoundMerchant()
  {
    ArrayList localArrayList = new ArrayList();
    MartItem localMartItem = new MartItem();
    localMartItem.id = -99;
    localMartItem.brand = Html.fromHtml("<center>" + getString(2131165697) + "</center>").toString();
    localMartItem.itemCategories = Html.fromHtml("<center>" + getString(2131165697) + "</center>").toString();
    localArrayList.add(localMartItem);
    return localArrayList;
  }
  
  private void gotToMartReview()
  {
    if (validateRouteItem())
    {
      Intent localIntent = new Intent(this, MartReview.class);
      localIntent.putExtra("BOOKING_DATA", mBookingStatus);
      localIntent.putExtra("MERCHANT", getIntent().getParcelableExtra("MART_MERCHANT"));
      localIntent.putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
      startActivityForResult(localIntent, 111);
    }
  }
  
  private void init()
  {
    this.listMartItem = new ArrayList();
    this.categoryDetailAdapter = new CategoryDetailAdapter(this, this, this.listMartItem, mBookingStatus);
    CategoryDetailAdapter.CategoryAdapterHandleClikInterface local4 = new CategoryDetailAdapter.CategoryAdapterHandleClikInterface()
    {
      public void onItem(int paramAnonymousInt, MartItem paramAnonymousMartItem)
      {
        Intent localIntent = new Intent(MartSearchByItem.this, MartProductDetail.class);
        localIntent.putExtra("MART_NAME", MartSearchByItem.this.getIntent().getStringExtra("MART_NAME"));
        localIntent.putExtra("MART_ID", MartSearchByItem.this.getIntent().getStringExtra("MART_ID"));
        localIntent.putExtra("MART_ITEM_ID", "" + paramAnonymousMartItem.getId());
        localIntent.putExtra("BOOKING_DATA", MartSearchByItem.mBookingStatus);
        localIntent.putExtra("LOCATION", MartSearchByItem.this.getIntent().getStringExtra("LOCATION"));
        if (paramAnonymousMartItem.getItemCategories() != null) {
          localIntent.putExtra("MART_ITEM_CATEGORY", paramAnonymousMartItem.getItemCategories().split(",")[0]);
        }
        MartSearchByItem.this.startActivityForResult(localIntent, 13);
      }
      
      public void onMinusButton(int paramAnonymousInt, MartItem paramAnonymousMartItem)
      {
        MartSearchByItem.this.buttonPlusMinusClicked(paramAnonymousMartItem, false);
      }
      
      public void onPlusButton(int paramAnonymousInt, MartItem paramAnonymousMartItem)
      {
        MartSearchByItem.this.buttonPlusMinusClicked(paramAnonymousMartItem, true);
      }
    };
    this.categoryDetailAdapter.setListener(local4);
    this.gridProduk.setAdapter(this.categoryDetailAdapter);
  }
  
  private void refreshRecentPurchase()
  {
    if (this.listMartItem != null)
    {
      int i = 0;
      if (i < this.listMartItem.size())
      {
        if (((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().size() == 0) {
          ((MartItem)this.listMartItem.get(i)).quantity = 0;
        }
        label197:
        for (;;)
        {
          i += 1;
          break;
          int j = 0;
          for (;;)
          {
            if (j >= ((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().size()) {
              break label197;
            }
            RouteItem localRouteItem = (RouteItem)((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().get(j);
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
      if (this.rpsi != null) {
        this.rpsi.notifyDataSetChanged();
      }
      this.categoryDetailAdapter.notifyDataSetChanged();
    }
  }
  
  private void searchItem()
  {
    findViewById(2131624214).setBackgroundColor(getResources().getColor(2131558493));
    this.mLLAddOtherItem.setVisibility(8);
    findViewById(2131624815).setVisibility(8);
    this.gridProduk.setVisibility(8);
    final Object localObject = this.search.getText().toString();
    if (((String)localObject).length() != 0)
    {
      this.labelRecentPurchase.setVisibility(8);
      this.recentPurchaseHorizontalListView.setVisibility(8);
      if (this.delayTask != null)
      {
        this.delayTask.cancel(true);
        this.delayTask = null;
      }
      this.listMartItem.clear();
      this.delayTask = new DelayTask(600, new DelayTask.CallBack()
      {
        public void onFinish()
        {
          MartSearchByItem.this.searchItem(MartSearchByItem.this.getIntent().getStringExtra("MART_ID"), localObject);
        }
      });
      localObject = this.delayTask;
      Void[] arrayOfVoid = new Void[0];
      if (!(localObject instanceof AsyncTask))
      {
        ((DelayTask)localObject).execute(arrayOfVoid);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)localObject, arrayOfVoid);
      return;
    }
    this.listMartItem.clear();
    this.gridProduk.setVisibility(8);
    this.recentPurchaseHorizontalListView.setVisibility(0);
    this.mLLAddOtherItem.setVisibility(8);
    findViewById(2131624815).setVisibility(8);
  }
  
  private void searchItem(String paramString1, final String paramString2)
  {
    if (this.listMartItem.size() == 0) {
      this.progres.setVisibility(0);
    }
    this.gridProduk.setVisibility(8);
    String str = Util.urlEncode(paramString2.trim());
    paramString1 = "https://api.gojek.co.id/gojek/mart-item/find?martId=" + paramString1 + "&keyword=" + str;
    VolleyClient.getInstance(this).getList("", paramString1, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        MartSearchByItem.this.listMartItem.clear();
        MartSearchByItem.this.progres.setVisibility(8);
        if (paramAnonymousVolleyError.getMessage() == null)
        {
          MartSearchByItem.this.gridProduk.setVisibility(8);
          MartSearchByItem.this.mLLAddOtherItem.setVisibility(0);
          MartSearchByItem.this.findViewById(2131624815).setVisibility(0);
        }
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        if (paramString2.length() >= 3)
        {
          if (paramAnonymousJSONArray.length() > 0)
          {
            Gson localGson = new Gson();
            Type localType;
            if (!(paramAnonymousJSONArray instanceof JSONArray))
            {
              paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
              localType = new MartSearchByItem.5.1(this).getType();
              if ((localGson instanceof Gson)) {
                break label166;
              }
            }
            label166:
            for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
            {
              paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
              MartSearchByItem.this.listMartItem.clear();
              MartSearchByItem.this.listMartItem.addAll(paramAnonymousJSONArray);
              MartSearchByItem.this.gridProduk.setVisibility(0);
              MartSearchByItem.this.mLLAddOtherItem.setVisibility(8);
              MartSearchByItem.this.findViewById(2131624815).setVisibility(8);
              MartSearchByItem.this.progres.setVisibility(8);
              MartSearchByItem.this.categoryDetailAdapter.notifyDataSetChanged();
              return;
              paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
              break;
            }
          }
          MartSearchByItem.this.listMartItem.clear();
          MartSearchByItem.this.gridProduk.setVisibility(8);
          MartSearchByItem.this.mLLAddOtherItem.setVisibility(0);
          MartSearchByItem.this.findViewById(2131624815).setVisibility(0);
          MartSearchByItem.this.progres.setVisibility(8);
          return;
        }
        MartSearchByItem.this.listMartItem.clear();
        MartSearchByItem.this.gridProduk.setVisibility(8);
        MartSearchByItem.this.mLLAddOtherItem.setVisibility(8);
        MartSearchByItem.this.findViewById(2131624815).setVisibility(8);
        MartSearchByItem.this.progres.setVisibility(8);
      }
    });
  }
  
  private boolean validateRouteItem()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((((Addresses)mBookingStatus.getAddresses().get(0)).getRouteItems().isEmpty()) && (this.otherRouteItems.isEmpty())) {
      localStringBuilder.append(getString(2131165698));
    }
    if (!localStringBuilder.toString().equals(""))
    {
      Toast.makeText(this, localStringBuilder.toString(), 0).show();
      return false;
    }
    return true;
  }
  
  public void afterTextChanged(final Editable paramEditable)
  {
    paramEditable = paramEditable.toString();
    findViewById(2131624214).setBackgroundColor(getResources().getColor(2131558493));
    this.mLLAddOtherItem.setVisibility(8);
    findViewById(2131624815).setVisibility(8);
    this.gridProduk.setVisibility(8);
    if (paramEditable.length() >= 3)
    {
      this.labelRecentPurchase.setVisibility(8);
      this.recentPurchaseHorizontalListView.setVisibility(8);
      if (this.delayTask != null)
      {
        this.delayTask.cancel(true);
        this.delayTask = null;
      }
      this.listMartItem.clear();
      this.delayTask = new DelayTask(600, new DelayTask.CallBack()
      {
        public void onFinish()
        {
          MartSearchByItem.this.searchItem(MartSearchByItem.this.getIntent().getStringExtra("MART_ID"), paramEditable);
        }
      });
      paramEditable = this.delayTask;
      Void[] arrayOfVoid = new Void[0];
      if (!(paramEditable instanceof AsyncTask))
      {
        paramEditable.execute(arrayOfVoid);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)paramEditable, arrayOfVoid);
      return;
    }
    this.listMartItem.clear();
    this.gridProduk.setVisibility(8);
    this.recentPurchaseHorizontalListView.setVisibility(0);
    this.mLLAddOtherItem.setVisibility(8);
    findViewById(2131624815).setVisibility(8);
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void loadRecentPurchase()
  {
    Object localObject = new Gson();
    String str = this.recentPurchasedBawaan;
    Type localType = new TypeToken() {}.getType();
    if (!(localObject instanceof Gson)) {}
    for (localObject = ((Gson)localObject).fromJson(str, localType);; localObject = GsonInstrumentation.fromJson((Gson)localObject, str, localType))
    {
      localObject = (List)localObject;
      this.rpsi = new RecentPurchaseSearchItem(this, getApplicationContext(), (List)localObject)
      {
        public long getItemId(int paramAnonymousInt)
        {
          return 0L;
        }
        
        protected void setData(MartItem paramAnonymousMartItem, TextView paramAnonymousTextView1, TextView paramAnonymousTextView2, TextView paramAnonymousTextView3) {}
      };
      localObject = new RecentPurchaseSearchItem.onItemClick()
      {
        public void itemClick(int paramAnonymousInt, MartItem paramAnonymousMartItem)
        {
          Intent localIntent = new Intent(MartSearchByItem.this, MartProductDetail.class);
          localIntent.putExtra("MART_NAME", MartSearchByItem.this.getIntent().getStringExtra("MART_NAME"));
          localIntent.putExtra("MART_ID", MartSearchByItem.this.getIntent().getStringExtra("MART_ID"));
          localIntent.putExtra("BOOKING_DATA", MartSearchByItem.mBookingStatus);
          localIntent.putExtra("MART_ITEM_ID", "" + paramAnonymousMartItem.getId());
          localIntent.putExtra("MART_CATEGORY_ID", MartSearchByItem.this.getIntent().getStringExtra("MART_CATEGORY_ID"));
          localIntent.putExtra("MART_CATEGORY_CODE", MartSearchByItem.this.getIntent().getStringExtra("MART_CATEGORY_CODE"));
          localIntent.putExtra("MART_CATEGORY_NAME", MartSearchByItem.this.getIntent().getStringExtra("MART_CATEGORY_NAME"));
          localIntent.putExtra("LOCATION", MartSearchByItem.this.getIntent().getStringExtra("LOCATION"));
          if (paramAnonymousMartItem.getItemCategories() != null) {
            localIntent.putExtra("MART_ITEM_CATEGORY", paramAnonymousMartItem.getItemCategories().split(",")[0]);
          }
          MartSearchByItem.this.startActivityForResult(localIntent, 12);
        }
      };
      this.rpsi.setListener((RecentPurchaseSearchItem.onItemClick)localObject);
      this.recentPurchaseHorizontalListView.setAdapter(this.rpsi);
      return;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    mBookingStatus = (BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA");
    setQuantityCost();
    refreshRecentPurchase();
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("MART_NAME", getIntent().getStringExtra("MART_NAME"));
    localIntent.putExtra("MART_ID", getIntent().getStringExtra("MART_ID"));
    localIntent.putExtra("BOOKING_DATA", mBookingStatus);
    localIntent.putExtra("MART_CATEGORY_ID", getIntent().getStringExtra("MART_CATEGORY_ID"));
    localIntent.putExtra("MART_CATEGORY_CODE", getIntent().getStringExtra("MART_CATEGORY_CODE"));
    localIntent.putExtra("MART_CATEGORY_NAME", getIntent().getStringExtra("MART_CATEGORY_NAME"));
    setResult(-1, localIntent);
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle("SEARCH ITEM");
    this.action.setIcon(null);
    setScreenSize();
    getWindow().setSoftInputMode(4);
    setContentView(2130968773);
    this.progres = ((ProgressBar)findViewById(2131624560));
    this.gridProduk = ((GridView)findViewById(2131624785));
    this.search = ((EditText)findViewById(2131624215));
    this.recentPurchaseHorizontalListView = ((ListView)findViewById(2131624806));
    this.mLLAddOtherItem = ((LinearLayout)findViewById(2131624824));
    this.labelRecentPurchase = findViewById(2131624835);
    this.mIVSearch = ((ImageView)findViewById(2131624216));
    reviewLayout = (RelativeLayout)findViewById(2131624488);
    reviewLayout.setVisibility(8);
    mTVManyAndCost = (XTextView)findViewById(2131624497);
    this.mTVReviewBtn = ((XTextView)findViewById(2131624490));
    this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MartSearchByItem.this.gotToMartReview();
      }
    });
    this.mLLAddOtherItem.setVisibility(8);
    findViewById(2131624815).setVisibility(8);
    this.search.addTextChangedListener(this);
    this.search.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        MartSearchByItem.this.hideKeyboard();
        MartSearchByItem.this.searchItem();
        return true;
      }
    });
    this.notFoundMerchants = getNotFoundMerchant();
    this.listMartItem = new ArrayList();
    this.mLLAddOtherItem.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(MartSearchByItem.this, MartOtherItem.class);
        paramAnonymousView.putExtra("MART_ID", MartSearchByItem.this.getIntent().getStringExtra("MART_ID"));
        paramAnonymousView.putExtra("BOOKING_DATA", MartSearchByItem.mBookingStatus);
        paramAnonymousView.putExtra("LOCATION", MartSearchByItem.this.getIntent().getStringExtra("LOCATION"));
        paramAnonymousView.putExtra("MART_MERCHANT", MartSearchByItem.this.getIntent().getParcelableExtra("MART_MERCHANT"));
        MartSearchByItem.this.startActivity(paramAnonymousView);
      }
    });
    this.recentPurchasedBawaan = getIntent().getStringExtra("JSON_RECENT_PURCHASE");
    mBookingStatus = (BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA");
    if (this.recentPurchasedBawaan != null)
    {
      loadRecentPurchase();
      setQuantityCost();
    }
    init();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  protected void onStop()
  {
    VolleyClient.getInstance(this).cancelAllRequest();
    super.onStop();
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void setQuantityCost()
  {
    tempCost = 0;
    tempQuantity = 0;
    Iterator localIterator = ((Addresses)mBookingStatus.addresses.get(0)).getRouteItems().iterator();
    while (localIterator.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)localIterator.next();
      tempCost += localRouteItem.getPrice() * localRouteItem.quantity;
      tempQuantity += 1;
      ((Addresses)mBookingStatus.addresses.get(0)).foodCostTotal = tempCost;
      ((Addresses)mBookingStatus.addresses.get(0)).foodQuantityTotal = tempQuantity;
    }
    if (tempQuantity != 0)
    {
      reviewLayout.setVisibility(0);
      mTVManyAndCost.setText(String.format("(%1$s items) %2$s", new Object[] { Integer.valueOf(tempQuantity), Util.getRupiahFormat(String.valueOf(tempCost)) }));
      return;
    }
    reviewLayout.setVisibility(8);
  }
  
  @SuppressLint({"NewApi"})
  public void setScreenSize()
  {
    if (Build.VERSION.SDK_INT >= 11) {
      localObject = new Point();
    }
    try
    {
      getWindowManager().getDefaultDisplay().getRealSize((Point)localObject);
      this.mScreenWidth = ((Point)localObject).x;
      this.mScreenHeight = ((Point)localObject).y;
      return;
    }
    catch (NoSuchMethodError localNoSuchMethodError) {}
    Object localObject = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    this.mScreenWidth = ((DisplayMetrics)localObject).widthPixels;
    this.mScreenHeight = ((DisplayMetrics)localObject).heightPixels;
    return;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MartSearchByItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */