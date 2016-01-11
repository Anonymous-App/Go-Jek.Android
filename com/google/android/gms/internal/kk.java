package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api.a;
import com.google.android.gms.common.api.BaseImplementation.a;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.Fitness;

public abstract interface kk
  extends Api.a
{
  public abstract Context getContext();
  
  public abstract kp jb();
  
  public static abstract class a<R extends Result>
    extends BaseImplementation.a<R, kk>
  {
    public a()
    {
      super();
    }
  }
  
  public static class b
    extends kt.a
  {
    private final BaseImplementation.b<Status> De;
    
    public b(BaseImplementation.b<Status> paramb)
    {
      this.De = paramb;
    }
    
    public void k(Status paramStatus)
    {
      this.De.b(paramStatus);
    }
  }
  
  public static abstract class c
    extends kk.a<Status>
  {
    protected Status d(Status paramStatus)
    {
      if (!paramStatus.isSuccess()) {}
      for (boolean bool = true;; bool = false)
      {
        o.K(bool);
        return paramStatus;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */