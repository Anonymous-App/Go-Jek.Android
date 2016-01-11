package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenListRequestDto
  extends IpgRequestDto
{
  private String ˊ;
  
  public String getMerchantUserCode()
  {
    return this.ˊ;
  }
  
  public void setMerchantUserCode(String paramString)
  {
    this.ˊ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/TokenListRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */