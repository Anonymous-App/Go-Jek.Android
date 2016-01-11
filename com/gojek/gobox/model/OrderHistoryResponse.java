package com.gojek.gobox.model;

public class OrderHistoryResponse
{
  private OrderHistory[] orders;
  
  public OrderHistoryResponse(OrderHistory[] paramArrayOfOrderHistory)
  {
    this.orders = paramArrayOfOrderHistory;
  }
  
  public OrderHistory[] getOrders()
  {
    return this.orders;
  }
  
  public void setOrders(OrderHistory[] paramArrayOfOrderHistory)
  {
    this.orders = paramArrayOfOrderHistory;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/OrderHistoryResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */