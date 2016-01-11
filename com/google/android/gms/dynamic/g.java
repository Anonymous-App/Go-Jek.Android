package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;

public abstract class g<T>
{
  private final String Sl;
  private T Sm;
  
  protected g(String paramString)
  {
    this.Sl = paramString;
  }
  
  protected final T L(Context paramContext)
    throws g.a
  {
    if (this.Sm == null)
    {
      o.i(paramContext);
      paramContext = GooglePlayServicesUtil.getRemoteContext(paramContext);
      if (paramContext == null) {
        throw new a("Could not get remote context.");
      }
      paramContext = paramContext.getClassLoader();
    }
    try
    {
      this.Sm = d((IBinder)paramContext.loadClass(this.Sl).newInstance());
      return (T)this.Sm;
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new a("Could not load creator class.", paramContext);
    }
    catch (InstantiationException paramContext)
    {
      throw new a("Could not instantiate creator.", paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      throw new a("Could not access creator.", paramContext);
    }
  }
  
  protected abstract T d(IBinder paramIBinder);
  
  public static class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
    
    public a(String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/dynamic/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */