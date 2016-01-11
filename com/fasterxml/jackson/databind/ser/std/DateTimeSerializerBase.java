package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase<T>
  extends StdScalarSerializer<T>
  implements ContextualSerializer
{
  protected final DateFormat _customFormat;
  protected final Boolean _useTimestamp;
  
  protected DateTimeSerializerBase(Class<T> paramClass, Boolean paramBoolean, DateFormat paramDateFormat)
  {
    super(paramClass);
    this._useTimestamp = paramBoolean;
    this._customFormat = paramDateFormat;
  }
  
  protected void _acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType, boolean paramBoolean)
    throws JsonMappingException
  {
    if (paramBoolean)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null)
      {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.LONG);
        paramJsonFormatVisitorWrapper.format(JsonValueFormat.UTC_MILLISEC);
      }
    }
    do
    {
      return;
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
    } while (paramJsonFormatVisitorWrapper == null);
    paramJsonFormatVisitorWrapper.format(JsonValueFormat.DATE_TIME);
  }
  
  protected boolean _asTimestamp(SerializerProvider paramSerializerProvider)
  {
    if (this._useTimestamp != null) {
      return this._useTimestamp.booleanValue();
    }
    if (this._customFormat == null)
    {
      if (paramSerializerProvider != null) {
        return paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      }
      throw new IllegalArgumentException("Null SerializerProvider passed for " + handledType().getName());
    }
    return false;
  }
  
  protected abstract long _timestamp(T paramT);
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    _acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, paramJavaType, _asTimestamp(paramJsonFormatVisitorWrapper.getProvider()));
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    TimeZone localTimeZone = null;
    Object localObject1 = this;
    Object localObject2;
    if (paramBeanProperty != null)
    {
      localObject2 = paramSerializerProvider.getAnnotationIntrospector().findFormat(paramBeanProperty.getMember());
      localObject1 = this;
      if (localObject2 != null)
      {
        if (!((JsonFormat.Value)localObject2).getShape().isNumeric()) {
          break label53;
        }
        localObject1 = withFormat(Boolean.TRUE, null);
      }
    }
    label53:
    do
    {
      return (JsonSerializer<?>)localObject1;
      paramBeanProperty = localTimeZone;
      if (((JsonFormat.Value)localObject2).getShape() == JsonFormat.Shape.STRING) {
        paramBeanProperty = Boolean.FALSE;
      }
      localTimeZone = ((JsonFormat.Value)localObject2).getTimeZone();
      if (((JsonFormat.Value)localObject2).hasPattern())
      {
        String str = ((JsonFormat.Value)localObject2).getPattern();
        if (((JsonFormat.Value)localObject2).hasLocale()) {}
        for (localObject1 = ((JsonFormat.Value)localObject2).getLocale();; localObject1 = paramSerializerProvider.getLocale())
        {
          localObject2 = new SimpleDateFormat(str, (Locale)localObject1);
          localObject1 = localTimeZone;
          if (localTimeZone == null) {
            localObject1 = paramSerializerProvider.getTimeZone();
          }
          ((SimpleDateFormat)localObject2).setTimeZone((TimeZone)localObject1);
          return withFormat(paramBeanProperty, (DateFormat)localObject2);
        }
      }
      localObject1 = this;
    } while (localTimeZone == null);
    localObject1 = paramSerializerProvider.getConfig().getDateFormat();
    if (localObject1.getClass() == StdDateFormat.class) {
      if (((JsonFormat.Value)localObject2).hasLocale())
      {
        paramSerializerProvider = ((JsonFormat.Value)localObject2).getLocale();
        paramSerializerProvider = StdDateFormat.getISO8601Format(localTimeZone, paramSerializerProvider);
      }
    }
    for (;;)
    {
      return withFormat(paramBeanProperty, paramSerializerProvider);
      paramSerializerProvider = paramSerializerProvider.getLocale();
      break;
      paramSerializerProvider = (DateFormat)((DateFormat)localObject1).clone();
      paramSerializerProvider.setTimeZone(localTimeZone);
    }
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (_asTimestamp(paramSerializerProvider)) {}
    for (paramSerializerProvider = "number";; paramSerializerProvider = "string") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, T paramT)
  {
    return (paramT == null) || (_timestamp(paramT) == 0L);
  }
  
  @Deprecated
  public boolean isEmpty(T paramT)
  {
    return (paramT == null) || (_timestamp(paramT) == 0L);
  }
  
  public abstract void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException;
  
  public abstract DateTimeSerializerBase<T> withFormat(Boolean paramBoolean, DateFormat paramDateFormat);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/DateTimeSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */