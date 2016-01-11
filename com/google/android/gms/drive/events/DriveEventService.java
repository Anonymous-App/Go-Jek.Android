package com.google.android.gms.drive.events;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.internal.OnEventResponse;
import com.google.android.gms.drive.internal.ad.a;
import com.google.android.gms.drive.internal.v;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class DriveEventService
  extends Service
  implements ChangeListener, CompletionListener
{
  public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
  private CountDownLatch NV;
  a NW;
  int NX = -1;
  private final String mName;
  
  protected DriveEventService()
  {
    this("DriveEventService");
  }
  
  protected DriveEventService(String paramString)
  {
    this.mName = paramString;
  }
  
  private void a(OnEventResponse paramOnEventResponse)
  {
    paramOnEventResponse = paramOnEventResponse.ih();
    v.n("DriveEventService", "handleEventMessage: " + paramOnEventResponse);
    for (;;)
    {
      try
      {
        switch (paramOnEventResponse.getType())
        {
        case 1: 
          v.p(this.mName, "Unhandled event: " + paramOnEventResponse);
          return;
        }
      }
      catch (Exception localException)
      {
        v.a(this.mName, localException, "Error handling event: " + paramOnEventResponse);
        return;
      }
      onChange((ChangeEvent)paramOnEventResponse);
      return;
      onCompletion((CompletionEvent)paramOnEventResponse);
      return;
    }
  }
  
  private boolean bc(int paramInt)
  {
    boolean bool2 = false;
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    boolean bool1 = bool2;
    int i;
    if (arrayOfString != null)
    {
      i = arrayOfString.length;
      paramInt = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (paramInt < i)
      {
        if ("com.google.android.gms".equals(arrayOfString[paramInt])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      paramInt += 1;
    }
  }
  
  private void hV()
    throws SecurityException
  {
    int i = getCallingUid();
    if (i == this.NX) {
      return;
    }
    if ((GooglePlayServicesUtil.b(getPackageManager(), "com.google.android.gms")) && (bc(i)))
    {
      this.NX = i;
      return;
    }
    throw new SecurityException("Caller is not GooglePlayServices");
  }
  
  protected int getCallingUid()
  {
    return Binder.getCallingUid();
  }
  
  public final IBinder onBind(final Intent paramIntent)
  {
    for (;;)
    {
      try
      {
        if ("com.google.android.gms.drive.events.HANDLE_EVENT".equals(paramIntent.getAction()))
        {
          if (this.NW == null)
          {
            paramIntent = new CountDownLatch(1);
            this.NV = new CountDownLatch(1);
            new Thread()
            {
              public void run()
              {
                try
                {
                  Looper.prepare();
                  DriveEventService.this.NW = new DriveEventService.a(DriveEventService.this);
                  paramIntent.countDown();
                  v.n("DriveEventService", "Bound and starting loop");
                  Looper.loop();
                  v.n("DriveEventService", "Finished loop");
                  return;
                }
                finally
                {
                  DriveEventService.b(DriveEventService.this).countDown();
                }
              }
            }.start();
          }
          try
          {
            paramIntent.await(5000L, TimeUnit.MILLISECONDS);
            paramIntent = new b().asBinder();
            return paramIntent;
          }
          catch (InterruptedException paramIntent)
          {
            throw new RuntimeException("Unable to start event handler", paramIntent);
          }
        }
        paramIntent = null;
      }
      finally {}
    }
  }
  
  public void onChange(ChangeEvent paramChangeEvent)
  {
    v.p(this.mName, "Unhandled change event: " + paramChangeEvent);
  }
  
  public void onCompletion(CompletionEvent paramCompletionEvent)
  {
    v.p(this.mName, "Unhandled completion event: " + paramCompletionEvent);
  }
  
  /* Error */
  public void onDestroy()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 31
    //   4: ldc -54
    //   6: invokestatic 82	com/google/android/gms/drive/internal/v:n	(Ljava/lang/String;Ljava/lang/String;)V
    //   9: aload_0
    //   10: getfield 160	com/google/android/gms/drive/events/DriveEventService:NW	Lcom/google/android/gms/drive/events/DriveEventService$a;
    //   13: ifnull +44 -> 57
    //   16: aload_0
    //   17: getfield 160	com/google/android/gms/drive/events/DriveEventService:NW	Lcom/google/android/gms/drive/events/DriveEventService$a;
    //   20: invokestatic 205	com/google/android/gms/drive/events/DriveEventService$a:a	(Lcom/google/android/gms/drive/events/DriveEventService$a;)Landroid/os/Message;
    //   23: astore_1
    //   24: aload_0
    //   25: getfield 160	com/google/android/gms/drive/events/DriveEventService:NW	Lcom/google/android/gms/drive/events/DriveEventService$a;
    //   28: aload_1
    //   29: invokevirtual 209	com/google/android/gms/drive/events/DriveEventService$a:sendMessage	(Landroid/os/Message;)Z
    //   32: pop
    //   33: aload_0
    //   34: aconst_null
    //   35: putfield 160	com/google/android/gms/drive/events/DriveEventService:NW	Lcom/google/android/gms/drive/events/DriveEventService$a;
    //   38: aload_0
    //   39: getfield 113	com/google/android/gms/drive/events/DriveEventService:NV	Ljava/util/concurrent/CountDownLatch;
    //   42: ldc2_w 172
    //   45: getstatic 179	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   48: invokevirtual 183	java/util/concurrent/CountDownLatch:await	(JLjava/util/concurrent/TimeUnit;)Z
    //   51: pop
    //   52: aload_0
    //   53: aconst_null
    //   54: putfield 113	com/google/android/gms/drive/events/DriveEventService:NV	Ljava/util/concurrent/CountDownLatch;
    //   57: aload_0
    //   58: invokespecial 211	android/app/Service:onDestroy	()V
    //   61: aload_0
    //   62: monitorexit
    //   63: return
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: athrow
    //   69: astore_1
    //   70: goto -18 -> 52
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	DriveEventService
    //   23	6	1	localMessage	Message
    //   64	4	1	localObject	Object
    //   69	1	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	38	64	finally
    //   38	52	64	finally
    //   52	57	64	finally
    //   57	61	64	finally
    //   38	52	69	java/lang/InterruptedException
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    return true;
  }
  
  final class a
    extends Handler
  {
    a() {}
    
    private Message b(OnEventResponse paramOnEventResponse)
    {
      return obtainMessage(1, paramOnEventResponse);
    }
    
    private Message hW()
    {
      return obtainMessage(2);
    }
    
    public void handleMessage(Message paramMessage)
    {
      v.n("DriveEventService", "handleMessage message type:" + paramMessage.what);
      switch (paramMessage.what)
      {
      default: 
        v.p("DriveEventService", "Unexpected message type:" + paramMessage.what);
        return;
      case 1: 
        DriveEventService.a(DriveEventService.this, (OnEventResponse)paramMessage.obj);
        return;
      }
      getLooper().quit();
    }
  }
  
  final class b
    extends ad.a
  {
    b() {}
    
    public void c(OnEventResponse paramOnEventResponse)
      throws RemoteException
    {
      synchronized (DriveEventService.this)
      {
        v.n("DriveEventService", "onEvent: " + paramOnEventResponse);
        o.i(DriveEventService.this.NW);
        DriveEventService.a(DriveEventService.this);
        paramOnEventResponse = DriveEventService.a.a(DriveEventService.this.NW, paramOnEventResponse);
        DriveEventService.this.NW.sendMessage(paramOnEventResponse);
        return;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/events/DriveEventService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */