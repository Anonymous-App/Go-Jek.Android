package com.gojek.app.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.gojek.app.util.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingStatus
  implements Parcelable, Cloneable, Comparable<BookingStatus>
{
  public static final Parcelable.Creator<BookingStatus> CREATOR = new BookingStatus.1();
  public int activitySource = 2;
  public List<Addresses> addresses = new ArrayList();
  public String arrivalTime;
  public String buyer;
  public String cancelDescription;
  public int cancelReasonId;
  public String closingTime;
  public boolean corporateFlag;
  public int corporateId;
  public String corporateName;
  public String corporatePin;
  public Customer customer = new Customer();
  public String deviceToken;
  public String dispatchTime;
  public String driverETA;
  public String driverId;
  public double driverLatitude;
  public double driverLongitude;
  public String driverName;
  public String driverPhone;
  public String driverType;
  public String feedback;
  public int flagBooking;
  public String gcmKey;
  public int id;
  public int issueTicketId;
  public boolean needAttention;
  public String needAttentionReason;
  public String needAttentionTime;
  public boolean newCustomer;
  public String optionReturn;
  public String orderNo;
  public String payment;
  public int paymentType;
  public int rate;
  public int serviceType;
  public int status;
  public int statusBooking;
  public String timeField;
  public int timeType;
  public long totalCustomerPrice;
  public double totalDistance;
  public String totalDriverCut;
  public long totalPrice;
  public int uniqueId;
  public long voucherAmountCut;
  
  public BookingStatus() {}
  
  public BookingStatus(int paramInt1, int paramInt2, int paramInt3, String paramString, long paramLong)
  {
    this.id = paramInt1;
    this.serviceType = paramInt2;
    this.statusBooking = paramInt3;
    this.addresses.add(new Addresses(paramString));
    this.timeField = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date(paramLong));
    paramInt1 = this.timeField.length();
    this.timeField = new StringBuilder(this.timeField).insert(paramInt1 - 2, ':').toString();
  }
  
  public BookingStatus(Parcel paramParcel)
  {
    this.id = paramParcel.readInt();
    this.uniqueId = paramParcel.readInt();
    this.orderNo = paramParcel.readString();
    this.serviceType = paramParcel.readInt();
    this.timeType = paramParcel.readInt();
    this.optionReturn = paramParcel.readString();
    this.payment = paramParcel.readString();
    this.customer = ((Customer)paramParcel.readValue(Customer.class.getClassLoader()));
    if (paramParcel.readByte() == 1)
    {
      this.addresses = new ArrayList();
      paramParcel.readList(this.addresses, Addresses.class.getClassLoader());
      this.flagBooking = paramParcel.readInt();
      this.statusBooking = paramParcel.readInt();
      this.driverId = paramParcel.readString();
      this.driverName = paramParcel.readString();
      this.driverLatitude = paramParcel.readDouble();
      this.driverLongitude = paramParcel.readDouble();
      this.driverPhone = paramParcel.readString();
      this.driverETA = paramParcel.readString();
      this.status = paramParcel.readInt();
      this.voucherAmountCut = paramParcel.readLong();
      this.totalCustomerPrice = paramParcel.readLong();
      this.totalPrice = paramParcel.readLong();
      this.totalDistance = paramParcel.readDouble();
      this.totalDriverCut = paramParcel.readString();
      this.corporateId = paramParcel.readInt();
      this.corporateName = paramParcel.readString();
      if (paramParcel.readByte() == 0) {
        break label460;
      }
      bool1 = true;
      label274:
      this.corporateFlag = bool1;
      this.buyer = paramParcel.readString();
      if (paramParcel.readByte() == 0) {
        break label465;
      }
      bool1 = true;
      label296:
      this.needAttention = bool1;
      this.needAttentionReason = paramParcel.readString();
      this.issueTicketId = paramParcel.readInt();
      this.cancelReasonId = paramParcel.readInt();
      this.cancelDescription = paramParcel.readString();
      if (paramParcel.readByte() == 0) {
        break label470;
      }
    }
    label460:
    label465:
    label470:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.newCustomer = bool1;
      this.rate = paramParcel.readInt();
      this.feedback = paramParcel.readString();
      this.corporatePin = paramParcel.readString();
      this.deviceToken = paramParcel.readString();
      this.gcmKey = paramParcel.readString();
      this.activitySource = paramParcel.readInt();
      this.driverType = paramParcel.readString();
      this.dispatchTime = paramParcel.readString();
      this.arrivalTime = paramParcel.readString();
      this.closingTime = paramParcel.readString();
      this.timeField = paramParcel.readString();
      this.needAttentionTime = paramParcel.readString();
      this.paymentType = paramParcel.readInt();
      return;
      this.addresses = null;
      break;
      bool1 = false;
      break label274;
      bool1 = false;
      break label296;
    }
  }
  
  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
  
  public int compareTo(BookingStatus paramBookingStatus)
  {
    Object localObject = new Date();
    Date localDate2 = new Date();
    try
    {
      Date localDate1 = Util.formatDateFromAPI(this.timeField);
      localObject = localDate1;
      paramBookingStatus = Util.formatDateFromAPI(paramBookingStatus.timeField);
      localObject = localDate1;
    }
    catch (Exception paramBookingStatus)
    {
      do
      {
        for (;;)
        {
          int i;
          paramBookingStatus.printStackTrace();
          paramBookingStatus = localDate2;
        }
      } while (!((Date)localObject).after(paramBookingStatus));
    }
    i = 0;
    if (((Date)localObject).before(paramBookingStatus))
    {
      i = 1;
      return i;
    }
    return -1;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getActivitySource()
  {
    return this.activitySource;
  }
  
  public List<Addresses> getAddresses()
  {
    return this.addresses;
  }
  
  public String getArrivalTime()
  {
    return this.arrivalTime;
  }
  
  public String getBuyer()
  {
    return this.buyer;
  }
  
  public String getCancelDescription()
  {
    return this.cancelDescription;
  }
  
  public int getCancelReasonId()
  {
    return this.cancelReasonId;
  }
  
  public String getClosingTime()
  {
    return this.closingTime;
  }
  
  public int getCorporateId()
  {
    return this.corporateId;
  }
  
  public String getCorporateName()
  {
    return this.corporateName;
  }
  
  public String getCorporatePin()
  {
    return this.corporatePin;
  }
  
  public Customer getCustomer()
  {
    return this.customer;
  }
  
  public String getDeviceToken()
  {
    return this.deviceToken;
  }
  
  public String getDispatchTime()
  {
    return this.dispatchTime;
  }
  
  public String getDriverETA()
  {
    return this.driverETA;
  }
  
  public String getDriverId()
  {
    return this.driverId;
  }
  
  public double getDriverLatitude()
  {
    return this.driverLatitude;
  }
  
  public double getDriverLongitude()
  {
    return this.driverLongitude;
  }
  
  public String getDriverName()
  {
    return this.driverName;
  }
  
  public String getDriverPhone()
  {
    return this.driverPhone;
  }
  
  public String getDriverType()
  {
    return this.driverType;
  }
  
  public String getFeedback()
  {
    return this.feedback;
  }
  
  public int getFlagBooking()
  {
    return this.flagBooking;
  }
  
  public String getGcmKey()
  {
    return this.gcmKey;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getIssueTicketId()
  {
    return this.issueTicketId;
  }
  
  public String getNeedAttentionReason()
  {
    return this.needAttentionReason;
  }
  
  public String getNeedAttentionTime()
  {
    return this.needAttentionTime;
  }
  
  public String getOptionReturn()
  {
    return this.optionReturn;
  }
  
  public String getOrderNo()
  {
    return this.orderNo;
  }
  
  public String getPayment()
  {
    return this.payment;
  }
  
  public int getPaymentType()
  {
    return this.paymentType;
  }
  
  public int getRate()
  {
    return this.rate;
  }
  
  public int getServiceType()
  {
    return this.serviceType;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public int getStatusBooking()
  {
    return this.statusBooking;
  }
  
  public String getTimeField()
  {
    return this.timeField;
  }
  
  public int getTimeType()
  {
    return this.timeType;
  }
  
  public long getTotalCustomerPrice()
  {
    return this.totalCustomerPrice;
  }
  
  public double getTotalDistance()
  {
    return this.totalDistance;
  }
  
  public String getTotalDriverCut()
  {
    return this.totalDriverCut;
  }
  
  public long getTotalPrice()
  {
    return this.totalPrice;
  }
  
  public int getUniqueId()
  {
    return this.uniqueId;
  }
  
  public long getVoucherAmountCut()
  {
    return this.voucherAmountCut;
  }
  
  public boolean isCorporateFlag()
  {
    return this.corporateFlag;
  }
  
  public boolean isNeedAttention()
  {
    return this.needAttention;
  }
  
  public boolean isNewCustomer()
  {
    return this.newCustomer;
  }
  
  public void setActivitySource(int paramInt)
  {
    this.activitySource = paramInt;
  }
  
  public void setAddresses(List<Addresses> paramList)
  {
    this.addresses = paramList;
  }
  
  public void setArrivalTime(String paramString)
  {
    this.arrivalTime = paramString;
  }
  
  public void setBuyer(String paramString)
  {
    this.buyer = paramString;
  }
  
  public void setCancelDescription(String paramString)
  {
    this.cancelDescription = paramString;
  }
  
  public void setCancelReasonId(int paramInt)
  {
    this.cancelReasonId = paramInt;
  }
  
  public void setClosingTime(String paramString)
  {
    this.closingTime = paramString;
  }
  
  public void setCorporateFlag(boolean paramBoolean)
  {
    this.corporateFlag = paramBoolean;
  }
  
  public void setCorporateId(int paramInt)
  {
    this.corporateId = paramInt;
  }
  
  public void setCorporateName(String paramString)
  {
    this.corporateName = paramString;
  }
  
  public void setCorporatePin(String paramString)
  {
    this.corporatePin = paramString;
  }
  
  public void setCustomer(Customer paramCustomer)
  {
    this.customer = paramCustomer;
  }
  
  public void setDeviceToken(String paramString)
  {
    this.deviceToken = paramString;
  }
  
  public void setDispatchTime(String paramString)
  {
    this.dispatchTime = paramString;
  }
  
  public void setDriverETA(String paramString)
  {
    this.driverETA = paramString;
  }
  
  public void setDriverId(String paramString)
  {
    this.driverId = paramString;
  }
  
  public void setDriverLatitude(double paramDouble)
  {
    this.driverLatitude = paramDouble;
  }
  
  public void setDriverLongitude(double paramDouble)
  {
    this.driverLongitude = paramDouble;
  }
  
  public void setDriverName(String paramString)
  {
    this.driverName = paramString;
  }
  
  public void setDriverPhone(String paramString)
  {
    this.driverPhone = paramString;
  }
  
  public void setDriverType(String paramString)
  {
    this.driverType = paramString;
  }
  
  public void setFeedback(String paramString)
  {
    this.feedback = paramString;
  }
  
  public void setFlagBooking(int paramInt)
  {
    this.flagBooking = paramInt;
  }
  
  public void setGcmKey(String paramString)
  {
    this.gcmKey = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setIssueTicketId(int paramInt)
  {
    this.issueTicketId = paramInt;
  }
  
  public void setNeedAttention(boolean paramBoolean)
  {
    this.needAttention = paramBoolean;
  }
  
  public void setNeedAttentionReason(String paramString)
  {
    this.needAttentionReason = paramString;
  }
  
  public void setNeedAttentionTime(String paramString)
  {
    this.needAttentionTime = paramString;
  }
  
  public void setNewCustomer(boolean paramBoolean)
  {
    this.newCustomer = paramBoolean;
  }
  
  public void setOptionReturn(String paramString)
  {
    this.optionReturn = paramString;
  }
  
  public void setOrderNo(String paramString)
  {
    this.orderNo = paramString;
  }
  
  public void setPayment(String paramString)
  {
    this.payment = paramString;
  }
  
  public void setPaymentType(int paramInt)
  {
    this.paymentType = paramInt;
  }
  
  public void setRate(int paramInt)
  {
    this.rate = paramInt;
  }
  
  public void setServiceType(int paramInt)
  {
    this.serviceType = paramInt;
  }
  
  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }
  
  public void setStatusBooking(int paramInt)
  {
    this.statusBooking = paramInt;
  }
  
  public void setTimeField(String paramString)
  {
    this.timeField = paramString;
  }
  
  public void setTimeType(int paramInt)
  {
    this.timeType = paramInt;
  }
  
  public void setTotalCustomerPrice(long paramLong)
  {
    this.totalCustomerPrice = paramLong;
  }
  
  public void setTotalDistance(double paramDouble)
  {
    this.totalDistance = paramDouble;
  }
  
  public void setTotalDriverCut(String paramString)
  {
    this.totalDriverCut = paramString;
  }
  
  public void setTotalPrice(long paramLong)
  {
    this.totalPrice = paramLong;
  }
  
  public void setUniqueId(int paramInt)
  {
    this.uniqueId = paramInt;
  }
  
  public void setVoucherAmountCut(long paramLong)
  {
    this.voucherAmountCut = paramLong;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("BookingStatus{");
    localStringBuffer.append("id=").append(this.id);
    localStringBuffer.append(", uniqueId=").append(this.uniqueId);
    localStringBuffer.append(", orderNo='").append(this.orderNo).append('\'');
    localStringBuffer.append(", serviceType=").append(this.serviceType);
    localStringBuffer.append(", timeType=").append(this.timeType);
    localStringBuffer.append(", optionReturn='").append(this.optionReturn).append('\'');
    localStringBuffer.append(", payment='").append(this.payment).append('\'');
    localStringBuffer.append(", customer=").append(this.customer);
    localStringBuffer.append(", addresses=").append(this.addresses);
    localStringBuffer.append(", flagBooking=").append(this.flagBooking);
    localStringBuffer.append(", statusBooking=").append(this.statusBooking);
    localStringBuffer.append(", driverId='").append(this.driverId).append('\'');
    localStringBuffer.append(", driverName='").append(this.driverName).append('\'');
    localStringBuffer.append(", driverLatitude=").append(this.driverLatitude);
    localStringBuffer.append(", driverLongitude=").append(this.driverLongitude);
    localStringBuffer.append(", driverPhone='").append(this.driverPhone).append('\'');
    localStringBuffer.append(", driverETA='").append(this.driverETA).append('\'');
    localStringBuffer.append(", status=").append(this.status);
    localStringBuffer.append(", voucherAmountCut=").append(this.voucherAmountCut);
    localStringBuffer.append(", totalCustomerPrice=").append(this.totalCustomerPrice);
    localStringBuffer.append(", totalPrice=").append(this.totalPrice);
    localStringBuffer.append(", totalDistance=").append(this.totalDistance);
    localStringBuffer.append(", totalDriverCut='").append(this.totalDriverCut).append('\'');
    localStringBuffer.append(", corporateId=").append(this.corporateId);
    localStringBuffer.append(", corporateName='").append(this.corporateName).append('\'');
    localStringBuffer.append(", corporateFlag=").append(this.corporateFlag);
    localStringBuffer.append(", buyer='").append(this.buyer).append('\'');
    localStringBuffer.append(", needAttention=").append(this.needAttention);
    localStringBuffer.append(", needAttentionReason='").append(this.needAttentionReason).append('\'');
    localStringBuffer.append(", issueTicketId=").append(this.issueTicketId);
    localStringBuffer.append(", cancelReasonId=").append(this.cancelReasonId);
    localStringBuffer.append(", cancelDescription='").append(this.cancelDescription).append('\'');
    localStringBuffer.append(", newCustomer=").append(this.newCustomer);
    localStringBuffer.append(", rate=").append(this.rate);
    localStringBuffer.append(", feedback='").append(this.feedback).append('\'');
    localStringBuffer.append(", corporatePin='").append(this.corporatePin).append('\'');
    localStringBuffer.append(", deviceToken='").append(this.deviceToken).append('\'');
    localStringBuffer.append(", gcmKey='").append(this.gcmKey).append('\'');
    localStringBuffer.append(", activitySource=").append(this.activitySource);
    localStringBuffer.append(", driverType='").append(this.driverType).append('\'');
    localStringBuffer.append(", dispatchTime='").append(this.dispatchTime).append('\'');
    localStringBuffer.append(", arrivalTime='").append(this.arrivalTime).append('\'');
    localStringBuffer.append(", closingTime='").append(this.closingTime).append('\'');
    localStringBuffer.append(", timeField='").append(this.timeField).append('\'');
    localStringBuffer.append(", needAttentionTime='").append(this.needAttentionTime).append('\'');
    localStringBuffer.append(", paymentType=").append(this.paymentType);
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeInt(this.id);
    paramParcel.writeInt(this.uniqueId);
    paramParcel.writeString(this.orderNo);
    paramParcel.writeInt(this.serviceType);
    paramParcel.writeInt(this.timeType);
    paramParcel.writeString(this.optionReturn);
    paramParcel.writeString(this.payment);
    paramParcel.writeValue(this.customer);
    if (this.addresses == null)
    {
      paramParcel.writeByte((byte)0);
      paramParcel.writeInt(this.flagBooking);
      paramParcel.writeInt(this.statusBooking);
      paramParcel.writeString(this.driverId);
      paramParcel.writeString(this.driverName);
      paramParcel.writeDouble(this.driverLatitude);
      paramParcel.writeDouble(this.driverLongitude);
      paramParcel.writeString(this.driverPhone);
      paramParcel.writeString(this.driverETA);
      paramParcel.writeInt(this.status);
      paramParcel.writeLong(this.voucherAmountCut);
      paramParcel.writeLong(this.totalCustomerPrice);
      paramParcel.writeLong(this.totalPrice);
      paramParcel.writeDouble(this.totalDistance);
      paramParcel.writeString(this.totalDriverCut);
      paramParcel.writeInt(this.corporateId);
      paramParcel.writeString(this.corporateName);
      if (!this.corporateFlag) {
        break label412;
      }
      paramInt = 1;
      label215:
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeString(this.buyer);
      if (!this.needAttention) {
        break label417;
      }
      paramInt = 1;
      label238:
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeString(this.needAttentionReason);
      paramParcel.writeInt(this.issueTicketId);
      paramParcel.writeInt(this.cancelReasonId);
      paramParcel.writeString(this.cancelDescription);
      if (!this.newCustomer) {
        break label422;
      }
    }
    label412:
    label417:
    label422:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeInt(this.rate);
      paramParcel.writeString(this.feedback);
      paramParcel.writeString(this.corporatePin);
      paramParcel.writeString(this.deviceToken);
      paramParcel.writeString(this.gcmKey);
      paramParcel.writeInt(this.activitySource);
      paramParcel.writeString(this.driverType);
      paramParcel.writeString(this.dispatchTime);
      paramParcel.writeString(this.arrivalTime);
      paramParcel.writeString(this.closingTime);
      paramParcel.writeString(this.timeField);
      paramParcel.writeString(this.needAttentionTime);
      paramParcel.writeInt(this.paymentType);
      return;
      paramParcel.writeByte((byte)1);
      paramParcel.writeList(this.addresses);
      break;
      paramInt = 0;
      break label215;
      paramInt = 0;
      break label238;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/parcelable/BookingStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */