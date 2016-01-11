package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ConnectionInfoCreator
  implements Parcelable.Creator<ConnectionInfo>
{
  static void a(ConnectionInfo paramConnectionInfo, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramConnectionInfo.jX(), false);
    b.c(paramParcel, 1000, paramConnectionInfo.getVersionCode());
    b.c(paramParcel, 2, paramConnectionInfo.jY());
    b.H(paramParcel, paramInt);
  }
  
  public ConnectionInfo cf(Parcel paramParcel)
  {
    int j = 0;
    int k = a.C(paramParcel);
    String str = null;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        str = a.o(paramParcel, m);
        break;
      case 1000: 
        i = a.g(paramParcel, m);
        break;
      case 2: 
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new ConnectionInfo(i, str, j);
  }
  
  public ConnectionInfo[] dA(int paramInt)
  {
    return new ConnectionInfo[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/ConnectionInfoCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */