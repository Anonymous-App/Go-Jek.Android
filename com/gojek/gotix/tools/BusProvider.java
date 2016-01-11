package com.gojek.gotix.tools;

import com.squareup.otto.ThreadEnforcer;

public class BusProvider
{
  private static final AndroidBus BUS = new AndroidBus(ThreadEnforcer.ANY);
  
  public static AndroidBus getInstance()
  {
    return BUS;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/tools/BusProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */