package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.google.android.gms.wearable.NodeApi.GetLocalNodeResult;
import com.google.android.gms.wearable.NodeApi.NodeListener;
import java.util.List;

public final class aj
  implements NodeApi
{
  public PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, final NodeApi.NodeListener paramNodeListener)
  {
    paramGoogleApiClient.a(new d()
    {
      protected void a(aw paramAnonymousaw)
        throws RemoteException
      {
        paramAnonymousaw.a(this, paramNodeListener);
      }
      
      public Status d(Status paramAnonymousStatus)
      {
        return new Status(13);
      }
    });
  }
  
  public PendingResult<NodeApi.GetConnectedNodesResult> getConnectedNodes(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.a(new d()
    {
      protected void a(aw paramAnonymousaw)
        throws RemoteException
      {
        paramAnonymousaw.q(this);
      }
      
      protected NodeApi.GetConnectedNodesResult aL(Status paramAnonymousStatus)
      {
        return new aj.a(paramAnonymousStatus, null);
      }
    });
  }
  
  public PendingResult<NodeApi.GetLocalNodeResult> getLocalNode(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.a(new d()
    {
      protected void a(aw paramAnonymousaw)
        throws RemoteException
      {
        paramAnonymousaw.p(this);
      }
      
      protected NodeApi.GetLocalNodeResult aK(Status paramAnonymousStatus)
      {
        return new aj.b(paramAnonymousStatus, null);
      }
    });
  }
  
  public PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, final NodeApi.NodeListener paramNodeListener)
  {
    paramGoogleApiClient.a(new d()
    {
      protected void a(aw paramAnonymousaw)
        throws RemoteException
      {
        paramAnonymousaw.b(this, paramNodeListener);
      }
      
      public Status d(Status paramAnonymousStatus)
      {
        return new Status(13);
      }
    });
  }
  
  public static class a
    implements NodeApi.GetConnectedNodesResult
  {
    private final Status CM;
    private final List<Node> avL;
    
    public a(Status paramStatus, List<Node> paramList)
    {
      this.CM = paramStatus;
      this.avL = paramList;
    }
    
    public List<Node> getNodes()
    {
      return this.avL;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
  
  public static class b
    implements NodeApi.GetLocalNodeResult
  {
    private final Status CM;
    private final Node avM;
    
    public b(Status paramStatus, Node paramNode)
    {
      this.CM = paramStatus;
      this.avM = paramNode;
    }
    
    public Node getNode()
    {
      return this.avM;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */