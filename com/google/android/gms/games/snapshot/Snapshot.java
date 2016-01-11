package com.google.android.gms.games.snapshot;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.drive.Contents;

public abstract interface Snapshot
  extends Parcelable, Freezable<Snapshot>
{
  @Deprecated
  public abstract Contents getContents();
  
  public abstract SnapshotMetadata getMetadata();
  
  public abstract SnapshotContents getSnapshotContents();
  
  @Deprecated
  public abstract boolean modifyBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);
  
  @Deprecated
  public abstract byte[] readFully();
  
  @Deprecated
  public abstract boolean writeBytes(byte[] paramArrayOfByte);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/snapshot/Snapshot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */