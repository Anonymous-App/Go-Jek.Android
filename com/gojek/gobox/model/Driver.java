package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class Driver
{
  private String avatar;
  private long id;
  private String name;
  @SerializedName("phone_number")
  private String phoneNumber;
  
  public Driver(long paramLong, String paramString1, String paramString2, String paramString3)
  {
    this.id = paramLong;
    this.name = paramString1;
    this.phoneNumber = paramString2;
    this.avatar = paramString3;
  }
  
  public String getAvatar()
  {
    return this.avatar;
  }
  
  public long getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }
  
  public void setAvatar(String paramString)
  {
    this.avatar = paramString;
  }
  
  public void setId(long paramLong)
  {
    this.id = paramLong;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPhoneNumber(String paramString)
  {
    this.phoneNumber = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/Driver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */