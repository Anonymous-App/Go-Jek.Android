package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class OrderResponse
{
  @SerializedName("order_id")
  int orderId;
  
  public int getOrderId()
  {
    return this.orderId;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/OrderResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */