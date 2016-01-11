package android.support.design.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

@TargetApi(21)
class FloatingActionButtonLollipop
  extends FloatingActionButtonHoneycombMr1
{
  private Interpolator mInterpolator;
  
  FloatingActionButtonLollipop(View paramView, ShadowViewDelegate paramShadowViewDelegate)
  {
    super(paramView, paramShadowViewDelegate);
    if (!paramView.isInEditMode()) {
      this.mInterpolator = AnimationUtils.loadInterpolator(this.mView.getContext(), 17563661);
    }
  }
  
  private Animator setupAnimator(Animator paramAnimator)
  {
    paramAnimator.setInterpolator(this.mInterpolator);
    return paramAnimator;
  }
  
  void jumpDrawableToCurrentState() {}
  
  CircularBorderDrawable newCircularDrawable()
  {
    return new CircularBorderDrawableLollipop();
  }
  
  void onDrawableStateChanged(int[] paramArrayOfInt) {}
  
  boolean requirePreDrawListener()
  {
    return false;
  }
  
  void setBackgroundDrawable(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int paramInt1, int paramInt2)
  {
    this.mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
    DrawableCompat.setTintList(this.mShapeDrawable, paramColorStateList);
    if (paramMode != null) {
      DrawableCompat.setTintMode(this.mShapeDrawable, paramMode);
    }
    if (paramInt2 > 0) {
      this.mBorderDrawable = createBorderDrawable(paramInt2, paramColorStateList);
    }
    for (paramColorStateList = new LayerDrawable(new Drawable[] { this.mBorderDrawable, this.mShapeDrawable });; paramColorStateList = this.mShapeDrawable)
    {
      this.mRippleDrawable = new RippleDrawable(ColorStateList.valueOf(paramInt1), paramColorStateList, null);
      this.mShadowViewDelegate.setBackgroundDrawable(this.mRippleDrawable);
      this.mShadowViewDelegate.setShadowPadding(0, 0, 0, 0);
      return;
      this.mBorderDrawable = null;
    }
  }
  
  public void setElevation(float paramFloat)
  {
    ViewCompat.setElevation(this.mView, paramFloat);
  }
  
  void setPressedTranslationZ(float paramFloat)
  {
    StateListAnimator localStateListAnimator = new StateListAnimator();
    localStateListAnimator.addState(PRESSED_ENABLED_STATE_SET, setupAnimator(ObjectAnimator.ofFloat(this.mView, "translationZ", new float[] { paramFloat })));
    localStateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, setupAnimator(ObjectAnimator.ofFloat(this.mView, "translationZ", new float[] { paramFloat })));
    localStateListAnimator.addState(EMPTY_STATE_SET, setupAnimator(ObjectAnimator.ofFloat(this.mView, "translationZ", new float[] { 0.0F })));
    this.mView.setStateListAnimator(localStateListAnimator);
  }
  
  void setRippleColor(int paramInt)
  {
    if ((this.mRippleDrawable instanceof RippleDrawable))
    {
      ((RippleDrawable)this.mRippleDrawable).setColor(ColorStateList.valueOf(paramInt));
      return;
    }
    super.setRippleColor(paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/FloatingActionButtonLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */