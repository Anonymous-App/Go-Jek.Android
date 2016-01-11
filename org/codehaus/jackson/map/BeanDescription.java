package org.codehaus.jackson.map;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

public abstract class BeanDescription
{
  protected final JavaType _type;
  
  protected BeanDescription(JavaType paramJavaType)
  {
    this._type = paramJavaType;
  }
  
  public abstract TypeBindings bindingsForBeanType();
  
  public abstract AnnotatedMethod findAnyGetter();
  
  public abstract AnnotatedMethod findAnySetter();
  
  public abstract AnnotatedConstructor findDefaultConstructor();
  
  @Deprecated
  public abstract LinkedHashMap<String, AnnotatedField> findDeserializableFields(VisibilityChecker<?> paramVisibilityChecker, Collection<String> paramCollection);
  
  @Deprecated
  public abstract LinkedHashMap<String, AnnotatedMethod> findGetters(VisibilityChecker<?> paramVisibilityChecker, Collection<String> paramCollection);
  
  public abstract Map<Object, AnnotatedMember> findInjectables();
  
  public abstract AnnotatedMethod findJsonValueMethod();
  
  public abstract List<BeanPropertyDefinition> findProperties();
  
  @Deprecated
  public abstract Map<String, AnnotatedField> findSerializableFields(VisibilityChecker<?> paramVisibilityChecker, Collection<String> paramCollection);
  
  @Deprecated
  public abstract LinkedHashMap<String, AnnotatedMethod> findSetters(VisibilityChecker<?> paramVisibilityChecker);
  
  public Class<?> getBeanClass()
  {
    return this._type.getRawClass();
  }
  
  public abstract Annotations getClassAnnotations();
  
  public abstract AnnotatedClass getClassInfo();
  
  public abstract Set<String> getIgnoredPropertyNames();
  
  public JavaType getType()
  {
    return this._type;
  }
  
  public abstract boolean hasKnownClassAnnotations();
  
  public abstract JavaType resolveType(Type paramType);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/BeanDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */