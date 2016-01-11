package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;

public final class TurnBasedMatchEntity
  implements SafeParcelable, TurnBasedMatch
{
  public static final TurnBasedMatchEntityCreator CREATOR = new TurnBasedMatchEntityCreator();
  private final int BR;
  private final int Di;
  private final String Tr;
  private final long Wk;
  private final String Xh;
  private final GameEntity aay;
  private final long abZ;
  private final String acE;
  private final String acF;
  private final int acG;
  private final byte[] acH;
  private final String acI;
  private final byte[] acJ;
  private final int acK;
  private final int acL;
  private final boolean acM;
  private final String acN;
  private final ArrayList<ParticipantEntity> acc;
  private final int acd;
  private final Bundle acs;
  private final String acw;
  
  TurnBasedMatchEntity(int paramInt1, GameEntity paramGameEntity, String paramString1, String paramString2, long paramLong1, String paramString3, long paramLong2, String paramString4, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte1, ArrayList<ParticipantEntity> paramArrayList, String paramString5, byte[] paramArrayOfByte2, int paramInt5, Bundle paramBundle, int paramInt6, boolean paramBoolean, String paramString6, String paramString7)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.Xh = paramString1;
    this.acw = paramString2;
    this.abZ = paramLong1;
    this.acE = paramString3;
    this.Wk = paramLong2;
    this.acF = paramString4;
    this.acG = paramInt2;
    this.acL = paramInt6;
    this.acd = paramInt3;
    this.Di = paramInt4;
    this.acH = paramArrayOfByte1;
    this.acc = paramArrayList;
    this.acI = paramString5;
    this.acJ = paramArrayOfByte2;
    this.acK = paramInt5;
    this.acs = paramBundle;
    this.acM = paramBoolean;
    this.Tr = paramString6;
    this.acN = paramString7;
  }
  
  public TurnBasedMatchEntity(TurnBasedMatch paramTurnBasedMatch)
  {
    this.BR = 2;
    this.aay = new GameEntity(paramTurnBasedMatch.getGame());
    this.Xh = paramTurnBasedMatch.getMatchId();
    this.acw = paramTurnBasedMatch.getCreatorId();
    this.abZ = paramTurnBasedMatch.getCreationTimestamp();
    this.acE = paramTurnBasedMatch.getLastUpdaterId();
    this.Wk = paramTurnBasedMatch.getLastUpdatedTimestamp();
    this.acF = paramTurnBasedMatch.getPendingParticipantId();
    this.acG = paramTurnBasedMatch.getStatus();
    this.acL = paramTurnBasedMatch.getTurnStatus();
    this.acd = paramTurnBasedMatch.getVariant();
    this.Di = paramTurnBasedMatch.getVersion();
    this.acI = paramTurnBasedMatch.getRematchId();
    this.acK = paramTurnBasedMatch.getMatchNumber();
    this.acs = paramTurnBasedMatch.getAutoMatchCriteria();
    this.acM = paramTurnBasedMatch.isLocallyModified();
    this.Tr = paramTurnBasedMatch.getDescription();
    this.acN = paramTurnBasedMatch.getDescriptionParticipantId();
    byte[] arrayOfByte = paramTurnBasedMatch.getData();
    if (arrayOfByte == null)
    {
      this.acH = null;
      arrayOfByte = paramTurnBasedMatch.getPreviousMatchData();
      if (arrayOfByte != null) {
        break label313;
      }
      this.acJ = null;
    }
    for (;;)
    {
      paramTurnBasedMatch = paramTurnBasedMatch.getParticipants();
      int j = paramTurnBasedMatch.size();
      this.acc = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        this.acc.add((ParticipantEntity)((Participant)paramTurnBasedMatch.get(i)).freeze());
        i += 1;
      }
      this.acH = new byte[arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, this.acH, 0, arrayOfByte.length);
      break;
      label313:
      this.acJ = new byte[arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, this.acJ, 0, arrayOfByte.length);
    }
  }
  
  static int a(TurnBasedMatch paramTurnBasedMatch)
  {
    return n.hashCode(new Object[] { paramTurnBasedMatch.getGame(), paramTurnBasedMatch.getMatchId(), paramTurnBasedMatch.getCreatorId(), Long.valueOf(paramTurnBasedMatch.getCreationTimestamp()), paramTurnBasedMatch.getLastUpdaterId(), Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp()), paramTurnBasedMatch.getPendingParticipantId(), Integer.valueOf(paramTurnBasedMatch.getStatus()), Integer.valueOf(paramTurnBasedMatch.getTurnStatus()), paramTurnBasedMatch.getDescription(), Integer.valueOf(paramTurnBasedMatch.getVariant()), Integer.valueOf(paramTurnBasedMatch.getVersion()), paramTurnBasedMatch.getParticipants(), paramTurnBasedMatch.getRematchId(), Integer.valueOf(paramTurnBasedMatch.getMatchNumber()), paramTurnBasedMatch.getAutoMatchCriteria(), Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots()), Boolean.valueOf(paramTurnBasedMatch.isLocallyModified()) });
  }
  
  static int a(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      Participant localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(paramString)) {
        return localParticipant.getStatus();
      }
      i += 1;
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramTurnBasedMatch.getMatchId());
  }
  
  static boolean a(TurnBasedMatch paramTurnBasedMatch, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof TurnBasedMatch)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramTurnBasedMatch == paramObject);
      paramObject = (TurnBasedMatch)paramObject;
      if ((!n.equal(((TurnBasedMatch)paramObject).getGame(), paramTurnBasedMatch.getGame())) || (!n.equal(((TurnBasedMatch)paramObject).getMatchId(), paramTurnBasedMatch.getMatchId())) || (!n.equal(((TurnBasedMatch)paramObject).getCreatorId(), paramTurnBasedMatch.getCreatorId())) || (!n.equal(Long.valueOf(((TurnBasedMatch)paramObject).getCreationTimestamp()), Long.valueOf(paramTurnBasedMatch.getCreationTimestamp()))) || (!n.equal(((TurnBasedMatch)paramObject).getLastUpdaterId(), paramTurnBasedMatch.getLastUpdaterId())) || (!n.equal(Long.valueOf(((TurnBasedMatch)paramObject).getLastUpdatedTimestamp()), Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp()))) || (!n.equal(((TurnBasedMatch)paramObject).getPendingParticipantId(), paramTurnBasedMatch.getPendingParticipantId())) || (!n.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getStatus()), Integer.valueOf(paramTurnBasedMatch.getStatus()))) || (!n.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getTurnStatus()), Integer.valueOf(paramTurnBasedMatch.getTurnStatus()))) || (!n.equal(((TurnBasedMatch)paramObject).getDescription(), paramTurnBasedMatch.getDescription())) || (!n.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getVariant()), Integer.valueOf(paramTurnBasedMatch.getVariant()))) || (!n.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getVersion()), Integer.valueOf(paramTurnBasedMatch.getVersion()))) || (!n.equal(((TurnBasedMatch)paramObject).getParticipants(), paramTurnBasedMatch.getParticipants())) || (!n.equal(((TurnBasedMatch)paramObject).getRematchId(), paramTurnBasedMatch.getRematchId())) || (!n.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getMatchNumber()), Integer.valueOf(paramTurnBasedMatch.getMatchNumber()))) || (!n.equal(((TurnBasedMatch)paramObject).getAutoMatchCriteria(), paramTurnBasedMatch.getAutoMatchCriteria())) || (!n.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getAvailableAutoMatchSlots()), Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots())))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(Boolean.valueOf(((TurnBasedMatch)paramObject).isLocallyModified()), Boolean.valueOf(paramTurnBasedMatch.isLocallyModified())));
    return false;
  }
  
  static String b(TurnBasedMatch paramTurnBasedMatch)
  {
    return n.h(paramTurnBasedMatch).a("Game", paramTurnBasedMatch.getGame()).a("MatchId", paramTurnBasedMatch.getMatchId()).a("CreatorId", paramTurnBasedMatch.getCreatorId()).a("CreationTimestamp", Long.valueOf(paramTurnBasedMatch.getCreationTimestamp())).a("LastUpdaterId", paramTurnBasedMatch.getLastUpdaterId()).a("LastUpdatedTimestamp", Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp())).a("PendingParticipantId", paramTurnBasedMatch.getPendingParticipantId()).a("MatchStatus", Integer.valueOf(paramTurnBasedMatch.getStatus())).a("TurnStatus", Integer.valueOf(paramTurnBasedMatch.getTurnStatus())).a("Description", paramTurnBasedMatch.getDescription()).a("Variant", Integer.valueOf(paramTurnBasedMatch.getVariant())).a("Data", paramTurnBasedMatch.getData()).a("Version", Integer.valueOf(paramTurnBasedMatch.getVersion())).a("Participants", paramTurnBasedMatch.getParticipants()).a("RematchId", paramTurnBasedMatch.getRematchId()).a("PreviousData", paramTurnBasedMatch.getPreviousMatchData()).a("MatchNumber", Integer.valueOf(paramTurnBasedMatch.getMatchNumber())).a("AutoMatchCriteria", paramTurnBasedMatch.getAutoMatchCriteria()).a("AvailableAutoMatchSlots", Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots())).a("LocallyModified", Boolean.valueOf(paramTurnBasedMatch.isLocallyModified())).a("DescriptionParticipantId", paramTurnBasedMatch.getDescriptionParticipantId()).toString();
  }
  
  static String b(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    paramTurnBasedMatch = paramTurnBasedMatch.getParticipants();
    int j = paramTurnBasedMatch.size();
    int i = 0;
    while (i < j)
    {
      Participant localParticipant = (Participant)paramTurnBasedMatch.get(i);
      Player localPlayer = localParticipant.getPlayer();
      if ((localPlayer != null) && (localPlayer.getPlayerId().equals(paramString))) {
        return localParticipant.getParticipantId();
      }
      i += 1;
    }
    return null;
  }
  
  static Participant c(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      Participant localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(paramString)) {
        return localParticipant;
      }
      i += 1;
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramTurnBasedMatch.getMatchId());
  }
  
  static ArrayList<String> c(TurnBasedMatch paramTurnBasedMatch)
  {
    paramTurnBasedMatch = paramTurnBasedMatch.getParticipants();
    int j = paramTurnBasedMatch.size();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(((Participant)paramTurnBasedMatch.get(i)).getParticipantId());
      i += 1;
    }
    return localArrayList;
  }
  
  public boolean canRematch()
  {
    return (this.acG == 2) && (this.acI == null);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public TurnBasedMatch freeze()
  {
    return this;
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.acs;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    if (this.acs == null) {
      return 0;
    }
    return this.acs.getInt("max_automatch_players");
  }
  
  public long getCreationTimestamp()
  {
    return this.abZ;
  }
  
  public String getCreatorId()
  {
    return this.acw;
  }
  
  public byte[] getData()
  {
    return this.acH;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public Participant getDescriptionParticipant()
  {
    String str = getDescriptionParticipantId();
    if (str == null) {
      return null;
    }
    return getParticipant(str);
  }
  
  public String getDescriptionParticipantId()
  {
    return this.acN;
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public long getLastUpdatedTimestamp()
  {
    return this.Wk;
  }
  
  public String getLastUpdaterId()
  {
    return this.acE;
  }
  
  public String getMatchId()
  {
    return this.Xh;
  }
  
  public int getMatchNumber()
  {
    return this.acK;
  }
  
  public Participant getParticipant(String paramString)
  {
    return c(this, paramString);
  }
  
  public String getParticipantId(String paramString)
  {
    return b(this, paramString);
  }
  
  public ArrayList<String> getParticipantIds()
  {
    return c(this);
  }
  
  public int getParticipantStatus(String paramString)
  {
    return a(this, paramString);
  }
  
  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.acc);
  }
  
  public String getPendingParticipantId()
  {
    return this.acF;
  }
  
  public byte[] getPreviousMatchData()
  {
    return this.acJ;
  }
  
  public String getRematchId()
  {
    return this.acI;
  }
  
  public int getStatus()
  {
    return this.acG;
  }
  
  public int getTurnStatus()
  {
    return this.acL;
  }
  
  public int getVariant()
  {
    return this.acd;
  }
  
  public int getVersion()
  {
    return this.Di;
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
  
  public boolean isLocallyModified()
  {
    return this.acM;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    TurnBasedMatchEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */