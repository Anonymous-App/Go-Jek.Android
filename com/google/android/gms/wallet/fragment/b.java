package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;

public class b
  implements Parcelable.Creator<WalletFragmentOptions>
{
  static void a(WalletFragmentOptions paramWalletFragmentOptions, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramWalletFragmentOptions.BR);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 2, paramWalletFragmentOptions.getEnvironment());
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 3, paramWalletFragmentOptions.getTheme());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 4, paramWalletFragmentOptions.getFragmentStyle(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 5, paramWalletFragmentOptions.getMode());
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, i);
  }
  
  public WalletFragmentOptions dE(Parcel paramParcel)
  {
    int i = 1;
    int j = 0;
    int n = a.C(paramParcel);
    WalletFragmentStyle localWalletFragmentStyle = null;
    int k = 1;
    int m = 0;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = a.B(paramParcel);
      switch (a.aD(i1))
      {
      default: 
        a.b(paramParcel, i1);
        break;
      case 1: 
        m = a.g(paramParcel, i1);
        break;
      case 2: 
        k = a.g(paramParcel, i1);
        break;
      case 3: 
        j = a.g(paramParcel, i1);
        break;
      case 4: 
        localWalletFragmentStyle = (WalletFragmentStyle)a.a(paramParcel, i1, WalletFragmentStyle.CREATOR);
        break;
      case 5: 
        i = a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new a.a("Overread allowed size end=" + n, paramParcel);
    }
    return new WalletFragmentOptions(m, k, j, localWalletFragmentStyle, i);
  }
  
  public WalletFragmentOptions[] fG(int paramInt)
  {
    return new WalletFragmentOptions[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/fragment/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */