package com.gojek.gobox.networking;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import retrofit.RequestInterceptor;
import retrofit.RequestInterceptor.RequestFacade;

class NetworkService$3
  implements RequestInterceptor
{
  NetworkService$3(NetworkService paramNetworkService) {}
  
  public void intercept(RequestInterceptor.RequestFacade paramRequestFacade)
  {
    Map localMap = NetworkService.access$100(this.this$0);
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      paramRequestFacade.addHeader(str, (String)localMap.get(str));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/networking/NetworkService$3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */