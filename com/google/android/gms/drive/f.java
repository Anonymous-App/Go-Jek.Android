package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class f
  implements Parcelable.Creator<RealtimeDocumentSyncRequest>
{
  static void a(RealtimeDocumentSyncRequest paramRealtimeDocumentSyncRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramRealtimeDocumentSyncRequest.BR);
    b.b(paramParcel, 2, paramRealtimeDocumentSyncRequest.Nz, false);
    b.b(paramParcel, 3, paramRealtimeDocumentSyncRequest.NA, false);
    b.H(paramParcel, paramInt);
  }
  
  public RealtimeDocumentSyncRequest Q(Parcel paramParcel)
  {
    ArrayList localArrayList2 = null;
    int j = a.C(paramParcel);
    int i = 0;
    ArrayList localArrayList1 = null;
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
        localArrayList1 = a.C(paramParcel, k);
        break;
      case 3: 
        localArrayList2 = a.C(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new RealtimeDocumentSyncRequest(i, localArrayList1, localArrayList2);
  }
  
  public RealtimeDocumentSyncRequest[] aX(int paramInt)
  {
    return new RealtimeDocumentSyncRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */