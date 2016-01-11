package com.google.android.gms.ads;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gr;

public final class AdSize
{
  public static final int AUTO_HEIGHT = -2;
  public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
  public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
  public static final int FULL_WIDTH = -1;
  public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
  public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
  public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
  public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
  public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
  private final int lf;
  private final int lg;
  private final String lh;
  
  public AdSize(int paramInt1, int paramInt2) {}
  
  AdSize(int paramInt1, int paramInt2, String paramString)
  {
    if ((paramInt1 < 0) && (paramInt1 != -1)) {
      throw new IllegalArgumentException("Invalid width for AdSize: " + paramInt1);
    }
    if ((paramInt2 < 0) && (paramInt2 != -2)) {
      throw new IllegalArgumentException("Invalid height for AdSize: " + paramInt2);
    }
    this.lf = paramInt1;
    this.lg = paramInt2;
    this.lh = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof AdSize)) {
        return false;
      }
      paramObject = (AdSize)paramObject;
    } while ((this.lf == ((AdSize)paramObject).lf) && (this.lg == ((AdSize)paramObject).lg) && (this.lh.equals(((AdSize)paramObject).lh)));
    return false;
  }
  
  public int getHeight()
  {
    return this.lg;
  }
  
  public int getHeightInPixels(Context paramContext)
  {
    if (this.lg == -2) {
      return ay.b(paramContext.getResources().getDisplayMetrics());
    }
    return gr.a(paramContext, this.lg);
  }
  
  public int getWidth()
  {
    return this.lf;
  }
  
  public int getWidthInPixels(Context paramContext)
  {
    if (this.lf == -1) {
      return ay.a(paramContext.getResources().getDisplayMetrics());
    }
    return gr.a(paramContext, this.lf);
  }
  
  public int hashCode()
  {
    return this.lh.hashCode();
  }
  
  public boolean isAutoHeight()
  {
    return this.lg == -2;
  }
  
  public boolean isFullWidth()
  {
    return this.lf == -1;
  }
  
  public String toString()
  {
    return this.lh;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/AdSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */