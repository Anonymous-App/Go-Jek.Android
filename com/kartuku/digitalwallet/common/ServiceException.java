package com.kartuku.digitalwallet.common;

public class ServiceException
  extends RuntimeException
{
  public ServiceException() {}
  
  public ServiceException(String paramString)
  {
    super(paramString);
  }
  
  public ServiceException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  protected ServiceException(String paramString, Throwable paramThrowable, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
  }
  
  public ServiceException(String paramString, Object... paramVarArgs)
  {
    super(String.format(paramString, paramVarArgs));
  }
  
  public ServiceException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
  
  public ServiceException(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    super(String.format(paramString, paramVarArgs), paramThrowable);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/ServiceException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */