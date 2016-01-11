package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorOnExceptionResumeNextViaObservable<T>
  implements Observable.Operator<T, T>
{
  final Observable<? extends T> resumeSequence;
  
  public OperatorOnExceptionResumeNextViaObservable(Observable<? extends T> paramObservable)
  {
    this.resumeSequence = paramObservable;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    OperatorOnExceptionResumeNextViaObservable.1 local1 = new OperatorOnExceptionResumeNextViaObservable.1(this, paramSubscriber);
    paramSubscriber.add(local1);
    return local1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/internal/operators/OperatorOnExceptionResumeNextViaObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */