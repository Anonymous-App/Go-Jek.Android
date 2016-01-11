package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;

public abstract interface be
  extends IInterface
{
  public abstract IBinder a(d paramd, ay paramay, String paramString, ct paramct, int paramInt)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements be
  {
    public static be g(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
      if ((localIInterface != null) && ((localIInterface instanceof be))) {
        return (be)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
      d locald = d.a.am(paramParcel1.readStrongBinder());
      if (paramParcel1.readInt() != 0) {}
      for (ay localay = ay.CREATOR.c(paramParcel1);; localay = null)
      {
        paramParcel1 = a(locald, localay, paramParcel1.readString(), ct.a.l(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      }
    }
    
    private static class a
      implements be
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public IBinder a(d paramd, ay paramay, String paramString, ct paramct, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 6
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 30
        //   17: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +107 -> 128
        //   24: aload_1
        //   25: invokeinterface 40 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload_2
        //   38: ifnull +95 -> 133
        //   41: aload 7
        //   43: iconst_1
        //   44: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   47: aload_2
        //   48: aload 7
        //   50: iconst_0
        //   51: invokevirtual 53	com/google/android/gms/internal/ay:writeToParcel	(Landroid/os/Parcel;I)V
        //   54: aload 7
        //   56: aload_3
        //   57: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   60: aload 6
        //   62: astore_1
        //   63: aload 4
        //   65: ifnull +11 -> 76
        //   68: aload 4
        //   70: invokeinterface 59 1 0
        //   75: astore_1
        //   76: aload 7
        //   78: aload_1
        //   79: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   82: aload 7
        //   84: iload 5
        //   86: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   89: aload_0
        //   90: getfield 18	com/google/android/gms/internal/be$a$a:lb	Landroid/os/IBinder;
        //   93: iconst_1
        //   94: aload 7
        //   96: aload 8
        //   98: iconst_0
        //   99: invokeinterface 65 5 0
        //   104: pop
        //   105: aload 8
        //   107: invokevirtual 68	android/os/Parcel:readException	()V
        //   110: aload 8
        //   112: invokevirtual 71	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   115: astore_1
        //   116: aload 8
        //   118: invokevirtual 74	android/os/Parcel:recycle	()V
        //   121: aload 7
        //   123: invokevirtual 74	android/os/Parcel:recycle	()V
        //   126: aload_1
        //   127: areturn
        //   128: aconst_null
        //   129: astore_1
        //   130: goto -99 -> 31
        //   133: aload 7
        //   135: iconst_0
        //   136: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   139: goto -85 -> 54
        //   142: astore_1
        //   143: aload 8
        //   145: invokevirtual 74	android/os/Parcel:recycle	()V
        //   148: aload 7
        //   150: invokevirtual 74	android/os/Parcel:recycle	()V
        //   153: aload_1
        //   154: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	155	0	this	a
        //   0	155	1	paramd	d
        //   0	155	2	paramay	ay
        //   0	155	3	paramString	String
        //   0	155	4	paramct	ct
        //   0	155	5	paramInt	int
        //   1	60	6	localObject	Object
        //   6	143	7	localParcel1	Parcel
        //   11	133	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	142	finally
        //   24	31	142	finally
        //   31	37	142	finally
        //   41	54	142	finally
        //   54	60	142	finally
        //   68	76	142	finally
        //   76	116	142	finally
        //   133	139	142	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/be.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */