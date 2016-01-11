package com.gojek.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.CustomerVerificationResponse;
import com.gojek.app.model.OauthTokenResponse;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class SmsUpdateProfileVerification
  extends GojekActivityBase
{
  private static final String TAG = SmsUpdateProfileVerification.class.getSimpleName();
  private CustomerSaved mCustomerSaved;
  private EditText mETVerificationCode;
  private ImageView mIVSubmit;
  private ProgressBar mProgress;
  private ProgressDialog progressDialog;
  private VolleyClient volleyClient;
  
  private void init()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    this.volleyClient = VolleyClient.getInstance(this);
    this.mIVSubmit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SmsUpdateProfileVerification.this.validation()) {
          SmsUpdateProfileVerification.this.verifyCustomer();
        }
      }
    });
  }
  
  private void renderView()
  {
    setContentView(2130968831);
    this.mETVerificationCode = ((EditText)findViewById(2131625020));
    this.mIVSubmit = ((ImageView)findViewById(2131624966));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
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
    String str1 = this.mETVerificationCode.getText().toString();
    final String str3 = getIntent().getStringExtra("UPDATED_PHONE_NUMBER");
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("id", Integer.valueOf(Integer.parseInt(this.mCustomerSaved.customerId)));
      localJSONObject.putOpt("phone", str3);
      localJSONObject.putOpt("verificationCode", str1);
      String str4 = TAG;
      StringBuilder localStringBuilder = new StringBuilder().append("verification update user profile to https://api.gojek.co.id/gojek/v2/customer/verificationUpdateProfile with payload ");
      if (!(localJSONObject instanceof JSONObject))
      {
        str1 = localJSONObject.toString();
        Log.i(str4, str1);
        this.volleyClient.post("https://api.gojek.co.id/gojek/v2/customer/verificationUpdateProfile", localJSONObject, new JsonCallback()
        {
          public void onError(VolleyError paramAnonymousVolleyError)
          {
            Log.e(SmsUpdateProfileVerification.TAG, "error verification sms " + paramAnonymousVolleyError);
            SmsUpdateProfileVerification.this.mProgress.setVisibility(8);
            SmsUpdateProfileVerification.this.mIVSubmit.setVisibility(0);
            if (!(paramAnonymousVolleyError instanceof AuthFailureError))
            {
              paramAnonymousVolleyError = SmsUpdateProfileVerification.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
              SmsUpdateProfileVerification.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
              return;
            }
            SmsUpdateProfileVerification.this.isUnauthorizedApiAccess();
          }
          
          public void onResponse(CustomerVerificationResponse paramAnonymousCustomerVerificationResponse)
          {
            SmsUpdateProfileVerification.this.mProgress.setVisibility(8);
            SmsUpdateProfileVerification.this.mIVSubmit.setVisibility(0);
            Log.i(SmsUpdateProfileVerification.TAG, "update customer verification " + paramAnonymousCustomerVerificationResponse.toString());
            if (paramAnonymousCustomerVerificationResponse.getCustomerId() != null)
            {
              if ((str3 != null) && (!str3.isEmpty())) {
                SmsUpdateProfileVerification.this.mCustomerSaved.verifyPhoneNumber(str3);
              }
              SmsUpdateProfileVerification.this.finish();
              return;
            }
            SmsUpdateProfileVerification.this.mProgress.setVisibility(8);
            SmsUpdateProfileVerification.this.mIVSubmit.setVisibility(0);
            SmsUpdateProfileVerification.this.showSimpleDialog(null, paramAnonymousCustomerVerificationResponse.getStatus(), null, true, null);
          }
        }, CustomerVerificationResponse.class, this.mCustomerSaved.getAccessToken());
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
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void doRequestToken()
  {
    String str = String.format("https://api.gojek.co.id/gojek/oauth/token?client_id=%s&grant_type=password&username=%s&password=%s", new Object[] { this.mCustomerSaved.getEmail(), this.mCustomerSaved.getPassword() });
    this.volleyClient.get(TAG, str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(SmsUpdateProfileVerification.TAG, "get error request token " + paramAnonymousVolleyError);
        SmsUpdateProfileVerification.this.mProgress.setVisibility(8);
        SmsUpdateProfileVerification.this.mIVSubmit.setVisibility(0);
        SmsUpdateProfileVerification.this.finish();
      }
      
      public void onResponse(OauthTokenResponse paramAnonymousOauthTokenResponse)
      {
        SmsUpdateProfileVerification.this.mProgress.setVisibility(8);
        SmsUpdateProfileVerification.this.mIVSubmit.setVisibility(0);
        Log.i(SmsUpdateProfileVerification.TAG, "get oauth response " + paramAnonymousOauthTokenResponse.toString());
        if (SmsUpdateProfileVerification.this.mCustomerSaved != null)
        {
          SmsUpdateProfileVerification.this.mCustomerSaved.saveToken(paramAnonymousOauthTokenResponse.getAccessToken(), paramAnonymousOauthTokenResponse.getRefreshToken());
          SmsUpdateProfileVerification.this.finish();
        }
      }
    }, OauthTokenResponse.class);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    renderView();
    init();
    setTitle("SMS Verification");
  }
  
  protected void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    super.onResume();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/SmsUpdateProfileVerification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */