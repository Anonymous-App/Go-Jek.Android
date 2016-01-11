package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UserMetadata
  implements SafeParcelable
{
  public static final Parcelable.Creator<UserMetadata> CREATOR = new h();
  final int BR;
  final String NG;
  final String NH;
  final String NI;
  final boolean NJ;
  final String NK;
  
  UserMetadata(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
  {
    this.BR = paramInt;
    this.NG = paramString1;
    this.NH = paramString2;
    this.NI = paramString3;
    this.NJ = paramBoolean;
    this.NK = paramString4;
  }
  
  public UserMetadata(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
  {
    this(1, paramString1, paramString2, paramString3, paramBoolean, paramString4);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", new Object[] { this.NG, this.NH, this.NI, Boolean.valueOf(this.NJ), this.NK });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/UserMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */