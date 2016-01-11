package com.google.android.gms.fitness;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class a
{
  public static String bq(String paramString)
  {
    String str;
    if (paramString.equals("https://www.googleapis.com/auth/fitness.activity.read")) {
      str = "https://www.googleapis.com/auth/fitness.activity.write";
    }
    do
    {
      return str;
      if (paramString.equals("https://www.googleapis.com/auth/fitness.location.read")) {
        return "https://www.googleapis.com/auth/fitness.location.write";
      }
      str = paramString;
    } while (!paramString.equals("https://www.googleapis.com/auth/fitness.body.read"));
    return "https://www.googleapis.com/auth/fitness.body.write";
  }
  
  public static String[] d(List<String> paramList)
  {
    HashSet localHashSet = new HashSet(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = bq(str1);
      if ((str2.equals(str1)) || (!paramList.contains(str2))) {
        localHashSet.add(str1);
      }
    }
    return (String[])localHashSet.toArray(new String[localHashSet.size()]);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */