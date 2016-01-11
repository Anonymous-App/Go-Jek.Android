package com.gojek.app.custom;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.Util;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.List;

public abstract class FoodMenuItemRender
{
  private static final String TAG = FoodMenuItemRender.class.getSimpleName();
  private final int DEFAULT_PAGE_SIZE = 3;
  private Activity mActivity;
  private Context mContext;
  private List<RouteItem> mRouteItems;
  private int page = 1;
  private int pageSize = 3;
  private AsyncTask<List<RouteItem>, Void, Void> renderTask;
  private int totalPage = 0;
  private ViewGroup view;
  
  public FoodMenuItemRender(Activity paramActivity, List<RouteItem> paramList)
  {
    this.mContext = paramActivity.getApplicationContext();
    this.mActivity = paramActivity;
    this.mRouteItems = paramList;
  }
  
  private void renderFoodItem(int paramInt)
  {
    this.renderTask = new FoodMenuItemRender.1(this, paramInt);
    AsyncTask localAsyncTask = this.renderTask;
    List[] arrayOfList = new List[1];
    arrayOfList[0] = this.mRouteItems;
    if (!(localAsyncTask instanceof AsyncTask))
    {
      localAsyncTask.execute(arrayOfList);
      return;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)localAsyncTask, arrayOfList);
  }
  
  private void renderListFoodItemPageable(List<RouteItem> paramList, int paramInt, ViewGroup paramViewGroup)
  {
    label42:
    View localView;
    Object localObject;
    LinearLayout localLinearLayout1;
    LinearLayout localLinearLayout2;
    EditText localEditText;
    TextView localTextView1;
    TextView localTextView4;
    TextView localTextView2;
    if (paramInt == 1)
    {
      paramInt = 0;
      int j = this.pageSize + paramInt - 1;
      int i = j;
      if (j >= paramList.size()) {
        i = paramList.size() - 1;
      }
      if (paramInt > i) {
        return;
      }
      localView = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(2130968696, null);
      localObject = (TextView)localView.findViewById(2131624500);
      TextView localTextView3 = (TextView)localView.findViewById(2131624502);
      localLinearLayout1 = (LinearLayout)localView.findViewById(2131624506);
      localLinearLayout2 = (LinearLayout)localView.findViewById(2131624507);
      localEditText = (EditText)localView.findViewById(2131624508);
      localTextView1 = (TextView)localView.findViewById(2131624505);
      localTextView4 = (TextView)localView.findViewById(2131624504);
      localTextView2 = (TextView)localView.findViewById(2131624503);
      String str1 = ((RouteItem)paramList.get(paramInt)).itemName;
      j = ((RouteItem)paramList.get(paramInt)).price;
      String str2 = ((RouteItem)paramList.get(paramInt)).notes;
      ((TextView)localObject).setText(str1);
      localTextView3.setText(Util.getRupiahFormat(String.valueOf(String.valueOf(j))));
      if (!Util.isTextNotNullEmpty(str2)) {
        break label371;
      }
      localLinearLayout2.setVisibility(0);
      localEditText.setText(str2);
    }
    for (;;)
    {
      localTextView4.setText(String.valueOf(((RouteItem)paramList.get(paramInt)).quantity));
      localLinearLayout1.setOnClickListener(new FoodMenuItemRender.2(this, localLinearLayout2, localTextView4, localEditText, paramList, paramInt));
      localEditText.addTextChangedListener(new FoodMenuItemRender.3(this, paramList, paramInt, localEditText));
      localObject = new FoodMenuItemRender.4(this, localTextView4, paramInt, localLinearLayout2, localEditText);
      localTextView1.setOnClickListener((View.OnClickListener)localObject);
      localTextView2.setOnClickListener((View.OnClickListener)localObject);
      paramViewGroup.addView(localView);
      paramInt += 1;
      break label42;
      paramInt = this.pageSize * paramInt - this.pageSize;
      break;
      label371:
      localLinearLayout2.setVisibility(8);
      localEditText.setText("");
    }
  }
  
  public void cancelRender()
  {
    if (this.renderTask != null) {
      this.renderTask.cancel(true);
    }
  }
  
  public abstract void onChangeFoodItemNote(RouteItem paramRouteItem);
  
  public abstract void onMinusFoodItemValue(RouteItem paramRouteItem);
  
  public abstract void onPlusFoodItemValue(RouteItem paramRouteItem);
  
  public void renderView(ViewGroup paramViewGroup)
  {
    this.view = paramViewGroup;
    if (this.mRouteItems.size() == 0) {
      return;
    }
    this.totalPage = ((int)Math.ceil(this.mRouteItems.size() / this.pageSize));
    renderFoodItem(this.page);
  }
  
  public void setPageSize(int paramInt)
  {
    this.pageSize = paramInt;
  }
  
  public void setView(ViewGroup paramViewGroup)
  {
    this.view = paramViewGroup;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/custom/FoodMenuItemRender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */