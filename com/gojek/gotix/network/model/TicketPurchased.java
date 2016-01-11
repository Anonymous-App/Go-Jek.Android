package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TicketPurchased
{
  private Attendee attendee;
  @SerializedName("event_id")
  private int eventId;
  private List<TicketItem> tickets;
  @SerializedName("user_id")
  private int userId;
  
  public TicketPurchased(int paramInt1, int paramInt2, List<TicketItem> paramList, Attendee paramAttendee)
  {
    this.eventId = paramInt1;
    this.userId = paramInt2;
    this.tickets = paramList;
    this.attendee = paramAttendee;
  }
  
  public Attendee getAttendee()
  {
    return this.attendee;
  }
  
  public int getEventId()
  {
    return this.eventId;
  }
  
  public List<TicketItem> getTickets()
  {
    return this.tickets;
  }
  
  public int getUserId()
  {
    return this.userId;
  }
  
  public void setAttendee(Attendee paramAttendee)
  {
    this.attendee = paramAttendee;
  }
  
  public void setEventId(int paramInt)
  {
    this.eventId = paramInt;
  }
  
  public void setTickets(List<TicketItem> paramList)
  {
    this.tickets = paramList;
  }
  
  public void setUserId(int paramInt)
  {
    this.userId = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/TicketPurchased.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */