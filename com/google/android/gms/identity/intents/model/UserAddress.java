package com.google.android.gms.identity.intents.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UserAddress
  implements SafeParcelable
{
  public static final Parcelable.Creator<UserAddress> CREATOR = new b();
  private final int BR;
  String adN;
  String adO;
  String adP;
  String adQ;
  String adR;
  String adS;
  String adT;
  String adU;
  String adV;
  String adW;
  boolean adX;
  String adY;
  String adZ;
  String name;
  String uW;
  
  UserAddress()
  {
    this.BR = 1;
  }
  
  UserAddress(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, boolean paramBoolean, String paramString13, String paramString14)
  {
    this.BR = paramInt;
    this.name = paramString1;
    this.adN = paramString2;
    this.adO = paramString3;
    this.adP = paramString4;
    this.adQ = paramString5;
    this.adR = paramString6;
    this.adS = paramString7;
    this.adT = paramString8;
    this.uW = paramString9;
    this.adU = paramString10;
    this.adV = paramString11;
    this.adW = paramString12;
    this.adX = paramBoolean;
    this.adY = paramString13;
    this.adZ = paramString14;
  }
  
  public static UserAddress fromIntent(Intent paramIntent)
  {
    if ((paramIntent == null) || (!paramIntent.hasExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS"))) {
      return null;
    }
    return (UserAddress)paramIntent.getParcelableExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS");
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
  
  public String getAddress4()
  {
    return this.adQ;
  }
  
  public String getAddress5()
  {
    return this.adR;
  }
  
  public String getAdministrativeArea()
  {
    return this.adS;
  }
  
  public String getCompanyName()
  {
    return this.adY;
  }
  
  public String getCountryCode()
  {
    return this.uW;
  }
  
  public String getEmailAddress()
  {
    return this.adZ;
  }
  
  public String getLocality()
  {
    return this.adT;
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
  
  public String getSortingCode()
  {
    return this.adV;
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
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/identity/intents/model/UserAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */