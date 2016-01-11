package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class hq
  implements SafeParcelable
{
  public static final hr CREATOR = new hr();
  final int BR;
  public final String Co;
  public final boolean Cp;
  public final boolean Cq;
  public final String Cr;
  public final hk[] Cs;
  final int[] Ct;
  public final String Cu;
  public final String name;
  public final int weight;
  
  hq(int paramInt1, String paramString1, String paramString2, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString3, hk[] paramArrayOfhk, int[] paramArrayOfInt, String paramString4)
  {
    this.BR = paramInt1;
    this.name = paramString1;
    this.Co = paramString2;
    this.Cp = paramBoolean1;
    this.weight = paramInt2;
    this.Cq = paramBoolean2;
    this.Cr = paramString3;
    this.Cs = paramArrayOfhk;
    this.Ct = paramArrayOfInt;
    this.Cu = paramString4;
  }
  
  hq(String paramString1, String paramString2, boolean paramBoolean1, int paramInt, boolean paramBoolean2, String paramString3, hk[] paramArrayOfhk, int[] paramArrayOfInt, String paramString4)
  {
    this(2, paramString1, paramString2, paramBoolean1, paramInt, paramBoolean2, paramString3, paramArrayOfhk, paramArrayOfInt, paramString4);
  }
  
  public int describeContents()
  {
    hr localhr = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof hq))
    {
      paramObject = (hq)paramObject;
      bool1 = bool2;
      if (this.name.equals(((hq)paramObject).name))
      {
        bool1 = bool2;
        if (this.Co.equals(((hq)paramObject).Co))
        {
          bool1 = bool2;
          if (this.Cp == ((hq)paramObject).Cp) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hr localhr = CREATOR;
    hr.a(this, paramParcel, paramInt);
  }
  
  public static final class a
  {
    private final List<hk> CA;
    private BitSet CB;
    private String CC;
    private String Cv;
    private boolean Cw;
    private int Cx;
    private boolean Cy;
    private String Cz;
    private final String mName;
    
    public a(String paramString)
    {
      this.mName = paramString;
      this.Cx = 1;
      this.CA = new ArrayList();
    }
    
    public a E(boolean paramBoolean)
    {
      this.Cw = paramBoolean;
      return this;
    }
    
    public a F(boolean paramBoolean)
    {
      this.Cy = paramBoolean;
      return this;
    }
    
    public a P(int paramInt)
    {
      if (this.CB == null) {
        this.CB = new BitSet();
      }
      this.CB.set(paramInt);
      return this;
    }
    
    public a at(String paramString)
    {
      this.Cv = paramString;
      return this;
    }
    
    public a au(String paramString)
    {
      this.CC = paramString;
      return this;
    }
    
    public hq fm()
    {
      int i = 0;
      Object localObject = null;
      if (this.CB != null)
      {
        int[] arrayOfInt = new int[this.CB.cardinality()];
        int j = this.CB.nextSetBit(0);
        for (;;)
        {
          localObject = arrayOfInt;
          if (j < 0) {
            break;
          }
          arrayOfInt[i] = j;
          j = this.CB.nextSetBit(j + 1);
          i += 1;
        }
      }
      return new hq(this.mName, this.Cv, this.Cw, this.Cx, this.Cy, this.Cz, (hk[])this.CA.toArray(new hk[this.CA.size()]), (int[])localObject, this.CC);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/hq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */