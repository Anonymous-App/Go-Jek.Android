package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataApi.DataListener;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageApi.MessageListener;
import com.google.android.gms.wearable.NodeApi.NodeListener;

public class ax
  extends ae.a
{
  private final DataApi.DataListener avX;
  private final MessageApi.MessageListener avY;
  private final NodeApi.NodeListener avZ;
  private final IntentFilter[] awa;
  
  public ax(DataApi.DataListener paramDataListener, MessageApi.MessageListener paramMessageListener, NodeApi.NodeListener paramNodeListener, IntentFilter[] paramArrayOfIntentFilter)
  {
    this.avX = paramDataListener;
    this.avY = paramMessageListener;
    this.avZ = paramNodeListener;
    this.awa = paramArrayOfIntentFilter;
  }
  
  public static ax a(DataApi.DataListener paramDataListener, IntentFilter[] paramArrayOfIntentFilter)
  {
    return new ax(paramDataListener, null, null, paramArrayOfIntentFilter);
  }
  
  public static ax a(MessageApi.MessageListener paramMessageListener, IntentFilter[] paramArrayOfIntentFilter)
  {
    return new ax(null, paramMessageListener, null, paramArrayOfIntentFilter);
  }
  
  public static ax a(NodeApi.NodeListener paramNodeListener)
  {
    return new ax(null, null, paramNodeListener, null);
  }
  
  public void Z(DataHolder paramDataHolder)
  {
    if (this.avX != null) {
      try
      {
        this.avX.onDataChanged(new DataEventBuffer(paramDataHolder));
        return;
      }
      finally
      {
        paramDataHolder.close();
      }
    }
    paramDataHolder.close();
  }
  
  public void a(ah paramah)
  {
    if (this.avY != null) {
      this.avY.onMessageReceived(paramah);
    }
  }
  
  public void a(ak paramak)
  {
    if (this.avZ != null) {
      this.avZ.onPeerConnected(paramak);
    }
  }
  
  public void b(ak paramak)
  {
    if (this.avZ != null) {
      this.avZ.onPeerDisconnected(paramak);
    }
  }
  
  public IntentFilter[] qb()
  {
    return this.awa;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */