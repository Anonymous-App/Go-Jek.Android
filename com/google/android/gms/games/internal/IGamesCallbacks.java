package com.google.android.gms.games.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;

public abstract interface IGamesCallbacks
  extends IInterface
{
  public abstract void A(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void B(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void C(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void D(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void E(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void F(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void G(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void H(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void I(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void J(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void K(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void L(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void M(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void N(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void O(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void P(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void Q(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void a(int paramInt, String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(DataHolder paramDataHolder1, DataHolder paramDataHolder2)
    throws RemoteException;
  
  public abstract void a(DataHolder paramDataHolder, Contents paramContents)
    throws RemoteException;
  
  public abstract void a(DataHolder paramDataHolder, String paramString, Contents paramContents1, Contents paramContents2, Contents paramContents3)
    throws RemoteException;
  
  public abstract void a(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void b(int paramInt1, int paramInt2, String paramString)
    throws RemoteException;
  
  public abstract void b(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void b(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void c(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void c(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void c(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void d(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void d(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void d(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void dx(int paramInt)
    throws RemoteException;
  
  public abstract void dy(int paramInt)
    throws RemoteException;
  
  public abstract void dz(int paramInt)
    throws RemoteException;
  
  public abstract void e(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void e(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void e(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void f(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void f(int paramInt, String paramString)
    throws RemoteException;
  
  public abstract void f(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void f(DataHolder paramDataHolder, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void fp()
    throws RemoteException;
  
  public abstract void g(int paramInt, String paramString)
    throws RemoteException;
  
  public abstract void g(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void h(int paramInt, String paramString)
    throws RemoteException;
  
  public abstract void h(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void i(int paramInt, String paramString)
    throws RemoteException;
  
  public abstract void i(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void j(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void k(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void l(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void m(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void n(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void o(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void onInvitationRemoved(String paramString)
    throws RemoteException;
  
  public abstract void onLeftRoom(int paramInt, String paramString)
    throws RemoteException;
  
  public abstract void onP2PConnected(String paramString)
    throws RemoteException;
  
  public abstract void onP2PDisconnected(String paramString)
    throws RemoteException;
  
  public abstract void onRealTimeMessageReceived(RealTimeMessage paramRealTimeMessage)
    throws RemoteException;
  
  public abstract void onRequestRemoved(String paramString)
    throws RemoteException;
  
  public abstract void onTurnBasedMatchRemoved(String paramString)
    throws RemoteException;
  
  public abstract void p(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void q(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void r(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void s(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void t(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void u(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void v(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void w(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void x(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void y(DataHolder paramDataHolder)
    throws RemoteException;
  
  public abstract void z(DataHolder paramDataHolder)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGamesCallbacks
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.games.internal.IGamesCallbacks");
    }
    
    public static IGamesCallbacks aA(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesCallbacks");
      if ((localIInterface != null) && ((localIInterface instanceof IGamesCallbacks))) {
        return (IGamesCallbacks)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject2 = null;
      Contents localContents1 = null;
      String str = null;
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
      Object localObject22 = null;
      Object localObject23 = null;
      Object localObject24 = null;
      Object localObject25 = null;
      Object localObject26 = null;
      Object localObject27 = null;
      Object localObject28 = null;
      Object localObject29 = null;
      Object localObject30 = null;
      Object localObject31 = null;
      Object localObject32 = null;
      Object localObject33 = null;
      Object localObject34 = null;
      Object localObject35 = null;
      Object localObject36 = null;
      Contents localContents2 = null;
      Object localObject37 = null;
      Object localObject38 = null;
      Object localObject39 = null;
      Object localObject40 = null;
      Object localObject41 = null;
      Object localObject42 = null;
      Object localObject43 = null;
      Object localObject44 = null;
      Object localObject45 = null;
      Object localObject1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesCallbacks");
        return true;
      case 5001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        f(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = DataHolder.CREATOR.z(paramParcel1);; paramParcel1 = null)
        {
          c(paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        g(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        e((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = DataHolder.CREATOR.z(paramParcel1);; localObject1 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localObject2 = DataHolder.CREATOR.z(paramParcel1);
          }
          a((DataHolder)localObject1, (DataHolder)localObject2);
          paramParcel2.writeNoException();
          return true;
        }
      case 5006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localContents1;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        f((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = str;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        g((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5008: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        h((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5009: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject4;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        i((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5010: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject5;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        j((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5011: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject6;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        k((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5016: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        fp();
        paramParcel2.writeNoException();
        return true;
      case 5017: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject7;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        m((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5037: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject8;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        n((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5018: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject9;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        u((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5019: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject10;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        v((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5020: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onLeftRoom(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5021: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject11;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        w((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5022: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject12;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        x((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5023: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject13;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        y((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5024: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject14;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        z((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5025: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject15;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        A((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5026: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject16;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        a((DataHolder)localObject1, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5027: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject17;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        b((DataHolder)localObject1, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5028: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject18;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        c((DataHolder)localObject1, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5029: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject19;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        d((DataHolder)localObject1, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5030: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject20;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        e((DataHolder)localObject1, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5031: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject21;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        f((DataHolder)localObject1, paramParcel1.createStringArray());
        paramParcel2.writeNoException();
        return true;
      case 5032: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (RealTimeMessage)RealTimeMessage.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          onRealTimeMessageReceived(paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 5033: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        b(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5034: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        paramInt1 = paramParcel1.readInt();
        localObject1 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool = true;; bool = false)
        {
          a(paramInt1, (String)localObject1, bool);
          paramParcel2.writeNoException();
          return true;
        }
      case 5038: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject22;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        B((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5035: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject23;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        C((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5036: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        dx(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 5039: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject24;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        D((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 5040: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        dy(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 6001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onP2PConnected(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 6002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onP2PDisconnected(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject25;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        E((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 8002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          b(paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 8003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject26;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        p((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 8004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject27;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        q((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 8005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject28;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        r((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 8006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject29;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        s((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 8007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        h(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8008: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject30;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        t((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 8009: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onTurnBasedMatchRemoved(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8010: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onInvitationRemoved(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 9001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject31;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        l((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 10001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject32;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        o((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 10002: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        onRequestRemoved(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 10003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject33;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        F((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 10004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject34;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        G((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 10005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          c(paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 10006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject35;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        H((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 11001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          d(paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 12001: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject36;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        I((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12004: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label2854;
          }
        }
        for (paramParcel1 = (Contents)Contents.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a((DataHolder)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 12017: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
          str = paramParcel1.readString();
          if (paramParcel1.readInt() == 0) {
            break label2976;
          }
          localObject2 = (Contents)Contents.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label2982;
          }
        }
        for (localContents1 = (Contents)Contents.CREATOR.createFromParcel(paramParcel1);; localContents1 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localContents2 = (Contents)Contents.CREATOR.createFromParcel(paramParcel1);
          }
          a((DataHolder)localObject1, str, (Contents)localObject2, localContents1, localContents2);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label2908;
        }
      case 12005: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject37;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        J((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12012: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        i(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12003: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          e(paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 12013: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject38;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        P((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12011: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject39;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        d((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12006: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject40;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        K((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12007: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject41;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        L((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12014: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject42;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        M((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12016: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject43;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        N((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12008: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject44;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        O((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 12015: 
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          f(paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 13001: 
        label2854:
        label2908:
        label2976:
        label2982:
        paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        localObject1 = localObject45;
        if (paramParcel1.readInt() != 0) {
          localObject1 = DataHolder.CREATOR.z(paramParcel1);
        }
        Q((DataHolder)localObject1);
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
      dz(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class Proxy
      implements IGamesCallbacks
    {
      private IBinder lb;
      
      Proxy(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void A(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5025
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
      public void B(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5038
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
      public void C(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5035
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
      public void D(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5039
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
      public void E(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 8001
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
      public void F(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 10003
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
      public void G(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 10004
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
      public void H(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 10006
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
      public void I(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12001
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
      public void J(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12005
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
      public void K(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12006
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
      public void L(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12007
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
      public void M(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12014
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
      public void N(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12016
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
      public void O(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12008
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
      public void P(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12013
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
      public void Q(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 13001
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
      
      public void a(int paramInt, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          paramInt = i;
          if (paramBoolean) {
            paramInt = 1;
          }
          localParcel1.writeInt(paramInt);
          this.lb.transact(5034, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void a(DataHolder paramDataHolder1, DataHolder paramDataHolder2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (paramDataHolder1 != null)
            {
              localParcel1.writeInt(1);
              paramDataHolder1.writeToParcel(localParcel1, 0);
              if (paramDataHolder2 != null)
              {
                localParcel1.writeInt(1);
                paramDataHolder2.writeToParcel(localParcel1, 0);
                this.lb.transact(5005, localParcel1, localParcel2, 0);
                localParcel2.readException();
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
        }
      }
      
      public void a(DataHolder paramDataHolder, Contents paramContents)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (paramDataHolder != null)
            {
              localParcel1.writeInt(1);
              paramDataHolder.writeToParcel(localParcel1, 0);
              if (paramContents != null)
              {
                localParcel1.writeInt(1);
                paramContents.writeToParcel(localParcel1, 0);
                this.lb.transact(12004, localParcel1, localParcel2, 0);
                localParcel2.readException();
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
        }
      }
      
      public void a(DataHolder paramDataHolder, String paramString, Contents paramContents1, Contents paramContents2, Contents paramContents3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (paramDataHolder != null)
            {
              localParcel1.writeInt(1);
              paramDataHolder.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString);
              if (paramContents1 != null)
              {
                localParcel1.writeInt(1);
                paramContents1.writeToParcel(localParcel1, 0);
                if (paramContents2 == null) {
                  break label160;
                }
                localParcel1.writeInt(1);
                paramContents2.writeToParcel(localParcel1, 0);
                if (paramContents3 == null) {
                  break label169;
                }
                localParcel1.writeInt(1);
                paramContents3.writeToParcel(localParcel1, 0);
                this.lb.transact(12017, localParcel1, localParcel2, 0);
                localParcel2.readException();
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
          label160:
          localParcel1.writeInt(0);
          continue;
          label169:
          localParcel1.writeInt(0);
        }
      }
      
      /* Error */
      public void a(DataHolder paramDataHolder, String[] paramArrayOfString)
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
        //   16: ifnull +51 -> 67
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: aload_2
        //   32: invokevirtual 91	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5026
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
        //   72: goto -42 -> 30
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 58	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 58	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	Proxy
        //   0	87	1	paramDataHolder	DataHolder
        //   0	87	2	paramArrayOfString	String[]
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	30	75	finally
        //   30	57	75	finally
        //   67	72	75	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void b(int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeString(paramString);
          this.lb.transact(5033, localParcel1, localParcel2, 0);
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
      public void b(int paramInt, Bundle paramBundle)
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
        //   32: invokevirtual 99	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 8002
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
      public void b(DataHolder paramDataHolder, String[] paramArrayOfString)
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
        //   16: ifnull +51 -> 67
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: aload_2
        //   32: invokevirtual 91	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5027
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
        //   72: goto -42 -> 30
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 58	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 58	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	Proxy
        //   0	87	1	paramDataHolder	DataHolder
        //   0	87	2	paramArrayOfString	String[]
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	30	75	finally
        //   30	57	75	finally
        //   67	72	75	finally
      }
      
      /* Error */
      public void c(int paramInt, Bundle paramBundle)
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
        //   32: invokevirtual 99	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 10005
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
      public void c(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
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
      public void c(DataHolder paramDataHolder, String[] paramArrayOfString)
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
        //   16: ifnull +51 -> 67
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: aload_2
        //   32: invokevirtual 91	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5028
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
        //   72: goto -42 -> 30
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 58	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 58	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	Proxy
        //   0	87	1	paramDataHolder	DataHolder
        //   0	87	2	paramArrayOfString	String[]
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	30	75	finally
        //   30	57	75	finally
        //   67	72	75	finally
      }
      
      /* Error */
      public void d(int paramInt, Bundle paramBundle)
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
        //   32: invokevirtual 99	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 11001
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
      public void d(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 12011
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
      public void d(DataHolder paramDataHolder, String[] paramArrayOfString)
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
        //   16: ifnull +51 -> 67
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: aload_2
        //   32: invokevirtual 91	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5029
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
        //   72: goto -42 -> 30
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 58	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 58	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	Proxy
        //   0	87	1	paramDataHolder	DataHolder
        //   0	87	2	paramArrayOfString	String[]
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	30	75	finally
        //   30	57	75	finally
        //   67	72	75	finally
      }
      
      public void dx(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
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
      
      public void dy(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          this.lb.transact(5040, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void dz(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          this.lb.transact(13002, localParcel1, localParcel2, 0);
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
      public void e(int paramInt, Bundle paramBundle)
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
        //   32: invokevirtual 99	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 12003
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
      public void e(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5004
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
      public void e(DataHolder paramDataHolder, String[] paramArrayOfString)
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
        //   16: ifnull +51 -> 67
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: aload_2
        //   32: invokevirtual 91	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5030
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
        //   72: goto -42 -> 30
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 58	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 58	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	Proxy
        //   0	87	1	paramDataHolder	DataHolder
        //   0	87	2	paramArrayOfString	String[]
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	30	75	finally
        //   30	57	75	finally
        //   67	72	75	finally
      }
      
      /* Error */
      public void f(int paramInt, Bundle paramBundle)
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
        //   32: invokevirtual 99	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 12015
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
      
      public void f(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
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
      public void f(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5006
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
      public void f(DataHolder paramDataHolder, String[] paramArrayOfString)
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
        //   16: ifnull +51 -> 67
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 46	com/google/android/gms/common/data/DataHolder:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: aload_2
        //   32: invokevirtual 91	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   35: aload_0
        //   36: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   39: sipush 5031
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
        //   72: goto -42 -> 30
        //   75: astore_1
        //   76: aload 4
        //   78: invokevirtual 58	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 58	android/os/Parcel:recycle	()V
        //   85: aload_1
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	Proxy
        //   0	87	1	paramDataHolder	DataHolder
        //   0	87	2	paramArrayOfString	String[]
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	75	finally
        //   19	30	75	finally
        //   30	57	75	finally
        //   67	72	75	finally
      }
      
      public void fp()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          this.lb.transact(5016, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void g(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5007
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
      
      public void h(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          this.lb.transact(8007, localParcel1, localParcel2, 0);
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
      public void h(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5008
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
      
      public void i(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
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
      public void i(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5009
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
      public void j(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5010
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
      public void k(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5011
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
      public void l(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 9001
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
      public void m(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5017
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
      public void n(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5037
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
      public void o(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 10001
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
      
      public void onInvitationRemoved(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
          this.lb.transact(8010, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onLeftRoom(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          this.lb.transact(5020, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onP2PConnected(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
          this.lb.transact(6001, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onP2PDisconnected(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
          this.lb.transact(6002, localParcel1, localParcel2, 0);
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
      public void onRealTimeMessageReceived(RealTimeMessage paramRealTimeMessage)
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
        //   26: invokevirtual 126	com/google/android/gms/games/multiplayer/realtime/RealTimeMessage:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5032
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
        //   0	77	1	paramRealTimeMessage	RealTimeMessage
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	66	finally
        //   18	29	66	finally
        //   29	49	66	finally
        //   58	63	66	finally
      }
      
      public void onRequestRemoved(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
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
      
      public void onTurnBasedMatchRemoved(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
          localParcel1.writeString(paramString);
          this.lb.transact(8009, localParcel1, localParcel2, 0);
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
      public void p(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 8003
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
      public void q(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 8004
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
      public void r(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 8005
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
      public void s(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 8006
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
      public void t(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 8008
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
      public void u(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5018
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
      public void v(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5019
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
      public void w(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5021
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
      public void x(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5022
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
      public void y(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5023
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
      public void z(DataHolder paramDataHolder)
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
        //   30: getfield 19	com/google/android/gms/games/internal/IGamesCallbacks$Stub$Proxy:lb	Landroid/os/IBinder;
        //   33: sipush 5024
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/IGamesCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */