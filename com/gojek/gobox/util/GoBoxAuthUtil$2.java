package com.gojek.gobox.util;

import android.content.Context;
import rx.Subscriber;

class GoBoxAuthUtil$2
  extends Subscriber<Boolean>
{
  GoBoxAuthUtil$2(GoBoxAuthUtil paramGoBoxAuthUtil) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    if ((GoBoxAuthUtil.access$400(this.this$0) != null) && (GoBoxAuthUtil.access$500(this.this$0) != null))
    {
      GoBoxAuthUtil.access$500(this.this$0).onAuthenticationFailed();
      GoBoxAuthUtil.access$400(this.this$0).unbindService(GoBoxAuthUtil.access$600(this.this$0));
    }
  }
  
  public void onNext(Boolean paramBoolean)
  {
    if ((GoBoxAuthUtil.access$400(this.this$0) != null) && (GoBoxAuthUtil.access$500(this.this$0) != null))
    {
      GoBoxAuthUtil.access$500(this.this$0).onAuthenticationSuccess();
      GoBoxAuthUtil.access$400(this.this$0).unbindService(GoBoxAuthUtil.access$600(this.this$0));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/GoBoxAuthUtil$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */