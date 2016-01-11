package rx.subjects;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.annotations.Experimental;

public abstract class Subject<T, R>
  extends Observable<R>
  implements Observer<T>
{
  private static final Object[] EMPTY_ARRAY = new Object[0];
  
  protected Subject(Observable.OnSubscribe<R> paramOnSubscribe)
  {
    super(paramOnSubscribe);
  }
  
  @Experimental
  public Throwable getThrowable()
  {
    throw new UnsupportedOperationException();
  }
  
  @Experimental
  public T getValue()
  {
    throw new UnsupportedOperationException();
  }
  
  @Experimental
  public Object[] getValues()
  {
    Object[] arrayOfObject2 = getValues((Object[])EMPTY_ARRAY);
    Object[] arrayOfObject1 = arrayOfObject2;
    if (arrayOfObject2 == EMPTY_ARRAY) {
      arrayOfObject1 = new Object[0];
    }
    return arrayOfObject1;
  }
  
  @Experimental
  public T[] getValues(T[] paramArrayOfT)
  {
    throw new UnsupportedOperationException();
  }
  
  @Experimental
  public boolean hasCompleted()
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean hasObservers();
  
  @Experimental
  public boolean hasThrowable()
  {
    throw new UnsupportedOperationException();
  }
  
  @Experimental
  public boolean hasValue()
  {
    throw new UnsupportedOperationException();
  }
  
  public final SerializedSubject<T, R> toSerialized()
  {
    if (getClass() == SerializedSubject.class) {
      return (SerializedSubject)this;
    }
    return new SerializedSubject(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/subjects/Subject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */