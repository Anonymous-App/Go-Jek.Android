package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoidPaymentRequestDto
  extends IpgRequestDto
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private boolean ᐝ;
  
  public String getIpgCaptureNo()
  {
    return this.ˏ;
  }
  
  public String getIpgGateway()
  {
    return this.ˊ;
  }
  
  public String getIpgTxnReference()
  {
    return this.ˎ;
  }
  
  public String getTxnReference()
  {
    return this.ˋ;
  }
  
  public boolean isWithCvv()
  {
    return this.ᐝ;
  }
  
  public void setIpgCaptureNo(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setIpgGateway(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setIpgTxnReference(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setWithCvv(boolean paramBoolean)
  {
    this.ᐝ = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/VoidPaymentRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */