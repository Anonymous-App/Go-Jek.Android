package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.internal.iz;
import com.google.android.gms.internal.ja;
import com.google.android.gms.internal.kc;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object Ks = new Object();
  private static HashSet<Uri> Kt = new HashSet();
  private static ImageManager Ku;
  private static ImageManager Kv;
  private final Map<Uri, ImageReceiver> KA;
  private final Map<Uri, Long> KB;
  private final ExecutorService Kw;
  private final b Kx;
  private final iz Ky;
  private final Map<a, ImageReceiver> Kz;
  private final Context mContext;
  private final Handler mHandler;
  
  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.Kw = Executors.newFixedThreadPool(4);
    if (paramBoolean)
    {
      this.Kx = new b(this.mContext);
      if (kc.hE()) {
        gG();
      }
    }
    for (;;)
    {
      this.Ky = new iz();
      this.Kz = new HashMap();
      this.KA = new HashMap();
      this.KB = new HashMap();
      return;
      this.Kx = null;
    }
  }
  
  private Bitmap a(a.a parama)
  {
    if (this.Kx == null) {
      return null;
    }
    return (Bitmap)this.Kx.get(parama);
  }
  
  public static ImageManager c(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (Kv == null) {
        Kv = new ImageManager(paramContext, true);
      }
      return Kv;
    }
    if (Ku == null) {
      Ku = new ImageManager(paramContext, false);
    }
    return Ku;
  }
  
  public static ImageManager create(Context paramContext)
  {
    return c(paramContext, false);
  }
  
  private void gG()
  {
    this.mContext.registerComponentCallbacks(new e(this.Kx));
  }
  
  public void a(a parama)
  {
    com.google.android.gms.common.internal.a.aT("ImageManager.loadImage() must be called in the main thread");
    new d(parama).run();
  }
  
  public void loadImage(ImageView paramImageView, int paramInt)
  {
    a(new a.b(paramImageView, paramInt));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri)
  {
    a(new a.b(paramImageView, paramUri));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    paramImageView = new a.b(paramImageView, paramUri);
    paramImageView.aw(paramInt);
    a(paramImageView);
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    a(new a.c(paramOnImageLoadedListener, paramUri));
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    paramOnImageLoadedListener = new a.c(paramOnImageLoadedListener, paramUri);
    paramOnImageLoadedListener.aw(paramInt);
    a(paramOnImageLoadedListener);
  }
  
  private final class ImageReceiver
    extends ResultReceiver
  {
    private final ArrayList<a> KC;
    private final Uri mUri;
    
    ImageReceiver(Uri paramUri)
    {
      super();
      this.mUri = paramUri;
      this.KC = new ArrayList();
    }
    
    public void b(a parama)
    {
      com.google.android.gms.common.internal.a.aT("ImageReceiver.addImageRequest() must be called in the main thread");
      this.KC.add(parama);
    }
    
    public void c(a parama)
    {
      com.google.android.gms.common.internal.a.aT("ImageReceiver.removeImageRequest() must be called in the main thread");
      this.KC.remove(parama);
    }
    
    public void gJ()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", this.mUri);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.b(ImageManager.this).sendBroadcast(localIntent);
    }
    
    public void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      paramBundle = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.f(ImageManager.this).execute(new ImageManager.c(ImageManager.this, this.mUri, paramBundle));
    }
  }
  
  public static abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }
  
  private static final class a
  {
    static int a(ActivityManager paramActivityManager)
    {
      return paramActivityManager.getLargeMemoryClass();
    }
  }
  
  private static final class b
    extends ja<a.a, Bitmap>
  {
    public b(Context paramContext)
    {
      super();
    }
    
    private static int I(Context paramContext)
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      if ((paramContext.getApplicationInfo().flags & 0x100000) != 0)
      {
        i = 1;
        if ((i == 0) || (!kc.hB())) {
          break label55;
        }
      }
      label55:
      for (int i = ImageManager.a.a(localActivityManager);; i = localActivityManager.getMemoryClass())
      {
        return (int)(i * 1048576 * 0.33F);
        i = 0;
        break;
      }
    }
    
    protected int a(a.a parama, Bitmap paramBitmap)
    {
      return paramBitmap.getHeight() * paramBitmap.getRowBytes();
    }
    
    protected void a(boolean paramBoolean, a.a parama, Bitmap paramBitmap1, Bitmap paramBitmap2)
    {
      super.entryRemoved(paramBoolean, parama, paramBitmap1, paramBitmap2);
    }
  }
  
  private final class c
    implements Runnable
  {
    private final ParcelFileDescriptor KE;
    private final Uri mUri;
    
    public c(Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
    {
      this.mUri = paramUri;
      this.KE = paramParcelFileDescriptor;
    }
    
    public void run()
    {
      com.google.android.gms.common.internal.a.aU("LoadBitmapFromDiskRunnable can't be executed in the main thread");
      boolean bool1 = false;
      boolean bool2 = false;
      Bitmap localBitmap = null;
      CountDownLatch localCountDownLatch = null;
      if (this.KE != null) {}
      try
      {
        localBitmap = BitmapFactoryInstrumentation.decodeFileDescriptor(this.KE.getFileDescriptor());
        bool1 = bool2;
        Object localObject;
        return;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        try
        {
          for (;;)
          {
            this.KE.close();
            localCountDownLatch = new CountDownLatch(1);
            ImageManager.g(ImageManager.this).post(new ImageManager.f(ImageManager.this, this.mUri, localBitmap, bool1, localCountDownLatch));
            try
            {
              localCountDownLatch.await();
              return;
            }
            catch (InterruptedException localInterruptedException)
            {
              Log.w("ImageManager", "Latch interrupted while posting " + this.mUri);
            }
            localOutOfMemoryError = localOutOfMemoryError;
            Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.mUri, localOutOfMemoryError);
            bool1 = true;
            localObject = localCountDownLatch;
          }
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            Log.e("ImageManager", "closed failed", localIOException);
          }
        }
      }
    }
  }
  
  private final class d
    implements Runnable
  {
    private final a KF;
    
    public d(a parama)
    {
      this.KF = parama;
    }
    
    public void run()
    {
      com.google.android.gms.common.internal.a.aT("LoadImageRunnable must be executed on the main thread");
      Object localObject1 = (ImageManager.ImageReceiver)ImageManager.a(ImageManager.this).get(this.KF);
      if (localObject1 != null)
      {
        ImageManager.a(ImageManager.this).remove(this.KF);
        ((ImageManager.ImageReceiver)localObject1).c(this.KF);
      }
      a.a locala = this.KF.KH;
      if (locala.uri == null)
      {
        this.KF.a(ImageManager.b(ImageManager.this), ImageManager.c(ImageManager.this), true);
        return;
      }
      localObject1 = ImageManager.a(ImageManager.this, locala);
      if (localObject1 != null)
      {
        this.KF.a(ImageManager.b(ImageManager.this), (Bitmap)localObject1, true);
        return;
      }
      localObject1 = (Long)ImageManager.d(ImageManager.this).get(locala.uri);
      if (localObject1 != null)
      {
        if (SystemClock.elapsedRealtime() - ((Long)localObject1).longValue() < 3600000L)
        {
          this.KF.a(ImageManager.b(ImageManager.this), ImageManager.c(ImageManager.this), true);
          return;
        }
        ImageManager.d(ImageManager.this).remove(locala.uri);
      }
      this.KF.a(ImageManager.b(ImageManager.this), ImageManager.c(ImageManager.this));
      ??? = (ImageManager.ImageReceiver)ImageManager.e(ImageManager.this).get(locala.uri);
      localObject1 = ???;
      if (??? == null)
      {
        localObject1 = new ImageManager.ImageReceiver(ImageManager.this, locala.uri);
        ImageManager.e(ImageManager.this).put(locala.uri, localObject1);
      }
      ((ImageManager.ImageReceiver)localObject1).b(this.KF);
      if (!(this.KF instanceof a.c)) {
        ImageManager.a(ImageManager.this).put(this.KF, localObject1);
      }
      synchronized (ImageManager.gH())
      {
        if (!ImageManager.gI().contains(locala.uri))
        {
          ImageManager.gI().add(locala.uri);
          ((ImageManager.ImageReceiver)localObject1).gJ();
        }
        return;
      }
    }
  }
  
  private static final class e
    implements ComponentCallbacks2
  {
    private final ImageManager.b Kx;
    
    public e(ImageManager.b paramb)
    {
      this.Kx = paramb;
    }
    
    public void onConfigurationChanged(Configuration paramConfiguration) {}
    
    public void onLowMemory()
    {
      this.Kx.evictAll();
    }
    
    public void onTrimMemory(int paramInt)
    {
      if (paramInt >= 60) {
        this.Kx.evictAll();
      }
      while (paramInt < 20) {
        return;
      }
      this.Kx.trimToSize(this.Kx.size() / 2);
    }
  }
  
  private final class f
    implements Runnable
  {
    private boolean KG;
    private final Bitmap mBitmap;
    private final Uri mUri;
    private final CountDownLatch mg;
    
    public f(Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
    {
      this.mUri = paramUri;
      this.mBitmap = paramBitmap;
      this.KG = paramBoolean;
      this.mg = paramCountDownLatch;
    }
    
    private void a(ImageManager.ImageReceiver paramImageReceiver, boolean paramBoolean)
    {
      paramImageReceiver = ImageManager.ImageReceiver.a(paramImageReceiver);
      int j = paramImageReceiver.size();
      int i = 0;
      if (i < j)
      {
        a locala = (a)paramImageReceiver.get(i);
        if (paramBoolean) {
          locala.a(ImageManager.b(ImageManager.this), this.mBitmap, false);
        }
        for (;;)
        {
          if (!(locala instanceof a.c)) {
            ImageManager.a(ImageManager.this).remove(locala);
          }
          i += 1;
          break;
          ImageManager.d(ImageManager.this).put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
          locala.a(ImageManager.b(ImageManager.this), ImageManager.c(ImageManager.this), false);
        }
      }
    }
    
    public void run()
    {
      com.google.android.gms.common.internal.a.aT("OnBitmapLoadedRunnable must be executed in the main thread");
      boolean bool;
      if (this.mBitmap != null) {
        bool = true;
      }
      while (ImageManager.h(ImageManager.this) != null) {
        if (this.KG)
        {
          ImageManager.h(ImageManager.this).evictAll();
          System.gc();
          this.KG = false;
          ImageManager.g(ImageManager.this).post(this);
          return;
          bool = false;
        }
        else if (bool)
        {
          ImageManager.h(ImageManager.this).put(new a.a(this.mUri), this.mBitmap);
        }
      }
      ??? = (ImageManager.ImageReceiver)ImageManager.e(ImageManager.this).remove(this.mUri);
      if (??? != null) {
        a((ImageManager.ImageReceiver)???, bool);
      }
      this.mg.countDown();
      synchronized (ImageManager.gH())
      {
        ImageManager.gI().remove(this.mUri);
        return;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/images/ImageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */