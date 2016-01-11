package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  
  public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor)
  {
    this.constructorConstructor = paramConstructorConstructor;
  }
  
  static TypeAdapter<?> getTypeAdapter(ConstructorConstructor paramConstructorConstructor, Gson paramGson, TypeToken<?> paramTypeToken, JsonAdapter paramJsonAdapter)
  {
    paramJsonAdapter = paramJsonAdapter.value();
    if (TypeAdapter.class.isAssignableFrom(paramJsonAdapter)) {
      return (TypeAdapter)paramConstructorConstructor.get(TypeToken.get(paramJsonAdapter)).construct();
    }
    if (TypeAdapterFactory.class.isAssignableFrom(paramJsonAdapter)) {
      return ((TypeAdapterFactory)paramConstructorConstructor.get(TypeToken.get(paramJsonAdapter)).construct()).create(paramGson, paramTypeToken);
    }
    throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
  }
  
  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    JsonAdapter localJsonAdapter = (JsonAdapter)paramTypeToken.getRawType().getAnnotation(JsonAdapter.class);
    if (localJsonAdapter == null) {
      return null;
    }
    return getTypeAdapter(this.constructorConstructor, paramGson, paramTypeToken, localJsonAdapter);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/gson/internal/bind/JsonAdapterAnnotationTypeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */