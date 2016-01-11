package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.net.Uri;
import android.widget.ImageView;

public final class iy
  extends ImageView
{
  private Uri Lj;
  private int Lk;
  private int Ll;
  private a Lm;
  private int Ln;
  private float Lo;
  
  public void ay(int paramInt)
  {
    this.Lk = paramInt;
  }
  
  public void g(Uri paramUri)
  {
    this.Lj = paramUri;
  }
  
  public int gM()
  {
    return this.Lk;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (this.Lm != null) {
      paramCanvas.clipPath(this.Lm.g(getWidth(), getHeight()));
    }
    super.onDraw(paramCanvas);
    if (this.Ll != 0) {
      paramCanvas.drawColor(this.Ll);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    switch (this.Ln)
    {
    default: 
      return;
    case 1: 
      paramInt1 = getMeasuredHeight();
      paramInt2 = (int)(paramInt1 * this.Lo);
    }
    for (;;)
    {
      setMeasuredDimension(paramInt2, paramInt1);
      return;
      paramInt2 = getMeasuredWidth();
      paramInt1 = (int)(paramInt2 / this.Lo);
    }
  }
  
  public static abstract interface a
  {
    public abstract Path g(int paramInt1, int paramInt2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/iy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */