package com.gojek.app.persistence.dao;

import android.content.Context;
import android.util.Log;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Customer;
import com.gojek.app.persistence.domain.BookingRate;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingRateDao
  extends BaseDao<BookingRate>
{
  private static final String TAG = BookingRateDao.class.getSimpleName();
  
  public BookingRateDao(Context paramContext)
  {
    super(paramContext);
  }
  
  public void addFromBookingStatus(BookingStatus paramBookingStatus, String paramString)
  {
    for (;;)
    {
      try
      {
        startTransaction();
        BookingRate localBookingRate = (BookingRate)getDB().createObject(BookingRate.class);
        localBookingRate.setId(paramBookingStatus.getId());
        localBookingRate.setOrderNo(paramBookingStatus.getOrderNo());
        localBookingRate.setServiceType(paramBookingStatus.getServiceType());
        localBookingRate.setStatusBooking(paramBookingStatus.getStatusBooking());
        if (paramBookingStatus.getDriverId() == null) {
          break label211;
        }
        str = paramBookingStatus.getDriverId();
        localBookingRate.setDriverId(str);
        if (paramBookingStatus.getDriverName() == null) {
          break label217;
        }
        str = paramBookingStatus.getDriverName();
        localBookingRate.setDriverName(str);
        localBookingRate.setRate(paramBookingStatus.getRate());
        if (paramBookingStatus.getFeedback() == null) {
          break label223;
        }
        str = paramBookingStatus.getFeedback();
        localBookingRate.setFeedback(str);
        if (paramBookingStatus.getCustomer().customerId != null)
        {
          localBookingRate.setCustomerId(Integer.parseInt(paramBookingStatus.getCustomer().customerId));
          if (paramString != null)
          {
            localBookingRate.setBookingJson(paramString);
            finishTransaction();
          }
        }
        else
        {
          localBookingRate.setCustomerId(0);
          continue;
        }
        localBookingRate.setBookingJson("");
      }
      catch (RealmException paramBookingStatus)
      {
        Log.e(TAG, "addFromBookingStatus got error " + paramBookingStatus.getMessage());
        cancelTransaction();
        return;
      }
      continue;
      label211:
      String str = "0";
      continue;
      label217:
      str = "";
      continue;
      label223:
      str = "";
    }
  }
  
  public BookingStatus findBookingDataById(int paramInt)
  {
    RealmResults localRealmResults = getDB().where(BookingRate.class).equalTo("id", paramInt).findAll();
    if ((localRealmResults != null) && (localRealmResults.size() > 0))
    {
      BookingStatus localBookingStatus = new BookingStatus();
      localBookingStatus.setId(((BookingRate)localRealmResults.get(0)).getId());
      localBookingStatus.setOrderNo(((BookingRate)localRealmResults.get(0)).getOrderNo());
      localBookingStatus.setServiceType(((BookingRate)localRealmResults.get(0)).getServiceType());
      localBookingStatus.setStatusBooking(((BookingRate)localRealmResults.get(0)).getStatusBooking());
      localBookingStatus.setDriverId(((BookingRate)localRealmResults.get(0)).getDriverId());
      localBookingStatus.setDriverName(((BookingRate)localRealmResults.get(0)).getDriverName());
      localBookingStatus.setRate(((BookingRate)localRealmResults.get(0)).getRate());
      localBookingStatus.setFeedback(((BookingRate)localRealmResults.get(0)).getFeedback());
      return localBookingStatus;
    }
    return null;
  }
  
  public BookingRate findByOrderNo(String paramString)
  {
    paramString = getDB().where(BookingRate.class).equalTo("orderNo", paramString).findAll();
    if (paramString.size() == 0) {
      return null;
    }
    return (BookingRate)paramString.get(0);
  }
  
  public List<BookingRate> findUnratedBooking()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getDB().where(BookingRate.class).equalTo("rate", 0).equalTo("statusBooking", 4).findAll().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((BookingRate)localIterator.next());
    }
    return localArrayList;
  }
  
  public List<BookingRate> findUnratedBooking(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getDB().where(BookingRate.class).equalTo("rate", 0).equalTo("statusBooking", 4).equalTo("customerId", paramInt).findAll().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((BookingRate)localIterator.next());
    }
    return localArrayList;
  }
  
  public void updateBookingRate(int paramInt1, int paramInt2, String paramString)
  {
    RealmResults localRealmResults = getDB().where(BookingRate.class).equalTo("id", paramInt1).findAll();
    if ((localRealmResults != null) && (localRealmResults.size() > 0))
    {
      getDB().beginTransaction();
      ((BookingRate)localRealmResults.get(0)).setRate(paramInt2);
      ((BookingRate)localRealmResults.get(0)).setFeedback(paramString);
      ((BookingRate)localRealmResults.get(0)).setStatusBooking(4);
    }
    try
    {
      paramString = (BookingRate)getDB().copyToRealmOrUpdate(localRealmResults.get(0));
      getDB().commitTransaction();
      return;
    }
    catch (RealmException paramString)
    {
      Log.e(TAG, "updateBookingRate() got realm exception " + paramString.getMessage());
      getDB().cancelTransaction();
      return;
    }
    catch (Exception paramString)
    {
      Log.e(TAG, "updateBookingRate() got exception " + paramString.getMessage());
      getDB().cancelTransaction();
    }
  }
  
  public void updateBookingStatus(int paramInt1, int paramInt2)
  {
    getDB().beginTransaction();
    Object localObject = getDB().where(BookingRate.class).equalTo("id", paramInt1).findAll();
    Log.i(TAG, "got existing RealmResults bookingRate " + ((RealmResults)localObject).size());
    if ((localObject != null) && (((RealmResults)localObject).size() > 0))
    {
      ((BookingRate)((RealmResults)localObject).get(0)).setId(paramInt1);
      ((BookingRate)((RealmResults)localObject).get(0)).setStatusBooking(paramInt2);
    }
    try
    {
      localObject = (BookingRate)getDB().copyToRealmOrUpdate(((RealmResults)localObject).get(0));
      getDB().commitTransaction();
      return;
    }
    catch (RealmException localRealmException)
    {
      Log.e(TAG, "updateBookingStatus() got realm exception " + localRealmException.getMessage());
      getDB().cancelTransaction();
      return;
    }
    catch (Exception localException)
    {
      Log.e(TAG, "updateBookingStatus() got exception " + localException.getMessage());
      getDB().cancelTransaction();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/persistence/dao/BookingRateDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */