package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.aa;
import com.google.android.gms.fitness.request.ad;
import com.google.android.gms.fitness.request.af;
import com.google.android.gms.fitness.request.ah;
import com.google.android.gms.fitness.request.aj;
import com.google.android.gms.fitness.request.b;
import com.google.android.gms.fitness.request.e;
import com.google.android.gms.fitness.request.j;
import com.google.android.gms.fitness.request.m;
import com.google.android.gms.fitness.request.o;
import com.google.android.gms.fitness.request.q;
import com.google.android.gms.fitness.request.u;
import com.google.android.gms.fitness.request.w;
import com.google.android.gms.fitness.request.y;

public abstract interface kp
  extends IInterface
{
  public abstract void a(DataDeleteRequest paramDataDeleteRequest, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(DataReadRequest paramDataReadRequest, km paramkm, String paramString)
    throws RemoteException;
  
  public abstract void a(DataSourcesRequest paramDataSourcesRequest, kn paramkn, String paramString)
    throws RemoteException;
  
  public abstract void a(DataTypeCreateRequest paramDataTypeCreateRequest, ko paramko, String paramString)
    throws RemoteException;
  
  public abstract void a(SessionInsertRequest paramSessionInsertRequest, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(SessionReadRequest paramSessionReadRequest, kr paramkr, String paramString)
    throws RemoteException;
  
  public abstract void a(StartBleScanRequest paramStartBleScanRequest, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(aa paramaa, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(ad paramad, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(af paramaf, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(ah paramah, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(aj paramaj, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(b paramb, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(e parame, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(j paramj, ko paramko, String paramString)
    throws RemoteException;
  
  public abstract void a(m paramm, kq paramkq, String paramString)
    throws RemoteException;
  
  public abstract void a(o paramo, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(q paramq, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(u paramu, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(w paramw, kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(y paramy, ks paramks, String paramString)
    throws RemoteException;
  
  public abstract void a(kt paramkt, String paramString)
    throws RemoteException;
  
  public abstract void a(lf paramlf, String paramString)
    throws RemoteException;
  
  public abstract void b(kt paramkt, String paramString)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements kp
  {
    public static kp as(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
      if ((localIInterface != null) && ((localIInterface instanceof kp))) {
        return (kp)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject2 = null;
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject5 = null;
      Object localObject6 = null;
      Object localObject7 = null;
      Object localObject8 = null;
      Object localObject9 = null;
      Object localObject10 = null;
      Object localObject11 = null;
      Object localObject12 = null;
      Object localObject13 = null;
      Object localObject14 = null;
      Object localObject15 = null;
      Object localObject16 = null;
      Object localObject17 = null;
      Object localObject18 = null;
      Object localObject19 = null;
      Object localObject20 = null;
      Object localObject21 = null;
      Object localObject1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject1;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (DataSourcesRequest)DataSourcesRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kn.a.aq(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject2;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (o)o.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject3;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (q)q.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject4;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (af)af.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject5;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (aj)aj.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject6;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (m)m.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kq.a.at(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject7;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (e)e.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        localObject1 = localObject8;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (DataDeleteRequest)DataDeleteRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((DataDeleteRequest)localObject1, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject9;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (DataTypeCreateRequest)DataTypeCreateRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, ko.a.ar(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject10;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (j)j.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, ko.a.ar(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject11;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (DataReadRequest)DataReadRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, km.a.ap(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject12;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (SessionInsertRequest)SessionInsertRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject13;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (SessionReadRequest)SessionReadRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kr.a.au(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject14;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (w)w.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject15;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (y)y.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, ks.a.av(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject16;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (StartBleScanRequest)StartBleScanRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject17;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (ad)ad.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject18;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (b)b.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject19;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (ah)ah.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 20: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject20;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (u)u.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 21: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        paramParcel2 = (Parcel)localObject21;
        if (paramParcel1.readInt() != 0) {
          paramParcel2 = (aa)aa.CREATOR.createFromParcel(paramParcel1);
        }
        a(paramParcel2, kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 22: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        a(kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      case 23: 
        paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        b(kt.a.aw(paramParcel1.readStrongBinder()), paramParcel1.readString());
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
      a(lf.a.ax(paramParcel1.readStrongBinder()), paramParcel1.readString());
      return true;
    }
    
    private static class a
      implements kp
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void a(DataDeleteRequest paramDataDeleteRequest, kt paramkt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
            if (paramDataDeleteRequest != null)
            {
              localParcel1.writeInt(1);
              paramDataDeleteRequest.writeToParcel(localParcel1, 0);
              if (paramkt != null)
              {
                paramDataDeleteRequest = paramkt.asBinder();
                localParcel1.writeStrongBinder(paramDataDeleteRequest);
                localParcel1.writeString(paramString);
                this.lb.transact(19, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramDataDeleteRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void a(DataReadRequest paramDataReadRequest, km paramkm, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 73	com/google/android/gms/fitness/request/DataReadRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 76 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 8
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramDataReadRequest	DataReadRequest
        //   0	97	2	paramkm	km
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(DataSourcesRequest paramDataSourcesRequest, kn paramkn, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +63 -> 79
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 80	com/google/android/gms/fitness/request/DataSourcesRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 83 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: iconst_1
        //   63: aload 5
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 62 5 0
        //   72: pop
        //   73: aload 5
        //   75: invokevirtual 68	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 5
        //   81: iconst_0
        //   82: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   85: goto -53 -> 32
        //   88: astore_1
        //   89: aload 5
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	a
        //   0	96	1	paramDataSourcesRequest	DataSourcesRequest
        //   0	96	2	paramkn	kn
        //   0	96	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	84	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	88	finally
        //   19	32	88	finally
        //   39	46	88	finally
        //   46	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(DataTypeCreateRequest paramDataTypeCreateRequest, ko paramko, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 87	com/google/android/gms/fitness/request/DataTypeCreateRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 90 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 13
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramDataTypeCreateRequest	DataTypeCreateRequest
        //   0	97	2	paramko	ko
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(SessionInsertRequest paramSessionInsertRequest, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 94	com/google/android/gms/fitness/request/SessionInsertRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 9
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramSessionInsertRequest	SessionInsertRequest
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(SessionReadRequest paramSessionReadRequest, kr paramkr, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 98	com/google/android/gms/fitness/request/SessionReadRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 101 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 10
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramSessionReadRequest	SessionReadRequest
        //   0	97	2	paramkr	kr
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(StartBleScanRequest paramStartBleScanRequest, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 105	com/google/android/gms/fitness/request/StartBleScanRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 15
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramStartBleScanRequest	StartBleScanRequest
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(aa paramaa, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 109	com/google/android/gms/fitness/request/aa:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 21
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramaa	aa
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(ad paramad, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 113	com/google/android/gms/fitness/request/ad:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 16
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramad	ad
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(af paramaf, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +63 -> 79
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 117	com/google/android/gms/fitness/request/af:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: iconst_4
        //   63: aload 5
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 62 5 0
        //   72: pop
        //   73: aload 5
        //   75: invokevirtual 68	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 5
        //   81: iconst_0
        //   82: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   85: goto -53 -> 32
        //   88: astore_1
        //   89: aload 5
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	a
        //   0	96	1	paramaf	af
        //   0	96	2	paramkt	kt
        //   0	96	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	84	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	88	finally
        //   19	32	88	finally
        //   39	46	88	finally
        //   46	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(ah paramah, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 121	com/google/android/gms/fitness/request/ah:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 18
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramah	ah
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(aj paramaj, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +63 -> 79
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 125	com/google/android/gms/fitness/request/aj:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: iconst_5
        //   63: aload 5
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 62 5 0
        //   72: pop
        //   73: aload 5
        //   75: invokevirtual 68	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 5
        //   81: iconst_0
        //   82: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   85: goto -53 -> 32
        //   88: astore_1
        //   89: aload 5
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	a
        //   0	96	1	paramaj	aj
        //   0	96	2	paramkt	kt
        //   0	96	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	84	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	88	finally
        //   19	32	88	finally
        //   39	46	88	finally
        //   46	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(b paramb, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 129	com/google/android/gms/fitness/request/b:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 17
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramb	b
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(e parame, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 133	com/google/android/gms/fitness/request/e:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 7
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	parame	e
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(j paramj, ko paramko, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 137	com/google/android/gms/fitness/request/j:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 90 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 14
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramj	j
        //   0	97	2	paramko	ko
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(m paramm, kq paramkq, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 141	com/google/android/gms/fitness/request/m:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 144 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 6
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramm	m
        //   0	97	2	paramkq	kq
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(o paramo, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +63 -> 79
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 148	com/google/android/gms/fitness/request/o:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: iconst_2
        //   63: aload 5
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 62 5 0
        //   72: pop
        //   73: aload 5
        //   75: invokevirtual 68	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 5
        //   81: iconst_0
        //   82: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   85: goto -53 -> 32
        //   88: astore_1
        //   89: aload 5
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	a
        //   0	96	1	paramo	o
        //   0	96	2	paramkt	kt
        //   0	96	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	84	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	88	finally
        //   19	32	88	finally
        //   39	46	88	finally
        //   46	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(q paramq, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +63 -> 79
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 152	com/google/android/gms/fitness/request/q:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: iconst_3
        //   63: aload 5
        //   65: aconst_null
        //   66: iconst_1
        //   67: invokeinterface 62 5 0
        //   72: pop
        //   73: aload 5
        //   75: invokevirtual 68	android/os/Parcel:recycle	()V
        //   78: return
        //   79: aload 5
        //   81: iconst_0
        //   82: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   85: goto -53 -> 32
        //   88: astore_1
        //   89: aload 5
        //   91: invokevirtual 68	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	a
        //   0	96	1	paramq	q
        //   0	96	2	paramkt	kt
        //   0	96	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	84	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	88	finally
        //   19	32	88	finally
        //   39	46	88	finally
        //   46	73	88	finally
        //   79	85	88	finally
      }
      
      /* Error */
      public void a(u paramu, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 156	com/google/android/gms/fitness/request/u:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 20
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramu	u
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(w paramw, kt paramkt, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 160	com/google/android/gms/fitness/request/w:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 50 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 11
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramw	w
        //   0	97	2	paramkt	kt
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      /* Error */
      public void a(y paramy, ks paramks, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 30
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +64 -> 80
        //   19: aload 5
        //   21: iconst_1
        //   22: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   25: aload_1
        //   26: aload 5
        //   28: iconst_0
        //   29: invokevirtual 164	com/google/android/gms/fitness/request/y:writeToParcel	(Landroid/os/Parcel;I)V
        //   32: aload 4
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 167 1 0
        //   45: astore_1
        //   46: aload 5
        //   48: aload_1
        //   49: invokevirtual 53	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 5
        //   54: aload_3
        //   55: invokevirtual 56	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload_0
        //   59: getfield 18	com/google/android/gms/internal/kp$a$a:lb	Landroid/os/IBinder;
        //   62: bipush 12
        //   64: aload 5
        //   66: aconst_null
        //   67: iconst_1
        //   68: invokeinterface 62 5 0
        //   73: pop
        //   74: aload 5
        //   76: invokevirtual 68	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 5
        //   82: iconst_0
        //   83: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   86: goto -54 -> 32
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 68	android/os/Parcel:recycle	()V
        //   95: aload_1
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	a
        //   0	97	1	paramy	y
        //   0	97	2	paramks	ks
        //   0	97	3	paramString	String
        //   1	32	4	localObject	Object
        //   6	85	5	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	89	finally
        //   19	32	89	finally
        //   39	46	89	finally
        //   46	74	89	finally
        //   80	86	89	finally
      }
      
      public void a(kt paramkt, String paramString)
        throws RemoteException
      {
        IBinder localIBinder = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
          if (paramkt != null) {
            localIBinder = paramkt.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          localParcel.writeString(paramString);
          this.lb.transact(22, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void a(lf paramlf, String paramString)
        throws RemoteException
      {
        IBinder localIBinder = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
          if (paramlf != null) {
            localIBinder = paramlf.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          localParcel.writeString(paramString);
          this.lb.transact(24, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void b(kt paramkt, String paramString)
        throws RemoteException
      {
        IBinder localIBinder = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
          if (paramkt != null) {
            localIBinder = paramkt.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          localParcel.writeString(paramString);
          this.lb.transact(23, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */