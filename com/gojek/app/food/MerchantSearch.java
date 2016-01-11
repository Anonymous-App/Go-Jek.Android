package com.gojek.app.food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.android.volley.VolleyError;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.Services;
import com.gojek.app.adapter.BaseItemAdapter;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.DelayTask;
import com.gojek.app.util.DelayTask.CallBack;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class MerchantSearch
  extends GojekActivityBase
  implements TextWatcher
{
  private static final String TAG = MerchantSearch.class.getSimpleName();
  private final int NOT_FOUND_RESTAURANT_ID = 0;
  private final int NOT_FOUND_RESULT_ID = -99;
  private String location;
  private BookingStatus mBookingData;
  private CustomerSaved mCustomer;
  private DelayTask mDelayTask;
  private EditText mETSearch;
  private ListView mLVMerchant;
  private List<Merchant> mListMerchant;
  private BaseItemAdapter<Merchant> mListMerchantAdapter;
  private ProgressBar mMerchantProgress;
  private int mScreenHeight;
  private int mScreenWidth;
  private List<Merchant> notFoundMerchants;
  private ArrayList<RouteItem> otherItems;
  
  private void doLoadMerchant(String paramString)
  {
    this.mLVMerchant.setVisibility(0);
    if (this.mListMerchant.size() == 0) {
      this.mMerchantProgress.setVisibility(0);
    }
    paramString = Util.urlEncode(paramString.trim());
    Log.e("keyword", paramString);
    paramString = String.format("https://api.gojek.co.id/gojek/merchant/find?location=%1s&name=%2s", new Object[] { this.location, paramString });
    Log.i(TAG, "request merchantByName to " + paramString);
    VolleyClient.getInstance(this).getList(TAG, paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MerchantSearch.TAG, "loadMerchant getError " + paramAnonymousVolleyError);
        MerchantSearch.this.mListMerchant.clear();
        MerchantSearch.this.mListMerchant.addAll(MerchantSearch.this.notFoundMerchants);
        MerchantSearch.this.mListMerchantAdapter.notifyDataSetChanged();
        MerchantSearch.this.mMerchantProgress.setVisibility(8);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson;
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.isEmpty())) {
            break label171;
          }
          localGson = new Gson();
          localType = new MerchantSearch.4.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label135;
          }
          paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);
          label57:
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          MerchantSearch.this.mListMerchant.clear();
          if ((paramAnonymousJSONArray == null) || (paramAnonymousJSONArray.isEmpty())) {
            break label148;
          }
          MerchantSearch.this.mListMerchant.addAll(paramAnonymousJSONArray);
          label101:
          MerchantSearch.this.mListMerchantAdapter.notifyDataSetChanged();
        }
        for (;;)
        {
          MerchantSearch.this.mMerchantProgress.setVisibility(8);
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
          label135:
          paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType);
          break label57;
          label148:
          MerchantSearch.this.mListMerchant.addAll(MerchantSearch.this.notFoundMerchants);
          break label101;
          label171:
          MerchantSearch.this.mListMerchant.clear();
          MerchantSearch.this.mListMerchant.addAll(MerchantSearch.this.notFoundMerchants);
          MerchantSearch.this.mListMerchantAdapter.notifyDataSetChanged();
        }
      }
    });
  }
  
  private void doLoadMerchantPage(String paramString, int paramInt)
  {
    if (this.mListMerchant.size() == 0) {
      return;
    }
    paramString = String.format("https://api.gojek.co.id/gojek/merchant/v2/find?name=%s&page=%d&limit=%d", new Object[] { Util.urlEncode(paramString.trim()), Integer.valueOf(paramInt), Integer.valueOf(10) });
    Log.i(TAG, "request merchantByName with page to " + paramString);
    VolleyClient.getInstance(this).getList(TAG, paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(MerchantSearch.TAG, "loadMerchant page getError " + paramAnonymousVolleyError);
        MerchantSearch.this.mMerchantProgress.setVisibility(8);
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        Gson localGson;
        Type localType;
        if (!(paramAnonymousJSONArray instanceof JSONArray))
        {
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          if ((paramAnonymousJSONArray != null) && (!paramAnonymousJSONArray.isEmpty()))
          {
            localGson = new Gson();
            localType = new MerchantSearch.5.1(this).getType();
            if ((localGson instanceof Gson)) {
              break label123;
            }
          }
        }
        label123:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          if ((paramAnonymousJSONArray != null) && (!paramAnonymousJSONArray.isEmpty())) {
            MerchantSearch.this.mListMerchant.addAll(paramAnonymousJSONArray);
          }
          MerchantSearch.this.mListMerchantAdapter.notifyDataSetChanged();
          MerchantSearch.this.mMerchantProgress.setVisibility(8);
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
      }
    });
  }
  
  private List<Merchant> getNotFoundMerchant()
  {
    ArrayList localArrayList = new ArrayList();
    Merchant localMerchant1 = new Merchant();
    localMerchant1.id = 0;
    localMerchant1.name = getString(2131165360);
    localMerchant1.address = getString(2131165887);
    Merchant localMerchant2 = new Merchant();
    localMerchant2.id = -99;
    localMerchant2.name = Html.fromHtml("<center>" + getString(2131165697) + "</center>").toString();
    localMerchant2.address = Html.fromHtml("<center>" + getString(2131165697) + "</center>").toString();
    localArrayList.add(localMerchant2);
    localArrayList.add(localMerchant1);
    return localArrayList;
  }
  
  private void gotoMerchantMenu(Merchant paramMerchant)
  {
    Intent localIntent = new Intent(this, MerchantMenu.class);
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putExtra("MERCHANT", paramMerchant);
    localIntent.putExtra("POI_LAT_LNG", this.location);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
    startActivityForResult(localIntent, 101);
  }
  
  private void renderView()
  {
    setContentView(2130968705);
    this.mLVMerchant = ((ListView)findViewById(2131624559));
    this.mETSearch = ((EditText)findViewById(2131624215));
    this.mMerchantProgress = ((ProgressBar)findViewById(2131624560));
  }
  
  private void resetBookingData()
  {
    ((Addresses)this.mBookingData.addresses.get(0)).merchantId = 0;
    ((Addresses)this.mBookingData.addresses.get(0)).merchant = new Merchant();
    ((Addresses)this.mBookingData.addresses.get(0)).item = null;
    ((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice = 0L;
    ((Addresses)this.mBookingData.addresses.get(0)).routeItems = new ArrayList();
    ((Addresses)this.mBookingData.addresses.get(0)).foodQuantityTotal = 0;
    ((Addresses)this.mBookingData.addresses.get(0)).foodCostTotal = 0;
    this.otherItems = new ArrayList();
  }
  
  public void afterTextChanged(final Editable paramEditable)
  {
    paramEditable = paramEditable.toString();
    if (paramEditable.length() > 2)
    {
      findViewById(2131624214).setBackgroundColor(getResources().getColor(2131558493));
      if (this.mDelayTask != null)
      {
        this.mDelayTask.cancel(true);
        this.mDelayTask = null;
      }
      this.mDelayTask = new DelayTask(600, new DelayTask.CallBack()
      {
        public void onFinish()
        {
          MerchantSearch.this.mLVMerchant.setOnScrollListener(null);
          MerchantSearch.this.doLoadMerchant(paramEditable);
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
    if (paramIntent != null)
    {
      this.mBookingData = ((BookingStatus)paramIntent.getParcelableExtra("BOOKING_DATA"));
      this.otherItems = paramIntent.getParcelableArrayListExtra("OTHER_ITEM");
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    Intent localIntent = new Intent();
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    localIntent.putParcelableArrayListExtra("OTHER_ITEM", this.otherItems);
    setResult(-1, localIntent);
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165763));
    this.action.setIcon(null);
    renderView();
    this.mCustomer = new CustomerSaved(this);
    setScreenSize();
    getWindow().setSoftInputMode(4);
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.location = getIntent().getStringExtra("LAST_LOCATION");
    paramBundle = new Addresses();
    this.mBookingData.addresses.add(paramBundle);
    this.notFoundMerchants = getNotFoundMerchant();
    this.mListMerchant = new ArrayList();
    this.otherItems = new ArrayList();
    this.mListMerchantAdapter = new BaseItemAdapter(this, this.mListMerchant, 2130837877)
    {
      public long getItemId(int paramAnonymousInt)
      {
        return ((Merchant)MerchantSearch.this.mListMerchant.get(paramAnonymousInt)).id;
      }
      
      protected void setData(Merchant paramAnonymousMerchant, TextView paramAnonymousTextView1, TextView paramAnonymousTextView2, ImageView paramAnonymousImageView, TextView paramAnonymousTextView3)
      {
        paramAnonymousTextView1.setVisibility(0);
        paramAnonymousImageView.setVisibility(0);
        paramAnonymousTextView3.setVisibility(0);
        paramAnonymousTextView1.setText(paramAnonymousMerchant.name);
        paramAnonymousTextView3.setText("(" + Util.roundDecimal(Double.valueOf(paramAnonymousMerchant.distance)) + " km)");
        paramAnonymousTextView2.setText(paramAnonymousMerchant.address);
        if (paramAnonymousMerchant.id == 0)
        {
          paramAnonymousImageView.setImageResource(2130838048);
          paramAnonymousTextView3.setVisibility(8);
        }
        if (paramAnonymousMerchant.id == -99)
        {
          paramAnonymousTextView1.setVisibility(8);
          paramAnonymousImageView.setVisibility(8);
          paramAnonymousTextView3.setVisibility(8);
        }
      }
    };
    this.mLVMerchant.setAdapter(this.mListMerchantAdapter);
    this.mLVMerchant.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        int i = ((Merchant)MerchantSearch.this.mListMerchant.get(paramAnonymousInt)).id;
        if (i == 0)
        {
          MerchantSearch.this.mBookingData.serviceType = 3;
          paramAnonymousAdapterView = new Intent(MerchantSearch.this, Services.class);
          paramAnonymousAdapterView.putExtra("BOOKING_DATA", MerchantSearch.this.mBookingData);
          MerchantSearch.this.startActivity(paramAnonymousAdapterView);
          MerchantSearch.this.finish();
        }
        while (i == -99) {
          return;
        }
        if ((((Addresses)MerchantSearch.this.mBookingData.addresses.get(0)).merchant.getId() != 0) && (i != ((Addresses)MerchantSearch.this.mBookingData.addresses.get(0)).merchant.getId()) && ((((Addresses)MerchantSearch.this.mBookingData.addresses.get(0)).routeItems.size() > 0) || (MerchantSearch.this.otherItems.size() > 0)))
        {
          Util.confirmDialog(MerchantSearch.this, new MerchantSearch.2.1(this, paramAnonymousInt), null, MerchantSearch.this.getString(2131165898), "Warning");
          return;
        }
        MerchantSearch.this.gotoMerchantMenu((Merchant)MerchantSearch.this.mListMerchant.get(paramAnonymousInt));
      }
    });
    this.mETSearch.addTextChangedListener(this);
    this.mETSearch.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        MerchantSearch.this.doLoadMerchant(paramAnonymousTextView.getText().toString());
        MerchantSearch.this.hideKeyboard();
        return false;
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/MerchantSearch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */