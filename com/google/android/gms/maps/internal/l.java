package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.internal.f.a;

public abstract interface l
  extends IInterface
{
  public abstract boolean a(f paramf)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements l
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerClickListener");
    }
    
    public static l bc(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
      if ((localIInterface != null) && ((localIInterface instanceof l))) {
        return (l)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.maps.internal.IOnMarkerClickListener");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
      boolean bool = a(f.a.bu(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      if (bool) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
      }
    }
    
    private static class a
      implements l
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public boolean a(f paramf)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 30
        //   16: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +59 -> 79
        //   23: aload_1
        //   24: invokeinterface 40 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_0
        //   37: getfield 18	com/google/android/gms/maps/internal/l$a$a:lb	Landroid/os/IBinder;
        //   40: iconst_1
        //   41: aload 4
        //   43: aload 5
        //   45: iconst_0
        //   46: invokeinterface 49 5 0
        //   51: pop
        //   52: aload 5
        //   54: invokevirtual 52	android/os/Parcel:readException	()V
        //   57: aload 5
        //   59: invokevirtual 56	android/os/Parcel:readInt	()I
        //   62: istore_2
        //   63: iload_2
        //   64: ifeq +20 -> 84
        //   67: aload 5
        //   69: invokevirtual 59	android/os/Parcel:recycle	()V
        //   72: aload 4
        //   74: invokevirtual 59	android/os/Parcel:recycle	()V
        //   77: iload_3
        //   78: ireturn
        //   79: aconst_null
        //   80: astore_1
        //   81: goto -51 -> 30
        //   84: iconst_0
        //   85: istore_3
        //   86: goto -19 -> 67
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 59	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 59	android/os/Parcel:recycle	()V
        //   100: aload_1
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	a
        //   0	102	1	paramf	f
        //   62	2	2	i	int
        //   1	85	3	bool	boolean
        //   5	91	4	localParcel1	Parcel
        //   10	81	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	89	finally
        //   23	30	89	finally
        //   30	63	89	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/internal/l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */