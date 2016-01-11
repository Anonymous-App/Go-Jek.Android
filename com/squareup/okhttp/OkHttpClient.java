package com.squareup.okhttp;

import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Network;
import com.squareup.okhttp.internal.RouteDatabase;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.AuthenticatorAdapter;
import com.squareup.okhttp.internal.tls.OkHostnameVerifier;
import java.net.CookieHandler;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public class OkHttpClient
  implements Cloneable
{
  private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS;
  private static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(new Protocol[] { Protocol.HTTP_2, Protocol.SPDY_3, Protocol.HTTP_1_1 });
  private static SSLSocketFactory defaultSslSocketFactory;
  private Authenticator authenticator;
  private Cache cache;
  private CertificatePinner certificatePinner;
  private int connectTimeout;
  private ConnectionPool connectionPool;
  private List<ConnectionSpec> connectionSpecs;
  private CookieHandler cookieHandler;
  private Dispatcher dispatcher;
  private boolean followRedirects = true;
  private boolean followSslRedirects = true;
  private HostnameVerifier hostnameVerifier;
  private final List<Interceptor> interceptors = new ArrayList();
  private InternalCache internalCache;
  private Network network;
  private final List<Interceptor> networkInterceptors = new ArrayList();
  private List<Protocol> protocols;
  private Proxy proxy;
  private ProxySelector proxySelector;
  private int readTimeout;
  private boolean retryOnConnectionFailure = true;
  private final RouteDatabase routeDatabase;
  private SocketFactory socketFactory;
  private SSLSocketFactory sslSocketFactory;
  private int writeTimeout;
  
  static
  {
    DEFAULT_CONNECTION_SPECS = Util.immutableList(new ConnectionSpec[] { ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT });
    com.squareup.okhttp.internal.Internal.instance = new OkHttpClient.1();
  }
  
  public OkHttpClient()
  {
    this.routeDatabase = new RouteDatabase();
    this.dispatcher = new Dispatcher();
  }
  
  private OkHttpClient(OkHttpClient paramOkHttpClient)
  {
    this.routeDatabase = paramOkHttpClient.routeDatabase;
    this.dispatcher = paramOkHttpClient.dispatcher;
    this.proxy = paramOkHttpClient.proxy;
    this.protocols = paramOkHttpClient.protocols;
    this.connectionSpecs = paramOkHttpClient.connectionSpecs;
    this.interceptors.addAll(paramOkHttpClient.interceptors);
    this.networkInterceptors.addAll(paramOkHttpClient.networkInterceptors);
    this.proxySelector = paramOkHttpClient.proxySelector;
    this.cookieHandler = paramOkHttpClient.cookieHandler;
    this.cache = paramOkHttpClient.cache;
    if (this.cache != null) {}
    for (InternalCache localInternalCache = this.cache.internalCache;; localInternalCache = paramOkHttpClient.internalCache)
    {
      this.internalCache = localInternalCache;
      this.socketFactory = paramOkHttpClient.socketFactory;
      this.sslSocketFactory = paramOkHttpClient.sslSocketFactory;
      this.hostnameVerifier = paramOkHttpClient.hostnameVerifier;
      this.certificatePinner = paramOkHttpClient.certificatePinner;
      this.authenticator = paramOkHttpClient.authenticator;
      this.connectionPool = paramOkHttpClient.connectionPool;
      this.network = paramOkHttpClient.network;
      this.followSslRedirects = paramOkHttpClient.followSslRedirects;
      this.followRedirects = paramOkHttpClient.followRedirects;
      this.retryOnConnectionFailure = paramOkHttpClient.retryOnConnectionFailure;
      this.connectTimeout = paramOkHttpClient.connectTimeout;
      this.readTimeout = paramOkHttpClient.readTimeout;
      this.writeTimeout = paramOkHttpClient.writeTimeout;
      return;
    }
  }
  
  /* Error */
  private SSLSocketFactory getDefaultSSLSocketFactory()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 179	com/squareup/okhttp/OkHttpClient:defaultSslSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
    //   5: astore_1
    //   6: aload_1
    //   7: ifnonnull +23 -> 30
    //   10: ldc -75
    //   12: invokestatic 187	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   15: astore_1
    //   16: aload_1
    //   17: aconst_null
    //   18: aconst_null
    //   19: aconst_null
    //   20: invokevirtual 191	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   23: aload_1
    //   24: invokevirtual 194	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   27: putstatic 179	com/squareup/okhttp/OkHttpClient:defaultSslSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
    //   30: getstatic 179	com/squareup/okhttp/OkHttpClient:defaultSslSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: areturn
    //   38: astore_1
    //   39: new 196	java/lang/AssertionError
    //   42: dup
    //   43: invokespecial 197	java/lang/AssertionError:<init>	()V
    //   46: athrow
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	OkHttpClient
    //   5	32	1	localObject1	Object
    //   38	1	1	localGeneralSecurityException	java.security.GeneralSecurityException
    //   47	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   10	30	38	java/security/GeneralSecurityException
    //   2	6	47	finally
    //   10	30	47	finally
    //   30	34	47	finally
    //   39	47	47	finally
  }
  
  public OkHttpClient cancel(Object paramObject)
  {
    getDispatcher().cancel(paramObject);
    return this;
  }
  
  public final OkHttpClient clone()
  {
    try
    {
      OkHttpClient localOkHttpClient = (OkHttpClient)super.clone();
      return localOkHttpClient;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError();
    }
  }
  
  final OkHttpClient copyWithDefaults()
  {
    OkHttpClient localOkHttpClient = new OkHttpClient(this);
    if (localOkHttpClient.proxySelector == null) {
      localOkHttpClient.proxySelector = ProxySelector.getDefault();
    }
    if (localOkHttpClient.cookieHandler == null) {
      localOkHttpClient.cookieHandler = CookieHandler.getDefault();
    }
    if (localOkHttpClient.socketFactory == null) {
      localOkHttpClient.socketFactory = SocketFactory.getDefault();
    }
    if (localOkHttpClient.sslSocketFactory == null) {
      localOkHttpClient.sslSocketFactory = getDefaultSSLSocketFactory();
    }
    if (localOkHttpClient.hostnameVerifier == null) {
      localOkHttpClient.hostnameVerifier = OkHostnameVerifier.INSTANCE;
    }
    if (localOkHttpClient.certificatePinner == null) {
      localOkHttpClient.certificatePinner = CertificatePinner.DEFAULT;
    }
    if (localOkHttpClient.authenticator == null) {
      localOkHttpClient.authenticator = AuthenticatorAdapter.INSTANCE;
    }
    if (localOkHttpClient.connectionPool == null) {
      localOkHttpClient.connectionPool = ConnectionPool.getDefault();
    }
    if (localOkHttpClient.protocols == null) {
      localOkHttpClient.protocols = DEFAULT_PROTOCOLS;
    }
    if (localOkHttpClient.connectionSpecs == null) {
      localOkHttpClient.connectionSpecs = DEFAULT_CONNECTION_SPECS;
    }
    if (localOkHttpClient.network == null) {
      localOkHttpClient.network = Network.DEFAULT;
    }
    return localOkHttpClient;
  }
  
  public final Authenticator getAuthenticator()
  {
    return this.authenticator;
  }
  
  public final Cache getCache()
  {
    return this.cache;
  }
  
  public final CertificatePinner getCertificatePinner()
  {
    return this.certificatePinner;
  }
  
  public final int getConnectTimeout()
  {
    return this.connectTimeout;
  }
  
  public final ConnectionPool getConnectionPool()
  {
    return this.connectionPool;
  }
  
  public final List<ConnectionSpec> getConnectionSpecs()
  {
    return this.connectionSpecs;
  }
  
  public final CookieHandler getCookieHandler()
  {
    return this.cookieHandler;
  }
  
  public final Dispatcher getDispatcher()
  {
    return this.dispatcher;
  }
  
  public final boolean getFollowRedirects()
  {
    return this.followRedirects;
  }
  
  public final boolean getFollowSslRedirects()
  {
    return this.followSslRedirects;
  }
  
  public final HostnameVerifier getHostnameVerifier()
  {
    return this.hostnameVerifier;
  }
  
  public final List<Protocol> getProtocols()
  {
    return this.protocols;
  }
  
  public final Proxy getProxy()
  {
    return this.proxy;
  }
  
  public final ProxySelector getProxySelector()
  {
    return this.proxySelector;
  }
  
  public final int getReadTimeout()
  {
    return this.readTimeout;
  }
  
  public final boolean getRetryOnConnectionFailure()
  {
    return this.retryOnConnectionFailure;
  }
  
  public final SocketFactory getSocketFactory()
  {
    return this.socketFactory;
  }
  
  public final SSLSocketFactory getSslSocketFactory()
  {
    return this.sslSocketFactory;
  }
  
  public final int getWriteTimeout()
  {
    return this.writeTimeout;
  }
  
  public List<Interceptor> interceptors()
  {
    return this.interceptors;
  }
  
  final InternalCache internalCache()
  {
    return this.internalCache;
  }
  
  public List<Interceptor> networkInterceptors()
  {
    return this.networkInterceptors;
  }
  
  public Call newCall(Request paramRequest)
  {
    return new Call(this, paramRequest);
  }
  
  final RouteDatabase routeDatabase()
  {
    return this.routeDatabase;
  }
  
  public final OkHttpClient setAuthenticator(Authenticator paramAuthenticator)
  {
    this.authenticator = paramAuthenticator;
    return this;
  }
  
  public final OkHttpClient setCache(Cache paramCache)
  {
    this.cache = paramCache;
    this.internalCache = null;
    return this;
  }
  
  public final OkHttpClient setCertificatePinner(CertificatePinner paramCertificatePinner)
  {
    this.certificatePinner = paramCertificatePinner;
    return this;
  }
  
  public final void setConnectTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("timeout < 0");
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L) {
      throw new IllegalArgumentException("Timeout too large.");
    }
    if ((l == 0L) && (paramLong > 0L)) {
      throw new IllegalArgumentException("Timeout too small.");
    }
    this.connectTimeout = ((int)l);
  }
  
  public final OkHttpClient setConnectionPool(ConnectionPool paramConnectionPool)
  {
    this.connectionPool = paramConnectionPool;
    return this;
  }
  
  public final OkHttpClient setConnectionSpecs(List<ConnectionSpec> paramList)
  {
    this.connectionSpecs = Util.immutableList(paramList);
    return this;
  }
  
  public final OkHttpClient setCookieHandler(CookieHandler paramCookieHandler)
  {
    this.cookieHandler = paramCookieHandler;
    return this;
  }
  
  public final OkHttpClient setDispatcher(Dispatcher paramDispatcher)
  {
    if (paramDispatcher == null) {
      throw new IllegalArgumentException("dispatcher == null");
    }
    this.dispatcher = paramDispatcher;
    return this;
  }
  
  public final void setFollowRedirects(boolean paramBoolean)
  {
    this.followRedirects = paramBoolean;
  }
  
  public final OkHttpClient setFollowSslRedirects(boolean paramBoolean)
  {
    this.followSslRedirects = paramBoolean;
    return this;
  }
  
  public final OkHttpClient setHostnameVerifier(HostnameVerifier paramHostnameVerifier)
  {
    this.hostnameVerifier = paramHostnameVerifier;
    return this;
  }
  
  final void setInternalCache(InternalCache paramInternalCache)
  {
    this.internalCache = paramInternalCache;
    this.cache = null;
  }
  
  public final OkHttpClient setProtocols(List<Protocol> paramList)
  {
    paramList = Util.immutableList(paramList);
    if (!paramList.contains(Protocol.HTTP_1_1)) {
      throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + paramList);
    }
    if (paramList.contains(Protocol.HTTP_1_0)) {
      throw new IllegalArgumentException("protocols must not contain http/1.0: " + paramList);
    }
    if (paramList.contains(null)) {
      throw new IllegalArgumentException("protocols must not contain null");
    }
    this.protocols = Util.immutableList(paramList);
    return this;
  }
  
  public final OkHttpClient setProxy(Proxy paramProxy)
  {
    this.proxy = paramProxy;
    return this;
  }
  
  public final OkHttpClient setProxySelector(ProxySelector paramProxySelector)
  {
    this.proxySelector = paramProxySelector;
    return this;
  }
  
  public final void setReadTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("timeout < 0");
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L) {
      throw new IllegalArgumentException("Timeout too large.");
    }
    if ((l == 0L) && (paramLong > 0L)) {
      throw new IllegalArgumentException("Timeout too small.");
    }
    this.readTimeout = ((int)l);
  }
  
  public final void setRetryOnConnectionFailure(boolean paramBoolean)
  {
    this.retryOnConnectionFailure = paramBoolean;
  }
  
  public final OkHttpClient setSocketFactory(SocketFactory paramSocketFactory)
  {
    this.socketFactory = paramSocketFactory;
    return this;
  }
  
  public final OkHttpClient setSslSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
    this.sslSocketFactory = paramSSLSocketFactory;
    return this;
  }
  
  public final void setWriteTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("timeout < 0");
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L) {
      throw new IllegalArgumentException("Timeout too large.");
    }
    if ((l == 0L) && (paramLong > 0L)) {
      throw new IllegalArgumentException("Timeout too small.");
    }
    this.writeTimeout = ((int)l);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/squareup/okhttp/OkHttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */