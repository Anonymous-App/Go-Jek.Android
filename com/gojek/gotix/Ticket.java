package com.gojek.gotix;

public class Ticket
{
  private String description;
  private Long id;
  private String name;
  private Long price;
  private long schedule_id;
  private String seatplan;
  private Integer stock;
  private Integer ticket_id;
  private String type;
  
  public Ticket() {}
  
  public Ticket(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public Ticket(Long paramLong1, Integer paramInteger1, String paramString1, String paramString2, String paramString3, String paramString4, Long paramLong2, Integer paramInteger2, long paramLong)
  {
    this.id = paramLong1;
    this.ticket_id = paramInteger1;
    this.name = paramString1;
    this.description = paramString2;
    this.seatplan = paramString3;
    this.type = paramString4;
    this.price = paramLong2;
    this.stock = paramInteger2;
    this.schedule_id = paramLong;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Long getPrice()
  {
    return this.price;
  }
  
  public long getSchedule_id()
  {
    return this.schedule_id;
  }
  
  public String getSeatplan()
  {
    return this.seatplan;
  }
  
  public Integer getStock()
  {
    return this.stock;
  }
  
  public Integer getTicket_id()
  {
    return this.ticket_id;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPrice(Long paramLong)
  {
    this.price = paramLong;
  }
  
  public void setSchedule_id(long paramLong)
  {
    this.schedule_id = paramLong;
  }
  
  public void setSeatplan(String paramString)
  {
    this.seatplan = paramString;
  }
  
  public void setStock(Integer paramInteger)
  {
    this.stock = paramInteger;
  }
  
  public void setTicket_id(Integer paramInteger)
  {
    this.ticket_id = paramInteger;
  }
  
  public void setType(String paramString)
  {
    this.type = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/Ticket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */