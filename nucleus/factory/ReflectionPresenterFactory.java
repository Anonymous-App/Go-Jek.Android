package nucleus.factory;

import android.support.annotation.Nullable;
import nucleus.presenter.Presenter;

public class ReflectionPresenterFactory<P extends Presenter>
  implements PresenterFactory<P>
{
  private Class<P> presenterClass;
  
  public ReflectionPresenterFactory(Class<P> paramClass)
  {
    this.presenterClass = paramClass;
  }
  
  @Nullable
  public static <P extends Presenter> ReflectionPresenterFactory<P> fromViewClass(Class<?> paramClass)
  {
    paramClass = (RequiresPresenter)paramClass.getAnnotation(RequiresPresenter.class);
    if (paramClass == null) {}
    for (paramClass = null; paramClass == null; paramClass = paramClass.value()) {
      return null;
    }
    return new ReflectionPresenterFactory(paramClass);
  }
  
  public P createPresenter()
  {
    try
    {
      Presenter localPresenter = (Presenter)this.presenterClass.newInstance();
      return localPresenter;
    }
    catch (Exception localException)
    {
      throw new RuntimeException(localException);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/factory/ReflectionPresenterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */