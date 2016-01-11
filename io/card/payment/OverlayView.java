package io.card.payment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import io.card.payment.i18n.LocalizedStrings;
import io.card.payment.i18n.StringKey;
import java.lang.ref.WeakReference;

class OverlayView
  extends View
{
  private static final GradientDrawable.Orientation[] GRADIENT_ORIENTATIONS = { GradientDrawable.Orientation.TOP_BOTTOM, GradientDrawable.Orientation.LEFT_RIGHT, GradientDrawable.Orientation.BOTTOM_TOP, GradientDrawable.Orientation.RIGHT_LEFT };
  private static final String TAG = OverlayView.class.getSimpleName();
  private int guideColor;
  private boolean hideCardIOLogo = false;
  private Bitmap mBitmap;
  private Rect mCameraPreviewRect;
  private DetectionInfo mDInfo;
  private CreditCard mDetectedCard;
  private GradientDrawable mGradientDrawable;
  private Rect mGuide;
  private final Paint mGuidePaint;
  private final Paint mLockedBackgroundPaint;
  private Path mLockedBackgroundPath;
  private final Logo mLogo;
  private Rect mLogoRect;
  private int mRotation;
  private int mRotationFlip;
  private float mScale = 1.0F;
  private final WeakReference<CardIOActivity> mScanActivityRef;
  private final boolean mShowTorch;
  private int mState;
  private final Torch mTorch;
  private Rect mTorchRect;
  private String scanInstructions;
  
  public OverlayView(CardIOActivity paramCardIOActivity, AttributeSet paramAttributeSet, boolean paramBoolean)
  {
    super(paramCardIOActivity, paramAttributeSet);
    this.mShowTorch = paramBoolean;
    this.mScanActivityRef = new WeakReference(paramCardIOActivity);
    this.mRotationFlip = 1;
    this.mScale = (getResources().getDisplayMetrics().density / 1.5F);
    this.mTorch = new Torch(70.0F * this.mScale, 50.0F * this.mScale);
    this.mLogo = new Logo(paramCardIOActivity);
    this.mGuidePaint = new Paint(1);
    this.mLockedBackgroundPaint = new Paint(1);
    this.mLockedBackgroundPaint.clearShadowLayer();
    this.mLockedBackgroundPaint.setStyle(Paint.Style.FILL);
    this.mLockedBackgroundPaint.setColor(-1157627904);
    this.scanInstructions = LocalizedStrings.getString(StringKey.SCAN_GUIDE);
  }
  
  private void decorateBitmap()
  {
    Object localObject = new RectF(2.0F, 2.0F, this.mBitmap.getWidth() - 2, this.mBitmap.getHeight() - 2);
    float f = this.mBitmap.getHeight() * 0.06666667F;
    Bitmap localBitmap = Bitmap.createBitmap(this.mBitmap.getWidth(), this.mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.drawColor(0);
    Paint localPaint = new Paint(1);
    localPaint.setColor(-16777216);
    localPaint.setStyle(Paint.Style.FILL);
    localCanvas.drawRoundRect((RectF)localObject, f, f, localPaint);
    localObject = new Paint();
    ((Paint)localObject).setFilterBitmap(false);
    localCanvas = new Canvas(this.mBitmap);
    ((Paint)localObject).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    localCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, (Paint)localObject);
    ((Paint)localObject).setXfermode(null);
    localBitmap.recycle();
  }
  
  private Rect guideStrokeRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = (int)(8.0F * this.mScale);
    Rect localRect = new Rect();
    localRect.left = (Math.min(paramInt1, paramInt3) - i);
    localRect.right = (Math.max(paramInt1, paramInt3) + i);
    localRect.top = (Math.min(paramInt2, paramInt4) - i);
    localRect.bottom = (Math.max(paramInt2, paramInt4) + i);
    return localRect;
  }
  
  public Bitmap getBitmap()
  {
    return this.mBitmap;
  }
  
  public Bitmap getCardImage()
  {
    if ((this.mBitmap != null) && (!this.mBitmap.isRecycled())) {
      return Bitmap.createBitmap(this.mBitmap, 0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
    }
    return null;
  }
  
  public Rect getTorchRect()
  {
    return this.mTorchRect;
  }
  
  public boolean isAnimating()
  {
    return this.mState != 0;
  }
  
  public void markupCard()
  {
    if (this.mBitmap == null) {}
    for (;;)
    {
      return;
      if (this.mDetectedCard.flipped)
      {
        localObject = new Matrix();
        ((Matrix)localObject).setRotate(180.0F, this.mBitmap.getWidth() / 2, this.mBitmap.getHeight() / 2);
        this.mBitmap = Bitmap.createBitmap(this.mBitmap, 0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight(), (Matrix)localObject, false);
      }
      Object localObject = new Canvas(this.mBitmap);
      Paint localPaint = new Paint();
      Util.setupTextPaintStyle(localPaint);
      localPaint.setTextSize(28.0F * this.mScale);
      int j = this.mDetectedCard.cardNumber.length();
      float f = this.mBitmap.getWidth() / 428.0F;
      int k = (int)(this.mDetectedCard.yoff * f - 6.0F);
      int i = 0;
      while (i < j)
      {
        int m = (int)(this.mDetectedCard.xoff[i] * f);
        ((Canvas)localObject).drawText("" + this.mDetectedCard.cardNumber.charAt(i), m, k, localPaint);
        i += 1;
      }
    }
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if ((this.mGuide == null) || (this.mCameraPreviewRect == null)) {}
    do
    {
      return;
      paramCanvas.save();
      this.mGradientDrawable.draw(paramCanvas);
      if ((this.mRotation == 0) || (this.mRotation == 180)) {}
      for (int i = (this.mGuide.bottom - this.mGuide.top) / 4;; i = (this.mGuide.right - this.mGuide.left) / 4)
      {
        if ((this.mDInfo != null) && (this.mDInfo.numVisibleEdges() == 4)) {
          paramCanvas.drawPath(this.mLockedBackgroundPath, this.mLockedBackgroundPaint);
        }
        this.mGuidePaint.clearShadowLayer();
        this.mGuidePaint.setStyle(Paint.Style.FILL);
        this.mGuidePaint.setColor(this.guideColor);
        paramCanvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.top, this.mGuide.left + i, this.mGuide.top), this.mGuidePaint);
        paramCanvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.top, this.mGuide.left, this.mGuide.top + i), this.mGuidePaint);
        paramCanvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.top, this.mGuide.right - i, this.mGuide.top), this.mGuidePaint);
        paramCanvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.top, this.mGuide.right, this.mGuide.top + i), this.mGuidePaint);
        paramCanvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.bottom, this.mGuide.left + i, this.mGuide.bottom), this.mGuidePaint);
        paramCanvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.bottom, this.mGuide.left, this.mGuide.bottom - i), this.mGuidePaint);
        paramCanvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.bottom, this.mGuide.right - i, this.mGuide.bottom), this.mGuidePaint);
        paramCanvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.bottom, this.mGuide.right, this.mGuide.bottom - i), this.mGuidePaint);
        if (this.mDInfo == null) {
          break;
        }
        if (this.mDInfo.topEdge) {
          paramCanvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.top, this.mGuide.right, this.mGuide.top), this.mGuidePaint);
        }
        if (this.mDInfo.bottomEdge) {
          paramCanvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.bottom, this.mGuide.right, this.mGuide.bottom), this.mGuidePaint);
        }
        if (this.mDInfo.leftEdge) {
          paramCanvas.drawRect(guideStrokeRect(this.mGuide.left, this.mGuide.top, this.mGuide.left, this.mGuide.bottom), this.mGuidePaint);
        }
        if (this.mDInfo.rightEdge) {
          paramCanvas.drawRect(guideStrokeRect(this.mGuide.right, this.mGuide.top, this.mGuide.right, this.mGuide.bottom), this.mGuidePaint);
        }
        if (this.mDInfo.numVisibleEdges() >= 3) {
          break;
        }
        float f2 = 34.0F * this.mScale;
        float f1 = 26.0F * this.mScale;
        Util.setupTextPaintStyle(this.mGuidePaint);
        this.mGuidePaint.setTextAlign(Paint.Align.CENTER);
        this.mGuidePaint.setTextSize(f1);
        paramCanvas.translate(this.mGuide.left + this.mGuide.width() / 2, this.mGuide.top + this.mGuide.height() / 2);
        paramCanvas.rotate(this.mRotationFlip * this.mRotation);
        if ((this.scanInstructions == null) || (this.scanInstructions == "")) {
          break;
        }
        String[] arrayOfString = this.scanInstructions.split("\n");
        f1 = -(((arrayOfString.length - 1) * f2 - f1) / 2.0F) - 3.0F;
        i = 0;
        while (i < arrayOfString.length)
        {
          paramCanvas.drawText(arrayOfString[i], 0.0F, f1, this.mGuidePaint);
          f1 += f2;
          i += 1;
        }
      }
      paramCanvas.restore();
      if (!this.hideCardIOLogo)
      {
        paramCanvas.save();
        paramCanvas.translate(this.mLogoRect.exactCenterX(), this.mLogoRect.exactCenterY());
        paramCanvas.rotate(this.mRotationFlip * this.mRotation);
        this.mLogo.draw(paramCanvas, 100.0F * this.mScale, 50.0F * this.mScale);
        paramCanvas.restore();
      }
    } while (!this.mShowTorch);
    paramCanvas.save();
    paramCanvas.translate(this.mTorchRect.exactCenterX(), this.mTorchRect.exactCenterY());
    paramCanvas.rotate(this.mRotationFlip * this.mRotation);
    this.mTorch.draw(paramCanvas);
    paramCanvas.restore();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    try
    {
      if ((paramMotionEvent.getAction() & 0xFF) != 0) {
        break label174;
      }
      paramMotionEvent = new Point((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      Rect localRect = Util.rectGivenCenter(paramMotionEvent, 20, 20);
      Log.d(TAG, "onTouchEvent: " + paramMotionEvent);
      if ((this.mShowTorch) && (this.mTorchRect != null) && (Rect.intersects(this.mTorchRect, localRect)))
      {
        Log.d(TAG, "torch touched");
        ((CardIOActivity)this.mScanActivityRef.get()).toggleFlash();
      }
      else if ((this.mLogoRect != null) && (Rect.intersects(this.mLogoRect, localRect)))
      {
        Log.d(TAG, "logo touched");
      }
    }
    catch (NullPointerException paramMotionEvent)
    {
      Log.d(TAG, "NullPointerException caught in onTouchEvent method");
    }
    ((CardIOActivity)this.mScanActivityRef.get()).triggerAutoFocus();
    label174:
    return false;
  }
  
  public void setBitmap(Bitmap paramBitmap)
  {
    if (this.mBitmap != null) {
      this.mBitmap.recycle();
    }
    this.mBitmap = paramBitmap;
    if (this.mBitmap != null) {
      decorateBitmap();
    }
  }
  
  public void setCameraPreviewRect(Rect paramRect)
  {
    this.mCameraPreviewRect = paramRect;
  }
  
  public void setDetectedCard(CreditCard paramCreditCard)
  {
    this.mDetectedCard = paramCreditCard;
  }
  
  public void setDetectionInfo(DetectionInfo paramDetectionInfo)
  {
    if ((this.mDInfo != null) && (!this.mDInfo.sameEdgesAs(paramDetectionInfo))) {
      invalidate();
    }
    this.mDInfo = paramDetectionInfo;
  }
  
  public void setGuideAndRotation(Rect paramRect, int paramInt)
  {
    Log.d(TAG, "setGuideAndRotation: " + paramRect + ", " + paramInt);
    this.mRotation = paramInt;
    this.mGuide = paramRect;
    invalidate();
    if (this.mRotation % 180 != 0) {
      paramRect = new Point((int)(this.mScale * 40.0F), (int)(this.mScale * 60.0F));
    }
    for (this.mRotationFlip = -1;; this.mRotationFlip = 1)
    {
      if (this.mCameraPreviewRect != null)
      {
        Log.d(TAG, "" + this.mCameraPreviewRect + ", " + paramRect + ", " + this.mCameraPreviewRect + ", " + paramRect);
        this.mTorchRect = Util.rectGivenCenter(new Point(this.mCameraPreviewRect.left + paramRect.x, this.mCameraPreviewRect.top + paramRect.y), (int)(70.0F * this.mScale), (int)(this.mScale * 50.0F));
        this.mLogoRect = Util.rectGivenCenter(new Point(this.mCameraPreviewRect.right - paramRect.x, this.mCameraPreviewRect.top + paramRect.y), (int)(100.0F * this.mScale), (int)(this.mScale * 50.0F));
        this.mGradientDrawable = new GradientDrawable(GRADIENT_ORIENTATIONS[(this.mRotation / 90 % 4)], new int[] { -1, -16777216 });
        this.mGradientDrawable.setGradientType(0);
        this.mGradientDrawable.setBounds(this.mGuide);
        this.mGradientDrawable.setAlpha(50);
        this.mLockedBackgroundPath = new Path();
        this.mLockedBackgroundPath.addRect(new RectF(this.mCameraPreviewRect), Path.Direction.CW);
        this.mLockedBackgroundPath.addRect(new RectF(this.mGuide), Path.Direction.CCW);
      }
      return;
      paramRect = new Point((int)(this.mScale * 60.0F), (int)(this.mScale * 40.0F));
    }
  }
  
  public void setGuideColor(int paramInt)
  {
    this.guideColor = paramInt;
  }
  
  public void setHideCardIOLogo(boolean paramBoolean)
  {
    this.hideCardIOLogo = paramBoolean;
  }
  
  public void setScanInstructions(String paramString)
  {
    this.scanInstructions = paramString;
  }
  
  public void setTorchOn(boolean paramBoolean)
  {
    this.mTorch.setOn(paramBoolean);
    invalidate();
  }
  
  public void setUseCardIOLogo(boolean paramBoolean)
  {
    this.mLogo.loadLogo(paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/OverlayView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */