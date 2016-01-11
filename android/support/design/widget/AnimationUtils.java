package android.support.design.widget;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

class AnimationUtils
{
  static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
  static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR;
  static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
  
  static
  {
    FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
  }
  
  static float lerp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat2 - paramFloat1) * paramFloat3 + paramFloat1;
  }
  
  static int lerp(int paramInt1, int paramInt2, float paramFloat)
  {
    return Math.round((paramInt2 - paramInt1) * paramFloat) + paramInt1;
  }
  
  static class AnimationListenerAdapter
    implements Animation.AnimationListener
  {
    public void onAnimationEnd(Animation paramAnimation) {}
    
    public void onAnimationRepeat(Animation paramAnimation) {}
    
    public void onAnimationStart(Animation paramAnimation) {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/AnimationUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */