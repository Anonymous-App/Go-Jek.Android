package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import java.util.List;

abstract class HeaderScrollingViewBehavior
  extends ViewOffsetBehavior<View>
{
  public HeaderScrollingViewBehavior() {}
  
  public HeaderScrollingViewBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  abstract View findFirstDependency(List<View> paramList);
  
  int getScrollRange(View paramView)
  {
    return paramView.getMeasuredHeight();
  }
  
  public boolean onMeasureChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = paramView.getLayoutParams().height;
    if ((j == -1) || (j == -2))
    {
      Object localObject = paramCoordinatorLayout.getDependencies(paramView);
      if (((List)localObject).isEmpty()) {
        return false;
      }
      localObject = findFirstDependency((List)localObject);
      if ((localObject != null) && (ViewCompat.isLaidOut((View)localObject)))
      {
        if (ViewCompat.getFitsSystemWindows((View)localObject)) {
          ViewCompat.setFitsSystemWindows(paramView, true);
        }
        int i = View.MeasureSpec.getSize(paramInt3);
        paramInt3 = i;
        if (i == 0) {
          paramInt3 = paramCoordinatorLayout.getHeight();
        }
        int k = ((View)localObject).getMeasuredHeight();
        int m = getScrollRange((View)localObject);
        if (j == -1) {}
        for (i = 1073741824;; i = Integer.MIN_VALUE)
        {
          paramCoordinatorLayout.onMeasureChild(paramView, paramInt1, paramInt2, View.MeasureSpec.makeMeasureSpec(paramInt3 - k + m, i), paramInt4);
          return true;
        }
      }
    }
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/HeaderScrollingViewBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */