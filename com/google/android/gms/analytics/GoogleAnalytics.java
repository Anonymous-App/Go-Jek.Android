package com.google.android.gms.analytics;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class GoogleAnalytics
  extends TrackerHandler
{
  private static GoogleAnalytics AC;
  private static boolean Av;
  private Set<a> AA;
  private boolean AB = false;
  private boolean Aw;
  private ae Ax;
  private volatile Boolean Ay = Boolean.valueOf(false);
  private Logger Az;
  private Context mContext;
  private String xL;
  private String xM;
  private f ye;
  
  protected GoogleAnalytics(Context paramContext)
  {
    this(paramContext, s.B(paramContext), q.dZ());
  }
  
  private GoogleAnalytics(Context paramContext, f paramf, ae paramae)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context cannot be null");
    }
    this.mContext = paramContext.getApplicationContext();
    this.ye = paramf;
    this.Ax = paramae;
    g.y(this.mContext);
    ad.y(this.mContext);
    h.y(this.mContext);
    this.Az = new k();
    this.AA = new HashSet();
    eE();
  }
  
  private Tracker a(Tracker paramTracker)
  {
    if (this.xL != null) {
      paramTracker.set("&an", this.xL);
    }
    if (this.xM != null) {
      paramTracker.set("&av", this.xM);
    }
    return paramTracker;
  }
  
  private int ai(String paramString)
  {
    paramString = paramString.toLowerCase();
    if ("verbose".equals(paramString)) {
      return 0;
    }
    if ("info".equals(paramString)) {
      return 1;
    }
    if ("warning".equals(paramString)) {
      return 2;
    }
    if ("error".equals(paramString)) {
      return 3;
    }
    return -1;
  }
  
  static GoogleAnalytics eD()
  {
    try
    {
      GoogleAnalytics localGoogleAnalytics = AC;
      return localGoogleAnalytics;
    }
    finally {}
  }
  
  private void eE()
  {
    if (Av) {}
    Object localObject;
    do
    {
      int i;
      do
      {
        do
        {
          return;
          try
          {
            ApplicationInfo localApplicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 129);
            if (localApplicationInfo == null)
            {
              z.W("Couldn't get ApplicationInfo to load gloabl config.");
              return;
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;)
            {
              z.V("PackageManager doesn't know about package: " + localNameNotFoundException);
              localObject = null;
            }
            localObject = ((ApplicationInfo)localObject).metaData;
          }
        } while (localObject == null);
        i = ((Bundle)localObject).getInt("com.google.android.gms.analytics.globalConfigResource");
      } while (i <= 0);
      localObject = (v)new u(this.mContext).w(i);
    } while (localObject == null);
    a((v)localObject);
  }
  
  public static GoogleAnalytics getInstance(Context paramContext)
  {
    try
    {
      if (AC == null) {
        AC = new GoogleAnalytics(paramContext);
      }
      paramContext = AC;
      return paramContext;
    }
    finally {}
  }
  
  void a(a parama)
  {
    this.AA.add(parama);
    if ((this.mContext instanceof Application)) {
      enableAutoActivityReports((Application)this.mContext);
    }
  }
  
  void a(v paramv)
  {
    z.V("Loading global config values.");
    if (paramv.et())
    {
      this.xL = paramv.eu();
      z.V("app name loaded: " + this.xL);
    }
    if (paramv.ev())
    {
      this.xM = paramv.ew();
      z.V("app version loaded: " + this.xM);
    }
    if (paramv.ex())
    {
      int i = ai(paramv.ey());
      if (i >= 0)
      {
        z.V("log level loaded: " + i);
        getLogger().setLogLevel(i);
      }
    }
    if (paramv.ez()) {
      this.Ax.setLocalDispatchPeriod(paramv.eA());
    }
    if (paramv.eB()) {
      setDryRun(paramv.eC());
    }
  }
  
  void b(a parama)
  {
    this.AA.remove(parama);
  }
  
  @Deprecated
  public void dispatchLocalHits()
  {
    this.Ax.dispatchLocalHits();
  }
  
  public void enableAutoActivityReports(Application paramApplication)
  {
    if ((Build.VERSION.SDK_INT >= 14) && (!this.AB))
    {
      paramApplication.registerActivityLifecycleCallbacks(new b());
      this.AB = true;
    }
  }
  
  void g(Activity paramActivity)
  {
    Iterator localIterator = this.AA.iterator();
    while (localIterator.hasNext()) {
      ((a)localIterator.next()).i(paramActivity);
    }
  }
  
  public boolean getAppOptOut()
  {
    t.ep().a(t.a.zW);
    return this.Ay.booleanValue();
  }
  
  public Logger getLogger()
  {
    return this.Az;
  }
  
  void h(Activity paramActivity)
  {
    Iterator localIterator = this.AA.iterator();
    while (localIterator.hasNext()) {
      ((a)localIterator.next()).j(paramActivity);
    }
  }
  
  public boolean isDryRunEnabled()
  {
    t.ep().a(t.a.Ai);
    return this.Aw;
  }
  
  public Tracker newTracker(int paramInt)
  {
    try
    {
      t.ep().a(t.a.zS);
      Tracker localTracker = new Tracker(null, this, this.mContext);
      if (paramInt > 0)
      {
        ai localai = (ai)new ah(this.mContext).w(paramInt);
        if (localai != null) {
          localTracker.a(localai);
        }
      }
      localTracker = a(localTracker);
      return localTracker;
    }
    finally {}
  }
  
  public Tracker newTracker(String paramString)
  {
    try
    {
      t.ep().a(t.a.zS);
      paramString = a(new Tracker(paramString, this, this.mContext));
      return paramString;
    }
    finally {}
  }
  
  public void reportActivityStart(Activity paramActivity)
  {
    if (!this.AB) {
      g(paramActivity);
    }
  }
  
  public void reportActivityStop(Activity paramActivity)
  {
    if (!this.AB) {
      h(paramActivity);
    }
  }
  
  public void setAppOptOut(boolean paramBoolean)
  {
    t.ep().a(t.a.zV);
    this.Ay = Boolean.valueOf(paramBoolean);
    if (this.Ay.booleanValue()) {
      this.ye.dH();
    }
  }
  
  public void setDryRun(boolean paramBoolean)
  {
    t.ep().a(t.a.Ah);
    this.Aw = paramBoolean;
  }
  
  @Deprecated
  public void setLocalDispatchPeriod(int paramInt)
  {
    this.Ax.setLocalDispatchPeriod(paramInt);
  }
  
  public void setLogger(Logger paramLogger)
  {
    t.ep().a(t.a.Aj);
    this.Az = paramLogger;
  }
  
  void u(Map<String, String> paramMap)
  {
    if (paramMap == null) {
      try
      {
        throw new IllegalArgumentException("hit cannot be null");
      }
      finally {}
    }
    aj.a(paramMap, "&ul", aj.a(Locale.getDefault()));
    aj.a(paramMap, "&sr", ad.eQ());
    paramMap.put("&_u", t.ep().er());
    t.ep().eq();
    this.ye.u(paramMap);
  }
  
  static abstract interface a
  {
    public abstract void i(Activity paramActivity);
    
    public abstract void j(Activity paramActivity);
  }
  
  class b
    implements Application.ActivityLifecycleCallbacks
  {
    b() {}
    
    public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityDestroyed(Activity paramActivity) {}
    
    public void onActivityPaused(Activity paramActivity) {}
    
    public void onActivityResumed(Activity paramActivity) {}
    
    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
    
    public void onActivityStarted(Activity paramActivity)
    {
      GoogleAnalytics.this.g(paramActivity);
    }
    
    public void onActivityStopped(Activity paramActivity)
    {
      GoogleAnalytics.this.h(paramActivity);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/GoogleAnalytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */