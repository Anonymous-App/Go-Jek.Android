package nucleus.view;

import nucleus.factory.PresenterFactory;
import nucleus.presenter.Presenter;

public abstract interface ViewWithPresenter<P extends Presenter>
{
  public abstract P getPresenter();
  
  public abstract PresenterFactory<P> getPresenterFactory();
  
  public abstract void setPresenterFactory(PresenterFactory<P> paramPresenterFactory);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/view/ViewWithPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */