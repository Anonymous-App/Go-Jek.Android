package com.gojek.gotix.network.model;

public class Attendee
{
  private String email;
  private String firstname;
  private String lastname;
  private String telephone;
  
  public Attendee(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.firstname = paramString1;
    this.lastname = paramString2;
    this.email = paramString3;
    this.telephone = paramString4;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getFirstname()
  {
    return this.firstname;
  }
  
  public String getLastname()
  {
    return this.lastname;
  }
  
  public String getTelephone()
  {
    return this.telephone;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setFirstname(String paramString)
  {
    this.firstname = paramString;
  }
  
  public void setLastname(String paramString)
  {
    this.lastname = paramString;
  }
  
  public void setTelephone(String paramString)
  {
    this.telephone = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/Attendee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */