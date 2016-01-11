package org.codehaus.jackson;

import java.io.IOException;

public class JsonProcessingException
  extends IOException
{
  static final long serialVersionUID = 123L;
  protected JsonLocation mLocation;
  
  protected JsonProcessingException(String paramString)
  {
    super(paramString);
  }
  
  protected JsonProcessingException(String paramString, Throwable paramThrowable)
  {
    this(paramString, null, paramThrowable);
  }
  
  protected JsonProcessingException(String paramString, JsonLocation paramJsonLocation)
  {
    this(paramString, paramJsonLocation, null);
  }
  
  protected JsonProcessingException(String paramString, JsonLocation paramJsonLocation, Throwable paramThrowable)
  {
    super(paramString);
    if (paramThrowable != null) {
      initCause(paramThrowable);
    }
    this.mLocation = paramJsonLocation;
  }
  
  protected JsonProcessingException(Throwable paramThrowable)
  {
    this(null, null, paramThrowable);
  }
  
  public JsonLocation getLocation()
  {
    return this.mLocation;
  }
  
  public String getMessage()
  {
    Object localObject2 = super.getMessage();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "N/A";
    }
    JsonLocation localJsonLocation = getLocation();
    localObject2 = localObject1;
    if (localJsonLocation != null)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append('\n');
      ((StringBuilder)localObject2).append(" at ");
      ((StringBuilder)localObject2).append(localJsonLocation.toString());
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    return (String)localObject2;
  }
  
  public String toString()
  {
    return getClass().getName() + ": " + getMessage();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/JsonProcessingException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */