package rx.subjects;

import rx.annotations.Experimental;
import rx.observers.SerializedObserver;

public class SerializedSubject<T, R>
  extends Subject<T, R>
{
  private final Subject<T, R> actual;
  private final SerializedObserver<T> observer;
  
  public SerializedSubject(Subject<T, R> paramSubject)
  {
    super(new SerializedSubject.1(paramSubject));
    this.actual = paramSubject;
    this.observer = new SerializedObserver(paramSubject);
  }
  
  @Experimental
  public Throwable getThrowable()
  {
    return this.actual.getThrowable();
  }
  
  @Experimental
  public T getValue()
  {
    return (T)this.actual.getValue();
  }
  
  @Experimental
  public Object[] getValues()
  {
    return this.actual.getValues();
  }
  
  @Experimental
  public T[] getValues(T[] paramArrayOfT)
  {
    return this.actual.getValues(paramArrayOfT);
  }
  
  @Experimental
  public boolean hasCompleted()
  {
    return this.actual.hasCompleted();
  }
  
  public boolean hasObservers()
  {
    return this.actual.hasObservers();
  }
  
  @Experimental
  public boolean hasThrowable()
  {
    return this.actual.hasThrowable();
  }
  
  @Experimental
  public boolean hasValue()
  {
    return this.actual.hasValue();
  }
  
  public void onCompleted()
  {
    this.observer.onCompleted();
  }
  
  public void onError(Throwable paramThrowable)
  {
    this.observer.onError(paramThrowable);
  }
  
  public void onNext(T paramT)
  {
    this.observer.onNext(paramT);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/subjects/SerializedSubject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */