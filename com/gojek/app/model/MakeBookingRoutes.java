package com.gojek.app.model;

import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import java.util.ArrayList;
import java.util.List;

public class MakeBookingRoutes
{
  public String destinationAddress;
  public String destinationContactName;
  public String destinationContactPhone;
  public String destinationLatLong;
  public String destinationName;
  public String destinationNote;
  public String detailsAddress;
  public String estimatedPrice;
  public String item;
  public int merchantId;
  public String originAddress;
  public String originContactName;
  public String originContactPhone;
  public String originLatLong;
  public String originName;
  public String originNote;
  public List<RouteItem> routeItems = new ArrayList();
  public int serviceType;
  
  public MakeBookingRoutes() {}
  
  public MakeBookingRoutes(int paramInt, Addresses paramAddresses)
  {
    this.serviceType = paramInt;
    this.originNote = paramAddresses.originNote;
    this.destinationNote = paramAddresses.destinationNote;
    this.originName = paramAddresses.originName;
    this.destinationName = paramAddresses.destinationName;
    this.originAddress = paramAddresses.originAddress;
    this.destinationAddress = paramAddresses.destinationAddress;
    this.originContactName = paramAddresses.originContactName;
    this.originContactPhone = paramAddresses.originContactPhone;
    this.destinationContactName = paramAddresses.destinationContactName;
    this.destinationContactPhone = paramAddresses.destinationContactPhone;
    this.originLatLong = paramAddresses.latLongOrigin;
    this.destinationLatLong = paramAddresses.latLongDestination;
    this.item = paramAddresses.item;
    this.routeItems = paramAddresses.routeItems;
    this.estimatedPrice = String.valueOf(paramAddresses.estimatedPrice);
    this.merchantId = paramAddresses.merchant.id;
    this.detailsAddress = paramAddresses.detailAddress;
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
  
  public String getDestinationLatLong()
  {
    return this.destinationLatLong;
  }
  
  public String getDestinationName()
  {
    return this.destinationName;
  }
  
  public String getDestinationNote()
  {
    return this.destinationNote;
  }
  
  public String getDetailsAddress()
  {
    return this.detailsAddress;
  }
  
  public String getEstimatedPrice()
  {
    return this.estimatedPrice;
  }
  
  public String getItem()
  {
    return this.item;
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
  
  public String getOriginLatLong()
  {
    return this.originLatLong;
  }
  
  public String getOriginName()
  {
    return this.originName;
  }
  
  public String getOriginNote()
  {
    return this.originNote;
  }
  
  public List<RouteItem> getRouteItems()
  {
    return this.routeItems;
  }
  
  public int getServiceType()
  {
    return this.serviceType;
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
  
  public void setDestinationLatLong(String paramString)
  {
    this.destinationLatLong = paramString;
  }
  
  public void setDestinationName(String paramString)
  {
    this.destinationName = paramString;
  }
  
  public void setDestinationNote(String paramString)
  {
    this.destinationNote = paramString;
  }
  
  public void setDetailsAddress(String paramString)
  {
    this.detailsAddress = paramString;
  }
  
  public void setEstimatedPrice(String paramString)
  {
    this.estimatedPrice = paramString;
  }
  
  public void setItem(String paramString)
  {
    this.item = paramString;
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
  
  public void setOriginLatLong(String paramString)
  {
    this.originLatLong = paramString;
  }
  
  public void setOriginName(String paramString)
  {
    this.originName = paramString;
  }
  
  public void setOriginNote(String paramString)
  {
    this.originNote = paramString;
  }
  
  public void setRouteItems(List<RouteItem> paramList)
  {
    this.routeItems = paramList;
  }
  
  public void setServiceType(int paramInt)
  {
    this.serviceType = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/MakeBookingRoutes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */