package android.support.v7.app;

import android.content.Context;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Window;
import android.view.Window.Callback;

class AppCompatDelegateImplV23
  extends AppCompatDelegateImplV14
{
  AppCompatDelegateImplV23(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  Window.Callback wrapWindowCallback(Window.Callback paramCallback)
  {
    return new AppCompatWindowCallbackV23(paramCallback);
  }
  
  class AppCompatWindowCallbackV23
    extends AppCompatDelegateImplV14.AppCompatWindowCallbackV14
  {
    AppCompatWindowCallbackV23(Window.Callback paramCallback)
    {
      super(paramCallback);
    }
    
    public ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback)
    {
      return null;
    }
    
    public ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback, int paramInt)
    {
      if (AppCompatDelegateImplV23.this.isHandleNativeActionModesEnabled()) {}
      switch (paramInt)
      {
      default: 
        return super.onWindowStartingActionMode(paramCallback, paramInt);
      }
      return startAsSupportActionMode(paramCallback);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/app/AppCompatDelegateImplV23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */