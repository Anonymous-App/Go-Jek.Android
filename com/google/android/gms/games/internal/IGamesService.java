package com.google.android.gms.games.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.achievement.AchievementEntity;
import com.google.android.gms.games.achievement.AchievementEntityCreator;
import com.google.android.gms.games.internal.multiplayer.InvitationClusterCreator;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.internal.request.GameRequestClusterCreator;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataChangeCreator;

public abstract interface IGamesService
  extends IInterface
{
  public abstract void N(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void O(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void P(boolean paramBoolean)
    throws RemoteException;
  
  public abstract int a(IGamesCallbacks paramIGamesCallbacks, byte[] paramArrayOfByte, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract Intent a(int paramInt1, int paramInt2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract Intent a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, String paramString)
    throws RemoteException;
  
  public abstract Intent a(AchievementEntity paramAchievementEntity)
    throws RemoteException;
  
  public abstract Intent a(ZInvitationCluster paramZInvitationCluster, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract Intent a(GameRequestCluster paramGameRequestCluster, String paramString)
    throws RemoteException;
  
  public abstract Intent a(RoomEntity paramRoomEntity, int paramInt)
    throws RemoteException;
  
  public abstract Intent a(String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
    throws RemoteException;
  
  public abstract Intent a(ParticipantEntity[] paramArrayOfParticipantEntity, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2)
    throws RemoteException;
  
  public abstract void a(long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void a(IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(Contents paramContents)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, String[] paramArrayOfString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int paramInt, int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, long paramLong)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, Bundle paramBundle, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, IBinder paramIBinder, int paramInt, String[] paramArrayOfString, Bundle paramBundle, boolean paramBoolean, long paramLong)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, IBinder paramIBinder, String paramString, boolean paramBoolean, long paramLong)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, long paramLong)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, long paramLong, String paramString2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, SnapshotMetadataChange paramSnapshotMetadataChange, Contents paramContents)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, SnapshotMetadataChange paramSnapshotMetadataChange, Contents paramContents)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult[] paramArrayOfParticipantResult)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, byte[] paramArrayOfByte, ParticipantResult[] paramArrayOfParticipantResult)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String paramString, String[] paramArrayOfString, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void a(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract int b(byte[] paramArrayOfByte, String paramString, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract Intent b(int paramInt1, int paramInt2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract Intent b(int[] paramArrayOfInt)
    throws RemoteException;
  
  public abstract void b(long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, long paramLong)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void b(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void b(String paramString1, String paramString2, int paramInt)
    throws RemoteException;
  
  public abstract Intent bC(String paramString)
    throws RemoteException;
  
  public abstract String bE(String paramString)
    throws RemoteException;
  
  public abstract String bF(String paramString)
    throws RemoteException;
  
  public abstract void bG(String paramString)
    throws RemoteException;
  
  public abstract int bH(String paramString)
    throws RemoteException;
  
  public abstract Uri bI(String paramString)
    throws RemoteException;
  
  public abstract void bJ(String paramString)
    throws RemoteException;
  
  public abstract ParcelFileDescriptor bK(String paramString)
    throws RemoteException;
  
  public abstract Intent bx(String paramString)
    throws RemoteException;
  
  public abstract void c(long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, long paramLong)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void c(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void c(String paramString1, String paramString2, int paramInt)
    throws RemoteException;
  
  public abstract void d(long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, long paramLong)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void d(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void dC(int paramInt)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void e(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void f(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract Bundle fC()
    throws RemoteException;
  
  public abstract void g(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void g(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void g(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract ParcelFileDescriptor h(Uri paramUri)
    throws RemoteException;
  
  public abstract RoomEntity h(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void h(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void h(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void i(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void i(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void j(IGamesCallbacks paramIGamesCallbacks)
    throws RemoteException;
  
  public abstract void j(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void k(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract boolean kA()
    throws RemoteException;
  
  public abstract DataHolder kB()
    throws RemoteException;
  
  public abstract void kC()
    throws RemoteException;
  
  public abstract Intent kD()
    throws RemoteException;
  
  public abstract void kE()
    throws RemoteException;
  
  public abstract boolean kF()
    throws RemoteException;
  
  public abstract String ka()
    throws RemoteException;
  
  public abstract String kb()
    throws RemoteException;
  
  public abstract Intent ke()
    throws RemoteException;
  
  public abstract Intent kf()
    throws RemoteException;
  
  public abstract Intent kg()
    throws RemoteException;
  
  public abstract Intent kh()
    throws RemoteException;
  
  public abstract Intent km()
    throws RemoteException;
  
  public abstract Intent kn()
    throws RemoteException;
  
  public abstract int ko()
    throws RemoteException;
  
  public abstract String kp()
    throws RemoteException;
  
  public abstract int kq()
    throws RemoteException;
  
  public abstract Intent kr()
    throws RemoteException;
  
  public abstract int ks()
    throws RemoteException;
  
  public abstract int kt()
    throws RemoteException;
  
  public abstract int ku()
    throws RemoteException;
  
  public abstract int kv()
    throws RemoteException;
  
  public abstract void kx()
    throws RemoteException;
  
  public abstract DataHolder kz()
    throws RemoteException;
  
  public abstract void l(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void m(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void n(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void n(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void o(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void o(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void p(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void p(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void q(long paramLong)
    throws RemoteException;
  
  public abstract void q(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void r(long paramLong)
    throws RemoteException;
  
  public abstract void r(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void r(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void s(long paramLong)
    throws RemoteException;
  
  public abstract void s(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void s(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void t(long paramLong)
    throws RemoteException;
  
  public abstract void t(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void u(long paramLong)
    throws RemoteException;
  
  public abstract void u(IGamesCallbacks paramIGamesCallbacks, String paramString)
    throws RemoteException;
  
  public abstract void u(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void v(String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGamesService
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.games.internal.IGamesService");
    }
    
    public static IGamesService aB(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
      if ((localIInterface != null) && ((localIInterface instanceof IGamesService))) {
        return (IGamesService)localIInterface;
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
      boolean bool1 = false;
      boolean bool8 = false;
      boolean bool9 = false;
      boolean bool3 = false;
      boolean bool5 = false;
      boolean bool10 = false;
      int i = 0;
      boolean bool11 = false;
      boolean bool12 = false;
      boolean bool13 = false;
      boolean bool14 = false;
      boolean bool6 = false;
      boolean bool7 = false;
      boolean bool4 = false;
      boolean bool15 = false;
      boolean bool16 = false;
      boolean bool17 = false;
      boolean bool18 = false;
      boolean bool19 = false;
      boolean bool20 = false;
      boolean bool21 = false;
      boolean bool22 = false;
      boolean bool23 = false;
      boolean bool24 = false;
      boolean bool25 = false;
      int j = 0;
      boolean bool26 = false;
      boolean bool27 = false;
      boolean bool28 = false;
      boolean bool29 = false;
      boolean bool30 = false;
      boolean bool31 = false;
      boolean bool32 = false;
      boolean bool33 = false;
      boolean bool34 = false;
      boolean bool2 = false;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesService");
        return true;
      case 5001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        q(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kp();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 5004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = fC();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((IBinder)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        kx();
        paramParcel2.writeNoException();
        return true;
      case 5007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = ka();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 5064: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = bE(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 5065: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        u(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5012: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kb();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 5013: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kz();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5014: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5015: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          }
          a((IGamesCallbacks)localObject1, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
        }
      case 5016: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5017: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5018: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5019: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        i = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          a((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, paramInt2, i, bool1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5020: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        i = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          b((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, paramInt2, i, bool1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5021: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject2 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          a((IGamesCallbacks)localObject2, (Bundle)localObject1, paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        }
      case 5022: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5023: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((IGamesCallbacks)localObject1, (String)localObject2, (IBinder)localObject3, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5024: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          b((IGamesCallbacks)localObject1, (String)localObject2, (IBinder)localObject3, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5025: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        localObject3 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, (IBinder)localObject3, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5026: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5027: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        e(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5028: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        p(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5029: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        o(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5058: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5059: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        r(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5030: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject2 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject3 = paramParcel1.readStrongBinder();
        paramInt1 = paramParcel1.readInt();
        localObject4 = paramParcel1.createStringArray();
        if (paramParcel1.readInt() != 0) {
          localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        }
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject2, (IBinder)localObject3, paramInt1, (String[])localObject4, (Bundle)localObject1, bool1, paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 5031: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readStrongBinder();
        localObject3 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          a((IGamesCallbacks)localObject1, (IBinder)localObject2, (String)localObject3, bool1, paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        }
      case 5032: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5033: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.createByteArray(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5034: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = b(paramParcel1.createByteArray(), paramParcel1.readString(), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5035: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = bF(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 5036: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        dC(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5037: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5038: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5039: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        i = paramParcel1.readInt();
        bool1 = bool8;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, paramInt1, paramInt2, i, bool1);
        paramParcel2.writeNoException();
        return true;
      case 5040: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        i = paramParcel1.readInt();
        bool1 = bool9;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        b((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, paramInt1, paramInt2, i, bool1);
        paramParcel2.writeNoException();
        return true;
      case 5041: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5042: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        e(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5043: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        f(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5044: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label3420;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          a((IGamesCallbacks)localObject1, paramInt1, paramInt2, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 5045: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label3497;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          a((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 5046: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          bool2 = bool3;
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          }
          b((IGamesCallbacks)localObject1, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
        }
      case 5047: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        f(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5048: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          bool2 = bool5;
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          }
          c((IGamesCallbacks)localObject1, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
        }
      case 5049: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        g(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5050: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        bG(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5051: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5052: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        g(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5053: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = h(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5060: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = bH(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5054: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        bool1 = bool10;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject1, (String)localObject2, bool1);
        paramParcel2.writeNoException();
        return true;
      case 5061: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        i(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5055: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        r(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5067: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        bool1 = kA();
        paramParcel2.writeNoException();
        paramInt1 = i;
        if (bool1) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5068: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        bool1 = bool11;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        N(bool1);
        paramParcel2.writeNoException();
        return true;
      case 5056: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        h(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5057: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        j(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5062: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        i(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5063: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool12;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((IGamesCallbacks)localObject1, bool1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5066: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = bI(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 5501: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label4257;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          b((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 5502: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kB();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 6001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool13;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 6002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        bool1 = bool14;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, bool1);
        paramParcel2.writeNoException();
        return true;
      case 6003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          bool2 = bool6;
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          }
          d((IGamesCallbacks)localObject1, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
        }
      case 6004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          bool2 = bool7;
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          }
          e((IGamesCallbacks)localObject1, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
        }
      case 6501: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label4633;
          }
          bool2 = true;
          if (paramParcel1.readInt() == 0) {
            break label4639;
          }
        }
        for (bool3 = true;; bool3 = false)
        {
          if (paramParcel1.readInt() != 0) {
            bool4 = true;
          }
          a((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1, bool2, bool3, bool4);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
          bool2 = false;
          break label4584;
        }
      case 6502: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        bool1 = bool15;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        b((IGamesCallbacks)localObject1, (String)localObject2, bool1);
        paramParcel2.writeNoException();
        return true;
      case 6503: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool16;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        b((IGamesCallbacks)localObject1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 6504: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        bool1 = bool17;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        c((IGamesCallbacks)localObject1, (String)localObject2, bool1);
        paramParcel2.writeNoException();
        return true;
      case 6505: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        bool1 = bool18;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        d((IGamesCallbacks)localObject1, (String)localObject2, bool1);
        paramParcel2.writeNoException();
        return true;
      case 6506: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        bool1 = bool19;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        b((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, bool1);
        paramParcel2.writeNoException();
        return true;
      case 6507: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          paramParcel1 = h(paramParcel1);
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
      case 7001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        k(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 7002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 7003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        localObject3 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          b((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, (IBinder)localObject3, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 8001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 8002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        bJ(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        return true;
      case 8004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        localObject2 = paramParcel1.createStringArray();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((IGamesCallbacks)localObject1, paramInt1, paramInt2, (String[])localObject2, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 8005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        l(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        m(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.createByteArray(), paramParcel1.readString(), (ParticipantResult[])paramParcel1.createTypedArray(ParticipantResult.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 8008: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.createByteArray(), (ParticipantResult[])paramParcel1.createTypedArray(ParticipantResult.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 8009: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        n(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8010: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        o(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8011: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8012: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 8013: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        s(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 8014: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        p(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8024: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = kq();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 8025: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        v(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8015: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8016: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        e(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8017: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        return true;
      case 8026: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 8018: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8019: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8020: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8021: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8022: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        kC();
        paramParcel2.writeNoException();
        return true;
      case 8023: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        bool1 = bool20;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 8027: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool21;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        c((IGamesCallbacks)localObject1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 9001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label6026;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          c((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 9002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        q(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 9003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = ke();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = bx(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kf();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kg();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kh();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9008: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          paramParcel1 = a(paramInt1, paramInt2, bool1);
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
      case 9009: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          paramParcel1 = b(paramInt1, paramInt2, bool1);
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
      case 9010: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = km();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9011: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (RoomEntity)RoomEntity.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          paramParcel1 = a((RoomEntity)localObject1, paramParcel1.readInt());
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
      case 9012: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kn();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9013: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kD();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9031: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject2 = (ParticipantEntity[])paramParcel1.createTypedArray(ParticipantEntity.CREATOR);
        localObject3 = paramParcel1.readString();
        localObject4 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label6701;
          }
        }
        for (paramParcel1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          paramParcel1 = a((ParticipantEntity[])localObject2, (String)localObject3, (String)localObject4, (Uri)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label6706;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
          localObject1 = null;
          break;
        }
        paramParcel2.writeInt(0);
        return true;
      case 9019: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = ko();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 9020: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label6806;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          d((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 9028: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label6891;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          a((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 9030: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = bK(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 10002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        t(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 10003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 10004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 10005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.createStringArray(), paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 10007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 10008: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 10009: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10010: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10011: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10012: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = a(paramParcel1.readInt(), paramParcel1.createByteArray(), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10013: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = ks();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 10023: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = kt();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 10015: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = kr();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10022: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = GameRequestCluster.CREATOR.ck(paramParcel1);
        }
        paramParcel1 = a((GameRequestCluster)localObject1, paramParcel1.readString());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10014: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        s(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10016: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 10017: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        bool1 = bool22;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        b((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 10021: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = ZInvitationCluster.CREATOR.ci(paramParcel1);
        }
        paramParcel1 = a((ZInvitationCluster)localObject1, paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 10018: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        return true;
      case 10019: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        a(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        return true;
      case 10020: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 11001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        j(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 11002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        kE();
        paramParcel2.writeNoException();
        return true;
      case 12001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label7869;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          paramParcel1 = a((String)localObject1, bool1, bool2, paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label7875;
          }
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
          bool1 = false;
          break;
        }
        paramParcel2.writeInt(0);
        return true;
      case 12002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool23;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        d((IGamesCallbacks)localObject1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 12003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        bool1 = bool24;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        c((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, bool1);
        paramParcel2.writeNoException();
        return true;
      case 12006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        bool1 = bool25;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        e((IGamesCallbacks)localObject1, (String)localObject2, bool1);
        paramParcel2.writeNoException();
        return true;
      case 12007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject2 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject3 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = SnapshotMetadataChange.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label8115;
          }
        }
        for (paramParcel1 = (Contents)Contents.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((IGamesCallbacks)localObject2, (String)localObject3, (SnapshotMetadataChange)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 12019: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Contents)Contents.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a(paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 12020: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        r(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12033: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject2 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject3 = paramParcel1.readString();
        localObject4 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = SnapshotMetadataChange.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label8277;
          }
        }
        for (paramParcel1 = (Contents)Contents.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((IGamesCallbacks)localObject2, (String)localObject3, (String)localObject4, (SnapshotMetadataChange)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 12035: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = ku();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 12036: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramInt1 = kv();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 12005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        s(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12023: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        b(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 12024: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        c(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 12021: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label8486;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          e((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 12022: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label8563;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          f((IGamesCallbacks)localObject1, (String)localObject2, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 12025: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        bool1 = kF();
        paramParcel2.writeNoException();
        paramInt1 = j;
        if (bool1) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        return true;
      case 12026: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        bool1 = bool26;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        O(bool1);
        paramParcel2.writeNoException();
        return true;
      case 12027: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        t(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12032: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool27;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        e((IGamesCallbacks)localObject1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 12016: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool28;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        f((IGamesCallbacks)localObject1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 12031: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool29;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject1, bool1, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 12017: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        n(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 12008: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        u(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12009: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        f(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12010: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.createIntArray();
        paramInt1 = paramParcel1.readInt();
        bool1 = bool30;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject1, (int[])localObject2, paramInt1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 12029: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.createStringArray();
        bool1 = bool31;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        a((IGamesCallbacks)localObject1, (String[])localObject2, bool1);
        paramParcel2.writeNoException();
        return true;
      case 12015: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        localObject4 = paramParcel1.createIntArray();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          a((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, (int[])localObject4, paramInt1, bool1);
          paramParcel2.writeNoException();
          return true;
        }
      case 12028: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        localObject4 = paramParcel1.createStringArray();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          a((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, (String[])localObject4, bool1);
          paramParcel2.writeNoException();
          return true;
        }
      case 12011: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 12013: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder()), paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12012: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        u(paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 12014: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        d(paramParcel1.readLong(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12030: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = b(paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 12034: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        paramParcel1 = bC(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          return true;
        }
        paramParcel2.writeInt(0);
        return true;
      case 12018: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          if (paramParcel1.readInt() == 0) {
            break label9395;
          }
        }
        for (bool2 = true;; bool2 = false)
        {
          b((IGamesCallbacks)localObject1, (String)localObject2, (String)localObject3, paramInt1, bool1, bool2);
          paramParcel2.writeNoException();
          return true;
          bool1 = false;
          break;
        }
      case 13001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        bool1 = bool32;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        P(bool1);
        paramParcel2.writeNoException();
        return true;
      case 13002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = paramParcel1.readString();
        localObject2 = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((String)localObject1, (IBinder)localObject2, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 13003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool33;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        g((IGamesCallbacks)localObject1, bool1);
        paramParcel2.writeNoException();
        return true;
      case 13004: 
        label3420:
        label3497:
        label4257:
        label4584:
        label4633:
        label4639:
        label6026:
        label6701:
        label6706:
        label6806:
        label6891:
        label7869:
        label7875:
        label8115:
        label8277:
        label8486:
        label8563:
        label9395:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
        localObject1 = IGamesCallbacks.Stub.aA(paramParcel1.readStrongBinder());
        bool1 = bool34;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        h((IGamesCallbacks)localObject1, bool1);
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesService");
      localObject1 = localObject4;
      if (paramParcel1.readInt() != 0) {
        localObject1 = AchievementEntity.CREATOR.createFromParcel(paramParcel1);
      }
      paramParcel1 = a((AchievementEntity)localObject1);
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    }
    
    private static class Proxy
      implements IGamesService
    {
      private IBinder lb;
      
      Proxy(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public void N(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(5068, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void O(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(12026, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void P(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.lb.transact(13001, localParcel1, localParcel2, 0);
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
      public int a(IGamesCallbacks paramIGamesCallbacks, byte[] paramArrayOfByte, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +78 -> 96
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: aload_2
        //   37: invokevirtual 70	android/os/Parcel:writeByteArray	([B)V
        //   40: aload 6
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   53: aload_0
        //   54: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   57: sipush 5033
        //   60: aload 6
        //   62: aload 7
        //   64: iconst_0
        //   65: invokeinterface 46 5 0
        //   70: pop
        //   71: aload 7
        //   73: invokevirtual 49	android/os/Parcel:readException	()V
        //   76: aload 7
        //   78: invokevirtual 77	android/os/Parcel:readInt	()I
        //   81: istore 5
        //   83: aload 7
        //   85: invokevirtual 52	android/os/Parcel:recycle	()V
        //   88: aload 6
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: iload 5
        //   95: ireturn
        //   96: aconst_null
        //   97: astore_1
        //   98: goto -70 -> 28
        //   101: astore_1
        //   102: aload 7
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload 6
        //   109: invokevirtual 52	android/os/Parcel:recycle	()V
        //   112: aload_1
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	Proxy
        //   0	114	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	114	2	paramArrayOfByte	byte[]
        //   0	114	3	paramString1	String
        //   0	114	4	paramString2	String
        //   81	13	5	i	int
        //   3	105	6	localParcel1	Parcel
        //   8	95	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	101	finally
        //   21	28	101	finally
        //   28	83	101	finally
      }
      
      /* Error */
      public Intent a(int paramInt1, int paramInt2, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 6
        //   22: iload_1
        //   23: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   26: aload 6
        //   28: iload_2
        //   29: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   32: iload 4
        //   34: istore_1
        //   35: iload_3
        //   36: ifeq +5 -> 41
        //   39: iconst_1
        //   40: istore_1
        //   41: aload 6
        //   43: iload_1
        //   44: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   47: aload_0
        //   48: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   51: sipush 9008
        //   54: aload 6
        //   56: aload 7
        //   58: iconst_0
        //   59: invokeinterface 46 5 0
        //   64: pop
        //   65: aload 7
        //   67: invokevirtual 49	android/os/Parcel:readException	()V
        //   70: aload 7
        //   72: invokevirtual 77	android/os/Parcel:readInt	()I
        //   75: ifeq +31 -> 106
        //   78: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   81: aload 7
        //   83: invokeinterface 90 2 0
        //   88: checkcast 80	android/content/Intent
        //   91: astore 5
        //   93: aload 7
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: invokevirtual 52	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: areturn
        //   106: aconst_null
        //   107: astore 5
        //   109: goto -16 -> 93
        //   112: astore 5
        //   114: aload 7
        //   116: invokevirtual 52	android/os/Parcel:recycle	()V
        //   119: aload 6
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 5
        //   126: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	127	0	this	Proxy
        //   0	127	1	paramInt1	int
        //   0	127	2	paramInt2	int
        //   0	127	3	paramBoolean	boolean
        //   1	32	4	i	int
        //   91	17	5	localIntent	Intent
        //   112	13	5	localObject	Object
        //   6	114	6	localParcel1	Parcel
        //   11	104	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	32	112	finally
        //   41	93	112	finally
      }
      
      /* Error */
      public Intent a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: iload_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload 5
        //   25: aload_2
        //   26: invokevirtual 70	android/os/Parcel:writeByteArray	([B)V
        //   29: aload 5
        //   31: iload_3
        //   32: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   35: aload 5
        //   37: aload 4
        //   39: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload_0
        //   43: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   46: sipush 10012
        //   49: aload 5
        //   51: aload 6
        //   53: iconst_0
        //   54: invokeinterface 46 5 0
        //   59: pop
        //   60: aload 6
        //   62: invokevirtual 49	android/os/Parcel:readException	()V
        //   65: aload 6
        //   67: invokevirtual 77	android/os/Parcel:readInt	()I
        //   70: ifeq +29 -> 99
        //   73: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   76: aload 6
        //   78: invokeinterface 90 2 0
        //   83: checkcast 80	android/content/Intent
        //   86: astore_2
        //   87: aload 6
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 5
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload_2
        //   98: areturn
        //   99: aconst_null
        //   100: astore_2
        //   101: goto -14 -> 87
        //   104: astore_2
        //   105: aload 6
        //   107: invokevirtual 52	android/os/Parcel:recycle	()V
        //   110: aload 5
        //   112: invokevirtual 52	android/os/Parcel:recycle	()V
        //   115: aload_2
        //   116: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	Proxy
        //   0	117	1	paramInt1	int
        //   0	117	2	paramArrayOfByte	byte[]
        //   0	117	3	paramInt2	int
        //   0	117	4	paramString	String
        //   3	108	5	localParcel1	Parcel
        //   8	98	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	87	104	finally
      }
      
      public Intent a(AchievementEntity paramAchievementEntity)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramAchievementEntity != null)
            {
              localParcel1.writeInt(1);
              paramAchievementEntity.writeToParcel(localParcel1, 0);
              this.lb.transact(13005, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramAchievementEntity = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return paramAchievementEntity;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramAchievementEntity = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Intent a(ZInvitationCluster paramZInvitationCluster, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramZInvitationCluster != null)
            {
              localParcel1.writeInt(1);
              paramZInvitationCluster.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString1);
              localParcel1.writeString(paramString2);
              this.lb.transact(10021, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramZInvitationCluster = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return paramZInvitationCluster;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramZInvitationCluster = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Intent a(GameRequestCluster paramGameRequestCluster, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramGameRequestCluster != null)
            {
              localParcel1.writeInt(1);
              paramGameRequestCluster.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString);
              this.lb.transact(10022, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramGameRequestCluster = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return paramGameRequestCluster;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramGameRequestCluster = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Intent a(RoomEntity paramRoomEntity, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramRoomEntity != null)
            {
              localParcel1.writeInt(1);
              paramRoomEntity.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt);
              this.lb.transact(9011, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramRoomEntity = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return paramRoomEntity;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramRoomEntity = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public Intent a(String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 7
        //   22: aload_1
        //   23: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   26: iload_2
        //   27: ifeq +92 -> 119
        //   30: iconst_1
        //   31: istore 5
        //   33: aload 7
        //   35: iload 5
        //   37: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   40: iload_3
        //   41: ifeq +84 -> 125
        //   44: iload 6
        //   46: istore 5
        //   48: aload 7
        //   50: iload 5
        //   52: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   55: aload 7
        //   57: iload 4
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: aload_0
        //   63: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   66: sipush 12001
        //   69: aload 7
        //   71: aload 8
        //   73: iconst_0
        //   74: invokeinterface 46 5 0
        //   79: pop
        //   80: aload 8
        //   82: invokevirtual 49	android/os/Parcel:readException	()V
        //   85: aload 8
        //   87: invokevirtual 77	android/os/Parcel:readInt	()I
        //   90: ifeq +41 -> 131
        //   93: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   96: aload 8
        //   98: invokeinterface 90 2 0
        //   103: checkcast 80	android/content/Intent
        //   106: astore_1
        //   107: aload 8
        //   109: invokevirtual 52	android/os/Parcel:recycle	()V
        //   112: aload 7
        //   114: invokevirtual 52	android/os/Parcel:recycle	()V
        //   117: aload_1
        //   118: areturn
        //   119: iconst_0
        //   120: istore 5
        //   122: goto -89 -> 33
        //   125: iconst_0
        //   126: istore 5
        //   128: goto -80 -> 48
        //   131: aconst_null
        //   132: astore_1
        //   133: goto -26 -> 107
        //   136: astore_1
        //   137: aload 8
        //   139: invokevirtual 52	android/os/Parcel:recycle	()V
        //   142: aload 7
        //   144: invokevirtual 52	android/os/Parcel:recycle	()V
        //   147: aload_1
        //   148: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	149	0	this	Proxy
        //   0	149	1	paramString	String
        //   0	149	2	paramBoolean1	boolean
        //   0	149	3	paramBoolean2	boolean
        //   0	149	4	paramInt	int
        //   31	96	5	i	int
        //   1	44	6	j	int
        //   6	137	7	localParcel1	Parcel
        //   11	127	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	136	finally
        //   33	40	136	finally
        //   48	107	136	finally
      }
      
      public Intent a(ParticipantEntity[] paramArrayOfParticipantEntity, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            localParcel1.writeTypedArray(paramArrayOfParticipantEntity, 0);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            if (paramUri1 != null)
            {
              localParcel1.writeInt(1);
              paramUri1.writeToParcel(localParcel1, 0);
              if (paramUri2 != null)
              {
                localParcel1.writeInt(1);
                paramUri2.writeToParcel(localParcel1, 0);
                this.lb.transact(9031, localParcel1, localParcel2, 0);
                localParcel2.readException();
                if (localParcel2.readInt() == 0) {
                  break label162;
                }
                paramArrayOfParticipantEntity = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return paramArrayOfParticipantEntity;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label162:
          paramArrayOfParticipantEntity = null;
        }
      }
      
      public void a(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.lb.transact(8019, localParcel1, localParcel2, 0);
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
      public void a(IBinder paramIBinder, Bundle paramBundle)
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
        //   16: aload_1
        //   17: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   20: aload_2
        //   21: ifnull +46 -> 67
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5005
        //   42: aload_3
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 46 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 49	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 52	android/os/Parcel:recycle	()V
        //   66: return
        //   67: aload_3
        //   68: iconst_0
        //   69: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   72: goto -37 -> 35
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	Proxy
        //   0	87	1	paramIBinder	IBinder
        //   0	87	2	paramBundle	Bundle
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
      public void a(Contents paramContents)
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
        //   26: invokevirtual 132	com/google/android/gms/drive/Contents:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12019
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 46 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 49	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 52	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 52	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aload_2
        //   59: iconst_0
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: goto -34 -> 29
        //   66: astore_1
        //   67: aload_3
        //   68: invokevirtual 52	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	Proxy
        //   0	77	1	paramContents	Contents
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
      public void a(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5002
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: iload_2
        //   33: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 10016
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
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
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +69 -> 87
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: iload_2
        //   37: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   40: aload 5
        //   42: iload_3
        //   43: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   46: aload 5
        //   48: iload 4
        //   50: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   53: aload_0
        //   54: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   57: sipush 10009
        //   60: aload 5
        //   62: aload 6
        //   64: iconst_0
        //   65: invokeinterface 46 5 0
        //   70: pop
        //   71: aload 6
        //   73: invokevirtual 49	android/os/Parcel:readException	()V
        //   76: aload 6
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: aload 5
        //   83: invokevirtual 52	android/os/Parcel:recycle	()V
        //   86: return
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -61 -> 28
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 52	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	105	2	paramInt1	int
        //   0	105	3	paramInt2	int
        //   0	105	4	paramInt3	int
        //   3	96	5	localParcel1	Parcel
        //   8	86	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	92	finally
        //   21	28	92	finally
        //   28	76	92	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +89 -> 110
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 7
        //   39: iload_2
        //   40: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   43: aload 7
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 4
        //   51: ifeq +64 -> 115
        //   54: iconst_1
        //   55: istore_2
        //   56: aload 7
        //   58: iload_2
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: iload 5
        //   64: ifeq +56 -> 120
        //   67: iload 6
        //   69: istore_2
        //   70: aload 7
        //   72: iload_2
        //   73: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   76: aload_0
        //   77: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   80: sipush 5044
        //   83: aload 7
        //   85: aload 8
        //   87: iconst_0
        //   88: invokeinterface 46 5 0
        //   93: pop
        //   94: aload 8
        //   96: invokevirtual 49	android/os/Parcel:readException	()V
        //   99: aload 8
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: return
        //   110: aconst_null
        //   111: astore_1
        //   112: goto -81 -> 31
        //   115: iconst_0
        //   116: istore_2
        //   117: goto -61 -> 56
        //   120: iconst_0
        //   121: istore_2
        //   122: goto -52 -> 70
        //   125: astore_1
        //   126: aload 8
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload 7
        //   133: invokevirtual 52	android/os/Parcel:recycle	()V
        //   136: aload_1
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramInt1	int
        //   0	138	3	paramInt2	int
        //   0	138	4	paramBoolean1	boolean
        //   0	138	5	paramBoolean2	boolean
        //   1	67	6	i	int
        //   6	126	7	localParcel1	Parcel
        //   11	116	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	125	finally
        //   24	31	125	finally
        //   31	49	125	finally
        //   56	62	125	finally
        //   70	99	125	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt1, int paramInt2, String[] paramArrayOfString, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +88 -> 106
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: iload_2
        //   37: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   40: aload 6
        //   42: iload_3
        //   43: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   53: aload 5
        //   55: ifnull +56 -> 111
        //   58: aload 6
        //   60: iconst_1
        //   61: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   72: aload_0
        //   73: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   76: sipush 8004
        //   79: aload 6
        //   81: aload 7
        //   83: iconst_0
        //   84: invokeinterface 46 5 0
        //   89: pop
        //   90: aload 7
        //   92: invokevirtual 49	android/os/Parcel:readException	()V
        //   95: aload 7
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: invokevirtual 52	android/os/Parcel:recycle	()V
        //   105: return
        //   106: aconst_null
        //   107: astore_1
        //   108: goto -80 -> 28
        //   111: aload 6
        //   113: iconst_0
        //   114: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   117: goto -45 -> 72
        //   120: astore_1
        //   121: aload 7
        //   123: invokevirtual 52	android/os/Parcel:recycle	()V
        //   126: aload 6
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload_1
        //   132: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	133	0	this	Proxy
        //   0	133	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	133	2	paramInt1	int
        //   0	133	3	paramInt2	int
        //   0	133	4	paramArrayOfString	String[]
        //   0	133	5	paramBundle	Bundle
        //   3	124	6	localParcel1	Parcel
        //   8	114	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	120	finally
        //   21	28	120	finally
        //   28	53	120	finally
        //   58	72	120	finally
        //   72	95	120	finally
        //   111	117	120	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +82 -> 103
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: iload_2
        //   40: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   43: iload_3
        //   44: ifeq +64 -> 108
        //   47: iconst_1
        //   48: istore_2
        //   49: aload 6
        //   51: iload_2
        //   52: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   55: iload 4
        //   57: ifeq +56 -> 113
        //   60: iload 5
        //   62: istore_2
        //   63: aload 6
        //   65: iload_2
        //   66: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   69: aload_0
        //   70: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   73: sipush 5015
        //   76: aload 6
        //   78: aload 7
        //   80: iconst_0
        //   81: invokeinterface 46 5 0
        //   86: pop
        //   87: aload 7
        //   89: invokevirtual 49	android/os/Parcel:readException	()V
        //   92: aload 7
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: return
        //   103: aconst_null
        //   104: astore_1
        //   105: goto -74 -> 31
        //   108: iconst_0
        //   109: istore_2
        //   110: goto -61 -> 49
        //   113: iconst_0
        //   114: istore_2
        //   115: goto -52 -> 63
        //   118: astore_1
        //   119: aload 7
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 6
        //   126: invokevirtual 52	android/os/Parcel:recycle	()V
        //   129: aload_1
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramInt	int
        //   0	131	3	paramBoolean1	boolean
        //   0	131	4	paramBoolean2	boolean
        //   1	60	5	i	int
        //   6	119	6	localParcel1	Parcel
        //   11	109	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	118	finally
        //   24	31	118	finally
        //   31	43	118	finally
        //   49	55	118	finally
        //   63	92	118	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int paramInt, int[] paramArrayOfInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: iload_2
        //   37: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 147	android/os/Parcel:writeIntArray	([I)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 10018
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	98	2	paramInt	int
        //   0	98	3	paramArrayOfInt	int[]
        //   3	89	4	localParcel1	Parcel
        //   8	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	85	finally
        //   21	28	85	finally
        //   28	69	85	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +56 -> 74
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: lload_2
        //   37: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   40: aload_0
        //   41: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   44: sipush 5058
        //   47: aload 4
        //   49: aload 5
        //   51: iconst_0
        //   52: invokeinterface 46 5 0
        //   57: pop
        //   58: aload 5
        //   60: invokevirtual 49	android/os/Parcel:readException	()V
        //   63: aload 5
        //   65: invokevirtual 52	android/os/Parcel:recycle	()V
        //   68: aload 4
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: return
        //   74: aconst_null
        //   75: astore_1
        //   76: goto -48 -> 28
        //   79: astore_1
        //   80: aload 5
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 4
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: aload_1
        //   91: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	92	0	this	Proxy
        //   0	92	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	92	2	paramLong	long
        //   3	83	4	localParcel1	Parcel
        //   8	73	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	79	finally
        //   21	28	79	finally
        //   28	63	79	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +63 -> 81
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: lload_2
        //   37: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   40: aload 5
        //   42: aload 4
        //   44: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   47: aload_0
        //   48: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   51: sipush 8018
        //   54: aload 5
        //   56: aload 6
        //   58: iconst_0
        //   59: invokeinterface 46 5 0
        //   64: pop
        //   65: aload 6
        //   67: invokevirtual 49	android/os/Parcel:readException	()V
        //   70: aload 6
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload 5
        //   77: invokevirtual 52	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore_1
        //   83: goto -55 -> 28
        //   86: astore_1
        //   87: aload 6
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 5
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	Proxy
        //   0	99	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	99	2	paramLong	long
        //   0	99	4	paramString	String
        //   3	90	5	localParcel1	Parcel
        //   8	80	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	86	finally
        //   21	28	86	finally
        //   28	70	86	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, Bundle paramBundle, int paramInt1, int paramInt2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +80 -> 98
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload_2
        //   35: ifnull +68 -> 103
        //   38: aload 5
        //   40: iconst_1
        //   41: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   44: aload_2
        //   45: aload 5
        //   47: iconst_0
        //   48: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   51: aload 5
        //   53: iload_3
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload 5
        //   59: iload 4
        //   61: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   64: aload_0
        //   65: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   68: sipush 5021
        //   71: aload 5
        //   73: aload 6
        //   75: iconst_0
        //   76: invokeinterface 46 5 0
        //   81: pop
        //   82: aload 6
        //   84: invokevirtual 49	android/os/Parcel:readException	()V
        //   87: aload 6
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 5
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: return
        //   98: aconst_null
        //   99: astore_1
        //   100: goto -72 -> 28
        //   103: aload 5
        //   105: iconst_0
        //   106: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   109: goto -58 -> 51
        //   112: astore_1
        //   113: aload 6
        //   115: invokevirtual 52	android/os/Parcel:recycle	()V
        //   118: aload 5
        //   120: invokevirtual 52	android/os/Parcel:recycle	()V
        //   123: aload_1
        //   124: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	125	0	this	Proxy
        //   0	125	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	125	2	paramBundle	Bundle
        //   0	125	3	paramInt1	int
        //   0	125	4	paramInt2	int
        //   3	116	5	localParcel1	Parcel
        //   8	106	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	112	finally
        //   21	28	112	finally
        //   28	34	112	finally
        //   38	51	112	finally
        //   51	87	112	finally
        //   103	109	112	finally
      }
      
      public void a(IGamesCallbacks paramIGamesCallbacks, IBinder paramIBinder, int paramInt, String[] paramArrayOfString, Bundle paramBundle, boolean paramBoolean, long paramLong)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramIGamesCallbacks != null)
            {
              paramIGamesCallbacks = paramIGamesCallbacks.asBinder();
              localParcel1.writeStrongBinder(paramIGamesCallbacks);
              localParcel1.writeStrongBinder(paramIBinder);
              localParcel1.writeInt(paramInt);
              localParcel1.writeStringArray(paramArrayOfString);
              if (paramBundle != null)
              {
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                break label157;
                localParcel1.writeInt(paramInt);
                localParcel1.writeLong(paramLong);
                this.lb.transact(5030, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              paramIGamesCallbacks = null;
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          label157:
          do
          {
            paramInt = 0;
            break;
          } while (!paramBoolean);
          paramInt = i;
        }
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, IBinder paramIBinder, String paramString, boolean paramBoolean, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 7
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +84 -> 105
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 8
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 8
        //   39: aload_2
        //   40: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   43: aload 8
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: iload 4
        //   51: ifeq +6 -> 57
        //   54: iconst_1
        //   55: istore 7
        //   57: aload 8
        //   59: iload 7
        //   61: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   64: aload 8
        //   66: lload 5
        //   68: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   71: aload_0
        //   72: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   75: sipush 5031
        //   78: aload 8
        //   80: aload 9
        //   82: iconst_0
        //   83: invokeinterface 46 5 0
        //   88: pop
        //   89: aload 9
        //   91: invokevirtual 49	android/os/Parcel:readException	()V
        //   94: aload 9
        //   96: invokevirtual 52	android/os/Parcel:recycle	()V
        //   99: aload 8
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: return
        //   105: aconst_null
        //   106: astore_1
        //   107: goto -76 -> 31
        //   110: astore_1
        //   111: aload 9
        //   113: invokevirtual 52	android/os/Parcel:recycle	()V
        //   116: aload 8
        //   118: invokevirtual 52	android/os/Parcel:recycle	()V
        //   121: aload_1
        //   122: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	123	0	this	Proxy
        //   0	123	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	123	2	paramIBinder	IBinder
        //   0	123	3	paramString	String
        //   0	123	4	paramBoolean	boolean
        //   0	123	5	paramLong	long
        //   1	59	7	i	int
        //   6	111	8	localParcel1	Parcel
        //   11	101	9	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	110	finally
        //   24	31	110	finally
        //   31	49	110	finally
        //   57	94	110	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5014
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: iload_3
        //   43: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 10011
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	98	2	paramString	String
        //   0	98	3	paramInt	int
        //   3	89	4	localParcel1	Parcel
        //   8	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	85	finally
        //   21	28	85	finally
        //   28	69	85	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 7
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 8
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 8
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 8
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: aload 8
        //   51: iload 4
        //   53: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   56: aload 8
        //   58: iload 5
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: iload 7
        //   65: istore_3
        //   66: iload 6
        //   68: ifeq +5 -> 73
        //   71: iconst_1
        //   72: istore_3
        //   73: aload 8
        //   75: iload_3
        //   76: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 5019
        //   86: aload 8
        //   88: aload 9
        //   90: iconst_0
        //   91: invokeinterface 46 5 0
        //   96: pop
        //   97: aload 9
        //   99: invokevirtual 49	android/os/Parcel:readException	()V
        //   102: aload 9
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload 8
        //   109: invokevirtual 52	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore_1
        //   115: goto -84 -> 31
        //   118: astore_1
        //   119: aload 9
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 8
        //   126: invokevirtual 52	android/os/Parcel:recycle	()V
        //   129: aload_1
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramString	String
        //   0	131	3	paramInt1	int
        //   0	131	4	paramInt2	int
        //   0	131	5	paramInt3	int
        //   0	131	6	paramBoolean	boolean
        //   1	63	7	i	int
        //   6	119	8	localParcel1	Parcel
        //   11	109	9	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	118	finally
        //   24	31	118	finally
        //   31	63	118	finally
        //   73	102	118	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +88 -> 106
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 6
        //   42: iload_3
        //   43: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   53: aload 5
        //   55: ifnull +56 -> 111
        //   58: aload 6
        //   60: iconst_1
        //   61: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   72: aload_0
        //   73: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   76: sipush 5025
        //   79: aload 6
        //   81: aload 7
        //   83: iconst_0
        //   84: invokeinterface 46 5 0
        //   89: pop
        //   90: aload 7
        //   92: invokevirtual 49	android/os/Parcel:readException	()V
        //   95: aload 7
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: invokevirtual 52	android/os/Parcel:recycle	()V
        //   105: return
        //   106: aconst_null
        //   107: astore_1
        //   108: goto -80 -> 28
        //   111: aload 6
        //   113: iconst_0
        //   114: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   117: goto -45 -> 72
        //   120: astore_1
        //   121: aload 7
        //   123: invokevirtual 52	android/os/Parcel:recycle	()V
        //   126: aload 6
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload_1
        //   132: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	133	0	this	Proxy
        //   0	133	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	133	2	paramString	String
        //   0	133	3	paramInt	int
        //   0	133	4	paramIBinder	IBinder
        //   0	133	5	paramBundle	Bundle
        //   3	124	6	localParcel1	Parcel
        //   8	114	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	120	finally
        //   21	28	120	finally
        //   28	53	120	finally
        //   58	72	120	finally
        //   72	95	120	finally
        //   111	117	120	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +78 -> 99
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 6
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 5
        //   51: istore_3
        //   52: iload 4
        //   54: ifeq +5 -> 59
        //   57: iconst_1
        //   58: istore_3
        //   59: aload 6
        //   61: iload_3
        //   62: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   65: aload_0
        //   66: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   69: sipush 8023
        //   72: aload 6
        //   74: aload 7
        //   76: iconst_0
        //   77: invokeinterface 46 5 0
        //   82: pop
        //   83: aload 7
        //   85: invokevirtual 49	android/os/Parcel:readException	()V
        //   88: aload 7
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 6
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: return
        //   99: aconst_null
        //   100: astore_1
        //   101: goto -70 -> 31
        //   104: astore_1
        //   105: aload 7
        //   107: invokevirtual 52	android/os/Parcel:recycle	()V
        //   110: aload 6
        //   112: invokevirtual 52	android/os/Parcel:recycle	()V
        //   115: aload_1
        //   116: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	Proxy
        //   0	117	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	117	2	paramString	String
        //   0	117	3	paramInt	int
        //   0	117	4	paramBoolean	boolean
        //   1	49	5	i	int
        //   6	105	6	localParcel1	Parcel
        //   11	95	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	104	finally
        //   24	31	104	finally
        //   31	49	104	finally
        //   59	88	104	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +89 -> 110
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 7
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 7
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 4
        //   51: ifeq +64 -> 115
        //   54: iconst_1
        //   55: istore_3
        //   56: aload 7
        //   58: iload_3
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: iload 5
        //   64: ifeq +56 -> 120
        //   67: iload 6
        //   69: istore_3
        //   70: aload 7
        //   72: iload_3
        //   73: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   76: aload_0
        //   77: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   80: sipush 5045
        //   83: aload 7
        //   85: aload 8
        //   87: iconst_0
        //   88: invokeinterface 46 5 0
        //   93: pop
        //   94: aload 8
        //   96: invokevirtual 49	android/os/Parcel:readException	()V
        //   99: aload 8
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: return
        //   110: aconst_null
        //   111: astore_1
        //   112: goto -81 -> 31
        //   115: iconst_0
        //   116: istore_3
        //   117: goto -61 -> 56
        //   120: iconst_0
        //   121: istore_3
        //   122: goto -52 -> 70
        //   125: astore_1
        //   126: aload 8
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload 7
        //   133: invokevirtual 52	android/os/Parcel:recycle	()V
        //   136: aload_1
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramString	String
        //   0	138	3	paramInt	int
        //   0	138	4	paramBoolean1	boolean
        //   0	138	5	paramBoolean2	boolean
        //   1	67	6	i	int
        //   6	126	7	localParcel1	Parcel
        //   11	116	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	125	finally
        //   24	31	125	finally
        //   31	49	125	finally
        //   56	62	125	finally
        //   70	99	125	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 8
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 9
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 10
        //   13: aload 9
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +115 -> 136
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 9
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 9
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 9
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 4
        //   51: ifeq +90 -> 141
        //   54: iconst_1
        //   55: istore_3
        //   56: aload 9
        //   58: iload_3
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: iload 5
        //   64: ifeq +82 -> 146
        //   67: iconst_1
        //   68: istore_3
        //   69: aload 9
        //   71: iload_3
        //   72: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   75: iload 6
        //   77: ifeq +74 -> 151
        //   80: iconst_1
        //   81: istore_3
        //   82: aload 9
        //   84: iload_3
        //   85: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   88: iload 7
        //   90: ifeq +66 -> 156
        //   93: iload 8
        //   95: istore_3
        //   96: aload 9
        //   98: iload_3
        //   99: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   102: aload_0
        //   103: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   106: sipush 6501
        //   109: aload 9
        //   111: aload 10
        //   113: iconst_0
        //   114: invokeinterface 46 5 0
        //   119: pop
        //   120: aload 10
        //   122: invokevirtual 49	android/os/Parcel:readException	()V
        //   125: aload 10
        //   127: invokevirtual 52	android/os/Parcel:recycle	()V
        //   130: aload 9
        //   132: invokevirtual 52	android/os/Parcel:recycle	()V
        //   135: return
        //   136: aconst_null
        //   137: astore_1
        //   138: goto -107 -> 31
        //   141: iconst_0
        //   142: istore_3
        //   143: goto -87 -> 56
        //   146: iconst_0
        //   147: istore_3
        //   148: goto -79 -> 69
        //   151: iconst_0
        //   152: istore_3
        //   153: goto -71 -> 82
        //   156: iconst_0
        //   157: istore_3
        //   158: goto -62 -> 96
        //   161: astore_1
        //   162: aload 10
        //   164: invokevirtual 52	android/os/Parcel:recycle	()V
        //   167: aload 9
        //   169: invokevirtual 52	android/os/Parcel:recycle	()V
        //   172: aload_1
        //   173: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	174	0	this	Proxy
        //   0	174	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	174	2	paramString	String
        //   0	174	3	paramInt	int
        //   0	174	4	paramBoolean1	boolean
        //   0	174	5	paramBoolean2	boolean
        //   0	174	6	paramBoolean3	boolean
        //   0	174	7	paramBoolean4	boolean
        //   1	93	8	i	int
        //   6	162	9	localParcel1	Parcel
        //   11	152	10	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	161	finally
        //   24	31	161	finally
        //   31	49	161	finally
        //   56	62	161	finally
        //   69	75	161	finally
        //   82	88	161	finally
        //   96	125	161	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, int[] paramArrayOfInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +69 -> 87
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: iload_3
        //   43: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   46: aload 5
        //   48: aload 4
        //   50: invokevirtual 147	android/os/Parcel:writeIntArray	([I)V
        //   53: aload_0
        //   54: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   57: sipush 10019
        //   60: aload 5
        //   62: aload 6
        //   64: iconst_0
        //   65: invokeinterface 46 5 0
        //   70: pop
        //   71: aload 6
        //   73: invokevirtual 49	android/os/Parcel:readException	()V
        //   76: aload 6
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: aload 5
        //   83: invokevirtual 52	android/os/Parcel:recycle	()V
        //   86: return
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -61 -> 28
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 52	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	105	2	paramString	String
        //   0	105	3	paramInt	int
        //   0	105	4	paramArrayOfInt	int[]
        //   3	96	5	localParcel1	Parcel
        //   8	86	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	92	finally
        //   21	28	92	finally
        //   28	76	92	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: lload_3
        //   43: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 5016
        //   53: aload 5
        //   55: aload 6
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 6
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 6
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 5
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 6
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 5
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	98	2	paramString	String
        //   0	98	3	paramLong	long
        //   3	89	5	localParcel1	Parcel
        //   8	79	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	85	finally
        //   21	28	85	finally
        //   28	69	85	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, long paramLong, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +69 -> 87
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 6
        //   42: lload_3
        //   43: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   46: aload 6
        //   48: aload 5
        //   50: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   53: aload_0
        //   54: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   57: sipush 7002
        //   60: aload 6
        //   62: aload 7
        //   64: iconst_0
        //   65: invokeinterface 46 5 0
        //   70: pop
        //   71: aload 7
        //   73: invokevirtual 49	android/os/Parcel:readException	()V
        //   76: aload 7
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: aload 6
        //   83: invokevirtual 52	android/os/Parcel:recycle	()V
        //   86: return
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -61 -> 28
        //   92: astore_1
        //   93: aload 7
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: invokevirtual 52	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	105	2	paramString1	String
        //   0	105	3	paramLong	long
        //   0	105	5	paramString2	String
        //   3	96	6	localParcel1	Parcel
        //   8	86	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	92	finally
        //   21	28	92	finally
        //   28	76	92	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +81 -> 99
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   46: aload 4
        //   48: ifnull +56 -> 104
        //   51: aload 5
        //   53: iconst_1
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload 4
        //   59: aload 5
        //   61: iconst_0
        //   62: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   65: aload_0
        //   66: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   69: sipush 5023
        //   72: aload 5
        //   74: aload 6
        //   76: iconst_0
        //   77: invokeinterface 46 5 0
        //   82: pop
        //   83: aload 6
        //   85: invokevirtual 49	android/os/Parcel:readException	()V
        //   88: aload 6
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 5
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: return
        //   99: aconst_null
        //   100: astore_1
        //   101: goto -73 -> 28
        //   104: aload 5
        //   106: iconst_0
        //   107: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   110: goto -45 -> 65
        //   113: astore_1
        //   114: aload 6
        //   116: invokevirtual 52	android/os/Parcel:recycle	()V
        //   119: aload 5
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload_1
        //   125: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	126	0	this	Proxy
        //   0	126	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	126	2	paramString	String
        //   0	126	3	paramIBinder	IBinder
        //   0	126	4	paramBundle	Bundle
        //   3	117	5	localParcel1	Parcel
        //   8	107	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	113	finally
        //   21	28	113	finally
        //   28	46	113	finally
        //   51	65	113	finally
        //   65	88	113	finally
        //   104	110	113	finally
      }
      
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, SnapshotMetadataChange paramSnapshotMetadataChange, Contents paramContents)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label137:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramIGamesCallbacks != null)
            {
              paramIGamesCallbacks = paramIGamesCallbacks.asBinder();
              localParcel1.writeStrongBinder(paramIGamesCallbacks);
              localParcel1.writeString(paramString);
              if (paramSnapshotMetadataChange != null)
              {
                localParcel1.writeInt(1);
                paramSnapshotMetadataChange.writeToParcel(localParcel1, 0);
                if (paramContents == null) {
                  break label137;
                }
                localParcel1.writeInt(1);
                paramContents.writeToParcel(localParcel1, 0);
                this.lb.transact(12007, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              paramIGamesCallbacks = null;
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
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 5038
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
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
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +76 -> 94
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 6
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 6
        //   48: iload 4
        //   50: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   53: aload 6
        //   55: iload 5
        //   57: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   60: aload_0
        //   61: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   64: sipush 8001
        //   67: aload 6
        //   69: aload 7
        //   71: iconst_0
        //   72: invokeinterface 46 5 0
        //   77: pop
        //   78: aload 7
        //   80: invokevirtual 49	android/os/Parcel:readException	()V
        //   83: aload 7
        //   85: invokevirtual 52	android/os/Parcel:recycle	()V
        //   88: aload 6
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: return
        //   94: aconst_null
        //   95: astore_1
        //   96: goto -68 -> 28
        //   99: astore_1
        //   100: aload 7
        //   102: invokevirtual 52	android/os/Parcel:recycle	()V
        //   105: aload 6
        //   107: invokevirtual 52	android/os/Parcel:recycle	()V
        //   110: aload_1
        //   111: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	112	0	this	Proxy
        //   0	112	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	112	2	paramString1	String
        //   0	112	3	paramString2	String
        //   0	112	4	paramInt1	int
        //   0	112	5	paramInt2	int
        //   3	103	6	localParcel1	Parcel
        //   8	93	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	99	finally
        //   21	28	99	finally
        //   28	83	99	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +83 -> 101
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 7
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 7
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 7
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 7
        //   48: iload 4
        //   50: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   53: aload 7
        //   55: iload 5
        //   57: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   60: aload 7
        //   62: iload 6
        //   64: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   67: aload_0
        //   68: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   71: sipush 10010
        //   74: aload 7
        //   76: aload 8
        //   78: iconst_0
        //   79: invokeinterface 46 5 0
        //   84: pop
        //   85: aload 8
        //   87: invokevirtual 49	android/os/Parcel:readException	()V
        //   90: aload 8
        //   92: invokevirtual 52	android/os/Parcel:recycle	()V
        //   95: aload 7
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: return
        //   101: aconst_null
        //   102: astore_1
        //   103: goto -75 -> 28
        //   106: astore_1
        //   107: aload 8
        //   109: invokevirtual 52	android/os/Parcel:recycle	()V
        //   112: aload 7
        //   114: invokevirtual 52	android/os/Parcel:recycle	()V
        //   117: aload_1
        //   118: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	119	0	this	Proxy
        //   0	119	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	119	2	paramString1	String
        //   0	119	3	paramString2	String
        //   0	119	4	paramInt1	int
        //   0	119	5	paramInt2	int
        //   0	119	6	paramInt3	int
        //   3	110	7	localParcel1	Parcel
        //   8	100	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	106	finally
        //   21	28	106	finally
        //   28	90	106	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 8
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 9
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 10
        //   13: aload 9
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +102 -> 123
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 9
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 9
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 9
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 9
        //   51: iload 4
        //   53: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   56: aload 9
        //   58: iload 5
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: aload 9
        //   65: iload 6
        //   67: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   70: iload 8
        //   72: istore 4
        //   74: iload 7
        //   76: ifeq +6 -> 82
        //   79: iconst_1
        //   80: istore 4
        //   82: aload 9
        //   84: iload 4
        //   86: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   89: aload_0
        //   90: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   93: sipush 5039
        //   96: aload 9
        //   98: aload 10
        //   100: iconst_0
        //   101: invokeinterface 46 5 0
        //   106: pop
        //   107: aload 10
        //   109: invokevirtual 49	android/os/Parcel:readException	()V
        //   112: aload 10
        //   114: invokevirtual 52	android/os/Parcel:recycle	()V
        //   117: aload 9
        //   119: invokevirtual 52	android/os/Parcel:recycle	()V
        //   122: return
        //   123: aconst_null
        //   124: astore_1
        //   125: goto -94 -> 31
        //   128: astore_1
        //   129: aload 10
        //   131: invokevirtual 52	android/os/Parcel:recycle	()V
        //   134: aload 9
        //   136: invokevirtual 52	android/os/Parcel:recycle	()V
        //   139: aload_1
        //   140: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	141	0	this	Proxy
        //   0	141	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	141	2	paramString1	String
        //   0	141	3	paramString2	String
        //   0	141	4	paramInt1	int
        //   0	141	5	paramInt2	int
        //   0	141	6	paramInt3	int
        //   0	141	7	paramBoolean	boolean
        //   1	70	8	i	int
        //   6	129	9	localParcel1	Parcel
        //   11	119	10	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	128	finally
        //   24	31	128	finally
        //   31	70	128	finally
        //   82	112	128	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 7
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +100 -> 121
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 8
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 8
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 8
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 8
        //   51: iload 4
        //   53: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   56: iload 5
        //   58: ifeq +68 -> 126
        //   61: iconst_1
        //   62: istore 4
        //   64: aload 8
        //   66: iload 4
        //   68: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   71: iload 6
        //   73: ifeq +59 -> 132
        //   76: iload 7
        //   78: istore 4
        //   80: aload 8
        //   82: iload 4
        //   84: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   87: aload_0
        //   88: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   91: sipush 9028
        //   94: aload 8
        //   96: aload 9
        //   98: iconst_0
        //   99: invokeinterface 46 5 0
        //   104: pop
        //   105: aload 9
        //   107: invokevirtual 49	android/os/Parcel:readException	()V
        //   110: aload 9
        //   112: invokevirtual 52	android/os/Parcel:recycle	()V
        //   115: aload 8
        //   117: invokevirtual 52	android/os/Parcel:recycle	()V
        //   120: return
        //   121: aconst_null
        //   122: astore_1
        //   123: goto -92 -> 31
        //   126: iconst_0
        //   127: istore 4
        //   129: goto -65 -> 64
        //   132: iconst_0
        //   133: istore 4
        //   135: goto -55 -> 80
        //   138: astore_1
        //   139: aload 9
        //   141: invokevirtual 52	android/os/Parcel:recycle	()V
        //   144: aload 8
        //   146: invokevirtual 52	android/os/Parcel:recycle	()V
        //   149: aload_1
        //   150: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	151	0	this	Proxy
        //   0	151	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	151	2	paramString1	String
        //   0	151	3	paramString2	String
        //   0	151	4	paramInt	int
        //   0	151	5	paramBoolean1	boolean
        //   0	151	6	paramBoolean2	boolean
        //   1	76	7	i	int
        //   6	139	8	localParcel1	Parcel
        //   11	129	9	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	138	finally
        //   24	31	138	finally
        //   31	56	138	finally
        //   64	71	138	finally
        //   80	110	138	finally
      }
      
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, SnapshotMetadataChange paramSnapshotMetadataChange, Contents paramContents)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label145:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramIGamesCallbacks != null)
            {
              paramIGamesCallbacks = paramIGamesCallbacks.asBinder();
              localParcel1.writeStrongBinder(paramIGamesCallbacks);
              localParcel1.writeString(paramString1);
              localParcel1.writeString(paramString2);
              if (paramSnapshotMetadataChange != null)
              {
                localParcel1.writeInt(1);
                paramSnapshotMetadataChange.writeToParcel(localParcel1, 0);
                if (paramContents == null) {
                  break label145;
                }
                localParcel1.writeInt(1);
                paramContents.writeToParcel(localParcel1, 0);
                this.lb.transact(12033, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              paramIGamesCallbacks = null;
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
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +77 -> 98
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 6
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: iload 4
        //   51: ifeq +6 -> 57
        //   54: iconst_1
        //   55: istore 5
        //   57: aload 6
        //   59: iload 5
        //   61: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   64: aload_0
        //   65: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   68: sipush 6002
        //   71: aload 6
        //   73: aload 7
        //   75: iconst_0
        //   76: invokeinterface 46 5 0
        //   81: pop
        //   82: aload 7
        //   84: invokevirtual 49	android/os/Parcel:readException	()V
        //   87: aload 7
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 6
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: return
        //   98: aconst_null
        //   99: astore_1
        //   100: goto -69 -> 31
        //   103: astore_1
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: aload 6
        //   111: invokevirtual 52	android/os/Parcel:recycle	()V
        //   114: aload_1
        //   115: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	116	0	this	Proxy
        //   0	116	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	116	2	paramString1	String
        //   0	116	3	paramString2	String
        //   0	116	4	paramBoolean	boolean
        //   1	59	5	i	int
        //   6	104	6	localParcel1	Parcel
        //   11	94	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	103	finally
        //   24	31	103	finally
        //   31	49	103	finally
        //   57	87	103	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 7
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +95 -> 116
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 8
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 8
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 8
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 8
        //   51: aload 4
        //   53: invokevirtual 147	android/os/Parcel:writeIntArray	([I)V
        //   56: aload 8
        //   58: iload 5
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: iload 7
        //   65: istore 5
        //   67: iload 6
        //   69: ifeq +6 -> 75
        //   72: iconst_1
        //   73: istore 5
        //   75: aload 8
        //   77: iload 5
        //   79: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   82: aload_0
        //   83: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   86: sipush 12015
        //   89: aload 8
        //   91: aload 9
        //   93: iconst_0
        //   94: invokeinterface 46 5 0
        //   99: pop
        //   100: aload 9
        //   102: invokevirtual 49	android/os/Parcel:readException	()V
        //   105: aload 9
        //   107: invokevirtual 52	android/os/Parcel:recycle	()V
        //   110: aload 8
        //   112: invokevirtual 52	android/os/Parcel:recycle	()V
        //   115: return
        //   116: aconst_null
        //   117: astore_1
        //   118: goto -87 -> 31
        //   121: astore_1
        //   122: aload 9
        //   124: invokevirtual 52	android/os/Parcel:recycle	()V
        //   127: aload 8
        //   129: invokevirtual 52	android/os/Parcel:recycle	()V
        //   132: aload_1
        //   133: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	134	0	this	Proxy
        //   0	134	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	134	2	paramString1	String
        //   0	134	3	paramString2	String
        //   0	134	4	paramArrayOfInt	int[]
        //   0	134	5	paramInt	int
        //   0	134	6	paramBoolean	boolean
        //   1	63	7	i	int
        //   6	122	8	localParcel1	Parcel
        //   11	112	9	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	121	finally
        //   24	31	121	finally
        //   31	63	121	finally
        //   75	105	121	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, String[] paramArrayOfString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +69 -> 87
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload 5
        //   48: aload 4
        //   50: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   53: aload_0
        //   54: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   57: sipush 10008
        //   60: aload 5
        //   62: aload 6
        //   64: iconst_0
        //   65: invokeinterface 46 5 0
        //   70: pop
        //   71: aload 6
        //   73: invokevirtual 49	android/os/Parcel:readException	()V
        //   76: aload 6
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: aload 5
        //   83: invokevirtual 52	android/os/Parcel:recycle	()V
        //   86: return
        //   87: aconst_null
        //   88: astore_1
        //   89: goto -61 -> 28
        //   92: astore_1
        //   93: aload 6
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload 5
        //   100: invokevirtual 52	android/os/Parcel:recycle	()V
        //   103: aload_1
        //   104: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	Proxy
        //   0	105	1	paramIGamesCallbacks	IGamesCallbacks
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
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +84 -> 105
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 7
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 7
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 7
        //   51: aload 4
        //   53: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   56: iload 5
        //   58: ifeq +6 -> 64
        //   61: iconst_1
        //   62: istore 6
        //   64: aload 7
        //   66: iload 6
        //   68: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   71: aload_0
        //   72: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   75: sipush 12028
        //   78: aload 7
        //   80: aload 8
        //   82: iconst_0
        //   83: invokeinterface 46 5 0
        //   88: pop
        //   89: aload 8
        //   91: invokevirtual 49	android/os/Parcel:readException	()V
        //   94: aload 8
        //   96: invokevirtual 52	android/os/Parcel:recycle	()V
        //   99: aload 7
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: return
        //   105: aconst_null
        //   106: astore_1
        //   107: goto -76 -> 31
        //   110: astore_1
        //   111: aload 8
        //   113: invokevirtual 52	android/os/Parcel:recycle	()V
        //   116: aload 7
        //   118: invokevirtual 52	android/os/Parcel:recycle	()V
        //   121: aload_1
        //   122: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	123	0	this	Proxy
        //   0	123	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	123	2	paramString1	String
        //   0	123	3	paramString2	String
        //   0	123	4	paramArrayOfString	String[]
        //   0	123	5	paramBoolean	boolean
        //   1	66	6	i	int
        //   6	111	7	localParcel1	Parcel
        //   11	101	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	110	finally
        //   24	31	110	finally
        //   31	56	110	finally
        //   64	94	110	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +70 -> 91
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 5
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: iload_3
        //   44: ifeq +6 -> 50
        //   47: iconst_1
        //   48: istore 4
        //   50: aload 5
        //   52: iload 4
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload_0
        //   58: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   61: sipush 5054
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokeinterface 46 5 0
        //   74: pop
        //   75: aload 6
        //   77: invokevirtual 49	android/os/Parcel:readException	()V
        //   80: aload 6
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: return
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -62 -> 31
        //   96: astore_1
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	109	2	paramString	String
        //   0	109	3	paramBoolean	boolean
        //   1	52	4	i	int
        //   6	97	5	localParcel1	Parcel
        //   11	87	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	96	finally
        //   24	31	96	finally
        //   31	43	96	finally
        //   50	80	96	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult[] paramArrayOfParticipantResult)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +77 -> 95
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 6
        //   42: aload_3
        //   43: invokevirtual 70	android/os/Parcel:writeByteArray	([B)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   53: aload 6
        //   55: aload 5
        //   57: iconst_0
        //   58: invokevirtual 116	android/os/Parcel:writeTypedArray	([Landroid/os/Parcelable;I)V
        //   61: aload_0
        //   62: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   65: sipush 8007
        //   68: aload 6
        //   70: aload 7
        //   72: iconst_0
        //   73: invokeinterface 46 5 0
        //   78: pop
        //   79: aload 7
        //   81: invokevirtual 49	android/os/Parcel:readException	()V
        //   84: aload 7
        //   86: invokevirtual 52	android/os/Parcel:recycle	()V
        //   89: aload 6
        //   91: invokevirtual 52	android/os/Parcel:recycle	()V
        //   94: return
        //   95: aconst_null
        //   96: astore_1
        //   97: goto -69 -> 28
        //   100: astore_1
        //   101: aload 7
        //   103: invokevirtual 52	android/os/Parcel:recycle	()V
        //   106: aload 6
        //   108: invokevirtual 52	android/os/Parcel:recycle	()V
        //   111: aload_1
        //   112: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	113	0	this	Proxy
        //   0	113	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	113	2	paramString1	String
        //   0	113	3	paramArrayOfByte	byte[]
        //   0	113	4	paramString2	String
        //   0	113	5	paramArrayOfParticipantResult	ParticipantResult[]
        //   3	104	6	localParcel1	Parcel
        //   8	94	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	100	finally
        //   21	28	100	finally
        //   28	84	100	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, byte[] paramArrayOfByte, ParticipantResult[] paramArrayOfParticipantResult)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +70 -> 88
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 70	android/os/Parcel:writeByteArray	([B)V
        //   46: aload 5
        //   48: aload 4
        //   50: iconst_0
        //   51: invokevirtual 116	android/os/Parcel:writeTypedArray	([Landroid/os/Parcelable;I)V
        //   54: aload_0
        //   55: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   58: sipush 8008
        //   61: aload 5
        //   63: aload 6
        //   65: iconst_0
        //   66: invokeinterface 46 5 0
        //   71: pop
        //   72: aload 6
        //   74: invokevirtual 49	android/os/Parcel:readException	()V
        //   77: aload 6
        //   79: invokevirtual 52	android/os/Parcel:recycle	()V
        //   82: aload 5
        //   84: invokevirtual 52	android/os/Parcel:recycle	()V
        //   87: return
        //   88: aconst_null
        //   89: astore_1
        //   90: goto -62 -> 28
        //   93: astore_1
        //   94: aload 6
        //   96: invokevirtual 52	android/os/Parcel:recycle	()V
        //   99: aload 5
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload_1
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	Proxy
        //   0	106	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	106	2	paramString	String
        //   0	106	3	paramArrayOfByte	byte[]
        //   0	106	4	paramArrayOfParticipantResult	ParticipantResult[]
        //   3	97	5	localParcel1	Parcel
        //   8	87	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	93	finally
        //   21	28	93	finally
        //   28	77	93	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, int[] paramArrayOfInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 147	android/os/Parcel:writeIntArray	([I)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 8017
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	98	2	paramString	String
        //   0	98	3	paramArrayOfInt	int[]
        //   3	89	4	localParcel1	Parcel
        //   8	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	85	finally
        //   21	28	85	finally
        //   28	69	85	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String paramString, String[] paramArrayOfString, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 7
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 8
        //   10: aload 7
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +83 -> 101
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 7
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 7
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 7
        //   42: aload_3
        //   43: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   46: aload 7
        //   48: iload 4
        //   50: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   53: aload 7
        //   55: aload 5
        //   57: invokevirtual 70	android/os/Parcel:writeByteArray	([B)V
        //   60: aload 7
        //   62: iload 6
        //   64: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   67: aload_0
        //   68: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   71: sipush 10005
        //   74: aload 7
        //   76: aload 8
        //   78: iconst_0
        //   79: invokeinterface 46 5 0
        //   84: pop
        //   85: aload 8
        //   87: invokevirtual 49	android/os/Parcel:readException	()V
        //   90: aload 8
        //   92: invokevirtual 52	android/os/Parcel:recycle	()V
        //   95: aload 7
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: return
        //   101: aconst_null
        //   102: astore_1
        //   103: goto -75 -> 28
        //   106: astore_1
        //   107: aload 8
        //   109: invokevirtual 52	android/os/Parcel:recycle	()V
        //   112: aload 7
        //   114: invokevirtual 52	android/os/Parcel:recycle	()V
        //   117: aload_1
        //   118: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	119	0	this	Proxy
        //   0	119	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	119	2	paramString	String
        //   0	119	3	paramArrayOfString	String[]
        //   0	119	4	paramInt1	int
        //   0	119	5	paramArrayOfByte	byte[]
        //   0	119	6	paramInt2	int
        //   3	110	7	localParcel1	Parcel
        //   8	100	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	106	finally
        //   21	28	106	finally
        //   28	90	106	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iload_2
        //   37: ifeq +5 -> 42
        //   40: iconst_1
        //   41: istore_3
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 6001
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 46 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 49	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	Proxy
        //   0	100	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	100	2	paramBoolean	boolean
        //   1	44	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	36	87	finally
        //   42	71	87	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +78 -> 99
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: iload_2
        //   38: ifeq +66 -> 104
        //   41: aload 5
        //   43: iload 4
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_3
        //   49: ifnull +61 -> 110
        //   52: aload 5
        //   54: iconst_1
        //   55: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   58: aload_3
        //   59: aload 5
        //   61: iconst_0
        //   62: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   65: aload_0
        //   66: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   69: sipush 5063
        //   72: aload 5
        //   74: aload 6
        //   76: iconst_0
        //   77: invokeinterface 46 5 0
        //   82: pop
        //   83: aload 6
        //   85: invokevirtual 49	android/os/Parcel:readException	()V
        //   88: aload 6
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 5
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: return
        //   99: aconst_null
        //   100: astore_1
        //   101: goto -70 -> 31
        //   104: iconst_0
        //   105: istore 4
        //   107: goto -66 -> 41
        //   110: aload 5
        //   112: iconst_0
        //   113: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   116: goto -51 -> 65
        //   119: astore_1
        //   120: aload 6
        //   122: invokevirtual 52	android/os/Parcel:recycle	()V
        //   125: aload 5
        //   127: invokevirtual 52	android/os/Parcel:recycle	()V
        //   130: aload_1
        //   131: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	132	0	this	Proxy
        //   0	132	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	132	2	paramBoolean	boolean
        //   0	132	3	paramBundle	Bundle
        //   1	105	4	i	int
        //   6	120	5	localParcel1	Parcel
        //   11	110	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	119	finally
        //   24	31	119	finally
        //   31	37	119	finally
        //   41	48	119	finally
        //   52	65	119	finally
        //   65	88	119	finally
        //   110	116	119	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean, String[] paramArrayOfString)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +70 -> 91
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: iload_2
        //   38: ifeq +6 -> 44
        //   41: iconst_1
        //   42: istore 4
        //   44: aload 5
        //   46: iload 4
        //   48: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   51: aload 5
        //   53: aload_3
        //   54: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   57: aload_0
        //   58: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   61: sipush 12031
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokeinterface 46 5 0
        //   74: pop
        //   75: aload 6
        //   77: invokevirtual 49	android/os/Parcel:readException	()V
        //   80: aload 6
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: return
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -62 -> 31
        //   96: astore_1
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	109	2	paramBoolean	boolean
        //   0	109	3	paramArrayOfString	String[]
        //   1	46	4	i	int
        //   6	97	5	localParcel1	Parcel
        //   11	87	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	96	finally
        //   24	31	96	finally
        //   31	37	96	finally
        //   44	80	96	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int[] paramArrayOfInt)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 147	android/os/Parcel:writeIntArray	([I)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 8003
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramArrayOfInt	int[]
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +78 -> 99
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: aload_2
        //   40: invokevirtual 147	android/os/Parcel:writeIntArray	([I)V
        //   43: aload 6
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 5
        //   51: istore_3
        //   52: iload 4
        //   54: ifeq +5 -> 59
        //   57: iconst_1
        //   58: istore_3
        //   59: aload 6
        //   61: iload_3
        //   62: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   65: aload_0
        //   66: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   69: sipush 12010
        //   72: aload 6
        //   74: aload 7
        //   76: iconst_0
        //   77: invokeinterface 46 5 0
        //   82: pop
        //   83: aload 7
        //   85: invokevirtual 49	android/os/Parcel:readException	()V
        //   88: aload 7
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 6
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: return
        //   99: aconst_null
        //   100: astore_1
        //   101: goto -70 -> 31
        //   104: astore_1
        //   105: aload 7
        //   107: invokevirtual 52	android/os/Parcel:recycle	()V
        //   110: aload 6
        //   112: invokevirtual 52	android/os/Parcel:recycle	()V
        //   115: aload_1
        //   116: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	Proxy
        //   0	117	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	117	2	paramArrayOfInt	int[]
        //   0	117	3	paramInt	int
        //   0	117	4	paramBoolean	boolean
        //   1	49	5	i	int
        //   6	105	6	localParcel1	Parcel
        //   11	95	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	104	finally
        //   24	31	104	finally
        //   31	49	104	finally
        //   59	88	104	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 10006
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramArrayOfString	String[]
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void a(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +70 -> 91
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 5
        //   39: aload_2
        //   40: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   43: iload_3
        //   44: ifeq +6 -> 50
        //   47: iconst_1
        //   48: istore 4
        //   50: aload 5
        //   52: iload 4
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload_0
        //   58: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   61: sipush 12029
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokeinterface 46 5 0
        //   74: pop
        //   75: aload 6
        //   77: invokevirtual 49	android/os/Parcel:readException	()V
        //   80: aload 6
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: return
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -62 -> 31
        //   96: astore_1
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	109	2	paramArrayOfString	String[]
        //   0	109	3	paramBoolean	boolean
        //   1	52	4	i	int
        //   6	97	5	localParcel1	Parcel
        //   11	87	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	96	finally
        //   24	31	96	finally
        //   31	43	96	finally
        //   50	80	96	finally
      }
      
      /* Error */
      public void a(String paramString, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: aload_1
        //   20: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 4
        //   25: aload_2
        //   26: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   29: aload_3
        //   30: ifnull +50 -> 80
        //   33: aload 4
        //   35: iconst_1
        //   36: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   39: aload_3
        //   40: aload 4
        //   42: iconst_0
        //   43: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 13002
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aload 4
        //   82: iconst_0
        //   83: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   86: goto -40 -> 46
        //   89: astore_1
        //   90: aload 5
        //   92: invokevirtual 52	android/os/Parcel:recycle	()V
        //   95: aload 4
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload_1
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	Proxy
        //   0	102	1	paramString	String
        //   0	102	2	paramIBinder	IBinder
        //   0	102	3	paramBundle	Bundle
        //   3	93	4	localParcel1	Parcel
        //   8	83	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	29	89	finally
        //   33	46	89	finally
        //   46	69	89	finally
        //   80	86	89	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public int b(byte[] paramArrayOfByte, String paramString, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeByteArray(paramArrayOfByte);
          localParcel1.writeString(paramString);
          localParcel1.writeStringArray(paramArrayOfString);
          this.lb.transact(5034, localParcel1, localParcel2, 0);
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
      
      /* Error */
      public Intent b(int paramInt1, int paramInt2, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 6
        //   22: iload_1
        //   23: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   26: aload 6
        //   28: iload_2
        //   29: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   32: iload 4
        //   34: istore_1
        //   35: iload_3
        //   36: ifeq +5 -> 41
        //   39: iconst_1
        //   40: istore_1
        //   41: aload 6
        //   43: iload_1
        //   44: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   47: aload_0
        //   48: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   51: sipush 9009
        //   54: aload 6
        //   56: aload 7
        //   58: iconst_0
        //   59: invokeinterface 46 5 0
        //   64: pop
        //   65: aload 7
        //   67: invokevirtual 49	android/os/Parcel:readException	()V
        //   70: aload 7
        //   72: invokevirtual 77	android/os/Parcel:readInt	()I
        //   75: ifeq +31 -> 106
        //   78: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   81: aload 7
        //   83: invokeinterface 90 2 0
        //   88: checkcast 80	android/content/Intent
        //   91: astore 5
        //   93: aload 7
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: invokevirtual 52	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: areturn
        //   106: aconst_null
        //   107: astore 5
        //   109: goto -16 -> 93
        //   112: astore 5
        //   114: aload 7
        //   116: invokevirtual 52	android/os/Parcel:recycle	()V
        //   119: aload 6
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 5
        //   126: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	127	0	this	Proxy
        //   0	127	1	paramInt1	int
        //   0	127	2	paramInt2	int
        //   0	127	3	paramBoolean	boolean
        //   1	32	4	i	int
        //   91	17	5	localIntent	Intent
        //   112	13	5	localObject	Object
        //   6	114	6	localParcel1	Parcel
        //   11	104	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	32	112	finally
        //   41	93	112	finally
      }
      
      /* Error */
      public Intent b(int[] paramArrayOfInt)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 147	android/os/Parcel:writeIntArray	([I)V
        //   19: aload_0
        //   20: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 12030
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 46 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 49	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 77	android/os/Parcel:readInt	()I
        //   43: ifeq +26 -> 69
        //   46: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 90 2 0
        //   55: checkcast 80	android/content/Intent
        //   58: astore_1
        //   59: aload_3
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_2
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: aload_1
        //   68: areturn
        //   69: aconst_null
        //   70: astore_1
        //   71: goto -12 -> 59
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramArrayOfInt	int[]
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	59	74	finally
      }
      
      public void b(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.lb.transact(8021, localParcel1, localParcel2, 0);
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
      public void b(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5017
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +82 -> 103
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: iload_2
        //   40: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   43: iload_3
        //   44: ifeq +64 -> 108
        //   47: iconst_1
        //   48: istore_2
        //   49: aload 6
        //   51: iload_2
        //   52: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   55: iload 4
        //   57: ifeq +56 -> 113
        //   60: iload 5
        //   62: istore_2
        //   63: aload 6
        //   65: iload_2
        //   66: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   69: aload_0
        //   70: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   73: sipush 5046
        //   76: aload 6
        //   78: aload 7
        //   80: iconst_0
        //   81: invokeinterface 46 5 0
        //   86: pop
        //   87: aload 7
        //   89: invokevirtual 49	android/os/Parcel:readException	()V
        //   92: aload 7
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: return
        //   103: aconst_null
        //   104: astore_1
        //   105: goto -74 -> 31
        //   108: iconst_0
        //   109: istore_2
        //   110: goto -61 -> 49
        //   113: iconst_0
        //   114: istore_2
        //   115: goto -52 -> 63
        //   118: astore_1
        //   119: aload 7
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 6
        //   126: invokevirtual 52	android/os/Parcel:recycle	()V
        //   129: aload_1
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramInt	int
        //   0	131	3	paramBoolean1	boolean
        //   0	131	4	paramBoolean2	boolean
        //   1	60	5	i	int
        //   6	119	6	localParcel1	Parcel
        //   11	109	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	118	finally
        //   24	31	118	finally
        //   31	43	118	finally
        //   49	55	118	finally
        //   63	92	118	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +56 -> 74
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: lload_2
        //   37: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   40: aload_0
        //   41: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   44: sipush 8012
        //   47: aload 4
        //   49: aload 5
        //   51: iconst_0
        //   52: invokeinterface 46 5 0
        //   57: pop
        //   58: aload 5
        //   60: invokevirtual 49	android/os/Parcel:readException	()V
        //   63: aload 5
        //   65: invokevirtual 52	android/os/Parcel:recycle	()V
        //   68: aload 4
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: return
        //   74: aconst_null
        //   75: astore_1
        //   76: goto -48 -> 28
        //   79: astore_1
        //   80: aload 5
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 4
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: aload_1
        //   91: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	92	0	this	Proxy
        //   0	92	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	92	2	paramLong	long
        //   3	83	4	localParcel1	Parcel
        //   8	73	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	79	finally
        //   21	28	79	finally
        //   28	63	79	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +63 -> 81
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: lload_2
        //   37: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   40: aload 5
        //   42: aload 4
        //   44: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   47: aload_0
        //   48: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   51: sipush 8020
        //   54: aload 5
        //   56: aload 6
        //   58: iconst_0
        //   59: invokeinterface 46 5 0
        //   64: pop
        //   65: aload 6
        //   67: invokevirtual 49	android/os/Parcel:readException	()V
        //   70: aload 6
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload 5
        //   77: invokevirtual 52	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore_1
        //   83: goto -55 -> 28
        //   86: astore_1
        //   87: aload 6
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 5
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	Proxy
        //   0	99	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	99	2	paramLong	long
        //   0	99	4	paramString	String
        //   3	90	5	localParcel1	Parcel
        //   8	80	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	86	finally
        //   21	28	86	finally
        //   28	70	86	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5018
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: iload_3
        //   43: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 12023
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	98	2	paramString	String
        //   0	98	3	paramInt	int
        //   3	89	4	localParcel1	Parcel
        //   8	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	85	finally
        //   21	28	85	finally
        //   28	69	85	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 7
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +92 -> 113
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 8
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 8
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 8
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: aload 8
        //   51: iload 4
        //   53: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   56: aload 8
        //   58: iload 5
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: iload 7
        //   65: istore_3
        //   66: iload 6
        //   68: ifeq +5 -> 73
        //   71: iconst_1
        //   72: istore_3
        //   73: aload 8
        //   75: iload_3
        //   76: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   79: aload_0
        //   80: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   83: sipush 5020
        //   86: aload 8
        //   88: aload 9
        //   90: iconst_0
        //   91: invokeinterface 46 5 0
        //   96: pop
        //   97: aload 9
        //   99: invokevirtual 49	android/os/Parcel:readException	()V
        //   102: aload 9
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload 8
        //   109: invokevirtual 52	android/os/Parcel:recycle	()V
        //   112: return
        //   113: aconst_null
        //   114: astore_1
        //   115: goto -84 -> 31
        //   118: astore_1
        //   119: aload 9
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 8
        //   126: invokevirtual 52	android/os/Parcel:recycle	()V
        //   129: aload_1
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramString	String
        //   0	131	3	paramInt1	int
        //   0	131	4	paramInt2	int
        //   0	131	5	paramInt3	int
        //   0	131	6	paramBoolean	boolean
        //   1	63	7	i	int
        //   6	119	8	localParcel1	Parcel
        //   11	109	9	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	118	finally
        //   24	31	118	finally
        //   31	63	118	finally
        //   73	102	118	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +88 -> 106
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 6
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 6
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 6
        //   42: iload_3
        //   43: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   46: aload 6
        //   48: aload 4
        //   50: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   53: aload 5
        //   55: ifnull +56 -> 111
        //   58: aload 6
        //   60: iconst_1
        //   61: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   72: aload_0
        //   73: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   76: sipush 7003
        //   79: aload 6
        //   81: aload 7
        //   83: iconst_0
        //   84: invokeinterface 46 5 0
        //   89: pop
        //   90: aload 7
        //   92: invokevirtual 49	android/os/Parcel:readException	()V
        //   95: aload 7
        //   97: invokevirtual 52	android/os/Parcel:recycle	()V
        //   100: aload 6
        //   102: invokevirtual 52	android/os/Parcel:recycle	()V
        //   105: return
        //   106: aconst_null
        //   107: astore_1
        //   108: goto -80 -> 28
        //   111: aload 6
        //   113: iconst_0
        //   114: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   117: goto -45 -> 72
        //   120: astore_1
        //   121: aload 7
        //   123: invokevirtual 52	android/os/Parcel:recycle	()V
        //   126: aload 6
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload_1
        //   132: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	133	0	this	Proxy
        //   0	133	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	133	2	paramString	String
        //   0	133	3	paramInt	int
        //   0	133	4	paramIBinder	IBinder
        //   0	133	5	paramBundle	Bundle
        //   3	124	6	localParcel1	Parcel
        //   8	114	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	120	finally
        //   21	28	120	finally
        //   28	53	120	finally
        //   58	72	120	finally
        //   72	95	120	finally
        //   111	117	120	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +78 -> 99
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 6
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 5
        //   51: istore_3
        //   52: iload 4
        //   54: ifeq +5 -> 59
        //   57: iconst_1
        //   58: istore_3
        //   59: aload 6
        //   61: iload_3
        //   62: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   65: aload_0
        //   66: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   69: sipush 10017
        //   72: aload 6
        //   74: aload 7
        //   76: iconst_0
        //   77: invokeinterface 46 5 0
        //   82: pop
        //   83: aload 7
        //   85: invokevirtual 49	android/os/Parcel:readException	()V
        //   88: aload 7
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 6
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: return
        //   99: aconst_null
        //   100: astore_1
        //   101: goto -70 -> 31
        //   104: astore_1
        //   105: aload 7
        //   107: invokevirtual 52	android/os/Parcel:recycle	()V
        //   110: aload 6
        //   112: invokevirtual 52	android/os/Parcel:recycle	()V
        //   115: aload_1
        //   116: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	Proxy
        //   0	117	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	117	2	paramString	String
        //   0	117	3	paramInt	int
        //   0	117	4	paramBoolean	boolean
        //   1	49	5	i	int
        //   6	105	6	localParcel1	Parcel
        //   11	95	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	104	finally
        //   24	31	104	finally
        //   31	49	104	finally
        //   59	88	104	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +89 -> 110
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 7
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 7
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 4
        //   51: ifeq +64 -> 115
        //   54: iconst_1
        //   55: istore_3
        //   56: aload 7
        //   58: iload_3
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: iload 5
        //   64: ifeq +56 -> 120
        //   67: iload 6
        //   69: istore_3
        //   70: aload 7
        //   72: iload_3
        //   73: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   76: aload_0
        //   77: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   80: sipush 5501
        //   83: aload 7
        //   85: aload 8
        //   87: iconst_0
        //   88: invokeinterface 46 5 0
        //   93: pop
        //   94: aload 8
        //   96: invokevirtual 49	android/os/Parcel:readException	()V
        //   99: aload 8
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: return
        //   110: aconst_null
        //   111: astore_1
        //   112: goto -81 -> 31
        //   115: iconst_0
        //   116: istore_3
        //   117: goto -61 -> 56
        //   120: iconst_0
        //   121: istore_3
        //   122: goto -52 -> 70
        //   125: astore_1
        //   126: aload 8
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload 7
        //   133: invokevirtual 52	android/os/Parcel:recycle	()V
        //   136: aload_1
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramString	String
        //   0	138	3	paramInt	int
        //   0	138	4	paramBoolean1	boolean
        //   0	138	5	paramBoolean2	boolean
        //   1	67	6	i	int
        //   6	126	7	localParcel1	Parcel
        //   11	116	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	125	finally
        //   24	31	125	finally
        //   31	49	125	finally
        //   56	62	125	finally
        //   70	99	125	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, IBinder paramIBinder, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +81 -> 99
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   46: aload 4
        //   48: ifnull +56 -> 104
        //   51: aload 5
        //   53: iconst_1
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload 4
        //   59: aload 5
        //   61: iconst_0
        //   62: invokevirtual 128	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   65: aload_0
        //   66: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   69: sipush 5024
        //   72: aload 5
        //   74: aload 6
        //   76: iconst_0
        //   77: invokeinterface 46 5 0
        //   82: pop
        //   83: aload 6
        //   85: invokevirtual 49	android/os/Parcel:readException	()V
        //   88: aload 6
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 5
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: return
        //   99: aconst_null
        //   100: astore_1
        //   101: goto -73 -> 28
        //   104: aload 5
        //   106: iconst_0
        //   107: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   110: goto -45 -> 65
        //   113: astore_1
        //   114: aload 6
        //   116: invokevirtual 52	android/os/Parcel:recycle	()V
        //   119: aload 5
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload_1
        //   125: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	126	0	this	Proxy
        //   0	126	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	126	2	paramString	String
        //   0	126	3	paramIBinder	IBinder
        //   0	126	4	paramBundle	Bundle
        //   3	117	5	localParcel1	Parcel
        //   8	107	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	113	finally
        //   21	28	113	finally
        //   28	46	113	finally
        //   51	65	113	finally
        //   65	88	113	finally
        //   104	110	113	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 5041
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
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
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 8
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 9
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 10
        //   13: aload 9
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +102 -> 123
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 9
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 9
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 9
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 9
        //   51: iload 4
        //   53: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   56: aload 9
        //   58: iload 5
        //   60: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   63: aload 9
        //   65: iload 6
        //   67: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   70: iload 8
        //   72: istore 4
        //   74: iload 7
        //   76: ifeq +6 -> 82
        //   79: iconst_1
        //   80: istore 4
        //   82: aload 9
        //   84: iload 4
        //   86: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   89: aload_0
        //   90: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   93: sipush 5040
        //   96: aload 9
        //   98: aload 10
        //   100: iconst_0
        //   101: invokeinterface 46 5 0
        //   106: pop
        //   107: aload 10
        //   109: invokevirtual 49	android/os/Parcel:readException	()V
        //   112: aload 10
        //   114: invokevirtual 52	android/os/Parcel:recycle	()V
        //   117: aload 9
        //   119: invokevirtual 52	android/os/Parcel:recycle	()V
        //   122: return
        //   123: aconst_null
        //   124: astore_1
        //   125: goto -94 -> 31
        //   128: astore_1
        //   129: aload 10
        //   131: invokevirtual 52	android/os/Parcel:recycle	()V
        //   134: aload 9
        //   136: invokevirtual 52	android/os/Parcel:recycle	()V
        //   139: aload_1
        //   140: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	141	0	this	Proxy
        //   0	141	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	141	2	paramString1	String
        //   0	141	3	paramString2	String
        //   0	141	4	paramInt1	int
        //   0	141	5	paramInt2	int
        //   0	141	6	paramInt3	int
        //   0	141	7	paramBoolean	boolean
        //   1	70	8	i	int
        //   6	129	9	localParcel1	Parcel
        //   11	119	10	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	128	finally
        //   24	31	128	finally
        //   31	70	128	finally
        //   82	112	128	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 7
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +100 -> 121
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 8
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 8
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 8
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 8
        //   51: iload 4
        //   53: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   56: iload 5
        //   58: ifeq +68 -> 126
        //   61: iconst_1
        //   62: istore 4
        //   64: aload 8
        //   66: iload 4
        //   68: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   71: iload 6
        //   73: ifeq +59 -> 132
        //   76: iload 7
        //   78: istore 4
        //   80: aload 8
        //   82: iload 4
        //   84: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   87: aload_0
        //   88: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   91: sipush 12018
        //   94: aload 8
        //   96: aload 9
        //   98: iconst_0
        //   99: invokeinterface 46 5 0
        //   104: pop
        //   105: aload 9
        //   107: invokevirtual 49	android/os/Parcel:readException	()V
        //   110: aload 9
        //   112: invokevirtual 52	android/os/Parcel:recycle	()V
        //   115: aload 8
        //   117: invokevirtual 52	android/os/Parcel:recycle	()V
        //   120: return
        //   121: aconst_null
        //   122: astore_1
        //   123: goto -92 -> 31
        //   126: iconst_0
        //   127: istore 4
        //   129: goto -65 -> 64
        //   132: iconst_0
        //   133: istore 4
        //   135: goto -55 -> 80
        //   138: astore_1
        //   139: aload 9
        //   141: invokevirtual 52	android/os/Parcel:recycle	()V
        //   144: aload 8
        //   146: invokevirtual 52	android/os/Parcel:recycle	()V
        //   149: aload_1
        //   150: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	151	0	this	Proxy
        //   0	151	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	151	2	paramString1	String
        //   0	151	3	paramString2	String
        //   0	151	4	paramInt	int
        //   0	151	5	paramBoolean1	boolean
        //   0	151	6	paramBoolean2	boolean
        //   1	76	7	i	int
        //   6	139	8	localParcel1	Parcel
        //   11	129	9	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	138	finally
        //   24	31	138	finally
        //   31	56	138	finally
        //   64	71	138	finally
        //   80	110	138	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +77 -> 98
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 6
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: iload 4
        //   51: ifeq +6 -> 57
        //   54: iconst_1
        //   55: istore 5
        //   57: aload 6
        //   59: iload 5
        //   61: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   64: aload_0
        //   65: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   68: sipush 6506
        //   71: aload 6
        //   73: aload 7
        //   75: iconst_0
        //   76: invokeinterface 46 5 0
        //   81: pop
        //   82: aload 7
        //   84: invokevirtual 49	android/os/Parcel:readException	()V
        //   87: aload 7
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 6
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: return
        //   98: aconst_null
        //   99: astore_1
        //   100: goto -69 -> 31
        //   103: astore_1
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: aload 6
        //   111: invokevirtual 52	android/os/Parcel:recycle	()V
        //   114: aload_1
        //   115: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	116	0	this	Proxy
        //   0	116	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	116	2	paramString1	String
        //   0	116	3	paramString2	String
        //   0	116	4	paramBoolean	boolean
        //   1	59	5	i	int
        //   6	104	6	localParcel1	Parcel
        //   11	94	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	103	finally
        //   24	31	103	finally
        //   31	49	103	finally
        //   57	87	103	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +70 -> 91
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 5
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: iload_3
        //   44: ifeq +6 -> 50
        //   47: iconst_1
        //   48: istore 4
        //   50: aload 5
        //   52: iload 4
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload_0
        //   58: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   61: sipush 6502
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokeinterface 46 5 0
        //   74: pop
        //   75: aload 6
        //   77: invokevirtual 49	android/os/Parcel:readException	()V
        //   80: aload 6
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: return
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -62 -> 31
        //   96: astore_1
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	109	2	paramString	String
        //   0	109	3	paramBoolean	boolean
        //   1	52	4	i	int
        //   6	97	5	localParcel1	Parcel
        //   11	87	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	96	finally
        //   24	31	96	finally
        //   31	43	96	finally
        //   50	80	96	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iload_2
        //   37: ifeq +5 -> 42
        //   40: iconst_1
        //   41: istore_3
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 6503
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 46 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 49	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	Proxy
        //   0	100	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	100	2	paramBoolean	boolean
        //   1	44	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	36	87	finally
        //   42	71	87	finally
      }
      
      /* Error */
      public void b(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 10007
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramArrayOfString	String[]
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void b(String paramString1, String paramString2, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          this.lb.transact(5051, localParcel1, localParcel2, 0);
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
      public Intent bC(String paramString)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 12034
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 46 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 49	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 77	android/os/Parcel:readInt	()I
        //   43: ifeq +26 -> 69
        //   46: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 90 2 0
        //   55: checkcast 80	android/content/Intent
        //   58: astore_1
        //   59: aload_3
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_2
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: aload_1
        //   68: areturn
        //   69: aconst_null
        //   70: astore_1
        //   71: goto -12 -> 59
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramString	String
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	59	74	finally
      }
      
      public String bE(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(5064, localParcel1, localParcel2, 0);
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
      
      public String bF(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(5035, localParcel1, localParcel2, 0);
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
      
      public void bG(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(5050, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int bH(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(5060, localParcel1, localParcel2, 0);
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
      
      /* Error */
      public Uri bI(String paramString)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 5066
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 46 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 49	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 77	android/os/Parcel:readInt	()I
        //   43: ifeq +26 -> 69
        //   46: getstatic 209	android/net/Uri:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 90 2 0
        //   55: checkcast 118	android/net/Uri
        //   58: astore_1
        //   59: aload_3
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_2
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: aload_1
        //   68: areturn
        //   69: aconst_null
        //   70: astore_1
        //   71: goto -12 -> 59
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramString	String
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	59	74	finally
      }
      
      public void bJ(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          this.lb.transact(8002, localParcel1, localParcel2, 0);
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
      public ParcelFileDescriptor bK(String paramString)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 9030
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 46 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 49	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 77	android/os/Parcel:readInt	()I
        //   43: ifeq +26 -> 69
        //   46: getstatic 215	android/os/ParcelFileDescriptor:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 90 2 0
        //   55: checkcast 214	android/os/ParcelFileDescriptor
        //   58: astore_1
        //   59: aload_3
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_2
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: aload_1
        //   68: areturn
        //   69: aconst_null
        //   70: astore_1
        //   71: goto -12 -> 59
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramString	String
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	59	74	finally
      }
      
      /* Error */
      public Intent bx(String paramString)
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
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   23: sipush 9004
        //   26: aload_2
        //   27: aload_3
        //   28: iconst_0
        //   29: invokeinterface 46 5 0
        //   34: pop
        //   35: aload_3
        //   36: invokevirtual 49	android/os/Parcel:readException	()V
        //   39: aload_3
        //   40: invokevirtual 77	android/os/Parcel:readInt	()I
        //   43: ifeq +26 -> 69
        //   46: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   49: aload_3
        //   50: invokeinterface 90 2 0
        //   55: checkcast 80	android/content/Intent
        //   58: astore_1
        //   59: aload_3
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_2
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: aload_1
        //   68: areturn
        //   69: aconst_null
        //   70: astore_1
        //   71: goto -12 -> 59
        //   74: astore_1
        //   75: aload_3
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramString	String
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	59	74	finally
      }
      
      public void c(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.lb.transact(10004, localParcel1, localParcel2, 0);
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
      public void c(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5022
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +82 -> 103
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: iload_2
        //   40: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   43: iload_3
        //   44: ifeq +64 -> 108
        //   47: iconst_1
        //   48: istore_2
        //   49: aload 6
        //   51: iload_2
        //   52: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   55: iload 4
        //   57: ifeq +56 -> 113
        //   60: iload 5
        //   62: istore_2
        //   63: aload 6
        //   65: iload_2
        //   66: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   69: aload_0
        //   70: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   73: sipush 5048
        //   76: aload 6
        //   78: aload 7
        //   80: iconst_0
        //   81: invokeinterface 46 5 0
        //   86: pop
        //   87: aload 7
        //   89: invokevirtual 49	android/os/Parcel:readException	()V
        //   92: aload 7
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: return
        //   103: aconst_null
        //   104: astore_1
        //   105: goto -74 -> 31
        //   108: iconst_0
        //   109: istore_2
        //   110: goto -61 -> 49
        //   113: iconst_0
        //   114: istore_2
        //   115: goto -52 -> 63
        //   118: astore_1
        //   119: aload 7
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 6
        //   126: invokevirtual 52	android/os/Parcel:recycle	()V
        //   129: aload_1
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramInt	int
        //   0	131	3	paramBoolean1	boolean
        //   0	131	4	paramBoolean2	boolean
        //   1	60	5	i	int
        //   6	119	6	localParcel1	Parcel
        //   11	109	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	118	finally
        //   24	31	118	finally
        //   31	43	118	finally
        //   49	55	118	finally
        //   63	92	118	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +56 -> 74
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: lload_2
        //   37: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   40: aload_0
        //   41: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   44: sipush 10001
        //   47: aload 4
        //   49: aload 5
        //   51: iconst_0
        //   52: invokeinterface 46 5 0
        //   57: pop
        //   58: aload 5
        //   60: invokevirtual 49	android/os/Parcel:readException	()V
        //   63: aload 5
        //   65: invokevirtual 52	android/os/Parcel:recycle	()V
        //   68: aload 4
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: return
        //   74: aconst_null
        //   75: astore_1
        //   76: goto -48 -> 28
        //   79: astore_1
        //   80: aload 5
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 4
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: aload_1
        //   91: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	92	0	this	Proxy
        //   0	92	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	92	2	paramLong	long
        //   3	83	4	localParcel1	Parcel
        //   8	73	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	79	finally
        //   21	28	79	finally
        //   28	63	79	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +63 -> 81
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: lload_2
        //   37: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   40: aload 5
        //   42: aload 4
        //   44: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   47: aload_0
        //   48: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   51: sipush 10003
        //   54: aload 5
        //   56: aload 6
        //   58: iconst_0
        //   59: invokeinterface 46 5 0
        //   64: pop
        //   65: aload 6
        //   67: invokevirtual 49	android/os/Parcel:readException	()V
        //   70: aload 6
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload 5
        //   77: invokevirtual 52	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore_1
        //   83: goto -55 -> 28
        //   86: astore_1
        //   87: aload 6
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 5
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	Proxy
        //   0	99	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	99	2	paramLong	long
        //   0	99	4	paramString	String
        //   3	90	5	localParcel1	Parcel
        //   8	80	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	86	finally
        //   21	28	86	finally
        //   28	70	86	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5032
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: iload_3
        //   43: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 12024
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	98	2	paramString	String
        //   0	98	3	paramInt	int
        //   3	89	4	localParcel1	Parcel
        //   8	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	85	finally
        //   21	28	85	finally
        //   28	69	85	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +89 -> 110
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 7
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 7
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 4
        //   51: ifeq +64 -> 115
        //   54: iconst_1
        //   55: istore_3
        //   56: aload 7
        //   58: iload_3
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: iload 5
        //   64: ifeq +56 -> 120
        //   67: iload 6
        //   69: istore_3
        //   70: aload 7
        //   72: iload_3
        //   73: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   76: aload_0
        //   77: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   80: sipush 9001
        //   83: aload 7
        //   85: aload 8
        //   87: iconst_0
        //   88: invokeinterface 46 5 0
        //   93: pop
        //   94: aload 8
        //   96: invokevirtual 49	android/os/Parcel:readException	()V
        //   99: aload 8
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: return
        //   110: aconst_null
        //   111: astore_1
        //   112: goto -81 -> 31
        //   115: iconst_0
        //   116: istore_3
        //   117: goto -61 -> 56
        //   120: iconst_0
        //   121: istore_3
        //   122: goto -52 -> 70
        //   125: astore_1
        //   126: aload 8
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload 7
        //   133: invokevirtual 52	android/os/Parcel:recycle	()V
        //   136: aload_1
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramString	String
        //   0	138	3	paramInt	int
        //   0	138	4	paramBoolean1	boolean
        //   0	138	5	paramBoolean2	boolean
        //   1	67	6	i	int
        //   6	126	7	localParcel1	Parcel
        //   11	116	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	125	finally
        //   24	31	125	finally
        //   31	49	125	finally
        //   56	62	125	finally
        //   70	99	125	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 8011
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
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
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +77 -> 98
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 6
        //   45: aload_3
        //   46: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: iload 4
        //   51: ifeq +6 -> 57
        //   54: iconst_1
        //   55: istore 5
        //   57: aload 6
        //   59: iload 5
        //   61: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   64: aload_0
        //   65: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   68: sipush 12003
        //   71: aload 6
        //   73: aload 7
        //   75: iconst_0
        //   76: invokeinterface 46 5 0
        //   81: pop
        //   82: aload 7
        //   84: invokevirtual 49	android/os/Parcel:readException	()V
        //   87: aload 7
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 6
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: return
        //   98: aconst_null
        //   99: astore_1
        //   100: goto -69 -> 31
        //   103: astore_1
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: aload 6
        //   111: invokevirtual 52	android/os/Parcel:recycle	()V
        //   114: aload_1
        //   115: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	116	0	this	Proxy
        //   0	116	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	116	2	paramString1	String
        //   0	116	3	paramString2	String
        //   0	116	4	paramBoolean	boolean
        //   1	59	5	i	int
        //   6	104	6	localParcel1	Parcel
        //   11	94	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	103	finally
        //   24	31	103	finally
        //   31	49	103	finally
        //   57	87	103	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +70 -> 91
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 5
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: iload_3
        //   44: ifeq +6 -> 50
        //   47: iconst_1
        //   48: istore 4
        //   50: aload 5
        //   52: iload 4
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload_0
        //   58: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   61: sipush 6504
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokeinterface 46 5 0
        //   74: pop
        //   75: aload 6
        //   77: invokevirtual 49	android/os/Parcel:readException	()V
        //   80: aload 6
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: return
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -62 -> 31
        //   96: astore_1
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	109	2	paramString	String
        //   0	109	3	paramBoolean	boolean
        //   1	52	4	i	int
        //   6	97	5	localParcel1	Parcel
        //   11	87	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	96	finally
        //   24	31	96	finally
        //   31	43	96	finally
        //   50	80	96	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iload_2
        //   37: ifeq +5 -> 42
        //   40: iconst_1
        //   41: istore_3
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 8027
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 46 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 49	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	Proxy
        //   0	100	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	100	2	paramBoolean	boolean
        //   1	44	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	36	87	finally
        //   42	71	87	finally
      }
      
      /* Error */
      public void c(IGamesCallbacks paramIGamesCallbacks, String[] paramArrayOfString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 141	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 10020
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramArrayOfString	String[]
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void c(String paramString1, String paramString2, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          this.lb.transact(8026, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void d(long paramLong, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString);
          this.lb.transact(12014, localParcel1, localParcel2, 0);
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
      public void d(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5026
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +82 -> 103
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: iload_2
        //   40: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   43: iload_3
        //   44: ifeq +64 -> 108
        //   47: iconst_1
        //   48: istore_2
        //   49: aload 6
        //   51: iload_2
        //   52: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   55: iload 4
        //   57: ifeq +56 -> 113
        //   60: iload 5
        //   62: istore_2
        //   63: aload 6
        //   65: iload_2
        //   66: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   69: aload_0
        //   70: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   73: sipush 6003
        //   76: aload 6
        //   78: aload 7
        //   80: iconst_0
        //   81: invokeinterface 46 5 0
        //   86: pop
        //   87: aload 7
        //   89: invokevirtual 49	android/os/Parcel:readException	()V
        //   92: aload 7
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: return
        //   103: aconst_null
        //   104: astore_1
        //   105: goto -74 -> 31
        //   108: iconst_0
        //   109: istore_2
        //   110: goto -61 -> 49
        //   113: iconst_0
        //   114: istore_2
        //   115: goto -52 -> 63
        //   118: astore_1
        //   119: aload 7
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 6
        //   126: invokevirtual 52	android/os/Parcel:recycle	()V
        //   129: aload_1
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramInt	int
        //   0	131	3	paramBoolean1	boolean
        //   0	131	4	paramBoolean2	boolean
        //   1	60	5	i	int
        //   6	119	6	localParcel1	Parcel
        //   11	109	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	118	finally
        //   24	31	118	finally
        //   31	43	118	finally
        //   49	55	118	finally
        //   63	92	118	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +56 -> 74
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: lload_2
        //   37: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   40: aload_0
        //   41: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   44: sipush 12011
        //   47: aload 4
        //   49: aload 5
        //   51: iconst_0
        //   52: invokeinterface 46 5 0
        //   57: pop
        //   58: aload 5
        //   60: invokevirtual 49	android/os/Parcel:readException	()V
        //   63: aload 5
        //   65: invokevirtual 52	android/os/Parcel:recycle	()V
        //   68: aload 4
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: return
        //   74: aconst_null
        //   75: astore_1
        //   76: goto -48 -> 28
        //   79: astore_1
        //   80: aload 5
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 4
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: aload_1
        //   91: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	92	0	this	Proxy
        //   0	92	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	92	2	paramLong	long
        //   3	83	4	localParcel1	Parcel
        //   8	73	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	79	finally
        //   21	28	79	finally
        //   28	63	79	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, long paramLong, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +63 -> 81
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 5
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 5
        //   36: lload_2
        //   37: invokevirtual 124	android/os/Parcel:writeLong	(J)V
        //   40: aload 5
        //   42: aload 4
        //   44: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   47: aload_0
        //   48: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   51: sipush 12013
        //   54: aload 5
        //   56: aload 6
        //   58: iconst_0
        //   59: invokeinterface 46 5 0
        //   64: pop
        //   65: aload 6
        //   67: invokevirtual 49	android/os/Parcel:readException	()V
        //   70: aload 6
        //   72: invokevirtual 52	android/os/Parcel:recycle	()V
        //   75: aload 5
        //   77: invokevirtual 52	android/os/Parcel:recycle	()V
        //   80: return
        //   81: aconst_null
        //   82: astore_1
        //   83: goto -55 -> 28
        //   86: astore_1
        //   87: aload 6
        //   89: invokevirtual 52	android/os/Parcel:recycle	()V
        //   92: aload 5
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	Proxy
        //   0	99	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	99	2	paramLong	long
        //   0	99	4	paramString	String
        //   3	90	5	localParcel1	Parcel
        //   8	80	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	17	86	finally
        //   21	28	86	finally
        //   28	70	86	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5037
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +89 -> 110
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 7
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 7
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 4
        //   51: ifeq +64 -> 115
        //   54: iconst_1
        //   55: istore_3
        //   56: aload 7
        //   58: iload_3
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: iload 5
        //   64: ifeq +56 -> 120
        //   67: iload 6
        //   69: istore_3
        //   70: aload 7
        //   72: iload_3
        //   73: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   76: aload_0
        //   77: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   80: sipush 9020
        //   83: aload 7
        //   85: aload 8
        //   87: iconst_0
        //   88: invokeinterface 46 5 0
        //   93: pop
        //   94: aload 8
        //   96: invokevirtual 49	android/os/Parcel:readException	()V
        //   99: aload 8
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: return
        //   110: aconst_null
        //   111: astore_1
        //   112: goto -81 -> 31
        //   115: iconst_0
        //   116: istore_3
        //   117: goto -61 -> 56
        //   120: iconst_0
        //   121: istore_3
        //   122: goto -52 -> 70
        //   125: astore_1
        //   126: aload 8
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload 7
        //   133: invokevirtual 52	android/os/Parcel:recycle	()V
        //   136: aload_1
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramString	String
        //   0	138	3	paramInt	int
        //   0	138	4	paramBoolean1	boolean
        //   0	138	5	paramBoolean2	boolean
        //   1	67	6	i	int
        //   6	126	7	localParcel1	Parcel
        //   11	116	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	125	finally
        //   24	31	125	finally
        //   31	49	125	finally
        //   56	62	125	finally
        //   70	99	125	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 8015
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
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
      public void d(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +70 -> 91
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 5
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: iload_3
        //   44: ifeq +6 -> 50
        //   47: iconst_1
        //   48: istore 4
        //   50: aload 5
        //   52: iload 4
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload_0
        //   58: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   61: sipush 6505
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokeinterface 46 5 0
        //   74: pop
        //   75: aload 6
        //   77: invokevirtual 49	android/os/Parcel:readException	()V
        //   80: aload 6
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: return
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -62 -> 31
        //   96: astore_1
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	109	2	paramString	String
        //   0	109	3	paramBoolean	boolean
        //   1	52	4	i	int
        //   6	97	5	localParcel1	Parcel
        //   11	87	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	96	finally
        //   24	31	96	finally
        //   31	43	96	finally
        //   50	80	96	finally
      }
      
      /* Error */
      public void d(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iload_2
        //   37: ifeq +5 -> 42
        //   40: iconst_1
        //   41: istore_3
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 12002
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 46 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 49	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	Proxy
        //   0	100	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	100	2	paramBoolean	boolean
        //   1	44	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	36	87	finally
        //   42	71	87	finally
      }
      
      public void dC(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeInt(paramInt);
          this.lb.transact(5036, localParcel1, localParcel2, 0);
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
      public void e(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5027
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +82 -> 103
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 6
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 6
        //   39: iload_2
        //   40: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   43: iload_3
        //   44: ifeq +64 -> 108
        //   47: iconst_1
        //   48: istore_2
        //   49: aload 6
        //   51: iload_2
        //   52: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   55: iload 4
        //   57: ifeq +56 -> 113
        //   60: iload 5
        //   62: istore_2
        //   63: aload 6
        //   65: iload_2
        //   66: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   69: aload_0
        //   70: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   73: sipush 6004
        //   76: aload 6
        //   78: aload 7
        //   80: iconst_0
        //   81: invokeinterface 46 5 0
        //   86: pop
        //   87: aload 7
        //   89: invokevirtual 49	android/os/Parcel:readException	()V
        //   92: aload 7
        //   94: invokevirtual 52	android/os/Parcel:recycle	()V
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: return
        //   103: aconst_null
        //   104: astore_1
        //   105: goto -74 -> 31
        //   108: iconst_0
        //   109: istore_2
        //   110: goto -61 -> 49
        //   113: iconst_0
        //   114: istore_2
        //   115: goto -52 -> 63
        //   118: astore_1
        //   119: aload 7
        //   121: invokevirtual 52	android/os/Parcel:recycle	()V
        //   124: aload 6
        //   126: invokevirtual 52	android/os/Parcel:recycle	()V
        //   129: aload_1
        //   130: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	131	0	this	Proxy
        //   0	131	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	131	2	paramInt	int
        //   0	131	3	paramBoolean1	boolean
        //   0	131	4	paramBoolean2	boolean
        //   1	60	5	i	int
        //   6	119	6	localParcel1	Parcel
        //   11	109	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	118	finally
        //   24	31	118	finally
        //   31	43	118	finally
        //   49	55	118	finally
        //   63	92	118	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5042
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +89 -> 110
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 7
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 7
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 4
        //   51: ifeq +64 -> 115
        //   54: iconst_1
        //   55: istore_3
        //   56: aload 7
        //   58: iload_3
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: iload 5
        //   64: ifeq +56 -> 120
        //   67: iload 6
        //   69: istore_3
        //   70: aload 7
        //   72: iload_3
        //   73: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   76: aload_0
        //   77: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   80: sipush 12021
        //   83: aload 7
        //   85: aload 8
        //   87: iconst_0
        //   88: invokeinterface 46 5 0
        //   93: pop
        //   94: aload 8
        //   96: invokevirtual 49	android/os/Parcel:readException	()V
        //   99: aload 8
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: return
        //   110: aconst_null
        //   111: astore_1
        //   112: goto -81 -> 31
        //   115: iconst_0
        //   116: istore_3
        //   117: goto -61 -> 56
        //   120: iconst_0
        //   121: istore_3
        //   122: goto -52 -> 70
        //   125: astore_1
        //   126: aload 8
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload 7
        //   133: invokevirtual 52	android/os/Parcel:recycle	()V
        //   136: aload_1
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramString	String
        //   0	138	3	paramInt	int
        //   0	138	4	paramBoolean1	boolean
        //   0	138	5	paramBoolean2	boolean
        //   1	67	6	i	int
        //   6	126	7	localParcel1	Parcel
        //   11	116	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	125	finally
        //   24	31	125	finally
        //   31	49	125	finally
        //   56	62	125	finally
        //   70	99	125	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 8016
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
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
      public void e(IGamesCallbacks paramIGamesCallbacks, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +70 -> 91
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 5
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 5
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: iload_3
        //   44: ifeq +6 -> 50
        //   47: iconst_1
        //   48: istore 4
        //   50: aload 5
        //   52: iload 4
        //   54: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   57: aload_0
        //   58: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   61: sipush 12006
        //   64: aload 5
        //   66: aload 6
        //   68: iconst_0
        //   69: invokeinterface 46 5 0
        //   74: pop
        //   75: aload 6
        //   77: invokevirtual 49	android/os/Parcel:readException	()V
        //   80: aload 6
        //   82: invokevirtual 52	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: invokevirtual 52	android/os/Parcel:recycle	()V
        //   90: return
        //   91: aconst_null
        //   92: astore_1
        //   93: goto -62 -> 31
        //   96: astore_1
        //   97: aload 6
        //   99: invokevirtual 52	android/os/Parcel:recycle	()V
        //   102: aload 5
        //   104: invokevirtual 52	android/os/Parcel:recycle	()V
        //   107: aload_1
        //   108: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	109	0	this	Proxy
        //   0	109	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	109	2	paramString	String
        //   0	109	3	paramBoolean	boolean
        //   1	52	4	i	int
        //   6	97	5	localParcel1	Parcel
        //   11	87	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	96	finally
        //   24	31	96	finally
        //   31	43	96	finally
        //   50	80	96	finally
      }
      
      /* Error */
      public void e(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iload_2
        //   37: ifeq +5 -> 42
        //   40: iconst_1
        //   41: istore_3
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 12032
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 46 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 49	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	Proxy
        //   0	100	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	100	2	paramBoolean	boolean
        //   1	44	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	36	87	finally
        //   42	71	87	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5047
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5043
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +89 -> 110
        //   24: aload_1
        //   25: invokeinterface 63 1 0
        //   30: astore_1
        //   31: aload 7
        //   33: aload_1
        //   34: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   37: aload 7
        //   39: aload_2
        //   40: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload 7
        //   45: iload_3
        //   46: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   49: iload 4
        //   51: ifeq +64 -> 115
        //   54: iconst_1
        //   55: istore_3
        //   56: aload 7
        //   58: iload_3
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: iload 5
        //   64: ifeq +56 -> 120
        //   67: iload 6
        //   69: istore_3
        //   70: aload 7
        //   72: iload_3
        //   73: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   76: aload_0
        //   77: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   80: sipush 12022
        //   83: aload 7
        //   85: aload 8
        //   87: iconst_0
        //   88: invokeinterface 46 5 0
        //   93: pop
        //   94: aload 8
        //   96: invokevirtual 49	android/os/Parcel:readException	()V
        //   99: aload 8
        //   101: invokevirtual 52	android/os/Parcel:recycle	()V
        //   104: aload 7
        //   106: invokevirtual 52	android/os/Parcel:recycle	()V
        //   109: return
        //   110: aconst_null
        //   111: astore_1
        //   112: goto -81 -> 31
        //   115: iconst_0
        //   116: istore_3
        //   117: goto -61 -> 56
        //   120: iconst_0
        //   121: istore_3
        //   122: goto -52 -> 70
        //   125: astore_1
        //   126: aload 8
        //   128: invokevirtual 52	android/os/Parcel:recycle	()V
        //   131: aload 7
        //   133: invokevirtual 52	android/os/Parcel:recycle	()V
        //   136: aload_1
        //   137: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	138	0	this	Proxy
        //   0	138	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	138	2	paramString	String
        //   0	138	3	paramInt	int
        //   0	138	4	paramBoolean1	boolean
        //   0	138	5	paramBoolean2	boolean
        //   1	67	6	i	int
        //   6	126	7	localParcel1	Parcel
        //   11	116	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	20	125	finally
        //   24	31	125	finally
        //   31	49	125	finally
        //   56	62	125	finally
        //   70	99	125	finally
      }
      
      /* Error */
      public void f(IGamesCallbacks paramIGamesCallbacks, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +62 -> 80
        //   21: aload_1
        //   22: invokeinterface 63 1 0
        //   27: astore_1
        //   28: aload 4
        //   30: aload_1
        //   31: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   34: aload 4
        //   36: aload_2
        //   37: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   40: aload 4
        //   42: aload_3
        //   43: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   50: sipush 12009
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 46 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 49	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore_1
        //   82: goto -54 -> 28
        //   85: astore_1
        //   86: aload 5
        //   88: invokevirtual 52	android/os/Parcel:recycle	()V
        //   91: aload 4
        //   93: invokevirtual 52	android/os/Parcel:recycle	()V
        //   96: aload_1
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	Proxy
        //   0	98	1	paramIGamesCallbacks	IGamesCallbacks
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
      public void f(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iload_2
        //   37: ifeq +5 -> 42
        //   40: iconst_1
        //   41: istore_3
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 12016
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 46 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 49	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	Proxy
        //   0	100	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	100	2	paramBoolean	boolean
        //   1	44	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	36	87	finally
        //   42	71	87	finally
      }
      
      /* Error */
      public Bundle fC()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 5004
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 224	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 127	android/os/Bundle
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localBundle	Bundle
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      /* Error */
      public void g(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5049
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void g(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5052
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void g(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iload_2
        //   37: ifeq +5 -> 42
        //   40: iconst_1
        //   41: istore_3
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 13003
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 46 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 49	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	Proxy
        //   0	100	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	100	2	paramBoolean	boolean
        //   1	44	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	36	87	finally
        //   42	71	87	finally
      }
      
      public ParcelFileDescriptor h(Uri paramUri)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              this.lb.transact(6507, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramUri = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(localParcel2);
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public RoomEntity h(IGamesCallbacks paramIGamesCallbacks, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +81 -> 101
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: aload_2
        //   39: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload_0
        //   43: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   46: sipush 5053
        //   49: aload 4
        //   51: aload 5
        //   53: iconst_0
        //   54: invokeinterface 46 5 0
        //   59: pop
        //   60: aload 5
        //   62: invokevirtual 49	android/os/Parcel:readException	()V
        //   65: aload_3
        //   66: astore_1
        //   67: aload 5
        //   69: invokevirtual 77	android/os/Parcel:readInt	()I
        //   72: ifeq +17 -> 89
        //   75: getstatic 229	com/google/android/gms/games/multiplayer/realtime/RoomEntity:CREATOR	Landroid/os/Parcelable$Creator;
        //   78: aload 5
        //   80: invokeinterface 90 2 0
        //   85: checkcast 109	com/google/android/gms/games/multiplayer/realtime/RoomEntity
        //   88: astore_1
        //   89: aload 5
        //   91: invokevirtual 52	android/os/Parcel:recycle	()V
        //   94: aload 4
        //   96: invokevirtual 52	android/os/Parcel:recycle	()V
        //   99: aload_1
        //   100: areturn
        //   101: aconst_null
        //   102: astore_1
        //   103: goto -73 -> 30
        //   106: astore_1
        //   107: aload 5
        //   109: invokevirtual 52	android/os/Parcel:recycle	()V
        //   112: aload 4
        //   114: invokevirtual 52	android/os/Parcel:recycle	()V
        //   117: aload_1
        //   118: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	119	0	this	Proxy
        //   0	119	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	119	2	paramString	String
        //   1	65	3	localObject	Object
        //   5	108	4	localParcel1	Parcel
        //   10	98	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	106	finally
        //   23	30	106	finally
        //   30	65	106	finally
        //   67	89	106	finally
      }
      
      /* Error */
      public void h(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5056
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void h(IGamesCallbacks paramIGamesCallbacks, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +62 -> 82
        //   23: aload_1
        //   24: invokeinterface 63 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: iload_2
        //   37: ifeq +5 -> 42
        //   40: iconst_1
        //   41: istore_3
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   52: sipush 13004
        //   55: aload 4
        //   57: aload 5
        //   59: iconst_0
        //   60: invokeinterface 46 5 0
        //   65: pop
        //   66: aload 5
        //   68: invokevirtual 49	android/os/Parcel:readException	()V
        //   71: aload 5
        //   73: invokevirtual 52	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: invokevirtual 52	android/os/Parcel:recycle	()V
        //   81: return
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -54 -> 30
        //   87: astore_1
        //   88: aload 5
        //   90: invokevirtual 52	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 52	android/os/Parcel:recycle	()V
        //   98: aload_1
        //   99: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	100	0	this	Proxy
        //   0	100	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	100	2	paramBoolean	boolean
        //   1	44	3	i	int
        //   5	89	4	localParcel1	Parcel
        //   10	79	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	87	finally
        //   23	30	87	finally
        //   30	36	87	finally
        //   42	71	87	finally
      }
      
      /* Error */
      public void i(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 5062
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void i(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5061
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void j(IGamesCallbacks paramIGamesCallbacks)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 63 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   34: sipush 11001
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 46 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 49	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 52	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -36 -> 25
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   0	75	1	paramIGamesCallbacks	IGamesCallbacks
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	25	64	finally
        //   25	50	64	finally
      }
      
      /* Error */
      public void j(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 5057
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void k(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 7001
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public boolean kA()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5067, localParcel1, localParcel2, 0);
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
      public DataHolder kB()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 5502
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +21 -> 59
        //   41: getstatic 241	com/google/android/gms/common/data/DataHolder:CREATOR	Lcom/google/android/gms/common/data/f;
        //   44: aload_3
        //   45: invokevirtual 247	com/google/android/gms/common/data/f:z	(Landroid/os/Parcel;)Lcom/google/android/gms/common/data/DataHolder;
        //   48: astore_1
        //   49: aload_3
        //   50: invokevirtual 52	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 52	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: areturn
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -12 -> 49
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   48	13	1	localDataHolder	DataHolder
        //   64	10	1	localObject	Object
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	49	64	finally
      }
      
      public void kC()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(8022, localParcel1, localParcel2, 0);
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
      public Intent kD()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9013
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 80	android/content/Intent
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localIntent	Intent
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      public void kE()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(11002, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean kF()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(12025, localParcel1, localParcel2, 0);
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
      
      public String ka()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5007, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String kb()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5012, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Intent ke()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9003
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 80	android/content/Intent
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localIntent	Intent
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      /* Error */
      public Intent kf()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9005
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 80	android/content/Intent
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localIntent	Intent
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      /* Error */
      public Intent kg()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9006
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 80	android/content/Intent
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localIntent	Intent
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      /* Error */
      public Intent kh()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9007
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 80	android/content/Intent
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localIntent	Intent
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      /* Error */
      public Intent km()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9010
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 80	android/content/Intent
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localIntent	Intent
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      /* Error */
      public Intent kn()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 9012
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 80	android/content/Intent
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localIntent	Intent
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      public int ko()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(9019, localParcel1, localParcel2, 0);
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
      
      public String kp()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(5003, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public int kq()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(8024, localParcel1, localParcel2, 0);
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
      
      /* Error */
      public Intent kr()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 10015
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +26 -> 64
        //   41: getstatic 84	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   44: aload_3
        //   45: invokeinterface 90 2 0
        //   50: checkcast 80	android/content/Intent
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 52	android/os/Parcel:recycle	()V
        //   58: aload_2
        //   59: invokevirtual 52	android/os/Parcel:recycle	()V
        //   62: aload_1
        //   63: areturn
        //   64: aconst_null
        //   65: astore_1
        //   66: goto -12 -> 54
        //   69: astore_1
        //   70: aload_3
        //   71: invokevirtual 52	android/os/Parcel:recycle	()V
        //   74: aload_2
        //   75: invokevirtual 52	android/os/Parcel:recycle	()V
        //   78: aload_1
        //   79: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	80	0	this	Proxy
        //   53	13	1	localIntent	Intent
        //   69	10	1	localObject	Object
        //   3	72	2	localParcel1	Parcel
        //   7	64	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	54	69	finally
      }
      
      public int ks()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(10013, localParcel1, localParcel2, 0);
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
      
      public int kt()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(10023, localParcel1, localParcel2, 0);
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
      
      public int ku()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(12035, localParcel1, localParcel2, 0);
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
      
      public int kv()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          this.lb.transact(12036, localParcel1, localParcel2, 0);
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
      
      public void kx()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
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
      public DataHolder kz()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   18: sipush 5013
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 46 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 49	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 77	android/os/Parcel:readInt	()I
        //   38: ifeq +21 -> 59
        //   41: getstatic 241	com/google/android/gms/common/data/DataHolder:CREATOR	Lcom/google/android/gms/common/data/f;
        //   44: aload_3
        //   45: invokevirtual 247	com/google/android/gms/common/data/f:z	(Landroid/os/Parcel;)Lcom/google/android/gms/common/data/DataHolder;
        //   48: astore_1
        //   49: aload_3
        //   50: invokevirtual 52	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 52	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: areturn
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -12 -> 49
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 52	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 52	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   48	13	1	localDataHolder	DataHolder
        //   64	10	1	localObject	Object
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	49	64	finally
      }
      
      /* Error */
      public void l(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 8005
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void m(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 8006
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      /* Error */
      public void n(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 8009
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void n(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(12017, localParcel1, localParcel2, 0);
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
      public void o(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 8010
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void o(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(5029, localParcel1, localParcel2, 0);
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
      public void p(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 8014
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void p(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(5028, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void q(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(5001, localParcel1, localParcel2, 0);
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
      public void q(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 9002
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void r(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(5059, localParcel1, localParcel2, 0);
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
      public void r(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 12020
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void r(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(5055, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void s(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(8013, localParcel1, localParcel2, 0);
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
      public void s(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 12005
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void s(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.lb.transact(10014, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void t(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(10002, localParcel1, localParcel2, 0);
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
      public void t(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 12027
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void u(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeLong(paramLong);
          this.lb.transact(12012, localParcel1, localParcel2, 0);
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
      public void u(IGamesCallbacks paramIGamesCallbacks, String paramString)
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
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 63 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 66	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_3
        //   32: aload_2
        //   33: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   36: aload_0
        //   37: getfield 19	com/google/android/gms/games/internal/IGamesService$Stub$Proxy:lb	Landroid/os/IBinder;
        //   40: sipush 12008
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 46 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 49	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 52	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 52	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -44 -> 26
        //   73: astore_1
        //   74: aload 4
        //   76: invokevirtual 52	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: invokevirtual 52	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	Proxy
        //   0	85	1	paramIGamesCallbacks	IGamesCallbacks
        //   0	85	2	paramString	String
        //   3	77	3	localParcel1	Parcel
        //   7	68	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	73	finally
        //   19	26	73	finally
        //   26	58	73	finally
      }
      
      public void u(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.lb.transact(5065, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void v(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.lb.transact(8025, localParcel1, localParcel2, 0);
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/IGamesService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */