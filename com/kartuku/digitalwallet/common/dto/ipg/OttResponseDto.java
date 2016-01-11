package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OttResponseDto
  extends IpgResponseDto
{
  private String ˊ;
  private String ˋ;
  
  public String getToken()
  {
    return this.ˊ;
  }
  
  public String getUrl()
  {
    return this.ˋ;
  }
  
  public void setToken(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setUrl(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/OttResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */