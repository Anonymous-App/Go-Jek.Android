package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class an
  implements Parcelable.Creator<OnListEntriesResponse>
{
  static void a(OnListEntriesResponse paramOnListEntriesResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOnListEntriesResponse.BR);
    b.a(paramParcel, 2, paramOnListEntriesResponse.Pu, paramInt, false);
    b.a(paramParcel, 3, paramOnListEntriesResponse.Oz);
    b.H(paramParcel, i);
  }
  
  public OnListEntriesResponse ap(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    DataHolder localDataHolder = null;
    int i = 0;
    if (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
      }
      for (;;)
      {
        break;
        i = a.g(paramParcel, k);
        continue;
        localDataHolder = (DataHolder)a.a(paramParcel, k, DataHolder.CREATOR);
        continue;
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new OnListEntriesResponse(i, localDataHolder, bool);
  }
  
  public OnListEntriesResponse[] bB(int paramInt)
  {
    return new OnListEntriesResponse[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/an.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */