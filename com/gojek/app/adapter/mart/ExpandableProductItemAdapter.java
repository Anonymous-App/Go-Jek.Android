package com.gojek.app.adapter.mart;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gojek.app.model.CompanyProductCategory;
import com.gojek.app.model.SubCategory;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.util.Util;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExpandableProductItemAdapter
  extends BaseExpandableListAdapter
{
  private static final String TAG = ExpandableProductItemAdapter.class.getSimpleName();
  private final int REQUEST_ITEMS = 12;
  private List<CompanyProductCategory> companyProductCategoryList;
  private ImageLoader imageLoader;
  private String jsonMerchantCategoryList;
  private MenuClickListener listener;
  private BookingStatus mBookingStatus;
  private List<String> mCategory = new ArrayList();
  private List<String> mCategoryId;
  private Activity mContext;
  private Map<Integer, List<SubCategory>> mProduct;
  
  public ExpandableProductItemAdapter(Activity paramActivity, List<CompanyProductCategory> paramList)
  {
    this.mContext = paramActivity;
    this.companyProductCategoryList = paramList;
    try
    {
      this.imageLoader = ImageLoader.getInstance();
      paramActivity = new ImageLoaderConfiguration.Builder(paramActivity).defaultDisplayImageOptions(Util.getDisplayImageConfig()).build();
      this.imageLoader.init(paramActivity);
      if ((paramList != null) && (paramList.size() > 0))
      {
        if (this.mCategory == null) {
          this.mCategory = new ArrayList();
        }
        if (this.mProduct == null) {
          this.mProduct = new HashMap();
        }
        if (this.mCategoryId == null) {
          this.mCategoryId = new ArrayList();
        }
        i = 0;
        paramActivity = paramList.iterator();
        if (paramActivity.hasNext())
        {
          Object localObject = (CompanyProductCategory)paramActivity.next();
          this.mCategory.add(((CompanyProductCategory)localObject).name);
          this.mCategoryId.add("" + ((CompanyProductCategory)localObject).id);
          SubCategory localSubCategory = new SubCategory();
          localSubCategory.id = 0;
          localSubCategory.name = ("All " + ((CompanyProductCategory)localObject).name);
          localSubCategory.code = ((CompanyProductCategory)localObject).code;
          localSubCategory.parentId = ((CompanyProductCategory)localObject).id;
          paramList = new ArrayList();
          paramList.add(0, localSubCategory);
          localObject = ((CompanyProductCategory)localObject).subCategory.iterator();
          while (((Iterator)localObject).hasNext()) {
            paramList.add(1, (SubCategory)((Iterator)localObject).next());
          }
        }
      }
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        int i;
        paramActivity.printStackTrace();
        continue;
        this.mProduct.put(Integer.valueOf(i), paramList);
        i += 1;
      }
    }
  }
  
  public Object getChild(int paramInt1, int paramInt2)
  {
    return ((SubCategory)((List)this.mProduct.get(Integer.valueOf(paramInt1))).get(paramInt2)).name;
  }
  
  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    String str = (String)getChild(paramInt1, paramInt2);
    LayoutInflater localLayoutInflater = this.mContext.getLayoutInflater();
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = localLayoutInflater.inflate(2130968751, null);
    }
    paramView = (TextView)paramViewGroup.findViewById(2131624787);
    paramView.setText(str);
    if (paramView.getText().toString().startsWith("All"))
    {
      paramView.setOnClickListener(new ExpandableProductItemAdapter.1(this, paramInt1));
      return paramViewGroup;
    }
    paramView.setOnClickListener(new ExpandableProductItemAdapter.2(this, paramInt2, paramInt1));
    return paramViewGroup;
  }
  
  public int getChildrenCount(int paramInt)
  {
    return ((List)this.mProduct.get(Integer.valueOf(paramInt))).size();
  }
  
  public Object getGroup(int paramInt)
  {
    return this.mCategory.get(paramInt);
  }
  
  public int getGroupCount()
  {
    return this.mCategory.size();
  }
  
  public long getGroupId(int paramInt)
  {
    return paramInt;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject2 = (String)getGroup(paramInt);
    Object localObject1 = ((CompanyProductCategory)this.companyProductCategoryList.get(paramInt)).imgLocation;
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(2130968752, null);
    }
    paramView = (TextView)paramViewGroup.findViewById(2131624789);
    paramView.setTypeface(null, 1);
    paramView.setText((CharSequence)localObject2);
    paramView = (ImageView)paramViewGroup.findViewById(2131624790);
    localObject2 = (ImageView)paramViewGroup.findViewById(2131624788);
    if ((localObject1 != null) && (!((String)localObject1).isEmpty()))
    {
      this.imageLoader.displayImage((String)localObject1, (ImageView)localObject2);
      localObject1 = BitmapFactoryInstrumentation.decodeResource(this.mContext.getResources(), 2130837847);
      if (!paramBoolean) {
        break label190;
      }
    }
    label190:
    for (float f = 90.0F;; f = 0.0F)
    {
      paramView.setImageBitmap(rotateImage((Bitmap)localObject1, f));
      return paramViewGroup;
      ((ImageView)localObject2).setImageBitmap(BitmapFactoryInstrumentation.decodeResource(this.mContext.getResources(), 2130837860));
      break;
    }
  }
  
  public boolean hasStableIds()
  {
    return false;
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public Bitmap rotateImage(Bitmap paramBitmap, float paramFloat)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramFloat);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }
  
  public void setListener(MenuClickListener paramMenuClickListener)
  {
    this.listener = paramMenuClickListener;
  }
  
  public static abstract interface MenuClickListener
  {
    public abstract void onMenuClickAll(CompanyProductCategory paramCompanyProductCategory);
    
    public abstract void onMenuClickProduct(int paramInt, List<SubCategory> paramList);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/adapter/mart/ExpandableProductItemAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */