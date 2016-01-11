package com.facebook;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.NativeProtocol;
import com.facebook.login.LoginFragment;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class FacebookActivity
  extends FragmentActivity
  implements TraceFieldInterface
{
  private static String FRAGMENT_TAG = "SingleFragment";
  public static String PASS_THROUGH_CANCEL_ACTION = "PassThrough";
  private Fragment singleFragment;
  
  private void handlePassThroughError()
  {
    Intent localIntent = getIntent();
    setResult(0, NativeProtocol.createProtocolResultIntent(localIntent, null, NativeProtocol.getExceptionFromErrorData(NativeProtocol.getMethodArgumentsFromIntent(localIntent))));
    finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.singleFragment != null) {
      this.singleFragment.onConfigurationChanged(paramConfiguration);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("FacebookActivity");
    FragmentManager localFragmentManager;
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "FacebookActivity#onCreate", null);
      super.onCreate(paramBundle);
      setContentView(R.layout.com_facebook_activity_layout);
      localIntent = getIntent();
      if (PASS_THROUGH_CANCEL_ACTION.equals(localIntent.getAction()))
      {
        handlePassThroughError();
        TraceMachine.exitMethod();
        return;
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      Intent localIntent;
      for (;;)
      {
        TraceMachine.enterMethod(null, "FacebookActivity#onCreate", null);
      }
      localFragmentManager = getSupportFragmentManager();
      Fragment localFragment = localFragmentManager.findFragmentByTag(FRAGMENT_TAG);
      paramBundle = localFragment;
      if (localFragment == null)
      {
        if (!"FacebookDialogFragment".equals(localIntent.getAction())) {
          break label128;
        }
        paramBundle = new FacebookDialogFragment();
        paramBundle.setRetainInstance(true);
        paramBundle.show(localFragmentManager, FRAGMENT_TAG);
      }
    }
    for (;;)
    {
      this.singleFragment = paramBundle;
      TraceMachine.exitMethod();
      return;
      label128:
      paramBundle = new LoginFragment();
      paramBundle.setRetainInstance(true);
      localFragmentManager.beginTransaction().add(R.id.com_facebook_fragment_container, paramBundle, FRAGMENT_TAG).commit();
    }
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/facebook/FacebookActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */