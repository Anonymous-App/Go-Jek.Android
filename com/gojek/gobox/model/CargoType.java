package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class CargoType
{
  private long additional_serv;
  private long fare;
  @SerializedName("fragile_item_support")
  private boolean fragileSupport;
  private long height;
  @SerializedName("icon_url")
  private String iconUrl;
  private long id;
  private long length;
  private int maxWeight;
  private String name;
  @SerializedName("price_per_km")
  private long pricePerKm;
  @SerializedName("price_per_shipper")
  private long pricePerShipper;
  @SerializedName("air_conditioner_support")
  private boolean refrigerator;
  private long width;
  
  public CargoType(long paramLong1, String paramString1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, boolean paramBoolean1, boolean paramBoolean2, String paramString2, long paramLong7, long paramLong8)
  {
    this.id = paramLong1;
    this.name = paramString1;
    this.length = paramLong2;
    this.width = paramLong3;
    this.height = paramLong4;
    this.fare = paramLong5;
    this.additional_serv = paramLong6;
    this.fragileSupport = paramBoolean1;
    this.refrigerator = paramBoolean2;
    this.iconUrl = paramString2;
    this.pricePerKm = paramLong7;
    this.pricePerShipper = paramLong8;
  }
  
  public long getAdditional_serv()
  {
    return this.additional_serv;
  }
  
  public long getFare()
  {
    return this.fare;
  }
  
  public long getHeight()
  {
    return this.height;
  }
  
  public long getId()
  {
    return this.id;
  }
  
  public String getImage()
  {
    return this.iconUrl;
  }
  
  public long getLength()
  {
    return this.length;
  }
  
  public int getMaxWeight()
  {
    return this.maxWeight;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public long getPricePerKm()
  {
    return this.pricePerKm;
  }
  
  public long getPricePerShipper()
  {
    return this.pricePerShipper;
  }
  
  public long getWidth()
  {
    return this.width;
  }
  
  public boolean isFragileSupport()
  {
    return this.fragileSupport;
  }
  
  public boolean isRefrigerator()
  {
    return this.refrigerator;
  }
  
  public void setPricePerKm(long paramLong)
  {
    this.pricePerKm = paramLong;
  }
  
  public void setPricePerShipper(long paramLong)
  {
    this.pricePerShipper = paramLong;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/CargoType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */