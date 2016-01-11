package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class ParcelableEvent
  implements SafeParcelable
{
  public static final Parcelable.Creator<ParcelableEvent> CREATOR = new b();
  final int BR;
  final ValuesSetDetails RA;
  final ValueChangedDetails RB;
  final ReferenceShiftedDetails RC;
  final ObjectChangedDetails RD;
  final String Rm;
  final String Rp;
  final List<String> Rt;
  final boolean Ru;
  final String Rv;
  final TextInsertedDetails Rw;
  final TextDeletedDetails Rx;
  final ValuesAddedDetails Ry;
  final ValuesRemovedDetails Rz;
  final String vL;
  
  ParcelableEvent(int paramInt, String paramString1, String paramString2, List<String> paramList, boolean paramBoolean, String paramString3, String paramString4, TextInsertedDetails paramTextInsertedDetails, TextDeletedDetails paramTextDeletedDetails, ValuesAddedDetails paramValuesAddedDetails, ValuesRemovedDetails paramValuesRemovedDetails, ValuesSetDetails paramValuesSetDetails, ValueChangedDetails paramValueChangedDetails, ReferenceShiftedDetails paramReferenceShiftedDetails, ObjectChangedDetails paramObjectChangedDetails)
  {
    this.BR = paramInt;
    this.vL = paramString1;
    this.Rm = paramString2;
    this.Rt = paramList;
    this.Ru = paramBoolean;
    this.Rp = paramString3;
    this.Rv = paramString4;
    this.Rw = paramTextInsertedDetails;
    this.Rx = paramTextDeletedDetails;
    this.Ry = paramValuesAddedDetails;
    this.Rz = paramValuesRemovedDetails;
    this.RA = paramValuesSetDetails;
    this.RB = paramValueChangedDetails;
    this.RC = paramReferenceShiftedDetails;
    this.RD = paramObjectChangedDetails;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/ParcelableEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */