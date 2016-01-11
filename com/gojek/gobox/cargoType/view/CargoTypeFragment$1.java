package com.gojek.gobox.cargoType.view;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.gojek.gobox.model.CargoType;
import com.gojek.gobox.orderForm.view.OrderFormActivity;

class CargoTypeFragment$1
  implements View.OnClickListener
{
  CargoTypeFragment$1(CargoTypeFragment paramCargoTypeFragment) {}
  
  public void onClick(View paramView)
  {
    paramView = new Intent(this.this$0.getActivity(), OrderFormActivity.class);
    paramView.putExtra("cargo_type_object", CargoTypeFragment.access$000(this.this$0).getId());
    this.this$0.startActivity(paramView);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/view/CargoTypeFragment$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */