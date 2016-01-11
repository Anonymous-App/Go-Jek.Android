package com.bumptech.glide;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.model.ImageVideoModelLoader;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.provider.FixedLoadProvider;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import java.io.File;
import java.io.InputStream;

public class DrawableTypeRequest<ModelType>
  extends DrawableRequestBuilder<ModelType>
  implements DownloadOptions
{
  private final ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader;
  private final RequestManager.OptionsApplier optionsApplier;
  private final ModelLoader<ModelType, InputStream> streamModelLoader;
  
  DrawableTypeRequest(Class<ModelType> paramClass, ModelLoader<ModelType, InputStream> paramModelLoader, ModelLoader<ModelType, ParcelFileDescriptor> paramModelLoader1, Context paramContext, Glide paramGlide, RequestTracker paramRequestTracker, Lifecycle paramLifecycle, RequestManager.OptionsApplier paramOptionsApplier)
  {
    super(paramContext, paramClass, buildProvider(paramGlide, paramModelLoader, paramModelLoader1, GifBitmapWrapper.class, GlideDrawable.class, null), paramGlide, paramRequestTracker, paramLifecycle);
    this.streamModelLoader = paramModelLoader;
    this.fileDescriptorModelLoader = paramModelLoader1;
    this.optionsApplier = paramOptionsApplier;
  }
  
  private static <A, Z, R> FixedLoadProvider<A, ImageVideoWrapper, Z, R> buildProvider(Glide paramGlide, ModelLoader<A, InputStream> paramModelLoader, ModelLoader<A, ParcelFileDescriptor> paramModelLoader1, Class<Z> paramClass, Class<R> paramClass1, ResourceTranscoder<Z, R> paramResourceTranscoder)
  {
    if ((paramModelLoader == null) && (paramModelLoader1 == null)) {
      return null;
    }
    Object localObject = paramResourceTranscoder;
    if (paramResourceTranscoder == null) {
      localObject = paramGlide.buildTranscoder(paramClass, paramClass1);
    }
    paramGlide = paramGlide.buildDataProvider(ImageVideoWrapper.class, paramClass);
    return new FixedLoadProvider(new ImageVideoModelLoader(paramModelLoader, paramModelLoader1), (ResourceTranscoder)localObject, paramGlide);
  }
  
  private GenericTranscodeRequest<ModelType, InputStream, File> getDownloadOnlyRequest()
  {
    return (GenericTranscodeRequest)this.optionsApplier.apply(new GenericTranscodeRequest(File.class, this, this.streamModelLoader, InputStream.class, File.class, this.optionsApplier));
  }
  
  public BitmapTypeRequest<ModelType> asBitmap()
  {
    return (BitmapTypeRequest)this.optionsApplier.apply(new BitmapTypeRequest(this, this.streamModelLoader, this.fileDescriptorModelLoader, this.optionsApplier));
  }
  
  public GifTypeRequest<ModelType> asGif()
  {
    return (GifTypeRequest)this.optionsApplier.apply(new GifTypeRequest(this, this.streamModelLoader, this.optionsApplier));
  }
  
  public FutureTarget<File> downloadOnly(int paramInt1, int paramInt2)
  {
    return getDownloadOnlyRequest().downloadOnly(paramInt1, paramInt2);
  }
  
  public <Y extends Target<File>> Y downloadOnly(Y paramY)
  {
    return getDownloadOnlyRequest().downloadOnly(paramY);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/DrawableTypeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */