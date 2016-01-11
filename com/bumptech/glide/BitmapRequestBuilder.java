package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.ImageVideoBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation.Animator;
import com.bumptech.glide.request.target.Target;
import java.io.File;
import java.io.InputStream;

public class BitmapRequestBuilder<ModelType, TranscodeType>
  extends GenericRequestBuilder<ModelType, ImageVideoWrapper, Bitmap, TranscodeType>
  implements BitmapOptions
{
  private final BitmapPool bitmapPool;
  private DecodeFormat decodeFormat;
  private Downsampler downsampler = Downsampler.AT_LEAST;
  private ResourceDecoder<InputStream, Bitmap> imageDecoder;
  private ResourceDecoder<ParcelFileDescriptor, Bitmap> videoDecoder;
  
  BitmapRequestBuilder(LoadProvider<ModelType, ImageVideoWrapper, Bitmap, TranscodeType> paramLoadProvider, Class<TranscodeType> paramClass, GenericRequestBuilder<ModelType, ?, ?, ?> paramGenericRequestBuilder)
  {
    super(paramLoadProvider, paramClass, paramGenericRequestBuilder);
    this.bitmapPool = paramGenericRequestBuilder.glide.getBitmapPool();
    this.decodeFormat = paramGenericRequestBuilder.glide.getDecodeFormat();
    this.imageDecoder = new StreamBitmapDecoder(this.bitmapPool, this.decodeFormat);
    this.videoDecoder = new FileDescriptorBitmapDecoder(this.bitmapPool, this.decodeFormat);
  }
  
  private BitmapRequestBuilder<ModelType, TranscodeType> downsample(Downsampler paramDownsampler)
  {
    this.downsampler = paramDownsampler;
    this.imageDecoder = new StreamBitmapDecoder(paramDownsampler, this.bitmapPool, this.decodeFormat);
    super.decoder(new ImageVideoBitmapDecoder(this.imageDecoder, this.videoDecoder));
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> animate(int paramInt)
  {
    super.animate(paramInt);
    return this;
  }
  
  @Deprecated
  public BitmapRequestBuilder<ModelType, TranscodeType> animate(Animation paramAnimation)
  {
    super.animate(paramAnimation);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> animate(ViewPropertyAnimation.Animator paramAnimator)
  {
    super.animate(paramAnimator);
    return this;
  }
  
  void applyCenterCrop()
  {
    centerCrop();
  }
  
  void applyFitCenter()
  {
    fitCenter();
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> approximate()
  {
    return downsample(Downsampler.AT_LEAST);
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> asIs()
  {
    return downsample(Downsampler.NONE);
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> atMost()
  {
    return downsample(Downsampler.AT_MOST);
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> cacheDecoder(ResourceDecoder<File, Bitmap> paramResourceDecoder)
  {
    super.cacheDecoder(paramResourceDecoder);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> centerCrop()
  {
    return transform(new BitmapTransformation[] { this.glide.getBitmapCenterCrop() });
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> clone()
  {
    return (BitmapRequestBuilder)super.clone();
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> decoder(ResourceDecoder<ImageVideoWrapper, Bitmap> paramResourceDecoder)
  {
    super.decoder(paramResourceDecoder);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> diskCacheStrategy(DiskCacheStrategy paramDiskCacheStrategy)
  {
    super.diskCacheStrategy(paramDiskCacheStrategy);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> dontAnimate()
  {
    super.dontAnimate();
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> dontTransform()
  {
    super.dontTransform();
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> encoder(ResourceEncoder<Bitmap> paramResourceEncoder)
  {
    super.encoder(paramResourceEncoder);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> error(int paramInt)
  {
    super.error(paramInt);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> error(Drawable paramDrawable)
  {
    super.error(paramDrawable);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> fallback(int paramInt)
  {
    super.fallback(paramInt);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> fallback(Drawable paramDrawable)
  {
    super.fallback(paramDrawable);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> fitCenter()
  {
    return transform(new BitmapTransformation[] { this.glide.getBitmapFitCenter() });
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> format(DecodeFormat paramDecodeFormat)
  {
    this.decodeFormat = paramDecodeFormat;
    this.imageDecoder = new StreamBitmapDecoder(this.downsampler, this.bitmapPool, paramDecodeFormat);
    this.videoDecoder = new FileDescriptorBitmapDecoder(new VideoBitmapDecoder(), this.bitmapPool, paramDecodeFormat);
    super.cacheDecoder(new FileToStreamDecoder(new StreamBitmapDecoder(this.downsampler, this.bitmapPool, paramDecodeFormat)));
    super.decoder(new ImageVideoBitmapDecoder(this.imageDecoder, this.videoDecoder));
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> imageDecoder(ResourceDecoder<InputStream, Bitmap> paramResourceDecoder)
  {
    this.imageDecoder = paramResourceDecoder;
    super.decoder(new ImageVideoBitmapDecoder(paramResourceDecoder, this.videoDecoder));
    return this;
  }
  
  public Target<TranscodeType> into(ImageView paramImageView)
  {
    return super.into(paramImageView);
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> listener(RequestListener<? super ModelType, TranscodeType> paramRequestListener)
  {
    super.listener(paramRequestListener);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> load(ModelType paramModelType)
  {
    super.load(paramModelType);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> override(int paramInt1, int paramInt2)
  {
    super.override(paramInt1, paramInt2);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> placeholder(int paramInt)
  {
    super.placeholder(paramInt);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> placeholder(Drawable paramDrawable)
  {
    super.placeholder(paramDrawable);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> priority(Priority paramPriority)
  {
    super.priority(paramPriority);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> signature(Key paramKey)
  {
    super.signature(paramKey);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> sizeMultiplier(float paramFloat)
  {
    super.sizeMultiplier(paramFloat);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> skipMemoryCache(boolean paramBoolean)
  {
    super.skipMemoryCache(paramBoolean);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> sourceEncoder(Encoder<ImageVideoWrapper> paramEncoder)
  {
    super.sourceEncoder(paramEncoder);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> thumbnail(float paramFloat)
  {
    super.thumbnail(paramFloat);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> thumbnail(BitmapRequestBuilder<?, TranscodeType> paramBitmapRequestBuilder)
  {
    super.thumbnail(paramBitmapRequestBuilder);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> thumbnail(GenericRequestBuilder<?, ?, ?, TranscodeType> paramGenericRequestBuilder)
  {
    super.thumbnail(paramGenericRequestBuilder);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> transcoder(ResourceTranscoder<Bitmap, TranscodeType> paramResourceTranscoder)
  {
    super.transcoder(paramResourceTranscoder);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> transform(Transformation<Bitmap>... paramVarArgs)
  {
    super.transform(paramVarArgs);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> transform(BitmapTransformation... paramVarArgs)
  {
    super.transform(paramVarArgs);
    return this;
  }
  
  public BitmapRequestBuilder<ModelType, TranscodeType> videoDecoder(ResourceDecoder<ParcelFileDescriptor, Bitmap> paramResourceDecoder)
  {
    this.videoDecoder = paramResourceDecoder;
    super.decoder(new ImageVideoBitmapDecoder(this.imageDecoder, paramResourceDecoder));
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/BitmapRequestBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */