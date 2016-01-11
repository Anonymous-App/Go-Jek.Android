package com.gojek.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PushModel
  implements Parcelable
{
  public static final Parcelable.Creator<PushModel> CREATOR = new PushModel.1();
  public int eta;
  public int id;
  public String latLong;
  public String message;
  public String name;
  public String no;
  public String phone;
  public int status;
  
  public PushModel() {}
  
  protected PushModel(Parcel paramParcel)
  {
    this.message = paramParcel.readString();
    this.eta = paramParcel.readInt();
    this.phone = paramParcel.readString();
    this.status = paramParcel.readInt();
    this.no = paramParcel.readString();
    this.name = paramParcel.readString();
    this.latLong = paramParcel.readString();
    this.id = paramParcel.readInt();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("PushModel{");
    localStringBuffer.append("eta=").append(this.eta);
    localStringBuffer.append(", id=").append(this.id);
    localStringBuffer.append(", latLong='").append(this.latLong).append('\'');
    localStringBuffer.append(", message='").append(this.message).append('\'');
    localStringBuffer.append(", name='").append(this.name).append('\'');
    localStringBuffer.append(", no='").append(this.no).append('\'');
    localStringBuffer.append(", phone='").append(this.phone).append('\'');
    localStringBuffer.append(", status=").append(this.status);
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.message);
    paramParcel.writeInt(this.eta);
    paramParcel.writeString(this.phone);
    paramParcel.writeInt(this.status);
    paramParcel.writeString(this.no);
    paramParcel.writeString(this.name);
    paramParcel.writeString(this.latLong);
    paramParcel.writeInt(this.id);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/PushModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */