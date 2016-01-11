package com.gojek.gotix.presenter;

import android.content.Context;
import android.os.Bundle;
import com.gojek.gotix.helper.GotixEngine;
import lib.gojek.base.AbstractBasePresenter;
import lib.gojek.base.networks.GeneralNetworkHandler;
import nucleus.view.ViewWithPresenter;

public abstract class GotixBasePresenter<T extends ViewWithPresenter>
  extends AbstractBasePresenter<T>
  implements GeneralNetworkHandler
{
  private Context context;
  
  protected Context getContext()
  {
    return this.context;
  }
  
  protected boolean isViewExists()
  {
    return getView() != null;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = GotixEngine.getInstance().getContext();
  }
  
  protected void onTakeView(T paramT)
  {
    super.onTakeView(paramT);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixBasePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */