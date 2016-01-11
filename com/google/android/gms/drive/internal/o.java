package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.CallbackHandler;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveApi.ContentsResult;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveApi.DriveIdResult;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.query.Query;

public class o
  implements DriveApi
{
  public PendingResult<DriveApi.DriveContentsResult> a(GoogleApiClient paramGoogleApiClient, final int paramInt)
  {
    paramGoogleApiClient.a(new d()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new CreateContentsRequest(paramInt), new o.k(this));
      }
    });
  }
  
  public PendingResult<Status> discardContents(GoogleApiClient paramGoogleApiClient, final Contents paramContents)
  {
    if (paramContents.hK()) {
      throw new IllegalStateException("DriveContents already closed.");
    }
    paramContents.hJ();
    paramGoogleApiClient.b(new p.a()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new CloseContentsRequest(paramContents, false), new bb(this));
      }
    });
  }
  
  public PendingResult<DriveApi.DriveIdResult> fetchDriveId(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.a(new g()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new GetMetadataRequest(DriveId.bg(paramString)), new o.e(this));
      }
    });
  }
  
  public DriveFolder getAppFolder(GoogleApiClient paramGoogleApiClient)
  {
    if (!paramGoogleApiClient.isConnected()) {
      throw new IllegalStateException("Client must be connected");
    }
    paramGoogleApiClient = ((q)paramGoogleApiClient.a(Drive.CU)).ia();
    if (paramGoogleApiClient != null) {
      return new u(paramGoogleApiClient);
    }
    return null;
  }
  
  public DriveFile getFile(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId)
  {
    if (paramDriveId == null) {
      throw new IllegalArgumentException("Id must be provided.");
    }
    if (!paramGoogleApiClient.isConnected()) {
      throw new IllegalStateException("Client must be connected");
    }
    return new s(paramDriveId);
  }
  
  public DriveFolder getFolder(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId)
  {
    if (paramDriveId == null) {
      throw new IllegalArgumentException("Id must be provided.");
    }
    if (!paramGoogleApiClient.isConnected()) {
      throw new IllegalStateException("Client must be connected");
    }
    return new u(paramDriveId);
  }
  
  public DriveFolder getRootFolder(GoogleApiClient paramGoogleApiClient)
  {
    if (!paramGoogleApiClient.isConnected()) {
      throw new IllegalStateException("Client must be connected");
    }
    return new u(((q)paramGoogleApiClient.a(Drive.CU)).hZ());
  }
  
  public PendingResult<DriveApi.ContentsResult> newContents(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.a(new b()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new CreateContentsRequest(536870912), new o.j(this));
      }
    });
  }
  
  public CreateFileActivityBuilder newCreateFileActivityBuilder()
  {
    return new CreateFileActivityBuilder();
  }
  
  public PendingResult<DriveApi.DriveContentsResult> newDriveContents(GoogleApiClient paramGoogleApiClient)
  {
    return a(paramGoogleApiClient, 536870912);
  }
  
  public OpenFileActivityBuilder newOpenFileActivityBuilder()
  {
    return new OpenFileActivityBuilder();
  }
  
  public PendingResult<DriveApi.MetadataBufferResult> query(GoogleApiClient paramGoogleApiClient, final Query paramQuery)
  {
    if (paramQuery == null) {
      throw new IllegalArgumentException("Query must be provided.");
    }
    paramGoogleApiClient.a(new i()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new QueryRequest(paramQuery), new o.l(this));
      }
    });
  }
  
  public PendingResult<Status> requestSync(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.b(new p.a()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new bb(this));
      }
    });
  }
  
  static class a
    implements DriveApi.ContentsResult
  {
    private final Status CM;
    private final Contents Ox;
    
    public a(Status paramStatus, Contents paramContents)
    {
      this.CM = paramStatus;
      this.Ox = paramContents;
    }
    
    public Contents getContents()
    {
      return this.Ox;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
  
  static abstract class b
    extends p<DriveApi.ContentsResult>
  {
    public DriveApi.ContentsResult p(Status paramStatus)
    {
      return new o.a(paramStatus, null);
    }
  }
  
  static class c
    implements DriveApi.DriveContentsResult
  {
    private final Status CM;
    private final DriveContents Nb;
    
    public c(Status paramStatus, DriveContents paramDriveContents)
    {
      this.CM = paramStatus;
      this.Nb = paramDriveContents;
    }
    
    public DriveContents getDriveContents()
    {
      return this.Nb;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
  
  static abstract class d
    extends p<DriveApi.DriveContentsResult>
  {
    public DriveApi.DriveContentsResult q(Status paramStatus)
    {
      return new o.c(paramStatus, null);
    }
  }
  
  static class e
    extends c
  {
    private final BaseImplementation.b<DriveApi.DriveIdResult> De;
    
    public e(BaseImplementation.b<DriveApi.DriveIdResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(OnDriveIdResponse paramOnDriveIdResponse)
      throws RemoteException
    {
      this.De.b(new o.f(Status.Jv, paramOnDriveIdResponse.getDriveId()));
    }
    
    public void a(OnMetadataResponse paramOnMetadataResponse)
      throws RemoteException
    {
      this.De.b(new o.f(Status.Jv, new l(paramOnMetadataResponse.il()).getDriveId()));
    }
    
    public void o(Status paramStatus)
      throws RemoteException
    {
      this.De.b(new o.f(paramStatus, null));
    }
  }
  
  private static class f
    implements DriveApi.DriveIdResult
  {
    private final Status CM;
    private final DriveId MW;
    
    public f(Status paramStatus, DriveId paramDriveId)
    {
      this.CM = paramStatus;
      this.MW = paramDriveId;
    }
    
    public DriveId getDriveId()
    {
      return this.MW;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
  
  static abstract class g
    extends p<DriveApi.DriveIdResult>
  {
    public DriveApi.DriveIdResult r(Status paramStatus)
    {
      return new o.f(paramStatus, null);
    }
  }
  
  static class h
    implements DriveApi.MetadataBufferResult
  {
    private final Status CM;
    private final MetadataBuffer Oy;
    private final boolean Oz;
    
    public h(Status paramStatus, MetadataBuffer paramMetadataBuffer, boolean paramBoolean)
    {
      this.CM = paramStatus;
      this.Oy = paramMetadataBuffer;
      this.Oz = paramBoolean;
    }
    
    public MetadataBuffer getMetadataBuffer()
    {
      return this.Oy;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
  
  static abstract class i
    extends p<DriveApi.MetadataBufferResult>
  {
    public DriveApi.MetadataBufferResult s(Status paramStatus)
    {
      return new o.h(paramStatus, null, false);
    }
  }
  
  private static class j
    extends c
  {
    private final BaseImplementation.b<DriveApi.ContentsResult> De;
    
    public j(BaseImplementation.b<DriveApi.ContentsResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(OnContentsResponse paramOnContentsResponse)
      throws RemoteException
    {
      this.De.b(new o.a(Status.Jv, paramOnContentsResponse.id()));
    }
    
    public void o(Status paramStatus)
      throws RemoteException
    {
      this.De.b(new o.a(paramStatus, null));
    }
  }
  
  private static class k
    extends c
  {
    private final BaseImplementation.b<DriveApi.DriveContentsResult> De;
    
    public k(BaseImplementation.b<DriveApi.DriveContentsResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(OnContentsResponse paramOnContentsResponse)
      throws RemoteException
    {
      this.De.b(new o.c(Status.Jv, new r(paramOnContentsResponse.id())));
    }
    
    public void o(Status paramStatus)
      throws RemoteException
    {
      this.De.b(new o.c(paramStatus, null));
    }
  }
  
  private static class l
    extends c
  {
    private final BaseImplementation.b<DriveApi.MetadataBufferResult> De;
    
    public l(BaseImplementation.b<DriveApi.MetadataBufferResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(OnListEntriesResponse paramOnListEntriesResponse)
      throws RemoteException
    {
      MetadataBuffer localMetadataBuffer = new MetadataBuffer(paramOnListEntriesResponse.ii(), null);
      this.De.b(new o.h(Status.Jv, localMetadataBuffer, paramOnListEntriesResponse.ij()));
    }
    
    public void o(Status paramStatus)
      throws RemoteException
    {
      this.De.b(new o.h(paramStatus, null, false));
    }
  }
  
  static class m
    extends p.a
  {
    m(GoogleApiClient paramGoogleApiClient, Status paramStatus)
    {
      a(new BaseImplementation.CallbackHandler(((q)paramGoogleApiClient.a(Drive.CU)).getLooper()));
      b(paramStatus);
    }
    
    protected void a(q paramq) {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */