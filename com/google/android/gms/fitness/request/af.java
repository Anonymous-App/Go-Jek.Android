package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Subscription;

public class af
  implements SafeParcelable
{
  public static final Parcelable.Creator<af> CREATOR = new ag();
  private final int BR;
  private final Subscription US;
  private final boolean UT;
  
  af(int paramInt, Subscription paramSubscription, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.US = paramSubscription;
    this.UT = paramBoolean;
  }
  
  private af(a parama)
  {
    this.BR = 1;
    this.US = a.a(parama);
    this.UT = a.b(parama);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public Subscription jD()
  {
    return this.US;
  }
  
  public boolean jE()
  {
    return this.UT;
  }
  
  public String toString()
  {
    return n.h(this).a("subscription", this.US).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ag.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private Subscription US;
    private boolean UT = false;
    
    public a b(Subscription paramSubscription)
    {
      this.US = paramSubscription;
      return this;
    }
    
    public af jF()
    {
      if (this.US != null) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Must call setSubscription()");
        return new af(this, null);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/af.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */