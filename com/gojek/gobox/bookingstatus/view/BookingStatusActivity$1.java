package com.gojek.gobox.bookingstatus.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class BookingStatusActivity$1
  extends BroadcastReceiver
{
  BookingStatusActivity$1(BookingStatusActivity paramBookingStatusActivity) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getStringExtra("push_type");
    if ((paramIntent.getStringExtra("order_id").equals(BookingStatusActivity.access$000(this.this$0))) && (paramContext.equals("1")))
    {
      if (paramIntent.getStringExtra("is_success").equalsIgnoreCase("true")) {
        this.this$0.showNextBookingStatusIfNeeded(0);
      }
    }
    else {
      return;
    }
    this.this$0.showNextBookingStatusIfNeeded(8);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/view/BookingStatusActivity$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */