package com.gojek.gotix.services.model;

public class ProduceOrder
{
  int eventId;
  int orderId;
  int requestCode;
  
  public ProduceOrder(int paramInt1, int paramInt2, int paramInt3)
  {
    this.orderId = paramInt1;
    this.eventId = paramInt2;
    this.requestCode = paramInt3;
  }
  
  public int getEventId()
  {
    return this.eventId;
  }
  
  public int getOrderId()
  {
    return this.orderId;
  }
  
  public int getRequestCode()
  {
    return this.requestCode;
  }
  
  public void setEventId(int paramInt)
  {
    this.eventId = paramInt;
  }
  
  public void setOrderId(int paramInt)
  {
    this.orderId = paramInt;
  }
  
  public void setRequestCode(int paramInt)
  {
    this.requestCode = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/services/model/ProduceOrder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */