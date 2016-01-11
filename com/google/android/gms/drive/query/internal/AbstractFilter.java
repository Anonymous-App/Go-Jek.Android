package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.c;

public abstract class AbstractFilter
  implements Filter
{
  public String toString()
  {
    return String.format("Filter[%s]", new Object[] { a(new c()) });
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/AbstractFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */