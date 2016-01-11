package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.design.R.styleable;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.Interpolator;

final class CollapsingTextHelper
{
  private static final boolean DEBUG_DRAW = false;
  private static final Paint DEBUG_DRAW_PAINT;
  private static final boolean USE_SCALING_TEXTURE;
  private boolean mBoundsChanged;
  private final Rect mCollapsedBounds;
  private float mCollapsedDrawX;
  private float mCollapsedDrawY;
  private int mCollapsedShadowColor;
  private float mCollapsedShadowDx;
  private float mCollapsedShadowDy;
  private float mCollapsedShadowRadius;
  private int mCollapsedTextColor;
  private int mCollapsedTextGravity = 16;
  private float mCollapsedTextSize = 15.0F;
  private Typeface mCollapsedTypeface;
  private final RectF mCurrentBounds;
  private float mCurrentDrawX;
  private float mCurrentDrawY;
  private float mCurrentTextSize;
  private Typeface mCurrentTypeface;
  private boolean mDrawTitle;
  private final Rect mExpandedBounds;
  private float mExpandedDrawX;
  private float mExpandedDrawY;
  private float mExpandedFraction;
  private int mExpandedShadowColor;
  private float mExpandedShadowDx;
  private float mExpandedShadowDy;
  private float mExpandedShadowRadius;
  private int mExpandedTextColor;
  private int mExpandedTextGravity = 16;
  private float mExpandedTextSize = 15.0F;
  private Bitmap mExpandedTitleTexture;
  private Typeface mExpandedTypeface;
  private boolean mIsRtl;
  private Interpolator mPositionInterpolator;
  private float mScale;
  private CharSequence mText;
  private final TextPaint mTextPaint;
  private Interpolator mTextSizeInterpolator;
  private CharSequence mTextToDraw;
  private float mTextureAscent;
  private float mTextureDescent;
  private Paint mTexturePaint;
  private boolean mUseTexture;
  private final View mView;
  
  static
  {
    if (Build.VERSION.SDK_INT < 18) {}
    for (boolean bool = true;; bool = false)
    {
      USE_SCALING_TEXTURE = bool;
      DEBUG_DRAW_PAINT = null;
      if (DEBUG_DRAW_PAINT != null)
      {
        DEBUG_DRAW_PAINT.setAntiAlias(true);
        DEBUG_DRAW_PAINT.setColor(-65281);
      }
      return;
    }
  }
  
  public CollapsingTextHelper(View paramView)
  {
    this.mView = paramView;
    this.mTextPaint = new TextPaint();
    this.mTextPaint.setAntiAlias(true);
    this.mCollapsedBounds = new Rect();
    this.mExpandedBounds = new Rect();
    this.mCurrentBounds = new RectF();
  }
  
  private static int blendColors(int paramInt1, int paramInt2, float paramFloat)
  {
    float f1 = 1.0F - paramFloat;
    float f2 = Color.alpha(paramInt1);
    float f3 = Color.alpha(paramInt2);
    float f4 = Color.red(paramInt1);
    float f5 = Color.red(paramInt2);
    float f6 = Color.green(paramInt1);
    float f7 = Color.green(paramInt2);
    float f8 = Color.blue(paramInt1);
    float f9 = Color.blue(paramInt2);
    return Color.argb((int)(f2 * f1 + f3 * paramFloat), (int)(f4 * f1 + f5 * paramFloat), (int)(f6 * f1 + f7 * paramFloat), (int)(f8 * f1 + f9 * paramFloat));
  }
  
  private void calculateBaseOffsets()
  {
    int j = 1;
    float f2 = this.mCurrentTextSize;
    calculateUsingTextSize(this.mCollapsedTextSize);
    float f1;
    int i;
    if (this.mTextToDraw != null)
    {
      f1 = this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length());
      int k = this.mCollapsedTextGravity;
      if (!this.mIsRtl) {
        break label387;
      }
      i = 1;
      label61:
      i = GravityCompat.getAbsoluteGravity(k, i);
      switch (i & 0x70)
      {
      default: 
        float f3 = (this.mTextPaint.descent() - this.mTextPaint.ascent()) / 2.0F;
        float f4 = this.mTextPaint.descent();
        this.mCollapsedDrawY = (this.mCollapsedBounds.centerY() + (f3 - f4));
        label144:
        switch (i & 0x7)
        {
        default: 
          this.mCollapsedDrawX = this.mCollapsedBounds.left;
          label188:
          calculateUsingTextSize(this.mExpandedTextSize);
          if (this.mTextToDraw != null)
          {
            f1 = this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length());
            label225:
            k = this.mExpandedTextGravity;
            if (!this.mIsRtl) {
              break label472;
            }
            i = j;
            label242:
            i = GravityCompat.getAbsoluteGravity(k, i);
            switch (i & 0x70)
            {
            default: 
              f3 = (this.mTextPaint.descent() - this.mTextPaint.ascent()) / 2.0F;
              f4 = this.mTextPaint.descent();
              this.mExpandedDrawY = (this.mExpandedBounds.centerY() + (f3 - f4));
              label328:
              switch (i & 0x7)
              {
              default: 
                this.mExpandedDrawX = this.mExpandedBounds.left;
              }
              break;
            }
          }
          break;
        }
        break;
      }
    }
    for (;;)
    {
      clearTexture();
      setInterpolatedTextSize(f2);
      return;
      f1 = 0.0F;
      break;
      label387:
      i = 0;
      break label61;
      this.mCollapsedDrawY = this.mCollapsedBounds.bottom;
      break label144;
      this.mCollapsedDrawY = (this.mCollapsedBounds.top - this.mTextPaint.ascent());
      break label144;
      this.mCollapsedDrawX = (this.mCollapsedBounds.centerX() - f1 / 2.0F);
      break label188;
      this.mCollapsedDrawX = (this.mCollapsedBounds.right - f1);
      break label188;
      f1 = 0.0F;
      break label225;
      label472:
      i = 0;
      break label242;
      this.mExpandedDrawY = this.mExpandedBounds.bottom;
      break label328;
      this.mExpandedDrawY = (this.mExpandedBounds.top - this.mTextPaint.ascent());
      break label328;
      this.mExpandedDrawX = (this.mExpandedBounds.centerX() - f1 / 2.0F);
      continue;
      this.mExpandedDrawX = (this.mExpandedBounds.right - f1);
    }
  }
  
  private void calculateCurrentOffsets()
  {
    calculateOffsets(this.mExpandedFraction);
  }
  
  private boolean calculateIsRtl(CharSequence paramCharSequence)
  {
    int i = 1;
    if (ViewCompat.getLayoutDirection(this.mView) == 1) {
      if (i == 0) {
        break label41;
      }
    }
    label41:
    for (TextDirectionHeuristicCompat localTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;; localTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR)
    {
      return localTextDirectionHeuristicCompat.isRtl(paramCharSequence, 0, paramCharSequence.length());
      i = 0;
      break;
    }
  }
  
  private void calculateOffsets(float paramFloat)
  {
    interpolateBounds(paramFloat);
    this.mCurrentDrawX = lerp(this.mExpandedDrawX, this.mCollapsedDrawX, paramFloat, this.mPositionInterpolator);
    this.mCurrentDrawY = lerp(this.mExpandedDrawY, this.mCollapsedDrawY, paramFloat, this.mPositionInterpolator);
    setInterpolatedTextSize(lerp(this.mExpandedTextSize, this.mCollapsedTextSize, paramFloat, this.mTextSizeInterpolator));
    if (this.mCollapsedTextColor != this.mExpandedTextColor) {
      this.mTextPaint.setColor(blendColors(this.mExpandedTextColor, this.mCollapsedTextColor, paramFloat));
    }
    for (;;)
    {
      this.mTextPaint.setShadowLayer(lerp(this.mExpandedShadowRadius, this.mCollapsedShadowRadius, paramFloat, null), lerp(this.mExpandedShadowDx, this.mCollapsedShadowDx, paramFloat, null), lerp(this.mExpandedShadowDy, this.mCollapsedShadowDy, paramFloat, null), blendColors(this.mExpandedShadowColor, this.mCollapsedShadowColor, paramFloat));
      ViewCompat.postInvalidateOnAnimation(this.mView);
      return;
      this.mTextPaint.setColor(this.mCollapsedTextColor);
    }
  }
  
  private void calculateUsingTextSize(float paramFloat)
  {
    if (this.mText == null) {
      return;
    }
    int j = 0;
    int i = 0;
    float f2;
    float f1;
    if (isClose(paramFloat, this.mCollapsedTextSize))
    {
      f2 = this.mCollapsedBounds.width();
      float f3 = this.mCollapsedTextSize;
      this.mScale = 1.0F;
      paramFloat = f2;
      f1 = f3;
      if (this.mCurrentTypeface != this.mCollapsedTypeface)
      {
        this.mCurrentTypeface = this.mCollapsedTypeface;
        i = 1;
        f1 = f3;
        paramFloat = f2;
      }
      label77:
      j = i;
      if (paramFloat > 0.0F) {
        if ((this.mCurrentTextSize == f1) && (!this.mBoundsChanged) && (i == 0)) {
          break label284;
        }
      }
    }
    label284:
    for (i = 1;; i = 0)
    {
      this.mCurrentTextSize = f1;
      this.mBoundsChanged = false;
      j = i;
      if ((this.mTextToDraw != null) && (j == 0)) {
        break;
      }
      this.mTextPaint.setTextSize(this.mCurrentTextSize);
      this.mTextPaint.setTypeface(this.mCurrentTypeface);
      CharSequence localCharSequence = TextUtils.ellipsize(this.mText, this.mTextPaint, paramFloat, TextUtils.TruncateAt.END);
      if (TextUtils.equals(localCharSequence, this.mTextToDraw)) {
        break;
      }
      this.mTextToDraw = localCharSequence;
      this.mIsRtl = calculateIsRtl(this.mTextToDraw);
      return;
      f2 = this.mExpandedBounds.width();
      f1 = this.mExpandedTextSize;
      i = j;
      if (this.mCurrentTypeface != this.mExpandedTypeface)
      {
        this.mCurrentTypeface = this.mExpandedTypeface;
        i = 1;
      }
      if (isClose(paramFloat, this.mExpandedTextSize))
      {
        this.mScale = 1.0F;
        paramFloat = f2;
        break label77;
      }
      this.mScale = (paramFloat / this.mExpandedTextSize);
      paramFloat = f2;
      break label77;
    }
  }
  
  private void clearTexture()
  {
    if (this.mExpandedTitleTexture != null)
    {
      this.mExpandedTitleTexture.recycle();
      this.mExpandedTitleTexture = null;
    }
  }
  
  private void ensureExpandedTexture()
  {
    if ((this.mExpandedTitleTexture != null) || (this.mExpandedBounds.isEmpty()) || (TextUtils.isEmpty(this.mTextToDraw))) {}
    do
    {
      int i;
      int j;
      do
      {
        return;
        calculateOffsets(0.0F);
        this.mTextureAscent = this.mTextPaint.ascent();
        this.mTextureDescent = this.mTextPaint.descent();
        i = Math.round(this.mTextPaint.measureText(this.mTextToDraw, 0, this.mTextToDraw.length()));
        j = Math.round(this.mTextureDescent - this.mTextureAscent);
      } while ((i <= 0) || (j <= 0));
      this.mExpandedTitleTexture = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      new Canvas(this.mExpandedTitleTexture).drawText(this.mTextToDraw, 0, this.mTextToDraw.length(), 0.0F, j - this.mTextPaint.descent(), this.mTextPaint);
    } while (this.mTexturePaint != null);
    this.mTexturePaint = new Paint(3);
  }
  
  private void interpolateBounds(float paramFloat)
  {
    this.mCurrentBounds.left = lerp(this.mExpandedBounds.left, this.mCollapsedBounds.left, paramFloat, this.mPositionInterpolator);
    this.mCurrentBounds.top = lerp(this.mExpandedDrawY, this.mCollapsedDrawY, paramFloat, this.mPositionInterpolator);
    this.mCurrentBounds.right = lerp(this.mExpandedBounds.right, this.mCollapsedBounds.right, paramFloat, this.mPositionInterpolator);
    this.mCurrentBounds.bottom = lerp(this.mExpandedBounds.bottom, this.mCollapsedBounds.bottom, paramFloat, this.mPositionInterpolator);
  }
  
  private static boolean isClose(float paramFloat1, float paramFloat2)
  {
    return Math.abs(paramFloat1 - paramFloat2) < 0.001F;
  }
  
  private static float lerp(float paramFloat1, float paramFloat2, float paramFloat3, Interpolator paramInterpolator)
  {
    float f = paramFloat3;
    if (paramInterpolator != null) {
      f = paramInterpolator.getInterpolation(paramFloat3);
    }
    return AnimationUtils.lerp(paramFloat1, paramFloat2, f);
  }
  
  private Typeface readFontFamilyTypeface(int paramInt)
  {
    TypedArray localTypedArray = this.mView.getContext().obtainStyledAttributes(paramInt, new int[] { 16843692 });
    try
    {
      Object localObject1 = localTypedArray.getString(0);
      if (localObject1 != null)
      {
        localObject1 = Typeface.create((String)localObject1, 0);
        return (Typeface)localObject1;
      }
      return null;
    }
    finally
    {
      localTypedArray.recycle();
    }
  }
  
  private static boolean rectEquals(Rect paramRect, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return (paramRect.left == paramInt1) && (paramRect.top == paramInt2) && (paramRect.right == paramInt3) && (paramRect.bottom == paramInt4);
  }
  
  private void setInterpolatedTextSize(float paramFloat)
  {
    calculateUsingTextSize(paramFloat);
    if ((USE_SCALING_TEXTURE) && (this.mScale != 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      this.mUseTexture = bool;
      if (this.mUseTexture) {
        ensureExpandedTexture();
      }
      ViewCompat.postInvalidateOnAnimation(this.mView);
      return;
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    int j = paramCanvas.save();
    float f4;
    int i;
    float f1;
    float f2;
    if ((this.mTextToDraw != null) && (this.mDrawTitle))
    {
      f4 = this.mCurrentDrawX;
      float f3 = this.mCurrentDrawY;
      if ((!this.mUseTexture) || (this.mExpandedTitleTexture == null)) {
        break label149;
      }
      i = 1;
      this.mTextPaint.setTextSize(this.mCurrentTextSize);
      if (i == 0) {
        break label155;
      }
      f1 = this.mTextureAscent * this.mScale;
      f2 = this.mTextureDescent;
      f2 = this.mScale;
      label85:
      f2 = f3;
      if (i != 0) {
        f2 = f3 + f1;
      }
      if (this.mScale != 1.0F) {
        paramCanvas.scale(this.mScale, this.mScale, f4, f2);
      }
      if (i == 0) {
        break label184;
      }
      paramCanvas.drawBitmap(this.mExpandedTitleTexture, f4, f2, this.mTexturePaint);
    }
    for (;;)
    {
      paramCanvas.restoreToCount(j);
      return;
      label149:
      i = 0;
      break;
      label155:
      f1 = this.mTextPaint.ascent() * this.mScale;
      this.mTextPaint.descent();
      f2 = this.mScale;
      break label85;
      label184:
      paramCanvas.drawText(this.mTextToDraw, 0, this.mTextToDraw.length(), f4, f2, this.mTextPaint);
    }
  }
  
  int getCollapsedTextColor()
  {
    return this.mCollapsedTextColor;
  }
  
  int getCollapsedTextGravity()
  {
    return this.mCollapsedTextGravity;
  }
  
  float getCollapsedTextSize()
  {
    return this.mCollapsedTextSize;
  }
  
  Typeface getCollapsedTypeface()
  {
    if (this.mCollapsedTypeface != null) {
      return this.mCollapsedTypeface;
    }
    return Typeface.DEFAULT;
  }
  
  int getExpandedTextColor()
  {
    return this.mExpandedTextColor;
  }
  
  int getExpandedTextGravity()
  {
    return this.mExpandedTextGravity;
  }
  
  float getExpandedTextSize()
  {
    return this.mExpandedTextSize;
  }
  
  Typeface getExpandedTypeface()
  {
    if (this.mExpandedTypeface != null) {
      return this.mExpandedTypeface;
    }
    return Typeface.DEFAULT;
  }
  
  float getExpansionFraction()
  {
    return this.mExpandedFraction;
  }
  
  CharSequence getText()
  {
    return this.mText;
  }
  
  void onBoundsChanged()
  {
    if ((this.mCollapsedBounds.width() > 0) && (this.mCollapsedBounds.height() > 0) && (this.mExpandedBounds.width() > 0) && (this.mExpandedBounds.height() > 0)) {}
    for (boolean bool = true;; bool = false)
    {
      this.mDrawTitle = bool;
      return;
    }
  }
  
  public void recalculate()
  {
    if ((this.mView.getHeight() > 0) && (this.mView.getWidth() > 0))
    {
      calculateBaseOffsets();
      calculateCurrentOffsets();
    }
  }
  
  void setCollapsedBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!rectEquals(this.mCollapsedBounds, paramInt1, paramInt2, paramInt3, paramInt4))
    {
      this.mCollapsedBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
      this.mBoundsChanged = true;
      onBoundsChanged();
    }
  }
  
  void setCollapsedTextAppearance(int paramInt)
  {
    TypedArray localTypedArray = this.mView.getContext().obtainStyledAttributes(paramInt, R.styleable.TextAppearance);
    if (localTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
      this.mCollapsedTextColor = localTypedArray.getColor(R.styleable.TextAppearance_android_textColor, this.mCollapsedTextColor);
    }
    if (localTypedArray.hasValue(R.styleable.TextAppearance_android_textSize)) {
      this.mCollapsedTextSize = localTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int)this.mCollapsedTextSize);
    }
    this.mCollapsedShadowColor = localTypedArray.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
    this.mCollapsedShadowDx = localTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
    this.mCollapsedShadowDy = localTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
    this.mCollapsedShadowRadius = localTypedArray.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
    localTypedArray.recycle();
    if (Build.VERSION.SDK_INT >= 16) {
      this.mCollapsedTypeface = readFontFamilyTypeface(paramInt);
    }
    recalculate();
  }
  
  void setCollapsedTextColor(int paramInt)
  {
    if (this.mCollapsedTextColor != paramInt)
    {
      this.mCollapsedTextColor = paramInt;
      recalculate();
    }
  }
  
  void setCollapsedTextGravity(int paramInt)
  {
    if (this.mCollapsedTextGravity != paramInt)
    {
      this.mCollapsedTextGravity = paramInt;
      recalculate();
    }
  }
  
  void setCollapsedTextSize(float paramFloat)
  {
    if (this.mCollapsedTextSize != paramFloat)
    {
      this.mCollapsedTextSize = paramFloat;
      recalculate();
    }
  }
  
  void setCollapsedTypeface(Typeface paramTypeface)
  {
    if (this.mCollapsedTypeface != paramTypeface)
    {
      this.mCollapsedTypeface = paramTypeface;
      recalculate();
    }
  }
  
  void setExpandedBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!rectEquals(this.mExpandedBounds, paramInt1, paramInt2, paramInt3, paramInt4))
    {
      this.mExpandedBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
      this.mBoundsChanged = true;
      onBoundsChanged();
    }
  }
  
  void setExpandedTextAppearance(int paramInt)
  {
    TypedArray localTypedArray = this.mView.getContext().obtainStyledAttributes(paramInt, R.styleable.TextAppearance);
    if (localTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
      this.mExpandedTextColor = localTypedArray.getColor(R.styleable.TextAppearance_android_textColor, this.mExpandedTextColor);
    }
    if (localTypedArray.hasValue(R.styleable.TextAppearance_android_textSize)) {
      this.mExpandedTextSize = localTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int)this.mExpandedTextSize);
    }
    this.mExpandedShadowColor = localTypedArray.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
    this.mExpandedShadowDx = localTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
    this.mExpandedShadowDy = localTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
    this.mExpandedShadowRadius = localTypedArray.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
    localTypedArray.recycle();
    if (Build.VERSION.SDK_INT >= 16) {
      this.mExpandedTypeface = readFontFamilyTypeface(paramInt);
    }
    recalculate();
  }
  
  void setExpandedTextColor(int paramInt)
  {
    if (this.mExpandedTextColor != paramInt)
    {
      this.mExpandedTextColor = paramInt;
      recalculate();
    }
  }
  
  void setExpandedTextGravity(int paramInt)
  {
    if (this.mExpandedTextGravity != paramInt)
    {
      this.mExpandedTextGravity = paramInt;
      recalculate();
    }
  }
  
  void setExpandedTextSize(float paramFloat)
  {
    if (this.mExpandedTextSize != paramFloat)
    {
      this.mExpandedTextSize = paramFloat;
      recalculate();
    }
  }
  
  void setExpandedTypeface(Typeface paramTypeface)
  {
    if (this.mExpandedTypeface != paramTypeface)
    {
      this.mExpandedTypeface = paramTypeface;
      recalculate();
    }
  }
  
  void setExpansionFraction(float paramFloat)
  {
    paramFloat = MathUtils.constrain(paramFloat, 0.0F, 1.0F);
    if (paramFloat != this.mExpandedFraction)
    {
      this.mExpandedFraction = paramFloat;
      calculateCurrentOffsets();
    }
  }
  
  void setPositionInterpolator(Interpolator paramInterpolator)
  {
    this.mPositionInterpolator = paramInterpolator;
    recalculate();
  }
  
  void setText(CharSequence paramCharSequence)
  {
    if ((paramCharSequence == null) || (!paramCharSequence.equals(this.mText)))
    {
      this.mText = paramCharSequence;
      this.mTextToDraw = null;
      clearTexture();
      recalculate();
    }
  }
  
  void setTextSizeInterpolator(Interpolator paramInterpolator)
  {
    this.mTextSizeInterpolator = paramInterpolator;
    recalculate();
  }
  
  void setTypefaces(Typeface paramTypeface)
  {
    this.mExpandedTypeface = paramTypeface;
    this.mCollapsedTypeface = paramTypeface;
    recalculate();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/CollapsingTextHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */