package com.kartuku.digitalwallet.common.client.util;

import java.io.IOException;
import org.springframework.http.HttpStatus;

public class CustomException
  extends IOException
{
  private HttpStatus ˊ;
  private String ˋ;
  
  public CustomException(String paramString)
  {
    super(paramString);
  }
  
  public CustomException(HttpStatus paramHttpStatus, String paramString1, String paramString2)
  {
    super(paramString2);
    this.ˊ = paramHttpStatus;
    this.ˋ = paramString1;
  }
  
  public String getBody()
  {
    return this.ˋ;
  }
  
  public HttpStatus getStatusCode()
  {
    return this.ˊ;
  }
  
  public void setBody(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setStatusCode(HttpStatus paramHttpStatus)
  {
    this.ˊ = paramHttpStatus;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/client/util/CustomException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */