package com.gojek.gobox.contactPersonDetailForm.view;

import android.content.Intent;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.View.OnClickListener;

class ContactPerson$1
  implements View.OnClickListener
{
  ContactPerson$1(ContactPerson paramContactPerson) {}
  
  public void onClick(View paramView)
  {
    paramView = new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI);
    this.this$0.startActivityForResult(paramView, 21);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/contactPersonDetailForm/view/ContactPerson$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */