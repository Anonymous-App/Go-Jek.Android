package rx;

import rx.internal.util.SubscriptionList;

public abstract class Subscriber<T>
  implements Observer<T>, Subscription
{
  private static final Long NOT_SET = Long.valueOf(Long.MIN_VALUE);
  private Producer producer;
  private long requested = NOT_SET.longValue();
  private final Subscriber<?> subscriber;
  private final SubscriptionList subscriptions;
  
  protected Subscriber()
  {
    this(null, false);
  }
  
  protected Subscriber(Subscriber<?> paramSubscriber)
  {
    this(paramSubscriber, true);
  }
  
  protected Subscriber(Subscriber<?> paramSubscriber, boolean paramBoolean)
  {
    this.subscriber = paramSubscriber;
    if ((paramBoolean) && (paramSubscriber != null)) {}
    for (paramSubscriber = paramSubscriber.subscriptions;; paramSubscriber = new SubscriptionList())
    {
      this.subscriptions = paramSubscriber;
      return;
    }
  }
  
  private void addToRequested(long paramLong)
  {
    if (this.requested == NOT_SET.longValue())
    {
      this.requested = paramLong;
      return;
    }
    paramLong = this.requested + paramLong;
    if (paramLong < 0L)
    {
      this.requested = Long.MAX_VALUE;
      return;
    }
    this.requested = paramLong;
  }
  
  public final void add(Subscription paramSubscription)
  {
    this.subscriptions.add(paramSubscription);
  }
  
  public final boolean isUnsubscribed()
  {
    return this.subscriptions.isUnsubscribed();
  }
  
  public void onStart() {}
  
  protected final void request(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("number requested cannot be negative: " + paramLong);
    }
    try
    {
      if (this.producer != null)
      {
        Producer localProducer = this.producer;
        localProducer.request(paramLong);
        return;
      }
      addToRequested(paramLong);
      return;
    }
    finally {}
  }
  
  public void setProducer(Producer paramProducer)
  {
    int j = 0;
    long l;
    try
    {
      l = this.requested;
      this.producer = paramProducer;
      int i = j;
      if (this.subscriber != null)
      {
        i = j;
        if (l == NOT_SET.longValue()) {
          i = 1;
        }
      }
      if (i != 0)
      {
        this.subscriber.setProducer(this.producer);
        return;
      }
    }
    finally {}
    if (l == NOT_SET.longValue())
    {
      this.producer.request(Long.MAX_VALUE);
      return;
    }
    this.producer.request(l);
  }
  
  public final void unsubscribe()
  {
    this.subscriptions.unsubscribe();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/Subscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */