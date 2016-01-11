package rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class CompositeSubscription
  implements Subscription
{
  private Set<Subscription> subscriptions;
  private volatile boolean unsubscribed;
  
  public CompositeSubscription() {}
  
  public CompositeSubscription(Subscription... paramVarArgs)
  {
    this.subscriptions = new HashSet(Arrays.asList(paramVarArgs));
  }
  
  private static void unsubscribeFromAll(Collection<Subscription> paramCollection)
  {
    if (paramCollection == null) {
      return;
    }
    Object localObject = null;
    Iterator localIterator = paramCollection.iterator();
    paramCollection = (Collection<Subscription>)localObject;
    while (localIterator.hasNext())
    {
      localObject = (Subscription)localIterator.next();
      try
      {
        ((Subscription)localObject).unsubscribe();
      }
      catch (Throwable localThrowable)
      {
        localObject = paramCollection;
        if (paramCollection == null) {
          localObject = new ArrayList();
        }
        ((List)localObject).add(localThrowable);
        paramCollection = (Collection<Subscription>)localObject;
      }
    }
    Exceptions.throwIfAny(paramCollection);
  }
  
  public void add(Subscription paramSubscription)
  {
    if (paramSubscription.isUnsubscribed()) {
      return;
    }
    if (!this.unsubscribed) {
      try
      {
        if (!this.unsubscribed)
        {
          if (this.subscriptions == null) {
            this.subscriptions = new HashSet(4);
          }
          this.subscriptions.add(paramSubscription);
          return;
        }
      }
      finally {}
    }
    paramSubscription.unsubscribe();
  }
  
  public void clear()
  {
    if (!this.unsubscribed) {
      try
      {
        if ((this.unsubscribed) || (this.subscriptions == null)) {
          return;
        }
        Set localSet = this.subscriptions;
        this.subscriptions = null;
        unsubscribeFromAll(localSet);
        return;
      }
      finally {}
    }
  }
  
  public boolean hasSubscriptions()
  {
    boolean bool2 = false;
    if (!this.unsubscribed)
    {
      boolean bool1 = bool2;
      try
      {
        if (!this.unsubscribed)
        {
          bool1 = bool2;
          if (this.subscriptions != null)
          {
            bool1 = bool2;
            if (!this.subscriptions.isEmpty()) {
              bool1 = true;
            }
          }
        }
        return bool1;
      }
      finally {}
    }
    return false;
  }
  
  public boolean isUnsubscribed()
  {
    return this.unsubscribed;
  }
  
  public void remove(Subscription paramSubscription)
  {
    if (!this.unsubscribed) {
      try
      {
        if ((this.unsubscribed) || (this.subscriptions == null)) {
          return;
        }
        boolean bool = this.subscriptions.remove(paramSubscription);
        if (bool)
        {
          paramSubscription.unsubscribe();
          return;
        }
      }
      finally {}
    }
  }
  
  public void unsubscribe()
  {
    if (!this.unsubscribed) {
      try
      {
        if (this.unsubscribed) {
          return;
        }
        this.unsubscribed = true;
        Set localSet = this.subscriptions;
        this.subscriptions = null;
        unsubscribeFromAll(localSet);
        return;
      }
      finally {}
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/subscriptions/CompositeSubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */