package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class he
  implements SafeParcelable
{
  public static final hf CREATOR = new hf();
  final int BR;
  final hi[] BS;
  public final String BT;
  public final boolean BU;
  public final Account account;
  
  he(int paramInt, hi[] paramArrayOfhi, String paramString, boolean paramBoolean, Account paramAccount)
  {
    this.BR = paramInt;
    this.BS = paramArrayOfhi;
    this.BT = paramString;
    this.BU = paramBoolean;
    this.account = paramAccount;
  }
  
  he(String paramString, boolean paramBoolean, Account paramAccount, hi... paramVarArgs)
  {
    this(1, paramVarArgs, paramString, paramBoolean, paramAccount);
    paramString = new BitSet(hp.fl());
    int i = 0;
    while (i < paramVarArgs.length)
    {
      int j = paramVarArgs[i].Cg;
      if (j != -1)
      {
        if (paramString.get(j)) {
          throw new IllegalArgumentException("Duplicate global search section type " + hp.O(j));
        }
        paramString.set(j);
      }
      i += 1;
    }
  }
  
  public int describeContents()
  {
    hf localhf = CREATOR;
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hf localhf = CREATOR;
    hf.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private List<hi> BV;
    private String BW;
    private boolean BX;
    private Account BY;
    
    public a D(boolean paramBoolean)
    {
      this.BX = paramBoolean;
      return this;
    }
    
    public a a(hi paramhi)
    {
      if (this.BV == null) {
        this.BV = new ArrayList();
      }
      this.BV.add(paramhi);
      return this;
    }
    
    public a ar(String paramString)
    {
      this.BW = paramString;
      return this;
    }
    
    public he fj()
    {
      String str = this.BW;
      boolean bool = this.BX;
      Account localAccount = this.BY;
      if (this.BV != null) {}
      for (hi[] arrayOfhi = (hi[])this.BV.toArray(new hi[this.BV.size()]);; arrayOfhi = null) {
        return new he(str, bool, localAccount, arrayOfhi);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/he.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */