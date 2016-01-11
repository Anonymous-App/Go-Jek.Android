package com.gojek.app;

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
import com.gojek.app.model.Response;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangePassword
  extends GojekActivityBase
  implements View.OnClickListener
{
  private static final String TAG = ChangePassword.class.getSimpleName();
  private CustomerSaved mCustomerSaved;
  private EditText mETConfirmNewPassword;
  private EditText mETNewPassword;
  private EditText mETOldPasword;
  private ImageView mIVSave;
  private ProgressBar mProgress;
  private VolleyClient volleyClient;
  
  private void doSave()
  {
    String str1 = this.mETOldPasword.getText().toString();
    String str2 = this.mETNewPassword.getText().toString();
    String str3 = this.mETConfirmNewPassword.getText().toString();
    CustomerSaved localCustomerSaved = new CustomerSaved(getApplicationContext());
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("customerId", localCustomerSaved.customerId);
      localJSONObject.putOpt("currentPassword", str1);
      localJSONObject.putOpt("newPassword", str2);
      localJSONObject.putOpt("confirmationNewPassword", str3);
      this.volleyClient.post("https://api.gojek.co.id/gojek/v2/customer/changePassword", localJSONObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(ChangePassword.TAG, "error change password " + paramAnonymousVolleyError);
          ChangePassword.this.mIVSave.setVisibility(0);
          ChangePassword.this.mProgress.setVisibility(4);
          if (!(paramAnonymousVolleyError instanceof AuthFailureError))
          {
            paramAnonymousVolleyError = ChangePassword.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
            ChangePassword.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
            return;
          }
          ChangePassword.this.isUnauthorizedApiAccess();
        }
        
        public void onResponse(Response paramAnonymousResponse)
        {
          if (paramAnonymousResponse != null) {
            ChangePassword.this.showSimpleDialog(null, paramAnonymousResponse.status, ChangePassword.this.getString(2131165411), true, null);
          }
          ChangePassword.this.mIVSave.setVisibility(0);
          ChangePassword.this.mProgress.setVisibility(4);
        }
      }, Response.class, this.mCustomerSaved.getAccessToken());
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
  
  private void initListener()
  {
    this.mIVSave.setOnClickListener(this);
  }
  
  private void renderView()
  {
    setContentView(2130968643);
    this.mETOldPasword = ((EditText)findViewById(2131624327));
    this.mETNewPassword = ((EditText)findViewById(2131624328));
    this.mETConfirmNewPassword = ((EditText)findViewById(2131624329));
    this.mIVSave = ((ImageView)findViewById(2131624330));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
  }
  
  private boolean validation()
  {
    String str1 = this.mETOldPasword.getText().toString();
    String str2 = this.mETNewPassword.getText().toString();
    String str3 = this.mETConfirmNewPassword.getText().toString();
    String str4 = getString(2131165566);
    if (TextUtils.isEmpty(str1))
    {
      Toast.makeText(this, String.format(Locale.US, str4, new Object[] { "Old password" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str2))
    {
      Toast.makeText(this, String.format(Locale.US, str4, new Object[] { "New password" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str3))
    {
      Toast.makeText(this, String.format(Locale.US, str4, new Object[] { "Confirm new password" }), 0).show();
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
    setTitle(getString(2131165370));
    this.volleyClient = VolleyClient.getInstance(this);
    this.mCustomerSaved = new CustomerSaved(this);
    renderView();
    initListener();
  }
  
  protected void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    super.onResume();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/ChangePassword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */