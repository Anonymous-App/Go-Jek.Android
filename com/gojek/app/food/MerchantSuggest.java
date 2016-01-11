package com.gojek.app.food;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.PickLocation;
import com.gojek.app.SignUp;
import com.gojek.app.SmsVerification;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.MerchantSuggestion;
import com.gojek.app.model.Poi;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.math.BigDecimal;
import org.json.JSONException;
import org.json.JSONObject;

public class MerchantSuggest
  extends GojekActivityBase
{
  private static final String TAG = MerchantSuggest.class.getSimpleName();
  private final int REQUEST_TO = 12;
  private CustomerSaved customerSaved;
  private Gson gson = new Gson();
  private boolean isLocationHistoryLoaded = false;
  private Context mContext;
  private EditText mETMerchantLocation;
  private EditText mETMerchantName;
  private EditText mETMerchantPhoneNumber;
  private ImageView mIVNext;
  private LinearLayout mLLPickLocationTo;
  private Poi mPoiTo = new Poi();
  private ProgressBar mProgress;
  private TextView mTVMerchantGuidance;
  private TextView mTVPickLocationTo;
  private VolleyClient volleyClient;
  
  private static void applySpan(SpannableString paramSpannableString, String paramString, ClickableSpan paramClickableSpan)
  {
    int i = paramSpannableString.toString().indexOf(paramString);
    paramSpannableString.setSpan(paramClickableSpan, i, i + paramString.length(), 33);
  }
  
  private void init()
  {
    this.mContext = this;
    this.customerSaved = new CustomerSaved(this);
    this.volleyClient = VolleyClient.getInstance(this);
    setTitle(2131165674);
    this.mIVNext.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MerchantSuggest.this.sendSuggestion();
      }
    });
    this.mLLPickLocationTo.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(MerchantSuggest.this.getApplicationContext(), PickLocation.class);
        if (MerchantSuggest.this.isLocationHistoryLoaded) {
          paramAnonymousView.putExtra("CACHE_LOCATION_HISTORY", true);
        }
        MerchantSuggest.this.startActivityForResult(paramAnonymousView, 12);
        if (!MerchantSuggest.this.isLocationHistoryLoaded) {
          MerchantSuggest.access$202(MerchantSuggest.this, true);
        }
      }
    });
  }
  
  private void initView()
  {
    setContentView(2130968782);
    this.mETMerchantName = ((EditText)findViewById(2131624856));
    this.mLLPickLocationTo = ((LinearLayout)findViewById(2131624548));
    this.mTVPickLocationTo = ((TextView)findViewById(2131624549));
    this.mETMerchantLocation = ((EditText)findViewById(2131624858));
    this.mETMerchantPhoneNumber = ((EditText)findViewById(2131624860));
    this.mTVMerchantGuidance = ((TextView)findViewById(2131624861));
    Object localObject2 = getResources().getString(2131165669);
    final Object localObject1 = getResources().getString(2131165670);
    localObject2 = SpannableString.valueOf((String)localObject2 + " " + (String)localObject1);
    applySpan((SpannableString)localObject2, (String)localObject1, new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.SEND");
        paramAnonymousView.setType("plain/text");
        paramAnonymousView.putExtra("android.intent.extra.EMAIL", new String[] { localObject1 });
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("I am a Restaurant Owner ");
        if ((MerchantSuggest.this.mETMerchantName.getText() != null) && (MerchantSuggest.this.mETMerchantName.getText().length() > 0)) {
          localStringBuffer.append("of " + MerchantSuggest.this.mETMerchantName.getText());
        }
        paramAnonymousView.putExtra("android.intent.extra.SUBJECT", localStringBuffer.toString());
        paramAnonymousView.putExtra("android.intent.extra.TEXT", "");
        MerchantSuggest.this.startActivity(Intent.createChooser(paramAnonymousView, "Send mail..."));
      }
    });
    this.mTVMerchantGuidance.setText((CharSequence)localObject2);
    this.mTVMerchantGuidance.setMovementMethod(LinkMovementMethod.getInstance());
    localObject1 = new TextView(this);
    ((TextView)localObject1).setText((CharSequence)localObject2);
    ((TextView)localObject1).setMovementMethod(LinkMovementMethod.getInstance());
    this.mIVNext = ((ImageView)findViewById(2131624558));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
  }
  
  private void sendSuggestion()
  {
    if (this.customerSaved.customerId == null) {
      if (this.customerSaved.email == null)
      {
        localObject1 = new Intent(this, SignUp.class);
        startActivity((Intent)localObject1);
      }
    }
    while (!validateSuggestion()) {
      for (;;)
      {
        return;
        localObject1 = new Intent(this, SmsVerification.class);
      }
    }
    Object localObject1 = new MerchantSuggestion();
    ((MerchantSuggestion)localObject1).customerId = Integer.parseInt(this.customerSaved.customerId);
    ((MerchantSuggestion)localObject1).merchantName = this.mETMerchantName.getText().toString();
    ((MerchantSuggestion)localObject1).merchantAddress = this.mPoiTo.address;
    ((MerchantSuggestion)localObject1).merchantAddressDetail = this.mETMerchantLocation.getText().toString();
    ((MerchantSuggestion)localObject1).merchantLocationName = this.mPoiTo.name;
    ((MerchantSuggestion)localObject1).added = false;
    ((MerchantSuggestion)localObject1).poiType = 2;
    ((MerchantSuggestion)localObject1).doneStatus = null;
    ((MerchantSuggestion)localObject1).doneBy = null;
    ((MerchantSuggestion)localObject1).phone = this.mETMerchantPhoneNumber.getText().toString();
    ((MerchantSuggestion)localObject1).latitude = BigDecimal.valueOf(this.mPoiTo.latitude);
    ((MerchantSuggestion)localObject1).longitude = BigDecimal.valueOf(this.mPoiTo.longitude);
    ((MerchantSuggestion)localObject1).suggestionTime = "";
    ((MerchantSuggestion)localObject1).addedTime = null;
    localObject3 = null;
    for (;;)
    {
      try
      {
        localGson = this.gson;
        if ((localGson instanceof Gson)) {
          continue;
        }
        localObject1 = localGson.toJson(localObject1);
        localObject1 = JSONObjectInstrumentation.init((String)localObject1);
      }
      catch (JSONException localJSONException)
      {
        Gson localGson;
        localJSONException.printStackTrace();
        Object localObject2 = localObject3;
        continue;
      }
      this.mProgress.setVisibility(0);
      this.mIVNext.setVisibility(8);
      this.volleyClient.put("https://api.gojek.co.id/gojek/merchant-suggestion/", (JSONObject)localObject1, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(MerchantSuggest.TAG, "sendSuggestion() error : " + paramAnonymousVolleyError);
          if (!(paramAnonymousVolleyError instanceof AuthFailureError))
          {
            paramAnonymousVolleyError = MerchantSuggest.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
            MerchantSuggest.this.showSimpleDialog(null, MerchantSuggest.this.getString(2131165675) + "\n " + paramAnonymousVolleyError, null, true, null);
          }
          for (;;)
          {
            MerchantSuggest.this.mProgress.setVisibility(8);
            MerchantSuggest.this.mIVNext.setVisibility(0);
            Util.hideSoftKeyboard(MerchantSuggest.this.mContext, MerchantSuggest.this.mETMerchantLocation);
            Util.resetTextInput(new EditText[] { MerchantSuggest.this.mETMerchantName, MerchantSuggest.this.mETMerchantLocation, MerchantSuggest.this.mETMerchantPhoneNumber });
            return;
            MerchantSuggest.this.isUnauthorizedApiAccess();
          }
        }
        
        public void onResponse(Integer paramAnonymousInteger)
        {
          MerchantSuggest.this.mProgress.setVisibility(8);
          MerchantSuggest.this.mIVNext.setVisibility(0);
          MerchantSuggest.this.showSimpleDialog(null, MerchantSuggest.this.getString(2131165828), null, true, null);
          Util.hideSoftKeyboard(MerchantSuggest.this.mContext, MerchantSuggest.this.mETMerchantLocation);
          MerchantSuggest.this.mTVPickLocationTo.setText("");
          Util.resetTextInput(new EditText[] { MerchantSuggest.this.mETMerchantName, MerchantSuggest.this.mETMerchantLocation, MerchantSuggest.this.mETMerchantPhoneNumber });
        }
      }, Integer.class, this.customerSaved.getAccessToken());
      return;
      localObject1 = GsonInstrumentation.toJson((Gson)localGson, localObject1);
    }
  }
  
  private boolean validateSuggestion()
  {
    boolean bool = true;
    StringBuilder localStringBuilder = new StringBuilder();
    if (TextUtils.isEmpty(this.mETMerchantName.getText().toString())) {
      localStringBuilder.append(getString(2131165561).toLowerCase());
    }
    if (TextUtils.isEmpty(this.mTVPickLocationTo.getText().toString()))
    {
      if (!localStringBuilder.toString().isEmpty()) {
        localStringBuilder.append("\n");
      }
      localStringBuilder.append(getString(2131165559).toLowerCase());
    }
    if (TextUtils.isEmpty(this.mETMerchantLocation.getText().toString()))
    {
      if (!localStringBuilder.toString().isEmpty()) {
        localStringBuilder.append("\n");
      }
      localStringBuilder.append(getString(2131165560).toLowerCase());
    }
    if (TextUtils.isEmpty(this.mETMerchantPhoneNumber.getText().toString()))
    {
      if (!localStringBuilder.toString().isEmpty()) {
        localStringBuilder.append("\n");
      }
      localStringBuilder.append(getString(2131165562).toLowerCase());
    }
    if (!localStringBuilder.toString().equals(""))
    {
      showSimpleDialog(null, getString(2131165734) + "\n" + localStringBuilder.toString(), "OK", true, null);
      bool = false;
    }
    return bool;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 12) && (paramInt2 == -1))
    {
      this.mPoiTo = new Poi(paramIntent.getIntExtra(PickLocation.LOCATION_ID, 0), paramIntent.getStringExtra(PickLocation.LOCATION_NAME), paramIntent.getDoubleExtra(PickLocation.LOCATION_LAT, 0.0D), paramIntent.getDoubleExtra(PickLocation.LOCATION_LNG, 0.0D), paramIntent.getStringExtra(PickLocation.LOCATION_ADDRESS));
      this.mTVPickLocationTo.setText(this.mPoiTo.name);
      String str = paramIntent.getStringExtra(PickLocation.LOCATION_DESC);
      EditText localEditText = this.mETMerchantLocation;
      paramIntent = str;
      if (str == null) {
        paramIntent = "";
      }
      localEditText.setText(paramIntent);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    initView();
    init();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  protected void onResume()
  {
    this.customerSaved = new CustomerSaved(this);
    super.onResume();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/MerchantSuggest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */