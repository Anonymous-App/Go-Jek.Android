package nucleus.presenter;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import nucleus.presenter.delivery.DeliverFirst;
import nucleus.presenter.delivery.DeliverLatestCache;
import nucleus.presenter.delivery.DeliverReplay;
import nucleus.presenter.delivery.Delivery;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.internal.util.SubscriptionList;
import rx.subjects.BehaviorSubject;

public class RxPresenter<View>
  extends Presenter<View>
{
  private static final String REQUESTED_KEY = RxPresenter.class.getName() + "#requested";
  private final ArrayList<Integer> requested = new ArrayList();
  private final HashMap<Integer, Subscription> restartableSubscriptions = new HashMap();
  private final HashMap<Integer, Func0<Subscription>> restartables = new HashMap();
  private final SubscriptionList subscriptions = new SubscriptionList();
  private final BehaviorSubject<View> views = BehaviorSubject.create();
  
  public void add(Subscription paramSubscription)
  {
    this.subscriptions.add(paramSubscription);
  }
  
  public <T> DeliverFirst<View, T> deliverFirst()
  {
    return new DeliverFirst(this.views);
  }
  
  public <T> DeliverLatestCache<View, T> deliverLatestCache()
  {
    return new DeliverLatestCache(this.views);
  }
  
  public <T> DeliverReplay<View, T> deliverReplay()
  {
    return new DeliverReplay(this.views);
  }
  
  @CallSuper
  protected void onCreate(Bundle paramBundle)
  {
    if (paramBundle != null) {
      this.requested.addAll(paramBundle.getIntegerArrayList(REQUESTED_KEY));
    }
  }
  
  @CallSuper
  protected void onDestroy()
  {
    super.onDestroy();
    this.views.onCompleted();
    this.subscriptions.unsubscribe();
    Iterator localIterator = this.restartableSubscriptions.entrySet().iterator();
    while (localIterator.hasNext()) {
      ((Subscription)((Map.Entry)localIterator.next()).getValue()).unsubscribe();
    }
  }
  
  @CallSuper
  protected void onDropView()
  {
    super.onDropView();
    this.views.onNext(null);
  }
  
  @CallSuper
  protected void onSave(Bundle paramBundle)
  {
    super.onSave(paramBundle);
    int i = this.requested.size() - 1;
    while (i >= 0)
    {
      int j = ((Integer)this.requested.get(i)).intValue();
      Subscription localSubscription = (Subscription)this.restartableSubscriptions.get(Integer.valueOf(j));
      if ((localSubscription != null) && (localSubscription.isUnsubscribed())) {
        this.requested.remove(i);
      }
      i -= 1;
    }
    paramBundle.putIntegerArrayList(REQUESTED_KEY, this.requested);
  }
  
  @CallSuper
  protected void onTakeView(View paramView)
  {
    super.onTakeView(paramView);
    this.views.onNext(paramView);
  }
  
  public void remove(Subscription paramSubscription)
  {
    this.subscriptions.remove(paramSubscription);
  }
  
  public void restartable(int paramInt, Func0<Subscription> paramFunc0)
  {
    this.restartables.put(Integer.valueOf(paramInt), paramFunc0);
    if (this.requested.contains(Integer.valueOf(paramInt))) {
      start(paramInt);
    }
  }
  
  public <T> void restartableFirst(int paramInt, Func0<Observable<T>> paramFunc0, Action2<View, T> paramAction2)
  {
    restartableFirst(paramInt, paramFunc0, paramAction2, null);
  }
  
  public <T> void restartableFirst(int paramInt, final Func0<Observable<T>> paramFunc0, final Action2<View, T> paramAction2, @Nullable final Action2<View, Throwable> paramAction21)
  {
    restartable(paramInt, new Func0()
    {
      public Subscription call()
      {
        return ((Observable)paramFunc0.call()).compose(RxPresenter.this.deliverFirst()).subscribe(RxPresenter.this.split(paramAction2, paramAction21));
      }
    });
  }
  
  public <T> void restartableLatestCache(int paramInt, Func0<Observable<T>> paramFunc0, Action2<View, T> paramAction2)
  {
    restartableLatestCache(paramInt, paramFunc0, paramAction2, null);
  }
  
  public <T> void restartableLatestCache(int paramInt, final Func0<Observable<T>> paramFunc0, final Action2<View, T> paramAction2, @Nullable final Action2<View, Throwable> paramAction21)
  {
    restartable(paramInt, new Func0()
    {
      public Subscription call()
      {
        return ((Observable)paramFunc0.call()).compose(RxPresenter.this.deliverLatestCache()).subscribe(RxPresenter.this.split(paramAction2, paramAction21));
      }
    });
  }
  
  public <T> void restartableReplay(int paramInt, Func0<Observable<T>> paramFunc0, Action2<View, T> paramAction2)
  {
    restartableReplay(paramInt, paramFunc0, paramAction2, null);
  }
  
  public <T> void restartableReplay(int paramInt, final Func0<Observable<T>> paramFunc0, final Action2<View, T> paramAction2, @Nullable final Action2<View, Throwable> paramAction21)
  {
    restartable(paramInt, new Func0()
    {
      public Subscription call()
      {
        return ((Observable)paramFunc0.call()).compose(RxPresenter.this.deliverReplay()).subscribe(RxPresenter.this.split(paramAction2, paramAction21));
      }
    });
  }
  
  public <T> Action1<Delivery<View, T>> split(Action2<View, T> paramAction2)
  {
    return split(paramAction2, null);
  }
  
  public <T> Action1<Delivery<View, T>> split(final Action2<View, T> paramAction2, @Nullable final Action2<View, Throwable> paramAction21)
  {
    new Action1()
    {
      public void call(Delivery<View, T> paramAnonymousDelivery)
      {
        paramAnonymousDelivery.split(paramAction2, paramAction21);
      }
    };
  }
  
  public void start(int paramInt)
  {
    stop(paramInt);
    this.requested.add(Integer.valueOf(paramInt));
    this.restartableSubscriptions.put(Integer.valueOf(paramInt), ((Func0)this.restartables.get(Integer.valueOf(paramInt))).call());
  }
  
  public void stop(int paramInt)
  {
    this.requested.remove(Integer.valueOf(paramInt));
    Subscription localSubscription = (Subscription)this.restartableSubscriptions.get(Integer.valueOf(paramInt));
    if (localSubscription != null) {
      localSubscription.unsubscribe();
    }
  }
  
  public Observable<View> view()
  {
    return this.views;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/presenter/RxPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */