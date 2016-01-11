package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class a
  implements Parcelable.Creator<BeginCompoundOperationRequest>
{
  static void a(BeginCompoundOperationRequest paramBeginCompoundOperationRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramBeginCompoundOperationRequest.BR);
    b.a(paramParcel, 2, paramBeginCompoundOperationRequest.Ri);
    b.a(paramParcel, 3, paramBeginCompoundOperationRequest.mName, false);
    b.a(paramParcel, 4, paramBeginCompoundOperationRequest.Rj);
    b.H(paramParcel, paramInt);
  }
  
  public BeginCompoundOperationRequest aU(Parcel paramParcel)
  {
    boolean bool1 = false;
    int j = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    String str = null;
    boolean bool2 = true;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, k);
        break;
      case 1: 
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, k);
        break;
      case 2: 
        bool1 = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, k);
        break;
      case 3: 
        str = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, k);
        break;
      case 4: 
        bool2 = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new BeginCompoundOperationRequest(i, bool1, str, bool2);
  }
  
  public BeginCompoundOperationRequest[] cg(int paramInt)
  {
    return new BeginCompoundOperationRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */