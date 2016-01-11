package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenListResponseDto
  extends IpgResponseDto
{
  private List<CardToken> ˊ;
  private String ˋ;
  
  public List<CardToken> getCardTokens()
  {
    return this.ˊ;
  }
  
  public String getMerchantUserCode()
  {
    return this.ˋ;
  }
  
  public void setCardTokens(List<CardToken> paramList)
  {
    this.ˊ = paramList;
  }
  
  public void setMerchantUserCode(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/TokenListResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */