package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.animation.Interpolator;

class ValueAnimatorCompatImplHoneycombMr1
  extends ValueAnimatorCompat.Impl
{
  final ValueAnimator mValueAnimator = new ValueAnimator();
  
  public void cancel()
  {
    this.mValueAnimator.cancel();
  }
  
  public void end()
  {
    this.mValueAnimator.end();
  }
  
  public float getAnimatedFloatValue()
  {
    return ((Float)this.mValueAnimator.getAnimatedValue()).floatValue();
  }
  
  public float getAnimatedFraction()
  {
    return this.mValueAnimator.getAnimatedFraction();
  }
  
  public int getAnimatedIntValue()
  {
    return ((Integer)this.mValueAnimator.getAnimatedValue()).intValue();
  }
  
  public long getDuration()
  {
    return this.mValueAnimator.getDuration();
  }
  
  public boolean isRunning()
  {
    return this.mValueAnimator.isRunning();
  }
  
  public void setDuration(int paramInt)
  {
    this.mValueAnimator.setDuration(paramInt);
  }
  
  public void setFloatValues(float paramFloat1, float paramFloat2)
  {
    this.mValueAnimator.setFloatValues(new float[] { paramFloat1, paramFloat2 });
  }
  
  public void setIntValues(int paramInt1, int paramInt2)
  {
    this.mValueAnimator.setIntValues(new int[] { paramInt1, paramInt2 });
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.mValueAnimator.setInterpolator(paramInterpolator);
  }
  
  public void setListener(final ValueAnimatorCompat.Impl.AnimatorListenerProxy paramAnimatorListenerProxy)
  {
    this.mValueAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationCancel(Animator paramAnonymousAnimator)
      {
        paramAnimatorListenerProxy.onAnimationCancel();
      }
      
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        paramAnimatorListenerProxy.onAnimationEnd();
      }
      
      public void onAnimationStart(Animator paramAnonymousAnimator)
      {
        paramAnimatorListenerProxy.onAnimationStart();
      }
    });
  }
  
  public void setUpdateListener(final ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy paramAnimatorUpdateListenerProxy)
  {
    this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        paramAnimatorUpdateListenerProxy.onAnimationUpdate();
      }
    });
  }
  
  public void start()
  {
    this.mValueAnimator.start();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/ValueAnimatorCompatImplHoneycombMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */