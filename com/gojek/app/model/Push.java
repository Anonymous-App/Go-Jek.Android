package com.gojek.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Push
  implements Parcelable
{
  public static final Parcelable.Creator<Push> CREATOR = new Push.1();
  public PushModel model;
  public long sentTime;
  public String type;
  
  public Push() {}
  
  protected Push(Parcel paramParcel)
  {
    this.sentTime = paramParcel.readLong();
    this.model = ((PushModel)paramParcel.readValue(PushModel.class.getClassLoader()));
    this.type = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("Push{");
    localStringBuffer.append("sentTime=").append(this.sentTime);
    localStringBuffer.append(", model=").append(this.model);
    localStringBuffer.append(", type='").append(this.type).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this.sentTime);
    paramParcel.writeValue(this.model);
    paramParcel.writeString(this.type);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/Push.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */