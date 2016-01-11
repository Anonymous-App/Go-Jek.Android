package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;
import java.io.Serializable;

@JacksonStdImpl
public class StdValueInstantiator
  extends ValueInstantiator
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected SettableBeanProperty[] _constructorArguments;
  protected AnnotatedWithParams _defaultCreator;
  protected SettableBeanProperty[] _delegateArguments;
  protected AnnotatedWithParams _delegateCreator;
  protected JavaType _delegateType;
  protected AnnotatedWithParams _fromBooleanCreator;
  protected AnnotatedWithParams _fromDoubleCreator;
  protected AnnotatedWithParams _fromIntCreator;
  protected AnnotatedWithParams _fromLongCreator;
  protected AnnotatedWithParams _fromStringCreator;
  protected AnnotatedParameter _incompleteParameter;
  protected final String _valueTypeDesc;
  protected AnnotatedWithParams _withArgsCreator;
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (paramDeserializationConfig = "UNKNOWN TYPE";; paramDeserializationConfig = paramJavaType.toString())
    {
      this._valueTypeDesc = paramDeserializationConfig;
      return;
    }
  }
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, Class<?> paramClass)
  {
    if (paramClass == null) {}
    for (paramDeserializationConfig = "UNKNOWN TYPE";; paramDeserializationConfig = paramClass.getName())
    {
      this._valueTypeDesc = paramDeserializationConfig;
      return;
    }
  }
  
  protected StdValueInstantiator(StdValueInstantiator paramStdValueInstantiator)
  {
    this._valueTypeDesc = paramStdValueInstantiator._valueTypeDesc;
    this._defaultCreator = paramStdValueInstantiator._defaultCreator;
    this._constructorArguments = paramStdValueInstantiator._constructorArguments;
    this._withArgsCreator = paramStdValueInstantiator._withArgsCreator;
    this._delegateType = paramStdValueInstantiator._delegateType;
    this._delegateCreator = paramStdValueInstantiator._delegateCreator;
    this._delegateArguments = paramStdValueInstantiator._delegateArguments;
    this._fromStringCreator = paramStdValueInstantiator._fromStringCreator;
    this._fromIntCreator = paramStdValueInstantiator._fromIntCreator;
    this._fromLongCreator = paramStdValueInstantiator._fromLongCreator;
    this._fromDoubleCreator = paramStdValueInstantiator._fromDoubleCreator;
    this._fromBooleanCreator = paramStdValueInstantiator._fromBooleanCreator;
  }
  
  public boolean canCreateFromBoolean()
  {
    return this._fromBooleanCreator != null;
  }
  
  public boolean canCreateFromDouble()
  {
    return this._fromDoubleCreator != null;
  }
  
  public boolean canCreateFromInt()
  {
    return this._fromIntCreator != null;
  }
  
  public boolean canCreateFromLong()
  {
    return this._fromLongCreator != null;
  }
  
  public boolean canCreateFromObjectWith()
  {
    return this._withArgsCreator != null;
  }
  
  public boolean canCreateFromString()
  {
    return this._fromStringCreator != null;
  }
  
  public boolean canCreateUsingDefault()
  {
    return this._defaultCreator != null;
  }
  
  public boolean canCreateUsingDelegate()
  {
    return this._delegateType != null;
  }
  
  public void configureFromBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromBooleanCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromDoubleCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromIntCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromIntCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromLongCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromLongCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromObjectSettings(AnnotatedWithParams paramAnnotatedWithParams1, AnnotatedWithParams paramAnnotatedWithParams2, JavaType paramJavaType, SettableBeanProperty[] paramArrayOfSettableBeanProperty1, AnnotatedWithParams paramAnnotatedWithParams3, SettableBeanProperty[] paramArrayOfSettableBeanProperty2)
  {
    this._defaultCreator = paramAnnotatedWithParams1;
    this._delegateCreator = paramAnnotatedWithParams2;
    this._delegateType = paramJavaType;
    this._delegateArguments = paramArrayOfSettableBeanProperty1;
    this._withArgsCreator = paramAnnotatedWithParams3;
    this._constructorArguments = paramArrayOfSettableBeanProperty2;
  }
  
  public void configureFromStringCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromStringCreator = paramAnnotatedWithParams;
  }
  
  public void configureIncompleteParameter(AnnotatedParameter paramAnnotatedParameter)
  {
    this._incompleteParameter = paramAnnotatedParameter;
  }
  
  public Object createFromBoolean(DeserializationContext paramDeserializationContext, boolean paramBoolean)
    throws IOException
  {
    try
    {
      if (this._fromBooleanCreator != null)
      {
        paramDeserializationContext = this._fromBooleanCreator.call1(Boolean.valueOf(paramBoolean));
        return paramDeserializationContext;
      }
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Boolean value (%s); no single-boolean/Boolean-arg constructor/factory method", new Object[] { getValueTypeDesc(), Boolean.valueOf(paramBoolean) });
  }
  
  public Object createFromDouble(DeserializationContext paramDeserializationContext, double paramDouble)
    throws IOException
  {
    try
    {
      if (this._fromDoubleCreator != null)
      {
        paramDeserializationContext = this._fromDoubleCreator.call1(Double.valueOf(paramDouble));
        return paramDeserializationContext;
      }
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Floating-point number (%s); no one-double/Double-arg constructor/factory method", new Object[] { getValueTypeDesc(), Double.valueOf(paramDouble) });
  }
  
  public Object createFromInt(DeserializationContext paramDeserializationContext, int paramInt)
    throws IOException
  {
    try
    {
      if (this._fromIntCreator != null) {
        return this._fromIntCreator.call1(Integer.valueOf(paramInt));
      }
      if (this._fromLongCreator != null)
      {
        paramDeserializationContext = this._fromLongCreator.call1(Long.valueOf(paramInt));
        return paramDeserializationContext;
      }
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Integral number (%s); no single-int-arg constructor/factory method", new Object[] { getValueTypeDesc(), Integer.valueOf(paramInt) });
  }
  
  public Object createFromLong(DeserializationContext paramDeserializationContext, long paramLong)
    throws IOException
  {
    try
    {
      if (this._fromLongCreator != null)
      {
        paramDeserializationContext = this._fromLongCreator.call1(Long.valueOf(paramLong));
        return paramDeserializationContext;
      }
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Long integral number (%s); no single-long-arg constructor/factory method", new Object[] { getValueTypeDesc(), Long.valueOf(paramLong) });
  }
  
  public Object createFromObjectWith(DeserializationContext paramDeserializationContext, Object[] paramArrayOfObject)
    throws IOException
  {
    if (this._withArgsCreator == null) {
      throw new IllegalStateException("No with-args constructor for " + getValueTypeDesc());
    }
    try
    {
      paramDeserializationContext = this._withArgsCreator.call(paramArrayOfObject);
      return paramDeserializationContext;
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
  }
  
  public Object createFromString(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException
  {
    if (this._fromStringCreator != null) {
      try
      {
        paramDeserializationContext = this._fromStringCreator.call1(paramString);
        return paramDeserializationContext;
      }
      catch (Exception paramDeserializationContext)
      {
        throw wrapException(paramDeserializationContext);
      }
      catch (ExceptionInInitializerError paramDeserializationContext)
      {
        throw wrapException(paramDeserializationContext);
      }
    }
    return _createFromStringFallbacks(paramDeserializationContext, paramString);
  }
  
  public Object createUsingDefault(DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._defaultCreator == null) {
      throw new IllegalStateException("No default constructor for " + getValueTypeDesc());
    }
    try
    {
      paramDeserializationContext = this._defaultCreator.call();
      return paramDeserializationContext;
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
  }
  
  public Object createUsingDelegate(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (this._delegateCreator == null) {
      throw new IllegalStateException("No delegate constructor for " + getValueTypeDesc());
    }
    for (;;)
    {
      int i;
      try
      {
        if (this._delegateArguments == null) {
          return this._delegateCreator.call1(paramObject);
        }
        int j = this._delegateArguments.length;
        arrayOfObject = new Object[j];
        i = 0;
        if (i < j)
        {
          SettableBeanProperty localSettableBeanProperty = this._delegateArguments[i];
          if (localSettableBeanProperty == null) {
            arrayOfObject[i] = paramObject;
          } else {
            arrayOfObject[i] = paramDeserializationContext.findInjectableValue(localSettableBeanProperty.getInjectableValueId(), localSettableBeanProperty, null);
          }
        }
      }
      catch (ExceptionInInitializerError paramDeserializationContext)
      {
        Object[] arrayOfObject;
        throw wrapException(paramDeserializationContext);
        paramDeserializationContext = this._delegateCreator.call(arrayOfObject);
        return paramDeserializationContext;
      }
      catch (Exception paramDeserializationContext)
      {
        throw wrapException(paramDeserializationContext);
      }
      i += 1;
    }
  }
  
  public AnnotatedWithParams getDefaultCreator()
  {
    return this._defaultCreator;
  }
  
  public AnnotatedWithParams getDelegateCreator()
  {
    return this._delegateCreator;
  }
  
  public JavaType getDelegateType(DeserializationConfig paramDeserializationConfig)
  {
    return this._delegateType;
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig paramDeserializationConfig)
  {
    return this._constructorArguments;
  }
  
  public AnnotatedParameter getIncompleteParameter()
  {
    return this._incompleteParameter;
  }
  
  public String getValueTypeDesc()
  {
    return this._valueTypeDesc;
  }
  
  public AnnotatedWithParams getWithArgsCreator()
  {
    return this._withArgsCreator;
  }
  
  protected JsonMappingException wrapException(Throwable paramThrowable)
  {
    while (paramThrowable.getCause() != null) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof JsonMappingException)) {
      return (JsonMappingException)paramThrowable;
    }
    return new JsonMappingException("Instantiation of " + getValueTypeDesc() + " value failed: " + paramThrowable.getMessage(), paramThrowable);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/StdValueInstantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */