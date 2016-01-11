package com.google.android.gms.common.api;

import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

public final class Batch
  extends BaseImplementation.AbstractPendingResult<BatchResult>
{
  private int IA;
  private boolean IB;
  private boolean IC;
  private final PendingResult<?>[] IE;
  private final Object mw = new Object();
  
  private Batch(List<PendingResult<?>> paramList, Looper paramLooper)
  {
    super(new BaseImplementation.CallbackHandler(paramLooper));
    this.IA = paramList.size();
    this.IE = new PendingResult[this.IA];
    int i = 0;
    while (i < paramList.size())
    {
      paramLooper = (PendingResult)paramList.get(i);
      this.IE[i] = paramLooper;
      paramLooper.a(new PendingResult.a()
      {
        public void n(Status paramAnonymousStatus)
        {
          for (;;)
          {
            synchronized (Batch.a(Batch.this))
            {
              if (Batch.this.isCanceled()) {
                return;
              }
              if (paramAnonymousStatus.isCanceled())
              {
                Batch.a(Batch.this, true);
                Batch.b(Batch.this);
                if (Batch.c(Batch.this) == 0)
                {
                  if (!Batch.d(Batch.this)) {
                    break;
                  }
                  Batch.e(Batch.this);
                }
                return;
              }
            }
            if (!paramAnonymousStatus.isSuccess()) {
              Batch.b(Batch.this, true);
            }
          }
          if (Batch.f(Batch.this)) {}
          for (paramAnonymousStatus = new Status(13);; paramAnonymousStatus = Status.Jv)
          {
            Batch.this.b(new BatchResult(paramAnonymousStatus, Batch.g(Batch.this)));
            break;
          }
        }
      });
      i += 1;
    }
  }
  
  public void cancel()
  {
    super.cancel();
    PendingResult[] arrayOfPendingResult = this.IE;
    int j = arrayOfPendingResult.length;
    int i = 0;
    while (i < j)
    {
      arrayOfPendingResult[i].cancel();
      i += 1;
    }
  }
  
  public BatchResult createFailedResult(Status paramStatus)
  {
    return new BatchResult(paramStatus, this.IE);
  }
  
  public static final class Builder
  {
    private List<PendingResult<?>> IG = new ArrayList();
    private Looper IH;
    
    public Builder(GoogleApiClient paramGoogleApiClient)
    {
      this.IH = paramGoogleApiClient.getLooper();
    }
    
    public <R extends Result> BatchResultToken<R> add(PendingResult<R> paramPendingResult)
    {
      BatchResultToken localBatchResultToken = new BatchResultToken(this.IG.size());
      this.IG.add(paramPendingResult);
      return localBatchResultToken;
    }
    
    public Batch build()
    {
      return new Batch(this.IG, this.IH, null);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/api/Batch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */