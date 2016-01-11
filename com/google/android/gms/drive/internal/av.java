package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveFile.DownloadProgressListener;

class av
  extends c
{
  private final BaseImplementation.b<DriveApi.DriveContentsResult> De;
  private final DriveFile.DownloadProgressListener OU;
  
  av(BaseImplementation.b<DriveApi.DriveContentsResult> paramb, DriveFile.DownloadProgressListener paramDownloadProgressListener)
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
      this.De.b(new o.c(localStatus, new r(paramOnContentsResponse.id())));
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
    this.De.b(new o.c(paramStatus, null));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/av.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */