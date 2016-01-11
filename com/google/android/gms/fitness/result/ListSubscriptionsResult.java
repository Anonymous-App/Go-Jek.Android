package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListSubscriptionsResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new e();
  private final int BR;
  private final Status CM;
  private final List<Subscription> UY;
  
  ListSubscriptionsResult(int paramInt, List<Subscription> paramList, Status paramStatus)
  {
    this.BR = paramInt;
    this.UY = paramList;
    this.CM = paramStatus;
  }
  
  public ListSubscriptionsResult(List<Subscription> paramList, Status paramStatus)
  {
    this.BR = 3;
    this.UY = Collections.unmodifiableList(paramList);
    this.CM = ((Status)o.b(paramStatus, "status"));
  }
  
  public static ListSubscriptionsResult G(Status paramStatus)
  {
    return new ListSubscriptionsResult(Collections.emptyList(), paramStatus);
  }
  
  private boolean b(ListSubscriptionsResult paramListSubscriptionsResult)
  {
    return (this.CM.equals(paramListSubscriptionsResult.CM)) && (n.equal(this.UY, paramListSubscriptionsResult.UY));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof ListSubscriptionsResult)) && (b((ListSubscriptionsResult)paramObject)));
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  public List<Subscription> getSubscriptions()
  {
    return this.UY;
  }
  
  public List<Subscription> getSubscriptions(DataType paramDataType)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.UY.iterator();
    while (localIterator.hasNext())
    {
      Subscription localSubscription = (Subscription)localIterator.next();
      if (paramDataType.equals(localSubscription.iY())) {
        localArrayList.add(localSubscription);
      }
    }
    return Collections.unmodifiableList(localArrayList);
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.CM, this.UY });
  }
  
  public String toString()
  {
    return n.h(this).a("status", this.CM).a("subscriptions", this.UY).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    e.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/ListSubscriptionsResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */