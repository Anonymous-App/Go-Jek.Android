package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api.a;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

public abstract class e<T extends IInterface>
  implements Api.a, f.b
{
  public static final String[] LE = { "service_esmobile", "service_googleme" };
  private final String[] Ds;
  private final Looper IH;
  private final f IX;
  private final ArrayList<e<T>.b<?>> LA = new ArrayList();
  private e<T>.f LB;
  private volatile int LC = 1;
  boolean LD = false;
  private T Lz;
  private final Context mContext;
  final Handler mHandler;
  
  protected e(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String... paramVarArgs)
  {
    this.mContext = ((Context)o.i(paramContext));
    this.IH = ((Looper)o.b(paramLooper, "Looper must not be null"));
    this.IX = new f(paramContext, paramLooper, this);
    this.mHandler = new a(paramLooper);
    c(paramVarArgs);
    this.Ds = paramVarArgs;
    registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)o.i(paramConnectionCallbacks));
    registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)o.i(paramOnConnectionFailedListener));
  }
  
  @Deprecated
  protected e(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener, String... paramVarArgs)
  {
    this(paramContext, paramContext.getMainLooper(), new c(paramConnectionCallbacks), new g(paramOnConnectionFailedListener), paramVarArgs);
  }
  
  private void az(int paramInt)
  {
    int i = this.LC;
    this.LC = paramInt;
    if (i != paramInt)
    {
      if (paramInt != 3) {
        break label25;
      }
      onConnected();
    }
    label25:
    while ((i != 3) || (paramInt != 1)) {
      return;
    }
    onDisconnected();
  }
  
  protected final void N(IBinder paramIBinder)
  {
    try
    {
      a(l.a.Q(paramIBinder), new e(this));
      return;
    }
    catch (RemoteException paramIBinder)
    {
      Log.w("GmsClient", "service died");
    }
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, new h(paramInt, paramIBinder, paramBundle)));
  }
  
  @Deprecated
  public final void a(e<T>.b<?> parame)
  {
    synchronized (this.LA)
    {
      this.LA.add(parame);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(2, parame));
      return;
    }
  }
  
  protected abstract void a(l paraml, e parame)
    throws RemoteException;
  
  public void aA(int paramInt)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(4, Integer.valueOf(paramInt)));
  }
  
  protected void c(String... paramVarArgs) {}
  
  public void connect()
  {
    this.LD = true;
    az(2);
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
    if (i != 0)
    {
      az(1);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(i)));
    }
    do
    {
      return;
      if (this.LB != null)
      {
        Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
        this.Lz = null;
        g.J(this.mContext).b(getStartServiceAction(), this.LB);
      }
      this.LB = new f();
    } while (g.J(this.mContext).a(getStartServiceAction(), this.LB));
    Log.e("GmsClient", "unable to connect to service: " + getStartServiceAction());
    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(9)));
  }
  
  protected final void dJ()
  {
    if (!isConnected()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  public void disconnect()
  {
    this.LD = false;
    synchronized (this.LA)
    {
      int j = this.LA.size();
      int i = 0;
      while (i < j)
      {
        ((b)this.LA.get(i)).gV();
        i += 1;
      }
      this.LA.clear();
      az(1);
      this.Lz = null;
      if (this.LB != null)
      {
        g.J(this.mContext).b(getStartServiceAction(), this.LB);
        this.LB = null;
      }
      return;
    }
  }
  
  public Bundle fC()
  {
    return null;
  }
  
  public final String[] gR()
  {
    return this.Ds;
  }
  
  public final T gS()
  {
    dJ();
    return this.Lz;
  }
  
  public final Context getContext()
  {
    return this.mContext;
  }
  
  public final Looper getLooper()
  {
    return this.IH;
  }
  
  protected abstract String getServiceDescriptor();
  
  protected abstract String getStartServiceAction();
  
  public boolean gq()
  {
    return this.LD;
  }
  
  public boolean isConnected()
  {
    return this.LC == 3;
  }
  
  public boolean isConnecting()
  {
    return this.LC == 2;
  }
  
  @Deprecated
  public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.IX.isConnectionCallbacksRegistered(new c(paramConnectionCallbacks));
  }
  
  @Deprecated
  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.IX.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  protected abstract T j(IBinder paramIBinder);
  
  protected void onConnected() {}
  
  protected void onDisconnected() {}
  
  @Deprecated
  public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.IX.registerConnectionCallbacks(new c(paramConnectionCallbacks));
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.IX.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  @Deprecated
  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.IX.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.IX.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  @Deprecated
  public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.IX.unregisterConnectionCallbacks(new c(paramConnectionCallbacks));
  }
  
  @Deprecated
  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.IX.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  final class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if ((paramMessage.what == 1) && (!e.this.isConnecting()))
      {
        paramMessage = (e.b)paramMessage.obj;
        paramMessage.gT();
        paramMessage.unregister();
        return;
      }
      if (paramMessage.what == 3)
      {
        e.a(e.this).b(new ConnectionResult(((Integer)paramMessage.obj).intValue(), null));
        return;
      }
      if (paramMessage.what == 4)
      {
        e.a(e.this, 1);
        e.a(e.this, null);
        e.a(e.this).aB(((Integer)paramMessage.obj).intValue());
        return;
      }
      if ((paramMessage.what == 2) && (!e.this.isConnected()))
      {
        paramMessage = (e.b)paramMessage.obj;
        paramMessage.gT();
        paramMessage.unregister();
        return;
      }
      if ((paramMessage.what == 2) || (paramMessage.what == 1))
      {
        ((e.b)paramMessage.obj).gU();
        return;
      }
      Log.wtf("GmsClient", "Don't know how to handle this message.");
    }
  }
  
  protected abstract class b<TListener>
  {
    private boolean LG;
    private TListener mListener;
    
    public b()
    {
      Object localObject;
      this.mListener = localObject;
      this.LG = false;
    }
    
    protected abstract void g(TListener paramTListener);
    
    protected abstract void gT();
    
    /* Error */
    public void gU()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 24	com/google/android/gms/common/internal/e$b:mListener	Ljava/lang/Object;
      //   6: astore_1
      //   7: aload_0
      //   8: getfield 26	com/google/android/gms/common/internal/e$b:LG	Z
      //   11: ifeq +33 -> 44
      //   14: ldc 37
      //   16: new 39	java/lang/StringBuilder
      //   19: dup
      //   20: invokespecial 40	java/lang/StringBuilder:<init>	()V
      //   23: ldc 42
      //   25: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   28: aload_0
      //   29: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   32: ldc 51
      //   34: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   37: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   40: invokestatic 61	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   43: pop
      //   44: aload_0
      //   45: monitorexit
      //   46: aload_1
      //   47: ifnull +34 -> 81
      //   50: aload_0
      //   51: aload_1
      //   52: invokevirtual 63	com/google/android/gms/common/internal/e$b:g	(Ljava/lang/Object;)V
      //   55: aload_0
      //   56: monitorenter
      //   57: aload_0
      //   58: iconst_1
      //   59: putfield 26	com/google/android/gms/common/internal/e$b:LG	Z
      //   62: aload_0
      //   63: monitorexit
      //   64: aload_0
      //   65: invokevirtual 66	com/google/android/gms/common/internal/e$b:unregister	()V
      //   68: return
      //   69: astore_1
      //   70: aload_0
      //   71: monitorexit
      //   72: aload_1
      //   73: athrow
      //   74: astore_1
      //   75: aload_0
      //   76: invokevirtual 68	com/google/android/gms/common/internal/e$b:gT	()V
      //   79: aload_1
      //   80: athrow
      //   81: aload_0
      //   82: invokevirtual 68	com/google/android/gms/common/internal/e$b:gT	()V
      //   85: goto -30 -> 55
      //   88: astore_1
      //   89: aload_0
      //   90: monitorexit
      //   91: aload_1
      //   92: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	93	0	this	b
      //   6	46	1	localObject1	Object
      //   69	4	1	localObject2	Object
      //   74	6	1	localRuntimeException	RuntimeException
      //   88	4	1	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   2	44	69	finally
      //   44	46	69	finally
      //   70	72	69	finally
      //   50	55	74	java/lang/RuntimeException
      //   57	64	88	finally
      //   89	91	88	finally
    }
    
    public void gV()
    {
      try
      {
        this.mListener = null;
        return;
      }
      finally {}
    }
    
    public void unregister()
    {
      gV();
      synchronized (e.b(e.this))
      {
        e.b(e.this).remove(this);
        return;
      }
    }
  }
  
  public static final class c
    implements GoogleApiClient.ConnectionCallbacks
  {
    private final GooglePlayServicesClient.ConnectionCallbacks LH;
    
    public c(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
    {
      this.LH = paramConnectionCallbacks;
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof c)) {
        return this.LH.equals(((c)paramObject).LH);
      }
      return this.LH.equals(paramObject);
    }
    
    public void onConnected(Bundle paramBundle)
    {
      this.LH.onConnected(paramBundle);
    }
    
    public void onConnectionSuspended(int paramInt)
    {
      this.LH.onDisconnected();
    }
  }
  
  public abstract class d<TListener>
    extends e<T>.b<TListener>
  {
    private final DataHolder II;
    
    public d(DataHolder paramDataHolder)
    {
      super(paramDataHolder);
      DataHolder localDataHolder;
      this.II = localDataHolder;
    }
    
    protected abstract void a(TListener paramTListener, DataHolder paramDataHolder);
    
    protected final void g(TListener paramTListener)
    {
      a(paramTListener, this.II);
    }
    
    protected void gT()
    {
      if (this.II != null) {
        this.II.close();
      }
    }
  }
  
  public static final class e
    extends k.a
  {
    private e LI;
    
    public e(e parame)
    {
      this.LI = parame;
    }
    
    public void b(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      o.b("onPostInitComplete can be called only once per call to getServiceFromBroker", this.LI);
      this.LI.a(paramInt, paramIBinder, paramBundle);
      this.LI = null;
    }
  }
  
  final class f
    implements ServiceConnection
  {
    f() {}
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      e.this.N(paramIBinder);
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      e.this.mHandler.sendMessage(e.this.mHandler.obtainMessage(4, Integer.valueOf(1)));
    }
  }
  
  public static final class g
    implements GoogleApiClient.OnConnectionFailedListener
  {
    private final GooglePlayServicesClient.OnConnectionFailedListener LJ;
    
    public g(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this.LJ = paramOnConnectionFailedListener;
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof g)) {
        return this.LJ.equals(((g)paramObject).LJ);
      }
      return this.LJ.equals(paramObject);
    }
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      this.LJ.onConnectionFailed(paramConnectionResult);
    }
  }
  
  protected final class h
    extends e<T>.b<Boolean>
  {
    public final Bundle LK;
    public final IBinder LL;
    public final int statusCode;
    
    public h(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      super(Boolean.valueOf(true));
      this.statusCode = paramInt;
      this.LL = paramIBinder;
      this.LK = paramBundle;
    }
    
    protected void b(Boolean paramBoolean)
    {
      if (paramBoolean == null)
      {
        e.a(e.this, 1);
        return;
      }
      switch (this.statusCode)
      {
      default: 
        if (this.LK == null) {
          break;
        }
      }
      for (paramBoolean = (PendingIntent)this.LK.getParcelable("pendingIntent");; paramBoolean = null)
      {
        if (e.d(e.this) != null)
        {
          g.J(e.e(e.this)).b(e.this.getStartServiceAction(), e.d(e.this));
          e.a(e.this, null);
        }
        e.a(e.this, 1);
        e.a(e.this, null);
        e.a(e.this).b(new ConnectionResult(this.statusCode, paramBoolean));
        return;
        try
        {
          paramBoolean = this.LL.getInterfaceDescriptor();
          if (e.this.getServiceDescriptor().equals(paramBoolean))
          {
            e.a(e.this, e.this.j(this.LL));
            if (e.c(e.this) != null)
            {
              e.a(e.this, 3);
              e.a(e.this).dL();
              return;
            }
          }
        }
        catch (RemoteException paramBoolean)
        {
          g.J(e.e(e.this)).b(e.this.getStartServiceAction(), e.d(e.this));
          e.a(e.this, null);
          e.a(e.this, 1);
          e.a(e.this, null);
          e.a(e.this).b(new ConnectionResult(8, null));
          return;
        }
        e.a(e.this, 1);
        throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
      }
    }
    
    protected void gT() {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/internal/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */