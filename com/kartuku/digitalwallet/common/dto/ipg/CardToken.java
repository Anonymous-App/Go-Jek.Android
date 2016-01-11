package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardToken
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  
  public String getCardExpiry()
  {
    return this.ˎ;
  }
  
  public String getCardNumberMasked()
  {
    return this.ˋ;
  }
  
  public String getCardToken()
  {
    return this.ˊ;
  }
  
  public void setCardExpiry(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setCardNumberMasked(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setCardToken(String paramString)
  {
    this.ˊ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/CardToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */