package com.facebook.login;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.R.id;
import com.facebook.R.layout;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class LoginFragment
  extends Fragment
  implements TraceFieldInterface
{
  private static final String EXTRA_REQUEST = "request";
  private static final String NULL_CALLING_PKG_ERROR_MSG = "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
  static final String RESULT_KEY = "com.facebook.LoginFragment:Result";
  private static final String SAVED_LOGIN_CLIENT = "loginClient";
  private static final String TAG = "LoginFragment";
  private String callingPackage;
  private LoginClient loginClient;
  private LoginClient.Request request;
  
  private void initializeCallingPackage(Activity paramActivity)
  {
    paramActivity = paramActivity.getCallingActivity();
    if (paramActivity == null) {
      return;
    }
    this.callingPackage = paramActivity.getPackageName();
  }
  
  private void onLoginClientCompleted(LoginClient.Result paramResult)
  {
    this.request = null;
    if (paramResult.code == LoginClient.Result.Code.CANCEL) {}
    for (int i = 0;; i = -1)
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("com.facebook.LoginFragment:Result", paramResult);
      paramResult = new Intent();
      paramResult.putExtras(localBundle);
      if (isAdded())
      {
        getActivity().setResult(i, paramResult);
        getActivity().finish();
      }
      return;
    }
  }
  
  static Bundle populateIntentExtras(LoginClient.Request paramRequest)
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("request", paramRequest);
    return localBundle;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.loginClient.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("LoginFragment");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "LoginFragment#onCreate", null);
      super.onCreate(paramBundle);
      if (paramBundle != null)
      {
        this.loginClient = ((LoginClient)paramBundle.getParcelable("loginClient"));
        this.loginClient.setFragment(this);
        this.loginClient.setOnCompletedListener(new LoginFragment.1(this));
        paramBundle = getActivity();
        if (paramBundle != null) {
          break label99;
        }
        TraceMachine.exitMethod();
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "LoginFragment#onCreate", null);
        continue;
        this.loginClient = new LoginClient(this);
      }
      label99:
      initializeCallingPackage(paramBundle);
      if (paramBundle.getIntent() != null) {
        this.request = ((LoginClient.Request)paramBundle.getIntent().getParcelableExtra("request"));
      }
      TraceMachine.exitMethod();
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "LoginFragment#onCreateView", null);
      paramLayoutInflater = paramLayoutInflater.inflate(R.layout.com_facebook_login_fragment, paramViewGroup, false);
      this.loginClient.setBackgroundProcessingListener(new LoginFragment.2(this, paramLayoutInflater));
      TraceMachine.exitMethod();
      return paramLayoutInflater;
    }
    catch (NoSuchFieldError paramBundle)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "LoginFragment#onCreateView", null);
      }
    }
  }
  
  public void onDestroy()
  {
    this.loginClient.cancelCurrentHandler();
    super.onDestroy();
  }
  
  public void onDetach()
  {
    super.onDetach();
    if (this.loginClient.getInProgress())
    {
      Object localObject = LoginClient.Result.createCancelResult(this.request, "Operation canceled");
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("com.facebook.LoginFragment:Result", (Parcelable)localObject);
      localObject = new Intent();
      ((Intent)localObject).putExtras(localBundle);
      getActivity().setResult(0, (Intent)localObject);
      getActivity().finish();
    }
  }
  
  public void onPause()
  {
    super.onPause();
    getActivity().findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.callingPackage == null)
    {
      Log.e("LoginFragment", "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
      getActivity().finish();
      return;
    }
    this.loginClient.startOrContinueAuth(this.request);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putParcelable("loginClient", this.loginClient);
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/facebook/login/LoginFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */