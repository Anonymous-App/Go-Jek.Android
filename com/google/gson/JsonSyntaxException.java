package com.google.gson;

public final class JsonSyntaxException
  extends JsonParseException
{
  private static final long serialVersionUID = 1L;
  
  public JsonSyntaxException(String paramString)
  {
    super(paramString);
  }
  
  public JsonSyntaxException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public JsonSyntaxException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/gson/JsonSyntaxException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */