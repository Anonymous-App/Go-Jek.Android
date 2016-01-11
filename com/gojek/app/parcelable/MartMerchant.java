package com.gojek.app.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.gojek.app.model.Martlocation;

public class MartMerchant
  implements Parcelable
{
  public static final Parcelable.Creator<MartMerchant> CREATOR = new MartMerchant.1();
  public String address;
  public boolean ageRestriction;
  public String categories;
  public String closeTime;
  public String description;
  public String detailAddress;
  public double distance;
  public String googlePlaceId;
  public int id;
  public boolean isOpen;
  public boolean isSelected;
  public String latlong;
  public Martlocation location;
  public String martCategory;
  public String martCategoryCode;
  public int martId;
  public int martMerchantId;
  public String martMerchantName;
  public String martName;
  public String phone;
  public int poiType;
  public String poiZone;
  public String serviceArea;
  public int status;
  public int version;
  
  public MartMerchant() {}
  
  protected MartMerchant(Parcel paramParcel)
  {
    this.poiZone = paramParcel.readString();
    this.martMerchantName = paramParcel.readString();
    this.martCategoryCode = paramParcel.readString();
    this.googlePlaceId = paramParcel.readString();
    this.id = paramParcel.readInt();
    this.closeTime = paramParcel.readString();
    this.version = paramParcel.readInt();
    this.latlong = paramParcel.readString();
    this.status = paramParcel.readInt();
    this.description = paramParcel.readString();
    if (paramParcel.readByte() != 0)
    {
      bool1 = true;
      this.isOpen = bool1;
      this.phone = paramParcel.readString();
      this.martName = paramParcel.readString();
      this.address = paramParcel.readString();
      this.detailAddress = paramParcel.readString();
      this.martId = paramParcel.readInt();
      this.categories = paramParcel.readString();
      this.serviceArea = paramParcel.readString();
      this.martCategory = paramParcel.readString();
      this.poiType = paramParcel.readInt();
      this.martMerchantId = paramParcel.readInt();
      if (paramParcel.readByte() == 0) {
        break label222;
      }
      bool1 = true;
      label189:
      this.ageRestriction = bool1;
      if (paramParcel.readByte() == 0) {
        break label227;
      }
    }
    label222:
    label227:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.isSelected = bool1;
      this.distance = paramParcel.readDouble();
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label189;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getLatLong()
  {
    this.latlong = ("" + this.location.lat + "," + this.location.lon);
    return this.latlong;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeString(this.poiZone);
    paramParcel.writeString(this.martMerchantName);
    paramParcel.writeString(this.martCategoryCode);
    paramParcel.writeString(this.googlePlaceId);
    paramParcel.writeInt(this.id);
    paramParcel.writeString(this.closeTime);
    paramParcel.writeInt(this.version);
    paramParcel.writeString(this.latlong);
    paramParcel.writeInt(this.status);
    paramParcel.writeString(this.description);
    if (this.isOpen)
    {
      paramInt = 1;
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeString(this.phone);
      paramParcel.writeString(this.martName);
      paramParcel.writeString(this.address);
      paramParcel.writeString(this.detailAddress);
      paramParcel.writeInt(this.martId);
      paramParcel.writeString(this.categories);
      paramParcel.writeString(this.serviceArea);
      paramParcel.writeString(this.martCategory);
      paramParcel.writeInt(this.poiType);
      paramParcel.writeInt(this.martMerchantId);
      if (!this.ageRestriction) {
        break label221;
      }
      paramInt = 1;
      label186:
      paramParcel.writeByte((byte)paramInt);
      if (!this.isSelected) {
        break label226;
      }
    }
    label221:
    label226:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeDouble(this.distance);
      return;
      paramInt = 0;
      break;
      paramInt = 0;
      break label186;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/parcelable/MartMerchant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */