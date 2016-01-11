package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import java.util.ArrayList;

public class b
  implements Parcelable.Creator<SortOrder>
{
  static void a(SortOrder paramSortOrder, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1000, paramSortOrder.BR);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramSortOrder.QI, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramSortOrder.QJ);
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, paramInt);
  }
  
  public SortOrder aJ(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    ArrayList localArrayList = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 1: 
        localArrayList = a.c(paramParcel, k, FieldWithSortOrder.CREATOR);
        break;
      case 2: 
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SortOrder(i, localArrayList, bool);
  }
  
  public SortOrder[] bV(int paramInt)
  {
    return new SortOrder[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */