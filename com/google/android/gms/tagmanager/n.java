package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;

class n
  implements ContainerHolder
{
  private Status CM;
  private final Looper IH;
  private boolean NU;
  private Container aok;
  private Container aol;
  private b aom;
  private a aon;
  private TagManager aoo;
  
  public n(Status paramStatus)
  {
    this.CM = paramStatus;
    this.IH = null;
  }
  
  public n(TagManager paramTagManager, Looper paramLooper, Container paramContainer, a parama)
  {
    this.aoo = paramTagManager;
    if (paramLooper != null) {}
    for (;;)
    {
      this.IH = paramLooper;
      this.aok = paramContainer;
      this.aon = parama;
      this.CM = Status.Jv;
      paramTagManager.a(this);
      return;
      paramLooper = Looper.getMainLooper();
    }
  }
  
  private void nV()
  {
    if (this.aom != null) {
      this.aom.cs(this.aol.nS());
    }
  }
  
  public void a(Container paramContainer)
  {
    for (;;)
    {
      try
      {
        boolean bool = this.NU;
        if (bool) {
          return;
        }
        if (paramContainer == null)
        {
          bh.T("Unexpected null container.");
          continue;
        }
        this.aol = paramContainer;
      }
      finally {}
      nV();
    }
  }
  
  /* Error */
  public void cp(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/google/android/gms/tagmanager/n:NU	Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: getfield 42	com/google/android/gms/tagmanager/n:aok	Lcom/google/android/gms/tagmanager/Container;
    //   18: aload_1
    //   19: invokevirtual 90	com/google/android/gms/tagmanager/Container:cp	(Ljava/lang/String;)V
    //   22: goto -11 -> 11
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	30	0	this	n
    //   0	30	1	paramString	String
    //   6	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	7	25	finally
    //   14	22	25	finally
  }
  
  void cr(String paramString)
  {
    if (this.NU)
    {
      bh.T("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
      return;
    }
    this.aon.cr(paramString);
  }
  
  /* Error */
  public Container getContainer()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 78	com/google/android/gms/tagmanager/n:NU	Z
    //   8: ifeq +12 -> 20
    //   11: ldc 99
    //   13: invokestatic 85	com/google/android/gms/tagmanager/bh:T	(Ljava/lang/String;)V
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: areturn
    //   20: aload_0
    //   21: getfield 65	com/google/android/gms/tagmanager/n:aol	Lcom/google/android/gms/tagmanager/Container;
    //   24: ifnull +16 -> 40
    //   27: aload_0
    //   28: aload_0
    //   29: getfield 65	com/google/android/gms/tagmanager/n:aol	Lcom/google/android/gms/tagmanager/Container;
    //   32: putfield 42	com/google/android/gms/tagmanager/n:aok	Lcom/google/android/gms/tagmanager/Container;
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield 65	com/google/android/gms/tagmanager/n:aol	Lcom/google/android/gms/tagmanager/Container;
    //   40: aload_0
    //   41: getfield 42	com/google/android/gms/tagmanager/n:aok	Lcom/google/android/gms/tagmanager/Container;
    //   44: astore_1
    //   45: goto -29 -> 16
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	n
    //   1	44	1	localContainer	Container
    //   48	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	16	48	finally
    //   20	40	48	finally
    //   40	45	48	finally
  }
  
  String getContainerId()
  {
    if (this.NU)
    {
      bh.T("getContainerId called on a released ContainerHolder.");
      return "";
    }
    return this.aok.getContainerId();
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  String nU()
  {
    if (this.NU)
    {
      bh.T("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
      return "";
    }
    return this.aon.nU();
  }
  
  /* Error */
  public void refresh()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/google/android/gms/tagmanager/n:NU	Z
    //   6: ifeq +11 -> 17
    //   9: ldc 114
    //   11: invokestatic 85	com/google/android/gms/tagmanager/bh:T	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: getfield 44	com/google/android/gms/tagmanager/n:aon	Lcom/google/android/gms/tagmanager/n$a;
    //   21: invokeinterface 117 1 0
    //   26: goto -12 -> 14
    //   29: astore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_1
    //   33: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	34	0	this	n
    //   29	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	14	29	finally
    //   17	26	29	finally
  }
  
  /* Error */
  public void release()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/google/android/gms/tagmanager/n:NU	Z
    //   6: ifeq +11 -> 17
    //   9: ldc 120
    //   11: invokestatic 85	com/google/android/gms/tagmanager/bh:T	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 78	com/google/android/gms/tagmanager/n:NU	Z
    //   22: aload_0
    //   23: getfield 40	com/google/android/gms/tagmanager/n:aoo	Lcom/google/android/gms/tagmanager/TagManager;
    //   26: aload_0
    //   27: invokevirtual 123	com/google/android/gms/tagmanager/TagManager:b	(Lcom/google/android/gms/tagmanager/n;)Z
    //   30: pop
    //   31: aload_0
    //   32: getfield 42	com/google/android/gms/tagmanager/n:aok	Lcom/google/android/gms/tagmanager/Container;
    //   35: invokevirtual 125	com/google/android/gms/tagmanager/Container:release	()V
    //   38: aload_0
    //   39: aconst_null
    //   40: putfield 42	com/google/android/gms/tagmanager/n:aok	Lcom/google/android/gms/tagmanager/Container;
    //   43: aload_0
    //   44: aconst_null
    //   45: putfield 65	com/google/android/gms/tagmanager/n:aol	Lcom/google/android/gms/tagmanager/Container;
    //   48: aload_0
    //   49: aconst_null
    //   50: putfield 44	com/google/android/gms/tagmanager/n:aon	Lcom/google/android/gms/tagmanager/n$a;
    //   53: aload_0
    //   54: aconst_null
    //   55: putfield 63	com/google/android/gms/tagmanager/n:aom	Lcom/google/android/gms/tagmanager/n$b;
    //   58: goto -44 -> 14
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	n
    //   61	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	14	61	finally
    //   17	58	61	finally
  }
  
  public void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener paramContainerAvailableListener)
  {
    for (;;)
    {
      try
      {
        if (this.NU)
        {
          bh.T("ContainerHolder is released.");
          return;
        }
        if (paramContainerAvailableListener == null)
        {
          this.aom = null;
          continue;
        }
        this.aom = new b(paramContainerAvailableListener, this.IH);
      }
      finally {}
      if (this.aol != null) {
        nV();
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void cr(String paramString);
    
    public abstract String nU();
    
    public abstract void nW();
  }
  
  private class b
    extends Handler
  {
    private final ContainerHolder.ContainerAvailableListener aop;
    
    public b(ContainerHolder.ContainerAvailableListener paramContainerAvailableListener, Looper paramLooper)
    {
      super();
      this.aop = paramContainerAvailableListener;
    }
    
    public void cs(String paramString)
    {
      sendMessage(obtainMessage(1, paramString));
    }
    
    protected void ct(String paramString)
    {
      this.aop.onContainerAvailable(n.this, paramString);
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        bh.T("Don't know how to handle this message.");
        return;
      }
      ct((String)paramMessage.obj);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */