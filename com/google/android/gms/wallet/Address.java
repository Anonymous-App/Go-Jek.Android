package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Deprecated
public final class Address
  implements SafeParcelable
{
  public static final Parcelable.Creator<Address> CREATOR = new a();
  private final int BR;
  String adN;
  String adO;
  String adP;
  String adU;
  String adW;
  boolean adX;
  String adY;
  String ast;
  String asu;
  String name;
  String uW;
  
  Address()
  {
    this.BR = 1;
  }
  
  Address(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, String paramString10)
  {
    this.BR = paramInt;
    this.name = paramString1;
    this.adN = paramString2;
    this.adO = paramString3;
    this.adP = paramString4;
    this.uW = paramString5;
    this.ast = paramString6;
    this.asu = paramString7;
    this.adU = paramString8;
    this.adW = paramString9;
    this.adX = paramBoolean;
    this.adY = paramString10;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAddress1()
  {
    return this.adN;
  }
  
  public String getAddress2()
  {
    return this.adO;
  }
  
  public String getAddress3()
  {
    return this.adP;
  }
  
  public String getCity()
  {
    return this.ast;
  }
  
  public String getCompanyName()
  {
    return this.adY;
  }
  
  public String getCountryCode()
  {
    return this.uW;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPhoneNumber()
  {
    return this.adW;
  }
  
  public String getPostalCode()
  {
    return this.adU;
  }
  
  public String getState()
  {
    return this.asu;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public boolean isPostBox()
  {
    return this.adX;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/Address.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */