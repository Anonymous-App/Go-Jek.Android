package org.codehaus.jackson.map.util;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.map.AnnotationIntrospector;

public final class EnumValues
{
  private final EnumMap<?, SerializedString> _values;
  
  private EnumValues(Map<Enum<?>, SerializedString> paramMap)
  {
    this._values = new EnumMap(paramMap);
  }
  
  public static EnumValues construct(Class<Enum<?>> paramClass, AnnotationIntrospector paramAnnotationIntrospector)
  {
    return constructFromName(paramClass, paramAnnotationIntrospector);
  }
  
  public static EnumValues constructFromName(Class<Enum<?>> paramClass, AnnotationIntrospector paramAnnotationIntrospector)
  {
    Enum[] arrayOfEnum = (Enum[])ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (arrayOfEnum != null)
    {
      paramClass = new HashMap();
      int j = arrayOfEnum.length;
      int i = 0;
      while (i < j)
      {
        Enum localEnum = arrayOfEnum[i];
        paramClass.put(localEnum, new SerializedString(paramAnnotationIntrospector.findEnumValue(localEnum)));
        i += 1;
      }
      return new EnumValues(paramClass);
    }
    throw new IllegalArgumentException("Can not determine enum constants for Class " + paramClass.getName());
  }
  
  public static EnumValues constructFromToString(Class<Enum<?>> paramClass, AnnotationIntrospector paramAnnotationIntrospector)
  {
    paramAnnotationIntrospector = (Enum[])ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (paramAnnotationIntrospector != null)
    {
      paramClass = new HashMap();
      int j = paramAnnotationIntrospector.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramAnnotationIntrospector[i];
        paramClass.put(localObject, new SerializedString(((Enum)localObject).toString()));
        i += 1;
      }
      return new EnumValues(paramClass);
    }
    throw new IllegalArgumentException("Can not determine enum constants for Class " + paramClass.getName());
  }
  
  public SerializedString serializedValueFor(Enum<?> paramEnum)
  {
    return (SerializedString)this._values.get(paramEnum);
  }
  
  @Deprecated
  public String valueFor(Enum<?> paramEnum)
  {
    paramEnum = (SerializedString)this._values.get(paramEnum);
    if (paramEnum == null) {
      return null;
    }
    return paramEnum.getValue();
  }
  
  public Collection<SerializedString> values()
  {
    return this._values.values();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/util/EnumValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */