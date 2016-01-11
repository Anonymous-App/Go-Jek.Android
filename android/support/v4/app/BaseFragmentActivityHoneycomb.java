package android.support.v4.app;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;

abstract class BaseFragmentActivityHoneycomb
  extends BaseFragmentActivityDonut
{
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    View localView2 = dispatchFragmentsOnCreateView(paramView, paramString, paramContext, paramAttributeSet);
    View localView1 = localView2;
    if (localView2 == null)
    {
      localView1 = localView2;
      if (Build.VERSION.SDK_INT >= 11) {
        localView1 = super.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
      }
    }
    return localView1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v4/app/BaseFragmentActivityHoneycomb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */