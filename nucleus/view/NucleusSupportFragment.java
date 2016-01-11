package nucleus.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import nucleus.factory.PresenterFactory;
import nucleus.factory.ReflectionPresenterFactory;
import nucleus.presenter.Presenter;

@Instrumented
public abstract class NucleusSupportFragment<P extends Presenter>
  extends Fragment
  implements ViewWithPresenter<P>
{
  private static final String PRESENTER_STATE_KEY = "presenter_state";
  private PresenterLifecycleDelegate<P> presenterDelegate = new PresenterLifecycleDelegate(ReflectionPresenterFactory.fromViewClass(getClass()));
  
  public P getPresenter()
  {
    return this.presenterDelegate.getPresenter();
  }
  
  public PresenterFactory<P> getPresenterFactory()
  {
    return this.presenterDelegate.getPresenterFactory();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("NucleusSupportFragment");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "NucleusSupportFragment#onCreate", null);
      super.onCreate(paramBundle);
      if (paramBundle != null) {
        this.presenterDelegate.onRestoreInstanceState(paramBundle.getBundle("presenter_state"));
      }
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "NucleusSupportFragment#onCreate", null);
      }
    }
  }
  
  public void onPause()
  {
    super.onPause();
    this.presenterDelegate.onPause(getActivity().isFinishing());
  }
  
  public void onResume()
  {
    super.onResume();
    this.presenterDelegate.onResume(this);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBundle("presenter_state", this.presenterDelegate.onSaveInstanceState());
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
  
  public void setPresenterFactory(PresenterFactory<P> paramPresenterFactory)
  {
    this.presenterDelegate.setPresenterFactory(paramPresenterFactory);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/view/NucleusSupportFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */