package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache.Entry;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork
  implements Network
{
  protected static final boolean DEBUG = VolleyLog.DEBUG;
  private static int DEFAULT_POOL_SIZE = 4096;
  private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
  protected final HttpStack mHttpStack;
  protected final ByteArrayPool mPool;
  
  public BasicNetwork(HttpStack paramHttpStack)
  {
    this(paramHttpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
  }
  
  public BasicNetwork(HttpStack paramHttpStack, ByteArrayPool paramByteArrayPool)
  {
    this.mHttpStack = paramHttpStack;
    this.mPool = paramByteArrayPool;
  }
  
  private void addCacheHeaders(Map<String, String> paramMap, Cache.Entry paramEntry)
  {
    if (paramEntry == null) {}
    do
    {
      return;
      if (paramEntry.etag != null) {
        paramMap.put("If-None-Match", paramEntry.etag);
      }
    } while (paramEntry.lastModified <= 0L);
    paramMap.put("If-Modified-Since", DateUtils.formatDate(new Date(paramEntry.lastModified)));
  }
  
  private static void attemptRetryOnException(String paramString, Request<?> paramRequest, VolleyError paramVolleyError)
    throws VolleyError
  {
    RetryPolicy localRetryPolicy = paramRequest.getRetryPolicy();
    int i = paramRequest.getTimeoutMs();
    try
    {
      localRetryPolicy.retry(paramVolleyError);
      paramRequest.addMarker(String.format("%s-retry [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
      return;
    }
    catch (VolleyError paramVolleyError)
    {
      paramRequest.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
      throw paramVolleyError;
    }
  }
  
  protected static Map<String, String> convertHeaders(Header[] paramArrayOfHeader)
  {
    TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    int i = 0;
    while (i < paramArrayOfHeader.length)
    {
      localTreeMap.put(paramArrayOfHeader[i].getName(), paramArrayOfHeader[i].getValue());
      i += 1;
    }
    return localTreeMap;
  }
  
  private byte[] entityToBytes(HttpEntity paramHttpEntity)
    throws IOException, ServerError
  {
    PoolingByteArrayOutputStream localPoolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int)paramHttpEntity.getContentLength());
    Object localObject2 = null;
    Object localObject1 = localObject2;
    Object localObject4;
    try
    {
      localObject4 = paramHttpEntity.getContent();
      if (localObject4 == null)
      {
        localObject1 = localObject2;
        throw new ServerError();
      }
    }
    finally {}
    try
    {
      paramHttpEntity.consumeContent();
      this.mPool.returnBuf((byte[])localObject1);
      localPoolingByteArrayOutputStream.close();
      throw ((Throwable)localObject3);
      localObject1 = localObject3;
      byte[] arrayOfByte = this.mPool.getBuf(1024);
      for (;;)
      {
        localObject1 = arrayOfByte;
        int i = ((InputStream)localObject4).read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localObject1 = arrayOfByte;
        localPoolingByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      localObject1 = arrayOfByte;
      localObject4 = localPoolingByteArrayOutputStream.toByteArray();
      try
      {
        paramHttpEntity.consumeContent();
        this.mPool.returnBuf(arrayOfByte);
        localPoolingByteArrayOutputStream.close();
        return (byte[])localObject4;
      }
      catch (IOException paramHttpEntity)
      {
        for (;;)
        {
          VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
        }
      }
    }
    catch (IOException paramHttpEntity)
    {
      for (;;)
      {
        VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
      }
    }
  }
  
  private void logSlowRequests(long paramLong, Request<?> paramRequest, byte[] paramArrayOfByte, StatusLine paramStatusLine)
  {
    if ((DEBUG) || (paramLong > SLOW_REQUEST_THRESHOLD_MS)) {
      if (paramArrayOfByte == null) {
        break label82;
      }
    }
    label82:
    for (paramArrayOfByte = Integer.valueOf(paramArrayOfByte.length);; paramArrayOfByte = "null")
    {
      VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[] { paramRequest, Long.valueOf(paramLong), paramArrayOfByte, Integer.valueOf(paramStatusLine.getStatusCode()), Integer.valueOf(paramRequest.getRetryPolicy().getCurrentRetryCount()) });
      return;
    }
  }
  
  protected void logError(String paramString1, String paramString2, long paramLong)
  {
    VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", new Object[] { paramString1, Long.valueOf(SystemClock.elapsedRealtime() - paramLong), paramString2 });
  }
  
  /* Error */
  public com.android.volley.NetworkResponse performRequest(Request<?> paramRequest)
    throws VolleyError
  {
    // Byte code:
    //   0: invokestatic 229	android/os/SystemClock:elapsedRealtime	()J
    //   3: lstore_3
    //   4: aconst_null
    //   5: astore 7
    //   7: invokestatic 243	java/util/Collections:emptyMap	()Ljava/util/Map;
    //   10: astore 8
    //   12: aload 8
    //   14: astore 5
    //   16: aload 7
    //   18: astore 6
    //   20: new 245	java/util/HashMap
    //   23: dup
    //   24: invokespecial 246	java/util/HashMap:<init>	()V
    //   27: astore 9
    //   29: aload 8
    //   31: astore 5
    //   33: aload 7
    //   35: astore 6
    //   37: aload_0
    //   38: aload 9
    //   40: aload_1
    //   41: invokevirtual 250	com/android/volley/Request:getCacheEntry	()Lcom/android/volley/Cache$Entry;
    //   44: invokespecial 252	com/android/volley/toolbox/BasicNetwork:addCacheHeaders	(Ljava/util/Map;Lcom/android/volley/Cache$Entry;)V
    //   47: aload 8
    //   49: astore 5
    //   51: aload 7
    //   53: astore 6
    //   55: aload_0
    //   56: getfield 41	com/android/volley/toolbox/BasicNetwork:mHttpStack	Lcom/android/volley/toolbox/HttpStack;
    //   59: aload_1
    //   60: aload 9
    //   62: invokeinterface 257 3 0
    //   67: astore 7
    //   69: aload 8
    //   71: astore 5
    //   73: aload 7
    //   75: astore 6
    //   77: aload 7
    //   79: invokeinterface 263 1 0
    //   84: astore 10
    //   86: aload 8
    //   88: astore 5
    //   90: aload 7
    //   92: astore 6
    //   94: aload 10
    //   96: invokeinterface 211 1 0
    //   101: istore_2
    //   102: aload 8
    //   104: astore 5
    //   106: aload 7
    //   108: astore 6
    //   110: aload 7
    //   112: invokeinterface 267 1 0
    //   117: invokestatic 269	com/android/volley/toolbox/BasicNetwork:convertHeaders	([Lorg/apache/http/Header;)Ljava/util/Map;
    //   120: astore 8
    //   122: iload_2
    //   123: sipush 304
    //   126: if_icmpne +570 -> 696
    //   129: aload 8
    //   131: astore 5
    //   133: aload 7
    //   135: astore 6
    //   137: aload_1
    //   138: invokevirtual 250	com/android/volley/Request:getCacheEntry	()Lcom/android/volley/Cache$Entry;
    //   141: astore 9
    //   143: aload 9
    //   145: ifnonnull +31 -> 176
    //   148: aload 8
    //   150: astore 5
    //   152: aload 7
    //   154: astore 6
    //   156: new 271	com/android/volley/NetworkResponse
    //   159: dup
    //   160: sipush 304
    //   163: aconst_null
    //   164: aload 8
    //   166: iconst_1
    //   167: invokestatic 229	android/os/SystemClock:elapsedRealtime	()J
    //   170: lload_3
    //   171: lsub
    //   172: invokespecial 274	com/android/volley/NetworkResponse:<init>	(I[BLjava/util/Map;ZJ)V
    //   175: areturn
    //   176: aload 8
    //   178: astore 5
    //   180: aload 7
    //   182: astore 6
    //   184: aload 9
    //   186: getfield 278	com/android/volley/Cache$Entry:responseHeaders	Ljava/util/Map;
    //   189: aload 8
    //   191: invokeinterface 282 2 0
    //   196: aload 8
    //   198: astore 5
    //   200: aload 7
    //   202: astore 6
    //   204: new 271	com/android/volley/NetworkResponse
    //   207: dup
    //   208: sipush 304
    //   211: aload 9
    //   213: getfield 286	com/android/volley/Cache$Entry:data	[B
    //   216: aload 9
    //   218: getfield 278	com/android/volley/Cache$Entry:responseHeaders	Ljava/util/Map;
    //   221: iconst_1
    //   222: invokestatic 229	android/os/SystemClock:elapsedRealtime	()J
    //   225: lload_3
    //   226: lsub
    //   227: invokespecial 274	com/android/volley/NetworkResponse:<init>	(I[BLjava/util/Map;ZJ)V
    //   230: areturn
    //   231: aload 8
    //   233: astore 5
    //   235: aload 7
    //   237: astore 6
    //   239: aload_1
    //   240: aload 8
    //   242: ldc_w 288
    //   245: invokeinterface 292 2 0
    //   250: checkcast 108	java/lang/String
    //   253: invokevirtual 295	com/android/volley/Request:setRedirectUrl	(Ljava/lang/String;)V
    //   256: aload 8
    //   258: astore 5
    //   260: aload 7
    //   262: astore 6
    //   264: aload 7
    //   266: invokeinterface 299 1 0
    //   271: ifnull +83 -> 354
    //   274: aload 8
    //   276: astore 5
    //   278: aload 7
    //   280: astore 6
    //   282: aload_0
    //   283: aload 7
    //   285: invokeinterface 299 1 0
    //   290: invokespecial 301	com/android/volley/toolbox/BasicNetwork:entityToBytes	(Lorg/apache/http/HttpEntity;)[B
    //   293: astore 9
    //   295: aload 9
    //   297: astore 5
    //   299: aload_0
    //   300: invokestatic 229	android/os/SystemClock:elapsedRealtime	()J
    //   303: lload_3
    //   304: lsub
    //   305: aload_1
    //   306: aload 5
    //   308: aload 10
    //   310: invokespecial 303	com/android/volley/toolbox/BasicNetwork:logSlowRequests	(JLcom/android/volley/Request;[BLorg/apache/http/StatusLine;)V
    //   313: iload_2
    //   314: sipush 200
    //   317: if_icmplt +10 -> 327
    //   320: iload_2
    //   321: sipush 299
    //   324: if_icmple +50 -> 374
    //   327: new 145	java/io/IOException
    //   330: dup
    //   331: invokespecial 304	java/io/IOException:<init>	()V
    //   334: athrow
    //   335: astore 5
    //   337: ldc_w 306
    //   340: aload_1
    //   341: new 308	com/android/volley/TimeoutError
    //   344: dup
    //   345: invokespecial 309	com/android/volley/TimeoutError:<init>	()V
    //   348: invokestatic 311	com/android/volley/toolbox/BasicNetwork:attemptRetryOnException	(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
    //   351: goto -347 -> 4
    //   354: aload 8
    //   356: astore 5
    //   358: aload 7
    //   360: astore 6
    //   362: iconst_0
    //   363: newarray <illegal type>
    //   365: astore 9
    //   367: aload 9
    //   369: astore 5
    //   371: goto -72 -> 299
    //   374: new 271	com/android/volley/NetworkResponse
    //   377: dup
    //   378: iload_2
    //   379: aload 5
    //   381: aload 8
    //   383: iconst_0
    //   384: invokestatic 229	android/os/SystemClock:elapsedRealtime	()J
    //   387: lload_3
    //   388: lsub
    //   389: invokespecial 274	com/android/volley/NetworkResponse:<init>	(I[BLjava/util/Map;ZJ)V
    //   392: astore 6
    //   394: aload 6
    //   396: areturn
    //   397: astore 5
    //   399: ldc_w 313
    //   402: aload_1
    //   403: new 308	com/android/volley/TimeoutError
    //   406: dup
    //   407: invokespecial 309	com/android/volley/TimeoutError:<init>	()V
    //   410: invokestatic 311	com/android/volley/toolbox/BasicNetwork:attemptRetryOnException	(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
    //   413: goto -409 -> 4
    //   416: astore 5
    //   418: new 315	java/lang/RuntimeException
    //   421: dup
    //   422: new 317	java/lang/StringBuilder
    //   425: dup
    //   426: invokespecial 318	java/lang/StringBuilder:<init>	()V
    //   429: ldc_w 320
    //   432: invokevirtual 324	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: aload_1
    //   436: invokevirtual 327	com/android/volley/Request:getUrl	()Ljava/lang/String;
    //   439: invokevirtual 324	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: invokevirtual 330	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   445: aload 5
    //   447: invokespecial 333	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   450: athrow
    //   451: astore 10
    //   453: aconst_null
    //   454: astore 9
    //   456: aload 6
    //   458: astore 7
    //   460: aload 5
    //   462: astore 8
    //   464: aload 10
    //   466: astore 6
    //   468: aload 7
    //   470: ifnull +112 -> 582
    //   473: aload 7
    //   475: invokeinterface 263 1 0
    //   480: invokeinterface 211 1 0
    //   485: istore_2
    //   486: iload_2
    //   487: sipush 301
    //   490: if_icmpeq +10 -> 500
    //   493: iload_2
    //   494: sipush 302
    //   497: if_icmpne +95 -> 592
    //   500: ldc_w 335
    //   503: iconst_2
    //   504: anewarray 4	java/lang/Object
    //   507: dup
    //   508: iconst_0
    //   509: aload_1
    //   510: invokevirtual 338	com/android/volley/Request:getOriginUrl	()Ljava/lang/String;
    //   513: aastore
    //   514: dup
    //   515: iconst_1
    //   516: aload_1
    //   517: invokevirtual 327	com/android/volley/Request:getUrl	()Ljava/lang/String;
    //   520: aastore
    //   521: invokestatic 341	com/android/volley/VolleyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   524: aload 9
    //   526: ifnull +136 -> 662
    //   529: new 271	com/android/volley/NetworkResponse
    //   532: dup
    //   533: iload_2
    //   534: aload 9
    //   536: aload 8
    //   538: iconst_0
    //   539: invokestatic 229	android/os/SystemClock:elapsedRealtime	()J
    //   542: lload_3
    //   543: lsub
    //   544: invokespecial 274	com/android/volley/NetworkResponse:<init>	(I[BLjava/util/Map;ZJ)V
    //   547: astore 5
    //   549: iload_2
    //   550: sipush 401
    //   553: if_icmpeq +10 -> 563
    //   556: iload_2
    //   557: sipush 403
    //   560: if_icmpne +59 -> 619
    //   563: ldc_w 343
    //   566: aload_1
    //   567: new 345	com/android/volley/AuthFailureError
    //   570: dup
    //   571: aload 5
    //   573: invokespecial 348	com/android/volley/AuthFailureError:<init>	(Lcom/android/volley/NetworkResponse;)V
    //   576: invokestatic 311	com/android/volley/toolbox/BasicNetwork:attemptRetryOnException	(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
    //   579: goto -575 -> 4
    //   582: new 350	com/android/volley/NoConnectionError
    //   585: dup
    //   586: aload 6
    //   588: invokespecial 353	com/android/volley/NoConnectionError:<init>	(Ljava/lang/Throwable;)V
    //   591: athrow
    //   592: ldc_w 355
    //   595: iconst_2
    //   596: anewarray 4	java/lang/Object
    //   599: dup
    //   600: iconst_0
    //   601: iload_2
    //   602: invokestatic 106	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   605: aastore
    //   606: dup
    //   607: iconst_1
    //   608: aload_1
    //   609: invokevirtual 327	com/android/volley/Request:getUrl	()Ljava/lang/String;
    //   612: aastore
    //   613: invokestatic 341	com/android/volley/VolleyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   616: goto -92 -> 524
    //   619: iload_2
    //   620: sipush 301
    //   623: if_icmpeq +10 -> 633
    //   626: iload_2
    //   627: sipush 302
    //   630: if_icmpne +22 -> 652
    //   633: ldc_w 357
    //   636: aload_1
    //   637: new 359	com/android/volley/RedirectError
    //   640: dup
    //   641: aload 5
    //   643: invokespecial 360	com/android/volley/RedirectError:<init>	(Lcom/android/volley/NetworkResponse;)V
    //   646: invokestatic 311	com/android/volley/toolbox/BasicNetwork:attemptRetryOnException	(Ljava/lang/String;Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
    //   649: goto -645 -> 4
    //   652: new 147	com/android/volley/ServerError
    //   655: dup
    //   656: aload 5
    //   658: invokespecial 361	com/android/volley/ServerError:<init>	(Lcom/android/volley/NetworkResponse;)V
    //   661: athrow
    //   662: new 363	com/android/volley/NetworkError
    //   665: dup
    //   666: aload 6
    //   668: invokespecial 364	com/android/volley/NetworkError:<init>	(Ljava/lang/Throwable;)V
    //   671: athrow
    //   672: astore 6
    //   674: aload 5
    //   676: astore 9
    //   678: goto -210 -> 468
    //   681: astore 5
    //   683: goto -265 -> 418
    //   686: astore 5
    //   688: goto -289 -> 399
    //   691: astore 5
    //   693: goto -356 -> 337
    //   696: iload_2
    //   697: sipush 301
    //   700: if_icmpeq -469 -> 231
    //   703: iload_2
    //   704: sipush 302
    //   707: if_icmpne -451 -> 256
    //   710: goto -479 -> 231
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	713	0	this	BasicNetwork
    //   0	713	1	paramRequest	Request<?>
    //   101	607	2	i	int
    //   3	540	3	l	long
    //   14	293	5	localObject1	Object
    //   335	1	5	localSocketTimeoutException1	java.net.SocketTimeoutException
    //   356	24	5	localObject2	Object
    //   397	1	5	localConnectTimeoutException1	org.apache.http.conn.ConnectTimeoutException
    //   416	45	5	localMalformedURLException1	java.net.MalformedURLException
    //   547	128	5	localNetworkResponse	com.android.volley.NetworkResponse
    //   681	1	5	localMalformedURLException2	java.net.MalformedURLException
    //   686	1	5	localConnectTimeoutException2	org.apache.http.conn.ConnectTimeoutException
    //   691	1	5	localSocketTimeoutException2	java.net.SocketTimeoutException
    //   18	649	6	localObject3	Object
    //   672	1	6	localIOException1	IOException
    //   5	469	7	localObject4	Object
    //   10	527	8	localObject5	Object
    //   27	650	9	localObject6	Object
    //   84	225	10	localStatusLine	StatusLine
    //   451	14	10	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   299	313	335	java/net/SocketTimeoutException
    //   327	335	335	java/net/SocketTimeoutException
    //   374	394	335	java/net/SocketTimeoutException
    //   20	29	397	org/apache/http/conn/ConnectTimeoutException
    //   37	47	397	org/apache/http/conn/ConnectTimeoutException
    //   55	69	397	org/apache/http/conn/ConnectTimeoutException
    //   77	86	397	org/apache/http/conn/ConnectTimeoutException
    //   94	102	397	org/apache/http/conn/ConnectTimeoutException
    //   110	122	397	org/apache/http/conn/ConnectTimeoutException
    //   137	143	397	org/apache/http/conn/ConnectTimeoutException
    //   156	176	397	org/apache/http/conn/ConnectTimeoutException
    //   184	196	397	org/apache/http/conn/ConnectTimeoutException
    //   204	231	397	org/apache/http/conn/ConnectTimeoutException
    //   239	256	397	org/apache/http/conn/ConnectTimeoutException
    //   264	274	397	org/apache/http/conn/ConnectTimeoutException
    //   282	295	397	org/apache/http/conn/ConnectTimeoutException
    //   362	367	397	org/apache/http/conn/ConnectTimeoutException
    //   20	29	416	java/net/MalformedURLException
    //   37	47	416	java/net/MalformedURLException
    //   55	69	416	java/net/MalformedURLException
    //   77	86	416	java/net/MalformedURLException
    //   94	102	416	java/net/MalformedURLException
    //   110	122	416	java/net/MalformedURLException
    //   137	143	416	java/net/MalformedURLException
    //   156	176	416	java/net/MalformedURLException
    //   184	196	416	java/net/MalformedURLException
    //   204	231	416	java/net/MalformedURLException
    //   239	256	416	java/net/MalformedURLException
    //   264	274	416	java/net/MalformedURLException
    //   282	295	416	java/net/MalformedURLException
    //   362	367	416	java/net/MalformedURLException
    //   20	29	451	java/io/IOException
    //   37	47	451	java/io/IOException
    //   55	69	451	java/io/IOException
    //   77	86	451	java/io/IOException
    //   94	102	451	java/io/IOException
    //   110	122	451	java/io/IOException
    //   137	143	451	java/io/IOException
    //   156	176	451	java/io/IOException
    //   184	196	451	java/io/IOException
    //   204	231	451	java/io/IOException
    //   239	256	451	java/io/IOException
    //   264	274	451	java/io/IOException
    //   282	295	451	java/io/IOException
    //   362	367	451	java/io/IOException
    //   299	313	672	java/io/IOException
    //   327	335	672	java/io/IOException
    //   374	394	672	java/io/IOException
    //   299	313	681	java/net/MalformedURLException
    //   327	335	681	java/net/MalformedURLException
    //   374	394	681	java/net/MalformedURLException
    //   299	313	686	org/apache/http/conn/ConnectTimeoutException
    //   327	335	686	org/apache/http/conn/ConnectTimeoutException
    //   374	394	686	org/apache/http/conn/ConnectTimeoutException
    //   20	29	691	java/net/SocketTimeoutException
    //   37	47	691	java/net/SocketTimeoutException
    //   55	69	691	java/net/SocketTimeoutException
    //   77	86	691	java/net/SocketTimeoutException
    //   94	102	691	java/net/SocketTimeoutException
    //   110	122	691	java/net/SocketTimeoutException
    //   137	143	691	java/net/SocketTimeoutException
    //   156	176	691	java/net/SocketTimeoutException
    //   184	196	691	java/net/SocketTimeoutException
    //   204	231	691	java/net/SocketTimeoutException
    //   239	256	691	java/net/SocketTimeoutException
    //   264	274	691	java/net/SocketTimeoutException
    //   282	295	691	java/net/SocketTimeoutException
    //   362	367	691	java/net/SocketTimeoutException
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/android/volley/toolbox/BasicNetwork.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */