package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;

public class am
  implements Parcelable.Creator<OnEventResponse>
{
  static void a(OnEventResponse paramOnEventResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOnEventResponse.BR);
    b.c(paramParcel, 2, paramOnEventResponse.Oa);
    b.a(paramParcel, 3, paramOnEventResponse.Ps, paramInt, false);
    b.a(paramParcel, 5, paramOnEventResponse.Pt, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public OnEventResponse ao(Parcel paramParcel)
  {
    CompletionEvent localCompletionEvent = null;
    int j = 0;
    int m = a.C(paramParcel);
    ChangeEvent localChangeEvent = null;
    int i = 0;
    if (paramParcel.dataPosition() < m)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      case 4: 
      default: 
        a.b(paramParcel, k);
        k = j;
        j = i;
        i = k;
      }
      for (;;)
      {
        k = j;
        j = i;
        i = k;
        break;
        k = a.g(paramParcel, k);
        i = j;
        j = k;
        continue;
        k = a.g(paramParcel, k);
        j = i;
        i = k;
        continue;
        localChangeEvent = (ChangeEvent)a.a(paramParcel, k, ChangeEvent.CREATOR);
        k = i;
        i = j;
        j = k;
        continue;
        localCompletionEvent = (CompletionEvent)a.a(paramParcel, k, CompletionEvent.CREATOR);
        k = i;
        i = j;
        j = k;
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new OnEventResponse(i, j, localChangeEvent, localCompletionEvent);
  }
  
  public OnEventResponse[] bA(int paramInt)
  {
    return new OnEventResponse[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */