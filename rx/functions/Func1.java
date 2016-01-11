package rx.functions;

public abstract interface Func1<T, R>
  extends Function
{
  public abstract R call(T paramT);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/functions/Func1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */