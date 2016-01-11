package com.gojek.gobox.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import com.gojek.gobox.R.string;

public class AlertDialogFragment
  extends DialogFragment
  implements DialogInterface.OnClickListener
{
  private static final String CONFIRM_TYPE_KEY = "confirm_type";
  private static final String DESC_KEY = "description";
  private static final String NEGATIVE_BUTTON = "negative_button";
  private static final String NEGATIVE_BUTTON_TITLE_KEY = "negative_button_key";
  private static final String POSITIVE_BUTTON_TITLE_KEY = "positive_button_key";
  private static final String TITLE_KEY = "title";
  public static final int WARNING_DIALOG = 18;
  private AlertDialogListener mListener;
  
  public static AlertDialogFragment newInstance(String paramString1, String paramString2, String paramString3)
  {
    return newInstance(paramString1, paramString2, paramString3, null, 18, false, null);
  }
  
  public static AlertDialogFragment newInstance(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    return newInstance(paramString1, paramString2, paramString3, null, paramInt, false, null);
  }
  
  public static AlertDialogFragment newInstance(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return newInstance(paramString1, paramString2, paramString3, paramString4, 18, true, null);
  }
  
  public static AlertDialogFragment newInstance(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    return newInstance(paramString1, paramString2, paramString3, paramString4, paramInt, true, null);
  }
  
  public static AlertDialogFragment newInstance(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, boolean paramBoolean, AlertDialogListener paramAlertDialogListener)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("title", paramString1);
    localBundle.putString("description", paramString2);
    localBundle.putString("positive_button_key", paramString3);
    localBundle.putString("negative_button_key", paramString4);
    localBundle.putBoolean("negative_button", paramBoolean);
    localBundle.putInt("confirm_type", paramInt);
    paramString1 = new AlertDialogFragment();
    paramString1.setAlertDialogListener(paramAlertDialogListener);
    paramString1.setArguments(localBundle);
    return paramString1;
  }
  
  private void setAlertDialogListener(AlertDialogListener paramAlertDialogListener)
  {
    this.mListener = paramAlertDialogListener;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    try
    {
      if (this.mListener == null) {
        this.mListener = ((AlertDialogListener)paramActivity);
      }
      return;
    }
    catch (ClassCastException paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.mListener != null)
    {
      if (paramInt != -1) {
        break label35;
      }
      this.mListener.positiveButtonClicked(getArguments().getInt("confirm_type"));
    }
    for (;;)
    {
      dismiss();
      return;
      label35:
      this.mListener.negativeButtonClicked(getArguments().getInt("confirm_type"));
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return setUpDialoglayout();
  }
  
  public AlertDialog setUpDialoglayout()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
    String str1 = getArguments().getString("title");
    String str2 = getArguments().getString("description");
    String str3 = getArguments().getString("positive_button_key");
    String str4 = getArguments().getString("negative_button_key");
    boolean bool = getArguments().getBoolean("negative_button");
    if (str1 == null)
    {
      localBuilder.setTitle(R.string.warning_title);
      if (str2 != null) {
        localBuilder.setMessage(str2);
      }
      if (str3 != null) {
        break label134;
      }
      localBuilder.setPositiveButton(R.string.button_OK, this);
      label103:
      if ((str4 == null) || (!bool)) {
        break label145;
      }
      localBuilder.setNegativeButton(str4, this);
    }
    for (;;)
    {
      return localBuilder.create();
      localBuilder.setTitle(str1);
      break;
      label134:
      localBuilder.setPositiveButton(str3, this);
      break label103;
      label145:
      if (bool) {
        localBuilder.setNegativeButton(R.string.button_cancel, this);
      }
    }
  }
  
  public static abstract interface AlertDialogListener
  {
    public abstract void negativeButtonClicked(int paramInt);
    
    public abstract void positiveButtonClicked(int paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/AlertDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */