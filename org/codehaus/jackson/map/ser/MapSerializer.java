package org.codehaus.jackson.map.ser;

import java.util.HashSet;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.type.JavaType;

@Deprecated
@JacksonStdImpl
public class MapSerializer
  extends org.codehaus.jackson.map.ser.std.MapSerializer
{
  protected MapSerializer()
  {
    this((HashSet)null, null, null, false, null, null, null, null);
  }
  
  @Deprecated
  protected MapSerializer(HashSet<String> paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer, BeanProperty paramBeanProperty)
  {
    super(paramHashSet, paramJavaType1, paramJavaType2, paramBoolean, paramTypeSerializer, paramJsonSerializer, null, paramBeanProperty);
  }
  
  protected MapSerializer(HashSet<String> paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2, BeanProperty paramBeanProperty)
  {
    super(paramHashSet, paramJavaType1, paramJavaType2, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2, paramBeanProperty);
  }
  
  @Deprecated
  protected MapSerializer(HashSet<String> paramHashSet, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer)
  {
    super(paramHashSet, UNSPECIFIED_TYPE, paramJavaType, paramBoolean, paramTypeSerializer, null, null, null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/MapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */