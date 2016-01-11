package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

abstract class BaseFragmentActivityDonut
  extends Activity
  implements TraceFieldInterface
{
  abstract View dispatchFragmentsOnCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet);
  
  protected void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("BaseFragmentActivityDonut");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "BaseFragmentActivityDonut#onCreate", null);
      if ((Build.VERSION.SDK_INT < 11) && (getLayoutInflater().getFactory() == null)) {
        getLayoutInflater().setFactory(this);
      }
      super.onCreate(paramBundle);
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "BaseFragmentActivityDonut#onCreate", null);
      }
    }
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    View localView2 = dispatchFragmentsOnCreateView(null, paramString, paramContext, paramAttributeSet);
    View localView1 = localView2;
    if (localView2 == null) {
      localView1 = super.onCreateView(paramString, paramContext, paramAttributeSet);
    }
    return localView1;
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v4/app/BaseFragmentActivityDonut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */