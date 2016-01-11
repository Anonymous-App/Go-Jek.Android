package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class CallCenter
{
  @SerializedName("call_center_number")
  private String callCenterNumber;
  @SerializedName("call_center_text")
  private String callCenterText;
  
  public CallCenter(String paramString)
  {
    this.callCenterText = paramString;
  }
  
  public String getCallCenterNumber()
  {
    return this.callCenterNumber;
  }
  
  public String getCallCenterText()
  {
    return this.callCenterText;
  }
  
  public void setCallCenterNumber(String paramString)
  {
    this.callCenterNumber = paramString;
  }
  
  public void setCallCenterText(String paramString)
  {
    this.callCenterText = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/CallCenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */