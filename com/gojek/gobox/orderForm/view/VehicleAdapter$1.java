package com.gojek.gobox.orderForm.view;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.gojek.gobox.model.CargoType;
import java.util.ArrayList;

class VehicleAdapter$1
  implements CompoundButton.OnCheckedChangeListener
{
  VehicleAdapter$1(VehicleAdapter paramVehicleAdapter, int paramInt) {}
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      VehicleAdapter.access$102(this.this$0, ((CargoType)VehicleAdapter.access$200(this.this$0).get(this.val$position)).getId());
      if (VehicleAdapter.access$300(this.this$0) != null) {
        VehicleAdapter.access$300(this.this$0).onSelectedCargoChanged((CargoType)VehicleAdapter.access$200(this.this$0).get(this.val$position));
      }
      this.this$0.notifyDataSetChanged();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/view/VehicleAdapter$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */