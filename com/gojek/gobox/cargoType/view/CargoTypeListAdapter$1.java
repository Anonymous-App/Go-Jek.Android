package com.gojek.gobox.cargoType.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.gojek.gobox.model.CargoType;
import java.util.List;

class CargoTypeListAdapter$1
  implements View.OnClickListener
{
  CargoTypeListAdapter$1(CargoTypeListAdapter paramCargoTypeListAdapter, int paramInt) {}
  
  public void onClick(View paramView)
  {
    CargoTypeListAdapter.access$600(this.this$0).OnCargoClick((CargoType)CargoTypeListAdapter.access$500(this.this$0).get(this.val$position));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/view/CargoTypeListAdapter$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */