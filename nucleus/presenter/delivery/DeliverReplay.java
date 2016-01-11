package nucleus.presenter.delivery;

import rx.Observable;
import rx.Observable.Transformer;
import rx.subjects.ReplaySubject;

public class DeliverReplay<View, T>
  implements Observable.Transformer<T, Delivery<View, T>>
{
  private final Observable<View> view;
  
  public DeliverReplay(Observable<View> paramObservable)
  {
    this.view = paramObservable;
  }
  
  public Observable<Delivery<View, T>> call(Observable<T> paramObservable)
  {
    ReplaySubject localReplaySubject = ReplaySubject.create();
    paramObservable = paramObservable.materialize().filter(new DeliverReplay.1(this)).subscribe(localReplaySubject);
    return this.view.switchMap(new DeliverReplay.3(this, localReplaySubject)).doOnUnsubscribe(new DeliverReplay.2(this, paramObservable));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/presenter/delivery/DeliverReplay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */