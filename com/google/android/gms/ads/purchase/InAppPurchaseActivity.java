package com.google.android.gms.ads.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.en;
import com.google.android.gms.internal.gs;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public final class InAppPurchaseActivity
  extends Activity
  implements TraceFieldInterface
{
  public static final String CLASS_NAME = "com.google.android.gms.ads.purchase.InAppPurchaseActivity";
  public static final String SIMPLE_CLASS_NAME = "InAppPurchaseActivity";
  private ei xk;
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    try
    {
      if (this.xk != null) {
        this.xk.onActivityResult(paramInt1, paramInt2, paramIntent);
      }
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward onActivityResult to in-app purchase manager:", localRemoteException);
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("InAppPurchaseActivity");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "InAppPurchaseActivity#onCreate", null);
      super.onCreate(paramBundle);
      this.xk = en.e(this);
      if (this.xk == null)
      {
        gs.W("Could not create in-app purchase manager.");
        finish();
        TraceMachine.exitMethod();
        return;
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "InAppPurchaseActivity#onCreate", null);
      }
    }
    try
    {
      this.xk.onCreate();
      TraceMachine.exitMethod();
      return;
    }
    catch (RemoteException paramBundle)
    {
      for (;;)
      {
        gs.d("Could not forward onCreate to in-app purchase manager:", paramBundle);
        finish();
      }
    }
  }
  
  protected void onDestroy()
  {
    try
    {
      if (this.xk != null) {
        this.xk.onDestroy();
      }
      super.onDestroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward onDestroy to in-app purchase manager:", localRemoteException);
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/purchase/InAppPurchaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */