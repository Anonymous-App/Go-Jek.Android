package com.kartuku.digitalwallet.common.dto.controller;

import com.kartuku.digitalwallet.common.dto.iam.UserBaseDto;
import com.kartuku.digitalwallet.common.entity.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserResponseDto
  extends UserBaseDto
{
  private Long ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private Long ᐝ;
  
  public UserResponseDto() {}
  
  public UserResponseDto(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public UserResponseDto(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramString1, paramString2);
    setUid(paramString3);
    setEmail(paramString4);
    this.ˋ = paramString5;
  }
  
  public final void fromEntity(User paramUser)
  {
    if (paramUser.getId() != null) {
      setId(paramUser.getId());
    }
    if (paramUser.getUid() != null) {
      setUid(paramUser.getUid());
    }
    if (paramUser.getEmail() != null) {
      setEmail(paramUser.getEmail());
    }
    if (paramUser.getFullname() != null) {
      setFullname(paramUser.getFullname());
    }
    if (paramUser.getPin() != null) {
      setPin(paramUser.getPin());
    }
    if (paramUser.getStatus() != null) {
      setStatus(paramUser.getStatus());
    }
    if (paramUser.getPhoneNumber() != null) {
      setPhoneNumber(paramUser.getPhoneNumber());
    }
    if (paramUser.getCardLinkId() != null) {
      setCardLinkId(paramUser.getCardLinkId());
    }
  }
  
  public final List<UserResponseDto> fromListEntity(List<User> paramList)
  {
    ArrayList localArrayList = null;
    if (paramList != null)
    {
      localArrayList = new ArrayList();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        User localUser = (User)paramList.next();
        UserResponseDto localUserResponseDto = new UserResponseDto("", "Member List");
        localUserResponseDto.fromEntity(localUser);
        localArrayList.add(localUserResponseDto);
      }
    }
    return localArrayList;
  }
  
  public String getCardLinkId()
  {
    return this.ˋ;
  }
  
  public Long getId()
  {
    return this.ˊ;
  }
  
  public String getPhoneNumber()
  {
    return this.ˏ;
  }
  
  public String getPrintedToken()
  {
    return this.ˎ;
  }
  
  public Long getStatus()
  {
    return this.ᐝ;
  }
  
  public void setCardLinkId(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setId(Long paramLong)
  {
    this.ˊ = paramLong;
  }
  
  public void setPhoneNumber(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setPrintedToken(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setStatus(Long paramLong)
  {
    this.ᐝ = paramLong;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/UserResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */