package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation.Animator;
import java.io.File;
import java.io.InputStream;

public class GifRequestBuilder<ModelType>
  extends GenericRequestBuilder<ModelType, InputStream, GifDrawable, GifDrawable>
  implements BitmapOptions, DrawableOptions
{
  GifRequestBuilder(LoadProvider<ModelType, InputStream, GifDrawable, GifDrawable> paramLoadProvider, Class<GifDrawable> paramClass, GenericRequestBuilder<ModelType, ?, ?, ?> paramGenericRequestBuilder)
  {
    super(paramLoadProvider, paramClass, paramGenericRequestBuilder);
  }
  
  private GifDrawableTransformation[] toGifTransformations(Transformation<Bitmap>[] paramArrayOfTransformation)
  {
    GifDrawableTransformation[] arrayOfGifDrawableTransformation = new GifDrawableTransformation[paramArrayOfTransformation.length];
    int i = 0;
    while (i < paramArrayOfTransformation.length)
    {
      arrayOfGifDrawableTransformation[i] = new GifDrawableTransformation(paramArrayOfTransformation[i], this.glide.getBitmapPool());
      i += 1;
    }
    return arrayOfGifDrawableTransformation;
  }
  
  public GifRequestBuilder<ModelType> animate(int paramInt)
  {
    super.animate(paramInt);
    return this;
  }
  
  @Deprecated
  public GifRequestBuilder<ModelType> animate(Animation paramAnimation)
  {
    super.animate(paramAnimation);
    return this;
  }
  
  public GifRequestBuilder<ModelType> animate(ViewPropertyAnimation.Animator paramAnimator)
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
  
  public GifRequestBuilder<ModelType> cacheDecoder(ResourceDecoder<File, GifDrawable> paramResourceDecoder)
  {
    super.cacheDecoder(paramResourceDecoder);
    return this;
  }
  
  public GifRequestBuilder<ModelType> centerCrop()
  {
    return transformFrame(new BitmapTransformation[] { this.glide.getBitmapCenterCrop() });
  }
  
  public GifRequestBuilder<ModelType> clone()
  {
    return (GifRequestBuilder)super.clone();
  }
  
  public GifRequestBuilder<ModelType> crossFade()
  {
    super.animate(new DrawableCrossFadeFactory());
    return this;
  }
  
  public GifRequestBuilder<ModelType> crossFade(int paramInt)
  {
    super.animate(new DrawableCrossFadeFactory(paramInt));
    return this;
  }
  
  public GifRequestBuilder<ModelType> crossFade(int paramInt1, int paramInt2)
  {
    super.animate(new DrawableCrossFadeFactory(this.context, paramInt1, paramInt2));
    return this;
  }
  
  @Deprecated
  public GifRequestBuilder<ModelType> crossFade(Animation paramAnimation, int paramInt)
  {
    super.animate(new DrawableCrossFadeFactory(paramAnimation, paramInt));
    return this;
  }
  
  public GifRequestBuilder<ModelType> decoder(ResourceDecoder<InputStream, GifDrawable> paramResourceDecoder)
  {
    super.decoder(paramResourceDecoder);
    return this;
  }
  
  public GifRequestBuilder<ModelType> diskCacheStrategy(DiskCacheStrategy paramDiskCacheStrategy)
  {
    super.diskCacheStrategy(paramDiskCacheStrategy);
    return this;
  }
  
  public GifRequestBuilder<ModelType> dontAnimate()
  {
    super.dontAnimate();
    return this;
  }
  
  public GifRequestBuilder<ModelType> dontTransform()
  {
    super.dontTransform();
    return this;
  }
  
  public GifRequestBuilder<ModelType> encoder(ResourceEncoder<GifDrawable> paramResourceEncoder)
  {
    super.encoder(paramResourceEncoder);
    return this;
  }
  
  public GifRequestBuilder<ModelType> error(int paramInt)
  {
    super.error(paramInt);
    return this;
  }
  
  public GifRequestBuilder<ModelType> error(Drawable paramDrawable)
  {
    super.error(paramDrawable);
    return this;
  }
  
  public GifRequestBuilder<ModelType> fallback(int paramInt)
  {
    super.fallback(paramInt);
    return this;
  }
  
  public GifRequestBuilder<ModelType> fallback(Drawable paramDrawable)
  {
    super.fallback(paramDrawable);
    return this;
  }
  
  public GifRequestBuilder<ModelType> fitCenter()
  {
    return transformFrame(new BitmapTransformation[] { this.glide.getBitmapFitCenter() });
  }
  
  public GifRequestBuilder<ModelType> listener(RequestListener<? super ModelType, GifDrawable> paramRequestListener)
  {
    super.listener(paramRequestListener);
    return this;
  }
  
  public GifRequestBuilder<ModelType> load(ModelType paramModelType)
  {
    super.load(paramModelType);
    return this;
  }
  
  public GifRequestBuilder<ModelType> override(int paramInt1, int paramInt2)
  {
    super.override(paramInt1, paramInt2);
    return this;
  }
  
  public GifRequestBuilder<ModelType> placeholder(int paramInt)
  {
    super.placeholder(paramInt);
    return this;
  }
  
  public GifRequestBuilder<ModelType> placeholder(Drawable paramDrawable)
  {
    super.placeholder(paramDrawable);
    return this;
  }
  
  public GifRequestBuilder<ModelType> priority(Priority paramPriority)
  {
    super.priority(paramPriority);
    return this;
  }
  
  public GifRequestBuilder<ModelType> signature(Key paramKey)
  {
    super.signature(paramKey);
    return this;
  }
  
  public GifRequestBuilder<ModelType> sizeMultiplier(float paramFloat)
  {
    super.sizeMultiplier(paramFloat);
    return this;
  }
  
  public GifRequestBuilder<ModelType> skipMemoryCache(boolean paramBoolean)
  {
    super.skipMemoryCache(paramBoolean);
    return this;
  }
  
  public GifRequestBuilder<ModelType> sourceEncoder(Encoder<InputStream> paramEncoder)
  {
    super.sourceEncoder(paramEncoder);
    return this;
  }
  
  public GifRequestBuilder<ModelType> thumbnail(float paramFloat)
  {
    super.thumbnail(paramFloat);
    return this;
  }
  
  public GifRequestBuilder<ModelType> thumbnail(GenericRequestBuilder<?, ?, ?, GifDrawable> paramGenericRequestBuilder)
  {
    super.thumbnail(paramGenericRequestBuilder);
    return this;
  }
  
  public GifRequestBuilder<ModelType> thumbnail(GifRequestBuilder<?> paramGifRequestBuilder)
  {
    super.thumbnail(paramGifRequestBuilder);
    return this;
  }
  
  public GifRequestBuilder<ModelType> transcoder(ResourceTranscoder<GifDrawable, GifDrawable> paramResourceTranscoder)
  {
    super.transcoder(paramResourceTranscoder);
    return this;
  }
  
  public GifRequestBuilder<ModelType> transform(Transformation<GifDrawable>... paramVarArgs)
  {
    super.transform(paramVarArgs);
    return this;
  }
  
  public GifRequestBuilder<ModelType> transformFrame(Transformation<Bitmap>... paramVarArgs)
  {
    return transform(toGifTransformations(paramVarArgs));
  }
  
  public GifRequestBuilder<ModelType> transformFrame(BitmapTransformation... paramVarArgs)
  {
    return transform(toGifTransformations(paramVarArgs));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/GifRequestBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */