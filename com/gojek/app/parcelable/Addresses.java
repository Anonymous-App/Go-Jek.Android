package com.gojek.app.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class Addresses
  implements Parcelable
{
  public static final Parcelable.Creator<Addresses> CREATOR = new Addresses.1();
  public int bookingId;
  public String destinationAddress;
  public String destinationContactName;
  public String destinationContactPhone;
  public String destinationName;
  public String destinationNote;
  public int destinationPoiId;
  public String detailAddress;
  public double distance;
  public long estimatedPrice;
  public long fare;
  public int foodCostTotal;
  public int foodQuantityTotal;
  public int id;
  public String item;
  public String latLongDestination;
  public String latLongOrigin;
  public MartMerchant martMerchant = new MartMerchant();
  public int martMerchantId;
  public Merchant merchant = new Merchant();
  public int merchantId;
  public String originAddress;
  public String originContactName;
  public String originContactPhone;
  public String originName;
  public String originNote;
  public int originPoiId;
  public String receivedBy;
  public List<RouteItem> routeItems = new ArrayList();
  public String routePolyline;
  public int serviceType;
  public long shoppingAddPrice;
  public int statusRoute;
  public long totalPrice;
  
  public Addresses() {}
  
  protected Addresses(Parcel paramParcel)
  {
    this.id = paramParcel.readInt();
    this.originPoiId = paramParcel.readInt();
    this.destinationPoiId = paramParcel.readInt();
    this.originNote = paramParcel.readString();
    this.destinationNote = paramParcel.readString();
    this.serviceType = paramParcel.readInt();
    this.statusRoute = paramParcel.readInt();
    this.distance = paramParcel.readDouble();
    this.fare = paramParcel.readLong();
    this.shoppingAddPrice = paramParcel.readLong();
    this.destinationContactName = paramParcel.readString();
    this.destinationContactPhone = paramParcel.readString();
    this.receivedBy = paramParcel.readString();
    this.originName = paramParcel.readString();
    this.originAddress = paramParcel.readString();
    this.latLongOrigin = paramParcel.readString();
    this.destinationName = paramParcel.readString();
    this.destinationAddress = paramParcel.readString();
    this.latLongDestination = paramParcel.readString();
    this.originContactName = paramParcel.readString();
    this.originContactPhone = paramParcel.readString();
    this.item = paramParcel.readString();
    this.estimatedPrice = paramParcel.readLong();
    this.totalPrice = paramParcel.readLong();
    if (paramParcel.readByte() == 1)
    {
      this.routeItems = new ArrayList();
      paramParcel.readList(this.routeItems, RouteItem.class.getClassLoader());
    }
    for (;;)
    {
      this.routePolyline = paramParcel.readString();
      this.foodQuantityTotal = paramParcel.readInt();
      this.foodCostTotal = paramParcel.readInt();
      this.merchant = ((Merchant)paramParcel.readValue(Merchant.class.getClassLoader()));
      this.merchantId = paramParcel.readInt();
      this.martMerchant = ((MartMerchant)paramParcel.readValue(MartMerchant.class.getClassLoader()));
      this.martMerchantId = paramParcel.readInt();
      this.detailAddress = paramParcel.readString();
      return;
      this.routeItems = null;
    }
  }
  
  public Addresses(String paramString)
  {
    this.destinationName = paramString;
  }
  
  public static Parcelable.Creator<Addresses> getCREATOR()
  {
    return CREATOR;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getBookingId()
  {
    return this.bookingId;
  }
  
  public String getDestinationAddress()
  {
    return this.destinationAddress;
  }
  
  public String getDestinationContactName()
  {
    return this.destinationContactName;
  }
  
  public String getDestinationContactPhone()
  {
    return this.destinationContactPhone;
  }
  
  public String getDestinationName()
  {
    return this.destinationName;
  }
  
  public String getDestinationNote()
  {
    return this.destinationNote;
  }
  
  public int getDestinationPoiId()
  {
    return this.destinationPoiId;
  }
  
  public String getDetailAddress()
  {
    return this.detailAddress;
  }
  
  public double getDistance()
  {
    return this.distance;
  }
  
  public long getEstimatedPrice()
  {
    return this.estimatedPrice;
  }
  
  public long getFare()
  {
    return this.fare;
  }
  
  public int getFoodCostTotal()
  {
    return this.foodCostTotal;
  }
  
  public int getFoodQuantityTotal()
  {
    return this.foodQuantityTotal;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getItem()
  {
    return this.item;
  }
  
  public String getLatLongDestination()
  {
    return this.latLongDestination;
  }
  
  public String getLatLongOrigin()
  {
    return this.latLongOrigin;
  }
  
  public MartMerchant getMartMerchant()
  {
    return this.martMerchant;
  }
  
  public int getMartMerchantId()
  {
    return this.martMerchantId;
  }
  
  public Merchant getMerchant()
  {
    return this.merchant;
  }
  
  public int getMerchantId()
  {
    return this.merchantId;
  }
  
  public String getOriginAddress()
  {
    return this.originAddress;
  }
  
  public String getOriginContactName()
  {
    return this.originContactName;
  }
  
  public String getOriginContactPhone()
  {
    return this.originContactPhone;
  }
  
  public String getOriginName()
  {
    return this.originName;
  }
  
  public String getOriginNote()
  {
    return this.originNote;
  }
  
  public int getOriginPoiId()
  {
    return this.originPoiId;
  }
  
  public String getReceivedBy()
  {
    return this.receivedBy;
  }
  
  public List<RouteItem> getRouteItems()
  {
    return this.routeItems;
  }
  
  public String getRoutePolyline()
  {
    return this.routePolyline;
  }
  
  public int getServiceType()
  {
    return this.serviceType;
  }
  
  public long getShoppingAddPrice()
  {
    return this.shoppingAddPrice;
  }
  
  public int getStatusRoute()
  {
    return this.statusRoute;
  }
  
  public long getTotalPrice()
  {
    return this.totalPrice;
  }
  
  public void setBookingId(int paramInt)
  {
    this.bookingId = paramInt;
  }
  
  public void setDestinationAddress(String paramString)
  {
    this.destinationAddress = paramString;
  }
  
  public void setDestinationContactName(String paramString)
  {
    this.destinationContactName = paramString;
  }
  
  public void setDestinationContactPhone(String paramString)
  {
    this.destinationContactPhone = paramString;
  }
  
  public void setDestinationName(String paramString)
  {
    this.destinationName = paramString;
  }
  
  public void setDestinationNote(String paramString)
  {
    this.destinationNote = paramString;
  }
  
  public void setDestinationPoiId(int paramInt)
  {
    this.destinationPoiId = paramInt;
  }
  
  public void setDetailAddress(String paramString)
  {
    this.detailAddress = paramString;
  }
  
  public void setDistance(double paramDouble)
  {
    this.distance = paramDouble;
  }
  
  public void setEstimatedPrice(long paramLong)
  {
    this.estimatedPrice = paramLong;
  }
  
  public void setFare(long paramLong)
  {
    this.fare = paramLong;
  }
  
  public void setFoodCostTotal(int paramInt)
  {
    this.foodCostTotal = paramInt;
  }
  
  public void setFoodQuantityTotal(int paramInt)
  {
    this.foodQuantityTotal = paramInt;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setItem(String paramString)
  {
    this.item = paramString;
  }
  
  public void setLatLongDestination(String paramString)
  {
    this.latLongDestination = paramString;
  }
  
  public void setLatLongOrigin(String paramString)
  {
    this.latLongOrigin = paramString;
  }
  
  public void setMartMerchant(MartMerchant paramMartMerchant)
  {
    this.martMerchant = paramMartMerchant;
  }
  
  public void setMartMerchantId(int paramInt)
  {
    this.martMerchantId = paramInt;
  }
  
  public void setMerchant(Merchant paramMerchant)
  {
    this.merchant = paramMerchant;
  }
  
  public void setMerchantId(int paramInt)
  {
    this.merchantId = paramInt;
  }
  
  public void setOriginAddress(String paramString)
  {
    this.originAddress = paramString;
  }
  
  public void setOriginContactName(String paramString)
  {
    this.originContactName = paramString;
  }
  
  public void setOriginContactPhone(String paramString)
  {
    this.originContactPhone = paramString;
  }
  
  public void setOriginName(String paramString)
  {
    this.originName = paramString;
  }
  
  public void setOriginNote(String paramString)
  {
    this.originNote = paramString;
  }
  
  public void setOriginPoiId(int paramInt)
  {
    this.originPoiId = paramInt;
  }
  
  public void setReceivedBy(String paramString)
  {
    this.receivedBy = paramString;
  }
  
  public void setRouteItems(List<RouteItem> paramList)
  {
    this.routeItems = paramList;
  }
  
  public void setRoutePolyline(String paramString)
  {
    this.routePolyline = paramString;
  }
  
  public void setServiceType(int paramInt)
  {
    this.serviceType = paramInt;
  }
  
  public void setShoppingAddPrice(long paramLong)
  {
    this.shoppingAddPrice = paramLong;
  }
  
  public void setStatusRoute(int paramInt)
  {
    this.statusRoute = paramInt;
  }
  
  public void setTotalPrice(long paramLong)
  {
    this.totalPrice = paramLong;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.id);
    paramParcel.writeInt(this.originPoiId);
    paramParcel.writeInt(this.destinationPoiId);
    paramParcel.writeString(this.originNote);
    paramParcel.writeString(this.destinationNote);
    paramParcel.writeInt(this.serviceType);
    paramParcel.writeInt(this.statusRoute);
    paramParcel.writeDouble(this.distance);
    paramParcel.writeLong(this.fare);
    paramParcel.writeLong(this.shoppingAddPrice);
    paramParcel.writeString(this.destinationContactName);
    paramParcel.writeString(this.destinationContactPhone);
    paramParcel.writeString(this.receivedBy);
    paramParcel.writeString(this.originName);
    paramParcel.writeString(this.originAddress);
    paramParcel.writeString(this.latLongOrigin);
    paramParcel.writeString(this.destinationName);
    paramParcel.writeString(this.destinationAddress);
    paramParcel.writeString(this.latLongDestination);
    paramParcel.writeString(this.originContactName);
    paramParcel.writeString(this.originContactPhone);
    paramParcel.writeString(this.item);
    paramParcel.writeLong(this.estimatedPrice);
    paramParcel.writeLong(this.totalPrice);
    if (this.routeItems == null) {
      paramParcel.writeByte((byte)0);
    }
    for (;;)
    {
      paramParcel.writeString(this.routePolyline);
      paramParcel.writeInt(this.foodQuantityTotal);
      paramParcel.writeInt(this.foodCostTotal);
      paramParcel.writeValue(this.merchant);
      paramParcel.writeInt(this.merchantId);
      paramParcel.writeValue(this.martMerchant);
      paramParcel.writeInt(this.martMerchantId);
      paramParcel.writeString(this.detailAddress);
      return;
      paramParcel.writeByte((byte)1);
      paramParcel.writeList(this.routeItems);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/parcelable/Addresses.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */