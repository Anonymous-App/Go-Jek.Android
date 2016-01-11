package com.gojek.app.model;

public class Route
{
  public double distance;
  public long price;
  public String routePolyline;
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("Route{");
    localStringBuffer.append("distance=").append(this.distance);
    localStringBuffer.append(", price=").append(this.price);
    localStringBuffer.append(", routePolyline='").append(this.routePolyline).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/Route.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */