package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class c
  implements Parcelable.Creator<CustomPropertyKey>
{
  static void a(CustomPropertyKey paramCustomPropertyKey, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramCustomPropertyKey.BR);
    b.a(paramParcel, 2, paramCustomPropertyKey.JO, false);
    b.c(paramParcel, 3, paramCustomPropertyKey.mVisibility);
    b.H(paramParcel, paramInt);
  }
  
  public CustomPropertyKey aE(Parcel paramParcel)
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
        i = a.g(paramParcel, m);
        break;
      case 2: 
        str = a.o(paramParcel, m);
        break;
      case 3: 
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new CustomPropertyKey(i, str, j);
  }
  
  public CustomPropertyKey[] bQ(int paramInt)
  {
    return new CustomPropertyKey[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/metadata/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */