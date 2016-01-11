package com.gojek.app.mart;

import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.adapter.mart.MartHistoryAdapter;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import java.util.ArrayList;
import java.util.List;

public class MartHistory
  extends GojekActivityBase
{
  private List<BookingStatus> listMakeBooking;
  private LinearLayout mLLNoItem;
  private ListView mLVMartHistory;
  private TextView mTVNoHistory;
  private MartHistoryAdapter martHistoryAdapter;
  
  public void init()
  {
    Object localObject = new CustomerSaved(this);
    int i;
    if (((CustomerSaved)localObject).getHistoryBooking() != null)
    {
      if (martIdActived > -1)
      {
        this.listMakeBooking = new ArrayList();
        i = 0;
        while (i < ((CustomerSaved)localObject).getHistoryBooking().size())
        {
          if (((Addresses)((BookingStatus)((CustomerSaved)localObject).getHistoryBooking().get(i)).addresses.get(0)).martMerchant.martId == martIdActived) {
            this.listMakeBooking.add(((CustomerSaved)localObject).getHistoryBooking().get(i));
          }
          i += 1;
        }
      }
      this.listMakeBooking = ((CustomerSaved)localObject).getHistoryBooking();
      localObject = this.mLLNoItem;
      if (this.listMakeBooking.size() > 0)
      {
        i = 8;
        ((LinearLayout)localObject).setVisibility(i);
      }
    }
    for (;;)
    {
      this.martHistoryAdapter = new MartHistoryAdapter(getApplicationContext(), this.listMakeBooking);
      this.mLVMartHistory.setAdapter(this.martHistoryAdapter);
      return;
      i = 0;
      break;
      this.listMakeBooking = new ArrayList();
    }
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle("HISTORY");
    setContentView(2130968753);
    this.mLVMartHistory = ((ListView)findViewById(2131624793));
    this.mTVNoHistory = ((TextView)findViewById(2131624792));
    this.mLLNoItem = ((LinearLayout)findViewById(2131624791));
    init();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MartHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */