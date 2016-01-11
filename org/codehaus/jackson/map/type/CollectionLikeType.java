package org.codehaus.jackson.map.type;

import java.util.Collection;
import org.codehaus.jackson.type.JavaType;

public class CollectionLikeType
  extends TypeBase
{
  protected final JavaType _elementType;
  
  @Deprecated
  protected CollectionLikeType(Class<?> paramClass, JavaType paramJavaType)
  {
    super(paramClass, paramJavaType.hashCode(), null, null);
    this._elementType = paramJavaType;
  }
  
  protected CollectionLikeType(Class<?> paramClass, JavaType paramJavaType, Object paramObject1, Object paramObject2)
  {
    super(paramClass, paramJavaType.hashCode(), paramObject1, paramObject2);
    this._elementType = paramJavaType;
  }
  
  public static CollectionLikeType construct(Class<?> paramClass, JavaType paramJavaType)
  {
    return new CollectionLikeType(paramClass, paramJavaType, null, null);
  }
  
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new CollectionLikeType(paramClass, this._elementType, this._valueHandler, this._typeHandler);
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    if (this._elementType != null)
    {
      localStringBuilder.append('<');
      localStringBuilder.append(this._elementType.toCanonical());
      localStringBuilder.append('>');
    }
    return localStringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt)
  {
    if (paramInt == 0) {
      return this._elementType;
    }
    return null;
  }
  
  public int containedTypeCount()
  {
    return 1;
  }
  
  public String containedTypeName(int paramInt)
  {
    if (paramInt == 0) {
      return "E";
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
      paramObject = (CollectionLikeType)paramObject;
    } while ((this._class == ((CollectionLikeType)paramObject)._class) && (this._elementType.equals(((CollectionLikeType)paramObject)._elementType)));
    return false;
  }
  
  public JavaType getContentType()
  {
    return this._elementType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    this._elementType.getGenericSignature(paramStringBuilder);
    paramStringBuilder.append(">;");
    return paramStringBuilder;
  }
  
  public boolean isCollectionLikeType()
  {
    return true;
  }
  
  public boolean isContainerType()
  {
    return true;
  }
  
  public boolean isTrueCollectionType()
  {
    return Collection.class.isAssignableFrom(this._class);
  }
  
  public JavaType narrowContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._elementType.getRawClass()) {
      return this;
    }
    return new CollectionLikeType(this._class, this._elementType.narrowBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public String toString()
  {
    return "[collection-like type; class " + this._class.getName() + ", contains " + this._elementType + "]";
  }
  
  public JavaType widenContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._elementType.getRawClass()) {
      return this;
    }
    return new CollectionLikeType(this._class, this._elementType.widenBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public CollectionLikeType withContentTypeHandler(Object paramObject)
  {
    return new CollectionLikeType(this._class, this._elementType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public CollectionLikeType withContentValueHandler(Object paramObject)
  {
    return new CollectionLikeType(this._class, this._elementType.withValueHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public CollectionLikeType withTypeHandler(Object paramObject)
  {
    return new CollectionLikeType(this._class, this._elementType, this._valueHandler, paramObject);
  }
  
  public CollectionLikeType withValueHandler(Object paramObject)
  {
    return new CollectionLikeType(this._class, this._elementType, paramObject, this._typeHandler);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/type/CollectionLikeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */