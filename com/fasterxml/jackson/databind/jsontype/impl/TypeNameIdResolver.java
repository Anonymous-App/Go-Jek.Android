package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class TypeNameIdResolver
  extends TypeIdResolverBase
{
  protected final MapperConfig<?> _config;
  protected final HashMap<String, JavaType> _idToType;
  protected final HashMap<String, String> _typeToId;
  
  protected TypeNameIdResolver(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, HashMap<String, String> paramHashMap, HashMap<String, JavaType> paramHashMap1)
  {
    super(paramJavaType, paramMapperConfig.getTypeFactory());
    this._config = paramMapperConfig;
    this._typeToId = paramHashMap;
    this._idToType = paramHashMap1;
  }
  
  protected static String _defaultTypeId(Class<?> paramClass)
  {
    paramClass = paramClass.getName();
    int i = paramClass.lastIndexOf('.');
    if (i < 0) {
      return paramClass;
    }
    return paramClass.substring(i + 1);
  }
  
  public static TypeNameIdResolver construct(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, Collection<NamedType> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1 == paramBoolean2) {
      throw new IllegalArgumentException();
    }
    HashMap localHashMap1 = null;
    HashMap localHashMap2 = null;
    if (paramBoolean1) {
      localHashMap1 = new HashMap();
    }
    if (paramBoolean2) {
      localHashMap2 = new HashMap();
    }
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      if (localIterator.hasNext())
      {
        paramCollection = (NamedType)localIterator.next();
        Class localClass = paramCollection.getType();
        if (paramCollection.hasName()) {}
        for (paramCollection = paramCollection.getName();; paramCollection = _defaultTypeId(localClass))
        {
          if (paramBoolean1) {
            localHashMap1.put(localClass.getName(), paramCollection);
          }
          if (!paramBoolean2) {
            break;
          }
          JavaType localJavaType = (JavaType)localHashMap2.get(paramCollection);
          if ((localJavaType != null) && (localClass.isAssignableFrom(localJavaType.getRawClass()))) {
            break;
          }
          localHashMap2.put(paramCollection, paramMapperConfig.constructType(localClass));
          break;
        }
      }
    }
    return new TypeNameIdResolver(paramMapperConfig, paramJavaType, localHashMap1, localHashMap2);
  }
  
  protected JavaType _typeFromId(String paramString)
  {
    return (JavaType)this._idToType.get(paramString);
  }
  
  public String getDescForKnownTypeIds()
  {
    return new TreeSet(this._idToType.keySet()).toString();
  }
  
  public JsonTypeInfo.Id getMechanism()
  {
    return JsonTypeInfo.Id.NAME;
  }
  
  protected String idFromClass(Class<?> paramClass)
  {
    if (paramClass == null) {
      return null;
    }
    Class localClass = this._typeFactory.constructType(paramClass).getRawClass();
    String str = localClass.getName();
    synchronized (this._typeToId)
    {
      paramClass = (String)this._typeToId.get(str);
      Object localObject = paramClass;
      if (paramClass == null)
      {
        if (this._config.isAnnotationProcessingEnabled())
        {
          paramClass = this._config.introspectClassAnnotations(localClass);
          paramClass = this._config.getAnnotationIntrospector().findTypeName(paramClass.getClassInfo());
        }
        localObject = paramClass;
        if (paramClass == null) {
          localObject = _defaultTypeId(localClass);
        }
        this._typeToId.put(str, localObject);
      }
      return (String)localObject;
    }
  }
  
  public String idFromValue(Object paramObject)
  {
    return idFromClass(paramObject.getClass());
  }
  
  public String idFromValueAndType(Object paramObject, Class<?> paramClass)
  {
    if (paramObject == null) {
      return idFromClass(paramClass);
    }
    return idFromValue(paramObject);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[').append(getClass().getName());
    localStringBuilder.append("; id-to-type=").append(this._idToType);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public JavaType typeFromId(DatabindContext paramDatabindContext, String paramString)
  {
    return _typeFromId(paramString);
  }
  
  @Deprecated
  public JavaType typeFromId(String paramString)
  {
    return _typeFromId(paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/jsontype/impl/TypeNameIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */