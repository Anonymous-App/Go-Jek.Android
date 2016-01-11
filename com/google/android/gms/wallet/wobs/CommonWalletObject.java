package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jr;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class CommonWalletObject
  implements SafeParcelable
{
  public static final Parcelable.Creator<CommonWalletObject> CREATOR = new a();
  private final int BR;
  String asU;
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
  ArrayList<j> atj;
  ArrayList<n> atk;
  String fl;
  String name;
  int state;
  
  CommonWalletObject()
  {
    this.BR = 1;
    this.atb = jr.hz();
    this.atd = jr.hz();
    this.atg = jr.hz();
    this.ati = jr.hz();
    this.atj = jr.hz();
    this.atk = jr.hz();
  }
  
  CommonWalletObject(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt2, ArrayList<p> paramArrayList, l paraml, ArrayList<LatLng> paramArrayList1, String paramString9, String paramString10, ArrayList<d> paramArrayList2, boolean paramBoolean, ArrayList<n> paramArrayList3, ArrayList<j> paramArrayList4, ArrayList<n> paramArrayList5)
  {
    this.BR = paramInt1;
    this.fl = paramString1;
    this.ata = paramString2;
    this.name = paramString3;
    this.asU = paramString4;
    this.asW = paramString5;
    this.asX = paramString6;
    this.asY = paramString7;
    this.asZ = paramString8;
    this.state = paramInt2;
    this.atb = paramArrayList;
    this.atc = paraml;
    this.atd = paramArrayList1;
    this.ate = paramString9;
    this.atf = paramString10;
    this.atg = paramArrayList2;
    this.ath = paramBoolean;
    this.ati = paramArrayList3;
    this.atj = paramArrayList4;
    this.atk = paramArrayList5;
  }
  
  public static a pQ()
  {
    CommonWalletObject localCommonWalletObject = new CommonWalletObject();
    localCommonWalletObject.getClass();
    return new a(null);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getId()
  {
    return this.fl;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
  
  public final class a
  {
    private a() {}
    
    public a df(String paramString)
    {
      CommonWalletObject.this.fl = paramString;
      return this;
    }
    
    public CommonWalletObject pR()
    {
      return CommonWalletObject.this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/wobs/CommonWalletObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */