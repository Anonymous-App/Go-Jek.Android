package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class SendInvoiceRequestBody
{
  private String email;
  @SerializedName("zone_id")
  private String zoneId;
  
  public SendInvoiceRequestBody(String paramString1, String paramString2)
  {
    this.email = paramString1;
    this.zoneId = paramString2;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getZoneId()
  {
    return this.zoneId;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setZoneId(String paramString)
  {
    this.zoneId = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/SendInvoiceRequestBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */