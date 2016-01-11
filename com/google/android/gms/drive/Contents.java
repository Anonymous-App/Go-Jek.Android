package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public class Contents
  implements SafeParcelable
{
  public static final Parcelable.Creator<Contents> CREATOR = new a();
  final int BR;
  final ParcelFileDescriptor KE;
  final int MV;
  final DriveId MW;
  final boolean MX;
  private boolean MY = false;
  private boolean MZ = false;
  private boolean mClosed = false;
  final int uQ;
  
  Contents(int paramInt1, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt2, int paramInt3, DriveId paramDriveId, boolean paramBoolean)
  {
    this.BR = paramInt1;
    this.KE = paramParcelFileDescriptor;
    this.uQ = paramInt2;
    this.MV = paramInt3;
    this.MW = paramDriveId;
    this.MX = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DriveId getDriveId()
  {
    return this.MW;
  }
  
  public InputStream getInputStream()
  {
    if (this.mClosed) {
      throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
    }
    if (this.MV != 268435456) {
      throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
    }
    if (this.MY) {
      throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
    }
    this.MY = true;
    return new FileInputStream(this.KE.getFileDescriptor());
  }
  
  public int getMode()
  {
    return this.MV;
  }
  
  public OutputStream getOutputStream()
  {
    if (this.mClosed) {
      throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }
    if (this.MV != 536870912) {
      throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
    }
    if (this.MZ) {
      throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
    }
    this.MZ = true;
    return new FileOutputStream(this.KE.getFileDescriptor());
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
  {
    if (this.mClosed) {
      throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }
    return this.KE;
  }
  
  public int getRequestId()
  {
    return this.uQ;
  }
  
  public void hJ()
  {
    this.mClosed = true;
  }
  
  public boolean hK()
  {
    return this.mClosed;
  }
  
  public boolean hL()
  {
    return this.MX;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/Contents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */