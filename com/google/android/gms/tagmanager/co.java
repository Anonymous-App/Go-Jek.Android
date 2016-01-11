package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.c.j;

class co
  implements Runnable
{
  private final String aoc;
  private volatile String aoy;
  private final bn aqr;
  private final String aqs;
  private bg<c.j> aqt;
  private volatile r aqu;
  private volatile String aqv;
  private final Context mContext;
  
  co(Context paramContext, String paramString, bn parambn, r paramr)
  {
    this.mContext = paramContext;
    this.aqr = parambn;
    this.aoc = paramString;
    this.aqu = paramr;
    this.aqs = ("/r?id=" + paramString);
    this.aoy = this.aqs;
    this.aqv = null;
  }
  
  public co(Context paramContext, String paramString, r paramr)
  {
    this(paramContext, paramString, new bn(), paramr);
  }
  
  private boolean oM()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      bh.V("...no network connectivity");
      return false;
    }
    return true;
  }
  
  /* Error */
  private void oN()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 95	com/google/android/gms/tagmanager/co:oM	()Z
    //   4: ifne +16 -> 20
    //   7: aload_0
    //   8: getfield 97	com/google/android/gms/tagmanager/co:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   11: getstatic 103	com/google/android/gms/tagmanager/bg$a:apM	Lcom/google/android/gms/tagmanager/bg$a;
    //   14: invokeinterface 109 2 0
    //   19: return
    //   20: ldc 111
    //   22: invokestatic 88	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: invokevirtual 114	com/google/android/gms/tagmanager/co:oO	()Ljava/lang/String;
    //   29: astore_2
    //   30: aload_0
    //   31: getfield 29	com/google/android/gms/tagmanager/co:aqr	Lcom/google/android/gms/tagmanager/bn;
    //   34: invokevirtual 118	com/google/android/gms/tagmanager/bn:ox	()Lcom/google/android/gms/tagmanager/bm;
    //   37: astore_1
    //   38: aload_1
    //   39: aload_2
    //   40: invokeinterface 124 2 0
    //   45: astore_3
    //   46: new 126	java/io/ByteArrayOutputStream
    //   49: dup
    //   50: invokespecial 127	java/io/ByteArrayOutputStream:<init>	()V
    //   53: astore 4
    //   55: aload_3
    //   56: aload 4
    //   58: invokestatic 133	com/google/android/gms/tagmanager/cr:b	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   61: aload 4
    //   63: invokevirtual 137	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   66: invokestatic 142	com/google/android/gms/internal/c$j:b	([B)Lcom/google/android/gms/internal/c$j;
    //   69: astore_3
    //   70: new 35	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   77: ldc -112
    //   79: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: aload_3
    //   83: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokestatic 88	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   92: aload_3
    //   93: getfield 151	com/google/android/gms/internal/c$j:gs	Lcom/google/android/gms/internal/c$f;
    //   96: ifnonnull +36 -> 132
    //   99: aload_3
    //   100: getfield 155	com/google/android/gms/internal/c$j:gr	[Lcom/google/android/gms/internal/c$i;
    //   103: arraylength
    //   104: ifne +28 -> 132
    //   107: new 35	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   114: ldc -99
    //   116: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: aload_0
    //   120: getfield 31	com/google/android/gms/tagmanager/co:aoc	Ljava/lang/String;
    //   123: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   129: invokestatic 88	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   132: aload_0
    //   133: getfield 97	com/google/android/gms/tagmanager/co:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   136: aload_3
    //   137: invokeinterface 161 2 0
    //   142: aload_1
    //   143: invokeinterface 164 1 0
    //   148: ldc -90
    //   150: invokestatic 88	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   153: return
    //   154: astore_3
    //   155: new 35	java/lang/StringBuilder
    //   158: dup
    //   159: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   162: ldc -88
    //   164: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: aload_2
    //   168: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: ldc -86
    //   173: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: aload_0
    //   177: getfield 31	com/google/android/gms/tagmanager/co:aoc	Ljava/lang/String;
    //   180: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: ldc -84
    //   185: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   191: invokestatic 175	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   194: aload_0
    //   195: getfield 97	com/google/android/gms/tagmanager/co:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   198: getstatic 178	com/google/android/gms/tagmanager/bg$a:apO	Lcom/google/android/gms/tagmanager/bg$a;
    //   201: invokeinterface 109 2 0
    //   206: aload_1
    //   207: invokeinterface 164 1 0
    //   212: return
    //   213: astore_3
    //   214: new 35	java/lang/StringBuilder
    //   217: dup
    //   218: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   221: ldc -76
    //   223: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: aload_2
    //   227: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: ldc -74
    //   232: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload_3
    //   236: invokevirtual 185	java/io/IOException:getMessage	()Ljava/lang/String;
    //   239: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   245: aload_3
    //   246: invokestatic 189	com/google/android/gms/tagmanager/bh:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   249: aload_0
    //   250: getfield 97	com/google/android/gms/tagmanager/co:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   253: getstatic 192	com/google/android/gms/tagmanager/bg$a:apN	Lcom/google/android/gms/tagmanager/bg$a;
    //   256: invokeinterface 109 2 0
    //   261: aload_1
    //   262: invokeinterface 164 1 0
    //   267: return
    //   268: astore_3
    //   269: new 35	java/lang/StringBuilder
    //   272: dup
    //   273: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   276: ldc -62
    //   278: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: aload_2
    //   282: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: ldc -74
    //   287: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: aload_3
    //   291: invokevirtual 185	java/io/IOException:getMessage	()Ljava/lang/String;
    //   294: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   300: aload_3
    //   301: invokestatic 189	com/google/android/gms/tagmanager/bh:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   304: aload_0
    //   305: getfield 97	com/google/android/gms/tagmanager/co:aqt	Lcom/google/android/gms/tagmanager/bg;
    //   308: getstatic 178	com/google/android/gms/tagmanager/bg$a:apO	Lcom/google/android/gms/tagmanager/bg$a;
    //   311: invokeinterface 109 2 0
    //   316: aload_1
    //   317: invokeinterface 164 1 0
    //   322: return
    //   323: astore_2
    //   324: aload_1
    //   325: invokeinterface 164 1 0
    //   330: aload_2
    //   331: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	332	0	this	co
    //   37	288	1	localbm	bm
    //   29	253	2	str	String
    //   323	8	2	localObject1	Object
    //   45	92	3	localObject2	Object
    //   154	1	3	localFileNotFoundException	java.io.FileNotFoundException
    //   213	33	3	localIOException1	java.io.IOException
    //   268	33	3	localIOException2	java.io.IOException
    //   53	9	4	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   38	46	154	java/io/FileNotFoundException
    //   38	46	213	java/io/IOException
    //   46	132	268	java/io/IOException
    //   132	142	268	java/io/IOException
    //   38	46	323	finally
    //   46	132	323	finally
    //   132	142	323	finally
    //   155	206	323	finally
    //   214	261	323	finally
    //   269	316	323	finally
  }
  
  void a(bg<c.j> parambg)
  {
    this.aqt = parambg;
  }
  
  void cJ(String paramString)
  {
    bh.S("Setting previous container version: " + paramString);
    this.aqv = paramString;
  }
  
  void cu(String paramString)
  {
    if (paramString == null)
    {
      this.aoy = this.aqs;
      return;
    }
    bh.S("Setting CTFE URL path: " + paramString);
    this.aoy = paramString;
  }
  
  String oO()
  {
    Object localObject2 = this.aqu.od() + this.aoy + "&v=a65833898";
    Object localObject1 = localObject2;
    if (this.aqv != null)
    {
      localObject1 = localObject2;
      if (!this.aqv.trim().equals("")) {
        localObject1 = (String)localObject2 + "&pv=" + this.aqv;
      }
    }
    localObject2 = localObject1;
    if (ce.oJ().oK().equals(ce.a.aqj)) {
      localObject2 = (String)localObject1 + "&gtm_debug=x";
    }
    return (String)localObject2;
  }
  
  public void run()
  {
    if (this.aqt == null) {
      throw new IllegalStateException("callback must be set before execute");
    }
    this.aqt.ob();
    oN();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/co.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */