package com.google.android.gms.internal;

import android.os.Handler;
import java.lang.ref.WeakReference;

@ez
public class ab
{
  private final a mj;
  private final Runnable mk;
  private av ml;
  private boolean mm = false;
  private boolean mn = false;
  private long mo = 0L;
  
  public ab(u paramu)
  {
    this(paramu, new a(gr.wC));
  }
  
  ab(final u paramu, a parama)
  {
    this.mj = parama;
    this.mk = new Runnable()
    {
      private final WeakReference<u> mp = new WeakReference(paramu);
      
      public void run()
      {
        ab.a(ab.this, false);
        u localu = (u)this.mp.get();
        if (localu != null) {
          localu.b(ab.a(ab.this));
        }
      }
    };
  }
  
  public void a(av paramav, long paramLong)
  {
    if (this.mm) {
      gs.W("An ad refresh is already scheduled.");
    }
    do
    {
      return;
      this.ml = paramav;
      this.mm = true;
      this.mo = paramLong;
    } while (this.mn);
    gs.U("Scheduling ad refresh " + paramLong + " milliseconds from now.");
    this.mj.postDelayed(this.mk, paramLong);
  }
  
  public boolean ay()
  {
    return this.mm;
  }
  
  public void c(av paramav)
  {
    a(paramav, 60000L);
  }
  
  public void cancel()
  {
    this.mm = false;
    this.mj.removeCallbacks(this.mk);
  }
  
  public void pause()
  {
    this.mn = true;
    if (this.mm) {
      this.mj.removeCallbacks(this.mk);
    }
  }
  
  public void resume()
  {
    this.mn = false;
    if (this.mm)
    {
      this.mm = false;
      a(this.ml, this.mo);
    }
  }
  
  public static class a
  {
    private final Handler mHandler;
    
    public a(Handler paramHandler)
    {
      this.mHandler = paramHandler;
    }
    
    public boolean postDelayed(Runnable paramRunnable, long paramLong)
    {
      return this.mHandler.postDelayed(paramRunnable, paramLong);
    }
    
    public void removeCallbacks(Runnable paramRunnable)
    {
      this.mHandler.removeCallbacks(paramRunnable);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */