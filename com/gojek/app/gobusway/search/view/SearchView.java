package com.gojek.app.gobusway.search.view;

import com.gojek.app.gobusway.model.Halte;
import java.util.ArrayList;

public abstract interface SearchView
{
  public abstract void initListView();
  
  public abstract void showAllHalte(ArrayList<Halte> paramArrayList);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/search/view/SearchView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */