package com.gojek.gotix.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.fragments.GotixDeliveryFormFragment;
import lib.gojek.base.helper.PickContactHelper;
import lib.gojek.base.helper.PickLocationHelper;

public class GotixDeliveryActivity
  extends GotixBaseActivity
{
  private static final String DELIVERY_PAGE_TAG = GotixDeliveryActivity.class.getSimpleName();
  public static final String ORDER_ID_KEY = "orderIdKey";
  private String bookingCode;
  private int eventId;
  private GotixDeliveryFormFragment gotixDeliveryFormFragment;
  private int orderId;
  private PickContactHelper pickContactHelper;
  private PickLocationHelper pickLocationHelper;
  private View shadowToolbar;
  private String ticket;
  private String titleEvent;
  
  private void bindContactToFragment()
  {
    if (this.gotixDeliveryFormFragment == null) {
      this.gotixDeliveryFormFragment = ((GotixDeliveryFormFragment)getFragment(DELIVERY_PAGE_TAG));
    }
    if ((!TextUtils.isEmpty(this.pickContactHelper.getContactName())) && (!TextUtils.isEmpty(this.pickContactHelper.getContactNumber()))) {
      this.gotixDeliveryFormFragment.onContactPicked(this.pickContactHelper.getContactName(), this.pickContactHelper.getContactNumber());
    }
  }
  
  private void bindLocationDeliveryToFragment()
  {
    this.gotixDeliveryFormFragment = ((GotixDeliveryFormFragment)getFragment(DELIVERY_PAGE_TAG));
    if (this.gotixDeliveryFormFragment != null) {
      this.gotixDeliveryFormFragment.onLocationPickedFromPickLocation(this.eventId, this.pickLocationHelper.getName(), this.pickLocationHelper.getAddress(), this.pickLocationHelper.getLat(), this.pickLocationHelper.getLon());
    }
  }
  
  private void bindViewById()
  {
    this.shadowToolbar = findViewById(R.id.shadow_toolbar);
  }
  
  private void finishActivity()
  {
    setResult(-1);
    finish();
  }
  
  private void getIntentFromReceipt()
  {
    Intent localIntent = getIntent();
    this.bookingCode = localIntent.getStringExtra("booking_code");
    this.titleEvent = localIntent.getStringExtra("title_event");
    this.ticket = localIntent.getStringExtra("ticket");
    this.orderId = localIntent.getIntExtra("orderIdKey", 0);
    this.eventId = localIntent.getIntExtra("event_id", 0);
  }
  
  private boolean isFromWaitingDriverActivity(int paramInt)
  {
    return paramInt == 22;
  }
  
  private void openDeliveryPage()
  {
    setShadowVisibility(true);
    loadFragment(GotixDeliveryFormFragment.newInstance(this.orderId, this.bookingCode, this.titleEvent, this.ticket), R.id.detailholder, DELIVERY_PAGE_TAG);
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_delivery;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (isFromWaitingDriverActivity(paramInt1))
    {
      if (paramInt2 == -1) {
        finishActivity();
      }
      return;
    }
    if (this.pickLocationHelper.onActivityForResult(paramInt1, paramInt2, paramIntent))
    {
      bindLocationDeliveryToFragment();
      return;
    }
    if (this.pickContactHelper.onActivityForResult(paramInt1, paramInt2, paramIntent))
    {
      bindContactToFragment();
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    setResult(0);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    bindViewById();
    getIntentFromReceipt();
    openDeliveryPage();
    this.pickLocationHelper = new PickLocationHelper(this);
    this.pickContactHelper = new PickContactHelper(this);
  }
  
  public void openPickContactPage()
  {
    this.pickContactHelper.openContactPicker();
  }
  
  public void openPickLocationPage()
  {
    this.pickLocationHelper.openPickLocationPage();
  }
  
  public void openWaitingDriverPage()
  {
    setShadowVisibility(false);
    Intent localIntent = new Intent(this, GotixWaitingDriverActivity.class);
    localIntent.putExtra("orderIdKey", this.orderId);
    startActivityForResult(localIntent, 22);
  }
  
  public void setShadowVisibility(boolean paramBoolean)
  {
    View localView = this.shadowToolbar;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      localView.setVisibility(i);
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixDeliveryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */