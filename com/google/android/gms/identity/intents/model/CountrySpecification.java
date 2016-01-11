package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CountrySpecification
  implements SafeParcelable
{
  public static final Parcelable.Creator<CountrySpecification> CREATOR = new a();
  private final int BR;
  String uW;
  
  CountrySpecification(int paramInt, String paramString)
  {
    this.BR = paramInt;
    this.uW = paramString;
  }
  
  public CountrySpecification(String paramString)
  {
    this.BR = 1;
    this.uW = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getCountryCode()
  {
    return this.uW;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/identity/intents/model/CountrySpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */