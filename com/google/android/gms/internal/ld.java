package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.SessionsApi;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.aa;
import com.google.android.gms.fitness.request.u;
import com.google.android.gms.fitness.request.w.a;
import com.google.android.gms.fitness.request.y.a;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;

public class ld
  implements SessionsApi
{
  private PendingResult<SessionStopResult> a(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2)
  {
    paramGoogleApiClient.b(new kk.a()
    {
      protected SessionStopResult B(Status paramAnonymousStatus)
      {
        return SessionStopResult.I(paramAnonymousStatus);
      }
      
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        ld.b localb = new ld.b(this, null);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(new y.a().bu(paramString1).bv(paramString2).jB(), localb, str);
      }
    });
  }
  
  public PendingResult<Status> insertSession(GoogleApiClient paramGoogleApiClient, final SessionInsertRequest paramSessionInsertRequest)
  {
    paramGoogleApiClient.a(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(paramSessionInsertRequest, localb, str);
      }
    });
  }
  
  public PendingResult<SessionReadResult> readSession(GoogleApiClient paramGoogleApiClient, final SessionReadRequest paramSessionReadRequest)
  {
    paramGoogleApiClient.a(new kk.a()
    {
      protected SessionReadResult C(Status paramAnonymousStatus)
      {
        return SessionReadResult.H(paramAnonymousStatus);
      }
      
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        ld.a locala = new ld.a(this, null);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(paramSessionReadRequest, locala, str);
      }
    });
  }
  
  public PendingResult<Status> registerForSessions(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.b(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        u localu = new u(paramPendingIntent);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(localu, localb, str);
      }
    });
  }
  
  public PendingResult<Status> startSession(GoogleApiClient paramGoogleApiClient, final Session paramSession)
  {
    paramGoogleApiClient.b(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(new w.a().b(paramSession).jA(), localb, str);
      }
    });
  }
  
  public PendingResult<SessionStopResult> stopSession(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return a(paramGoogleApiClient, null, paramString);
  }
  
  public PendingResult<Status> unregisterForSessions(GoogleApiClient paramGoogleApiClient, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.b(new kk.c()
    {
      protected void a(kk paramAnonymouskk)
        throws RemoteException
      {
        kk.b localb = new kk.b(this);
        aa localaa = new aa(paramPendingIntent);
        String str = paramAnonymouskk.getContext().getPackageName();
        paramAnonymouskk.jb().a(localaa, localb, str);
      }
    });
  }
  
  private static class a
    extends kr.a
  {
    private final BaseImplementation.b<SessionReadResult> De;
    
    private a(BaseImplementation.b<SessionReadResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(SessionReadResult paramSessionReadResult)
      throws RemoteException
    {
      this.De.b(paramSessionReadResult);
    }
  }
  
  private static class b
    extends ks.a
  {
    private final BaseImplementation.b<SessionStopResult> De;
    
    private b(BaseImplementation.b<SessionStopResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(SessionStopResult paramSessionStopResult)
    {
      this.De.b(paramSessionStopResult);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */