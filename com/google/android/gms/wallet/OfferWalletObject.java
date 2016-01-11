package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.CommonWalletObject.a;

public final class OfferWalletObject
  implements SafeParcelable
{
  public static final Parcelable.Creator<OfferWalletObject> CREATOR = new n();
  private final int BR;
  String atD;
  CommonWalletObject atE;
  String fl;
  
  OfferWalletObject()
  {
    this.BR = 3;
  }
  
  OfferWalletObject(int paramInt, String paramString1, String paramString2, CommonWalletObject paramCommonWalletObject)
  {
    this.BR = paramInt;
    this.atD = paramString2;
    if (paramInt < 3)
    {
      this.atE = CommonWalletObject.pQ().df(paramString1).pR();
      return;
    }
    this.atE = paramCommonWalletObject;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getId()
  {
    return this.atE.getId();
  }
  
  public String getRedemptionCode()
  {
    return this.atD;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    n.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/OfferWalletObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */