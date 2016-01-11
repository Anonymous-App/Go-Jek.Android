package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class hm
{
  public static class a
    implements SafeParcelable
  {
    public static final hn CREATOR = new hn();
    final int BR;
    public final Account Cj;
    
    public a()
    {
      this(null);
    }
    
    a(int paramInt, Account paramAccount)
    {
      this.BR = paramInt;
      this.Cj = paramAccount;
    }
    
    public a(Account paramAccount)
    {
      this(1, paramAccount);
    }
    
    public int describeContents()
    {
      hn localhn = CREATOR;
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      hn localhn = CREATOR;
      hn.a(this, paramParcel, paramInt);
    }
  }
  
  public static class b
    implements Result, SafeParcelable
  {
    public static final ho CREATOR = new ho();
    final int BR;
    public Status Ck;
    public List<hs> Cl;
    
    public b()
    {
      this.BR = 1;
    }
    
    b(int paramInt, Status paramStatus, List<hs> paramList)
    {
      this.BR = paramInt;
      this.Ck = paramStatus;
      this.Cl = paramList;
    }
    
    public int describeContents()
    {
      ho localho = CREATOR;
      return 0;
    }
    
    public Status getStatus()
    {
      return this.Ck;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      ho localho = CREATOR;
      ho.a(this, paramParcel, paramInt);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/hm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */