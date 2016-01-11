package com.google.android.gms.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.internal.dr;
import com.google.android.gms.internal.ds;
import com.google.android.gms.internal.gs;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public final class AdActivity
  extends Activity
  implements TraceFieldInterface
{
  public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
  public static final String SIMPLE_CLASS_NAME = "AdActivity";
  private ds lc;
  
  private void U()
  {
    if (this.lc != null) {}
    try
    {
      this.lc.U();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not forward setContentViewSet to ad overlay:", localRemoteException);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("AdActivity");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "AdActivity#onCreate", null);
      super.onCreate(paramBundle);
      this.lc = dr.b(this);
      if (this.lc == null)
      {
        gs.W("Could not create ad overlay.");
        finish();
        TraceMachine.exitMethod();
        return;
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "AdActivity#onCreate", null);
      }
    }
    try
    {
      this.lc.onCreate(paramBundle);
      TraceMachine.exitMethod();
      return;
    }
    catch (RemoteException paramBundle)
    {
      for (;;)
      {
        gs.d("Could not forward onCreate to ad overlay:", paramBundle);
        finish();
      }
    }
  }
  
  protected void onDestroy()
  {
    try
    {
      if (this.lc != null) {
        this.lc.onDestroy();
      }
      super.onDestroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward onDestroy to ad overlay:", localRemoteException);
      }
    }
  }
  
  protected void onPause()
  {
    try
    {
      if (this.lc != null) {
        this.lc.onPause();
      }
      super.onPause();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward onPause to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  protected void onRestart()
  {
    super.onRestart();
    try
    {
      if (this.lc != null) {
        this.lc.onRestart();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not forward onRestart to ad overlay:", localRemoteException);
      finish();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    try
    {
      if (this.lc != null) {
        this.lc.onResume();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not forward onResume to ad overlay:", localRemoteException);
      finish();
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    try
    {
      if (this.lc != null) {
        this.lc.onSaveInstanceState(paramBundle);
      }
      super.onSaveInstanceState(paramBundle);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward onSaveInstanceState to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  protected void onStart()
  {
    ApplicationStateMonitor.getInstance().activityStarted();
    super.onStart();
    try
    {
      if (this.lc != null) {
        this.lc.onStart();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not forward onStart to ad overlay:", localRemoteException);
      finish();
    }
  }
  
  protected void onStop()
  {
    ApplicationStateMonitor.getInstance().activityStopped();
    try
    {
      if (this.lc != null) {
        this.lc.onStop();
      }
      super.onStop();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.d("Could not forward onStop to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  public void setContentView(int paramInt)
  {
    super.setContentView(paramInt);
    U();
  }
  
  public void setContentView(View paramView)
  {
    super.setContentView(paramView);
    U();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
    U();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/AdActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */