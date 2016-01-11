package com.bumptech.glide;

import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import java.io.File;

abstract interface DownloadOptions
{
  public abstract FutureTarget<File> downloadOnly(int paramInt1, int paramInt2);
  
  public abstract <Y extends Target<File>> Y downloadOnly(Y paramY);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/DownloadOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */