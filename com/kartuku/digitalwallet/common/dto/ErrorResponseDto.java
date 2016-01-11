package com.kartuku.digitalwallet.common.dto;

public class ErrorResponseDto
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  
  public ErrorResponseDto() {}
  
  public ErrorResponseDto(String paramString1, String paramString2)
  {
    this.ˊ = paramString1;
    this.ˎ = paramString2;
  }
  
  public ErrorResponseDto(String paramString1, String paramString2, String paramString3)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
    this.ˎ = paramString3;
  }
  
  public String getErrorCode()
  {
    return this.ˊ;
  }
  
  public String getErrordetail()
  {
    return this.ˎ;
  }
  
  public String getErrormessage()
  {
    return this.ˋ;
  }
  
  public void setErrorCode(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setErrordetail(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setErrormessage(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ErrorResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */