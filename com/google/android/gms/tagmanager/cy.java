package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class cy
  extends cx
{
  private static cy arA;
  private static final Object yc = new Object();
  private Context arq;
  private at arr;
  private volatile ar ars;
  private int art = 1800000;
  private boolean aru = true;
  private boolean arv = false;
  private boolean arw = true;
  private au arx = new au()
  {
    public void z(boolean paramAnonymousBoolean)
    {
      cy.this.a(paramAnonymousBoolean, cy.a(cy.this));
    }
  };
  private bo ary;
  private boolean arz = false;
  private boolean connected = true;
  private Handler handler;
  
  private void ea()
  {
    this.ary = new bo(this);
    this.ary.z(this.arq);
  }
  
  private void eb()
  {
    this.handler = new Handler(this.arq.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        if ((1 == paramAnonymousMessage.what) && (cy.ee().equals(paramAnonymousMessage.obj)))
        {
          cy.this.dispatch();
          if ((cy.b(cy.this) > 0) && (!cy.c(cy.this))) {
            cy.d(cy.this).sendMessageDelayed(cy.d(cy.this).obtainMessage(1, cy.ee()), cy.b(cy.this));
          }
        }
        return true;
      }
    });
    if (this.art > 0) {
      this.handler.sendMessageDelayed(this.handler.obtainMessage(1, yc), this.art);
    }
  }
  
  public static cy pw()
  {
    if (arA == null) {
      arA = new cy();
    }
    return arA;
  }
  
  void A(boolean paramBoolean)
  {
    try
    {
      a(this.arz, paramBoolean);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  void a(Context paramContext, ar paramar)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 86	com/google/android/gms/tagmanager/cy:arq	Landroid/content/Context;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnull +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual 128	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   19: putfield 86	com/google/android/gms/tagmanager/cy:arq	Landroid/content/Context;
    //   22: aload_0
    //   23: getfield 130	com/google/android/gms/tagmanager/cy:ars	Lcom/google/android/gms/tagmanager/ar;
    //   26: ifnonnull -15 -> 11
    //   29: aload_0
    //   30: aload_2
    //   31: putfield 130	com/google/android/gms/tagmanager/cy:ars	Lcom/google/android/gms/tagmanager/ar;
    //   34: goto -23 -> 11
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	this	cy
    //   0	42	1	paramContext	Context
    //   0	42	2	paramar	ar
    //   6	2	3	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   2	7	37	finally
    //   14	34	37	finally
  }
  
  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      StringBuilder localStringBuilder;
      String str1;
      try
      {
        if (this.arz == paramBoolean1)
        {
          boolean bool = this.connected;
          if (bool == paramBoolean2) {
            return;
          }
        }
        if (((paramBoolean1) || (!paramBoolean2)) && (this.art > 0)) {
          this.handler.removeMessages(1, yc);
        }
        if ((!paramBoolean1) && (paramBoolean2) && (this.art > 0)) {
          this.handler.sendMessageDelayed(this.handler.obtainMessage(1, yc), this.art);
        }
        localStringBuilder = new StringBuilder().append("PowerSaveMode ");
        if (paramBoolean1) {
          break label153;
        }
        if (paramBoolean2) {
          break label146;
        }
      }
      finally {}
      bh.V(str1);
      this.arz = paramBoolean1;
      this.connected = paramBoolean2;
      continue;
      label146:
      String str2 = "terminated.";
      continue;
      label153:
      str2 = "initiated.";
    }
  }
  
  /* Error */
  public void dispatch()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 52	com/google/android/gms/tagmanager/cy:arv	Z
    //   6: ifne +16 -> 22
    //   9: ldc -96
    //   11: invokestatic 153	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 50	com/google/android/gms/tagmanager/cy:aru	Z
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield 130	com/google/android/gms/tagmanager/cy:ars	Lcom/google/android/gms/tagmanager/ar;
    //   26: new 10	com/google/android/gms/tagmanager/cy$3
    //   29: dup
    //   30: aload_0
    //   31: invokespecial 161	com/google/android/gms/tagmanager/cy$3:<init>	(Lcom/google/android/gms/tagmanager/cy;)V
    //   34: invokeinterface 166 2 0
    //   39: goto -20 -> 19
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	this	cy
    //   42	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	19	42	finally
    //   22	39	42	finally
  }
  
  void ed()
  {
    try
    {
      if ((!this.arz) && (this.connected) && (this.art > 0))
      {
        this.handler.removeMessages(1, yc);
        this.handler.sendMessage(this.handler.obtainMessage(1, yc));
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  at px()
  {
    try
    {
      if (this.arr != null) {
        break label50;
      }
      if (this.arq == null) {
        throw new IllegalStateException("Cant get a store unless we have a context");
      }
    }
    finally {}
    this.arr = new cb(this.arx, this.arq);
    label50:
    if (this.handler == null) {
      eb();
    }
    this.arv = true;
    if (this.aru)
    {
      dispatch();
      this.aru = false;
    }
    if ((this.ary == null) && (this.arw)) {
      ea();
    }
    at localat = this.arr;
    return localat;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/cy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */