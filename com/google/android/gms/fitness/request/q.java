package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.k;
import com.google.android.gms.fitness.data.k.a;

public class q
  implements SafeParcelable
{
  public static final Parcelable.Creator<q> CREATOR = new r();
  private final int BR;
  private final k UA;
  private final PendingIntent mPendingIntent;
  
  q(int paramInt, IBinder paramIBinder, PendingIntent paramPendingIntent)
  {
    this.BR = paramInt;
    if (paramIBinder == null) {}
    for (paramIBinder = null;; paramIBinder = k.a.an(paramIBinder))
    {
      this.UA = paramIBinder;
      this.mPendingIntent = paramPendingIntent;
      return;
    }
  }
  
  public q(k paramk, PendingIntent paramPendingIntent)
  {
    this.BR = 2;
    this.UA = paramk;
    this.mPendingIntent = paramPendingIntent;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public PendingIntent jr()
  {
    return this.mPendingIntent;
  }
  
  IBinder jw()
  {
    if (this.UA == null) {
      return null;
    }
    return this.UA.asBinder();
  }
  
  public String toString()
  {
    return String.format("SensorUnregistrationRequest{%s}", new Object[] { this.UA });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    r.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */