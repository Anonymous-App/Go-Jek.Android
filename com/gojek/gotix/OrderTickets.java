package com.gojek.gotix;

public class OrderTickets
{
  private Long id;
  private String name;
  private Integer quantity;
  private long schedule_id;
  private Integer ticket_id;
  
  public OrderTickets() {}
  
  public OrderTickets(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public OrderTickets(Long paramLong, Integer paramInteger1, String paramString, Integer paramInteger2, long paramLong1)
  {
    this.id = paramLong;
    this.ticket_id = paramInteger1;
    this.name = paramString;
    this.quantity = paramInteger2;
    this.schedule_id = paramLong1;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Integer getQuantity()
  {
    return this.quantity;
  }
  
  public long getSchedule_id()
  {
    return this.schedule_id;
  }
  
  public Integer getTicket_id()
  {
    return this.ticket_id;
  }
  
  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setQuantity(Integer paramInteger)
  {
    this.quantity = paramInteger;
  }
  
  public void setSchedule_id(long paramLong)
  {
    this.schedule_id = paramLong;
  }
  
  public void setTicket_id(Integer paramInteger)
  {
    this.ticket_id = paramInteger;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/OrderTickets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */