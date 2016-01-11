package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableCollaborator
  implements SafeParcelable
{
  public static final Parcelable.Creator<ParcelableCollaborator> CREATOR = new p();
  final int BR;
  final String NH;
  final boolean Rk;
  final boolean Rl;
  final String Rm;
  final String Rn;
  final String Ro;
  final String vL;
  
  ParcelableCollaborator(int paramInt, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.BR = paramInt;
    this.Rk = paramBoolean1;
    this.Rl = paramBoolean2;
    this.vL = paramString1;
    this.Rm = paramString2;
    this.NH = paramString3;
    this.Rn = paramString4;
    this.Ro = paramString5;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof ParcelableCollaborator)) {
      return false;
    }
    paramObject = (ParcelableCollaborator)paramObject;
    return this.vL.equals(((ParcelableCollaborator)paramObject).vL);
  }
  
  public int hashCode()
  {
    return this.vL.hashCode();
  }
  
  public String toString()
  {
    return "Collaborator [isMe=" + this.Rk + ", isAnonymous=" + this.Rl + ", sessionId=" + this.vL + ", userId=" + this.Rm + ", displayName=" + this.NH + ", color=" + this.Rn + ", photoUrl=" + this.Ro + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    p.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/ParcelableCollaborator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */