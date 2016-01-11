package com.gojek.app.gobusway.model;

public class Schedule
{
  private String arrival;
  private String departure;
  
  public Schedule(String paramString1, String paramString2)
  {
    this.departure = paramString1;
    this.arrival = paramString2;
  }
  
  public String getArrival()
  {
    return this.arrival;
  }
  
  public String getDeparture()
  {
    return this.departure;
  }
  
  public void setArrival(String paramString)
  {
    this.arrival = paramString;
  }
  
  public void setDeparture(String paramString)
  {
    this.departure = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/Schedule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */