package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.a;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.SensorsApi;
import com.google.android.gms.fitness.data.l;
import com.google.android.gms.fitness.data.l.a;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.o;
import com.google.android.gms.fitness.request.q;
import com.google.android.gms.fitness.result.DataSourcesResult;

public class lc
  implements SensorsApi
{
  private PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, final o paramo)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(paramo, localb, str);
      }
      
      protected Status d(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  private PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, final q paramq, final b paramb)
  {
    paramGoogleApiClient.b(new a()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        lc.d locald = new lc.d(this, paramb, null);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(paramq, locald, str);
      }
      
      protected Status d(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  public PendingResult<Status> add(GoogleApiClient paramGoogleApiClient, SensorRequest paramSensorRequest, PendingIntent paramPendingIntent)
  {
    return a(paramGoogleApiClient, new o(paramSensorRequest, null, paramPendingIntent));
  }
  
  public PendingResult<Status> add(GoogleApiClient paramGoogleApiClient, SensorRequest paramSensorRequest, OnDataPointListener paramOnDataPointListener)
  {
    return a(paramGoogleApiClient, new o(paramSensorRequest, l.a.iV().a(paramOnDataPointListener), null));
  }
  
  public PendingResult<DataSourcesResult> findDataSources(GoogleApiClient paramGoogleApiClient, final DataSourcesRequest paramDataSourcesRequest)
  {
    paramGoogleApiClient.a(new a()
    {
      protected DataSourcesResult A(Status paramAnonymousStatus)
      {
        return DataSourcesResult.E(paramAnonymousStatus);
      }
      
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        lc.c localc = new lc.c(this, null);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(paramDataSourcesRequest, localc, str);
      }
    });
  }
  
  public PendingResult<Status> remove(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return a(paramGoogleApiClient, new q(null, paramPendingIntent), null);
  }
  
  public PendingResult<Status> remove(GoogleApiClient paramGoogleApiClient, final OnDataPointListener paramOnDataPointListener)
  {
    l locall = l.a.iV().b(paramOnDataPointListener);
    if (locall == null) {
      return new ku(Status.Jv);
    }
    a(paramGoogleApiClient, new q(locall, null), new b()
    {
      public void jd()
      {
        l.a.iV().c(paramOnDataPointListener);
      }
    });
  }
  
  private static abstract class a<R extends Result>
    extends BaseImplementation.a<R, kk>
  {
    public a()
    {
      super();
    }
  }
  
  private static abstract interface b
  {
    public abstract void jd();
  }
  
  private static class c
    extends kn.a
  {
    private final BaseImplementation.b<DataSourcesResult> De;
    
    private c(BaseImplementation.b<DataSourcesResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(DataSourcesResult paramDataSourcesResult)
    {
      this.De.b(paramDataSourcesResult);
    }
  }
  
  private static class d
    extends kt.a
  {
    private final BaseImplementation.b<Status> De;
    private final lc.b TX;
    
    private d(BaseImplementation.b<Status> paramb, lc.b paramb1)
    {
      this.De = paramb;
      this.TX = paramb1;
    }
    
    public void k(Status paramStatus)
    {
      if ((this.TX != null) && (paramStatus.isSuccess())) {
        this.TX.jd();
      }
      this.De.b(paramStatus);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/lc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */