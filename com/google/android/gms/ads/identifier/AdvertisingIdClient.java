package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.s;
import com.google.android.gms.internal.s.a;
import java.io.IOException;

public final class AdvertisingIdClient
{
  a lk;
  s ll;
  boolean lm;
  final Context mContext;
  
  public AdvertisingIdClient(Context paramContext)
  {
    o.i(paramContext);
    this.mContext = paramContext;
    this.lm = false;
  }
  
  static s a(Context paramContext, a parama)
    throws IOException
  {
    try
    {
      paramContext = s.a.b(parama.fW());
      return paramContext;
    }
    catch (InterruptedException paramContext)
    {
      throw new IOException("Interrupted exception");
    }
  }
  
  public static Info getAdvertisingIdInfo(Context paramContext)
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    paramContext = new AdvertisingIdClient(paramContext);
    try
    {
      paramContext.start();
      Info localInfo = paramContext.W();
      return localInfo;
    }
    finally
    {
      paramContext.finish();
    }
  }
  
  static a i(Context paramContext)
    throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo("com.android.vending", 0);
      a locala;
      Intent localIntent;
      throw new IOException("Connection failure");
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      try
      {
        GooglePlayServicesUtil.D(paramContext);
        locala = new a();
        localIntent = new Intent("com.google.android.gms.ads.identifier.service.START");
        localIntent.setPackage("com.google.android.gms");
        if (!paramContext.bindService(localIntent, locala, 1)) {
          break label73;
        }
        return locala;
      }
      catch (GooglePlayServicesNotAvailableException paramContext)
      {
        throw new IOException(paramContext);
      }
      paramContext = paramContext;
      throw new GooglePlayServicesNotAvailableException(9);
    }
  }
  
  public Info W()
    throws IOException
  {
    o.aU("Calling this from your main thread can lead to deadlock");
    o.i(this.lk);
    o.i(this.ll);
    if (!this.lm) {
      throw new IOException("AdvertisingIdService is not connected.");
    }
    try
    {
      Info localInfo = new Info(this.ll.getId(), this.ll.a(true));
      return localInfo;
    }
    catch (RemoteException localRemoteException)
    {
      Log.i("AdvertisingIdClient", "GMS remote exception ", localRemoteException);
      throw new IOException("Remote exception");
    }
  }
  
  public void finish()
  {
    o.aU("Calling this from your main thread can lead to deadlock");
    if ((this.mContext == null) || (this.lk == null)) {
      return;
    }
    try
    {
      if (this.lm) {
        this.mContext.unbindService(this.lk);
      }
      this.lm = false;
      this.ll = null;
      this.lk = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", localIllegalArgumentException);
      }
    }
  }
  
  public void start()
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    o.aU("Calling this from your main thread can lead to deadlock");
    if (this.lm) {
      finish();
    }
    this.lk = i(this.mContext);
    this.ll = a(this.mContext, this.lk);
    this.lm = true;
  }
  
  public static final class Info
  {
    private final String ln;
    private final boolean lo;
    
    public Info(String paramString, boolean paramBoolean)
    {
      this.ln = paramString;
      this.lo = paramBoolean;
    }
    
    public String getId()
    {
      return this.ln;
    }
    
    public boolean isLimitAdTrackingEnabled()
    {
      return this.lo;
    }
    
    public String toString()
    {
      return "{" + this.ln + "}" + this.lo;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/identifier/AdvertisingIdClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */