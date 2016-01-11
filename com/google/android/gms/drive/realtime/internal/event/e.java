package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class e
  implements Parcelable.Creator<TextDeletedDetails>
{
  static void a(TextDeletedDetails paramTextDeletedDetails, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramTextDeletedDetails.BR);
    b.c(paramParcel, 2, paramTextDeletedDetails.mIndex);
    b.c(paramParcel, 3, paramTextDeletedDetails.RL);
    b.H(paramParcel, paramInt);
  }
  
  public TextDeletedDetails bc(Parcel paramParcel)
  {
    int k = 0;
    int m = a.C(paramParcel);
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        i = a.g(paramParcel, n);
        break;
      case 2: 
        j = a.g(paramParcel, n);
        break;
      case 3: 
        k = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new TextDeletedDetails(i, j, k);
  }
  
  public TextDeletedDetails[] cp(int paramInt)
  {
    return new TextDeletedDetails[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */