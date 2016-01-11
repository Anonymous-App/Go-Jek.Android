package com.gojek.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.SignUpResponse;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class SmsResend
  extends GojekActivityBase
{
  private static final String TAG = SmsResend.class.getSimpleName();
  private CustomerSaved customerSaved;
  private EditText mETPhone;
  private ImageView mIVSend;
  private ProgressBar mProgress;
  private VolleyClient volleyClient;
  
  private void init()
  {
    this.volleyClient = VolleyClient.getInstance(this);
    this.customerSaved = new CustomerSaved(this);
    this.mIVSend.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SmsResend.this.validation()) {
          SmsResend.this.resendSms();
        }
      }
    });
  }
  
  private void renderView()
  {
    setContentView(2130968830);
    this.mETPhone = ((EditText)findViewById(2131624430));
    this.mIVSend = ((ImageView)findViewById(2131624562));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
  }
  
  private void resendSms()
  {
    this.mIVSend.setVisibility(8);
    this.mProgress.setVisibility(0);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("phone", this.mETPhone.getText().toString());
      localJSONObject.putOpt("email", this.customerSaved.email);
      localJSONObject.putOpt("confirmationPassword", this.customerSaved.password);
      localJSONObject.putOpt("password", this.customerSaved.password);
      localJSONObject.putOpt("name", this.customerSaved.name);
      String str3 = TAG;
      StringBuilder localStringBuilder = new StringBuilder().append("signup https://api.gojek.co.id/gojek/customer/v2/signup with json ");
      if (!(localJSONObject instanceof JSONObject))
      {
        String str1 = localJSONObject.toString();
        Log.i(str3, str1);
        new Handler()
        {
          public void handleMessage(Message paramAnonymousMessage)
          {
            SmsResend.this.finish();
          }
        };
        this.volleyClient.post("https://api.gojek.co.id/gojek/customer/v2/signup", localJSONObject, new JsonCallback()
        {
          public void onError(VolleyError paramAnonymousVolleyError)
          {
            Log.e(SmsResend.TAG, "error resend sms " + paramAnonymousVolleyError);
            SmsResend.this.mIVSend.setVisibility(0);
            SmsResend.this.mProgress.setVisibility(8);
            paramAnonymousVolleyError = SmsResend.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
            SmsResend.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
          }
          
          public void onResponse(SignUpResponse paramAnonymousSignUpResponse)
          {
            SmsResend.this.mIVSend.setVisibility(0);
            SmsResend.this.mProgress.setVisibility(8);
            Log.i(SmsResend.TAG, "response " + paramAnonymousSignUpResponse.toString());
            if (paramAnonymousSignUpResponse != null)
            {
              paramAnonymousSignUpResponse = String.format(SmsResend.this.getString(2131165802), new Object[] { SmsResend.this.mETPhone.getText().toString() });
              SmsResend.this.customerSaved.phone = SmsResend.this.mETPhone.getText().toString();
              SmsResend.this.customerSaved.saveData();
              SmsResend.this.showSimpleDialog(null, paramAnonymousSignUpResponse, null, true, new SmsResend.3.1(this));
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
  
  private boolean validation()
  {
    String str1 = this.mETPhone.getText().toString();
    String str2 = getString(2131165566);
    if (TextUtils.isEmpty(str1))
    {
      Toast.makeText(this, String.format(Locale.US, str2, new Object[] { "Phone" }), 0).show();
      return false;
    }
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    renderView();
    init();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/SmsResend.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */