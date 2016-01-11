package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.List;

public abstract interface NodeApi
{
  public abstract PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, NodeListener paramNodeListener);
  
  public abstract PendingResult<GetConnectedNodesResult> getConnectedNodes(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<GetLocalNodeResult> getLocalNode(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, NodeListener paramNodeListener);
  
  public static abstract interface GetConnectedNodesResult
    extends Result
  {
    public abstract List<Node> getNodes();
  }
  
  public static abstract interface GetLocalNodeResult
    extends Result
  {
    public abstract Node getNode();
  }
  
  public static abstract interface NodeListener
  {
    public abstract void onPeerConnected(Node paramNode);
    
    public abstract void onPeerDisconnected(Node paramNode);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/NodeApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */