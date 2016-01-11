package com.gojek.gotix.helper;

import android.text.TextUtils;
import android.util.Log;
import com.gojek.gotix.Event;
import com.gojek.gotix.Order;
import com.gojek.gotix.network.model.Booking;
import com.gojek.gotix.network.model.DeliveryBooking;
import com.gojek.gotix.network.model.Driver;
import com.gojek.gotix.network.model.RegistrationGCM;
import com.gojek.gotix.network.model.TicketPurchased;
import com.gojek.gotix.network.model.Transaction;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import rx.Observable;

public class GotixData
{
  private static final String ACTIVE_ORDER = "activeOrder";
  private static final String BOOKING_DATA = "bookingData";
  private static final String CUSTOMER_DATA = "customerData";
  private static final String DELIVERY_DATA_BOOKING = "deliveryDataBooking";
  private static final String DEVICE_REGISTRATION_ID_DATA = "deviceRegistrationIdData";
  private static final String DRIVER_DATA = "driverData";
  private static final String EVENT_DATA = "eventData";
  private static final String REGISTRATION_ID_DATA = "registrationIdData";
  private static final String REVIEW_DATA = "reviewData";
  private static final String TAG = GotixData.class.getSimpleName();
  private static final String TICKET_PURCHASED_DATA = "ticketPurchasedData";
  private static final String TRANSACTION_DATA = "transactionData";
  
  public static void clearActiveOrder(int paramInt)
  {
    DataHelper.clearData("activeOrder" + paramInt);
  }
  
  public static void createActiveOrder(Event paramEvent, Transaction paramTransaction, TicketPurchased paramTicketPurchased)
  {
    saveTransactionData(paramTransaction);
    paramTicketPurchased = Observable.from(paramTicketPurchased.getTickets());
    Observable localObservable2 = Observable.from(paramEvent.getSchedules());
    Observable localObservable1 = paramTicketPurchased.flatMap(GotixData..Lambda.1.lambdaFactory$(localObservable2));
    localObservable2 = localObservable1.flatMap(GotixData..Lambda.2.lambdaFactory$(localObservable2)).distinct();
    Observable.combineLatest(localObservable2.flatMap(GotixData..Lambda.3.lambdaFactory$(localObservable1, paramTicketPurchased)).toList().cache(), localObservable2.flatMap(GotixData..Lambda.4.lambdaFactory$(paramEvent)).toList().cache(), GotixData..Lambda.5.lambdaFactory$(paramEvent, paramTransaction)).onErrorResumeNext(GotixData..Lambda.6.lambdaFactory$()).subscribe(GotixData..Lambda.7.lambdaFactory$());
  }
  
  public static Order getActiveOrder(int paramInt)
  {
    Object localObject = DataHelper.getString("activeOrder" + paramInt);
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (localObject = localGson.fromJson((String)localObject, Order.class);; localObject = GsonInstrumentation.fromJson((Gson)localGson, (String)localObject, Order.class)) {
      return (Order)localObject;
    }
  }
  
  public static Booking getBooking(int paramInt)
  {
    Log.i(TAG, "getBooking: bookingData" + paramInt);
    Object localObject = DataHelper.getString("bookingData" + paramInt);
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (localObject = localGson.fromJson((String)localObject, Booking.class);; localObject = GsonInstrumentation.fromJson((Gson)localGson, (String)localObject, Booking.class)) {
      return (Booking)localObject;
    }
  }
  
  public static DeliveryBooking getDeliveryData(int paramInt)
  {
    Object localObject = DataHelper.getString("deliveryDataBooking" + paramInt);
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (localObject = localGson.fromJson((String)localObject, DeliveryBooking.class);; localObject = GsonInstrumentation.fromJson((Gson)localGson, (String)localObject, DeliveryBooking.class)) {
      return (DeliveryBooking)localObject;
    }
  }
  
  public static Driver getDriver(int paramInt)
  {
    Object localObject = DataHelper.getString("driverData" + paramInt);
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (localObject = localGson.fromJson((String)localObject, Driver.class);; localObject = GsonInstrumentation.fromJson((Gson)localGson, (String)localObject, Driver.class)) {
      return (Driver)localObject;
    }
  }
  
  public static Event getEventData(int paramInt)
  {
    Object localObject = DataHelper.getString("eventData" + paramInt);
    Log.i(TAG, "getEventData: " + (String)localObject);
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (localObject = localGson.fromJson((String)localObject, Event.class);; localObject = GsonInstrumentation.fromJson((Gson)localGson, (String)localObject, Event.class)) {
      return (Event)localObject;
    }
  }
  
  public static RegistrationGCM getRegistrationGCM()
  {
    Object localObject = DataHelper.getString("deviceRegistrationIdData");
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (localObject = localGson.fromJson((String)localObject, RegistrationGCM.class);; localObject = GsonInstrumentation.fromJson((Gson)localGson, (String)localObject, RegistrationGCM.class)) {
      return (RegistrationGCM)localObject;
    }
  }
  
  public static Transaction getTransactionData(int paramInt)
  {
    Object localObject = DataHelper.getString("transactionData" + paramInt);
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (localObject = localGson.fromJson((String)localObject, Transaction.class);; localObject = GsonInstrumentation.fromJson((Gson)localGson, (String)localObject, Transaction.class)) {
      return (Transaction)localObject;
    }
  }
  
  private static Gson gsonBuilder()
  {
    return new Gson();
  }
  
  public static boolean hasReview(int paramInt)
  {
    return DataHelper.getBoolean("reviewData" + paramInt);
  }
  
  public static boolean isGCMRegistrationExist()
  {
    RegistrationGCM localRegistrationGCM = getRegistrationGCM();
    return (localRegistrationGCM != null) && (!TextUtils.isEmpty(localRegistrationGCM.getDeviceToken()));
  }
  
  public static void saveActiveOrder(Order paramOrder)
  {
    Object localObject = gsonBuilder();
    if (!(localObject instanceof Gson)) {}
    for (localObject = ((Gson)localObject).toJson(paramOrder);; localObject = GsonInstrumentation.toJson((Gson)localObject, paramOrder))
    {
      DataHelper.saveData("activeOrder" + paramOrder.getOrder_id(), (String)localObject);
      return;
    }
  }
  
  public static void saveBooking(int paramInt, Booking paramBooking)
  {
    paramBooking.setOrder_id(Integer.valueOf(paramInt));
    Log.i(TAG, "saveBooking: bookingData" + paramBooking.getOrder_id());
    String str = "bookingData" + paramBooking.getOrder_id();
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (paramBooking = localGson.toJson(paramBooking);; paramBooking = GsonInstrumentation.toJson((Gson)localGson, paramBooking))
    {
      DataHelper.saveData(str, paramBooking);
      return;
    }
  }
  
  public static void saveDeliveryData(DeliveryBooking paramDeliveryBooking, int paramInt)
  {
    String str = "deliveryDataBooking" + paramInt;
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (paramDeliveryBooking = localGson.toJson(paramDeliveryBooking);; paramDeliveryBooking = GsonInstrumentation.toJson((Gson)localGson, paramDeliveryBooking))
    {
      DataHelper.saveData(str, paramDeliveryBooking);
      return;
    }
  }
  
  public static void saveDriver(Driver paramDriver, int paramInt)
  {
    String str = "driverData" + paramInt;
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (paramDriver = localGson.toJson(paramDriver);; paramDriver = GsonInstrumentation.toJson((Gson)localGson, paramDriver))
    {
      DataHelper.saveData(str, paramDriver);
      return;
    }
  }
  
  public static void saveEventData(Event paramEvent)
  {
    Object localObject = gsonBuilder();
    Gson localGson;
    if (!(localObject instanceof Gson))
    {
      localObject = ((Gson)localObject).toJson(paramEvent);
      Log.i(TAG, "saveEventData: " + (String)localObject);
      localObject = "eventData" + paramEvent.getEvent_id();
      localGson = gsonBuilder();
      if ((localGson instanceof Gson)) {
        break label102;
      }
    }
    label102:
    for (paramEvent = localGson.toJson(paramEvent);; paramEvent = GsonInstrumentation.toJson((Gson)localGson, paramEvent))
    {
      DataHelper.saveData((String)localObject, paramEvent);
      return;
      localObject = GsonInstrumentation.toJson((Gson)localObject, paramEvent);
      break;
    }
  }
  
  public static void saveRegistrationGCM(RegistrationGCM paramRegistrationGCM)
  {
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (paramRegistrationGCM = localGson.toJson(paramRegistrationGCM);; paramRegistrationGCM = GsonInstrumentation.toJson((Gson)localGson, paramRegistrationGCM))
    {
      DataHelper.saveData("deviceRegistrationIdData", paramRegistrationGCM);
      return;
    }
  }
  
  public static void saveReview(int paramInt)
  {
    DataHelper.saveData("reviewData" + paramInt, true);
  }
  
  public static void saveTransactionData(Transaction paramTransaction)
  {
    String str = "transactionData" + paramTransaction.getEvent_id();
    Gson localGson = gsonBuilder();
    if (!(localGson instanceof Gson)) {}
    for (paramTransaction = localGson.toJson(paramTransaction);; paramTransaction = GsonInstrumentation.toJson((Gson)localGson, paramTransaction))
    {
      DataHelper.saveData(str, paramTransaction);
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/helper/GotixData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */