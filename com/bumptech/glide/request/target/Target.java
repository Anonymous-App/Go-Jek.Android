package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;

public abstract interface Target<R>
  extends LifecycleListener
{
  public static final int SIZE_ORIGINAL = Integer.MIN_VALUE;
  
  public abstract Request getRequest();
  
  public abstract void getSize(SizeReadyCallback paramSizeReadyCallback);
  
  public abstract void onLoadCleared(Drawable paramDrawable);
  
  public abstract void onLoadFailed(Exception paramException, Drawable paramDrawable);
  
  public abstract void onLoadStarted(Drawable paramDrawable);
  
  public abstract void onResourceReady(R paramR, GlideAnimation<? super R> paramGlideAnimation);
  
  public abstract void setRequest(Request paramRequest);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/request/target/Target.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */