package com.gojek.app.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class recommendedDishes
  implements Parcelable
{
  public static final Parcelable.Creator<recommendedDishes> CREATOR = new recommendedDishes.1();
  public Double distance;
  public int id;
  public String imgLocation;
  public String itemCategories;
  public String markupPrice;
  public int merchantId;
  public String merchantName;
  public String name;
  public Double price;
  public Double stockQty;
  public int total;
  
  public recommendedDishes() {}
  
  protected recommendedDishes(Parcel paramParcel)
  {
    this.id = paramParcel.readInt();
    this.name = paramParcel.readString();
    this.price = Double.valueOf(paramParcel.readDouble());
    this.itemCategories = paramParcel.readString();
    this.merchantId = paramParcel.readInt();
    this.merchantName = paramParcel.readString();
    this.markupPrice = paramParcel.readString();
    this.total = paramParcel.readInt();
    this.stockQty = Double.valueOf(paramParcel.readDouble());
    this.imgLocation = paramParcel.readString();
    this.distance = Double.valueOf(paramParcel.readDouble());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Double getDistance()
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
  
  public String getItemCategories()
  {
    return this.itemCategories;
  }
  
  public String getMarkupPrice()
  {
    return this.markupPrice;
  }
  
  public int getMerchantId()
  {
    return this.merchantId;
  }
  
  public String getMerchantName()
  {
    return this.merchantName;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public double getPrice()
  {
    return this.price.doubleValue();
  }
  
  public Double getStockQty()
  {
    return this.stockQty;
  }
  
  public int getTotal()
  {
    return this.total;
  }
  
  public void setDistance(Double paramDouble)
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
  
  public void setItemCategories(String paramString)
  {
    this.itemCategories = paramString;
  }
  
  public void setMarkupPrice(String paramString)
  {
    this.markupPrice = paramString;
  }
  
  public void setMerchantId(int paramInt)
  {
    this.merchantId = paramInt;
  }
  
  public void setMerchantName(String paramString)
  {
    this.merchantName = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPrice(double paramDouble)
  {
    this.price = Double.valueOf(paramDouble);
  }
  
  public void setStockQty(Double paramDouble)
  {
    this.stockQty = paramDouble;
  }
  
  public void setTotal(int paramInt)
  {
    this.total = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.id);
    paramParcel.writeString(this.name);
    paramParcel.writeDouble(this.price.doubleValue());
    paramParcel.writeString(this.itemCategories);
    paramParcel.writeInt(this.merchantId);
    paramParcel.writeString(this.merchantName);
    paramParcel.writeString(this.markupPrice);
    paramParcel.writeInt(this.total);
    paramParcel.writeDouble(this.stockQty.doubleValue());
    paramParcel.writeString(this.imgLocation);
    paramParcel.writeDouble(this.distance.doubleValue());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/parcelable/recommendedDishes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */