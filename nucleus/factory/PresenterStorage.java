package nucleus.factory;

import java.util.HashMap;
import nucleus.presenter.Presenter;

public enum PresenterStorage
{
  INSTANCE;
  
  private HashMap<String, Presenter> idToPresenter = new HashMap();
  private HashMap<Presenter, String> presenterToId = new HashMap();
  
  private PresenterStorage() {}
  
  public void add(Presenter paramPresenter)
  {
    String str = paramPresenter.getClass().getSimpleName() + "/" + System.nanoTime() + "/" + (int)(Math.random() * 2.147483647E9D);
    this.idToPresenter.put(str, paramPresenter);
    this.presenterToId.put(paramPresenter, str);
    paramPresenter.addOnDestroyListener(new PresenterStorage.1(this, paramPresenter));
  }
  
  public void clear()
  {
    this.idToPresenter.clear();
    this.presenterToId.clear();
  }
  
  public String getId(Presenter paramPresenter)
  {
    return (String)this.presenterToId.get(paramPresenter);
  }
  
  public <P> P getPresenter(String paramString)
  {
    return (P)this.idToPresenter.get(paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/factory/PresenterStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */