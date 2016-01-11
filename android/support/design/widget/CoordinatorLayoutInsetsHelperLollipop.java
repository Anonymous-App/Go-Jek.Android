package android.support.design.widget;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.view.View;

class CoordinatorLayoutInsetsHelperLollipop
  implements CoordinatorLayoutInsetsHelper
{
  public void setupForWindowInsets(View paramView, OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener)
  {
    if (ViewCompat.getFitsSystemWindows(paramView))
    {
      ViewCompat.setOnApplyWindowInsetsListener(paramView, paramOnApplyWindowInsetsListener);
      paramView.setSystemUiVisibility(1280);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/CoordinatorLayoutInsetsHelperLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */