package com.kartuku.digitalwallet.common.dto.iam;

import com.kartuku.digitalwallet.common.dto.controller.ResponseStatusDto;

public class IdentityDto
  extends ResponseStatusDto
{
  protected Long id;
  protected String name;
  
  public IdentityDto() {}
  
  public IdentityDto(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/iam/IdentityDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */