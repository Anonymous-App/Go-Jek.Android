package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.PlayerEntity;

public class AchievementEntityCreator
  implements Parcelable.Creator<AchievementEntity>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(AchievementEntity paramAchievementEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramAchievementEntity.getAchievementId(), false);
    b.c(paramParcel, 2, paramAchievementEntity.getType());
    b.a(paramParcel, 3, paramAchievementEntity.getName(), false);
    b.a(paramParcel, 4, paramAchievementEntity.getDescription(), false);
    b.a(paramParcel, 5, paramAchievementEntity.getUnlockedImageUri(), paramInt, false);
    b.a(paramParcel, 6, paramAchievementEntity.getUnlockedImageUrl(), false);
    b.a(paramParcel, 7, paramAchievementEntity.getRevealedImageUri(), paramInt, false);
    b.a(paramParcel, 8, paramAchievementEntity.getRevealedImageUrl(), false);
    b.c(paramParcel, 9, paramAchievementEntity.getTotalSteps());
    b.a(paramParcel, 10, paramAchievementEntity.getFormattedTotalSteps(), false);
    b.a(paramParcel, 11, paramAchievementEntity.getPlayer(), paramInt, false);
    b.c(paramParcel, 12, paramAchievementEntity.getState());
    b.c(paramParcel, 13, paramAchievementEntity.getCurrentSteps());
    b.a(paramParcel, 14, paramAchievementEntity.getFormattedCurrentSteps(), false);
    b.a(paramParcel, 15, paramAchievementEntity.getLastUpdatedTimestamp());
    b.a(paramParcel, 16, paramAchievementEntity.getXpValue());
    b.c(paramParcel, 1000, paramAchievementEntity.getVersionCode());
    b.H(paramParcel, i);
  }
  
  public AchievementEntity createFromParcel(Parcel paramParcel)
  {
    int i1 = a.C(paramParcel);
    int n = 0;
    String str7 = null;
    int m = 0;
    String str6 = null;
    String str5 = null;
    Uri localUri2 = null;
    String str4 = null;
    Uri localUri1 = null;
    String str3 = null;
    int k = 0;
    String str2 = null;
    PlayerEntity localPlayerEntity = null;
    int j = 0;
    int i = 0;
    String str1 = null;
    long l2 = 0L;
    long l1 = 0L;
    while (paramParcel.dataPosition() < i1)
    {
      int i2 = a.B(paramParcel);
      switch (a.aD(i2))
      {
      default: 
        a.b(paramParcel, i2);
        break;
      case 1: 
        str7 = a.o(paramParcel, i2);
        break;
      case 2: 
        m = a.g(paramParcel, i2);
        break;
      case 3: 
        str6 = a.o(paramParcel, i2);
        break;
      case 4: 
        str5 = a.o(paramParcel, i2);
        break;
      case 5: 
        localUri2 = (Uri)a.a(paramParcel, i2, Uri.CREATOR);
        break;
      case 6: 
        str4 = a.o(paramParcel, i2);
        break;
      case 7: 
        localUri1 = (Uri)a.a(paramParcel, i2, Uri.CREATOR);
        break;
      case 8: 
        str3 = a.o(paramParcel, i2);
        break;
      case 9: 
        k = a.g(paramParcel, i2);
        break;
      case 10: 
        str2 = a.o(paramParcel, i2);
        break;
      case 11: 
        localPlayerEntity = (PlayerEntity)a.a(paramParcel, i2, PlayerEntity.CREATOR);
        break;
      case 12: 
        j = a.g(paramParcel, i2);
        break;
      case 13: 
        i = a.g(paramParcel, i2);
        break;
      case 14: 
        str1 = a.o(paramParcel, i2);
        break;
      case 15: 
        l2 = a.i(paramParcel, i2);
        break;
      case 16: 
        l1 = a.i(paramParcel, i2);
        break;
      case 1000: 
        n = a.g(paramParcel, i2);
      }
    }
    if (paramParcel.dataPosition() != i1) {
      throw new a.a("Overread allowed size end=" + i1, paramParcel);
    }
    return new AchievementEntity(n, str7, m, str6, str5, localUri2, str4, localUri1, str3, k, str2, localPlayerEntity, j, i, str1, l2, l1);
  }
  
  public AchievementEntity[] newArray(int paramInt)
  {
    return new AchievementEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/achievement/AchievementEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */