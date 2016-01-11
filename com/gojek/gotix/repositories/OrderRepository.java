package com.gojek.gotix.repositories;

import com.gojek.gotix.DaoSession;
import com.gojek.gotix.Event;
import com.gojek.gotix.Location;
import com.gojek.gotix.LocationDao;
import com.gojek.gotix.Order;
import com.gojek.gotix.OrderDao;
import com.gojek.gotix.OrderDao.Properties;
import com.gojek.gotix.OrderSchedule;
import com.gojek.gotix.helper.GotixEngine;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.Iterator;
import java.util.List;
import lib.gojek.base.tools.RepoTools;

public class OrderRepository
{
  private static final String TAG = OrderRepository.class.getSimpleName();
  
  public static Order createFromEvent(Event paramEvent, int paramInt)
  {
    Order localOrder = new Order();
    localOrder.setOrder_id(Integer.valueOf(paramInt));
    localOrder.setEvent_id(paramEvent.getEvent_id());
    localOrder.setType(paramEvent.getType());
    localOrder.setStatus(paramEvent.getStatus());
    localOrder.setImage(paramEvent.getImage());
    localOrder.setDate(paramEvent.getDate());
    localOrder.setLocation(paramEvent.getLocation());
    localOrder.setName(paramEvent.getName());
    localOrder.setType_description(paramEvent.getType_description());
    localOrder.setTag(paramEvent.getTag());
    localOrder.setDescription(paramEvent.getDescription());
    localOrder.setIs_take_me_there(paramEvent.getIs_take_me_there());
    return localOrder;
  }
  
  public static Order find(int paramInt)
  {
    Order localOrder = null;
    List localList = getOrderDao().queryBuilder().where(OrderDao.Properties.Order_id.eq(Integer.valueOf(paramInt)), new WhereCondition[0]).limit(1).list();
    if (RepoTools.isRowAvailable(localList)) {
      localOrder = (Order)localList.get(0);
    }
    if (localOrder != null)
    {
      localOrder.setSchedules(OrderScheduleRepository.getByOrderId(localOrder.getId().longValue()));
      localOrder.setLocations(LocationRepository.getLocationDao()._queryOrder_Locations(localOrder.getId().longValue()));
    }
    return localOrder;
  }
  
  public static Order find(Order paramOrder)
  {
    if (getOrderDao().load(paramOrder.getId()) != null) {
      return paramOrder;
    }
    return null;
  }
  
  private static Order getOrder(int paramInt)
  {
    List localList = getOrderDao().queryBuilder().where(OrderDao.Properties.Order_id.eq(Integer.valueOf(paramInt)), new WhereCondition[0]).limit(1).list();
    if (RepoTools.isRowAvailable(localList)) {
      return (Order)localList.get(0);
    }
    return null;
  }
  
  public static OrderDao getOrderDao()
  {
    return GotixEngine.getInstance().getDaoSession().getOrderDao();
  }
  
  public static boolean has(int paramInt)
  {
    return find(paramInt) != null;
  }
  
  public static List<Order> loadData()
  {
    return getOrderDao().loadAll();
  }
  
  public static void save(Order paramOrder)
  {
    Object localObject;
    if (has(paramOrder.getOrder_id().intValue()))
    {
      localObject = find(paramOrder.getOrder_id().intValue());
      paramOrder.setId(((Order)localObject).getId());
      if (paramOrder.getCharged_amount() == null) {
        paramOrder.setCharged_amount(((Order)localObject).getCharged_amount());
      }
      if (paramOrder.getType() == null) {
        paramOrder.setType(((Order)localObject).getType());
      }
      if (paramOrder.getType_description() == null) {
        paramOrder.setType_description(((Order)localObject).getType_description());
      }
      if (paramOrder.getType_is_delivered() == null) {
        paramOrder.setType_is_delivered(((Order)localObject).getType_is_delivered());
      }
    }
    long l = getOrderDao().insertOrReplace(paramOrder);
    if (l > 0L)
    {
      if (paramOrder.getSchedules() != null)
      {
        localObject = paramOrder.getSchedules().iterator();
        while (((Iterator)localObject).hasNext()) {
          OrderScheduleRepository.save((OrderSchedule)((Iterator)localObject).next(), l);
        }
      }
      if (paramOrder.getLocations() != null)
      {
        paramOrder = paramOrder.getLocations().iterator();
        while (paramOrder.hasNext()) {
          LocationRepository.save(l, (Location)paramOrder.next());
        }
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/repositories/OrderRepository.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */