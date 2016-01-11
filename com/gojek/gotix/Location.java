package com.gojek.gotix;

public class Location
{
  private String address;
  private String district;
  private long event_id;
  private Long id;
  private Double latitude;
  private Integer location_id;
  private Double longitude;
  private String name;
  private long order_id;
  private String province;
  private String region;
  private String venue_name;
  
  public Location() {}
  
  public Location(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public Location(Long paramLong, Integer paramInteger, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Double paramDouble1, Double paramDouble2, long paramLong1, long paramLong2)
  {
    this.id = paramLong;
    this.location_id = paramInteger;
    this.name = paramString1;
    this.venue_name = paramString2;
    this.address = paramString3;
    this.district = paramString4;
    this.region = paramString5;
    this.province = paramString6;
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
    this.event_id = paramLong1;
    this.order_id = paramLong2;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public String getDistrict()
  {
    return this.district;
  }
  
  public long getEvent_id()
  {
    return this.event_id;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public Double getLatitude()
  {
    return this.latitude;
  }
  
  public Integer getLocation_id()
  {
    return this.location_id;
  }
  
  public Double getLongitude()
  {
    return this.longitude;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public long getOrder_id()
  {
    return this.order_id;
  }
  
  public String getProvince()
  {
    return this.province;
  }
  
  public String getRegion()
  {
    return this.region;
  }
  
  public String getVenue_name()
  {
    return this.venue_name;
  }
  
  public void setAddress(String paramString)
  {
    this.address = paramString;
  }
  
  public void setDistrict(String paramString)
  {
    this.district = paramString;
  }
  
  public void setEvent_id(long paramLong)
  {
    this.event_id = paramLong;
  }
  
  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public void setLatitude(Double paramDouble)
  {
    this.latitude = paramDouble;
  }
  
  public void setLocation_id(Integer paramInteger)
  {
    this.location_id = paramInteger;
  }
  
  public void setLongitude(Double paramDouble)
  {
    this.longitude = paramDouble;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setOrder_id(long paramLong)
  {
    this.order_id = paramLong;
  }
  
  public void setProvince(String paramString)
  {
    this.province = paramString;
  }
  
  public void setRegion(String paramString)
  {
    this.region = paramString;
  }
  
  public void setVenue_name(String paramString)
  {
    this.venue_name = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/Location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */