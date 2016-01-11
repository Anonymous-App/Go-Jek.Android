package com.gojek.gotix;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class Order
{
  private String booking_reference;
  private String charged_amount;
  private transient DaoSession daoSession;
  private String date;
  private Integer date_timestamp;
  private String description;
  private Integer event_id;
  private Long id;
  private String image;
  private Boolean is_take_me_there;
  private String location;
  private List<Location> locations;
  private transient OrderDao myDao;
  private String name;
  private Integer order_id;
  private String rating;
  private List<OrderSchedule> schedules;
  private String share_url;
  private String status;
  private String[] tag;
  private String tag_temporary;
  private String type;
  private String type_description;
  private Boolean type_is_delivered;
  
  public Order() {}
  
  public Order(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public Order(Long paramLong, Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Integer paramInteger3, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, Boolean paramBoolean1, String paramString13, Boolean paramBoolean2)
  {
    this.id = paramLong;
    this.order_id = paramInteger1;
    this.event_id = paramInteger2;
    this.name = paramString1;
    this.image = paramString2;
    this.description = paramString3;
    this.tag_temporary = paramString4;
    this.rating = paramString5;
    this.date = paramString6;
    this.date_timestamp = paramInteger3;
    this.location = paramString7;
    this.status = paramString8;
    this.booking_reference = paramString9;
    this.type = paramString10;
    this.type_description = paramString11;
    this.charged_amount = paramString12;
    this.is_take_me_there = paramBoolean1;
    this.share_url = paramString13;
    this.type_is_delivered = paramBoolean2;
  }
  
  public void __setDaoSession(DaoSession paramDaoSession)
  {
    this.daoSession = paramDaoSession;
    if (paramDaoSession != null) {}
    for (paramDaoSession = paramDaoSession.getOrderDao();; paramDaoSession = null)
    {
      this.myDao = paramDaoSession;
      return;
    }
  }
  
  public void delete()
  {
    if (this.myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    this.myDao.delete(this);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (Order)paramObject;
      if (this.order_id == null) {
        break;
      }
    } while (this.order_id.equals(((Order)paramObject).order_id));
    while (((Order)paramObject).order_id != null) {
      return false;
    }
    return true;
  }
  
  public String getBooking_reference()
  {
    return this.booking_reference;
  }
  
  public String getCharged_amount()
  {
    return this.charged_amount;
  }
  
  public String getDate()
  {
    return this.date;
  }
  
  public Integer getDate_timestamp()
  {
    return this.date_timestamp;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public Integer getEvent_id()
  {
    return this.event_id;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public String getImage()
  {
    return this.image;
  }
  
  public Boolean getIs_take_me_there()
  {
    return this.is_take_me_there;
  }
  
  public String getLocation()
  {
    return this.location;
  }
  
  public List<Location> getLocations()
  {
    List localList;
    if (this.locations == null)
    {
      if (this.daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      localList = this.daoSession.getLocationDao()._queryOrder_Locations(this.id.longValue());
    }
    try
    {
      if (this.locations == null) {
        this.locations = localList;
      }
      return this.locations;
    }
    finally {}
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Integer getOrder_id()
  {
    return this.order_id;
  }
  
  public String getRating()
  {
    return this.rating;
  }
  
  public List<OrderSchedule> getSchedules()
  {
    List localList;
    if (this.schedules == null)
    {
      if (this.daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      localList = this.daoSession.getOrderScheduleDao()._queryOrder_Schedules(this.id.longValue());
    }
    try
    {
      if (this.schedules == null) {
        this.schedules = localList;
      }
      return this.schedules;
    }
    finally {}
  }
  
  public String getShare_url()
  {
    return this.share_url;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public String[] getTag()
  {
    return this.tag;
  }
  
  public String getTag_temporary()
  {
    return this.tag_temporary;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public String getType_description()
  {
    return this.type_description;
  }
  
  public Boolean getType_is_delivered()
  {
    return this.type_is_delivered;
  }
  
  public int hashCode()
  {
    int j = 0;
    if (this.event_id != null) {}
    for (int i = this.event_id.hashCode();; i = 0)
    {
      if (this.name != null) {
        j = this.name.hashCode();
      }
      return i * 31 + j;
    }
  }
  
  public void refresh()
  {
    if (this.myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    this.myDao.refresh(this);
  }
  
  public void resetLocations()
  {
    try
    {
      this.locations = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void resetSchedules()
  {
    try
    {
      this.schedules = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void setBooking_reference(String paramString)
  {
    this.booking_reference = paramString;
  }
  
  public void setCharged_amount(String paramString)
  {
    this.charged_amount = paramString;
  }
  
  public void setDate(String paramString)
  {
    this.date = paramString;
  }
  
  public void setDate_timestamp(Integer paramInteger)
  {
    this.date_timestamp = paramInteger;
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setEvent_id(Integer paramInteger)
  {
    this.event_id = paramInteger;
  }
  
  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public void setImage(String paramString)
  {
    this.image = paramString;
  }
  
  public void setIs_take_me_there(Boolean paramBoolean)
  {
    this.is_take_me_there = paramBoolean;
  }
  
  public void setLocation(String paramString)
  {
    this.location = paramString;
  }
  
  public void setLocations(List<Location> paramList)
  {
    this.locations = paramList;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setOrder_id(Integer paramInteger)
  {
    this.order_id = paramInteger;
  }
  
  public void setRating(String paramString)
  {
    this.rating = paramString;
  }
  
  public void setSchedules(List<OrderSchedule> paramList)
  {
    this.schedules = paramList;
  }
  
  public void setShare_url(String paramString)
  {
    this.share_url = paramString;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public void setTag(String[] paramArrayOfString)
  {
    this.tag = paramArrayOfString;
  }
  
  public void setTag_temporary(String paramString)
  {
    this.tag_temporary = paramString;
  }
  
  public void setType(String paramString)
  {
    this.type = paramString;
  }
  
  public void setType_description(String paramString)
  {
    this.type_description = paramString;
  }
  
  public void setType_is_delivered(Boolean paramBoolean)
  {
    this.type_is_delivered = paramBoolean;
  }
  
  public void update()
  {
    if (this.myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    this.myDao.update(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/Order.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */