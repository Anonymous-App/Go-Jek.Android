package com.gojek.app;

import android.support.multidex.MultiDexApplication;
import com.flurry.android.FlurryAgent;
import com.newrelic.agent.android.NewRelic;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class GojekApp
  extends MultiDexApplication
{
  private boolean isAdult = false;
  
  public static DisplayImageOptions getDisplayOption(BitmapDisplayer paramBitmapDisplayer)
  {
    return new DisplayImageOptions.Builder().resetViewBeforeLoading(false).cacheInMemory(true).cacheOnDisk(true).showImageOnLoading(2130837873).showImageForEmptyUri(2130837873).showImageOnFail(2130837873).displayer(paramBitmapDisplayer).build();
  }
  
  public boolean isAdult()
  {
    return this.isAdult;
  }
  
  public void onCreate()
  {
    super.onCreate();
    FlurryAgent.setLogEnabled(false);
    FlurryAgent.init(this, "6FN49R3DK6YJXPTN34DJ");
    NewRelic.withApplicationToken("AA5473702b61b3b8f6b9953e065a42858db4d4e7b4").start(this);
    Object localObject = getDisplayOption(new SimpleBitmapDisplayer());
    localObject = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions((DisplayImageOptions)localObject).build();
    ImageLoader.getInstance().init((ImageLoaderConfiguration)localObject);
  }
  
  public void setIsAdult(boolean paramBoolean)
  {
    this.isAdult = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/GojekApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */