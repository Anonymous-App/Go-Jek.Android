package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class fj
  implements Parcelable.Creator<fi>
{
  static void a(fi paramfi, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramfi.versionCode);
    b.a(paramParcel, 2, paramfi.tw, false);
    b.a(paramParcel, 3, paramfi.tx, paramInt, false);
    b.a(paramParcel, 4, paramfi.lH, paramInt, false);
    b.a(paramParcel, 5, paramfi.lA, false);
    b.a(paramParcel, 6, paramfi.applicationInfo, paramInt, false);
    b.a(paramParcel, 7, paramfi.ty, paramInt, false);
    b.a(paramParcel, 8, paramfi.tz, false);
    b.a(paramParcel, 9, paramfi.tA, false);
    b.a(paramParcel, 10, paramfi.tB, false);
    b.a(paramParcel, 11, paramfi.lD, paramInt, false);
    b.a(paramParcel, 12, paramfi.tC, false);
    b.c(paramParcel, 13, paramfi.tD);
    b.b(paramParcel, 14, paramfi.lS, false);
    b.a(paramParcel, 15, paramfi.tE, false);
    b.a(paramParcel, 16, paramfi.tF);
    b.H(paramParcel, i);
  }
  
  public fi h(Parcel paramParcel)
  {
    int k = a.C(paramParcel);
    int j = 0;
    Bundle localBundle3 = null;
    av localav = null;
    ay localay = null;
    String str4 = null;
    ApplicationInfo localApplicationInfo = null;
    PackageInfo localPackageInfo = null;
    String str3 = null;
    String str2 = null;
    String str1 = null;
    gt localgt = null;
    Bundle localBundle2 = null;
    int i = 0;
    ArrayList localArrayList = null;
    Bundle localBundle1 = null;
    boolean bool = false;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        localBundle3 = a.q(paramParcel, m);
        break;
      case 3: 
        localav = (av)a.a(paramParcel, m, av.CREATOR);
        break;
      case 4: 
        localay = (ay)a.a(paramParcel, m, ay.CREATOR);
        break;
      case 5: 
        str4 = a.o(paramParcel, m);
        break;
      case 6: 
        localApplicationInfo = (ApplicationInfo)a.a(paramParcel, m, ApplicationInfo.CREATOR);
        break;
      case 7: 
        localPackageInfo = (PackageInfo)a.a(paramParcel, m, PackageInfo.CREATOR);
        break;
      case 8: 
        str3 = a.o(paramParcel, m);
        break;
      case 9: 
        str2 = a.o(paramParcel, m);
        break;
      case 10: 
        str1 = a.o(paramParcel, m);
        break;
      case 11: 
        localgt = (gt)a.a(paramParcel, m, gt.CREATOR);
        break;
      case 12: 
        localBundle2 = a.q(paramParcel, m);
        break;
      case 13: 
        i = a.g(paramParcel, m);
        break;
      case 14: 
        localArrayList = a.C(paramParcel, m);
        break;
      case 15: 
        localBundle1 = a.q(paramParcel, m);
        break;
      case 16: 
        bool = a.c(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new fi(j, localBundle3, localav, localay, str4, localApplicationInfo, localPackageInfo, str3, str2, str1, localgt, localBundle2, i, localArrayList, localBundle1, bool);
  }
  
  public fi[] p(int paramInt)
  {
    return new fi[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */