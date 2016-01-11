package com.gojek.app;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.flurry.android.FlurryAgent;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.PoiHistory;
import com.gojek.app.util.GojekPreference;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class PickLocation
  extends GojekActivityBase
  implements View.OnClickListener, View.OnFocusChangeListener
{
  public static String CURRENT_LOCATION = "CURRENT_LOCATION";
  public static String CUSTOM_LOCATION;
  public static String LOCATION_ADDRESS;
  public static String LOCATION_DESC;
  public static String LOCATION_ID;
  public static String LOCATION_LAT;
  public static String LOCATION_LNG;
  public static String LOCATION_NAME;
  public static String PAST_LOCATION = "PAST_LOCATION";
  private static final String TAG = PickLocation.class.getSimpleName();
  private final int REQUEST_LOCATION = 89;
  private CustomerSaved mCustomer;
  private EditText mETSearch;
  private HistoryAdapter mHistoryAdapter;
  private LinearLayout mLLCurrent;
  private ListView mLVHistory;
  private List<PoiHistory> mListHistory;
  
  static
  {
    LOCATION_NAME = "LOCATION_NAME";
    LOCATION_LAT = "LOCATION_LAT";
    LOCATION_LNG = "LOCATION_LNG";
    LOCATION_ID = "LOCATION_ID";
    LOCATION_ADDRESS = "LOCATION_ADDRESS";
    LOCATION_DESC = "LOCATION_DESC";
    CUSTOM_LOCATION = "CUSTOM_LOCATION";
  }
  
  private void doLoadHistory()
  {
    if (this.mCustomer.customerId == null) {
      return;
    }
    this.mListHistory.clear();
    findViewById(2131624286).setVisibility(0);
    String str = String.format("https://api.gojek.co.id/gojek/v2/customer/v2/history/%s", new Object[] { this.mCustomer.customerId });
    VolleyClient.getInstance(this).getList(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(PickLocation.TAG, "error load customer history " + paramAnonymousVolleyError);
        PickLocation.this.findViewById(2131624286).setVisibility(8);
        if ((paramAnonymousVolleyError instanceof AuthFailureError)) {
          PickLocation.this.doSessionAuthenticate();
        }
      }
      
      public void onResponse(JSONArray paramAnonymousJSONArray)
      {
        PickLocation.this.findViewById(2131624286).setVisibility(8);
        Gson localGson;
        Type localType;
        if ((paramAnonymousJSONArray != null) && (paramAnonymousJSONArray.length() > 0))
        {
          localGson = new Gson();
          if ((paramAnonymousJSONArray instanceof JSONArray)) {
            break label112;
          }
          paramAnonymousJSONArray = paramAnonymousJSONArray.toString();
          localType = new PickLocation.2.1(this).getType();
          if ((localGson instanceof Gson)) {
            break label123;
          }
        }
        label112:
        label123:
        for (paramAnonymousJSONArray = localGson.fromJson(paramAnonymousJSONArray, localType);; paramAnonymousJSONArray = GsonInstrumentation.fromJson((Gson)localGson, paramAnonymousJSONArray, localType))
        {
          paramAnonymousJSONArray = (List)paramAnonymousJSONArray;
          PickLocation.this.mListHistory.addAll(paramAnonymousJSONArray);
          PickLocation.this.mCustomer.saveHistoryList(paramAnonymousJSONArray);
          PickLocation.this.mHistoryAdapter.notifyDataSetChanged();
          return;
          paramAnonymousJSONArray = JSONArrayInstrumentation.toString((JSONArray)paramAnonymousJSONArray);
          break;
        }
      }
    }, this.mCustomer.getAccessToken());
  }
  
  private void loadHistoryFromPref()
  {
    if ((this.mCustomer.history == null) || (this.mCustomer.history.isEmpty()))
    {
      doLoadHistory();
      return;
    }
    List localList = this.mCustomer.getHistoryList();
    this.mListHistory.addAll(localList);
    this.mHistoryAdapter.notifyDataSetChanged();
  }
  
  private void renderView()
  {
    setContentView(2130968808);
    this.mLVHistory = ((ListView)findViewById(2131624920));
    this.mLLCurrent = ((LinearLayout)findViewById(2131624917));
    this.mETSearch = ((EditText)findViewById(2131624215));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 89))
    {
      if (!paramIntent.getBooleanExtra("current", false))
      {
        paramIntent.putExtra(CUSTOM_LOCATION, true);
        setResult(-1, paramIntent);
        finish();
      }
    }
    else if ((paramInt2 == -1) && (paramInt1 == 401))
    {
      if (!getIntent().getBooleanExtra("CACHE_LOCATION_HISTORY", false)) {
        break label98;
      }
      Log.i(TAG, "load customer history from preference after authenticate session");
      loadHistoryFromPref();
    }
    for (;;)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      paramIntent.putExtra(CURRENT_LOCATION, true);
      break;
      label98:
      Log.i(TAG, "load customer history from server after authenticate session");
      doLoadHistory();
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    paramView = (LocationManager)getSystemService("location");
    if ((paramView.isProviderEnabled("gps")) || (paramView.isProviderEnabled("network")))
    {
      paramView = new Intent(this, ChooseFromMap.class);
      paramView.putExtra("current", true);
      startActivityForResult(paramView, 89);
      return;
    }
    startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165727));
    this.action.setIcon(null);
    renderView();
    this.mCustomer = new CustomerSaved(this);
    this.mLLCurrent.setOnClickListener(this);
    this.mETSearch.setOnFocusChangeListener(this);
    this.mListHistory = new ArrayList();
    this.mHistoryAdapter = new HistoryAdapter(this, this.mListHistory);
    this.mLVHistory.setAdapter(this.mHistoryAdapter);
    this.mLVHistory.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        FlurryAgent.logEvent("Use_History_Location");
        paramAnonymousView = (PoiHistory)PickLocation.this.mListHistory.get(paramAnonymousInt);
        paramAnonymousAdapterView = new Intent();
        paramAnonymousAdapterView.putExtra(PickLocation.LOCATION_NAME, paramAnonymousView.name);
        paramAnonymousAdapterView.putExtra(PickLocation.LOCATION_ID, 0);
        paramAnonymousAdapterView.putExtra(PickLocation.LOCATION_ADDRESS, paramAnonymousView.address);
        paramAnonymousAdapterView.putExtra(PickLocation.LOCATION_DESC, paramAnonymousView.note);
        paramAnonymousView = paramAnonymousView.latLong.split(",");
        paramAnonymousAdapterView.putExtra(PickLocation.LOCATION_LAT, Double.parseDouble(paramAnonymousView[0]));
        paramAnonymousAdapterView.putExtra(PickLocation.LOCATION_LNG, Double.parseDouble(paramAnonymousView[1]));
        paramAnonymousAdapterView.putExtra(PickLocation.PAST_LOCATION, true);
        PickLocation.this.setResult(-1, paramAnonymousAdapterView);
        PickLocation.this.finish();
      }
    });
    if (getIntent().getBooleanExtra("CACHE_LOCATION_HISTORY", false))
    {
      Log.i(TAG, "load customer history from preference");
      loadHistoryFromPref();
      return;
    }
    Log.i(TAG, "load customer history from server");
    doLoadHistory();
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramView = new Intent(this, ChooseFromMap.class);
      paramView.putExtra("current", false);
      startActivityForResult(paramView, 89);
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 5) {
      doLoadHistory();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    paramMenu.add(0, 5, 0, getString(2131165762)).setIcon(2130837926);
    int i = 0;
    while (i < paramMenu.size())
    {
      MenuItemCompat.setShowAsAction(paramMenu.getItem(i), 1);
      i += 1;
    }
    return true;
  }
  
  protected void onResume()
  {
    this.mCustomer = new CustomerSaved(this);
    this.mETSearch.clearFocus();
    String str = GojekPreference.getInstance(this).getString("CACHE_KEY", null);
    if (!TextUtils.isEmpty(str)) {
      this.mETSearch.setText(str);
    }
    for (;;)
    {
      super.onResume();
      return;
      this.mETSearch.setText("");
    }
  }
  
  class HistoryAdapter
    extends BaseAdapter
  {
    LayoutInflater layoutInflater;
    List<PoiHistory> poiHistoryList;
    
    public HistoryAdapter(List<PoiHistory> paramList)
    {
      List localList;
      this.poiHistoryList = localList;
      this.layoutInflater = ((LayoutInflater)paramList.getSystemService("layout_inflater"));
    }
    
    public int getCount()
    {
      return this.poiHistoryList.size();
    }
    
    public PoiHistory getItem(int paramInt)
    {
      return (PoiHistory)this.poiHistoryList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      PoiHistory localPoiHistory = getItem(paramInt);
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968809, null);
        paramViewGroup = new PickLocation.ViewHolder(PickLocation.this);
        paramViewGroup.mIV = ((ImageView)paramView.findViewById(2131624305));
        paramViewGroup.mTVName = ((TextView)paramView.findViewById(2131624306));
        paramViewGroup.mTVNote = ((TextView)paramView.findViewById(2131624422));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        paramViewGroup.mTVName.setText(localPoiHistory.name);
        paramViewGroup.mTVNote.setText(localPoiHistory.note);
        paramViewGroup.mIV.setImageResource(2130837941);
        return paramView;
        paramViewGroup = (PickLocation.ViewHolder)paramView.getTag();
      }
    }
  }
  
  class ViewHolder
  {
    ImageView mIV;
    TextView mTVName;
    TextView mTVNote;
    
    ViewHolder() {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/PickLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */