package com.gojek.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CustomerPromo
  implements Parcelable
{
  public static final Parcelable.Creator<CustomerPromo> CREATOR = new CustomerPromo.1();
  private String content;
  private String imageUrl;
  private String subtitle;
  private String title;
  
  public CustomerPromo() {}
  
  public CustomerPromo(Parcel paramParcel)
  {
    this.imageUrl = paramParcel.readString();
    this.title = paramParcel.readString();
    this.content = paramParcel.readString();
    this.subtitle = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public String getImageUrl()
  {
    return this.imageUrl;
  }
  
  public String getSubtitle()
  {
    return this.subtitle;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setContent(String paramString)
  {
    this.content = paramString;
  }
  
  public void setImageUrl(String paramString)
  {
    this.imageUrl = paramString;
  }
  
  public void setSubtitle(String paramString)
  {
    this.subtitle = paramString;
  }
  
  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("CustomerPromoVO{");
    localStringBuffer.append("imageUrl='").append(this.imageUrl).append('\'');
    localStringBuffer.append(", title='").append(this.title).append('\'');
    localStringBuffer.append(", content='").append(this.content).append('\'');
    localStringBuffer.append(", subtitle='").append(this.subtitle).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.imageUrl);
    paramParcel.writeString(this.title);
    paramParcel.writeString(this.content);
    paramParcel.writeString(this.subtitle);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/CustomerPromo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */