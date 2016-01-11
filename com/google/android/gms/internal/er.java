package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;

@ez
public final class er
  extends es.a
{
  private final aa sM;
  private final String sN;
  private final String sO;
  
  public er(aa paramaa, String paramString1, String paramString2)
  {
    this.sM = paramaa;
    this.sN = paramString1;
    this.sO = paramString2;
  }
  
  public void ar()
  {
    this.sM.ar();
  }
  
  public void as()
  {
    this.sM.as();
  }
  
  public void c(d paramd)
  {
    if (paramd == null) {
      return;
    }
    this.sM.b((View)e.f(paramd));
  }
  
  public String cu()
  {
    return this.sN;
  }
  
  public String cv()
  {
    return this.sO;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/er.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */