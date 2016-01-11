package com.gojek.gobox.orderForm.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.gojek.gobox.R.id;

class OrderFormActivity$1
  implements View.OnClickListener
{
  OrderFormActivity$1(OrderFormActivity paramOrderFormActivity, View paramView) {}
  
  public void onClick(View paramView)
  {
    paramView = (LinearLayout)this.val$view.findViewById(R.id.linear_for_contact_holder_destination);
    if (paramView.getVisibility() == 0)
    {
      paramView.setVisibility(8);
      return;
    }
    paramView.setVisibility(0);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/view/OrderFormActivity$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */