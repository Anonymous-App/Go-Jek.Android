package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;

@ez
public abstract class fg
  extends gg
{
  private final fi pQ;
  private final ff.a tu;
  
  public fg(fi paramfi, ff.a parama)
  {
    this.pQ = paramfi;
    this.tu = parama;
  }
  
  private static fk a(fm paramfm, fi paramfi)
  {
    try
    {
      paramfm = paramfm.b(paramfi);
      return paramfm;
    }
    catch (RemoteException paramfm)
    {
      gs.d("Could not fetch ad response from ad request service.", paramfm);
      return null;
    }
    catch (NullPointerException paramfm)
    {
      gs.d("Could not fetch ad response from ad request service due to an Exception.", paramfm);
      return null;
    }
    catch (SecurityException paramfm)
    {
      gs.d("Could not fetch ad response from ad request service due to an Exception.", paramfm);
      return null;
    }
    catch (Throwable paramfm)
    {
      gb.e(paramfm);
    }
    return null;
  }
  
  public abstract void cC();
  
  public abstract fm cD();
  
  /* Error */
  public final void co()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 61	com/google/android/gms/internal/fg:cD	()Lcom/google/android/gms/internal/fm;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull +27 -> 33
    //   9: new 63	com/google/android/gms/internal/fk
    //   12: dup
    //   13: iconst_0
    //   14: invokespecial 66	com/google/android/gms/internal/fk:<init>	(I)V
    //   17: astore_1
    //   18: aload_0
    //   19: invokevirtual 68	com/google/android/gms/internal/fg:cC	()V
    //   22: aload_0
    //   23: getfield 24	com/google/android/gms/internal/fg:tu	Lcom/google/android/gms/internal/ff$a;
    //   26: aload_1
    //   27: invokeinterface 73 2 0
    //   32: return
    //   33: aload_1
    //   34: aload_0
    //   35: getfield 22	com/google/android/gms/internal/fg:pQ	Lcom/google/android/gms/internal/fi;
    //   38: invokestatic 75	com/google/android/gms/internal/fg:a	(Lcom/google/android/gms/internal/fm;Lcom/google/android/gms/internal/fi;)Lcom/google/android/gms/internal/fk;
    //   41: astore_2
    //   42: aload_2
    //   43: astore_1
    //   44: aload_2
    //   45: ifnonnull -27 -> 18
    //   48: new 63	com/google/android/gms/internal/fk
    //   51: dup
    //   52: iconst_0
    //   53: invokespecial 66	com/google/android/gms/internal/fk:<init>	(I)V
    //   56: astore_1
    //   57: goto -39 -> 18
    //   60: astore_1
    //   61: aload_0
    //   62: invokevirtual 68	com/google/android/gms/internal/fg:cC	()V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	fg
    //   4	53	1	localObject1	Object
    //   60	6	1	localObject2	Object
    //   41	4	2	localfk	fk
    // Exception table:
    //   from	to	target	type
    //   0	5	60	finally
    //   9	18	60	finally
    //   33	42	60	finally
    //   48	57	60	finally
  }
  
  public final void onStop()
  {
    cC();
  }
  
  @ez
  public static final class a
    extends fg
  {
    private final Context mContext;
    
    public a(Context paramContext, fi paramfi, ff.a parama)
    {
      super(parama);
      this.mContext = paramContext;
    }
    
    public void cC() {}
    
    public fm cD()
    {
      Object localObject = gb.bD();
      localObject = new bm(((Bundle)localObject).getString("gads:sdk_core_location"), ((Bundle)localObject).getString("gads:sdk_core_experiment_id"), ((Bundle)localObject).getString("gads:block_autoclicks_experiment_id"));
      return fr.a(this.mContext, (bm)localObject, new cj(), new fy());
    }
  }
  
  @ez
  public static final class b
    extends fg
    implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener
  {
    private final Object mw = new Object();
    private final ff.a tu;
    private final fh tv;
    
    public b(Context paramContext, fi paramfi, ff.a parama)
    {
      super(parama);
      this.tu = parama;
      this.tv = new fh(paramContext, this, this, paramfi.lD.wF);
      this.tv.connect();
    }
    
    public void cC()
    {
      synchronized (this.mw)
      {
        if ((this.tv.isConnected()) || (this.tv.isConnecting())) {
          this.tv.disconnect();
        }
        return;
      }
    }
    
    public fm cD()
    {
      synchronized (this.mw)
      {
        try
        {
          fm localfm = this.tv.cE();
          return localfm;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          return null;
        }
      }
    }
    
    public void onConnected(Bundle paramBundle)
    {
      start();
    }
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      this.tu.a(new fk(0));
    }
    
    public void onDisconnected()
    {
      gs.S("Disconnected from remote ad request service.");
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */