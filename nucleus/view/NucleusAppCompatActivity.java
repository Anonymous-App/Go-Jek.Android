package nucleus.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import nucleus.factory.PresenterFactory;
import nucleus.factory.ReflectionPresenterFactory;
import nucleus.presenter.Presenter;

public abstract class NucleusAppCompatActivity<P extends Presenter>
  extends AppCompatActivity
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
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle != null) {
      this.presenterDelegate.onRestoreInstanceState(paramBundle.getBundle("presenter_state"));
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    this.presenterDelegate.onPause(isFinishing());
  }
  
  protected void onResume()
  {
    super.onResume();
    this.presenterDelegate.onResume(this);
  }
  
  protected void onSaveInstanceState(@NonNull Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBundle("presenter_state", this.presenterDelegate.onSaveInstanceState());
  }
  
  public void setPresenterFactory(PresenterFactory<P> paramPresenterFactory)
  {
    this.presenterDelegate.setPresenterFactory(paramPresenterFactory);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/view/NucleusAppCompatActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */