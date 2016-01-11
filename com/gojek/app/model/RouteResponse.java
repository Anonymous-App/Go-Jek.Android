package com.gojek.app.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RouteResponse
{
  public double discount;
  public boolean isFree;
  public List<Route> routes;
  @SerializedName("totalShoppingFee")
  public long shoppingFee;
  public long subtotal;
  public double totalDistance;
  public long totalPrice;
  
  public double getDiscount()
  {
    return this.discount;
  }
  
  public List<Route> getRoutes()
  {
    return this.routes;
  }
  
  public long getShoppingFee()
  {
    return this.shoppingFee;
  }
  
  public long getSubtotal()
  {
    return this.subtotal;
  }
  
  public double getTotalDistance()
  {
    return this.totalDistance;
  }
  
  public long getTotalPrice()
  {
    return this.totalPrice;
  }
  
  public boolean isFree()
  {
    return this.isFree;
  }
  
  public void setDiscount(double paramDouble)
  {
    this.discount = paramDouble;
  }
  
  public void setFree(boolean paramBoolean)
  {
    this.isFree = paramBoolean;
  }
  
  public void setRoutes(List<Route> paramList)
  {
    this.routes = paramList;
  }
  
  public void setShoppingFee(long paramLong)
  {
    this.shoppingFee = paramLong;
  }
  
  public void setSubtotal(long paramLong)
  {
    this.subtotal = paramLong;
  }
  
  public void setTotalDistance(double paramDouble)
  {
    this.totalDistance = paramDouble;
  }
  
  public void setTotalPrice(long paramLong)
  {
    this.totalPrice = paramLong;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("RouteResponse{");
    localStringBuffer.append("routes=").append(this.routes);
    localStringBuffer.append(", totalDistance=").append(this.totalDistance);
    localStringBuffer.append(", subtotal=").append(this.subtotal);
    localStringBuffer.append(", shoppingFee=").append(this.shoppingFee);
    localStringBuffer.append(", discount=").append(this.discount);
    localStringBuffer.append(", totalPrice=").append(this.totalPrice);
    localStringBuffer.append(", isFree=").append(this.isFree);
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/RouteResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */