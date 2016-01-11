package com.gojek.gotix.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gojek.gotix.Location;
import com.gojek.gotix.Order;
import com.gojek.gotix.OrderSchedule;
import com.gojek.gotix.R.color;
import com.gojek.gotix.R.drawable;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.presenter.GotixReceiptTicketPresenter;
import com.gojek.gotix.services.model.ProduceOrder;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import com.gojek.gotix.tools.GotixUtils;
import com.gojek.gotix.views.SpannedTextView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.squareup.otto.Subscribe;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.helper.BaseToPickServiceGojek;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.helper.SignUpHelper;
import lib.gojek.base.util.BaseConnectionManager;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(GotixReceiptTicketPresenter.class)
public class GotixReceiptTicketActivity
  extends GotixBaseActivity<GotixReceiptTicketPresenter>
{
  private static final String ALL_DAY = "allday";
  private static final String BEGIN_TIME = "beginTime";
  public static final String BOOKING_CODE = "booking_code";
  public static final int DONE_BUTTON_SHOW = 1;
  public static final String DONE_FLAG = "done_flag";
  private static final String END_TIME = "endTime";
  public static final String EVENT_ID = "event_id";
  private static final String INTENT_SHARE = "Share";
  public static final String ORDER_ID = "orderIdKey";
  private static final String REQUEST_CODE = "requestCode";
  public static final int REQUEST_CODE_DELIVERY_FORM_INTENT = 20;
  private static final String SINGLE_SPACE = " ";
  private static String TAG = GotixReceiptTicketActivity.class.getSimpleName();
  public static final String TICKET = "ticket";
  public static final String TITLE_EVENT = "title_event";
  private static final String TITLE_SHARE = "title";
  private static final String TYPE_FILE_SHARE = "vnd.android.cursor.item/event";
  private static final String TYPE_TEXT_SHARE = "text/plain";
  public static boolean isRunning;
  private ImageButton addToCalendarButton;
  private BaseToPickServiceGojek baseToPickServiceGojek;
  private TextView bookingReferenceBig;
  private TextView bookingReferenceSmall;
  private GregorianCalendar calendarEnd;
  private GregorianCalendar calendarStart;
  private LinearLayout contentReceipt;
  private Context context;
  private TextView dateEventReceipt;
  private ImageButton deliverNowButton;
  private TextView deliveryMsgReceipt;
  private ImageButton doneButtonReceipt;
  private TextView emailMsgReceipt;
  private ImageView imageReceipt;
  private TextView locationEventReceipt;
  private CircleProgressBar mProgressBar;
  private int orderId;
  private TextView priceEventReceipt;
  private TextView receiptTextPurchased;
  private ImageButton shareButton;
  private SignUpHelper signUpHelper;
  private ImageButton takeMeThereButton;
  private TextView ticketEventReceipt;
  private RelativeLayout ticketInfoLayout;
  private SpannedTextView ticketTypeReceipt;
  private TextView timeEventReceipt;
  private TextView titleBookingReference;
  private TextView titleEventReceipt;
  private TextView titleReceipt;
  
  private void addEventToCalendar()
  {
    Intent localIntent = new Intent("android.intent.action.EDIT");
    localIntent.setType("vnd.android.cursor.item/event");
    localIntent.putExtra("beginTime", this.calendarStart.getTimeInMillis());
    localIntent.putExtra("allday", false);
    localIntent.putExtra("endTime", this.calendarEnd.getTimeInMillis());
    localIntent.putExtra("title", this.titleEventReceipt.getText().toString());
    startActivity(localIntent);
  }
  
  private void backToMainPage()
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("lastActiveTab", 2);
    setResult(-1, localIntent);
    finish();
  }
  
  private void bindViewById()
  {
    this.titleReceipt = ((TextView)findViewById(R.id.receipt_title));
    this.deliverNowButton = ((ImageButton)findViewById(R.id.receipt_button_deliver_now));
    this.takeMeThereButton = ((ImageButton)findViewById(R.id.receipt_button_take_me_there));
    this.imageReceipt = ((ImageView)findViewById(R.id.receipt_image));
    this.ticketInfoLayout = ((RelativeLayout)findViewById(R.id.receipt_ticket_type_layout));
    this.ticketTypeReceipt = ((SpannedTextView)findViewById(R.id.receipt_ticket_type));
    this.titleBookingReference = ((TextView)findViewById(R.id.title_booking_ref));
    this.bookingReferenceSmall = ((TextView)findViewById(R.id.receipt_booking_ref_small));
    this.bookingReferenceBig = ((TextView)findViewById(R.id.receipt_booking_ref_big));
    this.emailMsgReceipt = ((TextView)findViewById(R.id.receipt_email_msg));
    this.deliveryMsgReceipt = ((TextView)findViewById(R.id.receipt_delivery_msg));
    this.titleEventReceipt = ((TextView)findViewById(R.id.receipt_title_event));
    this.dateEventReceipt = ((TextView)findViewById(R.id.receipt_date_event));
    this.timeEventReceipt = ((TextView)findViewById(R.id.receipt_time_event));
    this.locationEventReceipt = ((TextView)findViewById(R.id.receipt_location_event));
    this.ticketEventReceipt = ((TextView)findViewById(R.id.receipt_ticket_event));
    this.priceEventReceipt = ((TextView)findViewById(R.id.receipt_price_event));
    this.doneButtonReceipt = ((ImageButton)findViewById(R.id.receipt_button_done));
    this.contentReceipt = ((LinearLayout)findViewById(R.id.content_receipt));
    this.mProgressBar = ((CircleProgressBar)findViewById(R.id.receipt_progress_bar));
    this.addToCalendarButton = ((ImageButton)findViewById(R.id.receipt_button_add_calendar));
    this.shareButton = ((ImageButton)findViewById(R.id.receipt_button_share));
    this.receiptTextPurchased = ((TextView)findViewById(R.id.receipt_text_purchased));
    this.addToCalendarButton.setOnClickListener(GotixReceiptTicketActivity..Lambda.1.lambdaFactory$(this));
  }
  
  private String checkStatusTicketEvent(String paramString)
  {
    if (new BigDecimal(paramString).compareTo(BigDecimal.ZERO) == 0) {
      return getString(R.string.event_detail_free_event);
    }
    return GotixUtils.getRupiahFormat(paramString);
  }
  
  private String checkStatusTicketForShare(String paramString)
  {
    if (new BigDecimal(paramString).compareTo(BigDecimal.ZERO) == 0) {
      return getString(R.string.ticket_booked);
    }
    return getString(R.string.ticket_bought);
  }
  
  private void configColorProgressBar()
  {
    this.mProgressBar.setColorSchemeResources(new int[] { R.color.bg_base_green });
  }
  
  private String getGojekShareUrl(String paramString)
  {
    if (BaseConnectionManager.isConnected(this.context)) {
      return paramString;
    }
    return getString(R.string.share_url);
  }
  
  private void getIntentEventId(Order paramOrder)
    throws ParseException
  {
    Intent localIntent = getIntent();
    switch (localIntent.getIntExtra("done_flag", 0))
    {
    }
    for (;;)
    {
      this.orderId = localIntent.getIntExtra("orderIdKey", paramOrder.getOrder_id().intValue());
      return;
      hideBackNavigation();
      showButtonDone();
      continue;
      hideButtonDone();
      hideDeliveryButton(2);
      showReceiptTextPurchased(paramOrder);
      continue;
      hideButtonDone();
      hideTitleTextReceipt();
      showDeliveryButton();
      continue;
      hideButtonDone();
      setTitleWhenDelivered();
      showTakeMeThere(((GotixReceiptTicketPresenter)getPresenter()).isTimeScheduleToday(paramOrder.getSchedules()));
      showPendingReview();
    }
  }
  
  private void hideButtonDone()
  {
    this.doneButtonReceipt.setVisibility(8);
  }
  
  private void hideDeliveryButton(int paramInt)
  {
    this.titleReceipt.setText(getString(R.string.receipt_purchased_title));
    if (paramInt == 2)
    {
      this.deliveryMsgReceipt.setVisibility(8);
      return;
    }
    this.deliveryMsgReceipt.setVisibility(0);
  }
  
  private void hideDetail()
  {
    this.contentReceipt.setVisibility(8);
  }
  
  private void hideTitleTextReceipt()
  {
    this.receiptTextPurchased.setVisibility(8);
  }
  
  private boolean isLatLongValid(Location paramLocation)
  {
    return (paramLocation.getLatitude().doubleValue() != 0.0D) && (paramLocation.getLongitude().doubleValue() != 0.0D);
  }
  
  private void receiptShareButton(String paramString1, String paramString2)
  {
    this.shareButton.setOnClickListener(GotixReceiptTicketActivity..Lambda.4.lambdaFactory$(this, paramString1, paramString2));
  }
  
  private void setFontTextView()
  {
    TypefaceHelper.typeface(this.titleReceipt, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.titleBookingReference, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.bookingReferenceSmall, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.bookingReferenceBig, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.emailMsgReceipt, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.deliveryMsgReceipt, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.titleEventReceipt, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.dateEventReceipt, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.timeEventReceipt, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.locationEventReceipt, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.ticketEventReceipt, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.priceEventReceipt, FontFaceHelper.getLatoFont());
  }
  
  private String setReceiptTextPurchased(boolean paramBoolean)
  {
    if (paramBoolean) {
      return getString(R.string.ticket_physical_desc);
    }
    return getString(R.string.ticket_online_desc);
  }
  
  private void setTitleWhenDelivered()
  {
    this.titleReceipt.setText(getString(R.string.receipt_take_me_there_title));
  }
  
  private void shareEventToThirdParty(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", String.format(getString(R.string.share_text), new Object[] { paramString1, this.titleEventReceipt.getText().toString(), getGojekShareUrl(paramString2) }));
    startActivity(Intent.createChooser(localIntent, "Share"));
  }
  
  private void showButtonDone()
  {
    hideDeliveryButton(1);
    this.doneButtonReceipt.setVisibility(0);
    this.doneButtonReceipt.setOnClickListener(GotixReceiptTicketActivity..Lambda.2.lambdaFactory$(this));
  }
  
  private void showDeliveryButton()
  {
    this.deliverNowButton.setVisibility(0);
    this.titleReceipt.setText(getString(R.string.receipt_delivery_title));
  }
  
  private void showDetail()
  {
    this.contentReceipt.setVisibility(0);
  }
  
  private void showDialogTicketInfo(String paramString)
  {
    this.ticketInfoLayout.setOnClickListener(GotixReceiptTicketActivity..Lambda.6.lambdaFactory$(this, paramString));
  }
  
  private void showPendingReview()
  {
    ((GotixReceiptTicketPresenter)getPresenter()).getPendingReviewByOrder(this.orderId);
  }
  
  private void showProgressBar()
  {
    this.mProgressBar.setVisibility(0);
    hideDetail();
  }
  
  private void showReceiptTextPurchased(Order paramOrder)
  {
    if (paramOrder != null) {
      if (paramOrder.getType_is_delivered() == null) {
        break label62;
      }
    }
    label62:
    for (boolean bool = paramOrder.getType_is_delivered().booleanValue();; bool = false)
    {
      this.receiptTextPurchased.setText(setReceiptTextPurchased(bool));
      this.receiptTextPurchased.setVisibility(0);
      if (!bool) {
        showTakeMeThere(((GotixReceiptTicketPresenter)getPresenter()).isTimeScheduleToday(paramOrder.getSchedules()));
      }
      return;
    }
  }
  
  private void showTakeMeThere(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.takeMeThereButton.setVisibility(0);
      return;
    }
    this.takeMeThereButton.setVisibility(8);
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_receipt_ticket;
  }
  
  public void hideProgressBar()
  {
    this.mProgressBar.setVisibility(8);
    showDetail();
  }
  
  public void listenerButtonTakeMeThere(Location paramLocation)
  {
    this.takeMeThereButton.setOnClickListener(GotixReceiptTicketActivity..Lambda.3.lambdaFactory$(this, paramLocation));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      finish();
    }
    while (paramInt2 != 0) {
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    backToMainPage();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.orderId = getIntent().getIntExtra("orderIdKey", 0);
    this.context = getApplicationContext();
    this.baseToPickServiceGojek = new BaseToPickServiceGojek();
    this.signUpHelper = new SignUpHelper(this);
    bindViewById();
    setFontTextView();
    configColorProgressBar();
    showProgressBar();
    ((GotixReceiptTicketPresenter)getPresenter()).loadData(BasePreferences.getCustomerId(), this.orderId);
  }
  
  public void onNetworkProblem()
  {
    super.showDialogErrorService();
  }
  
  public void onNoInternetConnection()
  {
    super.showDialogNoConnection();
  }
  
  protected void onPause()
  {
    super.onPause();
    BusProvider.getInstance().unregister(this);
    isRunning = false;
  }
  
  protected void onResume()
  {
    super.onResume();
    BusProvider.getInstance().register(this);
    isRunning = true;
  }
  
  @Subscribe
  public void onTicketsReadyFromNotif(ProduceOrder paramProduceOrder)
  {
    if (BasePreferences.isLoggedIn())
    {
      showProgressBar();
      if (paramProduceOrder.getEventId() != 0) {
        getIntent().putExtra("done_flag", 3);
      }
      for (;;)
      {
        ((GotixReceiptTicketPresenter)getPresenter()).loadData(BasePreferences.getCustomerId(), paramProduceOrder.getOrderId());
        return;
        getIntent().putExtra("done_flag", 1);
      }
    }
    Toast.makeText(this, getString(R.string.toast_warning_login), 1).show();
  }
  
  public void setDataCalendar(OrderSchedule paramOrderSchedule1, OrderSchedule paramOrderSchedule2)
  {
    String[] arrayOfString1 = paramOrderSchedule1.getDate().split(" ");
    String[] arrayOfString2 = paramOrderSchedule1.getStart_time().split(getString(R.string.timer_separator));
    paramOrderSchedule1 = Calendar.getInstance();
    try
    {
      paramOrderSchedule1.setTime(new SimpleDateFormat(getString(R.string.format_time_month)).parse(arrayOfString1[1]));
      i = paramOrderSchedule1.get(2);
      this.calendarStart = new GregorianCalendar(Integer.parseInt(arrayOfString1[2]), i, Integer.parseInt(arrayOfString1[0]), Integer.parseInt(arrayOfString2[0]), Integer.parseInt(arrayOfString2[1]));
      arrayOfString1 = paramOrderSchedule2.getDate().split(" ");
      paramOrderSchedule2 = paramOrderSchedule2.getStart_time().split(getString(R.string.timer_separator));
    }
    catch (ParseException localParseException2)
    {
      try
      {
        paramOrderSchedule1.setTime(new SimpleDateFormat(getString(R.string.format_time_month)).parse(arrayOfString1[1]));
        int i = paramOrderSchedule1.get(2);
        this.calendarEnd = new GregorianCalendar(Integer.parseInt(arrayOfString1[2]), i, Integer.parseInt(arrayOfString1[0]), Integer.parseInt(paramOrderSchedule2[0]), Integer.parseInt(paramOrderSchedule2[1]));
        return;
        localParseException2 = localParseException2;
        localParseException2.printStackTrace();
      }
      catch (ParseException localParseException1)
      {
        for (;;)
        {
          localParseException1.printStackTrace();
        }
      }
    }
  }
  
  public void setDeliverNowButtonListener(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.deliverNowButton.setOnClickListener(GotixReceiptTicketActivity..Lambda.5.lambdaFactory$(this, paramString1, paramString2, paramString3, paramInt));
  }
  
  public void setReceiptDetail(Order paramOrder)
  {
    try
    {
      getIntentEventId(paramOrder);
      this.titleEventReceipt.setText(paramOrder.getName());
      Glide.with(this.context).load(paramOrder.getImage()).placeholder(R.drawable.gotix_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(this.imageReceipt);
      this.bookingReferenceSmall.setText(paramOrder.getBooking_reference());
      this.bookingReferenceBig.setText(paramOrder.getBooking_reference());
      this.ticketTypeReceipt.setText(paramOrder.getType());
      if (paramOrder.getCharged_amount() == null)
      {
        this.priceEventReceipt.setText(" ");
        receiptShareButton(getString(R.string.ticket_price_zero), paramOrder.getShare_url());
        showDialogTicketInfo(paramOrder.getType_description());
        return;
      }
    }
    catch (ParseException localParseException)
    {
      for (;;)
      {
        localParseException.printStackTrace();
        continue;
        this.priceEventReceipt.setText(checkStatusTicketEvent(paramOrder.getCharged_amount()));
        receiptShareButton(paramOrder.getCharged_amount(), paramOrder.getShare_url());
      }
    }
  }
  
  public void setTextViewDate(String paramString)
  {
    this.dateEventReceipt.setText(paramString);
  }
  
  public void setTextViewLocation(String paramString)
  {
    this.locationEventReceipt.setText(paramString);
  }
  
  public void setTextViewTicket(String paramString)
  {
    this.ticketEventReceipt.setText(paramString);
  }
  
  public void setTextViewTime(String paramString)
  {
    this.timeEventReceipt.setText(paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixReceiptTicketActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */