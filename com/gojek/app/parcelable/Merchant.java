package com.gojek.app.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;

public class Merchant
  implements Parcelable
{
  public static final Parcelable.Creator<Merchant> CREATOR = new Merchant.1();
  public String address;
  public String categories;
  public String closeTime;
  public String detailAddress;
  public double distance;
  public int id;
  public String imgLocation;
  public String latLong;
  public String name;
  public String openTime;
  public String phone;
  public int poisId;
  
  public Merchant() {}
  
  protected Merchant(Parcel paramParcel)
  {
    this.id = paramParcel.readInt();
    this.name = paramParcel.readString();
    this.openTime = paramParcel.readString();
    this.closeTime = paramParcel.readString();
    this.poisId = paramParcel.readInt();
    this.categories = paramParcel.readString();
    this.imgLocation = paramParcel.readString();
    this.address = paramParcel.readString();
    this.latLong = paramParcel.readString();
    this.phone = paramParcel.readString();
    this.distance = paramParcel.readDouble();
    this.detailAddress = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public String getCategories()
  {
    return this.categories;
  }
  
  public String getCloseTime()
  {
    return this.closeTime;
  }
  
  public String getDetailAddress()
  {
    return this.detailAddress;
  }
  
  public double getDistance()
  {
    return this.distance;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getImgLocation()
  {
    return this.imgLocation;
  }
  
  public LatLng getLatLng()
  {
    String[] arrayOfString = this.latLong.split(",");
    return new LatLng(Double.parseDouble(arrayOfString[0]), Double.parseDouble(arrayOfString[1]));
  }
  
  public String getLatLong()
  {
    return this.latLong;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getOpenTime()
  {
    return this.openTime;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public int getPoisId()
  {
    return this.poisId;
  }
  
  public void setAddress(String paramString)
  {
    this.address = paramString;
  }
  
  public void setCategories(String paramString)
  {
    this.categories = paramString;
  }
  
  public void setCloseTime(String paramString)
  {
    this.closeTime = paramString;
  }
  
  public void setDetailAddress(String paramString)
  {
    this.detailAddress = paramString;
  }
  
  public void setDistance(double paramDouble)
  {
    this.distance = paramDouble;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setImgLocation(String paramString)
  {
    this.imgLocation = paramString;
  }
  
  public void setLatLong(String paramString)
  {
    this.latLong = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setOpenTime(String paramString)
  {
    this.openTime = paramString;
  }
  
  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }
  
  public void setPoisId(int paramInt)
  {
    this.poisId = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.id);
    paramParcel.writeString(this.name);
    paramParcel.writeString(this.openTime);
    paramParcel.writeString(this.closeTime);
    paramParcel.writeInt(this.poisId);
    paramParcel.writeString(this.categories);
    paramParcel.writeString(this.imgLocation);
    paramParcel.writeString(this.address);
    paramParcel.writeString(this.latLong);
    paramParcel.writeString(this.phone);
    paramParcel.writeDouble(this.distance);
    paramParcel.writeString(this.detailAddress);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/parcelable/Merchant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */