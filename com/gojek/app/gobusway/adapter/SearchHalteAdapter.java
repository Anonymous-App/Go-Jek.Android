package com.gojek.app.gobusway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import com.gojek.app.gobusway.R.id;
import com.gojek.app.gobusway.R.layout;
import com.gojek.app.gobusway.model.Halte;
import com.gojek.app.gobusway.util.StringUtil;
import java.util.ArrayList;

public class SearchHalteAdapter
  extends ArrayAdapter<Halte>
{
  private Context mContext;
  private ArrayList<Halte> mFilteredList;
  private ArrayList<Halte> mList;
  
  public SearchHalteAdapter(Context paramContext, int paramInt, ArrayList<Halte> paramArrayList)
  {
    super(paramContext, paramInt);
    this.mContext = paramContext;
    this.mList = paramArrayList;
    this.mFilteredList = paramArrayList;
  }
  
  public void addAllData(ArrayList<Halte> paramArrayList)
  {
    this.mList.clear();
    this.mList.addAll(paramArrayList);
    this.mFilteredList.clear();
    this.mFilteredList.addAll(paramArrayList);
    notifyDataSetChanged();
  }
  
  public int getCount()
  {
    return this.mFilteredList.size();
  }
  
  public Filter getFilter()
  {
    return new SearchHalteAdapter.1(this);
  }
  
  public Halte getItem(int paramInt)
  {
    return (Halte)this.mFilteredList.get(paramInt);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = LayoutInflater.from(this.mContext).inflate(R.layout.search_halte_item, null);
      paramViewGroup = new SearchViewHolder(paramView);
      paramView.setTag(paramViewGroup);
    }
    for (;;)
    {
      paramViewGroup.mHalteName.setText(StringUtil.upperCaseFirstLatter(((Halte)this.mFilteredList.get(paramInt)).getName()));
      return paramView;
      paramViewGroup = (SearchViewHolder)paramView.getTag();
    }
  }
  
  private static class SearchViewHolder
  {
    private TextView mHalteName;
    
    public SearchViewHolder(View paramView)
    {
      this.mHalteName = ((TextView)paramView.findViewById(R.id.halte_name));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/adapter/SearchHalteAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */