package com.gojek.gobox.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.gojek.gobox.networking.NetworkService.LocalBinder;

class GoBoxAuthUtil$1
  implements ServiceConnection
{
  GoBoxAuthUtil$1(GoBoxAuthUtil paramGoBoxAuthUtil) {}
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    paramComponentName = (NetworkService.LocalBinder)paramIBinder;
    GoBoxAuthUtil.access$002(this.this$0, paramComponentName.getService());
    GoBoxAuthUtil.access$102(this.this$0, true);
    GoBoxAuthUtil.access$200(this.this$0);
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    GoBoxAuthUtil.access$102(this.this$0, false);
    GoBoxAuthUtil.access$300(this.this$0).clearContextReference();
    GoBoxAuthUtil.access$402(this.this$0, null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/GoBoxAuthUtil$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */