package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;

public final class SnapshotMetadataEntity
  implements SafeParcelable, SnapshotMetadata
{
  public static final SnapshotMetadataEntityCreator CREATOR = new SnapshotMetadataEntityCreator();
  private final int BR;
  private final String Nw;
  private final String Tr;
  private final String WI;
  private final GameEntity aay;
  private final Uri adk;
  private final PlayerEntity ado;
  private final String adp;
  private final long adq;
  private final long adr;
  private final float ads;
  private final String adt;
  
  SnapshotMetadataEntity(int paramInt, GameEntity paramGameEntity, PlayerEntity paramPlayerEntity, String paramString1, Uri paramUri, String paramString2, String paramString3, String paramString4, long paramLong1, long paramLong2, float paramFloat, String paramString5)
  {
    this.BR = paramInt;
    this.aay = paramGameEntity;
    this.ado = paramPlayerEntity;
    this.WI = paramString1;
    this.adk = paramUri;
    this.adp = paramString2;
    this.ads = paramFloat;
    this.Nw = paramString3;
    this.Tr = paramString4;
    this.adq = paramLong1;
    this.adr = paramLong2;
    this.adt = paramString5;
  }
  
  public SnapshotMetadataEntity(SnapshotMetadata paramSnapshotMetadata)
  {
    this.BR = 3;
    this.aay = new GameEntity(paramSnapshotMetadata.getGame());
    this.ado = new PlayerEntity(paramSnapshotMetadata.getOwner());
    this.WI = paramSnapshotMetadata.getSnapshotId();
    this.adk = paramSnapshotMetadata.getCoverImageUri();
    this.adp = paramSnapshotMetadata.getCoverImageUrl();
    this.ads = paramSnapshotMetadata.getCoverImageAspectRatio();
    this.Nw = paramSnapshotMetadata.getTitle();
    this.Tr = paramSnapshotMetadata.getDescription();
    this.adq = paramSnapshotMetadata.getLastModifiedTimestamp();
    this.adr = paramSnapshotMetadata.getPlayedTime();
    this.adt = paramSnapshotMetadata.getUniqueName();
  }
  
  static int a(SnapshotMetadata paramSnapshotMetadata)
  {
    return n.hashCode(new Object[] { paramSnapshotMetadata.getGame(), paramSnapshotMetadata.getOwner(), paramSnapshotMetadata.getSnapshotId(), paramSnapshotMetadata.getCoverImageUri(), Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio()), paramSnapshotMetadata.getTitle(), paramSnapshotMetadata.getDescription(), Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(paramSnapshotMetadata.getPlayedTime()), paramSnapshotMetadata.getUniqueName() });
  }
  
  static boolean a(SnapshotMetadata paramSnapshotMetadata, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof SnapshotMetadata)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramSnapshotMetadata == paramObject);
      paramObject = (SnapshotMetadata)paramObject;
      if ((!n.equal(((SnapshotMetadata)paramObject).getGame(), paramSnapshotMetadata.getGame())) || (!n.equal(((SnapshotMetadata)paramObject).getOwner(), paramSnapshotMetadata.getOwner())) || (!n.equal(((SnapshotMetadata)paramObject).getSnapshotId(), paramSnapshotMetadata.getSnapshotId())) || (!n.equal(((SnapshotMetadata)paramObject).getCoverImageUri(), paramSnapshotMetadata.getCoverImageUri())) || (!n.equal(Float.valueOf(((SnapshotMetadata)paramObject).getCoverImageAspectRatio()), Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio()))) || (!n.equal(((SnapshotMetadata)paramObject).getTitle(), paramSnapshotMetadata.getTitle())) || (!n.equal(((SnapshotMetadata)paramObject).getDescription(), paramSnapshotMetadata.getDescription())) || (!n.equal(Long.valueOf(((SnapshotMetadata)paramObject).getLastModifiedTimestamp()), Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp()))) || (!n.equal(Long.valueOf(((SnapshotMetadata)paramObject).getPlayedTime()), Long.valueOf(paramSnapshotMetadata.getPlayedTime())))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((SnapshotMetadata)paramObject).getUniqueName(), paramSnapshotMetadata.getUniqueName()));
    return false;
  }
  
  static String b(SnapshotMetadata paramSnapshotMetadata)
  {
    return n.h(paramSnapshotMetadata).a("Game", paramSnapshotMetadata.getGame()).a("Owner", paramSnapshotMetadata.getOwner()).a("SnapshotId", paramSnapshotMetadata.getSnapshotId()).a("CoverImageUri", paramSnapshotMetadata.getCoverImageUri()).a("CoverImageUrl", paramSnapshotMetadata.getCoverImageUrl()).a("CoverImageAspectRatio", Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio())).a("Description", paramSnapshotMetadata.getDescription()).a("LastModifiedTimestamp", Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp())).a("PlayedTime", Long.valueOf(paramSnapshotMetadata.getPlayedTime())).a("UniqueName", paramSnapshotMetadata.getUniqueName()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public SnapshotMetadata freeze()
  {
    return this;
  }
  
  public float getCoverImageAspectRatio()
  {
    return this.ads;
  }
  
  public Uri getCoverImageUri()
  {
    return this.adk;
  }
  
  public String getCoverImageUrl()
  {
    return this.adp;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public long getLastModifiedTimestamp()
  {
    return this.adq;
  }
  
  public Player getOwner()
  {
    return this.ado;
  }
  
  public long getPlayedTime()
  {
    return this.adr;
  }
  
  public String getSnapshotId()
  {
    return this.WI;
  }
  
  public String getTitle()
  {
    return this.Nw;
  }
  
  public String getUniqueName()
  {
    return this.adt;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    SnapshotMetadataEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/snapshot/SnapshotMetadataEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */