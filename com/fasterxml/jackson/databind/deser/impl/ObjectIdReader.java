package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.io.Serializable;

public class ObjectIdReader
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final JsonDeserializer<Object> _deserializer;
  protected final JavaType _idType;
  public final ObjectIdGenerator<?> generator;
  public final SettableBeanProperty idProperty;
  public final PropertyName propertyName;
  public final ObjectIdResolver resolver;
  
  @Deprecated
  protected ObjectIdReader(JavaType paramJavaType, PropertyName paramPropertyName, ObjectIdGenerator<?> paramObjectIdGenerator, JsonDeserializer<?> paramJsonDeserializer, SettableBeanProperty paramSettableBeanProperty)
  {
    this(paramJavaType, paramPropertyName, paramObjectIdGenerator, paramJsonDeserializer, paramSettableBeanProperty, new SimpleObjectIdResolver());
  }
  
  protected ObjectIdReader(JavaType paramJavaType, PropertyName paramPropertyName, ObjectIdGenerator<?> paramObjectIdGenerator, JsonDeserializer<?> paramJsonDeserializer, SettableBeanProperty paramSettableBeanProperty, ObjectIdResolver paramObjectIdResolver)
  {
    this._idType = paramJavaType;
    this.propertyName = paramPropertyName;
    this.generator = paramObjectIdGenerator;
    this.resolver = paramObjectIdResolver;
    this._deserializer = paramJsonDeserializer;
    this.idProperty = paramSettableBeanProperty;
  }
  
  @Deprecated
  public static ObjectIdReader construct(JavaType paramJavaType, PropertyName paramPropertyName, ObjectIdGenerator<?> paramObjectIdGenerator, JsonDeserializer<?> paramJsonDeserializer, SettableBeanProperty paramSettableBeanProperty)
  {
    return construct(paramJavaType, paramPropertyName, paramObjectIdGenerator, paramJsonDeserializer, paramSettableBeanProperty, new SimpleObjectIdResolver());
  }
  
  public static ObjectIdReader construct(JavaType paramJavaType, PropertyName paramPropertyName, ObjectIdGenerator<?> paramObjectIdGenerator, JsonDeserializer<?> paramJsonDeserializer, SettableBeanProperty paramSettableBeanProperty, ObjectIdResolver paramObjectIdResolver)
  {
    return new ObjectIdReader(paramJavaType, paramPropertyName, paramObjectIdGenerator, paramJsonDeserializer, paramSettableBeanProperty, paramObjectIdResolver);
  }
  
  public JsonDeserializer<Object> getDeserializer()
  {
    return this._deserializer;
  }
  
  public JavaType getIdType()
  {
    return this._idType;
  }
  
  public boolean isValidReferencePropertyName(String paramString, JsonParser paramJsonParser)
  {
    return this.generator.isValidReferencePropertyName(paramString, paramJsonParser);
  }
  
  public boolean maySerializeAsObject()
  {
    return this.generator.maySerializeAsObject();
  }
  
  public Object readObjectReference(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return this._deserializer.deserialize(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/impl/ObjectIdReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */