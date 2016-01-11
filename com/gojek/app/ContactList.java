package com.gojek.app;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ContactList
  extends GojekActivityBase
{
  public static String ID = "ID";
  public static String NAME = "NAME";
  public static String NUMBER = "NUMBER";
  NumberAdapter adapter;
  private AdapterView.OnItemClickListener listItemClickHandler = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      paramAnonymousAdapterView = new Intent();
      paramAnonymousAdapterView.putExtra(ContactList.NUMBER, (String)ContactList.this.numbers.get(paramAnonymousInt));
      paramAnonymousAdapterView.putExtra(ContactList.NAME, ContactList.this.name);
      ContactList.this.setResult(-1, paramAnonymousAdapterView);
      ContactList.this.finish();
    }
  };
  ListView listView;
  String name;
  List<String> numbers;
  
  private void setNumbers(String paramString)
  {
    this.numbers = new ArrayList();
    paramString = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { paramString }, null);
    while (paramString.moveToNext()) {
      this.numbers.add(paramString.getString(paramString.getColumnIndex("data1")));
    }
    paramString.close();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968822);
    setTitle("SELECT CONTACT");
    setNumbers(getIntent().getExtras().getString(ID));
    this.name = getIntent().getExtras().getString(NAME);
    if (this.numbers.size() == 1)
    {
      paramBundle = new Intent();
      paramBundle.putExtra(NUMBER, (String)this.numbers.get(0));
      paramBundle.putExtra(NAME, this.name);
      setResult(-1, paramBundle);
      finish();
      return;
    }
    this.listView = ((ListView)findViewById(2131624916));
    this.adapter = new NumberAdapter();
    this.listView.setAdapter(this.adapter);
    this.listView.setOnItemClickListener(this.listItemClickHandler);
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  class NumberAdapter
    extends BaseAdapter
  {
    NumberAdapter() {}
    
    public int getCount()
    {
      return ContactList.this.numbers.size();
    }
    
    public Object getItem(int paramInt)
    {
      return ContactList.this.numbers.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = paramView;
      if (paramView == null) {
        paramViewGroup = LayoutInflater.from(ContactList.this).inflate(2130968807, null);
      }
      paramView = (TextView)paramViewGroup.findViewById(2131624306);
      ImageView localImageView = (ImageView)paramViewGroup.findViewById(2131624305);
      paramView.setText((CharSequence)ContactList.this.numbers.get(paramInt));
      return paramViewGroup;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/ContactList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */