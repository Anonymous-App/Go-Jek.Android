package org.parceler;

public class ParcelerRuntimeException
  extends RuntimeException
{
  public ParcelerRuntimeException(String paramString)
  {
    super(paramString);
  }
  
  public ParcelerRuntimeException(String paramString, Exception paramException)
  {
    super(paramString, paramException);
  }
  
  public ParcelerRuntimeException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/ParcelerRuntimeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */