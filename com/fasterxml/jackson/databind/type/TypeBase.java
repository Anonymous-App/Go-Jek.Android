package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class TypeBase
  extends JavaType
  implements JsonSerializable
{
  private static final long serialVersionUID = -3581199092426900829L;
  volatile transient String _canonicalName;
  
  @Deprecated
  protected TypeBase(Class<?> paramClass, int paramInt, Object paramObject1, Object paramObject2)
  {
    this(paramClass, paramInt, paramObject1, paramObject2, false);
  }
  
  protected TypeBase(Class<?> paramClass, int paramInt, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramInt, paramObject1, paramObject2, paramBoolean);
  }
  
  protected static StringBuilder _classSignature(Class<?> paramClass, StringBuilder paramStringBuilder, boolean paramBoolean)
  {
    if (paramClass.isPrimitive()) {
      if (paramClass == Boolean.TYPE) {
        paramStringBuilder.append('Z');
      }
    }
    do
    {
      return paramStringBuilder;
      if (paramClass == Byte.TYPE)
      {
        paramStringBuilder.append('B');
        return paramStringBuilder;
      }
      if (paramClass == Short.TYPE)
      {
        paramStringBuilder.append('S');
        return paramStringBuilder;
      }
      if (paramClass == Character.TYPE)
      {
        paramStringBuilder.append('C');
        return paramStringBuilder;
      }
      if (paramClass == Integer.TYPE)
      {
        paramStringBuilder.append('I');
        return paramStringBuilder;
      }
      if (paramClass == Long.TYPE)
      {
        paramStringBuilder.append('J');
        return paramStringBuilder;
      }
      if (paramClass == Float.TYPE)
      {
        paramStringBuilder.append('F');
        return paramStringBuilder;
      }
      if (paramClass == Double.TYPE)
      {
        paramStringBuilder.append('D');
        return paramStringBuilder;
      }
      if (paramClass == Void.TYPE)
      {
        paramStringBuilder.append('V');
        return paramStringBuilder;
      }
      throw new IllegalStateException("Unrecognized primitive type: " + paramClass.getName());
      paramStringBuilder.append('L');
      paramClass = paramClass.getName();
      int i = 0;
      int j = paramClass.length();
      while (i < j)
      {
        char c2 = paramClass.charAt(i);
        char c1 = c2;
        if (c2 == '.') {
          c1 = '/';
        }
        paramStringBuilder.append(c1);
        i += 1;
      }
    } while (!paramBoolean);
    paramStringBuilder.append(';');
    return paramStringBuilder;
  }
  
  protected abstract String buildCanonicalName();
  
  public abstract StringBuilder getErasedSignature(StringBuilder paramStringBuilder);
  
  public abstract StringBuilder getGenericSignature(StringBuilder paramStringBuilder);
  
  public <T> T getTypeHandler()
  {
    return (T)this._typeHandler;
  }
  
  public <T> T getValueHandler()
  {
    return (T)this._valueHandler;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeString(toCanonical());
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonProcessingException
  {
    paramTypeSerializer.writeTypePrefixForScalar(this, paramJsonGenerator);
    serialize(paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(this, paramJsonGenerator);
  }
  
  public String toCanonical()
  {
    String str2 = this._canonicalName;
    String str1 = str2;
    if (str2 == null) {
      str1 = buildCanonicalName();
    }
    return str1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/type/TypeBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */