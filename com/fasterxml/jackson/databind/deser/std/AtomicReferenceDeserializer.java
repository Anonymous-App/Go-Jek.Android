package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDeserializer
  extends StdDeserializer<AtomicReference<?>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _referencedType;
  protected final JsonDeserializer<?> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public AtomicReferenceDeserializer(JavaType paramJavaType)
  {
    this(paramJavaType, null, null);
  }
  
  public AtomicReferenceDeserializer(JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(AtomicReference.class);
    this._referencedType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer2 = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    JsonDeserializer localJsonDeserializer1 = localJsonDeserializer2;
    if (localJsonDeserializer2 == null) {
      localJsonDeserializer1 = paramDeserializationContext.findContextualValueDeserializer(this._referencedType, paramBeanProperty);
    }
    paramDeserializationContext = localTypeDeserializer;
    if (localTypeDeserializer != null) {
      paramDeserializationContext = localTypeDeserializer.forProperty(paramBeanProperty);
    }
    if ((localJsonDeserializer1 == this._valueDeserializer) && (paramDeserializationContext == this._valueTypeDeserializer)) {
      return this;
    }
    return withResolved(paramDeserializationContext, localJsonDeserializer1);
  }
  
  public AtomicReference<?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._valueTypeDeserializer != null) {
      return new AtomicReference(this._valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._valueTypeDeserializer));
    }
    return new AtomicReference(this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public Object[] deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return (Object[])paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  @Deprecated
  public AtomicReference<?> getNullValue()
  {
    return new AtomicReference();
  }
  
  public AtomicReference<?> getNullValue(DeserializationContext paramDeserializationContext)
  {
    return new AtomicReference();
  }
  
  public AtomicReferenceDeserializer withResolved(TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    return new AtomicReferenceDeserializer(this._referencedType, paramTypeDeserializer, paramJsonDeserializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/AtomicReferenceDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */