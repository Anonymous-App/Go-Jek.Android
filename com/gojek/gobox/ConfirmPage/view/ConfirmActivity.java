package com.gojek.gobox.ConfirmPage.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.gojek.gobox.ConfirmPage.presenter.ConfirmPresenter;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import com.gojek.gobox.base.PresenterFactory;
import com.gojek.gobox.bookingstatus.view.BookingStatusActivity;
import com.gojek.gobox.model.BookingItem;
import com.gojek.gobox.model.BookingRequestBody;
import com.gojek.gobox.model.EstimatedPrice;
import com.gojek.gobox.model.EstimationDetail;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.model.Location;
import com.gojek.gobox.model.Order;
import com.gojek.gobox.model.OrderResponse;
import com.gojek.gobox.model.Payment;
import com.gojek.gobox.networking.ConnectionManager;
import com.gojek.gobox.util.AlertDialogFragment.AlertDialogListener;
import com.gojek.gobox.util.GoBoxPreferences;
import com.gojek.gobox.util.PreferencesHelper;
import com.gojek.gobox.util.Utils;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ConfirmActivity
  extends BaseActivity
  implements View.OnClickListener, AdapterView.OnItemSelectedListener, ConfirmView, AlertDialogFragment.AlertDialogListener
{
  private static final String NULL_VAR = null;
  private static final String TAG = ConfirmActivity.class.getName();
  private static final String email_ = "email";
  private BookingRequestBody bookingRequestBody;
  private long customerCredit;
  private ImageView mButtonOrder;
  private long mCargoId;
  private ConfirmPresenter mConfirmPresenter;
  private EstimationResponse mEstimationResponse;
  private TextView mExtraService;
  private GoBoxPreferences mGoboxPreferences;
  private LinearLayout mLinearContentHolder;
  private LinearLayout mLinearCredit;
  private ProgressBar mOrderProgressBar;
  private TextView mPickUpTime;
  private TextView mPrice;
  private ProgressBar mProgressTotal;
  private View mShipperContainer;
  private Spinner mSpinnerPayWith;
  private TextView mTextCredit;
  private TextView mTextItemToDeliver;
  private TextView mTotalPrice;
  private WebView mWebViewPaymentInfo;
  private TextView madditionalPrice;
  private int paymentCode;
  
  private String getUserEmail()
  {
    return PreferencesHelper.getString(this, "email", NULL_VAR);
  }
  
  private boolean isGojekCreditAccepted()
  {
    boolean bool = false;
    double d = this.bookingRequestBody.getOrder().getTotalPrice();
    if (this.customerCredit >= d) {
      bool = true;
    }
    return bool;
  }
  
  private void populateOriginData(String paramString1, String paramString2)
  {
    View localView = LayoutInflater.from(this).inflate(R.layout.content_confirm, null);
    TextView localTextView1 = (TextView)localView.findViewById(R.id.text_location);
    TextView localTextView2 = (TextView)localView.findViewById(R.id.text_instruction);
    ((ImageView)localView.findViewById(R.id.point_icon)).setImageResource(R.drawable.ic_location_name);
    localTextView1.setText(paramString1);
    localTextView2.setText(paramString2);
    this.mLinearContentHolder.addView(localView);
  }
  
  private void setOrderButtonEnabled(boolean paramBoolean)
  {
    this.mButtonOrder.setEnabled(paramBoolean);
    if (paramBoolean)
    {
      this.mButtonOrder.setImageAlpha(200);
      return;
    }
    this.mButtonOrder.setImageAlpha(75);
  }
  
  private void showGoJekCreditPayment()
  {
    double d = this.bookingRequestBody.getOrder().getTotalPrice();
    this.mLinearCredit.setVisibility(0);
    if (isGojekCreditAccepted())
    {
      this.mTextCredit.setText("- " + getPriceByLocale(d));
      this.mTotalPrice.setText(getPriceByLocale(0.0D));
      setOrderButtonEnabled(true);
    }
    for (;;)
    {
      this.paymentCode = 1;
      return;
      String str = String.format(getString(R.string.insufficient_credit_message), new Object[] { getPriceByLocale(d - this.customerCredit) });
      showSimpleDialog(getString(R.string.insufficient_credit_title), str, null, true, null);
      this.mTextCredit.setText("- " + getPriceByLocale(this.customerCredit));
      this.mTotalPrice.setText(getPriceByLocale(d - this.customerCredit));
      setOrderButtonEnabled(false);
    }
  }
  
  private void showPaymentInfo()
  {
    this.mWebViewPaymentInfo = new WebView(this);
    this.mWebViewPaymentInfo.setWebViewClient(new WebViewClient());
    this.mWebViewPaymentInfo.setWebChromeClient(new WebChromeClient());
    Object localObject = this.mWebViewPaymentInfo.getSettings();
    ((WebSettings)localObject).setLoadsImagesAutomatically(false);
    ((WebSettings)localObject).setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    this.mWebViewPaymentInfo.loadUrl("http://go-jek.com/credits/top-up/#/email/" + getUserEmail());
    localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setView(this.mWebViewPaymentInfo);
    ((AlertDialog.Builder)localObject).setPositiveButton("OK", new ConfirmActivity.1(this));
    ((AlertDialog.Builder)localObject).show();
  }
  
  public String getPriceByLocale(double paramDouble)
  {
    NumberFormat localNumberFormat = NumberFormat.getCurrencyInstance(new Locale("ca", "CA"));
    return "Rp " + localNumberFormat.format(paramDouble).replace("CA$", "").replace(",00", "");
  }
  
  public void hideConfirmProgressBar()
  {
    this.mOrderProgressBar.setVisibility(8);
    this.mButtonOrder.setVisibility(0);
  }
  
  public void initView()
  {
    Object localObject = new Gson();
    String str = getIntent().getStringExtra("booking body");
    if (!(localObject instanceof Gson))
    {
      localObject = ((Gson)localObject).fromJson(str, BookingRequestBody.class);
      this.bookingRequestBody = ((BookingRequestBody)localObject);
      this.mCargoId = getIntent().getLongExtra("cargo_type_id", 0L);
      localObject = new Gson();
      str = getIntent().getStringExtra("loading_shipper_price");
      if ((localObject instanceof Gson)) {
        break label325;
      }
    }
    label325:
    for (localObject = ((Gson)localObject).fromJson(str, EstimationResponse.class);; localObject = GsonInstrumentation.fromJson((Gson)localObject, str, EstimationResponse.class))
    {
      this.mEstimationResponse = ((EstimationResponse)localObject);
      this.mLinearContentHolder = ((LinearLayout)findViewById(R.id.linear_content_holder));
      this.mTextItemToDeliver = ((TextView)findViewById(R.id.text_itemstodeliver));
      this.mExtraService = ((TextView)findViewById(R.id.text_extraservices));
      this.mPickUpTime = ((TextView)findViewById(R.id.text_pickuptime));
      this.mPrice = ((TextView)findViewById(R.id.text_price));
      this.mTotalPrice = ((TextView)findViewById(R.id.text_total_price));
      this.madditionalPrice = ((TextView)findViewById(R.id.additional_price));
      this.mButtonOrder = ((ImageView)findViewById(R.id.button_order));
      this.mSpinnerPayWith = ((Spinner)findViewById(R.id.spinner_pay_with));
      this.mLinearCredit = ((LinearLayout)findViewById(R.id.linear_gojek_credit));
      this.mShipperContainer = findViewById(R.id.shipper_container);
      this.mOrderProgressBar = ((ProgressBar)findViewById(R.id.order_progress));
      this.mProgressTotal = ((ProgressBar)findViewById(R.id.progress_bar));
      this.mTextCredit = ((TextView)findViewById(R.id.text_credit));
      this.mButtonOrder.setOnClickListener(this);
      this.mConfirmPresenter.onPopulateData();
      return;
      localObject = GsonInstrumentation.fromJson((Gson)localObject, str, BookingRequestBody.class);
      break;
    }
  }
  
  public void negativeButtonClicked(int paramInt) {}
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.button_order)
    {
      if (!this.mGoboxPreferences.isLoggedIn()) {
        break label71;
      }
      paramView = new Payment(this.paymentCode);
      this.bookingRequestBody.setPayment(paramView);
      if (ConnectionManager.isConnected(this)) {
        this.mConfirmPresenter.onOrderAction((int)this.mCargoId, this.bookingRequestBody);
      }
    }
    else
    {
      return;
    }
    noInternetConnectionHandler();
    return;
    label71:
    startActivity(new Intent("com.gojek.app.SIGN_UP"));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_confirm);
    initToolbarView();
    setTitle(getString(R.string.gobox_title));
    this.mConfirmPresenter = PresenterFactory.createConfirmPresenterFactory(this, getNetworkManager());
    this.mGoboxPreferences = new GoBoxPreferences(this);
    this.mConfirmPresenter.onCreateView();
  }
  
  public void onFetchGoJekCreditFinished()
  {
    this.mProgressTotal.setVisibility(8);
    this.mTotalPrice.setVisibility(0);
    setOrderButtonEnabled(true);
  }
  
  public void onFetchingGoJekCredit()
  {
    this.mProgressTotal.setVisibility(0);
    this.mTotalPrice.setVisibility(8);
    setOrderButtonEnabled(false);
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    double d = this.bookingRequestBody.getOrder().getTotalPrice();
    if (paramInt == 0)
    {
      setOrderButtonEnabled(true);
      paramLong = this.mGoboxPreferences.getEscrowCeiling();
      if (d >= paramLong)
      {
        paramAdapterView = String.format(getString(R.string.escrow_limit_price_message), new Object[] { getPriceByLocale(paramLong) });
        showSimpleDialog(getString(R.string.payment_method_is_not_valid), paramAdapterView, null, false, ConfirmActivity..Lambda.1.lambdaFactory$(this));
      }
      this.mTotalPrice.setText(getPriceByLocale(d));
      this.mLinearCredit.setVisibility(8);
      this.paymentCode = 0;
    }
    while (paramInt != 1) {
      return;
    }
    if (this.customerCredit == -1L)
    {
      this.mConfirmPresenter.onConfirmScreenResumed(this.mGoboxPreferences.getUserUUID());
      return;
    }
    showGoJekCreditPayment();
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
  
  protected void onResume()
  {
    super.onResume();
    showTotalPrice(getIntent().getLongExtra("credit balance", 0L));
  }
  
  public void openBookingStatusActivity(OrderResponse paramOrderResponse)
  {
    Intent localIntent = new Intent(this, BookingStatusActivity.class);
    localIntent.putExtra("order id", paramOrderResponse.getOrderId() + "");
    localIntent.addFlags(268468224);
    Log.d(TAG, "confirm orderId: " + paramOrderResponse.getOrderId());
    startActivity(localIntent);
    Log.d("Order Id", paramOrderResponse.getOrderId() + " ");
    finish();
  }
  
  public void populateView()
  {
    populateOriginData(this.bookingRequestBody.getOrigin().getAddress(), this.bookingRequestBody.getOrigin().getInstruction());
    int i = 0;
    while (i < this.bookingRequestBody.getDestinations().size())
    {
      localObject = LayoutInflater.from(this).inflate(R.layout.content_confirm, null);
      TextView localTextView1 = (TextView)((View)localObject).findViewById(R.id.text_location);
      TextView localTextView2 = (TextView)((View)localObject).findViewById(R.id.text_instruction);
      localTextView1.setText(((Location)this.bookingRequestBody.getDestinations().get(i)).getAddress());
      localTextView2.setText(((Location)this.bookingRequestBody.getDestinations().get(i)).getInstruction());
      if (i == this.bookingRequestBody.getDestinations().size() - 1) {
        ((LinearLayout)((View)localObject).findViewById(R.id.route_icon)).setVisibility(4);
      }
      this.mLinearContentHolder.addView((View)localObject);
      i += 1;
    }
    this.mTextItemToDeliver.setText(this.bookingRequestBody.getItem().getDescription());
    Object localObject = new StringBuilder();
    if (this.bookingRequestBody.getOrder().getNumberOfShipper() > 0)
    {
      ((StringBuilder)localObject).append(this.bookingRequestBody.getOrder().getNumberOfShipper() + " " + getString(R.string.additional_shipper_label));
      if (this.bookingRequestBody.getItem().isAirConditionerSupport()) {
        ((StringBuilder)localObject).append("\n" + getString(R.string.air_conditioner_label));
      }
      if ((this.bookingRequestBody.getOrder().getNumberOfShipper() != 0) || (this.bookingRequestBody.getItem().isAirConditionerSupport())) {
        break label382;
      }
      this.mShipperContainer.setVisibility(8);
    }
    for (;;)
    {
      localObject = new Date(this.bookingRequestBody.getOrder().getTime());
      this.mPickUpTime.setText(Utils.convertIntoFineDateFormat((Date)localObject));
      return;
      if (!this.bookingRequestBody.getItem().isAirConditionerSupport()) {
        break;
      }
      ((StringBuilder)localObject).append(getString(R.string.air_conditioner_label));
      break;
      label382:
      this.mExtraService.setText(((StringBuilder)localObject).toString());
    }
  }
  
  public void positiveButtonClicked(int paramInt)
  {
    if (paramInt == 18) {}
  }
  
  public void showConfirmProgressBar()
  {
    this.mOrderProgressBar.setVisibility(0);
    this.mButtonOrder.setVisibility(8);
  }
  
  public void showErrorConfirmOrder(Throwable paramThrowable)
  {
    errorConnectionHandler(paramThrowable);
  }
  
  public void showTotalPrice(long paramLong)
  {
    this.customerCredit = paramLong;
    this.mTextCredit.setText(getPriceByLocale(this.customerCredit));
    EstimatedPrice localEstimatedPrice = this.mEstimationResponse.getEstimatedPrice();
    this.mPrice.setText(getPriceByLocale(localEstimatedPrice.getDetail()[0].getPrice()));
    this.madditionalPrice.setText(getPriceByLocale(localEstimatedPrice.getDetail()[1].getPrice()));
    this.mTotalPrice.setVisibility(0);
    this.mSpinnerPayWith.setOnItemSelectedListener(this);
    if ((this.bookingRequestBody.getOrder().getTotalPrice() >= this.mGoboxPreferences.getEscrowCeiling()) || (this.bookingRequestBody.getOrder().getTotalPrice() <= this.customerCredit))
    {
      this.mSpinnerPayWith.setSelection(1);
      if (this.mSpinnerPayWith.getSelectedItemPosition() == 1) {
        showGoJekCreditPayment();
      }
      return;
    }
    this.mSpinnerPayWith.setSelection(0);
    this.mTotalPrice.setText(getPriceByLocale(this.bookingRequestBody.getOrder().getTotalPrice()));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ConfirmPage/view/ConfirmActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */