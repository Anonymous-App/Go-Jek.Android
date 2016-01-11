package com.gojek.app;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.facebook.appevents.AppEventsLogger;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.CustomerVerificationResponse;
import com.gojek.app.model.OauthTokenResponse;
import com.gojek.app.model.SignUpResponse;
import com.gojek.app.util.Util;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mixpanel.android.mpmetrics.MixpanelAPI.People;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class SmsVerification
  extends GojekActivityBase
{
  public static final String TAG = SmsVerification.class.getSimpleName();
  private CustomerSaved mCustomerSaved;
  private EditText mETVerificationCode;
  private ImageView mIVSubmit;
  private LinearLayout mLLResendSms;
  private ProgressBar mProgress;
  private TextView mTVResendSms;
  private ProgressDialog progressDialog;
  private AppEventsLogger verifyFBLogger;
  private VolleyClient volleyClient;
  
  private void doResendSms()
  {
    this.progressDialog = ProgressDialog.show(this, "", "Resend SMS...", true, true);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCanceledOnTouchOutside(false);
    this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        SmsVerification.this.finish();
      }
    });
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("phone", this.mCustomerSaved.phone);
      localJSONObject.putOpt("email", this.mCustomerSaved.email);
      localJSONObject.putOpt("confirmationPassword", this.mCustomerSaved.password);
      localJSONObject.putOpt("password", this.mCustomerSaved.password);
      localJSONObject.putOpt("name", this.mCustomerSaved.name);
      String str3 = TAG;
      StringBuilder localStringBuilder = new StringBuilder().append("resend sms using signup https://api.gojek.co.id/gojek/customer/v2/signup with json ");
      if (!(localJSONObject instanceof JSONObject))
      {
        String str1 = localJSONObject.toString();
        Log.i(str3, str1);
        this.volleyClient.post("https://api.gojek.co.id/gojek/customer/v2/signup", localJSONObject, new JsonCallback()
        {
          public void onError(VolleyError paramAnonymousVolleyError)
          {
            Log.e(SmsVerification.TAG, "error resend sms " + paramAnonymousVolleyError);
            SmsVerification.this.progressDialogDismiss();
            paramAnonymousVolleyError = SmsVerification.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
            SmsVerification.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
          }
          
          public void onResponse(SignUpResponse paramAnonymousSignUpResponse)
          {
            SmsVerification.this.progressDialogDismiss();
            Log.i(SmsVerification.TAG, "response " + paramAnonymousSignUpResponse.toString());
            if (paramAnonymousSignUpResponse != null)
            {
              paramAnonymousSignUpResponse = String.format(SmsVerification.this.getString(2131165802), new Object[] { SmsVerification.this.mCustomerSaved.phone });
              SmsVerification.this.showSimpleDialog(null, paramAnonymousSignUpResponse, null, true, null);
            }
          }
        }, SignUpResponse.class);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
        continue;
        String str2 = JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
      }
    }
  }
  
  private void init()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    this.volleyClient = VolleyClient.getInstance(this);
    this.mIVSubmit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SmsVerification.this.validation()) {
          SmsVerification.this.verifyCustomer();
        }
      }
    });
    this.mLLResendSms.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SmsVerification.this.resendSmsCode();
      }
    });
  }
  
  private void renderView()
  {
    setContentView(2130968832);
    this.mETVerificationCode = ((EditText)findViewById(2131625020));
    this.mIVSubmit = ((ImageView)findViewById(2131624966));
    this.mLLResendSms = ((LinearLayout)findViewById(2131625022));
    this.mTVResendSms = ((TextView)findViewById(2131625023));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
  }
  
  private void resendSmsCode()
  {
    Util.confirmDialog(this, new Handler()new Handler
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        SmsVerification.this.doResendSms();
      }
    }, new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        paramAnonymousMessage = new Intent(SmsVerification.this, SmsResend.class);
        SmsVerification.this.startActivity(paramAnonymousMessage);
      }
    }, String.format(getString(2131165804), new Object[] { this.mCustomerSaved.phone }), "Resend SMS", "Yes", "No");
  }
  
  private boolean validation()
  {
    String str1 = this.mETVerificationCode.getText().toString();
    String str2 = getString(2131165566);
    if (TextUtils.isEmpty(str1))
    {
      Toast.makeText(this, String.format(Locale.US, str2, new Object[] { "Verification Code" }), 0).show();
      return false;
    }
    return true;
  }
  
  private void verifyCustomer()
  {
    this.mIVSubmit.setVisibility(8);
    this.mProgress.setVisibility(0);
    final String str3 = this.mETVerificationCode.getText().toString();
    String str1 = this.mCustomerSaved.phone;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("phone", str1);
      localJSONObject.putOpt("verificationCode", str3);
      String str4 = TAG;
      StringBuilder localStringBuilder = new StringBuilder().append("verification user to https://api.gojek.co.id/gojek/customer/verification with payload ");
      if (!(localJSONObject instanceof JSONObject))
      {
        str1 = localJSONObject.toString();
        Log.i(str4, str1);
        this.volleyClient.post("https://api.gojek.co.id/gojek/customer/verification", localJSONObject, new JsonCallback()
        {
          public void onError(VolleyError paramAnonymousVolleyError)
          {
            Log.e(SmsVerification.TAG, "error verification sms " + paramAnonymousVolleyError);
            SmsVerification.this.mProgress.setVisibility(8);
            SmsVerification.this.mIVSubmit.setVisibility(0);
            paramAnonymousVolleyError = SmsVerification.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
            SmsVerification.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
          }
          
          public void onResponse(CustomerVerificationResponse paramAnonymousCustomerVerificationResponse)
          {
            Log.i(SmsVerification.TAG, "customer verification " + paramAnonymousCustomerVerificationResponse.toString());
            if (paramAnonymousCustomerVerificationResponse.getCustomerId() != null)
            {
              SmsVerification.this.mCustomerSaved.customerId = String.valueOf(paramAnonymousCustomerVerificationResponse.getCustomerId());
              SmsVerification.this.mCustomerSaved.verifyUser(SmsVerification.this.mCustomerSaved.customerId, str3);
              SmsVerification.this.doRequestToken();
              SmsVerification.this.verifyFBLogger.logEvent(SmsVerification.this.getString(2131165554));
              return;
            }
            SmsVerification.this.verifyFBLogger.logEvent(SmsVerification.this.getString(2131165553));
            SmsVerification.this.mProgress.setVisibility(8);
            SmsVerification.this.mIVSubmit.setVisibility(0);
            SmsVerification.this.showSimpleDialog(null, paramAnonymousCustomerVerificationResponse.getStatus(), null, true, null);
          }
        }, CustomerVerificationResponse.class);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
        continue;
        String str2 = JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
      }
    }
  }
  
  public void doRequestToken()
  {
    String str1 = this.mCustomerSaved.getEmail();
    String str2 = this.mCustomerSaved.getPassword();
    if (this.mCustomerSaved.getPassword().isEmpty()) {}
    for (;;)
    {
      str1 = String.format("https://api.gojek.co.id/gojek/oauth/token?client_id=%s&grant_type=password&username=%s&password=%s", new Object[] { "consumer-trusted-client", str1, str2 });
      this.volleyClient.get(TAG, str1, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(SmsVerification.TAG, "get error request token " + paramAnonymousVolleyError);
          SmsVerification.this.mProgress.setVisibility(8);
          SmsVerification.this.mIVSubmit.setVisibility(0);
          SmsVerification.this.finish();
        }
        
        public void onResponse(OauthTokenResponse paramAnonymousOauthTokenResponse)
        {
          SmsVerification.this.mProgress.setVisibility(8);
          SmsVerification.this.mIVSubmit.setVisibility(0);
          Log.i(SmsVerification.TAG, "get oauth response " + paramAnonymousOauthTokenResponse.toString());
          if (SmsVerification.this.mCustomerSaved != null)
          {
            SmsVerification.this.mCustomerSaved.saveToken(paramAnonymousOauthTokenResponse.getAccessToken(), paramAnonymousOauthTokenResponse.getRefreshToken());
            SmsVerification.this.mixPanel.alias(SmsVerification.this.mCustomerSaved.getCustomerId(), null);
            SmsVerification.this.mixPanel.getPeople().set("$name", SmsVerification.this.mCustomerSaved.getName());
            SmsVerification.this.mixPanel.getPeople().set("$email", SmsVerification.this.mCustomerSaved.getEmail());
            SmsVerification.this.mixPanel.getPeople().set("Name", SmsVerification.this.mCustomerSaved.getName());
            SmsVerification.this.mixPanel.getPeople().set("Email", SmsVerification.this.mCustomerSaved.getEmail());
            SmsVerification.this.finish();
          }
        }
      }, OauthTokenResponse.class);
      return;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.verifyFBLogger = AppEventsLogger.newLogger(getApplicationContext());
    renderView();
    init();
  }
  
  protected void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    super.onResume();
  }
  
  public void progressDialogDismiss()
  {
    if (!isFinishing()) {
      this.progressDialog.dismiss();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/SmsVerification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */