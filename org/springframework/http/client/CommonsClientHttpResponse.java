package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.springframework.http.HttpHeaders;

@Deprecated
final class CommonsClientHttpResponse
  extends AbstractClientHttpResponse
{
  private HttpHeaders headers;
  private final HttpMethod httpMethod;
  
  CommonsClientHttpResponse(HttpMethod paramHttpMethod)
  {
    this.httpMethod = paramHttpMethod;
  }
  
  protected void closeInternal()
  {
    this.httpMethod.releaseConnection();
  }
  
  protected InputStream getBodyInternal()
    throws IOException
  {
    return this.httpMethod.getResponseBodyAsStream();
  }
  
  public HttpHeaders getHeaders()
  {
    if (this.headers == null)
    {
      this.headers = new HttpHeaders();
      Header[] arrayOfHeader = this.httpMethod.getResponseHeaders();
      int j = arrayOfHeader.length;
      int i = 0;
      while (i < j)
      {
        Header localHeader = arrayOfHeader[i];
        this.headers.add(localHeader.getName(), localHeader.getValue());
        i += 1;
      }
    }
    return this.headers;
  }
  
  public int getRawStatusCode()
  {
    return this.httpMethod.getStatusCode();
  }
  
  public String getStatusText()
  {
    return this.httpMethod.getStatusText();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/springframework/http/client/CommonsClientHttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */