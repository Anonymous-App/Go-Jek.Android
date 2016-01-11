package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class BookingItem
{
  @SerializedName("air_conditioner_support")
  boolean airConditionerSupport;
  String description;
  @SerializedName("fragile_item_support")
  boolean fragileSupport;
  
  public BookingItem() {}
  
  public BookingItem(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.description = paramString;
    this.fragileSupport = paramBoolean1;
    this.airConditionerSupport = paramBoolean2;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public boolean isAirConditionerSupport()
  {
    return this.airConditionerSupport;
  }
  
  public boolean isFragileSupport()
  {
    return this.fragileSupport;
  }
  
  public void setAirConditionerSupport(boolean paramBoolean)
  {
    this.airConditionerSupport = paramBoolean;
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setFragileSupport(boolean paramBoolean)
  {
    this.fragileSupport = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/BookingItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */