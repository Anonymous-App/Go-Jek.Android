package com.google.android.gms.drive.internal;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.ExecutionOptions.Builder;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.io.InputStream;
import java.io.OutputStream;

public class r
  implements DriveContents
{
  private final Contents Ox;
  
  public r(Contents paramContents)
  {
    this.Ox = ((Contents)o.i(paramContents));
  }
  
  private PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, final MetadataChangeSet paramMetadataChangeSet, final ExecutionOptions paramExecutionOptions)
  {
    if (this.Ox.getMode() == 268435456) {
      throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
    }
    if ((ExecutionOptions.aV(paramExecutionOptions.hQ())) && (!this.Ox.hL())) {
      throw new IllegalStateException("DriveContents must be valid for conflict detection.");
    }
    ExecutionOptions.a(paramGoogleApiClient, paramExecutionOptions);
    if (this.Ox.hK()) {
      throw new IllegalStateException("DriveContents already closed.");
    }
    if (getDriveId() == null) {
      throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
    }
    if (paramMetadataChangeSet != null) {}
    for (;;)
    {
      this.Ox.hJ();
      paramGoogleApiClient.b(new p.a()
      {
        protected void a(q paramAnonymousq)
          throws RemoteException
        {
          paramMetadataChangeSet.hS().setContext(paramAnonymousq.getContext());
          paramAnonymousq.hY().a(new CloseContentsAndUpdateMetadataRequest(r.a(r.this).getDriveId(), paramMetadataChangeSet.hS(), r.a(r.this), paramExecutionOptions), new bb(this));
        }
      });
      paramMetadataChangeSet = MetadataChangeSet.Nt;
    }
  }
  
  public PendingResult<Status> commit(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet)
  {
    return a(paramGoogleApiClient, paramMetadataChangeSet, new ExecutionOptions.Builder().build());
  }
  
  public PendingResult<Status> commit(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, ExecutionOptions paramExecutionOptions)
  {
    return a(paramGoogleApiClient, paramMetadataChangeSet, paramExecutionOptions);
  }
  
  public void discard(GoogleApiClient paramGoogleApiClient)
  {
    if (this.Ox.hK()) {
      throw new IllegalStateException("DriveContents already closed.");
    }
    this.Ox.hJ();
    ((3)paramGoogleApiClient.b(new p.a()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new CloseContentsRequest(r.a(r.this), false), new bb(this));
      }
    })).setResultCallback(new ResultCallback()
    {
      public void k(Status paramAnonymousStatus)
      {
        if (!paramAnonymousStatus.isSuccess())
        {
          v.q("DriveContentsImpl", "Error discarding contents");
          return;
        }
        v.n("DriveContentsImpl", "Contents discarded");
      }
    });
  }
  
  public Contents getContents()
  {
    return this.Ox;
  }
  
  public DriveId getDriveId()
  {
    return this.Ox.getDriveId();
  }
  
  public InputStream getInputStream()
  {
    return this.Ox.getInputStream();
  }
  
  public int getMode()
  {
    return this.Ox.getMode();
  }
  
  public OutputStream getOutputStream()
  {
    return this.Ox.getOutputStream();
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
  {
    return this.Ox.getParcelFileDescriptor();
  }
  
  public PendingResult<DriveApi.DriveContentsResult> reopenForWrite(GoogleApiClient paramGoogleApiClient)
  {
    if (this.Ox.hK()) {
      throw new IllegalStateException("DriveContents already closed.");
    }
    if (this.Ox.getMode() != 268435456) {
      throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
    }
    this.Ox.hJ();
    paramGoogleApiClient.a(new o.d()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramAnonymousq.hY().a(new OpenContentsRequest(r.this.getDriveId(), 536870912, r.a(r.this).getRequestId()), new av(this, null));
      }
    });
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */