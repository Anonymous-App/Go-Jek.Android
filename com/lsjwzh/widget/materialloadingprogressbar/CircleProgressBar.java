package com.lsjwzh.widget.materialloadingprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class CircleProgressBar
  extends ImageView
{
  private static final int DEFAULT_CIRCLE_BG_LIGHT = -328966;
  private static final int DEFAULT_CIRCLE_DIAMETER = 56;
  public static final int DEFAULT_TEXT_SIZE = 9;
  private static final int FILL_SHADOW_COLOR = 1023410176;
  private static final int KEY_SHADOW_COLOR = 503316480;
  private static final int SHADOW_ELEVATION = 4;
  private static final float SHADOW_RADIUS = 3.5F;
  private static final int STROKE_WIDTH_LARGE = 3;
  private static final float X_OFFSET = 0.0F;
  private static final float Y_OFFSET = 1.75F;
  private int mArrowHeight;
  private int mArrowWidth;
  private int mBackGroundColor;
  private ShapeDrawable mBgCircle;
  private boolean mCircleBackgroundEnabled;
  private int[] mColors = { -16777216 };
  private int mDiameter;
  private boolean mIfDrawText;
  private int mInnerRadius;
  private Animation.AnimationListener mListener;
  private int mMax;
  private int mProgress;
  private int mProgressColor;
  private MaterialProgressDrawable mProgressDrawable;
  private int mProgressStokeWidth;
  private int mShadowRadius;
  private boolean mShowArrow;
  private int mTextColor;
  private Paint mTextPaint;
  private int mTextSize;
  
  public CircleProgressBar(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public CircleProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public CircleProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private boolean elevationSupported()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircleProgressBar, paramInt, 0);
    float f = getContext().getResources().getDisplayMetrics().density;
    this.mBackGroundColor = paramContext.getColor(R.styleable.CircleProgressBar_mlpb_background_color, -328966);
    this.mProgressColor = paramContext.getColor(R.styleable.CircleProgressBar_mlpb_progress_color, -328966);
    this.mColors = new int[] { this.mProgressColor };
    this.mInnerRadius = paramContext.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_inner_radius, -1);
    this.mProgressStokeWidth = paramContext.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_progress_stoke_width, (int)(3.0F * f));
    this.mArrowWidth = paramContext.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_arrow_width, -1);
    this.mArrowHeight = paramContext.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_arrow_height, -1);
    this.mTextSize = paramContext.getDimensionPixelOffset(R.styleable.CircleProgressBar_mlpb_progress_text_size, (int)(9.0F * f));
    this.mTextColor = paramContext.getColor(R.styleable.CircleProgressBar_mlpb_progress_text_color, -16777216);
    this.mShowArrow = paramContext.getBoolean(R.styleable.CircleProgressBar_mlpb_show_arrow, false);
    this.mCircleBackgroundEnabled = paramContext.getBoolean(R.styleable.CircleProgressBar_mlpb_enable_circle_background, true);
    this.mProgress = paramContext.getInt(R.styleable.CircleProgressBar_mlpb_progress, 0);
    this.mMax = paramContext.getInt(R.styleable.CircleProgressBar_mlpb_max, 100);
    if (paramContext.getInt(R.styleable.CircleProgressBar_mlpb_progress_text_visibility, 1) != 1) {
      this.mIfDrawText = true;
    }
    this.mTextPaint = new Paint();
    this.mTextPaint.setStyle(Paint.Style.FILL);
    this.mTextPaint.setColor(this.mTextColor);
    this.mTextPaint.setTextSize(this.mTextSize);
    this.mTextPaint.setAntiAlias(true);
    paramContext.recycle();
    this.mProgressDrawable = new MaterialProgressDrawable(getContext(), this);
    super.setImageDrawable(this.mProgressDrawable);
  }
  
  public boolean circleBackgroundEnabled()
  {
    return this.mCircleBackgroundEnabled;
  }
  
  public int getMax()
  {
    return this.mMax;
  }
  
  public int getProgress()
  {
    return this.mProgress;
  }
  
  public int getVisibility()
  {
    return super.getVisibility();
  }
  
  public boolean isShowArrow()
  {
    return this.mShowArrow;
  }
  
  public boolean isShowProgressText()
  {
    return this.mIfDrawText;
  }
  
  public void onAnimationEnd()
  {
    super.onAnimationEnd();
    if (this.mListener != null) {
      this.mListener.onAnimationEnd(getAnimation());
    }
  }
  
  public void onAnimationStart()
  {
    super.onAnimationStart();
    if (this.mListener != null) {
      this.mListener.onAnimationStart(getAnimation());
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    MaterialProgressDrawable localMaterialProgressDrawable;
    if (this.mProgressDrawable != null)
    {
      this.mProgressDrawable.stop();
      localMaterialProgressDrawable = this.mProgressDrawable;
      if (getVisibility() != 0) {
        break label40;
      }
    }
    label40:
    for (boolean bool = true;; bool = false)
    {
      localMaterialProgressDrawable.setVisible(bool, false);
      return;
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mProgressDrawable != null)
    {
      this.mProgressDrawable.stop();
      this.mProgressDrawable.setVisible(false, false);
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mIfDrawText)
    {
      String str = String.format("%s%%", new Object[] { Integer.valueOf(this.mProgress) });
      int i = getWidth() / 2;
      int j = str.length() * this.mTextSize / 4;
      int k = getHeight() / 2;
      int m = this.mTextSize / 4;
      paramCanvas.drawText(str, i - j, k + m, this.mTextPaint);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    float f1 = getContext().getResources().getDisplayMetrics().density;
    this.mDiameter = Math.min(getMeasuredWidth(), getMeasuredHeight());
    if (this.mDiameter <= 0) {
      this.mDiameter = ((int)f1 * 56);
    }
    MaterialProgressDrawable localMaterialProgressDrawable;
    double d2;
    double d3;
    double d1;
    label217:
    double d4;
    if ((getBackground() == null) && (this.mCircleBackgroundEnabled))
    {
      paramInt1 = (int)(1.75F * f1);
      paramInt2 = (int)(0.0F * f1);
      this.mShadowRadius = ((int)(3.5F * f1));
      if (elevationSupported())
      {
        this.mBgCircle = new ShapeDrawable(new OvalShape());
        ViewCompat.setElevation(this, 4.0F * f1);
        this.mBgCircle.getPaint().setColor(this.mBackGroundColor);
        setBackgroundDrawable(this.mBgCircle);
      }
    }
    else
    {
      this.mProgressDrawable.setBackgroundColor(this.mBackGroundColor);
      this.mProgressDrawable.setColorSchemeColors(this.mColors);
      localMaterialProgressDrawable = this.mProgressDrawable;
      d2 = this.mDiameter;
      d3 = this.mDiameter;
      if (this.mInnerRadius > 0) {
        break label425;
      }
      d1 = (this.mDiameter - this.mProgressStokeWidth * 2) / 4;
      d4 = this.mProgressStokeWidth;
      if (this.mArrowWidth >= 0) {
        break label435;
      }
      f1 = this.mProgressStokeWidth * 4;
      label240:
      if (this.mArrowHeight >= 0) {
        break label445;
      }
    }
    label425:
    label435:
    label445:
    for (float f2 = this.mProgressStokeWidth * 2;; f2 = this.mArrowHeight)
    {
      localMaterialProgressDrawable.setSizeParameters(d2, d3, d1, d4, f1, f2);
      if (isShowArrow())
      {
        this.mProgressDrawable.showArrowOnFirstStart(true);
        this.mProgressDrawable.setArrowScale(1.0F);
        this.mProgressDrawable.showArrow(true);
      }
      super.setImageDrawable(null);
      super.setImageDrawable(this.mProgressDrawable);
      this.mProgressDrawable.setAlpha(255);
      if (getVisibility() == 0) {
        this.mProgressDrawable.start();
      }
      return;
      this.mBgCircle = new ShapeDrawable(new OvalShadow(this.mShadowRadius, this.mDiameter - this.mShadowRadius * 2));
      ViewCompat.setLayerType(this, 1, this.mBgCircle.getPaint());
      this.mBgCircle.getPaint().setShadowLayer(this.mShadowRadius, paramInt2, paramInt1, 503316480);
      paramInt1 = this.mShadowRadius;
      setPadding(paramInt1, paramInt1, paramInt1, paramInt1);
      break;
      d1 = this.mInnerRadius;
      break label217;
      f1 = this.mArrowWidth;
      break label240;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (!elevationSupported()) {
      setMeasuredDimension(getMeasuredWidth() + this.mShadowRadius * 2, getMeasuredHeight() + this.mShadowRadius * 2);
    }
  }
  
  public void setAnimationListener(Animation.AnimationListener paramAnimationListener)
  {
    this.mListener = paramAnimationListener;
  }
  
  public void setBackgroundColor(int paramInt)
  {
    if ((getBackground() instanceof ShapeDrawable))
    {
      Resources localResources = getResources();
      ((ShapeDrawable)getBackground()).getPaint().setColor(localResources.getColor(paramInt));
    }
  }
  
  public void setCircleBackgroundEnabled(boolean paramBoolean)
  {
    this.mCircleBackgroundEnabled = paramBoolean;
  }
  
  public void setColorSchemeColors(int... paramVarArgs)
  {
    this.mColors = paramVarArgs;
    if (this.mProgressDrawable != null) {
      this.mProgressDrawable.setColorSchemeColors(paramVarArgs);
    }
  }
  
  public void setColorSchemeResources(int... paramVarArgs)
  {
    Resources localResources = getResources();
    int[] arrayOfInt = new int[paramVarArgs.length];
    int i = 0;
    while (i < paramVarArgs.length)
    {
      arrayOfInt[i] = localResources.getColor(paramVarArgs[i]);
      i += 1;
    }
    setColorSchemeColors(arrayOfInt);
  }
  
  public final void setImageDrawable(Drawable paramDrawable) {}
  
  public final void setImageResource(int paramInt) {}
  
  public final void setImageURI(Uri paramUri)
  {
    super.setImageURI(paramUri);
  }
  
  public void setMax(int paramInt)
  {
    this.mMax = paramInt;
  }
  
  public void setProgress(int paramInt)
  {
    if (getMax() > 0) {
      this.mProgress = paramInt;
    }
  }
  
  public void setShowArrow(boolean paramBoolean)
  {
    this.mShowArrow = paramBoolean;
  }
  
  public void setShowProgressText(boolean paramBoolean)
  {
    this.mIfDrawText = paramBoolean;
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    MaterialProgressDrawable localMaterialProgressDrawable;
    if (this.mProgressDrawable != null)
    {
      localMaterialProgressDrawable = this.mProgressDrawable;
      if (paramInt != 0) {
        break label42;
      }
    }
    label42:
    for (boolean bool = true;; bool = false)
    {
      localMaterialProgressDrawable.setVisible(bool, false);
      if (paramInt == 0) {
        break;
      }
      this.mProgressDrawable.stop();
      return;
    }
    if (this.mProgressDrawable.isRunning()) {
      this.mProgressDrawable.stop();
    }
    this.mProgressDrawable.start();
  }
  
  private class OvalShadow
    extends OvalShape
  {
    private int mCircleDiameter;
    private RadialGradient mRadialGradient;
    private Paint mShadowPaint = new Paint();
    private int mShadowRadius;
    
    public OvalShadow(int paramInt1, int paramInt2)
    {
      this.mShadowRadius = paramInt1;
      this.mCircleDiameter = paramInt2;
      float f1 = this.mCircleDiameter / 2;
      float f2 = this.mCircleDiameter / 2;
      float f3 = this.mShadowRadius;
      this$1 = Shader.TileMode.CLAMP;
      this.mRadialGradient = new RadialGradient(f1, f2, f3, new int[] { 1023410176, 0 }, null, CircleProgressBar.this);
      this.mShadowPaint.setShader(this.mRadialGradient);
    }
    
    public void draw(Canvas paramCanvas, Paint paramPaint)
    {
      int i = CircleProgressBar.this.getWidth();
      int j = CircleProgressBar.this.getHeight();
      paramCanvas.drawCircle(i / 2, j / 2, this.mCircleDiameter / 2 + this.mShadowRadius, this.mShadowPaint);
      paramCanvas.drawCircle(i / 2, j / 2, this.mCircleDiameter / 2, paramPaint);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/lsjwzh/widget/materialloadingprogressbar/CircleProgressBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */