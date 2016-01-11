package android.support.v7.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

@TargetApi(16)
class MenuItemWrapperJB
  extends MenuItemWrapperICS
{
  MenuItemWrapperJB(Context paramContext, SupportMenuItem paramSupportMenuItem)
  {
    super(paramContext, paramSupportMenuItem);
  }
  
  MenuItemWrapperICS.ActionProviderWrapper createActionProviderWrapper(ActionProvider paramActionProvider)
  {
    return new ActionProviderWrapperJB(this.mContext, paramActionProvider);
  }
  
  class ActionProviderWrapperJB
    extends MenuItemWrapperICS.ActionProviderWrapper
    implements android.view.ActionProvider.VisibilityListener
  {
    android.support.v4.view.ActionProvider.VisibilityListener mListener;
    
    public ActionProviderWrapperJB(Context paramContext, ActionProvider paramActionProvider)
    {
      super(paramContext, paramActionProvider);
    }
    
    public boolean isVisible()
    {
      return this.mInner.isVisible();
    }
    
    public void onActionProviderVisibilityChanged(boolean paramBoolean)
    {
      if (this.mListener != null) {
        this.mListener.onActionProviderVisibilityChanged(paramBoolean);
      }
    }
    
    public View onCreateActionView(MenuItem paramMenuItem)
    {
      return this.mInner.onCreateActionView(paramMenuItem);
    }
    
    public boolean overridesItemVisibility()
    {
      return this.mInner.overridesItemVisibility();
    }
    
    public void refreshVisibility()
    {
      this.mInner.refreshVisibility();
    }
    
    public void setVisibilityListener(android.support.v4.view.ActionProvider.VisibilityListener paramVisibilityListener)
    {
      this.mListener = paramVisibilityListener;
      ActionProvider localActionProvider = this.mInner;
      if (paramVisibilityListener != null) {}
      for (paramVisibilityListener = this;; paramVisibilityListener = null)
      {
        localActionProvider.setVisibilityListener(paramVisibilityListener);
        return;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/view/menu/MenuItemWrapperJB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */