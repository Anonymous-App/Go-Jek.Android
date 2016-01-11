package android.support.v7.view;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewPropertyAnimatorCompatSet
{
  private final ArrayList<ViewPropertyAnimatorCompat> mAnimators = new ArrayList();
  private long mDuration = -1L;
  private Interpolator mInterpolator;
  private boolean mIsStarted;
  private ViewPropertyAnimatorListener mListener;
  private final ViewPropertyAnimatorListenerAdapter mProxyListener = new ViewPropertyAnimatorListenerAdapter()
  {
    private int mProxyEndCount = 0;
    private boolean mProxyStarted = false;
    
    public void onAnimationEnd(View paramAnonymousView)
    {
      int i = this.mProxyEndCount + 1;
      this.mProxyEndCount = i;
      if (i == ViewPropertyAnimatorCompatSet.this.mAnimators.size())
      {
        if (ViewPropertyAnimatorCompatSet.this.mListener != null) {
          ViewPropertyAnimatorCompatSet.this.mListener.onAnimationEnd(null);
        }
        onEnd();
      }
    }
    
    public void onAnimationStart(View paramAnonymousView)
    {
      if (this.mProxyStarted) {}
      do
      {
        return;
        this.mProxyStarted = true;
      } while (ViewPropertyAnimatorCompatSet.this.mListener == null);
      ViewPropertyAnimatorCompatSet.this.mListener.onAnimationStart(null);
    }
    
    void onEnd()
    {
      this.mProxyEndCount = 0;
      this.mProxyStarted = false;
      ViewPropertyAnimatorCompatSet.this.onAnimationsEnded();
    }
  };
  
  private void onAnimationsEnded()
  {
    this.mIsStarted = false;
  }
  
  public void cancel()
  {
    if (!this.mIsStarted) {
      return;
    }
    Iterator localIterator = this.mAnimators.iterator();
    while (localIterator.hasNext()) {
      ((ViewPropertyAnimatorCompat)localIterator.next()).cancel();
    }
    this.mIsStarted = false;
  }
  
  public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat)
  {
    if (!this.mIsStarted) {
      this.mAnimators.add(paramViewPropertyAnimatorCompat);
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat1, ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat2)
  {
    this.mAnimators.add(paramViewPropertyAnimatorCompat1);
    paramViewPropertyAnimatorCompat2.setStartDelay(paramViewPropertyAnimatorCompat1.getDuration());
    this.mAnimators.add(paramViewPropertyAnimatorCompat2);
    return this;
  }
  
  public ViewPropertyAnimatorCompatSet setDuration(long paramLong)
  {
    if (!this.mIsStarted) {
      this.mDuration = paramLong;
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator paramInterpolator)
  {
    if (!this.mIsStarted) {
      this.mInterpolator = paramInterpolator;
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
  {
    if (!this.mIsStarted) {
      this.mListener = paramViewPropertyAnimatorListener;
    }
    return this;
  }
  
  public void start()
  {
    if (this.mIsStarted) {
      return;
    }
    Iterator localIterator = this.mAnimators.iterator();
    while (localIterator.hasNext())
    {
      ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat)localIterator.next();
      if (this.mDuration >= 0L) {
        localViewPropertyAnimatorCompat.setDuration(this.mDuration);
      }
      if (this.mInterpolator != null) {
        localViewPropertyAnimatorCompat.setInterpolator(this.mInterpolator);
      }
      if (this.mListener != null) {
        localViewPropertyAnimatorCompat.setListener(this.mProxyListener);
      }
      localViewPropertyAnimatorCompat.start();
    }
    this.mIsStarted = true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/view/ViewPropertyAnimatorCompatSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */