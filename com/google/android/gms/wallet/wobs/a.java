package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.jr;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class a
  implements Parcelable.Creator<CommonWalletObject>
{
  static void a(CommonWalletObject paramCommonWalletObject, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramCommonWalletObject.getVersionCode());
    b.a(paramParcel, 2, paramCommonWalletObject.fl, false);
    b.a(paramParcel, 3, paramCommonWalletObject.ata, false);
    b.a(paramParcel, 4, paramCommonWalletObject.name, false);
    b.a(paramParcel, 5, paramCommonWalletObject.asU, false);
    b.a(paramParcel, 6, paramCommonWalletObject.asW, false);
    b.a(paramParcel, 7, paramCommonWalletObject.asX, false);
    b.a(paramParcel, 8, paramCommonWalletObject.asY, false);
    b.a(paramParcel, 9, paramCommonWalletObject.asZ, false);
    b.c(paramParcel, 10, paramCommonWalletObject.state);
    b.c(paramParcel, 11, paramCommonWalletObject.atb, false);
    b.a(paramParcel, 12, paramCommonWalletObject.atc, paramInt, false);
    b.c(paramParcel, 13, paramCommonWalletObject.atd, false);
    b.a(paramParcel, 14, paramCommonWalletObject.ate, false);
    b.a(paramParcel, 15, paramCommonWalletObject.atf, false);
    b.a(paramParcel, 17, paramCommonWalletObject.ath);
    b.c(paramParcel, 16, paramCommonWalletObject.atg, false);
    b.c(paramParcel, 19, paramCommonWalletObject.atj, false);
    b.c(paramParcel, 18, paramCommonWalletObject.ati, false);
    b.c(paramParcel, 20, paramCommonWalletObject.atk, false);
    b.H(paramParcel, i);
  }
  
  public CommonWalletObject dG(Parcel paramParcel)
  {
    int k = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    int j = 0;
    String str10 = null;
    String str9 = null;
    String str8 = null;
    String str7 = null;
    String str6 = null;
    String str5 = null;
    String str4 = null;
    String str3 = null;
    int i = 0;
    ArrayList localArrayList6 = jr.hz();
    l locall = null;
    ArrayList localArrayList5 = jr.hz();
    String str2 = null;
    String str1 = null;
    ArrayList localArrayList4 = jr.hz();
    boolean bool = false;
    ArrayList localArrayList3 = jr.hz();
    ArrayList localArrayList2 = jr.hz();
    ArrayList localArrayList1 = jr.hz();
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
        str10 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 3: 
        str9 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 4: 
        str8 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 5: 
        str7 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 6: 
        str6 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 7: 
        str5 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 8: 
        str4 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 9: 
        str3 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 10: 
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
        break;
      case 11: 
        localArrayList6 = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, m, p.CREATOR);
        break;
      case 12: 
        locall = (l)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, m, l.CREATOR);
        break;
      case 13: 
        localArrayList5 = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, m, LatLng.CREATOR);
        break;
      case 14: 
        str2 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 15: 
        str1 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 17: 
        bool = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, m);
        break;
      case 16: 
        localArrayList4 = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, m, d.CREATOR);
        break;
      case 19: 
        localArrayList2 = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, m, j.CREATOR);
        break;
      case 18: 
        localArrayList3 = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, m, n.CREATOR);
        break;
      case 20: 
        localArrayList1 = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, m, n.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new CommonWalletObject(j, str10, str9, str8, str7, str6, str5, str4, str3, i, localArrayList6, locall, localArrayList5, str2, str1, localArrayList4, bool, localArrayList3, localArrayList2, localArrayList1);
  }
  
  public CommonWalletObject[] fJ(int paramInt)
  {
    return new CommonWalletObject[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/wobs/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */