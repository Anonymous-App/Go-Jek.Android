package lib.gojek.base.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import lib.gojek.base.R.string;

@Instrumented
public class BaseDialogFragment
  extends DialogFragment
  implements DialogInterface.OnClickListener, TraceFieldInterface
{
  private static final String DESC_KEY = "description";
  public static final int FLAG_ALERT_DIALOG = 0;
  public static final int FLAG_CONFIRMATION_DIALOG = 1;
  private static final String NEGATIVE_BUTTON_SHOW = "negative_button_show";
  private static final String NEGATIVE_BUTTON_TITLE_KEY = "negative_button_key";
  private static final String POSITIVE_BUTTON_TITLE_KEY = "positive_button_key";
  private static final String TITLE_KEY = "title";
  private static BaseDialogFragment dialog;
  private AlertDialogListener listener;
  
  public static BaseDialogFragment alertDialogInstance(String paramString1, String paramString2, String paramString3)
  {
    return newInstance(paramString1, paramString2, paramString3, null, false);
  }
  
  public static BaseDialogFragment confirmationDialogInstance(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return newInstance(paramString1, paramString2, paramString3, paramString4, true);
  }
  
  public static BaseDialogFragment newInstance(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("title", paramString1);
    localBundle.putString("description", paramString2);
    localBundle.putString("positive_button_key", paramString3);
    localBundle.putString("negative_button_key", paramString4);
    localBundle.putBoolean("negative_button_show", paramBoolean);
    if (dialog != null)
    {
      dialog.dismiss();
      dialog = null;
    }
    dialog = new BaseDialogFragment();
    dialog.setArguments(localBundle);
    dialog.setCancelable(false);
    return dialog;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.listener != null)
    {
      if (paramInt != -1) {
        break label26;
      }
      this.listener.positiveButtonClicked();
    }
    for (;;)
    {
      dismiss();
      return;
      label26:
      this.listener.negativeButtonClicked();
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return setupDialogLayout();
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
  
  public void setAlertDialogListener(AlertDialogListener paramAlertDialogListener)
  {
    this.listener = paramAlertDialogListener;
  }
  
  public AlertDialog setupDialogLayout()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
    localBuilder.setCancelable(false);
    String str1 = getArguments().getString("title");
    String str2 = getArguments().getString("description");
    String str3 = getArguments().getString("positive_button_key");
    String str4 = getArguments().getString("negative_button_key");
    boolean bool = getArguments().getBoolean("negative_button_show");
    if (str1 == null)
    {
      localBuilder.setTitle(R.string.warning_title);
      if (str2 != null) {
        localBuilder.setMessage(str2);
      }
      if (str3 != null) {
        break label140;
      }
      localBuilder.setPositiveButton(R.string.ok_button, this);
      label109:
      if ((str4 == null) || (!bool)) {
        break label151;
      }
      localBuilder.setNegativeButton(str4, this);
    }
    for (;;)
    {
      return localBuilder.create();
      localBuilder.setTitle(str1);
      break;
      label140:
      localBuilder.setPositiveButton(str3, this);
      break label109;
      label151:
      if (bool) {
        localBuilder.setNegativeButton(R.string.cancel_button, this);
      }
    }
  }
  
  public static abstract interface AlertDialogListener
  {
    public abstract void negativeButtonClicked();
    
    public abstract void positiveButtonClicked();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/util/BaseDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */