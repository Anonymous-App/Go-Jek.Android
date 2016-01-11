package com.kartuku.digitalwallet.common.dto.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kartuku.digitalwallet.common.entity.UserConfiguration;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserConfigurationResponseDto
  implements Serializable
{
  @JsonProperty
  public String code;
  @JsonProperty
  public String detail;
  @JsonProperty
  public String enablePin;
  @JsonProperty
  public String primaryCC;
  
  public UserConfigurationResponseDto() {}
  
  public UserConfigurationResponseDto(String paramString1, String paramString2)
  {
    this.code = paramString1;
    this.detail = paramString2;
  }
  
  public UserConfigurationResponseDto(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.code = paramString1;
    this.detail = paramString2;
    this.enablePin = paramString3;
    this.primaryCC = paramString4;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public String getDetail()
  {
    return this.detail;
  }
  
  public String getEnablePin()
  {
    return this.enablePin;
  }
  
  public String getPrimaryCC()
  {
    return this.primaryCC;
  }
  
  public void setCode(String paramString)
  {
    this.code = paramString;
  }
  
  public void setDetail(String paramString)
  {
    this.detail = paramString;
  }
  
  public void setEnablePin(String paramString)
  {
    this.enablePin = paramString;
  }
  
  public void setPrimaryCC(String paramString)
  {
    this.primaryCC = paramString;
  }
  
  public UserConfigurationResponseDto setResponse(String paramString1, String paramString2, UserConfiguration paramUserConfiguration)
  {
    this.code = paramString1;
    this.detail = paramString2;
    this.enablePin = paramUserConfiguration.getEnablePin();
    this.primaryCC = paramUserConfiguration.getPrimaryCC();
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/UserConfigurationResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */