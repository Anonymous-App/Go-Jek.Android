package com.gojek.gotix;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class OrderDao
  extends AbstractDao<Order, Long>
{
  public static final String TABLENAME = "ORDER";
  private DaoSession daoSession;
  
  public OrderDao(DaoConfig paramDaoConfig)
  {
    super(paramDaoConfig);
  }
  
  public OrderDao(DaoConfig paramDaoConfig, DaoSession paramDaoSession)
  {
    super(paramDaoConfig, paramDaoSession);
    this.daoSession = paramDaoSession;
  }
  
  public static void createTable(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "IF NOT EXISTS ";; str = "")
    {
      str = "CREATE TABLE " + str + "'ORDER' (" + "'_id' INTEGER PRIMARY KEY ," + "'ORDER_ID' INTEGER," + "'EVENT_ID' INTEGER," + "'NAME' TEXT," + "'IMAGE' TEXT," + "'DESCRIPTION' TEXT," + "'TAG_TEMPORARY' TEXT," + "'RATING' TEXT," + "'DATE' TEXT," + "'DATE_TIMESTAMP' INTEGER," + "'LOCATION' TEXT," + "'STATUS' TEXT," + "'BOOKING_REFERENCE' TEXT," + "'TYPE' TEXT," + "'TYPE_DESCRIPTION' TEXT," + "'CHARGED_AMOUNT' TEXT," + "'IS_TAKE_ME_THERE' INTEGER," + "'SHARE_URL' TEXT," + "'TYPE_IS_DELIVERED' INTEGER);";
      if ((paramSQLiteDatabase instanceof SQLiteDatabase)) {
        break;
      }
      paramSQLiteDatabase.execSQL(str);
      return;
    }
    SQLiteInstrumentation.execSQL((SQLiteDatabase)paramSQLiteDatabase, str);
  }
  
  public static void dropTable(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("DROP TABLE ");
    if (paramBoolean) {}
    for (String str = "IF EXISTS ";; str = "")
    {
      str = str + "'ORDER'";
      if ((paramSQLiteDatabase instanceof SQLiteDatabase)) {
        break;
      }
      paramSQLiteDatabase.execSQL(str);
      return;
    }
    SQLiteInstrumentation.execSQL((SQLiteDatabase)paramSQLiteDatabase, str);
  }
  
  protected void attachEntity(Order paramOrder)
  {
    super.attachEntity(paramOrder);
    paramOrder.__setDaoSession(this.daoSession);
  }
  
  protected void bindValues(SQLiteStatement paramSQLiteStatement, Order paramOrder)
  {
    paramSQLiteStatement.clearBindings();
    Object localObject = paramOrder.getId();
    if (localObject != null) {
      paramSQLiteStatement.bindLong(1, ((Long)localObject).longValue());
    }
    localObject = paramOrder.getOrder_id();
    if (localObject != null) {
      paramSQLiteStatement.bindLong(2, ((Integer)localObject).intValue());
    }
    localObject = paramOrder.getEvent_id();
    if (localObject != null) {
      paramSQLiteStatement.bindLong(3, ((Integer)localObject).intValue());
    }
    localObject = paramOrder.getName();
    if (localObject != null) {
      paramSQLiteStatement.bindString(4, (String)localObject);
    }
    localObject = paramOrder.getImage();
    if (localObject != null) {
      paramSQLiteStatement.bindString(5, (String)localObject);
    }
    localObject = paramOrder.getDescription();
    if (localObject != null) {
      paramSQLiteStatement.bindString(6, (String)localObject);
    }
    localObject = paramOrder.getTag_temporary();
    if (localObject != null) {
      paramSQLiteStatement.bindString(7, (String)localObject);
    }
    localObject = paramOrder.getRating();
    if (localObject != null) {
      paramSQLiteStatement.bindString(8, (String)localObject);
    }
    localObject = paramOrder.getDate();
    if (localObject != null) {
      paramSQLiteStatement.bindString(9, (String)localObject);
    }
    localObject = paramOrder.getDate_timestamp();
    if (localObject != null) {
      paramSQLiteStatement.bindLong(10, ((Integer)localObject).intValue());
    }
    localObject = paramOrder.getLocation();
    if (localObject != null) {
      paramSQLiteStatement.bindString(11, (String)localObject);
    }
    localObject = paramOrder.getStatus();
    if (localObject != null) {
      paramSQLiteStatement.bindString(12, (String)localObject);
    }
    localObject = paramOrder.getBooking_reference();
    if (localObject != null) {
      paramSQLiteStatement.bindString(13, (String)localObject);
    }
    localObject = paramOrder.getType();
    if (localObject != null) {
      paramSQLiteStatement.bindString(14, (String)localObject);
    }
    localObject = paramOrder.getType_description();
    if (localObject != null) {
      paramSQLiteStatement.bindString(15, (String)localObject);
    }
    localObject = paramOrder.getCharged_amount();
    if (localObject != null) {
      paramSQLiteStatement.bindString(16, (String)localObject);
    }
    localObject = paramOrder.getIs_take_me_there();
    if (localObject != null)
    {
      if (((Boolean)localObject).booleanValue())
      {
        l = 1L;
        paramSQLiteStatement.bindLong(17, l);
      }
    }
    else
    {
      localObject = paramOrder.getShare_url();
      if (localObject != null) {
        paramSQLiteStatement.bindString(18, (String)localObject);
      }
      paramOrder = paramOrder.getType_is_delivered();
      if (paramOrder != null) {
        if (!paramOrder.booleanValue()) {
          break label396;
        }
      }
    }
    label396:
    for (long l = 1L;; l = 0L)
    {
      paramSQLiteStatement.bindLong(19, l);
      return;
      l = 0L;
      break;
    }
  }
  
  public Long getKey(Order paramOrder)
  {
    if (paramOrder != null) {
      return paramOrder.getId();
    }
    return null;
  }
  
  protected boolean isEntityUpdateable()
  {
    return true;
  }
  
  public Order readEntity(Cursor paramCursor, int paramInt)
  {
    Long localLong;
    Integer localInteger1;
    label30:
    Integer localInteger2;
    label45:
    String str1;
    label60:
    String str2;
    label75:
    String str3;
    label90:
    String str4;
    label106:
    String str5;
    label122:
    String str6;
    label138:
    Integer localInteger3;
    label154:
    String str7;
    label170:
    String str8;
    label186:
    String str9;
    label202:
    String str10;
    label218:
    String str11;
    label234:
    String str12;
    label250:
    Boolean localBoolean;
    if (paramCursor.isNull(paramInt + 0))
    {
      localLong = null;
      if (!paramCursor.isNull(paramInt + 1)) {
        break label359;
      }
      localInteger1 = null;
      if (!paramCursor.isNull(paramInt + 2)) {
        break label376;
      }
      localInteger2 = null;
      if (!paramCursor.isNull(paramInt + 3)) {
        break label393;
      }
      str1 = null;
      if (!paramCursor.isNull(paramInt + 4)) {
        break label407;
      }
      str2 = null;
      if (!paramCursor.isNull(paramInt + 5)) {
        break label421;
      }
      str3 = null;
      if (!paramCursor.isNull(paramInt + 6)) {
        break label435;
      }
      str4 = null;
      if (!paramCursor.isNull(paramInt + 7)) {
        break label450;
      }
      str5 = null;
      if (!paramCursor.isNull(paramInt + 8)) {
        break label465;
      }
      str6 = null;
      if (!paramCursor.isNull(paramInt + 9)) {
        break label480;
      }
      localInteger3 = null;
      if (!paramCursor.isNull(paramInt + 10)) {
        break label498;
      }
      str7 = null;
      if (!paramCursor.isNull(paramInt + 11)) {
        break label513;
      }
      str8 = null;
      if (!paramCursor.isNull(paramInt + 12)) {
        break label528;
      }
      str9 = null;
      if (!paramCursor.isNull(paramInt + 13)) {
        break label543;
      }
      str10 = null;
      if (!paramCursor.isNull(paramInt + 14)) {
        break label558;
      }
      str11 = null;
      if (!paramCursor.isNull(paramInt + 15)) {
        break label573;
      }
      str12 = null;
      if (!paramCursor.isNull(paramInt + 16)) {
        break label588;
      }
      localBoolean = null;
      if (!paramCursor.isNull(paramInt + 17)) {
        break label617;
      }
    }
    label359:
    label376:
    label393:
    label407:
    label421:
    label435:
    label450:
    label465:
    label480:
    label498:
    label513:
    label528:
    label543:
    label558:
    label573:
    label588:
    label617:
    for (String str13 = null;; str13 = paramCursor.getString(paramInt + 17))
    {
      if (!paramCursor.isNull(paramInt + 18)) {
        break label632;
      }
      paramCursor = null;
      return new Order(localLong, localInteger1, localInteger2, str1, str2, str3, str4, str5, str6, localInteger3, str7, str8, str9, str10, str11, str12, localBoolean, str13, paramCursor);
      localLong = Long.valueOf(paramCursor.getLong(paramInt + 0));
      break;
      localInteger1 = Integer.valueOf(paramCursor.getInt(paramInt + 1));
      break label30;
      localInteger2 = Integer.valueOf(paramCursor.getInt(paramInt + 2));
      break label45;
      str1 = paramCursor.getString(paramInt + 3);
      break label60;
      str2 = paramCursor.getString(paramInt + 4);
      break label75;
      str3 = paramCursor.getString(paramInt + 5);
      break label90;
      str4 = paramCursor.getString(paramInt + 6);
      break label106;
      str5 = paramCursor.getString(paramInt + 7);
      break label122;
      str6 = paramCursor.getString(paramInt + 8);
      break label138;
      localInteger3 = Integer.valueOf(paramCursor.getInt(paramInt + 9));
      break label154;
      str7 = paramCursor.getString(paramInt + 10);
      break label170;
      str8 = paramCursor.getString(paramInt + 11);
      break label186;
      str9 = paramCursor.getString(paramInt + 12);
      break label202;
      str10 = paramCursor.getString(paramInt + 13);
      break label218;
      str11 = paramCursor.getString(paramInt + 14);
      break label234;
      str12 = paramCursor.getString(paramInt + 15);
      break label250;
      if (paramCursor.getShort(paramInt + 16) != 0) {}
      for (bool = true;; bool = false)
      {
        localBoolean = Boolean.valueOf(bool);
        break;
      }
    }
    label632:
    if (paramCursor.getShort(paramInt + 18) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramCursor = Boolean.valueOf(bool);
      break;
    }
  }
  
  public void readEntity(Cursor paramCursor, Order paramOrder, int paramInt)
  {
    boolean bool2 = true;
    Object localObject2 = null;
    if (paramCursor.isNull(paramInt + 0))
    {
      localObject1 = null;
      paramOrder.setId((Long)localObject1);
      if (!paramCursor.isNull(paramInt + 1)) {
        break label435;
      }
      localObject1 = null;
      label42:
      paramOrder.setOrder_id((Integer)localObject1);
      if (!paramCursor.isNull(paramInt + 2)) {
        break label452;
      }
      localObject1 = null;
      label63:
      paramOrder.setEvent_id((Integer)localObject1);
      if (!paramCursor.isNull(paramInt + 3)) {
        break label469;
      }
      localObject1 = null;
      label84:
      paramOrder.setName((String)localObject1);
      if (!paramCursor.isNull(paramInt + 4)) {
        break label483;
      }
      localObject1 = null;
      label105:
      paramOrder.setImage((String)localObject1);
      if (!paramCursor.isNull(paramInt + 5)) {
        break label497;
      }
      localObject1 = null;
      label126:
      paramOrder.setDescription((String)localObject1);
      if (!paramCursor.isNull(paramInt + 6)) {
        break label511;
      }
      localObject1 = null;
      label148:
      paramOrder.setTag_temporary((String)localObject1);
      if (!paramCursor.isNull(paramInt + 7)) {
        break label526;
      }
      localObject1 = null;
      label170:
      paramOrder.setRating((String)localObject1);
      if (!paramCursor.isNull(paramInt + 8)) {
        break label541;
      }
      localObject1 = null;
      label192:
      paramOrder.setDate((String)localObject1);
      if (!paramCursor.isNull(paramInt + 9)) {
        break label556;
      }
      localObject1 = null;
      label214:
      paramOrder.setDate_timestamp((Integer)localObject1);
      if (!paramCursor.isNull(paramInt + 10)) {
        break label574;
      }
      localObject1 = null;
      label236:
      paramOrder.setLocation((String)localObject1);
      if (!paramCursor.isNull(paramInt + 11)) {
        break label589;
      }
      localObject1 = null;
      label258:
      paramOrder.setStatus((String)localObject1);
      if (!paramCursor.isNull(paramInt + 12)) {
        break label604;
      }
      localObject1 = null;
      label280:
      paramOrder.setBooking_reference((String)localObject1);
      if (!paramCursor.isNull(paramInt + 13)) {
        break label619;
      }
      localObject1 = null;
      label302:
      paramOrder.setType((String)localObject1);
      if (!paramCursor.isNull(paramInt + 14)) {
        break label634;
      }
      localObject1 = null;
      label324:
      paramOrder.setType_description((String)localObject1);
      if (!paramCursor.isNull(paramInt + 15)) {
        break label649;
      }
      localObject1 = null;
      label346:
      paramOrder.setCharged_amount((String)localObject1);
      if (!paramCursor.isNull(paramInt + 16)) {
        break label664;
      }
      localObject1 = null;
      paramOrder.setIs_take_me_there((Boolean)localObject1);
      if (!paramCursor.isNull(paramInt + 17)) {
        break label696;
      }
    }
    label435:
    label452:
    label469:
    label483:
    label497:
    label511:
    label526:
    label541:
    label556:
    label574:
    label589:
    label604:
    label619:
    label634:
    label649:
    label664:
    label696:
    for (Object localObject1 = null;; localObject1 = paramCursor.getString(paramInt + 17))
    {
      paramOrder.setShare_url((String)localObject1);
      if (!paramCursor.isNull(paramInt + 18)) {
        break label711;
      }
      paramCursor = (Cursor)localObject2;
      paramOrder.setType_is_delivered(paramCursor);
      return;
      localObject1 = Long.valueOf(paramCursor.getLong(paramInt + 0));
      break;
      localObject1 = Integer.valueOf(paramCursor.getInt(paramInt + 1));
      break label42;
      localObject1 = Integer.valueOf(paramCursor.getInt(paramInt + 2));
      break label63;
      localObject1 = paramCursor.getString(paramInt + 3);
      break label84;
      localObject1 = paramCursor.getString(paramInt + 4);
      break label105;
      localObject1 = paramCursor.getString(paramInt + 5);
      break label126;
      localObject1 = paramCursor.getString(paramInt + 6);
      break label148;
      localObject1 = paramCursor.getString(paramInt + 7);
      break label170;
      localObject1 = paramCursor.getString(paramInt + 8);
      break label192;
      localObject1 = Integer.valueOf(paramCursor.getInt(paramInt + 9));
      break label214;
      localObject1 = paramCursor.getString(paramInt + 10);
      break label236;
      localObject1 = paramCursor.getString(paramInt + 11);
      break label258;
      localObject1 = paramCursor.getString(paramInt + 12);
      break label280;
      localObject1 = paramCursor.getString(paramInt + 13);
      break label302;
      localObject1 = paramCursor.getString(paramInt + 14);
      break label324;
      localObject1 = paramCursor.getString(paramInt + 15);
      break label346;
      if (paramCursor.getShort(paramInt + 16) != 0) {}
      for (bool1 = true;; bool1 = false)
      {
        localObject1 = Boolean.valueOf(bool1);
        break;
      }
    }
    label711:
    if (paramCursor.getShort(paramInt + 18) != 0) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      paramCursor = Boolean.valueOf(bool1);
      break;
    }
  }
  
  public Long readKey(Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt + 0)) {
      return null;
    }
    return Long.valueOf(paramCursor.getLong(paramInt + 0));
  }
  
  protected Long updateKeyAfterInsert(Order paramOrder, long paramLong)
  {
    paramOrder.setId(Long.valueOf(paramLong));
    return Long.valueOf(paramLong);
  }
  
  public static class Properties
  {
    public static final Property Booking_reference;
    public static final Property Charged_amount = new Property(15, String.class, "charged_amount", false, "CHARGED_AMOUNT");
    public static final Property Date;
    public static final Property Date_timestamp;
    public static final Property Description;
    public static final Property Event_id;
    public static final Property Id = new Property(0, Long.class, "id", true, "_id");
    public static final Property Image;
    public static final Property Is_take_me_there = new Property(16, Boolean.class, "is_take_me_there", false, "IS_TAKE_ME_THERE");
    public static final Property Location;
    public static final Property Name;
    public static final Property Order_id = new Property(1, Integer.class, "order_id", false, "ORDER_ID");
    public static final Property Rating;
    public static final Property Share_url = new Property(17, String.class, "share_url", false, "SHARE_URL");
    public static final Property Status;
    public static final Property Tag_temporary;
    public static final Property Type;
    public static final Property Type_description;
    public static final Property Type_is_delivered = new Property(18, Boolean.class, "type_is_delivered", false, "TYPE_IS_DELIVERED");
    
    static
    {
      Event_id = new Property(2, Integer.class, "event_id", false, "EVENT_ID");
      Name = new Property(3, String.class, "name", false, "NAME");
      Image = new Property(4, String.class, "image", false, "IMAGE");
      Description = new Property(5, String.class, "description", false, "DESCRIPTION");
      Tag_temporary = new Property(6, String.class, "tag_temporary", false, "TAG_TEMPORARY");
      Rating = new Property(7, String.class, "rating", false, "RATING");
      Date = new Property(8, String.class, "date", false, "DATE");
      Date_timestamp = new Property(9, Integer.class, "date_timestamp", false, "DATE_TIMESTAMP");
      Location = new Property(10, String.class, "location", false, "LOCATION");
      Status = new Property(11, String.class, "status", false, "STATUS");
      Booking_reference = new Property(12, String.class, "booking_reference", false, "BOOKING_REFERENCE");
      Type = new Property(13, String.class, "type", false, "TYPE");
      Type_description = new Property(14, String.class, "type_description", false, "TYPE_DESCRIPTION");
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/OrderDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */