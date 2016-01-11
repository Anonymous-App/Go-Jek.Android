package android.support.v7.app;

import android.content.Context;
import android.support.v7.view.SupportActionModeWrapper.CallbackWrapper;
import android.view.ActionMode.Callback;
import android.view.Window;
import android.view.Window.Callback;

class AppCompatDelegateImplV14
  extends AppCompatDelegateImplV11
{
  private boolean mHandleNativeActionModes = true;
  
  AppCompatDelegateImplV14(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  public boolean isHandleNativeActionModesEnabled()
  {
    return this.mHandleNativeActionModes;
  }
  
  public void setHandleNativeActionModesEnabled(boolean paramBoolean)
  {
    this.mHandleNativeActionModes = paramBoolean;
  }
  
  Window.Callback wrapWindowCallback(Window.Callback paramCallback)
  {
    return new AppCompatWindowCallbackV14(paramCallback);
  }
  
  class AppCompatWindowCallbackV14
    extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase
  {
    AppCompatWindowCallbackV14(Window.Callback paramCallback)
    {
      super(paramCallback);
    }
    
    public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback)
    {
      if (AppCompatDelegateImplV14.this.isHandleNativeActionModesEnabled()) {
        return startAsSupportActionMode(paramCallback);
      }
      return super.onWindowStartingActionMode(paramCallback);
    }
    
    final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback paramCallback)
    {
      paramCallback = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImplV14.this.mContext, paramCallback);
      android.support.v7.view.ActionMode localActionMode = AppCompatDelegateImplV14.this.startSupportActionMode(paramCallback);
      if (localActionMode != null) {
        return paramCallback.getActionModeWrapper(localActionMode);
      }
      return null;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/app/AppCompatDelegateImplV14.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */