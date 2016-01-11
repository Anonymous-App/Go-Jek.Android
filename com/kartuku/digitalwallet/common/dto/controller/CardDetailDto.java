package com.kartuku.digitalwallet.common.dto.controller;

import com.kartuku.digitalwallet.common.dto.prepaid.PrepaidCardBalanceDto;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CardDetailDto
  extends ResponseStatusDto
{
  private PrepaidCardBalanceDto ˊ;
  private List<ResponseReportTrxDto> ˋ;
  private List<ResponseReportTrxDto> ˎ;
  
  public CardDetailDto() {}
  
  public CardDetailDto(PrepaidCardBalanceDto paramPrepaidCardBalanceDto, List<ResponseReportTrxDto> paramList1, List<ResponseReportTrxDto> paramList2)
  {
    this.ˊ = paramPrepaidCardBalanceDto;
    this.ˋ = paramList1;
    this.ˎ = paramList2;
  }
  
  public CardDetailDto(String paramString1, String paramString2)
  {
    this.ˊ = new PrepaidCardBalanceDto();
    super.setCode(paramString1);
    super.setDetail(paramString2);
  }
  
  public PrepaidCardBalanceDto getCardInfo()
  {
    return this.ˊ;
  }
  
  public List<ResponseReportTrxDto> getTransactionSalesDetail()
  {
    return this.ˎ;
  }
  
  public List<ResponseReportTrxDto> getTransactionTopUpDetail()
  {
    return this.ˋ;
  }
  
  public void setCardInfo(PrepaidCardBalanceDto paramPrepaidCardBalanceDto)
  {
    this.ˊ = paramPrepaidCardBalanceDto;
  }
  
  public void setResponse(String paramString1, String paramString2)
  {
    super.setCode(paramString1);
    super.setDetail(paramString2);
  }
  
  public void setTransactionSalesDetail(List<ResponseReportTrxDto> paramList)
  {
    this.ˎ = paramList;
  }
  
  public void setTransactionTopUpDetail(List<ResponseReportTrxDto> paramList)
  {
    this.ˋ = paramList;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/CardDetailDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */