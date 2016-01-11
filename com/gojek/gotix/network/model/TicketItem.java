package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class TicketItem
{
  private int quantity;
  @SerializedName("ticket_id")
  private int ticketId;
  
  public TicketItem(int paramInt1, int paramInt2)
  {
    this.ticketId = paramInt1;
    this.quantity = paramInt2;
  }
  
  public int getQuantity()
  {
    return this.quantity;
  }
  
  public int getTicketId()
  {
    return this.ticketId;
  }
  
  public void setQuantity(int paramInt)
  {
    this.quantity = paramInt;
  }
  
  public void setTicketId(int paramInt)
  {
    this.ticketId = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/TicketItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */