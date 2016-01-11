package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.appindexing.AppIndexApi.AppIndexingLink;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.zip.CRC32;

public class hs
  implements SafeParcelable
{
  public static final ht CREATOR = new ht();
  final int BR;
  final hg CD;
  final long CE;
  final int CF;
  final he CG;
  public final String oT;
  
  hs(int paramInt1, hg paramhg, long paramLong, int paramInt2, String paramString, he paramhe)
  {
    this.BR = paramInt1;
    this.CD = paramhg;
    this.CE = paramLong;
    this.CF = paramInt2;
    this.oT = paramString;
    this.CG = paramhe;
  }
  
  public hs(hg paramhg, long paramLong, int paramInt)
  {
    this(1, paramhg, paramLong, paramInt, null, null);
  }
  
  public hs(String paramString1, Intent paramIntent, String paramString2, Uri paramUri, String paramString3, List<AppIndexApi.AppIndexingLink> paramList)
  {
    this(1, a(paramString1, paramIntent), System.currentTimeMillis(), 0, null, a(paramIntent, paramString2, paramUri, paramString3, paramList).fj());
  }
  
  public static he.a a(Intent paramIntent, String paramString1, Uri paramUri, String paramString2, List<AppIndexApi.AppIndexingLink> paramList)
  {
    he.a locala = new he.a();
    locala.a(av(paramString1));
    if (paramUri != null) {
      locala.a(f(paramUri));
    }
    if (paramList != null) {
      locala.a(b(paramList));
    }
    paramString1 = paramIntent.getAction();
    if (paramString1 != null) {
      locala.a(j("intent_action", paramString1));
    }
    paramString1 = paramIntent.getDataString();
    if (paramString1 != null) {
      locala.a(j("intent_data", paramString1));
    }
    paramString1 = paramIntent.getComponent();
    if (paramString1 != null) {
      locala.a(j("intent_activity", paramString1.getClassName()));
    }
    paramIntent = paramIntent.getExtras();
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getString("intent_extra_data_key");
      if (paramIntent != null) {
        locala.a(j("intent_extra_data", paramIntent));
      }
    }
    return locala.ar(paramString2).D(true);
  }
  
  public static hg a(String paramString, Intent paramIntent)
  {
    return i(paramString, g(paramIntent));
  }
  
  private static hi av(String paramString)
  {
    return new hi(paramString, new hq.a("title").P(1).F(true).au("name").fm(), "text1");
  }
  
  private static hi b(List<AppIndexApi.AppIndexingLink> paramList)
  {
    ll.a locala = new ll.a();
    ll.a.a[] arrayOfa = new ll.a.a[paramList.size()];
    int i = 0;
    while (i < arrayOfa.length)
    {
      arrayOfa[i] = new ll.a.a();
      AppIndexApi.AppIndexingLink localAppIndexingLink = (AppIndexApi.AppIndexingLink)paramList.get(i);
      arrayOfa[i].adG = localAppIndexingLink.appIndexingUrl.toString();
      arrayOfa[i].adH = localAppIndexingLink.webUrl.toString();
      arrayOfa[i].viewId = localAppIndexingLink.viewId;
      i += 1;
    }
    locala.adE = arrayOfa;
    return new hi(pn.f(locala), new hq.a("outlinks").E(true).au(".private:outLinks").at("blob").fm());
  }
  
  private static hi f(Uri paramUri)
  {
    return new hi(paramUri.toString(), new hq.a("web_url").P(4).E(true).au("url").fm());
  }
  
  private static String g(Intent paramIntent)
  {
    paramIntent = paramIntent.toUri(1);
    CRC32 localCRC32 = new CRC32();
    try
    {
      localCRC32.update(paramIntent.getBytes("UTF-8"));
      return Long.toHexString(localCRC32.getValue());
    }
    catch (UnsupportedEncodingException paramIntent)
    {
      throw new IllegalStateException(paramIntent);
    }
  }
  
  private static hg i(String paramString1, String paramString2)
  {
    return new hg(paramString1, "", paramString2);
  }
  
  private static hi j(String paramString1, String paramString2)
  {
    return new hi(paramString2, new hq.a(paramString1).E(true).fm(), paramString1);
  }
  
  public int describeContents()
  {
    ht localht = CREATOR;
    return 0;
  }
  
  public String toString()
  {
    return String.format("UsageInfo[documentId=%s, timestamp=%d, usageType=%d]", new Object[] { this.CD, Long.valueOf(this.CE), Integer.valueOf(this.CF) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ht localht = CREATOR;
    ht.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/hs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */