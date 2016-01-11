package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T>
  extends JsonDeserializer<T>
  implements Serializable
{
  protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask();
  private static final long serialVersionUID = 1L;
  protected final Class<?> _valueClass;
  
  protected StdDeserializer(JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (paramJavaType = null;; paramJavaType = paramJavaType.getRawClass())
    {
      this._valueClass = paramJavaType;
      return;
    }
  }
  
  protected StdDeserializer(StdDeserializer<?> paramStdDeserializer)
  {
    this._valueClass = paramStdDeserializer._valueClass;
  }
  
  protected StdDeserializer(Class<?> paramClass)
  {
    this._valueClass = paramClass;
  }
  
  protected static final double parseDouble(String paramString)
    throws NumberFormatException
  {
    if ("2.2250738585072012e-308".equals(paramString)) {
      return Double.MIN_VALUE;
    }
    return Double.parseDouble(paramString);
  }
  
  protected Object _coerceIntegral(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    int i = paramDeserializationContext.getDeserializationFeatures();
    if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(i)) {
      return paramJsonParser.getBigIntegerValue();
    }
    if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(i)) {
      return Long.valueOf(paramJsonParser.getLongValue());
    }
    return paramJsonParser.getBigIntegerValue();
  }
  
  protected T _deserializeFromEmpty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.START_ARRAY)
    {
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
        break label76;
      }
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {}
    }
    while ((localJsonToken == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().trim().isEmpty()))
    {
      return null;
      throw paramDeserializationContext.mappingException(handledType(), JsonToken.START_ARRAY);
    }
    label76:
    throw paramDeserializationContext.mappingException(handledType());
  }
  
  protected void _failDoubleToIntCoercion(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, String paramString)
    throws IOException
  {
    throw paramDeserializationContext.mappingException("Can not coerce a floating-point value ('%s') into %s; enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow", new Object[] { paramJsonParser.getValueAsString(), paramString });
  }
  
  protected boolean _hasTextualNull(String paramString)
  {
    return "null".equals(paramString);
  }
  
  protected final boolean _isIntNumber(String paramString)
  {
    int j = paramString.length();
    int i;
    if (j > 0)
    {
      i = paramString.charAt(0);
      if ((i != 45) && (i != 43)) {
        break label57;
      }
      i = 1;
    }
    while (i < j)
    {
      int k = paramString.charAt(i);
      if ((k > 57) || (k < 48))
      {
        return false;
        label57:
        i = 0;
      }
      else
      {
        i += 1;
      }
    }
    return true;
  }
  
  protected final boolean _isNaN(String paramString)
  {
    return "NaN".equals(paramString);
  }
  
  protected final boolean _isNegInf(String paramString)
  {
    return ("-Infinity".equals(paramString)) || ("-INF".equals(paramString));
  }
  
  protected final boolean _isPosInf(String paramString)
  {
    return ("Infinity".equals(paramString)) || ("INF".equals(paramString));
  }
  
  protected final Boolean _parseBoolean(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject = paramJsonParser.getCurrentToken();
    if (localObject == JsonToken.VALUE_TRUE) {
      return Boolean.TRUE;
    }
    if (localObject == JsonToken.VALUE_FALSE) {
      return Boolean.FALSE;
    }
    if (localObject == JsonToken.VALUE_NUMBER_INT)
    {
      if (paramJsonParser.getNumberType() == JsonParser.NumberType.INT)
      {
        if (paramJsonParser.getIntValue() == 0) {
          return Boolean.FALSE;
        }
        return Boolean.TRUE;
      }
      return Boolean.valueOf(_parseBooleanFromNumber(paramJsonParser, paramDeserializationContext));
    }
    if (localObject == JsonToken.VALUE_NULL) {
      return (Boolean)getNullValue(paramDeserializationContext);
    }
    if (localObject == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (("true".equals(paramJsonParser)) || ("True".equals(paramJsonParser))) {
        return Boolean.TRUE;
      }
      if (("false".equals(paramJsonParser)) || ("False".equals(paramJsonParser))) {
        return Boolean.FALSE;
      }
      if (paramJsonParser.length() == 0) {
        return (Boolean)getEmptyValue(paramDeserializationContext);
      }
      if (_hasTextualNull(paramJsonParser)) {
        return (Boolean)getNullValue(paramDeserializationContext);
      }
      throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "only \"true\" or \"false\" recognized");
    }
    if ((localObject == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
    {
      paramJsonParser.nextToken();
      localObject = _parseBoolean(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Boolean' value but there was more than a single value in the array");
      }
      return (Boolean)localObject;
    }
    throw paramDeserializationContext.mappingException(this._valueClass, (JsonToken)localObject);
  }
  
  protected final boolean _parseBooleanFromNumber(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.getNumberType() == JsonParser.NumberType.LONG)
    {
      if (paramJsonParser.getLongValue() == 0L) {}
      for (paramJsonParser = Boolean.FALSE;; paramJsonParser = Boolean.TRUE) {
        return paramJsonParser.booleanValue();
      }
    }
    paramJsonParser = paramJsonParser.getText();
    if (("0.0".equals(paramJsonParser)) || ("0".equals(paramJsonParser))) {
      return Boolean.FALSE.booleanValue();
    }
    return Boolean.TRUE.booleanValue();
  }
  
  protected final boolean _parseBooleanPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    boolean bool2 = true;
    boolean bool3 = false;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    boolean bool1;
    if (localJsonToken == JsonToken.VALUE_TRUE) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return bool1;
                bool1 = bool3;
              } while (localJsonToken == JsonToken.VALUE_FALSE);
              bool1 = bool3;
            } while (localJsonToken == JsonToken.VALUE_NULL);
            if (localJsonToken == JsonToken.VALUE_NUMBER_INT)
            {
              if (paramJsonParser.getNumberType() == JsonParser.NumberType.INT)
              {
                if (paramJsonParser.getIntValue() != 0) {}
                for (bool1 = bool2;; bool1 = false) {
                  return bool1;
                }
              }
              return _parseBooleanFromNumber(paramJsonParser, paramDeserializationContext);
            }
            if (localJsonToken != JsonToken.VALUE_STRING) {
              break;
            }
            paramJsonParser = paramJsonParser.getText().trim();
            if (("true".equals(paramJsonParser)) || ("True".equals(paramJsonParser))) {
              return true;
            }
            bool1 = bool3;
          } while ("false".equals(paramJsonParser));
          bool1 = bool3;
        } while ("False".equals(paramJsonParser));
        bool1 = bool3;
      } while (paramJsonParser.length() == 0);
      bool1 = bool3;
    } while (_hasTextualNull(paramJsonParser));
    throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "only \"true\" or \"false\" recognized");
    if ((localJsonToken == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
    {
      paramJsonParser.nextToken();
      bool1 = _parseBooleanPrimitive(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'boolean' value but there was more than a single value in the array");
      }
      return bool1;
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected Byte _parseByte(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      return Byte.valueOf(paramJsonParser.getByteValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (_hasTextualNull(paramJsonParser)) {
        return (Byte)getNullValue(paramDeserializationContext);
      }
      int i;
      try
      {
        if (paramJsonParser.length() == 0) {
          return (Byte)getEmptyValue(paramDeserializationContext);
        }
        i = NumberInput.parseInt(paramJsonParser);
        if ((i < -128) || (i > 255)) {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "overflow, value can not be represented as 8-bit value");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid Byte value");
      }
      return Byte.valueOf((byte)i);
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NUMBER_FLOAT)
    {
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
        _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "Byte");
      }
      return Byte.valueOf(paramJsonParser.getByteValue());
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Byte)getNullValue(paramDeserializationContext);
    }
    Byte localByte;
    if ((localIllegalArgumentException == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
    {
      paramJsonParser.nextToken();
      localByte = _parseByte(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
      }
      return localByte;
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localByte);
  }
  
  protected Date _parseDate(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject = paramJsonParser.getCurrentToken();
    if (localObject == JsonToken.VALUE_NUMBER_INT) {
      return new Date(paramJsonParser.getLongValue());
    }
    if (localObject == JsonToken.VALUE_NULL) {
      return (Date)getNullValue(paramDeserializationContext);
    }
    if (localObject == JsonToken.VALUE_STRING)
    {
      localObject = null;
      try
      {
        paramJsonParser = paramJsonParser.getText().trim();
        localObject = paramJsonParser;
        if (paramJsonParser.length() == 0)
        {
          localObject = paramJsonParser;
          return (Date)getEmptyValue(paramDeserializationContext);
        }
        localObject = paramJsonParser;
        if (_hasTextualNull(paramJsonParser))
        {
          localObject = paramJsonParser;
          return (Date)getNullValue(paramDeserializationContext);
        }
        localObject = paramJsonParser;
        paramJsonParser = paramDeserializationContext.parseDate(paramJsonParser);
        return paramJsonParser;
      }
      catch (IllegalArgumentException paramJsonParser)
      {
        throw paramDeserializationContext.weirdStringException((String)localObject, this._valueClass, "not a valid representation (error: " + paramJsonParser.getMessage() + ")");
      }
    }
    if ((localObject == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
    {
      paramJsonParser.nextToken();
      localObject = _parseDate(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'java.util.Date' value but there was more than a single value in the array");
      }
      return (Date)localObject;
    }
    throw paramDeserializationContext.mappingException(this._valueClass, (JsonToken)localObject);
  }
  
  protected final Double _parseDouble(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      return Double.valueOf(paramJsonParser.getDoubleValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return (Double)getEmptyValue(paramDeserializationContext);
      }
      if (_hasTextualNull(paramJsonParser)) {
        return (Double)getNullValue(paramDeserializationContext);
      }
      switch (paramJsonParser.charAt(0))
      {
      }
      for (;;)
      {
        try
        {
          double d = parseDouble(paramJsonParser);
          return Double.valueOf(d);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid Double value");
        }
        if (_isPosInf(paramJsonParser))
        {
          return Double.valueOf(Double.POSITIVE_INFINITY);
          if (_isNaN(paramJsonParser))
          {
            return Double.valueOf(NaN.0D);
            if (_isNegInf(paramJsonParser)) {
              return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
          }
        }
      }
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Double)getNullValue(paramDeserializationContext);
    }
    Double localDouble;
    if ((localIllegalArgumentException == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
    {
      paramJsonParser.nextToken();
      localDouble = _parseDouble(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Double' value but there was more than a single value in the array");
      }
      return localDouble;
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localDouble);
  }
  
  protected final double _parseDoublePrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    double d2 = 0.0D;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    double d1;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      d1 = paramJsonParser.getDoubleValue();
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return d1;
            if (localJsonToken != JsonToken.VALUE_STRING) {
              break;
            }
            paramJsonParser = paramJsonParser.getText().trim();
            d1 = d2;
          } while (paramJsonParser.length() == 0);
          d1 = d2;
        } while (_hasTextualNull(paramJsonParser));
        switch (paramJsonParser.charAt(0))
        {
        }
        for (;;)
        {
          try
          {
            d1 = parseDouble(paramJsonParser);
            return d1;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid double value");
          }
          if (_isPosInf(paramJsonParser))
          {
            return Double.POSITIVE_INFINITY;
            if (_isNaN(paramJsonParser))
            {
              return NaN.0D;
              if (_isNegInf(paramJsonParser)) {
                return Double.NEGATIVE_INFINITY;
              }
            }
          }
        }
        d1 = d2;
      } while (localIllegalArgumentException == JsonToken.VALUE_NULL);
      if ((localIllegalArgumentException != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
        break;
      }
      paramJsonParser.nextToken();
      d1 = _parseDoublePrimitive(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
    throw paramDeserializationContext.mappingException(this._valueClass, localIllegalArgumentException);
  }
  
  protected final Float _parseFloat(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      return Float.valueOf(paramJsonParser.getFloatValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return (Float)getEmptyValue(paramDeserializationContext);
      }
      if (_hasTextualNull(paramJsonParser)) {
        return (Float)getNullValue(paramDeserializationContext);
      }
      switch (paramJsonParser.charAt(0))
      {
      }
      for (;;)
      {
        try
        {
          float f = Float.parseFloat(paramJsonParser);
          return Float.valueOf(f);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid Float value");
        }
        if (_isPosInf(paramJsonParser))
        {
          return Float.valueOf(Float.POSITIVE_INFINITY);
          if (_isNaN(paramJsonParser))
          {
            return Float.valueOf(NaN.0F);
            if (_isNegInf(paramJsonParser)) {
              return Float.valueOf(Float.NEGATIVE_INFINITY);
            }
          }
        }
      }
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Float)getNullValue(paramDeserializationContext);
    }
    Float localFloat;
    if ((localIllegalArgumentException == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
    {
      paramJsonParser.nextToken();
      localFloat = _parseFloat(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
      }
      return localFloat;
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localFloat);
  }
  
  protected final float _parseFloatPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    float f2 = 0.0F;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    float f1;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      f1 = paramJsonParser.getFloatValue();
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return f1;
            if (localJsonToken != JsonToken.VALUE_STRING) {
              break;
            }
            paramJsonParser = paramJsonParser.getText().trim();
            f1 = f2;
          } while (paramJsonParser.length() == 0);
          f1 = f2;
        } while (_hasTextualNull(paramJsonParser));
        switch (paramJsonParser.charAt(0))
        {
        }
        for (;;)
        {
          try
          {
            f1 = Float.parseFloat(paramJsonParser);
            return f1;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid float value");
          }
          if (_isPosInf(paramJsonParser))
          {
            return Float.POSITIVE_INFINITY;
            if (_isNaN(paramJsonParser))
            {
              return NaN.0F;
              if (_isNegInf(paramJsonParser)) {
                return Float.NEGATIVE_INFINITY;
              }
            }
          }
        }
        f1 = f2;
      } while (localIllegalArgumentException == JsonToken.VALUE_NULL);
      if ((localIllegalArgumentException != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
        break;
      }
      paramJsonParser.nextToken();
      f1 = _parseFloatPrimitive(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'float' value but there was more than a single value in the array");
    throw paramDeserializationContext.mappingException(this._valueClass, localIllegalArgumentException);
  }
  
  protected final int _parseIntPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    int j = 0;
    int i;
    if (paramJsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
      i = paramJsonParser.getIntValue();
    }
    label169:
    do
    {
      do
      {
        int k;
        do
        {
          do
          {
            return i;
            JsonToken localJsonToken = paramJsonParser.getCurrentToken();
            if (localJsonToken != JsonToken.VALUE_STRING) {
              break;
            }
            paramJsonParser = paramJsonParser.getText().trim();
            i = j;
          } while (_hasTextualNull(paramJsonParser));
          long l;
          try
          {
            k = paramJsonParser.length();
            if (k <= 9) {
              break label169;
            }
            l = Long.parseLong(paramJsonParser);
            if ((l < -2147483648L) || (l > 2147483647L)) {
              throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "Overflow: numeric value (" + paramJsonParser + ") out of range of int (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
            }
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid int value");
          }
          return (int)l;
          i = j;
        } while (k == 0);
        i = NumberInput.parseInt(paramJsonParser);
        return i;
        if (localIllegalArgumentException == JsonToken.VALUE_NUMBER_FLOAT)
        {
          if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
            _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "int");
          }
          return paramJsonParser.getValueAsInt();
        }
        i = j;
      } while (localIllegalArgumentException == JsonToken.VALUE_NULL);
      if ((localIllegalArgumentException != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
        break;
      }
      paramJsonParser.nextToken();
      i = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'int' value but there was more than a single value in the array");
    throw paramDeserializationContext.mappingException(this._valueClass, localIllegalArgumentException);
  }
  
  protected final Integer _parseInteger(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    switch (paramJsonParser.getCurrentTokenId())
    {
    }
    label248:
    do
    {
      throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
      return Integer.valueOf(paramJsonParser.getIntValue());
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
        _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "Integer");
      }
      return Integer.valueOf(paramJsonParser.getValueAsInt());
      paramJsonParser = paramJsonParser.getText().trim();
      long l;
      try
      {
        i = paramJsonParser.length();
        if (_hasTextualNull(paramJsonParser)) {
          return (Integer)getNullValue(paramDeserializationContext);
        }
        if (i <= 9) {
          break label248;
        }
        l = Long.parseLong(paramJsonParser);
        if ((l < -2147483648L) || (l > 2147483647L)) {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "Overflow: numeric value (" + paramJsonParser + ") out of range of Integer (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid Integer value");
      }
      int i = (int)l;
      return Integer.valueOf(i);
      if (i == 0) {
        return (Integer)getEmptyValue(paramDeserializationContext);
      }
      i = NumberInput.parseInt(paramJsonParser);
      return Integer.valueOf(i);
      return (Integer)getNullValue(paramDeserializationContext);
    } while (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS));
    paramJsonParser.nextToken();
    Integer localInteger = _parseInteger(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Integer' value but there was more than a single value in the array");
    }
    return localInteger;
  }
  
  protected final Long _parseLong(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    switch (paramJsonParser.getCurrentTokenId())
    {
    }
    do
    {
      throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
      return Long.valueOf(paramJsonParser.getLongValue());
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
        _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "Long");
      }
      return Long.valueOf(paramJsonParser.getValueAsLong());
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return (Long)getEmptyValue(paramDeserializationContext);
      }
      if (_hasTextualNull(paramJsonParser)) {
        return (Long)getNullValue(paramDeserializationContext);
      }
      try
      {
        long l = NumberInput.parseLong(paramJsonParser);
        return Long.valueOf(l);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid Long value");
      }
      return (Long)getNullValue(paramDeserializationContext);
    } while (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS));
    paramJsonParser.nextToken();
    Long localLong = _parseLong(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Long' value but there was more than a single value in the array");
    }
    return localLong;
  }
  
  protected final long _parseLongPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    long l2 = 0L;
    long l1 = l2;
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 4: 
    case 5: 
    case 9: 
    case 10: 
    default: 
      throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
    case 7: 
      l1 = paramJsonParser.getLongValue();
    }
    do
    {
      do
      {
        do
        {
          return l1;
          if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
            _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "long");
          }
          return paramJsonParser.getValueAsLong();
          paramJsonParser = paramJsonParser.getText().trim();
          l1 = l2;
        } while (paramJsonParser.length() == 0);
        l1 = l2;
      } while (_hasTextualNull(paramJsonParser));
      try
      {
        l1 = NumberInput.parseLong(paramJsonParser);
        return l1;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid long value");
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
        break;
      }
      paramJsonParser.nextToken();
      l1 = _parseLongPrimitive(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'long' value but there was more than a single value in the array");
  }
  
  protected Short _parseShort(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      return Short.valueOf(paramJsonParser.getShortValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      int i;
      try
      {
        if (paramJsonParser.length() == 0) {
          return (Short)getEmptyValue(paramDeserializationContext);
        }
        if (_hasTextualNull(paramJsonParser)) {
          return (Short)getNullValue(paramDeserializationContext);
        }
        i = NumberInput.parseInt(paramJsonParser);
        if ((i < 32768) || (i > 32767)) {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "overflow, value can not be represented as 16-bit value");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, this._valueClass, "not a valid Short value");
      }
      return Short.valueOf((short)i);
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NUMBER_FLOAT)
    {
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
        _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "Short");
      }
      return Short.valueOf(paramJsonParser.getShortValue());
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Short)getNullValue(paramDeserializationContext);
    }
    Short localShort;
    if ((localIllegalArgumentException == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
    {
      paramJsonParser.nextToken();
      localShort = _parseShort(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Short' value but there was more than a single value in the array");
      }
      return localShort;
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localShort);
  }
  
  protected final short _parseShortPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    int i = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
    if ((i < 32768) || (i > 32767)) {
      throw paramDeserializationContext.weirdStringException(String.valueOf(i), this._valueClass, "overflow, value can not be represented as 16-bit value");
    }
    return (short)i;
  }
  
  protected final String _parseString(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject = paramJsonParser.getCurrentToken();
    if (localObject == JsonToken.VALUE_STRING) {
      localObject = paramJsonParser.getText();
    }
    do
    {
      return (String)localObject;
      if ((localObject != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
        break;
      }
      paramJsonParser.nextToken();
      localObject = _parseString(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'String' value but there was more than a single value in the array");
    localObject = paramJsonParser.getValueAsString();
    if (localObject != null) {
      return (String)localObject;
    }
    throw paramDeserializationContext.mappingException(String.class, paramJsonParser.getCurrentToken());
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  protected JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Object localObject1 = paramDeserializationContext.getAnnotationIntrospector();
    if ((localObject1 != null) && (paramBeanProperty != null))
    {
      Object localObject2 = paramBeanProperty.getMember();
      if (localObject2 != null)
      {
        localObject1 = ((AnnotationIntrospector)localObject1).findDeserializationContentConverter((AnnotatedMember)localObject2);
        if (localObject1 != null)
        {
          localObject2 = paramDeserializationContext.converterInstance(paramBeanProperty.getMember(), localObject1);
          JavaType localJavaType = ((Converter)localObject2).getInputType(paramDeserializationContext.getTypeFactory());
          localObject1 = paramJsonDeserializer;
          if (paramJsonDeserializer == null) {
            localObject1 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);
          }
          return new StdDelegatingDeserializer((Converter)localObject2, localJavaType, (JsonDeserializer)localObject1);
        }
      }
    }
    return paramJsonDeserializer;
  }
  
  protected JsonDeserializer<Object> findDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return paramDeserializationContext.findContextualValueDeserializer(paramJavaType, paramBeanProperty);
  }
  
  @Deprecated
  public final Class<?> getValueClass()
  {
    return this._valueClass;
  }
  
  public JavaType getValueType()
  {
    return null;
  }
  
  protected void handleUnknownProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = handledType();
    }
    if (paramDeserializationContext.handleUnknownProperty(paramJsonParser, this, localObject, paramString)) {
      return;
    }
    paramDeserializationContext.reportUnknownProperty(localObject, paramString, this);
    paramJsonParser.skipChildren();
  }
  
  public Class<?> handledType()
  {
    return this._valueClass;
  }
  
  protected boolean isDefaultDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    return ClassUtil.isJacksonStdImpl(paramJsonDeserializer);
  }
  
  protected boolean isDefaultKeyDeserializer(KeyDeserializer paramKeyDeserializer)
  {
    return ClassUtil.isJacksonStdImpl(paramKeyDeserializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/StdDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */