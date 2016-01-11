package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.R.string;
import java.util.Map;
import org.json.JSONObject;

@ez
public class dc
{
  private final Context mContext;
  private final gv md;
  private final Map<String, String> qM;
  private String qN;
  private long qO;
  private long qP;
  private String qQ;
  private String qR;
  
  public dc(gv paramgv, Map<String, String> paramMap)
  {
    this.md = paramgv;
    this.qM = paramMap;
    this.mContext = paramgv.dz();
    bG();
  }
  
  private String A(String paramString)
  {
    if (TextUtils.isEmpty((CharSequence)this.qM.get(paramString))) {
      return "";
    }
    return (String)this.qM.get(paramString);
  }
  
  private void bG()
  {
    this.qN = A("description");
    this.qQ = A("summary");
    this.qO = gj.O((String)this.qM.get("start"));
    this.qP = gj.O((String)this.qM.get("end"));
    this.qR = A("location");
  }
  
  Intent createIntent()
  {
    Intent localIntent = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
    localIntent.putExtra("title", this.qQ);
    localIntent.putExtra("eventLocation", this.qR);
    localIntent.putExtra("description", this.qN);
    localIntent.putExtra("beginTime", this.qO);
    localIntent.putExtra("endTime", this.qP);
    localIntent.setFlags(268435456);
    return localIntent;
  }
  
  public void execute()
  {
    if (!new bl(this.mContext).bo())
    {
      gs.W("This feature is not available on this version of the device.");
      return;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mContext);
    localBuilder.setTitle(gb.c(R.string.create_calendar_title, "Create calendar event"));
    localBuilder.setMessage(gb.c(R.string.create_calendar_message, "Allow Ad to create a calendar event?"));
    localBuilder.setPositiveButton(gb.c(R.string.accept, "Accept"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = dc.this.createIntent();
        dc.a(dc.this).startActivity(paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton(gb.c(R.string.decline, "Decline"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        dc.b(dc.this).b("onCalendarEventCanceled", new JSONObject());
      }
    });
    localBuilder.create().show();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */