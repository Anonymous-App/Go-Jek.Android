package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpgRequestDto
{
  private boolean ˊ;
  private String ˋ;
  private String ˎ;
  
  public String getProductType()
  {
    return this.ˎ;
  }
  
  public String getReceiveUrl()
  {
    return this.ˋ;
  }
  
  public boolean isWithNotification()
  {
    return this.ˊ;
  }
  
  public void setProductType(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setReceiveUrl(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setWithNotification(boolean paramBoolean)
  {
    this.ˊ = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/IpgRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */