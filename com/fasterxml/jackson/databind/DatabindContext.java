package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DatabindContext
{
  public final boolean canOverrideAccessModifiers()
  {
    return getConfig().canOverrideAccessModifiers();
  }
  
  public JavaType constructSpecializedType(JavaType paramJavaType, Class<?> paramClass)
  {
    if (paramJavaType.getRawClass() == paramClass) {
      return paramJavaType;
    }
    return getConfig().constructSpecializedType(paramJavaType, paramClass);
  }
  
  public JavaType constructType(Type paramType)
  {
    return getTypeFactory().constructType(paramType);
  }
  
  public Converter<Object, Object> converterInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    Object localObject = null;
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof Converter)) {
      return (Converter)paramObject;
    }
    if (!(paramObject instanceof Class)) {
      throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + paramObject.getClass().getName() + "; expected type Converter or Class<Converter> instead");
    }
    Class localClass = (Class)paramObject;
    if ((localClass == Converter.None.class) || (ClassUtil.isBogusClass(localClass))) {
      return null;
    }
    if (!Converter.class.isAssignableFrom(localClass)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<Converter>");
    }
    MapperConfig localMapperConfig = getConfig();
    paramObject = localMapperConfig.getHandlerInstantiator();
    if (paramObject == null) {}
    for (paramAnnotated = (Annotated)localObject;; paramAnnotated = ((HandlerInstantiator)paramObject).converterInstance(localMapperConfig, paramAnnotated, localClass))
    {
      paramObject = paramAnnotated;
      if (paramAnnotated == null) {
        paramObject = (Converter)ClassUtil.createInstance(localClass, localMapperConfig.canOverrideAccessModifiers());
      }
      return (Converter<Object, Object>)paramObject;
    }
  }
  
  public abstract Class<?> getActiveView();
  
  public abstract AnnotationIntrospector getAnnotationIntrospector();
  
  public abstract Object getAttribute(Object paramObject);
  
  public abstract MapperConfig<?> getConfig();
  
  public abstract Locale getLocale();
  
  public abstract TimeZone getTimeZone();
  
  public abstract TypeFactory getTypeFactory();
  
  public final boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return getConfig().isEnabled(paramMapperFeature);
  }
  
  public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
    throws JsonMappingException
  {
    Class localClass = paramObjectIdInfo.getGeneratorType();
    MapperConfig localMapperConfig = getConfig();
    Object localObject = localMapperConfig.getHandlerInstantiator();
    if (localObject == null) {}
    for (paramAnnotated = null;; paramAnnotated = ((HandlerInstantiator)localObject).objectIdGeneratorInstance(localMapperConfig, paramAnnotated, localClass))
    {
      localObject = paramAnnotated;
      if (paramAnnotated == null) {
        localObject = (ObjectIdGenerator)ClassUtil.createInstance(localClass, localMapperConfig.canOverrideAccessModifiers());
      }
      return ((ObjectIdGenerator)localObject).forScope(paramObjectIdInfo.getScope());
    }
  }
  
  public ObjectIdResolver objectIdResolverInstance(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
  {
    Class localClass = paramObjectIdInfo.getResolverType();
    MapperConfig localMapperConfig = getConfig();
    paramObjectIdInfo = localMapperConfig.getHandlerInstantiator();
    if (paramObjectIdInfo == null) {}
    for (paramAnnotated = null;; paramAnnotated = paramObjectIdInfo.resolverIdGeneratorInstance(localMapperConfig, paramAnnotated, localClass))
    {
      paramObjectIdInfo = paramAnnotated;
      if (paramAnnotated == null) {
        paramObjectIdInfo = (ObjectIdResolver)ClassUtil.createInstance(localClass, localMapperConfig.canOverrideAccessModifiers());
      }
      return paramObjectIdInfo;
    }
  }
  
  public abstract DatabindContext setAttribute(Object paramObject1, Object paramObject2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/DatabindContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */