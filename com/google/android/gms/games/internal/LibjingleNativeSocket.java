package com.google.android.gms.games.internal;

import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class LibjingleNativeSocket
  implements RealTimeSocket
{
  private static final String TAG = LibjingleNativeSocket.class.getSimpleName();
  private final ParcelFileDescriptor KE;
  private final InputStream XX;
  private final OutputStream XY;
  
  LibjingleNativeSocket(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    this.KE = paramParcelFileDescriptor;
    this.XX = new ParcelFileDescriptor.AutoCloseInputStream(paramParcelFileDescriptor);
    this.XY = new ParcelFileDescriptor.AutoCloseOutputStream(paramParcelFileDescriptor);
  }
  
  public void close()
    throws IOException
  {
    this.KE.close();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return this.XX;
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return this.XY;
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
    throws IOException
  {
    return this.KE;
  }
  
  public boolean isClosed()
  {
    try
    {
      this.XX.available();
      return false;
    }
    catch (IOException localIOException) {}
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/LibjingleNativeSocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */