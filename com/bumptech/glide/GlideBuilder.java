package com.bumptech.glide;

import android.content.Context;
import android.os.Build.VERSION;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCache.Factory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor;
import java.util.concurrent.ExecutorService;

public class GlideBuilder
{
  private BitmapPool bitmapPool;
  private final Context context;
  private DecodeFormat decodeFormat;
  private DiskCache.Factory diskCacheFactory;
  private ExecutorService diskCacheService;
  private Engine engine;
  private MemoryCache memoryCache;
  private ExecutorService sourceService;
  
  public GlideBuilder(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }
  
  Glide createGlide()
  {
    if (this.sourceService == null) {
      this.sourceService = new FifoPriorityThreadPoolExecutor(Math.max(1, Runtime.getRuntime().availableProcessors()));
    }
    if (this.diskCacheService == null) {
      this.diskCacheService = new FifoPriorityThreadPoolExecutor(1);
    }
    MemorySizeCalculator localMemorySizeCalculator = new MemorySizeCalculator(this.context);
    if (this.bitmapPool == null) {
      if (Build.VERSION.SDK_INT < 11) {
        break label209;
      }
    }
    label209:
    for (this.bitmapPool = new LruBitmapPool(localMemorySizeCalculator.getBitmapPoolSize());; this.bitmapPool = new BitmapPoolAdapter())
    {
      if (this.memoryCache == null) {
        this.memoryCache = new LruResourceCache(localMemorySizeCalculator.getMemoryCacheSize());
      }
      if (this.diskCacheFactory == null) {
        this.diskCacheFactory = new InternalCacheDiskCacheFactory(this.context);
      }
      if (this.engine == null) {
        this.engine = new Engine(this.memoryCache, this.diskCacheFactory, this.diskCacheService, this.sourceService);
      }
      if (this.decodeFormat == null) {
        this.decodeFormat = DecodeFormat.DEFAULT;
      }
      return new Glide(this.engine, this.memoryCache, this.bitmapPool, this.context, this.decodeFormat);
    }
  }
  
  public GlideBuilder setBitmapPool(BitmapPool paramBitmapPool)
  {
    this.bitmapPool = paramBitmapPool;
    return this;
  }
  
  public GlideBuilder setDecodeFormat(DecodeFormat paramDecodeFormat)
  {
    this.decodeFormat = paramDecodeFormat;
    return this;
  }
  
  public GlideBuilder setDiskCache(DiskCache.Factory paramFactory)
  {
    this.diskCacheFactory = paramFactory;
    return this;
  }
  
  @Deprecated
  public GlideBuilder setDiskCache(DiskCache paramDiskCache)
  {
    return setDiskCache(new GlideBuilder.1(this, paramDiskCache));
  }
  
  public GlideBuilder setDiskCacheService(ExecutorService paramExecutorService)
  {
    this.diskCacheService = paramExecutorService;
    return this;
  }
  
  GlideBuilder setEngine(Engine paramEngine)
  {
    this.engine = paramEngine;
    return this;
  }
  
  public GlideBuilder setMemoryCache(MemoryCache paramMemoryCache)
  {
    this.memoryCache = paramMemoryCache;
    return this;
  }
  
  public GlideBuilder setResizeService(ExecutorService paramExecutorService)
  {
    this.sourceService = paramExecutorService;
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/GlideBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */