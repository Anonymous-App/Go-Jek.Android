package com.gojek.gobox.contactPersonDetailForm.view;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.List;

class ContactPicker$1
  implements AdapterView.OnItemClickListener
{
  ContactPicker$1(ContactPicker paramContactPicker) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = new Intent();
    paramAdapterView.putExtra("phone number", (String)ContactPicker.access$000(this.this$0).get(paramInt));
    this.this$0.setResult(-1, paramAdapterView);
    this.this$0.finish();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/contactPersonDetailForm/view/ContactPicker$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */