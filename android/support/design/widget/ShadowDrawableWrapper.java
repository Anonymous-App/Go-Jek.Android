package android.support.design.widget;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.design.R.color;
import android.support.v7.graphics.drawable.DrawableWrapper;

class ShadowDrawableWrapper
  extends DrawableWrapper
{
  static final double COS_45 = Math.cos(Math.toRadians(45.0D));
  static final float SHADOW_BOTTOM_SCALE = 1.0F;
  static final float SHADOW_HORIZ_SCALE = 0.5F;
  static final float SHADOW_MULTIPLIER = 1.5F;
  static final float SHADOW_TOP_SCALE = 0.25F;
  private boolean mAddPaddingForCorners = true;
  final RectF mContentBounds;
  float mCornerRadius;
  final Paint mCornerShadowPaint;
  Path mCornerShadowPath;
  private boolean mDirty = true;
  final Paint mEdgeShadowPaint;
  float mMaxShadowSize;
  private boolean mPrintedShadowClipWarning = false;
  float mRawMaxShadowSize;
  float mRawShadowSize;
  private float mRotation;
  private final int mShadowEndColor;
  private final int mShadowMiddleColor;
  float mShadowSize;
  private final int mShadowStartColor;
  
  public ShadowDrawableWrapper(Resources paramResources, Drawable paramDrawable, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramDrawable);
    this.mShadowStartColor = paramResources.getColor(R.color.design_fab_shadow_start_color);
    this.mShadowMiddleColor = paramResources.getColor(R.color.design_fab_shadow_mid_color);
    this.mShadowEndColor = paramResources.getColor(R.color.design_fab_shadow_end_color);
    this.mCornerShadowPaint = new Paint(5);
    this.mCornerShadowPaint.setStyle(Paint.Style.FILL);
    this.mCornerRadius = Math.round(paramFloat1);
    this.mContentBounds = new RectF();
    this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint);
    this.mEdgeShadowPaint.setAntiAlias(false);
    setShadowSize(paramFloat2, paramFloat3);
  }
  
  private void buildComponents(Rect paramRect)
  {
    float f = this.mRawMaxShadowSize * 1.5F;
    this.mContentBounds.set(paramRect.left + this.mRawMaxShadowSize, paramRect.top + f, paramRect.right - this.mRawMaxShadowSize, paramRect.bottom - f);
    getWrappedDrawable().setBounds((int)this.mContentBounds.left, (int)this.mContentBounds.top, (int)this.mContentBounds.right, (int)this.mContentBounds.bottom);
    buildShadowCorners();
  }
  
  private void buildShadowCorners()
  {
    Object localObject = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
    RectF localRectF = new RectF((RectF)localObject);
    localRectF.inset(-this.mShadowSize, -this.mShadowSize);
    if (this.mCornerShadowPath == null) {
      this.mCornerShadowPath = new Path();
    }
    for (;;)
    {
      this.mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
      this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0F);
      this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0F);
      this.mCornerShadowPath.arcTo(localRectF, 180.0F, 90.0F, false);
      this.mCornerShadowPath.arcTo((RectF)localObject, 270.0F, -90.0F, false);
      this.mCornerShadowPath.close();
      float f1 = -localRectF.top;
      if (f1 > 0.0F)
      {
        f2 = this.mCornerRadius / f1;
        float f3 = (1.0F - f2) / 2.0F;
        localPaint = this.mCornerShadowPaint;
        i = this.mShadowStartColor;
        j = this.mShadowMiddleColor;
        k = this.mShadowEndColor;
        Shader.TileMode localTileMode = Shader.TileMode.CLAMP;
        localPaint.setShader(new RadialGradient(0.0F, 0.0F, f1, new int[] { 0, i, j, k }, new float[] { 0.0F, f2, f2 + f3, 1.0F }, localTileMode));
      }
      Paint localPaint = this.mEdgeShadowPaint;
      f1 = ((RectF)localObject).top;
      float f2 = localRectF.top;
      int i = this.mShadowStartColor;
      int j = this.mShadowMiddleColor;
      int k = this.mShadowEndColor;
      localObject = Shader.TileMode.CLAMP;
      localPaint.setShader(new LinearGradient(0.0F, f1, 0.0F, f2, new int[] { i, j, k }, new float[] { 0.0F, 0.5F, 1.0F }, (Shader.TileMode)localObject));
      this.mEdgeShadowPaint.setAntiAlias(false);
      return;
      this.mCornerShadowPath.reset();
    }
  }
  
  public static float calculateHorizontalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    float f = paramFloat1;
    if (paramBoolean) {
      f = (float)(paramFloat1 + (1.0D - COS_45) * paramFloat2);
    }
    return f;
  }
  
  public static float calculateVerticalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (paramBoolean) {
      return (float)(1.5F * paramFloat1 + (1.0D - COS_45) * paramFloat2);
    }
    return 1.5F * paramFloat1;
  }
  
  private void drawShadow(Canvas paramCanvas)
  {
    int k = paramCanvas.save();
    paramCanvas.rotate(this.mRotation, this.mContentBounds.centerX(), this.mContentBounds.centerY());
    float f1 = -this.mCornerRadius - this.mShadowSize;
    float f2 = this.mCornerRadius;
    int i;
    if (this.mContentBounds.width() - 2.0F * f2 > 0.0F)
    {
      i = 1;
      if (this.mContentBounds.height() - 2.0F * f2 <= 0.0F) {
        break label579;
      }
    }
    label579:
    for (int j = 1;; j = 0)
    {
      float f6 = this.mRawShadowSize;
      float f7 = this.mRawShadowSize;
      float f3 = this.mRawShadowSize;
      float f8 = this.mRawShadowSize;
      float f4 = this.mRawShadowSize;
      float f5 = this.mRawShadowSize;
      f3 = f2 / (f2 + (f3 - f8 * 0.5F));
      f6 = f2 / (f2 + (f6 - f7 * 0.25F));
      f4 = f2 / (f2 + (f4 - f5 * 1.0F));
      int m = paramCanvas.save();
      paramCanvas.translate(this.mContentBounds.left + f2, this.mContentBounds.top + f2);
      paramCanvas.scale(f3, f6);
      paramCanvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
      if (i != 0)
      {
        paramCanvas.scale(1.0F / f3, 1.0F);
        paramCanvas.drawRect(0.0F, f1, this.mContentBounds.width() - 2.0F * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
      }
      paramCanvas.restoreToCount(m);
      m = paramCanvas.save();
      paramCanvas.translate(this.mContentBounds.right - f2, this.mContentBounds.bottom - f2);
      paramCanvas.scale(f3, f4);
      paramCanvas.rotate(180.0F);
      paramCanvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
      if (i != 0)
      {
        paramCanvas.scale(1.0F / f3, 1.0F);
        f5 = this.mContentBounds.width();
        f7 = -this.mCornerRadius;
        paramCanvas.drawRect(0.0F, f1, f5 - 2.0F * f2, this.mShadowSize + f7, this.mEdgeShadowPaint);
      }
      paramCanvas.restoreToCount(m);
      i = paramCanvas.save();
      paramCanvas.translate(this.mContentBounds.left + f2, this.mContentBounds.bottom - f2);
      paramCanvas.scale(f3, f4);
      paramCanvas.rotate(270.0F);
      paramCanvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
      if (j != 0)
      {
        paramCanvas.scale(1.0F / f4, 1.0F);
        paramCanvas.drawRect(0.0F, f1, this.mContentBounds.height() - 2.0F * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
      }
      paramCanvas.restoreToCount(i);
      i = paramCanvas.save();
      paramCanvas.translate(this.mContentBounds.right - f2, this.mContentBounds.top + f2);
      paramCanvas.scale(f3, f6);
      paramCanvas.rotate(90.0F);
      paramCanvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
      if (j != 0)
      {
        paramCanvas.scale(1.0F / f6, 1.0F);
        paramCanvas.drawRect(0.0F, f1, this.mContentBounds.height() - 2.0F * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
      }
      paramCanvas.restoreToCount(i);
      paramCanvas.restoreToCount(k);
      return;
      i = 0;
      break;
    }
  }
  
  private static int toEven(float paramFloat)
  {
    int j = Math.round(paramFloat);
    int i = j;
    if (j % 2 == 1) {
      i = j - 1;
    }
    return i;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (this.mDirty)
    {
      buildComponents(getBounds());
      this.mDirty = false;
    }
    drawShadow(paramCanvas);
    super.draw(paramCanvas);
  }
  
  public float getCornerRadius()
  {
    return this.mCornerRadius;
  }
  
  public float getMaxShadowSize()
  {
    return this.mRawMaxShadowSize;
  }
  
  public float getMinHeight()
  {
    float f = Math.max(this.mRawMaxShadowSize, this.mCornerRadius + this.mRawMaxShadowSize * 1.5F / 2.0F);
    return this.mRawMaxShadowSize * 1.5F * 2.0F + 2.0F * f;
  }
  
  public float getMinWidth()
  {
    float f = Math.max(this.mRawMaxShadowSize, this.mCornerRadius + this.mRawMaxShadowSize / 2.0F);
    return this.mRawMaxShadowSize * 2.0F + 2.0F * f;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean getPadding(Rect paramRect)
  {
    int i = (int)Math.ceil(calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
    int j = (int)Math.ceil(calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
    paramRect.set(j, i, j, i);
    return true;
  }
  
  public float getShadowSize()
  {
    return this.mRawShadowSize;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    this.mDirty = true;
  }
  
  public void setAddPaddingForCorners(boolean paramBoolean)
  {
    this.mAddPaddingForCorners = paramBoolean;
    invalidateSelf();
  }
  
  public void setAlpha(int paramInt)
  {
    super.setAlpha(paramInt);
    this.mCornerShadowPaint.setAlpha(paramInt);
    this.mEdgeShadowPaint.setAlpha(paramInt);
  }
  
  public void setCornerRadius(float paramFloat)
  {
    paramFloat = Math.round(paramFloat);
    if (this.mCornerRadius == paramFloat) {
      return;
    }
    this.mCornerRadius = paramFloat;
    this.mDirty = true;
    invalidateSelf();
  }
  
  public void setMaxShadowSize(float paramFloat)
  {
    setShadowSize(this.mRawShadowSize, paramFloat);
  }
  
  final void setRotation(float paramFloat)
  {
    if (this.mRotation != paramFloat)
    {
      this.mRotation = paramFloat;
      invalidateSelf();
    }
  }
  
  public void setShadowSize(float paramFloat)
  {
    setShadowSize(paramFloat, this.mRawMaxShadowSize);
  }
  
  void setShadowSize(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat1 < 0.0F) || (paramFloat2 < 0.0F)) {
      throw new IllegalArgumentException("invalid shadow size");
    }
    float f1 = toEven(paramFloat1);
    float f2 = toEven(paramFloat2);
    paramFloat1 = f1;
    if (f1 > f2)
    {
      paramFloat2 = f2;
      paramFloat1 = paramFloat2;
      if (!this.mPrintedShadowClipWarning)
      {
        this.mPrintedShadowClipWarning = true;
        paramFloat1 = paramFloat2;
      }
    }
    if ((this.mRawShadowSize == paramFloat1) && (this.mRawMaxShadowSize == f2)) {
      return;
    }
    this.mRawShadowSize = paramFloat1;
    this.mRawMaxShadowSize = f2;
    this.mShadowSize = Math.round(1.5F * paramFloat1);
    this.mMaxShadowSize = f2;
    this.mDirty = true;
    invalidateSelf();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/ShadowDrawableWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */