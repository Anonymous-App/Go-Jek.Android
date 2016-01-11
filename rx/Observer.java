package rx;

public abstract interface Observer<T>
{
  public abstract void onCompleted();
  
  public abstract void onError(Throwable paramThrowable);
  
  public abstract void onNext(T paramT);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/Observer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */