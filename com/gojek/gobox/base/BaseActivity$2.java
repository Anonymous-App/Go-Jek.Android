package com.gojek.gobox.base;

import android.content.Intent;
import com.gojek.gobox.util.AlertDialogFragment.AlertDialogListener;

class BaseActivity$2
  implements AlertDialogFragment.AlertDialogListener
{
  BaseActivity$2(BaseActivity paramBaseActivity) {}
  
  public void negativeButtonClicked(int paramInt) {}
  
  public void positiveButtonClicked(int paramInt)
  {
    this.this$0.startActivity(new Intent("com.gojek.app.SIGN_UP"));
    this.this$0.finish();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/base/BaseActivity$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */