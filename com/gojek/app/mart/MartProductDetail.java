package com.gojek.app.mart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gojek.app.adapter.mart.CategoryDetailAdapter;
import com.gojek.app.custom.ExpandableHeightGridView;
import com.gojek.app.custom.XTextView;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.Adult;
import com.gojek.app.model.BookingSaved;
import com.gojek.app.model.MartItem;
import com.gojek.app.model.MartYouMayAlsoLike;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Instrumented
public class MartProductDetail
  extends Activity
  implements TraceFieldInterface
{
  private static final String TAG = MartProductDetail.class.getSimpleName();
  private String MartId;
  private String MartItemId;
  private int MartMerchantId;
  CategoryDetailAdapter categoryDetailAdapter;
  private ImageView image;
  private ImageLoader imageLoader;
  private boolean isDifferent;
  private String itemCategories;
  private ArrayList<MartItem> itemList;
  private Adult mAdult;
  private BookingStatus mBookingData;
  private BookingSaved mBookingSaved;
  private EditText mETItemNote;
  private ExpandableHeightGridView mGVProductList;
  private ImageView mIVClose;
  private ImageView mIVHaram;
  private LinearLayout mLLAddNote;
  private LinearLayout mLLItemNote;
  private TextView mTVManyAndCost;
  private TextView mTVProductDescription;
  private TextView mTVProductPrice;
  private TextView mTVProductWeight;
  private TextView mTVReviewBtn;
  private XTextView mTitle;
  private MartYouMayAlsoLike mYouMAL;
  private Map<Integer, RouteItem> mapRouteItems;
  private MartHome martHome;
  private List<MartItem> martItems = new ArrayList();
  private MartMerchant martMerchant;
  private TextView minusButon;
  private ArrayList<RouteItem> otherRouteItems;
  private TextView plusButton;
  private TextView quantityButton;
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
      this.mGVProductList.setPadding(0, 0, 0, i + 10);
      findViewById(2131624488).setVisibility(0);
      this.mTVManyAndCost.setText(String.format(getString(2131165577), new Object[] { Integer.valueOf(this.tempQuantity), Util.getRupiahFormat(String.valueOf(this.tempCost)) }));
      return;
    }
    this.mGVProductList.setPadding(0, 0, 0, 0);
    findViewById(2131624488).setVisibility(8);
  }
  
  private void updateItemNote(RouteItem paramRouteItem)
  {
    if ((this.mapRouteItems.containsKey(Integer.valueOf(paramRouteItem.itemId))) && (Util.isTextNotNullEmpty(paramRouteItem.notes))) {
      ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramRouteItem.itemId))).notes = paramRouteItem.notes;
    }
  }
  
  private boolean validateRouteItem()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((this.mapRouteItems != null) && (this.otherRouteItems != null) && (this.mapRouteItems.isEmpty()) && (this.otherRouteItems.isEmpty())) {
      localStringBuilder.append(getString(2131165698));
    }
    if (!localStringBuilder.toString().equals(""))
    {
      Toast.makeText(this, localStringBuilder.toString(), 0).show();
      return false;
    }
    return true;
  }
  
  public void doLoadYouMayAlsoLike(int paramInt, String paramString1, String paramString2)
  {
    paramString1 = String.format("https://api.gojek.co.id/gojek/mart-item/item?id=%d&martId=%1s&itemCategories=%s", new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
    Log.d("Bima", paramString1);
    VolleyClient.getInstance(this).get(TAG, paramString1, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MartProductDetail.TAG, "loadCategory get error " + paramAnonymousVolleyError);
        MartProductDetail.this.findViewById(2131624818).setVisibility(0);
        MartProductDetail.this.findViewById(2131624784).setVisibility(8);
      }
      
      public void onResponse(MartYouMayAlsoLike paramAnonymousMartYouMayAlsoLike)
      {
        MartProductDetail.access$1002(MartProductDetail.this, paramAnonymousMartYouMayAlsoLike);
        MartProductDetail.this.mTitle.setText(MartProductDetail.this.mYouMAL.martItem.brand + ", " + MartProductDetail.this.mYouMAL.martItem.subBrand);
        MartProductDetail.this.mTVProductPrice.setText("Est. " + Util.getRupiahFormat(MartProductDetail.this.mYouMAL.martItem.price));
        MartProductDetail.this.mTVProductWeight.setText(MartProductDetail.this.mYouMAL.martItem.size);
        MartProductDetail.this.mTVProductDescription.setText(MartProductDetail.this.mYouMAL.martItem.itemDescription);
        if ((MartProductDetail.this.mapRouteItems.containsKey(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))) && (((RouteItem)MartProductDetail.this.mapRouteItems.get(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))).notes != ""))
        {
          MartProductDetail.this.mLLItemNote.setVisibility(0);
          MartProductDetail.this.mETItemNote.setText(((RouteItem)MartProductDetail.this.mapRouteItems.get(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))).notes);
        }
        MartProductDetail.this.findViewById(2131624818).setVisibility(0);
        MartProductDetail.this.findViewById(2131624784).setVisibility(8);
        if (MartProductDetail.this.mYouMAL.martItem.imgLocation != "") {
          Glide.with(MartProductDetail.this.getApplicationContext()).load(MartProductDetail.this.mYouMAL.martItem.imgLocation).placeholder(2131558630).diskCacheStrategy(DiskCacheStrategy.ALL).into(MartProductDetail.this.image);
        }
        RequestManager localRequestManager = Glide.with(MartProductDetail.this.getApplicationContext());
        if (MartProductDetail.this.mYouMAL.martItem.isHaram)
        {
          paramAnonymousMartYouMayAlsoLike = "https://dmkykyvzhmwd0.cloudfront.net/non_halal.png";
          localRequestManager.load(paramAnonymousMartYouMayAlsoLike).diskCacheStrategy(DiskCacheStrategy.ALL).into(MartProductDetail.this.mIVHaram);
          MartProductDetail.this.categoryDetailAdapter = new CategoryDetailAdapter(MartProductDetail.this.getParent(), MartProductDetail.this, MartProductDetail.this.mYouMAL.alsoLike, MartProductDetail.this.mBookingData);
          paramAnonymousMartYouMayAlsoLike = new MartProductDetail.AlsoLikeAdapter(MartProductDetail.this, MartProductDetail.this.getParent(), MartProductDetail.this, MartProductDetail.this.mYouMAL.alsoLike);
          if (!MartProductDetail.this.mapRouteItems.containsKey(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))) {
            break label637;
          }
          MartProductDetail.this.quantityButton.setText(String.valueOf(((RouteItem)MartProductDetail.this.mapRouteItems.get(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))).quantity));
        }
        for (;;)
        {
          MartProductDetail.this.mGVProductList.setExpanded(true);
          MartProductDetail.this.mGVProductList.setAdapter(paramAnonymousMartYouMayAlsoLike);
          MartProductDetail.this.mGVProductList.setVisibility(0);
          MartProductDetail.this.mETItemNote.addTextChangedListener(new MartProductDetail.6.1(this));
          return;
          paramAnonymousMartYouMayAlsoLike = "";
          break;
          label637:
          MartProductDetail.this.quantityButton.setText("0");
        }
      }
    }, MartYouMayAlsoLike.class);
  }
  
  public BookingStatus getmBookingData()
  {
    return this.mBookingData;
  }
  
  public void init()
  {
    this.martHome = new MartHome();
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    this.itemCategories = getIntent().getStringExtra("MART_ITEM_CATEGORY");
    this.mBookingSaved = new BookingSaved(this);
    this.mAdult = new Adult(this);
    this.martMerchant = ((Addresses)this.mBookingData.addresses.get(0)).martMerchant;
    this.tempCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    findViewById(2131624818).setVisibility(8);
    this.mGVProductList.setFocusable(false);
    Object localObject = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
    if (localObject == null) {}
    for (this.mapRouteItems = listToMap(new ArrayList());; this.mapRouteItems = listToMap((List)localObject))
    {
      this.MartMerchantId = this.martMerchant.id;
      this.imageLoader = ImageLoader.getInstance();
      localObject = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(Util.getDisplayImageConfig()).build();
      this.imageLoader.init((ImageLoaderConfiguration)localObject);
      setQuantityCost();
      this.MartItemId = getIntent().getStringExtra("MART_ITEM_ID");
      this.MartId = getIntent().getStringExtra("MART_ID");
      doLoadYouMayAlsoLike(Integer.parseInt(this.MartItemId), this.MartId, this.itemCategories);
      this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (MartProductDetail.this.validateRouteItem())
          {
            MartProductDetail.this.setmBookingData();
            paramAnonymousView = MartProductDetail.this.mapToList(MartProductDetail.this.mapRouteItems);
            Addresses localAddresses = (Addresses)MartProductDetail.this.mBookingData.addresses.get(0);
            localAddresses.routeItems = paramAnonymousView;
            localAddresses.foodQuantityTotal = MartProductDetail.this.tempQuantity;
            localAddresses.foodCostTotal = MartProductDetail.this.tempCost;
            paramAnonymousView = new Intent(MartProductDetail.this, MartReview.class);
            paramAnonymousView.putExtra("BOOKING_DATA", MartProductDetail.this.mBookingData);
            paramAnonymousView.putParcelableArrayListExtra("OTHER_ITEM", MartProductDetail.this.otherRouteItems);
            MartProductDetail.this.mBookingSaved.saveData(MartProductDetail.this.mBookingData, MartProductDetail.this.otherRouteItems);
            paramAnonymousView.putExtra("MERCHANT", MartProductDetail.this.martMerchant);
            paramAnonymousView.putExtra("LOCATION", MartProductDetail.this.getIntent().getStringExtra("LOCATION"));
            MartProductDetail.this.startActivityForResult(paramAnonymousView, 111);
          }
        }
      });
      this.mIVClose.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MartProductDetail.this.onBackPressed();
        }
      });
      this.plusButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          int j = 0;
          int i = j;
          if (!MartProductDetail.this.mAdult.isAdult())
          {
            i = j;
            if (MartProductDetail.this.mYouMAL.martItem.ageRestriction) {
              i = 1;
            }
          }
          paramAnonymousView = new AlertDialog.Builder(MartProductDetail.this);
          paramAnonymousView.setTitle("Confirm");
          paramAnonymousView.setMessage(2131165676);
          paramAnonymousView.setPositiveButton("YES", new MartProductDetail.3.1(this));
          paramAnonymousView.setNegativeButton("NO", new MartProductDetail.3.2(this));
          paramAnonymousView = paramAnonymousView.create();
          if ((i != 0) && (Integer.parseInt(MartProductDetail.this.quantityButton.getText().toString()) < 1)) {
            paramAnonymousView.show();
          }
          i = Integer.parseInt(MartProductDetail.this.quantityButton.getText().toString()) + 1;
          MartProductDetail.this.quantityButton.setText(String.valueOf(i));
          MartProductDetail.access$502(MartProductDetail.this, MartProductDetail.this.tempCost + Integer.parseInt(MartProductDetail.this.mYouMAL.martItem.price));
          if (MartProductDetail.this.mapRouteItems.containsKey(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))) {
            ((RouteItem)MartProductDetail.this.mapRouteItems.get(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))).quantity = i;
          }
          for (;;)
          {
            MartProductDetail.this.setQuantityCost();
            return;
            paramAnonymousView = new RouteItem();
            paramAnonymousView.id = 0;
            paramAnonymousView.price = Integer.parseInt(MartProductDetail.this.mYouMAL.martItem.price);
            paramAnonymousView.itemId = MartProductDetail.this.mYouMAL.martItem.id;
            paramAnonymousView.itemName = (MartProductDetail.this.mYouMAL.martItem.brand + ", " + MartProductDetail.this.mYouMAL.martItem.subBrand + " " + MartProductDetail.this.mYouMAL.martItem.size);
            paramAnonymousView.imageUrl = MartProductDetail.this.mYouMAL.martItem.imgLocation;
            paramAnonymousView.quantity = i;
            MartProductDetail.this.mapRouteItems.put(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id), paramAnonymousView);
            MartProductDetail.access$408(MartProductDetail.this);
          }
        }
      });
      this.minusButon.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          int i = Integer.parseInt(MartProductDetail.this.quantityButton.getText().toString());
          int j = i - 1;
          if (j >= 0)
          {
            Log.i(MartProductDetail.TAG, "minus food cost");
            MartProductDetail.access$502(MartProductDetail.this, MartProductDetail.this.tempCost - Integer.parseInt(MartProductDetail.this.mYouMAL.martItem.price));
          }
          if (j <= 0)
          {
            MartProductDetail.this.quantityButton.setText("0");
            MartProductDetail.this.mapRouteItems.remove(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id));
            MartProductDetail.this.mLLItemNote.setVisibility(8);
            MartProductDetail.this.mETItemNote.setText("");
            if (i != 0) {
              MartProductDetail.access$410(MartProductDetail.this);
            }
          }
          for (;;)
          {
            MartProductDetail.this.setQuantityCost();
            return;
            MartProductDetail.this.quantityButton.setText(j + "");
            if (MartProductDetail.this.mapRouteItems.containsKey(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))) {
              ((RouteItem)MartProductDetail.this.mapRouteItems.get(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))).quantity = j;
            }
          }
        }
      });
      this.mLLAddNote.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (MartProductDetail.this.mLLItemNote.getVisibility() == 8)
          {
            if (!MartProductDetail.this.quantityButton.getText().toString().equals("0")) {
              MartProductDetail.this.mLLItemNote.setVisibility(0);
            }
            return;
          }
          MartProductDetail.this.mLLItemNote.setVisibility(8);
          MartProductDetail.this.mETItemNote.setText("");
          ((RouteItem)MartProductDetail.this.mapRouteItems.get(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id))).notes = null;
          MartProductDetail.this.updateItemNote((RouteItem)MartProductDetail.this.mapRouteItems.get(Integer.valueOf(MartProductDetail.this.mYouMAL.martItem.id)));
        }
      });
      return;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    this.tempCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    this.tempQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    List localList = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
    if (localList == null) {}
    for (this.mapRouteItems = listToMap(new ArrayList());; this.mapRouteItems = listToMap(localList))
    {
      setQuantityCost();
      findViewById(2131624818).setVisibility(8);
      findViewById(2131624784).setVisibility(0);
      this.mGVProductList.setVisibility(8);
      doLoadYouMayAlsoLike(Integer.parseInt(this.MartItemId), this.MartId, this.itemCategories);
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    }
  }
  
  public void onBackPressed()
  {
    setmBookingData();
    Intent localIntent = new Intent();
    localIntent.putExtra("MART_NAME", getIntent().getStringExtra("MART_NAME"));
    localIntent.putExtra("MART_ID", getIntent().getStringExtra("MART_ID"));
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    this.mBookingSaved.saveData(this.mBookingData, this.otherRouteItems);
    localIntent.putExtra("MART_CATEGORY_ID", getIntent().getStringExtra("MART_CATEGORY_ID"));
    localIntent.putExtra("MART_CATEGORY_CODE", getIntent().getStringExtra("MART_CATEGORY_CODE"));
    localIntent.putExtra("MART_CATEGORY_NAME", getIntent().getStringExtra("MART_CATEGORY_NAME"));
    setResult(-1, localIntent);
    super.onBackPressed();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("MartProductDetail");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "MartProductDetail#onCreate", null);
      super.onCreate(paramBundle);
      renderView();
      init();
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "MartProductDetail#onCreate", null);
      }
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
  
  public void renderView()
  {
    setContentView(2130968767);
    this.mGVProductList = ((ExpandableHeightGridView)findViewById(2131624785));
    this.mTVReviewBtn = ((TextView)findViewById(2131624490));
    this.mTitle = ((XTextView)findViewById(2131624043));
    this.mTVProductPrice = ((TextView)findViewById(2131624698));
    this.mTVProductWeight = ((TextView)findViewById(2131624699));
    this.mTVProductDescription = ((TextView)findViewById(2131624819));
    this.mIVClose = ((ImageView)findViewById(2131624817));
    this.image = ((ImageView)findViewById(2131624039));
    this.mIVHaram = ((ImageView)findViewById(2131624786));
    this.minusButon = ((TextView)findViewById(2131624503));
    this.quantityButton = ((TextView)findViewById(2131624504));
    this.plusButton = ((TextView)findViewById(2131624505));
    this.mTVManyAndCost = ((TextView)findViewById(2131624497));
    this.mLLAddNote = ((LinearLayout)findViewById(2131624506));
    this.mLLItemNote = ((LinearLayout)findViewById(2131624507));
    this.mETItemNote = ((EditText)findViewById(2131624508));
  }
  
  public void setmBookingData()
  {
    List localList = mapToList(this.mapRouteItems);
    Addresses localAddresses = (Addresses)this.mBookingData.addresses.get(0);
    localAddresses.routeItems = localList;
    localAddresses.foodQuantityTotal = this.tempQuantity;
    localAddresses.foodCostTotal = this.tempCost;
  }
  
  class AlsoLikeAdapter
    extends BaseAdapter
  {
    private ImageLoader imageLoader;
    private Activity mActivity;
    private Context mContext;
    private List<MartItem> martItems;
    
    public AlsoLikeAdapter(Context paramContext, List<MartItem> paramList)
    {
      this.mActivity = paramContext;
      this.mContext = paramList;
      List localList;
      this.martItems = localList;
      this.imageLoader = ImageLoader.getInstance();
      this$1 = new ImageLoaderConfiguration.Builder(this.mContext).defaultDisplayImageOptions(Util.getDisplayImageConfig()).build();
      this.imageLoader.init(MartProductDetail.this);
    }
    
    public int getCount()
    {
      return this.martItems.size();
    }
    
    public Object getItem(int paramInt)
    {
      return this.martItems.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return ((MartItem)this.martItems.get(paramInt)).id;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = (LayoutInflater)MartProductDetail.this.getApplication().getSystemService("layout_inflater");
      if (paramView == null)
      {
        paramView = paramViewGroup.inflate(2130968750, null);
        paramViewGroup = (ImageView)paramView.findViewById(2131624039);
        ImageView localImageView = (ImageView)paramView.findViewById(2131624786);
        TextView localTextView1 = (TextView)paramView.findViewById(2131624043);
        TextView localTextView2 = (TextView)paramView.findViewById(2131624698);
        TextView localTextView3 = (TextView)paramView.findViewById(2131624699);
        TextView localTextView4 = (TextView)paramView.findViewById(2131624503);
        TextView localTextView5 = (TextView)paramView.findViewById(2131624504);
        TextView localTextView6 = (TextView)paramView.findViewById(2131624505);
        localTextView1.setText(((MartItem)this.martItems.get(paramInt)).brand);
        localTextView2.setText("Est. Rp. " + ((MartItem)this.martItems.get(paramInt)).price);
        localTextView3.setText(((MartItem)this.martItems.get(paramInt)).size);
        if (MartProductDetail.this.mapRouteItems.containsKey(Integer.valueOf(((MartItem)this.martItems.get(paramInt)).id))) {
          localTextView5.setText(String.valueOf(((RouteItem)MartProductDetail.this.mapRouteItems.get(Integer.valueOf(((MartItem)this.martItems.get(paramInt)).id))).quantity));
        }
        if (((MartItem)this.martItems.get(paramInt)).imgLocation != "") {
          Glide.with(MartProductDetail.this.getApplicationContext()).load(((MartItem)this.martItems.get(paramInt)).imgLocation).placeholder(2131558630).diskCacheStrategy(DiskCacheStrategy.ALL).into(paramViewGroup);
        }
        if (((MartItem)this.martItems.get(paramInt)).isHaram) {
          Glide.with(MartProductDetail.this.getApplicationContext()).load("https://dmkykyvzhmwd0.cloudfront.net/non_halal.png").diskCacheStrategy(DiskCacheStrategy.ALL).into(localImageView);
        }
        for (;;)
        {
          paramViewGroup.setOnClickListener(new MartProductDetail.AlsoLikeAdapter.1(this, paramInt));
          localTextView6.setOnClickListener(new MartProductDetail.AlsoLikeAdapter.2(this, paramInt, localTextView5));
          localTextView4.setOnClickListener(new MartProductDetail.AlsoLikeAdapter.3(this, localTextView5, paramInt));
          return paramView;
          localImageView.setVisibility(8);
        }
      }
      return paramView;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MartProductDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */