package com.gojek.app.model;

import java.util.List;

public class CompanyProductCategory
{
  public String code;
  public int id;
  public String imgLocation;
  public List<MartItem> martItems;
  public String name;
  public List<SubCategory> subCategory;
  
  public String getCode()
  {
    return this.code;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getImgLocation()
  {
    return this.imgLocation;
  }
  
  public List<MartItem> getMartItems()
  {
    return this.martItems;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public List<SubCategory> getSubCategory()
  {
    return this.subCategory;
  }
  
  public void setCode(String paramString)
  {
    this.code = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setImgLocation(String paramString)
  {
    this.imgLocation = paramString;
  }
  
  public void setMartItems(List<MartItem> paramList)
  {
    this.martItems = paramList;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setSubCategory(List<SubCategory> paramList)
  {
    this.subCategory = paramList;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("CompanyProductCategory{");
    localStringBuffer.append("id=").append(this.id);
    localStringBuffer.append(", name='").append(this.name).append('\'');
    localStringBuffer.append(", code='").append(this.code).append('\'');
    localStringBuffer.append(", imgLocation='").append(this.imgLocation).append('\'');
    localStringBuffer.append(", subCategory=").append(this.subCategory);
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/CompanyProductCategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */