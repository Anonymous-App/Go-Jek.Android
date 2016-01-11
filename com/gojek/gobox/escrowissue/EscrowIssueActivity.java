package com.gojek.gobox.escrowissue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;

public class EscrowIssueActivity
  extends BaseActivity
{
  private LinearLayout mNewBookingButton;
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent("com.gojek.app.HOME");
    localIntent.addFlags(268468224);
    localIntent.putExtra("FEED_BACK", 2);
    startActivity(localIntent);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_escrow_issue);
    initToolbarView();
    setTitle(getString(R.string.my_booking_title));
    this.mNewBookingButton = ((LinearLayout)findViewById(R.id.new_booking_button));
    this.mNewBookingButton.setOnClickListener(EscrowIssueActivity..Lambda.1.lambdaFactory$(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/escrowissue/EscrowIssueActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */