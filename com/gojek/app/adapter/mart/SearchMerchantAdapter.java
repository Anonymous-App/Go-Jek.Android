package com.gojek.app.adapter.mart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public abstract class SearchMerchantAdapter<T>
  extends BaseAdapter
{
  private List<T> mData;
  private LayoutInflater mLayoutInflater;
  
  public SearchMerchantAdapter(Context paramContext, List<T> paramList)
  {
    this.mData = paramList;
    this.mLayoutInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public int getCount()
  {
    return this.mData.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.mData.get(paramInt);
  }
  
  public abstract long getItemId(int paramInt);
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = this.mData.get(paramInt);
    if (getCount() - 1 == paramInt) {}
    for (paramView = this.mLayoutInflater.inflate(2130968748, null);; paramView = this.mLayoutInflater.inflate(2130968766, null))
    {
      setData(paramViewGroup, (TextView)paramView.findViewById(2131624779), (TextView)paramView.findViewById(2131624780));
      return paramView;
    }
  }
  
  protected abstract void setData(T paramT, TextView paramTextView1, TextView paramTextView2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/adapter/mart/SearchMerchantAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */