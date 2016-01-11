package com.gojek.gotix.fragments;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.gojek.gotix.R.string;
import com.gojek.gotix.presenter.GotixBasePresenter;
import lib.gojek.base.AbstractBaseFragment;
import lib.gojek.base.util.BaseDialogFragment;
import lib.gojek.base.util.BaseDialogFragment.AlertDialogListener;

public abstract class GotixBaseFragment<T extends GotixBasePresenter>
  extends AbstractBaseFragment<T>
{
  private String title;
  
  private BaseDialogFragment getDialog(String paramString1, String paramString2)
  {
    return BaseDialogFragment.alertDialogInstance(paramString1, paramString2, getResources().getString(R.string.dialog_ok_button));
  }
  
  private BaseDialogFragment getDialog(String paramString1, String paramString2, String paramString3)
  {
    return BaseDialogFragment.alertDialogInstance(paramString1, paramString2, paramString3);
  }
  
  protected LayoutInflater getInflater()
  {
    return getActivity().getLayoutInflater();
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  protected void hideKeyboard()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getActivity().getSystemService("input_method");
    if (localInputMethodManager.isAcceptingText()) {
      localInputMethodManager.toggleSoftInput(1, 0);
    }
  }
  
  protected void hideKeyboard(View paramView)
  {
    setKeyboardVisibility(paramView, false);
  }
  
  protected void setKeyboardVisibility(View paramView, boolean paramBoolean)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getActivity().getSystemService("input_method");
    if (paramBoolean)
    {
      localInputMethodManager.showSoftInput(paramView, 1);
      return;
    }
    localInputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 2);
  }
  
  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
  
  protected void showDialog(String paramString1, String paramString2)
  {
    getDialog(paramString1, paramString2).show(getFragmentManager(), getResources().getString(R.string.dialog));
  }
  
  protected void showDialog(String paramString1, String paramString2, String paramString3)
  {
    getDialog(paramString1, paramString2, paramString3).show(getFragmentManager(), getResources().getString(R.string.dialog));
  }
  
  protected void showDialog(String paramString1, String paramString2, String paramString3, BaseDialogFragment.AlertDialogListener paramAlertDialogListener)
  {
    paramString1 = getDialog(paramString1, paramString2, paramString3);
    paramString1.setAlertDialogListener(paramAlertDialogListener);
    paramString1.show(getFragmentManager(), getResources().getString(R.string.dialog));
  }
  
  protected void showDialog(String paramString1, String paramString2, BaseDialogFragment.AlertDialogListener paramAlertDialogListener)
  {
    paramString1 = getDialog(paramString1, paramString2);
    paramString1.setAlertDialogListener(paramAlertDialogListener);
    paramString1.show(getFragmentManager(), getResources().getString(R.string.dialog));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/fragments/GotixBaseFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */