package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jr;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.d;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.p;
import java.util.ArrayList;

public final class LoyaltyWalletObject
  implements SafeParcelable
{
  public static final Parcelable.Creator<LoyaltyWalletObject> CREATOR = new j();
  private final int BR;
  String Dv;
  String asT;
  String asU;
  String asV;
  String asW;
  String asX;
  String asY;
  String asZ;
  String ata;
  ArrayList<p> atb;
  l atc;
  ArrayList<LatLng> atd;
  String ate;
  String atf;
  ArrayList<d> atg;
  boolean ath;
  ArrayList<n> ati;
  ArrayList<com.google.android.gms.wallet.wobs.j> atj;
  ArrayList<n> atk;
  f atl;
  String fl;
  int state;
  
  LoyaltyWalletObject()
  {
    this.BR = 4;
    this.atb = jr.hz();
    this.atd = jr.hz();
    this.atg = jr.hz();
    this.ati = jr.hz();
    this.atj = jr.hz();
    this.atk = jr.hz();
  }
  
  LoyaltyWalletObject(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, int paramInt2, ArrayList<p> paramArrayList, l paraml, ArrayList<LatLng> paramArrayList1, String paramString11, String paramString12, ArrayList<d> paramArrayList2, boolean paramBoolean, ArrayList<n> paramArrayList3, ArrayList<com.google.android.gms.wallet.wobs.j> paramArrayList4, ArrayList<n> paramArrayList5, f paramf)
  {
    this.BR = paramInt1;
    this.fl = paramString1;
    this.asT = paramString2;
    this.asU = paramString3;
    this.asV = paramString4;
    this.Dv = paramString5;
    this.asW = paramString6;
    this.asX = paramString7;
    this.asY = paramString8;
    this.asZ = paramString9;
    this.ata = paramString10;
    this.state = paramInt2;
    this.atb = paramArrayList;
    this.atc = paraml;
    this.atd = paramArrayList1;
    this.ate = paramString11;
    this.atf = paramString12;
    this.atg = paramArrayList2;
    this.ath = paramBoolean;
    this.ati = paramArrayList3;
    this.atj = paramArrayList4;
    this.atk = paramArrayList5;
    this.atl = paramf;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAccountId()
  {
    return this.asT;
  }
  
  public String getAccountName()
  {
    return this.Dv;
  }
  
  public String getBarcodeAlternateText()
  {
    return this.asW;
  }
  
  public String getBarcodeType()
  {
    return this.asX;
  }
  
  public String getBarcodeValue()
  {
    return this.asY;
  }
  
  public String getId()
  {
    return this.fl;
  }
  
  public String getIssuerName()
  {
    return this.asU;
  }
  
  public String getProgramName()
  {
    return this.asV;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    j.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/LoyaltyWalletObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */