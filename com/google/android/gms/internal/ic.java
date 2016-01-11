package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;

public abstract interface ic
  extends IInterface
{
  public abstract void S(int paramInt)
    throws RemoteException;
  
  public abstract void a(int paramInt, DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void a(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void e(int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract void fp()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ic
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.appstate.internal.IAppStateCallbacks");
    }
    
    public static ic J(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
      if ((localIInterface != null) && ((localIInterface instanceof ic))) {
        return (ic)localIInterface;
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
      Object localObject2 = null;
      Object localObject1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.appstate.internal.IAppStateCallbacks");
        return true;
      case 5001: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        a(paramInt1, (DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5002: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        a((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5003: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
        e(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5004: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
        fp();
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
      S(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class a
      implements ic
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void S(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateCallbacks");
          localParcel1.writeInt(paramInt);
          this.lb.transact(5005, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void a(int paramInt, DataHolder paramDataHolder)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 31
        //   12: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: iload_1
        //   17: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   20: aload_2
        //   21: ifnull +46 -> 67
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 58	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/internal/ic$a$a:lb	Landroid/os/IBinder;
        //   39: sipush 5001
        //   42: aload_3
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 44 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 47	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 50	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 50	android/os/Parcel:recycle	()V
        //   66: return
        //   67: aload_3
        //   68: iconst_0
        //   69: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   72: goto -37 -> 35
        //   75: astore_2
        //   76: aload 4
        //   78: invokevirtual 50	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 50	android/os/Parcel:recycle	()V
        //   85: aload_2
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	a
        //   0	87	1	paramInt	int
        //   0	87	2	paramDataHolder	DataHolder
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	75	finally
        //   24	35	75	finally
        //   35	57	75	finally
        //   67	72	75	finally
      }
      
      /* Error */
      public void a(DataHolder paramDataHolder)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 58	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/ic$a$a:lb	Landroid/os/IBinder;
        //   33: sipush 5002
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 44 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 47	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 50	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 50	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_2
        //   59: iconst_0
        //   60: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore_1
        //   67: aload_3
        //   68: invokevirtual 50	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 50	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	a
        //   0	77	1	paramDataHolder	DataHolder
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	29	66	finally
        //   29	49	66	finally
        //   58	63	66	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void e(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateCallbacks");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.lb.transact(5003, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void fp()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateCallbacks");
          this.lb.transact(5004, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */