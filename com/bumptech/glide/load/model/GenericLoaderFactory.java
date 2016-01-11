package com.bumptech.glide.load.model;

import android.content.Context;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GenericLoaderFactory
{
  private static final ModelLoader NULL_MODEL_LOADER = new GenericLoaderFactory.1();
  private final Map<Class, Map<Class, ModelLoader>> cachedModelLoaders = new HashMap();
  private final Context context;
  private final Map<Class, Map<Class, ModelLoaderFactory>> modelClassToResourceFactories = new HashMap();
  
  public GenericLoaderFactory(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }
  
  private <T, Y> void cacheModelLoader(Class<T> paramClass, Class<Y> paramClass1, ModelLoader<T, Y> paramModelLoader)
  {
    Map localMap = (Map)this.cachedModelLoaders.get(paramClass);
    Object localObject = localMap;
    if (localMap == null)
    {
      localObject = new HashMap();
      this.cachedModelLoaders.put(paramClass, localObject);
    }
    ((Map)localObject).put(paramClass1, paramModelLoader);
  }
  
  private <T, Y> void cacheNullLoader(Class<T> paramClass, Class<Y> paramClass1)
  {
    cacheModelLoader(paramClass, paramClass1, NULL_MODEL_LOADER);
  }
  
  private <T, Y> ModelLoader<T, Y> getCachedLoader(Class<T> paramClass, Class<Y> paramClass1)
  {
    Map localMap = (Map)this.cachedModelLoaders.get(paramClass);
    paramClass = null;
    if (localMap != null) {
      paramClass = (ModelLoader)localMap.get(paramClass1);
    }
    return paramClass;
  }
  
  private <T, Y> ModelLoaderFactory<T, Y> getFactory(Class<T> paramClass, Class<Y> paramClass1)
  {
    Object localObject2 = (Map)this.modelClassToResourceFactories.get(paramClass);
    Object localObject1 = null;
    if (localObject2 != null) {
      localObject1 = (ModelLoaderFactory)((Map)localObject2).get(paramClass1);
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      Iterator localIterator = this.modelClassToResourceFactories.keySet().iterator();
      do
      {
        do
        {
          do
          {
            localObject2 = localObject1;
            if (!localIterator.hasNext()) {
              break;
            }
            localObject2 = (Class)localIterator.next();
          } while (!((Class)localObject2).isAssignableFrom(paramClass));
          localObject2 = (Map)this.modelClassToResourceFactories.get(localObject2);
        } while (localObject2 == null);
        localObject2 = (ModelLoaderFactory)((Map)localObject2).get(paramClass1);
        localObject1 = localObject2;
      } while (localObject2 == null);
    }
    return (ModelLoaderFactory<T, Y>)localObject2;
  }
  
  public <T, Y> ModelLoader<T, Y> buildModelLoader(Class<T> paramClass, Class<Y> paramClass1)
  {
    for (;;)
    {
      try
      {
        ModelLoader localModelLoader = getCachedLoader(paramClass, paramClass1);
        if (localModelLoader != null)
        {
          boolean bool = NULL_MODEL_LOADER.equals(localModelLoader);
          if (bool)
          {
            paramClass = null;
            return paramClass;
          }
          paramClass = localModelLoader;
          continue;
        }
        ModelLoaderFactory localModelLoaderFactory = getFactory(paramClass, paramClass1);
        if (localModelLoaderFactory != null)
        {
          localModelLoader = localModelLoaderFactory.build(this.context, this);
          cacheModelLoader(paramClass, paramClass1, localModelLoader);
          paramClass = localModelLoader;
        }
        else
        {
          cacheNullLoader(paramClass, paramClass1);
          paramClass = localModelLoader;
        }
      }
      finally {}
    }
  }
  
  @Deprecated
  public <T, Y> ModelLoader<T, Y> buildModelLoader(Class<T> paramClass, Class<Y> paramClass1, Context paramContext)
  {
    try
    {
      paramClass = buildModelLoader(paramClass, paramClass1);
      return paramClass;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  public <T, Y> ModelLoaderFactory<T, Y> register(Class<T> paramClass, Class<Y> paramClass1, ModelLoaderFactory<T, Y> paramModelLoaderFactory)
  {
    try
    {
      this.cachedModelLoaders.clear();
      Map localMap = (Map)this.modelClassToResourceFactories.get(paramClass);
      Object localObject = localMap;
      if (localMap == null)
      {
        localObject = new HashMap();
        this.modelClassToResourceFactories.put(paramClass, localObject);
      }
      paramClass1 = (ModelLoaderFactory)((Map)localObject).put(paramClass1, paramModelLoaderFactory);
      paramClass = paramClass1;
      if (paramClass1 != null)
      {
        paramModelLoaderFactory = this.modelClassToResourceFactories.values().iterator();
        boolean bool;
        do
        {
          paramClass = paramClass1;
          if (!paramModelLoaderFactory.hasNext()) {
            break;
          }
          bool = ((Map)paramModelLoaderFactory.next()).containsValue(paramClass1);
        } while (!bool);
        paramClass = null;
      }
      return paramClass;
    }
    finally {}
  }
  
  public <T, Y> ModelLoaderFactory<T, Y> unregister(Class<T> paramClass, Class<Y> paramClass1)
  {
    try
    {
      this.cachedModelLoaders.clear();
      Object localObject = null;
      Map localMap = (Map)this.modelClassToResourceFactories.get(paramClass);
      paramClass = (Class<T>)localObject;
      if (localMap != null) {
        paramClass = (ModelLoaderFactory)localMap.remove(paramClass1);
      }
      return paramClass;
    }
    finally {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/load/model/GenericLoaderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */