package lib.gojek.base.helper;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import java.util.ArrayList;
import java.util.List;
import lib.gojek.base.activities.ContactPickerActivity;

public class PickContactHelper
{
  public static final String CONTACT_ID_EXTRAS_KEY = "contact id";
  public static final String CONTACT_NAME_EXTRAS_KEY = "contact name";
  public static final String PHONE_NUMBER_EXTRAS_KEY = "phone number";
  public static final int PICK_MULTIPLE_REQUEST_CONTEXT = 1;
  public static final int PICK_REQUEST_CONTACT = 21;
  private Activity activity;
  private String contactId;
  private String contactName;
  private String contactNumber;
  private Uri contactUri;
  
  public PickContactHelper(Activity paramActivity)
  {
    this.activity = paramActivity;
  }
  
  private void bindContactFromBundle(Uri paramUri)
  {
    paramUri = this.activity.getContentResolver().query(paramUri, null, null, null, null);
    if (paramUri.moveToFirst())
    {
      this.contactId = paramUri.getString(paramUri.getColumnIndex("_id"));
      this.contactName = paramUri.getString(paramUri.getColumnIndex("display_name"));
      getPhoneNumber(this.contactId, this.contactName);
    }
  }
  
  private void getPhoneNumber(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.activity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { paramString1 }, null);
    while (localCursor.moveToNext()) {
      localArrayList.add(localCursor.getString(localCursor.getColumnIndex("data1")));
    }
    localCursor.close();
    if (localArrayList.size() == 0)
    {
      this.contactNumber = "";
      return;
    }
    if (localArrayList.size() == 1)
    {
      this.contactNumber = ((String)localArrayList.get(0));
      return;
    }
    pickMultipleContact(paramString1, paramString2);
  }
  
  private void pickMultipleContact(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(this.activity, ContactPickerActivity.class);
    localIntent.putExtra("contact id", paramString1);
    localIntent.putExtra("contact name", paramString2);
    this.activity.startActivityForResult(localIntent, 1);
  }
  
  public Activity getActivity()
  {
    return this.activity;
  }
  
  public String getContactId()
  {
    return this.contactId;
  }
  
  public String getContactName()
  {
    return this.contactName;
  }
  
  public String getContactNumber()
  {
    return this.contactNumber;
  }
  
  public Uri getContactUri()
  {
    return this.contactUri;
  }
  
  public boolean onActivityForResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (21 == paramInt1)
    {
      if ((-1 == paramInt2) && (paramIntent != null))
      {
        this.contactUri = paramIntent.getData();
        bindContactFromBundle(this.contactUri);
        return true;
      }
    }
    else if ((1 == paramInt1) && (-1 == paramInt2) && (paramIntent != null))
    {
      this.contactNumber = paramIntent.getStringExtra("phone number");
      return true;
    }
    return false;
  }
  
  public void openContactPicker()
  {
    Intent localIntent = new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI);
    this.activity.startActivityForResult(localIntent, 21);
  }
  
  public void setActivity(Activity paramActivity)
  {
    this.activity = paramActivity;
  }
  
  public void setContactId(String paramString)
  {
    this.contactId = paramString;
  }
  
  public void setContactName(String paramString)
  {
    this.contactName = paramString;
  }
  
  public void setContactNumber(String paramString)
  {
    this.contactNumber = paramString;
  }
  
  public void setContactUri(Uri paramUri)
  {
    this.contactUri = paramUri;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/helper/PickContactHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */