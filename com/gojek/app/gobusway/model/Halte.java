package com.gojek.app.gobusway.model;

import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Halte
{
  @SerializedName("halte_id")
  String id;
  double latitude;
  @SerializedName("link_detail")
  String linkDetail;
  double longitude;
  @SerializedName("halte")
  String name;
  
  public Halte() {}
  
  @ParcelConstructor
  public Halte(String paramString1, String paramString2, double paramDouble1, double paramDouble2, String paramString3)
  {
    this.id = paramString1;
    this.name = paramString2;
    this.longitude = paramDouble2;
    this.latitude = paramDouble1;
    this.linkDetail = paramString3;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public double getLatitude()
  {
    return this.latitude;
  }
  
  public String getLinkDetail()
  {
    return this.linkDetail;
  }
  
  public double getLongitude()
  {
    return this.longitude;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setId(String paramString)
  {
    this.id = paramString;
  }
  
  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }
  
  public void setLinkDetail(String paramString)
  {
    this.linkDetail = paramString;
  }
  
  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/Halte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */