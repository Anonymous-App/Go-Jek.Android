package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class fq
  implements fo.a<bp>
{
  public bp c(fo paramfo, JSONObject paramJSONObject)
    throws JSONException, InterruptedException, ExecutionException
  {
    Future localFuture = paramfo.a(paramJSONObject, "image", true);
    paramfo = paramfo.a(paramJSONObject, "secondary_image", false);
    return new bp(paramJSONObject.getString("headline"), (Drawable)localFuture.get(), paramJSONObject.getString("body"), (Drawable)paramfo.get(), paramJSONObject.getString("call_to_action"), paramJSONObject.getString("attribution"));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */