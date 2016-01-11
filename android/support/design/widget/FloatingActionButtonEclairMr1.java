package android.support.design.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.Nullable;
import android.support.design.R.anim;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class FloatingActionButtonEclairMr1
  extends FloatingActionButtonImpl
{
  private int mAnimationDuration;
  CircularBorderDrawable mBorderDrawable;
  private float mElevation;
  private boolean mIsHiding;
  private float mPressedTranslationZ;
  Drawable mRippleDrawable;
  ShadowDrawableWrapper mShadowDrawable;
  Drawable mShapeDrawable;
  private StateListAnimator mStateListAnimator;
  
  FloatingActionButtonEclairMr1(View paramView, ShadowViewDelegate paramShadowViewDelegate)
  {
    super(paramView, paramShadowViewDelegate);
    this.mAnimationDuration = paramView.getResources().getInteger(17694720);
    this.mStateListAnimator = new StateListAnimator();
    this.mStateListAnimator.setTarget(paramView);
    this.mStateListAnimator.addState(PRESSED_ENABLED_STATE_SET, setupAnimation(new ElevateToTranslationZAnimation(null)));
    this.mStateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, setupAnimation(new ElevateToTranslationZAnimation(null)));
    this.mStateListAnimator.addState(EMPTY_STATE_SET, setupAnimation(new ResetElevationAnimation(null)));
  }
  
  private static ColorStateList createColorStateList(int paramInt)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    arrayOfInt[0] = FOCUSED_ENABLED_STATE_SET;
    arrayOfInt1[0] = paramInt;
    int i = 0 + 1;
    arrayOfInt[i] = PRESSED_ENABLED_STATE_SET;
    arrayOfInt1[i] = paramInt;
    paramInt = i + 1;
    arrayOfInt[paramInt] = new int[0];
    arrayOfInt1[paramInt] = 0;
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private Animation setupAnimation(Animation paramAnimation)
  {
    paramAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    paramAnimation.setDuration(this.mAnimationDuration);
    return paramAnimation;
  }
  
  private void updatePadding()
  {
    Rect localRect = new Rect();
    this.mShadowDrawable.getPadding(localRect);
    this.mShadowViewDelegate.setShadowPadding(localRect.left, localRect.top, localRect.right, localRect.bottom);
  }
  
  void hide(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener paramInternalVisibilityChangedListener)
  {
    if ((this.mIsHiding) || (this.mView.getVisibility() != 0))
    {
      if (paramInternalVisibilityChangedListener != null) {
        paramInternalVisibilityChangedListener.onHidden();
      }
      return;
    }
    Animation localAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.design_fab_out);
    localAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    localAnimation.setDuration(200L);
    localAnimation.setAnimationListener(new AnimationUtils.AnimationListenerAdapter()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        FloatingActionButtonEclairMr1.access$202(FloatingActionButtonEclairMr1.this, false);
        FloatingActionButtonEclairMr1.this.mView.setVisibility(8);
        if (paramInternalVisibilityChangedListener != null) {
          paramInternalVisibilityChangedListener.onHidden();
        }
      }
      
      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        FloatingActionButtonEclairMr1.access$202(FloatingActionButtonEclairMr1.this, true);
      }
    });
    this.mView.startAnimation(localAnimation);
  }
  
  void jumpDrawableToCurrentState()
  {
    this.mStateListAnimator.jumpToCurrentState();
  }
  
  void onDrawableStateChanged(int[] paramArrayOfInt)
  {
    this.mStateListAnimator.setState(paramArrayOfInt);
  }
  
  void setBackgroundDrawable(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int paramInt1, int paramInt2)
  {
    this.mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
    DrawableCompat.setTintList(this.mShapeDrawable, paramColorStateList);
    if (paramMode != null) {
      DrawableCompat.setTintMode(this.mShapeDrawable, paramMode);
    }
    this.mRippleDrawable = DrawableCompat.wrap(createShapeDrawable());
    DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(paramInt1));
    DrawableCompat.setTintMode(this.mRippleDrawable, PorterDuff.Mode.MULTIPLY);
    if (paramInt2 > 0)
    {
      this.mBorderDrawable = createBorderDrawable(paramInt2, paramColorStateList);
      paramColorStateList = new Drawable[3];
      paramColorStateList[0] = this.mBorderDrawable;
      paramColorStateList[1] = this.mShapeDrawable;
      paramColorStateList[2] = this.mRippleDrawable;
    }
    for (;;)
    {
      this.mShadowDrawable = new ShadowDrawableWrapper(this.mView.getResources(), new LayerDrawable(paramColorStateList), this.mShadowViewDelegate.getRadius(), this.mElevation, this.mElevation + this.mPressedTranslationZ);
      this.mShadowDrawable.setAddPaddingForCorners(false);
      this.mShadowViewDelegate.setBackgroundDrawable(this.mShadowDrawable);
      updatePadding();
      return;
      this.mBorderDrawable = null;
      paramColorStateList = new Drawable[2];
      paramColorStateList[0] = this.mShapeDrawable;
      paramColorStateList[1] = this.mRippleDrawable;
    }
  }
  
  void setBackgroundTintList(ColorStateList paramColorStateList)
  {
    DrawableCompat.setTintList(this.mShapeDrawable, paramColorStateList);
    if (this.mBorderDrawable != null) {
      this.mBorderDrawable.setBorderTint(paramColorStateList);
    }
  }
  
  void setBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    DrawableCompat.setTintMode(this.mShapeDrawable, paramMode);
  }
  
  void setElevation(float paramFloat)
  {
    if ((this.mElevation != paramFloat) && (this.mShadowDrawable != null))
    {
      this.mShadowDrawable.setShadowSize(paramFloat, this.mPressedTranslationZ + paramFloat);
      this.mElevation = paramFloat;
      updatePadding();
    }
  }
  
  void setPressedTranslationZ(float paramFloat)
  {
    if ((this.mPressedTranslationZ != paramFloat) && (this.mShadowDrawable != null))
    {
      this.mPressedTranslationZ = paramFloat;
      this.mShadowDrawable.setMaxShadowSize(this.mElevation + paramFloat);
      updatePadding();
    }
  }
  
  void setRippleColor(int paramInt)
  {
    DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(paramInt));
  }
  
  void show(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener paramInternalVisibilityChangedListener)
  {
    if ((this.mView.getVisibility() != 0) || (this.mIsHiding))
    {
      this.mView.clearAnimation();
      this.mView.setVisibility(0);
      localAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.design_fab_in);
      localAnimation.setDuration(200L);
      localAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
      localAnimation.setAnimationListener(new AnimationUtils.AnimationListenerAdapter()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (paramInternalVisibilityChangedListener != null) {
            paramInternalVisibilityChangedListener.onShown();
          }
        }
      });
      this.mView.startAnimation(localAnimation);
    }
    while (paramInternalVisibilityChangedListener == null)
    {
      Animation localAnimation;
      return;
    }
    paramInternalVisibilityChangedListener.onShown();
  }
  
  private abstract class BaseShadowAnimation
    extends Animation
  {
    private float mShadowSizeDiff;
    private float mShadowSizeStart;
    
    private BaseShadowAnimation() {}
    
    protected void applyTransformation(float paramFloat, Transformation paramTransformation)
    {
      FloatingActionButtonEclairMr1.this.mShadowDrawable.setShadowSize(this.mShadowSizeStart + this.mShadowSizeDiff * paramFloat);
    }
    
    protected abstract float getTargetShadowSize();
    
    public void reset()
    {
      super.reset();
      this.mShadowSizeStart = FloatingActionButtonEclairMr1.this.mShadowDrawable.getShadowSize();
      this.mShadowSizeDiff = (getTargetShadowSize() - this.mShadowSizeStart);
    }
  }
  
  private class ElevateToTranslationZAnimation
    extends FloatingActionButtonEclairMr1.BaseShadowAnimation
  {
    private ElevateToTranslationZAnimation()
    {
      super(null);
    }
    
    protected float getTargetShadowSize()
    {
      return FloatingActionButtonEclairMr1.this.mElevation + FloatingActionButtonEclairMr1.this.mPressedTranslationZ;
    }
  }
  
  private class ResetElevationAnimation
    extends FloatingActionButtonEclairMr1.BaseShadowAnimation
  {
    private ResetElevationAnimation()
    {
      super(null);
    }
    
    protected float getTargetShadowSize()
    {
      return FloatingActionButtonEclairMr1.this.mElevation;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/FloatingActionButtonEclairMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */