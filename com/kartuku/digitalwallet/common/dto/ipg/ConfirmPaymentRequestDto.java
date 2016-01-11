package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfirmPaymentRequestDto
  extends IpgRequestDto
{
  private String ʻ;
  private String ʼ;
  private boolean ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private String ᐝ;
  
  public String getAuthIpgTxnReference()
  {
    return this.ʼ;
  }
  
  public String getAuthTxnReference()
  {
    return this.ʻ;
  }
  
  public String getIpgGateway()
  {
    return this.ˋ;
  }
  
  public String getTxnAmount()
  {
    return this.ˏ;
  }
  
  public String getTxnCurrency()
  {
    return this.ᐝ;
  }
  
  public String getTxnReference()
  {
    return this.ˎ;
  }
  
  public boolean isWithCvv()
  {
    return this.ˊ;
  }
  
  public void setAuthIpgTxnReference(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setAuthTxnReference(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setIpgGateway(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setWithCvv(boolean paramBoolean)
  {
    this.ˊ = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/ConfirmPaymentRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */