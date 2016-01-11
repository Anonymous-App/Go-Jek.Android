package com.google.android.gms.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class an
  extends Thread
{
  private boolean mStarted = false;
  private final Object mw;
  private final int nf;
  private final int nh;
  private boolean ns = false;
  private boolean nt = false;
  private final am nu;
  private final al nv;
  private final ey nw;
  private final int nx;
  private final int ny;
  private final int nz;
  
  public an(am paramam, al paramal, Bundle paramBundle, ey paramey)
  {
    this.nu = paramam;
    this.nv = paramal;
    this.nw = paramey;
    this.mw = new Object();
    this.nf = paramBundle.getInt(bn.pe.getKey());
    this.ny = paramBundle.getInt(bn.pf.getKey());
    this.nh = paramBundle.getInt(bn.pg.getKey());
    this.nz = paramBundle.getInt(bn.ph.getKey());
    this.nx = paramBundle.getInt(bn.pi.getKey(), 10);
    setName("ContentFetchTask");
  }
  
  private void a(Activity paramActivity)
  {
    if (paramActivity == null) {}
    Object localObject1;
    do
    {
      return;
      Object localObject2 = null;
      localObject1 = localObject2;
      if (paramActivity.getWindow() != null)
      {
        localObject1 = localObject2;
        if (paramActivity.getWindow().getDecorView() != null) {
          localObject1 = paramActivity.getWindow().getDecorView().findViewById(16908290);
        }
      }
    } while (localObject1 == null);
    g((View)localObject1);
  }
  
  private boolean a(final WebView paramWebView, final ak paramak)
  {
    if (!kc.hI()) {
      return false;
    }
    paramak.aR();
    paramWebView.post(new Runnable()
    {
      ValueCallback<String> nC = new ValueCallback()
      {
        public void k(String paramAnonymous2String)
        {
          an.this.a(an.2.this.nD, an.2.this.nE, paramAnonymous2String);
        }
      };
      
      public void run()
      {
        if (paramWebView.getSettings().getJavaScriptEnabled()) {
          paramWebView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.nC);
        }
      }
    });
    return true;
  }
  
  private boolean aW()
  {
    try
    {
      Object localObject1 = this.nu.getContext();
      if (localObject1 == null) {
        return false;
      }
      Object localObject2 = (ActivityManager)((Context)localObject1).getSystemService("activity");
      KeyguardManager localKeyguardManager = (KeyguardManager)((Context)localObject1).getSystemService("keyguard");
      localObject1 = (PowerManager)((Context)localObject1).getSystemService("power");
      if ((localObject2 != null) && (localKeyguardManager != null) && (localObject1 != null))
      {
        localObject2 = ((ActivityManager)localObject2).getRunningAppProcesses();
        if (localObject2 == null) {
          return false;
        }
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
          if (Process.myPid() == localRunningAppProcessInfo.pid) {
            if ((localRunningAppProcessInfo.importance == 100) && (!localKeyguardManager.inKeyguardRestrictedInputMode()))
            {
              boolean bool = ((PowerManager)localObject1).isScreenOn();
              if (bool) {
                return true;
              }
            }
          }
        }
        return false;
      }
    }
    catch (Throwable localThrowable)
    {
      return false;
    }
    return false;
  }
  
  a a(View paramView, ak paramak)
  {
    int i = 0;
    if (paramView == null) {
      return new a(0, 0);
    }
    if (((paramView instanceof TextView)) && (!(paramView instanceof EditText)))
    {
      paramak.i(((TextView)paramView).getText().toString());
      return new a(1, 0);
    }
    if (((paramView instanceof WebView)) && (!(paramView instanceof gv)))
    {
      paramak.aR();
      if (a((WebView)paramView, paramak)) {
        return new a(0, 1);
      }
      return new a(0, 0);
    }
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int j = 0;
      int k = 0;
      while (i < paramView.getChildCount())
      {
        a locala = a(paramView.getChildAt(i), paramak);
        k += locala.nG;
        j += locala.nH;
        i += 1;
      }
      return new a(k, j);
    }
    return new a(0, 0);
  }
  
  void a(ak paramak, WebView paramWebView, String paramString)
  {
    paramak.aQ();
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = JSONObjectInstrumentation.init(paramString).optString("text");
        if (TextUtils.isEmpty(paramWebView.getTitle())) {
          break label80;
        }
        paramak.h(paramWebView.getTitle() + "\n" + paramString);
      }
      while (paramak.aN())
      {
        this.nv.b(paramak);
        return;
        label80:
        paramak.h(paramString);
      }
      return;
    }
    catch (JSONException paramak)
    {
      gs.S("Json string may be malformed.");
      return;
    }
    catch (Throwable paramak)
    {
      gs.a("Failed to get webview content.", paramak);
      this.nw.b(paramak);
    }
  }
  
  public void aV()
  {
    synchronized (this.mw)
    {
      if (this.mStarted)
      {
        gs.S("Content hash thread already started, quiting...");
        return;
      }
      this.mStarted = true;
      start();
      return;
    }
  }
  
  public ak aX()
  {
    return this.nv.aU();
  }
  
  public void aY()
  {
    synchronized (this.mw)
    {
      this.ns = true;
      gs.S("ContentFetchThread: paused, mPause = " + this.ns);
      return;
    }
  }
  
  public boolean aZ()
  {
    return this.ns;
  }
  
  boolean g(final View paramView)
  {
    if (paramView == null) {
      return false;
    }
    paramView.post(new Runnable()
    {
      public void run()
      {
        an.this.h(paramView);
      }
    });
    return true;
  }
  
  void h(View paramView)
  {
    try
    {
      ak localak = new ak(this.nf, this.ny, this.nh, this.nz);
      paramView = a(paramView, localak);
      localak.aS();
      if ((paramView.nG == 0) && (paramView.nH == 0)) {
        return;
      }
      if (((paramView.nH != 0) || (localak.aT() != 0)) && ((paramView.nH != 0) || (!this.nv.a(localak))))
      {
        this.nv.c(localak);
        return;
      }
    }
    catch (Exception paramView)
    {
      gs.b("Exception in fetchContentOnUIThread", paramView);
      this.nw.b(paramView);
    }
  }
  
  public void run()
  {
    while (!this.nt) {
      try
      {
        if (aW())
        {
          Activity localActivity = this.nu.getActivity();
          if (localActivity == null) {
            gs.S("ContentFetchThread: no activity");
          }
        }
      }
      catch (Throwable localThrowable)
      {
        gs.b("Error in ContentFetchTask", localThrowable);
        this.nw.b(localThrowable);
        synchronized (this.mw)
        {
          for (;;)
          {
            boolean bool = this.ns;
            if (!bool) {
              break;
            }
            try
            {
              gs.S("ContentFetchTask: waiting");
              this.mw.wait();
            }
            catch (InterruptedException localInterruptedException) {}
          }
          a((Activity)???);
          for (;;)
          {
            Thread.sleep(this.nx * 1000);
            break;
            gs.S("ContentFetchTask: sleeping");
            aY();
          }
        }
      }
    }
  }
  
  public void wakeup()
  {
    synchronized (this.mw)
    {
      this.ns = false;
      this.mw.notifyAll();
      gs.S("ContentFetchThread: wakeup");
      return;
    }
  }
  
  @ez
  class a
  {
    final int nG;
    final int nH;
    
    a(int paramInt1, int paramInt2)
    {
      this.nG = paramInt1;
      this.nH = paramInt2;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/an.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */