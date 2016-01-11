package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.internal.f.a;

public abstract interface d
  extends IInterface
{
  public abstract com.google.android.gms.dynamic.d f(f paramf)
    throws RemoteException;
  
  public abstract com.google.android.gms.dynamic.d g(f paramf)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements d
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }
    
    public static d aR(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
      if ((localIInterface != null) && ((localIInterface instanceof d))) {
        return (d)localIInterface;
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
      com.google.android.gms.dynamic.d locald2 = null;
      com.google.android.gms.dynamic.d locald1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.maps.internal.IInfoWindowAdapter");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
        locald2 = f(f.a.bu(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel1 = locald1;
        if (locald2 != null) {
          paramParcel1 = locald2.asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
      locald1 = g(f.a.bu(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      paramParcel1 = locald2;
      if (locald1 != null) {
        paramParcel1 = locald1.asBinder();
      }
      paramParcel2.writeStrongBinder(paramParcel1);
      return true;
    }
    
    private static class a
      implements d
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public com.google.android.gms.dynamic.d f(f paramf)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 33
        //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +51 -> 66
        //   18: aload_1
        //   19: invokeinterface 41 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 44	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/d$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_1
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 50 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 53	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 56	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 62	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 65	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 65	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aconst_null
        //   67: astore_1
        //   68: goto -43 -> 25
        //   71: astore_1
        //   72: aload_3
        //   73: invokevirtual 65	android/os/Parcel:recycle	()V
        //   76: aload_2
        //   77: invokevirtual 65	android/os/Parcel:recycle	()V
        //   80: aload_1
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	a
        //   0	82	1	paramf	f
        //   3	74	2	localParcel1	Parcel
        //   7	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	71	finally
        //   18	25	71	finally
        //   25	56	71	finally
      }
      
      /* Error */
      public com.google.android.gms.dynamic.d g(f paramf)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 33
        //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +51 -> 66
        //   18: aload_1
        //   19: invokeinterface 41 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 44	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/d$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 50 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 53	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 56	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 62	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 65	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 65	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aconst_null
        //   67: astore_1
        //   68: goto -43 -> 25
        //   71: astore_1
        //   72: aload_3
        //   73: invokevirtual 65	android/os/Parcel:recycle	()V
        //   76: aload_2
        //   77: invokevirtual 65	android/os/Parcel:recycle	()V
        //   80: aload_1
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	a
        //   0	82	1	paramf	f
        //   3	74	2	localParcel1	Parcel
        //   7	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	71	finally
        //   18	25	71	finally
        //   25	56	71	finally
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/internal/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */