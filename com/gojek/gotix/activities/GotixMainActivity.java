package com.gojek.gotix.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.Toast;
import com.gojek.gotix.Event;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.adapters.ViewPagerAdapter;
import com.gojek.gotix.fragments.GotixEventFragment;
import com.gojek.gotix.fragments.GotixMyTicketsFragment;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.presenter.GotixOrderPresenter;
import com.gojek.gotix.services.model.ProduceOrder;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.squareup.otto.Subscribe;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.helper.SignUpHelper;
import lib.gojek.base.util.NotificationHelper;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class GotixMainActivity
  extends GotixBaseActivity
  implements GotixMainNavigationListener
{
  public static final String EVENT_ID = "type_id";
  public static final String EVENT_ID_KEY = "event_id";
  public static final String LAST_ACTIVE_TAB = "lastActiveTab";
  public static final int MYTICKET_TAB = 2;
  public static final int ONSALE_TAB = 1;
  private static final String ORDER_ID_KEY = "orderIdKey";
  public static final int PAGE_TAB = 3;
  private static final String REQUEST_CODE = "requestCode";
  public static final int REQUEST_CODE_ACCEPTED_BY_DRIVER_INTENT = 24;
  public static final int REQUEST_CODE_CALL_CENTER_INTENT = 26;
  public static final int REQUEST_CODE_EVENT_DETAIL_INTENT = 21;
  public static final int REQUEST_CODE_PAYMENT_FAILED = 104;
  public static final int REQUEST_CODE_RECEIPT_INTENT = 101;
  public static final int REQUEST_CODE_REVIEW_INTENT = 25;
  public static final int REQUEST_CODE_WAITING_DRIVER_INTENT = 22;
  public static final int REQUEST_CODE_WAITING_PAYMENT = 103;
  public static final int WHATSHAPPENING_TAB = 0;
  public static boolean isRunning;
  private CompositeSubscription compositeSubscription;
  private boolean flagLoginMyTicket;
  private GotixMyTicketsFragment gotixMyTicketsFragment;
  ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener()
  {
    public void onPageScrollStateChanged(int paramAnonymousInt) {}
    
    public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {}
    
    public void onPageSelected(int paramAnonymousInt)
    {
      if (paramAnonymousInt == 2) {
        GotixMainActivity.this.checkUserLogin();
      }
      while (!GotixMainActivity.this.compositeSubscription.hasSubscriptions()) {
        return;
      }
      GotixMainActivity.this.compositeSubscription.clear();
    }
  };
  private SignUpHelper signUpHelper;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private ViewPagerAdapter viewPagerAdapter;
  
  private void bindViewById()
  {
    this.tabLayout = ((TabLayout)findViewById(R.id.tabs));
    this.viewPager = ((ViewPager)findViewById(R.id.pager));
  }
  
  private void castFragmentFromActiveFragment()
  {
    if ((getCurrentFragment(R.id.detailholder) instanceof GotixMyTicketsFragment)) {
      this.gotixMyTicketsFragment = ((GotixMyTicketsFragment)getCurrentFragment(R.id.detailholder));
    }
  }
  
  private void checkUserLogin()
  {
    if (this.viewPager.getCurrentItem() == 2) {
      this.compositeSubscription.add(Observable.just(Boolean.valueOf(BasePreferences.isLoggedIn())).subscribe(GotixMainActivity..Lambda.1.lambdaFactory$(this)));
    }
  }
  
  private void dataOrderBuyNow()
  {
    if ((this.viewPager.getCurrentItem() == 2) && (this.flagLoginMyTicket) && (!BasePreferences.isLoggedIn()) && (this.gotixMyTicketsFragment != null))
    {
      this.gotixMyTicketsFragment.emptyOrderMyTicket();
      this.flagLoginMyTicket = false;
    }
  }
  
  private void doNavigatePageFromNotif()
  {
    if ((getIntent().hasExtra("requestCode")) && (getIntent().getIntExtra("requestCode", 0) != 0)) {
      navigatePageFromNotif();
    }
  }
  
  private void navigatePageFromNotif()
  {
    int i = getIntent().getIntExtra("requestCode", 0);
    int j = getIntent().getIntExtra("orderIdKey", 0);
    int k = getIntent().getIntExtra("event_id", 0);
    switch (i)
    {
    }
    for (;;)
    {
      getIntent().removeExtra("requestCode");
      NotificationHelper.cancelAllNotification();
      return;
      navigateToWaitingDriverPage(j);
      continue;
      navigateToFoundDriverPage(j);
      continue;
      if (!GotixData.hasReview(j))
      {
        navigateToAcceptedByDriver();
      }
      else
      {
        navigateToReceiptPage(4, j);
        continue;
        if (k != 0)
        {
          navigateToReceiptPage(3, j);
        }
        else
        {
          navigateToReceiptPage(1, j);
          continue;
          navigateToPaymentFailedPage(j);
          continue;
          navigateToCancelPage();
          continue;
          navigateToEventDetail(k);
        }
      }
    }
  }
  
  private void navigateToAcceptedByDriver()
  {
    Intent localIntent = new Intent(this, GotixAcceptedByDriverActivity.class);
    localIntent.putExtra("orderIdKey", getIntent().getIntExtra("orderIdKey", 0));
    startActivityForResult(localIntent, 25);
  }
  
  private void refreshDataOrderFromNetwork()
  {
    if (this.gotixMyTicketsFragment != null) {
      ((GotixOrderPresenter)this.gotixMyTicketsFragment.getPresenter()).attemptMyEventList(BasePreferences.getCustomerId());
    }
  }
  
  private void setActiveTab(int paramInt)
  {
    this.viewPager.setCurrentItem(paramInt);
    this.tabLayout.setScrollPosition(paramInt, 0.0F, true);
  }
  
  private void setupSlidingTab()
  {
    this.tabLayout.setupWithViewPager(this.viewPager);
    TypefaceHelper.typeface(this.tabLayout, FontFaceHelper.getBebasNeue());
  }
  
  private void setupViewPager()
  {
    this.gotixMyTicketsFragment = GotixMyTicketsFragment.newInstance();
    this.viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    this.viewPagerAdapter.addPage(GotixEventFragment.happeningEventInstance());
    this.viewPagerAdapter.addPage(GotixEventFragment.onSaleEventInstance());
    this.viewPagerAdapter.addPage(this.gotixMyTicketsFragment);
    this.viewPager.setAdapter(this.viewPagerAdapter);
    this.viewPager.setOffscreenPageLimit(3);
    this.viewPager.addOnPageChangeListener(this.onPageChangeListener);
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_main;
  }
  
  public void navigateToCancelPage()
  {
    startActivityForResult(new Intent(this, GotixCallCenterActivity.class), 26);
  }
  
  public void navigateToEventDetail(int paramInt)
  {
    Intent localIntent = new Intent(this, GotixEventDetailActivity.class);
    localIntent.putExtra("type_id", paramInt);
    localIntent.putExtra("lastActiveTab", this.tabLayout.getSelectedTabPosition());
    startActivityForResult(localIntent, 21);
  }
  
  public void navigateToEventDetail(Event paramEvent)
  {
    Intent localIntent = new Intent(this, GotixEventDetailActivity.class);
    if (paramEvent != null)
    {
      GotixData.saveEventData(paramEvent);
      localIntent.putExtra("type_id", paramEvent.getEvent_id());
      localIntent.putExtra("lastActiveTab", this.tabLayout.getSelectedTabPosition());
      startActivityForResult(localIntent, 21);
    }
  }
  
  public void navigateToFoundDriverPage(int paramInt)
  {
    Intent localIntent = new Intent(this, GotixAcceptedByDriverActivity.class);
    localIntent.putExtra("orderIdKey", paramInt);
    startActivityForResult(localIntent, 24);
  }
  
  public void navigateToPaymentFailedPage(int paramInt)
  {
    Intent localIntent = new Intent(this, GotixPaymentFailedActivity.class);
    localIntent.putExtra("orderIdKey", paramInt);
    startActivityForResult(localIntent, 25);
  }
  
  public void navigateToReceiptPage(int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent(this, GotixReceiptTicketActivity.class);
    localIntent.putExtra("done_flag", paramInt1);
    localIntent.putExtra("orderIdKey", paramInt2);
    startActivityForResult(localIntent, 101);
  }
  
  public void navigateToSearchPage()
  {
    startActivity(new Intent(this, GotixSearchEventActivity.class));
  }
  
  public void navigateToSignUp()
  {
    if (!this.flagLoginMyTicket)
    {
      this.signUpHelper.openSignUpPage();
      this.flagLoginMyTicket = true;
    }
  }
  
  public void navigateToWaitingDriverPage(int paramInt)
  {
    Intent localIntent = new Intent(this, GotixWaitingDriverActivity.class);
    localIntent.putExtra("orderIdKey", paramInt);
    startActivityForResult(localIntent, 22);
  }
  
  public void navigateToWaitingPayment(int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent(this, GotixWaitingPaymentActivity.class);
    localIntent.putExtra("eventIdKey", paramInt2);
    localIntent.putExtra("orderIdKey", paramInt1);
    startActivityForResult(localIntent, 103);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      if (this.signUpHelper.onActivityForResult(paramInt1, paramInt2, paramIntent)) {
        checkUserLogin();
      }
      switch (paramInt1)
      {
      default: 
        refreshDataOrderFromNetwork();
        setActiveTab(2);
      }
    }
    for (;;)
    {
      getIntent().putExtra("requestCode", 0);
      return;
      setActiveTab(paramIntent.getIntExtra("lastActiveTab", 0));
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    bindViewById();
    setupViewPager();
    setupSlidingTab();
    this.signUpHelper = new SignUpHelper(this);
    this.compositeSubscription = new CompositeSubscription();
  }
  
  public void onNetworkProblem() {}
  
  public void onNoInternetConnection() {}
  
  protected void onPause()
  {
    super.onPause();
    BusProvider.getInstance().unregister(this);
    isRunning = false;
  }
  
  @Subscribe
  public void onReceiveNotif(ProduceOrder paramProduceOrder)
  {
    if (BasePreferences.isLoggedIn())
    {
      getIntent().putExtra("requestCode", paramProduceOrder.getRequestCode());
      getIntent().putExtra("orderIdKey", paramProduceOrder.getOrderId());
      getIntent().putExtra("event_id", paramProduceOrder.getEventId());
      return;
    }
    Toast.makeText(this, getString(R.string.login_required_message), 1).show();
  }
  
  protected void onResume()
  {
    super.onResume();
    isRunning = true;
    BusProvider.getInstance().register(this);
    castFragmentFromActiveFragment();
    dataOrderBuyNow();
    doNavigatePageFromNotif();
  }
  
  public void setOnSaleAsActiveTab()
  {
    setActiveTab(1);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixMainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */