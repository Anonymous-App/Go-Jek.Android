package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface eu
  extends IInterface
{
  public abstract void a(es parames)
    throws RemoteException;
  
  public abstract boolean e(String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements eu
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
    }
    
    public static eu B(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
      if ((localIInterface != null) && ((localIInterface instanceof eu))) {
        return (eu)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
        boolean bool = e(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (bool) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
      a(es.a.z(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class a
      implements eu
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void a(es parames)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/internal/eu$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 49 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 52	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 55	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 55	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 55	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 55	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	parames	es
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public boolean e(String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 30
        //   17: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 5
        //   22: aload_1
        //   23: invokevirtual 61	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   26: aload 5
        //   28: aload_2
        //   29: invokevirtual 61	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/internal/eu$a$a:lb	Landroid/os/IBinder;
        //   36: iconst_1
        //   37: aload 5
        //   39: aload 6
        //   41: iconst_0
        //   42: invokeinterface 49 5 0
        //   47: pop
        //   48: aload 6
        //   50: invokevirtual 52	android/os/Parcel:readException	()V
        //   53: aload 6
        //   55: invokevirtual 65	android/os/Parcel:readInt	()I
        //   58: istore_3
        //   59: iload_3
        //   60: ifeq +16 -> 76
        //   63: aload 6
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: aload 5
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: iload 4
        //   75: ireturn
        //   76: iconst_0
        //   77: istore 4
        //   79: goto -16 -> 63
        //   82: astore_1
        //   83: aload 6
        //   85: invokevirtual 55	android/os/Parcel:recycle	()V
        //   88: aload 5
        //   90: invokevirtual 55	android/os/Parcel:recycle	()V
        //   93: aload_1
        //   94: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	95	0	this	a
        //   0	95	1	paramString1	String
        //   0	95	2	paramString2	String
        //   58	2	3	i	int
        //   1	77	4	bool	boolean
        //   6	83	5	localParcel1	Parcel
        //   11	73	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	59	82	finally
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/eu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */