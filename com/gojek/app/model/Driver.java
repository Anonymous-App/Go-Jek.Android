package com.gojek.app.model;

public class Driver
{
  public int id;
  public String name;
  public String phone;
  public String photograph;
  public int status;
  
  public int getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public String getPhotograph()
  {
    return this.photograph;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }
  
  public void setPhotograph(String paramString)
  {
    this.photograph = paramString;
  }
  
  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("Driver{");
    localStringBuffer.append("id=").append(this.id);
    localStringBuffer.append(", name='").append(this.name).append('\'');
    localStringBuffer.append(", phone='").append(this.phone).append('\'');
    localStringBuffer.append(", photograph='").append(this.photograph).append('\'');
    localStringBuffer.append(", status=").append(this.status);
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/Driver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */