package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateContentsRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<CreateContentsRequest> CREATOR = new g();
  final int BR;
  final int MV;
  
  public CreateContentsRequest(int paramInt)
  {
    this(1, paramInt);
  }
  
  CreateContentsRequest(int paramInt1, int paramInt2)
  {
    this.BR = paramInt1;
    if ((paramInt2 == 536870912) || (paramInt2 == 805306368)) {}
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "Cannot create a new read-only contents!");
      this.MV = paramInt2;
      return;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    g.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/CreateContentsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */