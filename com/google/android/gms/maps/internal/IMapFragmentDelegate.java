package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.a;

public abstract interface IMapFragmentDelegate
  extends IInterface
{
  public abstract IGoogleMapDelegate getMap()
    throws RemoteException;
  
  public abstract boolean isReady()
    throws RemoteException;
  
  public abstract void onCreate(Bundle paramBundle)
    throws RemoteException;
  
  public abstract d onCreateView(d paramd1, d paramd2, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onDestroy()
    throws RemoteException;
  
  public abstract void onDestroyView()
    throws RemoteException;
  
  public abstract void onInflate(d paramd, GoogleMapOptions paramGoogleMapOptions, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onLowMemory()
    throws RemoteException;
  
  public abstract void onPause()
    throws RemoteException;
  
  public abstract void onResume()
    throws RemoteException;
  
  public abstract void onSaveInstanceState(Bundle paramBundle)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements IMapFragmentDelegate
  {
    public static IMapFragmentDelegate aT(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
      if ((localIInterface != null) && ((localIInterface instanceof IMapFragmentDelegate))) {
        return (IMapFragmentDelegate)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      GoogleMapOptions localGoogleMapOptions = null;
      d locald1;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        paramParcel1 = getMap();
        paramParcel2.writeNoException();
        if (paramParcel1 != null) {}
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        locald1 = d.a.am(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0)
        {
          localGoogleMapOptions = GoogleMapOptions.CREATOR.cG(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label240;
          }
        }
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          onInflate(locald1, localGoogleMapOptions, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localGoogleMapOptions = null;
          break;
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          onCreate(paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        locald1 = d.a.am(paramParcel1.readStrongBinder());
        d locald2 = d.a.am(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          locald1 = onCreateView(locald1, locald2, paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = localGoogleMapOptions;
          if (locald1 != null) {
            paramParcel1 = locald1.asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        onResume();
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        onPause();
        paramParcel2.writeNoException();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        onDestroyView();
        paramParcel2.writeNoException();
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        onDestroy();
        paramParcel2.writeNoException();
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        onLowMemory();
        paramParcel2.writeNoException();
        return true;
      case 10: 
        label240:
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          onSaveInstanceState(paramParcel1);
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
      boolean bool = isReady();
      paramParcel2.writeNoException();
      if (bool) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
      }
    }
    
    private static class a
      implements IMapFragmentDelegate
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
      
      public IGoogleMapDelegate getMap()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
          this.lb.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          IGoogleMapDelegate localIGoogleMapDelegate = IGoogleMapDelegate.a.aQ(localParcel2.readStrongBinder());
          return localIGoogleMapDelegate;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isReady()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
          this.lb.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void onCreate(Bundle paramBundle)
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
        //   15: ifnull +41 -> 56
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 71	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 77	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IMapFragmentDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: iconst_3
        //   34: aload_2
        //   35: aload_3
        //   36: iconst_0
        //   37: invokeinterface 43 5 0
        //   42: pop
        //   43: aload_3
        //   44: invokevirtual 46	android/os/Parcel:readException	()V
        //   47: aload_3
        //   48: invokevirtual 58	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 58	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_2
        //   57: iconst_0
        //   58: invokevirtual 71	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 58	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 58	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	a
        //   0	75	1	paramBundle	Bundle
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	29	64	finally
        //   29	47	64	finally
        //   56	61	64	finally
      }
      
      /* Error */
      public d onCreateView(d paramd1, d paramd2, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +95 -> 116
        //   24: aload_1
        //   25: invokeinterface 83 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 86	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 4
        //   39: astore_1
        //   40: aload_2
        //   41: ifnull +10 -> 51
        //   44: aload_2
        //   45: invokeinterface 83 1 0
        //   50: astore_1
        //   51: aload 5
        //   53: aload_1
        //   54: invokevirtual 86	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   57: aload_3
        //   58: ifnull +63 -> 121
        //   61: aload 5
        //   63: iconst_1
        //   64: invokevirtual 71	android/os/Parcel:writeInt	(I)V
        //   67: aload_3
        //   68: aload 5
        //   70: iconst_0
        //   71: invokevirtual 77	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   74: aload_0
        //   75: getfield 18	com/google/android/gms/maps/internal/IMapFragmentDelegate$a$a:lb	Landroid/os/IBinder;
        //   78: iconst_4
        //   79: aload 5
        //   81: aload 6
        //   83: iconst_0
        //   84: invokeinterface 43 5 0
        //   89: pop
        //   90: aload 6
        //   92: invokevirtual 46	android/os/Parcel:readException	()V
        //   95: aload 6
        //   97: invokevirtual 49	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   100: invokestatic 92	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   103: astore_1
        //   104: aload 6
        //   106: invokevirtual 58	android/os/Parcel:recycle	()V
        //   109: aload 5
        //   111: invokevirtual 58	android/os/Parcel:recycle	()V
        //   114: aload_1
        //   115: areturn
        //   116: aconst_null
        //   117: astore_1
        //   118: goto -87 -> 31
        //   121: aload 5
        //   123: iconst_0
        //   124: invokevirtual 71	android/os/Parcel:writeInt	(I)V
        //   127: goto -53 -> 74
        //   130: astore_1
        //   131: aload 6
        //   133: invokevirtual 58	android/os/Parcel:recycle	()V
        //   136: aload 5
        //   138: invokevirtual 58	android/os/Parcel:recycle	()V
        //   141: aload_1
        //   142: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	143	0	this	a
        //   0	143	1	paramd1	d
        //   0	143	2	paramd2	d
        //   0	143	3	paramBundle	Bundle
        //   1	37	4	localObject	Object
        //   6	131	5	localParcel1	Parcel
        //   11	121	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	130	finally
        //   24	31	130	finally
        //   31	37	130	finally
        //   44	51	130	finally
        //   51	57	130	finally
        //   61	74	130	finally
        //   74	104	130	finally
        //   121	127	130	finally
      }
      
      public void onDestroy()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
          this.lb.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onDestroyView()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
          this.lb.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onInflate(d paramd, GoogleMapOptions paramGoogleMapOptions, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label127:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            if (paramd != null)
            {
              paramd = paramd.asBinder();
              localParcel1.writeStrongBinder(paramd);
              if (paramGoogleMapOptions != null)
              {
                localParcel1.writeInt(1);
                paramGoogleMapOptions.writeToParcel(localParcel1, 0);
                if (paramBundle == null) {
                  break label127;
                }
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                this.lb.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              paramd = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void onLowMemory()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
          this.lb.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onPause()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
          this.lb.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onResume()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
          this.lb.transact(5, localParcel1, localParcel2, 0);
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
      public void onSaveInstanceState(Bundle paramBundle)
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
        //   15: ifnull +54 -> 69
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 71	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 77	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IMapFragmentDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 10
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 43 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 46	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 65	android/os/Parcel:readInt	()I
        //   52: ifeq +8 -> 60
        //   55: aload_1
        //   56: aload_3
        //   57: invokevirtual 107	android/os/Bundle:readFromParcel	(Landroid/os/Parcel;)V
        //   60: aload_3
        //   61: invokevirtual 58	android/os/Parcel:recycle	()V
        //   64: aload_2
        //   65: invokevirtual 58	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aload_2
        //   70: iconst_0
        //   71: invokevirtual 71	android/os/Parcel:writeInt	(I)V
        //   74: goto -45 -> 29
        //   77: astore_1
        //   78: aload_3
        //   79: invokevirtual 58	android/os/Parcel:recycle	()V
        //   82: aload_2
        //   83: invokevirtual 58	android/os/Parcel:recycle	()V
        //   86: aload_1
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	a
        //   0	88	1	paramBundle	Bundle
        //   3	80	2	localParcel1	Parcel
        //   7	72	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	77	finally
        //   18	29	77	finally
        //   29	60	77	finally
        //   69	74	77	finally
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/internal/IMapFragmentDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */