package org.codehaus.jackson.map.type;

import java.lang.reflect.Array;
import org.codehaus.jackson.type.JavaType;

public final class ArrayType
  extends TypeBase
{
  protected final JavaType _componentType;
  protected final Object _emptyArray;
  
  private ArrayType(JavaType paramJavaType, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    super(paramObject1.getClass(), paramJavaType.hashCode(), paramObject2, paramObject3);
    this._componentType = paramJavaType;
    this._emptyArray = paramObject1;
  }
  
  @Deprecated
  public static ArrayType construct(JavaType paramJavaType)
  {
    return construct(paramJavaType, null, null);
  }
  
  public static ArrayType construct(JavaType paramJavaType, Object paramObject1, Object paramObject2)
  {
    return new ArrayType(paramJavaType, Array.newInstance(paramJavaType.getRawClass(), 0), null, null);
  }
  
  protected JavaType _narrow(Class<?> paramClass)
  {
    if (!paramClass.isArray()) {
      throw new IllegalArgumentException("Incompatible narrowing operation: trying to narrow " + toString() + " to class " + paramClass.getName());
    }
    paramClass = paramClass.getComponentType();
    return construct(TypeFactory.defaultInstance().constructType(paramClass), this._valueHandler, this._typeHandler);
  }
  
  protected String buildCanonicalName()
  {
    return this._class.getName();
  }
  
  public JavaType containedType(int paramInt)
  {
    if (paramInt == 0) {
      return this._componentType;
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
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == null);
      bool1 = bool2;
    } while (paramObject.getClass() != getClass());
    paramObject = (ArrayType)paramObject;
    return this._componentType.equals(((ArrayType)paramObject)._componentType);
  }
  
  public JavaType getContentType()
  {
    return this._componentType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append('[');
    return this._componentType.getErasedSignature(paramStringBuilder);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append('[');
    return this._componentType.getGenericSignature(paramStringBuilder);
  }
  
  public boolean hasGenericTypes()
  {
    return this._componentType.hasGenericTypes();
  }
  
  public boolean isAbstract()
  {
    return false;
  }
  
  public boolean isArrayType()
  {
    return true;
  }
  
  public boolean isConcrete()
  {
    return true;
  }
  
  public boolean isContainerType()
  {
    return true;
  }
  
  public JavaType narrowContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._componentType.getRawClass()) {
      return this;
    }
    return construct(this._componentType.narrowBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public String toString()
  {
    return "[array type, component type: " + this._componentType + "]";
  }
  
  public JavaType widenContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._componentType.getRawClass()) {
      return this;
    }
    return construct(this._componentType.widenBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public ArrayType withContentTypeHandler(Object paramObject)
  {
    if (paramObject == this._componentType.getTypeHandler()) {
      return this;
    }
    return new ArrayType(this._componentType.withTypeHandler(paramObject), this._emptyArray, this._valueHandler, this._typeHandler);
  }
  
  public ArrayType withContentValueHandler(Object paramObject)
  {
    if (paramObject == this._componentType.getValueHandler()) {
      return this;
    }
    return new ArrayType(this._componentType.withValueHandler(paramObject), this._emptyArray, this._valueHandler, this._typeHandler);
  }
  
  public ArrayType withTypeHandler(Object paramObject)
  {
    if (paramObject == this._typeHandler) {
      return this;
    }
    return new ArrayType(this._componentType, this._emptyArray, this._valueHandler, paramObject);
  }
  
  public ArrayType withValueHandler(Object paramObject)
  {
    if (paramObject == this._valueHandler) {
      return this;
    }
    return new ArrayType(this._componentType, this._emptyArray, paramObject, this._typeHandler);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/type/ArrayType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */