package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import java.util.ArrayList;

public class b
  implements Parcelable.Creator<ParcelableEvent>
{
  static void a(ParcelableEvent paramParcelableEvent, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramParcelableEvent.BR);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramParcelableEvent.vL, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 3, paramParcelableEvent.Rm, false);
    com.google.android.gms.common.internal.safeparcel.b.b(paramParcel, 4, paramParcelableEvent.Rt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 5, paramParcelableEvent.Ru);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 6, paramParcelableEvent.Rp, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 7, paramParcelableEvent.Rv, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 8, paramParcelableEvent.Rw, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 9, paramParcelableEvent.Rx, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 10, paramParcelableEvent.Ry, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 11, paramParcelableEvent.Rz, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 12, paramParcelableEvent.RA, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 13, paramParcelableEvent.RB, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 14, paramParcelableEvent.RC, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 15, paramParcelableEvent.RD, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, i);
  }
  
  public ParcelableEvent aZ(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    String str4 = null;
    String str3 = null;
    ArrayList localArrayList = null;
    boolean bool = false;
    String str2 = null;
    String str1 = null;
    TextInsertedDetails localTextInsertedDetails = null;
    TextDeletedDetails localTextDeletedDetails = null;
    ValuesAddedDetails localValuesAddedDetails = null;
    ValuesRemovedDetails localValuesRemovedDetails = null;
    ValuesSetDetails localValuesSetDetails = null;
    ValueChangedDetails localValueChangedDetails = null;
    ReferenceShiftedDetails localReferenceShiftedDetails = null;
    ObjectChangedDetails localObjectChangedDetails = null;
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
        str4 = a.o(paramParcel, k);
        break;
      case 3: 
        str3 = a.o(paramParcel, k);
        break;
      case 4: 
        localArrayList = a.C(paramParcel, k);
        break;
      case 5: 
        bool = a.c(paramParcel, k);
        break;
      case 6: 
        str2 = a.o(paramParcel, k);
        break;
      case 7: 
        str1 = a.o(paramParcel, k);
        break;
      case 8: 
        localTextInsertedDetails = (TextInsertedDetails)a.a(paramParcel, k, TextInsertedDetails.CREATOR);
        break;
      case 9: 
        localTextDeletedDetails = (TextDeletedDetails)a.a(paramParcel, k, TextDeletedDetails.CREATOR);
        break;
      case 10: 
        localValuesAddedDetails = (ValuesAddedDetails)a.a(paramParcel, k, ValuesAddedDetails.CREATOR);
        break;
      case 11: 
        localValuesRemovedDetails = (ValuesRemovedDetails)a.a(paramParcel, k, ValuesRemovedDetails.CREATOR);
        break;
      case 12: 
        localValuesSetDetails = (ValuesSetDetails)a.a(paramParcel, k, ValuesSetDetails.CREATOR);
        break;
      case 13: 
        localValueChangedDetails = (ValueChangedDetails)a.a(paramParcel, k, ValueChangedDetails.CREATOR);
        break;
      case 14: 
        localReferenceShiftedDetails = (ReferenceShiftedDetails)a.a(paramParcel, k, ReferenceShiftedDetails.CREATOR);
        break;
      case 15: 
        localObjectChangedDetails = (ObjectChangedDetails)a.a(paramParcel, k, ObjectChangedDetails.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ParcelableEvent(i, str4, str3, localArrayList, bool, str2, str1, localTextInsertedDetails, localTextDeletedDetails, localValuesAddedDetails, localValuesRemovedDetails, localValuesSetDetails, localValueChangedDetails, localReferenceShiftedDetails, localObjectChangedDetails);
  }
  
  public ParcelableEvent[] cm(int paramInt)
  {
    return new ParcelableEvent[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */