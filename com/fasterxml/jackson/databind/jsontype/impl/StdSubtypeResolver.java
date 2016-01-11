package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class StdSubtypeResolver
  extends SubtypeResolver
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected LinkedHashSet<NamedType> _registeredSubtypes;
  
  protected void _collectAndResolve(AnnotatedClass paramAnnotatedClass, NamedType paramNamedType, MapperConfig<?> paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector, HashMap<NamedType, NamedType> paramHashMap)
  {
    NamedType localNamedType = paramNamedType;
    if (!paramNamedType.hasName())
    {
      String str = paramAnnotationIntrospector.findTypeName(paramAnnotatedClass);
      localNamedType = paramNamedType;
      if (str != null) {
        localNamedType = new NamedType(paramNamedType.getType(), str);
      }
    }
    if (paramHashMap.containsKey(localNamedType)) {
      if ((localNamedType.hasName()) && (!((NamedType)paramHashMap.get(localNamedType)).hasName())) {
        paramHashMap.put(localNamedType, localNamedType);
      }
    }
    for (;;)
    {
      return;
      paramHashMap.put(localNamedType, localNamedType);
      paramAnnotatedClass = paramAnnotationIntrospector.findSubtypes(paramAnnotatedClass);
      if ((paramAnnotatedClass != null) && (!paramAnnotatedClass.isEmpty()))
      {
        paramAnnotatedClass = paramAnnotatedClass.iterator();
        while (paramAnnotatedClass.hasNext())
        {
          paramNamedType = (NamedType)paramAnnotatedClass.next();
          _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(paramNamedType.getType(), paramAnnotationIntrospector, paramMapperConfig), paramNamedType, paramMapperConfig, paramAnnotationIntrospector, paramHashMap);
        }
      }
    }
  }
  
  protected void _collectAndResolveByTypeId(AnnotatedClass paramAnnotatedClass, NamedType paramNamedType, MapperConfig<?> paramMapperConfig, Set<Class<?>> paramSet, Map<String, NamedType> paramMap)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    NamedType localNamedType = paramNamedType;
    if (!paramNamedType.hasName())
    {
      String str = localAnnotationIntrospector.findTypeName(paramAnnotatedClass);
      localNamedType = paramNamedType;
      if (str != null) {
        localNamedType = new NamedType(paramNamedType.getType(), str);
      }
    }
    if (localNamedType.hasName()) {
      paramMap.put(localNamedType.getName(), localNamedType);
    }
    if (paramSet.add(localNamedType.getType()))
    {
      paramAnnotatedClass = localAnnotationIntrospector.findSubtypes(paramAnnotatedClass);
      if ((paramAnnotatedClass != null) && (!paramAnnotatedClass.isEmpty()))
      {
        paramAnnotatedClass = paramAnnotatedClass.iterator();
        while (paramAnnotatedClass.hasNext())
        {
          paramNamedType = (NamedType)paramAnnotatedClass.next();
          _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(paramNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), paramNamedType, paramMapperConfig, paramSet, paramMap);
        }
      }
    }
  }
  
  protected Collection<NamedType> _combineNamedAndUnnamed(Set<Class<?>> paramSet, Map<String, NamedType> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.values());
    paramMap = paramMap.values().iterator();
    while (paramMap.hasNext()) {
      paramSet.remove(((NamedType)paramMap.next()).getType());
    }
    paramSet = paramSet.iterator();
    while (paramSet.hasNext()) {
      localArrayList.add(new NamedType((Class)paramSet.next()));
    }
    return localArrayList;
  }
  
  @Deprecated
  public Collection<NamedType> collectAndResolveSubtypes(AnnotatedClass paramAnnotatedClass, MapperConfig<?> paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector)
  {
    return collectAndResolveSubtypesByClass(paramMapperConfig, paramAnnotatedClass);
  }
  
  @Deprecated
  public Collection<NamedType> collectAndResolveSubtypes(AnnotatedMember paramAnnotatedMember, MapperConfig<?> paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector, JavaType paramJavaType)
  {
    return collectAndResolveSubtypesByClass(paramMapperConfig, paramAnnotatedMember, paramJavaType);
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    HashMap localHashMap = new HashMap();
    if (this._registeredSubtypes != null)
    {
      Class localClass = paramAnnotatedClass.getRawType();
      Iterator localIterator = this._registeredSubtypes.iterator();
      while (localIterator.hasNext())
      {
        NamedType localNamedType = (NamedType)localIterator.next();
        if (localClass.isAssignableFrom(localNamedType.getType())) {
          _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, localAnnotationIntrospector, localHashMap);
        }
      }
    }
    _collectAndResolve(paramAnnotatedClass, new NamedType(paramAnnotatedClass.getRawType(), null), paramMapperConfig, localAnnotationIntrospector, localHashMap);
    return new ArrayList(localHashMap.values());
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    if (paramJavaType == null) {}
    HashMap localHashMap;
    Object localObject;
    for (paramJavaType = paramAnnotatedMember.getRawType();; paramJavaType = paramJavaType.getRawClass())
    {
      localHashMap = new HashMap();
      if (this._registeredSubtypes == null) {
        break;
      }
      localObject = this._registeredSubtypes.iterator();
      while (((Iterator)localObject).hasNext())
      {
        NamedType localNamedType = (NamedType)((Iterator)localObject).next();
        if (paramJavaType.isAssignableFrom(localNamedType.getType())) {
          _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, localAnnotationIntrospector, localHashMap);
        }
      }
    }
    paramAnnotatedMember = localAnnotationIntrospector.findSubtypes(paramAnnotatedMember);
    if (paramAnnotatedMember != null)
    {
      paramAnnotatedMember = paramAnnotatedMember.iterator();
      while (paramAnnotatedMember.hasNext())
      {
        localObject = (NamedType)paramAnnotatedMember.next();
        _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(((NamedType)localObject).getType(), localAnnotationIntrospector, paramMapperConfig), (NamedType)localObject, paramMapperConfig, localAnnotationIntrospector, localHashMap);
      }
    }
    paramAnnotatedMember = new NamedType(paramJavaType, null);
    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(paramJavaType, localAnnotationIntrospector, paramMapperConfig), paramAnnotatedMember, paramMapperConfig, localAnnotationIntrospector, localHashMap);
    return new ArrayList(localHashMap.values());
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass)
  {
    HashSet localHashSet = new HashSet();
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    _collectAndResolveByTypeId(paramAnnotatedClass, new NamedType(paramAnnotatedClass.getRawType(), null), paramMapperConfig, localHashSet, localLinkedHashMap);
    if (this._registeredSubtypes != null)
    {
      paramAnnotatedClass = paramAnnotatedClass.getRawType();
      Iterator localIterator = this._registeredSubtypes.iterator();
      while (localIterator.hasNext())
      {
        NamedType localNamedType = (NamedType)localIterator.next();
        if (paramAnnotatedClass.isAssignableFrom(localNamedType.getType()))
        {
          AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
          _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, localHashSet, localLinkedHashMap);
        }
      }
    }
    return _combineNamedAndUnnamed(localHashSet, localLinkedHashMap);
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    if (paramJavaType == null) {}
    HashSet localHashSet;
    LinkedHashMap localLinkedHashMap;
    NamedType localNamedType;
    for (paramJavaType = paramAnnotatedMember.getRawType();; paramJavaType = paramJavaType.getRawClass())
    {
      localHashSet = new HashSet();
      localLinkedHashMap = new LinkedHashMap();
      localNamedType = new NamedType(paramJavaType, null);
      _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(paramJavaType, localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, localHashSet, localLinkedHashMap);
      paramAnnotatedMember = localAnnotationIntrospector.findSubtypes(paramAnnotatedMember);
      if (paramAnnotatedMember == null) {
        break;
      }
      paramAnnotatedMember = paramAnnotatedMember.iterator();
      while (paramAnnotatedMember.hasNext())
      {
        localNamedType = (NamedType)paramAnnotatedMember.next();
        _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, localHashSet, localLinkedHashMap);
      }
    }
    if (this._registeredSubtypes != null)
    {
      paramAnnotatedMember = this._registeredSubtypes.iterator();
      while (paramAnnotatedMember.hasNext())
      {
        localNamedType = (NamedType)paramAnnotatedMember.next();
        if (paramJavaType.isAssignableFrom(localNamedType.getType())) {
          _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, localHashSet, localLinkedHashMap);
        }
      }
    }
    return _combineNamedAndUnnamed(localHashSet, localLinkedHashMap);
  }
  
  public void registerSubtypes(NamedType... paramVarArgs)
  {
    if (this._registeredSubtypes == null) {
      this._registeredSubtypes = new LinkedHashSet();
    }
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      NamedType localNamedType = paramVarArgs[i];
      this._registeredSubtypes.add(localNamedType);
      i += 1;
    }
  }
  
  public void registerSubtypes(Class<?>... paramVarArgs)
  {
    NamedType[] arrayOfNamedType = new NamedType[paramVarArgs.length];
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      arrayOfNamedType[i] = new NamedType(paramVarArgs[i]);
      i += 1;
    }
    registerSubtypes(arrayOfNamedType);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/jsontype/impl/StdSubtypeResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */