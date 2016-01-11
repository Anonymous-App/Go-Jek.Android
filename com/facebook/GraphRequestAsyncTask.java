package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

public class GraphRequestAsyncTask
  extends AsyncTask<Void, Void, List<GraphResponse>>
{
  private static final String TAG = GraphRequestAsyncTask.class.getCanonicalName();
  private static Method executeOnExecutorMethod;
  public Trace _nr_trace;
  private final HttpURLConnection connection;
  private Exception exception;
  private final GraphRequestBatch requests;
  
  static
  {
    Method[] arrayOfMethod = AsyncTask.class.getMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Method localMethod = arrayOfMethod[i];
        if ("executeOnExecutor".equals(localMethod.getName()))
        {
          Class[] arrayOfClass = localMethod.getParameterTypes();
          if ((arrayOfClass.length == 2) && (arrayOfClass[0] == Executor.class) && (arrayOfClass[1].isArray())) {
            executeOnExecutorMethod = localMethod;
          }
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  public GraphRequestAsyncTask(GraphRequestBatch paramGraphRequestBatch)
  {
    this(null, paramGraphRequestBatch);
  }
  
  public GraphRequestAsyncTask(HttpURLConnection paramHttpURLConnection, GraphRequestBatch paramGraphRequestBatch)
  {
    this.requests = paramGraphRequestBatch;
    this.connection = paramHttpURLConnection;
  }
  
  public GraphRequestAsyncTask(HttpURLConnection paramHttpURLConnection, Collection<GraphRequest> paramCollection)
  {
    this(paramHttpURLConnection, new GraphRequestBatch(paramCollection));
  }
  
  public GraphRequestAsyncTask(HttpURLConnection paramHttpURLConnection, GraphRequest... paramVarArgs)
  {
    this(paramHttpURLConnection, new GraphRequestBatch(paramVarArgs));
  }
  
  public GraphRequestAsyncTask(Collection<GraphRequest> paramCollection)
  {
    this(null, new GraphRequestBatch(paramCollection));
  }
  
  public GraphRequestAsyncTask(GraphRequest... paramVarArgs)
  {
    this(null, new GraphRequestBatch(paramVarArgs));
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
  
  protected List<GraphResponse> doInBackground(Void... paramVarArgs)
  {
    try
    {
      if (this.connection == null) {
        return this.requests.executeAndWait();
      }
      paramVarArgs = GraphRequest.executeConnectionAndWait(this.connection, this.requests);
      return paramVarArgs;
    }
    catch (Exception paramVarArgs)
    {
      this.exception = paramVarArgs;
    }
    return null;
  }
  
  GraphRequestAsyncTask executeOnSettingsExecutor()
  {
    if (executeOnExecutorMethod != null) {}
    try
    {
      executeOnExecutorMethod.invoke(this, new Object[] { FacebookSdk.getExecutor(), null });
      return this;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Void[] arrayOfVoid;
      return this;
    }
    catch (InvocationTargetException localInvocationTargetException) {}
    arrayOfVoid = new Void[0];
    if (!(this instanceof AsyncTask))
    {
      execute(arrayOfVoid);
      return this;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)this, arrayOfVoid);
    return this;
    return this;
  }
  
  protected final Exception getException()
  {
    return this.exception;
  }
  
  protected final GraphRequestBatch getRequests()
  {
    return this.requests;
  }
  
  protected void onPostExecute(List<GraphResponse> paramList)
  {
    super.onPostExecute(paramList);
    if (this.exception != null) {
      Log.d(TAG, String.format("onPostExecute: exception encountered during request: %s", new Object[] { this.exception.getMessage() }));
    }
  }
  
  protected void onPreExecute()
  {
    super.onPreExecute();
    if (this.requests.getCallbackHandler() == null) {
      this.requests.setCallbackHandler(new Handler());
    }
  }
  
  public String toString()
  {
    return "{RequestAsyncTask: " + " connection: " + this.connection + ", requests: " + this.requests + "}";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/facebook/GraphRequestAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */