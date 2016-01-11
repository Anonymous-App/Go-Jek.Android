package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class z
  implements Parcelable.Creator<y>
{
  static void a(y paramy, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramy.getName(), false);
    b.c(paramParcel, 1000, paramy.getVersionCode());
    b.a(paramParcel, 2, paramy.getIdentifier(), false);
    b.H(paramParcel, paramInt);
  }
  
  public y bO(Parcel paramParcel)
  {
    String str2 = null;
    int j = a.C(paramParcel);
    int i = 0;
    String str1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        str1 = a.o(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str2 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new y(i, str1, str2);
  }
  
  public y[] dg(int paramInt)
  {
    return new y[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */