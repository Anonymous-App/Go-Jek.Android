package lib.gojek.base.networks;

import retrofit.client.Response;

public abstract interface GeneralNetworkHandler
{
  public abstract void onFailedToProcessRequest(Response paramResponse);
  
  public abstract void onNetworkProblem();
  
  public abstract void onNoInternetConnection();
  
  public abstract void onRequestEnd();
  
  public abstract void onRequesting();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/networks/GeneralNetworkHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */