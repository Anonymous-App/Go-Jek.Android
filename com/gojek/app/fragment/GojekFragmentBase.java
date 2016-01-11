package com.gojek.app.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface.OnCancelListener;
import android.support.v4.app.Fragment;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
public class GojekFragmentBase
  extends Fragment
  implements TraceFieldInterface
{
  private static ProgressDialog pd;
  
  protected void dismissSimpleProgressDialog()
  {
    if ((pd != null) && (pd.isShowing())) {
      pd.dismiss();
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
  
  protected void showSimpleProgressDialog(String paramString, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    if ((pd != null) && (pd.isShowing())) {
      pd.dismiss();
    }
    String str = paramString;
    if (paramString == null) {
      str = "Loading...";
    }
    pd = ProgressDialog.show(getActivity(), null, str, true, true, paramOnCancelListener);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/fragment/GojekFragmentBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */