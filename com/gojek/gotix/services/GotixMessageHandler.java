package com.gojek.gotix.services;

import android.os.Bundle;
import android.util.Log;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.helper.GojekDataHelper;
import lib.gojek.base.services.GoServicesBaseMessageHandler;

public class GotixMessageHandler
  extends GoServicesBaseMessageHandler
{
  public static final int[] GOTIX_PUSH_TYPES = { 81, 82, 83 };
  public static final int PUSH_TYPE_BOOKING_STATUS = 83;
  public static final int PUSH_TYPE_ORDER_STATUS = 81;
  public static final int PUSH_TYPE_PURCHASE_STATUS = 82;
  public static final String TAG = "GotixMessageHandler";
  private Bundle bundle;
  private int pushType;
  
  public GotixMessageHandler()
  {
    super("GotixMessageHandler", GOTIX_PUSH_TYPES);
  }
  
  private void initPreferences()
  {
    if (!GojekDataHelper.isPreferencesExist()) {
      GojekDataHelper.init(getApplicationContext());
    }
  }
  
  private boolean isCustomerIdExist()
  {
    return BasePreferences.getCustomerId() != 0;
  }
  
  private void onPushNotifReceived()
  {
    if (isCustomerIdExist())
    {
      this.pushType = Integer.parseInt(this.bundle.getString("push_type"));
      Log.d("GotixMessageHandler", String.valueOf(this.pushType));
      switch (this.pushType)
      {
      default: 
        return;
      case 81: 
        new GotixEventHandler(this, this.bundle);
        return;
      case 82: 
        new GotixPurchaseHandler(this, this.bundle);
        return;
      }
      new GotixBookingHandler(this, this.bundle);
      return;
    }
    Log.d("GotixMessageHandler", "User not login");
  }
  
  public void message(Bundle paramBundle)
  {
    this.bundle = paramBundle;
    initPreferences();
    onPushNotifReceived();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/services/GotixMessageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */