package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

@JacksonStdImpl
public class CalendarSerializer
  extends DateTimeSerializerBase<Calendar>
{
  public static final CalendarSerializer instance = new CalendarSerializer();
  
  public CalendarSerializer()
  {
    this(null, null);
  }
  
  public CalendarSerializer(Boolean paramBoolean, DateFormat paramDateFormat)
  {
    super(Calendar.class, paramBoolean, paramDateFormat);
  }
  
  protected long _timestamp(Calendar paramCalendar)
  {
    if (paramCalendar == null) {
      return 0L;
    }
    return paramCalendar.getTimeInMillis();
  }
  
  public void serialize(Calendar paramCalendar, JsonGenerator paramJsonGenerator, SerializerProvider arg3)
    throws IOException
  {
    if (_asTimestamp(???))
    {
      paramJsonGenerator.writeNumber(_timestamp(paramCalendar));
      return;
    }
    if (this._customFormat != null) {
      synchronized (this._customFormat)
      {
        paramJsonGenerator.writeString(this._customFormat.format(paramCalendar.getTime()));
        return;
      }
    }
    ???.defaultSerializeDateValue(paramCalendar.getTime(), paramJsonGenerator);
  }
  
  public CalendarSerializer withFormat(Boolean paramBoolean, DateFormat paramDateFormat)
  {
    return new CalendarSerializer(paramBoolean, paramDateFormat);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/CalendarSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */