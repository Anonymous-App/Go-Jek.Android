package android.support.design.widget;

import android.os.Build.VERSION;
import android.view.View;

class ViewUtils
{
  static final ValueAnimatorCompat.Creator DEFAULT_ANIMATOR_CREATOR = new ValueAnimatorCompat.Creator()
  {
    public ValueAnimatorCompat createAnimator()
    {
      if (Build.VERSION.SDK_INT >= 12) {}
      for (Object localObject = new ValueAnimatorCompatImplHoneycombMr1();; localObject = new ValueAnimatorCompatImplEclairMr1()) {
        return new ValueAnimatorCompat((ValueAnimatorCompat.Impl)localObject);
      }
    }
  };
  private static final ViewUtilsImpl IMPL = new ViewUtilsImplBase(null);
  
  static
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      IMPL = new ViewUtilsImplLollipop(null);
      return;
    }
  }
  
  static ValueAnimatorCompat createAnimator()
  {
    return DEFAULT_ANIMATOR_CREATOR.createAnimator();
  }
  
  static void setBoundsViewOutlineProvider(View paramView)
  {
    IMPL.setBoundsViewOutlineProvider(paramView);
  }
  
  private static abstract interface ViewUtilsImpl
  {
    public abstract void setBoundsViewOutlineProvider(View paramView);
  }
  
  private static class ViewUtilsImplBase
    implements ViewUtils.ViewUtilsImpl
  {
    public void setBoundsViewOutlineProvider(View paramView) {}
  }
  
  private static class ViewUtilsImplLollipop
    implements ViewUtils.ViewUtilsImpl
  {
    public void setBoundsViewOutlineProvider(View paramView)
    {
      ViewUtilsLollipop.setBoundsViewOutlineProvider(paramView);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/ViewUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */