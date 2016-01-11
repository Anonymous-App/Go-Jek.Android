package com.gojek.app.gobusway.adapter;

import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import com.gojek.app.gobusway.model.Halte;
import java.util.ArrayList;

class SearchHalteAdapter$1
  extends Filter
{
  SearchHalteAdapter$1(SearchHalteAdapter paramSearchHalteAdapter) {}
  
  protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
  {
    paramCharSequence = paramCharSequence.toString().toLowerCase();
    Filter.FilterResults localFilterResults = new Filter.FilterResults();
    ArrayList localArrayList1 = SearchHalteAdapter.access$100(this.this$0);
    int j = localArrayList1.size();
    ArrayList localArrayList2 = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      if ((((Halte)localArrayList1.get(i)).getName().toLowerCase().contains(paramCharSequence)) || (TextUtils.isEmpty(paramCharSequence))) {
        localArrayList2.add(localArrayList1.get(i));
      }
      i += 1;
    }
    localFilterResults.values = localArrayList2;
    localFilterResults.count = localArrayList2.size();
    return localFilterResults;
  }
  
  protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
  {
    SearchHalteAdapter.access$202(this.this$0, (ArrayList)paramFilterResults.values);
    this.this$0.notifyDataSetChanged();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/adapter/SearchHalteAdapter$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */