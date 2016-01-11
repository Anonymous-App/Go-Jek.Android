package com.gojek.gobox.bookingstatus.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.gojek.gobox.bookingstatus.presenter.BookingStatusPresenter;

class BookingStatusActivity$3
  implements DialogInterface.OnClickListener
{
  BookingStatusActivity$3(BookingStatusActivity paramBookingStatusActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    BookingStatusActivity.access$400(this.this$0).onDoCanceling();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/view/BookingStatusActivity$3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */