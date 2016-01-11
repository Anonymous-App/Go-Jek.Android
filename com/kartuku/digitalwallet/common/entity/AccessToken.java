package com.kartuku.digitalwallet.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class AccessToken
  implements Serializable
{
  private boolean ʻ;
  private boolean ʼ;
  private boolean ʽ;
  private List<Product> ʾ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private boolean ˏ;
  private Timestamp ͺ;
  private boolean ᐝ;
  private List<User> ι;
  
  public AccessToken() {}
  
  public AccessToken(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Timestamp paramTimestamp)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
    this.ˎ = paramString3;
    this.ˏ = paramBoolean1;
    this.ᐝ = paramBoolean2;
    this.ʼ = paramBoolean3;
    this.ͺ = paramTimestamp;
  }
  
  public String getAccessToken()
  {
    return this.ˎ;
  }
  
  public Timestamp getDateUpdate()
  {
    return this.ͺ;
  }
  
  public List<Product> getProduct()
  {
    return this.ʾ;
  }
  
  public String getTokenId()
  {
    return this.ˊ;
  }
  
  public String getTokenOwner()
  {
    return this.ˋ;
  }
  
  public List<User> getUser()
  {
    return this.ι;
  }
  
  public boolean isAutoLinkCard()
  {
    return this.ʻ;
  }
  
  public boolean isCheckUserMerchant()
  {
    return this.ʼ;
  }
  
  public boolean isEnableToBypass()
  {
    return this.ˏ;
  }
  
  public boolean isEnableToBypassSession()
  {
    return this.ᐝ;
  }
  
  public boolean isIntegrateToIAM()
  {
    return this.ʽ;
  }
  
  public void setAccessToken(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setAutoLinkCard(boolean paramBoolean)
  {
    this.ʻ = paramBoolean;
  }
  
  public void setCheckUserMerchant(boolean paramBoolean)
  {
    this.ʼ = paramBoolean;
  }
  
  public void setDateUpdate(Timestamp paramTimestamp)
  {
    this.ͺ = paramTimestamp;
  }
  
  public void setEnableToBypass(boolean paramBoolean)
  {
    this.ˏ = paramBoolean;
  }
  
  public void setEnableToBypassSession(boolean paramBoolean)
  {
    this.ᐝ = paramBoolean;
  }
  
  public void setIntegrateToIAM(boolean paramBoolean)
  {
    this.ʽ = paramBoolean;
  }
  
  public void setProduct(List<Product> paramList)
  {
    this.ʾ = paramList;
  }
  
  public void setTokenId(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setTokenOwner(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setUser(List<User> paramList)
  {
    this.ι = paramList;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/AccessToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */