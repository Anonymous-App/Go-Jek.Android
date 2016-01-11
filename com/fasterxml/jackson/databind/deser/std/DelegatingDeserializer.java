package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.Collection;

public abstract class DelegatingDeserializer
  extends StdDeserializer<Object>
  implements ContextualDeserializer, ResolvableDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final JsonDeserializer<?> _delegatee;
  
  public DelegatingDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    super(_figureType(paramJsonDeserializer));
    this._delegatee = paramJsonDeserializer;
  }
  
  private static Class<?> _figureType(JsonDeserializer<?> paramJsonDeserializer)
  {
    paramJsonDeserializer = paramJsonDeserializer.handledType();
    if (paramJsonDeserializer != null) {
      return paramJsonDeserializer;
    }
    return Object.class;
  }
  
  @Deprecated
  protected JsonDeserializer<?> _createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty, JsonDeserializer<?> paramJsonDeserializer)
  {
    if (paramJsonDeserializer == this._delegatee) {
      return this;
    }
    return newDelegatingInstance(paramJsonDeserializer);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JavaType localJavaType = paramDeserializationContext.constructType(this._delegatee.handledType());
    paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization(this._delegatee, paramBeanProperty, localJavaType);
    if (paramDeserializationContext == this._delegatee) {
      return this;
    }
    return newDelegatingInstance(paramDeserializationContext);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return this._delegatee.deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    return this._delegatee.deserialize(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return this._delegatee.deserializeWithType(paramJsonParser, paramDeserializationContext, paramTypeDeserializer);
  }
  
  public SettableBeanProperty findBackReference(String paramString)
  {
    return this._delegatee.findBackReference(paramString);
  }
  
  public JsonDeserializer<?> getDelegatee()
  {
    return this._delegatee;
  }
  
  @Deprecated
  public Object getEmptyValue()
  {
    return this._delegatee.getEmptyValue();
  }
  
  public Object getEmptyValue(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    return this._delegatee.getEmptyValue(paramDeserializationContext);
  }
  
  public Collection<Object> getKnownPropertyNames()
  {
    return this._delegatee.getKnownPropertyNames();
  }
  
  @Deprecated
  public Object getNullValue()
  {
    return this._delegatee.getNullValue();
  }
  
  public Object getNullValue(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    return this._delegatee.getNullValue(paramDeserializationContext);
  }
  
  public ObjectIdReader getObjectIdReader()
  {
    return this._delegatee.getObjectIdReader();
  }
  
  public boolean isCachable()
  {
    return this._delegatee.isCachable();
  }
  
  protected abstract JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> paramJsonDeserializer);
  
  public JsonDeserializer<?> replaceDelegatee(JsonDeserializer<?> paramJsonDeserializer)
  {
    if (paramJsonDeserializer == this._delegatee) {
      return this;
    }
    return newDelegatingInstance(paramJsonDeserializer);
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    if ((this._delegatee instanceof ResolvableDeserializer)) {
      ((ResolvableDeserializer)this._delegatee).resolve(paramDeserializationContext);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/DelegatingDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */