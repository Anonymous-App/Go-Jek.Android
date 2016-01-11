package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewPropertyAnimator;

class FloatingActionButtonHoneycombMr1
  extends FloatingActionButtonEclairMr1
{
  private boolean mIsHiding;
  
  FloatingActionButtonHoneycombMr1(View paramView, ShadowViewDelegate paramShadowViewDelegate)
  {
    super(paramView, paramShadowViewDelegate);
  }
  
  private void updateFromViewRotation(float paramFloat)
  {
    if (this.mShadowDrawable != null) {
      this.mShadowDrawable.setRotation(-paramFloat);
    }
    if (this.mBorderDrawable != null) {
      this.mBorderDrawable.setRotation(-paramFloat);
    }
  }
  
  void hide(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener paramInternalVisibilityChangedListener)
  {
    if ((this.mIsHiding) || (this.mView.getVisibility() != 0)) {
      if (paramInternalVisibilityChangedListener != null) {
        paramInternalVisibilityChangedListener.onHidden();
      }
    }
    do
    {
      return;
      if ((ViewCompat.isLaidOut(this.mView)) && (!this.mView.isInEditMode())) {
        break;
      }
      this.mView.setVisibility(8);
    } while (paramInternalVisibilityChangedListener == null);
    paramInternalVisibilityChangedListener.onHidden();
    return;
    this.mView.animate().scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter()
    {
      public void onAnimationCancel(Animator paramAnonymousAnimator)
      {
        FloatingActionButtonHoneycombMr1.access$002(FloatingActionButtonHoneycombMr1.this, false);
      }
      
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        FloatingActionButtonHoneycombMr1.access$002(FloatingActionButtonHoneycombMr1.this, false);
        FloatingActionButtonHoneycombMr1.this.mView.setVisibility(8);
        if (paramInternalVisibilityChangedListener != null) {
          paramInternalVisibilityChangedListener.onHidden();
        }
      }
      
      public void onAnimationStart(Animator paramAnonymousAnimator)
      {
        FloatingActionButtonHoneycombMr1.access$002(FloatingActionButtonHoneycombMr1.this, true);
        FloatingActionButtonHoneycombMr1.this.mView.setVisibility(0);
      }
    });
  }
  
  void onPreDraw()
  {
    updateFromViewRotation(this.mView.getRotation());
  }
  
  boolean requirePreDrawListener()
  {
    return true;
  }
  
  void show(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener paramInternalVisibilityChangedListener)
  {
    if (this.mView.getVisibility() != 0)
    {
      if ((!ViewCompat.isLaidOut(this.mView)) || (this.mView.isInEditMode())) {
        break label99;
      }
      this.mView.setAlpha(0.0F);
      this.mView.setScaleY(0.0F);
      this.mView.setScaleX(0.0F);
      this.mView.animate().scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          if (paramInternalVisibilityChangedListener != null) {
            paramInternalVisibilityChangedListener.onShown();
          }
        }
        
        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
          FloatingActionButtonHoneycombMr1.this.mView.setVisibility(0);
        }
      });
    }
    label99:
    do
    {
      return;
      this.mView.setVisibility(0);
      this.mView.setAlpha(1.0F);
      this.mView.setScaleY(1.0F);
      this.mView.setScaleX(1.0F);
    } while (paramInternalVisibilityChangedListener == null);
    paramInternalVisibilityChangedListener.onShown();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/FloatingActionButtonHoneycombMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */