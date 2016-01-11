package nucleus.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Presenter<View>
{
  private CopyOnWriteArrayList<OnDestroyListener> onDestroyListeners = new CopyOnWriteArrayList();
  @Nullable
  private View view;
  
  public void addOnDestroyListener(OnDestroyListener paramOnDestroyListener)
  {
    this.onDestroyListeners.add(paramOnDestroyListener);
  }
  
  public void create(Bundle paramBundle)
  {
    onCreate(paramBundle);
  }
  
  public void destroy()
  {
    Iterator localIterator = this.onDestroyListeners.iterator();
    while (localIterator.hasNext()) {
      ((OnDestroyListener)localIterator.next()).onDestroy();
    }
    onDestroy();
  }
  
  public void dropView()
  {
    onDropView();
    this.view = null;
  }
  
  @Nullable
  public View getView()
  {
    return (View)this.view;
  }
  
  protected void onCreate(@Nullable Bundle paramBundle) {}
  
  protected void onDestroy() {}
  
  protected void onDropView() {}
  
  protected void onSave(Bundle paramBundle) {}
  
  protected void onTakeView(View paramView) {}
  
  public void removeOnDestroyListener(OnDestroyListener paramOnDestroyListener)
  {
    this.onDestroyListeners.remove(paramOnDestroyListener);
  }
  
  public void save(Bundle paramBundle)
  {
    onSave(paramBundle);
  }
  
  public void takeView(View paramView)
  {
    this.view = paramView;
    onTakeView(paramView);
  }
  
  public static abstract interface OnDestroyListener
  {
    public abstract void onDestroy();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/presenter/Presenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */