package com.kartuku.digitalwallet.dto;

import com.kartuku.digitalwallet.common.dto.controller.ResponseReportTrxDto;
import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ListReportTrxDto
  implements Serializable
{
  private List<ResponseReportTrxDto> ˊ;
  
  public ListReportTrxDto() {}
  
  public ListReportTrxDto(List<ResponseReportTrxDto> paramList)
  {
    this.ˊ = paramList;
  }
  
  public List<ResponseReportTrxDto> getReport()
  {
    return this.ˊ;
  }
  
  public void setReport(List<ResponseReportTrxDto> paramList)
  {
    this.ˊ = paramList;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/dto/ListReportTrxDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */