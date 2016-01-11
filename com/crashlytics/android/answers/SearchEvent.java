package com.crashlytics.android.answers;

public class SearchEvent
  extends PredefinedEvent<SearchEvent>
{
  static final String QUERY_ATTRIBUTE = "query";
  static final String TYPE = "search";
  
  String getPredefinedType()
  {
    return "search";
  }
  
  public SearchEvent putQuery(String paramString)
  {
    this.predefinedAttributes.put("query", paramString);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SearchEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */