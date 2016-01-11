package com.kartuku.digitalwallet.common.dto.controller;

import com.kartuku.digitalwallet.common.dto.cardlink.CardLinkRequest;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CardLinkDto
  extends CardLinkRequest
{
  private List<Card> ˊ;
  
  public CardLinkDto()
  {
    this.ˊ = new ArrayList();
  }
  
  public CardLinkDto(CardLinkDto paramCardLinkDto)
  {
    super.setCardLinkUserId(paramCardLinkDto.getCardLinkUserId());
    super.setReferenceNumber(paramCardLinkDto.getReferenceNumber());
    super.setTotalData(paramCardLinkDto.getTotalData());
    super.setTotalAmount(paramCardLinkDto.getTotalAmount());
    this.ˊ.addAll(paramCardLinkDto.getCardVoucherList());
  }
  
  public CardLinkDto(String paramString)
  {
    super(paramString);
  }
  
  public CardLinkDto(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public CardLinkDto(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2, paramString3);
  }
  
  public void addCardVoucherList(Card paramCard)
  {
    this.ˊ.add(paramCard);
  }
  
  public List<Card> getCardVoucherList()
  {
    return this.ˊ;
  }
  
  public void setCardVoucherList(List<Card> paramList)
  {
    this.ˊ = paramList;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/CardLinkDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */