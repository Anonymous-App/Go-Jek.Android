package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jv;

public final class ParticipantEntity
  extends GamesDowngradeableSafeParcel
  implements Participant
{
  public static final Parcelable.Creator<ParticipantEntity> CREATOR = new ParticipantEntityCreatorCompat();
  private final int BR;
  private final int EZ;
  private final int Fa;
  private final String NH;
  private final Uri Vh;
  private final Uri Vi;
  private final String Vs;
  private final String Vt;
  private final PlayerEntity Wh;
  private final String Wq;
  private final String Xr;
  private final boolean acg;
  private final ParticipantResult ach;
  
  ParticipantEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, int paramInt2, String paramString3, boolean paramBoolean, PlayerEntity paramPlayerEntity, int paramInt3, ParticipantResult paramParticipantResult, String paramString4, String paramString5)
  {
    this.BR = paramInt1;
    this.Xr = paramString1;
    this.NH = paramString2;
    this.Vh = paramUri1;
    this.Vi = paramUri2;
    this.Fa = paramInt2;
    this.Wq = paramString3;
    this.acg = paramBoolean;
    this.Wh = paramPlayerEntity;
    this.EZ = paramInt3;
    this.ach = paramParticipantResult;
    this.Vs = paramString4;
    this.Vt = paramString5;
  }
  
  public ParticipantEntity(Participant paramParticipant)
  {
    this.BR = 3;
    this.Xr = paramParticipant.getParticipantId();
    this.NH = paramParticipant.getDisplayName();
    this.Vh = paramParticipant.getIconImageUri();
    this.Vi = paramParticipant.getHiResImageUri();
    this.Fa = paramParticipant.getStatus();
    this.Wq = paramParticipant.jX();
    this.acg = paramParticipant.isConnectedToRoom();
    Object localObject = paramParticipant.getPlayer();
    if (localObject == null) {}
    for (localObject = null;; localObject = new PlayerEntity((Player)localObject))
    {
      this.Wh = ((PlayerEntity)localObject);
      this.EZ = paramParticipant.getCapabilities();
      this.ach = paramParticipant.getResult();
      this.Vs = paramParticipant.getIconImageUrl();
      this.Vt = paramParticipant.getHiResImageUrl();
      return;
    }
  }
  
  static int a(Participant paramParticipant)
  {
    return n.hashCode(new Object[] { paramParticipant.getPlayer(), Integer.valueOf(paramParticipant.getStatus()), paramParticipant.jX(), Boolean.valueOf(paramParticipant.isConnectedToRoom()), paramParticipant.getDisplayName(), paramParticipant.getIconImageUri(), paramParticipant.getHiResImageUri(), Integer.valueOf(paramParticipant.getCapabilities()), paramParticipant.getResult(), paramParticipant.getParticipantId() });
  }
  
  static boolean a(Participant paramParticipant, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Participant)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramParticipant == paramObject);
      paramObject = (Participant)paramObject;
      if ((!n.equal(((Participant)paramObject).getPlayer(), paramParticipant.getPlayer())) || (!n.equal(Integer.valueOf(((Participant)paramObject).getStatus()), Integer.valueOf(paramParticipant.getStatus()))) || (!n.equal(((Participant)paramObject).jX(), paramParticipant.jX())) || (!n.equal(Boolean.valueOf(((Participant)paramObject).isConnectedToRoom()), Boolean.valueOf(paramParticipant.isConnectedToRoom()))) || (!n.equal(((Participant)paramObject).getDisplayName(), paramParticipant.getDisplayName())) || (!n.equal(((Participant)paramObject).getIconImageUri(), paramParticipant.getIconImageUri())) || (!n.equal(((Participant)paramObject).getHiResImageUri(), paramParticipant.getHiResImageUri())) || (!n.equal(Integer.valueOf(((Participant)paramObject).getCapabilities()), Integer.valueOf(paramParticipant.getCapabilities()))) || (!n.equal(((Participant)paramObject).getResult(), paramParticipant.getResult()))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((Participant)paramObject).getParticipantId(), paramParticipant.getParticipantId()));
    return false;
  }
  
  static String b(Participant paramParticipant)
  {
    return n.h(paramParticipant).a("ParticipantId", paramParticipant.getParticipantId()).a("Player", paramParticipant.getPlayer()).a("Status", Integer.valueOf(paramParticipant.getStatus())).a("ClientAddress", paramParticipant.jX()).a("ConnectedToRoom", Boolean.valueOf(paramParticipant.isConnectedToRoom())).a("DisplayName", paramParticipant.getDisplayName()).a("IconImage", paramParticipant.getIconImageUri()).a("IconImageUrl", paramParticipant.getIconImageUrl()).a("HiResImage", paramParticipant.getHiResImageUri()).a("HiResImageUrl", paramParticipant.getHiResImageUrl()).a("Capabilities", Integer.valueOf(paramParticipant.getCapabilities())).a("Result", paramParticipant.getResult()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Participant freeze()
  {
    return this;
  }
  
  public int getCapabilities()
  {
    return this.EZ;
  }
  
  public String getDisplayName()
  {
    if (this.Wh == null) {
      return this.NH;
    }
    return this.Wh.getDisplayName();
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.Wh == null)
    {
      jv.b(this.NH, paramCharArrayBuffer);
      return;
    }
    this.Wh.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    if (this.Wh == null) {
      return this.Vi;
    }
    return this.Wh.getHiResImageUri();
  }
  
  public String getHiResImageUrl()
  {
    if (this.Wh == null) {
      return this.Vt;
    }
    return this.Wh.getHiResImageUrl();
  }
  
  public Uri getIconImageUri()
  {
    if (this.Wh == null) {
      return this.Vh;
    }
    return this.Wh.getIconImageUri();
  }
  
  public String getIconImageUrl()
  {
    if (this.Wh == null) {
      return this.Vs;
    }
    return this.Wh.getIconImageUrl();
  }
  
  public String getParticipantId()
  {
    return this.Xr;
  }
  
  public Player getPlayer()
  {
    return this.Wh;
  }
  
  public ParticipantResult getResult()
  {
    return this.ach;
  }
  
  public int getStatus()
  {
    return this.Fa;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isConnectedToRoom()
  {
    return this.acg;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String jX()
  {
    return this.Wq;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Object localObject2 = null;
    int j = 0;
    if (!gQ())
    {
      ParticipantEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.Xr);
    paramParcel.writeString(this.NH);
    Object localObject1;
    if (this.Vh == null)
    {
      localObject1 = null;
      label46:
      paramParcel.writeString((String)localObject1);
      if (this.Vi != null) {
        break label143;
      }
      localObject1 = localObject2;
      label63:
      paramParcel.writeString((String)localObject1);
      paramParcel.writeInt(this.Fa);
      paramParcel.writeString(this.Wq);
      if (!this.acg) {
        break label155;
      }
      i = 1;
      label94:
      paramParcel.writeInt(i);
      if (this.Wh != null) {
        break label160;
      }
    }
    label143:
    label155:
    label160:
    for (int i = j;; i = 1)
    {
      paramParcel.writeInt(i);
      if (this.Wh == null) {
        break;
      }
      this.Wh.writeToParcel(paramParcel, paramInt);
      return;
      localObject1 = this.Vh.toString();
      break label46;
      localObject1 = this.Vi.toString();
      break label63;
      i = 0;
      break label94;
    }
  }
  
  static final class ParticipantEntityCreatorCompat
    extends ParticipantEntityCreator
  {
    public ParticipantEntity cm(Parcel paramParcel)
    {
      int i = 1;
      if ((ParticipantEntity.b(ParticipantEntity.jT())) || (ParticipantEntity.bw(ParticipantEntity.class.getCanonicalName()))) {
        return super.cm(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2;
      label68:
      int j;
      String str3;
      boolean bool;
      if (localObject1 == null)
      {
        localObject1 = null;
        localObject2 = paramParcel.readString();
        if (localObject2 != null) {
          break label151;
        }
        localObject2 = null;
        j = paramParcel.readInt();
        str3 = paramParcel.readString();
        if (paramParcel.readInt() <= 0) {
          break label161;
        }
        bool = true;
        label89:
        if (paramParcel.readInt() <= 0) {
          break label167;
        }
        label96:
        if (i == 0) {
          break label172;
        }
      }
      label151:
      label161:
      label167:
      label172:
      for (paramParcel = (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(paramParcel);; paramParcel = null)
      {
        return new ParticipantEntity(3, str1, str2, (Uri)localObject1, (Uri)localObject2, j, str3, bool, paramParcel, 7, null, null, null);
        localObject1 = Uri.parse((String)localObject1);
        break;
        localObject2 = Uri.parse((String)localObject2);
        break label68;
        bool = false;
        break label89;
        i = 0;
        break label96;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/ParticipantEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */