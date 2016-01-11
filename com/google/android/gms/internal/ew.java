package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.b;

@ez
public final class ew
  extends et.a
{
  private final b oE;
  private final PublisherAdView sQ;
  
  public ew(b paramb, PublisherAdView paramPublisherAdView)
  {
    this.oE = paramb;
    this.sQ = paramPublisherAdView;
  }
  
  public void a(es parames)
  {
    this.oE.a(this.sQ, new ev(parames));
  }
  
  public boolean e(String paramString1, String paramString2)
  {
    return this.oE.a(this.sQ, paramString1, paramString2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */