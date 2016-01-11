package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

@ez
public final class fk
  implements SafeParcelable
{
  public static final fl CREATOR = new fl();
  public final int errorCode;
  public final int orientation;
  public final List<String> qf;
  public final List<String> qg;
  public final long qj;
  public final String rP;
  public final boolean tF;
  public final String tG;
  public final long tH;
  public final boolean tI;
  public final long tJ;
  public final List<String> tK;
  public final String tL;
  public final long tM;
  public final String tN;
  public final boolean tO;
  public final String tP;
  public final String tQ;
  public final boolean tR;
  public final boolean tS;
  public final boolean tT;
  public final int versionCode;
  
  public fk(int paramInt)
  {
    this(10, null, null, null, paramInt, null, -1L, false, -1L, null, -1L, -1, null, -1L, null, false, null, null, false, false, false, false);
  }
  
  public fk(int paramInt, long paramLong)
  {
    this(10, null, null, null, paramInt, null, -1L, false, -1L, null, paramLong, -1, null, -1L, null, false, null, null, false, false, false, false);
  }
  
  fk(int paramInt1, String paramString1, String paramString2, List<String> paramList1, int paramInt2, List<String> paramList2, long paramLong1, boolean paramBoolean1, long paramLong2, List<String> paramList3, long paramLong3, int paramInt3, String paramString3, long paramLong4, String paramString4, boolean paramBoolean2, String paramString5, String paramString6, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6)
  {
    this.versionCode = paramInt1;
    this.rP = paramString1;
    this.tG = paramString2;
    if (paramList1 != null)
    {
      paramString1 = Collections.unmodifiableList(paramList1);
      this.qf = paramString1;
      this.errorCode = paramInt2;
      if (paramList2 == null) {
        break label169;
      }
      paramString1 = Collections.unmodifiableList(paramList2);
      label52:
      this.qg = paramString1;
      this.tH = paramLong1;
      this.tI = paramBoolean1;
      this.tJ = paramLong2;
      if (paramList3 == null) {
        break label174;
      }
    }
    label169:
    label174:
    for (paramString1 = Collections.unmodifiableList(paramList3);; paramString1 = null)
    {
      this.tK = paramString1;
      this.qj = paramLong3;
      this.orientation = paramInt3;
      this.tL = paramString3;
      this.tM = paramLong4;
      this.tN = paramString4;
      this.tO = paramBoolean2;
      this.tP = paramString5;
      this.tQ = paramString6;
      this.tR = paramBoolean3;
      this.tS = paramBoolean4;
      this.tF = paramBoolean5;
      this.tT = paramBoolean6;
      return;
      paramString1 = null;
      break;
      paramString1 = null;
      break label52;
    }
  }
  
  public fk(String paramString1, String paramString2, List<String> paramList1, List<String> paramList2, long paramLong1, boolean paramBoolean1, long paramLong2, List<String> paramList3, long paramLong3, int paramInt, String paramString3, long paramLong4, String paramString4, String paramString5, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5)
  {
    this(10, paramString1, paramString2, paramList1, -2, paramList2, paramLong1, paramBoolean1, paramLong2, paramList3, paramLong3, paramInt, paramString3, paramLong4, paramString4, false, null, paramString5, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5);
  }
  
  public fk(String paramString1, String paramString2, List<String> paramList1, List<String> paramList2, long paramLong1, boolean paramBoolean1, long paramLong2, List<String> paramList3, long paramLong3, int paramInt, String paramString3, long paramLong4, String paramString4, boolean paramBoolean2, String paramString5, String paramString6, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6)
  {
    this(10, paramString1, paramString2, paramList1, -2, paramList2, paramLong1, paramBoolean1, paramLong2, paramList3, paramLong3, paramInt, paramString3, paramLong4, paramString4, paramBoolean2, paramString5, paramString6, paramBoolean3, paramBoolean4, paramBoolean5, paramBoolean6);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    fl.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */