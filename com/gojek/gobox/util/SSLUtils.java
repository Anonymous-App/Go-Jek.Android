package com.gojek.gobox.util;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLUtils
{
  public static X509TrustManager trustManager = new SSLUtils.1();
  
  public static void trustSelfSignedSSL()
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("SSL");
      localSSLContext.init(null, new TrustManager[] { trustManager }, null);
      HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
      return;
    }
    catch (Exception localException)
    {
      throw new RuntimeException("Exception occurred ", localException);
    }
  }
  
  public static void turnOnSslChecking()
  {
    SSLContext.getInstance("SSL").init(null, null, null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/SSLUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */