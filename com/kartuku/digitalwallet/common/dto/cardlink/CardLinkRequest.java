package com.kartuku.digitalwallet.common.dto.cardlink;

import com.kartuku.digitalwallet.common.dto.controller.ResponseStatusDto;

public class CardLinkRequest
  extends ResponseStatusDto
{
  private String ˊ;
  private String ˋ;
  private Integer ˎ;
  private Double ˏ;
  
  public CardLinkRequest() {}
  
  public CardLinkRequest(String paramString)
  {
    super(paramString);
  }
  
  public CardLinkRequest(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public CardLinkRequest(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2, paramString3);
  }
  
  public String getCardLinkUserId()
  {
    return this.ˊ;
  }
  
  public String getReferenceNumber()
  {
    return this.ˋ;
  }
  
  public Double getTotalAmount()
  {
    return this.ˏ;
  }
  
  public Integer getTotalData()
  {
    return this.ˎ;
  }
  
  public void setCardLinkUserId(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setReferenceNumber(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setTotalAmount(Double paramDouble)
  {
    this.ˏ = paramDouble;
  }
  
  public void setTotalData(Integer paramInteger)
  {
    this.ˎ = paramInteger;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/cardlink/CardLinkRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */