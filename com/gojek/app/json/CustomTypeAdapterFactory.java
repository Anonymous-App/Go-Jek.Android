package com.gojek.app.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class CustomTypeAdapterFactory<C>
  implements TypeAdapterFactory
{
  private final Class<C> customizedClass;
  
  public CustomTypeAdapterFactory(Class<C> paramClass)
  {
    this.customizedClass = paramClass;
  }
  
  private TypeAdapter<C> customizeMyClassAdapter(Gson paramGson, TypeToken<C> paramTypeToken)
  {
    return new CustomTypeAdapterFactory.1(this, paramGson.getDelegateAdapter(this, paramTypeToken), paramGson.getAdapter(JsonElement.class));
  }
  
  protected void afterRead(JsonElement paramJsonElement) {}
  
  protected void beforeWrite(C paramC, JsonElement paramJsonElement) {}
  
  public final <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    if (paramTypeToken.getRawType() == this.customizedClass) {
      return customizeMyClassAdapter(paramGson, paramTypeToken);
    }
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/json/CustomTypeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */