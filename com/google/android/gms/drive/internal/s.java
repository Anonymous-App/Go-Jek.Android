package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.c.b;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveApi.ContentsResult;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFile.DownloadProgressListener;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;

public class s
  extends w
  implements DriveFile
{
  public s(DriveId paramDriveId)
  {
    super(paramDriveId);
  }
  
  private static DriveFile.DownloadProgressListener a(GoogleApiClient paramGoogleApiClient, DriveFile.DownloadProgressListener paramDownloadProgressListener)
  {
    if (paramDownloadProgressListener == null) {
      return null;
    }
    return new a(paramGoogleApiClient.c(paramDownloadProgressListener));
  }
  
  public PendingResult<Status> commitAndCloseContents(GoogleApiClient paramGoogleApiClient, Contents paramContents)
  {
    return new r(paramContents).commit(paramGoogleApiClient, null);
  }
  
  public PendingResult<Status> commitAndCloseContents(GoogleApiClient paramGoogleApiClient, Contents paramContents, MetadataChangeSet paramMetadataChangeSet)
  {
    return new r(paramContents).commit(paramGoogleApiClient, paramMetadataChangeSet);
  }
  
  public PendingResult<Status> discardContents(GoogleApiClient paramGoogleApiClient, Contents paramContents)
  {
    return Drive.DriveApi.discardContents(paramGoogleApiClient, paramContents);
  }
  
  public PendingResult<DriveApi.DriveContentsResult> open(GoogleApiClient paramGoogleApiClient, final int paramInt, DriveFile.DownloadProgressListener paramDownloadProgressListener)
  {
    if ((paramInt != 268435456) && (paramInt != 536870912) && (paramInt != 805306368)) {
      throw new IllegalArgumentException("Invalid mode provided.");
    }
    paramGoogleApiClient.a(new o.d()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new OpenContentsRequest(s.this.getDriveId(), paramInt, 0), new av(this, this.OO));
      }
    });
  }
  
  public PendingResult<DriveApi.ContentsResult> openContents(GoogleApiClient paramGoogleApiClient, final int paramInt, DriveFile.DownloadProgressListener paramDownloadProgressListener)
  {
    if ((paramInt != 268435456) && (paramInt != 536870912) && (paramInt != 805306368)) {
      throw new IllegalArgumentException("Invalid mode provided.");
    }
    paramGoogleApiClient.a(new o.b()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new OpenContentsRequest(s.this.getDriveId(), paramInt, 0), new s.b(this, this.OO));
      }
    });
  }
  
  private static class a
    implements DriveFile.DownloadProgressListener
  {
    private final com.google.android.gms.common.api.c<DriveFile.DownloadProgressListener> OQ;
    
    public a(com.google.android.gms.common.api.c<DriveFile.DownloadProgressListener> paramc)
    {
      this.OQ = paramc;
    }
    
    public void onProgress(final long paramLong1, long paramLong2)
    {
      this.OQ.a(new c.b()
      {
        public void a(DriveFile.DownloadProgressListener paramAnonymousDownloadProgressListener)
        {
          paramAnonymousDownloadProgressListener.onProgress(paramLong1, this.OS);
        }
        
        public void gr() {}
      });
    }
  }
  
  private static class b
    extends c
  {
    private final BaseImplementation.b<DriveApi.ContentsResult> De;
    private final DriveFile.DownloadProgressListener OU;
    
    public b(BaseImplementation.b<DriveApi.ContentsResult> paramb, DriveFile.DownloadProgressListener paramDownloadProgressListener)
    {
      this.De = paramb;
      this.OU = paramDownloadProgressListener;
    }
    
    public void a(OnContentsResponse paramOnContentsResponse)
      throws RemoteException
    {
      if (paramOnContentsResponse.ie()) {}
      for (Status localStatus = new Status(-1);; localStatus = Status.Jv)
      {
        this.De.b(new o.a(localStatus, paramOnContentsResponse.id()));
        return;
      }
    }
    
    public void a(OnDownloadProgressResponse paramOnDownloadProgressResponse)
      throws RemoteException
    {
      if (this.OU != null) {
        this.OU.onProgress(paramOnDownloadProgressResponse.jdMethod_if(), paramOnDownloadProgressResponse.ig());
      }
    }
    
    public void o(Status paramStatus)
      throws RemoteException
    {
      this.De.b(new o.a(paramStatus, null));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */