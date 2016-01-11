package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

@ez
public class dk
  extends ds.a
{
  private static final int ru = Color.argb(0, 0, 0, 0);
  private gv md;
  private final Activity nr;
  private boolean rA = false;
  private FrameLayout rB;
  private WebChromeClient.CustomViewCallback rC;
  private boolean rD = false;
  private boolean rE = false;
  private boolean rF = false;
  private RelativeLayout rG;
  private dm rv;
  private do rw;
  private c rx;
  private dp ry;
  private boolean rz;
  
  public dk(Activity paramActivity)
  {
    this.nr = paramActivity;
  }
  
  private static RelativeLayout.LayoutParams a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt3, paramInt4);
    localLayoutParams.setMargins(paramInt1, paramInt2, 0, 0);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(9);
    return localLayoutParams;
  }
  
  public static void a(Context paramContext, dm paramdm)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramContext, "com.google.android.gms.ads.AdActivity");
    localIntent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", paramdm.lD.wG);
    dm.a(localIntent, paramdm);
    localIntent.addFlags(524288);
    if (!(paramContext instanceof Activity)) {
      localIntent.addFlags(268435456);
    }
    paramContext.startActivity(localIntent);
  }
  
  public void U()
  {
    this.rz = true;
  }
  
  public void a(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    this.rB = new FrameLayout(this.nr);
    this.rB.setBackgroundColor(-16777216);
    this.rB.addView(paramView, -1, -1);
    this.nr.setContentView(this.rB);
    U();
    this.rC = paramCustomViewCallback;
    this.rA = true;
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.rw != null) {
      this.rw.setLayoutParams(a(paramInt1, paramInt2, paramInt3, paramInt4));
    }
  }
  
  public do bV()
  {
    return this.rw;
  }
  
  public void bW()
  {
    if ((this.rv != null) && (this.rA)) {
      setRequestedOrientation(this.rv.orientation);
    }
    if (this.rB != null)
    {
      this.nr.setContentView(this.rG);
      U();
      this.rB.removeAllViews();
      this.rB = null;
    }
    if (this.rC != null)
    {
      this.rC.onCustomViewHidden();
      this.rC = null;
    }
    this.rA = false;
  }
  
  public void bX()
  {
    this.rG.removeView(this.ry);
    n(true);
  }
  
  void bY()
  {
    if ((!this.nr.isFinishing()) || (this.rE)) {}
    do
    {
      do
      {
        return;
        this.rE = true;
      } while (!this.nr.isFinishing());
      if (this.md != null)
      {
        ca();
        this.rG.removeView(this.md);
        if (this.rx != null)
        {
          this.md.x(false);
          this.rx.rJ.addView(this.md, this.rx.index, this.rx.rI);
        }
      }
    } while ((this.rv == null) || (this.rv.rM == null));
    this.rv.rM.ac();
  }
  
  void bZ()
  {
    this.md.bZ();
  }
  
  public void c(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.rw == null)
    {
      this.rw = new do(this.nr, this.md);
      this.rG.addView(this.rw, 0, a(paramInt1, paramInt2, paramInt3, paramInt4));
      this.md.du().y(false);
    }
  }
  
  void ca()
  {
    this.md.ca();
  }
  
  public void close()
  {
    this.nr.finish();
  }
  
  public void n(boolean paramBoolean)
  {
    RelativeLayout.LayoutParams localLayoutParams;
    if (paramBoolean)
    {
      i = 50;
      this.ry = new dp(this.nr, i);
      localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams.addRule(10);
      if (!paramBoolean) {
        break label86;
      }
    }
    label86:
    for (int i = 11;; i = 9)
    {
      localLayoutParams.addRule(i);
      this.ry.o(this.rv.rQ);
      this.rG.addView(this.ry, localLayoutParams);
      return;
      i = 32;
      break;
    }
  }
  
  public void o(boolean paramBoolean)
  {
    if (this.ry != null) {
      this.ry.o(paramBoolean);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    boolean bool = false;
    if (paramBundle != null) {
      bool = paramBundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
    }
    this.rD = bool;
    try
    {
      this.rv = dm.b(this.nr.getIntent());
      if (this.rv != null) {
        break label68;
      }
      throw new a("Could not get info for ad overlay.");
    }
    catch (a paramBundle)
    {
      gs.W(paramBundle.getMessage());
      this.nr.finish();
    }
    return;
    label68:
    if (this.rv.rW != null)
    {
      this.rF = this.rv.rW.lX;
      label92:
      if (paramBundle == null)
      {
        if (this.rv.rM != null) {
          this.rv.rM.ad();
        }
        if ((this.rv.rT != 1) && (this.rv.rL != null)) {
          this.rv.rL.onAdClicked();
        }
      }
      switch (this.rv.rT)
      {
      }
    }
    for (;;)
    {
      throw new a("Could not determine ad overlay type.");
      this.rF = false;
      break label92;
      p(false);
      return;
      this.rx = new c(this.rv.rN);
      p(false);
      return;
      p(true);
      return;
      if (this.rD)
      {
        this.nr.finish();
        return;
      }
      if (dh.a(this.nr, this.rv.rK, this.rv.rS)) {
        break;
      }
      this.nr.finish();
      return;
    }
  }
  
  public void onDestroy()
  {
    if (this.rw != null) {
      this.rw.destroy();
    }
    if (this.md != null) {
      this.rG.removeView(this.md);
    }
    bY();
  }
  
  public void onPause()
  {
    if (this.rw != null) {
      this.rw.pause();
    }
    bW();
    if ((this.md != null) && ((!this.nr.isFinishing()) || (this.rx == null))) {
      gj.a(this.md);
    }
    bY();
  }
  
  public void onRestart() {}
  
  public void onResume()
  {
    if ((this.rv != null) && (this.rv.rT == 4))
    {
      if (!this.rD) {
        break label47;
      }
      this.nr.finish();
    }
    for (;;)
    {
      if (this.md != null) {
        gj.b(this.md);
      }
      return;
      label47:
      this.rD = true;
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.rD);
  }
  
  public void onStart() {}
  
  public void onStop()
  {
    bY();
  }
  
  void p(boolean paramBoolean)
    throws dk.a
  {
    if (!this.rz) {
      this.nr.requestWindowFeature(1);
    }
    Object localObject = this.nr.getWindow();
    if ((!this.rF) || (this.rv.rW.mh)) {
      ((Window)localObject).setFlags(1024, 1024);
    }
    setRequestedOrientation(this.rv.orientation);
    if (Build.VERSION.SDK_INT >= 11)
    {
      gs.S("Enabling hardware acceleration on the AdActivity window.");
      gn.a((Window)localObject);
    }
    this.rG = new b(this.nr, this.rv.rV);
    boolean bool;
    if (!this.rF)
    {
      this.rG.setBackgroundColor(-16777216);
      this.nr.setContentView(this.rG);
      U();
      bool = this.rv.rN.du().dE();
      if (!paramBoolean) {
        break label436;
      }
      this.md = gv.a(this.nr, this.rv.rN.Y(), true, bool, null, this.rv.lD);
      this.md.du().a(null, null, this.rv.rO, this.rv.rS, true, this.rv.rU, this.rv.rN.du().dD());
      this.md.du().a(new gw.a()
      {
        public void a(gv paramAnonymousgv)
        {
          paramAnonymousgv.bZ();
        }
      });
      if (this.rv.rq == null) {
        break label384;
      }
      this.md.loadUrl(this.rv.rq);
    }
    for (;;)
    {
      this.md.a(this);
      localObject = this.md.getParent();
      if ((localObject != null) && ((localObject instanceof ViewGroup))) {
        ((ViewGroup)localObject).removeView(this.md);
      }
      if (this.rF) {
        this.md.setBackgroundColor(ru);
      }
      this.rG.addView(this.md, -1, -1);
      if (!paramBoolean) {
        bZ();
      }
      n(bool);
      if (this.md.dv()) {
        o(true);
      }
      return;
      this.rG.setBackgroundColor(ru);
      break;
      label384:
      if (this.rv.rR != null)
      {
        this.md.loadDataWithBaseURL(this.rv.rP, this.rv.rR, "text/html", "UTF-8", null);
      }
      else
      {
        throw new a("No URL or HTML to display in ad overlay.");
        label436:
        this.md = this.rv.rN;
        this.md.setContext(this.nr);
      }
    }
  }
  
  public void setRequestedOrientation(int paramInt)
  {
    this.nr.setRequestedOrientation(paramInt);
  }
  
  @ez
  private static final class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
  
  @ez
  private static final class b
    extends RelativeLayout
  {
    private final gm ly;
    
    public b(Context paramContext, String paramString)
    {
      super();
      this.ly = new gm(paramContext, paramString);
    }
    
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      this.ly.c(paramMotionEvent);
      return false;
    }
  }
  
  @ez
  private static final class c
  {
    public final int index;
    public final ViewGroup.LayoutParams rI;
    public final ViewGroup rJ;
    
    public c(gv paramgv)
      throws dk.a
    {
      this.rI = paramgv.getLayoutParams();
      ViewParent localViewParent = paramgv.getParent();
      if ((localViewParent instanceof ViewGroup))
      {
        this.rJ = ((ViewGroup)localViewParent);
        this.index = this.rJ.indexOfChild(paramgv);
        this.rJ.removeView(paramgv);
        paramgv.x(true);
        return;
      }
      throw new dk.a("Could not get the parent of the WebView for an overlay.");
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */