package com.google.android.gms.internal;

import android.content.Context;

@ez
public final class gq
  extends gg
{
  private final Context mContext;
  private final String mv;
  private final String uR;
  private String vW = null;
  
  public gq(Context paramContext, String paramString1, String paramString2)
  {
    this.mContext = paramContext;
    this.mv = paramString1;
    this.uR = paramString2;
  }
  
  public gq(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this.mContext = paramContext;
    this.mv = paramString1;
    this.uR = paramString2;
    this.vW = paramString3;
  }
  
  /* Error */
  public void co()
  {
    // Byte code:
    //   0: new 33	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   7: ldc 36
    //   9: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: aload_0
    //   13: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   16: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 50	com/google/android/gms/internal/gs:V	(Ljava/lang/String;)V
    //   25: new 52	java/net/URL
    //   28: dup
    //   29: aload_0
    //   30: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   33: invokespecial 54	java/net/URL:<init>	(Ljava/lang/String;)V
    //   36: invokevirtual 58	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   39: invokestatic 63	com/newrelic/agent/android/instrumentation/HttpInstrumentation:openConnection	(Ljava/net/URLConnection;)Ljava/net/URLConnection;
    //   42: checkcast 65	java/net/HttpURLConnection
    //   45: astore_2
    //   46: aload_0
    //   47: getfield 18	com/google/android/gms/internal/gq:vW	Ljava/lang/String;
    //   50: ifnonnull +74 -> 124
    //   53: aload_0
    //   54: getfield 20	com/google/android/gms/internal/gq:mContext	Landroid/content/Context;
    //   57: aload_0
    //   58: getfield 22	com/google/android/gms/internal/gq:mv	Ljava/lang/String;
    //   61: iconst_1
    //   62: aload_2
    //   63: invokestatic 71	com/google/android/gms/internal/gj:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
    //   66: aload_2
    //   67: invokevirtual 75	java/net/HttpURLConnection:getResponseCode	()I
    //   70: istore_1
    //   71: iload_1
    //   72: sipush 200
    //   75: if_icmplt +10 -> 85
    //   78: iload_1
    //   79: sipush 300
    //   82: if_icmplt +37 -> 119
    //   85: new 33	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   92: ldc 77
    //   94: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: iload_1
    //   98: invokevirtual 80	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   101: ldc 82
    //   103: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_0
    //   107: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   110: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 85	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   119: aload_2
    //   120: invokevirtual 88	java/net/HttpURLConnection:disconnect	()V
    //   123: return
    //   124: aload_0
    //   125: getfield 20	com/google/android/gms/internal/gq:mContext	Landroid/content/Context;
    //   128: aload_0
    //   129: getfield 22	com/google/android/gms/internal/gq:mv	Ljava/lang/String;
    //   132: iconst_1
    //   133: aload_2
    //   134: aload_0
    //   135: getfield 18	com/google/android/gms/internal/gq:vW	Ljava/lang/String;
    //   138: invokestatic 91	com/google/android/gms/internal/gj:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;Ljava/lang/String;)V
    //   141: goto -75 -> 66
    //   144: astore_3
    //   145: aload_2
    //   146: invokevirtual 88	java/net/HttpURLConnection:disconnect	()V
    //   149: aload_3
    //   150: athrow
    //   151: astore_2
    //   152: new 33	java/lang/StringBuilder
    //   155: dup
    //   156: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   159: ldc 93
    //   161: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload_0
    //   165: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   168: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: ldc 95
    //   173: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: aload_2
    //   177: invokevirtual 98	java/lang/IndexOutOfBoundsException:getMessage	()Ljava/lang/String;
    //   180: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: invokestatic 85	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   189: return
    //   190: astore_2
    //   191: new 33	java/lang/StringBuilder
    //   194: dup
    //   195: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   198: ldc 100
    //   200: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: aload_0
    //   204: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   207: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: ldc 95
    //   212: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: aload_2
    //   216: invokevirtual 101	java/io/IOException:getMessage	()Ljava/lang/String;
    //   219: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokestatic 85	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   228: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	229	0	this	gq
    //   70	28	1	i	int
    //   45	101	2	localHttpURLConnection	java.net.HttpURLConnection
    //   151	26	2	localIndexOutOfBoundsException	IndexOutOfBoundsException
    //   190	26	2	localIOException	java.io.IOException
    //   144	6	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   46	66	144	finally
    //   66	71	144	finally
    //   85	119	144	finally
    //   124	141	144	finally
    //   0	46	151	java/lang/IndexOutOfBoundsException
    //   119	123	151	java/lang/IndexOutOfBoundsException
    //   145	151	151	java/lang/IndexOutOfBoundsException
    //   0	46	190	java/io/IOException
    //   119	123	190	java/io/IOException
    //   145	151	190	java/io/IOException
  }
  
  public void onStop() {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */