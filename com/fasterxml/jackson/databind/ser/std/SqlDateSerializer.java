package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;

@JacksonStdImpl
public class SqlDateSerializer
  extends DateTimeSerializerBase<Date>
{
  public SqlDateSerializer()
  {
    this(Boolean.FALSE);
  }
  
  protected SqlDateSerializer(Boolean paramBoolean)
  {
    super(Date.class, paramBoolean, null);
  }
  
  protected long _timestamp(Date paramDate)
  {
    if (paramDate == null) {
      return 0L;
    }
    return paramDate.getTime();
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    _acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, paramJavaType, this._useTimestamp.booleanValue());
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("string", true);
  }
  
  public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (_asTimestamp(paramSerializerProvider))
    {
      paramJsonGenerator.writeNumber(_timestamp(paramDate));
      return;
    }
    paramJsonGenerator.writeString(paramDate.toString());
  }
  
  public SqlDateSerializer withFormat(Boolean paramBoolean, DateFormat paramDateFormat)
  {
    return new SqlDateSerializer(paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/SqlDateSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */