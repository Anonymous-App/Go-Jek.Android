package com.kartuku.digitalwallet.common.dto.controller;

import com.kartuku.digitalwallet.common.dto.cardlink.CardLinkRequest;
import java.util.List;

public class CardLinkRequestDto
  extends CardLinkRequest
{
  private boolean ˊ;
  private List<CardRequestDto> ˋ;
  
  public List<CardRequestDto> getCardVoucherList()
  {
    return this.ˋ;
  }
  
  public boolean isValidateCC()
  {
    return this.ˊ;
  }
  
  public void setCardVoucherList(List<CardRequestDto> paramList)
  {
    this.ˋ = paramList;
  }
  
  public void setValidateCC(boolean paramBoolean)
  {
    this.ˊ = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/CardLinkRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */