package com.nostra13.universalimageloader.cache.disc;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Deprecated
public abstract interface DiscCacheAware
{
  public abstract void clear();
  
  public abstract void close();
  
  public abstract File get(String paramString);
  
  public abstract File getDirectory();
  
  public abstract boolean remove(String paramString);
  
  public abstract boolean save(String paramString, Bitmap paramBitmap)
    throws IOException;
  
  public abstract boolean save(String paramString, InputStream paramInputStream, IoUtils.CopyListener paramCopyListener)
    throws IOException;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/nostra13/universalimageloader/cache/disc/DiscCacheAware.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */