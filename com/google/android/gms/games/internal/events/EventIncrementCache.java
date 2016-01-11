package com.google.android.gms.games.internal.events;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EventIncrementCache
{
  final Object aaq = new Object();
  private Handler aar;
  private boolean aas;
  private HashMap<String, AtomicInteger> aat;
  private int aau;
  
  public EventIncrementCache(Looper paramLooper, int paramInt)
  {
    this.aar = new Handler(paramLooper);
    this.aat = new HashMap();
    this.aau = paramInt;
  }
  
  private void kQ()
  {
    synchronized (this.aaq)
    {
      this.aas = false;
      flush();
      return;
    }
  }
  
  public void flush()
  {
    synchronized (this.aaq)
    {
      Iterator localIterator = this.aat.entrySet().iterator();
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        q((String)localEntry.getKey(), ((AtomicInteger)localEntry.getValue()).get());
      }
    }
    this.aat.clear();
  }
  
  protected abstract void q(String paramString, int paramInt);
  
  public void w(String paramString, int paramInt)
  {
    synchronized (this.aaq)
    {
      if (!this.aas)
      {
        this.aas = true;
        this.aar.postDelayed(new Runnable()
        {
          public void run()
          {
            EventIncrementCache.a(EventIncrementCache.this);
          }
        }, this.aau);
      }
      AtomicInteger localAtomicInteger2 = (AtomicInteger)this.aat.get(paramString);
      AtomicInteger localAtomicInteger1 = localAtomicInteger2;
      if (localAtomicInteger2 == null)
      {
        localAtomicInteger1 = new AtomicInteger();
        this.aat.put(paramString, localAtomicInteger1);
      }
      localAtomicInteger1.addAndGet(paramInt);
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/events/EventIncrementCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */