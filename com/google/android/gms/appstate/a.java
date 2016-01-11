package com.google.android.gms.appstate;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;

public final class a
  implements AppState
{
  private final int CO;
  private final String CP;
  private final byte[] CQ;
  private final boolean CR;
  private final String CS;
  private final byte[] CT;
  
  public a(AppState paramAppState)
  {
    this.CO = paramAppState.getKey();
    this.CP = paramAppState.getLocalVersion();
    this.CQ = paramAppState.getLocalData();
    this.CR = paramAppState.hasConflict();
    this.CS = paramAppState.getConflictVersion();
    this.CT = paramAppState.getConflictData();
  }
  
  static int a(AppState paramAppState)
  {
    return n.hashCode(new Object[] { Integer.valueOf(paramAppState.getKey()), paramAppState.getLocalVersion(), paramAppState.getLocalData(), Boolean.valueOf(paramAppState.hasConflict()), paramAppState.getConflictVersion(), paramAppState.getConflictData() });
  }
  
  static boolean a(AppState paramAppState, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof AppState)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramAppState == paramObject);
      paramObject = (AppState)paramObject;
      if ((!n.equal(Integer.valueOf(((AppState)paramObject).getKey()), Integer.valueOf(paramAppState.getKey()))) || (!n.equal(((AppState)paramObject).getLocalVersion(), paramAppState.getLocalVersion())) || (!n.equal(((AppState)paramObject).getLocalData(), paramAppState.getLocalData())) || (!n.equal(Boolean.valueOf(((AppState)paramObject).hasConflict()), Boolean.valueOf(paramAppState.hasConflict()))) || (!n.equal(((AppState)paramObject).getConflictVersion(), paramAppState.getConflictVersion()))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((AppState)paramObject).getConflictData(), paramAppState.getConflictData()));
    return false;
  }
  
  static String b(AppState paramAppState)
  {
    return n.h(paramAppState).a("Key", Integer.valueOf(paramAppState.getKey())).a("LocalVersion", paramAppState.getLocalVersion()).a("LocalData", paramAppState.getLocalData()).a("HasConflict", Boolean.valueOf(paramAppState.hasConflict())).a("ConflictVersion", paramAppState.getConflictVersion()).a("ConflictData", paramAppState.getConflictData()).toString();
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public AppState fo()
  {
    return this;
  }
  
  public byte[] getConflictData()
  {
    return this.CT;
  }
  
  public String getConflictVersion()
  {
    return this.CS;
  }
  
  public int getKey()
  {
    return this.CO;
  }
  
  public byte[] getLocalData()
  {
    return this.CQ;
  }
  
  public String getLocalVersion()
  {
    return this.CP;
  }
  
  public boolean hasConflict()
  {
    return this.CR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/appstate/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */