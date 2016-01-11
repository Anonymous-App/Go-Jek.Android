package com.google.android.gms.games.internal.events;

import java.util.concurrent.atomic.AtomicReference;

public abstract class EventIncrementManager
{
  private final AtomicReference<EventIncrementCache> aaw = new AtomicReference();
  
  public void flush()
  {
    EventIncrementCache localEventIncrementCache = (EventIncrementCache)this.aaw.get();
    if (localEventIncrementCache != null) {
      localEventIncrementCache.flush();
    }
  }
  
  protected abstract EventIncrementCache ky();
  
  public void n(String paramString, int paramInt)
  {
    EventIncrementCache localEventIncrementCache2 = (EventIncrementCache)this.aaw.get();
    EventIncrementCache localEventIncrementCache1 = localEventIncrementCache2;
    if (localEventIncrementCache2 == null)
    {
      localEventIncrementCache2 = ky();
      localEventIncrementCache1 = localEventIncrementCache2;
      if (!this.aaw.compareAndSet(null, localEventIncrementCache2)) {
        localEventIncrementCache1 = (EventIncrementCache)this.aaw.get();
      }
    }
    localEventIncrementCache1.w(paramString, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/events/EventIncrementManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */