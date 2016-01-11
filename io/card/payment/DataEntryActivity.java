package io.card.payment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DateKeyListener;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import io.card.payment.i18n.LocalizedStrings;
import io.card.payment.i18n.StringKey;
import io.card.payment.ui.ActivityHelper;
import io.card.payment.ui.Appearance;
import io.card.payment.ui.ViewUtil;

@Instrumented
public final class DataEntryActivity
  extends Activity
  implements TextWatcher, TraceFieldInterface
{
  private final String TAG = getClass().getName();
  private TextView activityTitleTextView;
  private boolean autoAcceptDone;
  private Button cancelBtn;
  private CreditCard capture;
  private ImageView cardView;
  private EditText cvvEdit;
  private Validator cvvValidator;
  private int defaultTextColor;
  private Button doneBtn;
  private int editTextIdCounter = 100;
  private EditText expiryEdit;
  private Validator expiryValidator;
  private String labelLeftPadding;
  private EditText numberEdit;
  private Validator numberValidator;
  private EditText postalCodeEdit;
  private Validator postalCodeValidator;
  private boolean useApplicationTheme;
  private int viewIdCounter = 1;
  
  private EditText advanceToNextEmptyField()
  {
    int i = 100;
    for (;;)
    {
      EditText localEditText = (EditText)findViewById(i);
      if (localEditText != null)
      {
        if ((localEditText.getText().length() == 0) && (localEditText.requestFocus())) {
          return localEditText;
        }
      }
      else {
        return null;
      }
      i += 1;
    }
  }
  
  private void completed()
  {
    if (this.capture == null) {
      this.capture = new CreditCard();
    }
    if (this.expiryEdit != null)
    {
      this.capture.expiryMonth = ((ExpiryValidator)this.expiryValidator).month;
      this.capture.expiryYear = ((ExpiryValidator)this.expiryValidator).year;
    }
    CreditCard localCreditCard = new CreditCard(this.numberValidator.getValue(), this.capture.expiryMonth, this.capture.expiryYear, this.cvvValidator.getValue(), this.postalCodeValidator.getValue());
    Intent localIntent = new Intent();
    localIntent.putExtra("io.card.payment.scanResult", localCreditCard);
    setResult(CardIOActivity.RESULT_CARD_INFO, localIntent);
    finish();
  }
  
  private void setDefaultColor(EditText paramEditText)
  {
    if (this.useApplicationTheme)
    {
      paramEditText.setTextColor(this.defaultTextColor);
      return;
    }
    paramEditText.setTextColor(-12303292);
  }
  
  private void validateAndEnableDoneButtonIfValid()
  {
    Button localButton = this.doneBtn;
    if ((this.numberValidator.isValid()) && (this.expiryValidator.isValid()) && (this.cvvValidator.isValid()) && (this.postalCodeValidator.isValid())) {}
    for (boolean bool = true;; bool = false)
    {
      localButton.setEnabled(bool);
      Log.d(this.TAG, "setting doneBtn.enabled=" + this.doneBtn.isEnabled());
      if ((this.autoAcceptDone) && (this.numberValidator.isValid()) && (this.expiryValidator.isValid()) && (this.cvvValidator.isValid()) && (this.postalCodeValidator.isValid())) {
        completed();
      }
      return;
    }
  }
  
  public void afterTextChanged(Editable paramEditable)
  {
    if ((this.numberEdit != null) && (paramEditable == this.numberEdit.getText())) {
      if (this.numberValidator.hasFullLength()) {
        if (!this.numberValidator.isValid())
        {
          this.numberEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
          if (this.cvvEdit != null)
          {
            paramEditable = CardType.fromCardNumber(this.numberValidator.getValue().toString());
            Object localObject = (FixedLengthValidator)this.cvvValidator;
            int i = paramEditable.cvvLength();
            ((FixedLengthValidator)localObject).requiredLength = i;
            localObject = this.cvvEdit;
            if (i != 4) {
              break label143;
            }
            paramEditable = "1234";
            label106:
            ((EditText)localObject).setHint(paramEditable);
          }
        }
      }
    }
    for (;;)
    {
      validateAndEnableDoneButtonIfValid();
      return;
      setDefaultColor(this.numberEdit);
      advanceToNextEmptyField();
      break;
      setDefaultColor(this.numberEdit);
      break;
      label143:
      paramEditable = "123";
      break label106;
      if ((this.expiryEdit != null) && (paramEditable == this.expiryEdit.getText()))
      {
        if (this.expiryValidator.hasFullLength())
        {
          if (!this.expiryValidator.isValid())
          {
            this.expiryEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
          }
          else
          {
            setDefaultColor(this.expiryEdit);
            advanceToNextEmptyField();
          }
        }
        else {
          setDefaultColor(this.expiryEdit);
        }
      }
      else if ((this.cvvEdit != null) && (paramEditable == this.cvvEdit.getText()))
      {
        if (this.cvvValidator.hasFullLength())
        {
          if (!this.cvvValidator.isValid())
          {
            this.cvvEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
          }
          else
          {
            setDefaultColor(this.cvvEdit);
            advanceToNextEmptyField();
          }
        }
        else {
          setDefaultColor(this.cvvEdit);
        }
      }
      else if ((this.postalCodeEdit != null) && (paramEditable == this.postalCodeEdit.getText())) {
        if (this.postalCodeValidator.hasFullLength())
        {
          if (!this.postalCodeValidator.isValid())
          {
            this.postalCodeEdit.setTextColor(Appearance.TEXT_COLOR_ERROR);
          }
          else
          {
            setDefaultColor(this.postalCodeEdit);
            advanceToNextEmptyField();
          }
        }
        else {
          setDefaultColor(this.postalCodeEdit);
        }
      }
    }
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  protected void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("DataEntryActivity");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "DataEntryActivity#onCreate", null);
      super.onCreate(paramBundle);
      Log.v(this.TAG, "onCreate");
      if (getIntent().getExtras() == null)
      {
        onBackPressed();
        TraceMachine.exitMethod();
        return;
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "DataEntryActivity#onCreate", null);
      }
      this.useApplicationTheme = getIntent().getBooleanExtra("io.card.payment.keepApplicationTheme", false);
      ActivityHelper.setActivityTheme(this, this.useApplicationTheme);
      this.defaultTextColor = new TextView(this).getTextColors().getDefaultColor();
      if (!ActivityHelper.holoSupported()) {
        break label1798;
      }
    }
    paramBundle = "12dip";
    this.labelLeftPadding = paramBundle;
    LocalizedStrings.setLanguage(getIntent());
    int j = ViewUtil.typedDimensionValueToPixelsInt("4dip", this);
    RelativeLayout localRelativeLayout = new RelativeLayout(this);
    if (!this.useApplicationTheme) {
      localRelativeLayout.setBackgroundColor(Appearance.DEFAULT_BACKGROUND_COLOR);
    }
    paramBundle = new ScrollView(this);
    int i = this.viewIdCounter;
    this.viewIdCounter = (i + 1);
    paramBundle.setId(i);
    Object localObject2 = new RelativeLayout.LayoutParams(-1, -2);
    ((RelativeLayout.LayoutParams)localObject2).addRule(10);
    localRelativeLayout.addView(paramBundle, (ViewGroup.LayoutParams)localObject2);
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    localLinearLayout1.setOrientation(1);
    paramBundle.addView(localLinearLayout1, -1, -1);
    LinearLayout localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setOrientation(1);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -1);
    this.capture = ((CreditCard)getIntent().getParcelableExtra("io.card.payment.scanResult"));
    this.autoAcceptDone = getIntent().getBooleanExtra("debug_autoAcceptResult", false);
    label391:
    LinearLayout localLinearLayout3;
    LinearLayout.LayoutParams localLayoutParams2;
    boolean bool1;
    Object localObject1;
    if (this.capture != null)
    {
      this.numberValidator = new CardNumberValidator(this.capture.cardNumber);
      this.cardView = new ImageView(this);
      paramBundle = new LinearLayout.LayoutParams(-1, -2);
      this.cardView.setPadding(0, 0, 0, j);
      paramBundle.weight = 1.0F;
      this.cardView.setImageBitmap(CardIOActivity.markedCardImage);
      localLinearLayout2.addView(this.cardView, paramBundle);
      ViewUtil.setMargins(this.cardView, null, null, null, "8dip");
      localLinearLayout3 = new LinearLayout(this);
      localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
      ViewUtil.setPadding(localLinearLayout3, null, "4dip", null, null);
      localLinearLayout3.setOrientation(0);
      bool1 = getIntent().getBooleanExtra("io.card.payment.requireExpiry", false);
      boolean bool2 = getIntent().getBooleanExtra("io.card.payment.requireCVV", false);
      boolean bool3 = getIntent().getBooleanExtra("io.card.payment.requirePostalCode", false);
      if (!bool1) {
        break label2145;
      }
      localObject1 = new LinearLayout(this);
      paramBundle = new LinearLayout.LayoutParams(0, -1, 1.0F);
      ((LinearLayout)localObject1).setOrientation(1);
      Object localObject3 = new TextView(this);
      if (!this.useApplicationTheme) {
        ((TextView)localObject3).setTextColor(Appearance.TEXT_COLOR_LABEL);
      }
      ((TextView)localObject3).setText(LocalizedStrings.getString(StringKey.ENTRY_EXPIRES));
      ViewUtil.setPadding((View)localObject3, this.labelLeftPadding, null, null, null);
      ((LinearLayout)localObject1).addView((View)localObject3, -2, -2);
      this.expiryEdit = new EditText(this);
      localObject3 = this.expiryEdit;
      i = this.editTextIdCounter;
      this.editTextIdCounter = (i + 1);
      ((EditText)localObject3).setId(i);
      this.expiryEdit.setMaxLines(1);
      this.expiryEdit.setImeOptions(6);
      this.expiryEdit.setTextAppearance(getApplicationContext(), 16842816);
      this.expiryEdit.setInputType(3);
      this.expiryEdit.setHint(LocalizedStrings.getString(StringKey.EXPIRES_PLACEHOLDER));
      if (this.capture == null) {
        break label2126;
      }
      this.expiryValidator = new ExpiryValidator(this.capture.expiryMonth, this.capture.expiryYear);
      label680:
      if (this.expiryValidator.hasFullLength()) {
        this.expiryEdit.setText(this.expiryValidator.getValue());
      }
      this.expiryEdit.addTextChangedListener(this.expiryValidator);
      this.expiryEdit.addTextChangedListener(this);
      this.expiryEdit.setFilters(new InputFilter[] { new DateKeyListener(), this.expiryValidator });
      ((LinearLayout)localObject1).addView(this.expiryEdit, -1, -2);
      localLinearLayout3.addView((View)localObject1, paramBundle);
      if ((!bool2) && (!bool3)) {
        break label2140;
      }
      paramBundle = "4dip";
      label789:
      ViewUtil.setMargins((View)localObject1, null, null, paramBundle, null);
      label798:
      if (!bool2) {
        break label2170;
      }
      localObject3 = new LinearLayout(this);
      paramBundle = new LinearLayout.LayoutParams(0, -1, 1.0F);
      ((LinearLayout)localObject3).setOrientation(1);
      localObject1 = new TextView(this);
      if (!this.useApplicationTheme) {
        ((TextView)localObject1).setTextColor(Appearance.TEXT_COLOR_LABEL);
      }
      ViewUtil.setPadding((View)localObject1, this.labelLeftPadding, null, null, null);
      ((TextView)localObject1).setText(LocalizedStrings.getString(StringKey.ENTRY_CVV));
      ((LinearLayout)localObject3).addView((View)localObject1, -2, -2);
      this.cvvEdit = new EditText(this);
      localObject1 = this.cvvEdit;
      i = this.editTextIdCounter;
      this.editTextIdCounter = (i + 1);
      ((EditText)localObject1).setId(i);
      this.cvvEdit.setMaxLines(1);
      this.cvvEdit.setImeOptions(6);
      this.cvvEdit.setTextAppearance(getApplicationContext(), 16842816);
      this.cvvEdit.setInputType(3);
      this.cvvEdit.setHint("123");
      i = 4;
      if (this.capture != null) {
        i = CardType.fromCardNumber(this.numberValidator.getValue()).cvvLength();
      }
      this.cvvValidator = new FixedLengthValidator(i);
      this.cvvEdit.setFilters(new InputFilter[] { new DigitsKeyListener(), this.cvvValidator });
      this.cvvEdit.addTextChangedListener(this.cvvValidator);
      this.cvvEdit.addTextChangedListener(this);
      ((LinearLayout)localObject3).addView(this.cvvEdit, -1, -2);
      localLinearLayout3.addView((View)localObject3, paramBundle);
      if (!bool1) {
        break label2159;
      }
      paramBundle = "4dip";
      label1086:
      if (!bool3) {
        break label2164;
      }
      localObject1 = "4dip";
      label1096:
      ViewUtil.setMargins((View)localObject3, paramBundle, null, (String)localObject1, null);
      label1106:
      if (!bool3) {
        break label2189;
      }
      localObject1 = new LinearLayout(this);
      paramBundle = new LinearLayout.LayoutParams(0, -1, 1.0F);
      ((LinearLayout)localObject1).setOrientation(1);
      localObject3 = new TextView(this);
      if (!this.useApplicationTheme) {
        ((TextView)localObject3).setTextColor(Appearance.TEXT_COLOR_LABEL);
      }
      ViewUtil.setPadding((View)localObject3, this.labelLeftPadding, null, null, null);
      ((TextView)localObject3).setText(LocalizedStrings.getString(StringKey.ENTRY_POSTAL_CODE));
      ((LinearLayout)localObject1).addView((View)localObject3, -2, -2);
      this.postalCodeEdit = new EditText(this);
      localObject3 = this.postalCodeEdit;
      i = this.editTextIdCounter;
      this.editTextIdCounter = (i + 1);
      ((EditText)localObject3).setId(i);
      this.postalCodeEdit.setMaxLines(1);
      this.postalCodeEdit.setImeOptions(6);
      this.postalCodeEdit.setTextAppearance(getApplicationContext(), 16842816);
      this.postalCodeEdit.setInputType(1);
      this.postalCodeValidator = new MaxLengthValidator(20);
      this.postalCodeEdit.addTextChangedListener(this.postalCodeValidator);
      this.postalCodeEdit.addTextChangedListener(this);
      ((LinearLayout)localObject1).addView(this.postalCodeEdit, -1, -2);
      localLinearLayout3.addView((View)localObject1, paramBundle);
      if ((!bool1) && (!bool2)) {
        break label2184;
      }
      paramBundle = "4dip";
      label1338:
      ViewUtil.setMargins((View)localObject1, paramBundle, null, null, null);
    }
    for (;;)
    {
      localLinearLayout2.addView(localLinearLayout3, localLayoutParams2);
      localLinearLayout1.addView(localLinearLayout2, localLayoutParams1);
      ViewUtil.setMargins(localLinearLayout2, "16dip", "20dip", "16dip", "20dip");
      paramBundle = new LinearLayout(this);
      i = this.viewIdCounter;
      this.viewIdCounter = (i + 1);
      paramBundle.setId(i);
      localObject1 = new RelativeLayout.LayoutParams(-1, -2);
      ((RelativeLayout.LayoutParams)localObject1).addRule(12);
      paramBundle.setPadding(0, j, 0, 0);
      paramBundle.setBackgroundColor(0);
      ((RelativeLayout.LayoutParams)localObject2).addRule(2, paramBundle.getId());
      this.doneBtn = new Button(this);
      localObject2 = new LinearLayout.LayoutParams(-1, -2, 1.0F);
      this.doneBtn.setText(LocalizedStrings.getString(StringKey.DONE));
      this.doneBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DataEntryActivity.this.completed();
        }
      });
      this.doneBtn.setEnabled(false);
      paramBundle.addView(this.doneBtn, (ViewGroup.LayoutParams)localObject2);
      ViewUtil.styleAsButton(this.doneBtn, true, this);
      ViewUtil.setPadding(this.doneBtn, "5dip", null, "5dip", null);
      ViewUtil.setMargins(this.doneBtn, "8dip", "8dip", "4dip", "8dip");
      this.doneBtn.setTextSize(16.0F);
      this.cancelBtn = new Button(this);
      localObject2 = new LinearLayout.LayoutParams(-1, -2, 1.0F);
      this.cancelBtn.setText(LocalizedStrings.getString(StringKey.CANCEL));
      this.cancelBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DataEntryActivity.this.setResult(CardIOActivity.RESULT_ENTRY_CANCELED);
          DataEntryActivity.this.finish();
        }
      });
      paramBundle.addView(this.cancelBtn, (ViewGroup.LayoutParams)localObject2);
      ViewUtil.styleAsButton(this.cancelBtn, false, this);
      ViewUtil.setPadding(this.cancelBtn, "5dip", null, "5dip", null);
      ViewUtil.setMargins(this.cancelBtn, "4dip", "8dip", "8dip", "8dip");
      this.cancelBtn.setTextSize(16.0F);
      localRelativeLayout.addView(paramBundle, (ViewGroup.LayoutParams)localObject1);
      ActivityHelper.addActionBarIfSupported(this);
      setContentView(localRelativeLayout);
      paramBundle = null;
      if (getIntent().getBooleanExtra("io.card.payment.intentSenderIsPayPal", true))
      {
        paramBundle = ViewUtil.base64ToBitmap("iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNDMzRTRFQ0M2MjQxMUUzOURBQ0E3QTY0NjU3OUI5QiIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpCNDMzRTRFREM2MjQxMUUzOURBQ0E3QTY0NjU3OUI5QiI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOkI0MzNFNEVBQzYyNDExRTM5REFDQTdBNjQ2NTc5QjlCIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOkI0MzNFNEVCQzYyNDExRTM5REFDQTdBNjQ2NTc5QjlCIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+Eyd0MQAABoFJREFUeNrMWl1M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", this, 240);
        paramBundle = new BitmapDrawable(getResources(), paramBundle);
      }
      if ((bool1) && (this.expiryValidator.isValid())) {
        afterTextChanged(this.expiryEdit.getEditableText());
      }
      ActivityHelper.setupActionBarIfSupported(this, this.activityTitleTextView, LocalizedStrings.getString(StringKey.MANUAL_ENTRY_TITLE), "card.io - ", paramBundle);
      TraceMachine.exitMethod();
      return;
      label1798:
      paramBundle = "2dip";
      break;
      this.activityTitleTextView = new TextView(this);
      this.activityTitleTextView.setTextSize(24.0F);
      if (!this.useApplicationTheme) {
        this.activityTitleTextView.setTextColor(Appearance.PAY_BLUE_COLOR);
      }
      localLinearLayout2.addView(this.activityTitleTextView);
      ViewUtil.setPadding(this.activityTitleTextView, null, null, null, "8dip");
      ViewUtil.setDimensions(this.activityTitleTextView, -2, -2);
      paramBundle = new LinearLayout(this);
      paramBundle.setOrientation(1);
      ViewUtil.setPadding(paramBundle, null, "4dip", null, "4dip");
      localObject1 = new TextView(this);
      ViewUtil.setPadding((View)localObject1, this.labelLeftPadding, null, null, null);
      ((TextView)localObject1).setText(LocalizedStrings.getString(StringKey.ENTRY_CARD_NUMBER));
      if (!this.useApplicationTheme) {
        ((TextView)localObject1).setTextColor(Appearance.TEXT_COLOR_LABEL);
      }
      paramBundle.addView((View)localObject1, -2, -2);
      this.numberEdit = new EditText(this);
      localObject1 = this.numberEdit;
      i = this.editTextIdCounter;
      this.editTextIdCounter = (i + 1);
      ((EditText)localObject1).setId(i);
      this.numberEdit.setMaxLines(1);
      this.numberEdit.setImeOptions(6);
      this.numberEdit.setTextAppearance(getApplicationContext(), 16842816);
      this.numberEdit.setInputType(3);
      this.numberEdit.setHint("1234 5678 1234 5678");
      this.numberValidator = new CardNumberValidator();
      this.numberEdit.addTextChangedListener(this.numberValidator);
      this.numberEdit.addTextChangedListener(this);
      this.numberEdit.setFilters(new InputFilter[] { new DigitsKeyListener(), this.numberValidator });
      paramBundle.addView(this.numberEdit, -1, -2);
      localLinearLayout2.addView(paramBundle, -1, -1);
      break label391;
      label2126:
      this.expiryValidator = new ExpiryValidator();
      break label680;
      label2140:
      paramBundle = null;
      break label789;
      label2145:
      this.expiryValidator = new AlwaysValid();
      break label798;
      label2159:
      paramBundle = null;
      break label1086;
      label2164:
      localObject1 = null;
      break label1096;
      label2170:
      this.cvvValidator = new AlwaysValid();
      break label1106;
      label2184:
      paramBundle = null;
      break label1338;
      label2189:
      this.postalCodeValidator = new AlwaysValid();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    Log.d(this.TAG, "onResume()");
    getWindow().setFlags(0, 1024);
    ActivityHelper.setFlagSecure(this);
    validateAndEnableDoneButtonIfValid();
    if ((this.numberEdit == null) && (this.expiryEdit != null) && (!this.expiryValidator.isValid())) {
      this.expiryEdit.requestFocus();
    }
    for (;;)
    {
      if ((this.numberEdit != null) || (this.expiryEdit != null) || (this.cvvEdit != null) || (this.postalCodeEdit != null)) {
        getWindow().setSoftInputMode(5);
      }
      Log.i(this.TAG, "ready for manual entry");
      return;
      advanceToNextEmptyField();
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/DataEntryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */