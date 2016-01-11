package com.gojek.app.gobusway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.gojek.app.gobusway.R.layout;

public class TimeAdapter
  extends ArrayAdapter<String>
{
  private Context mContext;
  private String[] mTimeList;
  
  public TimeAdapter(Context paramContext, int paramInt, String[] paramArrayOfString)
  {
    super(paramContext, paramInt);
    this.mTimeList = paramArrayOfString;
    this.mContext = paramContext;
  }
  
  public int getCount()
  {
    return this.mTimeList.length;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = LayoutInflater.from(this.mContext).inflate(R.layout.time_item, null);
    if (this.mTimeList[paramInt].equals("")) {
      paramView.setVisibility(4);
    }
    ((TextView)paramView).setText(this.mTimeList[paramInt]);
    return paramView;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/adapter/TimeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */