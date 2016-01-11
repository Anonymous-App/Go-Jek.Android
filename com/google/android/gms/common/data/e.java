package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class e<T extends SafeParcelable>
  extends DataBuffer<T>
{
  private static final String[] JZ = { "data" };
  private final Parcelable.Creator<T> Ka;
  
  public e(DataHolder paramDataHolder, Parcelable.Creator<T> paramCreator)
  {
    super(paramDataHolder);
    this.Ka = paramCreator;
  }
  
  public T aq(int paramInt)
  {
    Object localObject = this.II.f("data", paramInt, 0);
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall((byte[])localObject, 0, localObject.length);
    localParcel.setDataPosition(0);
    localObject = (SafeParcelable)this.Ka.createFromParcel(localParcel);
    localParcel.recycle();
    return (T)localObject;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/data/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */