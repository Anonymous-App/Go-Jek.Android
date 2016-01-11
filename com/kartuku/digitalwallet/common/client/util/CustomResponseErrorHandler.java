package com.kartuku.digitalwallet.common.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class CustomResponseErrorHandler
  implements ResponseErrorHandler
{
  public void handleError(ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    Object localObject = new BufferedReader(new InputStreamReader(paramClientHttpResponse.getBody()));
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      String str = ((BufferedReader)localObject).readLine();
      if (str == null) {
        break;
      }
      localStringBuilder.append(str);
    }
    localObject = localStringBuilder.toString();
    throw new CustomException(paramClientHttpResponse.getStatusCode(), (String)localObject, (String)localObject);
  }
  
  public boolean hasError(ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    return RestUtil.isError(paramClientHttpResponse.getStatusCode());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/client/util/CustomResponseErrorHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */