package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ViewSwitcher;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ez
public class u
  extends bd.a
  implements aa, bw, bz, cb, cn, dn, dq, fa.a, fd.a, gd, t
{
  private av lp;
  private final ct lq;
  private final b lr;
  private final ab ls;
  private final ae lt;
  private boolean lu;
  private final ComponentCallbacks lv = new ComponentCallbacks()
  {
    public void onConfigurationChanged(Configuration paramAnonymousConfiguration)
    {
      if ((u.a(u.this) != null) && (u.a(u.this).lI != null) && (u.a(u.this).lI.rN != null)) {
        u.a(u.this).lI.rN.bS();
      }
    }
    
    public void onLowMemory() {}
  };
  
  public u(Context paramContext, ay paramay, String paramString, ct paramct, gt paramgt)
  {
    this(new b(paramContext, paramay, paramString, paramgt), paramct, null);
  }
  
  u(b paramb, ct paramct, ab paramab)
  {
    this.lr = paramb;
    this.lq = paramct;
    if (paramab != null) {}
    for (;;)
    {
      this.ls = paramab;
      this.lt = new ae();
      gj.q(this.lr.lB);
      gb.a(this.lr.lB, this.lr.lD);
      Z();
      return;
      paramab = new ab(this);
    }
  }
  
  private void Z()
  {
    if ((Build.VERSION.SDK_INT >= 14) && (this.lr != null) && (this.lr.lB != null)) {
      this.lr.lB.registerComponentCallbacks(this.lv);
    }
  }
  
  private fi.a a(av paramav, Bundle paramBundle)
  {
    ApplicationInfo localApplicationInfo = this.lr.lB.getApplicationInfo();
    try
    {
      PackageInfo localPackageInfo = this.lr.lB.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 0);
      String str = null;
      Object localObject2 = str;
      if (!this.lr.lH.og)
      {
        localObject2 = str;
        if (this.lr.lz.getParent() != null)
        {
          localObject2 = new int[2];
          this.lr.lz.getLocationOnScreen((int[])localObject2);
          int k = localObject2[0];
          int m = localObject2[1];
          localObject2 = this.lr.lB.getResources().getDisplayMetrics();
          int n = this.lr.lz.getWidth();
          int i1 = this.lr.lz.getHeight();
          int j = 0;
          int i = j;
          if (this.lr.lz.isShown())
          {
            i = j;
            if (k + n > 0)
            {
              i = j;
              if (m + i1 > 0)
              {
                i = j;
                if (k <= ((DisplayMetrics)localObject2).widthPixels)
                {
                  i = j;
                  if (m <= ((DisplayMetrics)localObject2).heightPixels) {
                    i = 1;
                  }
                }
              }
            }
          }
          localObject2 = new Bundle(5);
          ((Bundle)localObject2).putInt("x", k);
          ((Bundle)localObject2).putInt("y", m);
          ((Bundle)localObject2).putInt("width", n);
          ((Bundle)localObject2).putInt("height", i1);
          ((Bundle)localObject2).putInt("visible", i);
        }
      }
      str = gb.cW();
      this.lr.lK = new ga(str, this.lr.lA);
      this.lr.lK.e(paramav);
      Bundle localBundle = gb.a(this.lr.lB, this, str);
      return new fi.a((Bundle)localObject2, paramav, this.lr.lH, this.lr.lA, localApplicationInfo, localPackageInfo, str, gb.vK, this.lr.lD, localBundle, this.lr.lS, paramBundle, gb.dc());
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject1 = null;
      }
    }
  }
  
  private gv a(v paramv)
  {
    if (this.lr.lH.og)
    {
      localObject = gv.a(this.lr.lB, this.lr.lH, false, false, this.lr.lC, this.lr.lD);
      ((gv)localObject).du().a(this, null, this, this, true, this, this, paramv);
      return (gv)localObject;
    }
    Object localObject = this.lr.lz.getNextView();
    if ((localObject instanceof gv))
    {
      localObject = (gv)localObject;
      ((gv)localObject).a(this.lr.lB, this.lr.lH);
    }
    for (;;)
    {
      ((gv)localObject).du().a(this, this, this, this, false, this, paramv);
      return (gv)localObject;
      if (localObject != null) {
        this.lr.lz.removeView((View)localObject);
      }
      gv localgv = gv.a(this.lr.lB, this.lr.lH, false, false, this.lr.lC, this.lr.lD);
      localObject = localgv;
      if (this.lr.lH.oh == null)
      {
        c(localgv);
        localObject = localgv;
      }
    }
  }
  
  private void a(int paramInt)
  {
    gs.W("Failed to load ad: " + paramInt);
    if (this.lr.lE != null) {}
    try
    {
      this.lr.lE.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not call AdListener.onAdFailedToLoad().", localRemoteException);
    }
  }
  
  private void aa()
  {
    if ((Build.VERSION.SDK_INT >= 14) && (this.lr != null) && (this.lr.lB != null)) {
      this.lr.lB.unregisterComponentCallbacks(this.lv);
    }
  }
  
  private void ak()
  {
    gs.U("Ad closing.");
    if (this.lr.lE != null) {}
    try
    {
      this.lr.lE.onAdClosed();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not call AdListener.onAdClosed().", localRemoteException);
    }
  }
  
  private void al()
  {
    gs.U("Ad leaving application.");
    if (this.lr.lE != null) {}
    try
    {
      this.lr.lE.onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not call AdListener.onAdLeftApplication().", localRemoteException);
    }
  }
  
  private void am()
  {
    gs.U("Ad opening.");
    if (this.lr.lE != null) {}
    try
    {
      this.lr.lE.onAdOpened();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not call AdListener.onAdOpened().", localRemoteException);
    }
  }
  
  private void an()
  {
    gs.U("Ad finished loading.");
    if (this.lr.lE != null) {}
    try
    {
      this.lr.lE.onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not call AdListener.onAdLoaded().", localRemoteException);
    }
  }
  
  private void ao()
  {
    try
    {
      if (((this.lr.lI.vu instanceof bo)) && (this.lr.lQ != null)) {
        this.lr.lQ.a((bo)this.lr.lI.vu);
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", localRemoteException);
    }
  }
  
  private void ap()
  {
    try
    {
      if (((this.lr.lI.vu instanceof bp)) && (this.lr.lR != null)) {
        this.lr.lR.a((bp)this.lr.lI.vu);
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.d("Could not call OnContentAdLoadedListener.onContentAdLoaded().", localRemoteException);
    }
  }
  
  private void at()
  {
    if (this.lr.lI != null)
    {
      if (this.lr.lW == 0) {
        this.lr.lI.rN.destroy();
      }
      this.lr.lI = null;
      this.lr.lX = false;
    }
  }
  
  /* Error */
  private boolean b(fz paramfz)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 423	com/google/android/gms/internal/fz:tI	Z
    //   4: ifeq +184 -> 188
    //   7: aload_1
    //   8: getfield 427	com/google/android/gms/internal/fz:qz	Lcom/google/android/gms/internal/cu;
    //   11: invokeinterface 433 1 0
    //   16: invokestatic 439	com/google/android/gms/dynamic/e:f	(Lcom/google/android/gms/dynamic/d;)Ljava/lang/Object;
    //   19: checkcast 441	android/view/View
    //   22: astore_1
    //   23: aload_0
    //   24: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   27: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   30: invokevirtual 270	com/google/android/gms/internal/u$a:getNextView	()Landroid/view/View;
    //   33: astore_2
    //   34: aload_2
    //   35: ifnull +14 -> 49
    //   38: aload_0
    //   39: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   42: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   45: aload_2
    //   46: invokevirtual 280	com/google/android/gms/internal/u$a:removeView	(Landroid/view/View;)V
    //   49: aload_0
    //   50: aload_1
    //   51: invokespecial 287	com/google/android/gms/internal/u:c	(Landroid/view/View;)V
    //   54: aload_0
    //   55: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   58: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   61: invokevirtual 444	com/google/android/gms/internal/u$a:getChildCount	()I
    //   64: iconst_1
    //   65: if_icmple +13 -> 78
    //   68: aload_0
    //   69: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   72: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   75: invokevirtual 447	com/google/android/gms/internal/u$a:showNext	()V
    //   78: aload_0
    //   79: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   82: getfield 370	com/google/android/gms/internal/u$b:lI	Lcom/google/android/gms/internal/fz;
    //   85: ifnull +70 -> 155
    //   88: aload_0
    //   89: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   92: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   95: invokevirtual 270	com/google/android/gms/internal/u$a:getNextView	()Landroid/view/View;
    //   98: astore_1
    //   99: aload_1
    //   100: instanceof 254
    //   103: ifeq +158 -> 261
    //   106: aload_1
    //   107: checkcast 254	com/google/android/gms/internal/gv
    //   110: aload_0
    //   111: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   114: getfield 85	com/google/android/gms/internal/u$b:lB	Landroid/content/Context;
    //   117: aload_0
    //   118: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   121: getfield 144	com/google/android/gms/internal/u$b:lH	Lcom/google/android/gms/internal/ay;
    //   124: invokevirtual 273	com/google/android/gms/internal/gv:a	(Landroid/content/Context;Lcom/google/android/gms/internal/ay;)V
    //   127: aload_0
    //   128: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   131: getfield 370	com/google/android/gms/internal/u$b:lI	Lcom/google/android/gms/internal/fz;
    //   134: getfield 427	com/google/android/gms/internal/fz:qz	Lcom/google/android/gms/internal/cu;
    //   137: ifnull +18 -> 155
    //   140: aload_0
    //   141: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   144: getfield 370	com/google/android/gms/internal/u$b:lI	Lcom/google/android/gms/internal/fz;
    //   147: getfield 427	com/google/android/gms/internal/fz:qz	Lcom/google/android/gms/internal/cu;
    //   150: invokeinterface 448 1 0
    //   155: aload_0
    //   156: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   159: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   162: iconst_0
    //   163: invokevirtual 451	com/google/android/gms/internal/u$a:setVisibility	(I)V
    //   166: iconst_1
    //   167: ireturn
    //   168: astore_1
    //   169: ldc_w 453
    //   172: aload_1
    //   173: invokestatic 326	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   176: iconst_0
    //   177: ireturn
    //   178: astore_1
    //   179: ldc_w 455
    //   182: aload_1
    //   183: invokestatic 326	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   186: iconst_0
    //   187: ireturn
    //   188: aload_1
    //   189: getfield 458	com/google/android/gms/internal/fz:vr	Lcom/google/android/gms/internal/ay;
    //   192: ifnull -138 -> 54
    //   195: aload_1
    //   196: getfield 411	com/google/android/gms/internal/fz:rN	Lcom/google/android/gms/internal/gv;
    //   199: aload_1
    //   200: getfield 458	com/google/android/gms/internal/fz:vr	Lcom/google/android/gms/internal/ay;
    //   203: invokevirtual 461	com/google/android/gms/internal/gv:a	(Lcom/google/android/gms/internal/ay;)V
    //   206: aload_0
    //   207: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   210: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   213: invokevirtual 464	com/google/android/gms/internal/u$a:removeAllViews	()V
    //   216: aload_0
    //   217: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   220: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   223: aload_1
    //   224: getfield 458	com/google/android/gms/internal/fz:vr	Lcom/google/android/gms/internal/ay;
    //   227: getfield 465	com/google/android/gms/internal/ay:widthPixels	I
    //   230: invokevirtual 468	com/google/android/gms/internal/u$a:setMinimumWidth	(I)V
    //   233: aload_0
    //   234: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   237: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   240: aload_1
    //   241: getfield 458	com/google/android/gms/internal/fz:vr	Lcom/google/android/gms/internal/ay;
    //   244: getfield 469	com/google/android/gms/internal/ay:heightPixels	I
    //   247: invokevirtual 472	com/google/android/gms/internal/u$a:setMinimumHeight	(I)V
    //   250: aload_0
    //   251: aload_1
    //   252: getfield 411	com/google/android/gms/internal/fz:rN	Lcom/google/android/gms/internal/gv;
    //   255: invokespecial 287	com/google/android/gms/internal/u:c	(Landroid/view/View;)V
    //   258: goto -204 -> 54
    //   261: aload_1
    //   262: ifnull -135 -> 127
    //   265: aload_0
    //   266: getfield 72	com/google/android/gms/internal/u:lr	Lcom/google/android/gms/internal/u$b;
    //   269: getfield 153	com/google/android/gms/internal/u$b:lz	Lcom/google/android/gms/internal/u$a;
    //   272: aload_1
    //   273: invokevirtual 280	com/google/android/gms/internal/u$a:removeView	(Landroid/view/View;)V
    //   276: goto -149 -> 127
    //   279: astore_1
    //   280: ldc_w 474
    //   283: invokestatic 311	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   286: goto -131 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	289	0	this	u
    //   0	289	1	paramfz	fz
    //   33	13	2	localView	View
    // Exception table:
    //   from	to	target	type
    //   7	23	168	android/os/RemoteException
    //   49	54	178	java/lang/Throwable
    //   140	155	279	android/os/RemoteException
  }
  
  private void c(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-2, -2);
    this.lr.lz.addView(paramView, localLayoutParams);
  }
  
  private void c(boolean paramBoolean)
  {
    if (this.lr.lI == null) {
      gs.W("Ad state was null when trying to ping impression URLs.");
    }
    do
    {
      return;
      gs.S("Pinging Impression URLs.");
      this.lr.lK.cO();
      if (this.lr.lI.qg != null) {
        gj.a(this.lr.lB, this.lr.lD.wD, this.lr.lI.qg);
      }
      if ((this.lr.lI.vq != null) && (this.lr.lI.vq.qg != null)) {
        cr.a(this.lr.lB, this.lr.lD.wD, this.lr.lI, this.lr.lA, paramBoolean, this.lr.lI.vq.qg);
      }
    } while ((this.lr.lI.qy == null) || (this.lr.lI.qy.qb == null));
    cr.a(this.lr.lB, this.lr.lD.wD, this.lr.lI, this.lr.lA, paramBoolean, this.lr.lI.qy.qb);
  }
  
  public d X()
  {
    o.aT("getAdFrame must be called on the main UI thread.");
    return e.k(this.lr.lz);
  }
  
  public ay Y()
  {
    o.aT("getAdSize must be called on the main UI thread.");
    return this.lr.lH;
  }
  
  Bundle a(an paraman)
  {
    if (paraman == null) {}
    for (;;)
    {
      return null;
      if (paraman.aZ()) {
        paraman.wakeup();
      }
      Object localObject = paraman.aX();
      if (localObject != null)
      {
        paraman = ((ak)localObject).aO();
        gs.S("In AdManger: loadAd, " + ((ak)localObject).toString());
      }
      while (paraman != null)
      {
        localObject = new Bundle(1);
        ((Bundle)localObject).putString("fingerprint", paraman);
        return (Bundle)localObject;
        paraman = null;
      }
    }
  }
  
  public void a(ay paramay)
  {
    o.aT("setAdSize must be called on the main UI thread.");
    this.lr.lH = paramay;
    if ((this.lr.lI != null) && (this.lr.lW == 0)) {
      this.lr.lI.rN.a(paramay);
    }
    if (this.lr.lz.getChildCount() > 1) {
      this.lr.lz.removeView(this.lr.lz.getNextView());
    }
    this.lr.lz.setMinimumWidth(paramay.widthPixels);
    this.lr.lz.setMinimumHeight(paramay.heightPixels);
    this.lr.lz.requestLayout();
  }
  
  public void a(bc parambc)
  {
    o.aT("setAdListener must be called on the main UI thread.");
    this.lr.lE = parambc;
  }
  
  public void a(bf parambf)
  {
    o.aT("setAppEventListener must be called on the main UI thread.");
    this.lr.lL = parambf;
  }
  
  public void a(eh parameh)
  {
    o.aT("setInAppPurchaseListener must be called on the main UI thread.");
    this.lr.lN = parameh;
  }
  
  public void a(el paramel, String paramString)
  {
    o.aT("setPlayStorePurchaseParams must be called on the main UI thread.");
    this.lr.lT = new ee(paramString);
    this.lr.lM = paramel;
    if ((!gb.da()) && (paramel != null)) {
      new dx(this.lr.lB, this.lr.lM, this.lr.lT).start();
    }
  }
  
  public void a(et paramet)
  {
    o.aT("setRawHtmlPublisherAdViewListener must be called on the main UI thread.");
    this.lr.lO = paramet;
  }
  
  public void a(eu parameu)
  {
    o.aT("setRawHtmlPublisherInterstitialAdListener must be called on the main UI thread.");
    this.lr.lP = parameu;
  }
  
  public void a(fz.a parama)
  {
    String str = null;
    this.lr.lF = null;
    this.lr.lJ = parama;
    a(null);
    final Object localObject;
    gv localgv;
    if (!parama.vw.tS)
    {
      localObject = new v();
      localgv = a((v)localObject);
      ((v)localObject).a(new v.b(parama, localgv));
      localgv.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          localObject.ar();
          return false;
        }
      });
      localgv.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localObject.ar();
        }
      });
    }
    for (;;)
    {
      if (parama.lH != null) {
        this.lr.lH = parama.lH;
      }
      if (parama.errorCode != -2)
      {
        a(new fz(parama, localgv, null, null, null, null, null));
        return;
      }
      if ((!parama.vw.tI) && (parama.vw.tR))
      {
        if (parama.vw.rP != null) {
          str = Uri.parse(parama.vw.rP).buildUpon().query(null).build().toString();
        }
        localObject = new er(this, str, parama.vw.tG);
        try
        {
          if ((this.lr.lO != null) && (!this.lr.lH.og) && (this.lr.lO.e(str, parama.vw.tG)))
          {
            this.lr.lW = 1;
            this.lr.lO.a((es)localObject);
            return;
          }
        }
        catch (RemoteException localRemoteException2)
        {
          gs.d("Could not call the rawHtmlPublisherAdViewListener.", localRemoteException2);
          try
          {
            if ((this.lr.lP != null) && (this.lr.lH.og) && (this.lr.lP.e(str, parama.vw.tG)))
            {
              this.lr.lW = 1;
              this.lr.lP.a((es)localObject);
              return;
            }
          }
          catch (RemoteException localRemoteException1)
          {
            gs.d("Could not call the RawHtmlPublisherInterstitialAdListener.", localRemoteException1);
          }
        }
      }
      this.lr.lW = 0;
      this.lr.lG = fd.a(this.lr.lB, this, parama, localgv, this.lq, this);
      return;
      localgv = null;
    }
  }
  
  public void a(fz paramfz)
  {
    this.lr.lG = null;
    if (paramfz.vu != null) {}
    for (boolean bool = true;; bool = false)
    {
      if ((paramfz.errorCode != -2) && (paramfz.errorCode != 3)) {
        gb.b(this.lr.au());
      }
      if (paramfz.errorCode != -1) {
        break;
      }
      return;
    }
    if (a(paramfz, bool)) {
      gs.S("Ad refresh scheduled.");
    }
    if ((paramfz.errorCode == 3) && (paramfz.vq != null) && (paramfz.vq.qh != null))
    {
      gs.S("Pinging no fill URLs.");
      cr.a(this.lr.lB, this.lr.lD.wD, paramfz, this.lr.lA, false, paramfz.vq.qh);
    }
    if (paramfz.errorCode != -2)
    {
      a(paramfz.errorCode);
      return;
    }
    if ((!this.lr.lH.og) && (!bool) && (this.lr.lW == 0))
    {
      if (!b(paramfz))
      {
        a(0);
        return;
      }
      if (this.lr.lz != null) {
        a.a(this.lr.lz).Q(paramfz.tN);
      }
    }
    if ((this.lr.lI != null) && (this.lr.lI.qB != null)) {
      this.lr.lI.qB.a(null);
    }
    if (paramfz.qB != null) {
      paramfz.qB.a(this);
    }
    this.lt.d(this.lr.lI);
    this.lr.lI = paramfz;
    this.lr.lK.j(paramfz.vs);
    this.lr.lK.k(paramfz.vt);
    this.lr.lK.t(this.lr.lH.og);
    this.lr.lK.u(paramfz.tI);
    if ((!this.lr.lH.og) && (!bool) && (this.lr.lW == 0)) {
      c(false);
    }
    if (this.lr.lU == null) {
      this.lr.lU = new ge(this.lr.lA);
    }
    int j;
    int i;
    if (paramfz.vq != null)
    {
      j = paramfz.vq.qk;
      i = paramfz.vq.ql;
    }
    for (;;)
    {
      this.lr.lU.d(j, i);
      if (this.lr.lW == 0)
      {
        if ((!this.lr.lH.og) && (paramfz.rN != null) && ((paramfz.rN.du().dE()) || (paramfz.vp != null)))
        {
          af localaf = this.lt.a(this.lr.lH, this.lr.lI);
          if ((paramfz.rN.du().dE()) && (localaf != null)) {
            localaf.a(new z(paramfz.rN));
          }
        }
        if (this.lr.lI.rN != null)
        {
          this.lr.lI.rN.bS();
          this.lr.lI.rN.du().dF();
        }
        if (bool)
        {
          paramfz = paramfz.vu;
          if ((!(paramfz instanceof bp)) || (this.lr.lR == null)) {
            break label650;
          }
          ap();
        }
        for (;;)
        {
          an();
          return;
          label650:
          if ((!(paramfz instanceof bo)) || (this.lr.lQ == null)) {
            break;
          }
          ao();
        }
        gs.W("No matching listener for retrieved native ad template.");
        a(0);
        return;
      }
      if ((this.lr.lV == null) || (paramfz.vp == null)) {
        break;
      }
      this.lt.a(this.lr.lB, this.lr.lH, this.lr.lI, this.lr.lV, this.lr.lD);
      return;
      i = 0;
      j = 0;
    }
  }
  
  public void a(String paramString, ArrayList<String> paramArrayList)
  {
    paramArrayList = new dy(paramString, paramArrayList, this.lr.lB, this.lr.lD.wD);
    if (this.lr.lN == null)
    {
      gs.W("InAppPurchaseListener is not set. Try to launch default purchase flow.");
      if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.lr.lB) != 0) {
        gs.W("Google Play Service unavailable, cannot launch default purchase flow.");
      }
      for (;;)
      {
        return;
        if (this.lr.lM == null)
        {
          gs.W("PlayStorePurchaseListener is not set.");
          return;
        }
        if (this.lr.lT == null)
        {
          gs.W("PlayStorePurchaseVerifier is not initialized.");
          return;
        }
        try
        {
          boolean bool = this.lr.lM.isValidPurchase(paramString);
          if (!bool) {}
        }
        catch (RemoteException paramString)
        {
          for (;;)
          {
            gs.W("Could not start In-App purchase.");
          }
        }
      }
      dz.a(this.lr.lB, this.lr.lD.wG, new dv(paramArrayList, this.lr.lM, this.lr.lT, this.lr.lB));
      return;
    }
    try
    {
      this.lr.lN.a(paramArrayList);
      return;
    }
    catch (RemoteException paramString)
    {
      gs.W("Could not start In-App purchase.");
    }
  }
  
  public void a(HashSet<ga> paramHashSet)
  {
    this.lr.a(paramHashSet);
  }
  
  public void a(List<String> paramList)
  {
    o.aT("setNativeTemplates must be called on the main UI thread.");
    this.lr.lS = paramList;
  }
  
  public boolean a(av paramav)
  {
    o.aT("loadAd must be called on the main UI thread.");
    if ((this.lr.lF != null) || (this.lr.lG != null))
    {
      if (this.lp != null) {
        gs.W("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
      }
      this.lp = paramav;
    }
    do
    {
      return false;
      if ((this.lr.lH.og) && (this.lr.lI != null))
      {
        gs.W("An interstitial is already loading. Aborting.");
        return false;
      }
    } while (!aq());
    gs.U("Starting ad request.");
    if (!paramav.nW) {
      gs.U("Use AdRequest.Builder.addTestDevice(\"" + gr.v(this.lr.lB) + "\") to get test ads on this device.");
    }
    Bundle localBundle = a(gb.cU().l(this.lr.lB));
    this.ls.cancel();
    this.lr.lW = 0;
    paramav = a(paramav, localBundle);
    this.lr.lF = fa.a(this.lr.lB, paramav, this.lr.lC, this);
    return true;
  }
  
  boolean a(fz paramfz, boolean paramBoolean)
  {
    boolean bool = false;
    Object localObject;
    if (this.lp != null)
    {
      localObject = this.lp;
      this.lp = null;
      if (!this.lr.lH.og) {
        break label96;
      }
      if (this.lr.lW == 0) {
        gj.a(paramfz.rN);
      }
    }
    for (;;)
    {
      return this.ls.ay();
      av localav = paramfz.tx;
      localObject = localav;
      if (localav.extras == null) {
        break;
      }
      bool = localav.extras.getBoolean("_noRefresh", false);
      localObject = localav;
      break;
      label96:
      if ((!(bool | paramBoolean)) && (this.lr.lW == 0)) {
        if (paramfz.qj > 0L) {
          this.ls.a((av)localObject, paramfz.qj);
        } else if ((paramfz.vq != null) && (paramfz.vq.qj > 0L)) {
          this.ls.a((av)localObject, paramfz.vq.qj);
        } else if ((!paramfz.tI) && (paramfz.errorCode == 2)) {
          this.ls.c((av)localObject);
        }
      }
    }
  }
  
  public void ab()
  {
    al();
  }
  
  public void ac()
  {
    this.lt.d(this.lr.lI);
    if (this.lr.lH.og) {
      at();
    }
    this.lu = false;
    ak();
    this.lr.lK.cQ();
  }
  
  public void ad()
  {
    if (this.lr.lH.og) {
      c(false);
    }
    this.lu = true;
    am();
  }
  
  public void ae()
  {
    onAdClicked();
  }
  
  public void af()
  {
    ac();
  }
  
  public void ag()
  {
    ab();
  }
  
  public void ah()
  {
    ad();
  }
  
  public void ai()
  {
    if (this.lr.lI != null) {
      gs.W("Mediation adapter " + this.lr.lI.qA + " refreshed, but mediation adapters should never refresh.");
    }
    c(true);
    an();
  }
  
  public void aj()
  {
    o.aT("recordManualImpression must be called on the main UI thread.");
    if (this.lr.lI == null) {
      gs.W("Ad state was null when trying to ping manual tracking URLs.");
    }
    do
    {
      return;
      gs.S("Pinging manual tracking URLs.");
    } while (this.lr.lI.tK == null);
    gj.a(this.lr.lB, this.lr.lD.wD, this.lr.lI.tK);
  }
  
  public boolean aq()
  {
    boolean bool = true;
    if (!gj.a(this.lr.lB.getPackageManager(), this.lr.lB.getPackageName(), "android.permission.INTERNET"))
    {
      if (!this.lr.lH.og) {
        gr.a(this.lr.lz, this.lr.lH, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
      }
      bool = false;
    }
    if (!gj.p(this.lr.lB))
    {
      if (!this.lr.lH.og) {
        gr.a(this.lr.lz, this.lr.lH, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
      }
      bool = false;
    }
    if ((!bool) && (!this.lr.lH.og)) {
      this.lr.lz.setVisibility(0);
    }
    return bool;
  }
  
  public void ar()
  {
    if (this.lr.lI == null) {
      gs.W("Ad state was null when trying to ping click URLs.");
    }
    do
    {
      return;
      gs.S("Pinging click URLs.");
      this.lr.lK.cP();
      if (this.lr.lI.qf != null) {
        gj.a(this.lr.lB, this.lr.lD.wD, this.lr.lI.qf);
      }
    } while ((this.lr.lI.vq == null) || (this.lr.lI.vq.qf == null));
    cr.a(this.lr.lB, this.lr.lD.wD, this.lr.lI, this.lr.lA, false, this.lr.lI.vq.qf);
  }
  
  public void as()
  {
    c(false);
  }
  
  public void b(View paramView)
  {
    this.lr.lV = paramView;
    a(new fz(this.lr.lJ, null, null, null, null, null, null));
  }
  
  public void b(av paramav)
  {
    ViewParent localViewParent = this.lr.lz.getParent();
    if (((localViewParent instanceof View)) && (((View)localViewParent).isShown()) && (gj.dk()) && (!this.lu))
    {
      a(paramav);
      return;
    }
    gs.U("Ad is not visible. Not refreshing ad.");
    this.ls.c(paramav);
  }
  
  public void b(boolean paramBoolean)
  {
    this.lr.lX = paramBoolean;
  }
  
  public void destroy()
  {
    o.aT("destroy must be called on the main UI thread.");
    aa();
    this.lr.lE = null;
    this.lr.lL = null;
    this.lr.lM = null;
    this.lr.lN = null;
    this.lr.lO = null;
    this.lr.lP = null;
    this.ls.cancel();
    this.lt.stop();
    stopLoading();
    if (this.lr.lz != null) {
      this.lr.lz.removeAllViews();
    }
    if ((this.lr.lI != null) && (this.lr.lI.rN != null)) {
      this.lr.lI.rN.destroy();
    }
    if ((this.lr.lI != null) && (this.lr.lI.qz != null)) {}
    try
    {
      this.lr.lI.qz.destroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      gs.W("Could not destroy mediation adapter.");
    }
  }
  
  public String getMediationAdapterClassName()
  {
    if (this.lr.lI != null) {
      return this.lr.lI.qA;
    }
    return null;
  }
  
  public boolean isReady()
  {
    o.aT("isLoaded must be called on the main UI thread.");
    return (this.lr.lF == null) && (this.lr.lG == null) && (this.lr.lI != null);
  }
  
  public void onAdClicked()
  {
    ar();
  }
  
  public void onAppEvent(String paramString1, String paramString2)
  {
    if (this.lr.lL != null) {}
    try
    {
      this.lr.lL.onAppEvent(paramString1, paramString2);
      return;
    }
    catch (RemoteException paramString1)
    {
      gs.d("Could not call the AppEventListener.", paramString1);
    }
  }
  
  public void pause()
  {
    o.aT("pause must be called on the main UI thread.");
    if ((this.lr.lI != null) && (this.lr.lW == 0)) {
      gj.a(this.lr.lI.rN);
    }
    if ((this.lr.lI != null) && (this.lr.lI.qz != null)) {}
    try
    {
      this.lr.lI.qz.pause();
      this.lt.pause();
      this.ls.pause();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.W("Could not pause mediation adapter.");
      }
    }
  }
  
  public void resume()
  {
    o.aT("resume must be called on the main UI thread.");
    if ((this.lr.lI != null) && (this.lr.lW == 0)) {
      gj.b(this.lr.lI.rN);
    }
    if ((this.lr.lI != null) && (this.lr.lI.qz != null)) {}
    try
    {
      this.lr.lI.qz.resume();
      this.ls.resume();
      this.lt.resume();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        gs.W("Could not resume mediation adapter.");
      }
    }
  }
  
  public void showInterstitial()
  {
    o.aT("showInterstitial must be called on the main UI thread.");
    if (!this.lr.lH.og) {
      gs.W("Cannot call showInterstitial on a banner ad.");
    }
    do
    {
      return;
      if (this.lr.lI == null)
      {
        gs.W("The interstitial has not loaded.");
        return;
      }
    } while (this.lr.lW == 1);
    if (this.lr.lI.rN.dy())
    {
      gs.W("The interstitial is already showing.");
      return;
    }
    this.lr.lI.rN.x(true);
    if ((this.lr.lI.rN.du().dE()) || (this.lr.lI.vp != null))
    {
      af localaf = this.lt.a(this.lr.lH, this.lr.lI);
      if ((this.lr.lI.rN.du().dE()) && (localaf != null)) {
        localaf.a(new z(this.lr.lI.rN));
      }
    }
    if (this.lr.lI.tI) {
      try
      {
        this.lr.lI.qz.showInterstitial();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        gs.d("Could not show interstitial.", localRemoteException);
        at();
        return;
      }
    }
    x localx = new x(this.lr.lX, false);
    Object localObject = localx;
    boolean bool2;
    if ((this.lr.lB instanceof Activity))
    {
      localObject = ((Activity)this.lr.lB).getWindow();
      Rect localRect1 = new Rect();
      Rect localRect2 = new Rect();
      ((Window)localObject).getDecorView().getGlobalVisibleRect(localRect1);
      ((Window)localObject).getDecorView().getWindowVisibleDisplayFrame(localRect2);
      localObject = localx;
      if (localRect1.bottom != 0)
      {
        localObject = localx;
        if (localRect2.bottom != 0)
        {
          bool2 = this.lr.lX;
          if (localRect1.top != localRect2.top) {
            break label431;
          }
        }
      }
    }
    label431:
    for (boolean bool1 = true;; bool1 = false)
    {
      localObject = new x(bool2, bool1);
      localObject = new dm(this, this, this, this.lr.lI.rN, this.lr.lI.orientation, this.lr.lD, this.lr.lI.tN, (x)localObject);
      dk.a(this.lr.lB, (dm)localObject);
      return;
    }
  }
  
  public void stopLoading()
  {
    o.aT("stopLoading must be called on the main UI thread.");
    if ((this.lr.lI != null) && (this.lr.lW == 0))
    {
      this.lr.lI.rN.stopLoading();
      this.lr.lI = null;
    }
    if (this.lr.lF != null) {
      this.lr.lF.cancel();
    }
    if (this.lr.lG != null) {
      this.lr.lG.cancel();
    }
  }
  
  @ez
  private static final class a
    extends ViewSwitcher
  {
    private final gm ly;
    
    public a(Context paramContext)
    {
      super();
      this.ly = new gm(paramContext);
    }
    
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      this.ly.c(paramMotionEvent);
      return false;
    }
  }
  
  @ez
  static class b
  {
    public final String lA;
    public final Context lB;
    public final k lC;
    public final gt lD;
    public bc lE;
    public gg lF;
    public gg lG;
    public ay lH;
    public fz lI;
    public fz.a lJ;
    public ga lK;
    public bf lL;
    public el lM;
    public eh lN;
    public et lO;
    public eu lP;
    public bt lQ;
    public bu lR;
    public List<String> lS;
    public ee lT;
    public ge lU = null;
    public View lV = null;
    public int lW = 0;
    public boolean lX = false;
    private HashSet<ga> lY = null;
    public final u.a lz;
    
    public b(Context paramContext, ay paramay, String paramString, gt paramgt)
    {
      if (paramay.og) {
        this.lz = null;
      }
      for (;;)
      {
        this.lH = paramay;
        this.lA = paramString;
        this.lB = paramContext;
        this.lD = paramgt;
        this.lC = new k(new w(this));
        return;
        this.lz = new u.a(paramContext);
        this.lz.setMinimumWidth(paramay.widthPixels);
        this.lz.setMinimumHeight(paramay.heightPixels);
        this.lz.setVisibility(4);
      }
    }
    
    public void a(HashSet<ga> paramHashSet)
    {
      this.lY = paramHashSet;
    }
    
    public HashSet<ga> au()
    {
      return this.lY;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */