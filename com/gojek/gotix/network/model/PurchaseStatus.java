package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class PurchaseStatus
{
  @SerializedName("order_id")
  private int orderId;
  @SerializedName("purchase_status")
  private int purchaseStatus;
  @SerializedName("share_message")
  private String shareMessage;
  
  public int getOrderId()
  {
    return this.orderId;
  }
  
  public int getPurchaseStatus()
  {
    return this.purchaseStatus;
  }
  
  public String getShareMessage()
  {
    return this.shareMessage;
  }
  
  public void setOrderId(int paramInt)
  {
    this.orderId = paramInt;
  }
  
  public void setPurchaseStatus(int paramInt)
  {
    this.purchaseStatus = paramInt;
  }
  
  public void setShareMessage(String paramString)
  {
    this.shareMessage = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/PurchaseStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */