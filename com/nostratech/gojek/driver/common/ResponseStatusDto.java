package com.nostratech.gojek.driver.common;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseStatusDto
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  
  public ResponseStatusDto() {}
  
  public ResponseStatusDto(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public ResponseStatusDto(String paramString1, String paramString2)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
  }
  
  public ResponseStatusDto(String paramString1, String paramString2, String paramString3)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
    this.ˎ = paramString3;
  }
  
  public String getCode()
  {
    return this.ˊ;
  }
  
  public String getDetail()
  {
    return this.ˎ;
  }
  
  public String getMessage()
  {
    return this.ˋ;
  }
  
  public void setCode(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setDetail(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setMessage(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/nostratech/gojek/driver/common/ResponseStatusDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */