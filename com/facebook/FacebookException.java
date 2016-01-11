package com.facebook;

public class FacebookException
  extends RuntimeException
{
  static final long serialVersionUID = 1L;
  
  public FacebookException() {}
  
  public FacebookException(String paramString)
  {
    super(paramString);
  }
  
  public FacebookException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public FacebookException(String paramString, Object... paramVarArgs)
  {
    this(String.format(paramString, paramVarArgs));
  }
  
  public FacebookException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
  
  public String toString()
  {
    return getMessage();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/facebook/FacebookException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */