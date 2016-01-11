package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class mj
  implements SafeParcelable
{
  public static final mk CREATOR = new mk();
  final int BR;
  final List<mp> afr;
  private final String afs;
  private final boolean aft;
  final List<mt> afu;
  final List<String> afv;
  private final Set<mp> afw;
  private final Set<mt> afx;
  private final Set<String> afy;
  
  mj(int paramInt, List<mp> paramList, String paramString, boolean paramBoolean, List<mt> paramList1, List<String> paramList2)
  {
    this.BR = paramInt;
    if (paramList == null)
    {
      paramList = Collections.emptyList();
      this.afr = paramList;
      paramList = paramString;
      if (paramString == null) {
        paramList = "";
      }
      this.afs = paramList;
      this.aft = paramBoolean;
      if (paramList1 != null) {
        break label112;
      }
      paramList = Collections.emptyList();
      label51:
      this.afu = paramList;
      if (paramList2 != null) {
        break label121;
      }
    }
    label112:
    label121:
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList2))
    {
      this.afv = paramList;
      this.afw = f(this.afr);
      this.afx = f(this.afu);
      this.afy = f(this.afv);
      return;
      paramList = Collections.unmodifiableList(paramList);
      break;
      paramList = Collections.unmodifiableList(paramList1);
      break label51;
    }
  }
  
  private static <E> Set<E> f(List<E> paramList)
  {
    if (paramList.isEmpty()) {
      return Collections.emptySet();
    }
    return Collections.unmodifiableSet(new HashSet(paramList));
  }
  
  public int describeContents()
  {
    mk localmk = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof mj)) {
        return false;
      }
      paramObject = (mj)paramObject;
    } while ((this.afw.equals(((mj)paramObject).afw)) && (this.aft == ((mj)paramObject).aft) && (this.afx.equals(((mj)paramObject).afx)) && (this.afy.equals(((mj)paramObject).afy)));
    return false;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.afw, Boolean.valueOf(this.aft), this.afx, this.afy });
  }
  
  @Deprecated
  public String mi()
  {
    return this.afs;
  }
  
  public boolean mj()
  {
    return this.aft;
  }
  
  public String toString()
  {
    return n.h(this).a("types", this.afw).a("placeIds", this.afy).a("requireOpenNow", Boolean.valueOf(this.aft)).a("requestedUserDataTypes", this.afx).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mk localmk = CREATOR;
    mk.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/mj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */