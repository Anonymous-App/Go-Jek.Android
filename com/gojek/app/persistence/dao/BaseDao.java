package com.gojek.app.persistence.dao;

import android.content.Context;
import android.util.Log;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmMigrationNeededException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseDao<T extends RealmObject>
{
  private static final String TAG = BaseDao.class.getSimpleName();
  private Class<T> clazz;
  private Context context;
  private Realm realm;
  
  public BaseDao(Context paramContext)
  {
    this.context = paramContext;
    try
    {
      this.realm = Realm.getInstance(paramContext);
      this.clazz = ((Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
      return;
    }
    catch (RealmMigrationNeededException localRealmMigrationNeededException)
    {
      for (;;)
      {
        Log.e(TAG, "database need to upgrade");
        Realm.deleteRealmFile(paramContext);
        this.realm = Realm.getInstance(paramContext);
      }
    }
  }
  
  public void cancelTransaction()
  {
    this.realm.cancelTransaction();
  }
  
  public void delete(Integer paramInteger)
    throws RealmException
  {
    paramInteger = findOne(paramInteger);
    if (paramInteger != null)
    {
      this.realm.beginTransaction();
      paramInteger.removeFromRealm();
      this.realm.commitTransaction();
    }
  }
  
  public List<T> findAll()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = this.realm.where(this.clazz).findAll();
    if ((localObject != null) && (((RealmResults)localObject).size() > 0))
    {
      localObject = ((RealmResults)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localArrayList.add(((Iterator)localObject).next());
      }
    }
    return localArrayList;
  }
  
  public T findOne(Integer paramInteger)
  {
    paramInteger = this.realm.where(this.clazz).equalTo("id", paramInteger.intValue()).findAll();
    if ((paramInteger != null) && (paramInteger.size() > 0)) {
      return paramInteger.get(0);
    }
    return null;
  }
  
  public void finishTransaction()
  {
    this.realm.commitTransaction();
  }
  
  public Realm getDB()
  {
    if (this.realm == null) {
      this.realm = Realm.getInstance(this.context);
    }
    return this.realm;
  }
  
  public void save(T paramT)
    throws RealmException
  {
    this.realm.beginTransaction();
    this.realm.copyToRealm(paramT);
    this.realm.commitTransaction();
  }
  
  public void startTransaction()
  {
    this.realm.beginTransaction();
  }
  
  public void update(T paramT, Integer paramInteger)
    throws RealmException
  {
    if (findOne(paramInteger) != null)
    {
      this.realm.beginTransaction();
      this.realm.copyToRealmOrUpdate(paramT);
      this.realm.commitTransaction();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/persistence/dao/BaseDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */