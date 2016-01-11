package com.gojek.gobox.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import com.squareup.picasso.Transformation;

public class CircleTransform
  implements Transformation
{
  public String key()
  {
    return "circle";
  }
  
  public Bitmap transform(Bitmap paramBitmap)
  {
    int i = Math.min(paramBitmap.getWidth(), paramBitmap.getHeight());
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap, (paramBitmap.getWidth() - i) / 2, (paramBitmap.getHeight() - i) / 2, i, i);
    if (localBitmap != paramBitmap) {
      paramBitmap.recycle();
    }
    paramBitmap = Bitmap.createBitmap(i, i, paramBitmap.getConfig());
    Canvas localCanvas = new Canvas(paramBitmap);
    Paint localPaint = new Paint();
    localPaint.setShader(new BitmapShader(localBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
    localPaint.setAntiAlias(true);
    float f = i / 2.0F;
    localCanvas.drawCircle(f, f, f, localPaint);
    localBitmap.recycle();
    return paramBitmap;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/CircleTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */