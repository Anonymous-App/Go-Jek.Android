package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.internal.a.a;

public abstract interface c
  extends IInterface
{
  public abstract IMapViewDelegate a(d paramd, GoogleMapOptions paramGoogleMapOptions)
    throws RemoteException;
  
  public abstract IStreetViewPanoramaViewDelegate a(d paramd, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
    throws RemoteException;
  
  public abstract void a(d paramd, int paramInt)
    throws RemoteException;
  
  public abstract void i(d paramd)
    throws RemoteException;
  
  public abstract IMapFragmentDelegate j(d paramd)
    throws RemoteException;
  
  public abstract IStreetViewPanoramaFragmentDelegate k(d paramd)
    throws RemoteException;
  
  public abstract ICameraUpdateFactoryDelegate mI()
    throws RemoteException;
  
  public abstract com.google.android.gms.maps.model.internal.a mJ()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements c
  {
    public static c aP(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
      if ((localIInterface != null) && ((localIInterface instanceof c))) {
        return (c)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject1 = null;
      Object localObject4 = null;
      Object localObject5 = null;
      Object localObject2 = null;
      Object localObject6 = null;
      Object localObject3 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.maps.internal.ICreator");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        i(d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = j(d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject3;
        if (localObject1 != null) {
          paramParcel1 = ((IMapFragmentDelegate)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject2 = d.a.am(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = GoogleMapOptions.CREATOR.cG(paramParcel1);; paramParcel1 = null)
        {
          localObject2 = a((d)localObject2, paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject1;
          if (localObject2 != null) {
            paramParcel1 = ((IMapViewDelegate)localObject2).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = mI();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject4;
        if (localObject1 != null) {
          paramParcel1 = ((ICameraUpdateFactoryDelegate)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = mJ();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject5;
        if (localObject1 != null) {
          paramParcel1 = ((com.google.android.gms.maps.model.internal.a)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        a(d.a.am(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
        localObject1 = d.a.am(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = StreetViewPanoramaOptions.CREATOR.cH(paramParcel1);; paramParcel1 = null)
        {
          localObject1 = a((d)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject2;
          if (localObject1 != null) {
            paramParcel1 = ((IStreetViewPanoramaViewDelegate)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICreator");
      localObject1 = k(d.a.am(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      paramParcel1 = (Parcel)localObject6;
      if (localObject1 != null) {
        paramParcel1 = ((IStreetViewPanoramaFragmentDelegate)localObject1).asBinder();
      }
      paramParcel2.writeStrongBinder(paramParcel1);
      return true;
    }
    
    private static class a
      implements c
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public IMapViewDelegate a(d paramd, GoogleMapOptions paramGoogleMapOptions)
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
        //   16: ifnull +70 -> 86
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_2
        //   32: ifnull +59 -> 91
        //   35: aload_3
        //   36: iconst_1
        //   37: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   40: aload_2
        //   41: aload_3
        //   42: iconst_0
        //   43: invokevirtual 53	com/google/android/gms/maps/GoogleMapOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   50: iconst_3
        //   51: aload_3
        //   52: aload 4
        //   54: iconst_0
        //   55: invokeinterface 59 5 0
        //   60: pop
        //   61: aload 4
        //   63: invokevirtual 62	android/os/Parcel:readException	()V
        //   66: aload 4
        //   68: invokevirtual 65	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   71: invokestatic 71	com/google/android/gms/maps/internal/IMapViewDelegate$a:aU	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/internal/IMapViewDelegate;
        //   74: astore_1
        //   75: aload 4
        //   77: invokevirtual 74	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: invokevirtual 74	android/os/Parcel:recycle	()V
        //   84: aload_1
        //   85: areturn
        //   86: aconst_null
        //   87: astore_1
        //   88: goto -62 -> 26
        //   91: aload_3
        //   92: iconst_0
        //   93: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   96: goto -50 -> 46
        //   99: astore_1
        //   100: aload 4
        //   102: invokevirtual 74	android/os/Parcel:recycle	()V
        //   105: aload_3
        //   106: invokevirtual 74	android/os/Parcel:recycle	()V
        //   109: aload_1
        //   110: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	111	0	this	a
        //   0	111	1	paramd	d
        //   0	111	2	paramGoogleMapOptions	GoogleMapOptions
        //   3	103	3	localParcel1	Parcel
        //   7	94	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	99	finally
        //   19	26	99	finally
        //   26	31	99	finally
        //   35	46	99	finally
        //   46	75	99	finally
        //   91	96	99	finally
      }
      
      /* Error */
      public IStreetViewPanoramaViewDelegate a(d paramd, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
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
        //   16: ifnull +71 -> 87
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_2
        //   32: ifnull +60 -> 92
        //   35: aload_3
        //   36: iconst_1
        //   37: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   40: aload_2
        //   41: aload_3
        //   42: iconst_0
        //   43: invokevirtual 79	com/google/android/gms/maps/StreetViewPanoramaOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   50: bipush 7
        //   52: aload_3
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 59 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 62	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 65	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   72: invokestatic 85	com/google/android/gms/maps/internal/IStreetViewPanoramaViewDelegate$a:bn	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/internal/IStreetViewPanoramaViewDelegate;
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 74	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 74	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: areturn
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -63 -> 26
        //   92: aload_3
        //   93: iconst_0
        //   94: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   97: goto -51 -> 46
        //   100: astore_1
        //   101: aload 4
        //   103: invokevirtual 74	android/os/Parcel:recycle	()V
        //   106: aload_3
        //   107: invokevirtual 74	android/os/Parcel:recycle	()V
        //   110: aload_1
        //   111: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	112	0	this	a
        //   0	112	1	paramd	d
        //   0	112	2	paramStreetViewPanoramaOptions	StreetViewPanoramaOptions
        //   3	104	3	localParcel1	Parcel
        //   7	95	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	100	finally
        //   19	26	100	finally
        //   26	31	100	finally
        //   35	46	100	finally
        //   46	76	100	finally
        //   92	97	100	finally
      }
      
      /* Error */
      public void a(d paramd, int paramInt)
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
        //   16: ifnull +51 -> 67
        //   19: aload_1
        //   20: invokeinterface 40 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: iload_2
        //   33: invokevirtual 47	android/os/Parcel:writeInt	(I)V
        //   36: aload_0
        //   37: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   40: bipush 6
        //   42: aload_3
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 59 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 62	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 74	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 74	android/os/Parcel:recycle	()V
        //   66: return
        //   67: aconst_null
        //   68: astore_1
        //   69: goto -43 -> 26
        //   72: astore_1
        //   73: aload 4
        //   75: invokevirtual 74	android/os/Parcel:recycle	()V
        //   78: aload_3
        //   79: invokevirtual 74	android/os/Parcel:recycle	()V
        //   82: aload_1
        //   83: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	84	0	this	a
        //   0	84	1	paramd	d
        //   0	84	2	paramInt	int
        //   3	76	3	localParcel1	Parcel
        //   7	67	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	72	finally
        //   19	26	72	finally
        //   26	57	72	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      /* Error */
      public void i(d paramd)
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
        //   31: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_1
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 59 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 62	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 74	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 74	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 74	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 74	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramd	d
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      /* Error */
      public IMapFragmentDelegate j(d paramd)
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
        //   15: ifnull +51 -> 66
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 59 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 62	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 65	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 96	com/google/android/gms/maps/internal/IMapFragmentDelegate$a:aT	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/internal/IMapFragmentDelegate;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 74	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 74	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aconst_null
        //   67: astore_1
        //   68: goto -43 -> 25
        //   71: astore_1
        //   72: aload_3
        //   73: invokevirtual 74	android/os/Parcel:recycle	()V
        //   76: aload_2
        //   77: invokevirtual 74	android/os/Parcel:recycle	()V
        //   80: aload_1
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	a
        //   0	82	1	paramd	d
        //   3	74	2	localParcel1	Parcel
        //   7	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	71	finally
        //   18	25	71	finally
        //   25	56	71	finally
      }
      
      /* Error */
      public IStreetViewPanoramaFragmentDelegate k(d paramd)
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
        //   15: ifnull +52 -> 67
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/c$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 8
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 59 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 62	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 65	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   53: invokestatic 104	com/google/android/gms/maps/internal/IStreetViewPanoramaFragmentDelegate$a:bm	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/internal/IStreetViewPanoramaFragmentDelegate;
        //   56: astore_1
        //   57: aload_3
        //   58: invokevirtual 74	android/os/Parcel:recycle	()V
        //   61: aload_2
        //   62: invokevirtual 74	android/os/Parcel:recycle	()V
        //   65: aload_1
        //   66: areturn
        //   67: aconst_null
        //   68: astore_1
        //   69: goto -44 -> 25
        //   72: astore_1
        //   73: aload_3
        //   74: invokevirtual 74	android/os/Parcel:recycle	()V
        //   77: aload_2
        //   78: invokevirtual 74	android/os/Parcel:recycle	()V
        //   81: aload_1
        //   82: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	83	0	this	a
        //   0	83	1	paramd	d
        //   3	75	2	localParcel1	Parcel
        //   7	67	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	72	finally
        //   18	25	72	finally
        //   25	57	72	finally
      }
      
      public ICameraUpdateFactoryDelegate mI()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
          this.lb.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          ICameraUpdateFactoryDelegate localICameraUpdateFactoryDelegate = ICameraUpdateFactoryDelegate.a.aN(localParcel2.readStrongBinder());
          return localICameraUpdateFactoryDelegate;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public com.google.android.gms.maps.model.internal.a mJ()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
          this.lb.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          com.google.android.gms.maps.model.internal.a locala = a.a.bp(localParcel2.readStrongBinder());
          return locala;
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/internal/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */