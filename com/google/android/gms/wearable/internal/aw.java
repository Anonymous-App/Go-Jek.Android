package com.google.android.gms.wearable.internal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi.DataItemResult;
import com.google.android.gms.wearable.DataApi.DataListener;
import com.google.android.gms.wearable.DataApi.DeleteDataItemsResult;
import com.google.android.gms.wearable.DataApi.GetFdForAssetResult;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.MessageApi.MessageListener;
import com.google.android.gms.wearable.MessageApi.SendMessageResult;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.google.android.gms.wearable.NodeApi.GetLocalNodeResult;
import com.google.android.gms.wearable.NodeApi.NodeListener;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class aw
  extends e<af>
{
  private final ExecutorService aqA = Executors.newCachedThreadPool();
  private final HashMap<DataApi.DataListener, ax> avQ = new HashMap();
  private final HashMap<MessageApi.MessageListener, ax> avR = new HashMap();
  private final HashMap<NodeApi.NodeListener, ax> avS = new HashMap();
  
  public aw(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
  }
  
  private FutureTask<Boolean> a(final ParcelFileDescriptor paramParcelFileDescriptor, final byte[] paramArrayOfByte)
  {
    new FutureTask(new Callable()
    {
      /* Error */
      public Boolean qa()
      {
        // Byte code:
        //   0: ldc 43
        //   2: iconst_3
        //   3: invokestatic 49	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
        //   6: ifeq +31 -> 37
        //   9: ldc 43
        //   11: new 51	java/lang/StringBuilder
        //   14: dup
        //   15: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   18: ldc 54
        //   20: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   23: aload_0
        //   24: getfield 24	com/google/android/gms/wearable/internal/aw$2:avU	Landroid/os/ParcelFileDescriptor;
        //   27: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   30: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   33: invokestatic 69	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   36: pop
        //   37: new 71	android/os/ParcelFileDescriptor$AutoCloseOutputStream
        //   40: dup
        //   41: aload_0
        //   42: getfield 24	com/google/android/gms/wearable/internal/aw$2:avU	Landroid/os/ParcelFileDescriptor;
        //   45: invokespecial 74	android/os/ParcelFileDescriptor$AutoCloseOutputStream:<init>	(Landroid/os/ParcelFileDescriptor;)V
        //   48: astore_1
        //   49: aload_1
        //   50: aload_0
        //   51: getfield 26	com/google/android/gms/wearable/internal/aw$2:CY	[B
        //   54: invokevirtual 78	android/os/ParcelFileDescriptor$AutoCloseOutputStream:write	([B)V
        //   57: aload_1
        //   58: invokevirtual 81	android/os/ParcelFileDescriptor$AutoCloseOutputStream:flush	()V
        //   61: ldc 43
        //   63: iconst_3
        //   64: invokestatic 49	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
        //   67: ifeq +31 -> 98
        //   70: ldc 43
        //   72: new 51	java/lang/StringBuilder
        //   75: dup
        //   76: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   79: ldc 83
        //   81: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   84: aload_0
        //   85: getfield 24	com/google/android/gms/wearable/internal/aw$2:avU	Landroid/os/ParcelFileDescriptor;
        //   88: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   91: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   94: invokestatic 69	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   97: pop
        //   98: iconst_1
        //   99: invokestatic 89	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
        //   102: astore_2
        //   103: ldc 43
        //   105: iconst_3
        //   106: invokestatic 49	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
        //   109: ifeq +31 -> 140
        //   112: ldc 43
        //   114: new 51	java/lang/StringBuilder
        //   117: dup
        //   118: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   121: ldc 91
        //   123: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: aload_0
        //   127: getfield 24	com/google/android/gms/wearable/internal/aw$2:avU	Landroid/os/ParcelFileDescriptor;
        //   130: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   133: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   136: invokestatic 69	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   139: pop
        //   140: aload_1
        //   141: invokevirtual 94	android/os/ParcelFileDescriptor$AutoCloseOutputStream:close	()V
        //   144: aload_2
        //   145: areturn
        //   146: astore_2
        //   147: ldc 43
        //   149: new 51	java/lang/StringBuilder
        //   152: dup
        //   153: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   156: ldc 96
        //   158: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: aload_0
        //   162: getfield 24	com/google/android/gms/wearable/internal/aw$2:avU	Landroid/os/ParcelFileDescriptor;
        //   165: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   168: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   171: invokestatic 99	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
        //   174: pop
        //   175: ldc 43
        //   177: iconst_3
        //   178: invokestatic 49	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
        //   181: ifeq +31 -> 212
        //   184: ldc 43
        //   186: new 51	java/lang/StringBuilder
        //   189: dup
        //   190: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   193: ldc 91
        //   195: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: aload_0
        //   199: getfield 24	com/google/android/gms/wearable/internal/aw$2:avU	Landroid/os/ParcelFileDescriptor;
        //   202: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   205: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   208: invokestatic 69	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   211: pop
        //   212: aload_1
        //   213: invokevirtual 94	android/os/ParcelFileDescriptor$AutoCloseOutputStream:close	()V
        //   216: iconst_0
        //   217: invokestatic 89	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
        //   220: areturn
        //   221: astore_2
        //   222: ldc 43
        //   224: iconst_3
        //   225: invokestatic 49	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
        //   228: ifeq +31 -> 259
        //   231: ldc 43
        //   233: new 51	java/lang/StringBuilder
        //   236: dup
        //   237: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   240: ldc 91
        //   242: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   245: aload_0
        //   246: getfield 24	com/google/android/gms/wearable/internal/aw$2:avU	Landroid/os/ParcelFileDescriptor;
        //   249: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   252: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   255: invokestatic 69	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   258: pop
        //   259: aload_1
        //   260: invokevirtual 94	android/os/ParcelFileDescriptor$AutoCloseOutputStream:close	()V
        //   263: aload_2
        //   264: athrow
        //   265: astore_1
        //   266: goto -3 -> 263
        //   269: astore_1
        //   270: goto -54 -> 216
        //   273: astore_1
        //   274: aload_2
        //   275: areturn
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	276	0	this	2
        //   48	212	1	localAutoCloseOutputStream	android.os.ParcelFileDescriptor.AutoCloseOutputStream
        //   265	1	1	localIOException1	IOException
        //   269	1	1	localIOException2	IOException
        //   273	1	1	localIOException3	IOException
        //   102	43	2	localBoolean1	Boolean
        //   146	1	2	localIOException4	IOException
        //   221	54	2	localBoolean2	Boolean
        // Exception table:
        //   from	to	target	type
        //   49	98	146	java/io/IOException
        //   98	103	146	java/io/IOException
        //   49	98	221	finally
        //   98	103	221	finally
        //   147	175	221	finally
        //   222	259	265	java/io/IOException
        //   259	263	265	java/io/IOException
        //   175	212	269	java/io/IOException
        //   212	216	269	java/io/IOException
        //   103	140	273	java/io/IOException
        //   140	144	273	java/io/IOException
      }
    });
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if (Log.isLoggable("WearableClient", 2)) {
      Log.d("WearableClient", "onPostInitHandler: statusCode " + paramInt);
    }
    af localaf;
    Iterator localIterator;
    Map.Entry localEntry;
    if (paramInt == 0) {
      try
      {
        a local1 = new a()
        {
          public void a(Status paramAnonymousStatus) {}
        };
        if (Log.isLoggable("WearableClient", 2)) {
          Log.d("WearableClient", "onPostInitHandler: service " + paramIBinder);
        }
        localaf = af.a.bT(paramIBinder);
        localIterator = this.avQ.entrySet().iterator();
        while (localIterator.hasNext())
        {
          localEntry = (Map.Entry)localIterator.next();
          if (Log.isLoggable("WearableClient", 2)) {
            Log.d("WearableClient", "onPostInitHandler: adding Data listener " + localEntry.getValue());
          }
          localaf.a(local1, new b((ax)localEntry.getValue()));
          continue;
          Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: done");
        }
      }
      catch (RemoteException localRemoteException)
      {
        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: error while adding listener", localRemoteException);
      }
    }
    for (;;)
    {
      super.a(paramInt, paramIBinder, paramBundle);
      return;
      localIterator = this.avR.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (Log.isLoggable("WearableClient", 2)) {
          Log.d("WearableClient", "onPostInitHandler: adding Message listener " + localEntry.getValue());
        }
        localaf.a(localRemoteException, new b((ax)localEntry.getValue()));
      }
      localIterator = this.avS.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (Log.isLoggable("WearableClient", 2)) {
          Log.d("WearableClient", "onPostInitHandler: adding Node listener " + localEntry.getValue());
        }
        localaf.a(localRemoteException, new b((ax)localEntry.getValue()));
      }
    }
  }
  
  public void a(final BaseImplementation.b<DataApi.DataItemResult> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(x paramAnonymousx)
      {
        paramb.b(new f.a(new Status(paramAnonymousx.statusCode), paramAnonymousx.avA));
      }
    }, paramUri);
  }
  
  public void a(final BaseImplementation.b<DataApi.GetFdForAssetResult> paramb, Asset paramAsset)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(z paramAnonymousz)
      {
        paramb.b(new f.c(new Status(paramAnonymousz.statusCode), paramAnonymousz.avB));
      }
    }, paramAsset);
  }
  
  public void a(BaseImplementation.b<Status> paramb, DataApi.DataListener paramDataListener)
    throws RemoteException
  {
    synchronized (this.avQ)
    {
      paramDataListener = (ae)this.avQ.remove(paramDataListener);
      if (paramDataListener == null)
      {
        paramb.b(new Status(4002));
        return;
      }
    }
    a(paramb, paramDataListener);
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final DataApi.DataListener paramDataListener, IntentFilter[] arg3)
    throws RemoteException
  {
    ax localax = ax.a(paramDataListener, ???);
    synchronized (this.avQ)
    {
      if (this.avQ.get(paramDataListener) != null)
      {
        paramb.b(new Status(4001));
        return;
      }
      this.avQ.put(paramDataListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.b(aw.this))
          {
            aw.b(aw.this).remove(paramDataListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
      return;
    }
  }
  
  public void a(BaseImplementation.b<DataApi.GetFdForAssetResult> paramb, DataItemAsset paramDataItemAsset)
    throws RemoteException
  {
    a(paramb, Asset.createFromRef(paramDataItemAsset.getId()));
  }
  
  public void a(BaseImplementation.b<Status> paramb, MessageApi.MessageListener paramMessageListener)
    throws RemoteException
  {
    synchronized (this.avR)
    {
      paramMessageListener = (ae)this.avR.remove(paramMessageListener);
      if (paramMessageListener == null)
      {
        paramb.b(new Status(4002));
        return;
      }
      a(paramb, paramMessageListener);
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final MessageApi.MessageListener paramMessageListener, IntentFilter[] arg3)
    throws RemoteException
  {
    ax localax = ax.a(paramMessageListener, ???);
    synchronized (this.avR)
    {
      if (this.avR.get(paramMessageListener) != null)
      {
        paramb.b(new Status(4001));
        return;
      }
      this.avR.put(paramMessageListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.c(aw.this))
          {
            aw.c(aw.this).remove(paramMessageListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
      return;
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, final NodeApi.NodeListener paramNodeListener)
    throws RemoteException, RemoteException
  {
    ax localax = ax.a(paramNodeListener);
    synchronized (this.avS)
    {
      if (this.avS.get(paramNodeListener) != null)
      {
        paramb.b(new Status(4001));
        return;
      }
      this.avS.put(paramNodeListener, localax);
      ((af)gS()).a(new a()new b
      {
        public void a(Status paramAnonymousStatus)
        {
          if (!paramAnonymousStatus.isSuccess()) {}
          synchronized (aw.d(aw.this))
          {
            aw.d(aw.this).remove(paramNodeListener);
            paramb.b(paramAnonymousStatus);
            return;
          }
        }
      }, new b(localax));
      return;
    }
  }
  
  public void a(BaseImplementation.b<DataApi.DataItemResult> paramb, PutDataRequest paramPutDataRequest)
    throws RemoteException
  {
    Object localObject1 = paramPutDataRequest.getAssets().entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Asset)((Map.Entry)((Iterator)localObject1).next()).getValue();
      if ((((Asset)localObject2).getData() == null) && (((Asset)localObject2).getDigest() == null) && (((Asset)localObject2).getFd() == null) && (((Asset)localObject2).getUri() == null)) {
        throw new IllegalArgumentException("Put for " + paramPutDataRequest.getUri() + " contains invalid asset: " + localObject2);
      }
    }
    localObject1 = PutDataRequest.k(paramPutDataRequest.getUri());
    ((PutDataRequest)localObject1).setData(paramPutDataRequest.getData());
    Object localObject2 = new ArrayList();
    Iterator localIterator = paramPutDataRequest.getAssets().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject3 = (Map.Entry)localIterator.next();
      Asset localAsset = (Asset)((Map.Entry)localObject3).getValue();
      if (localAsset.getData() == null) {
        ((PutDataRequest)localObject1).putAsset((String)((Map.Entry)localObject3).getKey(), (Asset)((Map.Entry)localObject3).getValue());
      } else {
        try
        {
          ParcelFileDescriptor[] arrayOfParcelFileDescriptor = ParcelFileDescriptor.createPipe();
          if (Log.isLoggable("WearableClient", 3)) {
            Log.d("WearableClient", "processAssets: replacing data with FD in asset: " + localAsset + " read:" + arrayOfParcelFileDescriptor[0] + " write:" + arrayOfParcelFileDescriptor[1]);
          }
          ((PutDataRequest)localObject1).putAsset((String)((Map.Entry)localObject3).getKey(), Asset.createFromFd(arrayOfParcelFileDescriptor[0]));
          localObject3 = a(arrayOfParcelFileDescriptor[1], localAsset.getData());
          ((List)localObject2).add(localObject3);
          this.aqA.submit((Runnable)localObject3);
        }
        catch (IOException paramb)
        {
          throw new IllegalStateException("Unable to create ParcelFileDescriptor for asset in request: " + paramPutDataRequest, paramb);
        }
      }
    }
    try
    {
      ((af)gS()).a(new a(paramb, (List)localObject2), (PutDataRequest)localObject1);
      return;
    }
    catch (NullPointerException paramb)
    {
      throw new IllegalStateException("Unable to putDataItem: " + paramPutDataRequest, paramb);
    }
  }
  
  public void a(final BaseImplementation.b<Status> paramb, ae paramae)
    throws RemoteException
  {
    ((af)gS()).a(new a()new aq
    {
      public void a(Status paramAnonymousStatus)
      {
        paramb.b(paramAnonymousStatus);
      }
    }, new aq(paramae));
  }
  
  public void a(final BaseImplementation.b<MessageApi.SendMessageResult> paramb, String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws RemoteException
  {
    ((af)gS()).a(new a()
    {
      public void a(as paramAnonymousas)
      {
        paramb.b(new ag.a(new Status(paramAnonymousas.statusCode), paramAnonymousas.avO));
      }
    }, paramString1, paramString2, paramArrayOfByte);
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    paraml.e(parame, 6171000, getContext().getPackageName());
  }
  
  public void b(final BaseImplementation.b<DataItemBuffer> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).b(new a()
    {
      public void aa(DataHolder paramAnonymousDataHolder)
      {
        paramb.b(new DataItemBuffer(paramAnonymousDataHolder));
      }
    }, paramUri);
  }
  
  public void b(BaseImplementation.b<Status> paramb, NodeApi.NodeListener paramNodeListener)
    throws RemoteException
  {
    synchronized (this.avS)
    {
      paramNodeListener = (ae)this.avS.remove(paramNodeListener);
      if (paramNodeListener == null)
      {
        paramb.b(new Status(4002));
        return;
      }
      a(paramb, paramNodeListener);
    }
  }
  
  protected af bU(IBinder paramIBinder)
  {
    return af.a.bT(paramIBinder);
  }
  
  public void c(final BaseImplementation.b<DataApi.DeleteDataItemsResult> paramb, Uri paramUri)
    throws RemoteException
  {
    ((af)gS()).c(new a()
    {
      public void a(p paramAnonymousp)
      {
        paramb.b(new f.b(new Status(paramAnonymousp.statusCode), paramAnonymousp.avw));
      }
    }, paramUri);
  }
  
  public void disconnect()
  {
    super.disconnect();
    this.avQ.clear();
    this.avR.clear();
    this.avS.clear();
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.wearable.internal.IWearableService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.wearable.BIND";
  }
  
  public void o(final BaseImplementation.b<DataItemBuffer> paramb)
    throws RemoteException
  {
    ((af)gS()).b(new a()
    {
      public void aa(DataHolder paramAnonymousDataHolder)
      {
        paramb.b(new DataItemBuffer(paramAnonymousDataHolder));
      }
    });
  }
  
  public void p(final BaseImplementation.b<NodeApi.GetLocalNodeResult> paramb)
    throws RemoteException
  {
    ((af)gS()).c(new a()
    {
      public void a(ab paramAnonymousab)
      {
        paramb.b(new aj.b(new Status(paramAnonymousab.statusCode), paramAnonymousab.avC));
      }
    });
  }
  
  public void q(final BaseImplementation.b<NodeApi.GetConnectedNodesResult> paramb)
    throws RemoteException
  {
    ((af)gS()).d(new a()
    {
      public void a(v paramAnonymousv)
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(paramAnonymousv.avz);
        paramb.b(new aj.a(new Status(paramAnonymousv.statusCode), localArrayList));
      }
    });
  }
  
  private static class a
    extends a
  {
    private final BaseImplementation.b<DataApi.DataItemResult> De;
    private final List<FutureTask<Boolean>> avW;
    
    a(BaseImplementation.b<DataApi.DataItemResult> paramb, List<FutureTask<Boolean>> paramList)
    {
      this.De = paramb;
      this.avW = paramList;
    }
    
    public void a(ao paramao)
    {
      this.De.b(new f.a(new Status(paramao.statusCode), paramao.avA));
      if (paramao.statusCode != 0)
      {
        paramao = this.avW.iterator();
        while (paramao.hasNext()) {
          ((FutureTask)paramao.next()).cancel(true);
        }
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/aw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */