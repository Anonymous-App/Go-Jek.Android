package com.gojek.app.food;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public abstract class FoodFragmentBase
  extends Fragment
  implements TraceFieldInterface
{
  protected ActionBar action;
  
  public abstract int getLayoutId();
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("FoodFragmentBase");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "FoodFragmentBase#onCreate", null);
      this.action = ((AppCompatActivity)getActivity()).getSupportActionBar();
      super.onCreate(paramBundle);
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "FoodFragmentBase#onCreate", null);
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/FoodFragmentBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */