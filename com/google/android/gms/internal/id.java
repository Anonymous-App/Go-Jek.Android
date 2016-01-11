package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface id
  extends IInterface
{
  public abstract void a(ic paramic)
    throws RemoteException;
  
  public abstract void a(ic paramic, int paramInt)
    throws RemoteException;
  
  public abstract void a(ic paramic, int paramInt, String paramString, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public abstract void a(ic paramic, int paramInt, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public abstract void b(ic paramic)
    throws RemoteException;
  
  public abstract void b(ic paramic, int paramInt)
    throws RemoteException;
  
  public abstract void c(ic paramic)
    throws RemoteException;
  
  public abstract int fq()
    throws RemoteException;
  
  public abstract int fr()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements id
  {
    public static id K(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.appstate.internal.IAppStateService");
      if ((localIInterface != null) && ((localIInterface instanceof id))) {
        return (id)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.appstate.internal.IAppStateService");
        return true;
      case 5001: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
        paramInt1 = fq();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5002: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
        paramInt1 = fr();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5003: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
        a(ic.a.J(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.createByteArray());
        paramParcel2.writeNoException();
        return true;
      case 5004: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
        a(ic.a.J(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5005: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
        a(ic.a.J(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5006: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
        a(ic.a.J(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.createByteArray());
        paramParcel2.writeNoException();
        return true;
      case 5007: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
        b(ic.a.J(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5008: 
        paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
        b(ic.a.J(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
      c(ic.a.J(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class a
      implements id
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void a(ic paramic)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/internal/id$a$a:lb	Landroid/os/IBinder;
        //   34: sipush 5005
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 49 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 52	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 55	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 55	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 55	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramic	ic
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void a(ic paramic, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: iload_2
        //   33: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   36: aload_0
        //   37: getfield 18	com/google/android/gms/internal/id$a$a:lb	Landroid/os/IBinder;
        //   40: sipush 5004
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 49 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 52	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 55	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 55	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 55	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramic	ic
        //   0	85	2	paramInt	int
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void a(ic paramic, int paramInt, String paramString, byte[] paramArrayOfByte)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +69 -> 87
        //   21: aload_1
        //   22: invokeinterface 40 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: iload_2
        //   37: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 65	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 5
        //   48: aload 4
        //   50: invokevirtual 69	android/os/Parcel:writeByteArray	([B)V
        //   53: aload_0
        //   54: getfield 18	com/google/android/gms/internal/id$a$a:lb	Landroid/os/IBinder;
        //   57: sipush 5006
        //   60: aload 5
        //   62: aload 6
        //   64: iconst_0
        //   65: invokeinterface 49 5 0
        //   70: pop
        //   71: aload 6
        //   73: invokevirtual 52	android/os/Parcel:readException	()V
        //   76: aload 6
        //   78: invokevirtual 55	android/os/Parcel:recycle	()V
        //   81: aload 5
        //   83: invokevirtual 55	android/os/Parcel:recycle	()V
        //   86: return
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -61 -> 28
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 55	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 55	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	a
        //   0	105	1	paramic	ic
        //   0	105	2	paramInt	int
        //   0	105	3	paramString	String
        //   0	105	4	paramArrayOfByte	byte[]
        //   3	96	5	localParcel1	Parcel
        //   8	86	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	92	finally
        //   21	28	92	finally
        //   28	76	92	finally
      }
      
      /* Error */
      public void a(ic paramic, int paramInt, byte[] paramArrayOfByte)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 40 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: iload_2
        //   37: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 69	android/os/Parcel:writeByteArray	([B)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/internal/id$a$a:lb	Landroid/os/IBinder;
        //   50: sipush 5003
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 49 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 52	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 55	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 55	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 55	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	a
        //   0	98	1	paramic	ic
        //   0	98	2	paramInt	int
        //   0	98	3	paramArrayOfByte	byte[]
        //   3	89	4	localParcel1	Parcel
        //   8	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	85	finally
        //   21	28	85	finally
        //   28	69	85	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void b(ic paramic)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/internal/id$a$a:lb	Landroid/os/IBinder;
        //   34: sipush 5008
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 49 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 52	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 55	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 55	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 55	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramic	ic
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void b(ic paramic, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: iload_2
        //   33: invokevirtual 61	android/os/Parcel:writeInt	(I)V
        //   36: aload_0
        //   37: getfield 18	com/google/android/gms/internal/id$a$a:lb	Landroid/os/IBinder;
        //   40: sipush 5007
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 49 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 52	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 55	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 55	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 55	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 55	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramic	ic
        //   0	85	2	paramInt	int
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void c(ic paramic)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/internal/id$a$a:lb	Landroid/os/IBinder;
        //   34: sipush 5009
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 49 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 52	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 55	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 55	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 55	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramic	ic
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      public int fq()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
          this.lb.transact(5001, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int fr()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
          this.lb.transact(5002, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/id.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */