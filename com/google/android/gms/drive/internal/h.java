package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class h
{
  private String Nw;
  private DriveId Ny;
  protected MetadataChangeSet Oi;
  private Integer Oj;
  private final int Ok;
  
  public h(int paramInt)
  {
    this.Ok = paramInt;
  }
  
  public void a(DriveId paramDriveId)
  {
    this.Ny = ((DriveId)o.i(paramDriveId));
  }
  
  public void a(MetadataChangeSet paramMetadataChangeSet)
  {
    this.Oi = ((MetadataChangeSet)o.i(paramMetadataChangeSet));
  }
  
  public void bi(String paramString)
  {
    this.Nw = ((String)o.i(paramString));
  }
  
  public void bk(int paramInt)
  {
    this.Oj = Integer.valueOf(paramInt);
  }
  
  public IntentSender build(GoogleApiClient paramGoogleApiClient)
  {
    o.b(this.Oi, "Must provide initial metadata to CreateFileActivityBuilder.");
    o.a(paramGoogleApiClient.isConnected(), "Client must be connected");
    paramGoogleApiClient = (q)paramGoogleApiClient.a(Drive.CU);
    this.Oi.hS().setContext(paramGoogleApiClient.getContext());
    paramGoogleApiClient = paramGoogleApiClient.hY();
    if (this.Oj == null) {}
    for (int i = -1;; i = this.Oj.intValue()) {
      try
      {
        paramGoogleApiClient = paramGoogleApiClient.a(new CreateFileIntentSenderRequest(this.Oi.hS(), i, this.Nw, this.Ny, this.Ok));
        return paramGoogleApiClient;
      }
      catch (RemoteException paramGoogleApiClient)
      {
        throw new RuntimeException("Unable to connect Drive Play Service", paramGoogleApiClient);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */