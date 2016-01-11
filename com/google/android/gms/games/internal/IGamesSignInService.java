package com.google.android.gms.games.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IGamesSignInService
  extends IInterface
{
  public abstract void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String paramString3)
    throws RemoteException;
  
  public abstract void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3)
    throws RemoteException;
  
  public abstract void b(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String paramString3)
    throws RemoteException;
  
  public abstract String bL(String paramString)
    throws RemoteException;
  
  public abstract String bM(String paramString)
    throws RemoteException;
  
  public abstract String h(String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void w(String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGamesSignInService
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.games.internal.IGamesSignInService");
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesSignInService");
        return true;
      case 5001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        paramParcel1 = bL(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 5002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        paramParcel1 = bM(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 5009: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        String str = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool = true;; bool = false)
        {
          paramParcel1 = h(str, bool);
          paramParcel2.writeNoException();
          paramParcel2.writeString(paramParcel1);
          return true;
        }
      case 5003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        a(IGamesSignInCallbacks.Stub.aC(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createStringArray(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        a(IGamesSignInCallbacks.Stub.aC(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        a(IGamesSignInCallbacks.Stub.aC(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        a(IGamesSignInCallbacks.Stub.aC(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        b(IGamesSignInCallbacks.Stub.aC(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5008: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
        a(IGamesSignInCallbacks.Stub.aC(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
      w(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class Proxy
      implements IGamesSignInService
    {
      private IBinder lb;
      
      /* Error */
      public void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 24
        //   14: invokevirtual 28	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 34 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 38	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 43	com/google/android/gms/games/internal/IGamesSignInService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 5006
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 49 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 53	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 56	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 56	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 56	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 56	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesSignInCallbacks	IGamesSignInCallbacks
        //   0	98	2	paramString1	String
        //   0	98	3	paramString2	String
        //   3	89	4	localParcel1	Parcel
        //   8	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	85	finally
        //   21	28	85	finally
        //   28	69	85	finally
      }
      
      /* Error */
      public void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String paramString3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 24
        //   14: invokevirtual 28	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +69 -> 87
        //   21: aload_1
        //   22: invokeinterface 34 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 38	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 5
        //   48: aload 4
        //   50: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   53: aload_0
        //   54: getfield 43	com/google/android/gms/games/internal/IGamesSignInService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   57: sipush 5005
        //   60: aload 5
        //   62: aload 6
        //   64: iconst_0
        //   65: invokeinterface 49 5 0
        //   70: pop
        //   71: aload 6
        //   73: invokevirtual 53	android/os/Parcel:readException	()V
        //   76: aload 6
        //   78: invokevirtual 56	android/os/Parcel:recycle	()V
        //   81: aload 5
        //   83: invokevirtual 56	android/os/Parcel:recycle	()V
        //   86: return
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -61 -> 28
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 56	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 56	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramIGamesSignInCallbacks	IGamesSignInCallbacks
        //   0	105	2	paramString1	String
        //   0	105	3	paramString2	String
        //   0	105	4	paramString3	String
        //   3	96	5	localParcel1	Parcel
        //   8	86	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	92	finally
        //   21	28	92	finally
        //   28	76	92	finally
      }
      
      /* Error */
      public void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 24
        //   14: invokevirtual 28	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +76 -> 94
        //   21: aload_1
        //   22: invokeinterface 34 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 38	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: aload_2
        //   37: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 6
        //   42: aload_3
        //   43: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   53: aload 6
        //   55: aload 5
        //   57: invokevirtual 64	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   60: aload_0
        //   61: getfield 43	com/google/android/gms/games/internal/IGamesSignInService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   64: sipush 5008
        //   67: aload 6
        //   69: aload 7
        //   71: iconst_0
        //   72: invokeinterface 49 5 0
        //   77: pop
        //   78: aload 7
        //   80: invokevirtual 53	android/os/Parcel:readException	()V
        //   83: aload 7
        //   85: invokevirtual 56	android/os/Parcel:recycle	()V
        //   88: aload 6
        //   90: invokevirtual 56	android/os/Parcel:recycle	()V
        //   93: return
        //   94: aconst_null
        //   95: astore_1
        //   96: goto -68 -> 28
        //   99: astore_1
        //   100: aload 7
        //   102: invokevirtual 56	android/os/Parcel:recycle	()V
        //   105: aload 6
        //   107: invokevirtual 56	android/os/Parcel:recycle	()V
        //   110: aload_1
        //   111: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	112	0	this	Proxy
        //   0	112	1	paramIGamesSignInCallbacks	IGamesSignInCallbacks
        //   0	112	2	paramString1	String
        //   0	112	3	paramString2	String
        //   0	112	4	paramString3	String
        //   0	112	5	paramArrayOfString	String[]
        //   3	103	6	localParcel1	Parcel
        //   8	93	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	28	99	finally
        //   28	83	99	finally
      }
      
      /* Error */
      public void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String[] paramArrayOfString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 24
        //   14: invokevirtual 28	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +69 -> 87
        //   21: aload_1
        //   22: invokeinterface 34 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 38	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 5
        //   48: aload 4
        //   50: invokevirtual 64	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   53: aload_0
        //   54: getfield 43	com/google/android/gms/games/internal/IGamesSignInService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   57: sipush 5004
        //   60: aload 5
        //   62: aload 6
        //   64: iconst_0
        //   65: invokeinterface 49 5 0
        //   70: pop
        //   71: aload 6
        //   73: invokevirtual 53	android/os/Parcel:readException	()V
        //   76: aload 6
        //   78: invokevirtual 56	android/os/Parcel:recycle	()V
        //   81: aload 5
        //   83: invokevirtual 56	android/os/Parcel:recycle	()V
        //   86: return
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -61 -> 28
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 56	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 56	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramIGamesSignInCallbacks	IGamesSignInCallbacks
        //   0	105	2	paramString1	String
        //   0	105	3	paramString2	String
        //   0	105	4	paramArrayOfString	String[]
        //   3	96	5	localParcel1	Parcel
        //   8	86	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	92	finally
        //   21	28	92	finally
        //   28	76	92	finally
      }
      
      /* Error */
      public void a(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 24
        //   14: invokevirtual 28	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +76 -> 94
        //   21: aload_1
        //   22: invokeinterface 34 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 38	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: aload_2
        //   37: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 6
        //   42: aload_3
        //   43: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 64	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   53: aload 6
        //   55: aload 5
        //   57: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   60: aload_0
        //   61: getfield 43	com/google/android/gms/games/internal/IGamesSignInService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   64: sipush 5003
        //   67: aload 6
        //   69: aload 7
        //   71: iconst_0
        //   72: invokeinterface 49 5 0
        //   77: pop
        //   78: aload 7
        //   80: invokevirtual 53	android/os/Parcel:readException	()V
        //   83: aload 7
        //   85: invokevirtual 56	android/os/Parcel:recycle	()V
        //   88: aload 6
        //   90: invokevirtual 56	android/os/Parcel:recycle	()V
        //   93: return
        //   94: aconst_null
        //   95: astore_1
        //   96: goto -68 -> 28
        //   99: astore_1
        //   100: aload 7
        //   102: invokevirtual 56	android/os/Parcel:recycle	()V
        //   105: aload 6
        //   107: invokevirtual 56	android/os/Parcel:recycle	()V
        //   110: aload_1
        //   111: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	112	0	this	Proxy
        //   0	112	1	paramIGamesSignInCallbacks	IGamesSignInCallbacks
        //   0	112	2	paramString1	String
        //   0	112	3	paramString2	String
        //   0	112	4	paramArrayOfString	String[]
        //   0	112	5	paramString3	String
        //   3	103	6	localParcel1	Parcel
        //   8	93	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	28	99	finally
        //   28	83	99	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void b(IGamesSignInCallbacks paramIGamesSignInCallbacks, String paramString1, String paramString2, String paramString3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 22	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 24
        //   14: invokevirtual 28	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +69 -> 87
        //   21: aload_1
        //   22: invokeinterface 34 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 38	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 5
        //   48: aload 4
        //   50: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   53: aload_0
        //   54: getfield 43	com/google/android/gms/games/internal/IGamesSignInService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   57: sipush 5007
        //   60: aload 5
        //   62: aload 6
        //   64: iconst_0
        //   65: invokeinterface 49 5 0
        //   70: pop
        //   71: aload 6
        //   73: invokevirtual 53	android/os/Parcel:readException	()V
        //   76: aload 6
        //   78: invokevirtual 56	android/os/Parcel:recycle	()V
        //   81: aload 5
        //   83: invokevirtual 56	android/os/Parcel:recycle	()V
        //   86: return
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -61 -> 28
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 56	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 56	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramIGamesSignInCallbacks	IGamesSignInCallbacks
        //   0	105	2	paramString1	String
        //   0	105	3	paramString2	String
        //   0	105	4	paramString3	String
        //   3	96	5	localParcel1	Parcel
        //   8	86	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	92	finally
        //   21	28	92	finally
        //   28	76	92	finally
      }
      
      public String bL(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
          localParcel1.writeString(paramString);
          this.lb.transact(5001, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.readString();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String bM(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
          localParcel1.writeString(paramString);
          this.lb.transact(5002, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.readString();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String h(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
          localParcel1.writeString(paramString);
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(5009, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.readString();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void w(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.lb.transact(9001, localParcel1, localParcel2, 0);
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/IGamesSignInService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */