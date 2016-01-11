package lib.gojek.base.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import lib.gojek.base.R.id;
import lib.gojek.base.R.layout;
import lib.gojek.base.R.string;

public class ContactPickerActivity
  extends BaseActivity
{
  private ContactAdapter adapter;
  private String contactId;
  private Context context;
  private ListView listContact;
  private ArrayList<String> phoneNumbers;
  
  private void bindData()
  {
    this.contactId = getIntent().getExtras().getString("contact id");
    this.phoneNumbers = populateNumbers(this.contactId);
  }
  
  private void bindViewById()
  {
    this.listContact = ((ListView)findViewById(R.id.phonenumber_list));
  }
  
  private ArrayList<String> populateNumbers(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { paramString }, null);
    while (paramString.moveToNext()) {
      localArrayList.add(paramString.getString(paramString.getColumnIndex("data1")));
    }
    paramString.close();
    return localArrayList;
  }
  
  private void prepareAdapter()
  {
    this.adapter = new ContactAdapter(this.context, this.phoneNumbers);
    this.listContact.setAdapter(this.adapter);
    this.listContact.setOnItemClickListener(ContactPickerActivity..Lambda.1.lambdaFactory$(this));
  }
  
  protected int getLayout()
  {
    return R.layout.base_pick_contact;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = this;
    setTitleActionBar(getString(R.string.title_pick_contact));
    bindViewById();
    bindData();
    prepareAdapter();
  }
  
  class ContactAdapter
    extends ArrayAdapter<String>
  {
    public ContactAdapter(ArrayList<String> paramArrayList)
    {
      super(0, localList);
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = paramView;
      if (paramView == null) {
        paramViewGroup = LayoutInflater.from(ContactPickerActivity.this).inflate(R.layout.base_pick_contact_item, null);
      }
      paramView = (String)getItem(paramInt);
      ((TextView)paramViewGroup.findViewById(R.id.base_contact_number)).setText(paramView);
      return paramViewGroup;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/activities/ContactPickerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */