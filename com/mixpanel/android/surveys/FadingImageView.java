package com.mixpanel.android.surveys;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import com.mixpanel.android.R.id;

public class FadingImageView
  extends ImageView
{
  private Paint mAlphaGradientPaint;
  private Shader mAlphaGradientShader;
  private Paint mDarkenGradientPaint;
  private Shader mDarkenGradientShader;
  private Matrix mGradientMatrix;
  private int mHeight;
  private int mWidth;
  
  public FadingImageView(Context paramContext)
  {
    super(paramContext);
    initFadingImageView();
  }
  
  public FadingImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initFadingImageView();
  }
  
  public FadingImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initFadingImageView();
  }
  
  private void initFadingImageView()
  {
    this.mGradientMatrix = new Matrix();
    this.mAlphaGradientPaint = new Paint();
    Shader.TileMode localTileMode = Shader.TileMode.CLAMP;
    this.mAlphaGradientShader = new LinearGradient(0.0F, 0.0F, 0.0F, 1.0F, new int[] { -16777216, -16777216, -452984832, 0 }, new float[] { 0.0F, 0.7F, 0.8F, 1.0F }, localTileMode);
    this.mAlphaGradientPaint.setShader(this.mAlphaGradientShader);
    this.mAlphaGradientPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    this.mDarkenGradientPaint = new Paint();
    localTileMode = Shader.TileMode.CLAMP;
    this.mDarkenGradientShader = new LinearGradient(0.0F, 0.0F, 0.0F, 1.0F, new int[] { 0, 0, -16777216, -16777216 }, new float[] { 0.0F, 0.85F, 0.98F, 1.0F }, localTileMode);
    this.mDarkenGradientPaint.setShader(this.mDarkenGradientShader);
    this.mAlphaGradientPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = paramCanvas.getClipBounds();
    int i = paramCanvas.saveLayer(0.0F, 0.0F, localRect.width(), localRect.height(), null, 31);
    super.draw(paramCanvas);
    if (getResources().getConfiguration().orientation == 1) {
      paramCanvas.drawRect(0.0F, 0.0F, this.mWidth, this.mHeight, this.mAlphaGradientPaint);
    }
    for (;;)
    {
      paramCanvas.restoreToCount(i);
      return;
      paramCanvas.drawRect(getPaddingLeft(), getPaddingTop(), this.mWidth - getPaddingRight(), this.mHeight - getPaddingBottom(), this.mDarkenGradientPaint);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    this.mHeight = getHeight();
    this.mWidth = getWidth();
    int i = View.MeasureSpec.getSize(paramInt2);
    if (getResources().getConfiguration().orientation == 1)
    {
      View localView = getRootView().findViewById(R.id.com_mixpanel_android_notification_bottom_wrapper);
      paramInt2 = 0;
      paramInt1 = paramInt2;
      if (localView != null)
      {
        paramInt1 = paramInt2;
        if (localView.getHeight() != 0) {
          paramInt1 = localView.getHeight();
        }
      }
      float f = TypedValue.applyDimension(1, 15.0F, getResources().getDisplayMetrics());
      this.mGradientMatrix.setScale(1.0F, i - paramInt1 + f);
    }
    for (;;)
    {
      this.mAlphaGradientShader.setLocalMatrix(this.mGradientMatrix);
      this.mDarkenGradientShader.setLocalMatrix(this.mGradientMatrix);
      return;
      this.mGradientMatrix.setScale(1.0F, i);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/surveys/FadingImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */