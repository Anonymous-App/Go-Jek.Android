package com.kartuku.digitalwallet.common.dto.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DeviceImprintResponseDto
  extends ResponseStatusDto
  implements Serializable
{
  @JsonProperty
  private String ˊ;
  @JsonProperty
  private String ˋ;
  @JsonProperty
  private Date ˎ;
  @JsonProperty
  private String ˏ;
  @JsonProperty
  private Long ᐝ;
  
  public DeviceImprintResponseDto(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public DeviceImprintResponseDto(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramString1, paramString2);
    setUid(paramString3);
    setImei(paramString4);
  }
  
  public Date getExpiredDate()
  {
    return this.ˎ;
  }
  
  public String getImei()
  {
    return this.ˊ;
  }
  
  public Long getStatus()
  {
    return this.ᐝ;
  }
  
  public String getTokenAccess()
  {
    return this.ˋ;
  }
  
  public String getUid()
  {
    return this.ˏ;
  }
  
  public void setExpiredDate(Date paramDate)
  {
    this.ˎ = paramDate;
  }
  
  public void setImei(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setStatus(Long paramLong)
  {
    this.ᐝ = paramLong;
  }
  
  public void setTokenAccess(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setUid(String paramString)
  {
    this.ˏ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/DeviceImprintResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */