package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ai
  implements Parcelable.Creator<ah>
{
  static void a(ah paramah, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramah.BR);
    b.c(paramParcel, 2, paramah.getRequestId());
    b.a(paramParcel, 3, paramah.getPath(), false);
    b.a(paramParcel, 4, paramah.getData(), false);
    b.a(paramParcel, 5, paramah.getSourceNodeId(), false);
    b.H(paramParcel, paramInt);
  }
  
  public ah ec(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int k = a.C(paramParcel);
    byte[] arrayOfByte = null;
    String str2 = null;
    int j = 0;
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
        i = a.g(paramParcel, m);
        break;
      case 3: 
        str2 = a.o(paramParcel, m);
        break;
      case 4: 
        arrayOfByte = a.r(paramParcel, m);
        break;
      case 5: 
        str1 = a.o(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new ah(j, i, str2, arrayOfByte, str1);
  }
  
  public ah[] gf(int paramInt)
  {
    return new ah[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */