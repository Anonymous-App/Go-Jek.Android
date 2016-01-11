package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;
import com.google.android.gms.drive.DriveFolder.DriveFolderResult;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.ExecutionOptions.Builder;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.Query.Builder;
import com.google.android.gms.drive.query.SearchableField;

public class u
  extends w
  implements DriveFolder
{
  public u(DriveId paramDriveId)
  {
    super(paramDriveId);
  }
  
  private PendingResult<DriveFolder.DriveFileResult> a(GoogleApiClient paramGoogleApiClient, final MetadataChangeSet paramMetadataChangeSet, final Contents paramContents, final int paramInt, final ExecutionOptions paramExecutionOptions)
  {
    ExecutionOptions.a(paramGoogleApiClient, paramExecutionOptions);
    if (paramContents != null) {
      paramContents.hJ();
    }
    paramGoogleApiClient.b(new d()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramMetadataChangeSet.hS().setContext(paramAnonymousq.getContext());
        if (paramContents == null) {}
        for (int i = 0;; i = paramContents.getRequestId())
        {
          CreateFileRequest localCreateFileRequest = new CreateFileRequest(u.this.getDriveId(), paramMetadataChangeSet.hS(), i, paramInt, paramExecutionOptions);
          paramAnonymousq.hY().a(localCreateFileRequest, new u.a(this));
          return;
        }
      }
    });
  }
  
  private PendingResult<DriveFolder.DriveFileResult> a(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, DriveContents paramDriveContents, ExecutionOptions paramExecutionOptions)
  {
    if (paramDriveContents == null) {
      throw new IllegalArgumentException("DriveContents must be provided.");
    }
    if (!(paramDriveContents instanceof r)) {
      throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
    }
    if (paramDriveContents.getDriveId() != null) {
      throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
    }
    if (paramDriveContents.getContents().hK()) {
      throw new IllegalArgumentException("DriveContents are already closed.");
    }
    if (paramMetadataChangeSet == null) {
      throw new IllegalArgumentException("MetadataChangeSet must be provided.");
    }
    if ("application/vnd.google-apps.folder".equals(paramMetadataChangeSet.getMimeType())) {
      throw new IllegalArgumentException("May not create folders (mimetype: application/vnd.google-apps.folder) using this method. Use DriveFolder.createFolder() instead.");
    }
    return a(paramGoogleApiClient, paramMetadataChangeSet, paramDriveContents.getContents(), 0, paramExecutionOptions);
  }
  
  public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, Contents paramContents)
  {
    return createFile(paramGoogleApiClient, paramMetadataChangeSet, new r(paramContents));
  }
  
  public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, DriveContents paramDriveContents)
  {
    return createFile(paramGoogleApiClient, paramMetadataChangeSet, paramDriveContents, null);
  }
  
  public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient paramGoogleApiClient, MetadataChangeSet paramMetadataChangeSet, DriveContents paramDriveContents, ExecutionOptions paramExecutionOptions)
  {
    ExecutionOptions localExecutionOptions = paramExecutionOptions;
    if (paramExecutionOptions == null) {
      localExecutionOptions = new ExecutionOptions.Builder().build();
    }
    if (localExecutionOptions.hQ() != 0) {
      throw new IllegalStateException("May not set a conflict strategy for calls to createFile.");
    }
    return a(paramGoogleApiClient, paramMetadataChangeSet, paramDriveContents, localExecutionOptions);
  }
  
  public PendingResult<DriveFolder.DriveFolderResult> createFolder(GoogleApiClient paramGoogleApiClient, final MetadataChangeSet paramMetadataChangeSet)
  {
    if (paramMetadataChangeSet == null) {
      throw new IllegalArgumentException("MetadataChangeSet must be provided.");
    }
    if ((paramMetadataChangeSet.getMimeType() != null) && (!paramMetadataChangeSet.getMimeType().equals("application/vnd.google-apps.folder"))) {
      throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
    }
    paramGoogleApiClient.b(new f()
    {
      protected void a(q paramAnonymousq)
        throws RemoteException
      {
        paramMetadataChangeSet.hS().setContext(paramAnonymousq.getContext());
        paramAnonymousq.hY().a(new CreateFolderRequest(u.this.getDriveId(), paramMetadataChangeSet.hS()), new u.b(this));
      }
    });
  }
  
  public PendingResult<DriveApi.MetadataBufferResult> listChildren(GoogleApiClient paramGoogleApiClient)
  {
    return queryChildren(paramGoogleApiClient, null);
  }
  
  public PendingResult<DriveApi.MetadataBufferResult> queryChildren(GoogleApiClient paramGoogleApiClient, Query paramQuery)
  {
    Query.Builder localBuilder = new Query.Builder().addFilter(Filters.in(SearchableField.PARENTS, getDriveId()));
    if (paramQuery != null)
    {
      if (paramQuery.getFilter() != null) {
        localBuilder.addFilter(paramQuery.getFilter());
      }
      localBuilder.setPageToken(paramQuery.getPageToken());
      localBuilder.setSortOrder(paramQuery.getSortOrder());
    }
    return new o().query(paramGoogleApiClient, localBuilder.build());
  }
  
  private static class a
    extends c
  {
    private final BaseImplementation.b<DriveFolder.DriveFileResult> De;
    
    public a(BaseImplementation.b<DriveFolder.DriveFileResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(OnDriveIdResponse paramOnDriveIdResponse)
      throws RemoteException
    {
      this.De.b(new u.c(Status.Jv, new s(paramOnDriveIdResponse.getDriveId())));
    }
    
    public void o(Status paramStatus)
      throws RemoteException
    {
      this.De.b(new u.c(paramStatus, null));
    }
  }
  
  private static class b
    extends c
  {
    private final BaseImplementation.b<DriveFolder.DriveFolderResult> De;
    
    public b(BaseImplementation.b<DriveFolder.DriveFolderResult> paramb)
    {
      this.De = paramb;
    }
    
    public void a(OnDriveIdResponse paramOnDriveIdResponse)
      throws RemoteException
    {
      this.De.b(new u.e(Status.Jv, new u(paramOnDriveIdResponse.getDriveId())));
    }
    
    public void o(Status paramStatus)
      throws RemoteException
    {
      this.De.b(new u.e(paramStatus, null));
    }
  }
  
  private static class c
    implements DriveFolder.DriveFileResult
  {
    private final Status CM;
    private final DriveFile OY;
    
    public c(Status paramStatus, DriveFile paramDriveFile)
    {
      this.CM = paramStatus;
      this.OY = paramDriveFile;
    }
    
    public DriveFile getDriveFile()
    {
      return this.OY;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
  
  static abstract class d
    extends p<DriveFolder.DriveFileResult>
  {
    public DriveFolder.DriveFileResult t(Status paramStatus)
    {
      return new u.c(paramStatus, null);
    }
  }
  
  private static class e
    implements DriveFolder.DriveFolderResult
  {
    private final Status CM;
    private final DriveFolder OZ;
    
    public e(Status paramStatus, DriveFolder paramDriveFolder)
    {
      this.CM = paramStatus;
      this.OZ = paramDriveFolder;
    }
    
    public DriveFolder getDriveFolder()
    {
      return this.OZ;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
  
  static abstract class f
    extends p<DriveFolder.DriveFolderResult>
  {
    public DriveFolder.DriveFolderResult u(Status paramStatus)
    {
      return new u.e(paramStatus, null);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */