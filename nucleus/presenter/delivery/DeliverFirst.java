package nucleus.presenter.delivery;

import rx.Observable;
import rx.Observable.Transformer;

public class DeliverFirst<View, T>
  implements Observable.Transformer<T, Delivery<View, T>>
{
  private final Observable<View> view;
  
  public DeliverFirst(Observable<View> paramObservable)
  {
    this.view = paramObservable;
  }
  
  public Observable<Delivery<View, T>> call(Observable<T> paramObservable)
  {
    return Observable.combineLatest(this.view, paramObservable.materialize().take(1), new DeliverFirst.2(this)).filter(new DeliverFirst.1(this)).take(1);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/presenter/delivery/DeliverFirst.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */