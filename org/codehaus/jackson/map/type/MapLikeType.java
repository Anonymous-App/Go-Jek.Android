package org.codehaus.jackson.map.type;

import java.util.Map;
import org.codehaus.jackson.type.JavaType;

public class MapLikeType
  extends TypeBase
{
  protected final JavaType _keyType;
  protected final JavaType _valueType;
  
  @Deprecated
  protected MapLikeType(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    super(paramClass, paramJavaType1.hashCode() ^ paramJavaType2.hashCode(), null, null);
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
  }
  
  protected MapLikeType(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2, Object paramObject1, Object paramObject2)
  {
    super(paramClass, paramJavaType1.hashCode() ^ paramJavaType2.hashCode(), paramObject1, paramObject2);
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
  }
  
  public static MapLikeType construct(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    return new MapLikeType(paramClass, paramJavaType1, paramJavaType2, null, null);
  }
  
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new MapLikeType(paramClass, this._keyType, this._valueType, this._valueHandler, this._typeHandler);
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    if (this._keyType != null)
    {
      localStringBuilder.append('<');
      localStringBuilder.append(this._keyType.toCanonical());
      localStringBuilder.append(',');
      localStringBuilder.append(this._valueType.toCanonical());
      localStringBuilder.append('>');
    }
    return localStringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt)
  {
    if (paramInt == 0) {
      return this._keyType;
    }
    if (paramInt == 1) {
      return this._valueType;
    }
    return null;
  }
  
  public int containedTypeCount()
  {
    return 2;
  }
  
  public String containedTypeName(int paramInt)
  {
    if (paramInt == 0) {
      return "K";
    }
    if (paramInt == 1) {
      return "V";
    }
    return null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (paramObject.getClass() != getClass()) {
        return false;
      }
      paramObject = (MapLikeType)paramObject;
    } while ((this._class == ((MapLikeType)paramObject)._class) && (this._keyType.equals(((MapLikeType)paramObject)._keyType)) && (this._valueType.equals(((MapLikeType)paramObject)._valueType)));
    return false;
  }
  
  public JavaType getContentType()
  {
    return this._valueType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    this._keyType.getGenericSignature(paramStringBuilder);
    this._valueType.getGenericSignature(paramStringBuilder);
    paramStringBuilder.append(">;");
    return paramStringBuilder;
  }
  
  public JavaType getKeyType()
  {
    return this._keyType;
  }
  
  public boolean isContainerType()
  {
    return true;
  }
  
  public boolean isMapLikeType()
  {
    return true;
  }
  
  public boolean isTrueMapType()
  {
    return Map.class.isAssignableFrom(this._class);
  }
  
  public JavaType narrowContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._valueType.getRawClass()) {
      return this;
    }
    return new MapLikeType(this._class, this._keyType, this._valueType.narrowBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public JavaType narrowKey(Class<?> paramClass)
  {
    if (paramClass == this._keyType.getRawClass()) {
      return this;
    }
    return new MapLikeType(this._class, this._keyType.narrowBy(paramClass), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public String toString()
  {
    return "[map-like type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
  }
  
  public JavaType widenContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._valueType.getRawClass()) {
      return this;
    }
    return new MapLikeType(this._class, this._keyType, this._valueType.widenBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public JavaType widenKey(Class<?> paramClass)
  {
    if (paramClass == this._keyType.getRawClass()) {
      return this;
    }
    return new MapLikeType(this._class, this._keyType.widenBy(paramClass), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withContentTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType, this._valueType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withContentValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType, this._valueType.withValueHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withKeyTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType.withTypeHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withKeyValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType.withValueHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType, this._valueType, this._valueHandler, paramObject);
  }
  
  public MapLikeType withValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType, this._valueType, paramObject, this._typeHandler);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/type/MapLikeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */