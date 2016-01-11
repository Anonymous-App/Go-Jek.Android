package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectRequest
  extends JsonRequest<JSONObject>
{
  public JsonObjectRequest(int paramInt, String paramString, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramInt, paramString, null, paramListener, paramErrorListener);
  }
  
  public JsonObjectRequest(int paramInt, String paramString1, String paramString2, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramInt, paramString1, paramString2, paramListener, paramErrorListener);
  }
  
  public JsonObjectRequest(int paramInt, String paramString, JSONObject paramJSONObject, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener) {}
  
  public JsonObjectRequest(String paramString, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(0, paramString, null, paramListener, paramErrorListener);
  }
  
  public JsonObjectRequest(String paramString, JSONObject paramJSONObject, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener) {}
  
  protected Response<JSONObject> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    try
    {
      paramNetworkResponse = Response.success(JSONObjectInstrumentation.init(new String(paramNetworkResponse.data, HttpHeaderParser.parseCharset(paramNetworkResponse.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
      return paramNetworkResponse;
    }
    catch (UnsupportedEncodingException paramNetworkResponse)
    {
      return Response.error(new ParseError(paramNetworkResponse));
    }
    catch (JSONException paramNetworkResponse) {}
    return Response.error(new ParseError(paramNetworkResponse));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/android/volley/toolbox/JsonObjectRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */