package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorOnErrorResumeNextViaObservable<T>
  implements Observable.Operator<T, T>
{
  final Observable<? extends T> resumeSequence;
  
  public OperatorOnErrorResumeNextViaObservable(Observable<? extends T> paramObservable)
  {
    this.resumeSequence = paramObservable;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    OperatorOnErrorResumeNextViaObservable.1 local1 = new OperatorOnErrorResumeNextViaObservable.1(this, paramSubscriber);
    paramSubscriber.add(local1);
    return local1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorOnErrorResumeNextViaObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */