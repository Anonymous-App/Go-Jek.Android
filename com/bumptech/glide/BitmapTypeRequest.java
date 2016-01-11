package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.model.ImageVideoModelLoader;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.FixedLoadProvider;
import java.io.InputStream;

public class BitmapTypeRequest<ModelType>
  extends BitmapRequestBuilder<ModelType, Bitmap>
{
  private final ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader;
  private final Glide glide;
  private final RequestManager.OptionsApplier optionsApplier;
  private final ModelLoader<ModelType, InputStream> streamModelLoader;
  
  BitmapTypeRequest(GenericRequestBuilder<ModelType, ?, ?, ?> paramGenericRequestBuilder, ModelLoader<ModelType, InputStream> paramModelLoader, ModelLoader<ModelType, ParcelFileDescriptor> paramModelLoader1, RequestManager.OptionsApplier paramOptionsApplier)
  {
    super(buildProvider(paramGenericRequestBuilder.glide, paramModelLoader, paramModelLoader1, Bitmap.class, null), Bitmap.class, paramGenericRequestBuilder);
    this.streamModelLoader = paramModelLoader;
    this.fileDescriptorModelLoader = paramModelLoader1;
    this.glide = paramGenericRequestBuilder.glide;
    this.optionsApplier = paramOptionsApplier;
  }
  
  private static <A, R> FixedLoadProvider<A, ImageVideoWrapper, Bitmap, R> buildProvider(Glide paramGlide, ModelLoader<A, InputStream> paramModelLoader, ModelLoader<A, ParcelFileDescriptor> paramModelLoader1, Class<R> paramClass, ResourceTranscoder<Bitmap, R> paramResourceTranscoder)
  {
    if ((paramModelLoader == null) && (paramModelLoader1 == null)) {
      return null;
    }
    Object localObject = paramResourceTranscoder;
    if (paramResourceTranscoder == null) {
      localObject = paramGlide.buildTranscoder(Bitmap.class, paramClass);
    }
    paramGlide = paramGlide.buildDataProvider(ImageVideoWrapper.class, Bitmap.class);
    return new FixedLoadProvider(new ImageVideoModelLoader(paramModelLoader, paramModelLoader1), (ResourceTranscoder)localObject, paramGlide);
  }
  
  public BitmapRequestBuilder<ModelType, byte[]> toBytes()
  {
    return transcode(new BitmapBytesTranscoder(), byte[].class);
  }
  
  public BitmapRequestBuilder<ModelType, byte[]> toBytes(Bitmap.CompressFormat paramCompressFormat, int paramInt)
  {
    return transcode(new BitmapBytesTranscoder(paramCompressFormat, paramInt), byte[].class);
  }
  
  public <R> BitmapRequestBuilder<ModelType, R> transcode(ResourceTranscoder<Bitmap, R> paramResourceTranscoder, Class<R> paramClass)
  {
    return (BitmapRequestBuilder)this.optionsApplier.apply(new BitmapRequestBuilder(buildProvider(this.glide, this.streamModelLoader, this.fileDescriptorModelLoader, paramClass, paramResourceTranscoder), paramClass, this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/BitmapTypeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */