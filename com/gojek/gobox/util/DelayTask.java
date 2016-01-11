package com.gojek.gobox.util;

import android.os.AsyncTask;
import com.newrelic.agent.android.tracing.Trace;

public class DelayTask
  extends AsyncTask<Void, Void, Void>
{
  public Trace _nr_trace;
  private CallBack callBack;
  private int delay = 1000;
  
  public DelayTask(int paramInt, CallBack paramCallBack)
  {
    this.delay = paramInt;
    this.callBack = paramCallBack;
  }
  
  public void _nr_setTrace(Trace paramTrace)
  {
    try
    {
      this._nr_trace = paramTrace;
      return;
    }
    catch (Exception paramTrace) {}
  }
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    try
    {
      Thread.sleep(this.delay);
      return null;
    }
    catch (InterruptedException paramVarArgs)
    {
      for (;;) {}
    }
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    if (!isCancelled()) {
      this.callBack.onFinish();
    }
  }
  
  public static abstract interface CallBack
  {
    public abstract void onFinish();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/DelayTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */