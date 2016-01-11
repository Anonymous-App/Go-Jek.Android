package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class ContainerDeserializerBase<T>
  extends StdDeserializer<T>
{
  protected ContainerDeserializerBase(JavaType paramJavaType)
  {
    super(paramJavaType);
  }
  
  @Deprecated
  protected ContainerDeserializerBase(Class<?> paramClass)
  {
    super(paramClass);
  }
  
  public SettableBeanProperty findBackReference(String paramString)
  {
    JsonDeserializer localJsonDeserializer = getContentDeserializer();
    if (localJsonDeserializer == null) {
      throw new IllegalArgumentException("Can not handle managed/back reference '" + paramString + "': type: container deserializer of type " + getClass().getName() + " returned null for 'getContentDeserializer()'");
    }
    return localJsonDeserializer.findBackReference(paramString);
  }
  
  public abstract JsonDeserializer<Object> getContentDeserializer();
  
  public abstract JavaType getContentType();
  
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if (((paramThrowable instanceof IOException)) && (!(paramThrowable instanceof JsonMappingException))) {
      throw ((IOException)paramThrowable);
    }
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/ContainerDeserializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */