package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.a;
import com.google.android.gms.maps.model.g;
import com.google.android.gms.maps.model.i;

public abstract interface ICameraUpdateFactoryDelegate
  extends IInterface
{
  public abstract d newCameraPosition(CameraPosition paramCameraPosition)
    throws RemoteException;
  
  public abstract d newLatLng(LatLng paramLatLng)
    throws RemoteException;
  
  public abstract d newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt)
    throws RemoteException;
  
  public abstract d newLatLngBoundsWithSize(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
  
  public abstract d newLatLngZoom(LatLng paramLatLng, float paramFloat)
    throws RemoteException;
  
  public abstract d scrollBy(float paramFloat1, float paramFloat2)
    throws RemoteException;
  
  public abstract d zoomBy(float paramFloat)
    throws RemoteException;
  
  public abstract d zoomByWithFocus(float paramFloat, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract d zoomIn()
    throws RemoteException;
  
  public abstract d zoomOut()
    throws RemoteException;
  
  public abstract d zoomTo(float paramFloat)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ICameraUpdateFactoryDelegate
  {
    public static ICameraUpdateFactoryDelegate aN(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
      if ((localIInterface != null) && ((localIInterface instanceof ICameraUpdateFactoryDelegate))) {
        return (ICameraUpdateFactoryDelegate)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject6 = null;
      Object localObject7 = null;
      Object localObject8 = null;
      Object localObject9 = null;
      Object localObject10 = null;
      Object localObject1 = null;
      d locald = null;
      Object localObject2 = null;
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject5 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        localObject1 = zoomIn();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject5;
        if (localObject1 != null) {
          paramParcel1 = ((d)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        localObject1 = zoomOut();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject6;
        if (localObject1 != null) {
          paramParcel1 = ((d)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        localObject1 = scrollBy(paramParcel1.readFloat(), paramParcel1.readFloat());
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject7;
        if (localObject1 != null) {
          paramParcel1 = ((d)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        localObject1 = zoomTo(paramParcel1.readFloat());
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject8;
        if (localObject1 != null) {
          paramParcel1 = ((d)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        localObject1 = zoomBy(paramParcel1.readFloat());
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject9;
        if (localObject1 != null) {
          paramParcel1 = ((d)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        localObject1 = zoomByWithFocus(paramParcel1.readFloat(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject10;
        if (localObject1 != null) {
          paramParcel1 = ((d)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = CameraPosition.CREATOR.cI(paramParcel1);; paramParcel1 = null)
        {
          locald = newCameraPosition(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject1;
          if (locald != null) {
            paramParcel1 = locald.asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = LatLng.CREATOR.cM(paramParcel1);; paramParcel1 = null)
        {
          localObject1 = newLatLng(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = locald;
          if (localObject1 != null) {
            paramParcel1 = ((d)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = LatLng.CREATOR.cM(paramParcel1);; localObject1 = null)
        {
          localObject1 = newLatLngZoom((LatLng)localObject1, paramParcel1.readFloat());
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject2;
          if (localObject1 != null) {
            paramParcel1 = ((d)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = LatLngBounds.CREATOR.cL(paramParcel1);; localObject1 = null)
        {
          localObject1 = newLatLngBounds((LatLngBounds)localObject1, paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject3;
          if (localObject1 != null) {
            paramParcel1 = ((d)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
      if (paramParcel1.readInt() != 0) {}
      for (localObject1 = LatLngBounds.CREATOR.cL(paramParcel1);; localObject1 = null)
      {
        localObject1 = newLatLngBoundsWithSize((LatLngBounds)localObject1, paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject4;
        if (localObject1 != null) {
          paramParcel1 = ((d)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      }
    }
    
    private static class a
      implements ICameraUpdateFactoryDelegate
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
      public d newCameraPosition(CameraPosition paramCameraPosition)
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
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 47	com/google/android/gms/maps/model/CameraPosition:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 7
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 53 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 56	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 59	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 65	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 68	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 68	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aload_2
        //   67: iconst_0
        //   68: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   71: goto -42 -> 29
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 68	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramCameraPosition	CameraPosition
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	74	finally
        //   18	29	74	finally
        //   29	56	74	finally
        //   66	71	74	finally
      }
      
      /* Error */
      public d newLatLng(LatLng paramLatLng)
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
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 74	com/google/android/gms/maps/model/LatLng:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 8
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 53 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 56	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 59	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 65	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 68	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 68	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aload_2
        //   67: iconst_0
        //   68: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   71: goto -42 -> 29
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 68	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramLatLng	LatLng
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	74	finally
        //   18	29	74	finally
        //   29	56	74	finally
        //   66	71	74	finally
      }
      
      /* Error */
      public d newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 33
        //   12: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +60 -> 76
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 79	com/google/android/gms/maps/model/LatLngBounds:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: iload_2
        //   32: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate$a$a:lb	Landroid/os/IBinder;
        //   39: bipush 10
        //   41: aload_3
        //   42: aload 4
        //   44: iconst_0
        //   45: invokeinterface 53 5 0
        //   50: pop
        //   51: aload 4
        //   53: invokevirtual 56	android/os/Parcel:readException	()V
        //   56: aload 4
        //   58: invokevirtual 59	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   61: invokestatic 65	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   64: astore_1
        //   65: aload 4
        //   67: invokevirtual 68	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 68	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: areturn
        //   76: aload_3
        //   77: iconst_0
        //   78: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   81: goto -51 -> 30
        //   84: astore_1
        //   85: aload 4
        //   87: invokevirtual 68	android/os/Parcel:recycle	()V
        //   90: aload_3
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	a
        //   0	96	1	paramLatLngBounds	LatLngBounds
        //   0	96	2	paramInt	int
        //   3	88	3	localParcel1	Parcel
        //   7	79	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	84	finally
        //   19	30	84	finally
        //   30	65	84	finally
        //   76	81	84	finally
      }
      
      /* Error */
      public d newLatLngBoundsWithSize(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 33
        //   14: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +78 -> 96
        //   21: aload 5
        //   23: iconst_1
        //   24: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   27: aload_1
        //   28: aload 5
        //   30: iconst_0
        //   31: invokevirtual 79	com/google/android/gms/maps/model/LatLngBounds:writeToParcel	(Landroid/os/Parcel;I)V
        //   34: aload 5
        //   36: iload_2
        //   37: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   40: aload 5
        //   42: iload_3
        //   43: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   46: aload 5
        //   48: iload 4
        //   50: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   53: aload_0
        //   54: getfield 18	com/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate$a$a:lb	Landroid/os/IBinder;
        //   57: bipush 11
        //   59: aload 5
        //   61: aload 6
        //   63: iconst_0
        //   64: invokeinterface 53 5 0
        //   69: pop
        //   70: aload 6
        //   72: invokevirtual 56	android/os/Parcel:readException	()V
        //   75: aload 6
        //   77: invokevirtual 59	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   80: invokestatic 65	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   83: astore_1
        //   84: aload 6
        //   86: invokevirtual 68	android/os/Parcel:recycle	()V
        //   89: aload 5
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: areturn
        //   96: aload 5
        //   98: iconst_0
        //   99: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   102: goto -68 -> 34
        //   105: astore_1
        //   106: aload 6
        //   108: invokevirtual 68	android/os/Parcel:recycle	()V
        //   111: aload 5
        //   113: invokevirtual 68	android/os/Parcel:recycle	()V
        //   116: aload_1
        //   117: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	118	0	this	a
        //   0	118	1	paramLatLngBounds	LatLngBounds
        //   0	118	2	paramInt1	int
        //   0	118	3	paramInt2	int
        //   0	118	4	paramInt3	int
        //   3	109	5	localParcel1	Parcel
        //   8	99	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	105	finally
        //   21	34	105	finally
        //   34	84	105	finally
        //   96	102	105	finally
      }
      
      /* Error */
      public d newLatLngZoom(LatLng paramLatLng, float paramFloat)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 33
        //   12: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +60 -> 76
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 74	com/google/android/gms/maps/model/LatLng:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: fload_2
        //   32: invokevirtual 87	android/os/Parcel:writeFloat	(F)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate$a$a:lb	Landroid/os/IBinder;
        //   39: bipush 9
        //   41: aload_3
        //   42: aload 4
        //   44: iconst_0
        //   45: invokeinterface 53 5 0
        //   50: pop
        //   51: aload 4
        //   53: invokevirtual 56	android/os/Parcel:readException	()V
        //   56: aload 4
        //   58: invokevirtual 59	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   61: invokestatic 65	com/google/android/gms/dynamic/d$a:am	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/d;
        //   64: astore_1
        //   65: aload 4
        //   67: invokevirtual 68	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 68	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: areturn
        //   76: aload_3
        //   77: iconst_0
        //   78: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   81: goto -51 -> 30
        //   84: astore_1
        //   85: aload 4
        //   87: invokevirtual 68	android/os/Parcel:recycle	()V
        //   90: aload_3
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	a
        //   0	96	1	paramLatLng	LatLng
        //   0	96	2	paramFloat	float
        //   3	88	3	localParcel1	Parcel
        //   7	79	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	84	finally
        //   19	30	84	finally
        //   30	65	84	finally
        //   76	81	84	finally
      }
      
      public d scrollBy(float paramFloat1, float paramFloat2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
          localParcel1.writeFloat(paramFloat1);
          localParcel1.writeFloat(paramFloat2);
          this.lb.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          d locald = d.a.am(localParcel2.readStrongBinder());
          return locald;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public d zoomBy(float paramFloat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
          localParcel1.writeFloat(paramFloat);
          this.lb.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          d locald = d.a.am(localParcel2.readStrongBinder());
          return locald;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public d zoomByWithFocus(float paramFloat, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
          localParcel1.writeFloat(paramFloat);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.lb.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          d locald = d.a.am(localParcel2.readStrongBinder());
          return locald;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public d zoomIn()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
          this.lb.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          d locald = d.a.am(localParcel2.readStrongBinder());
          return locald;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public d zoomOut()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
          this.lb.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          d locald = d.a.am(localParcel2.readStrongBinder());
          return locald;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public d zoomTo(float paramFloat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
          localParcel1.writeFloat(paramFloat);
          this.lb.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          d locald = d.a.am(localParcel2.readStrongBinder());
          return locald;
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */