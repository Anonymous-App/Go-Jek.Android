package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ez
public class gk<T>
  implements Future<T>
{
  private final Object mw = new Object();
  private boolean pS = false;
  private T wq = null;
  private boolean wr = false;
  
  public void a(T paramT)
  {
    synchronized (this.mw)
    {
      if (this.wr) {
        throw new IllegalStateException("Provided CallbackFuture with multiple values.");
      }
    }
    this.wr = true;
    this.wq = paramT;
    this.mw.notifyAll();
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return false;
    }
    synchronized (this.mw)
    {
      if (this.wr) {
        return false;
      }
    }
    this.pS = true;
    this.wr = true;
    this.mw.notifyAll();
    return true;
  }
  
  public T get()
  {
    synchronized (this.mw)
    {
      boolean bool = this.wr;
      if (bool) {}
    }
    try
    {
      this.mw.wait();
      if (this.pS)
      {
        throw new CancellationException("CallbackFuture was cancelled.");
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      Object localObject3 = this.wq;
      return (T)localObject3;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws TimeoutException
  {
    synchronized (this.mw)
    {
      boolean bool = this.wr;
      if (!bool) {}
      try
      {
        paramLong = paramTimeUnit.toMillis(paramLong);
        if (paramLong != 0L) {
          this.mw.wait(paramLong);
        }
      }
      catch (InterruptedException paramTimeUnit)
      {
        for (;;) {}
      }
      if (!this.wr) {
        throw new TimeoutException("CallbackFuture timed out.");
      }
    }
    if (this.pS) {
      throw new CancellationException("CallbackFuture was cancelled.");
    }
    paramTimeUnit = this.wq;
    return paramTimeUnit;
  }
  
  public boolean isCancelled()
  {
    synchronized (this.mw)
    {
      boolean bool = this.pS;
      return bool;
    }
  }
  
  public boolean isDone()
  {
    synchronized (this.mw)
    {
      boolean bool = this.wr;
      return bool;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */