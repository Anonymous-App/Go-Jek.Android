package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.f;
import com.google.android.gms.common.internal.f.b;
import com.google.android.gms.common.internal.o;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class b
  implements GoogleApiClient
{
  private final Looper IH;
  final int IU;
  private final Lock IV = new ReentrantLock();
  private final Condition IW = this.IV.newCondition();
  private final f IX = new f(paramContext, paramLooper, this.Jp);
  private final int IY;
  final Queue<c<?>> IZ = new LinkedList();
  private final a Iz = new a()
  {
    public void b(b.c<?> paramAnonymousc)
    {
      b.this.Jn.remove(paramAnonymousc);
    }
  };
  private ConnectionResult Ja;
  private int Jb;
  private volatile int Jc = 4;
  private volatile int Jd;
  private boolean Je = false;
  private int Jf;
  private long Jg = 5000L;
  final Handler Jh;
  private final Bundle Ji = new Bundle();
  private final Map<Api.c<?>, Api.a> Jj = new HashMap();
  private final List<String> Jk;
  private boolean Jl;
  private final Set<c<?>> Jm = Collections.newSetFromMap(new WeakHashMap());
  final Set<c<?>> Jn = Collections.newSetFromMap(new ConcurrentHashMap());
  private final GoogleApiClient.ConnectionCallbacks Jo = new GoogleApiClient.ConnectionCallbacks()
  {
    public void onConnected(Bundle paramAnonymousBundle)
    {
      b.a(b.this).lock();
      try
      {
        if (b.b(b.this) == 1)
        {
          if (paramAnonymousBundle != null) {
            b.c(b.this).putAll(paramAnonymousBundle);
          }
          b.d(b.this);
        }
        return;
      }
      finally
      {
        b.a(b.this).unlock();
      }
    }
    
    public void onConnectionSuspended(int paramAnonymousInt)
    {
      b.a(b.this).lock();
      for (;;)
      {
        try
        {
          b.a(b.this, paramAnonymousInt);
          switch (paramAnonymousInt)
          {
          default: 
            return;
          }
        }
        finally
        {
          b.a(b.this).unlock();
        }
        b.this.connect();
        continue;
        boolean bool = b.e(b.this);
        if (bool)
        {
          b.a(b.this).unlock();
          return;
        }
        b.b(b.this, b.this.IU);
        b.this.Jh.sendMessageDelayed(b.this.Jh.obtainMessage(1), b.f(b.this));
      }
    }
  };
  private final f.b Jp = new f.b()
  {
    public Bundle fC()
    {
      return null;
    }
    
    public boolean gq()
    {
      return b.g(b.this);
    }
    
    public boolean isConnected()
    {
      return b.this.isConnected();
    }
  };
  
  public b(Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, Map<Api<?>, Api.ApiOptions> paramMap, Set<GoogleApiClient.ConnectionCallbacks> paramSet, Set<GoogleApiClient.OnConnectionFailedListener> paramSet1, int paramInt1, int paramInt2)
  {
    this.IH = paramLooper;
    this.Jh = new b(paramLooper);
    this.IY = paramInt1;
    this.IU = paramInt2;
    paramSet = paramSet.iterator();
    final Object localObject1;
    while (paramSet.hasNext())
    {
      localObject1 = (GoogleApiClient.ConnectionCallbacks)paramSet.next();
      this.IX.registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)localObject1);
    }
    paramSet = paramSet1.iterator();
    while (paramSet.hasNext())
    {
      paramSet1 = (GoogleApiClient.OnConnectionFailedListener)paramSet.next();
      this.IX.registerConnectionFailedListener(paramSet1);
    }
    paramSet = paramMap.keySet().iterator();
    while (paramSet.hasNext())
    {
      paramSet1 = (Api)paramSet.next();
      localObject1 = paramSet1.gb();
      Object localObject2 = paramMap.get(paramSet1);
      this.Jj.put(paramSet1.ge(), a((Api.b)localObject1, localObject2, paramContext, paramLooper, paramClientSettings, this.Jo, new GoogleApiClient.OnConnectionFailedListener()
      {
        public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
        {
          b.a(b.this).lock();
          try
          {
            if ((b.i(b.this) == null) || (localObject1.getPriority() < b.j(b.this)))
            {
              b.a(b.this, paramAnonymousConnectionResult);
              b.c(b.this, localObject1.getPriority());
            }
            b.d(b.this);
            return;
          }
          finally
          {
            b.a(b.this).unlock();
          }
        }
      }));
    }
    this.Jk = Collections.unmodifiableList(paramClientSettings.getScopes());
  }
  
  private static <C extends Api.a, O> C a(Api.b<C, O> paramb, Object paramObject, Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return paramb.a(paramContext, paramLooper, paramClientSettings, paramObject, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  private <A extends Api.a> void a(c<A> paramc)
    throws DeadObjectException
  {
    this.IV.lock();
    try
    {
      if (paramc.ge() != null) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "This task can not be executed or enqueued (it's probably a Batch or malformed)");
        this.Jn.add(paramc);
        paramc.a(this.Iz);
        if (!go()) {
          break;
        }
        paramc.m(new Status(8));
        return;
      }
      paramc.b(a(paramc.ge()));
      return;
    }
    finally
    {
      this.IV.unlock();
    }
  }
  
  private void aj(int paramInt)
  {
    this.IV.lock();
    Object localObject2;
    try
    {
      if (this.Jc == 3) {
        break label374;
      }
      if (paramInt != -1) {
        break label241;
      }
      if (isConnecting())
      {
        Iterator localIterator1 = this.IZ.iterator();
        while (localIterator1.hasNext())
        {
          localObject2 = (c)localIterator1.next();
          if (((c)localObject2).gj() != 1)
          {
            ((c)localObject2).cancel();
            localIterator1.remove();
          }
        }
      }
      this.IZ.clear();
    }
    finally
    {
      this.IV.unlock();
    }
    Iterator localIterator2 = this.Jn.iterator();
    while (localIterator2.hasNext()) {
      ((c)localIterator2.next()).cancel();
    }
    this.Jn.clear();
    localIterator2 = this.Jm.iterator();
    while (localIterator2.hasNext()) {
      ((c)localIterator2.next()).clear();
    }
    this.Jm.clear();
    if ((this.Ja == null) && (!this.IZ.isEmpty()))
    {
      this.Je = true;
      this.IV.unlock();
      return;
    }
    label241:
    boolean bool1 = isConnecting();
    boolean bool2 = isConnected();
    this.Jc = 3;
    if (bool1)
    {
      if (paramInt == -1) {
        this.Ja = null;
      }
      this.IW.signalAll();
    }
    this.Jl = false;
    localIterator2 = this.Jj.values().iterator();
    while (localIterator2.hasNext())
    {
      localObject2 = (Api.a)localIterator2.next();
      if (((Api.a)localObject2).isConnected()) {
        ((Api.a)localObject2).disconnect();
      }
    }
    this.Jl = true;
    this.Jc = 4;
    if (bool2)
    {
      if (paramInt != -1) {
        this.IX.aB(paramInt);
      }
      this.Jl = false;
    }
    label374:
    this.IV.unlock();
  }
  
  private void gm()
  {
    this.Jf -= 1;
    if (this.Jf == 0)
    {
      if (this.Ja == null) {
        break label81;
      }
      this.Je = false;
      aj(3);
      if (!go()) {
        break label67;
      }
      this.Jh.sendMessageDelayed(this.Jh.obtainMessage(1), this.Jg);
    }
    for (;;)
    {
      this.Jl = false;
      return;
      label67:
      this.IX.b(this.Ja);
    }
    label81:
    this.Jc = 2;
    gp();
    this.IW.signalAll();
    gn();
    if (this.Je)
    {
      this.Je = false;
      aj(-1);
      return;
    }
    if (this.Ji.isEmpty()) {}
    for (Bundle localBundle = null;; localBundle = this.Ji)
    {
      this.IX.d(localBundle);
      return;
    }
  }
  
  private void gn()
  {
    this.IV.lock();
    for (;;)
    {
      try
      {
        if (isConnected()) {
          break label108;
        }
        if (!go()) {
          break label93;
        }
      }
      finally
      {
        this.IV.unlock();
      }
      o.a(bool, "GoogleApiClient is not connected yet.");
      boolean bool = this.IZ.isEmpty();
      if (!bool)
      {
        try
        {
          a((c)this.IZ.remove());
        }
        catch (DeadObjectException localDeadObjectException)
        {
          Log.w("GoogleApiClientImpl", "Service died while flushing queue", localDeadObjectException);
        }
        continue;
        label93:
        bool = false;
      }
      else
      {
        this.IV.unlock();
        return;
        label108:
        bool = true;
      }
    }
  }
  
  private boolean go()
  {
    return this.Jd != 0;
  }
  
  private void gp()
  {
    this.IV.lock();
    try
    {
      this.Jd = 0;
      this.Jh.removeMessages(1);
      return;
    }
    finally
    {
      this.IV.unlock();
    }
  }
  
  public <C extends Api.a> C a(Api.c<C> paramc)
  {
    paramc = (Api.a)this.Jj.get(paramc);
    o.b(paramc, "Appropriate Api was not requested.");
    return paramc;
  }
  
  /* Error */
  public <A extends Api.a, R extends Result, T extends BaseImplementation.a<R, A>> T a(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 244 1 0
    //   9: aload_1
    //   10: new 412	com/google/android/gms/common/api/BaseImplementation$CallbackHandler
    //   13: dup
    //   14: aload_0
    //   15: invokevirtual 416	com/google/android/gms/common/api/b:getLooper	()Landroid/os/Looper;
    //   18: invokespecial 419	com/google/android/gms/common/api/BaseImplementation$CallbackHandler:<init>	(Landroid/os/Looper;)V
    //   21: invokevirtual 424	com/google/android/gms/common/api/BaseImplementation$a:a	(Lcom/google/android/gms/common/api/BaseImplementation$CallbackHandler;)V
    //   24: aload_0
    //   25: invokevirtual 315	com/google/android/gms/common/api/b:isConnected	()Z
    //   28: ifeq +20 -> 48
    //   31: aload_0
    //   32: aload_1
    //   33: invokevirtual 426	com/google/android/gms/common/api/b:b	(Lcom/google/android/gms/common/api/BaseImplementation$a;)Lcom/google/android/gms/common/api/BaseImplementation$a;
    //   36: pop
    //   37: aload_0
    //   38: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   41: invokeinterface 274 1 0
    //   46: aload_1
    //   47: areturn
    //   48: aload_0
    //   49: getfield 92	com/google/android/gms/common/api/b:IZ	Ljava/util/Queue;
    //   52: aload_1
    //   53: invokeinterface 427 2 0
    //   58: pop
    //   59: goto -22 -> 37
    //   62: astore_1
    //   63: aload_0
    //   64: getfield 79	com/google/android/gms/common/api/b:IV	Ljava/util/concurrent/locks/Lock;
    //   67: invokeinterface 274 1 0
    //   72: aload_1
    //   73: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	b
    //   0	74	1	paramT	T
    // Exception table:
    //   from	to	target	type
    //   9	37	62	finally
    //   48	59	62	finally
  }
  
  public boolean a(Scope paramScope)
  {
    return this.Jk.contains(paramScope.gs());
  }
  
  public <A extends Api.a, T extends BaseImplementation.a<? extends Result, A>> T b(T paramT)
  {
    if ((isConnected()) || (go())) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "GoogleApiClient is not connected yet.");
      gn();
      try
      {
        a(paramT);
        return paramT;
      }
      catch (DeadObjectException localDeadObjectException)
      {
        aj(1);
      }
    }
    return paramT;
  }
  
  public ConnectionResult blockingConnect()
  {
    boolean bool;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool = true;
    }
    for (;;)
    {
      o.a(bool, "blockingConnect must not be called on the UI thread");
      this.IV.lock();
      try
      {
        connect();
        for (;;)
        {
          bool = isConnecting();
          if (!bool) {
            break label86;
          }
          try
          {
            this.IW.await();
          }
          catch (InterruptedException localInterruptedException)
          {
            Thread.currentThread().interrupt();
            localConnectionResult = new ConnectionResult(15, null);
            return localConnectionResult;
          }
        }
        bool = false;
        continue;
        label86:
        if (isConnected())
        {
          localConnectionResult = ConnectionResult.HE;
          return localConnectionResult;
        }
        if (this.Ja != null)
        {
          localConnectionResult = this.Ja;
          return localConnectionResult;
        }
        ConnectionResult localConnectionResult = new ConnectionResult(13, null);
        return localConnectionResult;
      }
      finally
      {
        this.IV.unlock();
      }
    }
  }
  
  public ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit)
  {
    boolean bool;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool = true;
    }
    for (;;)
    {
      o.a(bool, "blockingConnect must not be called on the UI thread");
      this.IV.lock();
      try
      {
        connect();
        paramLong = paramTimeUnit.toNanos(paramLong);
        for (;;)
        {
          bool = isConnecting();
          if (!bool) {
            break;
          }
          try
          {
            long l = this.IW.awaitNanos(paramLong);
            paramLong = l;
            if (l <= 0L)
            {
              paramTimeUnit = new ConnectionResult(14, null);
              return paramTimeUnit;
            }
          }
          catch (InterruptedException paramTimeUnit)
          {
            Thread.currentThread().interrupt();
            paramTimeUnit = new ConnectionResult(15, null);
            return paramTimeUnit;
          }
        }
        bool = false;
        continue;
        if (isConnected())
        {
          paramTimeUnit = ConnectionResult.HE;
          return paramTimeUnit;
        }
        if (this.Ja != null)
        {
          paramTimeUnit = this.Ja;
          return paramTimeUnit;
        }
        paramTimeUnit = new ConnectionResult(13, null);
        return paramTimeUnit;
      }
      finally
      {
        this.IV.unlock();
      }
    }
  }
  
  public <L> c<L> c(L paramL)
  {
    o.b(paramL, "Listener must not be null");
    this.IV.lock();
    try
    {
      paramL = new c(this.IH, paramL);
      this.Jm.add(paramL);
      return paramL;
    }
    finally
    {
      this.IV.unlock();
    }
  }
  
  public void connect()
  {
    this.IV.lock();
    try
    {
      this.Je = false;
      if (!isConnected())
      {
        boolean bool = isConnecting();
        if (!bool) {}
      }
      else
      {
        return;
      }
      this.Jl = true;
      this.Ja = null;
      this.Jc = 1;
      this.Ji.clear();
      this.Jf = this.Jj.size();
      Iterator localIterator = this.Jj.values().iterator();
      while (localIterator.hasNext()) {
        ((Api.a)localIterator.next()).connect();
      }
    }
    finally
    {
      this.IV.unlock();
    }
  }
  
  public void disconnect()
  {
    gp();
    aj(-1);
  }
  
  public Looper getLooper()
  {
    return this.IH;
  }
  
  public boolean isConnected()
  {
    return this.Jc == 2;
  }
  
  public boolean isConnecting()
  {
    return this.Jc == 1;
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.IX.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.IX.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public void reconnect()
  {
    disconnect();
    connect();
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.IX.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.IX.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void stopAutoManage(FragmentActivity paramFragmentActivity)
  {
    if (this.IY >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Called stopAutoManage but automatic lifecycle management is not enabled.");
      d.a(paramFragmentActivity).al(this.IY);
      return;
    }
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.IX.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.IX.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  static abstract interface a
  {
    public abstract void b(b.c<?> paramc);
  }
  
  class b
    extends Handler
  {
    b(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        b.a(b.this).lock();
        try
        {
          if ((!b.this.isConnected()) && (!b.this.isConnecting()))
          {
            boolean bool = b.e(b.this);
            if (bool) {}
          }
          else
          {
            return;
          }
          b.h(b.this);
          b.this.connect();
          return;
        }
        finally
        {
          b.a(b.this).unlock();
        }
      }
      Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
    }
  }
  
  static abstract interface c<A extends Api.a>
  {
    public abstract void a(b.a parama);
    
    public abstract void b(A paramA)
      throws DeadObjectException;
    
    public abstract void cancel();
    
    public abstract Api.c<A> ge();
    
    public abstract int gj();
    
    public abstract void m(Status paramStatus);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/api/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */