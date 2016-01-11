package com.gojek.app;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gojek.app.gcm.GcmUtil;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.FacebookLoginRespone;
import com.gojek.app.model.OauthTokenResponse;
import com.gojek.app.parcelable.Customer;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mixpanel.android.mpmetrics.MixpanelAPI.People;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class SignIn
  extends GojekActivityBase
  implements View.OnClickListener
{
  private static final String TAG = SignIn.class.getSimpleName();
  AccessToken acToken;
  AccessTokenTracker accessTokenTracker;
  CallbackManager callbackManager;
  private boolean isFromSocmed = false;
  LoginButton loginButton;
  AppEventsLogger loginFBLogger;
  private CustomerSaved mCustomerSaved;
  private EditText mETEmail;
  private EditText mETPassword;
  private String mEmail;
  private ImageView mIVSignIn;
  private String mPassword;
  private ProgressBar mProgress;
  private TextView mTVForgot;
  private TextView mTVSignUp;
  private ProgressDialog progress;
  private VolleyClient volleyClient;
  
  private void doSignIn()
  {
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
          Log.e(SignIn.TAG, "error sign in customer " + paramAnonymousVolleyError);
          SignIn.this.mIVSignIn.setVisibility(0);
          SignIn.this.mProgress.setVisibility(4);
          paramAnonymousVolleyError = SignIn.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
          SignIn.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
          SignIn.this.loginFBLogger.logEvent(SignIn.this.getString(2131165541));
        }
        
        public void onResponse(Customer paramAnonymousCustomer)
        {
          if (paramAnonymousCustomer != null)
          {
            paramAnonymousCustomer.email = str1;
            new CustomerSaved(SignIn.this).saveData(paramAnonymousCustomer);
            SignIn.access$402(SignIn.this, new CustomerSaved(SignIn.this));
            SignIn.this.mCustomerSaved.saveData(paramAnonymousCustomer);
            SignIn.this.doRequestToken();
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
  
  private void facebookGraph()
  {
    GraphRequest localGraphRequest = GraphRequest.newMeRequest(this.acToken, new GraphRequest.GraphJSONObjectCallback()
    {
      public void onCompleted(JSONObject paramAnonymousJSONObject, GraphResponse paramAnonymousGraphResponse)
      {
        JSONObject localJSONObject1 = new JSONObject();
        try
        {
          localJSONObject1.putOpt("name", paramAnonymousJSONObject.getString("name"));
          localJSONObject1.putOpt("password", "");
          localJSONObject1.putOpt("confirmationpassword", "");
          localJSONObject1.putOpt("phone", "");
          localJSONObject1.putOpt("email", paramAnonymousJSONObject.getString("email"));
          if (!(localJSONObject1 instanceof JSONObject))
          {
            paramAnonymousGraphResponse = localJSONObject1.toString();
            Log.d("Bima", paramAnonymousGraphResponse);
            localJSONObject2 = new JSONObject();
          }
        }
        catch (JSONException localJSONException)
        {
          try
          {
            localJSONObject2.putOpt("refreshToken", "");
            localJSONObject2.putOpt("token", SignIn.this.acToken.getToken());
            localJSONObject2.putOpt("typeSocialMedia", "fb");
            localJSONObject2.putOpt("idSocialMedia", paramAnonymousJSONObject.getString("id"));
            paramAnonymousGraphResponse = new JSONObject();
          }
          catch (JSONException localJSONException)
          {
            try
            {
              JSONObject localJSONObject2;
              paramAnonymousGraphResponse.putOpt("customerRegistrationRequestVO", localJSONObject1);
              paramAnonymousGraphResponse.putOpt("socmedRegistrationRequestVO", localJSONObject2);
            }
            catch (JSONException localJSONException)
            {
              try
              {
                for (;;)
                {
                  paramAnonymousJSONObject = paramAnonymousJSONObject.getString("email");
                  SignIn.this.volleyClient.post("https://api.gojek.co.id/gojek/customersocialmedia/login-social-media", paramAnonymousGraphResponse, new SignIn.3.1(this, paramAnonymousGraphResponse, paramAnonymousJSONObject), FacebookLoginRespone.class);
                  return;
                  paramAnonymousGraphResponse = paramAnonymousGraphResponse;
                  paramAnonymousGraphResponse.printStackTrace();
                  continue;
                  paramAnonymousGraphResponse = JSONObjectInstrumentation.toString((JSONObject)localJSONObject1);
                  continue;
                  paramAnonymousGraphResponse = paramAnonymousGraphResponse;
                  paramAnonymousGraphResponse.printStackTrace();
                  continue;
                  localJSONException = localJSONException;
                  localJSONException.printStackTrace();
                }
              }
              catch (JSONException paramAnonymousJSONObject)
              {
                for (;;)
                {
                  paramAnonymousJSONObject = "";
                }
              }
            }
          }
        }
      }
    });
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", "id,name,email");
    localGraphRequest.setParameters(localBundle);
    localGraphRequest.executeAsync();
  }
  
  private void goToHome()
  {
    this.progress.dismiss();
    this.loginFBLogger.logEvent(getString(2131165548));
    if (getIntent().getBooleanExtra("TO_HOME", false))
    {
      startActivity(new Intent(this, Home.class));
      finish();
      return;
    }
    if (getIntent().getBooleanExtra("NEED_SIGN_IN", false)) {
      setResult(-1, new Intent());
    }
    finish();
  }
  
  private void initListener()
  {
    this.mTVForgot.setOnClickListener(this);
    this.mTVSignUp.setOnClickListener(this);
    this.mIVSignIn.setOnClickListener(this);
  }
  
  private void renderView()
  {
    setContentView(2130968828);
    this.mETEmail = ((EditText)findViewById(2131624428));
    this.mETPassword = ((EditText)findViewById(2131625013));
    this.mTVForgot = ((TextView)findViewById(2131624561));
    this.mTVSignUp = ((TextView)findViewById(2131625016));
    this.mIVSignIn = ((ImageView)findViewById(2131625014));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
  }
  
  private boolean validation()
  {
    String str1 = this.mETEmail.getText().toString();
    String str2 = this.mETPassword.getText().toString();
    String str3 = getString(2131165566);
    if (TextUtils.isEmpty(str1))
    {
      Toast.makeText(this, String.format(Locale.US, str3, new Object[] { "Email" }), 0).show();
      return false;
    }
    if (TextUtils.isEmpty(str2))
    {
      Toast.makeText(this, String.format(Locale.US, str3, new Object[] { "Password" }), 0).show();
      return false;
    }
    return true;
  }
  
  public void doRequestToken()
  {
    String str3 = this.mETPassword.getText().toString();
    String str2;
    if (this.isFromSocmed)
    {
      str2 = this.mEmail;
      str3 = this.acToken.getToken();
    }
    for (String str1 = "socialmedia-trusted-client";; str1 = "consumer-trusted-client")
    {
      str1 = String.format("https://api.gojek.co.id/gojek/oauth/token?client_id=%s&grant_type=password&username=%s&password=%s", new Object[] { str1, str2, str3 });
      this.volleyClient.get(TAG, str1, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(SignIn.TAG, "get error request token " + paramAnonymousVolleyError);
          SignIn.this.mCustomerSaved.clearData();
          SignIn.this.mIVSignIn.setVisibility(0);
          SignIn.this.mProgress.setVisibility(4);
          SignIn.this.volleyClient.getErrorResponseBody(paramAnonymousVolleyError);
          SignIn.this.showSimpleDialog(null, "failed to get your session", null, true, null);
        }
        
        public void onResponse(OauthTokenResponse paramAnonymousOauthTokenResponse)
        {
          Log.i(SignIn.TAG, "get oauth response " + paramAnonymousOauthTokenResponse.toString());
          if (SignIn.this.mCustomerSaved != null)
          {
            SignIn.this.mCustomerSaved.saveToken(paramAnonymousOauthTokenResponse.getAccessToken(), paramAnonymousOauthTokenResponse.getRefreshToken());
            SignIn.this.mIVSignIn.setVisibility(0);
            SignIn.this.mProgress.setVisibility(4);
            SignIn.this.mixPanel.identify(SignIn.this.mCustomerSaved.getCustomerId());
            SignIn.this.mixPanel.getPeople().identify(SignIn.this.mCustomerSaved.getCustomerId());
            SignIn.this.mixPanel.getPeople().set("$name", SignIn.this.mCustomerSaved.getName());
            SignIn.this.mixPanel.getPeople().set("$email", SignIn.this.mCustomerSaved.getEmail());
            SignIn.this.mixPanel.getPeople().set("Name", SignIn.this.mCustomerSaved.getName());
            SignIn.this.mixPanel.getPeople().set("Email", SignIn.this.mCustomerSaved.getEmail());
            SignIn.this.goToHome();
          }
        }
      }, OauthTokenResponse.class);
      return;
      str2 = this.mETEmail.getText().toString().toLowerCase();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.acToken == null) {
      this.callbackManager.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      return;
      startActivity(new Intent(this, ForgotPassword.class));
      return;
      startActivity(new Intent(this, SignUp.class));
      return;
    } while (!validation());
    this.mIVSignIn.setVisibility(4);
    this.mProgress.setVisibility(0);
    doSignIn();
  }
  
  /* Error */
  protected void onCreate(Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 370	com/gojek/app/GojekActivityBase:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: aload_0
    //   7: invokevirtual 374	com/gojek/app/SignIn:getApplicationContext	()Landroid/content/Context;
    //   10: invokestatic 378	com/facebook/appevents/AppEventsLogger:newLogger	(Landroid/content/Context;)Lcom/facebook/appevents/AppEventsLogger;
    //   13: putfield 199	com/gojek/app/SignIn:loginFBLogger	Lcom/facebook/appevents/AppEventsLogger;
    //   16: aload_0
    //   17: getfield 165	com/gojek/app/SignIn:acToken	Lcom/facebook/AccessToken;
    //   20: ifnonnull +9 -> 29
    //   23: invokestatic 384	com/facebook/login/LoginManager:getInstance	()Lcom/facebook/login/LoginManager;
    //   26: invokevirtual 387	com/facebook/login/LoginManager:logOut	()V
    //   29: aload_0
    //   30: new 8	com/gojek/app/SignIn$1
    //   33: dup
    //   34: aload_0
    //   35: invokespecial 388	com/gojek/app/SignIn$1:<init>	(Lcom/gojek/app/SignIn;)V
    //   38: putfield 390	com/gojek/app/SignIn:accessTokenTracker	Lcom/facebook/AccessTokenTracker;
    //   41: aload_0
    //   42: invokevirtual 394	com/gojek/app/SignIn:getPackageManager	()Landroid/content/pm/PackageManager;
    //   45: ldc_w 396
    //   48: bipush 64
    //   50: invokevirtual 402	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   53: getfield 408	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   56: astore_1
    //   57: aload_1
    //   58: arraylength
    //   59: istore_3
    //   60: iconst_0
    //   61: istore_2
    //   62: iload_2
    //   63: iload_3
    //   64: if_icmpge +30 -> 94
    //   67: aload_1
    //   68: iload_2
    //   69: aaload
    //   70: astore 4
    //   72: ldc_w 410
    //   75: invokestatic 415	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   78: aload 4
    //   80: invokevirtual 421	android/content/pm/Signature:toByteArray	()[B
    //   83: invokevirtual 425	java/security/MessageDigest:update	([B)V
    //   86: iload_2
    //   87: iconst_1
    //   88: iadd
    //   89: istore_2
    //   90: goto -28 -> 62
    //   93: astore_1
    //   94: aload_0
    //   95: invokespecial 427	com/gojek/app/SignIn:renderView	()V
    //   98: aload_0
    //   99: new 194	android/app/ProgressDialog
    //   102: dup
    //   103: aload_0
    //   104: invokespecial 430	android/app/ProgressDialog:<init>	(Landroid/content/Context;)V
    //   107: putfield 68	com/gojek/app/SignIn:progress	Landroid/app/ProgressDialog;
    //   110: aload_0
    //   111: getfield 68	com/gojek/app/SignIn:progress	Landroid/app/ProgressDialog;
    //   114: ldc_w 432
    //   117: invokevirtual 436	android/app/ProgressDialog:setMessage	(Ljava/lang/CharSequence;)V
    //   120: aload_0
    //   121: invokestatic 440	com/facebook/AccessToken:getCurrentAccessToken	()Lcom/facebook/AccessToken;
    //   124: putfield 165	com/gojek/app/SignIn:acToken	Lcom/facebook/AccessToken;
    //   127: aload_0
    //   128: getfield 165	com/gojek/app/SignIn:acToken	Lcom/facebook/AccessToken;
    //   131: ifnonnull +75 -> 206
    //   134: aload_0
    //   135: aload_0
    //   136: ldc_w 441
    //   139: invokevirtual 266	com/gojek/app/SignIn:findViewById	(I)Landroid/view/View;
    //   142: checkcast 443	com/facebook/login/widget/LoginButton
    //   145: putfield 445	com/gojek/app/SignIn:loginButton	Lcom/facebook/login/widget/LoginButton;
    //   148: aload_0
    //   149: getfield 445	com/gojek/app/SignIn:loginButton	Lcom/facebook/login/widget/LoginButton;
    //   152: iconst_3
    //   153: anewarray 292	java/lang/String
    //   156: dup
    //   157: iconst_0
    //   158: ldc_w 447
    //   161: aastore
    //   162: dup
    //   163: iconst_1
    //   164: ldc_w 449
    //   167: aastore
    //   168: dup
    //   169: iconst_2
    //   170: ldc_w 451
    //   173: aastore
    //   174: invokestatic 457	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   177: invokevirtual 461	com/facebook/login/widget/LoginButton:setReadPermissions	(Ljava/util/List;)V
    //   180: aload_0
    //   181: invokestatic 467	com/facebook/CallbackManager$Factory:create	()Lcom/facebook/CallbackManager;
    //   184: putfield 336	com/gojek/app/SignIn:callbackManager	Lcom/facebook/CallbackManager;
    //   187: aload_0
    //   188: getfield 445	com/gojek/app/SignIn:loginButton	Lcom/facebook/login/widget/LoginButton;
    //   191: aload_0
    //   192: getfield 336	com/gojek/app/SignIn:callbackManager	Lcom/facebook/CallbackManager;
    //   195: new 10	com/gojek/app/SignIn$2
    //   198: dup
    //   199: aload_0
    //   200: invokespecial 468	com/gojek/app/SignIn$2:<init>	(Lcom/gojek/app/SignIn;)V
    //   203: invokevirtual 472	com/facebook/login/widget/LoginButton:registerCallback	(Lcom/facebook/CallbackManager;Lcom/facebook/FacebookCallback;)V
    //   206: aload_0
    //   207: aload_0
    //   208: invokestatic 475	com/gojek/app/json/VolleyClient:getInstance	(Landroid/content/Context;)Lcom/gojek/app/json/VolleyClient;
    //   211: putfield 89	com/gojek/app/SignIn:volleyClient	Lcom/gojek/app/json/VolleyClient;
    //   214: aload_0
    //   215: invokespecial 477	com/gojek/app/SignIn:initListener	()V
    //   218: return
    //   219: astore_1
    //   220: goto -126 -> 94
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	223	0	this	SignIn
    //   0	223	1	paramBundle	Bundle
    //   61	29	2	i	int
    //   59	6	3	j	int
    //   70	9	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   41	60	93	java/security/NoSuchAlgorithmException
    //   72	86	93	java/security/NoSuchAlgorithmException
    //   41	60	219	android/content/pm/PackageManager$NameNotFoundException
    //   72	86	219	android/content/pm/PackageManager$NameNotFoundException
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.accessTokenTracker.stopTracking();
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/SignIn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */