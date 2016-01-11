package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class ap
{
  private final int nJ;
  private final int nK;
  private final ao nL = new ar();
  private Base64OutputStream nM;
  private ByteArrayOutputStream nN;
  
  public ap(int paramInt)
  {
    this.nK = paramInt;
    this.nJ = 6;
  }
  
  private String m(String paramString)
  {
    paramString = paramString.split("\n");
    if ((paramString == null) || (paramString.length == 0)) {
      return "";
    }
    this.nN = new ByteArrayOutputStream();
    this.nM = new Base64OutputStream(this.nN, 10);
    Arrays.sort(paramString, new Comparator()
    {
      public int compare(String paramAnonymousString1, String paramAnonymousString2)
      {
        return paramAnonymousString2.length() - paramAnonymousString1.length();
      }
    });
    int i = 0;
    if ((i < paramString.length) && (i < this.nK))
    {
      if (paramString[i].trim().length() == 0) {}
      for (;;)
      {
        i += 1;
        break;
        try
        {
          this.nM.write(this.nL.l(paramString[i]));
        }
        catch (IOException localIOException)
        {
          gs.b("Error while writing hash to byteStream", localIOException);
        }
      }
    }
    try
    {
      this.nM.flush();
      this.nM.close();
      paramString = this.nN.toString();
      return paramString;
    }
    catch (IOException paramString)
    {
      gs.b("HashManager: Unable to convert to base 64", paramString);
    }
    return "";
  }
  
  public String a(ArrayList<String> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      localStringBuffer.append(((String)paramArrayList.next()).toLowerCase());
      localStringBuffer.append('\n');
    }
    switch (0)
    {
    default: 
      return "";
    case 0: 
      return n(localStringBuffer.toString());
    }
    return m(localStringBuffer.toString());
  }
  
  String n(String paramString)
  {
    paramString = paramString.split("\n");
    if ((paramString == null) || (paramString.length == 0)) {
      return "";
    }
    this.nN = new ByteArrayOutputStream();
    this.nM = new Base64OutputStream(this.nN, 10);
    Object localObject = new PriorityQueue(this.nK, new Comparator()
    {
      public int a(as.a paramAnonymousa1, as.a paramAnonymousa2)
      {
        return (int)(paramAnonymousa1.value - paramAnonymousa2.value);
      }
    });
    int i = 0;
    if (i < paramString.length)
    {
      String[] arrayOfString = aq.p(paramString[i]);
      if (arrayOfString.length < this.nJ) {}
      for (;;)
      {
        i += 1;
        break;
        as.a(arrayOfString, this.nK, this.nJ, (PriorityQueue)localObject);
      }
    }
    paramString = ((PriorityQueue)localObject).iterator();
    while (paramString.hasNext())
    {
      localObject = (as.a)paramString.next();
      try
      {
        this.nM.write(this.nL.l(((as.a)localObject).nQ));
      }
      catch (IOException localIOException)
      {
        gs.b("Error while writing hash to byteStream", localIOException);
      }
    }
    try
    {
      this.nM.flush();
      this.nM.close();
      paramString = this.nN.toString();
      return paramString;
    }
    catch (IOException paramString)
    {
      gs.b("HashManager: unable to convert to base 64", paramString);
    }
    return "";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */