package com.gojek.gobox.driverstatus.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.gojek.gobox.driverstatus.presenter.DriverStatusPresenter;
import com.gojek.gobox.networking.ConnectionManager;

class DriverStatusActivity$1
  extends BroadcastReceiver
{
  DriverStatusActivity$1(DriverStatusActivity paramDriverStatusActivity) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getStringExtra("push_type");
    if ((paramIntent.getStringExtra("order_id").equals(DriverStatusActivity.access$000(this.this$0) + "")) && (paramContext.equals("2")))
    {
      if (ConnectionManager.isConnected(this.this$0)) {
        DriverStatusActivity.access$100(this.this$0).onRefreshStatus(DriverStatusActivity.access$000(this.this$0));
      }
    }
    else {
      return;
    }
    this.this$0.noInternetConnectionHandler();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/view/DriverStatusActivity$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */