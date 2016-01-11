package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenStoreRequestDto
  extends IpgRequestDto
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private String ᐝ;
  
  public String getCardCVV()
  {
    return this.ˊ;
  }
  
  public String getCardExpiry()
  {
    return this.ˋ;
  }
  
  public String getCardNumber()
  {
    return this.ˎ;
  }
  
  public String getCardToken()
  {
    return this.ˏ;
  }
  
  public String getMerchantUserCode()
  {
    return this.ᐝ;
  }
  
  public String getTxnAmount()
  {
    return this.ʻ;
  }
  
  public String getTxnReference()
  {
    return this.ʼ;
  }
  
  public String getType()
  {
    return this.ʽ;
  }
  
  public void setCardCVV(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setCardExpiry(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setCardNumber(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setCardToken(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setMerchantUserCode(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setType(String paramString)
  {
    this.ʽ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/TokenStoreRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */