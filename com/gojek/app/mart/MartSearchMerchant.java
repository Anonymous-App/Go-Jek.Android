package com.gojek.app.mart;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.Services;
import com.gojek.app.adapter.mart.SearchMerchantAdapter;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.DelayTask;
import com.gojek.app.util.DelayTask.CallBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MartSearchMerchant
  extends GojekActivityBase
  implements TextWatcher
{
  private static final String TAG = MartSearchMerchant.class.getSimpleName();
  public String LOCATION_LAT = "LOCATION_LAT";
  private String LOCATION_LNG = "LOCATION_LNG";
  private final int NOT_FOUND_MERCHANT_ID = 0;
  private final int REQUEST_ITEMS = 12;
  private String location;
  private BookingStatus mBookingData;
  private DelayTask mDelayTask;
  private EditText mETSearch;
  private ImageView mIVSearch;
  private ListView mLVMerchant;
  private ListView mLVRecentItem;
  private List<MartMerchant> mListMerchant;
  private SearchMerchantAdapter<MartMerchant> mListMerchantAdapter;
  private ProgressBar mMerchantProgress;
  private int mScreenHeight;
  private int mScreenWidth;
  private ArrayList<RouteItem> otherRouteItems;
  
  private void golekLokal(String paramString)
  {
    Object localObject2 = getIntent().getStringExtra("JSON");
    Object localObject1;
    if ((localObject2 != null) && (!((String)localObject2).toString().isEmpty()))
    {
      localObject1 = new Gson();
      localObject2 = ((String)localObject2).toString();
      Object localObject3 = new TypeToken() {}.getType();
      if (!(localObject1 instanceof Gson)) {}
      for (localObject1 = ((Gson)localObject1).fromJson((String)localObject2, (Type)localObject3);; localObject1 = GsonInstrumentation.fromJson((Gson)localObject1, (String)localObject2, (Type)localObject3))
      {
        localObject2 = (List)localObject1;
        localObject1 = new ArrayList();
        if ((localObject2 == null) || (((List)localObject2).size() <= 0)) {
          break;
        }
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (MartMerchant)((Iterator)localObject2).next();
          if ((((MartMerchant)localObject3).martName.toLowerCase().indexOf(paramString.toLowerCase()) != -1) || (((MartMerchant)localObject3).martCategory.toLowerCase().indexOf(paramString.toLowerCase()) != -1)) {
            ((List)localObject1).add(localObject3);
          }
        }
      }
      if (((List)localObject1).size() != 0)
      {
        int j = 0;
        while (j < ((List)localObject1).size())
        {
          int k = 0;
          int m = 1;
          paramString = new StringBuilder(((MartMerchant)((List)localObject1).get(j)).martCategory.toLowerCase());
          if (k < paramString.length())
          {
            int i;
            if (paramString.charAt(k) == ' ') {
              i = 1;
            }
            for (;;)
            {
              k += 1;
              m = i;
              break;
              i = m;
              if (m != 0)
              {
                i = m;
                if (!Character.isWhitespace(paramString.charAt(k)))
                {
                  paramString.setCharAt(k, Character.toUpperCase(paramString.charAt(k)));
                  i = 0;
                }
              }
            }
          }
          ((MartMerchant)((List)localObject1).get(j)).martCategory = paramString.toString();
          j += 1;
        }
        this.mListMerchant.clear();
        paramString = new MartMerchant();
        paramString.id = 0;
        paramString.martName = "Can't find your store?";
        paramString.martCategory = getString(2131165887);
        ((List)localObject1).add(paramString);
        this.mListMerchant.addAll((Collection)localObject1);
        this.mListMerchantAdapter.notifyDataSetChanged();
        this.mMerchantProgress.setVisibility(8);
      }
    }
    else
    {
      return;
    }
    this.mListMerchant.clear();
    paramString = new MartMerchant();
    paramString.id = 0;
    paramString.martName = "Can't find your store?";
    paramString.martCategory = getString(2131165887);
    ((List)localObject1).add(paramString);
    this.mListMerchant.addAll((Collection)localObject1);
    this.mListMerchantAdapter.notifyDataSetChanged();
    this.mMerchantProgress.setVisibility(8);
  }
  
  private void renderView()
  {
    setContentView(2130968770);
    this.mLVMerchant = ((ListView)findViewById(2131624559));
    this.mLVRecentItem = ((ListView)findViewById(2131624831));
    this.mETSearch = ((EditText)findViewById(2131624215));
    this.mMerchantProgress = ((ProgressBar)findViewById(2131624560));
    this.mIVSearch = ((ImageView)findViewById(2131624216));
  }
  
  private void search()
  {
    this.mMerchantProgress.setVisibility(0);
    final Object localObject = this.mETSearch.getText().toString();
    if (((String)localObject).length() >= 3)
    {
      findViewById(2131624214).setBackgroundColor(getResources().getColor(2131558493));
      this.mLVMerchant.setVisibility(0);
      if (this.mDelayTask != null)
      {
        this.mDelayTask.cancel(true);
        this.mDelayTask = null;
      }
      this.mDelayTask = new DelayTask(600, new DelayTask.CallBack()
      {
        public void onFinish()
        {
          MartSearchMerchant.this.mLVMerchant.setOnScrollListener(null);
          MartSearchMerchant.this.mLVMerchant.setOnScrollListener(new MartSearchMerchant.6.1(this, 10));
          MartSearchMerchant.this.golekLokal(localObject);
        }
      });
      localObject = this.mDelayTask;
      Void[] arrayOfVoid = new Void[0];
      if (!(localObject instanceof AsyncTask))
      {
        ((DelayTask)localObject).execute(arrayOfVoid);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)localObject, arrayOfVoid);
      return;
    }
    findViewById(2131624214).setBackgroundColor(0);
    this.mMerchantProgress.setVisibility(8);
    this.mLVMerchant.setVisibility(8);
    this.mListMerchant.clear();
    this.mListMerchantAdapter.notifyDataSetChanged();
  }
  
  public void afterTextChanged(final Editable paramEditable)
  {
    paramEditable = paramEditable.toString();
    this.mMerchantProgress.setVisibility(0);
    if (paramEditable.length() >= 3)
    {
      findViewById(2131624214).setBackgroundColor(getResources().getColor(2131558493));
      this.mLVMerchant.setVisibility(0);
      if (this.mDelayTask != null)
      {
        this.mDelayTask.cancel(true);
        this.mDelayTask = null;
      }
      this.mDelayTask = new DelayTask(600, new DelayTask.CallBack()
      {
        public void onFinish()
        {
          MartSearchMerchant.this.mLVMerchant.setOnScrollListener(null);
          MartSearchMerchant.this.mLVMerchant.setOnScrollListener(new MartSearchMerchant.5.1(this, 10));
          MartSearchMerchant.this.golekLokal(paramEditable);
        }
      });
      paramEditable = this.mDelayTask;
      Void[] arrayOfVoid = new Void[0];
      if (!(paramEditable instanceof AsyncTask))
      {
        paramEditable.execute(arrayOfVoid);
        return;
      }
      AsyncTaskInstrumentation.execute((AsyncTask)paramEditable, arrayOfVoid);
      return;
    }
    findViewById(2131624214).setBackgroundColor(0);
    this.mMerchantProgress.setVisibility(8);
    this.mLVMerchant.setVisibility(8);
    this.mListMerchant.clear();
    this.mListMerchantAdapter.notifyDataSetChanged();
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherRouteItems);
    setResult(-1, localIntent);
    super.onBackPressed();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle("SEARCH STORE");
    this.action.setIcon(null);
    renderView();
    setScreenSize();
    getWindow().setSoftInputMode(4);
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.otherRouteItems = getIntent().getParcelableArrayListExtra("OTHER_ITEM");
    this.location = getIntent().getStringExtra("LOC_LAT");
    new Addresses();
    this.mListMerchant = new ArrayList();
    this.mListMerchantAdapter = new SearchMerchantAdapter(this, this.mListMerchant)
    {
      public long getItemId(int paramAnonymousInt)
      {
        return ((MartMerchant)MartSearchMerchant.this.mListMerchant.get(paramAnonymousInt)).id;
      }
      
      protected void setData(MartMerchant paramAnonymousMartMerchant, TextView paramAnonymousTextView1, TextView paramAnonymousTextView2)
      {
        paramAnonymousTextView1.setVisibility(0);
        paramAnonymousTextView1.setText(paramAnonymousMartMerchant.martName);
        paramAnonymousTextView2.setText(paramAnonymousMartMerchant.martCategory);
      }
    };
    this.mLVMerchant.setAdapter(this.mListMerchantAdapter);
    this.mLVMerchant.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
        int i = ((MartMerchant)MartSearchMerchant.this.mListMerchant.get(paramAnonymousInt)).id;
        int j = ((MartMerchant)MartSearchMerchant.this.mListMerchant.get(paramAnonymousInt)).martId;
        paramAnonymousView = ((MartMerchant)MartSearchMerchant.this.mListMerchant.get(paramAnonymousInt)).martName;
        if (i == 0)
        {
          MartSearchMerchant.this.mBookingData.serviceType = 3;
          paramAnonymousAdapterView = new Intent(MartSearchMerchant.this, Services.class);
          paramAnonymousAdapterView.putExtra("BOOKING_DATA", MartSearchMerchant.this.mBookingData);
          MartSearchMerchant.this.startActivity(paramAnonymousAdapterView);
          MartSearchMerchant.this.finish();
          return;
        }
        if ((j == ((Addresses)MartSearchMerchant.this.mBookingData.addresses.get(0)).martMerchant.martId) || (((Addresses)MartSearchMerchant.this.mBookingData.addresses.get(0)).martMerchant.martId == 0))
        {
          Intent localIntent = new Intent(MartSearchMerchant.this, MerchantCategoryMenu.class);
          localIntent.putExtra("MART_ID", String.valueOf(j));
          localIntent.putExtra("MART_MERCHANT", (MartMerchant)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt));
          localIntent.putExtra("BOOKING_DATA", MartSearchMerchant.this.mBookingData);
          localIntent.putParcelableArrayListExtra("OTHER_ITEM", MartSearchMerchant.this.otherRouteItems);
          localIntent.putExtra("MART_NAME", paramAnonymousView);
          MartSearchMerchant.this.startActivityForResult(localIntent, 12);
          return;
        }
        new AlertDialog.Builder(MartSearchMerchant.this).setTitle("You Can't choose diferent store in one order").setMessage("Are you sure move to this store? ").setNegativeButton(17039360, null).setPositiveButton(17039370, new MartSearchMerchant.2.1(this, paramAnonymousAdapterView, paramAnonymousInt, j, paramAnonymousView)).create().show();
      }
    });
    this.mETSearch.addTextChangedListener(this);
    this.mETSearch.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        MartSearchMerchant.this.hideKeyboard();
        MartSearchMerchant.this.search();
        return true;
      }
    });
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  protected void onStop()
  {
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    super.onStop();
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  @SuppressLint({"NewApi"})
  public void setScreenSize()
  {
    if (Build.VERSION.SDK_INT >= 11) {
      localObject = new Point();
    }
    try
    {
      getWindowManager().getDefaultDisplay().getRealSize((Point)localObject);
      this.mScreenWidth = ((Point)localObject).x;
      this.mScreenHeight = ((Point)localObject).y;
      return;
    }
    catch (NoSuchMethodError localNoSuchMethodError) {}
    Object localObject = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    this.mScreenWidth = ((DisplayMetrics)localObject).widthPixels;
    this.mScreenHeight = ((DisplayMetrics)localObject).heightPixels;
    return;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/mart/MartSearchMerchant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */