package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class SnapshotMetadataChange
  implements SafeParcelable
{
  public static final SnapshotMetadataChangeCreator CREATOR = new SnapshotMetadataChangeCreator();
  public static final SnapshotMetadataChange EMPTY_CHANGE = new SnapshotMetadataChange();
  private final int BR;
  private final String Tr;
  private final Long adj;
  private final Uri adk;
  private a adl;
  
  SnapshotMetadataChange()
  {
    this(4, null, null, null, null);
  }
  
  SnapshotMetadataChange(int paramInt, String paramString, Long paramLong, a parama, Uri paramUri)
  {
    this.BR = paramInt;
    this.Tr = paramString;
    this.adj = paramLong;
    this.adl = parama;
    this.adk = paramUri;
    if (this.adl != null) {
      if (this.adk == null) {
        o.a(bool1, "Cannot set both a URI and an image");
      }
    }
    while (this.adk == null) {
      for (;;)
      {
        return;
        bool1 = false;
      }
    }
    if (this.adl == null) {}
    for (bool1 = bool2;; bool1 = false)
    {
      o.a(bool1, "Cannot set both a URI and an image");
      return;
    }
  }
  
  SnapshotMetadataChange(String paramString, Long paramLong, a parama, Uri paramUri)
  {
    this(4, paramString, paramLong, parama, paramUri);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Bitmap getCoverImage()
  {
    if (this.adl == null) {
      return null;
    }
    return this.adl.gw();
  }
  
  public Uri getCoverImageUri()
  {
    return this.adk;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public Long getPlayedTimeMillis()
  {
    return this.adj;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public a lM()
  {
    return this.adl;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    SnapshotMetadataChangeCreator.a(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private String Tr;
    private Uri adk;
    private Long adm;
    private a adn;
    
    public SnapshotMetadataChange build()
    {
      return new SnapshotMetadataChange(this.Tr, this.adm, this.adn, this.adk);
    }
    
    public Builder fromMetadata(SnapshotMetadata paramSnapshotMetadata)
    {
      this.Tr = paramSnapshotMetadata.getDescription();
      this.adm = Long.valueOf(paramSnapshotMetadata.getPlayedTime());
      if (this.adm.longValue() == -1L) {
        this.adm = null;
      }
      this.adk = paramSnapshotMetadata.getCoverImageUri();
      if (this.adk != null) {
        this.adn = null;
      }
      return this;
    }
    
    public Builder setCoverImage(Bitmap paramBitmap)
    {
      this.adn = new a(paramBitmap);
      this.adk = null;
      return this;
    }
    
    public Builder setDescription(String paramString)
    {
      this.Tr = paramString;
      return this;
    }
    
    public Builder setPlayedTimeMillis(long paramLong)
    {
      this.adm = Long.valueOf(paramLong);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/snapshot/SnapshotMetadataChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */