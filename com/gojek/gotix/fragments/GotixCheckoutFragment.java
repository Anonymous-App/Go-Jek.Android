package com.gojek.gotix.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.annimon.stream.Stream;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.activities.GotixEventDetailActivity;
import com.gojek.gotix.network.model.PaymentComponent;
import com.gojek.gotix.network.model.PaymentData;
import com.gojek.gotix.network.model.PaymentProvider;
import com.gojek.gotix.network.model.PurchasedOrderData;
import com.gojek.gotix.presenter.GotixCheckoutPresenter;
import com.gojek.gotix.tools.GotixUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.nostratech.gojek.driver.common.EncryptedRequestDto;
import com.nostratech.gojek.driver.fragments.FragmentPaymentInfo;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.helper.FontFaceHelper;
import nucleus.factory.RequiresPresenter;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

@RequiresPresenter(GotixCheckoutPresenter.class)
public class GotixCheckoutFragment
  extends GotixBaseFragment<GotixCheckoutPresenter>
{
  private static final String EMPTY_STRING = "";
  private static final String MINUS_PREPOSITION = "- ";
  private static final int MIN_YEAR_DOB = 13;
  private static final int PAYMENT_TYPE_CREDIT_CARD = 2;
  private static final int PAYMENT_TYPE_FREE_EVENT = -1;
  private static final int PAYMENT_TYPE_GOJEK_CREDIT = 1;
  private static final String PAYMENT_WIDGET_AMOUNT_KEY = "amount";
  private static final String PAYMENT_WIDGET_CALLBACK_FLAG_KEY = "callback-notif-flag";
  private static final String PAYMENT_WIDGET_CALLBACK_FLAG_VALUE = "false";
  private static final String PAYMENT_WIDGET_CALLBACK_URL_KEY = "callback-notif-url";
  private static final String PAYMENT_WIDGET_CALLBACK_URL_VALUE = "https://digital-wallet.kartuku.co.id";
  private static final String PAYMENT_WIDGET_PRODUCT_TYPE_KEY = "product-type";
  private static final String PAYMENT_WIDGET_PRODUCT_TYPE_VALUE = "go-tix";
  private static final String PAYMENT_WIDGET_SECURITY_KEY = "security";
  private static final String PAYMENT_WIDGET_SECURITY_VALUE = "12345678";
  private static final String PAYMENT_WIDGET_TRANSACTION_REF_KEY = "transaction-ref";
  private static final String PAYMENT_WIDGET_USER_ID_KEY = "user-id";
  private static final String PURCHASE_ORDER_DATA_KEY = "encrypted_text";
  public static final String TAG = GotixCheckoutFragment.class.getSimpleName();
  private boolean agreementChecked;
  private ImageView agreementInfo;
  private CheckBox checkBoxAgreement;
  private CheckBox checkBoxDelivery;
  private ProgressBar checkoutProgressBar;
  private ImageView checkoutPurchasedBtn;
  private CompositeSubscription compositeSubscription;
  private FrameLayout creditCardLayout;
  private View creditCardLayoutSeparator;
  private String creditCardSurcharge;
  private String customerGojekCredit;
  private boolean dateOfBirthFilled;
  private EditText dateOfBirthPicker;
  private boolean deliveryChecked;
  private ImageView deliveryInfo;
  private TextView descCountDownTimer;
  private DatePickerDialog dobPickerDialog;
  private int eventId;
  private TextView eventTicketItem;
  private TextView eventTitle;
  private TextView eventTitlePayments;
  private TextView eventTitleTickets;
  public int fixDayOfMonth;
  public int fixMonthOfYear;
  public int fixYear;
  private final String formatTime = "%1$02d";
  private RadioGroup genderRadioGroup;
  private BigDecimal gojekCreditBigDecimal;
  private TextView minutesCountDown;
  private LinearLayout numberCountdownTimerContainer;
  private GotixEventDetailActivity parentActivity;
  private LinearLayout paymentComponentContainer;
  private RelativeLayout paymentTypeContainer;
  private LinearLayout paymentTypeLayout;
  private View paymentTypeSeparator;
  private Spinner paymentTypeSpinner;
  private TextView paymentTypeTitle;
  private TextView paymentTypeValue;
  private View paymentView;
  private FragmentPaymentInfo paymentWidget;
  private boolean pending;
  private TextView secondCountDown;
  private View separatorDelivery;
  private LinearLayout ticketCheckGroup;
  private TextView totalPriceValue;
  private String totalPriceWithCreditCard;
  private String totalPriceWithGojekCredit;
  private BigDecimal totalPriceWithGojekCreditBigDecimal;
  
  private void addCheckedListeners()
  {
    this.checkBoxDelivery.setOnCheckedChangeListener(GotixCheckoutFragment..Lambda.4.lambdaFactory$(this));
    this.checkBoxAgreement.setOnCheckedChangeListener(GotixCheckoutFragment..Lambda.5.lambdaFactory$(this));
  }
  
  private void addClickListeners()
  {
    this.deliveryInfo.setOnClickListener(GotixCheckoutFragment..Lambda.1.lambdaFactory$(this));
    this.agreementInfo.setOnClickListener(GotixCheckoutFragment..Lambda.2.lambdaFactory$(this));
    this.checkoutPurchasedBtn.setOnClickListener(GotixCheckoutFragment..Lambda.3.lambdaFactory$(this));
  }
  
  private void addListenerOnDateOfBirthPicker()
  {
    RxView.clicks(this.dateOfBirthPicker).subscribe(GotixCheckoutFragment..Lambda.9.lambdaFactory$(this));
  }
  
  private void addListenerOnPaymentSpinner()
  {
    this.paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (GotixCheckoutFragment.this.isPaymentTypeCreditCard())
        {
          GotixCheckoutFragment.this.setPaymentTypeComponent(true, GotixCheckoutFragment.this.creditCardSurcharge);
          GotixCheckoutFragment.this.setCreditCardLayoutVisibility(true);
          GotixCheckoutFragment.this.totalPriceValue.setText(GotixUtils.getRupiahFormat(GotixCheckoutFragment.this.totalPriceWithCreditCard));
        }
        for (;;)
        {
          GotixCheckoutFragment.this.updatePurchaseButtonState();
          return;
          if (GotixCheckoutFragment.this.isPaymentTypeGojekCredit())
          {
            GotixCheckoutFragment.this.setPaymentTypeComponent(false);
            GotixCheckoutFragment.this.setCreditCardLayoutVisibility(false);
            GotixCheckoutFragment.this.showGojekCreditPayment();
          }
        }
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
  }
  
  private void bindViewById(View paramView)
  {
    this.eventTitleTickets = ((TextView)paramView.findViewById(R.id.event_title_tickets));
    this.eventTitlePayments = ((TextView)paramView.findViewById(R.id.event_title_payment));
    this.eventTitle = ((TextView)paramView.findViewById(R.id.event_title));
    this.eventTicketItem = ((TextView)paramView.findViewById(R.id.event_ticket_item));
    this.paymentComponentContainer = ((LinearLayout)paramView.findViewById(R.id.payment_component_container));
    this.totalPriceValue = ((TextView)paramView.findViewById(R.id.checkout_total_price));
    this.paymentTypeTitle = ((TextView)paramView.findViewById(R.id.checkout_payment_type_title));
    this.paymentTypeValue = ((TextView)paramView.findViewById(R.id.checkout_payment_type_value));
    this.minutesCountDown = ((TextView)paramView.findViewById(R.id.minutes));
    this.secondCountDown = ((TextView)paramView.findViewById(R.id.seconds));
    this.descCountDownTimer = ((TextView)paramView.findViewById(R.id.desc_countdown_timer));
    this.deliveryInfo = ((ImageView)paramView.findViewById(R.id.info_delivery_checkout));
    this.agreementInfo = ((ImageView)paramView.findViewById(R.id.info_agreement_checkout));
    this.ticketCheckGroup = ((LinearLayout)paramView.findViewById(R.id.checkbox_ticket_info));
    this.creditCardLayoutSeparator = paramView.findViewById(R.id.credit_card_layout_separator);
    this.checkBoxAgreement = ((CheckBox)paramView.findViewById(R.id.checkbox_agreement_checkout));
    this.checkBoxDelivery = ((CheckBox)paramView.findViewById(R.id.checkbox_delivery_checkout));
    this.dateOfBirthPicker = ((EditText)paramView.findViewById(R.id.date_of_birth_picker));
    this.genderRadioGroup = ((RadioGroup)paramView.findViewById(R.id.gender_radio_group));
    this.checkoutProgressBar = ((ProgressBar)paramView.findViewById(R.id.checkout_progress_button));
    this.paymentTypeLayout = ((LinearLayout)paramView.findViewById(R.id.payment_type_layout));
    this.paymentTypeSeparator = paramView.findViewById(R.id.payment_type_separator);
    this.creditCardLayout = ((FrameLayout)paramView.findViewById(R.id.credit_card_frame_layout));
    this.paymentTypeSpinner = ((Spinner)paramView.findViewById(R.id.checkout_payment_type_spinner));
    this.paymentTypeContainer = ((RelativeLayout)paramView.findViewById(R.id.payment_type_container));
    this.separatorDelivery = paramView.findViewById(R.id.separator_delivery);
    this.checkoutPurchasedBtn = ((ImageView)paramView.findViewById(R.id.checkout_purchase_button));
    setButtonPurchaseEnabled(false);
    this.numberCountdownTimerContainer = ((LinearLayout)paramView.findViewById(R.id.number_countdown_timer_container));
    this.numberCountdownTimerContainer.setVisibility(8);
  }
  
  private void confirmPurchase()
  {
    showProgressBar();
    int i = 1;
    if ((isPaymentTypeCreditCard()) && (!((GotixCheckoutPresenter)getPresenter()).isFreeEvent()))
    {
      EncryptedRequestDto localEncryptedRequestDto = this.paymentWidget.getCardTransactionInformation();
      if (localEncryptedRequestDto != null) {
        ((GotixCheckoutPresenter)getPresenter()).attemptToConfirmPurchase(getPurchasedOrderData(localEncryptedRequestDto.getEncryptedText()));
      }
    }
    for (;;)
    {
      if (i != 0) {
        stopCountDownTimer();
      }
      return;
      hideProgressBar();
      i = 0;
      continue;
      ((GotixCheckoutPresenter)getPresenter()).attemptToConfirmPurchase(getPurchasedOrderData());
    }
  }
  
  private void dismissDateOfBirthPicker()
  {
    if ((this.dobPickerDialog != null) && (this.dobPickerDialog.isShowing())) {
      this.dobPickerDialog.dismiss();
    }
  }
  
  private String formatToStringPercentage(String paramString)
  {
    return GotixUtils.getPercentageFormat(paramString);
  }
  
  private int getCheckedGenderRadioButton()
  {
    if (this.genderRadioGroup.getCheckedRadioButtonId() == R.id.gender_radio_male) {
      return 1;
    }
    return 2;
  }
  
  private DatePickerDialog getDatePickerDialog(DatePickerDialog.OnDateSetListener paramOnDateSetListener)
  {
    Calendar localCalendar = Calendar.getInstance();
    return new DatePickerDialog(getContext(), paramOnDateSetListener, localCalendar.get(1) - 13, localCalendar.get(2), localCalendar.get(5));
  }
  
  private Integer getDefaultPaymentType(List<PaymentProvider> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      if (((PaymentProvider)paramList.get(i)).getPayment_provider_type() == 2) {
        return Integer.valueOf(i);
      }
      i += 1;
    }
    return Integer.valueOf(0);
  }
  
  private Long getMilisFromMinimumYear()
  {
    Calendar localCalendar = Calendar.getInstance();
    return Long.valueOf(new GregorianCalendar(localCalendar.get(1) - 13, localCalendar.get(2), localCalendar.get(5)).getTimeInMillis());
  }
  
  private Bundle getPaymentBundle(int paramInt)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("user-id", BasePreferences.getCustomerId() + "");
    localBundle.putString("security", "12345678");
    localBundle.putString("product-type", "go-tix");
    localBundle.putString("transaction-ref", "go-tix" + paramInt);
    localBundle.putString("callback-notif-flag", "false");
    localBundle.putString("callback-notif-url", "https://digital-wallet.kartuku.co.id");
    localBundle.putString("amount", getTotalAmountWithCreditCard());
    return localBundle;
  }
  
  private View getPaymentComponentLayout()
  {
    this.paymentView = getInflater().inflate(R.layout.fragment_gotix_payment_component, null);
    return this.paymentView;
  }
  
  private PaymentData[] getPaymentData(String paramString)
  {
    return new PaymentData[] { new PaymentData("encrypted_text", paramString) };
  }
  
  private PurchasedOrderData getPurchasedOrderData()
  {
    return getPurchasedOrderData("");
  }
  
  private PurchasedOrderData getPurchasedOrderData(String paramString)
  {
    return new PurchasedOrderData(getSelectedPaymentType(), getPaymentData(paramString), this.dateOfBirthPicker.getText().toString(), getCheckedGenderRadioButton());
  }
  
  private PaymentProvider getSelectedPaymentProvider()
  {
    return (PaymentProvider)this.paymentTypeSpinner.getItemAtPosition(this.paymentTypeSpinner.getSelectedItemPosition());
  }
  
  private int getSelectedPaymentType()
  {
    if (((GotixCheckoutPresenter)getPresenter()).isFreeEvent()) {
      return -1;
    }
    return getSelectedPaymentProvider().getPayment_provider_type();
  }
  
  private String getTotalAmountWithCreditCard()
  {
    int i = Double.valueOf(this.totalPriceWithCreditCard).intValue();
    return i + "";
  }
  
  private void initCompositeSubscription()
  {
    this.compositeSubscription = new CompositeSubscription();
  }
  
  private boolean isGojekCreditAccepted()
  {
    this.gojekCreditBigDecimal = new BigDecimal(this.customerGojekCredit);
    this.totalPriceWithGojekCreditBigDecimal = new BigDecimal(this.totalPriceWithGojekCredit);
    return this.gojekCreditBigDecimal.compareTo(this.totalPriceWithGojekCreditBigDecimal) >= 0;
  }
  
  private boolean isGreaterThanZero(String paramString)
  {
    return new BigDecimal(paramString).compareTo(BigDecimal.ZERO) > 0;
  }
  
  private boolean isPaymentTypeCreditCard()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (getSelectedPaymentProvider() != null)
    {
      bool1 = bool2;
      if (2 == getSelectedPaymentProvider().getPayment_provider_type()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private boolean isPaymentTypeGojekCredit()
  {
    return 1 == getSelectedPaymentProvider().getPayment_provider_type();
  }
  
  private boolean isPaymentTypeValid()
  {
    if (((GotixCheckoutPresenter)getPresenter()).isFreeEvent()) {
      return true;
    }
    if ((isPaymentTypeCreditCard()) || (isGojekCreditAccepted())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void loadCustomerData() {}
  
  public static GotixCheckoutFragment newInstance(int paramInt)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("transaction_event_id", paramInt);
    GotixCheckoutFragment localGotixCheckoutFragment = new GotixCheckoutFragment();
    localGotixCheckoutFragment.setArguments(localBundle);
    return localGotixCheckoutFragment;
  }
  
  private void setCreditCardLayoutVisibility(boolean paramBoolean)
  {
    int j = 0;
    Object localObject = this.creditCardLayout;
    if (paramBoolean)
    {
      i = 0;
      ((FrameLayout)localObject).setVisibility(i);
      localObject = this.creditCardLayoutSeparator;
      if (!paramBoolean) {
        break label45;
      }
    }
    label45:
    for (int i = j;; i = 8)
    {
      ((View)localObject).setVisibility(i);
      return;
      i = 8;
      break;
    }
  }
  
  private void setFontFace(View paramView)
  {
    TypefaceHelper.typeface(paramView, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.eventTitlePayments, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.eventTitleTickets, FontFaceHelper.getBebasNeue());
  }
  
  private void setPaymentTypeComponent(boolean paramBoolean)
  {
    setPaymentTypeComponent(paramBoolean, "");
  }
  
  private void setPaymentTypeComponent(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      if (isGreaterThanZero(paramString))
      {
        setPaymentTypeContainerVisibility(true);
        this.paymentTypeTitle.setText(getString(R.string.checkout_credit_surcharge));
        this.paymentTypeValue.setText(formatToStringPercentage(paramString));
        return;
      }
      setPaymentTypeContainerVisibility(false);
      return;
    }
    setPaymentTypeContainerVisibility(true);
    this.paymentTypeTitle.setText(getString(R.string.checkout_payment_gojek_credit));
  }
  
  private void setPaymentTypeContainerVisibility(boolean paramBoolean)
  {
    RelativeLayout localRelativeLayout = this.paymentTypeContainer;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      localRelativeLayout.setVisibility(i);
      return;
    }
  }
  
  private void showDateOfBirthPicker()
  {
    this.dobPickerDialog = getDatePickerDialog(GotixCheckoutFragment..Lambda.10.lambdaFactory$(this));
    this.dobPickerDialog.getDatePicker().setMaxDate(getMilisFromMinimumYear().longValue());
    this.dobPickerDialog.show();
  }
  
  private void showDialogInformationOfDelivery()
  {
    this.parentActivity.runOnUiThread(GotixCheckoutFragment..Lambda.11.lambdaFactory$(this));
  }
  
  private void showGojekCreditPayment()
  {
    if (isGojekCreditAccepted())
    {
      this.paymentTypeValue.setText("- " + GotixUtils.getRupiahFormat(this.totalPriceWithGojekCredit));
      this.totalPriceValue.setText(GotixUtils.getRupiahFormat(0L));
      return;
    }
    String str1 = this.gojekCreditBigDecimal.subtract(this.totalPriceWithGojekCreditBigDecimal).toString();
    String str2 = String.format(getString(R.string.checkout_insufficient_credit_message), new Object[] { GotixUtils.getRupiahFormat(str1) });
    showDialog(getString(R.string.checkout_insufficient_credit_title), str2);
    this.paymentTypeValue.setText("- " + GotixUtils.getRupiahFormat(this.customerGojekCredit));
    this.totalPriceValue.setText(GotixUtils.getRupiahFormat(str1));
  }
  
  private void showPendingDialog()
  {
    if (this.pending)
    {
      this.parentActivity.showDialogTimesUp();
      this.pending = false;
    }
  }
  
  private void showProgressBar()
  {
    this.parentActivity.runOnUiThread(GotixCheckoutFragment..Lambda.6.lambdaFactory$(this));
  }
  
  private void stopCountDownTimer()
  {
    ((GotixCheckoutPresenter)getPresenter()).stopCountDownTimerOfCheckout();
  }
  
  private void updatePurchaseButtonState()
  {
    ((GotixCheckoutPresenter)getPresenter()).updatePurchaseButtonState(this.agreementChecked, this.deliveryChecked, isPaymentTypeValid(), this.dateOfBirthFilled);
  }
  
  public void addSummaryTicketByPrice(StringBuilder paramStringBuilder)
  {
    this.eventTicketItem.setText(Html.fromHtml(paramStringBuilder.toString()));
  }
  
  public void bindDataTimerToView(long paramLong1, long paramLong2)
  {
    this.minutesCountDown.setText(String.format("%1$02d", new Object[] { Long.valueOf(paramLong1) }));
    this.secondCountDown.setText(String.format("%1$02d", new Object[] { Long.valueOf(paramLong2) }));
  }
  
  public void bindSummaryTicketFromTransactionData(String paramString, List<PaymentComponent> paramList, long paramLong)
  {
    this.eventTitle.setText(paramString);
    this.paymentComponentContainer.removeAllViews();
    Stream.of(paramList).filter(GotixCheckoutFragment..Lambda.12.lambdaFactory$(this)).forEach(GotixCheckoutFragment..Lambda.13.lambdaFactory$(this));
    this.descCountDownTimer.setText(String.format(getString(R.string.ticket_released_desc), new Object[] { Long.valueOf(TimeUnit.SECONDS.toMinutes(paramLong)) }));
  }
  
  protected int getLayout()
  {
    return R.layout.fragment_gotix_checkout;
  }
  
  public void hideComponentForFreeEvent()
  {
    this.paymentTypeValue.setVisibility(8);
    this.paymentTypeLayout.setVisibility(8);
    this.paymentTypeSeparator.setVisibility(8);
    this.creditCardLayout.setVisibility(8);
    this.creditCardLayoutSeparator.setVisibility(8);
  }
  
  public void hideProgressBar()
  {
    this.parentActivity.runOnUiThread(GotixCheckoutFragment..Lambda.7.lambdaFactory$(this));
  }
  
  public void hideTicketCheckbox()
  {
    this.ticketCheckGroup.setVisibility(8);
    this.separatorDelivery.setVisibility(8);
  }
  
  public void initPaymentWidget(int paramInt)
  {
    if (this.paymentWidget == null)
    {
      Bundle localBundle = getPaymentBundle(paramInt);
      this.paymentWidget = new FragmentPaymentInfo();
      this.paymentWidget.setArguments(localBundle);
      getFragmentManager().beginTransaction().replace(R.id.credit_card_frame_layout, this.paymentWidget).commit();
    }
  }
  
  public void navigateToReceiptPage(int paramInt)
  {
    navigateToReceiptPage(paramInt, 1);
  }
  
  public void navigateToReceiptPage(int paramInt1, int paramInt2)
  {
    this.parentActivity.closeCurrentFragment();
    this.parentActivity.openReceiptPage(paramInt1, paramInt2);
  }
  
  public void navigateToWaitingPayment(int paramInt, boolean paramBoolean)
  {
    this.parentActivity.closeCurrentFragment();
    this.parentActivity.openWaitingPaymentPage(paramInt, paramBoolean);
  }
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    this.parentActivity = ((GotixEventDetailActivity)getActivity());
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.eventId = getArguments().getInt("transaction_event_id");
    ((GotixCheckoutPresenter)getPresenter()).loadDataTransactionFromInstance(this.eventId);
  }
  
  public void onDestroyView()
  {
    super.onDestroyView();
    this.compositeSubscription.unsubscribe();
    stopCountDownTimer();
    hideKeyboard(this.creditCardLayout);
  }
  
  public void onResume()
  {
    super.onResume();
    showPendingDialog();
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    bindViewById(paramView);
    initCompositeSubscription();
    addListenerOnPaymentSpinner();
    addListenerOnDateOfBirthPicker();
    addClickListeners();
    addCheckedListeners();
    setFontFace(paramView);
    loadCustomerData();
    ((GotixCheckoutPresenter)getPresenter()).loadSummaryOfPurchaseToDynamicView();
    ((GotixCheckoutPresenter)getPresenter()).loadMainDataToView();
  }
  
  public void setAdapterOnPaymentSpinner(List<PaymentProvider> paramList)
  {
    if (paramList != null)
    {
      ArrayAdapter localArrayAdapter = new ArrayAdapter(getContext(), R.layout.item_ticket_purchase_payment, paramList);
      this.paymentTypeSpinner.setAdapter(localArrayAdapter);
      this.paymentTypeSpinner.setSelection(getDefaultPaymentType(paramList).intValue());
    }
  }
  
  public void setButtonPurchaseEnabled(boolean paramBoolean)
  {
    this.parentActivity.runOnUiThread(GotixCheckoutFragment..Lambda.8.lambdaFactory$(this, paramBoolean));
  }
  
  public void setCreditCardSurcharge(String paramString)
  {
    if (paramString != null) {
      this.creditCardSurcharge = paramString;
    }
  }
  
  public void setDateOfBirth()
  {
    this.dateOfBirthPicker.setText(String.format(getString(R.string.checkout_date_of_birth_format), new Object[] { Integer.valueOf(this.fixDayOfMonth), Integer.valueOf(this.fixMonthOfYear + 1), Integer.valueOf(this.fixYear) }));
    this.dateOfBirthFilled = true;
    updatePurchaseButtonState();
  }
  
  public void setTicketCheckboxVisibility(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      showTicketCheckbox();
      return;
    }
    hideTicketCheckbox();
  }
  
  public void setTotalPrice(String paramString1, String paramString2)
  {
    this.totalPriceWithCreditCard = paramString2;
    this.totalPriceWithGojekCredit = paramString1;
    this.totalPriceValue.setText(GotixUtils.getRupiahFormat(paramString2));
  }
  
  public void showDialogNetworkProblem()
  {
    this.parentActivity.onNetworkProblem();
  }
  
  public void showDialogWhenNoInternetConnection()
  {
    this.parentActivity.onNoInternetConnection();
  }
  
  public void showDialogWhenTimesUp()
  {
    this.parentActivity.showDialogTimesUp();
  }
  
  public void showNotificationBasedOnFragmentStatus()
  {
    dismissDateOfBirthPicker();
    if (isResumed())
    {
      showDialogWhenTimesUp();
      return;
    }
    this.pending = true;
    showNotificationRelase();
  }
  
  public void showNotificationRelase()
  {
    this.parentActivity.showNotifRelease();
  }
  
  public void showTicketCheckbox()
  {
    this.ticketCheckGroup.setVisibility(0);
    this.separatorDelivery.setVisibility(0);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/fragments/GotixCheckoutFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */