package com.gojek.app.model;

import com.gojek.app.parcelable.Customer;

public class FacebookLoginRespone
{
  private Customer customer;
  private int customerId;
  private int customerSocMedID;
  private String message;
  private String status;
  private int statusCode;
  
  public Customer getCustomer()
  {
    return this.customer;
  }
  
  public int getCustomerId()
  {
    return this.customerId;
  }
  
  public int getCustomerSocMedID()
  {
    return this.customerSocMedID;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public int getStatusCode()
  {
    return this.statusCode;
  }
  
  public void setCustomer(Customer paramCustomer)
  {
    this.customer = paramCustomer;
  }
  
  public void setCustomerId(int paramInt)
  {
    this.customerId = paramInt;
  }
  
  public void setCustomerSocMedID(int paramInt)
  {
    this.customerSocMedID = paramInt;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public void setStatusCode(int paramInt)
  {
    this.statusCode = paramInt;
  }
  
  public String toString()
  {
    return "FacebookLoginRespone [statusCode = " + this.statusCode + ", message = " + this.message + ", customerId = " + this.customerId + ", status = " + this.status + ", customerSocMedID = " + this.customerSocMedID + ", custumer ={" + this.customer + "}]";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/FacebookLoginRespone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */