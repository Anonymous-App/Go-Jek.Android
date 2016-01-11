package rx.functions;

public abstract interface Func2<T1, T2, R>
  extends Function
{
  public abstract R call(T1 paramT1, T2 paramT2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/functions/Func2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */