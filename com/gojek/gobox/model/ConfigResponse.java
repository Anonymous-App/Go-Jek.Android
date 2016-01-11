package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class ConfigResponse
{
  @SerializedName("callcentre_phonenumber")
  private String callCenterNumber;
  @SerializedName("escrow_ceiling")
  private String escrowCeiling;
  @SerializedName("max_additional_shipper")
  private String maxAdditionalShipper;
  @SerializedName("max_multi_dest")
  private String maxMultiDestination;
  
  public ConfigResponse(String paramString1, String paramString2)
  {
    this.maxMultiDestination = paramString1;
    this.maxAdditionalShipper = paramString2;
  }
  
  public String getCallCenterNumber()
  {
    return this.callCenterNumber;
  }
  
  public long getEscrowCeiling()
  {
    return Long.parseLong(this.escrowCeiling);
  }
  
  public int getMaxAdditionalShipper()
  {
    return Integer.parseInt(this.maxAdditionalShipper);
  }
  
  public int getMaxMultiDestination()
  {
    return Integer.parseInt(this.maxMultiDestination);
  }
  
  public void setCallCenterNumber(String paramString)
  {
    this.callCenterNumber = paramString;
  }
  
  public void setEscrowCeiling(String paramString)
  {
    this.escrowCeiling = paramString;
  }
  
  public void setMaxAdditionalShipper(String paramString)
  {
    this.maxAdditionalShipper = paramString;
  }
  
  public void setMaxMultiDestination(String paramString)
  {
    this.maxMultiDestination = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/ConfigResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */