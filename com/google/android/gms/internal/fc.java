package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import android.webkit.WebView;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;

@ez
public class fc
  implements Runnable
{
  private final int lf;
  private final int lg;
  protected final gv md;
  private final Handler td;
  private final long te;
  private long tf;
  private gw.a tg;
  protected boolean th;
  protected boolean ti;
  
  public fc(gw.a parama, gv paramgv, int paramInt1, int paramInt2)
  {
    this(parama, paramgv, paramInt1, paramInt2, 200L, 50L);
  }
  
  public fc(gw.a parama, gv paramgv, int paramInt1, int paramInt2, long paramLong1, long paramLong2)
  {
    this.te = paramLong1;
    this.tf = paramLong2;
    this.td = new Handler(Looper.getMainLooper());
    this.md = paramgv;
    this.tg = parama;
    this.th = false;
    this.ti = false;
    this.lg = paramInt2;
    this.lf = paramInt1;
  }
  
  public void a(fk paramfk, ha paramha)
  {
    this.md.setWebViewClient(paramha);
    gv localgv = this.md;
    if (TextUtils.isEmpty(paramfk.rP)) {}
    for (paramha = null;; paramha = gj.L(paramfk.rP))
    {
      localgv.loadDataWithBaseURL(paramha, paramfk.tG, "text/html", "UTF-8", null);
      return;
    }
  }
  
  public void b(fk paramfk)
  {
    a(paramfk, new ha(this, this.md, paramfk.tP));
  }
  
  public boolean cA()
  {
    try
    {
      boolean bool = this.th;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean cB()
  {
    return this.ti;
  }
  
  public void cy()
  {
    this.td.postDelayed(this, this.te);
  }
  
  public void cz()
  {
    try
    {
      this.th = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void run()
  {
    if ((this.md == null) || (cA()))
    {
      this.tg.a(this.md);
      return;
    }
    a locala = new a(this.md);
    Void[] arrayOfVoid = new Void[0];
    if (!(locala instanceof AsyncTask))
    {
      locala.execute(arrayOfVoid);
      return;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)locala, arrayOfVoid);
  }
  
  protected final class a
    extends AsyncTask<Void, Void, Boolean>
  {
    public Trace _nr_trace;
    private final WebView tj;
    private Bitmap tk;
    
    public a(WebView paramWebView)
    {
      this.tj = paramWebView;
    }
    
    public void _nr_setTrace(Trace paramTrace)
    {
      try
      {
        this._nr_trace = paramTrace;
        return;
      }
      catch (Exception paramTrace) {}
    }
    
    protected Boolean a(Void... paramVarArgs)
    {
      for (;;)
      {
        int i;
        int m;
        try
        {
          int n = this.tk.getWidth();
          int i1 = this.tk.getHeight();
          if ((n == 0) || (i1 == 0))
          {
            paramVarArgs = Boolean.valueOf(false);
            return paramVarArgs;
          }
          i = 0;
          j = 0;
          int k;
          if (i < n)
          {
            k = 0;
            if (k >= i1) {
              break label139;
            }
            m = j;
            if (this.tk.getPixel(i, k) != 0) {
              m = j + 1;
            }
          }
          else
          {
            if (j / (n * i1 / 100.0D) > 0.1D)
            {
              bool = true;
              paramVarArgs = Boolean.valueOf(bool);
              continue;
            }
            boolean bool = false;
            continue;
          }
          k += 10;
        }
        finally {}
        int j = m;
        continue;
        label139:
        i += 10;
      }
    }
    
    protected void a(Boolean paramBoolean)
    {
      fc.c(fc.this);
      if ((paramBoolean.booleanValue()) || (fc.this.cA()) || (fc.d(fc.this) <= 0L))
      {
        fc.this.ti = paramBoolean.booleanValue();
        fc.e(fc.this).a(fc.this.md);
      }
      while (fc.d(fc.this) <= 0L) {
        return;
      }
      if (gs.u(2)) {
        gs.S("Ad not detected, scheduling another run.");
      }
      fc.g(fc.this).postDelayed(fc.this, fc.f(fc.this));
    }
    
    protected void onPreExecute()
    {
      try
      {
        this.tk = Bitmap.createBitmap(fc.a(fc.this), fc.b(fc.this), Bitmap.Config.ARGB_8888);
        this.tj.setVisibility(0);
        this.tj.measure(View.MeasureSpec.makeMeasureSpec(fc.a(fc.this), 0), View.MeasureSpec.makeMeasureSpec(fc.b(fc.this), 0));
        this.tj.layout(0, 0, fc.a(fc.this), fc.b(fc.this));
        Canvas localCanvas = new Canvas(this.tk);
        this.tj.draw(localCanvas);
        this.tj.invalidate();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */