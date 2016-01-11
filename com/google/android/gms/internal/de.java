package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R.string;
import java.util.Map;
import org.json.JSONObject;

@ez
public class de
{
  private final Context mContext;
  private final gv md;
  private final Map<String, String> qM;
  
  public de(gv paramgv, Map<String, String> paramMap)
  {
    this.md = paramgv;
    this.qM = paramMap;
    this.mContext = paramgv.dz();
  }
  
  String B(String paramString)
  {
    return Uri.parse(paramString).getLastPathSegment();
  }
  
  DownloadManager.Request b(String paramString1, String paramString2)
  {
    paramString1 = new DownloadManager.Request(Uri.parse(paramString1));
    paramString1.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, paramString2);
    paramString1.allowScanningByMediaScanner();
    paramString1.setNotificationVisibility(1);
    return paramString1;
  }
  
  public void execute()
  {
    if (!new bl(this.mContext).bl())
    {
      gs.W("Store picture feature is not supported on this device.");
      return;
    }
    if (TextUtils.isEmpty((CharSequence)this.qM.get("iurl")))
    {
      gs.W("Image url cannot be empty.");
      return;
    }
    final String str1 = (String)this.qM.get("iurl");
    if (!URLUtil.isValidUrl(str1))
    {
      gs.W("Invalid image url:" + str1);
      return;
    }
    final String str2 = B(str1);
    if (!gj.N(str2))
    {
      gs.W("Image type not recognized:");
      return;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mContext);
    localBuilder.setTitle(gb.c(R.string.store_picture_title, "Save image"));
    localBuilder.setMessage(gb.c(R.string.store_picture_message, "Allow Ad to store image in Picture gallery?"));
    localBuilder.setPositiveButton(gb.c(R.string.accept, "Accept"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = (DownloadManager)de.a(de.this).getSystemService("download");
        try
        {
          paramAnonymousDialogInterface.enqueue(de.this.b(str1, str2));
          return;
        }
        catch (IllegalStateException paramAnonymousDialogInterface)
        {
          gs.U("Could not store picture.");
        }
      }
    });
    localBuilder.setNegativeButton(gb.c(R.string.decline, "Decline"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        de.b(de.this).b("onStorePictureCanceled", new JSONObject());
      }
    });
    localBuilder.create().show();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/de.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */