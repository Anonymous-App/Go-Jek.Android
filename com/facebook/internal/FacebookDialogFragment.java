package com.facebook.internal;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class FacebookDialogFragment
  extends DialogFragment
  implements TraceFieldInterface
{
  public static final String TAG = "FacebookDialogFragment";
  private Dialog dialog;
  
  private void onCompleteWebDialog(Bundle paramBundle, FacebookException paramFacebookException)
  {
    FragmentActivity localFragmentActivity = getActivity();
    paramBundle = NativeProtocol.createProtocolResultIntent(localFragmentActivity.getIntent(), paramBundle, paramFacebookException);
    if (paramFacebookException == null) {}
    for (int i = -1;; i = 0)
    {
      localFragmentActivity.setResult(i, paramBundle);
      localFragmentActivity.finish();
      return;
    }
  }
  
  private void onCompleteWebFallbackDialog(Bundle paramBundle)
  {
    FragmentActivity localFragmentActivity = getActivity();
    Intent localIntent = new Intent();
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localIntent.putExtras(localBundle);
    localFragmentActivity.setResult(-1, localIntent);
    localFragmentActivity.finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if ((this.dialog instanceof WebDialog)) {
      ((WebDialog)this.dialog).resize();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("FacebookDialogFragment");
    Bundle localBundle;
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "FacebookDialogFragment#onCreate", null);
      super.onCreate(paramBundle);
      if (this.dialog == null)
      {
        paramBundle = getActivity();
        localBundle = NativeProtocol.getMethodArgumentsFromIntent(paramBundle.getIntent());
        if (localBundle.getBoolean("is_fallback", false)) {
          break label131;
        }
        String str1 = localBundle.getString("action");
        localBundle = localBundle.getBundle("params");
        if (Utility.isNullOrEmpty(str1))
        {
          Utility.logd("FacebookDialogFragment", "Cannot start a WebDialog with an empty/missing 'actionName'");
          paramBundle.finish();
          TraceMachine.exitMethod();
          return;
        }
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "FacebookDialogFragment#onCreate", null);
      }
      paramBundle = new WebDialog.Builder(paramBundle, localNoSuchFieldError, localBundle).setOnCompleteListener(new FacebookDialogFragment.1(this)).build();
    }
    for (;;)
    {
      this.dialog = paramBundle;
      TraceMachine.exitMethod();
      return;
      label131:
      String str2 = localBundle.getString("url");
      if (Utility.isNullOrEmpty(str2))
      {
        Utility.logd("FacebookDialogFragment", "Cannot start a fallback WebDialog with an empty/missing 'url'");
        paramBundle.finish();
        TraceMachine.exitMethod();
        return;
      }
      paramBundle = new FacebookWebFallbackDialog(paramBundle, str2, String.format("fb%s://bridge/", new Object[] { FacebookSdk.getApplicationId() }));
      paramBundle.setOnCompleteListener(new FacebookDialogFragment.2(this));
    }
  }
  
  @NonNull
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    if (this.dialog == null)
    {
      onCompleteWebDialog(null, null);
      setShowsDialog(false);
    }
    return this.dialog;
  }
  
  public void onDestroyView()
  {
    if ((getDialog() != null) && (getRetainInstance())) {
      getDialog().setDismissMessage(null);
    }
    super.onDestroyView();
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
  
  public void setDialog(Dialog paramDialog)
  {
    this.dialog = paramDialog;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/facebook/internal/FacebookDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */