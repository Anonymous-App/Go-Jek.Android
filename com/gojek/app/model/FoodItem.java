package com.gojek.app.model;

public class FoodItem
{
  public String description;
  public int id;
  public String imgLocation;
  public String itemCategories;
  public int merchantId;
  public String name;
  public int price;
  
  public String getDescription()
  {
    return this.description;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getImgLocation()
  {
    return this.imgLocation;
  }
  
  public String getItemCategories()
  {
    return this.itemCategories;
  }
  
  public int getMerchantId()
  {
    return this.merchantId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getPrice()
  {
    return this.price;
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setImgLocation(String paramString)
  {
    this.imgLocation = paramString;
  }
  
  public void setItemCategories(String paramString)
  {
    this.itemCategories = paramString;
  }
  
  public void setMerchantId(int paramInt)
  {
    this.merchantId = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPrice(int paramInt)
  {
    this.price = paramInt;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("FoodItem{");
    localStringBuffer.append("id=").append(this.id);
    localStringBuffer.append(", name='").append(this.name).append('\'');
    localStringBuffer.append(", price=").append(this.price);
    localStringBuffer.append(", itemCategories='").append(this.itemCategories).append('\'');
    localStringBuffer.append(", merchantId=").append(this.merchantId);
    localStringBuffer.append(", description=").append(this.description).append("'");
    localStringBuffer.append(", imgLocation=").append(this.imgLocation);
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/FoodItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */