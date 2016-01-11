package com.gojek.gobox.driverstatus.view;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.gojek.gobox.R.color;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.menu;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import com.gojek.gobox.base.PresenterFactory;
import com.gojek.gobox.driverreview.view.DriverReviewActivity;
import com.gojek.gobox.driverstatus.presenter.DriverStatusPresenter;
import com.gojek.gobox.escrowissue.EscrowIssueActivity;
import com.gojek.gobox.model.BookingDriverStatusResponse;
import com.gojek.gobox.model.BookingInfoResponse;
import com.gojek.gobox.model.BookingItem;
import com.gojek.gobox.model.Driver;
import com.gojek.gobox.model.LatLongPosition;
import com.gojek.gobox.model.Location;
import com.gojek.gobox.model.Order;
import com.gojek.gobox.model.TrackingPolyline;
import com.gojek.gobox.model.TrackingStep;
import com.gojek.gobox.model.Vehicle;
import com.gojek.gobox.ordercompleteconfirmation.view.OrderCompleteConfirmation;
import com.gojek.gobox.util.AlertDialogFragment.AlertDialogListener;
import com.gojek.gobox.util.CustomMapFragment;
import com.gojek.gobox.util.GoBoxPreferences;
import com.gojek.gobox.util.PolyUtil;
import com.gojek.gobox.util.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverStatusActivity
  extends BaseActivity
  implements View.OnClickListener, DriverStatusView, AlertDialogFragment.AlertDialogListener
{
  private BookingDriverStatusResponse mBookingDriverStatusResponse;
  private BroadcastReceiver mBroadcastReceiver;
  private LinearLayout mCallButton;
  private LinearLayout mCallCenterButton;
  private View mCallMessageContainer;
  private LinearLayout mCancelBookingButton;
  private LinearLayout mDestinationContainer;
  private Driver mDriver;
  private ImageView mDriverAvatar;
  private TextView mDriverEstimation;
  private Marker mDriverMarker;
  private TextView mDriverName;
  private TextView mDriverPosition;
  private View mDriverPositionContainer;
  private View mDriverProfileContainer;
  private TextView mDriverStatusLabel;
  private DriverStatusPresenter mDriverStatusPresenter;
  private TextView mExtraService;
  private View mExtraServiceContainer;
  private GoogleMap mGoogleMap;
  private TextView mItemToDeliver;
  private View mMapContainer;
  private String mOrderId;
  private TextView mPickupTime;
  private Polyline mPolyLine;
  private ProgressBar mProgressBar;
  private RatingBar mRatingBar;
  private ScrollView mScrollView;
  private ImageView mSendInvoiceButton;
  private LinearLayout mSendMessage;
  private TextView mTotalText;
  private TextView mVehicleNumber;
  private TextView mVehicleType;
  private Menu optionsMenu;
  
  private void addLocationMarker(double paramDouble1, double paramDouble2, int paramInt)
  {
    this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(paramDouble2, paramDouble1)).icon(BitmapDescriptorFactory.fromResource(paramInt)));
  }
  
  private void bindReceiver()
  {
    this.mBroadcastReceiver = new DriverStatusActivity.1(this);
    registerReceiver(this.mBroadcastReceiver, new IntentFilter("com.gojek.app.GOBOX_PUSH_RECEIVER"));
  }
  
  private Marker createDriverMarker(double paramDouble1, double paramDouble2)
  {
    return this.mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(paramDouble2, paramDouble1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location_gobox)));
  }
  
  private View createLocationLayout(Location paramLocation, boolean paramBoolean1, boolean paramBoolean2)
  {
    View localView = LayoutInflater.from(this).inflate(R.layout.content_confirm, null);
    TextView localTextView1 = (TextView)localView.findViewById(R.id.text_location);
    TextView localTextView2 = (TextView)localView.findViewById(R.id.text_instruction);
    ImageView localImageView = (ImageView)localView.findViewById(R.id.point_icon);
    if (paramBoolean1) {
      localImageView.setImageResource(R.drawable.ic_location_name);
    }
    if (paramBoolean2) {
      ((LinearLayout)localView.findViewById(R.id.route_icon)).setVisibility(4);
    }
    localTextView1.setText(paramLocation.getAddress());
    localTextView2.setText(paramLocation.getInstruction());
    return localView;
  }
  
  private void drawActiveRoute(TrackingStep[] paramArrayOfTrackingStep)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfTrackingStep.length;
    int i = 0;
    while (i < j)
    {
      TrackingStep localTrackingStep = paramArrayOfTrackingStep[i];
      LatLongPosition localLatLongPosition1 = localTrackingStep.getStartLocation();
      LatLongPosition localLatLongPosition2 = localTrackingStep.getEndLocation();
      localArrayList.add(new LatLng(localLatLongPosition1.getLat(), localLatLongPosition1.getLongitude()));
      localArrayList.addAll(PolyUtil.decode(localTrackingStep.getPolyline().getPoints()));
      localArrayList.add(new LatLng(localLatLongPosition2.getLat(), localLatLongPosition2.getLongitude()));
      i += 1;
    }
    if (this.mPolyLine == null)
    {
      paramArrayOfTrackingStep = new PolylineOptions().width(5.0F).color(-16776961).geodesic(true);
      paramArrayOfTrackingStep.addAll(localArrayList);
      this.mPolyLine = this.mGoogleMap.addPolyline(paramArrayOfTrackingStep);
      return;
    }
    this.mPolyLine.setPoints(localArrayList);
  }
  
  private String getOrderIdFromExtras()
  {
    return getIntent().getStringExtra("order id");
  }
  
  private String getTitleFromExtras()
  {
    if (getIntent().getBooleanExtra("is complete", false)) {
      return getString(R.string.my_booking_title);
    }
    return getString(R.string.gobox_title);
  }
  
  private void initBookingSummaryInfo(Location paramLocation, Location[] paramArrayOfLocation, String paramString, int paramInt, double paramDouble, long paramLong)
  {
    Date localDate = new Date(paramLong);
    this.mPickupTime.setText(Utils.convertIntoFineDateFormat(localDate));
    this.mItemToDeliver.setText(paramString);
    int i;
    if (paramInt == 0)
    {
      this.mExtraServiceContainer.setVisibility(8);
      this.mDestinationContainer.addView(createLocationLayout(paramLocation, true, false));
      addLocationMarker(paramLocation.getLongitude().doubleValue(), paramLocation.getLat().doubleValue(), R.drawable.icon_location_origin);
      this.mTotalText.setText(Utils.getPriceByLocale(paramDouble));
      int j = paramArrayOfLocation.length;
      int k = paramArrayOfLocation.length;
      i = 0;
      paramInt = 0;
      label106:
      if (i >= k) {
        return;
      }
      paramLocation = paramArrayOfLocation[i];
      if (paramInt == j - 1) {
        break label218;
      }
      this.mDestinationContainer.addView(createLocationLayout(paramLocation, false, false));
    }
    for (;;)
    {
      addLocationMarker(paramLocation.getLongitude().doubleValue(), paramLocation.getLat().doubleValue(), R.drawable.icon_location_destination);
      i += 1;
      paramInt += 1;
      break label106;
      this.mExtraService.setText(paramInt + " " + getString(R.string.additional_shipper_label));
      break;
      label218:
      this.mDestinationContainer.addView(createLocationLayout(paramLocation, false, true));
    }
  }
  
  private void initBookingSummaryLayout()
  {
    this.mPickupTime = ((TextView)findViewById(R.id.text_pickuptime));
    this.mItemToDeliver = ((TextView)findViewById(R.id.text_itemstodeliver));
    this.mExtraService = ((TextView)findViewById(R.id.text_extraservices));
    this.mExtraServiceContainer = findViewById(R.id.shipper_container);
    this.mDestinationContainer = ((LinearLayout)findViewById(R.id.linear_content_holder));
    this.mTotalText = ((TextView)findViewById(R.id.text_total_price));
  }
  
  private void initDriverProfileInfo(Driver paramDriver, Vehicle paramVehicle)
  {
    this.mDriverProfileContainer.setVisibility(0);
    if (!TextUtils.isEmpty(paramDriver.getAvatar())) {
      Picasso.with(this).load("https://gobox-api.gojek.co.id/" + paramDriver.getAvatar()).into(this.mDriverAvatar);
    }
    this.mDriverName.setText(paramDriver.getName());
    this.mVehicleNumber.setText(paramVehicle.getNumber());
    this.mVehicleType.setText(paramVehicle.getName());
  }
  
  private void initDriverProfileView()
  {
    this.mDriverProfileContainer = findViewById(R.id.driver_profile_container);
    this.mDriverAvatar = ((ImageView)findViewById(R.id.driver_avatar));
    this.mDriverName = ((TextView)findViewById(R.id.driver_name));
    this.mVehicleNumber = ((TextView)findViewById(R.id.vehicle_number));
    this.mVehicleType = ((TextView)findViewById(R.id.vehicle_type));
    this.mSendMessage = ((LinearLayout)findViewById(R.id.send_message_button));
    this.mCallButton = ((LinearLayout)findViewById(R.id.call_button));
    this.mCallCenterButton = ((LinearLayout)findViewById(R.id.call_center_button));
    this.mCancelBookingButton = ((LinearLayout)findViewById(R.id.cancel_booking_button));
    this.mCallMessageContainer = findViewById(R.id.call_message_container);
    this.mRatingBar = ((RatingBar)findViewById(R.id.driver_rating_bar));
  }
  
  private void initDriverStatusInfo(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      this.mDriverStatusLabel.setText(getString(R.string.driver_accepted_message));
      this.mDriverPositionContainer.setVisibility(8);
      this.mCancelBookingButton.setVisibility(0);
      this.mScrollView.setVisibility(0);
      return;
    case 1: 
      this.mDriverStatusLabel.setText(getString(R.string.driver_accepted_message));
      this.mDriverPositionContainer.setVisibility(8);
      this.mCancelBookingButton.setVisibility(8);
      this.mScrollView.setVisibility(0);
      return;
    case 2: 
      this.mDriverStatusLabel.setText(getString(R.string.driver_on_the_way_label));
      this.mDriverPosition.setText(this.mBookingDriverStatusResponse.getDestinations().getAddress());
      showFormattedEstimationTime(this.mBookingDriverStatusResponse.getEstimationTime());
      if (this.mBookingDriverStatusResponse.getSteps() != null) {
        drawActiveRoute(this.mBookingDriverStatusResponse.getSteps());
      }
      this.mDriverPositionContainer.setVisibility(0);
      this.mCancelBookingButton.setVisibility(8);
      this.mScrollView.setVisibility(0);
      return;
    case 3: 
      Intent localIntent = new Intent(this, OrderCompleteConfirmation.class);
      localIntent.putExtra("order id", this.mOrderId);
      startActivityForResult(localIntent, 102);
      return;
    case 4: 
    case 7: 
      setTitle(getString(R.string.my_booking_title));
      this.mDriverStatusLabel.setText(R.string.booking_canceled_by_driver_message);
      this.mDriverStatusLabel.setTextColor(getResources().getColor(R.color.white));
      this.mDriverStatusLabel.setBackgroundColor(getResources().getColor(R.color.gobox_red_canceled));
      this.mDriverPositionContainer.setVisibility(8);
      this.mCancelBookingButton.setVisibility(8);
      this.mMapContainer.setVisibility(8);
      this.optionsMenu.getItem(0).setVisible(false);
      hideProgressBar();
      this.mScrollView.setVisibility(0);
      return;
    case 5: 
      showEscrowIssueScreen();
      return;
    }
    if (this.mBookingDriverStatusResponse.getRating() == 0)
    {
      showRatingScreen();
      return;
    }
    showOrderComplete(this.mBookingDriverStatusResponse.getRating());
  }
  
  private void showDriverPositionOnMap(double paramDouble1, double paramDouble2)
  {
    if (this.mDriverMarker == null) {
      this.mDriverMarker = createDriverMarker(paramDouble1, paramDouble2);
    }
    for (;;)
    {
      this.mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(paramDouble2, paramDouble1), 12.0F));
      return;
      this.mDriverMarker.setPosition(new LatLng(paramDouble2, paramDouble1));
    }
  }
  
  private void showEscrowIssueScreen()
  {
    startActivity(new Intent(this, EscrowIssueActivity.class));
    finish();
  }
  
  private void showFormattedEstimationTime(long paramLong)
  {
    long l1 = TimeUnit.SECONDS.toDays(paramLong);
    long l2 = TimeUnit.SECONDS.toHours(paramLong) % TimeUnit.DAYS.toHours(1L);
    long l3 = TimeUnit.SECONDS.toMinutes(paramLong) % TimeUnit.HOURS.toMinutes(1L);
    long l4 = TimeUnit.MINUTES.toSeconds(1L);
    StringBuilder localStringBuilder = new StringBuilder();
    if (l1 > 0L) {
      localStringBuilder.append(l1).append(getString(R.string.estimation_day_label));
    }
    if (l2 > 0L) {
      localStringBuilder.append(l2).append(getString(R.string.estimation_hour_label));
    }
    if (l3 > 0L) {
      localStringBuilder.append(l3).append(getString(R.string.estimation_minutes_label));
    }
    if ((paramLong % l4 > 0L) && (l3 == 0L)) {
      localStringBuilder.append(getString(R.string.estimation_one_minute_label));
    }
    this.mDriverEstimation.setText(String.format(getString(R.string.driver_status_estimation), new Object[] { localStringBuilder.toString() }));
  }
  
  private void showOrderComplete(int paramInt)
  {
    setTitle(getString(R.string.my_booking_title));
    this.optionsMenu.getItem(0).setVisible(false);
    this.mRatingBar.setRating(paramInt);
    this.mRatingBar.setVisibility(0);
    this.mCallMessageContainer.setVisibility(8);
    this.mDriverStatusLabel.setText(R.string.thanks_order_complete_header);
    this.mDriverStatusLabel.setTextColor(getResources().getColor(R.color.white));
    this.mDriverStatusLabel.setBackgroundColor(getResources().getColor(R.color.order_complete_header_background));
    this.mMapContainer.setVisibility(8);
    this.mDriverPositionContainer.setVisibility(8);
    this.mCancelBookingButton.setVisibility(8);
    this.mSendInvoiceButton.setVisibility(0);
    hideProgressBar();
    this.mScrollView.setVisibility(0);
  }
  
  public void callingCallCenterButtonClicked()
  {
    Intent localIntent = new Intent("android.intent.action.DIAL");
    localIntent.setData(Uri.parse("tel:" + new GoBoxPreferences(this).getCallCenterNumber()));
    startActivity(localIntent);
  }
  
  public void hideProgressBar()
  {
    this.mProgressBar.setVisibility(8);
  }
  
  public void hideToolBarProgress()
  {
    if (this.optionsMenu != null)
    {
      MenuItem localMenuItem = this.optionsMenu.findItem(R.id.refresh);
      if (localMenuItem != null) {
        localMenuItem.setActionView(null);
      }
    }
  }
  
  public void initMapView()
  {
    this.mMapContainer = findViewById(R.id.map_container);
    CustomMapFragment localCustomMapFragment = (CustomMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
    this.mScrollView = ((ScrollView)findViewById(R.id.scrollView));
    this.mGoogleMap = localCustomMapFragment.getMap();
    localCustomMapFragment.setListener(DriverStatusActivity..Lambda.1.lambdaFactory$(this));
    this.mGoogleMap.setMapType(1);
    this.mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
    this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
    this.mGoogleMap.setMyLocationEnabled(true);
  }
  
  public void initViews()
  {
    this.mProgressBar = ((ProgressBar)findViewById(R.id.progress_bar));
    this.mDriverStatusLabel = ((TextView)findViewById(R.id.driver_status_label));
    this.mDriverPositionContainer = findViewById(R.id.driver_position_status_container);
    this.mDriverPosition = ((TextView)findViewById(R.id.driver_position));
    this.mDriverEstimation = ((TextView)findViewById(R.id.driver_estimation));
    this.mSendInvoiceButton = ((ImageView)findViewById(R.id.send_invoice_button));
    initMapView();
    initDriverProfileView();
    initBookingSummaryLayout();
  }
  
  public void negativeButtonClicked(int paramInt) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 101) && (paramInt2 == -1))
    {
      showOrderComplete(paramIntent.getIntExtra("rating result", 0));
      return;
    }
    if ((paramInt1 == 102) && (paramInt2 == -1))
    {
      if (paramIntent.getBooleanExtra("confirmation result", false))
      {
        showRatingScreen();
        return;
      }
      showEscrowIssueScreen();
      return;
    }
    paramIntent = new Intent("com.gojek.app.HOME");
    paramIntent.addFlags(268468224);
    paramIntent.putExtra("FEED_BACK", 2);
    startActivity(paramIntent);
    finish();
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent("com.gojek.app.HOME");
    localIntent.putExtra("FEED_BACK", 2);
    localIntent.addFlags(131072);
    startActivity(localIntent);
    finish();
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.cancel_booking_button) {
      this.mDriverStatusPresenter.onCancelButtonClicked(this.mOrderId);
    }
    do
    {
      return;
      if (paramView.getId() == R.id.call_button)
      {
        openCallDriverScreen();
        return;
      }
      if (paramView.getId() == R.id.send_message_button)
      {
        openMessageDriverScreen();
        return;
      }
      if (paramView.getId() == R.id.call_center_button)
      {
        this.mDriverStatusPresenter.onCallingCallCenterButtonClicked();
        return;
      }
    } while (paramView.getId() != R.id.send_invoice_button);
    this.mDriverStatusPresenter.onSendInvoiceButtonClicked(this.mOrderId, new GoBoxPreferences(this).getEmail());
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_driver_status);
    initToolbarView();
    setTitle(getTitleFromExtras());
    this.mDriverStatusPresenter = PresenterFactory.createDriverStatusPresenter(this, getNetworkManager());
    this.mOrderId = getOrderIdFromExtras();
    this.mDriverStatusPresenter.onDriverStatusCreateView(this.mOrderId);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    this.optionsMenu = paramMenu;
    getMenuInflater().inflate(R.menu.menu_driver_status, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.refresh)
    {
      this.mDriverStatusPresenter.onRefreshStatus(this.mOrderId);
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onResume()
  {
    bindReceiver();
    super.onResume();
  }
  
  protected void onStop()
  {
    unregisterReceiver(this.mBroadcastReceiver);
    super.onStop();
  }
  
  public void openCallDriverScreen()
  {
    if ((this.mDriver != null) && (!TextUtils.isEmpty(this.mDriver.getPhoneNumber())))
    {
      String str = this.mDriver.getPhoneNumber();
      str = "tel:" + str;
      Intent localIntent = new Intent("android.intent.action.DIAL");
      localIntent.setData(Uri.parse(str));
      startActivity(localIntent);
      return;
    }
    Toast.makeText(this, "Driver doesn't have phone number", 0).show();
  }
  
  public void openMessageDriverScreen()
  {
    if ((this.mDriver != null) && (!TextUtils.isEmpty(this.mDriver.getPhoneNumber())))
    {
      String str = this.mDriver.getPhoneNumber();
      startActivity(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str)));
      return;
    }
    Toast.makeText(this, "Driver doesn't have phone number", 0).show();
  }
  
  public void positiveButtonClicked(int paramInt) {}
  
  public void showBookingData(BookingInfoResponse paramBookingInfoResponse, BookingDriverStatusResponse paramBookingDriverStatusResponse)
  {
    this.mBookingDriverStatusResponse = paramBookingDriverStatusResponse;
    this.mDriver = paramBookingInfoResponse.getDriver();
    updateBookingState(paramBookingDriverStatusResponse);
    if ((paramBookingInfoResponse.getDriver() != null) && (paramBookingInfoResponse.getVehicle() != null)) {
      initDriverProfileInfo(paramBookingInfoResponse.getDriver(), paramBookingInfoResponse.getVehicle());
    }
    for (;;)
    {
      initBookingSummaryInfo(paramBookingInfoResponse.getOrigin(), paramBookingInfoResponse.getDestinations(), paramBookingInfoResponse.getItem().getDescription(), paramBookingInfoResponse.getOrder().getNumberOfShipper(), paramBookingInfoResponse.getOrder().getTotalPrice(), paramBookingInfoResponse.getOrder().getTime());
      return;
      this.mDriverProfileContainer.setVisibility(8);
    }
  }
  
  public void showCancelConfirmationDialog()
  {
    new AlertDialog.Builder(this).setTitle(getString(R.string.confirmation_title)).setMessage(getString(R.string.cancel_booking_confirmation_message)).setPositiveButton("Yes", DriverStatusActivity..Lambda.3.lambdaFactory$(this)).setNegativeButton("No", null).show();
  }
  
  public void showCancelFailedMessage()
  {
    dismissSimpleProgressDialog();
    showSimpleDialog(null, getString(R.string.failed_canceling_booking_message), null, true, null);
  }
  
  public void showCancelSuccessMessage()
  {
    dismissSimpleProgressDialog();
    showSimpleDialog(null, getString(R.string.cancel_booking_success_message), null, false, DriverStatusActivity..Lambda.2.lambdaFactory$(this));
  }
  
  public void showCancelingProgress()
  {
    showSimpleProgressDialog(getString(R.string.canceling_progress_message));
  }
  
  public void showErrorMessage(Throwable paramThrowable)
  {
    dismissSimpleProgressDialog();
    hideProgressBar();
    errorConnectionHandler(paramThrowable);
  }
  
  public void showProgressBar(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      showSimpleProgressDialog("Requesting ...");
      return;
    }
    this.mProgressBar.setVisibility(0);
    this.mScrollView.setVisibility(8);
  }
  
  public void showRatingScreen()
  {
    Intent localIntent = new Intent(this, DriverReviewActivity.class);
    localIntent.putExtra("order id", this.mOrderId);
    localIntent.putExtra("driver name", this.mDriver.getName());
    localIntent.putExtra("avatar", this.mDriver.getAvatar());
    startActivityForResult(localIntent, 101);
  }
  
  public void showSendInvoiceSuccessMessage()
  {
    dismissSimpleProgressDialog();
    showSimpleDialog(null, "Invoice has been sent", null, true, null);
  }
  
  public void showToolBarProgress()
  {
    if (this.optionsMenu != null)
    {
      MenuItem localMenuItem = this.optionsMenu.findItem(R.id.refresh);
      if (localMenuItem != null) {
        localMenuItem.setActionView(R.layout.refresh_progress);
      }
    }
  }
  
  public void updateBookingState(BookingDriverStatusResponse paramBookingDriverStatusResponse)
  {
    this.mBookingDriverStatusResponse = paramBookingDriverStatusResponse;
    initDriverStatusInfo(paramBookingDriverStatusResponse.getState());
    if (paramBookingDriverStatusResponse.getTrackingDriver() != null) {
      showDriverPositionOnMap(paramBookingDriverStatusResponse.getTrackingDriver().getLongitude(), paramBookingDriverStatusResponse.getTrackingDriver().getLat());
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/view/DriverStatusActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */