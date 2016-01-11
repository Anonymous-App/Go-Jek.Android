package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;

@ez
public final class em
  extends eh.a
{
  private final InAppPurchaseListener oC;
  
  public em(InAppPurchaseListener paramInAppPurchaseListener)
  {
    this.oC = paramInAppPurchaseListener;
  }
  
  public void a(eg parameg)
  {
    this.oC.onInAppPurchaseRequested(new ep(parameg));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/em.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */