package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.NotificationLite;
import rx.subscriptions.Subscriptions;

final class SubjectSubscriptionManager<T>
  implements Observable.OnSubscribe<T>
{
  static final AtomicReferenceFieldUpdater<SubjectSubscriptionManager, Object> LATEST_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SubjectSubscriptionManager.class, Object.class, "latest");
  static final AtomicReferenceFieldUpdater<SubjectSubscriptionManager, State> STATE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SubjectSubscriptionManager.class, State.class, "state");
  boolean active = true;
  volatile Object latest;
  public final NotificationLite<T> nl = NotificationLite.instance();
  Action1<SubjectObserver<T>> onAdded = Actions.empty();
  Action1<SubjectObserver<T>> onStart = Actions.empty();
  Action1<SubjectObserver<T>> onTerminated = Actions.empty();
  volatile State<T> state = State.EMPTY;
  
  boolean add(SubjectObserver<T> paramSubjectObserver)
  {
    State localState1;
    State localState2;
    do
    {
      localState1 = this.state;
      if (localState1.terminated)
      {
        this.onTerminated.call(paramSubjectObserver);
        return false;
      }
      localState2 = localState1.add(paramSubjectObserver);
    } while (!STATE_UPDATER.compareAndSet(this, localState1, localState2));
    this.onAdded.call(paramSubjectObserver);
    return true;
  }
  
  void addUnsubscriber(Subscriber<? super T> paramSubscriber, final SubjectObserver<T> paramSubjectObserver)
  {
    paramSubscriber.add(Subscriptions.create(new Action0()
    {
      public void call()
      {
        SubjectSubscriptionManager.this.remove(paramSubjectObserver);
      }
    }));
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    SubjectObserver localSubjectObserver = new SubjectObserver(paramSubscriber);
    addUnsubscriber(paramSubscriber, localSubjectObserver);
    this.onStart.call(localSubjectObserver);
    if ((!paramSubscriber.isUnsubscribed()) && (add(localSubjectObserver)) && (paramSubscriber.isUnsubscribed())) {
      remove(localSubjectObserver);
    }
  }
  
  Object get()
  {
    return this.latest;
  }
  
  SubjectObserver<T>[] next(Object paramObject)
  {
    set(paramObject);
    return this.state.observers;
  }
  
  SubjectObserver<T>[] observers()
  {
    return this.state.observers;
  }
  
  void remove(SubjectObserver<T> paramSubjectObserver)
  {
    State localState1;
    State localState2;
    do
    {
      localState1 = this.state;
      if (localState1.terminated) {}
      do
      {
        return;
        localState2 = localState1.remove(paramSubjectObserver);
      } while (localState2 == localState1);
    } while (!STATE_UPDATER.compareAndSet(this, localState1, localState2));
  }
  
  void set(Object paramObject)
  {
    this.latest = paramObject;
  }
  
  SubjectObserver<T>[] terminate(Object paramObject)
  {
    set(paramObject);
    this.active = false;
    if (this.state.terminated) {
      return State.NO_OBSERVERS;
    }
    return ((State)STATE_UPDATER.getAndSet(this, State.TERMINATED)).observers;
  }
  
  protected static final class State<T>
  {
    static final State EMPTY = new State(false, NO_OBSERVERS);
    static final SubjectSubscriptionManager.SubjectObserver[] NO_OBSERVERS = new SubjectSubscriptionManager.SubjectObserver[0];
    static final State TERMINATED = new State(true, NO_OBSERVERS);
    final SubjectSubscriptionManager.SubjectObserver[] observers;
    final boolean terminated;
    
    public State(boolean paramBoolean, SubjectSubscriptionManager.SubjectObserver[] paramArrayOfSubjectObserver)
    {
      this.terminated = paramBoolean;
      this.observers = paramArrayOfSubjectObserver;
    }
    
    public State add(SubjectSubscriptionManager.SubjectObserver paramSubjectObserver)
    {
      int i = this.observers.length;
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = new SubjectSubscriptionManager.SubjectObserver[i + 1];
      System.arraycopy(this.observers, 0, arrayOfSubjectObserver, 0, i);
      arrayOfSubjectObserver[i] = paramSubjectObserver;
      return new State(this.terminated, arrayOfSubjectObserver);
    }
    
    public State remove(SubjectSubscriptionManager.SubjectObserver paramSubjectObserver)
    {
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver2 = this.observers;
      int m = arrayOfSubjectObserver2.length;
      State localState;
      if ((m == 1) && (arrayOfSubjectObserver2[0] == paramSubjectObserver)) {
        localState = EMPTY;
      }
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver1;
      int j;
      SubjectSubscriptionManager.SubjectObserver localSubjectObserver;
      do
      {
        do
        {
          return localState;
          localState = this;
        } while (m == 0);
        arrayOfSubjectObserver1 = new SubjectSubscriptionManager.SubjectObserver[m - 1];
        j = 0;
        i = 0;
        if (j >= m) {
          break;
        }
        localSubjectObserver = arrayOfSubjectObserver2[j];
        if (localSubjectObserver == paramSubjectObserver) {
          break label150;
        }
        localState = this;
      } while (i == m - 1);
      int k = i + 1;
      arrayOfSubjectObserver1[i] = localSubjectObserver;
      int i = k;
      label150:
      for (;;)
      {
        j += 1;
        break;
        if (i == 0) {
          return EMPTY;
        }
        paramSubjectObserver = arrayOfSubjectObserver1;
        if (i < m - 1)
        {
          paramSubjectObserver = new SubjectSubscriptionManager.SubjectObserver[i];
          System.arraycopy(arrayOfSubjectObserver1, 0, paramSubjectObserver, 0, i);
        }
        return new State(this.terminated, paramSubjectObserver);
      }
    }
  }
  
  protected static final class SubjectObserver<T>
    implements Observer<T>
  {
    final Observer<? super T> actual;
    protected volatile boolean caughtUp;
    boolean emitting;
    boolean fastPath;
    boolean first = true;
    private volatile Object index;
    List<Object> queue;
    
    public SubjectObserver(Observer<? super T> paramObserver)
    {
      this.actual = paramObserver;
    }
    
    protected void accept(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      if (paramObject != null) {
        paramNotificationLite.accept(this.actual, paramObject);
      }
    }
    
    protected void emitFirst(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      boolean bool = false;
      try
      {
        if ((!this.first) || (this.emitting)) {
          return;
        }
        this.first = false;
        if (paramObject != null) {
          bool = true;
        }
        this.emitting = bool;
        if (paramObject != null)
        {
          emitLoop(null, paramObject, paramNotificationLite);
          return;
        }
      }
      finally {}
    }
    
    /* Error */
    protected void emitLoop(List<Object> paramList, Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      // Byte code:
      //   0: iconst_1
      //   1: istore 6
      //   3: iconst_0
      //   4: istore 8
      //   6: iconst_0
      //   7: istore 7
      //   9: aload_1
      //   10: ifnull +62 -> 72
      //   13: iload 7
      //   15: istore 4
      //   17: aload_1
      //   18: invokeinterface 56 1 0
      //   23: astore_1
      //   24: iload 7
      //   26: istore 4
      //   28: aload_1
      //   29: invokeinterface 62 1 0
      //   34: ifeq +38 -> 72
      //   37: iload 7
      //   39: istore 4
      //   41: aload_0
      //   42: aload_1
      //   43: invokeinterface 66 1 0
      //   48: aload_3
      //   49: invokevirtual 68	rx/subjects/SubjectSubscriptionManager$SubjectObserver:accept	(Ljava/lang/Object;Lrx/internal/operators/NotificationLite;)V
      //   52: goto -28 -> 24
      //   55: astore_1
      //   56: iload 4
      //   58: ifne +12 -> 70
      //   61: aload_0
      //   62: monitorenter
      //   63: aload_0
      //   64: iconst_0
      //   65: putfield 46	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   68: aload_0
      //   69: monitorexit
      //   70: aload_1
      //   71: athrow
      //   72: iload 6
      //   74: istore 5
      //   76: iload 6
      //   78: ifeq +16 -> 94
      //   81: iconst_0
      //   82: istore 5
      //   84: iload 7
      //   86: istore 4
      //   88: aload_0
      //   89: aload_2
      //   90: aload_3
      //   91: invokevirtual 68	rx/subjects/SubjectSubscriptionManager$SubjectObserver:accept	(Ljava/lang/Object;Lrx/internal/operators/NotificationLite;)V
      //   94: iload 7
      //   96: istore 4
      //   98: aload_0
      //   99: monitorenter
      //   100: iload 8
      //   102: istore 4
      //   104: aload_0
      //   105: getfield 70	rx/subjects/SubjectSubscriptionManager$SubjectObserver:queue	Ljava/util/List;
      //   108: astore_1
      //   109: iload 8
      //   111: istore 4
      //   113: aload_0
      //   114: aconst_null
      //   115: putfield 70	rx/subjects/SubjectSubscriptionManager$SubjectObserver:queue	Ljava/util/List;
      //   118: aload_1
      //   119: ifnonnull +31 -> 150
      //   122: iload 8
      //   124: istore 4
      //   126: aload_0
      //   127: iconst_0
      //   128: putfield 46	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   131: iconst_1
      //   132: istore 4
      //   134: aload_0
      //   135: monitorexit
      //   136: iconst_1
      //   137: ifne +12 -> 149
      //   140: aload_0
      //   141: monitorenter
      //   142: aload_0
      //   143: iconst_0
      //   144: putfield 46	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   147: aload_0
      //   148: monitorexit
      //   149: return
      //   150: iload 8
      //   152: istore 4
      //   154: aload_0
      //   155: monitorexit
      //   156: iload 5
      //   158: istore 6
      //   160: goto -151 -> 9
      //   163: astore_1
      //   164: aload_0
      //   165: monitorexit
      //   166: aload_1
      //   167: athrow
      //   168: astore_1
      //   169: aload_0
      //   170: monitorexit
      //   171: aload_1
      //   172: athrow
      //   173: astore_1
      //   174: aload_0
      //   175: monitorexit
      //   176: aload_1
      //   177: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	178	0	this	SubjectObserver
      //   0	178	1	paramList	List<Object>
      //   0	178	2	paramObject	Object
      //   0	178	3	paramNotificationLite	NotificationLite<T>
      //   15	138	4	i	int
      //   74	83	5	j	int
      //   1	158	6	k	int
      //   7	88	7	m	int
      //   4	147	8	n	int
      // Exception table:
      //   from	to	target	type
      //   17	24	55	finally
      //   28	37	55	finally
      //   41	52	55	finally
      //   88	94	55	finally
      //   98	100	55	finally
      //   166	168	55	finally
      //   104	109	163	finally
      //   113	118	163	finally
      //   126	131	163	finally
      //   134	136	163	finally
      //   154	156	163	finally
      //   164	166	163	finally
      //   142	149	168	finally
      //   169	171	168	finally
      //   63	70	173	finally
      //   174	176	173	finally
    }
    
    protected void emitNext(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      if (!this.fastPath) {}
      try
      {
        this.first = false;
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(paramObject);
          return;
        }
        this.fastPath = true;
        paramNotificationLite.accept(this.actual, paramObject);
        return;
      }
      finally {}
    }
    
    protected Observer<? super T> getActual()
    {
      return this.actual;
    }
    
    public <I> I index()
    {
      return (I)this.index;
    }
    
    public void index(Object paramObject)
    {
      this.index = paramObject;
    }
    
    public void onCompleted()
    {
      this.actual.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.actual.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.actual.onNext(paramT);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/subjects/SubjectSubscriptionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */