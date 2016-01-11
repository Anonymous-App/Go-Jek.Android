package nucleus.presenter.delivery;

import rx.Observable;
import rx.Observable.Transformer;

public class DeliverLatestCache<View, T>
  implements Observable.Transformer<T, Delivery<View, T>>
{
  private final Observable<View> view;
  
  public DeliverLatestCache(Observable<View> paramObservable)
  {
    this.view = paramObservable;
  }
  
  public Observable<Delivery<View, T>> call(Observable<T> paramObservable)
  {
    return Observable.combineLatest(this.view, paramObservable.materialize().filter(new DeliverLatestCache.2(this)), new DeliverLatestCache.3(this)).filter(new DeliverLatestCache.1(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/presenter/delivery/DeliverLatestCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */