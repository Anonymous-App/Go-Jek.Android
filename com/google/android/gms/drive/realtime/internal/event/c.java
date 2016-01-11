package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class c
  implements Parcelable.Creator<ParcelableEventList>
{
  static void a(ParcelableEventList paramParcelableEventList, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramParcelableEventList.BR);
    b.c(paramParcel, 2, paramParcelableEventList.me, false);
    b.a(paramParcel, 3, paramParcelableEventList.RE, paramInt, false);
    b.a(paramParcel, 4, paramParcelableEventList.RF);
    b.b(paramParcel, 5, paramParcelableEventList.RG, false);
    b.H(paramParcel, i);
  }
  
  public ParcelableEventList ba(Parcel paramParcel)
  {
    boolean bool = false;
    ArrayList localArrayList1 = null;
    int j = a.C(paramParcel);
    DataHolder localDataHolder = null;
    ArrayList localArrayList2 = null;
    int i = 0;
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
        localArrayList2 = a.c(paramParcel, k, ParcelableEvent.CREATOR);
        break;
      case 3: 
        localDataHolder = (DataHolder)a.a(paramParcel, k, DataHolder.CREATOR);
        break;
      case 4: 
        bool = a.c(paramParcel, k);
        break;
      case 5: 
        localArrayList1 = a.C(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ParcelableEventList(i, localArrayList2, localDataHolder, bool, localArrayList1);
  }
  
  public ParcelableEventList[] cn(int paramInt)
  {
    return new ParcelableEventList[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */