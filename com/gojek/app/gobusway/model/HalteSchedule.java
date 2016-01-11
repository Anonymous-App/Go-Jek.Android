package com.gojek.app.gobusway.model;

import com.google.gson.annotations.SerializedName;

public class HalteSchedule
{
  @SerializedName("koridor_id")
  private int koridorId;
  @SerializedName("koridor_name")
  private String koridorName;
  @SerializedName("koridor_route")
  private KoridorRoute[] mKoridorRoutes;
  
  public int getKoridorId()
  {
    return this.koridorId;
  }
  
  public String getKoridorName()
  {
    return this.koridorName;
  }
  
  public KoridorRoute[] getKoridorRoutes()
  {
    return this.mKoridorRoutes;
  }
  
  public void setKoridorId(int paramInt)
  {
    this.koridorId = paramInt;
  }
  
  public void setKoridorName(String paramString)
  {
    this.koridorName = paramString;
  }
  
  public void setKoridorRoutes(KoridorRoute[] paramArrayOfKoridorRoute)
  {
    this.mKoridorRoutes = paramArrayOfKoridorRoute;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/HalteSchedule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */