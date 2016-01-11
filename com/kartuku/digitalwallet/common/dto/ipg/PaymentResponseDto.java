package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponseDto
  extends IpgResponseDto
{
  private String ʹ;
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ʾ;
  private String ʿ;
  private String ˈ;
  private String ˉ;
  private String ˊ;
  private String ˋ;
  private String ˌ;
  private String ˍ;
  private String ˎ;
  private String ˏ;
  private String ˑ;
  private String ͺ;
  private String ـ;
  private String ᐝ;
  private String ᐧ;
  private String ᐨ;
  private String ι;
  private String ﹳ;
  private String ﾞ;
  
  public String getCardExpiry()
  {
    return this.ˊ;
  }
  
  public String getCardNumberMasked()
  {
    return this.ˋ;
  }
  
  public String getCardToken()
  {
    return this.ˎ;
  }
  
  public String getIpgCaptureNo()
  {
    return this.ι;
  }
  
  public String getMerchantUserCode()
  {
    return this.ˏ;
  }
  
  public String getPaymentBrand()
  {
    return this.ͺ;
  }
  
  public String getPromoOriginalAmount()
  {
    return this.ˈ;
  }
  
  public String getPromoPointsEarned()
  {
    return this.ˉ;
  }
  
  public String getPromoTag()
  {
    return this.ˌ;
  }
  
  public String getRedirectUrl()
  {
    return this.ᐝ;
  }
  
  public String getTxnAmount()
  {
    return this.ʻ;
  }
  
  public String getTxnApprovalCode()
  {
    return this.ˍ;
  }
  
  public String getTxnAuthAmount()
  {
    return this.ʾ;
  }
  
  public String getTxnCapAmount()
  {
    return this.ʿ;
  }
  
  public String getTxnCurrency()
  {
    return this.ᐧ;
  }
  
  public String getTxnCustom1()
  {
    return this.ﹳ;
  }
  
  public String getTxnCustom2()
  {
    return this.ﾞ;
  }
  
  public String getTxnCustom3()
  {
    return this.ʹ;
  }
  
  public String getTxnRRN()
  {
    return this.ᐨ;
  }
  
  public String getTxnReference()
  {
    return this.ˑ;
  }
  
  public String getTxnResponseCode()
  {
    return this.ـ;
  }
  
  public String getTxnStoreCode()
  {
    return this.ʼ;
  }
  
  public String getTxnTradingDate()
  {
    return this.ʽ;
  }
  
  public void setCardExpiry(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setCardNumberMasked(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setCardToken(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setIpgCaptureNo(String paramString)
  {
    this.ι = paramString;
  }
  
  public void setMerchantUserCode(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setPaymentBrand(String paramString)
  {
    this.ͺ = paramString;
  }
  
  public void setPromoOriginalAmount(String paramString)
  {
    this.ˈ = paramString;
  }
  
  public void setPromoPointsEarned(String paramString)
  {
    this.ˉ = paramString;
  }
  
  public void setPromoTag(String paramString)
  {
    this.ˌ = paramString;
  }
  
  public void setRedirectUrl(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setTxnApprovalCode(String paramString)
  {
    this.ˍ = paramString;
  }
  
  public void setTxnAuthAmount(String paramString)
  {
    this.ʾ = paramString;
  }
  
  public void setTxnCapAmount(String paramString)
  {
    this.ʿ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ᐧ = paramString;
  }
  
  public void setTxnCustom1(String paramString)
  {
    this.ﹳ = paramString;
  }
  
  public void setTxnCustom2(String paramString)
  {
    this.ﾞ = paramString;
  }
  
  public void setTxnCustom3(String paramString)
  {
    this.ʹ = paramString;
  }
  
  public void setTxnRRN(String paramString)
  {
    this.ᐨ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ˑ = paramString;
  }
  
  public void setTxnResponseCode(String paramString)
  {
    this.ـ = paramString;
  }
  
  public void setTxnStoreCode(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setTxnTradingDate(String paramString)
  {
    this.ʽ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/PaymentResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */