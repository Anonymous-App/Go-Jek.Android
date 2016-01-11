package com.gojek.app.gobusway.networking;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class NetworkActivity$1
  implements ServiceConnection
{
  NetworkActivity$1(NetworkActivity paramNetworkActivity) {}
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    paramComponentName = (NetworkService.LocalBinder)paramIBinder;
    NetworkActivity.access$002(this.this$0, paramComponentName.getService());
    NetworkActivity.access$100(this.this$0);
    NetworkActivity.access$202(this.this$0, true);
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    NetworkActivity.access$202(this.this$0, false);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/networking/NetworkActivity$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */