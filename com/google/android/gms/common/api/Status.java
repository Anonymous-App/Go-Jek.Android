package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Status
  implements Result, SafeParcelable
{
  public static final StatusCreator CREATOR = new StatusCreator();
  public static final Status Jv = new Status(0);
  public static final Status Jw = new Status(14);
  public static final Status Jx = new Status(8);
  public static final Status Jy = new Status(15);
  public static final Status Jz = new Status(16);
  private final int BR;
  private final int HF;
  private final String JA;
  private final PendingIntent mPendingIntent;
  
  public Status(int paramInt)
  {
    this(paramInt, null);
  }
  
  Status(int paramInt1, int paramInt2, String paramString, PendingIntent paramPendingIntent)
  {
    this.BR = paramInt1;
    this.HF = paramInt2;
    this.JA = paramString;
    this.mPendingIntent = paramPendingIntent;
  }
  
  public Status(int paramInt, String paramString)
  {
    this(1, paramInt, paramString, null);
  }
  
  public Status(int paramInt, String paramString, PendingIntent paramPendingIntent)
  {
    this(1, paramInt, paramString, paramPendingIntent);
  }
  
  private String fX()
  {
    if (this.JA != null) {
      return this.JA;
    }
    return CommonStatusCodes.getStatusCodeString(this.HF);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Status)) {}
    do
    {
      return false;
      paramObject = (Status)paramObject;
    } while ((this.BR != ((Status)paramObject).BR) || (this.HF != ((Status)paramObject).HF) || (!n.equal(this.JA, ((Status)paramObject).JA)) || (!n.equal(this.mPendingIntent, ((Status)paramObject).mPendingIntent)));
    return true;
  }
  
  PendingIntent getPendingIntent()
  {
    return this.mPendingIntent;
  }
  
  public PendingIntent getResolution()
  {
    return this.mPendingIntent;
  }
  
  public Status getStatus()
  {
    return this;
  }
  
  public int getStatusCode()
  {
    return this.HF;
  }
  
  public String getStatusMessage()
  {
    return this.JA;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  @Deprecated
  public ConnectionResult gt()
  {
    return new ConnectionResult(this.HF, this.mPendingIntent);
  }
  
  public boolean hasResolution()
  {
    return this.mPendingIntent != null;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.BR), Integer.valueOf(this.HF), this.JA, this.mPendingIntent });
  }
  
  public boolean isCanceled()
  {
    return this.HF == 16;
  }
  
  public boolean isInterrupted()
  {
    return this.HF == 14;
  }
  
  public boolean isSuccess()
  {
    return this.HF <= 0;
  }
  
  public void startResolutionForResult(Activity paramActivity, int paramInt)
    throws IntentSender.SendIntentException
  {
    if (!hasResolution()) {
      return;
    }
    paramActivity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), paramInt, null, 0, 0, 0);
  }
  
  public String toString()
  {
    return n.h(this).a("statusCode", fX()).a("resolution", this.mPendingIntent).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    StatusCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/api/Status.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */