package de.greenrobot.dao;

import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import de.greenrobot.dao.identityscope.IdentityScope;
import de.greenrobot.dao.identityscope.IdentityScopeLong;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.FastCursor;
import de.greenrobot.dao.internal.TableStatements;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractDao<T, K>
{
  protected final DaoConfig config;
  protected final SQLiteDatabase db;
  protected IdentityScope<K, T> identityScope;
  protected IdentityScopeLong<T> identityScopeLong;
  protected final int pkOrdinal;
  protected final AbstractDaoSession session;
  protected TableStatements statements;
  
  public AbstractDao(DaoConfig paramDaoConfig)
  {
    this(paramDaoConfig, null);
  }
  
  public AbstractDao(DaoConfig paramDaoConfig, AbstractDaoSession paramAbstractDaoSession)
  {
    this.config = paramDaoConfig;
    this.session = paramAbstractDaoSession;
    this.db = paramDaoConfig.db;
    this.identityScope = paramDaoConfig.getIdentityScope();
    if ((this.identityScope instanceof IdentityScopeLong)) {
      this.identityScopeLong = ((IdentityScopeLong)this.identityScope);
    }
    this.statements = paramDaoConfig.statements;
    if (paramDaoConfig.pkProperty != null) {}
    for (int i = paramDaoConfig.pkProperty.ordinal;; i = -1)
    {
      this.pkOrdinal = i;
      return;
    }
  }
  
  private void deleteByKeyInsideSynchronized(K paramK, SQLiteStatement paramSQLiteStatement)
  {
    if ((paramK instanceof Long)) {
      paramSQLiteStatement.bindLong(1, ((Long)paramK).longValue());
    }
    for (;;)
    {
      paramSQLiteStatement.execute();
      return;
      if (paramK == null) {
        throw new DaoException("Cannot delete entity, key is null");
      }
      paramSQLiteStatement.bindString(1, paramK.toString());
    }
  }
  
  private void deleteInTxInternal(Iterable<T> paramIterable, Iterable<K> paramIterable1)
  {
    assertSinglePk();
    SQLiteStatement localSQLiteStatement = this.statements.getDeleteStatement();
    ArrayList localArrayList = null;
    this.db.beginTransaction();
    try
    {
      try
      {
        if (this.identityScope != null)
        {
          this.identityScope.lock();
          localArrayList = new ArrayList();
        }
        if (paramIterable != null)
        {
          try
          {
            paramIterable = paramIterable.iterator();
            while (paramIterable.hasNext())
            {
              Object localObject = getKeyVerified(paramIterable.next());
              deleteByKeyInsideSynchronized(localObject, localSQLiteStatement);
              if (localArrayList != null) {
                localArrayList.add(localObject);
              }
            }
            paramIterable = finally;
          }
          finally
          {
            if (this.identityScope != null) {
              this.identityScope.unlock();
            }
          }
          paramIterable = finally;
        }
      }
      finally {}
      if (paramIterable1 == null) {
        break label189;
      }
    }
    finally
    {
      this.db.endTransaction();
    }
    paramIterable = paramIterable1.iterator();
    while (paramIterable.hasNext())
    {
      paramIterable1 = paramIterable.next();
      deleteByKeyInsideSynchronized(paramIterable1, localSQLiteStatement);
      if (localArrayList != null) {
        localArrayList.add(paramIterable1);
      }
    }
    label189:
    if (this.identityScope != null) {
      this.identityScope.unlock();
    }
    this.db.setTransactionSuccessful();
    if ((localArrayList != null) && (this.identityScope != null)) {
      this.identityScope.remove(localArrayList);
    }
    this.db.endTransaction();
  }
  
  /* Error */
  private long executeInsert(T paramT, SQLiteStatement paramSQLiteStatement)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   4: invokevirtual 168	android/database/sqlite/SQLiteDatabase:isDbLockedByCurrentThread	()Z
    //   7: ifeq +32 -> 39
    //   10: aload_2
    //   11: monitorenter
    //   12: aload_0
    //   13: aload_2
    //   14: aload_1
    //   15: invokevirtual 172	de/greenrobot/dao/AbstractDao:bindValues	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/Object;)V
    //   18: aload_2
    //   19: invokevirtual 174	android/database/sqlite/SQLiteStatement:executeInsert	()J
    //   22: lstore_3
    //   23: aload_2
    //   24: monitorexit
    //   25: aload_0
    //   26: aload_1
    //   27: lload_3
    //   28: iconst_1
    //   29: invokevirtual 178	de/greenrobot/dao/AbstractDao:updateKeyAfterInsertAndAttach	(Ljava/lang/Object;JZ)V
    //   32: lload_3
    //   33: lreturn
    //   34: astore_1
    //   35: aload_2
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    //   39: aload_0
    //   40: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   43: invokevirtual 113	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   46: aload_2
    //   47: monitorenter
    //   48: aload_0
    //   49: aload_2
    //   50: aload_1
    //   51: invokevirtual 172	de/greenrobot/dao/AbstractDao:bindValues	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/Object;)V
    //   54: aload_2
    //   55: invokevirtual 174	android/database/sqlite/SQLiteStatement:executeInsert	()J
    //   58: lstore_3
    //   59: aload_2
    //   60: monitorexit
    //   61: aload_0
    //   62: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   65: invokevirtual 158	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   68: aload_0
    //   69: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   72: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   75: goto -50 -> 25
    //   78: astore_1
    //   79: aload_2
    //   80: monitorexit
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: aload_0
    //   85: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   88: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   91: aload_1
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	AbstractDao
    //   0	93	1	paramT	T
    //   0	93	2	paramSQLiteStatement	SQLiteStatement
    //   22	37	3	l	long
    // Exception table:
    //   from	to	target	type
    //   12	25	34	finally
    //   35	37	34	finally
    //   48	61	78	finally
    //   79	81	78	finally
    //   46	48	83	finally
    //   61	68	83	finally
    //   81	83	83	finally
  }
  
  private void executeInsertInTx(SQLiteStatement paramSQLiteStatement, Iterable<T> paramIterable, boolean paramBoolean)
  {
    this.db.beginTransaction();
    label108:
    for (;;)
    {
      try
      {
        try
        {
          if (this.identityScope != null) {
            this.identityScope.lock();
          }
          try
          {
            paramIterable = paramIterable.iterator();
            if (!paramIterable.hasNext()) {
              break;
            }
            Object localObject = paramIterable.next();
            bindValues(paramSQLiteStatement, localObject);
            if (!paramBoolean) {
              break label108;
            }
            updateKeyAfterInsertAndAttach(localObject, paramSQLiteStatement.executeInsert(), false);
            continue;
            paramIterable = finally;
          }
          finally
          {
            if (this.identityScope != null) {
              this.identityScope.unlock();
            }
          }
          paramSQLiteStatement = finally;
        }
        finally {}
        paramSQLiteStatement.execute();
      }
      finally
      {
        this.db.endTransaction();
      }
    }
    if (this.identityScope != null) {
      this.identityScope.unlock();
    }
    this.db.setTransactionSuccessful();
    this.db.endTransaction();
  }
  
  protected void assertSinglePk()
  {
    if (this.config.pkColumns.length != 1) {
      throw new DaoException(this + " (" + this.config.tablename + ") does not have a single-column primary key");
    }
  }
  
  protected void attachEntity(T paramT) {}
  
  protected final void attachEntity(K paramK, T paramT, boolean paramBoolean)
  {
    attachEntity(paramT);
    if ((this.identityScope != null) && (paramK != null))
    {
      if (paramBoolean) {
        this.identityScope.put(paramK, paramT);
      }
    }
    else {
      return;
    }
    this.identityScope.putNoLock(paramK, paramT);
  }
  
  protected abstract void bindValues(SQLiteStatement paramSQLiteStatement, T paramT);
  
  public long count()
  {
    return DatabaseUtils.queryNumEntries(this.db, '\'' + this.config.tablename + '\'');
  }
  
  public void delete(T paramT)
  {
    assertSinglePk();
    deleteByKey(getKeyVerified(paramT));
  }
  
  public void deleteAll()
  {
    SQLiteDatabase localSQLiteDatabase = this.db;
    String str = "DELETE FROM '" + this.config.tablename + "'";
    if (!(localSQLiteDatabase instanceof SQLiteDatabase)) {
      localSQLiteDatabase.execSQL(str);
    }
    for (;;)
    {
      if (this.identityScope != null) {
        this.identityScope.clear();
      }
      return;
      SQLiteInstrumentation.execSQL((SQLiteDatabase)localSQLiteDatabase, str);
    }
  }
  
  /* Error */
  public void deleteByKey(K paramK)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 102	de/greenrobot/dao/AbstractDao:assertSinglePk	()V
    //   4: aload_0
    //   5: getfield 52	de/greenrobot/dao/AbstractDao:statements	Lde/greenrobot/dao/internal/TableStatements;
    //   8: invokevirtual 108	de/greenrobot/dao/internal/TableStatements:getDeleteStatement	()Landroid/database/sqlite/SQLiteStatement;
    //   11: astore_2
    //   12: aload_0
    //   13: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   16: invokevirtual 168	android/database/sqlite/SQLiteDatabase:isDbLockedByCurrentThread	()Z
    //   19: ifeq +36 -> 55
    //   22: aload_2
    //   23: monitorenter
    //   24: aload_0
    //   25: aload_1
    //   26: aload_2
    //   27: invokespecial 143	de/greenrobot/dao/AbstractDao:deleteByKeyInsideSynchronized	(Ljava/lang/Object;Landroid/database/sqlite/SQLiteStatement;)V
    //   30: aload_2
    //   31: monitorexit
    //   32: aload_0
    //   33: getfield 45	de/greenrobot/dao/AbstractDao:identityScope	Lde/greenrobot/dao/identityscope/IdentityScope;
    //   36: ifnull +13 -> 49
    //   39: aload_0
    //   40: getfield 45	de/greenrobot/dao/AbstractDao:identityScope	Lde/greenrobot/dao/identityscope/IdentityScope;
    //   43: aload_1
    //   44: invokeinterface 251 2 0
    //   49: return
    //   50: astore_1
    //   51: aload_2
    //   52: monitorexit
    //   53: aload_1
    //   54: athrow
    //   55: aload_0
    //   56: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   59: invokevirtual 113	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   62: aload_2
    //   63: monitorenter
    //   64: aload_0
    //   65: aload_1
    //   66: aload_2
    //   67: invokespecial 143	de/greenrobot/dao/AbstractDao:deleteByKeyInsideSynchronized	(Ljava/lang/Object;Landroid/database/sqlite/SQLiteStatement;)V
    //   70: aload_2
    //   71: monitorexit
    //   72: aload_0
    //   73: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   76: invokevirtual 158	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   79: aload_0
    //   80: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   83: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   86: goto -54 -> 32
    //   89: astore_1
    //   90: aload_2
    //   91: monitorexit
    //   92: aload_1
    //   93: athrow
    //   94: astore_1
    //   95: aload_0
    //   96: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   99: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   102: aload_1
    //   103: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	104	0	this	AbstractDao
    //   0	104	1	paramK	K
    //   11	80	2	localSQLiteStatement	SQLiteStatement
    // Exception table:
    //   from	to	target	type
    //   24	32	50	finally
    //   51	53	50	finally
    //   64	72	89	finally
    //   90	92	89	finally
    //   62	64	94	finally
    //   72	79	94	finally
    //   92	94	94	finally
  }
  
  public void deleteByKeyInTx(Iterable<K> paramIterable)
  {
    deleteInTxInternal(null, paramIterable);
  }
  
  public void deleteByKeyInTx(K... paramVarArgs)
  {
    deleteInTxInternal(null, Arrays.asList(paramVarArgs));
  }
  
  public void deleteInTx(Iterable<T> paramIterable)
  {
    deleteInTxInternal(paramIterable, null);
  }
  
  public void deleteInTx(T... paramVarArgs)
  {
    deleteInTxInternal(Arrays.asList(paramVarArgs), null);
  }
  
  public boolean detach(T paramT)
  {
    if (this.identityScope != null)
    {
      Object localObject = getKeyVerified(paramT);
      return this.identityScope.detach(localObject, paramT);
    }
    return false;
  }
  
  public String[] getAllColumns()
  {
    return this.config.allColumns;
  }
  
  public SQLiteDatabase getDatabase()
  {
    return this.db;
  }
  
  protected abstract K getKey(T paramT);
  
  protected K getKeyVerified(T paramT)
  {
    Object localObject = getKey(paramT);
    if (localObject == null)
    {
      if (paramT == null) {
        throw new NullPointerException("Entity may not be null");
      }
      throw new DaoException("Entity has no key");
    }
    return (K)localObject;
  }
  
  public String[] getNonPkColumns()
  {
    return this.config.nonPkColumns;
  }
  
  public String[] getPkColumns()
  {
    return this.config.pkColumns;
  }
  
  public Property getPkProperty()
  {
    return this.config.pkProperty;
  }
  
  public Property[] getProperties()
  {
    return this.config.properties;
  }
  
  public AbstractDaoSession getSession()
  {
    return this.session;
  }
  
  TableStatements getStatements()
  {
    return this.config.statements;
  }
  
  public String getTablename()
  {
    return this.config.tablename;
  }
  
  public long insert(T paramT)
  {
    return executeInsert(paramT, this.statements.getInsertStatement());
  }
  
  public void insertInTx(Iterable<T> paramIterable)
  {
    insertInTx(paramIterable, isEntityUpdateable());
  }
  
  public void insertInTx(Iterable<T> paramIterable, boolean paramBoolean)
  {
    executeInsertInTx(this.statements.getInsertStatement(), paramIterable, paramBoolean);
  }
  
  public void insertInTx(T... paramVarArgs)
  {
    insertInTx(Arrays.asList(paramVarArgs), isEntityUpdateable());
  }
  
  public long insertOrReplace(T paramT)
  {
    return executeInsert(paramT, this.statements.getInsertOrReplaceStatement());
  }
  
  public void insertOrReplaceInTx(Iterable<T> paramIterable)
  {
    insertOrReplaceInTx(paramIterable, isEntityUpdateable());
  }
  
  public void insertOrReplaceInTx(Iterable<T> paramIterable, boolean paramBoolean)
  {
    executeInsertInTx(this.statements.getInsertOrReplaceStatement(), paramIterable, paramBoolean);
  }
  
  public void insertOrReplaceInTx(T... paramVarArgs)
  {
    insertOrReplaceInTx(Arrays.asList(paramVarArgs), isEntityUpdateable());
  }
  
  /* Error */
  public long insertWithoutSettingPk(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 52	de/greenrobot/dao/AbstractDao:statements	Lde/greenrobot/dao/internal/TableStatements;
    //   4: invokevirtual 313	de/greenrobot/dao/internal/TableStatements:getInsertStatement	()Landroid/database/sqlite/SQLiteStatement;
    //   7: astore 4
    //   9: aload_0
    //   10: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   13: invokevirtual 168	android/database/sqlite/SQLiteDatabase:isDbLockedByCurrentThread	()Z
    //   16: ifeq +30 -> 46
    //   19: aload 4
    //   21: monitorenter
    //   22: aload_0
    //   23: aload 4
    //   25: aload_1
    //   26: invokevirtual 172	de/greenrobot/dao/AbstractDao:bindValues	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/Object;)V
    //   29: aload 4
    //   31: invokevirtual 174	android/database/sqlite/SQLiteStatement:executeInsert	()J
    //   34: lstore_2
    //   35: aload 4
    //   37: monitorexit
    //   38: lload_2
    //   39: lreturn
    //   40: astore_1
    //   41: aload 4
    //   43: monitorexit
    //   44: aload_1
    //   45: athrow
    //   46: aload_0
    //   47: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   50: invokevirtual 113	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   53: aload 4
    //   55: monitorenter
    //   56: aload_0
    //   57: aload 4
    //   59: aload_1
    //   60: invokevirtual 172	de/greenrobot/dao/AbstractDao:bindValues	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/Object;)V
    //   63: aload 4
    //   65: invokevirtual 174	android/database/sqlite/SQLiteStatement:executeInsert	()J
    //   68: lstore_2
    //   69: aload 4
    //   71: monitorexit
    //   72: aload_0
    //   73: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   76: invokevirtual 158	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   79: aload_0
    //   80: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   83: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   86: lload_2
    //   87: lreturn
    //   88: astore_1
    //   89: aload 4
    //   91: monitorexit
    //   92: aload_1
    //   93: athrow
    //   94: astore_1
    //   95: aload_0
    //   96: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   99: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   102: aload_1
    //   103: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	104	0	this	AbstractDao
    //   0	104	1	paramT	T
    //   34	53	2	l	long
    //   7	83	4	localSQLiteStatement	SQLiteStatement
    // Exception table:
    //   from	to	target	type
    //   22	38	40	finally
    //   41	44	40	finally
    //   56	72	88	finally
    //   89	92	88	finally
    //   53	56	94	finally
    //   72	79	94	finally
    //   92	94	94	finally
  }
  
  protected abstract boolean isEntityUpdateable();
  
  public T load(K paramK)
  {
    assertSinglePk();
    if (paramK == null) {
      localObject1 = null;
    }
    do
    {
      return (T)localObject1;
      if (this.identityScope == null) {
        break;
      }
      localObject2 = this.identityScope.get(paramK);
      localObject1 = localObject2;
    } while (localObject2 != null);
    Object localObject1 = this.statements.getSelectByKey();
    Object localObject2 = new String[1];
    localObject2[0] = paramK.toString();
    paramK = this.db;
    if (!(paramK instanceof SQLiteDatabase)) {}
    for (paramK = paramK.rawQuery((String)localObject1, (String[])localObject2);; paramK = SQLiteInstrumentation.rawQuery((SQLiteDatabase)paramK, (String)localObject1, (String[])localObject2)) {
      return (T)loadUniqueAndCloseCursor(paramK);
    }
  }
  
  public List<T> loadAll()
  {
    Object localObject = this.db;
    String str = this.statements.getSelectAll();
    if (!(localObject instanceof SQLiteDatabase)) {}
    for (localObject = ((SQLiteDatabase)localObject).rawQuery(str, null);; localObject = SQLiteInstrumentation.rawQuery((SQLiteDatabase)localObject, str, null)) {
      return loadAllAndCloseCursor((Cursor)localObject);
    }
  }
  
  protected List<T> loadAllAndCloseCursor(Cursor paramCursor)
  {
    try
    {
      List localList = loadAllFromCursor(paramCursor);
      return localList;
    }
    finally
    {
      paramCursor.close();
    }
  }
  
  protected List<T> loadAllFromCursor(Cursor paramCursor)
  {
    int i = paramCursor.getCount();
    ArrayList localArrayList = new ArrayList(i);
    Object localObject = paramCursor;
    CursorWindow localCursorWindow;
    if ((paramCursor instanceof CrossProcessCursor))
    {
      localCursorWindow = ((CrossProcessCursor)paramCursor).getWindow();
      localObject = paramCursor;
      if (localCursorWindow != null) {
        if (localCursorWindow.getNumRows() != i) {
          break label149;
        }
      }
    }
    for (localObject = new FastCursor(localCursorWindow);; localObject = paramCursor)
    {
      if (((Cursor)localObject).moveToFirst()) {
        if (this.identityScope != null)
        {
          this.identityScope.lock();
          this.identityScope.reserveRoom(i);
        }
      }
      try
      {
        boolean bool;
        do
        {
          localArrayList.add(loadCurrent((Cursor)localObject, 0, false));
          bool = ((Cursor)localObject).moveToNext();
        } while (bool);
        return localArrayList;
      }
      finally
      {
        label149:
        if (this.identityScope == null) {
          break;
        }
        this.identityScope.unlock();
      }
      DaoLog.d("Window vs. result size: " + localCursorWindow.getNumRows() + "/" + i);
    }
  }
  
  public T loadByRowId(long paramLong)
  {
    Object localObject = new String[1];
    localObject[0] = Long.toString(paramLong);
    SQLiteDatabase localSQLiteDatabase = this.db;
    String str = this.statements.getSelectByRowId();
    if (!(localSQLiteDatabase instanceof SQLiteDatabase)) {}
    for (localObject = localSQLiteDatabase.rawQuery(str, (String[])localObject);; localObject = SQLiteInstrumentation.rawQuery((SQLiteDatabase)localSQLiteDatabase, str, (String[])localObject)) {
      return (T)loadUniqueAndCloseCursor((Cursor)localObject);
    }
  }
  
  protected final T loadCurrent(Cursor paramCursor, int paramInt, boolean paramBoolean)
  {
    Object localObject1 = null;
    if (this.identityScopeLong != null) {
      if ((paramInt == 0) || (!paramCursor.isNull(this.pkOrdinal + paramInt))) {}
    }
    label112:
    do
    {
      Object localObject3;
      do
      {
        return (T)localObject1;
        long l = paramCursor.getLong(this.pkOrdinal + paramInt);
        if (paramBoolean) {}
        for (localObject2 = this.identityScopeLong.get2(l);; localObject2 = this.identityScopeLong.get2NoLock(l))
        {
          localObject1 = localObject2;
          if (localObject2 != null) {
            break;
          }
          paramCursor = readEntity(paramCursor, paramInt);
          attachEntity(paramCursor);
          if (!paramBoolean) {
            break label112;
          }
          this.identityScopeLong.put2(l, paramCursor);
          return paramCursor;
        }
        this.identityScopeLong.put2NoLock(l, paramCursor);
        return paramCursor;
        if (this.identityScope == null) {
          break;
        }
        localObject3 = readKey(paramCursor, paramInt);
      } while ((paramInt != 0) && (localObject3 == null));
      if (paramBoolean) {}
      for (Object localObject2 = this.identityScope.get(localObject3);; localObject2 = this.identityScope.getNoLock(localObject3))
      {
        localObject1 = localObject2;
        if (localObject2 != null) {
          break;
        }
        paramCursor = readEntity(paramCursor, paramInt);
        attachEntity(localObject3, paramCursor, paramBoolean);
        return paramCursor;
      }
    } while ((paramInt != 0) && (readKey(paramCursor, paramInt) == null));
    paramCursor = readEntity(paramCursor, paramInt);
    attachEntity(paramCursor);
    return paramCursor;
  }
  
  protected final <O> O loadCurrentOther(AbstractDao<O, ?> paramAbstractDao, Cursor paramCursor, int paramInt)
  {
    return (O)paramAbstractDao.loadCurrent(paramCursor, paramInt, true);
  }
  
  protected T loadUnique(Cursor paramCursor)
  {
    if (!paramCursor.moveToFirst()) {
      return null;
    }
    if (!paramCursor.isLast()) {
      throw new DaoException("Expected unique result, but count was " + paramCursor.getCount());
    }
    return (T)loadCurrent(paramCursor, 0, true);
  }
  
  protected T loadUniqueAndCloseCursor(Cursor paramCursor)
  {
    try
    {
      Object localObject1 = loadUnique(paramCursor);
      return (T)localObject1;
    }
    finally
    {
      paramCursor.close();
    }
  }
  
  public QueryBuilder<T> queryBuilder()
  {
    return QueryBuilder.internalCreate(this);
  }
  
  public List<T> queryRaw(String paramString, String... paramVarArgs)
  {
    SQLiteDatabase localSQLiteDatabase = this.db;
    paramString = this.statements.getSelectAll() + paramString;
    if (!(localSQLiteDatabase instanceof SQLiteDatabase)) {}
    for (paramString = localSQLiteDatabase.rawQuery(paramString, paramVarArgs);; paramString = SQLiteInstrumentation.rawQuery((SQLiteDatabase)localSQLiteDatabase, paramString, paramVarArgs)) {
      return loadAllAndCloseCursor(paramString);
    }
  }
  
  public Query<T> queryRawCreate(String paramString, Object... paramVarArgs)
  {
    return queryRawCreateListArgs(paramString, Arrays.asList(paramVarArgs));
  }
  
  public Query<T> queryRawCreateListArgs(String paramString, Collection<Object> paramCollection)
  {
    return Query.internalCreate(this, this.statements.getSelectAll() + paramString, paramCollection.toArray());
  }
  
  protected abstract T readEntity(Cursor paramCursor, int paramInt);
  
  protected abstract void readEntity(Cursor paramCursor, T paramT, int paramInt);
  
  protected abstract K readKey(Cursor paramCursor, int paramInt);
  
  public void refresh(T paramT)
  {
    assertSinglePk();
    Object localObject2 = getKeyVerified(paramT);
    Object localObject1 = this.statements.getSelectByKey();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = localObject2.toString();
    SQLiteDatabase localSQLiteDatabase = this.db;
    if (!(localSQLiteDatabase instanceof SQLiteDatabase)) {}
    for (localObject1 = localSQLiteDatabase.rawQuery((String)localObject1, arrayOfString);; localObject1 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)localSQLiteDatabase, (String)localObject1, arrayOfString)) {
      try
      {
        if (((Cursor)localObject1).moveToFirst()) {
          break;
        }
        throw new DaoException("Entity does not exist in the database anymore: " + paramT.getClass() + " with key " + localObject2);
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
    }
    if (!((Cursor)localObject1).isLast()) {
      throw new DaoException("Expected unique result, but count was " + ((Cursor)localObject1).getCount());
    }
    readEntity((Cursor)localObject1, paramT, 0);
    attachEntity(localObject2, paramT, true);
    ((Cursor)localObject1).close();
  }
  
  /* Error */
  public void update(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 102	de/greenrobot/dao/AbstractDao:assertSinglePk	()V
    //   4: aload_0
    //   5: getfield 52	de/greenrobot/dao/AbstractDao:statements	Lde/greenrobot/dao/internal/TableStatements;
    //   8: invokevirtual 525	de/greenrobot/dao/internal/TableStatements:getUpdateStatement	()Landroid/database/sqlite/SQLiteStatement;
    //   11: astore_2
    //   12: aload_0
    //   13: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   16: invokevirtual 168	android/database/sqlite/SQLiteDatabase:isDbLockedByCurrentThread	()Z
    //   19: ifeq +20 -> 39
    //   22: aload_2
    //   23: monitorenter
    //   24: aload_0
    //   25: aload_1
    //   26: aload_2
    //   27: iconst_1
    //   28: invokevirtual 529	de/greenrobot/dao/AbstractDao:updateInsideSynchronized	(Ljava/lang/Object;Landroid/database/sqlite/SQLiteStatement;Z)V
    //   31: aload_2
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: aload_2
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    //   39: aload_0
    //   40: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   43: invokevirtual 113	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   46: aload_2
    //   47: monitorenter
    //   48: aload_0
    //   49: aload_1
    //   50: aload_2
    //   51: iconst_1
    //   52: invokevirtual 529	de/greenrobot/dao/AbstractDao:updateInsideSynchronized	(Ljava/lang/Object;Landroid/database/sqlite/SQLiteStatement;Z)V
    //   55: aload_2
    //   56: monitorexit
    //   57: aload_0
    //   58: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   61: invokevirtual 158	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   64: aload_0
    //   65: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   68: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   71: return
    //   72: astore_1
    //   73: aload_2
    //   74: monitorexit
    //   75: aload_1
    //   76: athrow
    //   77: astore_1
    //   78: aload_0
    //   79: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   82: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   85: aload_1
    //   86: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	AbstractDao
    //   0	87	1	paramT	T
    //   11	63	2	localSQLiteStatement	SQLiteStatement
    // Exception table:
    //   from	to	target	type
    //   24	33	34	finally
    //   35	37	34	finally
    //   48	57	72	finally
    //   73	75	72	finally
    //   46	48	77	finally
    //   57	64	77	finally
    //   75	77	77	finally
  }
  
  /* Error */
  public void updateInTx(Iterable<T> paramIterable)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 52	de/greenrobot/dao/AbstractDao:statements	Lde/greenrobot/dao/internal/TableStatements;
    //   4: invokevirtual 525	de/greenrobot/dao/internal/TableStatements:getUpdateStatement	()Landroid/database/sqlite/SQLiteStatement;
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   12: invokevirtual 113	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   15: aload_2
    //   16: monitorenter
    //   17: aload_0
    //   18: getfield 45	de/greenrobot/dao/AbstractDao:identityScope	Lde/greenrobot/dao/identityscope/IdentityScope;
    //   21: ifnull +12 -> 33
    //   24: aload_0
    //   25: getfield 45	de/greenrobot/dao/AbstractDao:identityScope	Lde/greenrobot/dao/identityscope/IdentityScope;
    //   28: invokeinterface 118 1 0
    //   33: aload_1
    //   34: invokeinterface 127 1 0
    //   39: astore_1
    //   40: aload_1
    //   41: invokeinterface 133 1 0
    //   46: ifeq +51 -> 97
    //   49: aload_0
    //   50: aload_1
    //   51: invokeinterface 137 1 0
    //   56: aload_2
    //   57: iconst_0
    //   58: invokevirtual 529	de/greenrobot/dao/AbstractDao:updateInsideSynchronized	(Ljava/lang/Object;Landroid/database/sqlite/SQLiteStatement;Z)V
    //   61: goto -21 -> 40
    //   64: astore_1
    //   65: aload_0
    //   66: getfield 45	de/greenrobot/dao/AbstractDao:identityScope	Lde/greenrobot/dao/identityscope/IdentityScope;
    //   69: ifnull +12 -> 81
    //   72: aload_0
    //   73: getfield 45	de/greenrobot/dao/AbstractDao:identityScope	Lde/greenrobot/dao/identityscope/IdentityScope;
    //   76: invokeinterface 152 1 0
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: aload_2
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    //   88: astore_1
    //   89: aload_0
    //   90: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   93: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   96: return
    //   97: aload_0
    //   98: getfield 45	de/greenrobot/dao/AbstractDao:identityScope	Lde/greenrobot/dao/identityscope/IdentityScope;
    //   101: ifnull +12 -> 113
    //   104: aload_0
    //   105: getfield 45	de/greenrobot/dao/AbstractDao:identityScope	Lde/greenrobot/dao/identityscope/IdentityScope;
    //   108: invokeinterface 152 1 0
    //   113: aload_2
    //   114: monitorexit
    //   115: aload_0
    //   116: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   119: invokevirtual 158	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   122: aload_0
    //   123: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   126: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   129: return
    //   130: astore_1
    //   131: iconst_0
    //   132: ifeq +13 -> 145
    //   135: ldc_w 534
    //   138: aload_1
    //   139: invokestatic 538	de/greenrobot/dao/DaoLog:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   142: pop
    //   143: aconst_null
    //   144: athrow
    //   145: aload_1
    //   146: athrow
    //   147: astore_2
    //   148: aload_1
    //   149: ifnull +13 -> 162
    //   152: ldc_w 534
    //   155: aload_2
    //   156: invokestatic 538	de/greenrobot/dao/DaoLog:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   159: pop
    //   160: aload_1
    //   161: athrow
    //   162: aload_2
    //   163: athrow
    //   164: astore_1
    //   165: aload_0
    //   166: getfield 39	de/greenrobot/dao/AbstractDao:db	Landroid/database/sqlite/SQLiteDatabase;
    //   169: invokevirtual 155	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   172: aload_1
    //   173: athrow
    //   174: astore_1
    //   175: iconst_0
    //   176: ifeq +13 -> 189
    //   179: ldc_w 534
    //   182: aload_1
    //   183: invokestatic 538	de/greenrobot/dao/DaoLog:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   186: pop
    //   187: aconst_null
    //   188: athrow
    //   189: aload_1
    //   190: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	this	AbstractDao
    //   0	191	1	paramIterable	Iterable<T>
    //   7	107	2	localSQLiteStatement	SQLiteStatement
    //   147	16	2	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   33	40	64	finally
    //   40	61	64	finally
    //   17	33	83	finally
    //   65	81	83	finally
    //   81	83	83	finally
    //   84	86	83	finally
    //   97	113	83	finally
    //   113	115	83	finally
    //   15	17	88	java/lang/RuntimeException
    //   86	88	88	java/lang/RuntimeException
    //   115	122	88	java/lang/RuntimeException
    //   122	129	130	java/lang/RuntimeException
    //   89	96	147	java/lang/RuntimeException
    //   15	17	164	finally
    //   86	88	164	finally
    //   115	122	164	finally
    //   165	172	174	java/lang/RuntimeException
  }
  
  public void updateInTx(T... paramVarArgs)
  {
    updateInTx(Arrays.asList(paramVarArgs));
  }
  
  protected void updateInsideSynchronized(T paramT, SQLiteStatement paramSQLiteStatement, boolean paramBoolean)
  {
    bindValues(paramSQLiteStatement, paramT);
    int i = this.config.allColumns.length + 1;
    Object localObject = getKey(paramT);
    if ((localObject instanceof Long)) {
      paramSQLiteStatement.bindLong(i, ((Long)localObject).longValue());
    }
    for (;;)
    {
      paramSQLiteStatement.execute();
      attachEntity(localObject, paramT, paramBoolean);
      return;
      if (localObject == null) {
        throw new DaoException("Cannot update entity without key - was it inserted before?");
      }
      paramSQLiteStatement.bindString(i, localObject.toString());
    }
  }
  
  protected abstract K updateKeyAfterInsert(T paramT, long paramLong);
  
  protected void updateKeyAfterInsertAndAttach(T paramT, long paramLong, boolean paramBoolean)
  {
    if (paramLong != -1L)
    {
      attachEntity(updateKeyAfterInsert(paramT, paramLong), paramT, paramBoolean);
      return;
    }
    DaoLog.w("Could not insert row (executeInsert returned -1)");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/de/greenrobot/dao/AbstractDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */