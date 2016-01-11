package rx.functions;

public final class Actions
{
  private static final EmptyAction EMPTY_ACTION = new EmptyAction(null);
  
  private Actions()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> empty()
  {
    return EMPTY_ACTION;
  }
  
  public static Func0<Void> toFunc(Action0 paramAction0)
  {
    return toFunc(paramAction0, (Void)null);
  }
  
  public static <R> Func0<R> toFunc(Action0 paramAction0, final R paramR)
  {
    new Func0()
    {
      public R call()
      {
        this.val$action.call();
        return (R)paramR;
      }
    };
  }
  
  public static <T1> Func1<T1, Void> toFunc(Action1<T1> paramAction1)
  {
    return toFunc(paramAction1, (Void)null);
  }
  
  public static <T1, R> Func1<T1, R> toFunc(Action1<T1> paramAction1, final R paramR)
  {
    new Func1()
    {
      public R call(T1 paramAnonymousT1)
      {
        this.val$action.call(paramAnonymousT1);
        return (R)paramR;
      }
    };
  }
  
  public static <T1, T2> Func2<T1, T2, Void> toFunc(Action2<T1, T2> paramAction2)
  {
    return toFunc(paramAction2, (Void)null);
  }
  
  public static <T1, T2, R> Func2<T1, T2, R> toFunc(Action2<T1, T2> paramAction2, final R paramR)
  {
    new Func2()
    {
      public R call(T1 paramAnonymousT1, T2 paramAnonymousT2)
      {
        this.val$action.call(paramAnonymousT1, paramAnonymousT2);
        return (R)paramR;
      }
    };
  }
  
  public static <T1, T2, T3> Func3<T1, T2, T3, Void> toFunc(Action3<T1, T2, T3> paramAction3)
  {
    return toFunc(paramAction3, (Void)null);
  }
  
  public static <T1, T2, T3, R> Func3<T1, T2, T3, R> toFunc(Action3<T1, T2, T3> paramAction3, final R paramR)
  {
    new Func3()
    {
      public R call(T1 paramAnonymousT1, T2 paramAnonymousT2, T3 paramAnonymousT3)
      {
        this.val$action.call(paramAnonymousT1, paramAnonymousT2, paramAnonymousT3);
        return (R)paramR;
      }
    };
  }
  
  public static <T1, T2, T3, T4> Func4<T1, T2, T3, T4, Void> toFunc(Action4<T1, T2, T3, T4> paramAction4)
  {
    return toFunc(paramAction4, (Void)null);
  }
  
  public static <T1, T2, T3, T4, R> Func4<T1, T2, T3, T4, R> toFunc(Action4<T1, T2, T3, T4> paramAction4, final R paramR)
  {
    new Func4()
    {
      public R call(T1 paramAnonymousT1, T2 paramAnonymousT2, T3 paramAnonymousT3, T4 paramAnonymousT4)
      {
        this.val$action.call(paramAnonymousT1, paramAnonymousT2, paramAnonymousT3, paramAnonymousT4);
        return (R)paramR;
      }
    };
  }
  
  public static <T1, T2, T3, T4, T5> Func5<T1, T2, T3, T4, T5, Void> toFunc(Action5<T1, T2, T3, T4, T5> paramAction5)
  {
    return toFunc(paramAction5, (Void)null);
  }
  
  public static <T1, T2, T3, T4, T5, R> Func5<T1, T2, T3, T4, T5, R> toFunc(Action5<T1, T2, T3, T4, T5> paramAction5, final R paramR)
  {
    new Func5()
    {
      public R call(T1 paramAnonymousT1, T2 paramAnonymousT2, T3 paramAnonymousT3, T4 paramAnonymousT4, T5 paramAnonymousT5)
      {
        this.val$action.call(paramAnonymousT1, paramAnonymousT2, paramAnonymousT3, paramAnonymousT4, paramAnonymousT5);
        return (R)paramR;
      }
    };
  }
  
  public static <T1, T2, T3, T4, T5, T6> Func6<T1, T2, T3, T4, T5, T6, Void> toFunc(Action6<T1, T2, T3, T4, T5, T6> paramAction6)
  {
    return toFunc(paramAction6, (Void)null);
  }
  
  public static <T1, T2, T3, T4, T5, T6, R> Func6<T1, T2, T3, T4, T5, T6, R> toFunc(Action6<T1, T2, T3, T4, T5, T6> paramAction6, final R paramR)
  {
    new Func6()
    {
      public R call(T1 paramAnonymousT1, T2 paramAnonymousT2, T3 paramAnonymousT3, T4 paramAnonymousT4, T5 paramAnonymousT5, T6 paramAnonymousT6)
      {
        this.val$action.call(paramAnonymousT1, paramAnonymousT2, paramAnonymousT3, paramAnonymousT4, paramAnonymousT5, paramAnonymousT6);
        return (R)paramR;
      }
    };
  }
  
  public static <T1, T2, T3, T4, T5, T6, T7> Func7<T1, T2, T3, T4, T5, T6, T7, Void> toFunc(Action7<T1, T2, T3, T4, T5, T6, T7> paramAction7)
  {
    return toFunc(paramAction7, (Void)null);
  }
  
  public static <T1, T2, T3, T4, T5, T6, T7, R> Func7<T1, T2, T3, T4, T5, T6, T7, R> toFunc(Action7<T1, T2, T3, T4, T5, T6, T7> paramAction7, final R paramR)
  {
    new Func7()
    {
      public R call(T1 paramAnonymousT1, T2 paramAnonymousT2, T3 paramAnonymousT3, T4 paramAnonymousT4, T5 paramAnonymousT5, T6 paramAnonymousT6, T7 paramAnonymousT7)
      {
        this.val$action.call(paramAnonymousT1, paramAnonymousT2, paramAnonymousT3, paramAnonymousT4, paramAnonymousT5, paramAnonymousT6, paramAnonymousT7);
        return (R)paramR;
      }
    };
  }
  
  public static <T1, T2, T3, T4, T5, T6, T7, T8> Func8<T1, T2, T3, T4, T5, T6, T7, T8, Void> toFunc(Action8<T1, T2, T3, T4, T5, T6, T7, T8> paramAction8)
  {
    return toFunc(paramAction8, (Void)null);
  }
  
  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> toFunc(Action8<T1, T2, T3, T4, T5, T6, T7, T8> paramAction8, final R paramR)
  {
    new Func8()
    {
      public R call(T1 paramAnonymousT1, T2 paramAnonymousT2, T3 paramAnonymousT3, T4 paramAnonymousT4, T5 paramAnonymousT5, T6 paramAnonymousT6, T7 paramAnonymousT7, T8 paramAnonymousT8)
      {
        this.val$action.call(paramAnonymousT1, paramAnonymousT2, paramAnonymousT3, paramAnonymousT4, paramAnonymousT5, paramAnonymousT6, paramAnonymousT7, paramAnonymousT8);
        return (R)paramR;
      }
    };
  }
  
  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, Void> toFunc(Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> paramAction9)
  {
    return toFunc(paramAction9, (Void)null);
  }
  
  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> toFunc(Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> paramAction9, final R paramR)
  {
    new Func9()
    {
      public R call(T1 paramAnonymousT1, T2 paramAnonymousT2, T3 paramAnonymousT3, T4 paramAnonymousT4, T5 paramAnonymousT5, T6 paramAnonymousT6, T7 paramAnonymousT7, T8 paramAnonymousT8, T9 paramAnonymousT9)
      {
        this.val$action.call(paramAnonymousT1, paramAnonymousT2, paramAnonymousT3, paramAnonymousT4, paramAnonymousT5, paramAnonymousT6, paramAnonymousT7, paramAnonymousT8, paramAnonymousT9);
        return (R)paramR;
      }
    };
  }
  
  public static FuncN<Void> toFunc(ActionN paramActionN)
  {
    return toFunc(paramActionN, (Void)null);
  }
  
  public static <R> FuncN<R> toFunc(ActionN paramActionN, final R paramR)
  {
    new FuncN()
    {
      public R call(Object... paramAnonymousVarArgs)
      {
        this.val$action.call(paramAnonymousVarArgs);
        return (R)paramR;
      }
    };
  }
  
  private static final class EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8>
    implements Action0, Action1<T0>, Action2<T0, T1>, Action3<T0, T1, T2>, Action4<T0, T1, T2, T3>, Action5<T0, T1, T2, T3, T4>, Action6<T0, T1, T2, T3, T4, T5>, Action7<T0, T1, T2, T3, T4, T5, T6>, Action8<T0, T1, T2, T3, T4, T5, T6, T7>, Action9<T0, T1, T2, T3, T4, T5, T6, T7, T8>, ActionN
  {
    public void call() {}
    
    public void call(T0 paramT0) {}
    
    public void call(T0 paramT0, T1 paramT1) {}
    
    public void call(T0 paramT0, T1 paramT1, T2 paramT2) {}
    
    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3) {}
    
    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4) {}
    
    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5) {}
    
    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6) {}
    
    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7) {}
    
    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8) {}
    
    public void call(Object... paramVarArgs) {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/functions/Actions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */