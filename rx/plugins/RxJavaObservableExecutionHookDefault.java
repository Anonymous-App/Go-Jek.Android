package rx.plugins;

class RxJavaObservableExecutionHookDefault
  extends RxJavaObservableExecutionHook
{
  private static RxJavaObservableExecutionHookDefault INSTANCE = new RxJavaObservableExecutionHookDefault();
  
  public static RxJavaObservableExecutionHook getInstance()
  {
    return INSTANCE;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/plugins/RxJavaObservableExecutionHookDefault.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */