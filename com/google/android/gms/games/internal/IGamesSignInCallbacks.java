package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;

public abstract interface IGamesSignInCallbacks
  extends IInterface
{
  public abstract void T(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void U(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void b(int paramInt, Intent paramIntent)
    throws RemoteException;
  
  public abstract void dD(int paramInt)
    throws RemoteException;
  
  public abstract void dE(int paramInt)
    throws RemoteException;
  
  public abstract void g(DataHolder paramDataHolder)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGamesSignInCallbacks
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.games.internal.IGamesSignInCallbacks");
    }
    
    public static IGamesSignInCallbacks aC(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
      if ((localIInterface != null) && ((localIInterface instanceof IGamesSignInCallbacks))) {
        return (IGamesSignInCallbacks)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject2 = null;
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        return true;
      case 5001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
        }
        b(paramInt1, (Intent)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        T((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        U((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        dD(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        localObject1 = localObject4;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        g((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
      dE(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class Proxy
      implements IGamesSignInCallbacks
    {
      private IBinder lb;
      
      Proxy(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void T(DataHolder paramDataHolder)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesSignInCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5002
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 58	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 58	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_2
        //   59: iconst_0
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore_1
        //   67: aload_3
        //   68: invokevirtual 58	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 58	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	Proxy
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
      
      /* Error */
      public void U(DataHolder paramDataHolder)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesSignInCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5003
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 58	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 58	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_2
        //   59: iconst_0
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore_1
        //   67: aload_3
        //   68: invokevirtual 58	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 58	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	Proxy
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
      
      /* Error */
      public void b(int paramInt, Intent paramIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 32
        //   12: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: iload_1
        //   17: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   20: aload_2
        //   21: ifnull +46 -> 67
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 67	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesSignInCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5001
        //   42: aload_3
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 52 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 55	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 58	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 58	android/os/Parcel:recycle	()V
        //   66: return
        //   67: aload_3
        //   68: iconst_0
        //   69: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   72: goto -37 -> 35
        //   75: astore_2
        //   76: aload 4
        //   78: invokevirtual 58	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 58	android/os/Parcel:recycle	()V
        //   85: aload_2
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	Proxy
        //   0	87	1	paramInt	int
        //   0	87	2	paramIntent	Intent
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	75	finally
        //   24	35	75	finally
        //   35	57	75	finally
        //   67	72	75	finally
      }
      
      public void dD(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
          localParcel1.writeInt(paramInt);
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
      
      public void dE(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
          localParcel1.writeInt(paramInt);
          this.lb.transact(5006, localParcel1, localParcel2, 0);
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
      public void g(DataHolder paramDataHolder)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesSignInCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5005
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 58	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 58	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_2
        //   59: iconst_0
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore_1
        //   67: aload_3
        //   68: invokevirtual 58	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 58	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	Proxy
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
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/IGamesSignInCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */