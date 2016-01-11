package com.gojek.app.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.gojek.app.model.Response;

public class Customer
  extends Response
  implements Parcelable
{
  public static final Parcelable.Creator<Customer> CREATOR = new Customer.1();
  public String corporateId;
  public String corporateName;
  public long creditBalance;
  public String customerId;
  public String email;
  public String hisBook;
  public String history;
  public int id;
  public String name;
  public String password;
  public String phone;
  public String simpanBooking;
  public String verificationCode;
  public boolean verified = false;
  
  public Customer() {}
  
  protected Customer(Parcel paramParcel)
  {
    this.customerId = paramParcel.readString();
    this.name = paramParcel.readString();
    this.phone = paramParcel.readString();
    this.email = paramParcel.readString();
    this.corporateId = paramParcel.readString();
    this.corporateName = paramParcel.readString();
    this.creditBalance = paramParcel.readLong();
    this.id = paramParcel.readInt();
    if (paramParcel.readByte() != 0) {
      bool = true;
    }
    this.verified = bool;
    this.verificationCode = paramParcel.readString();
    this.password = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getCorporateId()
  {
    return this.corporateId;
  }
  
  public String getCorporateName()
  {
    return this.corporateName;
  }
  
  public long getCreditBalance()
  {
    return this.creditBalance;
  }
  
  public String getCustomerId()
  {
    return this.customerId;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getHistory()
  {
    return this.history;
  }
  
  public String getHistoryBook()
  {
    return this.hisBook;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public String getSimpanBooking()
  {
    return this.simpanBooking;
  }
  
  public String getVerificationCode()
  {
    return this.verificationCode;
  }
  
  public boolean isVerified()
  {
    return this.verified;
  }
  
  public void setCorporateId(String paramString)
  {
    this.corporateId = paramString;
  }
  
  public void setCorporateName(String paramString)
  {
    this.corporateName = paramString;
  }
  
  public void setCreditBalance(long paramLong)
  {
    this.creditBalance = paramLong;
  }
  
  public void setCustomerId(String paramString)
  {
    this.customerId = paramString;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setHistory(String paramString)
  {
    this.history = paramString;
  }
  
  public void setHistoryBokk(String paramString)
  {
    this.hisBook = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.password = paramString;
  }
  
  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }
  
  public void setSimpanBooking(String paramString)
  {
    this.simpanBooking = paramString;
  }
  
  public void setVerificationCode(String paramString)
  {
    this.verificationCode = paramString;
  }
  
  public void setVerified(boolean paramBoolean)
  {
    this.verified = paramBoolean;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("Customer{");
    localStringBuffer.append("id=").append(this.id);
    localStringBuffer.append(", customerId='").append(this.customerId).append('\'');
    localStringBuffer.append(", name='").append(this.name).append('\'');
    localStringBuffer.append(", phone='").append(this.phone).append('\'');
    localStringBuffer.append(", email='").append(this.email).append('\'');
    localStringBuffer.append(", corporateId='").append(this.corporateId).append('\'');
    localStringBuffer.append(", corporateName='").append(this.corporateName).append('\'');
    localStringBuffer.append(", creditBalance=").append(this.creditBalance);
    localStringBuffer.append(", history='").append(this.history).append('\'');
    localStringBuffer.append(", verified=").append(this.verified);
    localStringBuffer.append(", verificationCode='").append(this.verificationCode).append('\'');
    localStringBuffer.append(", password='").append(this.password).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.customerId);
    paramParcel.writeString(this.name);
    paramParcel.writeString(this.phone);
    paramParcel.writeString(this.email);
    paramParcel.writeString(this.corporateId);
    paramParcel.writeString(this.corporateName);
    paramParcel.writeLong(this.creditBalance);
    paramParcel.writeInt(this.id);
    if (this.verified) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeString(this.verificationCode);
      paramParcel.writeString(this.password);
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/parcelable/Customer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */