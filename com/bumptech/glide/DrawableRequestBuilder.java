package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapperTransformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation.Animator;
import com.bumptech.glide.request.target.Target;
import java.io.File;

public class DrawableRequestBuilder<ModelType>
  extends GenericRequestBuilder<ModelType, ImageVideoWrapper, GifBitmapWrapper, GlideDrawable>
  implements BitmapOptions, DrawableOptions
{
  DrawableRequestBuilder(Context paramContext, Class<ModelType> paramClass, LoadProvider<ModelType, ImageVideoWrapper, GifBitmapWrapper, GlideDrawable> paramLoadProvider, Glide paramGlide, RequestTracker paramRequestTracker, Lifecycle paramLifecycle)
  {
    super(paramContext, paramClass, paramLoadProvider, GlideDrawable.class, paramGlide, paramRequestTracker, paramLifecycle);
    crossFade();
  }
  
  public DrawableRequestBuilder<ModelType> animate(int paramInt)
  {
    super.animate(paramInt);
    return this;
  }
  
  @Deprecated
  public DrawableRequestBuilder<ModelType> animate(Animation paramAnimation)
  {
    super.animate(paramAnimation);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> animate(ViewPropertyAnimation.Animator paramAnimator)
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
  
  public DrawableRequestBuilder<ModelType> bitmapTransform(Transformation<Bitmap>... paramVarArgs)
  {
    GifBitmapWrapperTransformation[] arrayOfGifBitmapWrapperTransformation = new GifBitmapWrapperTransformation[paramVarArgs.length];
    int i = 0;
    while (i < paramVarArgs.length)
    {
      arrayOfGifBitmapWrapperTransformation[i] = new GifBitmapWrapperTransformation(this.glide.getBitmapPool(), paramVarArgs[i]);
      i += 1;
    }
    return transform(arrayOfGifBitmapWrapperTransformation);
  }
  
  public DrawableRequestBuilder<ModelType> cacheDecoder(ResourceDecoder<File, GifBitmapWrapper> paramResourceDecoder)
  {
    super.cacheDecoder(paramResourceDecoder);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> centerCrop()
  {
    return transform(new Transformation[] { this.glide.getDrawableCenterCrop() });
  }
  
  public DrawableRequestBuilder<ModelType> clone()
  {
    return (DrawableRequestBuilder)super.clone();
  }
  
  public final DrawableRequestBuilder<ModelType> crossFade()
  {
    super.animate(new DrawableCrossFadeFactory());
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> crossFade(int paramInt)
  {
    super.animate(new DrawableCrossFadeFactory(paramInt));
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> crossFade(int paramInt1, int paramInt2)
  {
    super.animate(new DrawableCrossFadeFactory(this.context, paramInt1, paramInt2));
    return this;
  }
  
  @Deprecated
  public DrawableRequestBuilder<ModelType> crossFade(Animation paramAnimation, int paramInt)
  {
    super.animate(new DrawableCrossFadeFactory(paramAnimation, paramInt));
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> decoder(ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> paramResourceDecoder)
  {
    super.decoder(paramResourceDecoder);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> diskCacheStrategy(DiskCacheStrategy paramDiskCacheStrategy)
  {
    super.diskCacheStrategy(paramDiskCacheStrategy);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> dontAnimate()
  {
    super.dontAnimate();
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> dontTransform()
  {
    super.dontTransform();
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> encoder(ResourceEncoder<GifBitmapWrapper> paramResourceEncoder)
  {
    super.encoder(paramResourceEncoder);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> error(int paramInt)
  {
    super.error(paramInt);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> error(Drawable paramDrawable)
  {
    super.error(paramDrawable);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> fallback(int paramInt)
  {
    super.fallback(paramInt);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> fallback(Drawable paramDrawable)
  {
    super.fallback(paramDrawable);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> fitCenter()
  {
    return transform(new Transformation[] { this.glide.getDrawableFitCenter() });
  }
  
  public Target<GlideDrawable> into(ImageView paramImageView)
  {
    return super.into(paramImageView);
  }
  
  public DrawableRequestBuilder<ModelType> listener(RequestListener<? super ModelType, GlideDrawable> paramRequestListener)
  {
    super.listener(paramRequestListener);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> load(ModelType paramModelType)
  {
    super.load(paramModelType);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> override(int paramInt1, int paramInt2)
  {
    super.override(paramInt1, paramInt2);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> placeholder(int paramInt)
  {
    super.placeholder(paramInt);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> placeholder(Drawable paramDrawable)
  {
    super.placeholder(paramDrawable);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> priority(Priority paramPriority)
  {
    super.priority(paramPriority);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> signature(Key paramKey)
  {
    super.signature(paramKey);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> sizeMultiplier(float paramFloat)
  {
    super.sizeMultiplier(paramFloat);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> skipMemoryCache(boolean paramBoolean)
  {
    super.skipMemoryCache(paramBoolean);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> sourceEncoder(Encoder<ImageVideoWrapper> paramEncoder)
  {
    super.sourceEncoder(paramEncoder);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> thumbnail(float paramFloat)
  {
    super.thumbnail(paramFloat);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> thumbnail(DrawableRequestBuilder<?> paramDrawableRequestBuilder)
  {
    super.thumbnail(paramDrawableRequestBuilder);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> thumbnail(GenericRequestBuilder<?, ?, ?, GlideDrawable> paramGenericRequestBuilder)
  {
    super.thumbnail(paramGenericRequestBuilder);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> transcoder(ResourceTranscoder<GifBitmapWrapper, GlideDrawable> paramResourceTranscoder)
  {
    super.transcoder(paramResourceTranscoder);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> transform(Transformation<GifBitmapWrapper>... paramVarArgs)
  {
    super.transform(paramVarArgs);
    return this;
  }
  
  public DrawableRequestBuilder<ModelType> transform(BitmapTransformation... paramVarArgs)
  {
    return bitmapTransform(paramVarArgs);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/DrawableRequestBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */