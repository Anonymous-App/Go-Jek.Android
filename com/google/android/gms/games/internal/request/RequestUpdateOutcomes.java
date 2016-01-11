package com.google.android.gms.games.internal.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.internal.constants.RequestUpdateResultOutcome;
import java.util.HashMap;
import java.util.Set;

public final class RequestUpdateOutcomes
{
  private static final String[] abs = { "requestId", "outcome" };
  private final int HF;
  private final HashMap<String, Integer> abt;
  
  private RequestUpdateOutcomes(int paramInt, HashMap<String, Integer> paramHashMap)
  {
    this.HF = paramInt;
    this.abt = paramHashMap;
  }
  
  public static RequestUpdateOutcomes V(DataHolder paramDataHolder)
  {
    Builder localBuilder = new Builder();
    localBuilder.dR(paramDataHolder.getStatusCode());
    int j = paramDataHolder.getCount();
    int i = 0;
    while (i < j)
    {
      int k = paramDataHolder.ar(i);
      localBuilder.x(paramDataHolder.c("requestId", i, k), paramDataHolder.b("outcome", i, k));
      i += 1;
    }
    return localBuilder.ly();
  }
  
  public Set<String> getRequestIds()
  {
    return this.abt.keySet();
  }
  
  public int getRequestOutcome(String paramString)
  {
    o.b(this.abt.containsKey(paramString), "Request " + paramString + " was not part of the update operation!");
    return ((Integer)this.abt.get(paramString)).intValue();
  }
  
  public static final class Builder
  {
    private int HF = 0;
    private HashMap<String, Integer> abt = new HashMap();
    
    public Builder dR(int paramInt)
    {
      this.HF = paramInt;
      return this;
    }
    
    public RequestUpdateOutcomes ly()
    {
      return new RequestUpdateOutcomes(this.HF, this.abt, null);
    }
    
    public Builder x(String paramString, int paramInt)
    {
      if (RequestUpdateResultOutcome.isValid(paramInt)) {
        this.abt.put(paramString, Integer.valueOf(paramInt));
      }
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/request/RequestUpdateOutcomes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */