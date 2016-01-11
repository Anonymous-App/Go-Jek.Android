package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.KeyDeserializer.None;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationIntrospectorPair
  extends AnnotationIntrospector
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final AnnotationIntrospector _primary;
  protected final AnnotationIntrospector _secondary;
  
  public AnnotationIntrospectorPair(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2)
  {
    this._primary = paramAnnotationIntrospector1;
    this._secondary = paramAnnotationIntrospector2;
  }
  
  public static AnnotationIntrospector create(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2)
  {
    if (paramAnnotationIntrospector1 == null) {
      return paramAnnotationIntrospector2;
    }
    if (paramAnnotationIntrospector2 == null) {
      return paramAnnotationIntrospector1;
    }
    return new AnnotationIntrospectorPair(paramAnnotationIntrospector1, paramAnnotationIntrospector2);
  }
  
  protected boolean _isExplicitClassOrOb(Object paramObject, Class<?> paramClass)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramObject == null) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!(paramObject instanceof Class));
      paramObject = (Class)paramObject;
      if (paramObject == paramClass) {
        break;
      }
      bool1 = bool2;
    } while (!ClassUtil.isBogusClass((Class)paramObject));
    return false;
  }
  
  public Collection<AnnotationIntrospector> allIntrospectors()
  {
    return allIntrospectors(new ArrayList());
  }
  
  public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> paramCollection)
  {
    this._primary.allIntrospectors(paramCollection);
    this._secondary.allIntrospectors(paramCollection);
    return paramCollection;
  }
  
  public void findAndAddVirtualProperties(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, List<BeanPropertyWriter> paramList)
  {
    this._primary.findAndAddVirtualProperties(paramMapperConfig, paramAnnotatedClass, paramList);
    this._secondary.findAndAddVirtualProperties(paramMapperConfig, paramAnnotatedClass, paramList);
  }
  
  public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass paramAnnotatedClass, VisibilityChecker<?> paramVisibilityChecker)
  {
    paramVisibilityChecker = this._secondary.findAutoDetectVisibility(paramAnnotatedClass, paramVisibilityChecker);
    return this._primary.findAutoDetectVisibility(paramAnnotatedClass, paramVisibilityChecker);
  }
  
  public Object findContentDeserializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findContentDeserializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonDeserializer.None.class)) {
      return localObject;
    }
    return this._secondary.findContentDeserializer(paramAnnotated);
  }
  
  public Object findContentSerializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findContentSerializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonSerializer.None.class)) {
      return localObject;
    }
    return this._secondary.findContentSerializer(paramAnnotated);
  }
  
  public JsonCreator.Mode findCreatorBinding(Annotated paramAnnotated)
  {
    JsonCreator.Mode localMode = this._primary.findCreatorBinding(paramAnnotated);
    if (localMode != null) {
      return localMode;
    }
    return this._secondary.findCreatorBinding(paramAnnotated);
  }
  
  public Object findDeserializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    Object localObject2 = this._primary.findDeserializationContentConverter(paramAnnotatedMember);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findDeserializationContentConverter(paramAnnotatedMember);
    }
    return localObject1;
  }
  
  public Class<?> findDeserializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass2 = this._primary.findDeserializationContentType(paramAnnotated, paramJavaType);
    Class localClass1 = localClass2;
    if (localClass2 == null) {
      localClass1 = this._secondary.findDeserializationContentType(paramAnnotated, paramJavaType);
    }
    return localClass1;
  }
  
  public Object findDeserializationConverter(Annotated paramAnnotated)
  {
    Object localObject2 = this._primary.findDeserializationConverter(paramAnnotated);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findDeserializationConverter(paramAnnotated);
    }
    return localObject1;
  }
  
  public Class<?> findDeserializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass2 = this._primary.findDeserializationKeyType(paramAnnotated, paramJavaType);
    Class localClass1 = localClass2;
    if (localClass2 == null) {
      localClass1 = this._secondary.findDeserializationKeyType(paramAnnotated, paramJavaType);
    }
    return localClass1;
  }
  
  public Class<?> findDeserializationType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass = this._primary.findDeserializationType(paramAnnotated, paramJavaType);
    if (localClass != null) {
      return localClass;
    }
    return this._secondary.findDeserializationType(paramAnnotated, paramJavaType);
  }
  
  public Object findDeserializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findDeserializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonDeserializer.None.class)) {
      return localObject;
    }
    return this._secondary.findDeserializer(paramAnnotated);
  }
  
  public String findEnumValue(Enum<?> paramEnum)
  {
    String str2 = this._primary.findEnumValue(paramEnum);
    String str1 = str2;
    if (str2 == null) {
      str1 = this._secondary.findEnumValue(paramEnum);
    }
    return str1;
  }
  
  public Object findFilterId(Annotated paramAnnotated)
  {
    Object localObject2 = this._primary.findFilterId(paramAnnotated);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findFilterId(paramAnnotated);
    }
    return localObject1;
  }
  
  @Deprecated
  public Object findFilterId(AnnotatedClass paramAnnotatedClass)
  {
    Object localObject2 = this._primary.findFilterId(paramAnnotatedClass);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findFilterId(paramAnnotatedClass);
    }
    return localObject1;
  }
  
  public JsonFormat.Value findFormat(Annotated paramAnnotated)
  {
    JsonFormat.Value localValue2 = this._primary.findFormat(paramAnnotated);
    JsonFormat.Value localValue1 = localValue2;
    if (localValue2 == null) {
      localValue1 = this._secondary.findFormat(paramAnnotated);
    }
    return localValue1;
  }
  
  public Boolean findIgnoreUnknownProperties(AnnotatedClass paramAnnotatedClass)
  {
    Boolean localBoolean2 = this._primary.findIgnoreUnknownProperties(paramAnnotatedClass);
    Boolean localBoolean1 = localBoolean2;
    if (localBoolean2 == null) {
      localBoolean1 = this._secondary.findIgnoreUnknownProperties(paramAnnotatedClass);
    }
    return localBoolean1;
  }
  
  public String findImplicitPropertyName(AnnotatedMember paramAnnotatedMember)
  {
    String str2 = this._primary.findImplicitPropertyName(paramAnnotatedMember);
    String str1 = str2;
    if (str2 == null) {
      str1 = this._secondary.findImplicitPropertyName(paramAnnotatedMember);
    }
    return str1;
  }
  
  public Object findInjectableValueId(AnnotatedMember paramAnnotatedMember)
  {
    Object localObject2 = this._primary.findInjectableValueId(paramAnnotatedMember);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findInjectableValueId(paramAnnotatedMember);
    }
    return localObject1;
  }
  
  public Object findKeyDeserializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findKeyDeserializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, KeyDeserializer.None.class)) {
      return localObject;
    }
    return this._secondary.findKeyDeserializer(paramAnnotated);
  }
  
  public Object findKeySerializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findKeySerializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonSerializer.None.class)) {
      return localObject;
    }
    return this._secondary.findKeySerializer(paramAnnotated);
  }
  
  public PropertyName findNameForDeserialization(Annotated paramAnnotated)
  {
    PropertyName localPropertyName2 = this._primary.findNameForDeserialization(paramAnnotated);
    PropertyName localPropertyName1;
    if (localPropertyName2 == null) {
      localPropertyName1 = this._secondary.findNameForDeserialization(paramAnnotated);
    }
    do
    {
      do
      {
        return localPropertyName1;
        localPropertyName1 = localPropertyName2;
      } while (localPropertyName2 != PropertyName.USE_DEFAULT);
      paramAnnotated = this._secondary.findNameForDeserialization(paramAnnotated);
      localPropertyName1 = localPropertyName2;
    } while (paramAnnotated == null);
    return paramAnnotated;
  }
  
  public PropertyName findNameForSerialization(Annotated paramAnnotated)
  {
    PropertyName localPropertyName2 = this._primary.findNameForSerialization(paramAnnotated);
    PropertyName localPropertyName1;
    if (localPropertyName2 == null) {
      localPropertyName1 = this._secondary.findNameForSerialization(paramAnnotated);
    }
    do
    {
      do
      {
        return localPropertyName1;
        localPropertyName1 = localPropertyName2;
      } while (localPropertyName2 != PropertyName.USE_DEFAULT);
      paramAnnotated = this._secondary.findNameForSerialization(paramAnnotated);
      localPropertyName1 = localPropertyName2;
    } while (paramAnnotated == null);
    return paramAnnotated;
  }
  
  public Object findNamingStrategy(AnnotatedClass paramAnnotatedClass)
  {
    Object localObject2 = this._primary.findNamingStrategy(paramAnnotatedClass);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findNamingStrategy(paramAnnotatedClass);
    }
    return localObject1;
  }
  
  public Object findNullSerializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findNullSerializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonSerializer.None.class)) {
      return localObject;
    }
    return this._secondary.findNullSerializer(paramAnnotated);
  }
  
  public ObjectIdInfo findObjectIdInfo(Annotated paramAnnotated)
  {
    ObjectIdInfo localObjectIdInfo2 = this._primary.findObjectIdInfo(paramAnnotated);
    ObjectIdInfo localObjectIdInfo1 = localObjectIdInfo2;
    if (localObjectIdInfo2 == null) {
      localObjectIdInfo1 = this._secondary.findObjectIdInfo(paramAnnotated);
    }
    return localObjectIdInfo1;
  }
  
  public ObjectIdInfo findObjectReferenceInfo(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
  {
    paramObjectIdInfo = this._secondary.findObjectReferenceInfo(paramAnnotated, paramObjectIdInfo);
    return this._primary.findObjectReferenceInfo(paramAnnotated, paramObjectIdInfo);
  }
  
  public Class<?> findPOJOBuilder(AnnotatedClass paramAnnotatedClass)
  {
    Class localClass2 = this._primary.findPOJOBuilder(paramAnnotatedClass);
    Class localClass1 = localClass2;
    if (localClass2 == null) {
      localClass1 = this._secondary.findPOJOBuilder(paramAnnotatedClass);
    }
    return localClass1;
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass paramAnnotatedClass)
  {
    JsonPOJOBuilder.Value localValue2 = this._primary.findPOJOBuilderConfig(paramAnnotatedClass);
    JsonPOJOBuilder.Value localValue1 = localValue2;
    if (localValue2 == null) {
      localValue1 = this._secondary.findPOJOBuilderConfig(paramAnnotatedClass);
    }
    return localValue1;
  }
  
  @Deprecated
  public String[] findPropertiesToIgnore(Annotated paramAnnotated)
  {
    String[] arrayOfString2 = this._primary.findPropertiesToIgnore(paramAnnotated);
    String[] arrayOfString1 = arrayOfString2;
    if (arrayOfString2 == null) {
      arrayOfString1 = this._secondary.findPropertiesToIgnore(paramAnnotated);
    }
    return arrayOfString1;
  }
  
  public String[] findPropertiesToIgnore(Annotated paramAnnotated, boolean paramBoolean)
  {
    String[] arrayOfString2 = this._primary.findPropertiesToIgnore(paramAnnotated, paramBoolean);
    String[] arrayOfString1 = arrayOfString2;
    if (arrayOfString2 == null) {
      arrayOfString1 = this._secondary.findPropertiesToIgnore(paramAnnotated, paramBoolean);
    }
    return arrayOfString1;
  }
  
  public JsonProperty.Access findPropertyAccess(Annotated paramAnnotated)
  {
    JsonProperty.Access localAccess = this._primary.findPropertyAccess(paramAnnotated);
    if ((localAccess != null) && (localAccess != JsonProperty.Access.AUTO)) {
      return localAccess;
    }
    paramAnnotated = this._secondary.findPropertyAccess(paramAnnotated);
    if (paramAnnotated != null) {
      return paramAnnotated;
    }
    return JsonProperty.Access.AUTO;
  }
  
  public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    TypeResolverBuilder localTypeResolverBuilder2 = this._primary.findPropertyContentTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    TypeResolverBuilder localTypeResolverBuilder1 = localTypeResolverBuilder2;
    if (localTypeResolverBuilder2 == null) {
      localTypeResolverBuilder1 = this._secondary.findPropertyContentTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    }
    return localTypeResolverBuilder1;
  }
  
  public String findPropertyDefaultValue(Annotated paramAnnotated)
  {
    String str2 = this._primary.findPropertyDefaultValue(paramAnnotated);
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.isEmpty()) {}
    }
    else
    {
      str1 = this._secondary.findPropertyDefaultValue(paramAnnotated);
    }
    return str1;
  }
  
  public String findPropertyDescription(Annotated paramAnnotated)
  {
    String str2 = this._primary.findPropertyDescription(paramAnnotated);
    String str1 = str2;
    if (str2 == null) {
      str1 = this._secondary.findPropertyDescription(paramAnnotated);
    }
    return str1;
  }
  
  public JsonInclude.Value findPropertyInclusion(Annotated paramAnnotated)
  {
    JsonInclude.Value localValue = this._secondary.findPropertyInclusion(paramAnnotated);
    paramAnnotated = this._secondary.findPropertyInclusion(paramAnnotated);
    if (localValue == null) {
      return paramAnnotated;
    }
    return localValue.withOverrides(paramAnnotated);
  }
  
  public Integer findPropertyIndex(Annotated paramAnnotated)
  {
    Integer localInteger2 = this._primary.findPropertyIndex(paramAnnotated);
    Integer localInteger1 = localInteger2;
    if (localInteger2 == null) {
      localInteger1 = this._secondary.findPropertyIndex(paramAnnotated);
    }
    return localInteger1;
  }
  
  public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    TypeResolverBuilder localTypeResolverBuilder2 = this._primary.findPropertyTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    TypeResolverBuilder localTypeResolverBuilder1 = localTypeResolverBuilder2;
    if (localTypeResolverBuilder2 == null) {
      localTypeResolverBuilder1 = this._secondary.findPropertyTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    }
    return localTypeResolverBuilder1;
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember paramAnnotatedMember)
  {
    AnnotationIntrospector.ReferenceProperty localReferenceProperty2 = this._primary.findReferenceType(paramAnnotatedMember);
    AnnotationIntrospector.ReferenceProperty localReferenceProperty1 = localReferenceProperty2;
    if (localReferenceProperty2 == null) {
      localReferenceProperty1 = this._secondary.findReferenceType(paramAnnotatedMember);
    }
    return localReferenceProperty1;
  }
  
  public PropertyName findRootName(AnnotatedClass paramAnnotatedClass)
  {
    PropertyName localPropertyName2 = this._primary.findRootName(paramAnnotatedClass);
    PropertyName localPropertyName1;
    if (localPropertyName2 == null) {
      localPropertyName1 = this._secondary.findRootName(paramAnnotatedClass);
    }
    do
    {
      do
      {
        return localPropertyName1;
        localPropertyName1 = localPropertyName2;
      } while (localPropertyName2.hasSimpleName());
      paramAnnotatedClass = this._secondary.findRootName(paramAnnotatedClass);
      localPropertyName1 = localPropertyName2;
    } while (paramAnnotatedClass == null);
    return paramAnnotatedClass;
  }
  
  public Object findSerializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    Object localObject2 = this._primary.findSerializationContentConverter(paramAnnotatedMember);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findSerializationContentConverter(paramAnnotatedMember);
    }
    return localObject1;
  }
  
  public Class<?> findSerializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass2 = this._primary.findSerializationContentType(paramAnnotated, paramJavaType);
    Class localClass1 = localClass2;
    if (localClass2 == null) {
      localClass1 = this._secondary.findSerializationContentType(paramAnnotated, paramJavaType);
    }
    return localClass1;
  }
  
  public Object findSerializationConverter(Annotated paramAnnotated)
  {
    Object localObject2 = this._primary.findSerializationConverter(paramAnnotated);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findSerializationConverter(paramAnnotated);
    }
    return localObject1;
  }
  
  public JsonInclude.Include findSerializationInclusion(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    paramInclude = this._secondary.findSerializationInclusion(paramAnnotated, paramInclude);
    return this._primary.findSerializationInclusion(paramAnnotated, paramInclude);
  }
  
  public JsonInclude.Include findSerializationInclusionForContent(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    paramInclude = this._secondary.findSerializationInclusion(paramAnnotated, paramInclude);
    return this._primary.findSerializationInclusion(paramAnnotated, paramInclude);
  }
  
  public Class<?> findSerializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass2 = this._primary.findSerializationKeyType(paramAnnotated, paramJavaType);
    Class localClass1 = localClass2;
    if (localClass2 == null) {
      localClass1 = this._secondary.findSerializationKeyType(paramAnnotated, paramJavaType);
    }
    return localClass1;
  }
  
  public String[] findSerializationPropertyOrder(AnnotatedClass paramAnnotatedClass)
  {
    String[] arrayOfString2 = this._primary.findSerializationPropertyOrder(paramAnnotatedClass);
    String[] arrayOfString1 = arrayOfString2;
    if (arrayOfString2 == null) {
      arrayOfString1 = this._secondary.findSerializationPropertyOrder(paramAnnotatedClass);
    }
    return arrayOfString1;
  }
  
  public Boolean findSerializationSortAlphabetically(Annotated paramAnnotated)
  {
    Boolean localBoolean2 = this._primary.findSerializationSortAlphabetically(paramAnnotated);
    Boolean localBoolean1 = localBoolean2;
    if (localBoolean2 == null) {
      localBoolean1 = this._secondary.findSerializationSortAlphabetically(paramAnnotated);
    }
    return localBoolean1;
  }
  
  public Class<?> findSerializationType(Annotated paramAnnotated)
  {
    Class localClass2 = this._primary.findSerializationType(paramAnnotated);
    Class localClass1 = localClass2;
    if (localClass2 == null) {
      localClass1 = this._secondary.findSerializationType(paramAnnotated);
    }
    return localClass1;
  }
  
  public JsonSerialize.Typing findSerializationTyping(Annotated paramAnnotated)
  {
    JsonSerialize.Typing localTyping2 = this._primary.findSerializationTyping(paramAnnotated);
    JsonSerialize.Typing localTyping1 = localTyping2;
    if (localTyping2 == null) {
      localTyping1 = this._secondary.findSerializationTyping(paramAnnotated);
    }
    return localTyping1;
  }
  
  public Object findSerializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findSerializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonSerializer.None.class)) {
      return localObject;
    }
    return this._secondary.findSerializer(paramAnnotated);
  }
  
  public List<NamedType> findSubtypes(Annotated paramAnnotated)
  {
    List localList = this._primary.findSubtypes(paramAnnotated);
    paramAnnotated = this._secondary.findSubtypes(paramAnnotated);
    if ((localList == null) || (localList.isEmpty())) {
      return paramAnnotated;
    }
    if ((paramAnnotated == null) || (paramAnnotated.isEmpty())) {
      return localList;
    }
    ArrayList localArrayList = new ArrayList(localList.size() + paramAnnotated.size());
    localArrayList.addAll(localList);
    localArrayList.addAll(paramAnnotated);
    return localArrayList;
  }
  
  public String findTypeName(AnnotatedClass paramAnnotatedClass)
  {
    String str2 = this._primary.findTypeName(paramAnnotatedClass);
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (str2.length() != 0) {}
    }
    else
    {
      str1 = this._secondary.findTypeName(paramAnnotatedClass);
    }
    return str1;
  }
  
  public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType)
  {
    TypeResolverBuilder localTypeResolverBuilder2 = this._primary.findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType);
    TypeResolverBuilder localTypeResolverBuilder1 = localTypeResolverBuilder2;
    if (localTypeResolverBuilder2 == null) {
      localTypeResolverBuilder1 = this._secondary.findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType);
    }
    return localTypeResolverBuilder1;
  }
  
  public NameTransformer findUnwrappingNameTransformer(AnnotatedMember paramAnnotatedMember)
  {
    NameTransformer localNameTransformer2 = this._primary.findUnwrappingNameTransformer(paramAnnotatedMember);
    NameTransformer localNameTransformer1 = localNameTransformer2;
    if (localNameTransformer2 == null) {
      localNameTransformer1 = this._secondary.findUnwrappingNameTransformer(paramAnnotatedMember);
    }
    return localNameTransformer1;
  }
  
  public Object findValueInstantiator(AnnotatedClass paramAnnotatedClass)
  {
    Object localObject2 = this._primary.findValueInstantiator(paramAnnotatedClass);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._secondary.findValueInstantiator(paramAnnotatedClass);
    }
    return localObject1;
  }
  
  public Class<?>[] findViews(Annotated paramAnnotated)
  {
    Class[] arrayOfClass2 = this._primary.findViews(paramAnnotated);
    Class[] arrayOfClass1 = arrayOfClass2;
    if (arrayOfClass2 == null) {
      arrayOfClass1 = this._secondary.findViews(paramAnnotated);
    }
    return arrayOfClass1;
  }
  
  public PropertyName findWrapperName(Annotated paramAnnotated)
  {
    PropertyName localPropertyName2 = this._primary.findWrapperName(paramAnnotated);
    PropertyName localPropertyName1;
    if (localPropertyName2 == null) {
      localPropertyName1 = this._secondary.findWrapperName(paramAnnotated);
    }
    do
    {
      do
      {
        return localPropertyName1;
        localPropertyName1 = localPropertyName2;
      } while (localPropertyName2 != PropertyName.USE_DEFAULT);
      paramAnnotated = this._secondary.findWrapperName(paramAnnotated);
      localPropertyName1 = localPropertyName2;
    } while (paramAnnotated == null);
    return paramAnnotated;
  }
  
  public boolean hasAnyGetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return (this._primary.hasAnyGetterAnnotation(paramAnnotatedMethod)) || (this._secondary.hasAnyGetterAnnotation(paramAnnotatedMethod));
  }
  
  public boolean hasAnySetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return (this._primary.hasAnySetterAnnotation(paramAnnotatedMethod)) || (this._secondary.hasAnySetterAnnotation(paramAnnotatedMethod));
  }
  
  public boolean hasAsValueAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return (this._primary.hasAsValueAnnotation(paramAnnotatedMethod)) || (this._secondary.hasAsValueAnnotation(paramAnnotatedMethod));
  }
  
  public boolean hasCreatorAnnotation(Annotated paramAnnotated)
  {
    return (this._primary.hasCreatorAnnotation(paramAnnotated)) || (this._secondary.hasCreatorAnnotation(paramAnnotated));
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember paramAnnotatedMember)
  {
    return (this._primary.hasIgnoreMarker(paramAnnotatedMember)) || (this._secondary.hasIgnoreMarker(paramAnnotatedMember));
  }
  
  public Boolean hasRequiredMarker(AnnotatedMember paramAnnotatedMember)
  {
    Boolean localBoolean2 = this._primary.hasRequiredMarker(paramAnnotatedMember);
    Boolean localBoolean1 = localBoolean2;
    if (localBoolean2 == null) {
      localBoolean1 = this._secondary.hasRequiredMarker(paramAnnotatedMember);
    }
    return localBoolean1;
  }
  
  public boolean isAnnotationBundle(Annotation paramAnnotation)
  {
    return (this._primary.isAnnotationBundle(paramAnnotation)) || (this._secondary.isAnnotationBundle(paramAnnotation));
  }
  
  public Boolean isIgnorableType(AnnotatedClass paramAnnotatedClass)
  {
    Boolean localBoolean2 = this._primary.isIgnorableType(paramAnnotatedClass);
    Boolean localBoolean1 = localBoolean2;
    if (localBoolean2 == null) {
      localBoolean1 = this._secondary.isIgnorableType(paramAnnotatedClass);
    }
    return localBoolean1;
  }
  
  public Boolean isTypeId(AnnotatedMember paramAnnotatedMember)
  {
    Boolean localBoolean2 = this._primary.isTypeId(paramAnnotatedMember);
    Boolean localBoolean1 = localBoolean2;
    if (localBoolean2 == null) {
      localBoolean1 = this._secondary.isTypeId(paramAnnotatedMember);
    }
    return localBoolean1;
  }
  
  public Version version()
  {
    return this._primary.version();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/introspect/AnnotationIntrospectorPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */