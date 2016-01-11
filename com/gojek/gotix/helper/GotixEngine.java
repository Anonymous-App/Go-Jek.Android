package com.gojek.gotix.helper;

import android.content.Context;
import com.gojek.gotix.DaoMaster;
import com.gojek.gotix.DaoMaster.DevOpenHelper;
import com.gojek.gotix.DaoSession;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.helper.GojekDataHelper;

public class GotixEngine
{
  private static DaoMaster daoMaster;
  private static DaoSession daoSession;
  private static DaoMaster.DevOpenHelper database;
  private static GotixEngine instance = null;
  private Context context;
  
  private GotixEngine(Context paramContext)
  {
    this.context = paramContext;
    DataHelper.init(paramContext);
    GojekDataHelper.init(paramContext);
    FontFaceHelper.initFace(paramContext);
    createDatabase();
  }
  
  private void createDatabase()
  {
    database = new DaoMaster.DevOpenHelper(this.context, "gotix-tickets", null);
    daoMaster = new DaoMaster(database.getWritableDatabase());
    daoSession = daoMaster.newSession();
  }
  
  public static void createInstance(Context paramContext)
  {
    if (instance == null) {
      instance = new GotixEngine(paramContext);
    }
  }
  
  public static GotixEngine getInstance()
  {
    return instance;
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public DaoMaster getDaoMaster()
  {
    return daoMaster;
  }
  
  public DaoSession getDaoSession()
  {
    return daoSession;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/helper/GotixEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */