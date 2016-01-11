package com.gojek.gotix.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import com.gojek.gotix.Event;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.fragments.GotixCheckoutFragment;
import com.gojek.gotix.fragments.GotixEventDetailFragment;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.model.LocationMap;
import com.gojek.gotix.presenter.GotixCheckoutPresenter;
import com.gojek.gotix.tools.GotixUtils;
import com.nostratech.gojek.driver.fragments.FragmentPaymentInfo;
import com.nostratech.gojek.driver.interfaces.ServiceCallback;
import java.util.ArrayList;
import java.util.List;
import lib.gojek.base.helper.SignUpHelper;
import lib.gojek.base.util.BaseDialogFragment.AlertDialogListener;
import lib.gojek.base.util.NotificationHelper;

public class GotixEventDetailActivity
  extends GotixBaseActivity
  implements BaseDialogFragment.AlertDialogListener, ServiceCallback
{
  private static final String CHECKOUT_PAGE_TAG = GotixCheckoutFragment.class.getSimpleName();
  private static final String EVENT_DETAIL_PAGE_TAG = GotixEventDetailFragment.class.getSimpleName();
  public static final String EVENT_ID = "event_id";
  private static final int FRAGMENT_PAYMENT_TAG = 1;
  public static final String LOCATIONS = "locations";
  private static final int NOTIFICATION_REQUEST_CODE = 23;
  private static final int NOTIFICATION_TIMEOUT_ID = 1;
  private static final String REQUEST_CODE = "requestCode";
  public static boolean isRunning;
  private Context context;
  private int eventId;
  private GotixCheckoutFragment gotixCheckoutFragment;
  private boolean isReleased;
  private NotificationHelper notificationHelper;
  private SignUpHelper signUpHelper;
  
  private String getNameEvent()
  {
    if (GotixData.getEventData(this.eventId) != null) {
      return GotixData.getEventData(this.eventId).getName();
    }
    return getString(R.string.gotix);
  }
  
  private void openMainActivity()
  {
    setResult(-1, getIntent());
    finish();
  }
  
  private void openMainPage()
  {
    loadFragment(GotixEventDetailFragment.newInstance(this.eventId), R.id.detailholder, EVENT_DETAIL_PAGE_TAG);
  }
  
  private void setTitleToolbarOnDetailEvent(String paramString)
  {
    setTitleActionBar(paramString);
  }
  
  private void showDialogRelease()
  {
    runOnUiThread(GotixEventDetailActivity..Lambda.2.lambdaFactory$(this));
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_event_detail;
  }
  
  public void negativeButtonClicked() {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 101) || (paramInt1 == 103))
    {
      getIntent().putExtra("lastActiveTab", 2);
      setResult(-1, getIntent());
      finish();
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    if ((getCurrentFragment(R.id.detailholder) instanceof GotixCheckoutFragment))
    {
      if (!this.isReleased)
      {
        showDialogRelease();
        return;
      }
      this.isReleased = false;
      super.onBackPressed();
      return;
    }
    if ((getCurrentFragment(R.id.detailholder) instanceof GotixEventDetailFragment))
    {
      openMainActivity();
      return;
    }
    GotixUtils.openMainActivityWithFlag(this);
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = this;
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      this.eventId = paramBundle.getInt("type_id");
      setTitleToolbarOnDetailEvent(getNameEvent());
      openMainPage();
    }
    this.signUpHelper = new SignUpHelper(this);
    this.notificationHelper = new NotificationHelper(this);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    return true;
  }
  
  public void onNetworkProblem()
  {
    super.onNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    super.onNoInternetConnection();
  }
  
  protected void onPause()
  {
    super.onPause();
    isRunning = false;
  }
  
  protected void onResume()
  {
    super.onResume();
    isRunning = true;
  }
  
  public void openCheckoutPage()
  {
    this.gotixCheckoutFragment = GotixCheckoutFragment.newInstance(this.eventId);
    slideLeftFragment(this.gotixCheckoutFragment, R.id.detailholder, CHECKOUT_PAGE_TAG);
  }
  
  public void openMap(List<LocationMap> paramList)
  {
    Intent localIntent = new Intent(this, GotixMapActivity.class);
    localIntent.putStringArrayListExtra("locations", (ArrayList)paramList);
    startActivity(localIntent);
  }
  
  public void openReceiptPage(int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent(this.context, GotixReceiptTicketActivity.class);
    localIntent.putExtra("done_flag", paramInt2);
    localIntent.putExtra("orderIdKey", paramInt1);
    localIntent.putExtra("event_id", this.eventId);
    startActivityForResult(localIntent, 101);
  }
  
  public void openSignUpPage()
  {
    this.signUpHelper.openSignUpPage();
  }
  
  public void openWaitingPaymentPage(int paramInt, boolean paramBoolean)
  {
    Intent localIntent = new Intent(this.context, GotixWaitingPaymentActivity.class);
    localIntent.putExtra("eventIdKey", this.eventId);
    localIntent.putExtra("orderIdKey", paramInt);
    localIntent.putExtra("paymentFailedKey", paramBoolean);
    localIntent.setFlags(67108864);
    startActivityForResult(localIntent, 103);
  }
  
  public void positiveButtonClicked()
  {
    this.isReleased = true;
    if (this.gotixCheckoutFragment != null) {
      ((GotixCheckoutPresenter)this.gotixCheckoutFragment.getPresenter()).releasePaidOrderWhenCanceled();
    }
    onBackPressed();
  }
  
  public void postServiceExecute(Object paramObject, int paramInt1, int paramInt2)
  {
    Fragment localFragment = getSupportFragmentManager().findFragmentById(paramInt2);
    switch (paramInt1)
    {
    }
    do
    {
      return;
    } while (!(localFragment instanceof FragmentPaymentInfo));
    ((FragmentPaymentInfo)localFragment).onReceiveHttpResponse(paramObject);
  }
  
  public void preServiceExecute() {}
  
  public void showDialogTimesUp()
  {
    runOnUiThread(GotixEventDetailActivity..Lambda.1.lambdaFactory$(this));
  }
  
  public void showNotifRelease()
  {
    Intent localIntent = new Intent(this, GotixEventDetailActivity.class);
    localIntent.addFlags(603979776);
    localIntent.putExtra("type_id", this.eventId);
    this.notificationHelper.showNotif(1, 23, localIntent, getString(R.string.app_name), getString(R.string.dialog_timeup_desc), getString(R.string.dialog_timeup_desc));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixEventDetailActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */