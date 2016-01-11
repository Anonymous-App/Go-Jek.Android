package com.gojek.gobox.contactPersonDetailForm.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class ContactPerson
  extends BaseActivity
{
  private final int REQUEST_CONTACT = 21;
  private final int REQUEST_PICK_CONTACT = 22;
  private String contactName;
  private String contactPhone;
  private RelativeLayout mRelativeAddPerson;
  private RelativeLayout mRelativeButtonAdd;
  private EditText mTextContactName;
  private EditText mTextContactPhone;
  
  private void pickMultipleContact(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(this, ContactPicker.class);
    localIntent.putExtra("contact id", paramString1);
    localIntent.putExtra("contact name", paramString2);
    startActivityForResult(localIntent, 22);
  }
  
  private void populateContact(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { paramString1 }, null);
    while (localCursor.moveToNext()) {
      localArrayList.add(localCursor.getString(localCursor.getColumnIndex("data1")));
    }
    localCursor.close();
    if (localArrayList.size() == 0)
    {
      this.mTextContactPhone.setText("");
      this.mTextContactPhone.requestFocus();
      return;
    }
    if (localArrayList.size() == 1)
    {
      this.mTextContactPhone.setText((CharSequence)localArrayList.get(0));
      return;
    }
    pickMultipleContact(paramString1, paramString2);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 1)
    {
      if (paramInt2 == -1)
      {
        this.contactName = paramIntent.getStringExtra("contact name");
        this.contactPhone = paramIntent.getStringExtra("phone number");
        this.mTextContactName.setText(this.contactName);
        this.mTextContactPhone.setText(this.contactPhone);
      }
      if (paramInt2 != 0) {}
    }
    do
    {
      do
      {
        do
        {
          return;
          if (paramInt1 != 21) {
            break;
          }
        } while (paramInt2 != -1);
        paramIntent = paramIntent.getData();
        localObject = getContentResolver().query(paramIntent, null, null, null, null);
      } while (!((Cursor)localObject).moveToFirst());
      paramIntent = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("_id"));
      Object localObject = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("display_name"));
      this.mTextContactName.setText((CharSequence)localObject);
      populateContact(paramIntent, (String)localObject);
      return;
    } while ((paramInt1 != 22) || (paramInt2 != -1));
    paramIntent = paramIntent.getExtras().getString("phone number");
    this.mTextContactPhone.setText(paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_contact_person);
    initToolbarView();
    setTitle(getString(R.string.input_contact_title));
    this.mTextContactName = ((EditText)findViewById(R.id.text_name));
    this.mTextContactPhone = ((EditText)findViewById(R.id.text_phone_number));
    this.mRelativeButtonAdd = ((RelativeLayout)findViewById(R.id.relative_add_via_phonebook));
    this.mRelativeAddPerson = ((RelativeLayout)findViewById(R.id.relative_add_person));
    this.mRelativeButtonAdd.setOnClickListener(new ContactPerson.1(this));
    this.mRelativeAddPerson.setOnClickListener(new ContactPerson.2(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/contactPersonDetailForm/view/ContactPerson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */