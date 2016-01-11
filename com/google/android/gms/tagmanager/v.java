package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class v
  implements DataLayer.c
{
  private static final String aoQ = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[] { "datalayer", "ID", "key", "value", "expires" });
  private final Executor aoR;
  private a aoS;
  private int aoT;
  private final Context mContext;
  private ju yD;
  
  public v(Context paramContext)
  {
    this(paramContext, jw.hA(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
  }
  
  v(Context paramContext, ju paramju, String paramString, int paramInt, Executor paramExecutor)
  {
    this.mContext = paramContext;
    this.yD = paramju;
    this.aoT = paramInt;
    this.aoR = paramExecutor;
    this.aoS = new a(this.mContext, paramString);
  }
  
  private SQLiteDatabase al(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.aoS.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W(paramString);
    }
    return null;
  }
  
  /* Error */
  private void b(List<b> paramList, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/google/android/gms/tagmanager/v:yD	Lcom/google/android/gms/internal/ju;
    //   6: invokeinterface 121 1 0
    //   11: lstore 4
    //   13: aload_0
    //   14: lload 4
    //   16: invokespecial 125	com/google/android/gms/tagmanager/v:x	(J)V
    //   19: aload_0
    //   20: aload_1
    //   21: invokeinterface 131 1 0
    //   26: invokespecial 135	com/google/android/gms/tagmanager/v:fg	(I)V
    //   29: aload_0
    //   30: aload_1
    //   31: lload 4
    //   33: lload_2
    //   34: ladd
    //   35: invokespecial 138	com/google/android/gms/tagmanager/v:c	(Ljava/util/List;J)V
    //   38: aload_0
    //   39: invokespecial 141	com/google/android/gms/tagmanager/v:ol	()V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: invokespecial 141	com/google/android/gms/tagmanager/v:ol	()V
    //   50: aload_1
    //   51: athrow
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	v
    //   0	57	1	paramList	List<b>
    //   0	57	2	paramLong	long
    //   11	21	4	l	long
    // Exception table:
    //   from	to	target	type
    //   2	38	45	finally
    //   38	42	52	finally
    //   46	52	52	finally
  }
  
  private void c(List<b> paramList, long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for writeEntryToDatabase.");
    if (localSQLiteDatabase == null) {}
    for (;;)
    {
      return;
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        b localb = (b)paramList.next();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("expires", Long.valueOf(paramLong));
        localContentValues.put("key", localb.JO);
        localContentValues.put("value", localb.aoZ);
        if (!(localSQLiteDatabase instanceof SQLiteDatabase)) {
          localSQLiteDatabase.insert("datalayer", null, localContentValues);
        } else {
          SQLiteInstrumentation.insert((SQLiteDatabase)localSQLiteDatabase, "datalayer", null, localContentValues);
        }
      }
    }
  }
  
  /* Error */
  private void cy(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc -56
    //   3: invokespecial 147	com/google/android/gms/tagmanager/v:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull +4 -> 12
    //   11: return
    //   12: iconst_2
    //   13: anewarray 46	java/lang/String
    //   16: astore 4
    //   18: aload 4
    //   20: iconst_0
    //   21: aload_1
    //   22: aastore
    //   23: aload 4
    //   25: iconst_1
    //   26: new 202	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   33: aload_1
    //   34: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: ldc -47
    //   39: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: aastore
    //   46: aload_3
    //   47: instanceof 189
    //   50: ifne +46 -> 96
    //   53: aload_3
    //   54: ldc 36
    //   56: ldc -41
    //   58: aload 4
    //   60: invokevirtual 219	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   63: istore_2
    //   64: new 202	java/lang/StringBuilder
    //   67: dup
    //   68: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   71: ldc -35
    //   73: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: iload_2
    //   77: invokevirtual 224	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   80: ldc -30
    //   82: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: invokestatic 229	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   91: aload_0
    //   92: invokespecial 141	com/google/android/gms/tagmanager/v:ol	()V
    //   95: return
    //   96: aload_3
    //   97: checkcast 189	android/database/sqlite/SQLiteDatabase
    //   100: ldc 36
    //   102: ldc -41
    //   104: aload 4
    //   106: invokestatic 232	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:delete	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   109: istore_2
    //   110: goto -46 -> 64
    //   113: astore_3
    //   114: new 202	java/lang/StringBuilder
    //   117: dup
    //   118: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   121: ldc -22
    //   123: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: aload_1
    //   127: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: ldc -20
    //   132: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_3
    //   136: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   139: ldc -15
    //   141: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokestatic 114	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   150: aload_0
    //   151: invokespecial 141	com/google/android/gms/tagmanager/v:ol	()V
    //   154: return
    //   155: astore_1
    //   156: aload_0
    //   157: invokespecial 141	com/google/android/gms/tagmanager/v:ol	()V
    //   160: aload_1
    //   161: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	this	v
    //   0	162	1	paramString	String
    //   63	47	2	i	int
    //   6	91	3	localSQLiteDatabase	SQLiteDatabase
    //   113	23	3	localSQLiteException	SQLiteException
    //   16	89	4	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   12	18	113	android/database/sqlite/SQLiteException
    //   23	64	113	android/database/sqlite/SQLiteException
    //   64	91	113	android/database/sqlite/SQLiteException
    //   96	110	113	android/database/sqlite/SQLiteException
    //   12	18	155	finally
    //   23	64	155	finally
    //   64	91	155	finally
    //   96	110	155	finally
    //   114	150	155	finally
  }
  
  private void fg(int paramInt)
  {
    paramInt = ok() - this.aoT + paramInt;
    if (paramInt > 0)
    {
      List localList = fh(paramInt);
      bh.U("DataLayer store full, deleting " + localList.size() + " entries to make room.");
      i((String[])localList.toArray(new String[0]));
    }
  }
  
  /* Error */
  private List<String> fh(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 267	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 268	java/util/ArrayList:<init>	()V
    //   10: astore 6
    //   12: iload_1
    //   13: ifgt +12 -> 25
    //   16: ldc_w 270
    //   19: invokestatic 114	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   22: aload 6
    //   24: areturn
    //   25: aload_0
    //   26: ldc_w 272
    //   29: invokespecial 147	com/google/android/gms/tagmanager/v:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   32: astore_3
    //   33: aload_3
    //   34: ifnonnull +6 -> 40
    //   37: aload 6
    //   39: areturn
    //   40: iconst_1
    //   41: anewarray 46	java/lang/String
    //   44: astore 5
    //   46: aload 5
    //   48: iconst_0
    //   49: ldc 38
    //   51: aastore
    //   52: ldc_w 274
    //   55: iconst_1
    //   56: anewarray 4	java/lang/Object
    //   59: dup
    //   60: iconst_0
    //   61: ldc 38
    //   63: aastore
    //   64: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   67: astore 7
    //   69: iload_1
    //   70: invokestatic 279	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   73: astore 8
    //   75: aload_3
    //   76: instanceof 189
    //   79: ifne +80 -> 159
    //   82: aload_3
    //   83: ldc 36
    //   85: aload 5
    //   87: aconst_null
    //   88: aconst_null
    //   89: aconst_null
    //   90: aconst_null
    //   91: aload 7
    //   93: aload 8
    //   95: invokevirtual 283	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   98: astore_3
    //   99: aload_3
    //   100: astore 4
    //   102: aload_3
    //   103: invokeinterface 288 1 0
    //   108: ifeq +38 -> 146
    //   111: aload_3
    //   112: astore 4
    //   114: aload 6
    //   116: aload_3
    //   117: iconst_0
    //   118: invokeinterface 292 2 0
    //   123: invokestatic 295	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   126: invokeinterface 299 2 0
    //   131: pop
    //   132: aload_3
    //   133: astore 4
    //   135: aload_3
    //   136: invokeinterface 302 1 0
    //   141: istore_2
    //   142: iload_2
    //   143: ifne -32 -> 111
    //   146: aload_3
    //   147: ifnull +9 -> 156
    //   150: aload_3
    //   151: invokeinterface 305 1 0
    //   156: aload 6
    //   158: areturn
    //   159: aload_3
    //   160: checkcast 189	android/database/sqlite/SQLiteDatabase
    //   163: ldc 36
    //   165: aload 5
    //   167: aconst_null
    //   168: aconst_null
    //   169: aconst_null
    //   170: aconst_null
    //   171: aload 7
    //   173: aload 8
    //   175: invokestatic 308	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   178: astore_3
    //   179: goto -80 -> 99
    //   182: astore 5
    //   184: aconst_null
    //   185: astore_3
    //   186: aload_3
    //   187: astore 4
    //   189: new 202	java/lang/StringBuilder
    //   192: dup
    //   193: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   196: ldc_w 310
    //   199: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: aload 5
    //   204: invokevirtual 313	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   207: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: invokestatic 114	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   216: aload_3
    //   217: ifnull -61 -> 156
    //   220: aload_3
    //   221: invokeinterface 305 1 0
    //   226: goto -70 -> 156
    //   229: astore_3
    //   230: aload 4
    //   232: ifnull +10 -> 242
    //   235: aload 4
    //   237: invokeinterface 305 1 0
    //   242: aload_3
    //   243: athrow
    //   244: astore_3
    //   245: goto -15 -> 230
    //   248: astore 5
    //   250: goto -64 -> 186
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	253	0	this	v
    //   0	253	1	paramInt	int
    //   141	2	2	bool	boolean
    //   32	189	3	localObject1	Object
    //   229	14	3	localObject2	Object
    //   244	1	3	localObject3	Object
    //   1	235	4	localObject4	Object
    //   44	122	5	arrayOfString	String[]
    //   182	21	5	localSQLiteException1	SQLiteException
    //   248	1	5	localSQLiteException2	SQLiteException
    //   10	147	6	localArrayList	ArrayList
    //   67	105	7	str1	String
    //   73	101	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   40	46	182	android/database/sqlite/SQLiteException
    //   52	99	182	android/database/sqlite/SQLiteException
    //   159	179	182	android/database/sqlite/SQLiteException
    //   40	46	229	finally
    //   52	99	229	finally
    //   159	179	229	finally
    //   102	111	244	finally
    //   114	132	244	finally
    //   135	142	244	finally
    //   189	216	244	finally
    //   102	111	248	android/database/sqlite/SQLiteException
    //   114	132	248	android/database/sqlite/SQLiteException
    //   135	142	248	android/database/sqlite/SQLiteException
  }
  
  private List<DataLayer.a> h(List<b> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      b localb = (b)paramList.next();
      localArrayList.add(new DataLayer.a(localb.JO, j(localb.aoZ)));
    }
    return localArrayList;
  }
  
  private List<b> i(List<DataLayer.a> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      DataLayer.a locala = (DataLayer.a)paramList.next();
      localArrayList.add(new b(locala.JO, m(locala.wq)));
    }
    return localArrayList;
  }
  
  private void i(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return;
      localSQLiteDatabase = al("Error opening database for deleteEntries.");
    } while (localSQLiteDatabase == null);
    String str = String.format("%s in (%s)", new Object[] { "ID", TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?")) });
    try
    {
      if (!(localSQLiteDatabase instanceof SQLiteDatabase))
      {
        localSQLiteDatabase.delete("datalayer", str, paramArrayOfString);
        return;
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W("Error deleting entries " + Arrays.toString(paramArrayOfString));
      return;
    }
    SQLiteInstrumentation.delete((SQLiteDatabase)localSQLiteException, "datalayer", str, paramArrayOfString);
  }
  
  /* Error */
  private Object j(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 371	java/io/ByteArrayInputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 374	java/io/ByteArrayInputStream:<init>	([B)V
    //   8: astore_3
    //   9: new 376	java/io/ObjectInputStream
    //   12: dup
    //   13: aload_3
    //   14: invokespecial 379	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_1
    //   18: aload_1
    //   19: invokevirtual 382	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   22: astore_2
    //   23: aload_1
    //   24: ifnull +7 -> 31
    //   27: aload_1
    //   28: invokevirtual 383	java/io/ObjectInputStream:close	()V
    //   31: aload_3
    //   32: invokevirtual 384	java/io/ByteArrayInputStream:close	()V
    //   35: aload_2
    //   36: areturn
    //   37: astore_1
    //   38: aconst_null
    //   39: astore_1
    //   40: aload_1
    //   41: ifnull +7 -> 48
    //   44: aload_1
    //   45: invokevirtual 383	java/io/ObjectInputStream:close	()V
    //   48: aload_3
    //   49: invokevirtual 384	java/io/ByteArrayInputStream:close	()V
    //   52: aconst_null
    //   53: areturn
    //   54: astore_1
    //   55: aconst_null
    //   56: areturn
    //   57: astore_1
    //   58: aconst_null
    //   59: astore_1
    //   60: aload_1
    //   61: ifnull +7 -> 68
    //   64: aload_1
    //   65: invokevirtual 383	java/io/ObjectInputStream:close	()V
    //   68: aload_3
    //   69: invokevirtual 384	java/io/ByteArrayInputStream:close	()V
    //   72: aconst_null
    //   73: areturn
    //   74: astore_1
    //   75: aconst_null
    //   76: areturn
    //   77: astore_2
    //   78: aconst_null
    //   79: astore_1
    //   80: aload_1
    //   81: ifnull +7 -> 88
    //   84: aload_1
    //   85: invokevirtual 383	java/io/ObjectInputStream:close	()V
    //   88: aload_3
    //   89: invokevirtual 384	java/io/ByteArrayInputStream:close	()V
    //   92: aload_2
    //   93: athrow
    //   94: astore_1
    //   95: goto -3 -> 92
    //   98: astore_2
    //   99: goto -19 -> 80
    //   102: astore_2
    //   103: goto -43 -> 60
    //   106: astore_2
    //   107: goto -67 -> 40
    //   110: astore_1
    //   111: aload_2
    //   112: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	this	v
    //   0	113	1	paramArrayOfByte	byte[]
    //   22	14	2	localObject1	Object
    //   77	16	2	localObject2	Object
    //   98	1	2	localObject3	Object
    //   102	1	2	localClassNotFoundException	ClassNotFoundException
    //   106	6	2	localIOException	java.io.IOException
    //   8	81	3	localByteArrayInputStream	java.io.ByteArrayInputStream
    // Exception table:
    //   from	to	target	type
    //   9	18	37	java/io/IOException
    //   44	48	54	java/io/IOException
    //   48	52	54	java/io/IOException
    //   9	18	57	java/lang/ClassNotFoundException
    //   64	68	74	java/io/IOException
    //   68	72	74	java/io/IOException
    //   9	18	77	finally
    //   84	88	94	java/io/IOException
    //   88	92	94	java/io/IOException
    //   18	23	98	finally
    //   18	23	102	java/lang/ClassNotFoundException
    //   18	23	106	java/io/IOException
    //   27	31	110	java/io/IOException
    //   31	35	110	java/io/IOException
  }
  
  /* Error */
  private byte[] m(Object paramObject)
  {
    // Byte code:
    //   0: new 386	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 387	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_3
    //   8: new 389	java/io/ObjectOutputStream
    //   11: dup
    //   12: aload_3
    //   13: invokespecial 392	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_2
    //   17: aload_2
    //   18: aload_1
    //   19: invokevirtual 396	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   22: aload_3
    //   23: invokevirtual 400	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   26: astore_1
    //   27: aload_2
    //   28: ifnull +7 -> 35
    //   31: aload_2
    //   32: invokevirtual 401	java/io/ObjectOutputStream:close	()V
    //   35: aload_3
    //   36: invokevirtual 402	java/io/ByteArrayOutputStream:close	()V
    //   39: aload_1
    //   40: areturn
    //   41: astore_1
    //   42: aconst_null
    //   43: astore_2
    //   44: aload_2
    //   45: ifnull +7 -> 52
    //   48: aload_2
    //   49: invokevirtual 401	java/io/ObjectOutputStream:close	()V
    //   52: aload_3
    //   53: invokevirtual 402	java/io/ByteArrayOutputStream:close	()V
    //   56: aconst_null
    //   57: areturn
    //   58: astore_1
    //   59: aconst_null
    //   60: areturn
    //   61: astore_1
    //   62: aconst_null
    //   63: astore_2
    //   64: aload_2
    //   65: ifnull +7 -> 72
    //   68: aload_2
    //   69: invokevirtual 401	java/io/ObjectOutputStream:close	()V
    //   72: aload_3
    //   73: invokevirtual 402	java/io/ByteArrayOutputStream:close	()V
    //   76: aload_1
    //   77: athrow
    //   78: astore_2
    //   79: goto -3 -> 76
    //   82: astore_1
    //   83: goto -19 -> 64
    //   86: astore_1
    //   87: goto -43 -> 44
    //   90: astore_2
    //   91: aload_1
    //   92: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	v
    //   0	93	1	paramObject	Object
    //   16	53	2	localObjectOutputStream	java.io.ObjectOutputStream
    //   78	1	2	localIOException1	java.io.IOException
    //   90	1	2	localIOException2	java.io.IOException
    //   7	66	3	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   8	17	41	java/io/IOException
    //   48	52	58	java/io/IOException
    //   52	56	58	java/io/IOException
    //   8	17	61	finally
    //   68	72	78	java/io/IOException
    //   72	76	78	java/io/IOException
    //   17	27	82	finally
    //   17	27	86	java/io/IOException
    //   31	35	90	java/io/IOException
    //   35	39	90	java/io/IOException
  }
  
  private List<DataLayer.a> oi()
  {
    try
    {
      x(this.yD.currentTimeMillis());
      List localList = h(oj());
      return localList;
    }
    finally
    {
      ol();
    }
  }
  
  private List<b> oj()
  {
    Object localObject = al("Error opening database for loadSerialized.");
    ArrayList localArrayList = new ArrayList();
    if (localObject == null) {
      return localArrayList;
    }
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "key";
    arrayOfString[1] = "value";
    if (!(localObject instanceof SQLiteDatabase)) {
      localObject = ((SQLiteDatabase)localObject).query("datalayer", arrayOfString, null, null, null, null, "ID", null);
    }
    try
    {
      while (((Cursor)localObject).moveToNext()) {
        localArrayList.add(new b(((Cursor)localObject).getString(0), ((Cursor)localObject).getBlob(1)));
      }
    }
    finally
    {
      ((Cursor)localObject).close();
    }
    ((Cursor)localObject).close();
    return localList;
  }
  
  private int ok()
  {
    Cursor localCursor = null;
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for getNumStoredEntries.");
    if (localSQLiteDatabase == null) {
      return 0;
    }
    localObject2 = localCursor;
    for (;;)
    {
      try
      {
        if (!(localSQLiteDatabase instanceof SQLiteDatabase))
        {
          localObject2 = localCursor;
          localCursor = localSQLiteDatabase.rawQuery("SELECT COUNT(*) from datalayer", null);
          localObject2 = localCursor;
        }
      }
      catch (SQLiteException localSQLiteException1)
      {
        long l;
        localObject1 = null;
      }
      finally
      {
        try
        {
          bh.W("Error getting numStoredEntries");
          if (localObject1 == null) {
            break label178;
          }
          ((Cursor)localObject1).close();
          j = 0;
          continue;
        }
        finally {}
        localObject4 = finally;
        Object localObject1 = localObject2;
        localObject2 = localObject4;
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
      }
      try
      {
        if (!localCursor.moveToFirst()) {
          break label183;
        }
        localObject2 = localCursor;
        l = localCursor.getLong(0);
        i = (int)l;
      }
      catch (SQLiteException localSQLiteException2)
      {
        continue;
        j = 0;
        continue;
        i = 0;
        continue;
      }
      j = i;
      if (localCursor != null)
      {
        localCursor.close();
        j = i;
      }
      return j;
      localObject2 = localCursor;
      localCursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)localSQLiteDatabase, "SELECT COUNT(*) from datalayer", null);
    }
  }
  
  private void ol()
  {
    try
    {
      this.aoS.close();
      return;
    }
    catch (SQLiteException localSQLiteException) {}
  }
  
  private void x(long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for deleteOlderThan.");
    if (localSQLiteDatabase == null) {
      return;
    }
    for (;;)
    {
      String[] arrayOfString;
      try
      {
        arrayOfString = new String[1];
        arrayOfString[0] = Long.toString(paramLong);
        if (!(localSQLiteDatabase instanceof SQLiteDatabase))
        {
          i = localSQLiteDatabase.delete("datalayer", "expires <= ?", arrayOfString);
          bh.V("Deleted " + i + " expired items");
          return;
        }
      }
      catch (SQLiteException localSQLiteException)
      {
        bh.W("Error deleting old entries.");
        return;
      }
      int i = SQLiteInstrumentation.delete((SQLiteDatabase)localSQLiteException, "datalayer", "expires <= ?", arrayOfString);
    }
  }
  
  public void a(final DataLayer.c.a parama)
  {
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        parama.g(v.a(v.this));
      }
    });
  }
  
  public void a(final List<DataLayer.a> paramList, final long paramLong)
  {
    paramList = i(paramList);
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        v.a(v.this, paramList, paramLong);
      }
    });
  }
  
  public void cx(final String paramString)
  {
    this.aoR.execute(new Runnable()
    {
      public void run()
      {
        v.a(v.this, paramString);
      }
    });
  }
  
  class a
    extends SQLiteOpenHelper
  {
    a(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      if (!(paramSQLiteDatabase instanceof SQLiteDatabase)) {
        paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
      }
      for (;;)
      {
        HashSet localHashSet = new HashSet();
        try
        {
          String[] arrayOfString = paramSQLiteDatabase.getColumnNames();
          int i = 0;
          for (;;)
          {
            if (i < arrayOfString.length)
            {
              localHashSet.add(arrayOfString[i]);
              i += 1;
              continue;
              paramSQLiteDatabase = SQLiteInstrumentation.rawQuery((SQLiteDatabase)paramSQLiteDatabase, "SELECT * FROM datalayer WHERE 0", null);
              break;
            }
          }
          paramSQLiteDatabase.close();
          if ((!localHashSet.remove("key")) || (!localHashSet.remove("value")) || (!localHashSet.remove("ID")) || (!localHashSet.remove("expires"))) {
            throw new SQLiteException("Database column missing");
          }
        }
        finally
        {
          paramSQLiteDatabase.close();
        }
      }
      if (!((Set)localObject).isEmpty()) {
        throw new SQLiteException("Database has extra columns");
      }
    }
    
    /* Error */
    private boolean a(String paramString, SQLiteDatabase paramSQLiteDatabase)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: iconst_1
      //   4: anewarray 78	java/lang/String
      //   7: astore 5
      //   9: aload 5
      //   11: iconst_0
      //   12: ldc 80
      //   14: aastore
      //   15: iconst_1
      //   16: anewarray 78	java/lang/String
      //   19: astore 6
      //   21: aload 6
      //   23: iconst_0
      //   24: aload_1
      //   25: aastore
      //   26: aload_2
      //   27: instanceof 20
      //   30: ifne +38 -> 68
      //   33: aload_2
      //   34: ldc 82
      //   36: aload 5
      //   38: ldc 84
      //   40: aload 6
      //   42: aconst_null
      //   43: aconst_null
      //   44: aconst_null
      //   45: invokevirtual 88	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   48: astore_2
      //   49: aload_2
      //   50: invokeinterface 91 1 0
      //   55: istore_3
      //   56: aload_2
      //   57: ifnull +9 -> 66
      //   60: aload_2
      //   61: invokeinterface 51 1 0
      //   66: iload_3
      //   67: ireturn
      //   68: aload_2
      //   69: checkcast 20	android/database/sqlite/SQLiteDatabase
      //   72: ldc 82
      //   74: aload 5
      //   76: ldc 84
      //   78: aload 6
      //   80: aconst_null
      //   81: aconst_null
      //   82: aconst_null
      //   83: invokestatic 94	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   86: astore_2
      //   87: goto -38 -> 49
      //   90: astore_2
      //   91: aconst_null
      //   92: astore_2
      //   93: new 96	java/lang/StringBuilder
      //   96: dup
      //   97: invokespecial 97	java/lang/StringBuilder:<init>	()V
      //   100: ldc 99
      //   102: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   105: aload_1
      //   106: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   109: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   112: invokestatic 112	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
      //   115: aload_2
      //   116: ifnull +9 -> 125
      //   119: aload_2
      //   120: invokeinterface 51 1 0
      //   125: iconst_0
      //   126: ireturn
      //   127: astore_1
      //   128: aload 4
      //   130: astore_2
      //   131: aload_2
      //   132: ifnull +9 -> 141
      //   135: aload_2
      //   136: invokeinterface 51 1 0
      //   141: aload_1
      //   142: athrow
      //   143: astore_1
      //   144: goto -13 -> 131
      //   147: astore_1
      //   148: goto -17 -> 131
      //   151: astore 4
      //   153: goto -60 -> 93
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	156	0	this	a
      //   0	156	1	paramString	String
      //   0	156	2	paramSQLiteDatabase	SQLiteDatabase
      //   55	12	3	bool	boolean
      //   1	128	4	localObject	Object
      //   151	1	4	localSQLiteException	SQLiteException
      //   7	68	5	arrayOfString1	String[]
      //   19	60	6	arrayOfString2	String[]
      // Exception table:
      //   from	to	target	type
      //   3	9	90	android/database/sqlite/SQLiteException
      //   15	21	90	android/database/sqlite/SQLiteException
      //   26	49	90	android/database/sqlite/SQLiteException
      //   68	87	90	android/database/sqlite/SQLiteException
      //   3	9	127	finally
      //   15	21	127	finally
      //   26	49	127	finally
      //   68	87	127	finally
      //   49	56	143	finally
      //   93	115	147	finally
      //   49	56	151	android/database/sqlite/SQLiteException
    }
    
    public SQLiteDatabase getWritableDatabase()
    {
      Object localObject1 = null;
      try
      {
        localObject2 = super.getWritableDatabase();
        localObject1 = localObject2;
      }
      catch (SQLiteException localSQLiteException)
      {
        for (;;)
        {
          Object localObject2;
          v.b(v.this).getDatabasePath("google_tagmanager.db").delete();
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = super.getWritableDatabase();
      }
      return (SQLiteDatabase)localObject2;
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      ak.ag(paramSQLiteDatabase.getPath());
    }
    
    public void onOpen(SQLiteDatabase paramSQLiteDatabase)
    {
      if (Build.VERSION.SDK_INT < 15) {
        if ((paramSQLiteDatabase instanceof SQLiteDatabase)) {
          break label63;
        }
      }
      for (Object localObject = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);; localObject = SQLiteInstrumentation.rawQuery((SQLiteDatabase)paramSQLiteDatabase, "PRAGMA journal_mode=memory", null)) {
        try
        {
          ((Cursor)localObject).moveToFirst();
          ((Cursor)localObject).close();
          if (a("datalayer", paramSQLiteDatabase)) {
            break label95;
          }
          localObject = v.om();
          if ((paramSQLiteDatabase instanceof SQLiteDatabase)) {
            break;
          }
          return;
        }
        finally
        {
          label63:
          ((Cursor)localObject).close();
        }
      }
      return;
      label95:
      a(paramSQLiteDatabase);
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  }
  
  private static class b
  {
    final String JO;
    final byte[] aoZ;
    
    b(String paramString, byte[] paramArrayOfByte)
    {
      this.JO = paramString;
      this.aoZ = paramArrayOfByte;
    }
    
    public String toString()
    {
      return "KeyAndSerialized: key = " + this.JO + " serialized hash = " + Arrays.hashCode(this.aoZ);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */