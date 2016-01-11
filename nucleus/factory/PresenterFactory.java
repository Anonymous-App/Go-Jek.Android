package nucleus.factory;

import nucleus.presenter.Presenter;

public abstract interface PresenterFactory<P extends Presenter>
{
  public abstract P createPresenter();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/factory/PresenterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */