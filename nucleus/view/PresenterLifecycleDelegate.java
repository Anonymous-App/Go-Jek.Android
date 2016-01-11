package nucleus.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import nucleus.factory.PresenterFactory;
import nucleus.factory.PresenterStorage;
import nucleus.presenter.Presenter;

public final class PresenterLifecycleDelegate<P extends Presenter>
{
  private static final String PRESENTER_ID_KEY = "presenter_id";
  private static final String PRESENTER_KEY = "presenter";
  @Nullable
  private Bundle bundle;
  @Nullable
  private P presenter;
  @Nullable
  private PresenterFactory<P> presenterFactory;
  
  public PresenterLifecycleDelegate(@Nullable PresenterFactory<P> paramPresenterFactory)
  {
    this.presenterFactory = paramPresenterFactory;
  }
  
  public P getPresenter()
  {
    Presenter localPresenter;
    if (this.presenterFactory != null)
    {
      if ((this.presenter == null) && (this.bundle != null)) {
        this.presenter = ((Presenter)PresenterStorage.INSTANCE.getPresenter(this.bundle.getString("presenter_id")));
      }
      if (this.presenter == null)
      {
        this.presenter = this.presenterFactory.createPresenter();
        PresenterStorage.INSTANCE.add(this.presenter);
        localPresenter = this.presenter;
        if (this.bundle != null) {
          break label102;
        }
      }
    }
    label102:
    for (Bundle localBundle = null;; localBundle = this.bundle.getBundle("presenter"))
    {
      localPresenter.create(localBundle);
      this.bundle = null;
      return this.presenter;
    }
  }
  
  @Nullable
  public PresenterFactory<P> getPresenterFactory()
  {
    return this.presenterFactory;
  }
  
  public void onPause(boolean paramBoolean)
  {
    if (this.presenter != null)
    {
      this.presenter.dropView();
      if (paramBoolean)
      {
        this.presenter.destroy();
        this.presenter = null;
      }
    }
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    if (this.presenter != null) {
      throw new IllegalArgumentException("onRestoreInstanceState() should be called before onResume()");
    }
    this.bundle = paramBundle;
  }
  
  public void onResume(Object paramObject)
  {
    getPresenter();
    if (this.presenter != null) {
      this.presenter.takeView(paramObject);
    }
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle1 = new Bundle();
    getPresenter();
    if (this.presenter != null)
    {
      Bundle localBundle2 = new Bundle();
      this.presenter.save(localBundle2);
      localBundle1.putBundle("presenter", localBundle2);
      localBundle1.putString("presenter_id", PresenterStorage.INSTANCE.getId(this.presenter));
    }
    return localBundle1;
  }
  
  public void setPresenterFactory(@Nullable PresenterFactory<P> paramPresenterFactory)
  {
    if (this.presenter != null) {
      throw new IllegalArgumentException("setPresenterFactory() should be called before onResume()");
    }
    this.presenterFactory = paramPresenterFactory;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/view/PresenterLifecycleDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */