package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class SimpleBeanPropertyDefinition
  extends BeanPropertyDefinition
{
  protected final PropertyName _fullName;
  protected final JsonInclude.Include _inclusion;
  protected final AnnotationIntrospector _introspector;
  protected final AnnotatedMember _member;
  protected final PropertyMetadata _metadata;
  @Deprecated
  protected final String _name;
  
  @Deprecated
  public SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember)
  {
    this(paramAnnotatedMember, paramAnnotatedMember.getName(), null);
  }
  
  protected SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember, PropertyName paramPropertyName, AnnotationIntrospector paramAnnotationIntrospector, PropertyMetadata paramPropertyMetadata, JsonInclude.Include paramInclude)
  {
    this._introspector = paramAnnotationIntrospector;
    this._member = paramAnnotatedMember;
    this._fullName = paramPropertyName;
    this._name = paramPropertyName.getSimpleName();
    paramAnnotatedMember = paramPropertyMetadata;
    if (paramPropertyMetadata == null) {
      paramAnnotatedMember = PropertyMetadata.STD_OPTIONAL;
    }
    this._metadata = paramAnnotatedMember;
    this._inclusion = paramInclude;
  }
  
  @Deprecated
  public SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember, String paramString)
  {
    this(paramAnnotatedMember, new PropertyName(paramString), null, null, null);
  }
  
  @Deprecated
  protected SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember, String paramString, AnnotationIntrospector paramAnnotationIntrospector)
  {
    this(paramAnnotatedMember, new PropertyName(paramString), paramAnnotationIntrospector, null, null);
  }
  
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember)
  {
    PropertyName localPropertyName = PropertyName.construct(paramAnnotatedMember.getName());
    if (paramMapperConfig == null) {}
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.getAnnotationIntrospector()) {
      return new SimpleBeanPropertyDefinition(paramAnnotatedMember, localPropertyName, paramMapperConfig, null, null);
    }
  }
  
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, PropertyName paramPropertyName)
  {
    return construct(paramMapperConfig, paramAnnotatedMember, paramPropertyName, null, null);
  }
  
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, PropertyName paramPropertyName, PropertyMetadata paramPropertyMetadata, JsonInclude.Include paramInclude)
  {
    if (paramMapperConfig == null) {}
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.getAnnotationIntrospector()) {
      return new SimpleBeanPropertyDefinition(paramAnnotatedMember, paramPropertyName, paramMapperConfig, paramPropertyMetadata, paramInclude);
    }
  }
  
  @Deprecated
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, String paramString)
  {
    paramString = PropertyName.construct(paramString);
    if (paramMapperConfig == null) {}
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.getAnnotationIntrospector()) {
      return new SimpleBeanPropertyDefinition(paramAnnotatedMember, paramString, paramMapperConfig, null, null);
    }
  }
  
  public JsonInclude.Include findInclusion()
  {
    return this._inclusion;
  }
  
  public AnnotatedMember getAccessor()
  {
    AnnotatedMethod localAnnotatedMethod = getGetter();
    Object localObject = localAnnotatedMethod;
    if (localAnnotatedMethod == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedParameter getConstructorParameter()
  {
    if ((this._member instanceof AnnotatedParameter)) {
      return (AnnotatedParameter)this._member;
    }
    return null;
  }
  
  public Iterator<AnnotatedParameter> getConstructorParameters()
  {
    AnnotatedParameter localAnnotatedParameter = getConstructorParameter();
    if (localAnnotatedParameter == null) {
      return EmptyIterator.instance();
    }
    return Collections.singleton(localAnnotatedParameter).iterator();
  }
  
  public AnnotatedField getField()
  {
    if ((this._member instanceof AnnotatedField)) {
      return (AnnotatedField)this._member;
    }
    return null;
  }
  
  public PropertyName getFullName()
  {
    return this._fullName;
  }
  
  public AnnotatedMethod getGetter()
  {
    if (((this._member instanceof AnnotatedMethod)) && (((AnnotatedMethod)this._member).getParameterCount() == 0)) {
      return (AnnotatedMethod)this._member;
    }
    return null;
  }
  
  public String getInternalName()
  {
    return getName();
  }
  
  public PropertyMetadata getMetadata()
  {
    return this._metadata;
  }
  
  public AnnotatedMember getMutator()
  {
    Object localObject2 = getConstructorParameter();
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject2 = getSetter();
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = getField();
      }
    }
    return (AnnotatedMember)localObject1;
  }
  
  public String getName()
  {
    return this._fullName.getSimpleName();
  }
  
  public AnnotatedMember getNonConstructorMutator()
  {
    AnnotatedMethod localAnnotatedMethod = getSetter();
    Object localObject = localAnnotatedMethod;
    if (localAnnotatedMethod == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedMember getPrimaryMember()
  {
    return this._member;
  }
  
  public AnnotatedMethod getSetter()
  {
    if (((this._member instanceof AnnotatedMethod)) && (((AnnotatedMethod)this._member).getParameterCount() == 1)) {
      return (AnnotatedMethod)this._member;
    }
    return null;
  }
  
  public PropertyName getWrapperName()
  {
    if ((this._introspector == null) && (this._member != null)) {
      return null;
    }
    return this._introspector.findWrapperName(this._member);
  }
  
  public boolean hasConstructorParameter()
  {
    return this._member instanceof AnnotatedParameter;
  }
  
  public boolean hasField()
  {
    return this._member instanceof AnnotatedField;
  }
  
  public boolean hasGetter()
  {
    return getGetter() != null;
  }
  
  public boolean hasName(PropertyName paramPropertyName)
  {
    return this._fullName.equals(paramPropertyName);
  }
  
  public boolean hasSetter()
  {
    return getSetter() != null;
  }
  
  public boolean isExplicitlyIncluded()
  {
    return false;
  }
  
  public boolean isExplicitlyNamed()
  {
    return false;
  }
  
  public BeanPropertyDefinition withInclusion(JsonInclude.Include paramInclude)
  {
    if (this._inclusion == paramInclude) {
      return this;
    }
    return new SimpleBeanPropertyDefinition(this._member, this._fullName, this._introspector, this._metadata, paramInclude);
  }
  
  public BeanPropertyDefinition withMetadata(PropertyMetadata paramPropertyMetadata)
  {
    if (paramPropertyMetadata.equals(this._metadata)) {
      return this;
    }
    return new SimpleBeanPropertyDefinition(this._member, this._fullName, this._introspector, paramPropertyMetadata, this._inclusion);
  }
  
  public BeanPropertyDefinition withName(PropertyName paramPropertyName)
  {
    if (this._fullName.equals(paramPropertyName)) {
      return this;
    }
    return new SimpleBeanPropertyDefinition(this._member, paramPropertyName, this._introspector, this._metadata, this._inclusion);
  }
  
  @Deprecated
  public BeanPropertyDefinition withName(String paramString)
  {
    return withSimpleName(paramString);
  }
  
  public BeanPropertyDefinition withSimpleName(String paramString)
  {
    if ((this._fullName.hasSimpleName(paramString)) && (!this._fullName.hasNamespace())) {
      return this;
    }
    return new SimpleBeanPropertyDefinition(this._member, new PropertyName(paramString), this._introspector, this._metadata, this._inclusion);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/util/SimpleBeanPropertyDefinition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */