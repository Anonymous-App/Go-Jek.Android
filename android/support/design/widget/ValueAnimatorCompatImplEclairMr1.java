package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

class ValueAnimatorCompatImplEclairMr1
  extends ValueAnimatorCompat.Impl
{
  private static final int DEFAULT_DURATION = 200;
  private static final int HANDLER_DELAY = 10;
  private static final Handler sHandler = new Handler(Looper.getMainLooper());
  private float mAnimatedFraction;
  private int mDuration = 200;
  private final float[] mFloatValues = new float[2];
  private final int[] mIntValues = new int[2];
  private Interpolator mInterpolator;
  private boolean mIsRunning;
  private ValueAnimatorCompat.Impl.AnimatorListenerProxy mListener;
  private final Runnable mRunnable = new Runnable()
  {
    public void run()
    {
      ValueAnimatorCompatImplEclairMr1.this.update();
    }
  };
  private long mStartTime;
  private ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy mUpdateListener;
  
  private void update()
  {
    if (this.mIsRunning)
    {
      float f2 = (float)(SystemClock.uptimeMillis() - this.mStartTime) / this.mDuration;
      float f1 = f2;
      if (this.mInterpolator != null) {
        f1 = this.mInterpolator.getInterpolation(f2);
      }
      this.mAnimatedFraction = f1;
      if (this.mUpdateListener != null) {
        this.mUpdateListener.onAnimationUpdate();
      }
      if (SystemClock.uptimeMillis() >= this.mStartTime + this.mDuration)
      {
        this.mIsRunning = false;
        if (this.mListener != null) {
          this.mListener.onAnimationEnd();
        }
      }
    }
    if (this.mIsRunning) {
      sHandler.postDelayed(this.mRunnable, 10L);
    }
  }
  
  public void cancel()
  {
    this.mIsRunning = false;
    sHandler.removeCallbacks(this.mRunnable);
    if (this.mListener != null) {
      this.mListener.onAnimationCancel();
    }
  }
  
  public void end()
  {
    if (this.mIsRunning)
    {
      this.mIsRunning = false;
      sHandler.removeCallbacks(this.mRunnable);
      this.mAnimatedFraction = 1.0F;
      if (this.mUpdateListener != null) {
        this.mUpdateListener.onAnimationUpdate();
      }
      if (this.mListener != null) {
        this.mListener.onAnimationEnd();
      }
    }
  }
  
  public float getAnimatedFloatValue()
  {
    return AnimationUtils.lerp(this.mFloatValues[0], this.mFloatValues[1], getAnimatedFraction());
  }
  
  public float getAnimatedFraction()
  {
    return this.mAnimatedFraction;
  }
  
  public int getAnimatedIntValue()
  {
    return AnimationUtils.lerp(this.mIntValues[0], this.mIntValues[1], getAnimatedFraction());
  }
  
  public long getDuration()
  {
    return this.mDuration;
  }
  
  public boolean isRunning()
  {
    return this.mIsRunning;
  }
  
  public void setDuration(int paramInt)
  {
    this.mDuration = paramInt;
  }
  
  public void setFloatValues(float paramFloat1, float paramFloat2)
  {
    this.mFloatValues[0] = paramFloat1;
    this.mFloatValues[1] = paramFloat2;
  }
  
  public void setIntValues(int paramInt1, int paramInt2)
  {
    this.mIntValues[0] = paramInt1;
    this.mIntValues[1] = paramInt2;
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.mInterpolator = paramInterpolator;
  }
  
  public void setListener(ValueAnimatorCompat.Impl.AnimatorListenerProxy paramAnimatorListenerProxy)
  {
    this.mListener = paramAnimatorListenerProxy;
  }
  
  public void setUpdateListener(ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy paramAnimatorUpdateListenerProxy)
  {
    this.mUpdateListener = paramAnimatorUpdateListenerProxy;
  }
  
  public void start()
  {
    if (this.mIsRunning) {
      return;
    }
    if (this.mInterpolator == null) {
      this.mInterpolator = new AccelerateDecelerateInterpolator();
    }
    this.mStartTime = SystemClock.uptimeMillis();
    this.mIsRunning = true;
    if (this.mListener != null) {
      this.mListener.onAnimationStart();
    }
    sHandler.postDelayed(this.mRunnable, 10L);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/ValueAnimatorCompatImplEclairMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */