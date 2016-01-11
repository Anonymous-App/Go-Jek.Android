package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

@ez
public class ec
{
  private static final Object mw = new Object();
  private static final String sG = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[] { "InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time" });
  private static ec sI;
  private final a sH;
  
  private ec(Context paramContext)
  {
    this.sH = new a(paramContext, "google_inapp_purchase.db");
  }
  
  public static ec j(Context paramContext)
  {
    synchronized (mw)
    {
      if (sI == null) {
        sI = new ec(paramContext);
      }
      paramContext = sI;
      return paramContext;
    }
  }
  
  public ea a(Cursor paramCursor)
  {
    if (paramCursor == null) {
      return null;
    }
    return new ea(paramCursor.getLong(0), paramCursor.getString(1), paramCursor.getString(2));
  }
  
  public void a(ea paramea)
  {
    if (paramea == null) {
      return;
    }
    SQLiteDatabase localSQLiteDatabase;
    synchronized (mw)
    {
      localSQLiteDatabase = getWritableDatabase();
      if (localSQLiteDatabase == null) {
        return;
      }
    }
    paramea = String.format("%s = %d", new Object[] { "purchase_id", Long.valueOf(paramea.sA) });
    if (!(localSQLiteDatabase instanceof SQLiteDatabase)) {
      localSQLiteDatabase.delete("InAppPurchase", paramea, null);
    }
    for (;;)
    {
      return;
      SQLiteInstrumentation.delete((SQLiteDatabase)localSQLiteDatabase, "InAppPurchase", paramea, null);
    }
  }
  
  public void b(ea paramea)
  {
    if (paramea == null) {
      return;
    }
    SQLiteDatabase localSQLiteDatabase;
    synchronized (mw)
    {
      localSQLiteDatabase = getWritableDatabase();
      if (localSQLiteDatabase == null) {
        return;
      }
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("product_id", paramea.sC);
    localContentValues.put("developer_payload", paramea.sB);
    localContentValues.put("record_time", Long.valueOf(SystemClock.elapsedRealtime()));
    if (!(localSQLiteDatabase instanceof SQLiteDatabase)) {}
    for (long l = localSQLiteDatabase.insert("InAppPurchase", null, localContentValues);; l = SQLiteInstrumentation.insert((SQLiteDatabase)localSQLiteDatabase, "InAppPurchase", null, localContentValues))
    {
      paramea.sA = l;
      if (getRecordCount() > 20000L) {
        cr();
      }
      return;
    }
  }
  
  /* Error */
  public void cr()
  {
    // Byte code:
    //   0: getstatic 43	com/google/android/gms/internal/ec:mw	Ljava/lang/Object;
    //   3: astore 4
    //   5: aload 4
    //   7: monitorenter
    //   8: aload_0
    //   9: invokevirtual 81	com/google/android/gms/internal/ec:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   12: astore_1
    //   13: aload_1
    //   14: ifnonnull +7 -> 21
    //   17: aload 4
    //   19: monitorexit
    //   20: return
    //   21: aload_1
    //   22: instanceof 95
    //   25: ifne +65 -> 90
    //   28: aload_1
    //   29: ldc 22
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: ldc -109
    //   38: ldc -107
    //   40: invokevirtual 153	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   43: astore_1
    //   44: aload_1
    //   45: ifnull +25 -> 70
    //   48: aload_1
    //   49: astore_2
    //   50: aload_1
    //   51: invokeinterface 157 1 0
    //   56: ifeq +14 -> 70
    //   59: aload_1
    //   60: astore_2
    //   61: aload_0
    //   62: aload_0
    //   63: aload_1
    //   64: invokevirtual 159	com/google/android/gms/internal/ec:a	(Landroid/database/Cursor;)Lcom/google/android/gms/internal/ea;
    //   67: invokevirtual 161	com/google/android/gms/internal/ec:a	(Lcom/google/android/gms/internal/ea;)V
    //   70: aload_1
    //   71: ifnull +9 -> 80
    //   74: aload_1
    //   75: invokeinterface 164 1 0
    //   80: aload 4
    //   82: monitorexit
    //   83: return
    //   84: astore_1
    //   85: aload 4
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    //   90: aload_1
    //   91: checkcast 95	android/database/sqlite/SQLiteDatabase
    //   94: ldc 22
    //   96: aconst_null
    //   97: aconst_null
    //   98: aconst_null
    //   99: aconst_null
    //   100: aconst_null
    //   101: ldc -109
    //   103: ldc -107
    //   105: invokestatic 167	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   108: astore_1
    //   109: goto -65 -> 44
    //   112: astore_3
    //   113: aconst_null
    //   114: astore_1
    //   115: aload_1
    //   116: astore_2
    //   117: new 169	java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   124: ldc -84
    //   126: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_3
    //   130: invokevirtual 179	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   133: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: invokestatic 188	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   142: aload_1
    //   143: ifnull -63 -> 80
    //   146: aload_1
    //   147: invokeinterface 164 1 0
    //   152: goto -72 -> 80
    //   155: aload_2
    //   156: ifnull +9 -> 165
    //   159: aload_2
    //   160: invokeinterface 164 1 0
    //   165: aload_1
    //   166: athrow
    //   167: astore_1
    //   168: goto -13 -> 155
    //   171: astore_3
    //   172: goto -57 -> 115
    //   175: astore_1
    //   176: aconst_null
    //   177: astore_2
    //   178: goto -23 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	this	ec
    //   12	63	1	localObject1	Object
    //   84	7	1	localObject2	Object
    //   108	58	1	localCursor	Cursor
    //   167	1	1	localObject3	Object
    //   175	1	1	localObject4	Object
    //   49	129	2	localObject5	Object
    //   112	18	3	localSQLiteException1	SQLiteException
    //   171	1	3	localSQLiteException2	SQLiteException
    //   3	83	4	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   8	13	84	finally
    //   17	20	84	finally
    //   74	80	84	finally
    //   80	83	84	finally
    //   85	88	84	finally
    //   146	152	84	finally
    //   159	165	84	finally
    //   165	167	84	finally
    //   21	44	112	android/database/sqlite/SQLiteException
    //   90	109	112	android/database/sqlite/SQLiteException
    //   50	59	167	finally
    //   61	70	167	finally
    //   117	142	167	finally
    //   50	59	171	android/database/sqlite/SQLiteException
    //   61	70	171	android/database/sqlite/SQLiteException
    //   21	44	175	finally
    //   90	109	175	finally
  }
  
  /* Error */
  public java.util.List<ea> d(long paramLong)
  {
    // Byte code:
    //   0: getstatic 43	com/google/android/gms/internal/ec:mw	Ljava/lang/Object;
    //   3: astore 7
    //   5: aload 7
    //   7: monitorenter
    //   8: new 192	java/util/LinkedList
    //   11: dup
    //   12: invokespecial 193	java/util/LinkedList:<init>	()V
    //   15: astore 8
    //   17: lload_1
    //   18: lconst_0
    //   19: lcmp
    //   20: ifgt +9 -> 29
    //   23: aload 7
    //   25: monitorexit
    //   26: aload 8
    //   28: areturn
    //   29: aload_0
    //   30: invokevirtual 81	com/google/android/gms/internal/ec:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: astore 4
    //   35: aload 4
    //   37: ifnonnull +9 -> 46
    //   40: aload 7
    //   42: monitorexit
    //   43: aload 8
    //   45: areturn
    //   46: lload_1
    //   47: invokestatic 196	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   50: astore 5
    //   52: aload 4
    //   54: instanceof 95
    //   57: ifne +87 -> 144
    //   60: aload 4
    //   62: ldc 22
    //   64: aconst_null
    //   65: aconst_null
    //   66: aconst_null
    //   67: aconst_null
    //   68: aconst_null
    //   69: ldc -109
    //   71: aload 5
    //   73: invokevirtual 153	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   76: astore 4
    //   78: aload 4
    //   80: astore 5
    //   82: aload 4
    //   84: invokeinterface 157 1 0
    //   89: ifeq +37 -> 126
    //   92: aload 4
    //   94: astore 5
    //   96: aload 8
    //   98: aload_0
    //   99: aload 4
    //   101: invokevirtual 159	com/google/android/gms/internal/ec:a	(Landroid/database/Cursor;)Lcom/google/android/gms/internal/ea;
    //   104: invokeinterface 202 2 0
    //   109: pop
    //   110: aload 4
    //   112: astore 5
    //   114: aload 4
    //   116: invokeinterface 205 1 0
    //   121: istore_3
    //   122: iload_3
    //   123: ifne -31 -> 92
    //   126: aload 4
    //   128: ifnull +10 -> 138
    //   131: aload 4
    //   133: invokeinterface 164 1 0
    //   138: aload 7
    //   140: monitorexit
    //   141: aload 8
    //   143: areturn
    //   144: aload 4
    //   146: checkcast 95	android/database/sqlite/SQLiteDatabase
    //   149: ldc 22
    //   151: aconst_null
    //   152: aconst_null
    //   153: aconst_null
    //   154: aconst_null
    //   155: aconst_null
    //   156: ldc -109
    //   158: aload 5
    //   160: invokestatic 167	com/newrelic/agent/android/instrumentation/SQLiteInstrumentation:query	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   163: astore 4
    //   165: goto -87 -> 78
    //   168: astore 6
    //   170: aconst_null
    //   171: astore 4
    //   173: aload 4
    //   175: astore 5
    //   177: new 169	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   184: ldc -49
    //   186: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload 6
    //   191: invokevirtual 179	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   194: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: invokestatic 188	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   203: aload 4
    //   205: ifnull -67 -> 138
    //   208: aload 4
    //   210: invokeinterface 164 1 0
    //   215: goto -77 -> 138
    //   218: astore 4
    //   220: aload 7
    //   222: monitorexit
    //   223: aload 4
    //   225: athrow
    //   226: astore 4
    //   228: aconst_null
    //   229: astore 5
    //   231: aload 5
    //   233: ifnull +10 -> 243
    //   236: aload 5
    //   238: invokeinterface 164 1 0
    //   243: aload 4
    //   245: athrow
    //   246: astore 4
    //   248: goto -17 -> 231
    //   251: astore 6
    //   253: goto -80 -> 173
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	256	0	this	ec
    //   0	256	1	paramLong	long
    //   121	2	3	bool	boolean
    //   33	176	4	localObject1	Object
    //   218	6	4	localObject2	Object
    //   226	18	4	localObject3	Object
    //   246	1	4	localObject4	Object
    //   50	187	5	localObject5	Object
    //   168	22	6	localSQLiteException1	SQLiteException
    //   251	1	6	localSQLiteException2	SQLiteException
    //   3	218	7	localObject6	Object
    //   15	127	8	localLinkedList	java.util.LinkedList
    // Exception table:
    //   from	to	target	type
    //   46	78	168	android/database/sqlite/SQLiteException
    //   144	165	168	android/database/sqlite/SQLiteException
    //   8	17	218	finally
    //   23	26	218	finally
    //   29	35	218	finally
    //   40	43	218	finally
    //   131	138	218	finally
    //   138	141	218	finally
    //   208	215	218	finally
    //   220	223	218	finally
    //   236	243	218	finally
    //   243	246	218	finally
    //   46	78	226	finally
    //   144	165	226	finally
    //   82	92	246	finally
    //   96	110	246	finally
    //   114	122	246	finally
    //   177	203	246	finally
    //   82	92	251	android/database/sqlite/SQLiteException
    //   96	110	251	android/database/sqlite/SQLiteException
    //   114	122	251	android/database/sqlite/SQLiteException
  }
  
  public int getRecordCount()
  {
    Object localObject5 = null;
    Cursor localCursor1 = null;
    SQLiteDatabase localSQLiteDatabase;
    synchronized (mw)
    {
      localSQLiteDatabase = getWritableDatabase();
      if (localSQLiteDatabase == null) {
        return 0;
      }
      localObject4 = localCursor1;
      localObject3 = localObject5;
    }
    try
    {
      if (!(localSQLiteDatabase instanceof SQLiteDatabase))
      {
        localObject4 = localCursor1;
        localObject3 = localObject5;
      }
      Cursor localCursor2;
      for (localCursor1 = localSQLiteDatabase.rawQuery("select count(*) from InAppPurchase", null);; localCursor2 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)localSQLiteDatabase, "select count(*) from InAppPurchase", null))
      {
        localObject4 = localCursor1;
        localObject3 = localCursor1;
        if (!localCursor1.moveToFirst()) {
          break;
        }
        localObject4 = localCursor1;
        localObject3 = localCursor1;
        int i = localCursor1.getInt(0);
        if (localCursor1 != null) {
          localCursor1.close();
        }
        return i;
        localObject1 = finally;
        throw ((Throwable)localObject1);
        localObject4 = localObject1;
        localObject3 = localObject5;
      }
      if (localCursor2 != null) {
        localCursor2.close();
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        localObject3 = localObject4;
        gs.W("Error getting record count" + localSQLiteException.getMessage());
        if (localObject4 != null) {
          ((Cursor)localObject4).close();
        }
      }
    }
    finally
    {
      if (localObject3 == null) {
        break label197;
      }
      ((Cursor)localObject3).close();
    }
    return 0;
  }
  
  public SQLiteDatabase getWritableDatabase()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.sH.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      gs.W("Error opening writable conversion tracking database");
    }
    return null;
  }
  
  public class a
    extends SQLiteOpenHelper
  {
    public a(Context paramContext, String paramString)
    {
      super(paramString, null, 4);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      String str = ec.cs();
      if (!(paramSQLiteDatabase instanceof SQLiteDatabase))
      {
        paramSQLiteDatabase.execSQL(str);
        return;
      }
      SQLiteInstrumentation.execSQL((SQLiteDatabase)paramSQLiteDatabase, str);
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      gs.U("Database updated from version " + paramInt1 + " to version " + paramInt2);
      if (!(paramSQLiteDatabase instanceof SQLiteDatabase)) {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
      }
      for (;;)
      {
        onCreate(paramSQLiteDatabase);
        return;
        SQLiteInstrumentation.execSQL((SQLiteDatabase)paramSQLiteDatabase, "DROP TABLE IF EXISTS InAppPurchase");
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */