package com.flurry.sdk;

import android.content.Context;
import com.flurry.android.FlurryEventRecordStatus;
import java.util.Map;

public class ep
  implements ge
{
  private ew a;
  
  public static ep a()
  {
    try
    {
      ep localep = (ep)fn.a().a(ep.class);
      return localep;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private ez a(gv paramgv)
  {
    if (paramgv == null) {
      return null;
    }
    return (ez)paramgv.c(ez.class);
  }
  
  private ez f()
  {
    return a(gw.a().d());
  }
  
  public FlurryEventRecordStatus a(String paramString)
  {
    ez localez = f();
    FlurryEventRecordStatus localFlurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
    if (localez != null) {
      localFlurryEventRecordStatus = localez.a(paramString, null, false);
    }
    return localFlurryEventRecordStatus;
  }
  
  public FlurryEventRecordStatus a(String paramString, Map<String, String> paramMap)
  {
    ez localez = f();
    FlurryEventRecordStatus localFlurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
    if (localez != null) {
      localFlurryEventRecordStatus = localez.a(paramString, paramMap, false);
    }
    return localFlurryEventRecordStatus;
  }
  
  public FlurryEventRecordStatus a(String paramString, Map<String, String> paramMap, boolean paramBoolean)
  {
    ez localez = f();
    FlurryEventRecordStatus localFlurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
    if (localez != null) {
      localFlurryEventRecordStatus = localez.a(paramString, paramMap, paramBoolean);
    }
    return localFlurryEventRecordStatus;
  }
  
  public FlurryEventRecordStatus a(String paramString, boolean paramBoolean)
  {
    ez localez = f();
    FlurryEventRecordStatus localFlurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
    if (localez != null) {
      localFlurryEventRecordStatus = localez.a(paramString, null, paramBoolean);
    }
    return localFlurryEventRecordStatus;
  }
  
  public void a(Context paramContext)
  {
    gv.a(ez.class);
    this.a = new ew();
  }
  
  @Deprecated
  public void a(String paramString1, String paramString2, String paramString3)
  {
    Object localObject1 = Thread.currentThread().getStackTrace();
    Object localObject2;
    if ((localObject1 != null) && (localObject1.length > 2))
    {
      localObject2 = new StackTraceElement[localObject1.length - 2];
      System.arraycopy(localObject1, 2, localObject2, 0, localObject2.length);
      localObject1 = localObject2;
    }
    for (;;)
    {
      localObject2 = new Throwable(paramString2);
      ((Throwable)localObject2).setStackTrace((StackTraceElement[])localObject1);
      localObject1 = f();
      if (localObject1 != null) {
        ((ez)localObject1).a(paramString1, paramString2, paramString3, (Throwable)localObject2);
      }
      return;
    }
  }
  
  public void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    ez localez = f();
    if (localez != null) {
      localez.a(paramString1, paramString2, paramThrowable.getClass().getName(), paramThrowable);
    }
  }
  
  public void b()
  {
    if (this.a != null)
    {
      this.a.a();
      this.a = null;
    }
    gv.b(ez.class);
  }
  
  public void b(String paramString)
  {
    ez localez = f();
    if (localez != null) {
      localez.a(paramString, null);
    }
  }
  
  public void b(String paramString, Map<String, String> paramMap)
  {
    ez localez = f();
    if (localez != null) {
      localez.a(paramString, paramMap);
    }
  }
  
  public ew c()
  {
    return this.a;
  }
  
  public void c(String paramString)
  {
    ez localez = f();
    if (localez != null) {
      localez.a(paramString, null, false);
    }
  }
  
  public void c(String paramString, Map<String, String> paramMap)
  {
    ez localez = f();
    if (localez != null) {
      localez.a(paramString, paramMap, false);
    }
  }
  
  public void d()
  {
    ez localez = f();
    if (localez != null) {
      localez.b();
    }
  }
  
  public void e()
  {
    ez localez = f();
    if (localez != null) {
      localez.d();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/flurry/sdk/ep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */