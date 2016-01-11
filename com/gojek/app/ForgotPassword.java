package com.gojek.app;

import android.os.Bundle;
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
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.Response;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPassword
  extends GojekActivityBase
  implements View.OnClickListener
{
  private static final String TAG = ForgotPassword.class.getSimpleName();
  private EditText mETEmail;
  private ImageView mIVSend;
  private ProgressBar mProgress;
  private TextView mTVSignIn;
  private VolleyClient volleyClient;
  
  private void doSend()
  {
    String str = this.mETEmail.getText().toString();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("email", str);
      this.volleyClient.post("https://api.gojek.co.id/gojek/customer/forgetPassword", localJSONObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(ForgotPassword.TAG, "error forget password " + paramAnonymousVolleyError);
          paramAnonymousVolleyError = ForgotPassword.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
          ForgotPassword.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
          ForgotPassword.this.mIVSend.setVisibility(0);
          ForgotPassword.this.mProgress.setVisibility(4);
        }
        
        public void onResponse(Response paramAnonymousResponse)
        {
          if (paramAnonymousResponse != null)
          {
            paramAnonymousResponse = ForgotPassword.this.getString(2131165816) + "\n" + ForgotPassword.this.getString(2131165586);
            ForgotPassword.this.showSimpleDialog(null, paramAnonymousResponse, ForgotPassword.this.getString(2131165411), true, null);
          }
          ForgotPassword.this.mIVSend.setVisibility(0);
          ForgotPassword.this.mProgress.setVisibility(4);
        }
      }, Response.class);
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
    this.mIVSend.setOnClickListener(this);
    this.mTVSignIn.setOnClickListener(this);
  }
  
  private void renderView()
  {
    setContentView(2130968706);
    this.mETEmail = ((EditText)findViewById(2131624428));
    this.mIVSend = ((ImageView)findViewById(2131624562));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
    this.mTVSignIn = ((TextView)findViewById(2131624563));
  }
  
  private boolean validation()
  {
    String str1 = this.mETEmail.getText().toString();
    String str2 = getString(2131165566);
    if (TextUtils.isEmpty(str1))
    {
      Toast.makeText(this, String.format(Locale.US, str2, new Object[] { "Email" }), 0).show();
      return false;
    }
    return true;
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
    case 2131624562: 
      do
      {
        return;
      } while (!validation());
      this.mIVSend.setVisibility(4);
      this.mProgress.setVisibility(0);
      doSend();
      return;
    }
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.volleyClient = VolleyClient.getInstance(this);
    renderView();
    initListener();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/ForgotPassword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */