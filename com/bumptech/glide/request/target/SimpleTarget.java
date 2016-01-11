package com.bumptech.glide.request.target;

import com.bumptech.glide.util.Util;

public abstract class SimpleTarget<Z>
  extends BaseTarget<Z>
{
  private final int height;
  private final int width;
  
  public SimpleTarget()
  {
    this(Integer.MIN_VALUE, Integer.MIN_VALUE);
  }
  
  public SimpleTarget(int paramInt1, int paramInt2)
  {
    this.width = paramInt1;
    this.height = paramInt2;
  }
  
  public final void getSize(SizeReadyCallback paramSizeReadyCallback)
  {
    if (!Util.isValidDimensions(this.width, this.height)) {
      throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.width + " and height: " + this.height + ", either provide dimensions in the constructor" + " or call override()");
    }
    paramSizeReadyCallback.onSizeReady(this.width, this.height);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/request/target/SimpleTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */