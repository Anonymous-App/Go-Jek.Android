package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class g
  implements Parcelable.Creator<ValueChangedDetails>
{
  static void a(ValueChangedDetails paramValueChangedDetails, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramValueChangedDetails.BR);
    b.c(paramParcel, 2, paramValueChangedDetails.RM);
    b.H(paramParcel, paramInt);
  }
  
  public ValueChangedDetails be(Parcel paramParcel)
  {
    int j = 0;
    int k = a.C(paramParcel);
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
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new ValueChangedDetails(i, j);
  }
  
  public ValueChangedDetails[] cr(int paramInt)
  {
    return new ValueChangedDetails[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */