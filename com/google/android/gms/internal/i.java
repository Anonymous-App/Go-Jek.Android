package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public abstract class i
  extends h
{
  private static Method kA;
  private static Method kB;
  private static Method kC;
  private static Method kD;
  private static Method kE;
  private static Method kF;
  private static Method kG;
  private static Method kH;
  private static Method kI;
  private static String kJ;
  private static String kK;
  private static String kL;
  private static o kM;
  static boolean kN = false;
  private static long startTime = 0L;
  
  protected i(Context paramContext, m paramm, n paramn)
  {
    super(paramContext, paramm, paramn);
  }
  
  static String a(Context paramContext, m paramm)
    throws i.a
  {
    if (kK != null) {
      return kK;
    }
    if (kD == null) {
      throw new a();
    }
    try
    {
      paramContext = (ByteBuffer)kD.invoke(null, new Object[] { paramContext });
      if (paramContext == null) {
        throw new a();
      }
    }
    catch (IllegalAccessException paramContext)
    {
      throw new a(paramContext);
      kK = paramm.a(paramContext.array(), true);
      paramContext = kK;
      return paramContext;
    }
    catch (InvocationTargetException paramContext)
    {
      throw new a(paramContext);
    }
  }
  
  static ArrayList<Long> a(MotionEvent paramMotionEvent, DisplayMetrics paramDisplayMetrics)
    throws i.a
  {
    if ((kE == null) || (paramMotionEvent == null)) {
      throw new a();
    }
    try
    {
      paramMotionEvent = (ArrayList)kE.invoke(null, new Object[] { paramMotionEvent, paramDisplayMetrics });
      return paramMotionEvent;
    }
    catch (IllegalAccessException paramMotionEvent)
    {
      throw new a(paramMotionEvent);
    }
    catch (InvocationTargetException paramMotionEvent)
    {
      throw new a(paramMotionEvent);
    }
  }
  
  /* Error */
  protected static void a(String paramString, Context paramContext, m paramm)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 33	com/google/android/gms/internal/i:kN	Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +36 -> 44
    //   11: new 84	com/google/android/gms/internal/o
    //   14: dup
    //   15: aload_2
    //   16: aconst_null
    //   17: invokespecial 87	com/google/android/gms/internal/o:<init>	(Lcom/google/android/gms/internal/m;Ljava/security/SecureRandom;)V
    //   20: putstatic 89	com/google/android/gms/internal/i:kM	Lcom/google/android/gms/internal/o;
    //   23: aload_0
    //   24: putstatic 91	com/google/android/gms/internal/i:kJ	Ljava/lang/String;
    //   27: aload_1
    //   28: invokestatic 95	com/google/android/gms/internal/i:g	(Landroid/content/Context;)V
    //   31: invokestatic 99	com/google/android/gms/internal/i:w	()Ljava/lang/Long;
    //   34: invokevirtual 105	java/lang/Long:longValue	()J
    //   37: putstatic 31	com/google/android/gms/internal/i:startTime	J
    //   40: iconst_1
    //   41: putstatic 33	com/google/android/gms/internal/i:kN	Z
    //   44: ldc 2
    //   46: monitorexit
    //   47: return
    //   48: astore_0
    //   49: ldc 2
    //   51: monitorexit
    //   52: aload_0
    //   53: athrow
    //   54: astore_0
    //   55: goto -11 -> 44
    //   58: astore_0
    //   59: goto -15 -> 44
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	paramString	String
    //   0	62	1	paramContext	Context
    //   0	62	2	paramm	m
    //   6	2	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	7	48	finally
    //   11	44	48	finally
    //   11	44	54	java/lang/UnsupportedOperationException
    //   11	44	58	com/google/android/gms/internal/i$a
  }
  
  static String b(Context paramContext, m paramm)
    throws i.a
  {
    if (kL != null) {
      return kL;
    }
    if (kG == null) {
      throw new a();
    }
    try
    {
      paramContext = (ByteBuffer)kG.invoke(null, new Object[] { paramContext });
      if (paramContext == null) {
        throw new a();
      }
    }
    catch (IllegalAccessException paramContext)
    {
      throw new a(paramContext);
      kL = paramm.a(paramContext.array(), true);
      paramContext = kL;
      return paramContext;
    }
    catch (InvocationTargetException paramContext)
    {
      throw new a(paramContext);
    }
  }
  
  private static String b(byte[] paramArrayOfByte, String paramString)
    throws i.a
  {
    try
    {
      paramArrayOfByte = new String(kM.c(paramArrayOfByte, paramString), "UTF-8");
      return paramArrayOfByte;
    }
    catch (o.a paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      throw new a(paramArrayOfByte);
    }
  }
  
  static String d(Context paramContext)
    throws i.a
  {
    if (kF == null) {
      throw new a();
    }
    try
    {
      paramContext = (String)kF.invoke(null, new Object[] { paramContext });
      if (paramContext == null) {
        throw new a();
      }
    }
    catch (IllegalAccessException paramContext)
    {
      throw new a(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new a(paramContext);
    }
    return paramContext;
  }
  
  static ArrayList<Long> e(Context paramContext)
    throws i.a
  {
    if (kH == null) {
      throw new a();
    }
    try
    {
      paramContext = (ArrayList)kH.invoke(null, new Object[] { paramContext });
      if ((paramContext == null) || (paramContext.size() != 2)) {
        throw new a();
      }
    }
    catch (IllegalAccessException paramContext)
    {
      throw new a(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new a(paramContext);
    }
    return paramContext;
  }
  
  static int[] f(Context paramContext)
    throws i.a
  {
    if (kI == null) {
      throw new a();
    }
    try
    {
      paramContext = (int[])kI.invoke(null, new Object[] { paramContext });
      return paramContext;
    }
    catch (IllegalAccessException paramContext)
    {
      throw new a(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new a(paramContext);
    }
  }
  
  private static void g(Context paramContext)
    throws i.a
  {
    try
    {
      localObject1 = kM.b(q.getKey());
      localObject2 = kM.c((byte[])localObject1, q.B());
      localFile2 = paramContext.getCacheDir();
      localFile1 = localFile2;
      if (localFile2 == null)
      {
        localFile2 = paramContext.getDir("dex", 0);
        localFile1 = localFile2;
        if (localFile2 == null) {
          throw new a();
        }
      }
    }
    catch (FileNotFoundException paramContext)
    {
      Object localObject1;
      Object localObject2;
      File localFile1;
      throw new a(paramContext);
      File localFile2 = File.createTempFile("ads", ".jar", localFile1);
      Object localObject3 = new FileOutputStream(localFile2);
      ((FileOutputStream)localObject3).write((byte[])localObject2, 0, localObject2.length);
      ((FileOutputStream)localObject3).close();
      try
      {
        Object localObject4 = new DexClassLoader(localFile2.getAbsolutePath(), localFile1.getAbsolutePath(), null, paramContext.getClassLoader());
        paramContext = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.E()));
        localObject2 = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.Q()));
        localObject3 = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.K()));
        Class localClass1 = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.I()));
        Class localClass2 = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.S()));
        Class localClass3 = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.G()));
        Class localClass4 = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.O()));
        Class localClass5 = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.M()));
        localObject4 = ((DexClassLoader)localObject4).loadClass(b((byte[])localObject1, q.C()));
        kA = paramContext.getMethod(b((byte[])localObject1, q.F()), new Class[0]);
        kB = ((Class)localObject2).getMethod(b((byte[])localObject1, q.R()), new Class[0]);
        kC = ((Class)localObject3).getMethod(b((byte[])localObject1, q.L()), new Class[0]);
        kD = localClass1.getMethod(b((byte[])localObject1, q.J()), new Class[] { Context.class });
        kE = localClass2.getMethod(b((byte[])localObject1, q.T()), new Class[] { MotionEvent.class, DisplayMetrics.class });
        kF = localClass3.getMethod(b((byte[])localObject1, q.H()), new Class[] { Context.class });
        kG = localClass4.getMethod(b((byte[])localObject1, q.P()), new Class[] { Context.class });
        kH = localClass5.getMethod(b((byte[])localObject1, q.N()), new Class[] { Context.class });
        kI = ((Class)localObject4).getMethod(b((byte[])localObject1, q.D()), new Class[] { Context.class });
        return;
      }
      finally
      {
        localObject1 = localFile2.getName();
        localFile2.delete();
        new File(localFile1, ((String)localObject1).replace(".jar", ".dex")).delete();
      }
    }
    catch (IOException paramContext)
    {
      throw new a(paramContext);
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new a(paramContext);
    }
    catch (o.a paramContext)
    {
      throw new a(paramContext);
    }
    catch (NoSuchMethodException paramContext)
    {
      throw new a(paramContext);
    }
    catch (NullPointerException paramContext)
    {
      throw new a(paramContext);
    }
  }
  
  static String v()
    throws i.a
  {
    if (kJ == null) {
      throw new a();
    }
    return kJ;
  }
  
  static Long w()
    throws i.a
  {
    if (kA == null) {
      throw new a();
    }
    try
    {
      Long localLong = (Long)kA.invoke(null, new Object[0]);
      return localLong;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  static String x()
    throws i.a
  {
    if (kC == null) {
      throw new a();
    }
    try
    {
      String str = (String)kC.invoke(null, new Object[0]);
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  static Long y()
    throws i.a
  {
    if (kB == null) {
      throw new a();
    }
    try
    {
      Long localLong = (Long)kB.invoke(null, new Object[0]);
      return localLong;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }
  
  /* Error */
  protected void b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: invokestatic 309	com/google/android/gms/internal/i:x	()Ljava/lang/String;
    //   5: invokevirtual 312	com/google/android/gms/internal/i:a	(ILjava/lang/String;)V
    //   8: aload_0
    //   9: iconst_2
    //   10: invokestatic 314	com/google/android/gms/internal/i:v	()Ljava/lang/String;
    //   13: invokevirtual 312	com/google/android/gms/internal/i:a	(ILjava/lang/String;)V
    //   16: invokestatic 99	com/google/android/gms/internal/i:w	()Ljava/lang/Long;
    //   19: invokevirtual 105	java/lang/Long:longValue	()J
    //   22: lstore_2
    //   23: aload_0
    //   24: bipush 25
    //   26: lload_2
    //   27: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   30: getstatic 31	com/google/android/gms/internal/i:startTime	J
    //   33: lconst_0
    //   34: lcmp
    //   35: ifeq +23 -> 58
    //   38: aload_0
    //   39: bipush 17
    //   41: lload_2
    //   42: getstatic 31	com/google/android/gms/internal/i:startTime	J
    //   45: lsub
    //   46: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   49: aload_0
    //   50: bipush 23
    //   52: getstatic 31	com/google/android/gms/internal/i:startTime	J
    //   55: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   58: aload_1
    //   59: invokestatic 319	com/google/android/gms/internal/i:e	(Landroid/content/Context;)Ljava/util/ArrayList;
    //   62: astore 4
    //   64: aload_0
    //   65: bipush 31
    //   67: aload 4
    //   69: iconst_0
    //   70: invokevirtual 323	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   73: checkcast 101	java/lang/Long
    //   76: invokevirtual 105	java/lang/Long:longValue	()J
    //   79: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   82: aload_0
    //   83: bipush 32
    //   85: aload 4
    //   87: iconst_1
    //   88: invokevirtual 323	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   91: checkcast 101	java/lang/Long
    //   94: invokevirtual 105	java/lang/Long:longValue	()J
    //   97: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   100: aload_0
    //   101: bipush 33
    //   103: invokestatic 325	com/google/android/gms/internal/i:y	()Ljava/lang/Long;
    //   106: invokevirtual 105	java/lang/Long:longValue	()J
    //   109: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   112: aload_0
    //   113: bipush 27
    //   115: aload_1
    //   116: aload_0
    //   117: getfield 329	com/google/android/gms/internal/i:ky	Lcom/google/android/gms/internal/m;
    //   120: invokestatic 331	com/google/android/gms/internal/i:a	(Landroid/content/Context;Lcom/google/android/gms/internal/m;)Ljava/lang/String;
    //   123: invokevirtual 312	com/google/android/gms/internal/i:a	(ILjava/lang/String;)V
    //   126: aload_0
    //   127: bipush 29
    //   129: aload_1
    //   130: aload_0
    //   131: getfield 329	com/google/android/gms/internal/i:ky	Lcom/google/android/gms/internal/m;
    //   134: invokestatic 333	com/google/android/gms/internal/i:b	(Landroid/content/Context;Lcom/google/android/gms/internal/m;)Ljava/lang/String;
    //   137: invokevirtual 312	com/google/android/gms/internal/i:a	(ILjava/lang/String;)V
    //   140: aload_1
    //   141: invokestatic 335	com/google/android/gms/internal/i:f	(Landroid/content/Context;)[I
    //   144: astore_1
    //   145: aload_0
    //   146: iconst_5
    //   147: aload_1
    //   148: iconst_0
    //   149: iaload
    //   150: i2l
    //   151: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   154: aload_0
    //   155: bipush 6
    //   157: aload_1
    //   158: iconst_1
    //   159: iaload
    //   160: i2l
    //   161: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   164: return
    //   165: astore_1
    //   166: return
    //   167: astore_1
    //   168: return
    //   169: astore 4
    //   171: goto -31 -> 140
    //   174: astore 4
    //   176: goto -50 -> 126
    //   179: astore 4
    //   181: goto -69 -> 112
    //   184: astore 4
    //   186: goto -86 -> 100
    //   189: astore 4
    //   191: goto -133 -> 58
    //   194: astore 4
    //   196: goto -180 -> 16
    //   199: astore 4
    //   201: goto -193 -> 8
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	204	0	this	i
    //   0	204	1	paramContext	Context
    //   22	20	2	l	long
    //   62	24	4	localArrayList	ArrayList
    //   169	1	4	locala1	a
    //   174	1	4	locala2	a
    //   179	1	4	locala3	a
    //   184	1	4	locala4	a
    //   189	1	4	locala5	a
    //   194	1	4	locala6	a
    //   199	1	4	locala7	a
    // Exception table:
    //   from	to	target	type
    //   0	8	165	java/io/IOException
    //   8	16	165	java/io/IOException
    //   16	58	165	java/io/IOException
    //   58	100	165	java/io/IOException
    //   100	112	165	java/io/IOException
    //   112	126	165	java/io/IOException
    //   126	140	165	java/io/IOException
    //   140	164	165	java/io/IOException
    //   140	164	167	com/google/android/gms/internal/i$a
    //   126	140	169	com/google/android/gms/internal/i$a
    //   112	126	174	com/google/android/gms/internal/i$a
    //   100	112	179	com/google/android/gms/internal/i$a
    //   58	100	184	com/google/android/gms/internal/i$a
    //   16	58	189	com/google/android/gms/internal/i$a
    //   8	16	194	com/google/android/gms/internal/i$a
    //   0	8	199	com/google/android/gms/internal/i$a
  }
  
  /* Error */
  protected void c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_2
    //   2: invokestatic 314	com/google/android/gms/internal/i:v	()Ljava/lang/String;
    //   5: invokevirtual 312	com/google/android/gms/internal/i:a	(ILjava/lang/String;)V
    //   8: aload_0
    //   9: iconst_1
    //   10: invokestatic 309	com/google/android/gms/internal/i:x	()Ljava/lang/String;
    //   13: invokevirtual 312	com/google/android/gms/internal/i:a	(ILjava/lang/String;)V
    //   16: aload_0
    //   17: bipush 25
    //   19: invokestatic 99	com/google/android/gms/internal/i:w	()Ljava/lang/Long;
    //   22: invokevirtual 105	java/lang/Long:longValue	()J
    //   25: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   28: aload_0
    //   29: getfield 339	com/google/android/gms/internal/i:kw	Landroid/view/MotionEvent;
    //   32: aload_0
    //   33: getfield 343	com/google/android/gms/internal/i:kx	Landroid/util/DisplayMetrics;
    //   36: invokestatic 345	com/google/android/gms/internal/i:a	(Landroid/view/MotionEvent;Landroid/util/DisplayMetrics;)Ljava/util/ArrayList;
    //   39: astore_1
    //   40: aload_0
    //   41: bipush 14
    //   43: aload_1
    //   44: iconst_0
    //   45: invokevirtual 323	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   48: checkcast 101	java/lang/Long
    //   51: invokevirtual 105	java/lang/Long:longValue	()J
    //   54: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   57: aload_0
    //   58: bipush 15
    //   60: aload_1
    //   61: iconst_1
    //   62: invokevirtual 323	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   65: checkcast 101	java/lang/Long
    //   68: invokevirtual 105	java/lang/Long:longValue	()J
    //   71: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   74: aload_1
    //   75: invokevirtual 138	java/util/ArrayList:size	()I
    //   78: iconst_3
    //   79: if_icmplt +20 -> 99
    //   82: aload_0
    //   83: bipush 16
    //   85: aload_1
    //   86: iconst_2
    //   87: invokevirtual 323	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   90: checkcast 101	java/lang/Long
    //   93: invokevirtual 105	java/lang/Long:longValue	()J
    //   96: invokevirtual 317	com/google/android/gms/internal/i:a	(IJ)V
    //   99: return
    //   100: astore_1
    //   101: return
    //   102: astore_1
    //   103: return
    //   104: astore_1
    //   105: goto -77 -> 28
    //   108: astore_1
    //   109: goto -93 -> 16
    //   112: astore_1
    //   113: goto -105 -> 8
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	this	i
    //   0	116	1	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   0	8	100	java/io/IOException
    //   8	16	100	java/io/IOException
    //   16	28	100	java/io/IOException
    //   28	99	100	java/io/IOException
    //   28	99	102	com/google/android/gms/internal/i$a
    //   16	28	104	com/google/android/gms/internal/i$a
    //   8	16	108	com/google/android/gms/internal/i$a
    //   0	8	112	com/google/android/gms/internal/i$a
  }
  
  static class a
    extends Exception
  {
    public a() {}
    
    public a(Throwable paramThrowable)
    {
      super();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */