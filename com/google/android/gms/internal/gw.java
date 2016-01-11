package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@ez
public class gw
  extends WebViewClient
{
  protected final gv md;
  private final Object mw = new Object();
  private cb pJ;
  private bz pL;
  private v pM;
  private bw pz;
  private a tg;
  private final HashMap<String, by> wP = new HashMap();
  private t wQ;
  private dn wR;
  private boolean wS = false;
  private boolean wT;
  private dq wU;
  private final dg wV;
  
  public gw(gv paramgv, boolean paramBoolean)
  {
    this(paramgv, paramBoolean, new dg(paramgv, paramgv.getContext(), new bl(paramgv.getContext())));
  }
  
  gw(gv paramgv, boolean paramBoolean, dg paramdg)
  {
    this.md = paramgv;
    this.wT = paramBoolean;
    this.wV = paramdg;
  }
  
  private static boolean d(Uri paramUri)
  {
    paramUri = paramUri.getScheme();
    return ("http".equalsIgnoreCase(paramUri)) || ("https".equalsIgnoreCase(paramUri));
  }
  
  private void e(Uri paramUri)
  {
    Object localObject = paramUri.getPath();
    by localby = (by)this.wP.get(localObject);
    if (localby != null)
    {
      paramUri = gj.c(paramUri);
      if (gs.u(2))
      {
        gs.V("Received GMSG: " + (String)localObject);
        localObject = paramUri.keySet().iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str = (String)((Iterator)localObject).next();
          gs.V("  " + str + ": " + (String)paramUri.get(str));
        }
      }
      localby.a(this.md, paramUri);
      return;
    }
    gs.V("No GMSG handler found for GMSG: " + paramUri);
  }
  
  public final void a(dj paramdj)
  {
    dn localdn = null;
    boolean bool = this.md.dy();
    t localt;
    if ((bool) && (!this.md.Y().og))
    {
      localt = null;
      if (!bool) {
        break label69;
      }
    }
    for (;;)
    {
      a(new dm(paramdj, localt, localdn, this.wU, this.md.dx()));
      return;
      localt = this.wQ;
      break;
      label69:
      localdn = this.wR;
    }
  }
  
  protected void a(dm paramdm)
  {
    dk.a(this.md.getContext(), paramdm);
  }
  
  public final void a(a parama)
  {
    this.tg = parama;
  }
  
  public void a(t paramt, dn paramdn, bw parambw, dq paramdq, boolean paramBoolean, bz parambz, cb paramcb, v paramv)
  {
    a(paramt, paramdn, parambw, paramdq, paramBoolean, parambz, paramv);
    a("/setInterstitialProperties", new ca(paramcb));
    this.pJ = paramcb;
  }
  
  public void a(t paramt, dn paramdn, bw parambw, dq paramdq, boolean paramBoolean, bz parambz, v paramv)
  {
    v localv = paramv;
    if (paramv == null) {
      localv = new v(false);
    }
    a("/appEvent", new bv(parambw));
    a("/canOpenURLs", bx.pB);
    a("/click", bx.pC);
    a("/close", bx.pD);
    a("/customClose", bx.pE);
    a("/httpTrack", bx.pF);
    a("/log", bx.pG);
    a("/open", new cd(parambz, localv));
    a("/touch", bx.pH);
    a("/video", bx.pI);
    a("/mraid", new cc());
    this.wQ = paramt;
    this.wR = paramdn;
    this.pz = parambw;
    this.pL = parambz;
    this.wU = paramdq;
    this.pM = localv;
    y(paramBoolean);
  }
  
  public final void a(String paramString, by paramby)
  {
    this.wP.put(paramString, paramby);
  }
  
  public final void a(boolean paramBoolean, int paramInt)
  {
    if ((this.md.dy()) && (!this.md.Y().og)) {}
    for (t localt = null;; localt = this.wQ)
    {
      a(new dm(localt, this.wR, this.wU, this.md, paramBoolean, paramInt, this.md.dx()));
      return;
    }
  }
  
  public final void a(boolean paramBoolean, int paramInt, String paramString)
  {
    dn localdn = null;
    boolean bool = this.md.dy();
    t localt;
    if ((bool) && (!this.md.Y().og))
    {
      localt = null;
      if (!bool) {
        break label89;
      }
    }
    for (;;)
    {
      a(new dm(localt, localdn, this.pz, this.wU, this.md, paramBoolean, paramInt, paramString, this.md.dx(), this.pL));
      return;
      localt = this.wQ;
      break;
      label89:
      localdn = this.wR;
    }
  }
  
  public final void a(boolean paramBoolean, int paramInt, String paramString1, String paramString2)
  {
    boolean bool = this.md.dy();
    t localt;
    if ((bool) && (!this.md.Y().og))
    {
      localt = null;
      if (!bool) {
        break label91;
      }
    }
    label91:
    for (dn localdn = null;; localdn = this.wR)
    {
      a(new dm(localt, localdn, this.pz, this.wU, this.md, paramBoolean, paramInt, paramString1, paramString2, this.md.dx(), this.pL));
      return;
      localt = this.wQ;
      break;
    }
  }
  
  public final void bX()
  {
    synchronized (this.mw)
    {
      this.wS = false;
      this.wT = true;
      final dk localdk = this.md.dt();
      if (localdk != null)
      {
        if (!gr.ds()) {
          gr.wC.post(new Runnable()
          {
            public void run()
            {
              localdk.bX();
            }
          });
        }
      }
      else {
        return;
      }
      localdk.bX();
    }
  }
  
  public v dD()
  {
    return this.pM;
  }
  
  public boolean dE()
  {
    synchronized (this.mw)
    {
      boolean bool = this.wT;
      return bool;
    }
  }
  
  public void dF()
  {
    if (dE()) {
      this.wV.bP();
    }
  }
  
  public final void onLoadResource(WebView paramWebView, String paramString)
  {
    gs.V("Loading resource: " + paramString);
    paramWebView = Uri.parse(paramString);
    if (("gmsg".equalsIgnoreCase(paramWebView.getScheme())) && ("mobileads.google.com".equalsIgnoreCase(paramWebView.getHost()))) {
      e(paramWebView);
    }
  }
  
  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    if (this.tg != null)
    {
      this.tg.a(this.md);
      this.tg = null;
    }
  }
  
  public final void reset()
  {
    synchronized (this.mw)
    {
      this.wP.clear();
      this.wQ = null;
      this.wR = null;
      this.tg = null;
      this.pz = null;
      this.wS = false;
      this.wT = false;
      this.pL = null;
      this.wU = null;
      return;
    }
  }
  
  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    gs.V("AdWebView shouldOverrideUrlLoading: " + paramString);
    Uri localUri = Uri.parse(paramString);
    if (("gmsg".equalsIgnoreCase(localUri.getScheme())) && ("mobileads.google.com".equalsIgnoreCase(localUri.getHost()))) {
      e(localUri);
    }
    for (;;)
    {
      return true;
      if ((this.wS) && (paramWebView == this.md) && (d(localUri))) {
        return super.shouldOverrideUrlLoading(paramWebView, paramString);
      }
      if (!this.md.willNotDraw())
      {
        try
        {
          k localk = this.md.dw();
          paramWebView = localUri;
          if (localk != null)
          {
            paramWebView = localUri;
            if (localk.b(localUri)) {
              paramWebView = localk.a(localUri, this.md.getContext());
            }
          }
        }
        catch (l paramWebView)
        {
          for (;;)
          {
            gs.W("Unable to append parameter to URL: " + paramString);
            paramWebView = localUri;
          }
          this.pM.d(paramString);
        }
        if ((this.pM == null) || (this.pM.av())) {
          a(new dj("android.intent.action.VIEW", paramWebView.toString(), null, null, null, null, null));
        }
      }
      else
      {
        gs.W("AdWebView unable to handle URL: " + paramString);
      }
    }
  }
  
  public final void y(boolean paramBoolean)
  {
    this.wS = paramBoolean;
  }
  
  public static abstract interface a
  {
    public abstract void a(gv paramgv);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */