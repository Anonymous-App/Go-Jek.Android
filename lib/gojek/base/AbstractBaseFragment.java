package lib.gojek.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import nucleus.view.NucleusSupportFragment;

public abstract class AbstractBaseFragment<T extends AbstractBasePresenter>
  extends NucleusSupportFragment<T>
{
  protected abstract int getLayout();
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(getLayout(), paramViewGroup, false);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/AbstractBaseFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */