package com.lsjwzh.widget.materialloadingprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class MaterialProgressDrawable
  extends Drawable
  implements Animatable
{
  private static final int ANIMATION_DURATION = 1333;
  private static final int ARROW_HEIGHT = 5;
  static final int ARROW_HEIGHT_LARGE = 6;
  private static final float ARROW_OFFSET_ANGLE = 0.0F;
  private static final int ARROW_WIDTH = 10;
  static final int ARROW_WIDTH_LARGE = 12;
  private static final float CENTER_RADIUS = 8.75F;
  private static final float CENTER_RADIUS_LARGE = 12.5F;
  private static final int CIRCLE_DIAMETER = 40;
  private static final int CIRCLE_DIAMETER_LARGE = 56;
  public static final int DEFAULT = 1;
  private static final Interpolator EASE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
  private static final Interpolator END_CURVE_INTERPOLATOR;
  public static final int LARGE = 0;
  private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
  private static final float MAX_PROGRESS_ARC = 0.8F;
  private static final float NUM_POINTS = 5.0F;
  private static final Interpolator START_CURVE_INTERPOLATOR;
  private static final float STROKE_WIDTH = 2.5F;
  static final float STROKE_WIDTH_LARGE = 3.0F;
  private final int[] COLORS = { -16777216 };
  private View mAnimExcutor;
  private Animation mAnimation;
  private final ArrayList<Animation> mAnimators = new ArrayList();
  private final Drawable.Callback mCallback = new Drawable.Callback()
  {
    public void invalidateDrawable(Drawable paramAnonymousDrawable)
    {
      MaterialProgressDrawable.this.invalidateSelf();
    }
    
    public void scheduleDrawable(Drawable paramAnonymousDrawable, Runnable paramAnonymousRunnable, long paramAnonymousLong)
    {
      MaterialProgressDrawable.this.scheduleSelf(paramAnonymousRunnable, paramAnonymousLong);
    }
    
    public void unscheduleDrawable(Drawable paramAnonymousDrawable, Runnable paramAnonymousRunnable)
    {
      MaterialProgressDrawable.this.unscheduleSelf(paramAnonymousRunnable);
    }
  };
  boolean mFinishing;
  private double mHeight;
  private Resources mResources;
  private final Ring mRing;
  private float mRotation;
  private float mRotationCount;
  private boolean mShowArrowOnFirstStart = false;
  private double mWidth;
  
  static
  {
    END_CURVE_INTERPOLATOR = new EndCurveInterpolator(null);
    START_CURVE_INTERPOLATOR = new StartCurveInterpolator(null);
  }
  
  public MaterialProgressDrawable(Context paramContext, View paramView)
  {
    this.mAnimExcutor = paramView;
    this.mResources = paramContext.getResources();
    this.mRing = new Ring(this.mCallback);
    this.mRing.setColors(this.COLORS);
    updateSizes(1);
    setupAnimators();
  }
  
  private void applyFinishTranslation(float paramFloat, Ring paramRing)
  {
    float f = (float)(Math.floor(paramRing.getStartingRotation() / 0.8F) + 1.0D);
    paramRing.setStartTrim(paramRing.getStartingStartTrim() + (paramRing.getStartingEndTrim() - paramRing.getStartingStartTrim()) * paramFloat);
    paramRing.setRotation(paramRing.getStartingRotation() + (f - paramRing.getStartingRotation()) * paramFloat);
  }
  
  private float getRotation()
  {
    return this.mRotation;
  }
  
  private void setupAnimators()
  {
    final Ring localRing = this.mRing;
    Animation local2 = new Animation()
    {
      public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        if (MaterialProgressDrawable.this.mFinishing)
        {
          MaterialProgressDrawable.this.applyFinishTranslation(paramAnonymousFloat, localRing);
          return;
        }
        float f2 = (float)Math.toRadians(localRing.getStrokeWidth() / (6.283185307179586D * localRing.getCenterRadius()));
        float f4 = localRing.getStartingEndTrim();
        float f1 = localRing.getStartingStartTrim();
        float f3 = localRing.getStartingRotation();
        f2 = f4 + MaterialProgressDrawable.START_CURVE_INTERPOLATOR.getInterpolation(paramAnonymousFloat) * (0.8F - f2);
        f4 = f1 + 0.8F * MaterialProgressDrawable.END_CURVE_INTERPOLATOR.getInterpolation(paramAnonymousFloat);
        f1 = f2;
        if (Math.abs(f2 - f4) >= 1.0F) {
          f1 = f4 + 0.5F;
        }
        localRing.setEndTrim(f1);
        localRing.setStartTrim(f4);
        localRing.setRotation(f3 + 0.25F * paramAnonymousFloat);
        f1 = MaterialProgressDrawable.this.mRotationCount / 5.0F;
        MaterialProgressDrawable.this.setRotation(144.0F * paramAnonymousFloat + 720.0F * f1);
      }
    };
    local2.setRepeatCount(-1);
    local2.setRepeatMode(1);
    local2.setInterpolator(LINEAR_INTERPOLATOR);
    local2.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation) {}
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
        localRing.storeOriginals();
        localRing.goToNextColor();
        localRing.setStartTrim(localRing.getEndTrim());
        if (MaterialProgressDrawable.this.mFinishing)
        {
          MaterialProgressDrawable.this.mFinishing = false;
          paramAnonymousAnimation.setDuration(1333L);
          localRing.setShowArrow(false);
          return;
        }
        MaterialProgressDrawable.access$502(MaterialProgressDrawable.this, (MaterialProgressDrawable.this.mRotationCount + 1.0F) % 5.0F);
      }
      
      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        MaterialProgressDrawable.access$502(MaterialProgressDrawable.this, 0.0F);
      }
    });
    this.mAnimation = local2;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = getBounds();
    int i = paramCanvas.save();
    paramCanvas.rotate(this.mRotation, localRect.exactCenterX(), localRect.exactCenterY());
    this.mRing.draw(paramCanvas, localRect);
    paramCanvas.restoreToCount(i);
  }
  
  public int getAlpha()
  {
    return this.mRing.getAlpha();
  }
  
  public int getIntrinsicHeight()
  {
    return (int)this.mHeight;
  }
  
  public int getIntrinsicWidth()
  {
    return (int)this.mWidth;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean isRunning()
  {
    return !this.mAnimation.hasEnded();
  }
  
  public void setAlpha(int paramInt)
  {
    this.mRing.setAlpha(paramInt);
  }
  
  public void setArrowScale(float paramFloat)
  {
    this.mRing.setArrowScale(paramFloat);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    this.mRing.setBackgroundColor(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.mRing.setColorFilter(paramColorFilter);
  }
  
  public void setColorSchemeColors(int... paramVarArgs)
  {
    this.mRing.setColors(paramVarArgs);
    this.mRing.setColorIndex(0);
  }
  
  public void setProgressRotation(float paramFloat)
  {
    this.mRing.setRotation(paramFloat);
  }
  
  void setRotation(float paramFloat)
  {
    this.mRotation = paramFloat;
    invalidateSelf();
  }
  
  public void setSizeParameters(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, float paramFloat1, float paramFloat2)
  {
    Ring localRing = this.mRing;
    this.mWidth = paramDouble1;
    this.mHeight = paramDouble2;
    localRing.setStrokeWidth((float)paramDouble4);
    localRing.setCenterRadius(paramDouble3);
    localRing.setColorIndex(0);
    localRing.setArrowDimensions(paramFloat1, paramFloat2);
    localRing.setInsets((int)this.mWidth, (int)this.mHeight);
  }
  
  public void setStartEndTrim(float paramFloat1, float paramFloat2)
  {
    this.mRing.setStartTrim(paramFloat1);
    this.mRing.setEndTrim(paramFloat2);
  }
  
  public void showArrow(boolean paramBoolean)
  {
    this.mRing.setShowArrow(paramBoolean);
  }
  
  public void showArrowOnFirstStart(boolean paramBoolean)
  {
    this.mShowArrowOnFirstStart = paramBoolean;
  }
  
  public void start()
  {
    this.mAnimation.reset();
    this.mRing.storeOriginals();
    this.mRing.setShowArrow(this.mShowArrowOnFirstStart);
    if (this.mRing.getEndTrim() != this.mRing.getStartTrim())
    {
      this.mFinishing = true;
      this.mAnimation.setDuration(666L);
      this.mAnimExcutor.startAnimation(this.mAnimation);
      return;
    }
    this.mRing.setColorIndex(0);
    this.mRing.resetOriginals();
    this.mAnimation.setDuration(1333L);
    this.mAnimExcutor.startAnimation(this.mAnimation);
  }
  
  public void stop()
  {
    this.mAnimExcutor.clearAnimation();
    setRotation(0.0F);
    this.mRing.setShowArrow(false);
    this.mRing.setColorIndex(0);
    this.mRing.resetOriginals();
  }
  
  public void updateSizes(@ProgressDrawableSize int paramInt)
  {
    float f = this.mResources.getDisplayMetrics().density;
    if (paramInt == 0)
    {
      setSizeParameters(56.0F * f, 56.0F * f, 12.5F * f, 3.0F * f, 12.0F * f, 6.0F * f);
      return;
    }
    setSizeParameters(40.0F * f, 40.0F * f, 8.75F * f, 2.5F * f, 10.0F * f, 5.0F * f);
  }
  
  private static class EndCurveInterpolator
    extends AccelerateDecelerateInterpolator
  {
    public float getInterpolation(float paramFloat)
    {
      return super.getInterpolation(Math.max(0.0F, (paramFloat - 0.5F) * 2.0F));
    }
  }
  
  @Retention(RetentionPolicy.CLASS)
  @IntDef({0L, 1L})
  public static @interface ProgressDrawableSize {}
  
  private static class Ring
  {
    private int mAlpha;
    private Path mArrow;
    private int mArrowHeight;
    private final Paint mArrowPaint = new Paint();
    private float mArrowScale;
    private int mArrowWidth;
    private int mBackgroundColor;
    private final Drawable.Callback mCallback;
    private final Paint mCirclePaint = new Paint();
    private int mColorIndex;
    private int[] mColors;
    private float mEndTrim = 0.0F;
    private final Paint mPaint = new Paint();
    private double mRingCenterRadius;
    private float mRotation = 0.0F;
    private boolean mShowArrow;
    private float mStartTrim = 0.0F;
    private float mStartingEndTrim;
    private float mStartingRotation;
    private float mStartingStartTrim;
    private float mStrokeInset = 2.5F;
    private float mStrokeWidth = 5.0F;
    private final RectF mTempBounds = new RectF();
    
    public Ring(Drawable.Callback paramCallback)
    {
      this.mCallback = paramCallback;
      this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
      this.mPaint.setAntiAlias(true);
      this.mPaint.setStyle(Paint.Style.STROKE);
      this.mArrowPaint.setStyle(Paint.Style.FILL);
      this.mArrowPaint.setAntiAlias(true);
    }
    
    private void drawTriangle(Canvas paramCanvas, float paramFloat1, float paramFloat2, Rect paramRect)
    {
      if (this.mShowArrow)
      {
        if (this.mArrow != null) {
          break label221;
        }
        this.mArrow = new Path();
        this.mArrow.setFillType(Path.FillType.EVEN_ODD);
      }
      for (;;)
      {
        float f1 = (float)(this.mRingCenterRadius * Math.cos(0.0D) + paramRect.exactCenterX());
        float f2 = (float)(this.mRingCenterRadius * Math.sin(0.0D) + paramRect.exactCenterY());
        this.mArrow.moveTo(0.0F, 0.0F);
        this.mArrow.lineTo(this.mArrowWidth * this.mArrowScale, 0.0F);
        this.mArrow.lineTo(this.mArrowWidth * this.mArrowScale / 2.0F, this.mArrowHeight * this.mArrowScale);
        this.mArrow.offset(f1 - this.mArrowWidth * this.mArrowScale / 2.0F, f2);
        this.mArrow.close();
        this.mArrowPaint.setColor(this.mColors[this.mColorIndex]);
        f1 = paramFloat2;
        if (paramFloat2 < 0.0F) {
          f1 = 0.0F;
        }
        paramCanvas.rotate(paramFloat1 + f1 - 0.0F, paramRect.exactCenterX(), paramRect.exactCenterY());
        paramCanvas.drawPath(this.mArrow, this.mArrowPaint);
        return;
        label221:
        this.mArrow.reset();
      }
    }
    
    private void invalidateSelf()
    {
      this.mCallback.invalidateDrawable(null);
    }
    
    public void draw(Canvas paramCanvas, Rect paramRect)
    {
      RectF localRectF = this.mTempBounds;
      localRectF.set(paramRect);
      localRectF.inset(this.mStrokeInset, this.mStrokeInset);
      float f1 = (this.mStartTrim + this.mRotation) * 360.0F;
      float f2 = (this.mEndTrim + this.mRotation) * 360.0F - f1;
      this.mPaint.setColor(this.mColors[this.mColorIndex]);
      paramCanvas.drawArc(localRectF, f1, f2, false, this.mPaint);
      drawTriangle(paramCanvas, f1, f2, paramRect);
      if (this.mAlpha < 255)
      {
        this.mCirclePaint.setColor(this.mBackgroundColor);
        this.mCirclePaint.setAlpha(255 - this.mAlpha);
        paramCanvas.drawCircle(paramRect.exactCenterX(), paramRect.exactCenterY(), paramRect.width() / 2, this.mCirclePaint);
      }
    }
    
    public int getAlpha()
    {
      return this.mAlpha;
    }
    
    public double getCenterRadius()
    {
      return this.mRingCenterRadius;
    }
    
    public float getEndTrim()
    {
      return this.mEndTrim;
    }
    
    public float getInsets()
    {
      return this.mStrokeInset;
    }
    
    public float getRotation()
    {
      return this.mRotation;
    }
    
    public float getStartTrim()
    {
      return this.mStartTrim;
    }
    
    public float getStartingEndTrim()
    {
      return this.mStartingEndTrim;
    }
    
    public float getStartingRotation()
    {
      return this.mStartingRotation;
    }
    
    public float getStartingStartTrim()
    {
      return this.mStartingStartTrim;
    }
    
    public float getStrokeWidth()
    {
      return this.mStrokeWidth;
    }
    
    public void goToNextColor()
    {
      this.mColorIndex = ((this.mColorIndex + 1) % this.mColors.length);
    }
    
    public void resetOriginals()
    {
      this.mStartingStartTrim = 0.0F;
      this.mStartingEndTrim = 0.0F;
      this.mStartingRotation = 0.0F;
      setStartTrim(0.0F);
      setEndTrim(0.0F);
      setRotation(0.0F);
    }
    
    public void setAlpha(int paramInt)
    {
      this.mAlpha = paramInt;
    }
    
    public void setArrowDimensions(float paramFloat1, float paramFloat2)
    {
      this.mArrowWidth = ((int)paramFloat1);
      this.mArrowHeight = ((int)paramFloat2);
    }
    
    public void setArrowScale(float paramFloat)
    {
      if (paramFloat != this.mArrowScale)
      {
        this.mArrowScale = paramFloat;
        invalidateSelf();
      }
    }
    
    public void setBackgroundColor(int paramInt)
    {
      this.mBackgroundColor = paramInt;
    }
    
    public void setCenterRadius(double paramDouble)
    {
      this.mRingCenterRadius = paramDouble;
    }
    
    public void setColorFilter(ColorFilter paramColorFilter)
    {
      this.mPaint.setColorFilter(paramColorFilter);
      invalidateSelf();
    }
    
    public void setColorIndex(int paramInt)
    {
      this.mColorIndex = paramInt;
    }
    
    public void setColors(@NonNull int[] paramArrayOfInt)
    {
      this.mColors = paramArrayOfInt;
      setColorIndex(0);
    }
    
    public void setEndTrim(float paramFloat)
    {
      this.mEndTrim = paramFloat;
      invalidateSelf();
    }
    
    public void setInsets(int paramInt1, int paramInt2)
    {
      float f = Math.min(paramInt1, paramInt2);
      if ((this.mRingCenterRadius <= 0.0D) || (f < 0.0F)) {}
      for (f = (float)Math.ceil(this.mStrokeWidth / 2.0F);; f = (float)(f / 2.0F - this.mRingCenterRadius))
      {
        this.mStrokeInset = f;
        return;
      }
    }
    
    public void setRotation(float paramFloat)
    {
      this.mRotation = paramFloat;
      invalidateSelf();
    }
    
    public void setShowArrow(boolean paramBoolean)
    {
      if (this.mShowArrow != paramBoolean)
      {
        this.mShowArrow = paramBoolean;
        invalidateSelf();
      }
    }
    
    public void setStartTrim(float paramFloat)
    {
      this.mStartTrim = paramFloat;
      invalidateSelf();
    }
    
    public void setStrokeWidth(float paramFloat)
    {
      this.mStrokeWidth = paramFloat;
      this.mPaint.setStrokeWidth(paramFloat);
      invalidateSelf();
    }
    
    public void storeOriginals()
    {
      this.mStartingStartTrim = this.mStartTrim;
      this.mStartingEndTrim = this.mEndTrim;
      this.mStartingRotation = this.mRotation;
    }
  }
  
  private static class StartCurveInterpolator
    extends AccelerateDecelerateInterpolator
  {
    public float getInterpolation(float paramFloat)
    {
      return super.getInterpolation(Math.min(1.0F, 2.0F * paramFloat));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/lsjwzh/widget/materialloadingprogressbar/MaterialProgressDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */