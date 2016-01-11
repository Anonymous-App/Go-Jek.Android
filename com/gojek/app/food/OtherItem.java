package com.gojek.app.food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.gojek.app.model.FoodItem;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OtherItem
  extends GojekActivityBase
{
  public static final String OTHER_ITEM_CATEGORY = "OTHER_ITEM";
  private static final String TAG = OtherItem.class.getSimpleName();
  private boolean isDifferent;
  private String isOpen;
  private ItemAdapter itemAdapter;
  private List<FoodItem> listOtherItem;
  private ArrayList<RouteItem> listOtherRouteItems;
  private BookingStatus mBookingData;
  private ListView mLVMenuDetail;
  private RelativeLayout mRLAddMerchant;
  private TextView mTVManyAndCost;
  private TextView mTVReviewBtn;
  private Map<Integer, RouteItem> mapRouteItems;
  private Merchant merchant;
  private int merchantId;
  private int randomFoodId = 0;
  private int totalCost = 0;
  private int totalQuantity = 0;
  
  private FoodItem createOtherFoodItem()
  {
    this.randomFoodId += 1;
    FoodItem localFoodItem = new FoodItem();
    localFoodItem.setId(this.randomFoodId);
    localFoodItem.setMerchantId(this.merchant.getId());
    localFoodItem.setPrice(0);
    localFoodItem.setItemCategories("OTHER_ITEM");
    return localFoodItem;
  }
  
  private void gotoReview()
  {
    ArrayList localArrayList = mapToList(this.mapRouteItems);
    Object localObject = (Addresses)this.mBookingData.addresses.get(0);
    ((Addresses)localObject).foodQuantityTotal = this.totalQuantity;
    ((Addresses)localObject).foodCostTotal = this.totalCost;
    localObject = new Intent(this, FoodReview.class);
    ((Intent)localObject).putExtra("BOOKING_DATA", this.mBookingData);
    ((Intent)localObject).putExtra("MERCHANT", this.merchant);
    ((Intent)localObject).putParcelableArrayListExtra("OTHER_ITEM", localArrayList);
    startActivityForResult((Intent)localObject, 111);
  }
  
  private void init()
  {
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.merchant = ((Merchant)getIntent().getParcelableExtra("MERCHANT"));
    this.isOpen = getIntent().getStringExtra("Merchant_OfficialHour");
    this.listOtherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    this.totalQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
    this.totalCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
    setQuantityCost();
    this.listOtherItem = new ArrayList();
    this.mapRouteItems = new LinkedHashMap();
    if (this.listOtherRouteItems != null)
    {
      this.mapRouteItems = listToMap(this.listOtherRouteItems);
      this.listOtherItem = routeToFoodItem(this.listOtherRouteItems);
      this.randomFoodId = this.listOtherItem.size();
    }
    for (;;)
    {
      this.merchantId = this.merchant.getId();
      this.isDifferent = false;
      if ((((Addresses)this.mBookingData.addresses.get(0)).merchant.getId() != 0) && (this.merchantId != ((Addresses)this.mBookingData.addresses.get(0)).merchant.getId())) {
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
          OtherItem.access$008(OtherItem.this);
          OtherItem.this.listOtherItem.add(OtherItem.this.createOtherFoodItem());
          OtherItem.this.itemAdapter.notifyDataSetChanged();
          Util.hideSoftKeyboard(OtherItem.this, OtherItem.this.mRLAddMerchant);
        }
      });
      this.mTVReviewBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((OtherItem.this.validateOtherItem()) && (OtherItem.this.validateRouteItem())) {
            OtherItem.this.gotoReview();
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
      if ((((RouteItem)paramMap.get(localInteger)).getQuantity() > 0) && (!TextUtils.isEmpty(((RouteItem)paramMap.get(localInteger)).getItemName())) && (((RouteItem)paramMap.get(localInteger)).getPrice() > 0))
      {
        localArrayList.add(paramMap.get(localInteger));
      }
      else
      {
        int i = ((RouteItem)paramMap.get(localInteger)).getQuantity();
        int j = ((RouteItem)paramMap.get(localInteger)).getPrice();
        this.totalCost -= i * j;
        this.totalQuantity -= ((RouteItem)paramMap.get(localInteger)).getQuantity();
      }
    }
    return localArrayList;
  }
  
  private void plusButton(TextView paramTextView, FoodItem paramFoodItem)
  {
    if ((this.isOpen.equals("true")) || (this.isOpen.equals("")) || (this.isOpen.equals("null")))
    {
      ((Addresses)this.mBookingData.addresses.get(0)).merchant = this.merchant;
      int i = Integer.parseInt(paramTextView.getText().toString()) + 1;
      paramTextView.setText(String.valueOf(i));
      this.totalCost += paramFoodItem.getPrice();
      if (this.mapRouteItems.containsKey(Integer.valueOf(paramFoodItem.getId()))) {
        ((RouteItem)this.mapRouteItems.get(Integer.valueOf(paramFoodItem.getId()))).setQuantity(i);
      }
      for (;;)
      {
        this.totalQuantity += 1;
        setQuantityCost();
        return;
        paramTextView = new RouteItem();
        paramTextView.setId(0);
        paramTextView.setPrice(paramFoodItem.getPrice());
        paramTextView.setItemId(paramFoodItem.getId());
        paramTextView.setItemName(paramFoodItem.getName());
        paramTextView.setQuantity(i);
        this.mapRouteItems.put(Integer.valueOf(paramFoodItem.id), paramTextView);
      }
    }
    Util.confirmDialog(this, new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage) {}
    }, "Your restaurant is currently closed :(", "SORRY");
  }
  
  private List<FoodItem> routeToFoodItem(List<RouteItem> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      RouteItem localRouteItem = (RouteItem)paramList.next();
      FoodItem localFoodItem = new FoodItem();
      localFoodItem.setId(localRouteItem.getItemId());
      localFoodItem.setName(localRouteItem.getItemName());
      localFoodItem.setPrice(localRouteItem.getPrice());
      localFoodItem.setMerchantId(this.merchantId);
      localFoodItem.setItemCategories("OTHER_ITEM");
      localArrayList.add(localFoodItem);
    }
    return localArrayList;
  }
  
  private void setQuantityCost()
  {
    this.mTVManyAndCost.setText(String.format(getString(2131165577), new Object[] { Integer.valueOf(this.totalQuantity), Util.getRupiahFormat(String.valueOf(this.totalCost)) }));
  }
  
  private boolean validate(String paramString1, String paramString2)
  {
    boolean bool = true;
    StringBuilder localStringBuilder = new StringBuilder();
    if ((!Util.isTextNotNullEmpty(paramString1)) || (paramString1.trim().length() <= 0)) {
      localStringBuilder.append("- " + getString(2131165517));
    }
    if ((!Util.isTextNotNullEmpty(paramString2)) || (Integer.parseInt(paramString2) <= 0))
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
  
  private boolean validateOtherItem()
  {
    boolean bool1 = true;
    Iterator localIterator = this.mapRouteItems.keySet().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      boolean bool2 = validate(((RouteItem)this.mapRouteItems.get(localInteger)).getItemName(), String.valueOf(((RouteItem)this.mapRouteItems.get(localInteger)).getPrice()));
      bool1 = bool2;
      if (!bool2) {
        bool1 = bool2;
      }
    }
    return bool1;
  }
  
  private boolean validateRouteItem()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((this.mapRouteItems.isEmpty()) && (((Addresses)this.mBookingData.addresses.get(0)).routeItems.isEmpty())) {
      localStringBuilder.append(getString(2131165698));
    }
    Iterator localIterator = this.mapRouteItems.keySet().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      if ((((RouteItem)this.mapRouteItems.get(localInteger)).getQuantity() <= 0) || (TextUtils.isEmpty(((RouteItem)this.mapRouteItems.get(localInteger)).getItemName())) || (((RouteItem)this.mapRouteItems.get(localInteger)).getPrice() <= 0))
      {
        this.totalQuantity -= ((RouteItem)this.mapRouteItems.get(localInteger)).getQuantity();
        this.mapRouteItems.remove(localInteger);
      }
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
      this.listOtherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
      if (this.listOtherRouteItems != null)
      {
        this.mapRouteItems = listToMap(this.listOtherRouteItems);
        this.listOtherItem = routeToFoodItem(this.listOtherRouteItems);
        this.randomFoodId = this.listOtherItem.size();
        this.itemAdapter.notifyDataSetChanged();
        this.totalQuantity = ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal;
        this.totalCost = ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal;
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
      return OtherItem.this.listOtherItem.size();
    }
    
    public FoodItem getItem(int paramInt)
    {
      return (FoodItem)OtherItem.this.listOtherItem.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return getItem(paramInt).getId();
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      FoodItem localFoodItem = getItem(paramInt);
      if (paramView == null)
      {
        paramView = this.mlayoutInflater.inflate(2130968699, null);
        paramViewGroup = new OtherItem.ViewHolder(OtherItem.this);
        paramViewGroup.mLLAddNote = ((LinearLayout)paramView.findViewById(2131624506));
        paramViewGroup.mLLItemNote = ((LinearLayout)paramView.findViewById(2131624507));
        paramViewGroup.mETItemNote = ((EditText)paramView.findViewById(2131624508));
        paramViewGroup.mETItemName = ((EditText)paramView.findViewById(2131624515));
        paramViewGroup.mETItemPrice = ((EditText)paramView.findViewById(2131624517));
        paramViewGroup.mTVPlus = ((TextView)paramView.findViewById(2131624505));
        paramViewGroup.mTVQuantity = ((TextView)paramView.findViewById(2131624504));
        paramViewGroup.mTVMinus = ((TextView)paramView.findViewById(2131624503));
        paramViewGroup.mutableWatcherItemName = new OtherItem.ItemAdapter.1(this);
        paramViewGroup.mutableWatcherItemPrice = new OtherItem.ItemAdapter.2(this);
        paramViewGroup.mutableWatcherItemNote = new OtherItem.ItemAdapter.3(this);
        paramViewGroup.mETItemNote.addTextChangedListener(paramViewGroup.mutableWatcherItemNote);
        paramViewGroup.mETItemName.addTextChangedListener(paramViewGroup.mutableWatcherItemName);
        paramViewGroup.mETItemPrice.addTextChangedListener(paramViewGroup.mutableWatcherItemPrice);
        paramView.setTag(paramViewGroup);
        Object localObject = new OtherItem.ItemAdapter.4(this, paramViewGroup);
        paramViewGroup.mLLAddNote.setOnClickListener((View.OnClickListener)localObject);
        localObject = new OtherItem.ItemAdapter.5(this, paramViewGroup, localFoodItem, paramInt);
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
        if ((OtherItem.this.mapRouteItems.containsKey(Integer.valueOf(localFoodItem.getId()))) && (!OtherItem.this.isDifferent)) {
          paramViewGroup.mTVQuantity.setText(String.valueOf(((RouteItem)OtherItem.this.mapRouteItems.get(Integer.valueOf(localFoodItem.getId()))).quantity));
        }
        if (!paramViewGroup.mTVQuantity.getText().toString().equals("0"))
        {
          paramViewGroup.mLLItemNote.setVisibility(0);
          paramViewGroup.mETItemNote.setText(((RouteItem)OtherItem.this.mapRouteItems.get(Integer.valueOf(localFoodItem.getId()))).notes);
          if (!Util.isTextNotNullEmpty(paramViewGroup.mETItemNote.getText().toString())) {
            paramViewGroup.mLLItemNote.setVisibility(8);
          }
          paramViewGroup.mutableWatcherItemNote.setPosition(paramInt);
          paramViewGroup.mutableWatcherItemNote.setActive(true);
          paramViewGroup.mutableWatcherItemName.setPosition(paramInt);
          paramViewGroup.mutableWatcherItemName.setActive(true);
        }
        if (!Util.isTextNotNullEmpty(localFoodItem.getName())) {
          break label604;
        }
        paramViewGroup.mETItemName.setText(localFoodItem.getName());
      }
      for (;;)
      {
        if (localFoodItem.getPrice() <= 0) {
          break label675;
        }
        paramViewGroup.mETItemPrice.setText(String.valueOf(localFoodItem.getPrice()));
        return paramView;
        paramViewGroup = (OtherItem.ViewHolder)paramView.getTag();
        break;
        label604:
        if (OtherItem.this.mapRouteItems.containsKey(Integer.valueOf(localFoodItem.getId()))) {
          paramViewGroup.mETItemName.setText(((RouteItem)OtherItem.this.mapRouteItems.get(Integer.valueOf(localFoodItem.getId()))).getItemName());
        } else {
          paramViewGroup.mETItemName.setText("");
        }
      }
      label675:
      if (OtherItem.this.mapRouteItems.containsKey(Integer.valueOf(localFoodItem.getId())))
      {
        paramViewGroup.mETItemPrice.setText(String.valueOf(((RouteItem)OtherItem.this.mapRouteItems.get(Integer.valueOf(localFoodItem.getId()))).getPrice()));
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/OtherItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */