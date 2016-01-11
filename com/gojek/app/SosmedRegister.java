package com.gojek.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.flurry.android.FlurryAgent;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.FacebookLoginRespone;
import com.gojek.app.parcelable.Customer;
import com.gojek.app.util.Util;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SosmedRegister
  extends GojekActivityBase
  implements View.OnClickListener
{
  private static final String TAG = SosmedRegister.class.getSimpleName();
  private JSONObject customerRegistrationRequestVO;
  private String email;
  private EditText mETEmail;
  private EditText mETPhone;
  private ImageView mIVSend;
  private ProgressBar mProgress;
  private JSONObject obj;
  private String phone;
  private JSONObject socmedRegistrationRequestVO;
  private VolleyClient volleyClient;
  
  private void doSignIn()
  {
    Object localObject2 = "";
    String str1;
    if (this.email.isEmpty()) {
      str1 = this.mETEmail.getText().toString();
    }
    for (;;)
    {
      final String str2;
      label46:
      final Object localObject3;
      Object localObject1;
      if (this.phone.isEmpty())
      {
        str2 = this.mETPhone.getText().toString();
        localObject3 = new JSONObject();
        localObject1 = localObject2;
      }
      try
      {
        ((JSONObject)localObject3).putOpt("name", this.customerRegistrationRequestVO.getString("name"));
        localObject1 = localObject2;
        localObject2 = this.customerRegistrationRequestVO.getString("name");
        localObject1 = localObject2;
        ((JSONObject)localObject3).putOpt("password", "");
        localObject1 = localObject2;
        ((JSONObject)localObject3).putOpt("confirmationpassword", "");
        localObject1 = localObject2;
        ((JSONObject)localObject3).putOpt("email", str1);
        localObject1 = localObject2;
        ((JSONObject)localObject3).putOpt("phone", str2);
        localObject1 = localObject2;
        localObject2 = new JSONObject();
      }
      catch (JSONException localJSONException1)
      {
        try
        {
          ((JSONObject)localObject2).putOpt("customerRegistrationRequestVO", localObject3);
          ((JSONObject)localObject2).putOpt("socmedRegistrationRequestVO", this.socmedRegistrationRequestVO);
          localObject3 = new HashMap();
          ((Map)localObject3).put("email", str1);
          ((Map)localObject3).put("phone", str2);
          ((Map)localObject3).put("name", localObject1);
          this.volleyClient.post("https://api.gojek.co.id/gojek/customersocialmedia/login-social-media", (JSONObject)localObject2, new JsonCallback()
          {
            public void onError(VolleyError paramAnonymousVolleyError)
            {
              paramAnonymousVolleyError = SosmedRegister.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
              SosmedRegister.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
            }
            
            public void onResponse(FacebookLoginRespone paramAnonymousFacebookLoginRespone)
            {
              if ((paramAnonymousFacebookLoginRespone != null) && (paramAnonymousFacebookLoginRespone.getStatusCode() == 201))
              {
                FlurryAgent.logEvent("SignupSocmed", localObject3);
                paramAnonymousFacebookLoginRespone = String.format(SosmedRegister.this.getString(2131165802), new Object[] { str2 });
                SosmedRegister.this.doVerify(paramAnonymousFacebookLoginRespone);
              }
            }
          }, FacebookLoginRespone.class);
          return;
          str1 = this.email;
          continue;
          str2 = this.phone;
          break label46;
          localJSONException1 = localJSONException1;
          localJSONException1.printStackTrace();
        }
        catch (JSONException localJSONException2)
        {
          for (;;)
          {
            localJSONException2.printStackTrace();
          }
        }
      }
    }
  }
  
  private void doVerify(String paramString)
  {
    String str1;
    if (this.email.isEmpty()) {
      str1 = this.mETEmail.getText().toString();
    }
    for (;;)
    {
      String str2;
      Customer localCustomer;
      if (this.phone.isEmpty())
      {
        str2 = this.mETPhone.getText().toString();
        localCustomer = new Customer();
        localCustomer.setEmail(str1);
      }
      try
      {
        localCustomer.setName(this.customerRegistrationRequestVO.getString("name"));
        localCustomer.setPhone(str2);
        localCustomer.setPassword("P4sS" + str2 + "w0rd");
        new CustomerSaved(this).saveData(localCustomer);
        Util.confirmDialog(this, new Handler()
        {
          public void handleMessage(Message paramAnonymousMessage)
          {
            paramAnonymousMessage = new Intent(SosmedRegister.this, SmsVerification.class);
            SosmedRegister.this.startActivity(paramAnonymousMessage);
            SosmedRegister.this.finish();
          }
        }, paramString, null);
        return;
        str1 = this.email;
        continue;
        str2 = this.phone;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          localCustomer.setName("");
          localJSONException.printStackTrace();
        }
      }
    }
  }
  
  private void initListener()
  {
    this.mIVSend.setOnClickListener(this);
  }
  
  private void renderView()
  {
    setContentView(2130968833);
    this.mETEmail = ((EditText)findViewById(2131624428));
    this.mETPhone = ((EditText)findViewById(2131624430));
    this.mIVSend = ((ImageView)findViewById(2131624562));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
  }
  
  private boolean validation()
  {
    String str = getString(2131165566);
    if ((this.email.isEmpty()) && (TextUtils.isEmpty(this.mETEmail.getText().toString())))
    {
      Toast.makeText(this, String.format(Locale.US, str, new Object[] { "Email" }), 0).show();
      return false;
    }
    if ((this.phone.isEmpty()) && (TextUtils.isEmpty(this.mETPhone.getText().toString())))
    {
      Toast.makeText(this, String.format(Locale.US, str, new Object[] { "phone" }), 0).show();
      return false;
    }
    return true;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    LoginManager.getInstance().logOut();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      return;
    } while (!validation());
    this.mIVSend.setVisibility(4);
    this.mProgress.setVisibility(0);
    doSignIn();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    renderView();
    this.volleyClient = VolleyClient.getInstance(this);
    try
    {
      this.obj = JSONObjectInstrumentation.init(getIntent().getStringExtra("fbJason"));
      this.socmedRegistrationRequestVO = JSONObjectInstrumentation.init(this.obj.getString("socmedRegistrationRequestVO"));
      this.customerRegistrationRequestVO = JSONObjectInstrumentation.init(this.obj.getString("customerRegistrationRequestVO"));
      this.email = this.customerRegistrationRequestVO.getString("email");
      this.phone = this.customerRegistrationRequestVO.getString("phone");
      if (!this.email.isEmpty()) {
        findViewById(2131625024).setVisibility(8);
      }
      if (!this.phone.isEmpty()) {
        findViewById(2131625025).setVisibility(8);
      }
      initListener();
      return;
    }
    catch (JSONException paramBundle)
    {
      for (;;)
      {
        paramBundle.printStackTrace();
        this.email = "";
        this.phone = "";
      }
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    AppEventsLogger.deactivateApp(this);
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    AppEventsLogger.activateApp(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/SosmedRegister.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */