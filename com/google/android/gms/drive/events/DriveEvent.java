package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract interface DriveEvent
  extends SafeParcelable
{
  public abstract int getType();
  
  public static abstract interface Listener<E extends DriveEvent>
    extends c
  {
    public abstract void onEvent(E paramE);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/events/DriveEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */