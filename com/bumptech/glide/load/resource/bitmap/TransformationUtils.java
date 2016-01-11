package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public final class TransformationUtils
{
  public static final int PAINT_FLAGS = 6;
  private static final String TAG = "TransformationUtils";
  
  public static Bitmap centerCrop(Bitmap paramBitmap1, Bitmap paramBitmap2, int paramInt1, int paramInt2)
  {
    if (paramBitmap2 == null) {
      localObject = null;
    }
    do
    {
      return (Bitmap)localObject;
      if (paramBitmap2.getWidth() != paramInt1) {
        break;
      }
      localObject = paramBitmap2;
    } while (paramBitmap2.getHeight() == paramInt2);
    float f1 = 0.0F;
    float f2 = 0.0F;
    Object localObject = new Matrix();
    float f3;
    if (paramBitmap2.getWidth() * paramInt2 > paramBitmap2.getHeight() * paramInt1)
    {
      f3 = paramInt2 / paramBitmap2.getHeight();
      f1 = (paramInt1 - paramBitmap2.getWidth() * f3) * 0.5F;
      ((Matrix)localObject).setScale(f3, f3);
      ((Matrix)localObject).postTranslate((int)(f1 + 0.5F), (int)(f2 + 0.5F));
      if (paramBitmap1 == null) {
        break label177;
      }
    }
    for (;;)
    {
      setAlpha(paramBitmap2, paramBitmap1);
      new Canvas(paramBitmap1).drawBitmap(paramBitmap2, (Matrix)localObject, new Paint(6));
      return paramBitmap1;
      f3 = paramInt1 / paramBitmap2.getWidth();
      f2 = (paramInt2 - paramBitmap2.getHeight() * f3) * 0.5F;
      break;
      label177:
      paramBitmap1 = Bitmap.createBitmap(paramInt1, paramInt2, getSafeConfig(paramBitmap2));
    }
  }
  
  public static Bitmap fitCenter(Bitmap paramBitmap, BitmapPool paramBitmapPool, int paramInt1, int paramInt2)
  {
    if ((paramBitmap.getWidth() == paramInt1) && (paramBitmap.getHeight() == paramInt2)) {
      if (Log.isLoggable("TransformationUtils", 2)) {
        Log.v("TransformationUtils", "requested target size matches input, returning input");
      }
    }
    float f;
    int i;
    int j;
    do
    {
      return paramBitmap;
      f = Math.min(paramInt1 / paramBitmap.getWidth(), paramInt2 / paramBitmap.getHeight());
      i = (int)(paramBitmap.getWidth() * f);
      j = (int)(paramBitmap.getHeight() * f);
      if ((paramBitmap.getWidth() != i) || (paramBitmap.getHeight() != j)) {
        break;
      }
    } while (!Log.isLoggable("TransformationUtils", 2));
    Log.v("TransformationUtils", "adjusted target size matches input, returning input");
    return paramBitmap;
    Object localObject2 = getSafeConfig(paramBitmap);
    Object localObject1 = paramBitmapPool.get(i, j, (Bitmap.Config)localObject2);
    paramBitmapPool = (BitmapPool)localObject1;
    if (localObject1 == null) {
      paramBitmapPool = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject2);
    }
    setAlpha(paramBitmap, paramBitmapPool);
    if (Log.isLoggable("TransformationUtils", 2))
    {
      Log.v("TransformationUtils", "request: " + paramInt1 + "x" + paramInt2);
      Log.v("TransformationUtils", "toFit:   " + paramBitmap.getWidth() + "x" + paramBitmap.getHeight());
      Log.v("TransformationUtils", "toReuse: " + paramBitmapPool.getWidth() + "x" + paramBitmapPool.getHeight());
      Log.v("TransformationUtils", "minPct:   " + f);
    }
    localObject1 = new Canvas(paramBitmapPool);
    localObject2 = new Matrix();
    ((Matrix)localObject2).setScale(f, f);
    ((Canvas)localObject1).drawBitmap(paramBitmap, (Matrix)localObject2, new Paint(6));
    return paramBitmapPool;
  }
  
  public static int getExifOrientationDegrees(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 5: 
    case 6: 
      return 90;
    case 3: 
    case 4: 
      return 180;
    }
    return 270;
  }
  
  @Deprecated
  @TargetApi(5)
  public static int getOrientation(String paramString)
  {
    int i = 0;
    try
    {
      int j = getExifOrientationDegrees(new ExifInterface(paramString).getAttributeInt("Orientation", 0));
      i = j;
    }
    catch (Exception localException)
    {
      while (!Log.isLoggable("TransformationUtils", 6)) {}
      Log.e("TransformationUtils", "Unable to get orientation for image with path=" + paramString, localException);
    }
    return i;
    return 0;
  }
  
  private static Bitmap.Config getSafeConfig(Bitmap paramBitmap)
  {
    if (paramBitmap.getConfig() != null) {
      return paramBitmap.getConfig();
    }
    return Bitmap.Config.ARGB_8888;
  }
  
  static void initializeMatrixForRotation(int paramInt, Matrix paramMatrix)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 2: 
      paramMatrix.setScale(-1.0F, 1.0F);
      return;
    case 3: 
      paramMatrix.setRotate(180.0F);
      return;
    case 4: 
      paramMatrix.setRotate(180.0F);
      paramMatrix.postScale(-1.0F, 1.0F);
      return;
    case 5: 
      paramMatrix.setRotate(90.0F);
      paramMatrix.postScale(-1.0F, 1.0F);
      return;
    case 6: 
      paramMatrix.setRotate(90.0F);
      return;
    case 7: 
      paramMatrix.setRotate(-90.0F);
      paramMatrix.postScale(-1.0F, 1.0F);
      return;
    }
    paramMatrix.setRotate(-90.0F);
  }
  
  @Deprecated
  public static Bitmap orientImage(String paramString, Bitmap paramBitmap)
  {
    return rotateImage(paramBitmap, getOrientation(paramString));
  }
  
  public static Bitmap rotateImage(Bitmap paramBitmap, int paramInt)
  {
    Bitmap localBitmap = paramBitmap;
    Object localObject = localBitmap;
    if (paramInt != 0) {}
    try
    {
      localObject = new Matrix();
      ((Matrix)localObject).setRotate(paramInt);
      localObject = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), (Matrix)localObject, true);
      return (Bitmap)localObject;
    }
    catch (Exception paramBitmap)
    {
      do
      {
        localObject = localBitmap;
      } while (!Log.isLoggable("TransformationUtils", 6));
      Log.e("TransformationUtils", "Exception when trying to orient image", paramBitmap);
    }
    return localBitmap;
  }
  
  public static Bitmap rotateImageExif(Bitmap paramBitmap, BitmapPool paramBitmapPool, int paramInt)
  {
    Matrix localMatrix = new Matrix();
    initializeMatrixForRotation(paramInt, localMatrix);
    if (localMatrix.isIdentity()) {
      return paramBitmap;
    }
    RectF localRectF = new RectF(0.0F, 0.0F, paramBitmap.getWidth(), paramBitmap.getHeight());
    localMatrix.mapRect(localRectF);
    paramInt = Math.round(localRectF.width());
    int i = Math.round(localRectF.height());
    Bitmap.Config localConfig = getSafeConfig(paramBitmap);
    Bitmap localBitmap = paramBitmapPool.get(paramInt, i, localConfig);
    paramBitmapPool = localBitmap;
    if (localBitmap == null) {
      paramBitmapPool = Bitmap.createBitmap(paramInt, i, localConfig);
    }
    localMatrix.postTranslate(-localRectF.left, -localRectF.top);
    new Canvas(paramBitmapPool).drawBitmap(paramBitmap, localMatrix, new Paint(6));
    return paramBitmapPool;
  }
  
  @TargetApi(12)
  public static void setAlpha(Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    if ((Build.VERSION.SDK_INT >= 12) && (paramBitmap2 != null)) {
      paramBitmap2.setHasAlpha(paramBitmap1.hasAlpha());
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/load/resource/bitmap/TransformationUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */