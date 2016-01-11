package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public class NumberSerializer
  extends StdScalarSerializer<Number>
{
  public static final NumberSerializer instance = new NumberSerializer(Number.class);
  protected final boolean _isInt;
  
  @Deprecated
  public NumberSerializer()
  {
    super(Number.class);
    this._isInt = false;
  }
  
  public NumberSerializer(Class<? extends Number> paramClass)
  {
    super(paramClass, false);
    if (paramClass == BigInteger.class) {
      bool = true;
    }
    this._isInt = bool;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (this._isInt)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.BIG_INTEGER);
      }
    }
    do
    {
      return;
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectNumberFormat(paramJavaType);
    } while ((paramJsonFormatVisitorWrapper == null) || (handledType() != BigDecimal.class));
    paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.BIG_DECIMAL);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (this._isInt) {}
    for (paramSerializerProvider = "integer";; paramSerializerProvider = "number") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public void serialize(Number paramNumber, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((paramNumber instanceof BigDecimal))
    {
      paramJsonGenerator.writeNumber((BigDecimal)paramNumber);
      return;
    }
    if ((paramNumber instanceof BigInteger))
    {
      paramJsonGenerator.writeNumber((BigInteger)paramNumber);
      return;
    }
    if ((paramNumber instanceof Integer))
    {
      paramJsonGenerator.writeNumber(paramNumber.intValue());
      return;
    }
    if ((paramNumber instanceof Long))
    {
      paramJsonGenerator.writeNumber(paramNumber.longValue());
      return;
    }
    if ((paramNumber instanceof Double))
    {
      paramJsonGenerator.writeNumber(paramNumber.doubleValue());
      return;
    }
    if ((paramNumber instanceof Float))
    {
      paramJsonGenerator.writeNumber(paramNumber.floatValue());
      return;
    }
    if (((paramNumber instanceof Byte)) || ((paramNumber instanceof Short)))
    {
      paramJsonGenerator.writeNumber(paramNumber.intValue());
      return;
    }
    paramJsonGenerator.writeNumber(paramNumber.toString());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/NumberSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */