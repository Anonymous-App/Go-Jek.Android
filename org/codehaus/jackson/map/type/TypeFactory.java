package org.codehaus.jackson.map.type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

public final class TypeFactory
{
  private static final JavaType[] NO_TYPES = new JavaType[0];
  @Deprecated
  public static final TypeFactory instance = new TypeFactory();
  protected HierarchicType _cachedArrayListType;
  protected HierarchicType _cachedHashMapType;
  protected final TypeModifier[] _modifiers;
  protected final TypeParser _parser;
  
  private TypeFactory()
  {
    this._parser = new TypeParser(this);
    this._modifiers = null;
  }
  
  protected TypeFactory(TypeParser paramTypeParser, TypeModifier[] paramArrayOfTypeModifier)
  {
    this._parser = paramTypeParser;
    this._modifiers = paramArrayOfTypeModifier;
  }
  
  private JavaType _collectionType(Class<?> paramClass)
  {
    JavaType[] arrayOfJavaType = findTypeParameters(paramClass, Collection.class);
    if (arrayOfJavaType == null) {
      return CollectionType.construct(paramClass, _unknownType());
    }
    if (arrayOfJavaType.length != 1) {
      throw new IllegalArgumentException("Strange Collection type " + paramClass.getName() + ": can not determine type parameters");
    }
    return CollectionType.construct(paramClass, arrayOfJavaType[0]);
  }
  
  private JavaType _mapType(Class<?> paramClass)
  {
    JavaType[] arrayOfJavaType = findTypeParameters(paramClass, Map.class);
    if (arrayOfJavaType == null) {
      return MapType.construct(paramClass, _unknownType(), _unknownType());
    }
    if (arrayOfJavaType.length != 2) {
      throw new IllegalArgumentException("Strange Map type " + paramClass.getName() + ": can not determine type parameters");
    }
    return MapType.construct(paramClass, arrayOfJavaType[0], arrayOfJavaType[1]);
  }
  
  @Deprecated
  public static JavaType arrayType(Class<?> paramClass)
  {
    return instance.constructArrayType(instance.constructType(paramClass));
  }
  
  @Deprecated
  public static JavaType arrayType(JavaType paramJavaType)
  {
    return instance.constructArrayType(paramJavaType);
  }
  
  @Deprecated
  public static JavaType collectionType(Class<? extends Collection> paramClass, Class<?> paramClass1)
  {
    return instance.constructCollectionType(paramClass, instance.constructType(paramClass1));
  }
  
  @Deprecated
  public static JavaType collectionType(Class<? extends Collection> paramClass, JavaType paramJavaType)
  {
    return instance.constructCollectionType(paramClass, paramJavaType);
  }
  
  public static TypeFactory defaultInstance()
  {
    return instance;
  }
  
  @Deprecated
  public static JavaType fastSimpleType(Class<?> paramClass)
  {
    return instance.uncheckedSimpleType(paramClass);
  }
  
  @Deprecated
  public static JavaType[] findParameterTypes(Class<?> paramClass1, Class<?> paramClass2)
  {
    return instance.findTypeParameters(paramClass1, paramClass2);
  }
  
  @Deprecated
  public static JavaType[] findParameterTypes(Class<?> paramClass1, Class<?> paramClass2, TypeBindings paramTypeBindings)
  {
    return instance.findTypeParameters(paramClass1, paramClass2, paramTypeBindings);
  }
  
  @Deprecated
  public static JavaType[] findParameterTypes(JavaType paramJavaType, Class<?> paramClass)
  {
    return instance.findTypeParameters(paramJavaType, paramClass);
  }
  
  public static JavaType fromCanonical(String paramString)
    throws IllegalArgumentException
  {
    return instance.constructFromCanonical(paramString);
  }
  
  @Deprecated
  public static JavaType fromClass(Class<?> paramClass)
  {
    return instance._fromClass(paramClass, null);
  }
  
  @Deprecated
  public static JavaType fromType(Type paramType)
  {
    return instance._constructType(paramType, null);
  }
  
  @Deprecated
  public static JavaType fromTypeReference(TypeReference<?> paramTypeReference)
  {
    return type(paramTypeReference.getType());
  }
  
  @Deprecated
  public static JavaType mapType(Class<? extends Map> paramClass, Class<?> paramClass1, Class<?> paramClass2)
  {
    return instance.constructMapType(paramClass, type(paramClass1), instance.constructType(paramClass2));
  }
  
  @Deprecated
  public static JavaType mapType(Class<? extends Map> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    return instance.constructMapType(paramClass, paramJavaType1, paramJavaType2);
  }
  
  @Deprecated
  public static JavaType parametricType(Class<?> paramClass, Class<?>... paramVarArgs)
  {
    return instance.constructParametricType(paramClass, paramVarArgs);
  }
  
  @Deprecated
  public static JavaType parametricType(Class<?> paramClass, JavaType... paramVarArgs)
  {
    return instance.constructParametricType(paramClass, paramVarArgs);
  }
  
  public static Class<?> rawClass(Type paramType)
  {
    if ((paramType instanceof Class)) {
      return (Class)paramType;
    }
    return defaultInstance().constructType(paramType).getRawClass();
  }
  
  @Deprecated
  public static JavaType specialize(JavaType paramJavaType, Class<?> paramClass)
  {
    return instance.constructSpecializedType(paramJavaType, paramClass);
  }
  
  @Deprecated
  public static JavaType type(Type paramType)
  {
    return instance._constructType(paramType, null);
  }
  
  @Deprecated
  public static JavaType type(Type paramType, Class<?> paramClass)
  {
    return instance.constructType(paramType, paramClass);
  }
  
  @Deprecated
  public static JavaType type(Type paramType, TypeBindings paramTypeBindings)
  {
    return instance._constructType(paramType, paramTypeBindings);
  }
  
  @Deprecated
  public static JavaType type(Type paramType, JavaType paramJavaType)
  {
    return instance.constructType(paramType, paramJavaType);
  }
  
  @Deprecated
  public static JavaType type(TypeReference<?> paramTypeReference)
  {
    return instance.constructType(paramTypeReference.getType());
  }
  
  public static JavaType unknownType()
  {
    return defaultInstance()._unknownType();
  }
  
  protected HierarchicType _arrayListSuperInterfaceChain(HierarchicType paramHierarchicType)
  {
    try
    {
      if (this._cachedArrayListType == null)
      {
        localHierarchicType = paramHierarchicType.deepCloneWithoutSubtype();
        _doFindSuperInterfaceChain(localHierarchicType, List.class);
        this._cachedArrayListType = localHierarchicType.getSuperType();
      }
      HierarchicType localHierarchicType = this._cachedArrayListType.deepCloneWithoutSubtype();
      paramHierarchicType.setSuperType(localHierarchicType);
      localHierarchicType.setSubType(paramHierarchicType);
      return paramHierarchicType;
    }
    finally {}
  }
  
  public JavaType _constructType(Type paramType, TypeBindings paramTypeBindings)
  {
    Object localObject2;
    Object localObject1;
    if ((paramType instanceof Class))
    {
      localObject2 = (Class)paramType;
      localObject1 = paramTypeBindings;
      if (paramTypeBindings == null) {
        localObject1 = new TypeBindings(this, (Class)localObject2);
      }
      localObject2 = _fromClass((Class)localObject2, (TypeBindings)localObject1);
      paramTypeBindings = (TypeBindings)localObject1;
      localObject1 = localObject2;
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (this._modifiers == null) {
        break label231;
      }
      localObject2 = localObject1;
      if (((JavaType)localObject1).isContainerType()) {
        break label231;
      }
      TypeModifier[] arrayOfTypeModifier = this._modifiers;
      int j = arrayOfTypeModifier.length;
      int i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= j) {
          break;
        }
        localObject1 = arrayOfTypeModifier[i].modifyType((JavaType)localObject1, paramType, paramTypeBindings, this);
        i += 1;
      }
      if ((paramType instanceof ParameterizedType))
      {
        localObject1 = _fromParamType((ParameterizedType)paramType, paramTypeBindings);
      }
      else if ((paramType instanceof GenericArrayType))
      {
        localObject1 = _fromArrayType((GenericArrayType)paramType, paramTypeBindings);
      }
      else if ((paramType instanceof TypeVariable))
      {
        localObject1 = _fromVariable((TypeVariable)paramType, paramTypeBindings);
      }
      else
      {
        if (!(paramType instanceof WildcardType)) {
          break;
        }
        localObject1 = _fromWildcard((WildcardType)paramType, paramTypeBindings);
      }
    }
    throw new IllegalArgumentException("Unrecognized Type: " + paramType.toString());
    label231:
    return (JavaType)localObject2;
  }
  
  protected HierarchicType _doFindSuperInterfaceChain(HierarchicType paramHierarchicType, Class<?> paramClass)
  {
    Object localObject = paramHierarchicType.getRawClass();
    Type[] arrayOfType = ((Class)localObject).getGenericInterfaces();
    if (arrayOfType != null)
    {
      int j = arrayOfType.length;
      int i = 0;
      while (i < j)
      {
        HierarchicType localHierarchicType = _findSuperInterfaceChain(arrayOfType[i], paramClass);
        if (localHierarchicType != null)
        {
          localHierarchicType.setSubType(paramHierarchicType);
          paramHierarchicType.setSuperType(localHierarchicType);
          return paramHierarchicType;
        }
        i += 1;
      }
    }
    localObject = ((Class)localObject).getGenericSuperclass();
    if (localObject != null)
    {
      paramClass = _findSuperInterfaceChain((Type)localObject, paramClass);
      if (paramClass != null)
      {
        paramClass.setSubType(paramHierarchicType);
        paramHierarchicType.setSuperType(paramClass);
        return paramHierarchicType;
      }
    }
    return null;
  }
  
  protected HierarchicType _findSuperClassChain(Type paramType, Class<?> paramClass)
  {
    paramType = new HierarchicType(paramType);
    Object localObject = paramType.getRawClass();
    if (localObject == paramClass) {
      return paramType;
    }
    localObject = ((Class)localObject).getGenericSuperclass();
    if (localObject != null)
    {
      paramClass = _findSuperClassChain((Type)localObject, paramClass);
      if (paramClass != null)
      {
        paramClass.setSubType(paramType);
        paramType.setSuperType(paramClass);
        return paramType;
      }
    }
    return null;
  }
  
  protected HierarchicType _findSuperInterfaceChain(Type paramType, Class<?> paramClass)
  {
    HierarchicType localHierarchicType = new HierarchicType(paramType);
    Class localClass = localHierarchicType.getRawClass();
    if (localClass == paramClass) {
      return new HierarchicType(paramType);
    }
    if ((localClass == HashMap.class) && (paramClass == Map.class)) {
      return _hashMapSuperInterfaceChain(localHierarchicType);
    }
    if ((localClass == ArrayList.class) && (paramClass == List.class)) {
      return _arrayListSuperInterfaceChain(localHierarchicType);
    }
    return _doFindSuperInterfaceChain(localHierarchicType, paramClass);
  }
  
  protected HierarchicType _findSuperTypeChain(Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2.isInterface()) {
      return _findSuperInterfaceChain(paramClass1, paramClass2);
    }
    return _findSuperClassChain(paramClass1, paramClass2);
  }
  
  protected JavaType _fromArrayType(GenericArrayType paramGenericArrayType, TypeBindings paramTypeBindings)
  {
    return ArrayType.construct(_constructType(paramGenericArrayType.getGenericComponentType(), paramTypeBindings), null, null);
  }
  
  protected JavaType _fromClass(Class<?> paramClass, TypeBindings paramTypeBindings)
  {
    if (paramClass.isArray()) {
      return ArrayType.construct(_constructType(paramClass.getComponentType(), null), null, null);
    }
    if (paramClass.isEnum()) {
      return new SimpleType(paramClass);
    }
    if (Map.class.isAssignableFrom(paramClass)) {
      return _mapType(paramClass);
    }
    if (Collection.class.isAssignableFrom(paramClass)) {
      return _collectionType(paramClass);
    }
    return new SimpleType(paramClass);
  }
  
  protected JavaType _fromParamType(ParameterizedType paramParameterizedType, TypeBindings paramTypeBindings)
  {
    Class localClass = (Class)paramParameterizedType.getRawType();
    Type[] arrayOfType = paramParameterizedType.getActualTypeArguments();
    if (arrayOfType == null) {}
    for (int i = 0; i == 0; i = arrayOfType.length)
    {
      paramParameterizedType = NO_TYPES;
      if (!Map.class.isAssignableFrom(localClass)) {
        break label174;
      }
      paramParameterizedType = findTypeParameters(constructSimpleType(localClass, paramParameterizedType), Map.class);
      if (paramParameterizedType.length == 2) {
        break label162;
      }
      throw new IllegalArgumentException("Could not find 2 type parameters for Map class " + localClass.getName() + " (found " + paramParameterizedType.length + ")");
    }
    JavaType[] arrayOfJavaType = new JavaType[i];
    int j = 0;
    for (;;)
    {
      paramParameterizedType = arrayOfJavaType;
      if (j >= i) {
        break;
      }
      arrayOfJavaType[j] = _constructType(arrayOfType[j], paramTypeBindings);
      j += 1;
    }
    label162:
    return MapType.construct(localClass, paramParameterizedType[0], paramParameterizedType[1]);
    label174:
    if (Collection.class.isAssignableFrom(localClass))
    {
      paramParameterizedType = findTypeParameters(constructSimpleType(localClass, paramParameterizedType), Collection.class);
      if (paramParameterizedType.length != 1) {
        throw new IllegalArgumentException("Could not find 1 type parameter for Collection class " + localClass.getName() + " (found " + paramParameterizedType.length + ")");
      }
      return CollectionType.construct(localClass, paramParameterizedType[0]);
    }
    if (i == 0) {
      return new SimpleType(localClass);
    }
    return constructSimpleType(localClass, paramParameterizedType);
  }
  
  protected JavaType _fromParameterizedClass(Class<?> paramClass, List<JavaType> paramList)
  {
    if (paramClass.isArray()) {
      return ArrayType.construct(_constructType(paramClass.getComponentType(), null), null, null);
    }
    if (paramClass.isEnum()) {
      return new SimpleType(paramClass);
    }
    if (Map.class.isAssignableFrom(paramClass))
    {
      if (paramList.size() > 0)
      {
        JavaType localJavaType = (JavaType)paramList.get(0);
        if (paramList.size() >= 2) {}
        for (paramList = (JavaType)paramList.get(1);; paramList = _unknownType()) {
          return MapType.construct(paramClass, localJavaType, paramList);
        }
      }
      return _mapType(paramClass);
    }
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (paramList.size() >= 1) {
        return CollectionType.construct(paramClass, (JavaType)paramList.get(0));
      }
      return _collectionType(paramClass);
    }
    if (paramList.size() == 0) {
      return new SimpleType(paramClass);
    }
    return constructSimpleType(paramClass, (JavaType[])paramList.toArray(new JavaType[paramList.size()]));
  }
  
  protected JavaType _fromVariable(TypeVariable<?> paramTypeVariable, TypeBindings paramTypeBindings)
  {
    Object localObject;
    if (paramTypeBindings == null) {
      localObject = _unknownType();
    }
    String str;
    JavaType localJavaType;
    do
    {
      return (JavaType)localObject;
      str = paramTypeVariable.getName();
      localJavaType = paramTypeBindings.findType(str);
      localObject = localJavaType;
    } while (localJavaType != null);
    paramTypeVariable = paramTypeVariable.getBounds();
    paramTypeBindings._addPlaceholder(str);
    return _constructType(paramTypeVariable[0], paramTypeBindings);
  }
  
  protected JavaType _fromWildcard(WildcardType paramWildcardType, TypeBindings paramTypeBindings)
  {
    return _constructType(paramWildcardType.getUpperBounds()[0], paramTypeBindings);
  }
  
  protected HierarchicType _hashMapSuperInterfaceChain(HierarchicType paramHierarchicType)
  {
    try
    {
      if (this._cachedHashMapType == null)
      {
        localHierarchicType = paramHierarchicType.deepCloneWithoutSubtype();
        _doFindSuperInterfaceChain(localHierarchicType, Map.class);
        this._cachedHashMapType = localHierarchicType.getSuperType();
      }
      HierarchicType localHierarchicType = this._cachedHashMapType.deepCloneWithoutSubtype();
      paramHierarchicType.setSuperType(localHierarchicType);
      localHierarchicType.setSubType(paramHierarchicType);
      return paramHierarchicType;
    }
    finally {}
  }
  
  protected JavaType _resolveVariableViaSubTypes(HierarchicType paramHierarchicType, String paramString, TypeBindings paramTypeBindings)
  {
    if ((paramHierarchicType != null) && (paramHierarchicType.isGeneric()))
    {
      TypeVariable[] arrayOfTypeVariable = paramHierarchicType.getRawClass().getTypeParameters();
      int i = 0;
      int j = arrayOfTypeVariable.length;
      while (i < j)
      {
        if (paramString.equals(arrayOfTypeVariable[i].getName()))
        {
          paramString = paramHierarchicType.asGeneric().getActualTypeArguments()[i];
          if ((paramString instanceof TypeVariable)) {
            return _resolveVariableViaSubTypes(paramHierarchicType.getSubType(), ((TypeVariable)paramString).getName(), paramTypeBindings);
          }
          return _constructType(paramString, paramTypeBindings);
        }
        i += 1;
      }
    }
    return _unknownType();
  }
  
  protected JavaType _unknownType()
  {
    return new SimpleType(Object.class);
  }
  
  public ArrayType constructArrayType(Class<?> paramClass)
  {
    return ArrayType.construct(_constructType(paramClass, null), null, null);
  }
  
  public ArrayType constructArrayType(JavaType paramJavaType)
  {
    return ArrayType.construct(paramJavaType, null, null);
  }
  
  public CollectionLikeType constructCollectionLikeType(Class<?> paramClass1, Class<?> paramClass2)
  {
    return CollectionLikeType.construct(paramClass1, constructType(paramClass2));
  }
  
  public CollectionLikeType constructCollectionLikeType(Class<?> paramClass, JavaType paramJavaType)
  {
    return CollectionLikeType.construct(paramClass, paramJavaType);
  }
  
  public CollectionType constructCollectionType(Class<? extends Collection> paramClass, Class<?> paramClass1)
  {
    return CollectionType.construct(paramClass, constructType(paramClass1));
  }
  
  public CollectionType constructCollectionType(Class<? extends Collection> paramClass, JavaType paramJavaType)
  {
    return CollectionType.construct(paramClass, paramJavaType);
  }
  
  public JavaType constructFromCanonical(String paramString)
    throws IllegalArgumentException
  {
    return this._parser.parse(paramString);
  }
  
  public MapLikeType constructMapLikeType(Class<?> paramClass1, Class<?> paramClass2, Class<?> paramClass3)
  {
    return MapType.construct(paramClass1, constructType(paramClass2), constructType(paramClass3));
  }
  
  public MapLikeType constructMapLikeType(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    return MapLikeType.construct(paramClass, paramJavaType1, paramJavaType2);
  }
  
  public MapType constructMapType(Class<? extends Map> paramClass, Class<?> paramClass1, Class<?> paramClass2)
  {
    return MapType.construct(paramClass, constructType(paramClass1), constructType(paramClass2));
  }
  
  public MapType constructMapType(Class<? extends Map> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    return MapType.construct(paramClass, paramJavaType1, paramJavaType2);
  }
  
  public JavaType constructParametricType(Class<?> paramClass, Class<?>... paramVarArgs)
  {
    int j = paramVarArgs.length;
    JavaType[] arrayOfJavaType = new JavaType[j];
    int i = 0;
    while (i < j)
    {
      arrayOfJavaType[i] = _fromClass(paramVarArgs[i], null);
      i += 1;
    }
    return constructParametricType(paramClass, arrayOfJavaType);
  }
  
  public JavaType constructParametricType(Class<?> paramClass, JavaType... paramVarArgs)
  {
    if (paramClass.isArray())
    {
      if (paramVarArgs.length != 1) {
        throw new IllegalArgumentException("Need exactly 1 parameter type for arrays (" + paramClass.getName() + ")");
      }
      return constructArrayType(paramVarArgs[0]);
    }
    if (Map.class.isAssignableFrom(paramClass))
    {
      if (paramVarArgs.length != 2) {
        throw new IllegalArgumentException("Need exactly 2 parameter types for Map types (" + paramClass.getName() + ")");
      }
      return constructMapType(paramClass, paramVarArgs[0], paramVarArgs[1]);
    }
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (paramVarArgs.length != 1) {
        throw new IllegalArgumentException("Need exactly 1 parameter type for Collection types (" + paramClass.getName() + ")");
      }
      return constructCollectionType(paramClass, paramVarArgs[0]);
    }
    return constructSimpleType(paramClass, paramVarArgs);
  }
  
  public CollectionLikeType constructRawCollectionLikeType(Class<?> paramClass)
  {
    return CollectionLikeType.construct(paramClass, unknownType());
  }
  
  public CollectionType constructRawCollectionType(Class<? extends Collection> paramClass)
  {
    return CollectionType.construct(paramClass, unknownType());
  }
  
  public MapLikeType constructRawMapLikeType(Class<?> paramClass)
  {
    return MapLikeType.construct(paramClass, unknownType(), unknownType());
  }
  
  public MapType constructRawMapType(Class<? extends Map> paramClass)
  {
    return MapType.construct(paramClass, unknownType(), unknownType());
  }
  
  public JavaType constructSimpleType(Class<?> paramClass, JavaType[] paramArrayOfJavaType)
  {
    TypeVariable[] arrayOfTypeVariable = paramClass.getTypeParameters();
    if (arrayOfTypeVariable.length != paramArrayOfJavaType.length) {
      throw new IllegalArgumentException("Parameter type mismatch for " + paramClass.getName() + ": expected " + arrayOfTypeVariable.length + " parameters, was given " + paramArrayOfJavaType.length);
    }
    String[] arrayOfString = new String[arrayOfTypeVariable.length];
    int i = 0;
    int j = arrayOfTypeVariable.length;
    while (i < j)
    {
      arrayOfString[i] = arrayOfTypeVariable[i].getName();
      i += 1;
    }
    return new SimpleType(paramClass, arrayOfString, paramArrayOfJavaType, null, null);
  }
  
  public JavaType constructSpecializedType(JavaType paramJavaType, Class<?> paramClass)
  {
    if (((paramJavaType instanceof SimpleType)) && ((paramClass.isArray()) || (Map.class.isAssignableFrom(paramClass)) || (Collection.class.isAssignableFrom(paramClass))))
    {
      if (!paramJavaType.getRawClass().isAssignableFrom(paramClass)) {
        throw new IllegalArgumentException("Class " + paramClass.getClass().getName() + " not subtype of " + paramJavaType);
      }
      Object localObject1 = _fromClass(paramClass, new TypeBindings(this, paramJavaType.getRawClass()));
      Object localObject2 = paramJavaType.getValueHandler();
      paramClass = (Class<?>)localObject1;
      if (localObject2 != null) {
        paramClass = ((JavaType)localObject1).withValueHandler(localObject2);
      }
      localObject1 = paramJavaType.getTypeHandler();
      paramJavaType = paramClass;
      if (localObject1 != null) {
        paramJavaType = paramClass.withTypeHandler(localObject1);
      }
      return paramJavaType;
    }
    return paramJavaType.narrowBy(paramClass);
  }
  
  public JavaType constructType(Type paramType)
  {
    return _constructType(paramType, null);
  }
  
  public JavaType constructType(Type paramType, Class<?> paramClass)
  {
    if (paramClass == null) {}
    for (paramClass = null;; paramClass = new TypeBindings(this, paramClass)) {
      return _constructType(paramType, paramClass);
    }
  }
  
  public JavaType constructType(Type paramType, TypeBindings paramTypeBindings)
  {
    return _constructType(paramType, paramTypeBindings);
  }
  
  public JavaType constructType(Type paramType, JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (paramJavaType = null;; paramJavaType = new TypeBindings(this, paramJavaType)) {
      return _constructType(paramType, paramJavaType);
    }
  }
  
  public JavaType constructType(TypeReference<?> paramTypeReference)
  {
    return _constructType(paramTypeReference.getType(), null);
  }
  
  public JavaType[] findTypeParameters(Class<?> paramClass1, Class<?> paramClass2)
  {
    return findTypeParameters(paramClass1, paramClass2, new TypeBindings(this, paramClass1));
  }
  
  public JavaType[] findTypeParameters(Class<?> paramClass1, Class<?> paramClass2, TypeBindings paramTypeBindings)
  {
    Object localObject1 = _findSuperTypeChain(paramClass1, paramClass2);
    if (localObject1 == null) {
      throw new IllegalArgumentException("Class " + paramClass1.getName() + " is not a subtype of " + paramClass2.getName());
    }
    paramClass1 = (Class<?>)localObject1;
    while (paramClass1.getSuperType() != null)
    {
      paramClass1 = paramClass1.getSuperType();
      Object localObject2 = paramClass1.getRawClass();
      paramClass2 = new TypeBindings(this, (Class)localObject2);
      if (paramClass1.isGeneric())
      {
        localObject1 = paramClass1.asGeneric().getActualTypeArguments();
        localObject2 = ((Class)localObject2).getTypeParameters();
        int j = localObject1.length;
        int i = 0;
        while (i < j)
        {
          paramClass2.addBinding(localObject2[i].getName(), instance._constructType(localObject1[i], paramTypeBindings));
          i += 1;
        }
      }
      paramTypeBindings = paramClass2;
    }
    if (!paramClass1.isGeneric()) {
      return null;
    }
    return paramTypeBindings.typesAsArray();
  }
  
  public JavaType[] findTypeParameters(JavaType paramJavaType, Class<?> paramClass)
  {
    Object localObject = paramJavaType.getRawClass();
    if (localObject == paramClass)
    {
      int j = paramJavaType.containedTypeCount();
      if (j == 0)
      {
        paramClass = null;
        return paramClass;
      }
      localObject = new JavaType[j];
      int i = 0;
      for (;;)
      {
        paramClass = (Class<?>)localObject;
        if (i >= j) {
          break;
        }
        localObject[i] = paramJavaType.containedType(i);
        i += 1;
      }
    }
    return findTypeParameters((Class)localObject, paramClass, new TypeBindings(this, paramJavaType));
  }
  
  public JavaType uncheckedSimpleType(Class<?> paramClass)
  {
    return new SimpleType(paramClass);
  }
  
  public TypeFactory withModifier(TypeModifier paramTypeModifier)
  {
    if (this._modifiers == null) {
      return new TypeFactory(this._parser, new TypeModifier[] { paramTypeModifier });
    }
    return new TypeFactory(this._parser, (TypeModifier[])ArrayBuilders.insertInListNoDup(this._modifiers, paramTypeModifier));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/type/TypeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */