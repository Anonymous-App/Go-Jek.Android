package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class db
  implements ab
{
  private final String arH;
  private final HttpClient arI;
  private a arJ;
  private final Context arq;
  
  db(HttpClient paramHttpClient, Context paramContext, a parama)
  {
    this.arq = paramContext.getApplicationContext();
    this.arH = a("GoogleTagManager", "4.00", Build.VERSION.RELEASE, c(Locale.getDefault()), Build.MODEL, Build.ID);
    this.arI = paramHttpClient;
    this.arJ = parama;
  }
  
  private HttpEntityEnclosingRequest a(URL paramURL)
  {
    try
    {
      paramURL = new BasicHttpEntityEnclosingRequest("GET", paramURL.toURI().toString());
      bh.W("Exception sending hit: " + localURISyntaxException1.getClass().getSimpleName());
    }
    catch (URISyntaxException localURISyntaxException1)
    {
      try
      {
        paramURL.addHeader("User-Agent", this.arH);
        return paramURL;
      }
      catch (URISyntaxException localURISyntaxException2)
      {
        for (;;) {}
      }
      localURISyntaxException1 = localURISyntaxException1;
      paramURL = null;
    }
    bh.W(localURISyntaxException1.getMessage());
    return paramURL;
  }
  
  private void a(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = paramHttpEntityEnclosingRequest.getAllHeaders();
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localStringBuffer.append(localObject[i].toString()).append("\n");
      i += 1;
    }
    localStringBuffer.append(paramHttpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
    if (paramHttpEntityEnclosingRequest.getEntity() != null) {}
    try
    {
      paramHttpEntityEnclosingRequest = paramHttpEntityEnclosingRequest.getEntity().getContent();
      if (paramHttpEntityEnclosingRequest != null)
      {
        i = paramHttpEntityEnclosingRequest.available();
        if (i > 0)
        {
          localObject = new byte[i];
          paramHttpEntityEnclosingRequest.read((byte[])localObject);
          localStringBuffer.append("POST:\n");
          localStringBuffer.append(new String((byte[])localObject)).append("\n");
        }
      }
    }
    catch (IOException paramHttpEntityEnclosingRequest)
    {
      for (;;)
      {
        bh.V("Error Writing hit to log...");
      }
    }
    bh.V(localStringBuffer.toString());
  }
  
  static String c(Locale paramLocale)
  {
    if (paramLocale == null) {}
    while ((paramLocale.getLanguage() == null) || (paramLocale.getLanguage().length() == 0)) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLocale.getLanguage().toLowerCase());
    if ((paramLocale.getCountry() != null) && (paramLocale.getCountry().length() != 0)) {
      localStringBuilder.append("-").append(paramLocale.getCountry().toLowerCase());
    }
    return localStringBuilder.toString();
  }
  
  String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6 });
  }
  
  URL d(ap paramap)
  {
    paramap = paramap.ou();
    try
    {
      paramap = new URL(paramap);
      return paramap;
    }
    catch (MalformedURLException paramap)
    {
      bh.T("Error trying to parse the GTM url.");
    }
    return null;
  }
  
  public boolean dX()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.arq.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      bh.V("...no network connectivity");
      return false;
    }
    return true;
  }
  
  /* Error */
  public void j(java.util.List<ap> paramList)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 243 1 0
    //   6: bipush 40
    //   8: invokestatic 249	java/lang/Math:min	(II)I
    //   11: istore 4
    //   13: iconst_1
    //   14: istore_2
    //   15: iconst_0
    //   16: istore_3
    //   17: iload_3
    //   18: iload 4
    //   20: if_icmpge +361 -> 381
    //   23: aload_1
    //   24: iload_3
    //   25: invokeinterface 253 2 0
    //   30: checkcast 203	com/google/android/gms/tagmanager/ap
    //   33: astore 7
    //   35: aload_0
    //   36: aload 7
    //   38: invokevirtual 255	com/google/android/gms/tagmanager/db:d	(Lcom/google/android/gms/tagmanager/ap;)Ljava/net/URL;
    //   41: astore 8
    //   43: aload 8
    //   45: ifnonnull +27 -> 72
    //   48: ldc_w 257
    //   51: invokestatic 121	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   54: aload_0
    //   55: getfield 66	com/google/android/gms/tagmanager/db:arJ	Lcom/google/android/gms/tagmanager/db$a;
    //   58: aload 7
    //   60: invokeinterface 261 2 0
    //   65: iload_3
    //   66: iconst_1
    //   67: iadd
    //   68: istore_3
    //   69: goto -52 -> 17
    //   72: aload_0
    //   73: aload 8
    //   75: invokespecial 263	com/google/android/gms/tagmanager/db:a	(Ljava/net/URL;)Lorg/apache/http/HttpEntityEnclosingRequest;
    //   78: astore 6
    //   80: aload 6
    //   82: ifnonnull +17 -> 99
    //   85: aload_0
    //   86: getfield 66	com/google/android/gms/tagmanager/db:arJ	Lcom/google/android/gms/tagmanager/db$a;
    //   89: aload 7
    //   91: invokeinterface 261 2 0
    //   96: goto -31 -> 65
    //   99: new 265	org/apache/http/HttpHost
    //   102: dup
    //   103: aload 8
    //   105: invokevirtual 268	java/net/URL:getHost	()Ljava/lang/String;
    //   108: aload 8
    //   110: invokevirtual 271	java/net/URL:getPort	()I
    //   113: aload 8
    //   115: invokevirtual 274	java/net/URL:getProtocol	()Ljava/lang/String;
    //   118: invokespecial 277	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   121: astore 8
    //   123: aload 6
    //   125: ldc_w 279
    //   128: aload 8
    //   130: invokevirtual 282	org/apache/http/HttpHost:toHostString	()Ljava/lang/String;
    //   133: invokeinterface 96 3 0
    //   138: aload_0
    //   139: aload 6
    //   141: invokespecial 284	com/google/android/gms/tagmanager/db:a	(Lorg/apache/http/HttpEntityEnclosingRequest;)V
    //   144: iload_2
    //   145: ifeq +247 -> 392
    //   148: aload_0
    //   149: getfield 30	com/google/android/gms/tagmanager/db:arq	Landroid/content/Context;
    //   152: invokestatic 290	com/google/android/gms/tagmanager/bo:A	(Landroid/content/Context;)V
    //   155: iconst_0
    //   156: istore_2
    //   157: aload_0
    //   158: getfield 64	com/google/android/gms/tagmanager/db:arI	Lorg/apache/http/client/HttpClient;
    //   161: astore 9
    //   163: aload 9
    //   165: instanceof 292
    //   168: ifne +107 -> 275
    //   171: aload 9
    //   173: aload 8
    //   175: aload 6
    //   177: invokeinterface 296 3 0
    //   182: astore 6
    //   184: aload 6
    //   186: invokeinterface 302 1 0
    //   191: invokeinterface 307 1 0
    //   196: istore 5
    //   198: aload 6
    //   200: invokeinterface 308 1 0
    //   205: astore 8
    //   207: aload 8
    //   209: ifnull +10 -> 219
    //   212: aload 8
    //   214: invokeinterface 311 1 0
    //   219: iload 5
    //   221: sipush 200
    //   224: if_icmpeq +68 -> 292
    //   227: new 98	java/lang/StringBuilder
    //   230: dup
    //   231: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   234: ldc_w 313
    //   237: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: aload 6
    //   242: invokeinterface 302 1 0
    //   247: invokeinterface 307 1 0
    //   252: invokevirtual 316	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   255: invokevirtual 115	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   258: invokestatic 121	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   261: aload_0
    //   262: getfield 66	com/google/android/gms/tagmanager/db:arJ	Lcom/google/android/gms/tagmanager/db$a;
    //   265: aload 7
    //   267: invokeinterface 318 2 0
    //   272: goto +123 -> 395
    //   275: aload 9
    //   277: checkcast 292	org/apache/http/client/HttpClient
    //   280: aload 8
    //   282: aload 6
    //   284: invokestatic 323	com/newrelic/agent/android/instrumentation/HttpInstrumentation:execute	(Lorg/apache/http/client/HttpClient;Lorg/apache/http/HttpHost;Lorg/apache/http/HttpRequest;)Lorg/apache/http/HttpResponse;
    //   287: astore 6
    //   289: goto -105 -> 184
    //   292: aload_0
    //   293: getfield 66	com/google/android/gms/tagmanager/db:arJ	Lcom/google/android/gms/tagmanager/db$a;
    //   296: aload 7
    //   298: invokeinterface 325 2 0
    //   303: goto +92 -> 395
    //   306: astore 6
    //   308: ldc_w 327
    //   311: invokestatic 121	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   314: aload_0
    //   315: getfield 66	com/google/android/gms/tagmanager/db:arJ	Lcom/google/android/gms/tagmanager/db$a;
    //   318: aload 7
    //   320: invokeinterface 261 2 0
    //   325: goto -260 -> 65
    //   328: astore 6
    //   330: new 98	java/lang/StringBuilder
    //   333: dup
    //   334: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   337: ldc 101
    //   339: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: aload 6
    //   344: invokevirtual 109	java/lang/Object:getClass	()Ljava/lang/Class;
    //   347: invokevirtual 114	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   350: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: invokevirtual 115	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   356: invokestatic 121	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   359: aload 6
    //   361: invokevirtual 328	java/io/IOException:getMessage	()Ljava/lang/String;
    //   364: invokestatic 121	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   367: aload_0
    //   368: getfield 66	com/google/android/gms/tagmanager/db:arJ	Lcom/google/android/gms/tagmanager/db$a;
    //   371: aload 7
    //   373: invokeinterface 318 2 0
    //   378: goto -313 -> 65
    //   381: return
    //   382: astore 6
    //   384: goto -54 -> 330
    //   387: astore 6
    //   389: goto -81 -> 308
    //   392: goto -235 -> 157
    //   395: goto -330 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	398	0	this	db
    //   0	398	1	paramList	java.util.List<ap>
    //   14	143	2	i	int
    //   16	53	3	j	int
    //   11	10	4	k	int
    //   196	29	5	m	int
    //   78	210	6	localObject1	Object
    //   306	1	6	localClientProtocolException1	org.apache.http.client.ClientProtocolException
    //   328	32	6	localIOException1	IOException
    //   382	1	6	localIOException2	IOException
    //   387	1	6	localClientProtocolException2	org.apache.http.client.ClientProtocolException
    //   33	339	7	localap	ap
    //   41	240	8	localObject2	Object
    //   161	115	9	localHttpClient	HttpClient
    // Exception table:
    //   from	to	target	type
    //   157	184	306	org/apache/http/client/ClientProtocolException
    //   184	207	306	org/apache/http/client/ClientProtocolException
    //   212	219	306	org/apache/http/client/ClientProtocolException
    //   227	272	306	org/apache/http/client/ClientProtocolException
    //   275	289	306	org/apache/http/client/ClientProtocolException
    //   292	303	306	org/apache/http/client/ClientProtocolException
    //   148	155	328	java/io/IOException
    //   157	184	382	java/io/IOException
    //   184	207	382	java/io/IOException
    //   212	219	382	java/io/IOException
    //   227	272	382	java/io/IOException
    //   275	289	382	java/io/IOException
    //   292	303	382	java/io/IOException
    //   148	155	387	org/apache/http/client/ClientProtocolException
  }
  
  public static abstract interface a
  {
    public abstract void a(ap paramap);
    
    public abstract void b(ap paramap);
    
    public abstract void c(ap paramap);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/db.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */