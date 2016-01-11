package com.gojek.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.facebook.appevents.AppEventsLogger;
import com.flurry.android.FlurryAgent;
import com.gojek.app.gcm.GcmUtil;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.SignUpResponse;
import com.gojek.app.parcelable.Customer;
import com.gojek.app.util.Util;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUp
  extends GojekActivityBase
  implements View.OnClickListener
{
  private static final String TAG = SignUp.class.getSimpleName();
  private EditText mETConfirmPassword;
  private EditText mETEmail;
  private EditText mETName;
  private EditText mETPassword;
  private EditText mETPhone;
  private ImageView mIVSignUp;
  private ProgressBar mProgress;
  private TextView mTVSignIn;
  private AppEventsLogger signUpFBLogger;
  private VolleyClient volleyClient;
  
  private void doSignIn()
  {
    this.mIVSignUp.setVisibility(4);
    this.mProgress.setVisibility(0);
    final String str1 = this.mETEmail.getText().toString();
    String str2 = this.mETPassword.getText().toString();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("userName", str1);
      localJSONObject.putOpt("password", str2);
      localJSONObject.putOpt("deviceToken", GcmUtil.getRegistrationId(this));
      localJSONObject.putOpt("activitySource", Integer.valueOf(2));
      this.volleyClient.post("https://api.gojek.co.id/gojek/customer/login", localJSONObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(SignUp.TAG, "error sign in customer " + paramAnonymousVolleyError);
          SignUp.this.mIVSignUp.setVisibility(0);
          SignUp.this.mProgress.setVisibility(4);
          paramAnonymousVolleyError = SignUp.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
          SignUp.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
        }
        
        public void onResponse(Customer paramAnonymousCustomer)
        {
          SignUp.this.mIVSignUp.setVisibility(0);
          SignUp.this.mProgress.setVisibility(4);
          if (paramAnonymousCustomer != null)
          {
            paramAnonymousCustomer.email = str1;
            new CustomerSaved(SignUp.this).saveData(paramAnonymousCustomer);
            SignUp.this.showSimpleDialog(null, "Register success.", SignUp.this.getString(17039370), false, new SignUp.3.1(this));
          }
        }
      }, Customer.class);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  private void doSignUp()
  {
    String str2 = this.mETEmail.getText().toString();
    String str3 = this.mETName.getText().toString();
    final String str1 = this.mETPhone.getText().toString();
    String str4 = this.mETPassword.getText().toString();
    String str5 = this.mETConfirmPassword.getText().toString();
    final HashMap localHashMap = new HashMap();
    localHashMap.put("email", str2);
    localHashMap.put("phone", str1);
    localHashMap.put("name", str3);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("phone", str1);
      localJSONObject.putOpt("email", str2);
      localJSONObject.putOpt("confirmationPassword", str5);
      localJSONObject.putOpt("password", str4);
      localJSONObject.putOpt("name", str3);
      this.volleyClient.post("https://api.gojek.co.id/gojek/customer/v2/signup", localJSONObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(SignUp.TAG, "error sign up customer " + paramAnonymousVolleyError);
          SignUp.this.mIVSignUp.setVisibility(0);
          SignUp.this.mProgress.setVisibility(4);
          paramAnonymousVolleyError = SignUp.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
          SignUp.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
        }
        
        public void onResponse(SignUpResponse paramAnonymousSignUpResponse)
        {
          SignUp.this.mIVSignUp.setVisibility(0);
          SignUp.this.mProgress.setVisibility(4);
          if (paramAnonymousSignUpResponse != null)
          {
            FlurryAgent.logEvent("Signup", localHashMap);
            paramAnonymousSignUpResponse = String.format(SignUp.this.getString(2131165802), new Object[] { str1 });
            SignUp.this.doVerify(paramAnonymousSignUpResponse);
            SignUp.this.signUpFBLogger.logEvent("fb_mobile_complete_registration");
          }
        }
      }, SignUpResponse.class);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  private void doVerify(String paramString)
  {
    Customer localCustomer = new Customer();
    localCustomer.setEmail(this.mETEmail.getText().toString());
    localCustomer.setName(this.mETName.getText().toString());
    localCustomer.setPhone(this.mETPhone.getText().toString());
    localCustomer.setPassword(this.mETPassword.getText().toString());
    new CustomerSaved(this).saveData(localCustomer);
    Util.confirmDialog(this, new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        paramAnonymousMessage = new Intent(SignUp.this, SmsVerification.class);
        SignUp.this.startActivity(paramAnonymousMessage);
        SignUp.this.finish();
      }
    }, paramString, null);
  }
  
  private void initListener()
  {
    this.mIVSignUp.setOnClickListener(this);
    this.mTVSignIn.setOnClickListener(this);
  }
  
  private void renderView()
  {
    setContentView(2130968829);
    this.mETEmail = ((EditText)findViewById(2131624428));
    this.mETName = ((EditText)findViewById(2131624429));
    this.mETPhone = ((EditText)findViewById(2131624430));
    this.mETPassword = ((EditText)findViewById(2131625013));
    this.mETConfirmPassword = ((EditText)findViewById(2131625017));
    this.mIVSignUp = ((ImageView)findViewById(2131625018));
    this.mTVSignIn = ((TextView)findViewById(2131624563));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
  }
  
  private boolean validation()
  {
    String str1 = this.mETEmail.getText().toString();
    String str2 = this.mETName.getText().toString();
    String str3 = this.mETPhone.getText().toString();
    String str4 = this.mETPassword.getText().toString();
    String str5 = this.mETConfirmPassword.getText().toString();
    String str6 = getString(2131165566);
    if (TextUtils.isEmpty(str1))
    {
      Toast.makeText(this, String.format(Locale.US, str6, new Object[] { "Email" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str2))
    {
      Toast.makeText(this, String.format(Locale.US, str6, new Object[] { "Name" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str3))
    {
      Toast.makeText(this, String.format(Locale.US, str6, new Object[] { "Phone" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str4))
    {
      Toast.makeText(this, String.format(Locale.US, str6, new Object[] { "Password" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str5))
    {
      Toast.makeText(this, String.format(Locale.US, str6, new Object[] { "Confirmation Password" }), 0).show();
      return false;
    }
    return true;
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
    case 2131625018: 
      do
      {
        return;
      } while (!validation());
      this.mIVSignUp.setVisibility(4);
      this.mProgress.setVisibility(0);
      doSignUp();
      return;
    }
    if (new CustomerSaved(this).customerId == null) {
      startActivityForResult(new Intent(this, SignIn.class), 100);
    }
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    renderView();
    initListener();
    this.volleyClient = VolleyClient.getInstance(this);
    this.signUpFBLogger = AppEventsLogger.newLogger(getApplicationContext());
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/SignUp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */