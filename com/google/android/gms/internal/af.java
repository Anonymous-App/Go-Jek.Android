package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public final class af
  implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener
{
  private static final long mK = TimeUnit.MILLISECONDS.toNanos(100L);
  private WeakReference<ViewTreeObserver> mA;
  private final WeakReference<View> mB;
  private final ad mC;
  private final Context mD;
  private final ah mE;
  private boolean mF;
  private final WindowManager mG;
  private final PowerManager mH;
  private final KeyguardManager mI;
  private ag mJ;
  private boolean mL = false;
  private final BlockingQueue<Runnable> mM = new ArrayBlockingQueue(2);
  private long mN = Long.MIN_VALUE;
  private boolean mO;
  private boolean mP;
  private BroadcastReceiver mQ;
  private final HashSet<ac> mR = new HashSet();
  private boolean mn = false;
  private final Object mw = new Object();
  private final WeakReference<fz> mz;
  
  public af(Context paramContext, ay paramay, fz paramfz, View paramView, gt paramgt)
  {
    this(paramay, paramfz, paramgt, paramView, new aj(paramContext, paramgt));
  }
  
  public af(final ay paramay, fz paramfz, gt paramgt, final View paramView, ah paramah)
  {
    this.mz = new WeakReference(paramfz);
    this.mB = new WeakReference(paramView);
    this.mA = new WeakReference(null);
    this.mO = true;
    this.mC = new ad(UUID.randomUUID().toString(), paramgt, paramay.of, paramfz.vp);
    this.mE = paramah;
    this.mG = ((WindowManager)paramView.getContext().getSystemService("window"));
    this.mH = ((PowerManager)paramView.getContext().getApplicationContext().getSystemService("power"));
    this.mI = ((KeyguardManager)paramView.getContext().getSystemService("keyguard"));
    this.mD = paramView.getContext().getApplicationContext();
    a(paramah);
    this.mE.a(new ah.a()
    {
      public void aM()
      {
        af.b(af.this, true);
        af.this.d(paramView);
        af.this.aD();
      }
    });
    b(this.mE);
    try
    {
      paramay = e(paramView);
      this.mM.add(new Runnable()
      {
        public void run()
        {
          af.this.a(paramay);
        }
      });
      this.mM.add(new Runnable()
      {
        public void run()
        {
          af.this.e(false);
        }
      });
      gs.S("Tracking ad unit: " + this.mC.aC());
      return;
    }
    catch (Throwable paramay)
    {
      for (;;) {}
    }
  }
  
  protected int a(int paramInt, DisplayMetrics paramDisplayMetrics)
  {
    float f = paramDisplayMetrics.density;
    return (int)(paramInt / f);
  }
  
  protected void a(View paramView, Map<String, String> paramMap)
  {
    e(false);
  }
  
  public void a(ac paramac)
  {
    this.mR.add(paramac);
  }
  
  public void a(ag paramag)
  {
    synchronized (this.mw)
    {
      this.mJ = paramag;
      return;
    }
  }
  
  protected void a(ah paramah)
  {
    paramah.f("https://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.html");
  }
  
  protected void a(JSONObject paramJSONObject)
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      JSONObject localJSONObject = new JSONObject();
      localJSONArray.put(paramJSONObject);
      localJSONObject.put("units", localJSONArray);
      this.mE.a("AFMA_updateActiveView", localJSONObject);
      return;
    }
    catch (Throwable paramJSONObject)
    {
      gs.b("Skipping active view message.", paramJSONObject);
    }
  }
  
  protected boolean a(Map<String, String> paramMap)
  {
    if (paramMap == null) {
      return false;
    }
    paramMap = (String)paramMap.get("hashCode");
    if ((!TextUtils.isEmpty(paramMap)) && (paramMap.equals(this.mC.aC()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected void aD()
  {
    synchronized (this.mw)
    {
      if (this.mQ != null) {
        return;
      }
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.SCREEN_ON");
      localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
      this.mQ = new BroadcastReceiver()
      {
        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          af.this.e(false);
        }
      };
      this.mD.registerReceiver(this.mQ, localIntentFilter);
      return;
    }
  }
  
  protected void aE()
  {
    synchronized (this.mw)
    {
      if (this.mQ != null)
      {
        this.mD.unregisterReceiver(this.mQ);
        this.mQ = null;
      }
      return;
    }
  }
  
  public void aF()
  {
    synchronized (this.mw)
    {
      if (this.mO) {
        this.mP = true;
      }
      try
      {
        a(aL());
        gs.S("Untracking ad unit: " + this.mC.aC());
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          gs.b("JSON Failure while processing active view data.", localJSONException);
        }
      }
    }
  }
  
  protected void aG()
  {
    if (this.mJ != null) {
      this.mJ.a(this);
    }
  }
  
  public boolean aH()
  {
    synchronized (this.mw)
    {
      boolean bool = this.mO;
      return bool;
    }
  }
  
  protected void aI()
  {
    Object localObject = (View)this.mB.get();
    if (localObject == null) {}
    ViewTreeObserver localViewTreeObserver;
    do
    {
      return;
      localViewTreeObserver = (ViewTreeObserver)this.mA.get();
      localObject = ((View)localObject).getViewTreeObserver();
    } while (localObject == localViewTreeObserver);
    this.mA = new WeakReference(localObject);
    ((ViewTreeObserver)localObject).addOnScrollChangedListener(this);
    ((ViewTreeObserver)localObject).addOnGlobalLayoutListener(this);
  }
  
  protected void aJ()
  {
    ViewTreeObserver localViewTreeObserver = (ViewTreeObserver)this.mA.get();
    if ((localViewTreeObserver == null) || (!localViewTreeObserver.isAlive())) {
      return;
    }
    localViewTreeObserver.removeOnScrollChangedListener(this);
    localViewTreeObserver.removeGlobalOnLayoutListener(this);
  }
  
  protected JSONObject aK()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("afmaVersion", this.mC.aA()).put("activeViewJSON", this.mC.aB()).put("timestamp", TimeUnit.NANOSECONDS.toMillis(System.nanoTime())).put("adFormat", this.mC.az()).put("hashCode", this.mC.aC());
    return localJSONObject;
  }
  
  protected JSONObject aL()
    throws JSONException
  {
    JSONObject localJSONObject = aK();
    localJSONObject.put("doneReasonCode", "u");
    return localJSONObject;
  }
  
  protected void b(ah paramah)
  {
    paramah.a("/updateActiveView", new by()
    {
      public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
      {
        if (!af.this.a(paramAnonymousMap)) {
          return;
        }
        af.this.a(paramAnonymousgv, paramAnonymousMap);
      }
    });
    paramah.a("/untrackActiveViewUnit", new by()
    {
      public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
      {
        if (!af.this.a(paramAnonymousMap)) {
          return;
        }
        gs.S("Received request to untrack: " + af.b(af.this).aC());
        af.this.destroy();
      }
    });
    paramah.a("/visibilityChanged", new by()
    {
      public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
      {
        if (!af.this.a(paramAnonymousMap)) {}
        while (!paramAnonymousMap.containsKey("isVisible")) {
          return;
        }
        if (("1".equals(paramAnonymousMap.get("isVisible"))) || ("true".equals(paramAnonymousMap.get("isVisible")))) {}
        for (boolean bool = true;; bool = false)
        {
          af.this.d(Boolean.valueOf(bool).booleanValue());
          return;
        }
      }
    });
    paramah.a("/viewabilityChanged", bx.pA);
  }
  
  protected void d(View paramView)
  {
    paramView = new ArrayList();
    this.mM.drainTo(paramView);
    paramView = paramView.iterator();
    while (paramView.hasNext()) {
      ((Runnable)paramView.next()).run();
    }
  }
  
  protected void d(boolean paramBoolean)
  {
    Iterator localIterator = this.mR.iterator();
    while (localIterator.hasNext()) {
      ((ac)localIterator.next()).a(this, paramBoolean);
    }
  }
  
  protected void destroy()
  {
    synchronized (this.mw)
    {
      aJ();
      aE();
      this.mO = false;
    }
    try
    {
      this.mE.destroy();
      aG();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  protected JSONObject e(View paramView)
    throws JSONException
  {
    Object localObject2 = new int[2];
    Object localObject1 = new int[2];
    paramView.getLocationOnScreen((int[])localObject2);
    paramView.getLocationInWindow((int[])localObject1);
    localObject1 = aK();
    DisplayMetrics localDisplayMetrics = paramView.getContext().getResources().getDisplayMetrics();
    Rect localRect1 = new Rect();
    localRect1.left = localObject2[0];
    localRect1.top = localObject2[1];
    localRect1.right = (localRect1.left + paramView.getWidth());
    localRect1.bottom = (localRect1.top + paramView.getHeight());
    localObject2 = new Rect();
    ((Rect)localObject2).right = this.mG.getDefaultDisplay().getWidth();
    ((Rect)localObject2).bottom = this.mG.getDefaultDisplay().getHeight();
    Rect localRect2 = new Rect();
    boolean bool1 = paramView.getGlobalVisibleRect(localRect2, null);
    Rect localRect3 = new Rect();
    boolean bool2 = paramView.getLocalVisibleRect(localRect3);
    ((JSONObject)localObject1).put("viewBox", new JSONObject().put("top", a(((Rect)localObject2).top, localDisplayMetrics)).put("bottom", a(((Rect)localObject2).bottom, localDisplayMetrics)).put("left", a(((Rect)localObject2).left, localDisplayMetrics)).put("right", a(((Rect)localObject2).right, localDisplayMetrics))).put("adBox", new JSONObject().put("top", a(localRect1.top, localDisplayMetrics)).put("bottom", a(localRect1.bottom, localDisplayMetrics)).put("left", a(localRect1.left, localDisplayMetrics)).put("right", a(localRect1.right, localDisplayMetrics))).put("globalVisibleBox", new JSONObject().put("top", a(localRect2.top, localDisplayMetrics)).put("bottom", a(localRect2.bottom, localDisplayMetrics)).put("left", a(localRect2.left, localDisplayMetrics)).put("right", a(localRect2.right, localDisplayMetrics))).put("globalVisibleBoxVisible", bool1).put("localVisibleBox", new JSONObject().put("top", a(localRect3.top, localDisplayMetrics)).put("bottom", a(localRect3.bottom, localDisplayMetrics)).put("left", a(localRect3.left, localDisplayMetrics)).put("right", a(localRect3.right, localDisplayMetrics))).put("localVisibleBoxVisible", bool2).put("screenDensity", localDisplayMetrics.density).put("isVisible", f(paramView)).put("isStopped", this.mL).put("isPaused", this.mn);
    return (JSONObject)localObject1;
  }
  
  protected void e(boolean paramBoolean)
  {
    long l;
    synchronized (this.mw)
    {
      if ((!this.mF) || (!this.mO)) {
        return;
      }
      l = System.nanoTime();
      if ((paramBoolean) && (this.mN + mK > l)) {
        return;
      }
    }
    this.mN = l;
    fz localfz = (fz)this.mz.get();
    View localView = (View)this.mB.get();
    if (localView != null) {
      if (localfz == null) {
        break label154;
      }
    }
    for (;;)
    {
      int i;
      if (i != 0)
      {
        aF();
        return;
        i = 0;
      }
      else
      {
        try
        {
          a(e(localView));
          aI();
          aG();
          return;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            gs.a("Active view update failed.", localJSONException);
          }
        }
        label154:
        i = 1;
      }
    }
  }
  
  protected boolean f(View paramView)
  {
    return (paramView.getVisibility() == 0) && (paramView.isShown()) && (this.mH.isScreenOn()) && (!this.mI.inKeyguardRestrictedInputMode());
  }
  
  public void onGlobalLayout()
  {
    e(false);
  }
  
  public void onScrollChanged()
  {
    e(true);
  }
  
  public void pause()
  {
    synchronized (this.mw)
    {
      this.mn = true;
      e(false);
      this.mE.pause();
      return;
    }
  }
  
  public void resume()
  {
    synchronized (this.mw)
    {
      this.mE.resume();
      this.mn = false;
      e(false);
      return;
    }
  }
  
  public void stop()
  {
    synchronized (this.mw)
    {
      this.mL = true;
      e(false);
      this.mE.pause();
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/af.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */