package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class aw
  implements Parcelable.Creator<av>
{
  static void a(av paramav, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramav.versionCode);
    b.a(paramParcel, 2, paramav.nT);
    b.a(paramParcel, 3, paramav.extras, false);
    b.c(paramParcel, 4, paramav.nU);
    b.b(paramParcel, 5, paramav.nV, false);
    b.a(paramParcel, 6, paramav.nW);
    b.c(paramParcel, 7, paramav.nX);
    b.a(paramParcel, 8, paramav.nY);
    b.a(paramParcel, 9, paramav.nZ, false);
    b.a(paramParcel, 10, paramav.oa, paramInt, false);
    b.a(paramParcel, 11, paramav.ob, paramInt, false);
    b.a(paramParcel, 12, paramav.oc, false);
    b.a(paramParcel, 13, paramav.od, false);
    b.H(paramParcel, i);
  }
  
  public av b(Parcel paramParcel)
  {
    int m = a.C(paramParcel);
    int k = 0;
    long l = 0L;
    Bundle localBundle2 = null;
    int j = 0;
    ArrayList localArrayList = null;
    boolean bool2 = false;
    int i = 0;
    boolean bool1 = false;
    String str2 = null;
    bj localbj = null;
    Location localLocation = null;
    String str1 = null;
    Bundle localBundle1 = null;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        l = a.i(paramParcel, n);
        break;
      case 3: 
        localBundle2 = a.q(paramParcel, n);
        break;
      case 4: 
        j = a.g(paramParcel, n);
        break;
      case 5: 
        localArrayList = a.C(paramParcel, n);
        break;
      case 6: 
        bool2 = a.c(paramParcel, n);
        break;
      case 7: 
        i = a.g(paramParcel, n);
        break;
      case 8: 
        bool1 = a.c(paramParcel, n);
        break;
      case 9: 
        str2 = a.o(paramParcel, n);
        break;
      case 10: 
        localbj = (bj)a.a(paramParcel, n, bj.CREATOR);
        break;
      case 11: 
        localLocation = (Location)a.a(paramParcel, n, Location.CREATOR);
        break;
      case 12: 
        str1 = a.o(paramParcel, n);
        break;
      case 13: 
        localBundle1 = a.q(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new av(k, l, localBundle2, j, localArrayList, bool2, i, bool1, str2, localbj, localLocation, str1, localBundle1);
  }
  
  public av[] e(int paramInt)
  {
    return new av[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/aw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */