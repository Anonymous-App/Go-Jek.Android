package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import rx.Observable.OnSubscribe;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;

public final class BehaviorSubject<T>
  extends Subject<T, T>
{
  private final NotificationLite<T> nl = NotificationLite.instance();
  private final SubjectSubscriptionManager<T> state;
  
  protected BehaviorSubject(Observable.OnSubscribe<T> paramOnSubscribe, SubjectSubscriptionManager<T> paramSubjectSubscriptionManager)
  {
    super(paramOnSubscribe);
    this.state = paramSubjectSubscriptionManager;
  }
  
  public static <T> BehaviorSubject<T> create()
  {
    return create(null, false);
  }
  
  public static <T> BehaviorSubject<T> create(T paramT)
  {
    return create(paramT, true);
  }
  
  private static <T> BehaviorSubject<T> create(T paramT, boolean paramBoolean)
  {
    SubjectSubscriptionManager localSubjectSubscriptionManager = new SubjectSubscriptionManager();
    if (paramBoolean) {
      localSubjectSubscriptionManager.set(NotificationLite.instance().next(paramT));
    }
    localSubjectSubscriptionManager.onAdded = new Action1()
    {
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramAnonymousSubjectObserver)
      {
        paramAnonymousSubjectObserver.emitFirst(this.val$state.get(), this.val$state.nl);
      }
    };
    localSubjectSubscriptionManager.onTerminated = localSubjectSubscriptionManager.onAdded;
    return new BehaviorSubject(localSubjectSubscriptionManager, localSubjectSubscriptionManager);
  }
  
  @Experimental
  public Throwable getThrowable()
  {
    Object localObject = this.state.get();
    if (this.nl.isError(localObject)) {
      return this.nl.getError(localObject);
    }
    return null;
  }
  
  @Experimental
  public T getValue()
  {
    Object localObject = this.state.get();
    if (this.nl.isNext(localObject)) {
      return (T)this.nl.getValue(localObject);
    }
    return null;
  }
  
  @Experimental
  public T[] getValues(T[] paramArrayOfT)
  {
    Object localObject2 = this.state.get();
    if (this.nl.isNext(localObject2))
    {
      Object localObject1 = paramArrayOfT;
      if (paramArrayOfT.length == 0) {
        localObject1 = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), 1);
      }
      localObject1[0] = this.nl.getValue(localObject2);
      localObject2 = localObject1;
      if (localObject1.length > 1)
      {
        localObject1[1] = null;
        localObject2 = localObject1;
      }
    }
    do
    {
      return (T[])localObject2;
      localObject2 = paramArrayOfT;
    } while (paramArrayOfT.length <= 0);
    paramArrayOfT[0] = null;
    return paramArrayOfT;
  }
  
  @Experimental
  public boolean hasCompleted()
  {
    Object localObject = this.state.get();
    return this.nl.isCompleted(localObject);
  }
  
  public boolean hasObservers()
  {
    return this.state.observers().length > 0;
  }
  
  @Experimental
  public boolean hasThrowable()
  {
    Object localObject = this.state.get();
    return this.nl.isError(localObject);
  }
  
  @Experimental
  public boolean hasValue()
  {
    Object localObject = this.state.get();
    return this.nl.isNext(localObject);
  }
  
  public void onCompleted()
  {
    if ((this.state.get() == null) || (this.state.active))
    {
      Object localObject = this.nl.completed();
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.terminate(localObject);
      int j = arrayOfSubjectObserver.length;
      int i = 0;
      while (i < j)
      {
        arrayOfSubjectObserver[i].emitNext(localObject, this.state.nl);
        i += 1;
      }
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    if ((this.state.get() == null) || (this.state.active))
    {
      Object localObject2 = this.nl.error(paramThrowable);
      paramThrowable = null;
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.terminate(localObject2);
      int j = arrayOfSubjectObserver.length;
      int i = 0;
      for (;;)
      {
        if (i < j)
        {
          Object localObject1 = arrayOfSubjectObserver[i];
          try
          {
            ((SubjectSubscriptionManager.SubjectObserver)localObject1).emitNext(localObject2, this.state.nl);
            i += 1;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              localObject1 = paramThrowable;
              if (paramThrowable == null) {
                localObject1 = new ArrayList();
              }
              ((List)localObject1).add(localThrowable);
              paramThrowable = (Throwable)localObject1;
            }
          }
        }
      }
      Exceptions.throwIfAny(paramThrowable);
    }
  }
  
  public void onNext(T paramT)
  {
    if ((this.state.get() == null) || (this.state.active))
    {
      paramT = this.nl.next(paramT);
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.next(paramT);
      int j = arrayOfSubjectObserver.length;
      int i = 0;
      while (i < j)
      {
        arrayOfSubjectObserver[i].emitNext(paramT, this.state.nl);
        i += 1;
      }
    }
  }
  
  int subscriberCount()
  {
    return this.state.observers().length;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/subjects/BehaviorSubject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */