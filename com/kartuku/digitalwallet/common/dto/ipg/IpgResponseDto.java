package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.digitalwallet.common.dto.controller.ResponseStatusDto;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpgResponseDto
  extends ResponseStatusDto
{
  private String ʻ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private String ᐝ;
  
  public String getIpgGateway()
  {
    return this.ˊ;
  }
  
  public String getIpgResponseCode()
  {
    return this.ˋ;
  }
  
  public String getIpgTxnReference()
  {
    return this.ˎ;
  }
  
  public String getRespMessage()
  {
    return this.ˏ;
  }
  
  public String getTxnIpAddress()
  {
    return this.ᐝ;
  }
  
  public String getTxnMID()
  {
    return this.ʻ;
  }
  
  public void setIpgGateway(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setIpgResponseCode(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setIpgTxnReference(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setRespMessage(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setTxnIpAddress(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTxnMID(String paramString)
  {
    this.ʻ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/IpgResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */