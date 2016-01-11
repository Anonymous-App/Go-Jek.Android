package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public class a
  implements Parcelable.Creator<WalletFragmentInitParams>
{
  static void a(WalletFragmentInitParams paramWalletFragmentInitParams, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramWalletFragmentInitParams.BR);
    b.a(paramParcel, 2, paramWalletFragmentInitParams.getAccountName(), false);
    b.a(paramParcel, 3, paramWalletFragmentInitParams.getMaskedWalletRequest(), paramInt, false);
    b.c(paramParcel, 4, paramWalletFragmentInitParams.getMaskedWalletRequestCode());
    b.a(paramParcel, 5, paramWalletFragmentInitParams.getMaskedWallet(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public WalletFragmentInitParams dD(Parcel paramParcel)
  {
    MaskedWallet localMaskedWallet = null;
    int k = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    int j = 0;
    int i = -1;
    MaskedWalletRequest localMaskedWalletRequest = null;
    String str = null;
    while (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, m);
        break;
      case 1: 
        j = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
        break;
      case 2: 
        str = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 3: 
        localMaskedWalletRequest = (MaskedWalletRequest)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, m, MaskedWalletRequest.CREATOR);
        break;
      case 4: 
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
        break;
      case 5: 
        localMaskedWallet = (MaskedWallet)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, m, MaskedWallet.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new WalletFragmentInitParams(j, str, localMaskedWalletRequest, i, localMaskedWallet);
  }
  
  public WalletFragmentInitParams[] fF(int paramInt)
  {
    return new WalletFragmentInitParams[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/fragment/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */