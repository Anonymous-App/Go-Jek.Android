package com.kartuku.digitalwallet.common.entity;

import java.sql.Timestamp;

public class DwSession
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private Timestamp ˏ;
  
  public DwSession() {}
  
  public DwSession(String paramString1, String paramString2)
  {
    this.ˋ = paramString1;
    this.ˎ = paramString2;
  }
  
  public DwSession(String paramString1, String paramString2, String paramString3, Timestamp paramTimestamp)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
    this.ˎ = paramString3;
    this.ˏ = paramTimestamp;
  }
  
  public Timestamp getDateUpdate()
  {
    return this.ˏ;
  }
  
  public String getJsessionID()
  {
    return this.ˎ;
  }
  
  public String getServerName()
  {
    return this.ˋ;
  }
  
  public String getSidd()
  {
    return this.ˊ;
  }
  
  public void setDateUpdate(Timestamp paramTimestamp)
  {
    this.ˏ = paramTimestamp;
  }
  
  public void setJsessionID(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setServerName(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setSidd(String paramString)
  {
    this.ˊ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/DwSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */