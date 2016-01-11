package org.springframework.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class ClassUtils
{
  public static final String ARRAY_SUFFIX = "[]";
  public static final String CGLIB_CLASS_SEPARATOR = "$$";
  public static final String CLASS_FILE_SUFFIX = ".class";
  private static final char INNER_CLASS_SEPARATOR = '$';
  private static final String INTERNAL_ARRAY_PREFIX = "[";
  private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
  private static final char PACKAGE_SEPARATOR = '.';
  private static final Map<String, Class<?>> commonClassCache;
  private static final Map<String, Class<?>> primitiveTypeNameMap;
  private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap;
  private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new HashMap(8);
  
  static
  {
    primitiveTypeToWrapperMap = new HashMap(8);
    primitiveTypeNameMap = new HashMap(32);
    commonClassCache = new HashMap(32);
    primitiveWrapperTypeMap.put(Boolean.class, Boolean.TYPE);
    primitiveWrapperTypeMap.put(Byte.class, Byte.TYPE);
    primitiveWrapperTypeMap.put(Character.class, Character.TYPE);
    primitiveWrapperTypeMap.put(Double.class, Double.TYPE);
    primitiveWrapperTypeMap.put(Float.class, Float.TYPE);
    primitiveWrapperTypeMap.put(Integer.class, Integer.TYPE);
    primitiveWrapperTypeMap.put(Long.class, Long.TYPE);
    primitiveWrapperTypeMap.put(Short.class, Short.TYPE);
    Object localObject1 = primitiveWrapperTypeMap.entrySet().iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)localObject1).next();
      primitiveTypeToWrapperMap.put(((Map.Entry)localObject2).getValue(), ((Map.Entry)localObject2).getKey());
      registerCommonClasses(new Class[] { (Class)((Map.Entry)localObject2).getKey() });
    }
    localObject1 = new HashSet(32);
    ((Set)localObject1).addAll(primitiveWrapperTypeMap.values());
    ((Set)localObject1).addAll(Arrays.asList(new Class[] { boolean[].class, byte[].class, char[].class, double[].class, float[].class, int[].class, long[].class, short[].class }));
    ((Set)localObject1).add(Void.TYPE);
    localObject1 = ((Set)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Class)((Iterator)localObject1).next();
      primitiveTypeNameMap.put(((Class)localObject2).getName(), localObject2);
    }
    registerCommonClasses(new Class[] { Boolean[].class, Byte[].class, Character[].class, Double[].class, Float[].class, Integer[].class, Long[].class, Short[].class });
    registerCommonClasses(new Class[] { Number.class, Number[].class, String.class, String[].class, Object.class, Object[].class, Class.class, Class[].class });
    registerCommonClasses(new Class[] { Throwable.class, Exception.class, RuntimeException.class, Error.class, StackTraceElement.class, StackTraceElement[].class });
  }
  
  public static String addResourcePathToPackagePath(Class<?> paramClass, String paramString)
  {
    Assert.notNull(paramString, "Resource name must not be null");
    if (!paramString.startsWith("/")) {
      return classPackageAsResourcePath(paramClass) + "/" + paramString;
    }
    return classPackageAsResourcePath(paramClass) + paramString;
  }
  
  public static String classNamesToString(Collection<Class> paramCollection)
  {
    if (CollectionUtils.isEmpty(paramCollection)) {
      return "[]";
    }
    StringBuilder localStringBuilder = new StringBuilder("[");
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      localStringBuilder.append(((Class)paramCollection.next()).getName());
      if (paramCollection.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public static String classNamesToString(Class... paramVarArgs)
  {
    return classNamesToString(Arrays.asList(paramVarArgs));
  }
  
  public static String classPackageAsResourcePath(Class<?> paramClass)
  {
    if (paramClass == null) {
      return "";
    }
    paramClass = paramClass.getName();
    int i = paramClass.lastIndexOf('.');
    if (i == -1) {
      return "";
    }
    return paramClass.substring(0, i).replace('.', '/');
  }
  
  public static String convertClassNameToResourcePath(String paramString)
  {
    Assert.notNull(paramString, "Class name must not be null");
    return paramString.replace('.', '/');
  }
  
  public static String convertResourcePathToClassName(String paramString)
  {
    Assert.notNull(paramString, "Resource path must not be null");
    return paramString.replace('/', '.');
  }
  
  public static Class<?> createCompositeInterface(Class<?>[] paramArrayOfClass, ClassLoader paramClassLoader)
  {
    Assert.notEmpty(paramArrayOfClass, "Interfaces must not be empty");
    Assert.notNull(paramClassLoader, "ClassLoader must not be null");
    return Proxy.getProxyClass(paramClassLoader, paramArrayOfClass);
  }
  
  @Deprecated
  public static Class<?> forName(String paramString)
    throws ClassNotFoundException, LinkageError
  {
    return forName(paramString, getDefaultClassLoader());
  }
  
  public static Class<?> forName(String paramString, ClassLoader paramClassLoader)
    throws ClassNotFoundException, LinkageError
  {
    Assert.notNull(paramString, "Name must not be null");
    Class localClass = resolvePrimitiveClassName(paramString);
    Object localObject = localClass;
    if (localClass == null) {
      localObject = (Class)commonClassCache.get(paramString);
    }
    if (localObject != null) {
      return (Class<?>)localObject;
    }
    if (paramString.endsWith("[]")) {
      return Array.newInstance(forName(paramString.substring(0, paramString.length() - "[]".length()), paramClassLoader), 0).getClass();
    }
    if ((paramString.startsWith("[L")) && (paramString.endsWith(";"))) {
      return Array.newInstance(forName(paramString.substring("[L".length(), paramString.length() - 1), paramClassLoader), 0).getClass();
    }
    if (paramString.startsWith("[")) {
      return Array.newInstance(forName(paramString.substring("[".length()), paramClassLoader), 0).getClass();
    }
    localObject = paramClassLoader;
    paramClassLoader = (ClassLoader)localObject;
    if (localObject == null) {
      paramClassLoader = getDefaultClassLoader();
    }
    try
    {
      localObject = paramClassLoader.loadClass(paramString);
      return (Class<?>)localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      int i = paramString.lastIndexOf('.');
      if (i != -1)
      {
        paramString = paramString.substring(0, i) + '$' + paramString.substring(i + 1);
        try
        {
          paramString = paramClassLoader.loadClass(paramString);
          return paramString;
        }
        catch (ClassNotFoundException paramString) {}
      }
      throw localClassNotFoundException;
    }
  }
  
  public static Class[] getAllInterfaces(Object paramObject)
  {
    Assert.notNull(paramObject, "Instance must not be null");
    return getAllInterfacesForClass(paramObject.getClass());
  }
  
  public static Set<Class> getAllInterfacesAsSet(Object paramObject)
  {
    Assert.notNull(paramObject, "Instance must not be null");
    return getAllInterfacesForClassAsSet(paramObject.getClass());
  }
  
  public static Class<?>[] getAllInterfacesForClass(Class<?> paramClass)
  {
    return getAllInterfacesForClass(paramClass, null);
  }
  
  public static Class<?>[] getAllInterfacesForClass(Class<?> paramClass, ClassLoader paramClassLoader)
  {
    paramClass = getAllInterfacesForClassAsSet(paramClass, paramClassLoader);
    return (Class[])paramClass.toArray(new Class[paramClass.size()]);
  }
  
  public static Set<Class> getAllInterfacesForClassAsSet(Class paramClass)
  {
    return getAllInterfacesForClassAsSet(paramClass, null);
  }
  
  public static Set<Class> getAllInterfacesForClassAsSet(Class paramClass, ClassLoader paramClassLoader)
  {
    Assert.notNull(paramClass, "Class must not be null");
    Object localObject;
    if ((paramClass.isInterface()) && (isVisible(paramClass, paramClassLoader)))
    {
      localObject = Collections.singleton(paramClass);
      return (Set<Class>)localObject;
    }
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    for (;;)
    {
      localObject = localLinkedHashSet;
      if (paramClass == null) {
        break;
      }
      localObject = paramClass.getInterfaces();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localLinkedHashSet.addAll(getAllInterfacesForClassAsSet(localObject[i], paramClassLoader));
        i += 1;
      }
      paramClass = paramClass.getSuperclass();
    }
  }
  
  public static String getClassFileName(Class<?> paramClass)
  {
    Assert.notNull(paramClass, "Class must not be null");
    paramClass = paramClass.getName();
    int i = paramClass.lastIndexOf('.');
    return paramClass.substring(i + 1) + ".class";
  }
  
  public static <T> Constructor<T> getConstructorIfAvailable(Class<T> paramClass, Class<?>... paramVarArgs)
  {
    Assert.notNull(paramClass, "Class must not be null");
    try
    {
      paramClass = paramClass.getConstructor(paramVarArgs);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass) {}
    return null;
  }
  
  public static ClassLoader getDefaultClassLoader()
  {
    Object localObject1 = null;
    try
    {
      localObject2 = Thread.currentThread().getContextClassLoader();
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = ClassUtils.class.getClassLoader();
    }
    return (ClassLoader)localObject2;
  }
  
  public static String getDescriptiveType(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    Object localObject = paramObject.getClass();
    if (Proxy.isProxyClass((Class)localObject))
    {
      paramObject = new StringBuilder(((Class)localObject).getName());
      ((StringBuilder)paramObject).append(" implementing ");
      localObject = ((Class)localObject).getInterfaces();
      int i = 0;
      while (i < localObject.length)
      {
        ((StringBuilder)paramObject).append(localObject[i].getName());
        if (i < localObject.length - 1) {
          ((StringBuilder)paramObject).append(',');
        }
        i += 1;
      }
      return ((StringBuilder)paramObject).toString();
    }
    if (((Class)localObject).isArray()) {
      return getQualifiedNameForArray((Class)localObject);
    }
    return ((Class)localObject).getName();
  }
  
  public static Method getMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    Assert.notNull(paramClass, "Class must not be null");
    Assert.notNull(paramString, "Method name must not be null");
    try
    {
      paramClass = paramClass.getMethod(paramString, paramVarArgs);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      throw new IllegalStateException("Expected method not found: " + paramClass);
    }
  }
  
  public static int getMethodCountForName(Class<?> paramClass, String paramString)
  {
    Assert.notNull(paramClass, "Class must not be null");
    Assert.notNull(paramString, "Method name must not be null");
    int i = 0;
    Object localObject = paramClass.getDeclaredMethods();
    int m = localObject.length;
    int j = 0;
    while (j < m)
    {
      k = i;
      if (paramString.equals(localObject[j].getName())) {
        k = i + 1;
      }
      j += 1;
      i = k;
    }
    localObject = paramClass.getInterfaces();
    int k = localObject.length;
    j = 0;
    while (j < k)
    {
      i += getMethodCountForName(localObject[j], paramString);
      j += 1;
    }
    j = i;
    if (paramClass.getSuperclass() != null) {
      j = i + getMethodCountForName(paramClass.getSuperclass(), paramString);
    }
    return j;
  }
  
  public static Method getMethodIfAvailable(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    Assert.notNull(paramClass, "Class must not be null");
    Assert.notNull(paramString, "Method name must not be null");
    try
    {
      paramClass = paramClass.getMethod(paramString, paramVarArgs);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass) {}
    return null;
  }
  
  public static String getPackageName(Class<?> paramClass)
  {
    Assert.notNull(paramClass, "Class must not be null");
    paramClass = paramClass.getName();
    int i = paramClass.lastIndexOf('.');
    if (i != -1) {
      return paramClass.substring(0, i);
    }
    return "";
  }
  
  public static String getQualifiedMethodName(Method paramMethod)
  {
    Assert.notNull(paramMethod, "Method must not be null");
    return paramMethod.getDeclaringClass().getName() + "." + paramMethod.getName();
  }
  
  public static String getQualifiedName(Class<?> paramClass)
  {
    Assert.notNull(paramClass, "Class must not be null");
    if (paramClass.isArray()) {
      return getQualifiedNameForArray(paramClass);
    }
    return paramClass.getName();
  }
  
  private static String getQualifiedNameForArray(Class<?> paramClass)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (paramClass.isArray())
    {
      paramClass = paramClass.getComponentType();
      localStringBuilder.append("[]");
    }
    localStringBuilder.insert(0, paramClass.getName());
    return localStringBuilder.toString();
  }
  
  public static String getShortName(Class<?> paramClass)
  {
    return getShortName(getQualifiedName(paramClass));
  }
  
  public static String getShortName(String paramString)
  {
    Assert.hasLength(paramString, "Class name must not be empty");
    int k = paramString.lastIndexOf('.');
    int j = paramString.indexOf("$$");
    int i = j;
    if (j == -1) {
      i = paramString.length();
    }
    return paramString.substring(k + 1, i).replace('$', '.');
  }
  
  public static Method getStaticMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    Assert.notNull(paramClass, "Class must not be null");
    Assert.notNull(paramString, "Method name must not be null");
    try
    {
      paramClass = paramClass.getMethod(paramString, paramVarArgs);
      boolean bool = Modifier.isStatic(paramClass.getModifiers());
      if (bool) {
        return paramClass;
      }
      return null;
    }
    catch (NoSuchMethodException paramClass) {}
    return null;
  }
  
  public static Class<?> getUserClass(Class<?> paramClass)
  {
    if ((paramClass != null) && (paramClass.getName().contains("$$")))
    {
      Class localClass = paramClass.getSuperclass();
      if ((localClass != null) && (!Object.class.equals(localClass))) {
        return localClass;
      }
    }
    return paramClass;
  }
  
  public static Class<?> getUserClass(Object paramObject)
  {
    Assert.notNull(paramObject, "Instance must not be null");
    return getUserClass(paramObject.getClass());
  }
  
  public static boolean hasAtLeastOneMethodWithName(Class<?> paramClass, String paramString)
  {
    Assert.notNull(paramClass, "Class must not be null");
    Assert.notNull(paramString, "Method name must not be null");
    Object localObject = paramClass.getDeclaredMethods();
    int j = localObject.length;
    int i = 0;
    if (i < j) {
      if (!localObject[i].getName().equals(paramString)) {}
    }
    label89:
    do
    {
      return true;
      i += 1;
      break;
      localObject = paramClass.getInterfaces();
      j = localObject.length;
      i = 0;
      for (;;)
      {
        if (i >= j) {
          break label89;
        }
        if (hasAtLeastOneMethodWithName(localObject[i], paramString)) {
          break;
        }
        i += 1;
      }
    } while ((paramClass.getSuperclass() != null) && (hasAtLeastOneMethodWithName(paramClass.getSuperclass(), paramString)));
    return false;
  }
  
  public static boolean hasConstructor(Class<?> paramClass, Class<?>... paramVarArgs)
  {
    return getConstructorIfAvailable(paramClass, paramVarArgs) != null;
  }
  
  public static boolean hasMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    return getMethodIfAvailable(paramClass, paramString, paramVarArgs) != null;
  }
  
  public static boolean isAssignable(Class<?> paramClass1, Class<?> paramClass2)
  {
    Assert.notNull(paramClass1, "Left-hand side type must not be null");
    Assert.notNull(paramClass2, "Right-hand side type must not be null");
    if (paramClass1.isAssignableFrom(paramClass2)) {}
    do
    {
      return true;
      if (!paramClass1.isPrimitive()) {
        break;
      }
      paramClass2 = (Class)primitiveWrapperTypeMap.get(paramClass2);
    } while ((paramClass2 != null) && (paramClass1.equals(paramClass2)));
    do
    {
      return false;
      paramClass2 = (Class)primitiveTypeToWrapperMap.get(paramClass2);
    } while ((paramClass2 == null) || (!paramClass1.isAssignableFrom(paramClass2)));
    return true;
  }
  
  public static boolean isAssignableValue(Class<?> paramClass, Object paramObject)
  {
    Assert.notNull(paramClass, "Type must not be null");
    if (paramObject != null) {
      return isAssignable(paramClass, paramObject.getClass());
    }
    return !paramClass.isPrimitive();
  }
  
  public static boolean isCacheSafe(Class<?> paramClass, ClassLoader paramClassLoader)
  {
    Assert.notNull(paramClass, "Class must not be null");
    ClassLoader localClassLoader = paramClass.getClassLoader();
    if (localClassLoader == null) {}
    do
    {
      while (paramClass == null)
      {
        return false;
        paramClass = paramClassLoader;
        if (paramClassLoader == localClassLoader) {
          return true;
        }
      }
      paramClassLoader = paramClass.getParent();
      paramClass = paramClassLoader;
    } while (paramClassLoader != localClassLoader);
    return true;
  }
  
  @Deprecated
  public static boolean isPresent(String paramString)
  {
    return isPresent(paramString, getDefaultClassLoader());
  }
  
  public static boolean isPresent(String paramString, ClassLoader paramClassLoader)
  {
    try
    {
      forName(paramString, paramClassLoader);
      return true;
    }
    catch (Throwable paramString) {}
    return false;
  }
  
  public static boolean isPrimitiveArray(Class<?> paramClass)
  {
    Assert.notNull(paramClass, "Class must not be null");
    return (paramClass.isArray()) && (paramClass.getComponentType().isPrimitive());
  }
  
  public static boolean isPrimitiveOrWrapper(Class<?> paramClass)
  {
    Assert.notNull(paramClass, "Class must not be null");
    return (paramClass.isPrimitive()) || (isPrimitiveWrapper(paramClass));
  }
  
  public static boolean isPrimitiveWrapper(Class<?> paramClass)
  {
    Assert.notNull(paramClass, "Class must not be null");
    return primitiveWrapperTypeMap.containsKey(paramClass);
  }
  
  public static boolean isPrimitiveWrapperArray(Class<?> paramClass)
  {
    Assert.notNull(paramClass, "Class must not be null");
    return (paramClass.isArray()) && (isPrimitiveWrapper(paramClass.getComponentType()));
  }
  
  public static boolean isVisible(Class<?> paramClass, ClassLoader paramClassLoader)
  {
    if (paramClassLoader == null) {}
    for (;;)
    {
      return true;
      try
      {
        paramClassLoader = paramClassLoader.loadClass(paramClass.getName());
        if (paramClass != paramClassLoader) {
          return false;
        }
      }
      catch (ClassNotFoundException paramClass) {}
    }
    return false;
  }
  
  public static boolean matchesTypeName(Class<?> paramClass, String paramString)
  {
    return (paramString != null) && ((paramString.equals(paramClass.getName())) || (paramString.equals(paramClass.getSimpleName())) || ((paramClass.isArray()) && (paramString.equals(getQualifiedNameForArray(paramClass)))));
  }
  
  public static ClassLoader overrideThreadContextClassLoader(ClassLoader paramClassLoader)
  {
    Thread localThread = Thread.currentThread();
    ClassLoader localClassLoader = localThread.getContextClassLoader();
    if ((paramClassLoader != null) && (!paramClassLoader.equals(localClassLoader)))
    {
      localThread.setContextClassLoader(paramClassLoader);
      return localClassLoader;
    }
    return null;
  }
  
  private static void registerCommonClasses(Class<?>... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      Class<?> localClass = paramVarArgs[i];
      commonClassCache.put(localClass.getName(), localClass);
      i += 1;
    }
  }
  
  public static Class<?> resolveClassName(String paramString, ClassLoader paramClassLoader)
    throws IllegalArgumentException
  {
    try
    {
      paramClassLoader = forName(paramString, paramClassLoader);
      return paramClassLoader;
    }
    catch (ClassNotFoundException paramClassLoader)
    {
      throw new IllegalArgumentException("Cannot find class [" + paramString + "]", paramClassLoader);
    }
    catch (LinkageError paramClassLoader)
    {
      throw new IllegalArgumentException("Error loading class [" + paramString + "]: problem with class file or dependent class.", paramClassLoader);
    }
  }
  
  public static Class<?> resolvePrimitiveClassName(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (paramString.length() <= 8) {
        localObject1 = (Class)primitiveTypeNameMap.get(paramString);
      }
    }
    return (Class<?>)localObject1;
  }
  
  public static Class<?> resolvePrimitiveIfNecessary(Class<?> paramClass)
  {
    Assert.notNull(paramClass, "Class must not be null");
    if ((paramClass.isPrimitive()) && (paramClass != Void.TYPE)) {
      return (Class)primitiveTypeToWrapperMap.get(paramClass);
    }
    return paramClass;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/springframework/util/ClassUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */