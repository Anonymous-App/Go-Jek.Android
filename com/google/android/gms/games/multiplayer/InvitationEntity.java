package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.ArrayList;

public final class InvitationEntity
  extends GamesDowngradeableSafeParcel
  implements Invitation
{
  public static final Parcelable.Creator<InvitationEntity> CREATOR = new InvitationEntityCreatorCompat();
  private final int BR;
  private final String WO;
  private final GameEntity aay;
  private final long abZ;
  private final int aca;
  private final ParticipantEntity acb;
  private final ArrayList<ParticipantEntity> acc;
  private final int acd;
  private final int ace;
  
  InvitationEntity(int paramInt1, GameEntity paramGameEntity, String paramString, long paramLong, int paramInt2, ParticipantEntity paramParticipantEntity, ArrayList<ParticipantEntity> paramArrayList, int paramInt3, int paramInt4)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.WO = paramString;
    this.abZ = paramLong;
    this.aca = paramInt2;
    this.acb = paramParticipantEntity;
    this.acc = paramArrayList;
    this.acd = paramInt3;
    this.ace = paramInt4;
  }
  
  InvitationEntity(Invitation paramInvitation)
  {
    this.BR = 2;
    this.aay = new GameEntity(paramInvitation.getGame());
    this.WO = paramInvitation.getInvitationId();
    this.abZ = paramInvitation.getCreationTimestamp();
    this.aca = paramInvitation.getInvitationType();
    this.acd = paramInvitation.getVariant();
    this.ace = paramInvitation.getAvailableAutoMatchSlots();
    String str = paramInvitation.getInviter().getParticipantId();
    Participant localParticipant = null;
    ArrayList localArrayList = paramInvitation.getParticipants();
    int j = localArrayList.size();
    this.acc = new ArrayList(j);
    int i = 0;
    paramInvitation = localParticipant;
    while (i < j)
    {
      localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(str)) {
        paramInvitation = localParticipant;
      }
      this.acc.add((ParticipantEntity)localParticipant.freeze());
      i += 1;
    }
    o.b(paramInvitation, "Must have a valid inviter!");
    this.acb = ((ParticipantEntity)paramInvitation.freeze());
  }
  
  static int a(Invitation paramInvitation)
  {
    return n.hashCode(new Object[] { paramInvitation.getGame(), paramInvitation.getInvitationId(), Long.valueOf(paramInvitation.getCreationTimestamp()), Integer.valueOf(paramInvitation.getInvitationType()), paramInvitation.getInviter(), paramInvitation.getParticipants(), Integer.valueOf(paramInvitation.getVariant()), Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots()) });
  }
  
  static boolean a(Invitation paramInvitation, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Invitation)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramInvitation == paramObject);
      paramObject = (Invitation)paramObject;
      if ((!n.equal(((Invitation)paramObject).getGame(), paramInvitation.getGame())) || (!n.equal(((Invitation)paramObject).getInvitationId(), paramInvitation.getInvitationId())) || (!n.equal(Long.valueOf(((Invitation)paramObject).getCreationTimestamp()), Long.valueOf(paramInvitation.getCreationTimestamp()))) || (!n.equal(Integer.valueOf(((Invitation)paramObject).getInvitationType()), Integer.valueOf(paramInvitation.getInvitationType()))) || (!n.equal(((Invitation)paramObject).getInviter(), paramInvitation.getInviter())) || (!n.equal(((Invitation)paramObject).getParticipants(), paramInvitation.getParticipants())) || (!n.equal(Integer.valueOf(((Invitation)paramObject).getVariant()), Integer.valueOf(paramInvitation.getVariant())))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(Integer.valueOf(((Invitation)paramObject).getAvailableAutoMatchSlots()), Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots())));
    return false;
  }
  
  static String b(Invitation paramInvitation)
  {
    return n.h(paramInvitation).a("Game", paramInvitation.getGame()).a("InvitationId", paramInvitation.getInvitationId()).a("CreationTimestamp", Long.valueOf(paramInvitation.getCreationTimestamp())).a("InvitationType", Integer.valueOf(paramInvitation.getInvitationType())).a("Inviter", paramInvitation.getInviter()).a("Participants", paramInvitation.getParticipants()).a("Variant", Integer.valueOf(paramInvitation.getVariant())).a("AvailableAutoMatchSlots", Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Invitation freeze()
  {
    return this;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    return this.ace;
  }
  
  public long getCreationTimestamp()
  {
    return this.abZ;
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public String getInvitationId()
  {
    return this.WO;
  }
  
  public int getInvitationType()
  {
    return this.aca;
  }
  
  public Participant getInviter()
  {
    return this.acb;
  }
  
  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.acc);
  }
  
  public int getVariant()
  {
    return this.acd;
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
    if (!gQ()) {
      InvitationEntityCreator.a(this, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      this.aay.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(this.WO);
      paramParcel.writeLong(this.abZ);
      paramParcel.writeInt(this.aca);
      this.acb.writeToParcel(paramParcel, paramInt);
      int j = this.acc.size();
      paramParcel.writeInt(j);
      int i = 0;
      while (i < j)
      {
        ((ParticipantEntity)this.acc.get(i)).writeToParcel(paramParcel, paramInt);
        i += 1;
      }
    }
  }
  
  static final class InvitationEntityCreatorCompat
    extends InvitationEntityCreator
  {
    public InvitationEntity cl(Parcel paramParcel)
    {
      if ((InvitationEntity.b(InvitationEntity.jT())) || (InvitationEntity.bw(InvitationEntity.class.getCanonicalName()))) {
        return super.cl(paramParcel);
      }
      GameEntity localGameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(paramParcel);
      String str = paramParcel.readString();
      long l = paramParcel.readLong();
      int j = paramParcel.readInt();
      ParticipantEntity localParticipantEntity = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(paramParcel);
      int k = paramParcel.readInt();
      ArrayList localArrayList = new ArrayList(k);
      int i = 0;
      while (i < k)
      {
        localArrayList.add(ParticipantEntity.CREATOR.createFromParcel(paramParcel));
        i += 1;
      }
      return new InvitationEntity(2, localGameEntity, str, l, j, localParticipantEntity, localArrayList, -1, 0);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/InvitationEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */