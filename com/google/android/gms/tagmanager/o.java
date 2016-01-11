package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ol.a;

class o
  extends BaseImplementation.AbstractPendingResult<ContainerHolder>
{
  private final Looper IH;
  private a aoA;
  private final String aoc;
  private long aoh;
  private final TagManager aoo;
  private final d aor;
  private final cg aos;
  private final int aot;
  private f aou;
  private volatile n aov;
  private volatile boolean aow;
  private c.j aox;
  private String aoy;
  private e aoz;
  private final Context mContext;
  private final ju yD;
  
  o(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, f paramf, e parame, ju paramju, cg paramcg) {}
  
  public o(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, r paramr)
  {
    this(paramContext, paramTagManager, paramLooper, paramString, paramInt, new cq(paramContext, paramString), new cp(paramContext, paramString, paramr), jw.hA(), new bf(30, 900000L, 5000L, "refreshing", jw.hA()));
  }
  
  private void T(final boolean paramBoolean)
  {
    this.aou.a(new b(null));
    this.aoz.a(new c(null));
    cr.c localc = this.aou.ff(this.aot);
    if (localc != null) {
      this.aov = new n(this.aoo, this.IH, new Container(this.mContext, this.aoo.getDataLayer(), this.aoc, 0L, localc), this.aor);
    }
    this.aoA = new a()
    {
      public boolean b(Container paramAnonymousContainer)
      {
        if (paramBoolean) {
          if (paramAnonymousContainer.getLastRefreshTime() + 43200000L < o.a(o.this).currentTimeMillis()) {}
        }
        while (!paramAnonymousContainer.isDefault())
        {
          return true;
          return false;
        }
        return false;
      }
    };
    if (oa())
    {
      this.aoz.e(0L, "");
      return;
    }
    this.aou.oc();
  }
  
  private void a(c.j paramj)
  {
    try
    {
      if (this.aou != null)
      {
        ol.a locala = new ol.a();
        locala.asr = this.aoh;
        locala.gs = new c.f();
        locala.ass = paramj;
        this.aou.b(locala);
      }
      return;
    }
    finally
    {
      paramj = finally;
      throw paramj;
    }
  }
  
  private void a(c.j paramj, long paramLong, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        paramBoolean = this.aow;
        if (paramBoolean) {
          return;
        }
        if ((isReady()) && (this.aov == null)) {}
        this.aox = paramj;
        this.aoh = paramLong;
        w(Math.max(0L, Math.min(43200000L, this.aoh + 43200000L - this.yD.currentTimeMillis())));
        paramj = new Container(this.mContext, this.aoo.getDataLayer(), this.aoc, paramLong, paramj);
        if (this.aov == null)
        {
          this.aov = new n(this.aoo, this.IH, paramj, this.aor);
          if ((!isReady()) && (this.aoA.b(paramj))) {
            b(this.aov);
          }
        }
        else
        {
          this.aov.a(paramj);
        }
      }
      finally {}
    }
  }
  
  private boolean oa()
  {
    ce localce = ce.oJ();
    return ((localce.oK() == ce.a.aqi) || (localce.oK() == ce.a.aqj)) && (this.aoc.equals(localce.getContainerId()));
  }
  
  /* Error */
  private void w(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 83	com/google/android/gms/tagmanager/o:aoz	Lcom/google/android/gms/tagmanager/o$e;
    //   6: ifnonnull +12 -> 18
    //   9: ldc_w 290
    //   12: invokestatic 295	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: aload_0
    //   19: getfield 83	com/google/android/gms/tagmanager/o:aoz	Lcom/google/android/gms/tagmanager/o$e;
    //   22: lload_1
    //   23: aload_0
    //   24: getfield 95	com/google/android/gms/tagmanager/o:aox	Lcom/google/android/gms/internal/c$j;
    //   27: getfield 298	com/google/android/gms/internal/c$j:gt	Ljava/lang/String;
    //   30: invokeinterface 188 4 0
    //   35: goto -20 -> 15
    //   38: astore_3
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_3
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	o
    //   0	43	1	paramLong	long
    //   38	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	15	38	finally
    //   18	35	38	finally
  }
  
  protected ContainerHolder aE(Status paramStatus)
  {
    if (this.aov != null) {
      return this.aov;
    }
    if (paramStatus == Status.Jy) {
      bh.T("timer expired: setting result to failure");
    }
    return new n(paramStatus);
  }
  
  void cr(String paramString)
  {
    try
    {
      this.aoy = paramString;
      if (this.aoz != null) {
        this.aoz.cu(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  String nU()
  {
    try
    {
      String str = this.aoy;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void nX()
  {
    Object localObject = this.aou.ff(this.aot);
    if (localObject != null)
    {
      localObject = new Container(this.mContext, this.aoo.getDataLayer(), this.aoc, 0L, (cr.c)localObject);
      b(new n(this.aoo, this.IH, (Container)localObject, new n.a()
      {
        public void cr(String paramAnonymousString)
        {
          o.this.cr(paramAnonymousString);
        }
        
        public String nU()
        {
          return o.this.nU();
        }
        
        public void nW()
        {
          bh.W("Refresh ignored: container loaded as default only.");
        }
      }));
    }
    for (;;)
    {
      this.aoz = null;
      this.aou = null;
      return;
      bh.T("Default was requested, but no default container was found");
      b(aE(new Status(10, "Default was requested, but no default container was found", null)));
    }
  }
  
  public void nY()
  {
    T(false);
  }
  
  public void nZ()
  {
    T(true);
  }
  
  static abstract interface a
  {
    public abstract boolean b(Container paramContainer);
  }
  
  private class b
    implements bg<ol.a>
  {
    private b() {}
    
    public void a(ol.a parama)
    {
      c.j localj;
      if (parama.ass != null) {
        localj = parama.ass;
      }
      for (;;)
      {
        o.a(o.this, localj, parama.asr, true);
        return;
        c.f localf = parama.gs;
        localj = new c.j();
        localj.gs = localf;
        localj.gr = null;
        localj.gt = localf.version;
      }
    }
    
    public void a(bg.a parama)
    {
      if (!o.b(o.this)) {
        o.a(o.this, 0L);
      }
    }
    
    public void ob() {}
  }
  
  private class c
    implements bg<c.j>
  {
    private c() {}
    
    public void a(bg.a parama)
    {
      if (o.f(o.this) != null) {
        o.this.b(o.f(o.this));
      }
      for (;;)
      {
        o.a(o.this, 3600000L);
        return;
        o.this.b(o.this.aE(Status.Jy));
      }
    }
    
    public void b(c.j paramj)
    {
      synchronized (o.this)
      {
        if (paramj.gs == null)
        {
          if (o.c(o.this).gs == null)
          {
            bh.T("Current resource is null; network resource is also null");
            o.a(o.this, 3600000L);
            return;
          }
          paramj.gs = o.c(o.this).gs;
        }
        o.a(o.this, paramj, o.a(o.this).currentTimeMillis(), false);
        bh.V("setting refresh time to current time: " + o.d(o.this));
        if (!o.e(o.this)) {
          o.a(o.this, paramj);
        }
        return;
      }
    }
    
    public void ob() {}
  }
  
  private class d
    implements n.a
  {
    private d() {}
    
    public void cr(String paramString)
    {
      o.this.cr(paramString);
    }
    
    public String nU()
    {
      return o.this.nU();
    }
    
    public void nW()
    {
      if (o.g(o.this).eJ()) {
        o.a(o.this, 0L);
      }
    }
  }
  
  static abstract interface e
    extends Releasable
  {
    public abstract void a(bg<c.j> parambg);
    
    public abstract void cu(String paramString);
    
    public abstract void e(long paramLong, String paramString);
  }
  
  static abstract interface f
    extends Releasable
  {
    public abstract void a(bg<ol.a> parambg);
    
    public abstract void b(ol.a parama);
    
    public abstract cr.c ff(int paramInt);
    
    public abstract void oc();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */