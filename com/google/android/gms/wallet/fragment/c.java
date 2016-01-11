package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class c
  implements Parcelable.Creator<WalletFragmentStyle>
{
  static void a(WalletFragmentStyle paramWalletFragmentStyle, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramWalletFragmentStyle.BR);
    b.a(paramParcel, 2, paramWalletFragmentStyle.auo, false);
    b.c(paramParcel, 3, paramWalletFragmentStyle.aup);
    b.H(paramParcel, paramInt);
  }
  
  public WalletFragmentStyle dF(Parcel paramParcel)
  {
    int j = 0;
    int k = a.C(paramParcel);
    Bundle localBundle = null;
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
        i = a.g(paramParcel, m);
        break;
      case 2: 
        localBundle = a.q(paramParcel, m);
        break;
      case 3: 
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new WalletFragmentStyle(i, localBundle, j);
  }
  
  public WalletFragmentStyle[] fH(int paramInt)
  {
    return new WalletFragmentStyle[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/fragment/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */