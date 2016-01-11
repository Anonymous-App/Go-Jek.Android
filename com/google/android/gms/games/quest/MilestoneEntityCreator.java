package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class MilestoneEntityCreator
  implements Parcelable.Creator<MilestoneEntity>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(MilestoneEntity paramMilestoneEntity, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramMilestoneEntity.getMilestoneId(), false);
    b.c(paramParcel, 1000, paramMilestoneEntity.getVersionCode());
    b.a(paramParcel, 2, paramMilestoneEntity.getCurrentProgress());
    b.a(paramParcel, 3, paramMilestoneEntity.getTargetProgress());
    b.a(paramParcel, 4, paramMilestoneEntity.getCompletionRewardData(), false);
    b.c(paramParcel, 5, paramMilestoneEntity.getState());
    b.a(paramParcel, 6, paramMilestoneEntity.getEventId(), false);
    b.H(paramParcel, paramInt);
  }
  
  public MilestoneEntity createFromParcel(Parcel paramParcel)
  {
    long l1 = 0L;
    int i = 0;
    String str1 = null;
    int k = a.C(paramParcel);
    byte[] arrayOfByte = null;
    long l2 = 0L;
    String str2 = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        str2 = a.o(paramParcel, m);
        break;
      case 1000: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        l2 = a.i(paramParcel, m);
        break;
      case 3: 
        l1 = a.i(paramParcel, m);
        break;
      case 4: 
        arrayOfByte = a.r(paramParcel, m);
        break;
      case 5: 
        i = a.g(paramParcel, m);
        break;
      case 6: 
        str1 = a.o(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new MilestoneEntity(j, str2, l2, l1, arrayOfByte, i, str1);
  }
  
  public MilestoneEntity[] newArray(int paramInt)
  {
    return new MilestoneEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/quest/MilestoneEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */