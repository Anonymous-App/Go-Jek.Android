package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;

public final class MaskedWalletRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<MaskedWalletRequest> CREATOR = new l();
  private final int BR;
  String asC;
  Cart asL;
  String asw;
  boolean atp;
  boolean atq;
  boolean atr;
  String ats;
  String att;
  boolean atu;
  boolean atv;
  CountrySpecification[] atw;
  boolean atx;
  boolean aty;
  ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> atz;
  
  MaskedWalletRequest()
  {
    this.BR = 3;
    this.atx = true;
    this.aty = true;
  }
  
  MaskedWalletRequest(int paramInt, String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, String paramString3, String paramString4, Cart paramCart, boolean paramBoolean4, boolean paramBoolean5, CountrySpecification[] paramArrayOfCountrySpecification, boolean paramBoolean6, boolean paramBoolean7, ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> paramArrayList)
  {
    this.BR = paramInt;
    this.asC = paramString1;
    this.atp = paramBoolean1;
    this.atq = paramBoolean2;
    this.atr = paramBoolean3;
    this.ats = paramString2;
    this.asw = paramString3;
    this.att = paramString4;
    this.asL = paramCart;
    this.atu = paramBoolean4;
    this.atv = paramBoolean5;
    this.atw = paramArrayOfCountrySpecification;
    this.atx = paramBoolean6;
    this.aty = paramBoolean7;
    this.atz = paramArrayList;
  }
  
  public static Builder newBuilder()
  {
    MaskedWalletRequest localMaskedWalletRequest = new MaskedWalletRequest();
    localMaskedWalletRequest.getClass();
    return new Builder(null);
  }
  
  public boolean allowDebitCard()
  {
    return this.aty;
  }
  
  public boolean allowPrepaidCard()
  {
    return this.atx;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> getAllowedCountrySpecificationsForShipping()
  {
    return this.atz;
  }
  
  public CountrySpecification[] getAllowedShippingCountrySpecifications()
  {
    return this.atw;
  }
  
  public Cart getCart()
  {
    return this.asL;
  }
  
  public String getCurrencyCode()
  {
    return this.asw;
  }
  
  public String getEstimatedTotalPrice()
  {
    return this.ats;
  }
  
  public String getMerchantName()
  {
    return this.att;
  }
  
  public String getMerchantTransactionId()
  {
    return this.asC;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public boolean isBillingAgreement()
  {
    return this.atv;
  }
  
  public boolean isPhoneNumberRequired()
  {
    return this.atp;
  }
  
  public boolean isShippingAddressRequired()
  {
    return this.atq;
  }
  
  public boolean shouldRetrieveWalletObjects()
  {
    return this.atu;
  }
  
  public boolean useMinimalBillingAddress()
  {
    return this.atr;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    l.a(this, paramParcel, paramInt);
  }
  
  public final class Builder
  {
    private Builder() {}
    
    public Builder addAllowedCountrySpecificationForShipping(com.google.android.gms.identity.intents.model.CountrySpecification paramCountrySpecification)
    {
      if (MaskedWalletRequest.this.atz == null) {
        MaskedWalletRequest.this.atz = new ArrayList();
      }
      MaskedWalletRequest.this.atz.add(paramCountrySpecification);
      return this;
    }
    
    public Builder addAllowedCountrySpecificationsForShipping(Collection<com.google.android.gms.identity.intents.model.CountrySpecification> paramCollection)
    {
      if (paramCollection != null)
      {
        if (MaskedWalletRequest.this.atz == null) {
          MaskedWalletRequest.this.atz = new ArrayList();
        }
        MaskedWalletRequest.this.atz.addAll(paramCollection);
      }
      return this;
    }
    
    public MaskedWalletRequest build()
    {
      return MaskedWalletRequest.this;
    }
    
    public Builder setAllowDebitCard(boolean paramBoolean)
    {
      MaskedWalletRequest.this.aty = paramBoolean;
      return this;
    }
    
    public Builder setAllowPrepaidCard(boolean paramBoolean)
    {
      MaskedWalletRequest.this.atx = paramBoolean;
      return this;
    }
    
    public Builder setCart(Cart paramCart)
    {
      MaskedWalletRequest.this.asL = paramCart;
      return this;
    }
    
    public Builder setCurrencyCode(String paramString)
    {
      MaskedWalletRequest.this.asw = paramString;
      return this;
    }
    
    public Builder setEstimatedTotalPrice(String paramString)
    {
      MaskedWalletRequest.this.ats = paramString;
      return this;
    }
    
    public Builder setIsBillingAgreement(boolean paramBoolean)
    {
      MaskedWalletRequest.this.atv = paramBoolean;
      return this;
    }
    
    public Builder setMerchantName(String paramString)
    {
      MaskedWalletRequest.this.att = paramString;
      return this;
    }
    
    public Builder setMerchantTransactionId(String paramString)
    {
      MaskedWalletRequest.this.asC = paramString;
      return this;
    }
    
    public Builder setPhoneNumberRequired(boolean paramBoolean)
    {
      MaskedWalletRequest.this.atp = paramBoolean;
      return this;
    }
    
    public Builder setShippingAddressRequired(boolean paramBoolean)
    {
      MaskedWalletRequest.this.atq = paramBoolean;
      return this;
    }
    
    public Builder setShouldRetrieveWalletObjects(boolean paramBoolean)
    {
      MaskedWalletRequest.this.atu = paramBoolean;
      return this;
    }
    
    public Builder setUseMinimalBillingAddress(boolean paramBoolean)
    {
      MaskedWalletRequest.this.atr = paramBoolean;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/MaskedWalletRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */