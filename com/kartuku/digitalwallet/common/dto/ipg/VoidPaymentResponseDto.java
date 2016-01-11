package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoidPaymentResponseDto
  extends IpgResponseDto
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private String ͺ;
  private String ᐝ;
  
  public String getTxnAmount()
  {
    return this.ˊ;
  }
  
  public String getTxnApprovalCode()
  {
    return this.ˋ;
  }
  
  public String getTxnAuthAmount()
  {
    return this.ʼ;
  }
  
  public String getTxnCapAmount()
  {
    return this.ʽ;
  }
  
  public String getTxnCurrency()
  {
    return this.ˎ;
  }
  
  public String getTxnRRN()
  {
    return this.ˏ;
  }
  
  public String getTxnReference()
  {
    return this.ᐝ;
  }
  
  public String getTxnRefundedAmount()
  {
    return this.ͺ;
  }
  
  public String getTxnResponseCode()
  {
    return this.ʻ;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setTxnApprovalCode(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setTxnAuthAmount(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setTxnCapAmount(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setTxnRRN(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTxnRefundedAmount(String paramString)
  {
    this.ͺ = paramString;
  }
  
  public void setTxnResponseCode(String paramString)
  {
    this.ʻ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/VoidPaymentResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */