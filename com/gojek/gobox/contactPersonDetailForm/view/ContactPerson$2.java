package com.gojek.gobox.contactPersonDetailForm.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

class ContactPerson$2
  implements View.OnClickListener
{
  ContactPerson$2(ContactPerson paramContactPerson) {}
  
  public void onClick(View paramView)
  {
    if ((!TextUtils.isEmpty(ContactPerson.access$000(this.this$0).getText())) && (!TextUtils.isEmpty(ContactPerson.access$100(this.this$0).getText())))
    {
      paramView = new Intent();
      paramView.putExtra("phone number", ContactPerson.access$000(this.this$0).getText().toString());
      paramView.putExtra("contact name", ContactPerson.access$100(this.this$0).getText().toString());
      this.this$0.setResult(-1, paramView);
      this.this$0.finish();
      return;
    }
    Toast.makeText(this.this$0, "harap kontak diisi terlebih dahulu", 1).show();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/contactPersonDetailForm/view/ContactPerson$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */