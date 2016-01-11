package com.google.android.gms.ads.search;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.bg;
import com.google.android.gms.internal.bg.a;

public final class SearchAdRequest
{
  public static final int BORDER_TYPE_DASHED = 1;
  public static final int BORDER_TYPE_DOTTED = 2;
  public static final int BORDER_TYPE_NONE = 0;
  public static final int BORDER_TYPE_SOLID = 3;
  public static final int CALL_BUTTON_COLOR_DARK = 2;
  public static final int CALL_BUTTON_COLOR_LIGHT = 0;
  public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
  public static final String DEVICE_ID_EMULATOR = bg.DEVICE_ID_EMULATOR;
  public static final int ERROR_CODE_INTERNAL_ERROR = 0;
  public static final int ERROR_CODE_INVALID_REQUEST = 1;
  public static final int ERROR_CODE_NETWORK_ERROR = 2;
  public static final int ERROR_CODE_NO_FILL = 3;
  private final bg ld;
  private final int xl;
  private final int xm;
  private final int xn;
  private final int xo;
  private final int xp;
  private final int xq;
  private final int xr;
  private final int xs;
  private final String xt;
  private final int xu;
  private final String xv;
  private final int xw;
  private final int xx;
  private final String xy;
  
  private SearchAdRequest(Builder paramBuilder)
  {
    this.xl = Builder.a(paramBuilder);
    this.xm = Builder.b(paramBuilder);
    this.xn = Builder.c(paramBuilder);
    this.xo = Builder.d(paramBuilder);
    this.xp = Builder.e(paramBuilder);
    this.xq = Builder.f(paramBuilder);
    this.xr = Builder.g(paramBuilder);
    this.xs = Builder.h(paramBuilder);
    this.xt = Builder.i(paramBuilder);
    this.xu = Builder.j(paramBuilder);
    this.xv = Builder.k(paramBuilder);
    this.xw = Builder.l(paramBuilder);
    this.xx = Builder.m(paramBuilder);
    this.xy = Builder.n(paramBuilder);
    this.ld = new bg(Builder.o(paramBuilder), this);
  }
  
  bg V()
  {
    return this.ld;
  }
  
  public int getAnchorTextColor()
  {
    return this.xl;
  }
  
  public int getBackgroundColor()
  {
    return this.xm;
  }
  
  public int getBackgroundGradientBottom()
  {
    return this.xn;
  }
  
  public int getBackgroundGradientTop()
  {
    return this.xo;
  }
  
  public int getBorderColor()
  {
    return this.xp;
  }
  
  public int getBorderThickness()
  {
    return this.xq;
  }
  
  public int getBorderType()
  {
    return this.xr;
  }
  
  public int getCallButtonColor()
  {
    return this.xs;
  }
  
  public String getCustomChannels()
  {
    return this.xt;
  }
  
  public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> paramClass)
  {
    return this.ld.getCustomEventExtrasBundle(paramClass);
  }
  
  public int getDescriptionTextColor()
  {
    return this.xu;
  }
  
  public String getFontFace()
  {
    return this.xv;
  }
  
  public int getHeaderTextColor()
  {
    return this.xw;
  }
  
  public int getHeaderTextSize()
  {
    return this.xx;
  }
  
  public Location getLocation()
  {
    return this.ld.getLocation();
  }
  
  @Deprecated
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return this.ld.getNetworkExtras(paramClass);
  }
  
  public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> paramClass)
  {
    return this.ld.getNetworkExtrasBundle(paramClass);
  }
  
  public String getQuery()
  {
    return this.xy;
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.ld.isTestDevice(paramContext);
  }
  
  public static final class Builder
  {
    private final bg.a le = new bg.a();
    private int xl;
    private int xm;
    private int xn;
    private int xo;
    private int xp;
    private int xq;
    private int xr = 0;
    private int xs;
    private String xt;
    private int xu;
    private String xv;
    private int xw;
    private int xx;
    private String xy;
    
    public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
    {
      this.le.b(paramClass, paramBundle);
      return this;
    }
    
    public Builder addNetworkExtras(NetworkExtras paramNetworkExtras)
    {
      this.le.a(paramNetworkExtras);
      return this;
    }
    
    public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
    {
      this.le.a(paramClass, paramBundle);
      return this;
    }
    
    public Builder addTestDevice(String paramString)
    {
      this.le.s(paramString);
      return this;
    }
    
    public SearchAdRequest build()
    {
      return new SearchAdRequest(this, null);
    }
    
    public Builder setAnchorTextColor(int paramInt)
    {
      this.xl = paramInt;
      return this;
    }
    
    public Builder setBackgroundColor(int paramInt)
    {
      this.xm = paramInt;
      this.xn = Color.argb(0, 0, 0, 0);
      this.xo = Color.argb(0, 0, 0, 0);
      return this;
    }
    
    public Builder setBackgroundGradient(int paramInt1, int paramInt2)
    {
      this.xm = Color.argb(0, 0, 0, 0);
      this.xn = paramInt2;
      this.xo = paramInt1;
      return this;
    }
    
    public Builder setBorderColor(int paramInt)
    {
      this.xp = paramInt;
      return this;
    }
    
    public Builder setBorderThickness(int paramInt)
    {
      this.xq = paramInt;
      return this;
    }
    
    public Builder setBorderType(int paramInt)
    {
      this.xr = paramInt;
      return this;
    }
    
    public Builder setCallButtonColor(int paramInt)
    {
      this.xs = paramInt;
      return this;
    }
    
    public Builder setCustomChannels(String paramString)
    {
      this.xt = paramString;
      return this;
    }
    
    public Builder setDescriptionTextColor(int paramInt)
    {
      this.xu = paramInt;
      return this;
    }
    
    public Builder setFontFace(String paramString)
    {
      this.xv = paramString;
      return this;
    }
    
    public Builder setHeaderTextColor(int paramInt)
    {
      this.xw = paramInt;
      return this;
    }
    
    public Builder setHeaderTextSize(int paramInt)
    {
      this.xx = paramInt;
      return this;
    }
    
    public Builder setLocation(Location paramLocation)
    {
      this.le.a(paramLocation);
      return this;
    }
    
    public Builder setQuery(String paramString)
    {
      this.xy = paramString;
      return this;
    }
    
    public Builder tagForChildDirectedTreatment(boolean paramBoolean)
    {
      this.le.h(paramBoolean);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/search/SearchAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */