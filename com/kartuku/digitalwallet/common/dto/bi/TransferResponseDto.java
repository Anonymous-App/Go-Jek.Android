package com.kartuku.digitalwallet.common.dto.bi;

import com.kartuku.digitalwallet.common.dto.controller.ResponseStatusDto;
import java.util.Date;

public class TransferResponseDto
  extends ResponseStatusDto
{
  private String ˊ;
  private Date ˋ;
  private String ˎ;
  private String ˏ;
  
  public String getAccountFrom()
  {
    return this.ˎ;
  }
  
  public String getCompanyCode()
  {
    return this.ˏ;
  }
  
  public String getRequestID()
  {
    return this.ˊ;
  }
  
  public Date getRequestTime()
  {
    return this.ˋ;
  }
  
  public void setAccountFrom(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setCompanyCode(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setRequestID(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setRequestTime(Date paramDate)
  {
    this.ˋ = paramDate;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/bi/TransferResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */