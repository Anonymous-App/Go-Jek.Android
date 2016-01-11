package org.parceler;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public final class InjectionUtil
{
  public static final String CALL_CONSTRUCTOR_METHOD = "callConstructor";
  public static final String CALL_METHOD_METHOD = "callMethod";
  public static final String GET_FIELD_METHOD = "getField";
  public static final String SET_FIELD_METHOD = "setField";
  
  public static <T> T callConstructor(Class<T> paramClass, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramClass = AccessController.doPrivileged(new SetConstructorPrivilegedAction(paramClass.getDeclaredConstructor(paramArrayOfClass), paramArrayOfObject, null));
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      throw new ParcelerRuntimeException("Exception during method injection: NoSuchMethodException", paramClass);
    }
    catch (PrivilegedActionException paramClass)
    {
      throw new ParcelerRuntimeException("PrivilegedActionException Exception during field injection", paramClass);
    }
    catch (Exception paramClass)
    {
      throw new ParcelerRuntimeException("Exception during field injection", paramClass);
    }
  }
  
  public static <T> T callMethod(Class<T> paramClass, Class<?> paramClass1, Object paramObject, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramClass = AccessController.doPrivileged(new SetMethodPrivilegedAction(paramClass1.getDeclaredMethod(paramString, paramArrayOfClass), paramObject, paramArrayOfObject, null));
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      throw new ParcelerRuntimeException("Exception during method injection: NoSuchFieldException", paramClass);
    }
    catch (PrivilegedActionException paramClass)
    {
      throw new ParcelerRuntimeException("PrivilegedActionException Exception during field injection", paramClass);
    }
    catch (Exception paramClass)
    {
      throw new ParcelerRuntimeException("Exception during field injection", paramClass);
    }
  }
  
  public static <T> T getField(Class<T> paramClass, Class<?> paramClass1, Object paramObject, String paramString)
  {
    try
    {
      paramClass = AccessController.doPrivileged(new GetFieldPrivilegedAction(paramClass1.getDeclaredField(paramString), paramObject, null));
      return paramClass;
    }
    catch (NoSuchFieldException paramClass)
    {
      throw new ParcelerRuntimeException("NoSuchFieldException Exception during field injection: " + paramString + " in " + paramObject.getClass(), paramClass);
    }
    catch (PrivilegedActionException paramClass)
    {
      throw new ParcelerRuntimeException("PrivilegedActionException Exception during field injection", paramClass);
    }
    catch (Exception paramClass)
    {
      throw new ParcelerRuntimeException("Exception during field injection", paramClass);
    }
  }
  
  public static void setField(Class<?> paramClass, Object paramObject1, String paramString, Object paramObject2)
  {
    try
    {
      AccessController.doPrivileged(new SetFieldPrivilegedAction(paramClass.getDeclaredField(paramString), paramObject1, paramObject2, null));
      return;
    }
    catch (NoSuchFieldException paramClass)
    {
      throw new ParcelerRuntimeException("NoSuchFieldException Exception during field injection: " + paramString + " in " + paramObject1.getClass(), paramClass);
    }
    catch (PrivilegedActionException paramClass)
    {
      throw new ParcelerRuntimeException("PrivilegedActionException Exception during field injection", paramClass);
    }
    catch (Exception paramClass)
    {
      throw new ParcelerRuntimeException("Exception during field injection", paramClass);
    }
  }
  
  private static abstract class AccessibleElementPrivilegedAction<T, E extends AccessibleObject>
    implements PrivilegedExceptionAction<T>
  {
    private final E accessible;
    
    protected AccessibleElementPrivilegedAction(E paramE)
    {
      this.accessible = paramE;
    }
    
    public T run()
      throws Exception
    {
      boolean bool = this.accessible.isAccessible();
      this.accessible.setAccessible(true);
      Object localObject = run(this.accessible);
      this.accessible.setAccessible(bool);
      return (T)localObject;
    }
    
    public abstract T run(E paramE)
      throws Exception;
  }
  
  private static final class GetFieldPrivilegedAction<T>
    extends InjectionUtil.AccessibleElementPrivilegedAction<T, Field>
  {
    private final Object target;
    
    private GetFieldPrivilegedAction(Field paramField, Object paramObject)
    {
      super();
      this.target = paramObject;
    }
    
    public T run(Field paramField)
      throws IllegalAccessException
    {
      return (T)paramField.get(this.target);
    }
  }
  
  private static final class SetConstructorPrivilegedAction<T>
    extends InjectionUtil.AccessibleElementPrivilegedAction<T, Constructor>
  {
    private final Object[] args;
    
    private SetConstructorPrivilegedAction(Constructor paramConstructor, Object[] paramArrayOfObject)
    {
      super();
      this.args = paramArrayOfObject;
    }
    
    public T run(Constructor paramConstructor)
      throws InvocationTargetException, InstantiationException, IllegalAccessException
    {
      return (T)paramConstructor.newInstance(this.args);
    }
  }
  
  private static final class SetFieldPrivilegedAction
    extends InjectionUtil.AccessibleElementPrivilegedAction<Void, Field>
  {
    private final Object target;
    private final Object value;
    
    private SetFieldPrivilegedAction(Field paramField, Object paramObject1, Object paramObject2)
    {
      super();
      this.target = paramObject1;
      this.value = paramObject2;
    }
    
    public Void run(Field paramField)
      throws IllegalAccessException
    {
      paramField.set(this.target, this.value);
      return null;
    }
  }
  
  private static final class SetMethodPrivilegedAction<T>
    extends InjectionUtil.AccessibleElementPrivilegedAction<T, Method>
  {
    private final Object[] args;
    private final Object target;
    
    private SetMethodPrivilegedAction(Method paramMethod, Object paramObject, Object[] paramArrayOfObject)
    {
      super();
      this.target = paramObject;
      this.args = paramArrayOfObject;
    }
    
    public T run(Method paramMethod)
      throws InvocationTargetException, IllegalAccessException
    {
      return (T)paramMethod.invoke(this.target, this.args);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/InjectionUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */