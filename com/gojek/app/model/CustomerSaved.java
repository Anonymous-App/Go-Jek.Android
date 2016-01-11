package com.gojek.app.model;

import android.content.Context;
import android.util.Log;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Customer;
import com.gojek.app.util.GojekPreference;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerSaved
  extends Customer
{
  public static final String access_token = "accessToken";
  public static final String email_ = "email";
  public static final String expires_in = "expiresIn";
  public static final String historyBook = "history_book";
  public static final String history_ = "history";
  public static final String id_ = "id";
  public static final String name_ = "name";
  public static final String password_ = "password";
  public static final String phone_ = "phone";
  public static final String refresh_token = "refreshToken";
  public static final String simpan_booking = "booking";
  private static final String updated_phone_number = "updatedPhoneNumber";
  public static final String verification_code = "verificationCode";
  public static final String verified_ = "verified";
  private String accessToken;
  private GojekPreference mPref;
  private String refreshToken;
  
  public CustomerSaved(Context paramContext)
  {
    this.mPref = GojekPreference.getInstance(paramContext);
    this.customerId = this.mPref.getString("id", null);
    this.name = this.mPref.getString("name", null);
    this.phone = this.mPref.getString("phone", null);
    this.email = this.mPref.getString("email", null);
    this.history = this.mPref.getString("history", null);
    this.hisBook = this.mPref.getString("history_book", null);
    this.verified = this.mPref.getBoolean("verified", false);
    this.verificationCode = this.mPref.getString("verificationCode", null);
    this.password = this.mPref.getString("password", null);
    this.simpanBooking = this.mPref.setString("booking", null);
    this.accessToken = this.mPref.getString("accessToken", null);
    this.refreshToken = this.mPref.getString("refreshToken", null);
  }
  
  public void clearData()
  {
    this.mPref.setString("id", null);
    this.mPref.setString("name", null);
    this.mPref.setString("phone", null);
    this.mPref.setString("email", null);
    this.mPref.setString("history", null);
    this.mPref.setBoolean("verified", false);
    this.mPref.setString("verificationCode", null);
    this.mPref.setString("password", null);
    this.mPref.setString("accessToken", null);
    this.mPref.setString("refreshToken", null);
    this.mPref.setString("updatedPhoneNumber", null);
  }
  
  public String getAccessToken()
  {
    return this.mPref.getString("accessToken", "");
  }
  
  public String getCustomerId()
  {
    return this.mPref.getString("id", "");
  }
  
  public List<BookingStatus> getHistoryBooking()
  {
    if (this.hisBook != null)
    {
      Object localObject = new Gson();
      String str = this.hisBook;
      if (!(localObject instanceof Gson)) {}
      for (localObject = ((Gson)localObject).fromJson(str, BookingStatus[].class);; localObject = GsonInstrumentation.fromJson((Gson)localObject, str, BookingStatus[].class)) {
        return new ArrayList(Arrays.asList((BookingStatus[])localObject));
      }
    }
    return new ArrayList();
  }
  
  public List<PoiHistory> getHistoryList()
  {
    if (this.history != null)
    {
      Object localObject = new Gson();
      String str = this.history;
      Type localType = new CustomerSaved.1(this).getType();
      if (!(localObject instanceof Gson)) {}
      for (localObject = ((Gson)localObject).fromJson(str, localType);; localObject = GsonInstrumentation.fromJson((Gson)localObject, str, localType)) {
        return (List)localObject;
      }
    }
    return new ArrayList();
  }
  
  public String getRefreshToken()
  {
    return this.mPref.getString("refreshToken", "");
  }
  
  public BookingStatus getSaveBooking()
  {
    Object localObject1 = new BookingStatus();
    Log.wtf("TK", "simpan booking customersaved.class === " + this.mPref.getString("booking", this.simpanBooking));
    Object localObject2 = this.mPref.getString("booking", "");
    if (!((String)localObject2).equals(""))
    {
      localObject1 = new Gson();
      if ((localObject1 instanceof Gson)) {
        break label110;
      }
    }
    label110:
    for (localObject1 = ((Gson)localObject1).fromJson((String)localObject2, BookingStatus.class);; localObject1 = GsonInstrumentation.fromJson((Gson)localObject1, (String)localObject2, BookingStatus.class))
    {
      localObject1 = (BookingStatus)localObject1;
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new BookingStatus();
      }
      return (BookingStatus)localObject2;
    }
  }
  
  public String getUpdatePhoneNumber()
  {
    return this.mPref.getString("updatedPhoneNumber", null);
  }
  
  public void saveBooking(BookingStatus paramBookingStatus)
  {
    Gson localGson = new Gson();
    if (!(localGson instanceof Gson)) {}
    for (paramBookingStatus = localGson.toJson(paramBookingStatus);; paramBookingStatus = GsonInstrumentation.toJson((Gson)localGson, paramBookingStatus))
    {
      this.simpanBooking = paramBookingStatus;
      Log.d("Bima", "SIMMPAAANN BOOKIINGGG : " + this.simpanBooking.toString());
      this.mPref.setString("booking", this.simpanBooking);
      return;
    }
  }
  
  public void saveData()
  {
    this.mPref.setString("id", this.customerId);
    this.mPref.setString("name", this.name);
    this.mPref.setString("phone", this.phone);
    this.mPref.setString("email", this.email);
    this.mPref.setBoolean("verified", this.verified);
    this.mPref.setString("verificationCode", this.verificationCode);
    this.mPref.setString("password", this.password);
  }
  
  public void saveData(Customer paramCustomer)
  {
    this.customerId = paramCustomer.customerId;
    this.name = paramCustomer.name;
    this.phone = paramCustomer.phone;
    this.email = paramCustomer.email;
    this.verified = paramCustomer.verified;
    this.verificationCode = paramCustomer.verificationCode;
    this.password = paramCustomer.password;
    saveData();
  }
  
  public void saveExpiresIn(long paramLong)
  {
    this.mPref.setLong("expiresIn", paramLong);
  }
  
  public void saveHistoryBooking(List<BookingStatus> paramList)
  {
    Gson localGson = new Gson();
    if (!(localGson instanceof Gson)) {}
    for (paramList = localGson.toJson(paramList);; paramList = GsonInstrumentation.toJson((Gson)localGson, paramList))
    {
      this.hisBook = paramList;
      this.mPref.setString("history_book", this.hisBook);
      return;
    }
  }
  
  public void saveHistoryList(List<PoiHistory> paramList)
  {
    Gson localGson = new Gson();
    if (!(localGson instanceof Gson)) {}
    for (paramList = localGson.toJson(paramList);; paramList = GsonInstrumentation.toJson((Gson)localGson, paramList))
    {
      this.history = paramList;
      this.mPref.setString("history", this.history);
      return;
    }
  }
  
  public void saveToken(String paramString1, String paramString2)
  {
    this.mPref.setString("accessToken", paramString1);
    this.mPref.setString("refreshToken", paramString2);
  }
  
  public void updatePhoneNumber(String paramString)
  {
    this.mPref.setString("updatedPhoneNumber", paramString);
  }
  
  public void verifyPhoneNumber()
  {
    String str = this.mPref.getString("updatedPhoneNumber", null);
    this.mPref.setString("updatedPhoneNumber", null);
    this.mPref.setString("phone", str);
  }
  
  public void verifyPhoneNumber(String paramString)
  {
    this.mPref.setString("updatedPhoneNumber", null);
    this.mPref.setString("phone", paramString);
  }
  
  public void verifyUser(String paramString1, String paramString2)
  {
    this.verificationCode = paramString2;
    this.verified = true;
    this.customerId = paramString1;
    this.mPref.setString("id", paramString1);
    this.mPref.setBoolean("verified", true);
    this.mPref.setString("verificationCode", paramString2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/CustomerSaved.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */