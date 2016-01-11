package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Field;
import java.util.ArrayList;

public class i
  implements Parcelable.Creator<DataTypeCreateRequest>
{
  static void a(DataTypeCreateRequest paramDataTypeCreateRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramDataTypeCreateRequest.getName(), false);
    b.c(paramParcel, 1000, paramDataTypeCreateRequest.getVersionCode());
    b.c(paramParcel, 2, paramDataTypeCreateRequest.getFields(), false);
    b.H(paramParcel, paramInt);
  }
  
  public DataTypeCreateRequest bF(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int j = a.C(paramParcel);
    int i = 0;
    String str = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        str = a.o(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localArrayList = a.c(paramParcel, k, Field.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DataTypeCreateRequest(i, str, localArrayList);
  }
  
  public DataTypeCreateRequest[] cW(int paramInt)
  {
    return new DataTypeCreateRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */