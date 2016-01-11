package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.h;
import com.google.android.gms.maps.model.w;

public abstract interface IGoogleMapDelegate
  extends IInterface
{
  public abstract com.google.android.gms.maps.model.internal.b addCircle(CircleOptions paramCircleOptions)
    throws RemoteException;
  
  public abstract com.google.android.gms.maps.model.internal.c addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions)
    throws RemoteException;
  
  public abstract com.google.android.gms.maps.model.internal.f addMarker(MarkerOptions paramMarkerOptions)
    throws RemoteException;
  
  public abstract com.google.android.gms.maps.model.internal.g addPolygon(PolygonOptions paramPolygonOptions)
    throws RemoteException;
  
  public abstract IPolylineDelegate addPolyline(PolylineOptions paramPolylineOptions)
    throws RemoteException;
  
  public abstract h addTileOverlay(TileOverlayOptions paramTileOverlayOptions)
    throws RemoteException;
  
  public abstract void animateCamera(com.google.android.gms.dynamic.d paramd)
    throws RemoteException;
  
  public abstract void animateCameraWithCallback(com.google.android.gms.dynamic.d paramd, b paramb)
    throws RemoteException;
  
  public abstract void animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.d paramd, int paramInt, b paramb)
    throws RemoteException;
  
  public abstract void clear()
    throws RemoteException;
  
  public abstract CameraPosition getCameraPosition()
    throws RemoteException;
  
  public abstract com.google.android.gms.maps.model.internal.d getFocusedBuilding()
    throws RemoteException;
  
  public abstract int getMapType()
    throws RemoteException;
  
  public abstract float getMaxZoomLevel()
    throws RemoteException;
  
  public abstract float getMinZoomLevel()
    throws RemoteException;
  
  public abstract Location getMyLocation()
    throws RemoteException;
  
  public abstract IProjectionDelegate getProjection()
    throws RemoteException;
  
  public abstract IUiSettingsDelegate getUiSettings()
    throws RemoteException;
  
  public abstract boolean isBuildingsEnabled()
    throws RemoteException;
  
  public abstract boolean isIndoorEnabled()
    throws RemoteException;
  
  public abstract boolean isMyLocationEnabled()
    throws RemoteException;
  
  public abstract boolean isTrafficEnabled()
    throws RemoteException;
  
  public abstract void moveCamera(com.google.android.gms.dynamic.d paramd)
    throws RemoteException;
  
  public abstract void setBuildingsEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract boolean setIndoorEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setInfoWindowAdapter(d paramd)
    throws RemoteException;
  
  public abstract void setLocationSource(ILocationSourceDelegate paramILocationSourceDelegate)
    throws RemoteException;
  
  public abstract void setMapType(int paramInt)
    throws RemoteException;
  
  public abstract void setMyLocationEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setOnCameraChangeListener(e parame)
    throws RemoteException;
  
  public abstract void setOnIndoorStateChangeListener(f paramf)
    throws RemoteException;
  
  public abstract void setOnInfoWindowClickListener(g paramg)
    throws RemoteException;
  
  public abstract void setOnMapClickListener(i parami)
    throws RemoteException;
  
  public abstract void setOnMapLoadedCallback(j paramj)
    throws RemoteException;
  
  public abstract void setOnMapLongClickListener(k paramk)
    throws RemoteException;
  
  public abstract void setOnMarkerClickListener(l paraml)
    throws RemoteException;
  
  public abstract void setOnMarkerDragListener(m paramm)
    throws RemoteException;
  
  public abstract void setOnMyLocationButtonClickListener(n paramn)
    throws RemoteException;
  
  public abstract void setOnMyLocationChangeListener(o paramo)
    throws RemoteException;
  
  public abstract void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws RemoteException;
  
  public abstract void setTrafficEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void snapshot(s params, com.google.android.gms.dynamic.d paramd)
    throws RemoteException;
  
  public abstract void stopAnimation()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements IGoogleMapDelegate
  {
    public static IGoogleMapDelegate aQ(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
      if ((localIInterface != null) && ((localIInterface instanceof IGoogleMapDelegate))) {
        return (IGoogleMapDelegate)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool1 = false;
      int j = 0;
      int k = 0;
      int m = 0;
      boolean bool2 = false;
      int n = 0;
      boolean bool3 = false;
      int i = 0;
      IPolylineDelegate localIPolylineDelegate = null;
      Object localObject2 = null;
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject6 = null;
      Object localObject7 = null;
      Object localObject5 = null;
      Object localObject8 = null;
      Object localObject1 = null;
      float f;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        paramParcel1 = getCameraPosition();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        f = getMaxZoomLevel();
        paramParcel2.writeNoException();
        paramParcel2.writeFloat(f);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        f = getMinZoomLevel();
        paramParcel2.writeNoException();
        paramParcel2.writeFloat(f);
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        moveCamera(com.google.android.gms.dynamic.d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        animateCamera(com.google.android.gms.dynamic.d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        animateCameraWithCallback(com.google.android.gms.dynamic.d.a.am(paramParcel1.readStrongBinder()), b.a.aO(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.d.a.am(paramParcel1.readStrongBinder()), paramParcel1.readInt(), b.a.aO(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        stopAnimation();
        paramParcel2.writeNoException();
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = PolylineOptions.CREATOR.cP(paramParcel1);; paramParcel1 = null)
        {
          localIPolylineDelegate = addPolyline(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject1;
          if (localIPolylineDelegate != null) {
            paramParcel1 = localIPolylineDelegate.asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = PolygonOptions.CREATOR.cO(paramParcel1);; paramParcel1 = null)
        {
          localObject1 = addPolygon(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = localIPolylineDelegate;
          if (localObject1 != null) {
            paramParcel1 = ((com.google.android.gms.maps.model.internal.g)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = MarkerOptions.CREATOR.cN(paramParcel1);; paramParcel1 = null)
        {
          localObject1 = addMarker(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject2;
          if (localObject1 != null) {
            paramParcel1 = ((com.google.android.gms.maps.model.internal.f)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = GroundOverlayOptions.CREATOR.cK(paramParcel1);; paramParcel1 = null)
        {
          localObject1 = addGroundOverlay(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject3;
          if (localObject1 != null) {
            paramParcel1 = ((com.google.android.gms.maps.model.internal.c)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = TileOverlayOptions.CREATOR.cV(paramParcel1);; paramParcel1 = null)
        {
          localObject1 = addTileOverlay(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject4;
          if (localObject1 != null) {
            paramParcel1 = ((h)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        clear();
        paramParcel2.writeNoException();
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        paramInt1 = getMapType();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setMapType(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        bool1 = isTrafficEnabled();
        paramParcel2.writeNoException();
        paramInt1 = i;
        if (bool1) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        setTrafficEnabled(bool1);
        paramParcel2.writeNoException();
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        bool1 = isIndoorEnabled();
        paramParcel2.writeNoException();
        paramInt1 = j;
        if (bool1) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 20: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          bool1 = setIndoorEnabled(bool1);
          paramParcel2.writeNoException();
          paramInt1 = k;
          if (bool1) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      case 21: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        bool1 = isMyLocationEnabled();
        paramParcel2.writeNoException();
        paramInt1 = m;
        if (bool1) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 22: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        bool1 = bool2;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        setMyLocationEnabled(bool1);
        paramParcel2.writeNoException();
        return true;
      case 23: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        paramParcel1 = getMyLocation();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 24: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setLocationSource(ILocationSourceDelegate.a.aS(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 25: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        localObject1 = getUiSettings();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject6;
        if (localObject1 != null) {
          paramParcel1 = ((IUiSettingsDelegate)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 26: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        localObject1 = getProjection();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject7;
        if (localObject1 != null) {
          paramParcel1 = ((IProjectionDelegate)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 27: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnCameraChangeListener(e.a.aV(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 28: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnMapClickListener(i.a.aZ(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 29: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnMapLongClickListener(k.a.bb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 30: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnMarkerClickListener(l.a.bc(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 31: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnMarkerDragListener(m.a.bd(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 32: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnInfoWindowClickListener(g.a.aX(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 33: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setInfoWindowAdapter(d.a.aR(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 35: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = CircleOptions.CREATOR.cJ(paramParcel1);; paramParcel1 = null)
        {
          localObject1 = addCircle(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel1 = (Parcel)localObject5;
          if (localObject1 != null) {
            paramParcel1 = ((com.google.android.gms.maps.model.internal.b)localObject1).asBinder();
          }
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 36: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnMyLocationChangeListener(o.a.bf(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 37: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnMyLocationButtonClickListener(n.a.be(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 38: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        snapshot(s.a.bk(paramParcel1.readStrongBinder()), com.google.android.gms.dynamic.d.a.am(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 39: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setPadding(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 40: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        bool1 = isBuildingsEnabled();
        paramParcel2.writeNoException();
        paramInt1 = n;
        if (bool1) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 41: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        bool1 = bool3;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        setBuildingsEnabled(bool1);
        paramParcel2.writeNoException();
        return true;
      case 42: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        setOnMapLoadedCallback(j.a.ba(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 44: 
        paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        localObject1 = getFocusedBuilding();
        paramParcel2.writeNoException();
        paramParcel1 = (Parcel)localObject8;
        if (localObject1 != null) {
          paramParcel1 = ((com.google.android.gms.maps.model.internal.d)localObject1).asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
      setOnIndoorStateChangeListener(f.a.aW(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class a
      implements IGoogleMapDelegate
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public com.google.android.gms.maps.model.internal.b addCircle(CircleOptions paramCircleOptions)
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
        //   15: ifnull +51 -> 66
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 45	com/google/android/gms/maps/model/CircleOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 35
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 64	com/google/android/gms/maps/model/internal/b$a:bq	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/model/internal/b;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 67	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 67	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aload_2
        //   67: iconst_0
        //   68: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   71: goto -42 -> 29
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 67	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 67	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramCircleOptions	CircleOptions
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
      public com.google.android.gms.maps.model.internal.c addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions)
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
        //   15: ifnull +51 -> 66
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 73	com/google/android/gms/maps/model/GroundOverlayOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 12
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 79	com/google/android/gms/maps/model/internal/c$a:br	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/model/internal/c;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 67	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 67	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aload_2
        //   67: iconst_0
        //   68: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   71: goto -42 -> 29
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 67	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 67	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramGroundOverlayOptions	GroundOverlayOptions
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
      public com.google.android.gms.maps.model.internal.f addMarker(MarkerOptions paramMarkerOptions)
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
        //   15: ifnull +51 -> 66
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 84	com/google/android/gms/maps/model/MarkerOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 11
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 90	com/google/android/gms/maps/model/internal/f$a:bu	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/model/internal/f;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 67	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 67	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aload_2
        //   67: iconst_0
        //   68: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   71: goto -42 -> 29
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 67	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 67	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramMarkerOptions	MarkerOptions
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
      public com.google.android.gms.maps.model.internal.g addPolygon(PolygonOptions paramPolygonOptions)
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
        //   15: ifnull +51 -> 66
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 95	com/google/android/gms/maps/model/PolygonOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 10
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 101	com/google/android/gms/maps/model/internal/g$a:bv	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/model/internal/g;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 67	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 67	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aload_2
        //   67: iconst_0
        //   68: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   71: goto -42 -> 29
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 67	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 67	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramPolygonOptions	PolygonOptions
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
      public IPolylineDelegate addPolyline(PolylineOptions paramPolylineOptions)
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
        //   15: ifnull +51 -> 66
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 106	com/google/android/gms/maps/model/PolylineOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 9
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 112	com/google/android/gms/maps/model/internal/IPolylineDelegate$a:bw	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/model/internal/IPolylineDelegate;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 67	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 67	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aload_2
        //   67: iconst_0
        //   68: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   71: goto -42 -> 29
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 67	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 67	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramPolylineOptions	PolylineOptions
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
      public h addTileOverlay(TileOverlayOptions paramTileOverlayOptions)
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
        //   15: ifnull +51 -> 66
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 117	com/google/android/gms/maps/model/TileOverlayOptions:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 13
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   52: invokestatic 123	com/google/android/gms/maps/model/internal/h$a:bx	(Landroid/os/IBinder;)Lcom/google/android/gms/maps/model/internal/h;
        //   55: astore_1
        //   56: aload_3
        //   57: invokevirtual 67	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 67	android/os/Parcel:recycle	()V
        //   64: aload_1
        //   65: areturn
        //   66: aload_2
        //   67: iconst_0
        //   68: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   71: goto -42 -> 29
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 67	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 67	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramTileOverlayOptions	TileOverlayOptions
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
      public void animateCamera(com.google.android.gms.dynamic.d paramd)
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
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 130 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_5
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 67	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 67	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 67	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 67	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramd	com.google.android.gms.dynamic.d
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      /* Error */
      public void animateCameraWithCallback(com.google.android.gms.dynamic.d paramd, b paramb)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_3
        //   2: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 31
        //   16: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +68 -> 88
        //   23: aload_1
        //   24: invokeinterface 130 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_3
        //   37: astore_1
        //   38: aload_2
        //   39: ifnull +10 -> 49
        //   42: aload_2
        //   43: invokeinterface 138 1 0
        //   48: astore_1
        //   49: aload 4
        //   51: aload_1
        //   52: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   55: aload_0
        //   56: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   59: bipush 6
        //   61: aload 4
        //   63: aload 5
        //   65: iconst_0
        //   66: invokeinterface 51 5 0
        //   71: pop
        //   72: aload 5
        //   74: invokevirtual 54	android/os/Parcel:readException	()V
        //   77: aload 5
        //   79: invokevirtual 67	android/os/Parcel:recycle	()V
        //   82: aload 4
        //   84: invokevirtual 67	android/os/Parcel:recycle	()V
        //   87: return
        //   88: aconst_null
        //   89: astore_1
        //   90: goto -60 -> 30
        //   93: astore_1
        //   94: aload 5
        //   96: invokevirtual 67	android/os/Parcel:recycle	()V
        //   99: aload 4
        //   101: invokevirtual 67	android/os/Parcel:recycle	()V
        //   104: aload_1
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	a
        //   0	106	1	paramd	com.google.android.gms.dynamic.d
        //   0	106	2	paramb	b
        //   1	36	3	localObject	Object
        //   5	95	4	localParcel1	Parcel
        //   10	85	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	93	finally
        //   23	30	93	finally
        //   30	36	93	finally
        //   42	49	93	finally
        //   49	77	93	finally
      }
      
      /* Error */
      public void animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.d paramd, int paramInt, b paramb)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 31
        //   17: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +75 -> 96
        //   24: aload_1
        //   25: invokeinterface 130 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 5
        //   39: iload_2
        //   40: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   43: aload 4
        //   45: astore_1
        //   46: aload_3
        //   47: ifnull +10 -> 57
        //   50: aload_3
        //   51: invokeinterface 138 1 0
        //   56: astore_1
        //   57: aload 5
        //   59: aload_1
        //   60: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   63: aload_0
        //   64: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   67: bipush 7
        //   69: aload 5
        //   71: aload 6
        //   73: iconst_0
        //   74: invokeinterface 51 5 0
        //   79: pop
        //   80: aload 6
        //   82: invokevirtual 54	android/os/Parcel:readException	()V
        //   85: aload 6
        //   87: invokevirtual 67	android/os/Parcel:recycle	()V
        //   90: aload 5
        //   92: invokevirtual 67	android/os/Parcel:recycle	()V
        //   95: return
        //   96: aconst_null
        //   97: astore_1
        //   98: goto -67 -> 31
        //   101: astore_1
        //   102: aload 6
        //   104: invokevirtual 67	android/os/Parcel:recycle	()V
        //   107: aload 5
        //   109: invokevirtual 67	android/os/Parcel:recycle	()V
        //   112: aload_1
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	a
        //   0	114	1	paramd	com.google.android.gms.dynamic.d
        //   0	114	2	paramInt	int
        //   0	114	3	paramb	b
        //   1	43	4	localObject	Object
        //   6	102	5	localParcel1	Parcel
        //   11	92	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	101	finally
        //   24	31	101	finally
        //   31	43	101	finally
        //   50	57	101	finally
        //   57	85	101	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void clear()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(14, localParcel1, localParcel2, 0);
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
      public CameraPosition getCameraPosition()
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
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   18: iconst_1
        //   19: aload_2
        //   20: aload_3
        //   21: iconst_0
        //   22: invokeinterface 51 5 0
        //   27: pop
        //   28: aload_3
        //   29: invokevirtual 54	android/os/Parcel:readException	()V
        //   32: aload_3
        //   33: invokevirtual 147	android/os/Parcel:readInt	()I
        //   36: ifeq +21 -> 57
        //   39: getstatic 153	com/google/android/gms/maps/model/CameraPosition:CREATOR	Lcom/google/android/gms/maps/model/a;
        //   42: aload_3
        //   43: invokevirtual 159	com/google/android/gms/maps/model/a:cI	(Landroid/os/Parcel;)Lcom/google/android/gms/maps/model/CameraPosition;
        //   46: astore_1
        //   47: aload_3
        //   48: invokevirtual 67	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 67	android/os/Parcel:recycle	()V
        //   55: aload_1
        //   56: areturn
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -12 -> 47
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 67	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 67	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   46	13	1	localCameraPosition	CameraPosition
        //   62	10	1	localObject	Object
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	47	62	finally
      }
      
      public com.google.android.gms.maps.model.internal.d getFocusedBuilding()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(44, localParcel1, localParcel2, 0);
          localParcel2.readException();
          com.google.android.gms.maps.model.internal.d locald = com.google.android.gms.maps.model.internal.d.a.bs(localParcel2.readStrongBinder());
          return locald;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int getMapType()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(15, localParcel1, localParcel2, 0);
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
      
      public float getMaxZoomLevel()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          float f = localParcel2.readFloat();
          return f;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public float getMinZoomLevel()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          float f = localParcel2.readFloat();
          return f;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Location getMyLocation()
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
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   18: bipush 23
        //   20: aload_2
        //   21: aload_3
        //   22: iconst_0
        //   23: invokeinterface 51 5 0
        //   28: pop
        //   29: aload_3
        //   30: invokevirtual 54	android/os/Parcel:readException	()V
        //   33: aload_3
        //   34: invokevirtual 147	android/os/Parcel:readInt	()I
        //   37: ifeq +26 -> 63
        //   40: getstatic 181	android/location/Location:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_3
        //   44: invokeinterface 187 2 0
        //   49: checkcast 178	android/location/Location
        //   52: astore_1
        //   53: aload_3
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: aload_2
        //   58: invokevirtual 67	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_3
        //   70: invokevirtual 67	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 67	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   52	13	1	localLocation	Location
        //   68	10	1	localObject	Object
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      public IProjectionDelegate getProjection()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(26, localParcel1, localParcel2, 0);
          localParcel2.readException();
          IProjectionDelegate localIProjectionDelegate = IProjectionDelegate.a.bj(localParcel2.readStrongBinder());
          return localIProjectionDelegate;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public IUiSettingsDelegate getUiSettings()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(25, localParcel1, localParcel2, 0);
          localParcel2.readException();
          IUiSettingsDelegate localIUiSettingsDelegate = IUiSettingsDelegate.a.bo(localParcel2.readStrongBinder());
          return localIUiSettingsDelegate;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isBuildingsEnabled()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(40, localParcel1, localParcel2, 0);
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
      
      public boolean isIndoorEnabled()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(19, localParcel1, localParcel2, 0);
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
      
      public boolean isMyLocationEnabled()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(21, localParcel1, localParcel2, 0);
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
      
      public boolean isTrafficEnabled()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          this.lb.transact(17, localParcel1, localParcel2, 0);
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
      public void moveCamera(com.google.android.gms.dynamic.d paramd)
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
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 130 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: iconst_4
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 67	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 67	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 67	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 67	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	a
        //   0	73	1	paramd	com.google.android.gms.dynamic.d
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      public void setBuildingsEnabled(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(41, localParcel1, localParcel2, 0);
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
      public boolean setIndoorEnabled(boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 31
        //   16: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: iload_1
        //   20: ifeq +57 -> 77
        //   23: iconst_1
        //   24: istore_2
        //   25: aload 4
        //   27: iload_2
        //   28: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   31: aload_0
        //   32: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   35: bipush 20
        //   37: aload 4
        //   39: aload 5
        //   41: iconst_0
        //   42: invokeinterface 51 5 0
        //   47: pop
        //   48: aload 5
        //   50: invokevirtual 54	android/os/Parcel:readException	()V
        //   53: aload 5
        //   55: invokevirtual 147	android/os/Parcel:readInt	()I
        //   58: istore_2
        //   59: iload_2
        //   60: ifeq +22 -> 82
        //   63: iload_3
        //   64: istore_1
        //   65: aload 5
        //   67: invokevirtual 67	android/os/Parcel:recycle	()V
        //   70: aload 4
        //   72: invokevirtual 67	android/os/Parcel:recycle	()V
        //   75: iload_1
        //   76: ireturn
        //   77: iconst_0
        //   78: istore_2
        //   79: goto -54 -> 25
        //   82: iconst_0
        //   83: istore_1
        //   84: goto -19 -> 65
        //   87: astore 6
        //   89: aload 5
        //   91: invokevirtual 67	android/os/Parcel:recycle	()V
        //   94: aload 4
        //   96: invokevirtual 67	android/os/Parcel:recycle	()V
        //   99: aload 6
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	a
        //   0	102	1	paramBoolean	boolean
        //   24	55	2	i	int
        //   1	63	3	bool	boolean
        //   5	90	4	localParcel1	Parcel
        //   10	80	5	localParcel2	Parcel
        //   87	13	6	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   25	59	87	finally
      }
      
      /* Error */
      public void setInfoWindowAdapter(d paramd)
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
        //   18: aload_1
        //   19: invokeinterface 218 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 33
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramd	d
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setLocationSource(ILocationSourceDelegate paramILocationSourceDelegate)
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
        //   18: aload_1
        //   19: invokeinterface 223 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 24
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramILocationSourceDelegate	ILocationSourceDelegate
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      public void setMapType(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          localParcel1.writeInt(paramInt);
          this.lb.transact(16, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setMyLocationEnabled(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(22, localParcel1, localParcel2, 0);
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
      public void setOnCameraChangeListener(e parame)
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
        //   18: aload_1
        //   19: invokeinterface 230 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 27
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	parame	e
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnIndoorStateChangeListener(f paramf)
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
        //   18: aload_1
        //   19: invokeinterface 235 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 45
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramf	f
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnInfoWindowClickListener(g paramg)
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
        //   18: aload_1
        //   19: invokeinterface 240 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 32
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramg	g
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnMapClickListener(i parami)
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
        //   18: aload_1
        //   19: invokeinterface 245 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 28
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	parami	i
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnMapLoadedCallback(j paramj)
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
        //   18: aload_1
        //   19: invokeinterface 250 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 42
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramj	j
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnMapLongClickListener(k paramk)
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
        //   18: aload_1
        //   19: invokeinterface 255 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 29
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramk	k
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnMarkerClickListener(l paraml)
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
        //   18: aload_1
        //   19: invokeinterface 260 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 30
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paraml	l
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnMarkerDragListener(m paramm)
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
        //   18: aload_1
        //   19: invokeinterface 265 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 31
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramm	m
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnMyLocationButtonClickListener(n paramn)
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
        //   18: aload_1
        //   19: invokeinterface 270 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 37
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramn	n
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void setOnMyLocationChangeListener(o paramo)
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
        //   18: aload_1
        //   19: invokeinterface 275 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 36
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 51 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 54	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 67	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 67	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 67	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 67	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramo	o
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeInt(paramInt4);
          this.lb.transact(39, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setTrafficEnabled(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(18, localParcel1, localParcel2, 0);
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
      public void snapshot(s params, com.google.android.gms.dynamic.d paramd)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_3
        //   2: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 31
        //   16: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +68 -> 88
        //   23: aload_1
        //   24: invokeinterface 283 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_3
        //   37: astore_1
        //   38: aload_2
        //   39: ifnull +10 -> 49
        //   42: aload_2
        //   43: invokeinterface 130 1 0
        //   48: astore_1
        //   49: aload 4
        //   51: aload_1
        //   52: invokevirtual 133	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   55: aload_0
        //   56: getfield 18	com/google/android/gms/maps/internal/IGoogleMapDelegate$a$a:lb	Landroid/os/IBinder;
        //   59: bipush 38
        //   61: aload 4
        //   63: aload 5
        //   65: iconst_0
        //   66: invokeinterface 51 5 0
        //   71: pop
        //   72: aload 5
        //   74: invokevirtual 54	android/os/Parcel:readException	()V
        //   77: aload 5
        //   79: invokevirtual 67	android/os/Parcel:recycle	()V
        //   82: aload 4
        //   84: invokevirtual 67	android/os/Parcel:recycle	()V
        //   87: return
        //   88: aconst_null
        //   89: astore_1
        //   90: goto -60 -> 30
        //   93: astore_1
        //   94: aload 5
        //   96: invokevirtual 67	android/os/Parcel:recycle	()V
        //   99: aload 4
        //   101: invokevirtual 67	android/os/Parcel:recycle	()V
        //   104: aload_1
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	a
        //   0	106	1	params	s
        //   0	106	2	paramd	com.google.android.gms.dynamic.d
        //   1	36	3	localObject	Object
        //   5	95	4	localParcel1	Parcel
        //   10	85	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	93	finally
        //   23	30	93	finally
        //   30	36	93	finally
        //   42	49	93	finally
        //   49	77	93	finally
      }
      
      public void stopAnimation()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
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
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/internal/IGoogleMapDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */