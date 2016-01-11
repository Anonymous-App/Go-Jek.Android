package com.gojek.gotix.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gojek.gotix.OrderDao.Properties;
import com.gojek.gotix.R.color;
import com.gojek.gotix.R.drawable;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.presenter.GotixAcceptedByDriverPresenter;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import com.gojek.gotix.tools.GotixUtils;
import com.gojek.gotix.tools.ObservableIntervalHelper;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.norbsoft.typefacehelper.TypefaceHelper;
import de.greenrobot.dao.Property;
import lib.gojek.base.fragments.MapsFragment;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.util.BaseDialogFragment.AlertDialogListener;
import lib.gojek.base.util.MapsUtils;
import nucleus.factory.RequiresPresenter;
import rx.subscriptions.CompositeSubscription;

@RequiresPresenter(GotixAcceptedByDriverPresenter.class)
public class GotixAcceptedByDriverActivity
  extends GotixBaseActivity<GotixAcceptedByDriverPresenter>
{
  private static final String BOOKING_DATE_FORMAT = "hh:mm - dd MMM yyyy";
  private static final char END_DELIMETER = '\n';
  private static final String ORDER_ID_KEY = "orderIdKey";
  private static final String PHONE_PREFIX = "tel:";
  private static final String REQUEST_CODE = "requestCode";
  private static final String SMS_PREFIX = "smsto:";
  private static final char START_DELIMETER = ':';
  private static final String TAG = GotixAcceptedByDriverActivity.class.getSimpleName();
  private static final int delayTapTime = 250;
  public static boolean isRunning;
  private TextView arrivedTimeText;
  private TextView bookingDateTime;
  private TextView bookingDetailsTitle;
  private TextView bookingLocationDestination;
  private TextView bookingLocationDestinationAddress;
  private TextView bookingLocationOrigin;
  private TextView bookingLocationOriginAddress;
  private LinearLayout callNow;
  private LinearLayout cancelBooking;
  private CircleProgressBar circleProgressBar;
  private CompositeSubscription compositeSubscription;
  private TextView destinationText;
  private ImageView driverAvatar;
  private TextView driverName;
  private TextView itemTicketInformation;
  private TextView itemTicketTitle;
  private int mDoubleTapCounter;
  private MapsFragment mapsFragment;
  private ObservableIntervalHelper observableIntervalHelperRefresh;
  private int orderId;
  private TextView otwText;
  private LinearLayout sendMessage;
  
  private void bindViewById()
  {
    this.otwText = ((TextView)findViewById(R.id.otw_text));
    this.destinationText = ((TextView)findViewById(R.id.destination_text));
    this.arrivedTimeText = ((TextView)findViewById(R.id.arrived_time_text));
    this.driverName = ((TextView)findViewById(R.id.driver_name));
    this.driverAvatar = ((ImageView)findViewById(R.id.driver_profile_pic));
    this.bookingDateTime = ((TextView)findViewById(R.id.booking_details_datetime));
    this.bookingDetailsTitle = ((TextView)findViewById(R.id.booking_details_title));
    this.bookingLocationOrigin = ((TextView)findViewById(R.id.booking_details_location_origin));
    this.bookingLocationOriginAddress = ((TextView)findViewById(R.id.booking_details_location_origin_address));
    this.bookingLocationDestination = ((TextView)findViewById(R.id.booking_details_location_destination));
    this.bookingLocationDestinationAddress = ((TextView)findViewById(R.id.booking_details_location_destination_address));
    this.callNow = ((LinearLayout)findViewById(R.id.call_driver));
    this.sendMessage = ((LinearLayout)findViewById(R.id.send_message_driver));
    this.cancelBooking = ((LinearLayout)findViewById(R.id.cancel_booking_button));
    this.circleProgressBar = ((CircleProgressBar)findViewById(R.id.accepted_driver_progbar));
    this.cancelBooking.setOnClickListener(GotixAcceptedByDriverActivity..Lambda.2.lambdaFactory$(this));
    this.itemTicketInformation = ((TextView)findViewById(R.id.item_ticket_information));
    this.itemTicketTitle = ((TextView)findViewById(R.id.item_ticket_title));
  }
  
  private void cancelBookingConfirmation(String paramString)
  {
    showDialog(getString(R.string.confirmation), paramString, new BaseDialogFragment.AlertDialogListener()
    {
      public void negativeButtonClicked() {}
      
      public void positiveButtonClicked()
      {
        ((GotixAcceptedByDriverPresenter)GotixAcceptedByDriverActivity.this.getPresenter()).doCancelbooking(GotixAcceptedByDriverActivity.this.orderId);
      }
    }, 2);
  }
  
  private void configCircleProgresbar()
  {
    this.circleProgressBar.setColorSchemeResources(new int[] { R.color.bg_base_green });
  }
  
  private void createIntervalRefresh()
  {
    this.observableIntervalHelperRefresh = new ObservableIntervalHelper()
    {
      public void doOnTimerStart()
      {
        GotixAcceptedByDriverActivity.this.onRefreshFindingDriver();
      }
    };
    this.observableIntervalHelperRefresh.startTimer();
  }
  
  private void doubleTapListener()
  {
    this.mDoubleTapCounter += 1;
    Handler localHandler = new Handler();
    Runnable localRunnable = GotixAcceptedByDriverActivity..Lambda.3.lambdaFactory$(this);
    if (this.mDoubleTapCounter == 1) {
      localHandler.postDelayed(localRunnable, 250L);
    }
    while (this.mDoubleTapCounter != 2) {
      return;
    }
    this.mDoubleTapCounter = 0;
    openMap(this.orderId);
  }
  
  private void initCompositeSubscription()
  {
    this.compositeSubscription = new CompositeSubscription();
  }
  
  private void openMainActivity()
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("lastActiveTab", 2);
    setResult(-1, localIntent);
    finish();
  }
  
  private void setFontTextView()
  {
    TypefaceHelper.typeface(this, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.bookingDetailsTitle, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.otwText, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.destinationText, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.arrivedTimeText, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.itemTicketTitle, FontFaceHelper.getBebasNeue());
    setFontTitleBar(FontFaceHelper.getBebasNeue());
  }
  
  public void callDriver(String paramString)
  {
    this.callNow.setOnClickListener(GotixAcceptedByDriverActivity..Lambda.4.lambdaFactory$(this, paramString));
  }
  
  public void drawRoutOnMap(int paramInt, String paramString1, String paramString2, String paramString3, double paramDouble1, double paramDouble2)
  {
    MapsUtils.drawRoute(this.mapsFragment.getGoogleMap(), paramInt, paramString1, paramString2, paramString3, paramDouble1, paramDouble2, R.drawable.ic_driver, R.drawable.ic_origin_pin, R.drawable.gotix_icon_location_origin, R.drawable.ic_destination_pin);
  }
  
  public void failedCancelBookingInformation()
  {
    runOnUiThread(GotixAcceptedByDriverActivity..Lambda.7.lambdaFactory$(this));
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_accepted_by_driver;
  }
  
  public void hideCancelBooking()
  {
    this.cancelBooking.setVisibility(8);
  }
  
  public void hideProgressBar()
  {
    runOnUiThread(GotixAcceptedByDriverActivity..Lambda.8.lambdaFactory$(this));
  }
  
  public void loadMaps()
  {
    this.mapsFragment = MapsFragment.newInstance();
    loadFragment(this.mapsFragment, R.id.mapsHolder, TAG);
  }
  
  public void navigateToReviewScreen()
  {
    Intent localIntent = new Intent(this, GotixReviewActivity.class);
    localIntent.putExtra("orderIdKey", this.orderId);
    startActivityForResult(localIntent, 25);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 25)
    {
      setResult(-1);
      finish();
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    openMainActivity();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.orderId = getIntent().getIntExtra("orderIdKey", 0);
    initCompositeSubscription();
    loadMaps();
    bindViewById();
    configCircleProgresbar();
    setFontTextView();
    hideBackNavigation();
    ((GotixAcceptedByDriverPresenter)getPresenter()).findingDriver(this.orderId);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    setRefreshVisible(true);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.compositeSubscription.unsubscribe();
  }
  
  public void onNetworkProblem()
  {
    super.onNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    super.onNoInternetConnection();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.action_refresh)
    {
      onRefreshFindingDriver();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    this.compositeSubscription.unsubscribe();
    BusProvider.getInstance().unregister(this);
    isRunning = false;
  }
  
  public void onRefreshFindingDriver()
  {
    showProgressBar();
    ((GotixAcceptedByDriverPresenter)getPresenter()).checkStatusBooking(this.orderId);
  }
  
  protected void onResume()
  {
    super.onResume();
    BusProvider.getInstance().register(this);
    isRunning = true;
  }
  
  protected void onStart()
  {
    super.onStart();
    this.mapsFragment.setZoomControlsEnabled(false);
    this.mapsFragment.setZoomGesturesEnabled(false);
    this.mapsFragment.setMyLocationButtonEnabled(false);
    this.mapsFragment.getMapView().setOnClickListener(GotixAcceptedByDriverActivity..Lambda.1.lambdaFactory$(this));
  }
  
  public void openMap(int paramInt)
  {
    Intent localIntent = new Intent(this, GotixMapsDetailActivity.class);
    localIntent.putExtra(OrderDao.Properties.Order_id.name, paramInt);
    startActivity(localIntent);
  }
  
  public void sendMessageDriver(String paramString)
  {
    this.sendMessage.setOnClickListener(GotixAcceptedByDriverActivity..Lambda.5.lambdaFactory$(this, paramString));
  }
  
  public void setArrivedTimeText(String paramString)
  {
    this.arrivedTimeText.setText(String.format(getResources().getString(R.string.accepted_by_driver_arrived_time_text), new Object[] { paramString }));
  }
  
  public void setBookingDateTime(int paramInt)
  {
    this.bookingDateTime.setText(GotixUtils.getDateFromTimestamp(paramInt, "hh:mm - dd MMM yyyy"));
  }
  
  public void setBookingLocationDestination(String paramString)
  {
    this.bookingLocationDestination.setText(paramString);
  }
  
  public void setBookingLocationDestinationAddress(String paramString)
  {
    this.bookingLocationDestinationAddress.setText(paramString);
  }
  
  public void setBookingLocationOrigin(String paramString)
  {
    this.bookingLocationOrigin.setText(paramString);
  }
  
  public void setBookingLocationOriginAddress(String paramString)
  {
    this.bookingLocationOriginAddress.setText(paramString);
  }
  
  public void setDestinationText(String paramString)
  {
    this.destinationText.setText(paramString);
  }
  
  public void setDriverAvatar(String paramString)
  {
    Glide.with(getApplicationContext()).load(paramString).placeholder(R.drawable.gotix_driver_avatar_placeholder).dontAnimate().into(this.driverAvatar);
  }
  
  public void setDriverName(String paramString)
  {
    this.driverName.setText(paramString);
  }
  
  public void setItemTicketInformation(String paramString)
  {
    SpannableString localSpannableString = new SpannableString(paramString);
    paramString = GotixUtils.getIndexPositionFrom(paramString, ':', '\n');
    localSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gotix_green)), paramString[0], paramString[1], 0);
    this.itemTicketInformation.setText(localSpannableString);
  }
  
  public void showProgressBar()
  {
    runOnUiThread(GotixAcceptedByDriverActivity..Lambda.6.lambdaFactory$(this));
  }
  
  public void successCancelBooking()
  {
    openMainActivity();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixAcceptedByDriverActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */