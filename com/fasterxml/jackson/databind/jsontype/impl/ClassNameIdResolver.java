package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.util.EnumMap;
import java.util.EnumSet;

public class ClassNameIdResolver
  extends TypeIdResolverBase
{
  public ClassNameIdResolver(JavaType paramJavaType, TypeFactory paramTypeFactory)
  {
    super(paramJavaType, paramTypeFactory);
  }
  
  protected final String _idFrom(Object paramObject, Class<?> paramClass)
  {
    Object localObject = paramClass;
    if (Enum.class.isAssignableFrom(paramClass))
    {
      localObject = paramClass;
      if (!paramClass.isEnum()) {
        localObject = paramClass.getSuperclass();
      }
    }
    paramClass = ((Class)localObject).getName();
    if (paramClass.startsWith("java.util")) {
      if ((paramObject instanceof EnumSet))
      {
        paramObject = ClassUtil.findEnumType((EnumSet)paramObject);
        paramObject = TypeFactory.defaultInstance().constructCollectionType(EnumSet.class, (Class)paramObject).toCanonical();
      }
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
              return (String)paramObject;
              if ((paramObject instanceof EnumMap))
              {
                paramObject = ClassUtil.findEnumType((EnumMap)paramObject);
                return TypeFactory.defaultInstance().constructMapType(EnumMap.class, (Class)paramObject, Object.class).toCanonical();
              }
              localObject = paramClass.substring(9);
              if (((String)localObject).startsWith(".Arrays$")) {
                break;
              }
              paramObject = paramClass;
            } while (!((String)localObject).startsWith(".Collections$"));
            paramObject = paramClass;
          } while (paramClass.indexOf("List") < 0);
          return "java.util.ArrayList";
          paramObject = paramClass;
        } while (paramClass.indexOf('$') < 0);
        paramObject = paramClass;
      } while (ClassUtil.getOuterClass((Class)localObject) == null);
      paramObject = paramClass;
    } while (ClassUtil.getOuterClass(this._baseType.getRawClass()) != null);
    return this._baseType.getRawClass().getName();
  }
  
  protected JavaType _typeFromId(String paramString, TypeFactory paramTypeFactory)
  {
    if (paramString.indexOf('<') > 0) {
      return paramTypeFactory.constructFromCanonical(paramString);
    }
    try
    {
      Class localClass = paramTypeFactory.findClass(paramString);
      paramTypeFactory = paramTypeFactory.constructSpecializedType(this._baseType, localClass);
      return paramTypeFactory;
    }
    catch (ClassNotFoundException paramTypeFactory)
    {
      throw new IllegalArgumentException("Invalid type id '" + paramString + "' (for id type 'Id.class'): no such class found");
    }
    catch (Exception paramTypeFactory)
    {
      throw new IllegalArgumentException("Invalid type id '" + paramString + "' (for id type 'Id.class'): " + paramTypeFactory.getMessage(), paramTypeFactory);
    }
  }
  
  public String getDescForKnownTypeIds()
  {
    return "class name used as type id";
  }
  
  public JsonTypeInfo.Id getMechanism()
  {
    return JsonTypeInfo.Id.CLASS;
  }
  
  public String idFromValue(Object paramObject)
  {
    return _idFrom(paramObject, paramObject.getClass());
  }
  
  public String idFromValueAndType(Object paramObject, Class<?> paramClass)
  {
    return _idFrom(paramObject, paramClass);
  }
  
  public void registerSubtype(Class<?> paramClass, String paramString) {}
  
  public JavaType typeFromId(DatabindContext paramDatabindContext, String paramString)
  {
    return _typeFromId(paramString, paramDatabindContext.getTypeFactory());
  }
  
  @Deprecated
  public JavaType typeFromId(String paramString)
  {
    return _typeFromId(paramString, this._typeFactory);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/jsontype/impl/ClassNameIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */