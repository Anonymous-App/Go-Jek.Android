package com.gojek.gotix;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class Event
{
  private String call_center_number;
  private String call_center_text;
  private transient DaoSession daoSession;
  private String date;
  private String description;
  private Integer event_id;
  private Long id;
  private String image;
  private List<Image> images;
  private Boolean is_take_me_there;
  private String location;
  private List<Location> locations;
  private String lowest_available_price;
  private Integer maximum_ticket_transaction;
  private transient EventDao myDao;
  private String name;
  private String rating;
  private List<Schedule> schedules;
  private String status;
  private String[] tag;
  private String tag_temporary;
  private String type;
  private String type_description;
  private Boolean type_is_delivered;
  
  public Event() {}
  
  public Event(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public Event(Long paramLong, Integer paramInteger1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, Boolean paramBoolean1, String paramString12, String paramString13, Integer paramInteger2, Boolean paramBoolean2)
  {
    this.id = paramLong;
    this.event_id = paramInteger1;
    this.name = paramString1;
    this.image = paramString2;
    this.description = paramString3;
    this.rating = paramString4;
    this.type = paramString5;
    this.type_description = paramString6;
    this.tag_temporary = paramString7;
    this.date = paramString8;
    this.lowest_available_price = paramString9;
    this.location = paramString10;
    this.status = paramString11;
    this.is_take_me_there = paramBoolean1;
    this.call_center_text = paramString12;
    this.call_center_number = paramString13;
    this.maximum_ticket_transaction = paramInteger2;
    this.type_is_delivered = paramBoolean2;
  }
  
  public void __setDaoSession(DaoSession paramDaoSession)
  {
    this.daoSession = paramDaoSession;
    if (paramDaoSession != null) {}
    for (paramDaoSession = paramDaoSession.getEventDao();; paramDaoSession = null)
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
      paramObject = (Event)paramObject;
      if (this.event_id != null)
      {
        if (this.event_id.equals(((Event)paramObject).event_id)) {}
      }
      else {
        while (((Event)paramObject).event_id != null) {
          return false;
        }
      }
      if (this.name == null) {
        break;
      }
    } while (this.name.equals(((Event)paramObject).name));
    while (((Event)paramObject).name != null) {
      return false;
    }
    return true;
  }
  
  public String getCall_center_number()
  {
    return this.call_center_number;
  }
  
  public String getCall_center_text()
  {
    return this.call_center_text;
  }
  
  public String getDate()
  {
    return this.date;
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
  
  public List<Image> getImages()
  {
    List localList;
    if (this.images == null)
    {
      if (this.daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      localList = this.daoSession.getImageDao()._queryEvent_Images(this.id.longValue());
    }
    try
    {
      if (this.images == null) {
        this.images = localList;
      }
      return this.images;
    }
    finally {}
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
      localList = this.daoSession.getLocationDao()._queryEvent_Locations(this.id.longValue());
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
  
  public String getLowest_available_price()
  {
    return this.lowest_available_price;
  }
  
  public Integer getMaximum_ticket_transaction()
  {
    return this.maximum_ticket_transaction;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getRating()
  {
    return this.rating;
  }
  
  public List<Schedule> getSchedules()
  {
    List localList;
    if (this.schedules == null)
    {
      if (this.daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      localList = this.daoSession.getScheduleDao()._queryEvent_Schedules(this.id.longValue());
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
  
  public void resetImages()
  {
    try
    {
      this.images = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
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
  
  public void setCall_center_number(String paramString)
  {
    this.call_center_number = paramString;
  }
  
  public void setCall_center_text(String paramString)
  {
    this.call_center_text = paramString;
  }
  
  public void setDate(String paramString)
  {
    this.date = paramString;
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
  
  public void setLowest_available_price(String paramString)
  {
    this.lowest_available_price = paramString;
  }
  
  public void setMaximum_ticket_transaction(Integer paramInteger)
  {
    this.maximum_ticket_transaction = paramInteger;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setRating(String paramString)
  {
    this.rating = paramString;
  }
  
  public void setSchedules(List<Schedule> paramList)
  {
    if (this.schedules == null) {
      this.schedules = paramList;
    }
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/Event.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */