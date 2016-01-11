package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.o;
import java.util.concurrent.TimeUnit;

public final class BatchResult
  implements Result
{
  private final Status CM;
  private final PendingResult<?>[] IE;
  
  BatchResult(Status paramStatus, PendingResult<?>[] paramArrayOfPendingResult)
  {
    this.CM = paramStatus;
    this.IE = paramArrayOfPendingResult;
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  public <R extends Result> R take(BatchResultToken<R> paramBatchResultToken)
  {
    if (paramBatchResultToken.mId < this.IE.length) {}
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "The result token does not belong to this batch");
      return this.IE[paramBatchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/api/BatchResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */