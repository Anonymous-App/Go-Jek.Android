package com.gojek.gobox.util;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

final class SSLUtils$1
  implements X509TrustManager
{
  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public X509Certificate[] getAcceptedIssuers()
  {
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/SSLUtils$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */