package com.google.android.gms.drive.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.DriveEvent.Listener;
import com.google.android.gms.drive.events.c;
import java.util.ArrayList;
import java.util.List;

public class y
  extends ad.a
{
  private final int Oa;
  private final c Pe;
  private final a Pf;
  private final List<Integer> Pg = new ArrayList();
  
  public y(Looper paramLooper, Context paramContext, int paramInt, c paramc)
  {
    this.Oa = paramInt;
    this.Pe = paramc;
    this.Pf = new a(paramLooper, paramContext, null);
  }
  
  public void bq(int paramInt)
  {
    this.Pg.add(Integer.valueOf(paramInt));
  }
  
  public boolean br(int paramInt)
  {
    return this.Pg.contains(Integer.valueOf(paramInt));
  }
  
  public void c(OnEventResponse paramOnEventResponse)
    throws RemoteException
  {
    paramOnEventResponse = paramOnEventResponse.ih();
    if (this.Oa == paramOnEventResponse.getType()) {}
    for (boolean bool = true;; bool = false)
    {
      o.I(bool);
      o.I(this.Pg.contains(Integer.valueOf(paramOnEventResponse.getType())));
      this.Pf.a(this.Pe, paramOnEventResponse);
      return;
    }
  }
  
  private static class a
    extends Handler
  {
    private final Context mContext;
    
    private a(Looper paramLooper, Context paramContext)
    {
      super();
      this.mContext = paramContext;
    }
    
    public void a(c paramc, DriveEvent paramDriveEvent)
    {
      sendMessage(obtainMessage(1, new Pair(paramc, paramDriveEvent)));
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        v.e(this.mContext, "EventCallback", "Don't know how to handle this event");
        return;
      }
      Object localObject = (Pair)paramMessage.obj;
      paramMessage = (c)((Pair)localObject).first;
      localObject = (DriveEvent)((Pair)localObject).second;
      switch (((DriveEvent)localObject).getType())
      {
      default: 
        v.p("EventCallback", "Unexpected event: " + localObject);
        return;
      case 1: 
        if ((paramMessage instanceof DriveEvent.Listener))
        {
          ((DriveEvent.Listener)paramMessage).onEvent((ChangeEvent)localObject);
          return;
        }
        ((ChangeListener)paramMessage).onChange((ChangeEvent)localObject);
        return;
      }
      ((CompletionListener)paramMessage).onCompletion((CompletionEvent)localObject);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */