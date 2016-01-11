package com.gojek.app;

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
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Response;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class EditProfile
  extends GojekActivityBase
  implements View.OnClickListener
{
  private static final String TAG = EditProfile.class.getSimpleName();
  private CustomerSaved mCustomer;
  private EditText mETEmail;
  private EditText mETName;
  private EditText mETPhone;
  private ImageView mIVSave;
  private ProgressBar mProgress;
  private VolleyClient volleyClient;
  
  private void doSave()
  {
    final String str3 = this.mETEmail.getText().toString();
    final String str4 = this.mETName.getText().toString();
    final String str5 = this.mETPhone.getText().toString();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("phone", str5);
      localJSONObject.putOpt("email", str3);
      localJSONObject.putOpt("name", str4);
      localJSONObject.putOpt("id", this.mCustomer.customerId);
      String str6 = TAG;
      StringBuilder localStringBuilder = new StringBuilder().append("update profile to https://api.gojek.co.id/gojek/v2/customer/edit/v2  with payload ");
      if (!(localJSONObject instanceof JSONObject))
      {
        String str1 = localJSONObject.toString();
        Log.i(str6, str1);
        this.volleyClient.post("https://api.gojek.co.id/gojek/v2/customer/edit/v2", localJSONObject, new JsonCallback()
        {
          public void onError(VolleyError paramAnonymousVolleyError)
          {
            EditProfile.this.mIVSave.setVisibility(0);
            EditProfile.this.mProgress.setVisibility(4);
            Log.e(EditProfile.TAG, "error update profile " + paramAnonymousVolleyError);
            Object localObject1;
            if (!(paramAnonymousVolleyError instanceof AuthFailureError)) {
              localObject1 = null;
            }
            try
            {
              localObject2 = JSONObjectInstrumentation.init(new String(paramAnonymousVolleyError.networkResponse.data, "utf-8")).getString("message");
              localObject1 = localObject2;
            }
            catch (JSONException localJSONException)
            {
              Object localObject2;
              for (;;) {}
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException)
            {
              for (;;) {}
            }
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = EditProfile.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
            }
            EditProfile.this.showSimpleDialog(null, (String)localObject2, null, true, null);
            return;
            EditProfile.this.isUnauthorizedApiAccess();
          }
          
          public void onResponse(Response paramAnonymousResponse)
          {
            EditProfile.this.mIVSave.setVisibility(0);
            EditProfile.this.mProgress.setVisibility(4);
            if (paramAnonymousResponse.getStatus() == null)
            {
              Toast.makeText(EditProfile.this.getApplicationContext(), "We were unable to process your request, please try again.", 1).show();
              return;
            }
            if (!paramAnonymousResponse.getStatus().equals("SMS_VERIFICATION"))
            {
              EditProfile.this.showSimpleDialog(null, "Profile successfully updated.", null, true, null);
              EditProfile.this.mCustomer.email = str3;
              EditProfile.this.mCustomer.name = str4;
              EditProfile.this.mCustomer.saveData();
              return;
            }
            paramAnonymousResponse = new Intent(EditProfile.this, SmsUpdateProfileVerification.class);
            paramAnonymousResponse.putExtra("UPDATED_PHONE_NUMBER", str5);
            EditProfile.this.startActivity(paramAnonymousResponse);
          }
        }, Response.class, this.mCustomer.getAccessToken());
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
  
  private void initListener()
  {
    this.mIVSave.setOnClickListener(this);
  }
  
  private void loadCustomerPref()
  {
    this.mETEmail.setText(this.mCustomer.email);
    this.mETName.setText(this.mCustomer.name);
    this.mETPhone.setText(this.mCustomer.phone);
  }
  
  private void renderView()
  {
    setContentView(2130968679);
    this.mETEmail = ((EditText)findViewById(2131624428));
    this.mETName = ((EditText)findViewById(2131624429));
    this.mETPhone = ((EditText)findViewById(2131624430));
    this.mIVSave = ((ImageView)findViewById(2131624330));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
    loadCustomerPref();
  }
  
  private boolean validation()
  {
    String str1 = this.mETEmail.getText().toString();
    String str2 = this.mETName.getText().toString();
    String str3 = this.mETPhone.getText().toString();
    String str4 = getString(2131165566);
    if (TextUtils.isEmpty(str1))
    {
      Toast.makeText(this, String.format(Locale.US, str4, new Object[] { "Email" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str2))
    {
      Toast.makeText(this, String.format(Locale.US, str4, new Object[] { "Name" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str3))
    {
      Toast.makeText(this, String.format(Locale.US, str4, new Object[] { "Phone" }), 0).show();
      return false;
    }
    return true;
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
    this.mIVSave.setVisibility(4);
    this.mProgress.setVisibility(0);
    doSave();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165747));
    this.mCustomer = new CustomerSaved(this);
    this.volleyClient = VolleyClient.getInstance(this);
    renderView();
    initListener();
  }
  
  protected void onResume()
  {
    this.mCustomer = new CustomerSaved(this);
    loadCustomerPref();
    super.onResume();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/EditProfile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */