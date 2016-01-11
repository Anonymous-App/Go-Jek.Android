package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class j
  extends i
{
  protected j(Context paramContext, m paramm, n paramn)
  {
    super(paramContext, paramm, paramn);
  }
  
  public static j a(String paramString, Context paramContext)
  {
    e locale = new e();
    a(paramString, paramContext, locale);
    return new j(paramContext, locale, new p(239));
  }
  
  protected void b(Context paramContext)
  {
    super.b(paramContext);
    try
    {
      Object localObject = h(paramContext);
      if (((a)localObject).isLimitAdTrackingEnabled()) {}
      for (long l = 1L;; l = 0L)
      {
        a(28, l);
        localObject = ((a)localObject).getId();
        if (localObject != null)
        {
          a(26, 5L);
          a(24, (String)localObject);
        }
        return;
      }
      return;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      a(24, d(paramContext));
      return;
    }
    catch (IOException paramContext)
    {
      return;
    }
    catch (i.a paramContext) {}
  }
  
  a h(Context paramContext)
    throws IOException, GooglePlayServicesNotAvailableException
  {
    int i = 0;
    AdvertisingIdClient.Info localInfo;
    try
    {
      localInfo = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      paramContext = localInfo.getId();
      if ((paramContext != null) && (paramContext.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")))
      {
        byte[] arrayOfByte = new byte[16];
        int j = 0;
        while (i < paramContext.length())
        {
          int k;
          if ((i != 8) && (i != 13) && (i != 18))
          {
            k = i;
            if (i != 23) {}
          }
          else
          {
            k = i + 1;
          }
          arrayOfByte[j] = ((byte)((Character.digit(paramContext.charAt(k), 16) << 4) + Character.digit(paramContext.charAt(k + 1), 16)));
          j += 1;
          i = k + 2;
        }
        paramContext = this.ky.a(arrayOfByte, true);
      }
    }
    catch (GooglePlayServicesRepairableException paramContext)
    {
      throw new IOException(paramContext);
    }
    catch (SecurityException paramContext)
    {
      throw new IOException(paramContext);
    }
    for (;;)
    {
      return new a(paramContext, localInfo.isLimitAdTrackingEnabled());
    }
  }
  
  class a
  {
    private String kO;
    private boolean kP;
    
    public a(String paramString, boolean paramBoolean)
    {
      this.kO = paramString;
      this.kP = paramBoolean;
    }
    
    public String getId()
    {
      return this.kO;
    }
    
    public boolean isLimitAdTrackingEnabled()
    {
      return this.kP;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */