package com.gojek.app.gobusway.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.ParcelWrapper;

public class Halte$$Parcelable
  implements Parcelable, ParcelWrapper<Halte>
{
  public static final Creator..0 CREATOR = new Parcelable.Creator()
  {
    public Halte..Parcelable createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Halte..Parcelable(paramAnonymousParcel);
    }
    
    public Halte..Parcelable[] newArray(int paramAnonymousInt)
    {
      return new Halte..Parcelable[paramAnonymousInt];
    }
  };
  private Halte halte$$0;
  
  public Halte$$Parcelable(Parcel paramParcel)
  {
    if (paramParcel.readInt() == -1) {}
    for (paramParcel = null;; paramParcel = readcom_gojek_app_gobusway_model_Halte(paramParcel))
    {
      this.halte$$0 = paramParcel;
      return;
    }
  }
  
  public Halte$$Parcelable(Halte paramHalte)
  {
    this.halte$$0 = paramHalte;
  }
  
  private Halte readcom_gojek_app_gobusway_model_Halte(Parcel paramParcel)
  {
    return new Halte(paramParcel.readString(), paramParcel.readString(), paramParcel.readDouble(), paramParcel.readDouble(), paramParcel.readString());
  }
  
  private void writecom_gojek_app_gobusway_model_Halte(Halte paramHalte, Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(paramHalte.id);
    paramParcel.writeString(paramHalte.name);
    paramParcel.writeDouble(paramHalte.latitude);
    paramParcel.writeDouble(paramHalte.longitude);
    paramParcel.writeString(paramHalte.linkDetail);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Halte getParcel()
  {
    return this.halte$$0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.halte$$0 == null)
    {
      paramParcel.writeInt(-1);
      return;
    }
    paramParcel.writeInt(1);
    writecom_gojek_app_gobusway_model_Halte(this.halte$$0, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/Halte$$Parcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */