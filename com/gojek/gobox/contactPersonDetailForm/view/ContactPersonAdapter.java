package com.gojek.gobox.contactPersonDetailForm.view;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.util.CircleTransform;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class ContactPersonAdapter
  extends CursorAdapter
{
  private Context mContext;
  private OnAddContactPerson mOnAddContactPerson;
  
  public ContactPersonAdapter(Context paramContext, Cursor paramCursor, OnAddContactPerson paramOnAddContactPerson)
  {
    super(paramContext, paramCursor);
    this.mContext = paramContext;
    this.mOnAddContactPerson = paramOnAddContactPerson;
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    String str = paramCursor.getString(paramCursor.getColumnIndex("display_name"));
    TextView localTextView = (TextView)paramView.findViewById(R.id.text_contact_name);
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.image_contact_photo);
    paramView = (Button)paramView.findViewById(R.id.button_add_contact);
    paramView.setTag(paramCursor.getString(paramCursor.getColumnIndex("contact_id")));
    paramView.setOnClickListener(new ContactPersonAdapter.1(this));
    paramView = paramCursor.getString(paramCursor.getColumnIndex("photo_thumb_uri"));
    Picasso.with(paramContext).load(paramView).placeholder(R.drawable.round).error(R.drawable.round).transform(new CircleTransform()).into(localImageView);
    localTextView.setText(str);
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(R.layout.content_contact_phone, paramViewGroup, false);
  }
  
  public static abstract interface OnAddContactPerson
  {
    public abstract void onButtonAddClick(String paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/contactPersonDetailForm/view/ContactPersonAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */