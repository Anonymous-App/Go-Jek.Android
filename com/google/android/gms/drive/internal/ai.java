package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

public class ai
  implements Parcelable.Creator<OnContentsResponse>
{
  static void a(OnContentsResponse paramOnContentsResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOnContentsResponse.BR);
    b.a(paramParcel, 2, paramOnContentsResponse.Ox, paramInt, false);
    b.a(paramParcel, 3, paramOnContentsResponse.Po);
    b.H(paramParcel, i);
  }
  
  public OnContentsResponse ak(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    Contents localContents = null;
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
        localContents = (Contents)a.a(paramParcel, k, Contents.CREATOR);
        continue;
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new OnContentsResponse(i, localContents, bool);
  }
  
  public OnContentsResponse[] bw(int paramInt)
  {
    return new OnContentsResponse[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */