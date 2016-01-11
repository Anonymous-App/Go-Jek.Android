package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceSerializer
  extends StdSerializer<AtomicReference<?>>
{
  private static final long serialVersionUID = 1L;
  
  @Deprecated
  public AtomicReferenceSerializer()
  {
    super(AtomicReference.class, false);
  }
  
  public AtomicReferenceSerializer(ReferenceType paramReferenceType)
  {
    super(paramReferenceType);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("any", true);
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, AtomicReference<?> paramAtomicReference)
  {
    return (paramAtomicReference == null) || (paramAtomicReference.get() == null);
  }
  
  public void serialize(AtomicReference<?> paramAtomicReference, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramSerializerProvider.defaultSerializeValue(paramAtomicReference.get(), paramJsonGenerator);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/AtomicReferenceSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */