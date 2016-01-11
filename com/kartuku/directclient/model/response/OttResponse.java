package com.kartuku.directclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OttResponse
  extends Response
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  
  public String getMessage()
  {
    return this.ˎ;
  }
  
  public String getToken()
  {
    return this.ˊ;
  }
  
  public String getUrl()
  {
    return this.ˋ;
  }
  
  public void setMessage(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setToken(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setUrl(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/response/OttResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */