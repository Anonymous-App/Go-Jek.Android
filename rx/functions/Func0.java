package rx.functions;

import java.util.concurrent.Callable;

public abstract interface Func0<R>
  extends Function, Callable<R>
{
  public abstract R call();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/functions/Func0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */