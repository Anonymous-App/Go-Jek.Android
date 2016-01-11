package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;

public class ki
{
  public static <T> boolean a(List<T> paramList1, List<T> paramList2)
  {
    if (paramList1.size() != paramList2.size()) {
      return false;
    }
    paramList1 = paramList1.iterator();
    while (paramList1.hasNext()) {
      if (!paramList2.contains(paramList1.next())) {
        return false;
      }
    }
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ki.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */