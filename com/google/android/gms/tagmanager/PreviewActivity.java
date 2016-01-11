package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class PreviewActivity
  extends Activity
  implements TraceFieldInterface
{
  private void d(String paramString1, String paramString2, String paramString3)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    localAlertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localAlertDialog.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("PreviewActivity");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "PreviewActivity#onCreate", null);
      try
      {
        super.onCreate(paramBundle);
        bh.U("Preview activity");
        paramBundle = getIntent().getData();
        if (!TagManager.getInstance(this).i(paramBundle))
        {
          paramBundle = "Cannot preview the app with the uri: " + paramBundle + ". Launching current version instead.";
          bh.W(paramBundle);
          d("Preview failure", paramBundle, "Continue");
        }
        paramBundle = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (paramBundle == null) {
          break label143;
        }
        bh.U("Invoke the launch activity for package name: " + getPackageName());
        startActivity(paramBundle);
        TraceMachine.exitMethod();
      }
      catch (Exception paramBundle)
      {
        for (;;)
        {
          bh.T("Calling preview threw an exception: " + paramBundle.getMessage());
        }
      }
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "PreviewActivity#onCreate", null);
        continue;
        label143:
        bh.U("No launch activity found for package name: " + getPackageName());
      }
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/PreviewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */