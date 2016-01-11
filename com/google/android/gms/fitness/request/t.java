package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public class t
  implements Parcelable.Creator<SessionReadRequest>
{
  static void a(SessionReadRequest paramSessionReadRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramSessionReadRequest.getSessionName(), false);
    b.c(paramParcel, 1000, paramSessionReadRequest.getVersionCode());
    b.a(paramParcel, 2, paramSessionReadRequest.getSessionId(), false);
    b.a(paramParcel, 3, paramSessionReadRequest.iD());
    b.a(paramParcel, 4, paramSessionReadRequest.iE());
    b.c(paramParcel, 5, paramSessionReadRequest.getDataTypes(), false);
    b.c(paramParcel, 6, paramSessionReadRequest.getDataSources(), false);
    b.a(paramParcel, 7, paramSessionReadRequest.jz());
    b.a(paramParcel, 8, paramSessionReadRequest.jl());
    b.b(paramParcel, 9, paramSessionReadRequest.getExcludedPackages(), false);
    b.H(paramParcel, paramInt);
  }
  
  public SessionReadRequest bL(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    String str2 = null;
    String str1 = null;
    long l2 = 0L;
    long l1 = 0L;
    ArrayList localArrayList3 = null;
    ArrayList localArrayList2 = null;
    boolean bool2 = false;
    boolean bool1 = false;
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
        str2 = a.o(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str1 = a.o(paramParcel, k);
        break;
      case 3: 
        l2 = a.i(paramParcel, k);
        break;
      case 4: 
        l1 = a.i(paramParcel, k);
        break;
      case 5: 
        localArrayList3 = a.c(paramParcel, k, DataType.CREATOR);
        break;
      case 6: 
        localArrayList2 = a.c(paramParcel, k, DataSource.CREATOR);
        break;
      case 7: 
        bool2 = a.c(paramParcel, k);
        break;
      case 8: 
        bool1 = a.c(paramParcel, k);
        break;
      case 9: 
        localArrayList1 = a.C(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SessionReadRequest(i, str2, str1, l2, l1, localArrayList3, localArrayList2, bool2, bool1, localArrayList1);
  }
  
  public SessionReadRequest[] dd(int paramInt)
  {
    return new SessionReadRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */