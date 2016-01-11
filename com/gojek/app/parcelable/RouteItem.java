package com.gojek.app.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RouteItem
  implements Parcelable
{
  public static final Parcelable.Creator<RouteItem> CREATOR = new RouteItem.1();
  public int id;
  public String imageUrl;
  public int itemId;
  public String itemName;
  public String notes;
  public int price;
  public int quantity;
  public String routeId;
  
  public RouteItem() {}
  
  protected RouteItem(Parcel paramParcel)
  {
    this.quantity = paramParcel.readInt();
    this.notes = paramParcel.readString();
    this.routeId = paramParcel.readString();
    this.itemName = paramParcel.readString();
    this.imageUrl = paramParcel.readString();
    this.itemId = paramParcel.readInt();
    this.id = paramParcel.readInt();
    this.price = paramParcel.readInt();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getImageUrl()
  {
    return this.imageUrl;
  }
  
  public int getItemId()
  {
    return this.itemId;
  }
  
  public String getItemName()
  {
    return this.itemName;
  }
  
  public String getNotes()
  {
    return this.notes;
  }
  
  public int getPrice()
  {
    return this.price;
  }
  
  public int getQuantity()
  {
    return this.quantity;
  }
  
  public String getRouteId()
  {
    return this.routeId;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setImageUrl(String paramString)
  {
    this.imageUrl = paramString;
  }
  
  public void setItemId(int paramInt)
  {
    this.itemId = paramInt;
  }
  
  public void setItemName(String paramString)
  {
    this.itemName = paramString;
  }
  
  public void setNotes(String paramString)
  {
    this.notes = paramString;
  }
  
  public void setPrice(int paramInt)
  {
    this.price = paramInt;
  }
  
  public void setQuantity(int paramInt)
  {
    this.quantity = paramInt;
  }
  
  public void setRouteId(String paramString)
  {
    this.routeId = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("RouteItem{");
    localStringBuffer.append("id=").append(this.id);
    localStringBuffer.append(", itemId=").append(this.itemId);
    localStringBuffer.append(", itemName='").append(this.itemName).append('\'');
    localStringBuffer.append(", imageUrl='").append(this.imageUrl).append('\'');
    localStringBuffer.append(", notes='").append(this.notes).append('\'');
    localStringBuffer.append(", price=").append(this.price);
    localStringBuffer.append(", quantity=").append(this.quantity);
    localStringBuffer.append(", routeId='").append(this.routeId).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.quantity);
    paramParcel.writeString(this.notes);
    paramParcel.writeString(this.routeId);
    paramParcel.writeString(this.itemName);
    paramParcel.writeString(this.imageUrl);
    paramParcel.writeInt(this.itemId);
    paramParcel.writeInt(this.id);
    paramParcel.writeInt(this.price);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/parcelable/RouteItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */