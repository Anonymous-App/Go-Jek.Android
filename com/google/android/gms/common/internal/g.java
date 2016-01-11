package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class g
  implements Handler.Callback
{
  private static final Object LS = new Object();
  private static g LT;
  private final HashMap<String, a> LU = new HashMap();
  private final Context mD;
  private final Handler mHandler = new Handler(paramContext.getMainLooper(), this);
  
  private g(Context paramContext)
  {
    this.mD = paramContext.getApplicationContext();
  }
  
  public static g J(Context paramContext)
  {
    synchronized (LS)
    {
      if (LT == null) {
        LT = new g(paramContext.getApplicationContext());
      }
      return LT;
    }
  }
  
  public boolean a(String paramString, e<?>.f parame)
  {
    for (;;)
    {
      a locala;
      synchronized (this.LU)
      {
        locala = (a)this.LU.get(paramString);
        if (locala == null)
        {
          locala = new a(paramString);
          locala.a(parame);
          parame = new Intent(paramString).setPackage("com.google.android.gms");
          locala.J(this.mD.bindService(parame, locala.gW(), 129));
          this.LU.put(paramString, locala);
          paramString = locala;
          boolean bool = paramString.isBound();
          return bool;
        }
        this.mHandler.removeMessages(0, locala);
        if (locala.c(parame)) {
          throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + paramString);
        }
      }
      locala.a(parame);
      switch (locala.getState())
      {
      case 1: 
        parame.onServiceConnected(locala.getComponentName(), locala.getBinder());
        paramString = locala;
        break;
      case 2: 
        paramString = new Intent(paramString).setPackage("com.google.android.gms");
        locala.J(this.mD.bindService(paramString, locala.gW(), 129));
        paramString = locala;
        break;
      default: 
        paramString = locala;
      }
    }
  }
  
  public void b(String paramString, e<?>.f parame)
  {
    a locala;
    synchronized (this.LU)
    {
      locala = (a)this.LU.get(paramString);
      if (locala == null) {
        throw new IllegalStateException("Nonexistent connection status for service action: " + paramString);
      }
    }
    if (!locala.c(parame)) {
      throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + paramString);
    }
    locala.b(parame);
    if (locala.gY())
    {
      paramString = this.mHandler.obtainMessage(0, locala);
      this.mHandler.sendMessageDelayed(paramString, 5000L);
    }
  }
  
  public boolean handleMessage(Message arg1)
  {
    switch (???.what)
    {
    default: 
      return false;
    }
    a locala = (a)???.obj;
    synchronized (this.LU)
    {
      if (locala.gY())
      {
        this.mD.unbindService(locala.gW());
        this.LU.remove(locala.gX());
      }
      return true;
    }
  }
  
  final class a
  {
    private final String LV;
    private final a LW;
    private final HashSet<e<?>.f> LX;
    private boolean LY;
    private IBinder LZ;
    private ComponentName Ma;
    private int mState;
    
    public a(String paramString)
    {
      this.LV = paramString;
      this.LW = new a();
      this.LX = new HashSet();
      this.mState = 0;
    }
    
    public void J(boolean paramBoolean)
    {
      this.LY = paramBoolean;
    }
    
    public void a(e<?>.f parame)
    {
      this.LX.add(parame);
    }
    
    public void b(e<?>.f parame)
    {
      this.LX.remove(parame);
    }
    
    public boolean c(e<?>.f parame)
    {
      return this.LX.contains(parame);
    }
    
    public a gW()
    {
      return this.LW;
    }
    
    public String gX()
    {
      return this.LV;
    }
    
    public boolean gY()
    {
      return this.LX.isEmpty();
    }
    
    public IBinder getBinder()
    {
      return this.LZ;
    }
    
    public ComponentName getComponentName()
    {
      return this.Ma;
    }
    
    public int getState()
    {
      return this.mState;
    }
    
    public boolean isBound()
    {
      return this.LY;
    }
    
    public class a
      implements ServiceConnection
    {
      public a() {}
      
      public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        synchronized (g.a(g.this))
        {
          g.a.a(g.a.this, paramIBinder);
          g.a.a(g.a.this, paramComponentName);
          Iterator localIterator = g.a.a(g.a.this).iterator();
          if (localIterator.hasNext()) {
            ((e.f)localIterator.next()).onServiceConnected(paramComponentName, paramIBinder);
          }
        }
        g.a.a(g.a.this, 1);
      }
      
      public void onServiceDisconnected(ComponentName paramComponentName)
      {
        synchronized (g.a(g.this))
        {
          g.a.a(g.a.this, null);
          g.a.a(g.a.this, paramComponentName);
          Iterator localIterator = g.a.a(g.a.this).iterator();
          if (localIterator.hasNext()) {
            ((e.f)localIterator.next()).onServiceDisconnected(paramComponentName);
          }
        }
        g.a.a(g.a.this, 2);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/internal/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */