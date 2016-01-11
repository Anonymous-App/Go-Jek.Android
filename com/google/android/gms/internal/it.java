package com.google.android.gms.internal;

import android.os.SystemClock;
import org.json.JSONObject;

public final class it
{
  private static final ip Gr = new ip("RequestTracker");
  public static final Object Hz = new Object();
  private long Hv;
  private long Hw;
  private long Hx;
  private is Hy;
  
  public it(long paramLong)
  {
    this.Hv = paramLong;
    this.Hw = -1L;
    this.Hx = 0L;
  }
  
  private void fU()
  {
    this.Hw = -1L;
    this.Hy = null;
    this.Hx = 0L;
  }
  
  public void a(long paramLong, is paramis)
  {
    synchronized (Hz)
    {
      is localis = this.Hy;
      long l = this.Hw;
      this.Hw = paramLong;
      this.Hy = paramis;
      this.Hx = SystemClock.elapsedRealtime();
      if (localis != null) {
        localis.n(l);
      }
      return;
    }
  }
  
  public boolean b(long paramLong, int paramInt, JSONObject paramJSONObject)
  {
    boolean bool = true;
    is localis = null;
    for (;;)
    {
      synchronized (Hz)
      {
        if ((this.Hw != -1L) && (this.Hw == paramLong))
        {
          Gr.b("request %d completed", new Object[] { Long.valueOf(this.Hw) });
          localis = this.Hy;
          fU();
          if (localis != null) {
            localis.a(paramLong, paramInt, paramJSONObject);
          }
          return bool;
        }
      }
      bool = false;
    }
  }
  
  public void clear()
  {
    synchronized (Hz)
    {
      if (this.Hw != -1L) {
        fU();
      }
      return;
    }
  }
  
  public boolean d(long paramLong, int paramInt)
  {
    return b(paramLong, paramInt, null);
  }
  
  public boolean e(long paramLong, int paramInt)
  {
    boolean bool = true;
    long l = 0L;
    for (;;)
    {
      synchronized (Hz)
      {
        if ((this.Hw != -1L) && (paramLong - this.Hx >= this.Hv))
        {
          Gr.b("request %d timed out", new Object[] { Long.valueOf(this.Hw) });
          paramLong = this.Hw;
          is localis = this.Hy;
          fU();
          if (localis != null) {
            localis.a(paramLong, paramInt, null);
          }
          return bool;
        }
      }
      bool = false;
      Object localObject2 = null;
      paramLong = l;
    }
  }
  
  public boolean fV()
  {
    for (;;)
    {
      synchronized (Hz)
      {
        if (this.Hw != -1L)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public boolean p(long paramLong)
  {
    for (;;)
    {
      synchronized (Hz)
      {
        if ((this.Hw != -1L) && (this.Hw == paramLong))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/it.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */