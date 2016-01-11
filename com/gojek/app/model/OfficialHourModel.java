package com.gojek.app.model;

public class OfficialHourModel
{
  public String closeTime;
  public String dayOfWeek;
  public int id;
  public int merchantId;
  public String openTime;
  
  public OfficialHourModel() {}
  
  public OfficialHourModel(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    this.id = paramInt1;
    this.merchantId = paramInt2;
    this.dayOfWeek = paramString1;
    this.openTime = paramString2;
    this.closeTime = paramString3;
  }
  
  public String getCloseTime()
  {
    return this.closeTime;
  }
  
  public String getDayOfWeek()
  {
    return this.dayOfWeek;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getMerchantId()
  {
    return this.merchantId;
  }
  
  public String getOpenTime()
  {
    return this.openTime;
  }
  
  public void setCloseTime(String paramString)
  {
    this.closeTime = paramString;
  }
  
  public void setDayOfWeek(String paramString)
  {
    this.dayOfWeek = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setMerchantId(int paramInt)
  {
    this.merchantId = paramInt;
  }
  
  public void setOpenTime(String paramString)
  {
    this.openTime = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/OfficialHourModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */