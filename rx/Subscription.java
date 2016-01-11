package rx;

public abstract interface Subscription
{
  public abstract boolean isUnsubscribed();
  
  public abstract void unsubscribe();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/Subscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */