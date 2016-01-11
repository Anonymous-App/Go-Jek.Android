package com.gojek.app.persistence.dao;

import android.content.Context;
import android.util.Log;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.persistence.domain.BookingHistory;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingHistoryDao
  extends BaseDao<BookingHistory>
{
  public static final int HISTORY_COMPLETED = 2;
  public static final int HISTORY_IN_PROGRESS = 1;
  private static final String TAG = BookingHistoryDao.class.getSimpleName();
  
  public BookingHistoryDao(Context paramContext)
  {
    super(paramContext);
    Log.e(TAG, "BookingHistoryDao created from= " + paramContext);
  }
  
  private BookingStatus getConvertedObject(BookingHistory paramBookingHistory)
  {
    BookingStatus localBookingStatus = new BookingStatus();
    localBookingStatus.setId(paramBookingHistory.getId());
    localBookingStatus.setTimeField(paramBookingHistory.getTimeField());
    localBookingStatus.setServiceType(paramBookingHistory.getServiceType());
    localBookingStatus.setStatusBooking(paramBookingHistory.getStatusBooking());
    localBookingStatus.setCancelReasonId(paramBookingHistory.getCancelReasonId());
    localBookingStatus.setRate(paramBookingHistory.getRate());
    localBookingStatus.setTotalPrice(paramBookingHistory.getTotalPrice());
    localBookingStatus.setFeedback(paramBookingHistory.getFeedback());
    localBookingStatus.setDriverId(paramBookingHistory.getDriverId());
    localBookingStatus.setDriverName(paramBookingHistory.getDriverName());
    localBookingStatus.setDriverLatitude(paramBookingHistory.getDriverLatitude());
    localBookingStatus.setDriverLongitude(paramBookingHistory.getDriverLongitude());
    localBookingStatus.setDriverPhone(paramBookingHistory.getDriverPhone());
    localBookingStatus.setDriverETA(paramBookingHistory.getDriverETA());
    ArrayList localArrayList = new ArrayList();
    Addresses localAddresses = new Addresses();
    localAddresses.setMerchantId(paramBookingHistory.getAddrMerchantId());
    localAddresses.setOriginName(paramBookingHistory.getAddrOriginName());
    localAddresses.setDestinationName(paramBookingHistory.getAddrDestinationName());
    localAddresses.setDestinationAddress(paramBookingHistory.getAddrDestinationAddress());
    localAddresses.setOriginAddress(paramBookingHistory.getAddrOriginAddress());
    localAddresses.setOriginNote(paramBookingHistory.getAddrOriginNote());
    localAddresses.setDestinationNote(paramBookingHistory.getAddrDestinationNote());
    localAddresses.setOriginContactName(paramBookingHistory.getAddrOriginContactName());
    localAddresses.setOriginContactPhone(paramBookingHistory.getAddrOriginContactPhone());
    localAddresses.setDestinationContactName(paramBookingHistory.getAddrDestinationContactName());
    localAddresses.setDestinationContactPhone(paramBookingHistory.getAddrDestinationContactPhone());
    localAddresses.setItem(paramBookingHistory.getAddrItem());
    localAddresses.setLatLongOrigin(paramBookingHistory.getAddrLatLongOrigin());
    localAddresses.setLatLongDestination(paramBookingHistory.getAddrLatLongDestination());
    localAddresses.setRoutePolyline(paramBookingHistory.getAddrRoutePolyline());
    localArrayList.add(localAddresses);
    localBookingStatus.setAddresses(localArrayList);
    return localBookingStatus;
  }
  
  private BookingHistory getHistoryResult(int paramInt)
  {
    return (BookingHistory)getDB().where(BookingHistory.class).equalTo("id", paramInt).findFirst();
  }
  
  private RealmResults<BookingHistory> getHistoryResults(int paramInt)
  {
    return getDB().where(BookingHistory.class).equalTo("id", paramInt).findAll();
  }
  
  public void addFromBookingStatus(BookingStatus paramBookingStatus, String paramString)
  {
    Log.d(TAG, "Store new booking history...");
    try
    {
      startTransaction();
      BookingHistory localBookingHistory = (BookingHistory)getDB().createObject(BookingHistory.class);
      localBookingHistory.setId(paramBookingStatus.getId());
      localBookingHistory.setOrderNo("" + paramBookingStatus.getOrderNo());
      localBookingHistory.setTimeField("" + paramBookingStatus.getTimeField());
      localBookingHistory.setServiceType(paramBookingStatus.getServiceType());
      localBookingHistory.setStatusBooking(paramBookingStatus.getStatusBooking());
      localBookingHistory.setCancelReasonId(paramBookingStatus.getCancelReasonId());
      localBookingHistory.setRate(paramBookingStatus.getRate());
      localBookingHistory.setTotalPrice(paramBookingStatus.getTotalPrice());
      localBookingHistory.setFeedback("" + paramBookingStatus.getFeedback());
      localBookingHistory.setDriverId("" + paramBookingStatus.getDriverId());
      localBookingHistory.setDriverName("" + paramBookingStatus.getDriverName());
      localBookingHistory.setDriverLatitude(paramBookingStatus.getDriverLatitude());
      localBookingHistory.setDriverLongitude(paramBookingStatus.getDriverLongitude());
      localBookingHistory.setDriverPhone("" + paramBookingStatus.getDriverPhone());
      localBookingHistory.setDriverETA("" + paramBookingStatus.getDriverETA());
      if ((paramBookingStatus.getAddresses() != null) && (paramBookingStatus.getAddresses().size() > 0))
      {
        localBookingHistory.setAddrMerchantId(((Addresses)paramBookingStatus.getAddresses().get(0)).getMerchantId());
        localBookingHistory.setAddrOriginName("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getOriginName());
        localBookingHistory.setAddrDestinationName("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getDestinationName());
        localBookingHistory.setAddrDestinationAddress("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getDestinationAddress());
        localBookingHistory.setAddrOriginAddress("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getOriginAddress());
        localBookingHistory.setAddrOriginNote("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getOriginNote());
        localBookingHistory.setAddrDestinationNote("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getDestinationNote());
        localBookingHistory.setAddrOriginContactName("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getOriginContactName());
        localBookingHistory.setAddrOriginContactPhone("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getOriginContactPhone());
        localBookingHistory.setAddrDestinationContactName("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getDestinationContactName());
        localBookingHistory.setAddrDestinationContactPhone("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getDestinationContactPhone());
        localBookingHistory.setAddrItem("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getItem());
        localBookingHistory.setAddrLatLongOrigin("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getLatLongOrigin());
        localBookingHistory.setAddrLatLongDestination("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getLatLongDestination());
        localBookingHistory.setAddrRoutePolyline("" + ((Addresses)paramBookingStatus.getAddresses().get(0)).getRoutePolyline());
      }
      localBookingHistory.setBookingJson("" + paramString);
      Log.d(TAG, "addFromBookingStatus success");
      finishTransaction();
      return;
    }
    catch (RealmException paramBookingStatus)
    {
      Log.e(TAG, "addFromBookingStatus got error " + paramBookingStatus.getMessage());
      cancelTransaction();
    }
  }
  
  public void addFromBookingStatusList(List<BookingStatus> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      addFromBookingStatus((BookingStatus)paramList.next(), null);
    }
  }
  
  public void deleteAllHistory()
  {
    Log.d(TAG, "Deleting all booking history...");
    getDB().beginTransaction();
    RealmResults localRealmResults = getDB().where(BookingHistory.class).findAll();
    try
    {
      localRealmResults.clear();
      finishTransaction();
      return;
    }
    catch (RealmException localRealmException)
    {
      Log.e(TAG, "Delete all booking history failed.");
      cancelTransaction();
    }
  }
  
  public void deleteHistory(int paramInt)
  {
    Log.d(TAG, "Deleting booking history id= " + paramInt + "...");
    Object localObject = null;
    try
    {
      BookingHistory localBookingHistory = (BookingHistory)getDB().where(BookingHistory.class).equalTo("id", paramInt).findFirst();
      localObject = localBookingHistory;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(TAG, "cannot find booking history, delete canceled");
      }
      startTransaction();
      try
      {
        ((BookingHistory)localObject).removeFromRealm();
        finishTransaction();
        return;
      }
      catch (RealmException localRealmException)
      {
        Log.e(TAG, "Delete booking history failed.");
        cancelTransaction();
      }
    }
    if (localObject == null) {
      return;
    }
  }
  
  public List<BookingHistory> findAllHistory()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getDB().where(BookingHistory.class).findAll().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((BookingHistory)localIterator.next());
    }
    return localArrayList;
  }
  
  public List<BookingHistory> findCompletedBooking()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getDB().where(BookingHistory.class).equalTo("statusBooking", 4).or().equalTo("statusBooking", 5).findAll().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((BookingHistory)localIterator.next());
    }
    return localArrayList;
  }
  
  public List<BookingHistory> findInProgressBooking()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getDB().where(BookingHistory.class).equalTo("statusBooking", 2).or().equalTo("statusBooking", 6).or().equalTo("statusBooking", 7).or().equalTo("statusBooking", 8).findAll().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((BookingHistory)localIterator.next());
    }
    return localArrayList;
  }
  
  public List<BookingStatus> getBookingData()
  {
    Log.d(TAG, "try to load booking history...");
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = findAllHistory().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(getConvertedObject((BookingHistory)localIterator.next()));
    }
    Log.d(TAG, "got history list= " + localArrayList.toString());
    return localArrayList;
  }
  
  public List<BookingStatus> getBookingData(int paramInt)
  {
    Log.d(TAG, "try to load booking history...");
    ArrayList localArrayList = new ArrayList();
    switch (paramInt)
    {
    }
    for (;;)
    {
      Log.d(TAG, "got history list= " + localArrayList.toString());
      return localArrayList;
      Iterator localIterator = findInProgressBooking().iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(getConvertedObject((BookingHistory)localIterator.next()));
      }
      localIterator = findCompletedBooking().iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(getConvertedObject((BookingHistory)localIterator.next()));
      }
    }
  }
  
  public BookingStatus getHistoryByOrderNo(String paramString)
  {
    Log.d(TAG, "get booking history from orderNo= " + paramString + "...");
    return getConvertedObject((BookingHistory)getDB().where(BookingHistory.class).equalTo("orderNo", paramString).findFirst());
  }
  
  public void updateBookingRate(int paramInt1, int paramInt2, String paramString)
  {
    BookingHistory localBookingHistory = getHistoryResult(paramInt1);
    Log.i(TAG, "got existing RealmResults bookingHistory " + localBookingHistory.toString() + ", ready to update booking rate...");
    startTransaction();
    if (localBookingHistory != null)
    {
      localBookingHistory.setId(paramInt1);
      localBookingHistory.setRate(paramInt2);
      localBookingHistory.setFeedback(paramString);
    }
    try
    {
      getDB().copyToRealmOrUpdate(localBookingHistory);
      finishTransaction();
      Log.d(TAG, "updateBookingRate() success!");
      return;
    }
    catch (RealmException paramString)
    {
      Log.e(TAG, "updateBookingRate() got realm exception " + paramString.getMessage());
      cancelTransaction();
      return;
    }
    catch (Exception paramString)
    {
      Log.e(TAG, "updateBookingRate() got exception " + paramString.getMessage());
      cancelTransaction();
    }
  }
  
  public void updateBookingStatus(int paramInt1, int paramInt2)
  {
    BookingHistory localBookingHistory = getHistoryResult(paramInt1);
    Log.i(TAG, "got existing RealmResults bookingHistory " + localBookingHistory + ", ready to update status...");
    startTransaction();
    if (localBookingHistory != null)
    {
      localBookingHistory.setId(paramInt1);
      localBookingHistory.setStatusBooking(paramInt2);
    }
    try
    {
      getDB().copyToRealmOrUpdate(localBookingHistory);
      finishTransaction();
      Log.e(TAG, "Update booking updated to " + paramInt2);
      Log.d(TAG, "Update booking status success!");
      return;
    }
    catch (RealmException localRealmException)
    {
      Log.e(TAG, "updateBookingStatus() got realm exception " + localRealmException.getMessage());
      cancelTransaction();
      return;
    }
    catch (Exception localException)
    {
      Log.e(TAG, "updateBookingStatus() got exception " + localException.getMessage());
      cancelTransaction();
    }
  }
  
  public void updateBookingToCancel(int paramInt)
  {
    BookingHistory localBookingHistory = getHistoryResult(paramInt);
    Log.i(TAG, "got existing RealmResults bookingHistory " + localBookingHistory.toString() + ", ready to update to cancel...");
    startTransaction();
    if (localBookingHistory != null)
    {
      localBookingHistory.setId(paramInt);
      localBookingHistory.setStatusBooking(0);
      localBookingHistory.setCancelReasonId(4);
    }
    try
    {
      getDB().copyToRealmOrUpdate(localBookingHistory);
      finishTransaction();
      Log.d(TAG, "updateBookingToCancel() success!");
      return;
    }
    catch (RealmException localRealmException)
    {
      Log.e(TAG, "updateBookingToCancel() got realm exception " + localRealmException.getMessage());
      cancelTransaction();
      return;
    }
    catch (Exception localException)
    {
      Log.e(TAG, "updateBookingToCancel() got exception " + localException.getMessage());
      cancelTransaction();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/persistence/dao/BookingHistoryDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */