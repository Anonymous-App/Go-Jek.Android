package com.gojek.app.custom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout.LayoutParams;

public class FloatingActionButton
  extends View
{
  static final AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
  static final OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
  Context context;
  Bitmap mBitmap;
  Paint mButtonPaint;
  Paint mDrawablePaint;
  boolean mHidden = false;
  
  public FloatingActionButton(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init(-1);
  }
  
  public void hideFloatingActionButton()
  {
    if (!this.mHidden)
    {
      ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(this, "scaleX", new float[] { 1.0F, 0.0F });
      ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(this, "scaleY", new float[] { 1.0F, 0.0F });
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.playTogether(new Animator[] { localObjectAnimator1, localObjectAnimator2 });
      localAnimatorSet.setInterpolator(accelerateInterpolator);
      localAnimatorSet.setDuration(100L);
      localAnimatorSet.start();
      this.mHidden = true;
    }
  }
  
  public void init(int paramInt)
  {
    setWillNotDraw(false);
    setLayerType(1, null);
    this.mButtonPaint = new Paint(1);
    this.mButtonPaint.setColor(paramInt);
    this.mButtonPaint.setStyle(Paint.Style.FILL);
    this.mButtonPaint.setShadowLayer(10.0F, 0.0F, 3.5F, Color.argb(100, 0, 0, 0));
    this.mDrawablePaint = new Paint(1);
    invalidate();
  }
  
  public boolean isHidden()
  {
    return this.mHidden;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    setClickable(true);
    paramCanvas.drawCircle(getWidth() / 2, getHeight() / 2, (float)(getWidth() / 2.6D), this.mButtonPaint);
    paramCanvas.drawBitmap(this.mBitmap, (getWidth() - this.mBitmap.getWidth()) / 2, (getHeight() - this.mBitmap.getHeight()) / 2, this.mDrawablePaint);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1) {
      setAlpha(1.0F);
    }
    for (;;)
    {
      return super.onTouchEvent(paramMotionEvent);
      if (paramMotionEvent.getAction() == 0) {
        setAlpha(0.6F);
      }
    }
  }
  
  public void setFloatingActionButtonColor(int paramInt)
  {
    init(paramInt);
  }
  
  public void setFloatingActionButtonDrawable(Drawable paramDrawable)
  {
    this.mBitmap = ((BitmapDrawable)paramDrawable).getBitmap();
    invalidate();
  }
  
  public void showFloatingActionButton()
  {
    if (this.mHidden)
    {
      ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(this, "scaleX", new float[] { 0.0F, 1.0F });
      ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(this, "scaleY", new float[] { 0.0F, 1.0F });
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.playTogether(new Animator[] { localObjectAnimator1, localObjectAnimator2 });
      localAnimatorSet.setInterpolator(overshootInterpolator);
      localAnimatorSet.setDuration(200L);
      localAnimatorSet.start();
      this.mHidden = false;
    }
  }
  
  public static class Builder
  {
    private final Activity activity;
    int color = -1;
    Drawable drawable;
    int gravity = 85;
    private FrameLayout.LayoutParams params;
    float scale = 0.0F;
    int size = 0;
    
    public Builder(Activity paramActivity)
    {
      this.scale = paramActivity.getResources().getDisplayMetrics().density;
      this.size = convertToPixels(72, this.scale);
      this.params = new FrameLayout.LayoutParams(this.size, this.size);
      this.params.gravity = this.gravity;
      this.activity = paramActivity;
    }
    
    private int convertToPixels(int paramInt, float paramFloat)
    {
      return (int)(paramInt * paramFloat + 0.5F);
    }
    
    public FloatingActionButton create()
    {
      FloatingActionButton localFloatingActionButton = new FloatingActionButton(this.activity);
      localFloatingActionButton.setFloatingActionButtonColor(this.color);
      localFloatingActionButton.setFloatingActionButtonDrawable(this.drawable);
      this.params.gravity = this.gravity;
      ((ViewGroup)this.activity.findViewById(16908290)).addView(localFloatingActionButton, this.params);
      return localFloatingActionButton;
    }
    
    public Builder withButtonColor(int paramInt)
    {
      this.color = paramInt;
      return this;
    }
    
    public Builder withButtonSize(int paramInt)
    {
      paramInt = convertToPixels(paramInt, this.scale);
      this.params = new FrameLayout.LayoutParams(paramInt, paramInt);
      return this;
    }
    
    public Builder withDrawable(Drawable paramDrawable)
    {
      this.drawable = paramDrawable;
      return this;
    }
    
    public Builder withGravity(int paramInt)
    {
      this.gravity = paramInt;
      return this;
    }
    
    public Builder withMargins(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.params.setMargins(convertToPixels(paramInt1, this.scale), convertToPixels(paramInt2, this.scale), convertToPixels(paramInt3, this.scale), convertToPixels(paramInt4, this.scale));
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/custom/FloatingActionButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */