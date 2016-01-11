package com.google.android.gms.games.multiplayer.realtime;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface RealTimeSocket
{
  public abstract void close()
    throws IOException;
  
  public abstract InputStream getInputStream()
    throws IOException;
  
  public abstract OutputStream getOutputStream()
    throws IOException;
  
  public abstract ParcelFileDescriptor getParcelFileDescriptor()
    throws IOException;
  
  public abstract boolean isClosed();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/realtime/RealTimeSocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */