package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@ez
public class ax
{
  public static final ax oe = new ax();
  
  public static ax bb()
  {
    return oe;
  }
  
  public av a(Context paramContext, bg parambg)
  {
    Object localObject = parambg.getBirthday();
    long l;
    String str1;
    int i;
    label59:
    boolean bool1;
    int j;
    Location localLocation;
    Bundle localBundle;
    boolean bool2;
    String str2;
    if (localObject != null)
    {
      l = ((Date)localObject).getTime();
      str1 = parambg.getContentUrl();
      i = parambg.getGender();
      localObject = parambg.getKeywords();
      if (((Set)localObject).isEmpty()) {
        break label157;
      }
      localObject = Collections.unmodifiableList(new ArrayList((Collection)localObject));
      bool1 = parambg.isTestDevice(paramContext);
      j = parambg.bg();
      localLocation = parambg.getLocation();
      localBundle = parambg.getNetworkExtrasBundle(AdMobAdapter.class);
      bool2 = parambg.getManualImpressionsEnabled();
      str2 = parambg.getPublisherProvidedId();
      paramContext = parambg.bd();
      if (paramContext == null) {
        break label163;
      }
    }
    label157:
    label163:
    for (paramContext = new bj(paramContext);; paramContext = null)
    {
      return new av(4, l, localBundle, i, (List)localObject, bool1, j, bool2, str2, paramContext, localLocation, str1, parambg.bf());
      l = -1L;
      break;
      localObject = null;
      break label59;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */