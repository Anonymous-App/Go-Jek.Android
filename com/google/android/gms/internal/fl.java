package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class fl
  implements Parcelable.Creator<fk>
{
  static void a(fk paramfk, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramfk.versionCode);
    b.a(paramParcel, 2, paramfk.rP, false);
    b.a(paramParcel, 3, paramfk.tG, false);
    b.b(paramParcel, 4, paramfk.qf, false);
    b.c(paramParcel, 5, paramfk.errorCode);
    b.b(paramParcel, 6, paramfk.qg, false);
    b.a(paramParcel, 7, paramfk.tH);
    b.a(paramParcel, 8, paramfk.tI);
    b.a(paramParcel, 9, paramfk.tJ);
    b.b(paramParcel, 10, paramfk.tK, false);
    b.a(paramParcel, 11, paramfk.qj);
    b.c(paramParcel, 12, paramfk.orientation);
    b.a(paramParcel, 13, paramfk.tL, false);
    b.a(paramParcel, 14, paramfk.tM);
    b.a(paramParcel, 15, paramfk.tN, false);
    b.a(paramParcel, 19, paramfk.tP, false);
    b.a(paramParcel, 18, paramfk.tO);
    b.a(paramParcel, 21, paramfk.tQ, false);
    b.a(paramParcel, 23, paramfk.tS);
    b.a(paramParcel, 22, paramfk.tR);
    b.a(paramParcel, 25, paramfk.tT);
    b.a(paramParcel, 24, paramfk.tF);
    b.H(paramParcel, paramInt);
  }
  
  public fk i(Parcel paramParcel)
  {
    int m = a.C(paramParcel);
    int k = 0;
    String str6 = null;
    String str5 = null;
    ArrayList localArrayList3 = null;
    int j = 0;
    ArrayList localArrayList2 = null;
    long l4 = 0L;
    boolean bool6 = false;
    long l3 = 0L;
    ArrayList localArrayList1 = null;
    long l2 = 0L;
    int i = 0;
    String str4 = null;
    long l1 = 0L;
    String str3 = null;
    boolean bool5 = false;
    String str2 = null;
    String str1 = null;
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = false;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      case 16: 
      case 17: 
      case 20: 
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        str6 = a.o(paramParcel, n);
        break;
      case 3: 
        str5 = a.o(paramParcel, n);
        break;
      case 4: 
        localArrayList3 = a.C(paramParcel, n);
        break;
      case 5: 
        j = a.g(paramParcel, n);
        break;
      case 6: 
        localArrayList2 = a.C(paramParcel, n);
        break;
      case 7: 
        l4 = a.i(paramParcel, n);
        break;
      case 8: 
        bool6 = a.c(paramParcel, n);
        break;
      case 9: 
        l3 = a.i(paramParcel, n);
        break;
      case 10: 
        localArrayList1 = a.C(paramParcel, n);
        break;
      case 11: 
        l2 = a.i(paramParcel, n);
        break;
      case 12: 
        i = a.g(paramParcel, n);
        break;
      case 13: 
        str4 = a.o(paramParcel, n);
        break;
      case 14: 
        l1 = a.i(paramParcel, n);
        break;
      case 15: 
        str3 = a.o(paramParcel, n);
        break;
      case 19: 
        str2 = a.o(paramParcel, n);
        break;
      case 18: 
        bool5 = a.c(paramParcel, n);
        break;
      case 21: 
        str1 = a.o(paramParcel, n);
        break;
      case 23: 
        bool3 = a.c(paramParcel, n);
        break;
      case 22: 
        bool4 = a.c(paramParcel, n);
        break;
      case 25: 
        bool1 = a.c(paramParcel, n);
        break;
      case 24: 
        bool2 = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new fk(k, str6, str5, localArrayList3, j, localArrayList2, l4, bool6, l3, localArrayList1, l2, i, str4, l1, str3, bool5, str2, str1, bool4, bool3, bool2, bool1);
  }
  
  public fk[] q(int paramInt)
  {
    return new fk[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */