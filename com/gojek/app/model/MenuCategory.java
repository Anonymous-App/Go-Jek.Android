package com.gojek.app.model;

public class MenuCategory
{
  public String code;
  public int id;
  public int merchantId;
  public String name;
  
  public String getCode()
  {
    return this.code;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getMerchantId()
  {
    return this.merchantId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setCode(String paramString)
  {
    this.code = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setMerchantId(int paramInt)
  {
    this.merchantId = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/MenuCategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */