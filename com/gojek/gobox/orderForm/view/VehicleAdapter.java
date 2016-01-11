package com.gojek.gobox.orderForm.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ToggleButton;
import com.gojek.gobox.R.color;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.model.CargoType;
import java.util.ArrayList;

public class VehicleAdapter
  extends ArrayAdapter<CargoType>
{
  private static final int CENTER_TYPE = 1;
  private static final String FONT = "fonts/BebasNeue Bold.ttf";
  private static final int LEFT_TYPE = 0;
  private static final int RIGHT_TYPE = 2;
  private Context mContext;
  private ArrayList<CargoType> mList;
  private long mSelectedVehicleId;
  private OnSelectedVehicleChangedListener onSelectedVehicleChangedListener;
  
  public VehicleAdapter(Context paramContext, int paramInt, ArrayList<CargoType> paramArrayList, long paramLong)
  {
    super(paramContext, paramInt);
    this.mContext = paramContext;
    this.mSelectedVehicleId = paramLong;
    this.mList = paramArrayList;
  }
  
  public int getCount()
  {
    return this.mList.size();
  }
  
  public int getItemViewType(int paramInt)
  {
    if (paramInt == 0) {
      return 0;
    }
    if (paramInt == this.mList.size() - 1) {
      return 2;
    }
    return 1;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    if (paramView == null)
    {
      localView = LayoutInflater.from(this.mContext).inflate(R.layout.vehicle_option_item, null);
      paramViewGroup = Typeface.createFromAsset(getContext().getAssets(), "fonts/BebasNeue Bold.ttf");
      paramView = new ViewHolder(localView);
      localView.setTag(paramView);
      paramView.mToggleButton.setTypeface(paramViewGroup);
      paramViewGroup = paramView;
      if (getItemViewType(paramInt) != 0) {
        break label240;
      }
      paramViewGroup.mToggleButton.setBackgroundResource(R.drawable.left_vehicle_button_selector);
      label77:
      if (this.mSelectedVehicleId == ((CargoType)this.mList.get(paramInt)).getId()) {
        break label275;
      }
      paramViewGroup.mToggleButton.setChecked(false);
      paramViewGroup.mToggleButton.setEnabled(true);
      paramViewGroup.mToggleButton.setTextColor(this.mContext.getResources().getColor(R.color.gobox_green));
    }
    for (;;)
    {
      paramViewGroup.mToggleButton.setText(((CargoType)this.mList.get(paramInt)).getName().toUpperCase());
      paramViewGroup.mToggleButton.setTextOn(((CargoType)this.mList.get(paramInt)).getName().toUpperCase());
      paramViewGroup.mToggleButton.setTextOff(((CargoType)this.mList.get(paramInt)).getName().toUpperCase());
      paramViewGroup.mToggleButton.setOnCheckedChangeListener(new VehicleAdapter.1(this, paramInt));
      return localView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      localView = paramView;
      break;
      label240:
      if (getItemViewType(paramInt) == 1)
      {
        paramViewGroup.mToggleButton.setBackgroundResource(R.drawable.center_vehicle_button_selector);
        break label77;
      }
      paramViewGroup.mToggleButton.setBackgroundResource(R.drawable.right_vehicle_button_selector);
      break label77;
      label275:
      paramViewGroup.mToggleButton.setChecked(true);
      paramViewGroup.mToggleButton.setEnabled(false);
      paramViewGroup.mToggleButton.setTextColor(this.mContext.getResources().getColor(17170443));
    }
  }
  
  public void setOnSelectedVehicleChangedListener(OnSelectedVehicleChangedListener paramOnSelectedVehicleChangedListener)
  {
    this.onSelectedVehicleChangedListener = paramOnSelectedVehicleChangedListener;
  }
  
  public static abstract interface OnSelectedVehicleChangedListener
  {
    public abstract void onSelectedCargoChanged(CargoType paramCargoType);
  }
  
  static class ViewHolder
  {
    private ToggleButton mToggleButton;
    
    public ViewHolder(View paramView)
    {
      this.mToggleButton = ((ToggleButton)paramView);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/view/VehicleAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */