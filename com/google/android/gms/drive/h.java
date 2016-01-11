package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class h
  implements Parcelable.Creator<UserMetadata>
{
  static void a(UserMetadata paramUserMetadata, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramUserMetadata.BR);
    b.a(paramParcel, 2, paramUserMetadata.NG, false);
    b.a(paramParcel, 3, paramUserMetadata.NH, false);
    b.a(paramParcel, 4, paramUserMetadata.NI, false);
    b.a(paramParcel, 5, paramUserMetadata.NJ);
    b.a(paramParcel, 6, paramUserMetadata.NK, false);
    b.H(paramParcel, paramInt);
  }
  
  public UserMetadata S(Parcel paramParcel)
  {
    boolean bool = false;
    String str1 = null;
    int j = a.C(paramParcel);
    String str2 = null;
    String str3 = null;
    String str4 = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str4 = a.o(paramParcel, k);
        break;
      case 3: 
        str3 = a.o(paramParcel, k);
        break;
      case 4: 
        str2 = a.o(paramParcel, k);
        break;
      case 5: 
        bool = a.c(paramParcel, k);
        break;
      case 6: 
        str1 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new UserMetadata(i, str4, str3, str2, bool, str1);
  }
  
  public UserMetadata[] aZ(int paramInt)
  {
    return new UserMetadata[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */