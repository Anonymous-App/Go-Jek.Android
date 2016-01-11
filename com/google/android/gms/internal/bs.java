package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;

public abstract interface bs
  extends IInterface
{
  public abstract void as()
    throws RemoteException;
  
  public abstract d bA()
    throws RemoteException;
  
  public abstract String bB()
    throws RemoteException;
  
  public abstract String bt()
    throws RemoteException;
  
  public abstract d bu()
    throws RemoteException;
  
  public abstract String bw()
    throws RemoteException;
  
  public abstract String getBody()
    throws RemoteException;
  
  public abstract void i(int paramInt)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements bs
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      d locald2 = null;
      d locald1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        i(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        as();
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        paramParcel1 = bt();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        locald2 = bu();
        paramParcel2.writeNoException();
        paramParcel1 = locald1;
        if (locald2 != null) {
          paramParcel1 = locald2.asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        paramParcel1 = getBody();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        locald1 = bA();
        paramParcel2.writeNoException();
        paramParcel1 = locald2;
        if (locald1 != null) {
          paramParcel1 = locald1.asBinder();
        }
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        paramParcel1 = bw();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
      paramParcel1 = bB();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/bs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */