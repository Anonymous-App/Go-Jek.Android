package com.gojek.app.model;

public class Poi
{
  public String address;
  public String description;
  public String gojekPlaceId;
  public String googlePlaceId;
  public int id;
  public double latitude;
  public double longitude;
  public String name;
  public int status;
  
  public Poi() {}
  
  public Poi(int paramInt, String paramString1, double paramDouble1, double paramDouble2, String paramString2)
  {
    this.id = paramInt;
    this.name = paramString1;
    this.longitude = paramDouble2;
    this.latitude = paramDouble1;
    this.address = paramString2;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getGojekPlaceId()
  {
    return this.gojekPlaceId;
  }
  
  public String getGooglePlaceId()
  {
    return this.googlePlaceId;
  }
  
  public String getID()
  {
    if (((this.googlePlaceId == null) || (this.googlePlaceId.isEmpty())) && ((this.gojekPlaceId == null) || (this.gojekPlaceId.isEmpty()))) {
      return "";
    }
    if ((this.googlePlaceId != null) && (!this.googlePlaceId.isEmpty())) {
      return String.format("?googlePlaceId=%s", new Object[] { this.googlePlaceId });
    }
    return String.format("?gojekPlaceId=%s", new Object[] { this.gojekPlaceId });
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public double getLatitude()
  {
    return this.latitude;
  }
  
  public double getLongitude()
  {
    return this.longitude;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public void setAddress(String paramString)
  {
    this.address = paramString;
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setGojekPlaceId(String paramString)
  {
    this.gojekPlaceId = paramString;
  }
  
  public void setGooglePlaceId(String paramString)
  {
    this.googlePlaceId = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }
  
  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("Poi{");
    localStringBuffer.append("id=").append(this.id);
    localStringBuffer.append(", name='").append(this.name).append('\'');
    localStringBuffer.append(", address='").append(this.address).append('\'');
    localStringBuffer.append(", description='").append(this.description).append('\'');
    localStringBuffer.append(", longitude=").append(this.longitude);
    localStringBuffer.append(", latitude=").append(this.latitude);
    localStringBuffer.append(", status=").append(this.status);
    localStringBuffer.append(", googlePlaceId='").append(this.googlePlaceId).append('\'');
    localStringBuffer.append(", gojekPlaceId='").append(this.gojekPlaceId).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/Poi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */