package rx.plugins;

import rx.annotations.Experimental;
import rx.exceptions.Exceptions;

public abstract class RxJavaErrorHandler
{
  protected static final String ERROR_IN_RENDERING_SUFFIX = ".errorRendering";
  
  public void handleError(Throwable paramThrowable) {}
  
  @Experimental
  public final String handleOnNextValueRendering(Object paramObject)
  {
    try
    {
      String str = render(paramObject);
      return str;
    }
    catch (InterruptedException localInterruptedException)
    {
      Thread.currentThread().interrupt();
      return paramObject.getClass().getName() + ".errorRendering";
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Exceptions.throwIfFatal(localThrowable);
      }
    }
  }
  
  @Experimental
  protected String render(Object paramObject)
    throws InterruptedException
  {
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/plugins/RxJavaErrorHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */