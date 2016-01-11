package com.gojek.app.mart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.custom.MutableWatcher;
import com.gojek.app.model.MartItem;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MartOtherItem
  extends GojekActivityBase
{
  public static final String OTHER_ITEM_CATEGORY = "OTHER_ITEM";
  private static final String TAG = MartOtherItem.class.getSimpleName();
  private DecimalFormat df = new DecimalFormat("#,###.##");
  private DecimalFormat dfnd = new DecimalFormat("#,###");
  private boolean isDifferent;
  private ItemAdapter itemAdapter;
  private List<MartItem> listOtherItem;
  private ArrayList<RouteItem> listOtherRouteItems;
  private BookingStatus mBookingData;
  private ListView mLVMenuDetail;
  private RelativeLayout mRLAddMerchant;
  private RelativeLayout mRLReviewCost;
  private TextView mTVManyAndCost;
  private TextView mTVReviewBtn;
  private Map<Integer, RouteItem> mapRouteItems;
  private MartMerchant martMerchant;
  private int martMerchantID;
  private int randomFoodId = 0;
  private int tmpQuantity = 0;
  private int totalCost = 0;
  private int totalQuantity = 0;
  
  private MartItem createOtherFoodItem()
  {
    this.randomFoodId += 1;
    MartItem localMartItem = new MartItem();
    localMartItem.setId(this.randomFoodId);
    localMartItem.setMerchantId(String.valueOf(this.martMerchantID));
    localMartItem.setPrice("0");
    localMartItem.setItemCategories("OTHER_ITEM");
    return localMartItem;
  }
  
  private void gotoReview()
  {
    ArrayList localArrayList = mapToList(this.mapRouteItems);
    Object localObject = (Addresses)this.mBookingData.addresses.get(0);
    ((Addresses)this.mBookingData.addresses.get(0)).martMerchant = this.martMerchant;
    ((Addresses)localObject).foodQuantityTotal = this.totalQuantity;
    ((Addresses)localObject).foodCostTotal = this.totalCost;
    localObject = new Intent(this, MartReview.class);
    ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingData);
    ((Intent)localObject).putExtra("MART_ID", this.martMerchant);
    ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", localArrayList);
    ((Intent)localObject).putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    ((Intent)localObject).putExtra("MART_MERCHANT", getIntent().getParcelableExtra("MART_MERCHANT"));
    startActivityForResult((Intent)localObject, 111);
  }
  
  private void init()
  {
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.martMerchant = ((MartMerchant)getIntent().getParcelableExtra("MART_MERCHANT"));
    this.listOtherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    this.totalQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    this.totalCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    setQuantityCost();
    this.listOtherItem = new ArrayList();
    this.mapRouteItems = new LinkedHashMap();
    if (this.listOtherRouteItems == null) {
      this.listOtherRouteItems = new ArrayList();
    }
    if (this.listOtherRouteItems != null)
    {
      this.mapRouteItems = listToMap(this.listOtherRouteItems);
      this.listOtherItem = routeToFoodItem(this.listOtherRouteItems);
      this.randomFoodId = this.listOtherItem.size();
    }
    for (;;)
    {
      this.martMerchantID = Integer.valueOf(getIntent().getStringExtra("MART_ID")).intValue();
      this.isDifferent = false;
      if ((((Addresses)this.mBookingData.addresses.get(0)).martMerchant.martId != 0) && (this.martMerchantID != ((Addresses)this.mBookingData.addresses.get(0)).martMerchant.martId)) {
        this.isDifferent = true;
      }
      if (this.listOtherRouteItems.size() == 0) {
        this.listOtherItem.add(createOtherFoodItem());
      }
      this.itemAdapter = new ItemAdapter(this);
      this.mLVMenuDetail.setAdapter(this.itemAdapter);
      this.mRLAddMerchant.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((MartOtherItem.this.tmpQuantity != MartOtherItem.this.totalQuantity) || (MartOtherItem.this.tmpQuantity == 0))
          {
            MartOtherItem.access$208(MartOtherItem.this);
            MartOtherItem.this.listOtherItem.add(MartOtherItem.this.createOtherFoodItem());
            MartOtherItem.this.itemAdapter.notifyDataSetChanged();
            Util.hideSoftKeyboard(MartOtherItem.this, MartOtherItem.this.mRLAddMerchant);
            MartOtherItem.access$002(MartOtherItem.this, MartOtherItem.this.totalQuantity);
          }
        }
      });
      this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (MartOtherItem.this.validateRouteItem()) {
            MartOtherItem.this.gotoReview();
          }
        }
      });
      return;
      this.mapRouteItems = listToMap(new ArrayList());
    }
  }
  
  private void initView()
  {
    setTitle(getString(2131165612));
    this.mRLAddMerchant = ((RelativeLayout)findViewById(2131624511));
    this.mRLReviewCost = ((RelativeLayout)findViewById(2131624488));
    this.mLVMenuDetail = ((ListView)findViewById(2131624496));
    this.mTVReviewBtn = ((TextView)findViewById(2131624490));
    this.mTVManyAndCost = ((TextView)findViewById(2131624497));
  }
  
  private Map<Integer, RouteItem> listToMap(List<RouteItem> paramList)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)paramList.next();
      localLinkedHashMap.put(Integer.valueOf(localRouteItem.getItemId()), localRouteItem);
    }
    return localLinkedHashMap;
  }
  
  private ArrayList<RouteItem> mapToList(Map<Integer, RouteItem> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      if ((((RouteItem)paramMap.get(localInteger)).getQuantity() > 0) && (!TextUtils.isEmpty(((RouteItem)paramMap.get(localInteger)).getItemName())) && (((RouteItem)paramMap.get(localInteger)).getPrice() > 0)) {
        localArrayList.add(paramMap.get(localInteger));
      }
    }
    return localArrayList;
  }
  
  private void plusButton(TextView paramTextView, MartItem paramMartItem)
  {
    ((Addresses)this.mBookingData.addresses.get(0)).martMerchant = this.martMerchant;
    int i = Integer.parseInt(paramTextView.getText().toString()) + 1;
    paramTextView.setText(String.valueOf(i));
    this.totalCost += Integer.parseInt(paramMartItem.getPrice());
    if (this.mapRouteItems.containsKey(Integer.valueOf(paramMartItem.getId()))) {
      ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramMartItem.getId()))).setQuantity(i);
    }
    for (;;)
    {
      setQuantityCost();
      return;
      paramTextView = new RouteItem();
      paramTextView.setId(0);
      paramTextView.setPrice(Integer.parseInt(paramMartItem.getPrice()));
      paramTextView.setItemId(paramMartItem.getId());
      paramTextView.setItemName(paramMartItem.getBrand());
      paramTextView.setImageUrl(paramMartItem.getImgLocation());
      paramTextView.setQuantity(i);
      this.mapRouteItems.put(Integer.valueOf(paramMartItem.id), paramTextView);
      this.totalQuantity += 1;
    }
  }
  
  private List<MartItem> routeToFoodItem(List<RouteItem> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)paramList.next();
      MartItem localMartItem = new MartItem();
      localMartItem.setId(localRouteItem.getItemId());
      localMartItem.setBrand(localRouteItem.getItemName());
      localMartItem.setPrice(String.valueOf(localRouteItem.getPrice()));
      localMartItem.setMerchantId(String.valueOf(this.martMerchantID));
      localMartItem.setItemCategories("OTHER_ITEM");
      localArrayList.add(localMartItem);
    }
    return localArrayList;
  }
  
  private void setQuantityCost()
  {
    if (this.totalQuantity > 0)
    {
      this.mRLReviewCost.setVisibility(0);
      this.mTVManyAndCost.setText(String.format(getString(2131165577), new Object[] { Integer.valueOf(this.totalQuantity), Util.getRupiahFormat(String.valueOf(this.totalCost)) }));
      return;
    }
    this.mRLReviewCost.setVisibility(8);
  }
  
  private boolean validate(EditText paramEditText1, EditText paramEditText2)
  {
    boolean bool = true;
    paramEditText1 = paramEditText1.getText().toString();
    paramEditText2 = paramEditText2.getText().toString();
    StringBuilder localStringBuilder = new StringBuilder();
    if (!Util.isTextNotNullEmpty(paramEditText1)) {
      localStringBuilder.append("- " + getString(2131165517));
    }
    if (!Util.isTextNotNullEmpty(paramEditText2))
    {
      if (!localStringBuilder.toString().isEmpty()) {
        localStringBuilder.append("\n");
      }
      localStringBuilder.append("- " + getString(2131165518));
    }
    if (!localStringBuilder.toString().isEmpty())
    {
      showSimpleDialog(null, getString(2131165732) + "\n" + localStringBuilder.toString(), "OK", true, null);
      bool = false;
    }
    return bool;
  }
  
  private boolean validateRouteItem()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((this.mapRouteItems.isEmpty()) && (((Addresses)this.mBookingData.addresses.get(0)).routeItems.isEmpty())) {
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
      this.listOtherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
      if (this.listOtherRouteItems != null)
      {
        this.mapRouteItems = listToMap(this.listOtherRouteItems);
        this.listOtherItem = routeToFoodItem(this.listOtherRouteItems);
        this.randomFoodId = this.listOtherItem.size();
        this.itemAdapter.notifyDataSetChanged();
        this.totalQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
        this.totalCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
        this.tmpQuantity = 0;
        setQuantityCost();
      }
    }
  }
  
  public void onBackPressed()
  {
    ArrayList localArrayList = mapToList(this.mapRouteItems);
    Object localObject = (Addresses)this.mBookingData.addresses.get(0);
    ((Addresses)localObject).foodQuantityTotal = this.totalQuantity;
    ((Addresses)localObject).foodCostTotal = this.totalCost;
    localObject = new Intent();
    ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingData);
    ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", localArrayList);
    ((Intent)localObject).putExtra("MART_ID", this.martMerchant);
    ((Intent)localObject).putExtra("LOCATION", getIntent().getStringExtra("LOCATION"));
    ((Intent)localObject).putExtra("MART_MERCHANT", getIntent().getParcelableExtra("MART_MERCHANT"));
    setResult(-1, (Intent)localObject);
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968698);
    initView();
    init();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  class ItemAdapter
    extends BaseAdapter
  {
    private LayoutInflater mlayoutInflater;
    
    public ItemAdapter(Context paramContext)
    {
      this.mlayoutInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    }
    
    public int getCount()
    {
      return MartOtherItem.this.listOtherItem.size();
    }
    
    public MartItem getItem(int paramInt)
    {
      return (MartItem)MartOtherItem.this.listOtherItem.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return getItem(paramInt).getId();
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      MartItem localMartItem = getItem(paramInt);
      if (paramView == null)
      {
        paramView = this.mlayoutInflater.inflate(2130968699, null);
        paramViewGroup = new MartOtherItem.ViewHolder(MartOtherItem.this);
        paramViewGroup.mLLAddNote = ((LinearLayout)paramView.findViewById(2131624506));
        paramViewGroup.mLLItemNote = ((LinearLayout)paramView.findViewById(2131624507));
        paramViewGroup.mETItemNote = ((EditText)paramView.findViewById(2131624508));
        paramViewGroup.mETItemName = ((EditText)paramView.findViewById(2131624515));
        paramViewGroup.mETItemPrice = ((EditText)paramView.findViewById(2131624517));
        paramViewGroup.mTVPlus = ((TextView)paramView.findViewById(2131624505));
        paramViewGroup.mTVQuantity = ((TextView)paramView.findViewById(2131624504));
        paramViewGroup.mTVMinus = ((TextView)paramView.findViewById(2131624503));
        paramViewGroup.mutableWatcherItemName = new MartOtherItem.ItemAdapter.1(this);
        paramViewGroup.mutableWatcherItemPrice = new MartOtherItem.ItemAdapter.2(this);
        paramViewGroup.mutableWatcherItemNote = new MartOtherItem.ItemAdapter.3(this);
        paramViewGroup.mETItemNote.addTextChangedListener(paramViewGroup.mutableWatcherItemNote);
        paramViewGroup.mETItemName.addTextChangedListener(paramViewGroup.mutableWatcherItemName);
        paramViewGroup.mETItemPrice.addTextChangedListener(paramViewGroup.mutableWatcherItemPrice);
        paramViewGroup.mETItemPrice.addTextChangedListener(new MartOtherItem.ItemAdapter.4(this, paramViewGroup));
        paramView.setTag(paramViewGroup);
        Object localObject = new MartOtherItem.ItemAdapter.5(this, paramViewGroup);
        paramViewGroup.mLLAddNote.setOnClickListener((View.OnClickListener)localObject);
        localObject = new MartOtherItem.ItemAdapter.6(this, paramViewGroup, localMartItem, paramInt);
        paramViewGroup.mTVPlus.setOnClickListener((View.OnClickListener)localObject);
        paramViewGroup.mTVMinus.setOnClickListener((View.OnClickListener)localObject);
        paramViewGroup.mETItemName.setText("");
        paramViewGroup.mETItemPrice.setText("");
        paramViewGroup.mTVQuantity.setText(String.valueOf(0));
        paramViewGroup.mLLItemNote.setVisibility(8);
        paramViewGroup.mutableWatcherItemName.setActive(false);
        paramViewGroup.mutableWatcherItemPrice.setActive(false);
        paramViewGroup.mutableWatcherItemNote.setActive(false);
        paramViewGroup.mutableWatcherItemName.setPosition(paramInt);
        paramViewGroup.mutableWatcherItemName.setActive(true);
        paramViewGroup.mutableWatcherItemPrice.setPosition(paramInt);
        paramViewGroup.mutableWatcherItemPrice.setActive(true);
        if ((MartOtherItem.this.mapRouteItems.containsKey(Integer.valueOf(localMartItem.getId()))) && (!MartOtherItem.this.isDifferent)) {
          paramViewGroup.mTVQuantity.setText(String.valueOf(((RouteItem)MartOtherItem.this.mapRouteItems.get(Integer.valueOf(localMartItem.getId()))).quantity));
        }
        if (!paramViewGroup.mTVQuantity.getText().toString().equals("0"))
        {
          paramViewGroup.mLLItemNote.setVisibility(0);
          paramViewGroup.mETItemNote.setText(((RouteItem)MartOtherItem.this.mapRouteItems.get(Integer.valueOf(localMartItem.getId()))).notes);
          if (!Util.isTextNotNullEmpty(paramViewGroup.mETItemNote.getText().toString())) {
            paramViewGroup.mLLItemNote.setVisibility(8);
          }
          paramViewGroup.mutableWatcherItemNote.setPosition(paramInt);
          paramViewGroup.mutableWatcherItemNote.setActive(true);
          paramViewGroup.mutableWatcherItemName.setPosition(paramInt);
          paramViewGroup.mutableWatcherItemName.setActive(true);
        }
        if (!Util.isTextNotNullEmpty(localMartItem.getBrand())) {
          break label623;
        }
        paramViewGroup.mETItemName.setText(localMartItem.getBrand());
      }
      for (;;)
      {
        if (Integer.parseInt(localMartItem.getPrice()) <= 0) {
          break label694;
        }
        paramViewGroup.mETItemPrice.setText(String.valueOf(localMartItem.getPrice()));
        return paramView;
        paramViewGroup = (MartOtherItem.ViewHolder)paramView.getTag();
        break;
        label623:
        if (MartOtherItem.this.mapRouteItems.containsKey(Integer.valueOf(localMartItem.getId()))) {
          paramViewGroup.mETItemName.setText(((RouteItem)MartOtherItem.this.mapRouteItems.get(Integer.valueOf(localMartItem.getId()))).getItemName());
        } else {
          paramViewGroup.mETItemName.setText("");
        }
      }
      label694:
      if (MartOtherItem.this.mapRouteItems.containsKey(Integer.valueOf(localMartItem.getId())))
      {
        paramViewGroup.mETItemPrice.setText(String.valueOf(((RouteItem)MartOtherItem.this.mapRouteItems.get(Integer.valueOf(localMartItem.getId()))).getPrice()));
        return paramView;
      }
      paramViewGroup.mETItemPrice.setText("");
      return paramView;
    }
  }
  
  class ViewHolder
  {
    EditText mETItemName;
    EditText mETItemNote;
    EditText mETItemPrice;
    LinearLayout mLLAddNote;
    LinearLayout mLLItemNote;
    TextView mTVMinus;
    TextView mTVPlus;
    TextView mTVQuantity;
    MutableWatcher mutableWatcherItemName;
    MutableWatcher mutableWatcherItemNote;
    MutableWatcher mutableWatcherItemPrice;
    
    ViewHolder() {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MartOtherItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */