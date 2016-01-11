package com.gojek.gobox.contactPersonDetailForm.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class ContactPicker
  extends BaseActivity
{
  private ContactAdapter adapter;
  private String mId;
  private ListView mListView;
  private String mName;
  private List<String> mPhoneNumbers;
  
  private List<String> populateNumbers(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { paramString }, null);
    while (paramString.moveToNext()) {
      localArrayList.add(paramString.getString(paramString.getColumnIndex("data1")));
    }
    paramString.close();
    return localArrayList;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.pick_contact);
    initToolbarView();
    setTitle(getString(R.string.select_contact_title));
    this.mName = getIntent().getExtras().getString("contact name");
    this.mId = getIntent().getExtras().getString("contact id");
    this.mPhoneNumbers = populateNumbers(this.mId);
    this.mListView = ((ListView)findViewById(R.id.mLV));
    this.adapter = new ContactAdapter();
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(new ContactPicker.1(this));
  }
  
  class ContactAdapter
    extends BaseAdapter
  {
    ContactAdapter() {}
    
    public int getCount()
    {
      return ContactPicker.this.mPhoneNumbers.size();
    }
    
    public Object getItem(int paramInt)
    {
      return ContactPicker.this.mPhoneNumbers.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = paramView;
      if (paramView == null) {
        paramViewGroup = LayoutInflater.from(ContactPicker.this).inflate(R.layout.pick_contact_item, null);
      }
      ((TextView)paramViewGroup.findViewById(R.id.mTVName)).setText((CharSequence)ContactPicker.this.mPhoneNumbers.get(paramInt));
      return paramViewGroup;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/contactPersonDetailForm/view/ContactPicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */