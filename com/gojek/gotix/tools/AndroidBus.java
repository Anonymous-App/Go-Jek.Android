package com.gojek.gotix.tools;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class AndroidBus
  extends Bus
{
  private final Handler mainThread = new Handler(Looper.getMainLooper());
  
  public AndroidBus() {}
  
  public AndroidBus(ThreadEnforcer paramThreadEnforcer)
  {
    super(paramThreadEnforcer);
  }
  
  public AndroidBus(ThreadEnforcer paramThreadEnforcer, String paramString)
  {
    super(paramThreadEnforcer, paramString);
  }
  
  public AndroidBus(String paramString)
  {
    super(paramString);
  }
  
  public void post(Object paramObject)
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      super.post(paramObject);
      return;
    }
    this.mainThread.post(AndroidBus..Lambda.1.lambdaFactory$(this, paramObject));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/tools/AndroidBus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */