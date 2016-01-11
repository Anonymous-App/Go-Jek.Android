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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.http.impl.client.DefaultHttpClient;

class cb
  implements at
{
  private static final String AY = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[] { "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time" });
  private final String Bb;
  private long Bd;
  private final int Be;
  private final b apW;
  private volatile ab apX;
  private final au apY;
  private final Context mContext;
  private ju yD;
  
  cb(au paramau, Context paramContext)
  {
    this(paramau, paramContext, "gtm_urls.db", 2000);
  }
  
  cb(au paramau, Context paramContext, String paramString, int paramInt)
  {
    this.mContext = paramContext.getApplicationContext();
    this.Bb = paramString;
    this.apY = paramau;
    this.yD = jw.hA();
    this.apW = new b(this.mContext, this.Bb);
    this.apX = new db(new DefaultHttpClient(), this.mContext, new a());
    this.Bd = 0L;
    this.Be = paramInt;
  }
  
  private SQLiteDatabase al(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.apW.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W(paramString);
    }
    return null;
  }
  
  private void c(long paramLong1, long paramLong2)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for getNumStoredHits.");
    if (localSQLiteDatabase == null) {
      return;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("hit_first_send_time", Long.valueOf(paramLong2));
    String[] arrayOfString;
    try
    {
      arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramLong1);
      if (!(localSQLiteDatabase instanceof SQLiteDatabase))
      {
        localSQLiteDatabase.update("gtm_hits", localContentValues, "hit_id=?", arrayOfString);
        return;
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.W("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + paramLong1);
      y(paramLong1);
      return;
    }
    SQLiteInstrumentation.update((SQLiteDatabase)localSQLiteException, "gtm_hits", localContentValues, "hit_id=?", arrayOfString);
  }
  
  private void eM()
  {
    int i = eO() - this.Be + 1;
    if (i > 0)
    {
      List localList = F(i);
      bh.V("Store full, deleting " + localList.size() + " hits to make room.");
      b((String[])localList.toArray(new String[0]));
    }
  }
  
  private void g(long paramLong, String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = al("Error opening database for putHit");
    if (localSQLiteDatabase == null) {
      return;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("hit_time", Long.valueOf(paramLong));
    localContentValues.put("hit_url", paramString);
    localContentValues.put("hit_first_send_time", Integer.valueOf(0));
    for (;;)
    {
      try
      {
        if (!(localSQLiteDatabase instanceof SQLiteDatabase))
        {
          localSQLiteDatabase.insert("gtm_hits", null, localContentValues);
          this.apY.z(false);
          return;
        }
      }
      catch (SQLiteException paramString)
      {
        bh.W("Error storing hit");
        return;
      }
      SQLiteInstrumentation.insert((SQLiteDatabase)localSQLiteDatabase, "gtm_hits", null, localContentValues);
    }
  }
  
  private void y(long paramLong)
  {
    b(new String[] { String.valueOf(paramLong) });
  }
  
  /* Error */
  List<String> F(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 245	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 246	java/util/ArrayList:<init>	()V
    //   10: astore 6
    //   12: iload_1
    //   13: ifgt +11 -> 24
    //   16: ldc -8
    //   18: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   21: aload 6
    //   23: areturn
    //   24: aload_0
    //   25: ldc -6
    //   27: invokespecial 134	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   30: astore_3
    //   31: aload_3
    //   32: ifnonnull +6 -> 38
    //   35: aload 6
    //   37: areturn
    //   38: iconst_1
    //   39: anewarray 45	java/lang/String
    //   42: astore 5
    //   44: aload 5
    //   46: iconst_0
    //   47: ldc 37
    //   49: aastore
    //   50: ldc -4
    //   52: iconst_1
    //   53: anewarray 4	java/lang/Object
    //   56: dup
    //   57: iconst_0
    //   58: ldc 37
    //   60: aastore
    //   61: invokestatic 49	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   64: astore 7
    //   66: iload_1
    //   67: invokestatic 255	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   70: astore 8
    //   72: aload_3
    //   73: instanceof 152
    //   76: ifne +80 -> 156
    //   79: aload_3
    //   80: ldc 35
    //   82: aload 5
    //   84: aconst_null
    //   85: aconst_null
    //   86: aconst_null
    //   87: aconst_null
    //   88: aload 7
    //   90: aload 8
    //   92: invokevirtual 259	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   95: astore_3
    //   96: aload_3
    //   97: astore 4
    //   99: aload_3
    //   100: invokeinterface 265 1 0
    //   105: ifeq +38 -> 143
    //   108: aload_3
    //   109: astore 4
    //   111: aload 6
    //   113: aload_3
    //   114: iconst_0
    //   115: invokeinterface 269 2 0
    //   120: invokestatic 150	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   123: invokeinterface 273 2 0
    //   128: pop
    //   129: aload_3
    //   130: astore 4
    //   132: aload_3
    //   133: invokeinterface 276 1 0
    //   138: istore_2
    //   139: iload_2
    //   140: ifne -32 -> 108
    //   143: aload_3
    //   144: ifnull +9 -> 153
    //   147: aload_3
    //   148: invokeinterface 279 1 0
    //   153: aload 6
    //   155: areturn
    //   156: aload_3
    //   157: checkcast 152	android/database/sqlite/SQLiteDatabase
    //   160: ldc 35
    //   162: aload 5
    //   164: aconst_null
    //   165: aconst_null
    //   166: aconst_null
    //   167: aconst_null
    //   168: aload 7
    //   170: aload 8
    //   172: invokestatic 282	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   175: astore_3
    //   176: goto -80 -> 96
    //   179: astore 5
    //   181: aconst_null
    //   182: astore_3
    //   183: aload_3
    //   184: astore 4
    //   186: new 160	java/lang/StringBuilder
    //   189: dup
    //   190: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   193: ldc_w 284
    //   196: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: aload 5
    //   201: invokevirtual 287	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   204: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   210: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   213: aload_3
    //   214: ifnull -61 -> 153
    //   217: aload_3
    //   218: invokeinterface 279 1 0
    //   223: goto -70 -> 153
    //   226: astore_3
    //   227: aload 4
    //   229: ifnull +10 -> 239
    //   232: aload 4
    //   234: invokeinterface 279 1 0
    //   239: aload_3
    //   240: athrow
    //   241: astore_3
    //   242: goto -15 -> 227
    //   245: astore 5
    //   247: goto -64 -> 183
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	250	0	this	cb
    //   0	250	1	paramInt	int
    //   138	2	2	bool	boolean
    //   30	188	3	localObject1	Object
    //   226	14	3	localObject2	Object
    //   241	1	3	localObject3	Object
    //   1	232	4	localObject4	Object
    //   42	121	5	arrayOfString	String[]
    //   179	21	5	localSQLiteException1	SQLiteException
    //   245	1	5	localSQLiteException2	SQLiteException
    //   10	144	6	localArrayList	java.util.ArrayList
    //   64	105	7	str1	String
    //   70	101	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   38	44	179	android/database/sqlite/SQLiteException
    //   50	96	179	android/database/sqlite/SQLiteException
    //   156	176	179	android/database/sqlite/SQLiteException
    //   38	44	226	finally
    //   50	96	226	finally
    //   156	176	226	finally
    //   99	108	241	finally
    //   111	129	241	finally
    //   132	139	241	finally
    //   186	213	241	finally
    //   99	108	245	android/database/sqlite/SQLiteException
    //   111	129	245	android/database/sqlite/SQLiteException
    //   132	139	245	android/database/sqlite/SQLiteException
  }
  
  /* Error */
  public List<ap> G(int paramInt)
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 246	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: ldc_w 292
    //   13: invokespecial 134	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: astore 8
    //   18: aload 8
    //   20: ifnonnull +10 -> 30
    //   23: aload 5
    //   25: astore 6
    //   27: aload 6
    //   29: areturn
    //   30: aconst_null
    //   31: astore 4
    //   33: iconst_3
    //   34: anewarray 45	java/lang/String
    //   37: astore 6
    //   39: aload 6
    //   41: iconst_0
    //   42: ldc 37
    //   44: aastore
    //   45: aload 6
    //   47: iconst_1
    //   48: ldc 39
    //   50: aastore
    //   51: aload 6
    //   53: iconst_2
    //   54: ldc 43
    //   56: aastore
    //   57: ldc -4
    //   59: iconst_1
    //   60: anewarray 4	java/lang/Object
    //   63: dup
    //   64: iconst_0
    //   65: ldc 37
    //   67: aastore
    //   68: invokestatic 49	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   71: astore 7
    //   73: iload_1
    //   74: invokestatic 255	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   77: astore 9
    //   79: aload 8
    //   81: instanceof 152
    //   84: ifne +274 -> 358
    //   87: aload 8
    //   89: ldc 35
    //   91: aload 6
    //   93: aconst_null
    //   94: aconst_null
    //   95: aconst_null
    //   96: aconst_null
    //   97: aload 7
    //   99: aload 9
    //   101: invokevirtual 259	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   104: astore 6
    //   106: aload 6
    //   108: astore 4
    //   110: new 245	java/util/ArrayList
    //   113: dup
    //   114: invokespecial 246	java/util/ArrayList:<init>	()V
    //   117: astore 7
    //   119: aload 4
    //   121: invokeinterface 265 1 0
    //   126: ifeq +54 -> 180
    //   129: aload 7
    //   131: new 294	com/google/android/gms/tagmanager/ap
    //   134: dup
    //   135: aload 4
    //   137: iconst_0
    //   138: invokeinterface 269 2 0
    //   143: aload 4
    //   145: iconst_1
    //   146: invokeinterface 269 2 0
    //   151: aload 4
    //   153: iconst_2
    //   154: invokeinterface 269 2 0
    //   159: invokespecial 297	com/google/android/gms/tagmanager/ap:<init>	(JJJ)V
    //   162: invokeinterface 273 2 0
    //   167: pop
    //   168: aload 4
    //   170: invokeinterface 276 1 0
    //   175: istore_3
    //   176: iload_3
    //   177: ifne -48 -> 129
    //   180: aload 4
    //   182: ifnull +10 -> 192
    //   185: aload 4
    //   187: invokeinterface 279 1 0
    //   192: aload 4
    //   194: astore 5
    //   196: iconst_2
    //   197: anewarray 45	java/lang/String
    //   200: astore 6
    //   202: aload 6
    //   204: iconst_0
    //   205: ldc 37
    //   207: aastore
    //   208: aload 6
    //   210: iconst_1
    //   211: ldc 41
    //   213: aastore
    //   214: aload 4
    //   216: astore 5
    //   218: ldc -4
    //   220: iconst_1
    //   221: anewarray 4	java/lang/Object
    //   224: dup
    //   225: iconst_0
    //   226: ldc 37
    //   228: aastore
    //   229: invokestatic 49	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   232: astore 9
    //   234: aload 4
    //   236: astore 5
    //   238: iload_1
    //   239: invokestatic 255	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   242: astore 10
    //   244: aload 4
    //   246: astore 5
    //   248: aload 8
    //   250: instanceof 152
    //   253: ifne +218 -> 471
    //   256: aload 4
    //   258: astore 5
    //   260: aload 8
    //   262: ldc 35
    //   264: aload 6
    //   266: aconst_null
    //   267: aconst_null
    //   268: aconst_null
    //   269: aconst_null
    //   270: aload 9
    //   272: aload 10
    //   274: invokevirtual 259	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   277: astore 6
    //   279: aload 6
    //   281: astore 4
    //   283: aload 4
    //   285: invokeinterface 265 1 0
    //   290: ifeq +53 -> 343
    //   293: iconst_0
    //   294: istore_1
    //   295: aload 4
    //   297: checkcast 299	android/database/sqlite/SQLiteCursor
    //   300: invokevirtual 303	android/database/sqlite/SQLiteCursor:getWindow	()Landroid/database/CursorWindow;
    //   303: invokevirtual 308	android/database/CursorWindow:getNumRows	()I
    //   306: ifle +198 -> 504
    //   309: aload 7
    //   311: iload_1
    //   312: invokeinterface 312 2 0
    //   317: checkcast 294	com/google/android/gms/tagmanager/ap
    //   320: aload 4
    //   322: iconst_1
    //   323: invokeinterface 315 2 0
    //   328: invokevirtual 318	com/google/android/gms/tagmanager/ap:ak	(Ljava/lang/String;)V
    //   331: aload 4
    //   333: invokeinterface 276 1 0
    //   338: istore_3
    //   339: iload_3
    //   340: ifne +433 -> 773
    //   343: aload 4
    //   345: ifnull +10 -> 355
    //   348: aload 4
    //   350: invokeinterface 279 1 0
    //   355: aload 7
    //   357: areturn
    //   358: aload 8
    //   360: checkcast 152	android/database/sqlite/SQLiteDatabase
    //   363: ldc 35
    //   365: aload 6
    //   367: aconst_null
    //   368: aconst_null
    //   369: aconst_null
    //   370: aconst_null
    //   371: aload 7
    //   373: aload 9
    //   375: invokestatic 282	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   378: astore 6
    //   380: aload 6
    //   382: astore 4
    //   384: goto -274 -> 110
    //   387: astore 6
    //   389: aconst_null
    //   390: astore 7
    //   392: aload 5
    //   394: astore 4
    //   396: aload 7
    //   398: astore 5
    //   400: new 160	java/lang/StringBuilder
    //   403: dup
    //   404: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   407: ldc_w 284
    //   410: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   413: aload 6
    //   415: invokevirtual 287	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   418: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   424: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   427: aload 4
    //   429: astore 6
    //   431: aload 5
    //   433: ifnull -406 -> 27
    //   436: aload 5
    //   438: invokeinterface 279 1 0
    //   443: aload 4
    //   445: areturn
    //   446: astore 6
    //   448: aload 4
    //   450: astore 5
    //   452: aload 6
    //   454: astore 4
    //   456: aload 5
    //   458: ifnull +10 -> 468
    //   461: aload 5
    //   463: invokeinterface 279 1 0
    //   468: aload 4
    //   470: athrow
    //   471: aload 4
    //   473: astore 5
    //   475: aload 8
    //   477: checkcast 152	android/database/sqlite/SQLiteDatabase
    //   480: ldc 35
    //   482: aload 6
    //   484: aconst_null
    //   485: aconst_null
    //   486: aconst_null
    //   487: aconst_null
    //   488: aload 9
    //   490: aload 10
    //   492: invokestatic 282	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   495: astore 6
    //   497: aload 6
    //   499: astore 4
    //   501: goto -218 -> 283
    //   504: ldc_w 320
    //   507: iconst_1
    //   508: anewarray 4	java/lang/Object
    //   511: dup
    //   512: iconst_0
    //   513: aload 7
    //   515: iload_1
    //   516: invokeinterface 312 2 0
    //   521: checkcast 294	com/google/android/gms/tagmanager/ap
    //   524: invokevirtual 324	com/google/android/gms/tagmanager/ap:eG	()J
    //   527: invokestatic 143	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   530: aastore
    //   531: invokestatic 49	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   534: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   537: goto -206 -> 331
    //   540: astore 6
    //   542: aload 4
    //   544: astore 5
    //   546: new 160	java/lang/StringBuilder
    //   549: dup
    //   550: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   553: ldc_w 326
    //   556: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   559: aload 6
    //   561: invokevirtual 287	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   564: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   567: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   570: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   573: aload 4
    //   575: astore 5
    //   577: new 245	java/util/ArrayList
    //   580: dup
    //   581: invokespecial 246	java/util/ArrayList:<init>	()V
    //   584: astore 6
    //   586: iconst_0
    //   587: istore_1
    //   588: aload 4
    //   590: astore 5
    //   592: aload 7
    //   594: invokeinterface 330 1 0
    //   599: astore 7
    //   601: aload 4
    //   603: astore 5
    //   605: aload 7
    //   607: invokeinterface 335 1 0
    //   612: ifeq +42 -> 654
    //   615: aload 4
    //   617: astore 5
    //   619: aload 7
    //   621: invokeinterface 339 1 0
    //   626: checkcast 294	com/google/android/gms/tagmanager/ap
    //   629: astore 8
    //   631: aload 4
    //   633: astore 5
    //   635: aload 8
    //   637: invokevirtual 342	com/google/android/gms/tagmanager/ap:ou	()Ljava/lang/String;
    //   640: invokestatic 348	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   643: istore_3
    //   644: iload_1
    //   645: istore_2
    //   646: iload_3
    //   647: ifeq +24 -> 671
    //   650: iload_1
    //   651: ifeq +18 -> 669
    //   654: aload 4
    //   656: ifnull +10 -> 666
    //   659: aload 4
    //   661: invokeinterface 279 1 0
    //   666: aload 6
    //   668: areturn
    //   669: iconst_1
    //   670: istore_2
    //   671: aload 4
    //   673: astore 5
    //   675: aload 6
    //   677: aload 8
    //   679: invokeinterface 273 2 0
    //   684: pop
    //   685: iload_2
    //   686: istore_1
    //   687: goto -86 -> 601
    //   690: astore 4
    //   692: aload 5
    //   694: ifnull +10 -> 704
    //   697: aload 5
    //   699: invokeinterface 279 1 0
    //   704: aload 4
    //   706: athrow
    //   707: astore 6
    //   709: aload 4
    //   711: astore 5
    //   713: aload 6
    //   715: astore 4
    //   717: goto -25 -> 692
    //   720: astore 6
    //   722: goto -180 -> 542
    //   725: astore 6
    //   727: aload 4
    //   729: astore 5
    //   731: aload 6
    //   733: astore 4
    //   735: goto -279 -> 456
    //   738: astore 4
    //   740: goto -284 -> 456
    //   743: astore 6
    //   745: aload 4
    //   747: astore 7
    //   749: aload 5
    //   751: astore 4
    //   753: aload 7
    //   755: astore 5
    //   757: goto -357 -> 400
    //   760: astore 6
    //   762: aload 4
    //   764: astore 5
    //   766: aload 7
    //   768: astore 4
    //   770: goto -370 -> 400
    //   773: iload_1
    //   774: iconst_1
    //   775: iadd
    //   776: istore_1
    //   777: goto -482 -> 295
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	780	0	this	cb
    //   0	780	1	paramInt	int
    //   645	41	2	i	int
    //   175	472	3	bool	boolean
    //   31	641	4	localObject1	Object
    //   690	20	4	localObject2	Object
    //   715	19	4	localObject3	Object
    //   738	8	4	localObject4	Object
    //   751	18	4	localObject5	Object
    //   7	758	5	localObject6	Object
    //   25	356	6	localObject7	Object
    //   387	27	6	localSQLiteException1	SQLiteException
    //   429	1	6	localObject8	Object
    //   446	37	6	arrayOfString	String[]
    //   495	3	6	localCursor	Cursor
    //   540	20	6	localSQLiteException2	SQLiteException
    //   584	92	6	localArrayList	java.util.ArrayList
    //   707	7	6	localObject9	Object
    //   720	1	6	localSQLiteException3	SQLiteException
    //   725	7	6	localObject10	Object
    //   743	1	6	localSQLiteException4	SQLiteException
    //   760	1	6	localSQLiteException5	SQLiteException
    //   71	696	7	localObject11	Object
    //   16	662	8	localObject12	Object
    //   77	412	9	str1	String
    //   242	249	10	str2	String
    // Exception table:
    //   from	to	target	type
    //   33	39	387	android/database/sqlite/SQLiteException
    //   57	106	387	android/database/sqlite/SQLiteException
    //   358	380	387	android/database/sqlite/SQLiteException
    //   33	39	446	finally
    //   57	106	446	finally
    //   358	380	446	finally
    //   283	293	540	android/database/sqlite/SQLiteException
    //   295	331	540	android/database/sqlite/SQLiteException
    //   331	339	540	android/database/sqlite/SQLiteException
    //   504	537	540	android/database/sqlite/SQLiteException
    //   196	202	690	finally
    //   218	234	690	finally
    //   238	244	690	finally
    //   248	256	690	finally
    //   260	279	690	finally
    //   475	497	690	finally
    //   546	573	690	finally
    //   577	586	690	finally
    //   592	601	690	finally
    //   605	615	690	finally
    //   619	631	690	finally
    //   635	644	690	finally
    //   675	685	690	finally
    //   283	293	707	finally
    //   295	331	707	finally
    //   331	339	707	finally
    //   504	537	707	finally
    //   196	202	720	android/database/sqlite/SQLiteException
    //   218	234	720	android/database/sqlite/SQLiteException
    //   238	244	720	android/database/sqlite/SQLiteException
    //   248	256	720	android/database/sqlite/SQLiteException
    //   260	279	720	android/database/sqlite/SQLiteException
    //   475	497	720	android/database/sqlite/SQLiteException
    //   110	119	725	finally
    //   119	129	725	finally
    //   129	176	725	finally
    //   400	427	738	finally
    //   110	119	743	android/database/sqlite/SQLiteException
    //   119	129	760	android/database/sqlite/SQLiteException
    //   129	176	760	android/database/sqlite/SQLiteException
  }
  
  void b(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return;
      localSQLiteDatabase = al("Error opening database for deleteHits.");
    } while (localSQLiteDatabase == null);
    String str = String.format("HIT_ID in (%s)", new Object[] { TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?")) });
    for (;;)
    {
      try
      {
        if (!(localSQLiteDatabase instanceof SQLiteDatabase))
        {
          localSQLiteDatabase.delete("gtm_hits", str, paramArrayOfString);
          paramArrayOfString = this.apY;
          if (eO() != 0) {
            break label114;
          }
          bool = true;
          paramArrayOfString.z(bool);
          return;
        }
      }
      catch (SQLiteException paramArrayOfString)
      {
        bh.W("Error deleting hits");
        return;
      }
      SQLiteInstrumentation.delete((SQLiteDatabase)localSQLiteDatabase, "gtm_hits", str, paramArrayOfString);
      continue;
      label114:
      boolean bool = false;
    }
  }
  
  public void dispatch()
  {
    bh.V("GTM Dispatch running...");
    if (!this.apX.dX()) {}
    do
    {
      return;
      List localList = G(40);
      if (localList.isEmpty())
      {
        bh.V("...nothing to dispatch");
        this.apY.z(true);
        return;
      }
      this.apX.j(localList);
    } while (oH() <= 0);
    cy.pw().dispatch();
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
      i = ((SQLiteDatabase)localObject).delete("gtm_hits", "HIT_TIME < ?", arrayOfString);
      localObject = this.apY;
      if (eO() != 0) {
        break label137;
      }
    }
    for (;;)
    {
      ((au)localObject).z(bool);
      return i;
      i = SQLiteInstrumentation.delete((SQLiteDatabase)localObject, "gtm_hits", "HIT_TIME < ?", arrayOfString);
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
          localCursor = localSQLiteDatabase.rawQuery("SELECT COUNT(*) from gtm_hits", null);
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
          bh.W("Error getting numStoredHits");
          if (localObject1 == null) {
            break label177;
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
          break label182;
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
      localCursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)localSQLiteDatabase, "SELECT COUNT(*) from gtm_hits", null);
    }
  }
  
  public void f(long paramLong, String paramString)
  {
    eN();
    eM();
    g(paramLong, paramString);
  }
  
  /* Error */
  int oH()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ldc -124
    //   6: invokespecial 134	com/google/android/gms/tagmanager/cb:al	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore_3
    //   10: aload_3
    //   11: ifnonnull +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: iconst_2
    //   17: anewarray 45	java/lang/String
    //   20: astore 5
    //   22: aload 5
    //   24: iconst_0
    //   25: ldc 37
    //   27: aastore
    //   28: aload 5
    //   30: iconst_1
    //   31: ldc 43
    //   33: aastore
    //   34: aload_3
    //   35: instanceof 152
    //   38: ifne +42 -> 80
    //   41: aload_3
    //   42: ldc 35
    //   44: aload 5
    //   46: ldc_w 441
    //   49: aconst_null
    //   50: aconst_null
    //   51: aconst_null
    //   52: aconst_null
    //   53: invokevirtual 444	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   56: astore_3
    //   57: aload_3
    //   58: invokeinterface 447 1 0
    //   63: istore_2
    //   64: iload_2
    //   65: istore_1
    //   66: aload_3
    //   67: ifnull +11 -> 78
    //   70: aload_3
    //   71: invokeinterface 279 1 0
    //   76: iload_2
    //   77: istore_1
    //   78: iload_1
    //   79: ireturn
    //   80: aload_3
    //   81: checkcast 152	android/database/sqlite/SQLiteDatabase
    //   84: ldc 35
    //   86: aload 5
    //   88: ldc_w 441
    //   91: aconst_null
    //   92: aconst_null
    //   93: aconst_null
    //   94: aconst_null
    //   95: invokestatic 450	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   98: astore_3
    //   99: goto -42 -> 57
    //   102: astore_3
    //   103: aconst_null
    //   104: astore_3
    //   105: ldc_w 452
    //   108: invokestatic 128	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
    //   111: aload_3
    //   112: ifnull +56 -> 168
    //   115: aload_3
    //   116: invokeinterface 279 1 0
    //   121: iconst_0
    //   122: istore_1
    //   123: goto -45 -> 78
    //   126: astore_3
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 279 1 0
    //   139: aload_3
    //   140: athrow
    //   141: astore 5
    //   143: aload_3
    //   144: astore 4
    //   146: aload 5
    //   148: astore_3
    //   149: goto -22 -> 127
    //   152: astore 5
    //   154: aload_3
    //   155: astore 4
    //   157: aload 5
    //   159: astore_3
    //   160: goto -33 -> 127
    //   163: astore 4
    //   165: goto -60 -> 105
    //   168: iconst_0
    //   169: istore_1
    //   170: goto -92 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	173	0	this	cb
    //   65	105	1	i	int
    //   63	14	2	j	int
    //   9	90	3	localObject1	Object
    //   102	1	3	localSQLiteException1	SQLiteException
    //   104	12	3	localObject2	Object
    //   126	18	3	localObject3	Object
    //   148	12	3	localObject4	Object
    //   1	155	4	localObject5	Object
    //   163	1	4	localSQLiteException2	SQLiteException
    //   20	67	5	arrayOfString	String[]
    //   141	6	5	localObject6	Object
    //   152	6	5	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   16	22	102	android/database/sqlite/SQLiteException
    //   34	57	102	android/database/sqlite/SQLiteException
    //   80	99	102	android/database/sqlite/SQLiteException
    //   16	22	126	finally
    //   34	57	126	finally
    //   80	99	126	finally
    //   57	64	141	finally
    //   105	111	152	finally
    //   57	64	163	android/database/sqlite/SQLiteException
  }
  
  class a
    implements db.a
  {
    a() {}
    
    public void a(ap paramap)
    {
      cb.a(cb.this, paramap.eG());
    }
    
    public void b(ap paramap)
    {
      cb.a(cb.this, paramap.eG());
      bh.V("Permanent failure dispatching hitId: " + paramap.eG());
    }
    
    public void c(ap paramap)
    {
      long l = paramap.ot();
      if (l == 0L) {
        cb.a(cb.this, paramap.eG(), cb.a(cb.this).currentTimeMillis());
      }
      while (l + 14400000L >= cb.a(cb.this).currentTimeMillis()) {
        return;
      }
      cb.a(cb.this, paramap.eG());
      bh.V("Giving up on failed hitId: " + paramap.eG());
    }
  }
  
  class b
    extends SQLiteOpenHelper
  {
    private boolean Bf;
    private long Bg = 0L;
    
    b(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      if (!(paramSQLiteDatabase instanceof SQLiteDatabase)) {
        paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
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
              paramSQLiteDatabase = SQLiteInstrumentation.rawQuery((SQLiteDatabase)paramSQLiteDatabase, "SELECT * FROM gtm_hits WHERE 0", null);
              break;
            }
          }
          paramSQLiteDatabase.close();
          if ((!localHashSet.remove("hit_id")) || (!localHashSet.remove("hit_url")) || (!localHashSet.remove("hit_time")) || (!localHashSet.remove("hit_first_send_time"))) {
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
      //   4: anewarray 85	java/lang/String
      //   7: astore 5
      //   9: aload 5
      //   11: iconst_0
      //   12: ldc 87
      //   14: aastore
      //   15: iconst_1
      //   16: anewarray 85	java/lang/String
      //   19: astore 6
      //   21: aload 6
      //   23: iconst_0
      //   24: aload_1
      //   25: aastore
      //   26: aload_2
      //   27: instanceof 27
      //   30: ifne +38 -> 68
      //   33: aload_2
      //   34: ldc 89
      //   36: aload 5
      //   38: ldc 91
      //   40: aload 6
      //   42: aconst_null
      //   43: aconst_null
      //   44: aconst_null
      //   45: invokevirtual 95	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   48: astore_2
      //   49: aload_2
      //   50: invokeinterface 98 1 0
      //   55: istore_3
      //   56: aload_2
      //   57: ifnull +9 -> 66
      //   60: aload_2
      //   61: invokeinterface 58 1 0
      //   66: iload_3
      //   67: ireturn
      //   68: aload_2
      //   69: checkcast 27	android/database/sqlite/SQLiteDatabase
      //   72: ldc 89
      //   74: aload 5
      //   76: ldc 91
      //   78: aload 6
      //   80: aconst_null
      //   81: aconst_null
      //   82: aconst_null
      //   83: invokestatic 101	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   86: astore_2
      //   87: goto -38 -> 49
      //   90: astore_2
      //   91: aconst_null
      //   92: astore_2
      //   93: new 103	java/lang/StringBuilder
      //   96: dup
      //   97: invokespecial 104	java/lang/StringBuilder:<init>	()V
      //   100: ldc 106
      //   102: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   105: aload_1
      //   106: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   109: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   112: invokestatic 119	com/google/android/gms/tagmanager/bh:W	(Ljava/lang/String;)V
      //   115: aload_2
      //   116: ifnull +9 -> 125
      //   119: aload_2
      //   120: invokeinterface 58 1 0
      //   125: iconst_0
      //   126: ireturn
      //   127: astore_1
      //   128: aload 4
      //   130: astore_2
      //   131: aload_2
      //   132: ifnull +9 -> 141
      //   135: aload_2
      //   136: invokeinterface 58 1 0
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
      //   0	156	0	this	b
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
      if ((this.Bf) && (this.Bg + 3600000L > cb.a(cb.this).currentTimeMillis())) {
        throw new SQLiteException("Database creation failed");
      }
      Object localObject1 = null;
      this.Bf = true;
      this.Bg = cb.a(cb.this).currentTimeMillis();
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
          cb.c(cb.this).getDatabasePath(cb.b(cb.this)).delete();
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
          if (a("gtm_hits", paramSQLiteDatabase)) {
            break label95;
          }
          localObject = cb.oI();
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/cb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */