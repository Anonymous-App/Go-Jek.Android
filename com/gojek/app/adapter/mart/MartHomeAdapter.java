package com.gojek.app.adapter.mart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.gojek.app.custom.XTextView;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import java.util.List;

public abstract class MartHomeAdapter
  extends BaseAdapter
{
  private BookingStatus bookingStatus;
  private Context contect;
  private List<MartMerchant> data;
  private LayoutInflater mLayoutInflater;
  
  public MartHomeAdapter(Context paramContext, List<MartMerchant> paramList, BookingStatus paramBookingStatus)
  {
    this.contect = paramContext;
    this.data = paramList;
    this.bookingStatus = paramBookingStatus;
    this.mLayoutInflater = ((LayoutInflater)this.contect.getSystemService("layout_inflater"));
  }
  
  public int getCount()
  {
    return this.data.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.data.get(paramInt);
  }
  
  public abstract long getItemId(int paramInt);
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = (MartMerchant)this.data.get(paramInt);
    paramViewGroup = this.mLayoutInflater.inflate(2130968766, null);
    XTextView localXTextView1 = (XTextView)paramViewGroup.findViewById(2131624779);
    XTextView localXTextView2 = (XTextView)paramViewGroup.findViewById(2131624780);
    ImageView localImageView1 = (ImageView)paramViewGroup.findViewById(2131624778);
    ImageView localImageView2 = (ImageView)paramViewGroup.findViewById(2131624042);
    if (((Addresses)this.bookingStatus.addresses.get(0)).martMerchant.martId == paramView.martId)
    {
      localImageView1.setVisibility(8);
      localImageView2.setVisibility(0);
    }
    for (;;)
    {
      setData(paramView, localXTextView1, localXTextView2);
      return paramViewGroup;
      localImageView2.setVisibility(8);
      localImageView1.setVisibility(0);
    }
  }
  
  protected abstract void setData(MartMerchant paramMartMerchant, XTextView paramXTextView1, XTextView paramXTextView2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/adapter/mart/MartHomeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */