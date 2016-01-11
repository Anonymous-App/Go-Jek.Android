package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Tracker
{
  private final TrackerHandler Bm;
  private ac Bn;
  private final h Bo;
  private final ad Bp;
  private final g Bq;
  private boolean Br;
  private a Bs;
  private ai Bt;
  private ExceptionReporter Bu;
  private Context mContext;
  private final Map<String, String> qM = new HashMap();
  
  Tracker(String paramString, TrackerHandler paramTrackerHandler, Context paramContext)
  {
    this(paramString, paramTrackerHandler, h.dQ(), ad.eQ(), g.dP(), new y("tracking"), paramContext);
  }
  
  Tracker(String paramString, TrackerHandler paramTrackerHandler, h paramh, ad paramad, g paramg, ac paramac, Context paramContext)
  {
    this.Bm = paramTrackerHandler;
    if (paramContext != null) {
      this.mContext = paramContext.getApplicationContext();
    }
    if (paramString != null) {
      this.qM.put("&tid", paramString);
    }
    this.qM.put("useSecure", "1");
    this.Bo = paramh;
    this.Bp = paramad;
    this.Bq = paramg;
    this.qM.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
    this.Bn = paramac;
    this.Bs = new a();
    enableAdvertisingIdCollection(false);
  }
  
  void a(ai paramai)
  {
    z.V("Loading Tracker config values.");
    this.Bt = paramai;
    if (this.Bt.eZ())
    {
      paramai = this.Bt.fa();
      set("&tid", paramai);
      z.V("[Tracker] trackingId loaded: " + paramai);
    }
    if (this.Bt.fb())
    {
      paramai = Double.toString(this.Bt.fc());
      set("&sf", paramai);
      z.V("[Tracker] sample frequency loaded: " + paramai);
    }
    if (this.Bt.fd())
    {
      setSessionTimeout(this.Bt.getSessionTimeout());
      z.V("[Tracker] session timeout loaded: " + eT());
    }
    if (this.Bt.fe())
    {
      enableAutoActivityTracking(this.Bt.ff());
      z.V("[Tracker] auto activity tracking loaded: " + eU());
    }
    if (this.Bt.fg())
    {
      if (this.Bt.fh())
      {
        set("&aip", "1");
        z.V("[Tracker] anonymize ip loaded: true");
      }
      z.V("[Tracker] anonymize ip loaded: false");
    }
    enableExceptionReporting(this.Bt.fi());
  }
  
  long eT()
  {
    return this.Bs.eT();
  }
  
  boolean eU()
  {
    return this.Bs.eU();
  }
  
  public void enableAdvertisingIdCollection(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      this.qM.put("&ate", null);
      this.qM.put("&adid", null);
    }
    do
    {
      return;
      if (this.qM.containsKey("&ate")) {
        this.qM.remove("&ate");
      }
    } while (!this.qM.containsKey("&adid"));
    this.qM.remove("&adid");
  }
  
  public void enableAutoActivityTracking(boolean paramBoolean)
  {
    this.Bs.enableAutoActivityTracking(paramBoolean);
  }
  
  public void enableExceptionReporting(boolean paramBoolean)
  {
    if (this.Br == paramBoolean) {
      return;
    }
    this.Br = paramBoolean;
    if (paramBoolean)
    {
      this.Bu = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), this.mContext);
      Thread.setDefaultUncaughtExceptionHandler(this.Bu);
      z.V("Uncaught exceptions will be reported to Google Analytics.");
      return;
    }
    if (this.Bu != null) {
      Thread.setDefaultUncaughtExceptionHandler(this.Bu.dY());
    }
    for (;;)
    {
      z.V("Uncaught exceptions will not be reported to Google Analytics.");
      return;
      Thread.setDefaultUncaughtExceptionHandler(null);
    }
  }
  
  public String get(String paramString)
  {
    t.ep().a(t.a.zo);
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      if (this.qM.containsKey(paramString)) {
        return (String)this.qM.get(paramString);
      }
      if (paramString.equals("&ul")) {
        return aj.a(Locale.getDefault());
      }
      if ((this.Bo != null) && (this.Bo.ac(paramString))) {
        return this.Bo.getValue(paramString);
      }
      if ((this.Bp != null) && (this.Bp.ac(paramString))) {
        return this.Bp.getValue(paramString);
      }
    } while ((this.Bq == null) || (!this.Bq.ac(paramString)));
    return this.Bq.getValue(paramString);
  }
  
  public void send(Map<String, String> paramMap)
  {
    t.ep().a(t.a.zq);
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(this.qM);
    if (paramMap != null) {
      localHashMap.putAll(paramMap);
    }
    if (TextUtils.isEmpty((CharSequence)localHashMap.get("&tid"))) {
      z.W(String.format("Missing tracking id (%s) parameter.", new Object[] { "&tid" }));
    }
    String str = (String)localHashMap.get("&t");
    paramMap = str;
    if (TextUtils.isEmpty(str))
    {
      z.W(String.format("Missing hit type (%s) parameter.", new Object[] { "&t" }));
      paramMap = "";
    }
    if (this.Bs.eV()) {
      localHashMap.put("&sc", "start");
    }
    paramMap = paramMap.toLowerCase();
    if (("screenview".equals(paramMap)) || ("pageview".equals(paramMap)) || ("appview".equals(paramMap)) || (TextUtils.isEmpty(paramMap)))
    {
      int j = Integer.parseInt((String)this.qM.get("&a")) + 1;
      int i = j;
      if (j >= Integer.MAX_VALUE) {
        i = 1;
      }
      this.qM.put("&a", Integer.toString(i));
    }
    if ((!paramMap.equals("transaction")) && (!paramMap.equals("item")) && (!this.Bn.eJ()))
    {
      z.W("Too many hits sent too quickly, rate limiting invoked.");
      return;
    }
    this.Bm.u(localHashMap);
  }
  
  public void set(String paramString1, String paramString2)
  {
    o.b(paramString1, "Key should be non-null");
    t.ep().a(t.a.zp);
    this.qM.put(paramString1, paramString2);
  }
  
  public void setAnonymizeIp(boolean paramBoolean)
  {
    set("&aip", aj.C(paramBoolean));
  }
  
  public void setAppId(String paramString)
  {
    set("&aid", paramString);
  }
  
  public void setAppInstallerId(String paramString)
  {
    set("&aiid", paramString);
  }
  
  public void setAppName(String paramString)
  {
    set("&an", paramString);
  }
  
  public void setAppVersion(String paramString)
  {
    set("&av", paramString);
  }
  
  public void setClientId(String paramString)
  {
    set("&cid", paramString);
  }
  
  public void setEncoding(String paramString)
  {
    set("&de", paramString);
  }
  
  public void setHostname(String paramString)
  {
    set("&dh", paramString);
  }
  
  public void setLanguage(String paramString)
  {
    set("&ul", paramString);
  }
  
  public void setLocation(String paramString)
  {
    set("&dl", paramString);
  }
  
  public void setPage(String paramString)
  {
    set("&dp", paramString);
  }
  
  public void setReferrer(String paramString)
  {
    set("&dr", paramString);
  }
  
  public void setSampleRate(double paramDouble)
  {
    set("&sf", Double.toHexString(paramDouble));
  }
  
  public void setScreenColors(String paramString)
  {
    set("&sd", paramString);
  }
  
  public void setScreenName(String paramString)
  {
    set("&cd", paramString);
  }
  
  public void setScreenResolution(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) && (paramInt2 < 0))
    {
      z.W("Invalid width or height. The values should be non-negative.");
      return;
    }
    set("&sr", paramInt1 + "x" + paramInt2);
  }
  
  public void setSessionTimeout(long paramLong)
  {
    this.Bs.setSessionTimeout(1000L * paramLong);
  }
  
  public void setTitle(String paramString)
  {
    set("&dt", paramString);
  }
  
  public void setUseSecure(boolean paramBoolean)
  {
    set("useSecure", aj.C(paramBoolean));
  }
  
  public void setViewportSize(String paramString)
  {
    set("&vp", paramString);
  }
  
  private class a
    implements GoogleAnalytics.a
  {
    private boolean Bv = false;
    private int Bw = 0;
    private long Bx = -1L;
    private boolean By = false;
    private long Bz;
    private ju yD = jw.hA();
    
    public a() {}
    
    private void eW()
    {
      GoogleAnalytics localGoogleAnalytics = GoogleAnalytics.eD();
      if (localGoogleAnalytics == null)
      {
        z.T("GoogleAnalytics isn't initialized for the Tracker!");
        return;
      }
      if ((this.Bx >= 0L) || (this.Bv))
      {
        localGoogleAnalytics.a(Tracker.b(Tracker.this));
        return;
      }
      localGoogleAnalytics.b(Tracker.b(Tracker.this));
    }
    
    public long eT()
    {
      return this.Bx;
    }
    
    public boolean eU()
    {
      return this.Bv;
    }
    
    public boolean eV()
    {
      boolean bool = this.By;
      this.By = false;
      return bool;
    }
    
    boolean eX()
    {
      return this.yD.elapsedRealtime() >= this.Bz + Math.max(1000L, this.Bx);
    }
    
    public void enableAutoActivityTracking(boolean paramBoolean)
    {
      this.Bv = paramBoolean;
      eW();
    }
    
    public void i(Activity paramActivity)
    {
      t.ep().a(t.a.An);
      if ((this.Bw == 0) && (eX())) {
        this.By = true;
      }
      this.Bw += 1;
      HashMap localHashMap;
      Tracker localTracker;
      if (this.Bv)
      {
        localHashMap = new HashMap();
        localHashMap.put("&t", "screenview");
        t.ep().B(true);
        localTracker = Tracker.this;
        if (Tracker.c(Tracker.this) == null) {
          break label121;
        }
      }
      label121:
      for (paramActivity = Tracker.c(Tracker.this).k(paramActivity);; paramActivity = paramActivity.getClass().getCanonicalName())
      {
        localTracker.set("&cd", paramActivity);
        Tracker.this.send(localHashMap);
        t.ep().B(false);
        return;
      }
    }
    
    public void j(Activity paramActivity)
    {
      t.ep().a(t.a.Ao);
      this.Bw -= 1;
      this.Bw = Math.max(0, this.Bw);
      if (this.Bw == 0) {
        this.Bz = this.yD.elapsedRealtime();
      }
    }
    
    public void setSessionTimeout(long paramLong)
    {
      this.Bx = paramLong;
      eW();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/Tracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */