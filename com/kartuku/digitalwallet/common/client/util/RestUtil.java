package com.kartuku.digitalwallet.common.client.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;

public class RestUtil
{
  public static boolean isError(HttpStatus paramHttpStatus)
  {
    paramHttpStatus = paramHttpStatus.series();
    return (HttpStatus.Series.CLIENT_ERROR.equals(paramHttpStatus)) || (HttpStatus.Series.SERVER_ERROR.equals(paramHttpStatus));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/client/util/RestUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */