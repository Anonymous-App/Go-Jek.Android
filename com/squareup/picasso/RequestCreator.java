package com.squareup.picasso;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.RemoteViews;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestCreator
{
  private static final AtomicInteger nextId = new AtomicInteger();
  private final Request.Builder data;
  private boolean deferred;
  private Drawable errorDrawable;
  private int errorResId;
  private int memoryPolicy;
  private int networkPolicy;
  private boolean noFade;
  private final Picasso picasso;
  private Drawable placeholderDrawable;
  private int placeholderResId;
  private boolean setPlaceholder = true;
  private Object tag;
  
  RequestCreator()
  {
    this.picasso = null;
    this.data = new Request.Builder(null, 0, null);
  }
  
  RequestCreator(Picasso paramPicasso, Uri paramUri, int paramInt)
  {
    if (paramPicasso.shutdown) {
      throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
    }
    this.picasso = paramPicasso;
    this.data = new Request.Builder(paramUri, paramInt, paramPicasso.defaultBitmapConfig);
  }
  
  private Request createRequest(long paramLong)
  {
    int i = nextId.getAndIncrement();
    Request localRequest1 = this.data.build();
    localRequest1.id = i;
    localRequest1.started = paramLong;
    boolean bool = this.picasso.loggingEnabled;
    if (bool) {
      Utils.log("Main", "created", localRequest1.plainId(), localRequest1.toString());
    }
    Request localRequest2 = this.picasso.transformRequest(localRequest1);
    if (localRequest2 != localRequest1)
    {
      localRequest2.id = i;
      localRequest2.started = paramLong;
      if (bool) {
        Utils.log("Main", "changed", localRequest2.logId(), "into " + localRequest2);
      }
    }
    return localRequest2;
  }
  
  private Drawable getPlaceholderDrawable()
  {
    if (this.placeholderResId != 0) {
      return this.picasso.context.getResources().getDrawable(this.placeholderResId);
    }
    return this.placeholderDrawable;
  }
  
  private void performRemoteViewInto(RemoteViewsAction paramRemoteViewsAction)
  {
    if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy))
    {
      Bitmap localBitmap = this.picasso.quickMemoryCacheCheck(paramRemoteViewsAction.getKey());
      if (localBitmap != null)
      {
        paramRemoteViewsAction.complete(localBitmap, Picasso.LoadedFrom.MEMORY);
        return;
      }
    }
    if (this.placeholderResId != 0) {
      paramRemoteViewsAction.setImageResource(this.placeholderResId);
    }
    this.picasso.enqueueAndSubmit(paramRemoteViewsAction);
  }
  
  public RequestCreator centerCrop()
  {
    this.data.centerCrop();
    return this;
  }
  
  public RequestCreator centerInside()
  {
    this.data.centerInside();
    return this;
  }
  
  public RequestCreator config(Bitmap.Config paramConfig)
  {
    this.data.config(paramConfig);
    return this;
  }
  
  public RequestCreator error(int paramInt)
  {
    if (paramInt == 0) {
      throw new IllegalArgumentException("Error image resource invalid.");
    }
    if (this.errorDrawable != null) {
      throw new IllegalStateException("Error image already set.");
    }
    this.errorResId = paramInt;
    return this;
  }
  
  public RequestCreator error(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      throw new IllegalArgumentException("Error image may not be null.");
    }
    if (this.errorResId != 0) {
      throw new IllegalStateException("Error image already set.");
    }
    this.errorDrawable = paramDrawable;
    return this;
  }
  
  public void fetch()
  {
    fetch(null);
  }
  
  public void fetch(Callback paramCallback)
  {
    long l = System.nanoTime();
    if (this.deferred) {
      throw new IllegalStateException("Fit cannot be used with fetch.");
    }
    Request localRequest;
    String str;
    if (this.data.hasImage())
    {
      if (!this.data.hasPriority()) {
        this.data.priority(Picasso.Priority.LOW);
      }
      localRequest = createRequest(l);
      str = Utils.createKey(localRequest, new StringBuilder());
      if (this.picasso.quickMemoryCacheCheck(str) == null) {
        break label139;
      }
      if (this.picasso.loggingEnabled) {
        Utils.log("Main", "completed", localRequest.plainId(), "from " + Picasso.LoadedFrom.MEMORY);
      }
      if (paramCallback != null) {
        paramCallback.onSuccess();
      }
    }
    return;
    label139:
    paramCallback = new FetchAction(this.picasso, localRequest, this.memoryPolicy, this.networkPolicy, this.tag, str, paramCallback);
    this.picasso.submit(paramCallback);
  }
  
  public RequestCreator fit()
  {
    this.deferred = true;
    return this;
  }
  
  public Bitmap get()
    throws IOException
  {
    long l = System.nanoTime();
    Utils.checkNotMain();
    if (this.deferred) {
      throw new IllegalStateException("Fit cannot be used with get.");
    }
    if (!this.data.hasImage()) {
      return null;
    }
    Object localObject = createRequest(l);
    String str = Utils.createKey((Request)localObject, new StringBuilder());
    localObject = new GetAction(this.picasso, (Request)localObject, this.memoryPolicy, this.networkPolicy, this.tag, str);
    return BitmapHunter.forRequest(this.picasso, this.picasso.dispatcher, this.picasso.cache, this.picasso.stats, (Action)localObject).hunt();
  }
  
  public void into(ImageView paramImageView)
  {
    into(paramImageView, null);
  }
  
  public void into(ImageView paramImageView, Callback paramCallback)
  {
    long l = System.nanoTime();
    Utils.checkMain();
    if (paramImageView == null) {
      throw new IllegalArgumentException("Target must not be null.");
    }
    if (!this.data.hasImage())
    {
      this.picasso.cancelRequest(paramImageView);
      if (this.setPlaceholder) {
        PicassoDrawable.setPlaceholder(paramImageView, getPlaceholderDrawable());
      }
    }
    Request localRequest;
    String str;
    do
    {
      return;
      if (this.deferred)
      {
        if (this.data.hasSize()) {
          throw new IllegalStateException("Fit cannot be used with resize.");
        }
        int i = paramImageView.getWidth();
        int j = paramImageView.getHeight();
        if ((i == 0) || (j == 0))
        {
          if (this.setPlaceholder) {
            PicassoDrawable.setPlaceholder(paramImageView, getPlaceholderDrawable());
          }
          this.picasso.defer(paramImageView, new DeferredRequestCreator(this, paramImageView, paramCallback));
          return;
        }
        this.data.resize(i, j);
      }
      localRequest = createRequest(l);
      str = Utils.createKey(localRequest);
      if (!MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy)) {
        break;
      }
      Bitmap localBitmap = this.picasso.quickMemoryCacheCheck(str);
      if (localBitmap == null) {
        break;
      }
      this.picasso.cancelRequest(paramImageView);
      PicassoDrawable.setBitmap(paramImageView, this.picasso.context, localBitmap, Picasso.LoadedFrom.MEMORY, this.noFade, this.picasso.indicatorsEnabled);
      if (this.picasso.loggingEnabled) {
        Utils.log("Main", "completed", localRequest.plainId(), "from " + Picasso.LoadedFrom.MEMORY);
      }
    } while (paramCallback == null);
    paramCallback.onSuccess();
    return;
    if (this.setPlaceholder) {
      PicassoDrawable.setPlaceholder(paramImageView, getPlaceholderDrawable());
    }
    paramImageView = new ImageViewAction(this.picasso, paramImageView, localRequest, this.memoryPolicy, this.networkPolicy, this.errorResId, this.errorDrawable, str, this.tag, paramCallback, this.noFade);
    this.picasso.enqueueAndSubmit(paramImageView);
  }
  
  public void into(RemoteViews paramRemoteViews, int paramInt1, int paramInt2, Notification paramNotification)
  {
    long l = System.nanoTime();
    if (paramRemoteViews == null) {
      throw new IllegalArgumentException("RemoteViews must not be null.");
    }
    if (paramNotification == null) {
      throw new IllegalArgumentException("Notification must not be null.");
    }
    if (this.deferred) {
      throw new IllegalStateException("Fit cannot be used with RemoteViews.");
    }
    if ((this.placeholderDrawable != null) || (this.placeholderResId != 0) || (this.errorDrawable != null)) {
      throw new IllegalArgumentException("Cannot use placeholder or error drawables with remote views.");
    }
    Request localRequest = createRequest(l);
    String str = Utils.createKey(localRequest, new StringBuilder());
    performRemoteViewInto(new RemoteViewsAction.NotificationAction(this.picasso, localRequest, paramRemoteViews, paramInt1, paramInt2, paramNotification, this.memoryPolicy, this.networkPolicy, str, this.tag, this.errorResId));
  }
  
  public void into(RemoteViews paramRemoteViews, int paramInt, int[] paramArrayOfInt)
  {
    long l = System.nanoTime();
    if (paramRemoteViews == null) {
      throw new IllegalArgumentException("remoteViews must not be null.");
    }
    if (paramArrayOfInt == null) {
      throw new IllegalArgumentException("appWidgetIds must not be null.");
    }
    if (this.deferred) {
      throw new IllegalStateException("Fit cannot be used with remote views.");
    }
    if ((this.placeholderDrawable != null) || (this.placeholderResId != 0) || (this.errorDrawable != null)) {
      throw new IllegalArgumentException("Cannot use placeholder or error drawables with remote views.");
    }
    Request localRequest = createRequest(l);
    String str = Utils.createKey(localRequest, new StringBuilder());
    performRemoteViewInto(new RemoteViewsAction.AppWidgetAction(this.picasso, localRequest, paramRemoteViews, paramInt, paramArrayOfInt, this.memoryPolicy, this.networkPolicy, str, this.tag, this.errorResId));
  }
  
  public void into(Target paramTarget)
  {
    Object localObject = null;
    Request localRequest = null;
    long l = System.nanoTime();
    Utils.checkMain();
    if (paramTarget == null) {
      throw new IllegalArgumentException("Target must not be null.");
    }
    if (this.deferred) {
      throw new IllegalStateException("Fit cannot be used with a Target.");
    }
    if (!this.data.hasImage())
    {
      this.picasso.cancelRequest(paramTarget);
      localObject = localRequest;
      if (this.setPlaceholder) {
        localObject = getPlaceholderDrawable();
      }
      paramTarget.onPrepareLoad((Drawable)localObject);
      return;
    }
    localRequest = createRequest(l);
    String str = Utils.createKey(localRequest);
    if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy))
    {
      Bitmap localBitmap = this.picasso.quickMemoryCacheCheck(str);
      if (localBitmap != null)
      {
        this.picasso.cancelRequest(paramTarget);
        paramTarget.onBitmapLoaded(localBitmap, Picasso.LoadedFrom.MEMORY);
        return;
      }
    }
    if (this.setPlaceholder) {
      localObject = getPlaceholderDrawable();
    }
    paramTarget.onPrepareLoad((Drawable)localObject);
    paramTarget = new TargetAction(this.picasso, paramTarget, localRequest, this.memoryPolicy, this.networkPolicy, this.errorDrawable, str, this.tag, this.errorResId);
    this.picasso.enqueueAndSubmit(paramTarget);
  }
  
  public RequestCreator memoryPolicy(MemoryPolicy paramMemoryPolicy, MemoryPolicy... paramVarArgs)
  {
    if (paramMemoryPolicy == null) {
      throw new IllegalArgumentException("Memory policy cannot be null.");
    }
    this.memoryPolicy |= paramMemoryPolicy.index;
    if (paramVarArgs == null) {
      throw new IllegalArgumentException("Memory policy cannot be null.");
    }
    if (paramVarArgs.length > 0)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        paramMemoryPolicy = paramVarArgs[i];
        if (paramMemoryPolicy == null) {
          throw new IllegalArgumentException("Memory policy cannot be null.");
        }
        this.memoryPolicy |= paramMemoryPolicy.index;
        i += 1;
      }
    }
    return this;
  }
  
  public RequestCreator networkPolicy(NetworkPolicy paramNetworkPolicy, NetworkPolicy... paramVarArgs)
  {
    if (paramNetworkPolicy == null) {
      throw new IllegalArgumentException("Network policy cannot be null.");
    }
    this.networkPolicy |= paramNetworkPolicy.index;
    if (paramVarArgs == null) {
      throw new IllegalArgumentException("Network policy cannot be null.");
    }
    if (paramVarArgs.length > 0)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        paramNetworkPolicy = paramVarArgs[i];
        if (paramNetworkPolicy == null) {
          throw new IllegalArgumentException("Network policy cannot be null.");
        }
        this.networkPolicy |= paramNetworkPolicy.index;
        i += 1;
      }
    }
    return this;
  }
  
  public RequestCreator noFade()
  {
    this.noFade = true;
    return this;
  }
  
  public RequestCreator noPlaceholder()
  {
    if (this.placeholderResId != 0) {
      throw new IllegalStateException("Placeholder resource already set.");
    }
    if (this.placeholderDrawable != null) {
      throw new IllegalStateException("Placeholder image already set.");
    }
    this.setPlaceholder = false;
    return this;
  }
  
  public RequestCreator onlyScaleDown()
  {
    this.data.onlyScaleDown();
    return this;
  }
  
  public RequestCreator placeholder(int paramInt)
  {
    if (!this.setPlaceholder) {
      throw new IllegalStateException("Already explicitly declared as no placeholder.");
    }
    if (paramInt == 0) {
      throw new IllegalArgumentException("Placeholder image resource invalid.");
    }
    if (this.placeholderDrawable != null) {
      throw new IllegalStateException("Placeholder image already set.");
    }
    this.placeholderResId = paramInt;
    return this;
  }
  
  public RequestCreator placeholder(Drawable paramDrawable)
  {
    if (!this.setPlaceholder) {
      throw new IllegalStateException("Already explicitly declared as no placeholder.");
    }
    if (this.placeholderResId != 0) {
      throw new IllegalStateException("Placeholder image already set.");
    }
    this.placeholderDrawable = paramDrawable;
    return this;
  }
  
  public RequestCreator priority(Picasso.Priority paramPriority)
  {
    this.data.priority(paramPriority);
    return this;
  }
  
  public RequestCreator resize(int paramInt1, int paramInt2)
  {
    this.data.resize(paramInt1, paramInt2);
    return this;
  }
  
  public RequestCreator resizeDimen(int paramInt1, int paramInt2)
  {
    Resources localResources = this.picasso.context.getResources();
    return resize(localResources.getDimensionPixelSize(paramInt1), localResources.getDimensionPixelSize(paramInt2));
  }
  
  public RequestCreator rotate(float paramFloat)
  {
    this.data.rotate(paramFloat);
    return this;
  }
  
  public RequestCreator rotate(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.data.rotate(paramFloat1, paramFloat2, paramFloat3);
    return this;
  }
  
  @Deprecated
  public RequestCreator skipMemoryCache()
  {
    return memoryPolicy(MemoryPolicy.NO_CACHE, new MemoryPolicy[] { MemoryPolicy.NO_STORE });
  }
  
  public RequestCreator stableKey(String paramString)
  {
    this.data.stableKey(paramString);
    return this;
  }
  
  public RequestCreator tag(Object paramObject)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException("Tag invalid.");
    }
    if (this.tag != null) {
      throw new IllegalStateException("Tag already set.");
    }
    this.tag = paramObject;
    return this;
  }
  
  public RequestCreator transform(Transformation paramTransformation)
  {
    this.data.transform(paramTransformation);
    return this;
  }
  
  RequestCreator unfit()
  {
    this.deferred = false;
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/squareup/picasso/RequestCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */