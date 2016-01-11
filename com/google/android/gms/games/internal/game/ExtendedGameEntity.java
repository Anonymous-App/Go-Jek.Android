package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import java.util.ArrayList;

public final class ExtendedGameEntity
  extends GamesDowngradeableSafeParcel
  implements ExtendedGame
{
  public static final ExtendedGameEntityCreator CREATOR = new ExtendedGameEntityCreatorCompat();
  private final int BR;
  private final boolean aaA;
  private final int aaB;
  private final long aaC;
  private final long aaD;
  private final String aaE;
  private final long aaF;
  private final String aaG;
  private final ArrayList<GameBadgeEntity> aaH;
  private final SnapshotMetadataEntity aaI;
  private final GameEntity aay;
  private final int aaz;
  
  ExtendedGameEntity(int paramInt1, GameEntity paramGameEntity, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong1, long paramLong2, String paramString1, long paramLong3, String paramString2, ArrayList<GameBadgeEntity> paramArrayList, SnapshotMetadataEntity paramSnapshotMetadataEntity)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.aaz = paramInt2;
    this.aaA = paramBoolean;
    this.aaB = paramInt3;
    this.aaC = paramLong1;
    this.aaD = paramLong2;
    this.aaE = paramString1;
    this.aaF = paramLong3;
    this.aaG = paramString2;
    this.aaH = paramArrayList;
    this.aaI = paramSnapshotMetadataEntity;
  }
  
  public ExtendedGameEntity(ExtendedGame paramExtendedGame)
  {
    this.BR = 2;
    Object localObject1 = paramExtendedGame.getGame();
    if (localObject1 == null)
    {
      localObject1 = null;
      this.aay = ((GameEntity)localObject1);
      this.aaz = paramExtendedGame.kS();
      this.aaA = paramExtendedGame.kT();
      this.aaB = paramExtendedGame.kU();
      this.aaC = paramExtendedGame.kV();
      this.aaD = paramExtendedGame.kW();
      this.aaE = paramExtendedGame.kX();
      this.aaF = paramExtendedGame.kY();
      this.aaG = paramExtendedGame.kZ();
      localObject1 = paramExtendedGame.la();
      if (localObject1 != null) {
        break label213;
      }
    }
    label213:
    for (localObject1 = localObject2;; localObject1 = new SnapshotMetadataEntity((SnapshotMetadata)localObject1))
    {
      this.aaI = ((SnapshotMetadataEntity)localObject1);
      paramExtendedGame = paramExtendedGame.kR();
      int j = paramExtendedGame.size();
      this.aaH = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        this.aaH.add((GameBadgeEntity)((GameBadge)paramExtendedGame.get(i)).freeze());
        i += 1;
      }
      localObject1 = new GameEntity((Game)localObject1);
      break;
    }
  }
  
  static int a(ExtendedGame paramExtendedGame)
  {
    return n.hashCode(new Object[] { paramExtendedGame.getGame(), Integer.valueOf(paramExtendedGame.kS()), Boolean.valueOf(paramExtendedGame.kT()), Integer.valueOf(paramExtendedGame.kU()), Long.valueOf(paramExtendedGame.kV()), Long.valueOf(paramExtendedGame.kW()), paramExtendedGame.kX(), Long.valueOf(paramExtendedGame.kY()), paramExtendedGame.kZ() });
  }
  
  static boolean a(ExtendedGame paramExtendedGame, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof ExtendedGame)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramExtendedGame == paramObject);
      paramObject = (ExtendedGame)paramObject;
      if ((!n.equal(((ExtendedGame)paramObject).getGame(), paramExtendedGame.getGame())) || (!n.equal(Integer.valueOf(((ExtendedGame)paramObject).kS()), Integer.valueOf(paramExtendedGame.kS()))) || (!n.equal(Boolean.valueOf(((ExtendedGame)paramObject).kT()), Boolean.valueOf(paramExtendedGame.kT()))) || (!n.equal(Integer.valueOf(((ExtendedGame)paramObject).kU()), Integer.valueOf(paramExtendedGame.kU()))) || (!n.equal(Long.valueOf(((ExtendedGame)paramObject).kV()), Long.valueOf(paramExtendedGame.kV()))) || (!n.equal(Long.valueOf(((ExtendedGame)paramObject).kW()), Long.valueOf(paramExtendedGame.kW()))) || (!n.equal(((ExtendedGame)paramObject).kX(), paramExtendedGame.kX())) || (!n.equal(Long.valueOf(((ExtendedGame)paramObject).kY()), Long.valueOf(paramExtendedGame.kY())))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((ExtendedGame)paramObject).kZ(), paramExtendedGame.kZ()));
    return false;
  }
  
  static String b(ExtendedGame paramExtendedGame)
  {
    return n.h(paramExtendedGame).a("Game", paramExtendedGame.getGame()).a("Availability", Integer.valueOf(paramExtendedGame.kS())).a("Owned", Boolean.valueOf(paramExtendedGame.kT())).a("AchievementUnlockedCount", Integer.valueOf(paramExtendedGame.kU())).a("LastPlayedServerTimestamp", Long.valueOf(paramExtendedGame.kV())).a("PriceMicros", Long.valueOf(paramExtendedGame.kW())).a("FormattedPrice", paramExtendedGame.kX()).a("FullPriceMicros", Long.valueOf(paramExtendedGame.kY())).a("FormattedFullPrice", paramExtendedGame.kZ()).a("Snapshot", paramExtendedGame.la()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
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
  
  public ArrayList<GameBadge> kR()
  {
    return new ArrayList(this.aaH);
  }
  
  public int kS()
  {
    return this.aaz;
  }
  
  public boolean kT()
  {
    return this.aaA;
  }
  
  public int kU()
  {
    return this.aaB;
  }
  
  public long kV()
  {
    return this.aaC;
  }
  
  public long kW()
  {
    return this.aaD;
  }
  
  public String kX()
  {
    return this.aaE;
  }
  
  public long kY()
  {
    return this.aaF;
  }
  
  public String kZ()
  {
    return this.aaG;
  }
  
  public SnapshotMetadata la()
  {
    return this.aaI;
  }
  
  public GameEntity lb()
  {
    return this.aay;
  }
  
  public ExtendedGame lc()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int j = 0;
    if (!gQ())
    {
      ExtendedGameEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    this.aay.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.aaz);
    if (this.aaA) {}
    for (int i = 1;; i = 0)
    {
      paramParcel.writeInt(i);
      paramParcel.writeInt(this.aaB);
      paramParcel.writeLong(this.aaC);
      paramParcel.writeLong(this.aaD);
      paramParcel.writeString(this.aaE);
      paramParcel.writeLong(this.aaF);
      paramParcel.writeString(this.aaG);
      int k = this.aaH.size();
      paramParcel.writeInt(k);
      i = j;
      while (i < k)
      {
        ((GameBadgeEntity)this.aaH.get(i)).writeToParcel(paramParcel, paramInt);
        i += 1;
      }
      break;
    }
  }
  
  static final class ExtendedGameEntityCreatorCompat
    extends ExtendedGameEntityCreator
  {
    public ExtendedGameEntity cg(Parcel paramParcel)
    {
      if ((ExtendedGameEntity.b(ExtendedGameEntity.jT())) || (ExtendedGameEntity.bw(ExtendedGameEntity.class.getCanonicalName()))) {
        return super.cg(paramParcel);
      }
      GameEntity localGameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(paramParcel);
      int j = paramParcel.readInt();
      if (paramParcel.readInt() == 1) {}
      int k;
      long l1;
      long l2;
      String str1;
      long l3;
      String str2;
      ArrayList localArrayList;
      for (boolean bool = true;; bool = false)
      {
        k = paramParcel.readInt();
        l1 = paramParcel.readLong();
        l2 = paramParcel.readLong();
        str1 = paramParcel.readString();
        l3 = paramParcel.readLong();
        str2 = paramParcel.readString();
        int m = paramParcel.readInt();
        localArrayList = new ArrayList(m);
        int i = 0;
        while (i < m)
        {
          localArrayList.add(GameBadgeEntity.CREATOR.ch(paramParcel));
          i += 1;
        }
      }
      return new ExtendedGameEntity(2, localGameEntity, j, bool, k, l1, l2, str1, l3, str2, localArrayList, null);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/ExtendedGameEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */