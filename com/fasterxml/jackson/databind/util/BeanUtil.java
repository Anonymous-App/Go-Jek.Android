package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class BeanUtil
{
  protected static boolean isCglibGetCallbacks(AnnotatedMethod paramAnnotatedMethod)
  {
    paramAnnotatedMethod = paramAnnotatedMethod.getRawType();
    if ((paramAnnotatedMethod == null) || (!paramAnnotatedMethod.isArray())) {}
    do
    {
      do
      {
        return false;
        paramAnnotatedMethod = paramAnnotatedMethod.getComponentType().getPackage();
      } while (paramAnnotatedMethod == null);
      paramAnnotatedMethod = paramAnnotatedMethod.getName();
    } while ((!paramAnnotatedMethod.contains(".cglib")) || ((!paramAnnotatedMethod.startsWith("net.sf.cglib")) && (!paramAnnotatedMethod.startsWith("org.hibernate.repackage.cglib")) && (!paramAnnotatedMethod.startsWith("org.springframework.cglib"))));
    return true;
  }
  
  protected static boolean isGroovyMetaClassGetter(AnnotatedMethod paramAnnotatedMethod)
  {
    paramAnnotatedMethod = paramAnnotatedMethod.getRawType();
    if ((paramAnnotatedMethod == null) || (paramAnnotatedMethod.isArray())) {}
    do
    {
      return false;
      paramAnnotatedMethod = paramAnnotatedMethod.getPackage();
    } while ((paramAnnotatedMethod == null) || (!paramAnnotatedMethod.getName().startsWith("groovy.lang")));
    return true;
  }
  
  protected static boolean isGroovyMetaClassSetter(AnnotatedMethod paramAnnotatedMethod)
  {
    boolean bool2 = false;
    paramAnnotatedMethod = paramAnnotatedMethod.getRawParameterType(0).getPackage();
    boolean bool1 = bool2;
    if (paramAnnotatedMethod != null)
    {
      bool1 = bool2;
      if (paramAnnotatedMethod.getName().startsWith("groovy.lang")) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  protected static String legacyManglePropertyName(String paramString, int paramInt)
  {
    int j = paramString.length();
    if (j == paramInt) {
      return null;
    }
    Object localObject1 = null;
    int i = paramInt;
    for (;;)
    {
      char c2;
      if (i < j)
      {
        char c1 = paramString.charAt(i);
        c2 = Character.toLowerCase(c1);
        if (c1 != c2) {}
      }
      else
      {
        if (localObject1 != null) {
          break;
        }
        return paramString.substring(paramInt);
      }
      Object localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = new StringBuilder(j - paramInt);
        ((StringBuilder)localObject2).append(paramString, paramInt, j);
      }
      ((StringBuilder)localObject2).setCharAt(i - paramInt, c2);
      i += 1;
      localObject1 = localObject2;
    }
    return ((StringBuilder)localObject1).toString();
  }
  
  @Deprecated
  public static String okNameForGetter(AnnotatedMethod paramAnnotatedMethod)
  {
    return okNameForGetter(paramAnnotatedMethod, false);
  }
  
  public static String okNameForGetter(AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean)
  {
    String str3 = paramAnnotatedMethod.getName();
    String str2 = okNameForIsGetter(paramAnnotatedMethod, str3, paramBoolean);
    String str1 = str2;
    if (str2 == null) {
      str1 = okNameForRegularGetter(paramAnnotatedMethod, str3, paramBoolean);
    }
    return str1;
  }
  
  @Deprecated
  public static String okNameForIsGetter(AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return okNameForIsGetter(paramAnnotatedMethod, paramString, false);
  }
  
  public static String okNameForIsGetter(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean)
  {
    if (paramString.startsWith("is"))
    {
      paramAnnotatedMethod = paramAnnotatedMethod.getRawType();
      if ((paramAnnotatedMethod == Boolean.class) || (paramAnnotatedMethod == Boolean.TYPE))
      {
        if (paramBoolean) {
          return stdManglePropertyName(paramString, 2);
        }
        return legacyManglePropertyName(paramString, 2);
      }
    }
    return null;
  }
  
  @Deprecated
  public static String okNameForMutator(AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return okNameForMutator(paramAnnotatedMethod, paramString, false);
  }
  
  public static String okNameForMutator(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean)
  {
    paramAnnotatedMethod = paramAnnotatedMethod.getName();
    if (paramAnnotatedMethod.startsWith(paramString))
    {
      if (paramBoolean) {
        return stdManglePropertyName(paramAnnotatedMethod, paramString.length());
      }
      return legacyManglePropertyName(paramAnnotatedMethod, paramString.length());
    }
    return null;
  }
  
  @Deprecated
  public static String okNameForRegularGetter(AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return okNameForRegularGetter(paramAnnotatedMethod, paramString, false);
  }
  
  public static String okNameForRegularGetter(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean)
  {
    if (paramString.startsWith("get"))
    {
      if (!"getCallbacks".equals(paramString)) {
        break label27;
      }
      if (!isCglibGetCallbacks(paramAnnotatedMethod)) {
        break label43;
      }
    }
    label27:
    while (("getMetaClass".equals(paramString)) && (isGroovyMetaClassGetter(paramAnnotatedMethod))) {
      return null;
    }
    label43:
    if (paramBoolean) {
      return stdManglePropertyName(paramString, 3);
    }
    return legacyManglePropertyName(paramString, 3);
  }
  
  @Deprecated
  public static String okNameForSetter(AnnotatedMethod paramAnnotatedMethod)
  {
    return okNameForSetter(paramAnnotatedMethod, false);
  }
  
  public static String okNameForSetter(AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean)
  {
    String str = okNameForMutator(paramAnnotatedMethod, "set", paramBoolean);
    if ((str != null) && ((!"metaClass".equals(str)) || (!isGroovyMetaClassSetter(paramAnnotatedMethod)))) {
      return str;
    }
    return null;
  }
  
  protected static String stdManglePropertyName(String paramString, int paramInt)
  {
    int i = paramString.length();
    if (i == paramInt) {
      return null;
    }
    char c1 = paramString.charAt(paramInt);
    char c2 = Character.toLowerCase(c1);
    if (c1 == c2) {
      return paramString.substring(paramInt);
    }
    if ((paramInt + 1 < i) && (Character.isUpperCase(paramString.charAt(paramInt + 1)))) {
      return paramString.substring(paramInt);
    }
    StringBuilder localStringBuilder = new StringBuilder(i - paramInt);
    localStringBuilder.append(c2);
    localStringBuilder.append(paramString, paramInt + 1, i);
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/util/BeanUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */