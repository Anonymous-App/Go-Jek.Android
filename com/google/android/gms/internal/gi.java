package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@ez
public final class gi
{
  private static final ThreadFactory wh = new ThreadFactory()
  {
    private final AtomicInteger wl = new AtomicInteger(1);
    
    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      return new Thread(paramAnonymousRunnable, "AdWorker #" + this.wl.getAndIncrement());
    }
  };
  private static final ExecutorService wi = Executors.newFixedThreadPool(10, wh);
  
  public static Future<Void> a(Runnable paramRunnable)
  {
    submit(new Callable()
    {
      public Void dj()
      {
        this.wj.run();
        return null;
      }
    });
  }
  
  public static <T> Future<T> submit(Callable<T> paramCallable)
  {
    try
    {
      paramCallable = wi.submit(new Callable()
      {
        public T call()
          throws Exception
        {
          try
          {
            Process.setThreadPriority(10);
            Object localObject = this.wk.call();
            return (T)localObject;
          }
          catch (Exception localException)
          {
            gb.e(localException);
          }
          return null;
        }
      });
      return paramCallable;
    }
    catch (RejectedExecutionException paramCallable)
    {
      gs.d("Thread execution is rejected.", paramCallable);
    }
    return new gl(null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */