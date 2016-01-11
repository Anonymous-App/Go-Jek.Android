package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.request.Request;

public abstract class BaseTarget<Z>
  implements Target<Z>
{
  private Request request;
  
  public Request getRequest()
  {
    return this.request;
  }
  
  public void onDestroy() {}
  
  public void onLoadCleared(Drawable paramDrawable) {}
  
  public void onLoadFailed(Exception paramException, Drawable paramDrawable) {}
  
  public void onLoadStarted(Drawable paramDrawable) {}
  
  public void onStart() {}
  
  public void onStop() {}
  
  public void setRequest(Request paramRequest)
  {
    this.request = paramRequest;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/request/target/BaseTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */