package lib.gojek.base;

import android.os.Bundle;
import nucleus.view.NucleusAppCompatActivity;

public abstract class AbstractBaseActivity<T extends AbstractBasePresenter>
  extends NucleusAppCompatActivity<T>
{
  protected abstract int getLayout();
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(getLayout());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/AbstractBaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */