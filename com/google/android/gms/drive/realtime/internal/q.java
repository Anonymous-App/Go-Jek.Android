package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class q
  implements Parcelable.Creator<ParcelableIndexReference>
{
  static void a(ParcelableIndexReference paramParcelableIndexReference, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramParcelableIndexReference.BR);
    b.a(paramParcel, 2, paramParcelableIndexReference.Rp, false);
    b.c(paramParcel, 3, paramParcelableIndexReference.mIndex);
    b.a(paramParcel, 4, paramParcelableIndexReference.Rq);
    b.H(paramParcel, paramInt);
  }
  
  public ParcelableIndexReference aX(Parcel paramParcel)
  {
    boolean bool = false;
    int k = a.C(paramParcel);
    String str = null;
    int j = 0;
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
        break;
      case 4: 
        bool = a.c(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new ParcelableIndexReference(i, str, j, bool);
  }
  
  public ParcelableIndexReference[] ck(int paramInt)
  {
    return new ParcelableIndexReference[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */