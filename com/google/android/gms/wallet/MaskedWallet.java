package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class MaskedWallet
  implements SafeParcelable
{
  public static final Parcelable.Creator<MaskedWallet> CREATOR = new k();
  private final int BR;
  String asB;
  String asC;
  String asE;
  Address asF;
  Address asG;
  String[] asH;
  UserAddress asI;
  UserAddress asJ;
  InstrumentInfo[] asK;
  LoyaltyWalletObject[] atm;
  OfferWalletObject[] atn;
  
  private MaskedWallet()
  {
    this.BR = 2;
  }
  
  MaskedWallet(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, Address paramAddress1, Address paramAddress2, LoyaltyWalletObject[] paramArrayOfLoyaltyWalletObject, OfferWalletObject[] paramArrayOfOfferWalletObject, UserAddress paramUserAddress1, UserAddress paramUserAddress2, InstrumentInfo[] paramArrayOfInstrumentInfo)
  {
    this.BR = paramInt;
    this.asB = paramString1;
    this.asC = paramString2;
    this.asH = paramArrayOfString;
    this.asE = paramString3;
    this.asF = paramAddress1;
    this.asG = paramAddress2;
    this.atm = paramArrayOfLoyaltyWalletObject;
    this.atn = paramArrayOfOfferWalletObject;
    this.asI = paramUserAddress1;
    this.asJ = paramUserAddress2;
    this.asK = paramArrayOfInstrumentInfo;
  }
  
  public static Builder newBuilderFrom(MaskedWallet paramMaskedWallet)
  {
    o.i(paramMaskedWallet);
    return pM().setGoogleTransactionId(paramMaskedWallet.getGoogleTransactionId()).setMerchantTransactionId(paramMaskedWallet.getMerchantTransactionId()).setPaymentDescriptions(paramMaskedWallet.getPaymentDescriptions()).setInstrumentInfos(paramMaskedWallet.getInstrumentInfos()).setEmail(paramMaskedWallet.getEmail()).setLoyaltyWalletObjects(paramMaskedWallet.getLoyaltyWalletObjects()).setOfferWalletObjects(paramMaskedWallet.getOfferWalletObjects()).setBuyerBillingAddress(paramMaskedWallet.getBuyerBillingAddress()).setBuyerShippingAddress(paramMaskedWallet.getBuyerShippingAddress());
  }
  
  public static Builder pM()
  {
    MaskedWallet localMaskedWallet = new MaskedWallet();
    localMaskedWallet.getClass();
    return new Builder(null);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  @Deprecated
  public Address getBillingAddress()
  {
    return this.asF;
  }
  
  public UserAddress getBuyerBillingAddress()
  {
    return this.asI;
  }
  
  public UserAddress getBuyerShippingAddress()
  {
    return this.asJ;
  }
  
  public String getEmail()
  {
    return this.asE;
  }
  
  public String getGoogleTransactionId()
  {
    return this.asB;
  }
  
  public InstrumentInfo[] getInstrumentInfos()
  {
    return this.asK;
  }
  
  public LoyaltyWalletObject[] getLoyaltyWalletObjects()
  {
    return this.atm;
  }
  
  public String getMerchantTransactionId()
  {
    return this.asC;
  }
  
  public OfferWalletObject[] getOfferWalletObjects()
  {
    return this.atn;
  }
  
  public String[] getPaymentDescriptions()
  {
    return this.asH;
  }
  
  @Deprecated
  public Address getShippingAddress()
  {
    return this.asG;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    k.a(this, paramParcel, paramInt);
  }
  
  public final class Builder
  {
    private Builder() {}
    
    public MaskedWallet build()
    {
      return MaskedWallet.this;
    }
    
    public Builder setBillingAddress(Address paramAddress)
    {
      MaskedWallet.this.asF = paramAddress;
      return this;
    }
    
    public Builder setBuyerBillingAddress(UserAddress paramUserAddress)
    {
      MaskedWallet.this.asI = paramUserAddress;
      return this;
    }
    
    public Builder setBuyerShippingAddress(UserAddress paramUserAddress)
    {
      MaskedWallet.this.asJ = paramUserAddress;
      return this;
    }
    
    public Builder setEmail(String paramString)
    {
      MaskedWallet.this.asE = paramString;
      return this;
    }
    
    public Builder setGoogleTransactionId(String paramString)
    {
      MaskedWallet.this.asB = paramString;
      return this;
    }
    
    public Builder setInstrumentInfos(InstrumentInfo[] paramArrayOfInstrumentInfo)
    {
      MaskedWallet.this.asK = paramArrayOfInstrumentInfo;
      return this;
    }
    
    public Builder setLoyaltyWalletObjects(LoyaltyWalletObject[] paramArrayOfLoyaltyWalletObject)
    {
      MaskedWallet.this.atm = paramArrayOfLoyaltyWalletObject;
      return this;
    }
    
    public Builder setMerchantTransactionId(String paramString)
    {
      MaskedWallet.this.asC = paramString;
      return this;
    }
    
    public Builder setOfferWalletObjects(OfferWalletObject[] paramArrayOfOfferWalletObject)
    {
      MaskedWallet.this.atn = paramArrayOfOfferWalletObject;
      return this;
    }
    
    public Builder setPaymentDescriptions(String[] paramArrayOfString)
    {
      MaskedWallet.this.asH = paramArrayOfString;
      return this;
    }
    
    public Builder setShippingAddress(Address paramAddress)
    {
      MaskedWallet.this.asG = paramAddress;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/MaskedWallet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */