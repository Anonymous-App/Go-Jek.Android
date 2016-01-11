package com.google.android.gms.internal;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@ez
public final class do
  extends FrameLayout
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener
{
  private final gv md;
  private final MediaController rX;
  private final a rY;
  private final VideoView rZ;
  private long sa;
  private String sb;
  
  public do(Context paramContext, gv paramgv)
  {
    super(paramContext);
    this.md = paramgv;
    this.rZ = new VideoView(paramContext);
    paramgv = new FrameLayout.LayoutParams(-1, -1, 17);
    addView(this.rZ, paramgv);
    this.rX = new MediaController(paramContext);
    this.rY = new a(this);
    this.rY.cj();
    this.rZ.setOnCompletionListener(this);
    this.rZ.setOnPreparedListener(this);
    this.rZ.setOnErrorListener(this);
  }
  
  private static void a(gv paramgv, String paramString)
  {
    a(paramgv, paramString, new HashMap(1));
  }
  
  public static void a(gv paramgv, String paramString1, String paramString2)
  {
    int i;
    if (paramString2 == null)
    {
      i = 1;
      if (i == 0) {
        break label60;
      }
    }
    label60:
    for (int j = 2;; j = 3)
    {
      HashMap localHashMap = new HashMap(j);
      localHashMap.put("what", paramString1);
      if (i == 0) {
        localHashMap.put("extra", paramString2);
      }
      a(paramgv, "error", localHashMap);
      return;
      i = 0;
      break;
    }
  }
  
  private static void a(gv paramgv, String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap(2);
    localHashMap.put(paramString2, paramString3);
    a(paramgv, paramString1, localHashMap);
  }
  
  private static void a(gv paramgv, String paramString, Map<String, String> paramMap)
  {
    paramMap.put("event", paramString);
    paramgv.a("onVideoEvent", paramMap);
  }
  
  public void C(String paramString)
  {
    this.sb = paramString;
  }
  
  public void b(MotionEvent paramMotionEvent)
  {
    this.rZ.dispatchTouchEvent(paramMotionEvent);
  }
  
  public void ch()
  {
    if (!TextUtils.isEmpty(this.sb))
    {
      this.rZ.setVideoPath(this.sb);
      return;
    }
    a(this.md, "no_src", null);
  }
  
  public void ci()
  {
    long l = this.rZ.getCurrentPosition();
    if (this.sa != l)
    {
      float f = (float)l / 1000.0F;
      a(this.md, "timeupdate", "time", String.valueOf(f));
      this.sa = l;
    }
  }
  
  public void destroy()
  {
    this.rY.cancel();
    this.rZ.stopPlayback();
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    a(this.md, "ended");
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    a(this.md, String.valueOf(paramInt1), String.valueOf(paramInt2));
    return true;
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    float f = this.rZ.getDuration() / 1000.0F;
    a(this.md, "canplaythrough", "duration", String.valueOf(f));
  }
  
  public void pause()
  {
    this.rZ.pause();
  }
  
  public void play()
  {
    this.rZ.start();
  }
  
  public void q(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.rZ.setMediaController(this.rX);
      return;
    }
    this.rX.hide();
    this.rZ.setMediaController(null);
  }
  
  public void seekTo(int paramInt)
  {
    this.rZ.seekTo(paramInt);
  }
  
  private static final class a
  {
    private final Runnable mk;
    private volatile boolean sc = false;
    
    public a(final do paramdo)
    {
      this.mk = new Runnable()
      {
        private final WeakReference<do> sd = new WeakReference(paramdo);
        
        public void run()
        {
          do localdo = (do)this.sd.get();
          if ((!do.a.a(do.a.this)) && (localdo != null))
          {
            localdo.ci();
            do.a.this.cj();
          }
        }
      };
    }
    
    public void cancel()
    {
      this.sc = true;
      gr.wC.removeCallbacks(this.mk);
    }
    
    public void cj()
    {
      gr.wC.postDelayed(this.mk, 250L);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/do.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */