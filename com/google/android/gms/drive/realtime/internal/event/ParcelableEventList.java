package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class ParcelableEventList
  implements SafeParcelable
{
  public static final Parcelable.Creator<ParcelableEventList> CREATOR = new c();
  final int BR;
  final DataHolder RE;
  final boolean RF;
  final List<String> RG;
  final List<ParcelableEvent> me;
  
  ParcelableEventList(int paramInt, List<ParcelableEvent> paramList, DataHolder paramDataHolder, boolean paramBoolean, List<String> paramList1)
  {
    this.BR = paramInt;
    this.me = paramList;
    this.RE = paramDataHolder;
    this.RF = paramBoolean;
    this.RG = paramList1;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/ParcelableEventList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */