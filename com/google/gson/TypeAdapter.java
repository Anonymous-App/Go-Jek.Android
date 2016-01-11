package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter<T>
{
  public final T fromJson(Reader paramReader)
    throws IOException
  {
    return (T)read(new JsonReader(paramReader));
  }
  
  public final T fromJson(String paramString)
    throws IOException
  {
    return (T)fromJson(new StringReader(paramString));
  }
  
  public final T fromJsonTree(JsonElement paramJsonElement)
  {
    try
    {
      paramJsonElement = read(new JsonTreeReader(paramJsonElement));
      return paramJsonElement;
    }
    catch (IOException paramJsonElement)
    {
      throw new JsonIOException(paramJsonElement);
    }
  }
  
  public final TypeAdapter<T> nullSafe()
  {
    new TypeAdapter()
    {
      public T read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return (T)TypeAdapter.this.read(paramAnonymousJsonReader);
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, T paramAnonymousT)
        throws IOException
      {
        if (paramAnonymousT == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        TypeAdapter.this.write(paramAnonymousJsonWriter, paramAnonymousT);
      }
    };
  }
  
  public abstract T read(JsonReader paramJsonReader)
    throws IOException;
  
  public final String toJson(T paramT)
    throws IOException
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(localStringWriter, paramT);
    return localStringWriter.toString();
  }
  
  public final void toJson(Writer paramWriter, T paramT)
    throws IOException
  {
    write(new JsonWriter(paramWriter), paramT);
  }
  
  public final JsonElement toJsonTree(T paramT)
  {
    try
    {
      JsonTreeWriter localJsonTreeWriter = new JsonTreeWriter();
      write(localJsonTreeWriter, paramT);
      paramT = localJsonTreeWriter.get();
      return paramT;
    }
    catch (IOException paramT)
    {
      throw new JsonIOException(paramT);
    }
  }
  
  public abstract void write(JsonWriter paramJsonWriter, T paramT)
    throws IOException;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/gson/TypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */