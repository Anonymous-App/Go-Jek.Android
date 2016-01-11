package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.BleApi;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.ad;
import com.google.android.gms.fitness.request.ah;
import com.google.android.gms.fitness.request.b;
import com.google.android.gms.fitness.result.BleDevicesResult;

public class kx
  implements BleApi
{
  public PendingResult<Status> claimBleDevice(GoogleApiClient paramGoogleApiClient, final BleDevice paramBleDevice)
  {
    paramGoogleApiClient.b(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(new b(paramBleDevice), localb, str);
      }
    });
  }
  
  public PendingResult<Status> claimBleDevice(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(new b(paramString), localb, str);
      }
    });
  }
  
  public PendingResult<BleDevicesResult> listClaimedBleDevices(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.a(new kk.a()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kx.a locala = new kx.a(this, null);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(locala, str);
      }
      
      protected BleDevicesResult w(Status paramAnonymousStatus)
      {
        return BleDevicesResult.D(paramAnonymousStatus);
      }
    });
  }
  
  public PendingResult<Status> startBleScan(GoogleApiClient paramGoogleApiClient, final StartBleScanRequest paramStartBleScanRequest)
  {
    paramGoogleApiClient.a(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(paramStartBleScanRequest, localb, str);
      }
    });
  }
  
  public PendingResult<Status> stopBleScan(GoogleApiClient paramGoogleApiClient, final BleScanCallback paramBleScanCallback)
  {
    paramGoogleApiClient.a(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        String str = paramAnonymouskk.getContext().getPackageName();
        ad localad = new ad(paramBleScanCallback);
        paramAnonymouskk.jb().a(localad, localb, str);
      }
    });
  }
  
  public PendingResult<Status> unclaimBleDevice(GoogleApiClient paramGoogleApiClient, BleDevice paramBleDevice)
  {
    return unclaimBleDevice(paramGoogleApiClient, paramBleDevice.getAddress());
  }
  
  public PendingResult<Status> unclaimBleDevice(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(new ah(paramString), localb, str);
      }
    });
  }
  
  private static class a
    extends lf.a
  {
    private final BaseImplementation.b<BleDevicesResult> De;
    
    private a(BaseImplementation.b<BleDevicesResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(BleDevicesResult paramBleDevicesResult)
    {
      this.De.b(paramBleDevicesResult);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */