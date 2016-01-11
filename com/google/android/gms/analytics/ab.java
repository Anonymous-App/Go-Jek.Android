package com.google.android.gms.analytics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jw;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.impl.client.DefaultHttpClient;

class ab
  implements d
{
  private static final String AY = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[] { "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id" });
  private final a AZ;
  private volatile m Ba;
  private final String Bb;
  private aa Bc;
  private long Bd;
  private final int Be;
  private final Context mContext;
  private ju yD;
  private final e yl;
  
  ab(e parame, Context paramContext)
  {
    this(parame, paramContext, "google_analytics_v4.db", 2000);
  }
  
  ab(e parame, Context paramContext, String paramString, int paramInt)
  {
    this.mContext = paramContext.getApplicationContext();
    this.Bb = paramString;
    this.yl = parame;
    this.yD = jw.hA();
    this.AZ = new a(this.mContext, this.Bb);
    this.Ba = new ag(new DefaultHttpClient(), this.mContext);
    this.Bd = 0L;
    this.Be = paramInt;
  }
  
  static String A(Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localArrayList.add(x.encode((String)localEntry.getKey()) + "=" + x.encode((String)localEntry.getValue()));
    }
    return TextUtils.join("&", localArrayList);
  }
  
  private void a(Map<String, String> paramMap, long paramLong, String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for putHit");
    if (localSQLiteDatabase == null) {
      return;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("hit_string", A(paramMap));
    localContentValues.put("hit_time", Long.valueOf(paramLong));
    long l = 0L;
    paramLong = l;
    if (paramMap.containsKey("AppUID")) {}
    try
    {
      paramLong = Long.parseLong((String)paramMap.get("AppUID"));
      localContentValues.put("hit_app_id", Long.valueOf(paramLong));
      paramMap = paramString;
      if (paramString == null) {
        paramMap = "http://www.google-analytics.com/collect";
      }
      if (paramMap.length() == 0)
      {
        z.W("Empty path: not sending hit");
        return;
      }
      localContentValues.put("hit_url", paramMap);
      for (;;)
      {
        try
        {
          if (!(localSQLiteDatabase instanceof SQLiteDatabase))
          {
            localSQLiteDatabase.insert("hits2", null, localContentValues);
            this.yl.z(false);
            return;
          }
        }
        catch (SQLiteException paramMap)
        {
          z.W("Error storing hit");
          return;
        }
        SQLiteInstrumentation.insert((SQLiteDatabase)localSQLiteDatabase, "hits2", null, localContentValues);
      }
    }
    catch (NumberFormatException paramMap)
    {
      for (;;)
      {
        paramLong = l;
      }
    }
  }
  
  private void a(Map<String, String> paramMap, Collection<hb> paramCollection)
  {
    String str = "&_v".substring(1);
    if (paramCollection != null)
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        hb localhb = (hb)paramCollection.next();
        if ("appendVersion".equals(localhb.getId())) {
          paramMap.put(str, localhb.getValue());
        }
      }
    }
  }
  
  private SQLiteDatabase al(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.AZ.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      z.W(paramString);
    }
    return null;
  }
  
  private void eM()
  {
    int i = eO() - this.Be + 1;
    if (i > 0)
    {
      List localList = F(i);
      z.V("Store full, deleting " + localList.size() + " hits to make room.");
      b((String[])localList.toArray(new String[0]));
    }
  }
  
  /* Error */
  List<String> F(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 105	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 317	java/util/ArrayList:<init>	()V
    //   10: astore 6
    //   12: iload_1
    //   13: ifgt +12 -> 25
    //   16: ldc_w 319
    //   19: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   22: aload 6
    //   24: areturn
    //   25: aload_0
    //   26: ldc_w 321
    //   29: invokespecial 189	com/google/android/gms/analytics/ab:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
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
    //   49: ldc 36
    //   51: aastore
    //   52: ldc_w 323
    //   55: iconst_1
    //   56: anewarray 4	java/lang/Object
    //   59: dup
    //   60: iconst_0
    //   61: ldc 36
    //   63: aastore
    //   64: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   67: astore 7
    //   69: iload_1
    //   70: invokestatic 327	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   73: astore 8
    //   75: aload_3
    //   76: instanceof 235
    //   79: ifne +80 -> 159
    //   82: aload_3
    //   83: ldc 34
    //   85: aload 5
    //   87: aconst_null
    //   88: aconst_null
    //   89: aconst_null
    //   90: aconst_null
    //   91: aload 7
    //   93: aload 8
    //   95: invokevirtual 331	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   98: astore_3
    //   99: aload_3
    //   100: astore 4
    //   102: aload_3
    //   103: invokeinterface 336 1 0
    //   108: ifeq +38 -> 146
    //   111: aload_3
    //   112: astore 4
    //   114: aload 6
    //   116: aload_3
    //   117: iconst_0
    //   118: invokeinterface 340 2 0
    //   123: invokestatic 343	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   126: invokeinterface 167 2 0
    //   131: pop
    //   132: aload_3
    //   133: astore 4
    //   135: aload_3
    //   136: invokeinterface 346 1 0
    //   141: istore_2
    //   142: iload_2
    //   143: ifne -32 -> 111
    //   146: aload_3
    //   147: ifnull +9 -> 156
    //   150: aload_3
    //   151: invokeinterface 349 1 0
    //   156: aload 6
    //   158: areturn
    //   159: aload_3
    //   160: checkcast 235	android/database/sqlite/SQLiteDatabase
    //   163: ldc 34
    //   165: aload 5
    //   167: aconst_null
    //   168: aconst_null
    //   169: aconst_null
    //   170: aconst_null
    //   171: aload 7
    //   173: aload 8
    //   175: invokestatic 352	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   178: astore_3
    //   179: goto -80 -> 99
    //   182: astore 5
    //   184: aconst_null
    //   185: astore_3
    //   186: aload_3
    //   187: astore 4
    //   189: new 138	java/lang/StringBuilder
    //   192: dup
    //   193: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   196: ldc_w 354
    //   199: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: aload 5
    //   204: invokevirtual 357	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   207: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   216: aload_3
    //   217: ifnull -61 -> 156
    //   220: aload_3
    //   221: invokeinterface 349 1 0
    //   226: goto -70 -> 156
    //   229: astore_3
    //   230: aload 4
    //   232: ifnull +10 -> 242
    //   235: aload 4
    //   237: invokeinterface 349 1 0
    //   242: aload_3
    //   243: athrow
    //   244: astore_3
    //   245: goto -15 -> 230
    //   248: astore 5
    //   250: goto -64 -> 186
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	253	0	this	ab
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
  
  /* Error */
  public List<w> G(int paramInt)
  {
    // Byte code:
    //   0: new 105	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 317	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: ldc_w 361
    //   13: invokespecial 189	com/google/android/gms/analytics/ab:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: astore 8
    //   18: aload 8
    //   20: ifnonnull +10 -> 30
    //   23: aload 5
    //   25: astore 6
    //   27: aload 6
    //   29: areturn
    //   30: aconst_null
    //   31: astore 4
    //   33: iconst_2
    //   34: anewarray 46	java/lang/String
    //   37: astore 6
    //   39: aload 6
    //   41: iconst_0
    //   42: ldc 36
    //   44: aastore
    //   45: aload 6
    //   47: iconst_1
    //   48: ldc 38
    //   50: aastore
    //   51: ldc_w 323
    //   54: iconst_1
    //   55: anewarray 4	java/lang/Object
    //   58: dup
    //   59: iconst_0
    //   60: ldc 36
    //   62: aastore
    //   63: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   66: astore 7
    //   68: iload_1
    //   69: invokestatic 327	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   72: astore 9
    //   74: aload 8
    //   76: instanceof 235
    //   79: ifne +296 -> 375
    //   82: aload 8
    //   84: ldc 34
    //   86: aload 6
    //   88: aconst_null
    //   89: aconst_null
    //   90: aconst_null
    //   91: aconst_null
    //   92: aload 7
    //   94: aload 9
    //   96: invokevirtual 331	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   99: astore 6
    //   101: aload 6
    //   103: astore 4
    //   105: new 105	java/util/ArrayList
    //   108: dup
    //   109: invokespecial 317	java/util/ArrayList:<init>	()V
    //   112: astore 7
    //   114: aload 4
    //   116: invokeinterface 336 1 0
    //   121: ifeq +47 -> 168
    //   124: aload 7
    //   126: new 363	com/google/android/gms/analytics/w
    //   129: dup
    //   130: aconst_null
    //   131: aload 4
    //   133: iconst_0
    //   134: invokeinterface 340 2 0
    //   139: aload 4
    //   141: iconst_1
    //   142: invokeinterface 340 2 0
    //   147: invokespecial 366	com/google/android/gms/analytics/w:<init>	(Ljava/lang/String;JJ)V
    //   150: invokeinterface 167 2 0
    //   155: pop
    //   156: aload 4
    //   158: invokeinterface 346 1 0
    //   163: istore_3
    //   164: iload_3
    //   165: ifne -41 -> 124
    //   168: aload 4
    //   170: ifnull +10 -> 180
    //   173: aload 4
    //   175: invokeinterface 349 1 0
    //   180: aload 4
    //   182: astore 5
    //   184: iconst_3
    //   185: anewarray 46	java/lang/String
    //   188: astore 6
    //   190: aload 6
    //   192: iconst_0
    //   193: ldc 36
    //   195: aastore
    //   196: aload 6
    //   198: iconst_1
    //   199: ldc 42
    //   201: aastore
    //   202: aload 6
    //   204: iconst_2
    //   205: ldc 40
    //   207: aastore
    //   208: aload 4
    //   210: astore 5
    //   212: ldc_w 323
    //   215: iconst_1
    //   216: anewarray 4	java/lang/Object
    //   219: dup
    //   220: iconst_0
    //   221: ldc 36
    //   223: aastore
    //   224: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   227: astore 9
    //   229: aload 4
    //   231: astore 5
    //   233: iload_1
    //   234: invokestatic 327	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   237: astore 10
    //   239: aload 4
    //   241: astore 5
    //   243: aload 8
    //   245: instanceof 235
    //   248: ifne +240 -> 488
    //   251: aload 4
    //   253: astore 5
    //   255: aload 8
    //   257: ldc 34
    //   259: aload 6
    //   261: aconst_null
    //   262: aconst_null
    //   263: aconst_null
    //   264: aconst_null
    //   265: aload 9
    //   267: aload 10
    //   269: invokevirtual 331	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   272: astore 6
    //   274: aload 6
    //   276: astore 4
    //   278: aload 4
    //   280: invokeinterface 336 1 0
    //   285: ifeq +75 -> 360
    //   288: iconst_0
    //   289: istore_1
    //   290: aload 4
    //   292: checkcast 368	android/database/sqlite/SQLiteCursor
    //   295: invokevirtual 372	android/database/sqlite/SQLiteCursor:getWindow	()Landroid/database/CursorWindow;
    //   298: invokevirtual 377	android/database/CursorWindow:getNumRows	()I
    //   301: ifle +220 -> 521
    //   304: aload 7
    //   306: iload_1
    //   307: invokeinterface 380 2 0
    //   312: checkcast 363	com/google/android/gms/analytics/w
    //   315: aload 4
    //   317: iconst_1
    //   318: invokeinterface 383 2 0
    //   323: invokevirtual 386	com/google/android/gms/analytics/w:aj	(Ljava/lang/String;)V
    //   326: aload 7
    //   328: iload_1
    //   329: invokeinterface 380 2 0
    //   334: checkcast 363	com/google/android/gms/analytics/w
    //   337: aload 4
    //   339: iconst_2
    //   340: invokeinterface 383 2 0
    //   345: invokevirtual 389	com/google/android/gms/analytics/w:ak	(Ljava/lang/String;)V
    //   348: aload 4
    //   350: invokeinterface 346 1 0
    //   355: istore_3
    //   356: iload_3
    //   357: ifne +433 -> 790
    //   360: aload 4
    //   362: ifnull +10 -> 372
    //   365: aload 4
    //   367: invokeinterface 349 1 0
    //   372: aload 7
    //   374: areturn
    //   375: aload 8
    //   377: checkcast 235	android/database/sqlite/SQLiteDatabase
    //   380: ldc 34
    //   382: aload 6
    //   384: aconst_null
    //   385: aconst_null
    //   386: aconst_null
    //   387: aconst_null
    //   388: aload 7
    //   390: aload 9
    //   392: invokestatic 352	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   395: astore 6
    //   397: aload 6
    //   399: astore 4
    //   401: goto -296 -> 105
    //   404: astore 6
    //   406: aconst_null
    //   407: astore 7
    //   409: aload 5
    //   411: astore 4
    //   413: aload 7
    //   415: astore 5
    //   417: new 138	java/lang/StringBuilder
    //   420: dup
    //   421: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   424: ldc_w 354
    //   427: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: aload 6
    //   432: invokevirtual 357	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   435: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   441: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   444: aload 4
    //   446: astore 6
    //   448: aload 5
    //   450: ifnull -423 -> 27
    //   453: aload 5
    //   455: invokeinterface 349 1 0
    //   460: aload 4
    //   462: areturn
    //   463: astore 6
    //   465: aload 4
    //   467: astore 5
    //   469: aload 6
    //   471: astore 4
    //   473: aload 5
    //   475: ifnull +10 -> 485
    //   478: aload 5
    //   480: invokeinterface 349 1 0
    //   485: aload 4
    //   487: athrow
    //   488: aload 4
    //   490: astore 5
    //   492: aload 8
    //   494: checkcast 235	android/database/sqlite/SQLiteDatabase
    //   497: ldc 34
    //   499: aload 6
    //   501: aconst_null
    //   502: aconst_null
    //   503: aconst_null
    //   504: aconst_null
    //   505: aload 9
    //   507: aload 10
    //   509: invokestatic 352	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   512: astore 6
    //   514: aload 6
    //   516: astore 4
    //   518: goto -240 -> 278
    //   521: ldc_w 391
    //   524: iconst_1
    //   525: anewarray 4	java/lang/Object
    //   528: dup
    //   529: iconst_0
    //   530: aload 7
    //   532: iload_1
    //   533: invokeinterface 380 2 0
    //   538: checkcast 363	com/google/android/gms/analytics/w
    //   541: invokevirtual 395	com/google/android/gms/analytics/w:eG	()J
    //   544: invokestatic 204	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   547: aastore
    //   548: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   551: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   554: goto -206 -> 348
    //   557: astore 6
    //   559: aload 4
    //   561: astore 5
    //   563: new 138	java/lang/StringBuilder
    //   566: dup
    //   567: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   570: ldc_w 397
    //   573: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   576: aload 6
    //   578: invokevirtual 357	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   581: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   587: invokestatic 233	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   590: aload 4
    //   592: astore 5
    //   594: new 105	java/util/ArrayList
    //   597: dup
    //   598: invokespecial 317	java/util/ArrayList:<init>	()V
    //   601: astore 6
    //   603: iconst_0
    //   604: istore_1
    //   605: aload 4
    //   607: astore 5
    //   609: aload 7
    //   611: invokeinterface 398 1 0
    //   616: astore 7
    //   618: aload 4
    //   620: astore 5
    //   622: aload 7
    //   624: invokeinterface 130 1 0
    //   629: ifeq +42 -> 671
    //   632: aload 4
    //   634: astore 5
    //   636: aload 7
    //   638: invokeinterface 134 1 0
    //   643: checkcast 363	com/google/android/gms/analytics/w
    //   646: astore 8
    //   648: aload 4
    //   650: astore 5
    //   652: aload 8
    //   654: invokevirtual 401	com/google/android/gms/analytics/w:eF	()Ljava/lang/String;
    //   657: invokestatic 405	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   660: istore_3
    //   661: iload_1
    //   662: istore_2
    //   663: iload_3
    //   664: ifeq +24 -> 688
    //   667: iload_1
    //   668: ifeq +18 -> 686
    //   671: aload 4
    //   673: ifnull +10 -> 683
    //   676: aload 4
    //   678: invokeinterface 349 1 0
    //   683: aload 6
    //   685: areturn
    //   686: iconst_1
    //   687: istore_2
    //   688: aload 4
    //   690: astore 5
    //   692: aload 6
    //   694: aload 8
    //   696: invokeinterface 167 2 0
    //   701: pop
    //   702: iload_2
    //   703: istore_1
    //   704: goto -86 -> 618
    //   707: astore 4
    //   709: aload 5
    //   711: ifnull +10 -> 721
    //   714: aload 5
    //   716: invokeinterface 349 1 0
    //   721: aload 4
    //   723: athrow
    //   724: astore 6
    //   726: aload 4
    //   728: astore 5
    //   730: aload 6
    //   732: astore 4
    //   734: goto -25 -> 709
    //   737: astore 6
    //   739: goto -180 -> 559
    //   742: astore 6
    //   744: aload 4
    //   746: astore 5
    //   748: aload 6
    //   750: astore 4
    //   752: goto -279 -> 473
    //   755: astore 4
    //   757: goto -284 -> 473
    //   760: astore 6
    //   762: aload 4
    //   764: astore 7
    //   766: aload 5
    //   768: astore 4
    //   770: aload 7
    //   772: astore 5
    //   774: goto -357 -> 417
    //   777: astore 6
    //   779: aload 4
    //   781: astore 5
    //   783: aload 7
    //   785: astore 4
    //   787: goto -370 -> 417
    //   790: iload_1
    //   791: iconst_1
    //   792: iadd
    //   793: istore_1
    //   794: goto -504 -> 290
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	797	0	this	ab
    //   0	797	1	paramInt	int
    //   662	41	2	i	int
    //   163	501	3	bool	boolean
    //   31	658	4	localObject1	Object
    //   707	20	4	localObject2	Object
    //   732	19	4	localObject3	Object
    //   755	8	4	localObject4	Object
    //   768	18	4	localObject5	Object
    //   7	775	5	localObject6	Object
    //   25	373	6	localObject7	Object
    //   404	27	6	localSQLiteException1	SQLiteException
    //   446	1	6	localObject8	Object
    //   463	37	6	arrayOfString	String[]
    //   512	3	6	localCursor	Cursor
    //   557	20	6	localSQLiteException2	SQLiteException
    //   601	92	6	localArrayList	ArrayList
    //   724	7	6	localObject9	Object
    //   737	1	6	localSQLiteException3	SQLiteException
    //   742	7	6	localObject10	Object
    //   760	1	6	localSQLiteException4	SQLiteException
    //   777	1	6	localSQLiteException5	SQLiteException
    //   66	718	7	localObject11	Object
    //   16	679	8	localObject12	Object
    //   72	434	9	str1	String
    //   237	271	10	str2	String
    // Exception table:
    //   from	to	target	type
    //   33	39	404	android/database/sqlite/SQLiteException
    //   51	101	404	android/database/sqlite/SQLiteException
    //   375	397	404	android/database/sqlite/SQLiteException
    //   33	39	463	finally
    //   51	101	463	finally
    //   375	397	463	finally
    //   278	288	557	android/database/sqlite/SQLiteException
    //   290	348	557	android/database/sqlite/SQLiteException
    //   348	356	557	android/database/sqlite/SQLiteException
    //   521	554	557	android/database/sqlite/SQLiteException
    //   184	190	707	finally
    //   212	229	707	finally
    //   233	239	707	finally
    //   243	251	707	finally
    //   255	274	707	finally
    //   492	514	707	finally
    //   563	590	707	finally
    //   594	603	707	finally
    //   609	618	707	finally
    //   622	632	707	finally
    //   636	648	707	finally
    //   652	661	707	finally
    //   692	702	707	finally
    //   278	288	724	finally
    //   290	348	724	finally
    //   348	356	724	finally
    //   521	554	724	finally
    //   184	190	737	android/database/sqlite/SQLiteException
    //   212	229	737	android/database/sqlite/SQLiteException
    //   233	239	737	android/database/sqlite/SQLiteException
    //   243	251	737	android/database/sqlite/SQLiteException
    //   255	274	737	android/database/sqlite/SQLiteException
    //   492	514	737	android/database/sqlite/SQLiteException
    //   105	114	742	finally
    //   114	124	742	finally
    //   124	164	742	finally
    //   417	444	755	finally
    //   105	114	760	android/database/sqlite/SQLiteException
    //   114	124	777	android/database/sqlite/SQLiteException
    //   124	164	777	android/database/sqlite/SQLiteException
  }
  
  public void a(Map<String, String> paramMap, long paramLong, String paramString, Collection<hb> paramCollection)
  {
    eN();
    eM();
    a(paramMap, paramCollection);
    a(paramMap, paramLong, paramString);
  }
  
  @Deprecated
  void b(Collection<w> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty()))
    {
      z.W("Empty/Null collection passed to deleteHits.");
      return;
    }
    String[] arrayOfString = new String[paramCollection.size()];
    paramCollection = paramCollection.iterator();
    int i = 0;
    while (paramCollection.hasNext())
    {
      arrayOfString[i] = String.valueOf(((w)paramCollection.next()).eG());
      i += 1;
    }
    b(arrayOfString);
  }
  
  void b(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      z.W("Empty hitIds passed to deleteHits.");
    }
    Object localObject;
    do
    {
      return;
      localObject = al("Error opening database for deleteHits.");
    } while (localObject == null);
    String str = String.format("HIT_ID in (%s)", new Object[] { TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?")) });
    for (;;)
    {
      try
      {
        if (!(localObject instanceof SQLiteDatabase))
        {
          ((SQLiteDatabase)localObject).delete("hits2", str, paramArrayOfString);
          localObject = this.yl;
          if (eO() != 0) {
            break label143;
          }
          bool = true;
          ((e)localObject).z(bool);
          return;
        }
      }
      catch (SQLiteException localSQLiteException)
      {
        z.W("Error deleting hits " + TextUtils.join(",", paramArrayOfString));
        return;
      }
      SQLiteInstrumentation.delete((SQLiteDatabase)localSQLiteException, "hits2", str, paramArrayOfString);
      continue;
      label143:
      boolean bool = false;
    }
  }
  
  public m dM()
  {
    return this.Ba;
  }
  
  public void dispatch()
  {
    boolean bool = true;
    z.V("Dispatch running...");
    if (!this.Ba.dX()) {
      return;
    }
    List localList = G(40);
    if (localList.isEmpty())
    {
      z.V("...nothing to dispatch");
      this.yl.z(true);
      return;
    }
    if (this.Bc == null) {
      this.Bc = new aa("_t=dispatch&_v=ma4.0.3", true);
    }
    if (eO() <= localList.size()) {}
    for (;;)
    {
      int i = this.Ba.a(localList, this.Bc, bool);
      z.V("sent " + i + " of " + localList.size() + " hits");
      b(localList.subList(0, Math.min(i, localList.size())));
      if ((i != localList.size()) || (eO() <= 0)) {
        break;
      }
      GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
      return;
      bool = false;
    }
    this.Bc = null;
  }
  
  int eN()
  {
    boolean bool = true;
    long l = this.yD.currentTimeMillis();
    if (l <= this.Bd + 86400000L) {}
    Object localObject;
    do
    {
      return 0;
      this.Bd = l;
      localObject = al("Error opening database for deleteStaleHits.");
    } while (localObject == null);
    l = this.yD.currentTimeMillis();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = Long.toString(l - 2592000000L);
    int i;
    if (!(localObject instanceof SQLiteDatabase))
    {
      i = ((SQLiteDatabase)localObject).delete("hits2", "HIT_TIME < ?", arrayOfString);
      localObject = this.yl;
      if (eO() != 0) {
        break label137;
      }
    }
    for (;;)
    {
      ((e)localObject).z(bool);
      return i;
      i = SQLiteInstrumentation.delete((SQLiteDatabase)localObject, "hits2", "HIT_TIME < ?", arrayOfString);
      break;
      label137:
      bool = false;
    }
  }
  
  int eO()
  {
    Cursor localCursor = null;
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for getNumStoredHits.");
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
          localCursor = localSQLiteDatabase.rawQuery("SELECT COUNT(*) from hits2", null);
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
          z.W("Error getting numStoredHits");
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
      localCursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)localSQLiteDatabase, "SELECT COUNT(*) from hits2", null);
    }
  }
  
  public void l(long paramLong)
  {
    Object localObject = al("Error opening database for clearHits");
    if (localObject != null)
    {
      if (paramLong != 0L) {
        break label78;
      }
      if ((localObject instanceof SQLiteDatabase)) {
        break label62;
      }
      ((SQLiteDatabase)localObject).delete("hits2", null, null);
      localObject = this.yl;
      if (eO() != 0) {
        break label138;
      }
    }
    label62:
    label78:
    label138:
    for (boolean bool = true;; bool = false)
    {
      ((e)localObject).z(bool);
      return;
      SQLiteInstrumentation.delete((SQLiteDatabase)localObject, "hits2", null, null);
      break;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = Long.valueOf(paramLong).toString();
      if (!(localObject instanceof SQLiteDatabase))
      {
        ((SQLiteDatabase)localObject).delete("hits2", "hit_app_id = ?", arrayOfString);
        break;
      }
      SQLiteInstrumentation.delete((SQLiteDatabase)localObject, "hits2", "hit_app_id = ?", arrayOfString);
      break;
    }
  }
  
  class a
    extends SQLiteOpenHelper
  {
    private boolean Bf;
    private long Bg = 0L;
    
    a(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor;
      if (!(paramSQLiteDatabase instanceof SQLiteDatabase)) {
        localCursor = paramSQLiteDatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
      }
      HashSet localHashSet;
      for (;;)
      {
        localHashSet = new HashSet();
        try
        {
          String[] arrayOfString = localCursor.getColumnNames();
          i = 0;
          for (;;)
          {
            if (i < arrayOfString.length)
            {
              localHashSet.add(arrayOfString[i]);
              i += 1;
              continue;
              localCursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)paramSQLiteDatabase, "SELECT * FROM hits2 WHERE 0", null);
              break;
            }
          }
          localCursor.close();
          if ((!localHashSet.remove("hit_id")) || (!localHashSet.remove("hit_url")) || (!localHashSet.remove("hit_string")) || (!localHashSet.remove("hit_time"))) {
            throw new SQLiteException("Database column missing");
          }
        }
        finally
        {
          localCursor.close();
        }
      }
      if (!localHashSet.remove("hit_app_id")) {}
      for (int i = 1; !localHashSet.isEmpty(); i = 0) {
        throw new SQLiteException("Database has extra columns");
      }
      if (i != 0)
      {
        if (!(paramSQLiteDatabase instanceof SQLiteDatabase)) {
          paramSQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
        }
      }
      else {
        return;
      }
      SQLiteInstrumentation.execSQL((SQLiteDatabase)paramSQLiteDatabase, "ALTER TABLE hits2 ADD COLUMN hit_app_id");
    }
    
    /* Error */
    private boolean a(String paramString, SQLiteDatabase paramSQLiteDatabase)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: iconst_1
      //   4: anewarray 94	java/lang/String
      //   7: astore 5
      //   9: aload 5
      //   11: iconst_0
      //   12: ldc 96
      //   14: aastore
      //   15: iconst_1
      //   16: anewarray 94	java/lang/String
      //   19: astore 6
      //   21: aload 6
      //   23: iconst_0
      //   24: aload_1
      //   25: aastore
      //   26: aload_2
      //   27: instanceof 26
      //   30: ifne +38 -> 68
      //   33: aload_2
      //   34: ldc 98
      //   36: aload 5
      //   38: ldc 100
      //   40: aload 6
      //   42: aconst_null
      //   43: aconst_null
      //   44: aconst_null
      //   45: invokevirtual 104	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   48: astore_2
      //   49: aload_2
      //   50: invokeinterface 107 1 0
      //   55: istore_3
      //   56: aload_2
      //   57: ifnull +9 -> 66
      //   60: aload_2
      //   61: invokeinterface 57 1 0
      //   66: iload_3
      //   67: ireturn
      //   68: aload_2
      //   69: checkcast 26	android/database/sqlite/SQLiteDatabase
      //   72: ldc 98
      //   74: aload 5
      //   76: ldc 100
      //   78: aload 6
      //   80: aconst_null
      //   81: aconst_null
      //   82: aconst_null
      //   83: invokestatic 110	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   86: astore_2
      //   87: goto -38 -> 49
      //   90: astore_2
      //   91: aconst_null
      //   92: astore_2
      //   93: new 112	java/lang/StringBuilder
      //   96: dup
      //   97: invokespecial 113	java/lang/StringBuilder:<init>	()V
      //   100: ldc 115
      //   102: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   105: aload_1
      //   106: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   109: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   112: invokestatic 128	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
      //   115: aload_2
      //   116: ifnull +9 -> 125
      //   119: aload_2
      //   120: invokeinterface 57 1 0
      //   125: iconst_0
      //   126: ireturn
      //   127: astore_1
      //   128: aload 4
      //   130: astore_2
      //   131: aload_2
      //   132: ifnull +9 -> 141
      //   135: aload_2
      //   136: invokeinterface 57 1 0
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
      if ((this.Bf) && (this.Bg + 3600000L > ab.a(ab.this).currentTimeMillis())) {
        throw new SQLiteException("Database creation failed");
      }
      Object localObject1 = null;
      this.Bf = true;
      this.Bg = ab.a(ab.this).currentTimeMillis();
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
          ab.c(ab.this).getDatabasePath(ab.b(ab.this)).delete();
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = super.getWritableDatabase();
      }
      this.Bf = false;
      return (SQLiteDatabase)localObject2;
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      o.ag(paramSQLiteDatabase.getPath());
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
          if (a("hits2", paramSQLiteDatabase)) {
            break label95;
          }
          localObject = ab.eP();
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
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */