package com.google.android.gms.internal;

import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@ez
public class go
{
  public static final a<Void> wy = new a()
  {
    public Void c(InputStream paramAnonymousInputStream)
    {
      return null;
    }
    
    public Void dq()
    {
      return null;
    }
  };
  
  public <T> Future<T> a(final String paramString, final a<T> parama)
  {
    gi.submit(new Callable()
    {
      /* Error */
      public T call()
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 5
        //   3: aconst_null
        //   4: astore_2
        //   5: aconst_null
        //   6: astore 4
        //   8: new 38	java/net/URL
        //   11: dup
        //   12: aload_0
        //   13: getfield 24	com/google/android/gms/internal/go$2:wz	Ljava/lang/String;
        //   16: invokespecial 41	java/net/URL:<init>	(Ljava/lang/String;)V
        //   19: invokevirtual 45	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   22: invokestatic 50	com/newrelic/agent/android/instrumentation/HttpInstrumentation:openConnection	(Ljava/net/URLConnection;)Ljava/net/URLConnection;
        //   25: checkcast 52	java/net/HttpURLConnection
        //   28: astore_3
        //   29: aload_3
        //   30: invokevirtual 55	java/net/HttpURLConnection:connect	()V
        //   33: aload_3
        //   34: invokevirtual 59	java/net/HttpURLConnection:getResponseCode	()I
        //   37: istore_1
        //   38: iload_1
        //   39: sipush 200
        //   42: if_icmplt +34 -> 76
        //   45: iload_1
        //   46: sipush 299
        //   49: if_icmpgt +27 -> 76
        //   52: aload_0
        //   53: getfield 26	com/google/android/gms/internal/go$2:wA	Lcom/google/android/gms/internal/go$a;
        //   56: aload_3
        //   57: invokevirtual 63	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
        //   60: invokeinterface 69 2 0
        //   65: astore_2
        //   66: aload_3
        //   67: ifnull +7 -> 74
        //   70: aload_3
        //   71: invokevirtual 72	java/net/HttpURLConnection:disconnect	()V
        //   74: aload_2
        //   75: areturn
        //   76: aload_3
        //   77: ifnull +7 -> 84
        //   80: aload_3
        //   81: invokevirtual 72	java/net/HttpURLConnection:disconnect	()V
        //   84: aload_0
        //   85: getfield 26	com/google/android/gms/internal/go$2:wA	Lcom/google/android/gms/internal/go$a;
        //   88: invokeinterface 75 1 0
        //   93: areturn
        //   94: astore_2
        //   95: aload 4
        //   97: astore_3
        //   98: aload_2
        //   99: astore 4
        //   101: aload_3
        //   102: astore_2
        //   103: ldc 77
        //   105: aload 4
        //   107: invokestatic 83	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   110: aload_3
        //   111: ifnull -27 -> 84
        //   114: aload_3
        //   115: invokevirtual 72	java/net/HttpURLConnection:disconnect	()V
        //   118: goto -34 -> 84
        //   121: astore 4
        //   123: aload 5
        //   125: astore_3
        //   126: aload_3
        //   127: astore_2
        //   128: ldc 77
        //   130: aload 4
        //   132: invokestatic 83	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   135: aload_3
        //   136: ifnull -52 -> 84
        //   139: aload_3
        //   140: invokevirtual 72	java/net/HttpURLConnection:disconnect	()V
        //   143: goto -59 -> 84
        //   146: astore 4
        //   148: aload_2
        //   149: astore_3
        //   150: aload 4
        //   152: astore_2
        //   153: aload_3
        //   154: ifnull +7 -> 161
        //   157: aload_3
        //   158: invokevirtual 72	java/net/HttpURLConnection:disconnect	()V
        //   161: aload_2
        //   162: athrow
        //   163: astore_2
        //   164: goto -11 -> 153
        //   167: astore 4
        //   169: goto -43 -> 126
        //   172: astore 4
        //   174: goto -73 -> 101
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	177	0	this	2
        //   37	13	1	i	int
        //   4	71	2	localObject1	Object
        //   94	5	2	localMalformedURLException1	java.net.MalformedURLException
        //   102	60	2	localObject2	Object
        //   163	1	2	localObject3	Object
        //   28	130	3	localObject4	Object
        //   6	100	4	localMalformedURLException2	java.net.MalformedURLException
        //   121	10	4	localIOException1	java.io.IOException
        //   146	5	4	localObject5	Object
        //   167	1	4	localIOException2	java.io.IOException
        //   172	1	4	localMalformedURLException3	java.net.MalformedURLException
        //   1	123	5	localObject6	Object
        // Exception table:
        //   from	to	target	type
        //   8	29	94	java/net/MalformedURLException
        //   8	29	121	java/io/IOException
        //   8	29	146	finally
        //   103	110	146	finally
        //   128	135	146	finally
        //   29	38	163	finally
        //   52	66	163	finally
        //   29	38	167	java/io/IOException
        //   52	66	167	java/io/IOException
        //   29	38	172	java/net/MalformedURLException
        //   52	66	172	java/net/MalformedURLException
      }
    });
  }
  
  public static abstract interface a<T>
  {
    public abstract T b(InputStream paramInputStream);
    
    public abstract T cJ();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/go.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */