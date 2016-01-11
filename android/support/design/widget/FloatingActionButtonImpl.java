package android.support.design.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.design.R.color;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

abstract class FloatingActionButtonImpl
{
  static final int[] EMPTY_STATE_SET = new int[0];
  static final int[] FOCUSED_ENABLED_STATE_SET;
  static final int[] PRESSED_ENABLED_STATE_SET = { 16842919, 16842910 };
  static final int SHOW_HIDE_ANIM_DURATION = 200;
  private ViewTreeObserver.OnPreDrawListener mPreDrawListener;
  final ShadowViewDelegate mShadowViewDelegate;
  final View mView;
  
  static
  {
    FOCUSED_ENABLED_STATE_SET = new int[] { 16842908, 16842910 };
  }
  
  FloatingActionButtonImpl(View paramView, ShadowViewDelegate paramShadowViewDelegate)
  {
    this.mView = paramView;
    this.mShadowViewDelegate = paramShadowViewDelegate;
  }
  
  private void ensurePreDrawListener()
  {
    if (this.mPreDrawListener == null) {
      this.mPreDrawListener = new ViewTreeObserver.OnPreDrawListener()
      {
        public boolean onPreDraw()
        {
          FloatingActionButtonImpl.this.onPreDraw();
          return true;
        }
      };
    }
  }
  
  CircularBorderDrawable createBorderDrawable(int paramInt, ColorStateList paramColorStateList)
  {
    Resources localResources = this.mView.getResources();
    CircularBorderDrawable localCircularBorderDrawable = newCircularDrawable();
    localCircularBorderDrawable.setGradientColors(localResources.getColor(R.color.design_fab_stroke_top_outer_color), localResources.getColor(R.color.design_fab_stroke_top_inner_color), localResources.getColor(R.color.design_fab_stroke_end_inner_color), localResources.getColor(R.color.design_fab_stroke_end_outer_color));
    localCircularBorderDrawable.setBorderWidth(paramInt);
    localCircularBorderDrawable.setBorderTint(paramColorStateList);
    return localCircularBorderDrawable;
  }
  
  GradientDrawable createShapeDrawable()
  {
    GradientDrawable localGradientDrawable = new GradientDrawable();
    localGradientDrawable.setShape(1);
    localGradientDrawable.setColor(-1);
    return localGradientDrawable;
  }
  
  abstract void hide(@Nullable InternalVisibilityChangedListener paramInternalVisibilityChangedListener);
  
  abstract void jumpDrawableToCurrentState();
  
  CircularBorderDrawable newCircularDrawable()
  {
    return new CircularBorderDrawable();
  }
  
  void onAttachedToWindow()
  {
    if (requirePreDrawListener())
    {
      ensurePreDrawListener();
      this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
    }
  }
  
  void onDetachedFromWindow()
  {
    if (this.mPreDrawListener != null)
    {
      this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
      this.mPreDrawListener = null;
    }
  }
  
  abstract void onDrawableStateChanged(int[] paramArrayOfInt);
  
  void onPreDraw() {}
  
  boolean requirePreDrawListener()
  {
    return false;
  }
  
  abstract void setBackgroundDrawable(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int paramInt1, int paramInt2);
  
  abstract void setBackgroundTintList(ColorStateList paramColorStateList);
  
  abstract void setBackgroundTintMode(PorterDuff.Mode paramMode);
  
  abstract void setElevation(float paramFloat);
  
  abstract void setPressedTranslationZ(float paramFloat);
  
  abstract void setRippleColor(int paramInt);
  
  abstract void show(@Nullable InternalVisibilityChangedListener paramInternalVisibilityChangedListener);
  
  static abstract interface InternalVisibilityChangedListener
  {
    public abstract void onHidden();
    
    public abstract void onShown();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/FloatingActionButtonImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */